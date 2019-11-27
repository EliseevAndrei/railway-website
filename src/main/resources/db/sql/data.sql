INSERT INTO Station VALUES (1,'Брест'),(2,'Жлобин'),(3,'Витебск'),(4,'Минск'),(5,'Гродно');

INSERT INTO User VALUES (1,'eliseev.andrei345@mail.ru','admin','andrei','admin','eliseev'),(2,'kozlova@mail.ru','user','vera','user','kozlova');
INSERT INTO Ticket (id, surname, passport_number, dep_station, dep_time, arr_station, arr_time, train, seat_type, name, user_id) VALUES (1,'Елисеев','авыла23а23','Брест','2019-10-10 11:11:00.000000','Гродно','2019-10-10 11:11:00.000000','20b','КУПЕ','Елисеев',1),(2,'Козлова','авыла23а23','Брест','2019-10-10 11:11:00.000000','Гродно','2019-10-10 11:11:00.000000','20b','КУПЕ','Вероника',1);

INSERT INTO Train VALUES (1,10,10,10,'10в'), (2,11,1,1,'21в');

INSERT INTO train_route_piece (id, train_id, start_station_id, end_station_id, serial_number, distance) values ( 1, 1, 1, 2, 1, 100 ),( 2, 1, 2, 3, 2 , 100 ),( 3, 1, 3, 4, 3, 100  );

insert into train_date (id, train_id) values ( 1, 1 ),( 2, 2 );

INSERT INTO station_stop_time (id, start_arr_time, start_dep_time,end_arr_time, end_dep_time, coupe_places_amount, lying_places_amount, common_places_amount, train_route_piece_id, train_date_id) values (1, '2019-10-11 11:11:00.000000', '2019-10-11 11:11:00.000000','2019-10-11 11:11:00.000000','2019-10-11 11:11:00.000000', 10, 10, 10, 1, 1),(2, '2019-10-11 11:11:00.000000', '2019-10-11 11:11:00.000000','2019-10-11 11:11:00.000000','2019-10-11 11:11:00.000000', 10, 10, 10, 2, 1),(3, '2019-10-11 11:11:00.000000', '2019-10-11 11:11:00.000000','2019-10-11 11:11:00.000000','2019-10-11 11:11:00.000000', 10, 10, 10, 3, 1);