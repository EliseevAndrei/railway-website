use railway;

-- /*1.date 2.start_station 3.end_station */

set @ind1 := (select s1.id from station s1
					where s1.name = "Брест"
						limit 1);
set @ind2 := (select s2.id from station s2
							where s2.name = "Минск"
									limit 1);

select trainId, train_date_id as trainDateId
from (
       select trainId, train_route_piece.id as pieceId
       from train_route_piece
              inner join (
         select t.id as trainId, min(serial_number)
         from train_route_piece td
                inner join train t on t.id = td.train_id
         where td.start_station_id = @ind1
            or td.end_station_id = @ind2
         group by trainId
         having count(t.id) >= 1
       ) as tab on train_route_piece.train_id = tab.trainId
       where start_station_id = @ind1
     ) as ta
       left join station_stop_time sst on sst.train_route_piece_id = pieceId
where sst.dep_time between '2019-10-11 00:00:00' and '2019-10-11 23:59:59';

-------------------------------------------------------------------------------------------------------------------------
//searching amount of free places
use railway;

set @ind1 := (select s1.id from station s1
					where s1.name = "Брест"
						limit 1);
set @ind2 := (select s2.id from station s2
							where s2.name = "Минск"
									limit 1);


select min(common_places_amount), min(coupe_places_amount), min(lying_places_amount)
from (
       select *
       from train_route_piece trp,
            (select @max1 := (select trp.serial_number from train_route_piece trp
               where trp.train_id = 1
               and trp.start_station_id = @ind1
               limit 1)) as x,
            (select @max2 := (select trp.serial_number from train_route_piece trp
               where trp.train_id = 1
               and trp.end_station_id = @ind2
               limit 1)) as y
       where trp.train_id = 1
         and trp.serial_number between @max1 and @max2
     ) as tab
       left join station_stop_time sst on sst.train_route_piece_id = tab.id
where train_date_id = 1;

------------------------------------
update station_stop_time
set coupe_places_amount = coupe_places_amount - 1
where id in (
  select tab.stopTimeId
  from (
         select sst.id as stopTimeID, sst.train_route_piece_id
         from station_stop_time sst
         where sst.train_date_id = 1
       ) as tab
         inner join train_route_piece trp on trp.id = tab.train_route_piece_id
  where trp.serial_number between 1 and 3
  );

---------------------------------------

select station_stop_time_id, car_place.carriage_id, place_id, is_taken, number, type
from (
       select *
       from station_stop_time_car sstc
       where sstc.station_stop_time_id in (
         select sst.id as stopStationId
         from (
                select *
                from train_route_piece trp,
                     (select @max1 := (select trp.serial_number from train_route_piece trp
                        where trp.train_id = 1
                        and trp.start_station_id = @ind1
                        limit 1)) as x,
                     (select @max2 := (select trp.serial_number from train_route_piece trp
                        where trp.train_id = 1
                        and trp.end_station_id = @ind2
                        limit 1)) as y
                where trp.train_id = 1
                  and trp.serial_number between @max1 and @max2
              ) as tab
                left join station_stop_time sst on sst.train_route_piece_id = tab.id
         where train_date_id = 1
         )
     ) as tabl
       join car_place on car_place.carriage_id = tabl.carriage_id
       join place on place.id = place_id

group by carriage_id, number
having count(if(is_taken = 0, null, is_taken)) = 0
order by station_stop_time_id, carriage_id, type, number;

----------------------------------------------------

select *
from carriage c
       inner join train_car on c.id = train_car.carriage_id
       inner join car_place on car_place.carriage_id = c.id
where train_id = 1
  and place_id not in (
  select ta.place_id as placeId
  from (
         select *
         from ticket t
         where t.train_date_id = 1
       ) as ta
         left join train_route_piece trp1 on trp1.id = ta.dep_train_route_piece_id
         left join train_route_piece trp2 on trp2.id = ta.arr_train_route_piece_id
  where (trp1.serial_number between @max1 and @max2)
    and (trp2.serial_number between @max1 and @max2)
  )