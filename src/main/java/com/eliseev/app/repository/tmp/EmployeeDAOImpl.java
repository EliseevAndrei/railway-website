package com.eliseev.app.repository.tmp;

import com.eliseev.app.models.tmp.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

    @PersistenceContext
    private EntityManager manager;

    @Override
    public long count() {
        logger.info("some info");
        return findAll().size();
    }

    @Override
    public Employee findOne(long id) {
        return manager.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee) {

        if (employee.getId() == null)
            manager.persist(employee);
        else
            return manager.merge(employee);

        return employee;
    }


    @Override
    public void delete(long id) {
        manager.remove(findOne(id));
    }

    @Override
    public List<Employee> findAll() {
        return manager.createQuery("select s from Employee s", Employee.class)
                .getResultList();
    }

}


