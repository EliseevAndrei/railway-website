INSERT INTO Station VALUES (1,'Брест'),(2,'Жлобин'),(3,'Витебск'),(4,'Минск'),(5,'Гродно');

INSERT INTO User VALUES (1,'eliseev.andrei345@mail.ru','admin','andrei','admin','eliseev'),(2,'kozlova@mail.ru','user','vera','user','kozlova');

INSERT INTO Train VALUES (1,10,10,10,'10в'), (2,11,1,1,'21в');

INSERT INTO train_route_piece (id, train_id, start_station_id, end_station_id, serial_number, distance) values ( 1, 1, 1, 2, 1, 100 ),( 2, 1, 2, 3, 2 , 100 ),( 3, 1, 3, 4, 3, 100  );

insert into train_date (id, train_id) values ( 1, 1 ),( 2, 2 );

INSERT INTO station_stop_time (id,arr_time,dep_time, coupe_places_amount, lying_places_amount, common_places_amount, train_route_piece_id, train_date_id) values (1,'2019-10-11 11:11:00.000000','2019-10-11 11:11:00.000000', 10, 10, 10, 1, 1),(2,'2019-10-11 11:11:00.000000','2019-10-11 11:11:00.000000', 10, 10, 10, 2, 1),(3, '2019-10-11 11:11:00.000000','2019-10-11 11:11:00.000000', 10, 10, 10, 3, 1);