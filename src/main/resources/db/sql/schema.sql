/*
create table if not exists Employee (
                                          id identity,
                                          firstName varchar(45) not null,
                                          lastName varchar(45) not null,
                                          email varchar(45) not null,
                                          department bigint not null
);
create table if not exists Department (
                                    id identity,
                                    name varchar(45) not null
);

alter table Employee
    add foreign key (department) references Department(id);*/
