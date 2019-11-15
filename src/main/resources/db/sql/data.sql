delete from Employee;
delete from Department;
insert into Department (id, name) values ( 1, 'dep' );
insert into Department (id, name) values ( 2, 'arr' );
insert into Department (id, name) values ( 3, 'time' );
insert into EMPLOYEE (id, firstName, lastName, email, department_id) values ( 1,  'andrei', 'eliseev', 'eliseev.andrei345@mail.ru',  1);
insert into EMPLOYEE (id, firstName, lastName, email, department_id) values ( 2,  'vera', 'eliseev', 'eliseev.andrei345@mailr.u',  2);
insert into EMPLOYEE (id, firstName, lastName, email, department_id) values ( 3,  'notvera', 'eliseev', 'eliseev.andrei345@mailr.u',  3);
