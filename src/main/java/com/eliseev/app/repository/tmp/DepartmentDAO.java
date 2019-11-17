package com.eliseev.app.repository.tmp;

import com.eliseev.app.models.tmp.Department;

import java.util.List;

public interface DepartmentDAO {

    long count();

    Department findOne(long id);

    Department save(Department employee);

    void delete(long id);

    List<Department> findAll();

}
