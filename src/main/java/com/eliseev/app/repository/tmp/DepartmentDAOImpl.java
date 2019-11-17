package com.eliseev.app.repository.tmp;

import com.eliseev.app.models.tmp.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public Department findOne(long id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public Department save(Department department) {
        if (department.getId() == null) {
            entityManager.persist(department);
        } else {
            return entityManager.merge(department);
        }
        return department;
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findOne(id));
    }

    @Override
    public List<Department> findAll() {
        return entityManager.createQuery("select d from Department d", Department.class)
                .getResultList();
    }
}
