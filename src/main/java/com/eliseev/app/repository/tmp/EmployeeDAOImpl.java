package com.eliseev.app.repository.tmp;

import com.eliseev.app.models.tmp.Employee;
import com.eliseev.app.repository.AbstractDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl extends AbstractDAO<Employee>
        implements EmployeeDAO {

    private Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

    public EmployeeDAOImpl() {
        super(Employee.class);
    }

}


