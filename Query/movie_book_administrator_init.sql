-- <관리자 기능>
-- 1. 데이터베이스 초기화
drop table if exists tickets;
drop table if exists movie_schedule;
drop table if exists seats;
drop table if exists booking;
drop table if exists screens;
drop table if exists members;
drop table if exists movies;

CREATE TABLE IF NOT EXISTS movies (
  `movie_id` INT NOT NULL AUTO_INCREMENT,
  `movie_title` VARCHAR(45) NOT NULL,
  `running_time` VARCHAR(45) NOT NULL,
  `movie_rating` VARCHAR(45) NOT NULL,
  `director` VARCHAR(45) NOT NULL,
  `actor` VARCHAR(45) NOT NULL,
  `genre` VARCHAR(45) NOT NULL,
  `movie_introduction` TEXT NOT NULL,
  `release_date` DATE NOT NULL,
  PRIMARY KEY (`movie_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS screens (
  `screen_id` INT NOT NULL AUTO_INCREMENT,
  `seats` INT NOT NULL,
  `is_available` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`screen_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS movie_schedule (
  `movie_schedule_id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `screen_id` INT NOT NULL,
  `screening_start_date` DATE NOT NULL,
  `screening_day` VARCHAR(45) NOT NULL,
  `screening_round` VARCHAR(45) NOT NULL,
  `screening_start_time` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`movie_schedule_id`, `screen_id`, `movie_id`),
  INDEX `fk_movie_schedule_movies1_idx` (`movie_id` ASC) VISIBLE,
  INDEX `fk_movie_schedule_screens1_idx` (`screen_id` ASC) VISIBLE,
  CONSTRAINT `fk_movie_schedule_movies1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie_book`.`movies` (`movie_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_movie_schedule_screens1`
    FOREIGN KEY (`screen_id`)
    REFERENCES `movie_book`.`screens` (`screen_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS seats (
  `seat_id` INT NOT NULL AUTO_INCREMENT,
  `screen_id` INT NOT NULL,
  `is_available` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`seat_id`, `screen_id`),
  INDEX `fk_seats_screens1_idx` (`screen_id` ASC) VISIBLE,
  CONSTRAINT `fk_seats_screens1`
    FOREIGN KEY (`screen_id`)
    REFERENCES `movie_book`.`screens` (`screen_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS members (
  `member_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS booking (
  `booking_id` INT NOT NULL AUTO_INCREMENT,
  `pay_method` VARCHAR(45) NOT NULL,
  `pay_statement` TINYINT NOT NULL,
  `price` VARCHAR(45) NOT NULL,
  `member_id` INT NOT NULL,
  `pay_date` DATE NOT NULL,
  PRIMARY KEY (`booking_id`, `member_id`),
  INDEX `fk_booking_members1_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_booking_members1`
    FOREIGN KEY (`member_id`)
    REFERENCES `movie_book`.`members` (`member_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS tickets (
  `ticket_id` INT NOT NULL AUTO_INCREMENT,
  `movie_schedule_id` INT NOT NULL,
  `screen_id` INT NOT NULL,
  `seat_id` INT NOT NULL,
  `booking_id` INT NOT NULL,
  `is_ticket_printed` TINYINT NOT NULL DEFAULT 0,
  `standard_price` VARCHAR(45) NOT NULL,
  `selling_price` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ticket_id`, `movie_schedule_id`, `screen_id`, `seat_id`, `booking_id`),
  INDEX `fk_tickets_movie_schedule1_idx` (`movie_schedule_id` ASC) VISIBLE,
  INDEX `fk_tickets_seats1_idx` (`seat_id` ASC) VISIBLE,
  INDEX `fk_tickets_booking1_idx` (`booking_id` ASC) VISIBLE,
  INDEX `fk_tickets_screens1_idx` (`screen_id` ASC) VISIBLE,
  CONSTRAINT `fk_tickets_movie_schedule1`
    FOREIGN KEY (`movie_schedule_id`)
    REFERENCES `movie_book`.`movie_schedule` (`movie_schedule_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tickets_seats1`
    FOREIGN KEY (`seat_id`)
    REFERENCES `movie_book`.`seats` (`seat_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tickets_booking1`
    FOREIGN KEY (`booking_id`)
    REFERENCES `movie_book`.`booking` (`booking_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tickets_screens1`
    FOREIGN KEY (`screen_id`)
    REFERENCES `movie_book`.`screens` (`screen_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

INSERT INTO movies VALUES(1, '닥터 스트레인지', '2시간 20분', '15세', '조민수', '서진형', '판타지', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-01-09','%Y-%m-%d'));
INSERT INTO movies VALUES(2, '범죄도시1', '2시간 21분', '18세', '조민수', '이진형', '스릴러', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-02-09','%Y-%m-%d'));
INSERT INTO movies VALUES(3, '범죄도시3', '2시간 22분', '12세', '최민수', '국진형', '호러', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-03-09','%Y-%m-%d'));
INSERT INTO movies VALUES(4, '범죄도시4', '2시간 23분', '18세', '박민수', '오진형', '코미디', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-04-09','%Y-%m-%d'));
INSERT INTO movies VALUES(5, '범죄도시5', '2시간 24분', '15세', '김민수','임진형', '호러', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-05-09','%Y-%m-%d'));
INSERT INTO movies VALUES(6, '범죄도시6', '2시간 25분', '12세', '임민수', '김진형', '로맨스', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-06-09','%Y-%m-%d'));
INSERT INTO movies VALUES(7, '범죄도시6', '2시간 26분', '전체이용가', '오민수', '박진형', '판타지', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-07-09','%Y-%m-%d'));
INSERT INTO movies VALUES(8, '범죄도시7', '2시간 27분', '15세', '국민수', '최진형', '액션', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-08-09','%Y-%m-%d'));
INSERT INTO movies VALUES(9, '범죄도시8', '2시간 28분', '18세', '이민수', '조진형', '스릴러', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-09-09','%Y-%m-%d'));
INSERT INTO movies VALUES(10, '범죄도시2', '2시간 29분', '15세', '서민수', '리진형', '액션', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-10-09','%Y-%m-%d'));

insert into members values (1, 'Brooke', '735-214-8098', 'bmarlon0@constantcontact.com');
insert into members values (2, 'Martita', '964-716-5566', 'mbusk1@printfriendly.com');
insert into members values (3, 'Eziechiele', '768-705-1335', 'evan2@miitbeian.gov.cn');
insert into members values (4, 'Pacorro', '212-772-9242', 'phovard3@oracle.com');
insert into members values (5, 'Cori', '400-461-8374', 'caburrow4@163.com');
insert into members values (6, 'Ahmad', '555-584-2230', 'aparram5@yandex.ru');
insert into members values (7, 'Elliot', '550-149-1193', 'egiacubo6@elpais.com');
insert into members values (8, 'Barret', '432-886-2071', 'bleathwood7@cisco.com');
insert into members values (9, 'Ranique', '445-800-0015', 'rculter8@scientificamerican.com');
insert into members values (10, 'Jaclyn', '585-410-7043', 'jbouchier9@unesco.org');

insert into screens values (1, 500, 1);
insert into screens values (2, 250, 1);
insert into screens values (3, 500, 1);
insert into screens values (4, 650, 1);
insert into screens values (5, 200, 0);
insert into screens values (6, 155, 1);
insert into screens values (7, 140, 1);
insert into screens values (8, 124, 1);
insert into screens values (9, 450, 1);
insert into screens values (10, 123, 1);

insert into seats values (1, 1, 0);
insert into seats values (2, 2, 0);
insert into seats values (3, 3, 0);
insert into seats values (4, 4, 0);
insert into seats values (5, 5, 0);
insert into seats values (6, 6, 0);
insert into seats values (7, 7, 0);
insert into seats values (8, 8, 0);
insert into seats values (9, 9, 0);
insert into seats values (10, 10, 0);
insert into seats values (11, 1, 1);
insert into seats values (12, 2, 1);
insert into seats values (13, 3, 1);
insert into seats values (14, 4, 1);
insert into seats values (15, 5, 1);
insert into seats values (16, 6, 1);
insert into seats values (17, 7, 1);
insert into seats values (18, 8, 1);
insert into seats values (19, 2, 1);
insert into seats values (20, 4, 1);


insert into movie_schedule values(1, 1, 1, STR_TO_DATE('2021-01-09','%Y-%m-%d'), '월요일', '1회차', '09시 15분');
insert into movie_schedule values(2, 2, 2, STR_TO_DATE('2021-02-09','%Y-%m-%d'), '화요일', '2회차', '10시 15분');
insert into movie_schedule values(3, 3, 3, STR_TO_DATE('2021-03-09','%Y-%m-%d'), '수요일', '2회차', '11시 15분');
insert into movie_schedule values(4, 4, 4, STR_TO_DATE('2021-04-09','%Y-%m-%d'), '목요일', '3회차', '10시 15분');
insert into movie_schedule values(5, 5, 5, STR_TO_DATE('2021-05-09','%Y-%m-%d'), '금요일', '5회차', '18시 15분');
insert into movie_schedule values(6, 6, 6, STR_TO_DATE('2021-06-09','%Y-%m-%d'), '월요일', '2회차', '10시 15분');
insert into movie_schedule values(7, 7, 7, STR_TO_DATE('2021-07-09','%Y-%m-%d'), '월요일', '7회차', '22시 15분');
insert into movie_schedule values(8, 8, 8, STR_TO_DATE('2021-08-09','%Y-%m-%d'), '수요일', '2회차', '11시 15분');
insert into movie_schedule values(9, 9, 9, STR_TO_DATE('2021-09-09','%Y-%m-%d'), '금요일', '4회차', '13시 15분');
insert into movie_schedule values(10, 10, 10, STR_TO_DATE('2021-10-09','%Y-%m-%d'), '일요일', '1회차', '09시 15분');
insert into movie_schedule values(11, 1, 8, STR_TO_DATE('2021-01-10','%Y-%m-%d'), '화요일', '2회차', '09시 20분');

insert into booking values(1, '카드', 1, '14,000원', 1, STR_TO_DATE('2021-01-08','%Y-%m-%d'));
insert into booking values(2, '카드', 1, '12,000원', 2, STR_TO_DATE('2021-02-09','%Y-%m-%d'));
insert into booking values(3, '현금', 1, '14,000원', 3, STR_TO_DATE('2021-03-08','%Y-%m-%d'));
insert into booking values(4, '카드', 1, '12,000원', 4, STR_TO_DATE('2021-04-08','%Y-%m-%d'));
insert into booking values(5, '현금', 1, '14,000원', 5, STR_TO_DATE('2021-05-08','%Y-%m-%d'));
insert into booking values(6, '카드', 1, '13,000원', 6, STR_TO_DATE('2021-06-08','%Y-%m-%d'));
insert into booking values(7, '무통장', 0, '14,000원', 7, STR_TO_DATE('2021-07-08','%Y-%m-%d'));
insert into booking values(8, '현금', 1, '14,000원', 8, STR_TO_DATE('2021-08-08','%Y-%m-%d'));
insert into booking values(9, '현금', 1, '12,000원', 9, STR_TO_DATE('2021-09-08','%Y-%m-%d'));
insert into booking values(10, '카드', 1, '14,000원', 10, STR_TO_DATE('2021-10-08','%Y-%m-%d'));

insert into tickets values(1, 1, 1, 1, 1, 1, '14,000원', '14,000원');
insert into tickets values(2, 2, 2, 2, 2, 0, '12,000원', '12,000원');
insert into tickets values(3, 3, 3, 3, 3, 1, '14,000원', '14,000원');
insert into tickets values(4, 4, 4, 4, 4, 0, '14,000원', '13,000원');
insert into tickets values(5, 5, 5, 5, 5, 0, '14,000원', '13,000원');
insert into tickets values(6, 6, 6, 6, 6, 1, '12,000원', '12,000원');
insert into tickets values(7, 7, 7, 7, 7, 1, '14,000원', '14,000원');
insert into tickets values(8, 8, 8, 8, 8, 1, '13,000원', '11,000원');
insert into tickets values(9, 9, 9, 9, 9, 1, '14,000원', '14,000원');
insert into tickets values(10, 10, 10, 10, 10, 1, '14,000원', '11,000원');

-- 2. 데이터베이스에 포함된 모든 테이블에 대한 입력/삭제/변경 기능 (단, 삭제/변경은 '조건식' 입력받아서 삭제/변경하는 방식으로 구현)
-- 3. 전체 테이블 보기: 모든 테이블 내용 보여주는 기능