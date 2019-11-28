use railway;


-- /*1.date 2.start_station 3.end_station */

select trainId, dateId
from station_stop_time
         inner join (
-- 3. поиск дат для поезда
    select trainId, id as dateId
    from train_date
             inner join (
-- 2.поиск поездов у которых станции идут в правильно порядке
        select trainId
        from train_route_piece
                 inner join (
-- 1.поиск подходящих станций для поезда
            select t.id as trainId, max(serial_number)
            from train_route_piece td
                     inner join train t on t.id = td.train_id
            where td.start_station_id in (select s1.id
                                          from station s1
                                          where s1.name = "Брест")
               or td.end_station_id in (select s2.id
                                        from station s2
                                        where s2.name = "Минск")
-- 1.поиск подходящих станций для поезда
            group by trainId
        ) as tab on train_route_piece.train_id = tab.trainId
-- 2.поиск поездов у которых станции идут в правильно порядке
                 left join station s on s.id = end_station_id
        where name = "Минск"
    ) as tab1 on train_date.train_id = trainId
) as tab2 on dateId = station_stop_time.train_date_id
where train_route_piece_id in
      (select id from train_route_piece where start_station_id in (select id from station where name = "Брест"))
  and start_dep_time = '2019-10-11 11:11:00.000000'



-- select r.id , ta.mapId, max(ta.mapSerialNumber), ta.endPoint from route r
-- inner join
-- (select m.route_id as routeId, m.id as mapId, m.serial_number as mapSerialNumber, m.end_point_id as endPoint
-- from map m
-- 			where m.start_point_id  in  (select  p1.id from point p1 where p1.name  = 'Минск') or
-- 			m.end_point_id in ( select  p2.id from point p2 where p2.name  = 'Витебск')
--             ) as ta on r.id = ta.routeId
-- group by r.id
-- having ta.endPoint = 3