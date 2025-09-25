package com.qd.service;

import com.qd.dto.EmployeeDTO;
import com.qd.dto.EmployeeLoginDTO;
import com.qd.dto.EmployeePageQueryDTO;
import com.qd.entity.Employee;
import com.qd.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void save(EmployeeDTO employeeDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void startOrStop(Integer status, Long id);

    Employee getById(Long id);

    void update(EmployeeDTO employeeDTO);
}
