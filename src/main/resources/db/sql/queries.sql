use railway;

-- /*1.date 2.start_station 3.end_station */

set @ind1 := (select s1.id from station s1
					where s1.name = "Брест"
						limit 1);
set @ind2 := (select s2.id from station s2
							where s2.name = "Минск"
									limit 1);

select trainId, train_date_id as trainDateId from (
                                                    select trainId, train_route_piece.id as pieceId
                                                    from train_route_piece
                                                           inner join (

                                                      select t.id as trainId, min(serial_number)
                                                      from train_route_piece td
                                                             inner join train t on t.id = td.train_id
                                                      where td.start_station_id =  @ind1
                                                         or td.end_station_id = @ind2
                                                      group by trainId
                                                      having count(t.id) >= 2

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
       select * from train_route_piece trp,
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
set coupe_places_amount = coupe_places_amount -1
where id in (
  select tab.stopTimeId  from (
                                select sst.id as stopTimeID, sst.train_route_piece_id from station_stop_time sst
                                where sst.train_date_id = 1
                              ) as tab inner join train_route_piece trp on trp.id = tab.train_route_piece_id
  where trp.serial_number between 1 and 3
)