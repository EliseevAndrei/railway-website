package com.eliseev.app.repository.tmp;

import com.eliseev.app.models.tmp.Department;
import com.eliseev.app.models.tmp.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = manager.createQuery("select a from Employee a", Employee.class).getResultList();
        return employees;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> depts = manager.createQuery("Select a From Department a", Department.class).getResultList();
        return depts;
    }

    public Department getDepartmentById(Integer id)
    {
        return manager.find(Department.class, id);
    }

    @Override
    public void addEmployee(Employee employee) {
        employee.setDepartment(getDepartmentById(employee.getDepartment().getId()));
        manager.persist(employee);
    }
}
