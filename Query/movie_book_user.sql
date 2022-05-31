-- <회원 기능>
-- 1. 모든 영화에 대한 조회 기능 : 영화명, 감독명, 배우명, 장르 조회
select m.movie_id, m.movie_title, m.running_time, m.movie_rating, m.director, m.actor, m.genre,
ms.movie_schedule_id, ms.screening_day, ms.screening_start_time, sc.screen_id, s.seat_id
from movies as m
left join movie_schedule as ms
on m.movie_id = ms.movie_id
left join screens as sc
on ms.screen_id = sc.screen_id 
left join seats as s
on ms.screen_id = s.screen_id
where sc.is_available = 1 and s.is_available = 1 and m.movie_title = '범죄도시1' and m.director = '조민수' and m.actor = '이진형' and m.genre = '스릴러';

-- 2. 위에서 조회한 영화에 대한 예매 기능
insert into booking (pay_method, pay_statement, price, member_id, pay_date)
values ('카드', 1, '14,000원', 1, STR_TO_DATE('2021-01-01','%Y-%m-%d')); -- pay_method, member_id는 변수 /pay_date는 영화상영시작일에 맞게 변수 처리하던가 해야할덧...
select last_insert_id() as booking_id; -- booking 테이블에 방금 insert 한 booking_id 값 가져오기
select movie_schedule_id
from movie_schedule 
where screen_id = 1 and screening_day = STR_TO_DATE('2021-01-01','%Y-%m-%d') and screening_start_time = '9시 15분'; 
insert into tickets (movie_schedule_id, screen_id, seat_id, booking_id, is_ticket_printed, standard_price, selling_price)
values (1, 1, 1, 11, 0, '15,000원', '14,000원'); -- movie_schedule_id, screen_id, seat_id, booking_id는 변수
update seats set is_available = 0 where seat_id = 1; -- 방금 예매한 좌석 사용 불가 처리/ seat_id는 변수

-- 3. 본인이 예매한 영화에 대해서 영화명, 상영일, 상영관번호, 좌석번호, 판매가격 정보 보여주는 기능
select m.movie_id, b.booking_id, m.movie_title, ms.screening_start_date, t.screen_id, t.seat_id, b.price
from booking as b
left join tickets as t
on b.booking_id = t.booking_id
left join movie_schedule as ms
on ms.movie_schedule_id = t.movie_schedule_id
left join movies as m
on m.movie_id = ms.movie_id
where member_id = 1;  -- member_id는 변수

-- 4. 위에서 표시된 예매 정보 중에서 하나를 클릭하면 해당 예매에 대해서 모든 상영일정, 상영관, 티켓에 대한 정보를 보여주는 기능 상영관번호 좌석번호
select ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time,
t.screen_id, t.seat_id, s.seats, t.is_ticket_printed, t.standard_price, t.selling_price
from tickets as t
left join movie_schedule as ms
on t.movie_schedule_id = ms.movie_schedule_id
left join screens as s
on t.screen_id = s.screen_id
where booking_id in (select booking_id from tickets
where screen_id = 1 and seat_id = 1); -- 예매 정보 중에서 선택한 것의 booking_id

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
where member_id = 1; -- 본인의 member_id 값 / booking, tickets, movie_schedule, movies 테이블 정보 가져옴

delete from booking where booking_id in (select booking_id from tickets where seat_id = ?);  -- 삭제할 booking_id 값/ 본인의 예매 정보 하나 삭제
update seats set is_available = 1 where seat_id = ?; -- 삭제한 좌석 사용 가능 처리

