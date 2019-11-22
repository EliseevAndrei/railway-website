INSERT INTO Station VALUES (1,'Брест'),(2,'Жлобин'),(3,'Витебск'),(4,'Минск'),(5,'Гродно');

INSERT INTO User VALUES (1,'eliseev.andrei345@mail.ru','admin','andrei','admin','eliseev'),(2,'kozlova@mail.ru','user','vera','user','kozlova');
INSERT INTO Ticket (id, surname, passport_number, dep_station, dep_time, arr_station, arr_time, train, seat_type, name, user_id) VALUES (1,'Елисеев','авыла23а23','Брест','2019-10-10 11:11:00.000000','Гродно','2019-10-10 11:11:00.000000','20b','КУПЕ','Елисеев',1),(2,'Козлова','авыла23а23','Брест','2019-10-10 11:11:00.000000','Гродно','2019-10-10 11:11:00.000000','20b','КУПЕ','Вероника',1);

INSERT INTO Train VALUES (1,10,10,10,'10в'),(2,11,1,1,'21в');

INSERT INTO TrainStation (id, train_id, station_id, station_serial_number) values ( 1, 1, 1, 1 );
INSERT INTO TrainStation (id, train_id, station_id, station_serial_number) values ( 2, 1, 2, 2 );
INSERT INTO TrainStation (id, train_id, station_id, station_serial_number) values ( 3, 1, 3, 3 );
INSERT INTO TrainStation (id, train_id, station_id, station_serial_number) values ( 4, 2, 1, 1 );


insert into train_date (id, train_id) values ( 1, 1 );
insert into train_date (id, train_id) values ( 2, 2 );

INSERT INTO station_stop_time (id, arr_time, dep_time, coupe_places_amount, lying_places_amount, common_places_amount, train_station_id, train_date_id) values (1, '2019-10-11 11:11:00.000000', '2019-10-11 11:11:00.000000', 10, 10, 10, 1, 1);
INSERT INTO station_stop_time (id, arr_time, dep_time, coupe_places_amount, lying_places_amount, common_places_amount, train_station_id, train_date_id) values (2, '2019-10-11 11:11:00.000000', '2019-10-11 11:11:00.000000', 10, 10, 10, 2, 1);
INSERT INTO station_stop_time (id, arr_time, dep_time, coupe_places_amount, lying_places_amount, common_places_amount, train_station_id, train_date_id) values (3, '2019-10-11 11:11:00.000000', '2019-10-11 11:11:00.000000', 10, 10, 10, 3, 1);
INSERT INTO station_stop_time (id, arr_time, dep_time, coupe_places_amount, lying_places_amount, common_places_amount, train_station_id, train_date_id) values (4, '2019-10-11 11:11:00.000000', '2019-10-11 11:11:00.000000', 10, 10, 10, 4, 2);
