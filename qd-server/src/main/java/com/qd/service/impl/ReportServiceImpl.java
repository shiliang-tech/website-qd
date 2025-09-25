package com.qd.service.impl;

import com.qd.dto.GoodsSalesDTO;
import com.qd.entity.Orders;
import com.qd.mapper.OrderMapper;
import com.qd.mapper.ReportMapper;
import com.qd.mapper.UserMapper;
import com.qd.service.ReportService;
import com.qd.service.WorkspaceService;
import com.qd.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WorkspaceService workspaceService;

    /**
     * 统计指定时间段的营业额数据
     * @param startDate
     * @param endDate
     * @return
     */
    public TurnoverReportVO getTurnoverStatistics(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> datelist=new ArrayList<>();
        datelist.add(startDate);
        while (!startDate.equals(endDate)) {
            //日期计算，计算指定日期的后一天对应的日期
            startDate = startDate.plusDays(1);
            datelist.add(startDate);
        }

        List<Double> turnoverList=new ArrayList<>();
        for (LocalDate date : datelist) {
            //查询对应营业额
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            HashMap hashMap = new HashMap();
            hashMap.put("begin", beginTime);
            hashMap.put("end", endTime);
            hashMap.put("status", Orders.COMPLETED);
            Double turnover = orderMapper.sumByMap(hashMap);
            turnover = turnover == null ? 0.0 : turnover;
            turnoverList.add(turnover);
        }

        return TurnoverReportVO.builder()
                .dateList(StringUtils.join(datelist, ","))
                .turnoverList(StringUtils.join(turnoverList, ","))
                .build();
    }

    /**
     * 统计指定时间段的用户数据
     * @param startDate
     * @param endDate
     * @return
     */
    public UserReportVO getUserStatistics(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> datelist=new ArrayList<>();
        datelist.add(startDate);
        while (!startDate.equals(endDate)) {
            startDate = startDate.plusDays(1);
            datelist.add(startDate);
        }
        List<Integer> totalUserList=new ArrayList<>();
        List<Integer> newUserList=new ArrayList<>();

        for (LocalDate date : datelist) {
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            HashMap hashMap = new HashMap();
            hashMap.put("end", endTime);
            Integer total = userMapper.countByMap(hashMap);
            totalUserList.add(total);
            hashMap.put("begin", beginTime);
            Integer newNumber = userMapper.countByMap(hashMap);
            newUserList.add(newNumber);

        }

        return UserReportVO.builder()
                .dateList(StringUtils.join(datelist, ","))
                .totalUserList(StringUtils.join(totalUserList, ","))
                .newUserList(StringUtils.join(newUserList, ","))
                .build();
    }


    /**
     * 统计指定时间段的用户数据
     * @param begin
     * @param end
     * @return
     */
    public OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> datelist=new ArrayList<>();
        datelist.add(begin);
        while (!begin.equals(end)) {
            begin = begin.plusDays(1);
            datelist.add(begin);
        }

        List<Integer> orderCountList=new ArrayList<>();
        List<Integer> validOrderCountList=new ArrayList<>();

        for (LocalDate localDate : datelist) {
            //查每天订单总数
            LocalDateTime beginTime = LocalDateTime.of(localDate, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(localDate, LocalTime.MAX);
            Integer orderCount = getOrderCount(beginTime, endTime, null);
            orderCountList.add(orderCount);
            //查每天有效订单数
            Integer validCount = getOrderCount(beginTime, endTime, Orders.COMPLETED);
            validOrderCountList.add(validCount);
        }

        Integer totalOrderCount = orderCountList.stream().reduce(Integer::sum).get();
        Integer validOrderCount = validOrderCountList.stream().reduce(Integer::sum).get();

        Double orderCompletionRate = 0.0;
        if (totalOrderCount != 0) {
            orderCompletionRate = validOrderCount.doubleValue() / totalOrderCount;
        }

        return OrderReportVO.builder()
                .dateList(StringUtils.join(datelist, ","))
                .orderCountList(StringUtils.join(orderCountList, ","))
                .validOrderCountList(StringUtils.join(validOrderCountList, ","))
                .orderCompletionRate(orderCompletionRate)
                .totalOrderCount(totalOrderCount)
                .validOrderCount(validOrderCount)
                .build();
    }

    /**
     * 统计指定时间段的销量前十
     * @param begin
     * @param end
     * @return
     */
    public SalesTop10ReportVO getTop10Statistics(LocalDate begin, LocalDate end) {
        LocalDateTime beginTime = LocalDateTime.of(begin, LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(end, LocalTime.MAX);
        List<GoodsSalesDTO> list = orderMapper.getSalesTop(beginTime, endTime);
        List<String> names = list.stream().map(GoodsSalesDTO::getName).collect(Collectors.toList());
        String nameList = StringUtils.join(names, ",");

        List<Integer> number = list.stream().map(GoodsSalesDTO::getNumber).collect(Collectors.toList());
        String numberList = StringUtils.join(number, ",");

        return SalesTop10ReportVO.builder()
                .nameList(nameList)
                .numberList(numberList)
                .build();
    }

    /**
     * 导出运营数据报表
     * @param response
     */
    public void exportBusinessData(HttpServletResponse response) {
        //查最近30天的运营数据
        LocalDate dateBegin = LocalDate.now().minusDays(30);
        LocalDate end = LocalDate.now().minusDays(1);
        BusinessDataVO businessDataVO = workspaceService.getBusinessData(LocalDateTime.of(dateBegin, LocalTime.MIN), LocalDateTime.of(end, LocalTime.MAX));
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("template/运营数据报表模板.xlsx");

        try {
            XSSFWorkbook excel = new XSSFWorkbook(in);
            //填充数据--时间
            XSSFSheet sheet = excel.getSheet("Sheet1");

            sheet.getRow(1).getCell(1).setCellValue("时间：" + dateBegin + "至" + end);

            // 获得第4行
            XSSFRow row = sheet.getRow(3);
            row.getCell(2).setCellValue(businessDataVO.getTurnover());
            row.getCell(4).setCellValue(businessDataVO.getOrderCompletionRate());
            row.getCell(6).setCellValue(businessDataVO.getNewUsers());

            // 获得第5行
            row = sheet.getRow(4);
            row.getCell(2).setCellValue(businessDataVO.getValidOrderCount());
            row.getCell(4).setCellValue(businessDataVO.getUnitPrice());

            // 填充明细数据
            for (int i = 0; i < 30; i++) {
                LocalDate date = dateBegin.plusDays(i);
                // 查询某一天的营业数据
                BusinessDataVO businessData = workspaceService.getBusinessData(LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX));

                // 获得某一行
                row = sheet.getRow(7 + i);
                row.getCell(1).setCellValue(date.toString());
                row.getCell(2).setCellValue(businessData.getTurnover());
                row.getCell(3).setCellValue(businessData.getValidOrderCount());
                row.getCell(4).setCellValue(businessData.getOrderCompletionRate());
                row.getCell(5).setCellValue(businessData.getUnitPrice());
                row.getCell(6).setCellValue(businessData.getNewUsers());
            }


            ServletOutputStream out = response.getOutputStream();
            excel.write(out);

            //关闭资源
            out.close();
            excel.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer getOrderCount(LocalDateTime begin, LocalDateTime end, Integer status) {
        HashMap hashMap = new HashMap();
        hashMap.put("begin", begin);
        hashMap.put("end", end);
        hashMap.put("status", status);
        return orderMapper.countByMap(hashMap);
    }
}
