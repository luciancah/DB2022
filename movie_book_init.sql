USE movie_book;

CREATE TABLE IF NOT EXISTS `movie_book`.`movies` (
  `movie_id` INT NOT NULL,
  `movie_titlebooking` VARCHAR(45) NOT NULL,
  `running_time` VARCHAR(45) NOT NULL,
  `movie_rating` VARCHAR(45) NOT NULL,
  `director` VARCHAR(45) NOT NULL,
  `actor` VARCHAR(45) NOT NULL,
  `genre` VARCHAR(45) NOT NULL,
  `movie_introduction` TEXT NOT NULL,
  `release_date` DATE NOT NULL,
  PRIMARY KEY (`movie_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `movie_book`.`screens` (
  `screen_id` INT NOT NULL,
  `seats` INT NOT NULL,
  `is_available` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`screen_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `movie_book`.`movie_schedule` (
  `movie_schedule_id` INT NOT NULL,
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movie_schedule_screens1`
    FOREIGN KEY (`screen_id`)
    REFERENCES `movie_book`.`screens` (`screen_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `movie_book`.`seats` (
  `seat_id` INT NOT NULL,
  `screen_id` INT NOT NULL,
  `is_available` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`seat_id`, `screen_id`),
  INDEX `fk_seats_screens1_idx` (`screen_id` ASC) VISIBLE,
  CONSTRAINT `fk_seats_screens1`
    FOREIGN KEY (`screen_id`)
    REFERENCES `movie_book`.`screens` (`screen_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `movie_book`.`members` (
  `member_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `movie_book`.`booking` (
  `booking_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  `pay_method` VARCHAR(45) NOT NULL,
  `pay_statement` TINYINT NOT NULL,
  `price` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`booking_id`, `member_id`),
  INDEX `fk_booking_members1_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_booking_members1`
    FOREIGN KEY (`member_id`)
    REFERENCES `movie_book`.`members` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `movie_book`.`tickets` (
  `ticket_id` INT NOT NULL,
  `movie_schedule_id` INT NOT NULL,
  `seat_id` INT NOT NULL,
  `screen_id` INT NOT NULL,
  `booking_id` INT NOT NULL,
  `is_ticket_printed` TINYINT NOT NULL DEFAULT 0,
  `standard_price` VARCHAR(45) NOT NULL,
  `selling_price` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ticket_id`, `movie_schedule_id`, `seat_id`, `screen_id`, `booking_id`),
  INDEX `fk_tickets_movie_schedule1_idx` (`movie_schedule_id` ASC) VISIBLE,
  INDEX `fk_tickets_seats1_idx` (`seat_id` ASC) VISIBLE,
  INDEX `fk_tickets_booking1_idx` (`booking_id` ASC) VISIBLE,
  INDEX `fk_tickets_screens1_idx` (`screen_id` ASC) VISIBLE,
  CONSTRAINT `fk_tickets_movie_schedule1`
    FOREIGN KEY (`movie_schedule_id`)
    REFERENCES `movie_book`.`movie_schedule` (`movie_schedule_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_seats1`
    FOREIGN KEY (`seat_id`)
    REFERENCES `movie_book`.`seats` (`seat_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_booking1`
    FOREIGN KEY (`booking_id`)
    REFERENCES `movie_book`.`booking` (`booking_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_screens1`
    FOREIGN KEY (`screen_id`)
    REFERENCES `movie_book`.`screens` (`screen_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

