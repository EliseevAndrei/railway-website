package com.eliseev.app.repository.tmp;

import com.eliseev.app.models.tmp.Department;
import com.eliseev.app.models.tmp.Employee;
import com.eliseev.app.repository.AbstractDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DepartmentDAOImpl extends AbstractDAO<Department>
        implements DepartmentDAO {

    public DepartmentDAOImpl() {
        super(Department.class);
    }

}
