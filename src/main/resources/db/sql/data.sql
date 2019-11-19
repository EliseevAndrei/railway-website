delete from Employee;
delete from Department;
insert into Department (id, name) values ( 1, 'dep' );
insert into Department (id, name) values ( 2, 'arr' );
insert into Department (id, name) values ( 3, 'time' );
insert into EMPLOYEE (id, firstName, lastName, email, department_id) values ( 1,  'andrei', 'eliseev', 'eliseev.andrei345@mail.ru',  1);
insert into EMPLOYEE (id, firstName, lastName, email, department_id) values ( 2,  'vera', 'eliseev', 'eliseev.andrei345@mailr.u',  2);
insert into EMPLOYEE (id, firstName, lastName, email, department_id) values ( 3,  'notvera', 'eliseev', 'eliseev.andrei345@mailr.u',  3);


INSERT INTO Station VALUES (1,'Брест'),(2,'Гродно'),(3,'Витебск'),(4,'Минск');

INSERT INTO User VALUES (1,'eliseev.andrei345@mail.ru','dron','andrei','dron','eliseev'),(2,'kozlova@mail.ru','vera','vera','vera','kozlova');
INSERT INTO Ticket VALUES (1,'Елисеев','авыла23а23','Брест','2019-10-10 11:11:00.000000',10,10,'Гродно','2019-10-10 11:11:00.000000',11,'20b','КУПЕ','Елисеев',1),(2,'Козлова','авыла23а23','Брест','2019-10-10 11:11:00.000000',10,10,'Гродно','2019-10-10 11:11:00.000000',11,'20b','КУПЕ','Вероника',1);

INSERT INTO Train VALUES (1,10,10,10,'10в'),(2,11,1,1,'21в');

INSERT INTO TrainStation VALUES (1,'2019-10-11 11:11:00.000000',1,1,'2019-10-11 11:11:00.000000',1,1,1,1),(2,'2019-10-11 11:11:00.000000',2,2,'2019-10-11 11:11:00.000000',2,2,2,1),(3,'2019-10-11 11:11:00.000000',3,3,'2019-10-11 11:11:00.000000',3,3,3,1),(4,'2019-10-11 11:11:00.000000',3,3,'2019-10-11 11:11:00.000000',3,3,3,2);
