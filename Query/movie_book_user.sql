-- <회원 기능>
-- 1. 모든 영화에 대한 조회 기능 : 영화명, 감독명, 배우명, 장르 조회
select * from movies where movie_title = '영화명';
select * from movies where director = '감독명';
select * from movies where actor = '배우명';
select * from movies where genre = '장르명';

-- 2. 위에서 조회한 영화에 대한 예매 기능
select ms.movie_schedule_id, ms.screen_id, ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time, s.is_available
from movie_schedule as ms
left join screens as s
on s.is_available = 1 and ms.screen_id = s.screen_id
where ms.movie_id = ?; -- movie_id는 위에서 조회한 영화 id 값
select * from seats where screen_id = ? and is_available = 1; -- screen_id는 위 쿼리문에서 받아온 screen_id 값
insert into booking (pay_method, pay_statement, price, member_id, pay_date)
values (?, 1, '14,000원', ?, STR_TO_DATE('2021-01-01','%Y-%m-%d')); -- pay_method, member_id는 변수 /pay_date는 영화상영시작일에 맞게 변수 처리하던가 해야할덧...
select last_insert_id(); -- booking 테이블에 방금 insert 한 booking_id 값 가져오기 
insert into tickets (movie_schedule_id, screen_id, seat_id, booking_id, is_ticket_printed, standard_price, selling_price)
values (?, ?, ?, ?, 0, '15,000원', '14,000원'); -- movie_schedule_id, screen_id, seat_id, booking_id는 변수
update seats set is_available = 0 where seat_id = ?; -- 방금 예매한 좌석 사용 불가 처리/ seat_id는 변수

-- 3. 본인이 예매한 영화에 대해서 영화명, 상영일, 상영관번호, 좌석번호, 판매가격 정보 보여주는 기능
select m.movie_id, b.booking_id, m.movie_title, ms.screening_start_date, t.screen_id, t.seat_id, b.price
from booking as b
left join tickets as t
on b.booking_id = t.booking_id
left join movie_schedule as ms
on ms.movie_schedule_id = t.movie_schedule_id
left join movies as m
on m.movie_id = ms.movie_id
where member_id = ?;  -- member_id는 변수

-- 4. 위에서 표시된 예매 정보 중에서 하나를 클릭하면 해당 예매에 대해서 모든 상영일정, 상영관, 티켓에 대한 정보를 보여주는 기능
select ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time,
s.seats, s.is_available, t.is_ticket_printed, t.standard_price, t.selling_price
from tickets as t
left join movie_schedule as ms
on t.movie_schedule_id = ms.movie_schedule_id
left join screens as s
on t.screen_id = s.screen_id
where booking_id = ?; -- 예매 정보 중에서 선택한 것의 booking_id

-- 5. 본인이 예매한 영화에 대하여 조회하고 한 개 이상의 예매 정보를 삭제하는 기능
select b.booking_id, b.pay_method, b.pay_statement, b.price, b.pay_date,
t.ticket_id, t.is_ticket_printed, t.standard_price, t.selling_price,
ms.movie_schedule_id, ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time,
m.*
from booking as b
left join tickets as t
on t.booking_id = b.booking_id
left join movie_schedule as ms
on ms.movie_schedule_id = t.movie_schedule_id
left join movies as m
on m.movie_id = ms.movie_id
where member_id = ?; -- 본인의 member_id 값 / booking, tickets, movie_schedule, movies 테이블 정보 가져옴
select seat_id from tickets where booking_id = ?; -- 삭제할 booking_id 값/ 삭제할 좌석 사용 가능 처리를 위한 seat_id 조회
delete from booking where booking_id = ?; -- 삭제할 booking_id 값/ 본인의 예매 정보 하나 삭제
update seats set is_available = 1 where seat_id = ?; -- 삭제한 좌석 사용 가능 처리

-- 6. 본인이 예매한 영화에 대하여 조회하여 다른 영화로 예매를 변경하는 기능
-- select m.*, b.booking_id
-- from booking as b
-- left join tickets as t
-- on t.booking_id = b.booking_id
-- left join movie_schedule as ms
-- on ms.movie_schedule_id = t.movie_schedule_id
-- left join movies as m
-- on m.movie_id = ms.movie_id
-- where member_id = 1; 
select b.booking_id, b.pay_method, b.pay_statement, b.price, b.pay_date,
t.ticket_id, t.is_ticket_printed, t.standard_price, t.selling_price,
ms.movie_schedule_id, ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time,
m.*
from booking as b
left join tickets as t
on t.booking_id = b.booking_id
left join movie_schedule as ms
on ms.movie_schedule_id = t.movie_schedule_id
left join movies as m
on m.movie_id = ms.movie_id
where member_id = ?; -- 본인의 member_id 값 / booking, tickets, movie_schedule, movies 테이블 정보 가져옴
select ms.movie_schedule_id, ms.screen_id, ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time, s.is_available
from movie_schedule as ms
left join screens as s
on s.is_available = 1 and ms.screen_id = s.screen_id
where movie_id != ?; -- 현재 예매한 영화 id를 제외한 모든 movie_schedule 정보 가져오기
select * from seats where screen_id = ? and is_available = 1; -- screen_id는 위 쿼리문에서 받아온 screen_id 값
update tickets set movie_schedule_id = ?, screen_id = ?, seat_id = ? where booking_id = ?; -- 변경할 booking_id를 위 쿼리문에서 조회한 다른 영화의 movie_schedule_id, screen_id, seat_id로 예매 정보 변경
update seats set is_available = 0 where seat_id = ?; -- 변경한 좌석 사용 불가 처리
update seats set is_available = 1 where seat_id = ?; -- 예약되어 있던 좌석 사용 가능 처리

-- 7. 본인이 예매한 영화에 대하여 조회하여 다른 상영 일정으로 변경하는 기능
select b.booking_id, b.pay_method, b.pay_statement, b.price, b.pay_date,
t.ticket_id, t.is_ticket_printed, t.standard_price, t.selling_price,
ms.movie_schedule_id, ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time,
m.*
from booking as b
left join tickets as t
on t.booking_id = b.booking_id
left join movie_schedule as ms
on ms.movie_schedule_id = t.movie_schedule_id
left join movies as m
on m.movie_id = ms.movie_id
where member_id = ?; -- 본인의 member_id 값 / booking, tickets, movie_schedule, movies 테이블 정보 가져옴
select ms.movie_schedule_id, ms.screen_id, ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time, s.is_available
from movie_schedule as ms
left join screens as s
on s.is_available = 1 and ms.screen_id = s.screen_id
where movie_schedule_id != ? and movie_id = ?; -- 현재 예매한 영화의 movie_schedule_id를 제외한 현재 영화의 다른 상영 일정을 조회
select * from seats where screen_id = ? and is_available = 1; -- screen_id는 위 쿼리문에서 받아온 screen_id 값
update tickets set movie_schedule_id = ?, screen_id = ?, seat_id = ? where booking_id = ?; -- 변경할 booking_id를 위 쿼리문에서 조회한 다른 상영 일정의 movie_schedule_id, screen_id, seat_id로 예매 정보 변경
update seats set is_available = 0 where seat_id = ?; -- 변경한 좌석 사용 불가 처리
update seats set is_available = 1 where seat_id = ?; -- 예약되어 있던 좌석 사용 가능 처리