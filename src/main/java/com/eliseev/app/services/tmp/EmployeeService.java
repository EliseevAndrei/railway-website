package com.eliseev.app.services.tmp;

import com.eliseev.app.models.tmp.Employee;
import com.eliseev.app.repository.tmp.EmployeeDAO;
import com.eliseev.app.services.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService extends AbstractService<Employee, EmployeeDAO> {

    @Autowired
    public EmployeeService(EmployeeDAO dao) {
        super(dao);
    }

    @Transactional(readOnly = true)
    public Employee findOne(long id) {
        return dao.findOne(id);
    }

    @Transactional
    public Employee save(Employee employee) {
        return dao.save(employee);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public long count() {
        return dao.count();
    }

    @Transactional
    public Employee update(Employee employee) {
        return dao.save(employee);
    }

}
