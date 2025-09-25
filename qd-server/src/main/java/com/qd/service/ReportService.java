package com.qd.service;

import com.qd.vo.OrderReportVO;
import com.qd.vo.SalesTop10ReportVO;
import com.qd.vo.TurnoverReportVO;
import com.qd.vo.UserReportVO;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public interface ReportService {
    TurnoverReportVO getTurnoverStatistics(LocalDate startDate, LocalDate endDate);

    UserReportVO getUserStatistics(LocalDate startDate, LocalDate endDate);

    OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end);

    SalesTop10ReportVO getTop10Statistics(LocalDate begin, LocalDate end);

    void exportBusinessData(HttpServletResponse response);
}