-- 6. 본인이 예매한 영화에 대하여 조회하여 다른 영화로 예매를 변경하는 기능
select b.booking_id, b.pay_method, b.pay_statement, b.price, b.pay_date,
t.ticket_id, t.is_ticket_printed, t.standard_price, t.selling_price,
ms.movie_schedule_id, ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time, s.screen_id, se.seat_id,
m.*
from booking as b
left join tickets as t
on t.booking_id = b.booking_id
left join movie_schedule as ms
on ms.movie_schedule_id = t.movie_schedule_id
left join screens as s
on ms.screen_id = s.screen_id
left join seats as se
on t.seat_id = se.seat_id
left join movies as m
on m.movie_id = ms.movie_id
where member_id = 1; -- 본인의 member_id 값 / booking, tickets, movie_schedule, movies 테이블 정보 가져옴

select ms.movie_schedule_id, ms.screen_id, ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time, se.seat_id, se.is_available
from movie_schedule as ms
left join screens as s
on s.is_available = 1 and ms.screen_id = s.screen_id
left join seats as se
on s.screen_id = se.screen_id
where movie_id not in (select movie_id from movie_schedule where movie_schedule_id in (select movie_schedule_id from tickets where seat_id = 1)) and se.is_available = 1 and s.is_available = 1; -- 현재 예매한 영화 id를 제외한 모든 movie_schedule 정보 가져오기

select movie_schedule_id from tickets where seat_id = 2; -- 변경할 seat_id
select booking_id from tickets where seat_id = 1; -- 맨첨에 셀렉한 seat_id
update tickets set movie_schedule_id = 2, screen_id = 2, seat_id = 12 where booking_id = 1; -- 변경할 booking_id를 위 쿼리문에서 조회한 다른 영화의 movie_schedule_id, screen_id, seat_id로 예매 정보 변경
update seats set is_available = 0 where seat_id = 12; -- 변경한 좌석 사용 불가 처리
update seats set is_available = 1 where seat_id = 2; -- 예약되어 있던 좌석 사용 가능 처리


update tickets
set movie_schedule_id = 2, screen_id = 2, seat_id = 12
where booking_id in (select booking_id from (select t.booking_id from tickets as t where seat_id = 1) temp);

-- 7. 본인이 예매한 영화에 대하여 조회하여 다른 상영 일정으로 변경하는 기능
select b.booking_id, b.pay_method, b.pay_statement, b.price, b.pay_date,
t.ticket_id, t.is_ticket_printed, t.standard_price, t.selling_price,
ms.movie_schedule_id, ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time, s.screen_id, se.seat_id,
m.*
from booking as b
left join tickets as t
on t.booking_id = b.booking_id
left join movie_schedule as ms
on ms.movie_schedule_id = t.movie_schedule_id
left join screens as s
on ms.screen_id = s.screen_id
left join seats as se
on t.seat_id = se.seat_id
left join movies as m
on m.movie_id = ms.movie_id
where member_id = 1; -- 본인의 member_id 값 / booking, tickets, movie_schedule, movies 테이블 정보 가져옴

select ms.movie_schedule_id, ms.screen_id, se.seat_id, ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time
from movie_schedule as ms
left join screens as s
on ms.screen_id = s.screen_id
left join seats as se
on s.screen_id = se.screen_id
where ms.movie_schedule_id not in (select movie_schedule_id from tickets where seat_id = 1) and movie_id in (select movie_id from movie_schedule where movie_schedule_id in (select movie_schedule_id from tickets where seat_id = 1)) and s.is_available = 1 and se.is_available = 1; -- 현재 예매한 영화의 movie_schedule_id를 제외한 현재 영화의 다른 상영 일정을 조회

select movie_schedule_id from tickets where seat_id = 18; -- 변경할 seat_id
select booking_id from tickets where seat_id = 1; -- 맨첨에 셀렉한 seat_id
update tickets set movie_schedule_id = 11, screen_id = 8, seat_id = 18 where booking_id = 1; -- 변경할 booking_id를 위 쿼리문에서 조회한 다른 상영 일정의 movie_schedule_id, screen_id, seat_id로 예매 정보 변경
update seats set is_available = 0 where seat_id = 18; -- 변경한 좌석 사용 불가 처리
update seats set is_available = 1 where seat_id = 1; -- 예약되어 있던 좌석 사용 가능 처리