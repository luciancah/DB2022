package databaseProject;

import java.io.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;

class Title extends JFrame{
	SQL sql = new SQL();
	JButton button[] = new JButton[2];

	public Title() {
		Container c1 = getContentPane();
		c1.setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(2000,2000);
		setTitle("18011560/조민수");
	
		button[0] = new JButton("관리자");
		button[0].setSize(300,300);
		button[0].setLocation(700, 300);
		c1.add(button[0]);
		
		button[1] = new JButton("회원");
		button[1].setSize(300,300);
		button[1].setLocation(1000, 300);
		c1.add(button[1]);
		
		buttonFunction();
		
		setVisible(true);
	}
	
	public void buttonFunction() {
		button[0].addActionListener(event -> {
			new Manager(sql);
			setVisible(false);
		});
		
		button[1].addActionListener(event -> {
			new Member(sql);
			setVisible(false);
		});
	}
}

class Manager extends JFrame {
	JButton button[] = new JButton[3];
	SQL sql;
	JTextField  jt2 = new JTextField(1000);
	JLabel error = new JLabel();
	JTextArea result = new JTextArea();
	boolean insert = false;
	JScrollPane scrollPane = new JScrollPane(result);

	public Manager(SQL _sql) {
		sql = _sql;
		Container c1 = getContentPane();
		c1.setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(2000,2000);
		setTitle("18011560/조민수");
	
		button[0] = new JButton("초기화");
		button[0].setSize(200,100);
		button[0].setLocation(0, 0);
		c1.add(button[0]);
		
		button[1] = new JButton("입력/삭제/변경");
		button[1].setSize(200,100);
		button[1].setLocation(500, 150);
		c1.add(button[1]);
		
		
		button[2] = new JButton("전체 테이블 보기");
		button[2].setSize(200,100);
		button[2].setLocation(200, 0);
		c1.add(button[2]);
		
		JLabel label = new JLabel("SQL");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setSize(100,10);
		label.setLocation(0, 200);
		c1.add(label);

		jt2.setLocation(100,200);
		jt2.setSize(400,50);
		c1.add(jt2);
		
		error.setSize(300,50);
		error.setLocation(0, 250);
		c1.add(error);
		
		scrollPane.setSize(1700,500);
		scrollPane.setLocation(0, 400);
		c1.add(scrollPane);
		
		buttonFunction();
		
		setVisible(true);
	}
	
	public void buttonFunction() {
		button[0].addActionListener(event -> {
			result.setText(null);
			error.setText(null);
			
			sql.Initialize();
		});
		
		button[1].addActionListener(event -> {
			result.setText(null);
			error.setText(null);
			
				
		});
		
		button[2].addActionListener(event -> {
			result.setText(null);
			error.setText(null);
			
			
			sql.AllSearch(result);
		});
	}
}


class Member extends JFrame {
	JButton button[] = new JButton[6];
	SQL sql;
	Object []attribute = {"!"};
	Object [][]value = {{"1"}};
	DefaultTableModel model = new DefaultTableModel(value,attribute);
	JTable total = new JTable(model);
	boolean insert = false;
	JScrollPane scrollPane = new JScrollPane(total);

	public Member(SQL _sql) {
		sql = _sql;
		Container c1 = getContentPane();
		c1.setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(2000,2000);
		setTitle("18011560/조민수");
	
		button[0] = new JButton("모든 영화 조회");
		button[0].setSize(200,100);
		button[0].setLocation(0, 0);
		c1.add(button[0]);
		
		button[1] = new JButton("예매");
		button[1].setSize(200,100);
		button[1].setLocation(200, 0);
		c1.add(button[1]);
		
		button[2] = new JButton("예매 내역 조회");
		button[2].setSize(200,100);
		button[2].setLocation(400, 0);
		c1.add(button[2]);
		
		button[3] = new JButton("예매 삭제");
		button[3].setSize(200,100);
		button[3].setLocation(600, 0);
		c1.add(button[3]);
		
		button[4] = new JButton("영화 변경");
		button[4].setSize(200,100);
		button[4].setLocation(800, 0);
		c1.add(button[4]);
		
		button[5] = new JButton("일정 변경");
		button[5].setSize(200,100);
		button[5].setLocation(1000, 0);
		c1.add(button[5]);
		
		JLabel label1 = new JLabel("모든 영화");
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setSize(100, 50);
		label1.setLocation(50, 100);
		c1.add(label1);
		
		JButton temp = new JButton("전체 테이블 보기");
		temp.setSize(200,100);
		temp.setLocation(30, 100);
		
		
		scrollPane.setSize(1700,400);
		scrollPane.setLocation(0, 150);
		scrollPane.add(temp);
		c1.add(scrollPane);
		
		buttonFunction();
		
		setVisible(true);
	}
	
	public void buttonFunction() {
		button[0].addActionListener(event -> {
		});
		
		button[1].addActionListener(event -> {
		});
		
		button[2].addActionListener(event -> {
		});
		
		button[3].addActionListener(event -> {
		});
		
		button[4].addActionListener(event -> {
		});
		
		button[5].addActionListener(event -> {
		});
	}
}

class SQL {
   Connection con;
   	
   public SQL() {
     String Driver="";
     String url="jdbc:mysql://localhost:3306/hospital?&serverTimezone=Asia/Seoul"; 
     String userid="hospital";
     String pwd="hospital";
     
     try {
       Class.forName("com.mysql.cj.jdbc.Driver");   
       System.out.println("드라이버 로드 성공");
     } catch(ClassNotFoundException e) {
         e.printStackTrace();
      }
     
     try {
       System.out.println("데이터베이스 연결 준비...");	
       con=DriverManager.getConnection(url, userid, pwd);
       System.out.println("데이터베이스 연결 성공");
     } catch(SQLException e) {
         e.printStackTrace();
       }
   }
   
   public void Initialize() {
	   try {
		  Statement stmt=con.createStatement();
		  stmt.executeUpdate("drop table if exists tickets;");
		  stmt.executeUpdate("drop table if exists movie_schedule;");
		  stmt.executeUpdate("drop table if exists seats;");
		  stmt.executeUpdate("drop table if exists booking;");
		  stmt.executeUpdate("drop table if exists screens;");
		  stmt.executeUpdate("drop table if exists members;");
		  stmt.executeUpdate("drop table if exists movies;");

		  stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `movie_book`.`movies` (\r\n"
		  		+ "  `movie_id` INT NOT NULL,\r\n"
		  		+ "  `movie_title` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  `running_time` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  `movie_rating` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  `director` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  `actor` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  `genre` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  `movie_introduction` TEXT NOT NULL,\r\n"
		  		+ "  `release_date` DATE NOT NULL,\r\n"
		  		+ "  PRIMARY KEY (`movie_id`))\r\n"
		  		+ "ENGINE = InnoDB;");
		  stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `movie_book`.`screens` (\r\n"
		  		+ "  `screen_id` INT NOT NULL,\r\n"
		  		+ "  `seats` INT NOT NULL,\r\n"
		  		+ "  `is_available` TINYINT NOT NULL DEFAULT 0,\r\n"
		  		+ "  PRIMARY KEY (`screen_id`))\r\n"
		  		+ "ENGINE = InnoDB;");
		  stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `movie_book`.`movie_schedule` (\r\n"
		  		+ "  `movie_schedule_id` INT NOT NULL,\r\n"
		  		+ "  `movie_id` INT NOT NULL,\r\n"
		  		+ "  `screen_id` INT NOT NULL,\r\n"
		  		+ "  `screening_start_date` DATE NOT NULL,\r\n"
		  		+ "  `screening_day` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  `screening_round` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  `screening_start_time` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  PRIMARY KEY (`movie_schedule_id`, `screen_id`, `movie_id`),\r\n"
		  		+ "  INDEX `fk_movie_schedule_movies1_idx` (`movie_id` ASC) VISIBLE,\r\n"
		  		+ "  INDEX `fk_movie_schedule_screens1_idx` (`screen_id` ASC) VISIBLE,\r\n"
		  		+ "  CONSTRAINT `fk_movie_schedule_movies1`\r\n"
		  		+ "    FOREIGN KEY (`movie_id`)\r\n"
		  		+ "    REFERENCES `movie_book`.`movies` (`movie_id`)\r\n"
		  		+ "    ON DELETE NO ACTION\r\n"
		  		+ "    ON UPDATE NO ACTION,\r\n"
		  		+ "  CONSTRAINT `fk_movie_schedule_screens1`\r\n"
		  		+ "    FOREIGN KEY (`screen_id`)\r\n"
		  		+ "    REFERENCES `movie_book`.`screens` (`screen_id`)\r\n"
		  		+ "    ON DELETE NO ACTION\r\n"
		  		+ "    ON UPDATE NO ACTION)\r\n"
		  		+ "ENGINE = InnoDB;");
		  stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `movie_book`.`seats` (\r\n"
		  		+ "  `seat_id` INT NOT NULL,\r\n"
		  		+ "  `screen_id` INT NOT NULL,\r\n"
		  		+ "  `is_available` TINYINT NOT NULL DEFAULT 0,\r\n"
		  		+ "  PRIMARY KEY (`seat_id`, `screen_id`),\r\n"
		  		+ "  INDEX `fk_seats_screens1_idx` (`screen_id` ASC) VISIBLE,\r\n"
		  		+ "  CONSTRAINT `fk_seats_screens1`\r\n"
		  		+ "    FOREIGN KEY (`screen_id`)\r\n"
		  		+ "    REFERENCES `movie_book`.`screens` (`screen_id`)\r\n"
		  		+ "    ON DELETE NO ACTION\r\n"
		  		+ "    ON UPDATE NO ACTION)\r\n"
		  		+ "ENGINE = InnoDB;");
		  stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `movie_book`.`members` (\r\n"
		  		+ "  `member_id` INT NOT NULL,\r\n"
		  		+ "  `name` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  `phone` VARCHAR(45) NULL,\r\n"
		  		+ "  `email` VARCHAR(45) NULL,\r\n"
		  		+ "  PRIMARY KEY (`member_id`))\r\n"
		  		+ "ENGINE = InnoDB;");
		  stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `movie_book`.`booking` (\r\n"
		  		+ "  `booking_id` INT NOT NULL,\r\n"
		  		+ "  `member_id` INT NOT NULL,\r\n"
		  		+ "  `pay_method` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  `pay_statement` TINYINT NOT NULL,\r\n"
		  		+ "  `price` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  PRIMARY KEY (`booking_id`, `member_id`),\r\n"
		  		+ "  INDEX `fk_booking_members1_idx` (`member_id` ASC) VISIBLE,\r\n"
		  		+ "  CONSTRAINT `fk_booking_members1`\r\n"
		  		+ "    FOREIGN KEY (`member_id`)\r\n"
		  		+ "    REFERENCES `movie_book`.`members` (`member_id`)\r\n"
		  		+ "    ON DELETE NO ACTION\r\n"
		  		+ "    ON UPDATE NO ACTION)\r\n"
		  		+ "ENGINE = InnoDB;");
		  stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `movie_book`.`tickets` (\r\n"
		  		+ "  `ticket_id` INT NOT NULL,\r\n"
		  		+ "  `movie_schedule_id` INT NOT NULL,\r\n"
		  		+ "  `screen_id` INT NOT NULL,\r\n"
		  		+ "  `seat_id` INT NOT NULL,\r\n"
		  		+ "  `booking_id` INT NOT NULL,\r\n"
		  		+ "  `is_ticket_printed` TINYINT NOT NULL DEFAULT 0,\r\n"
		  		+ "  `standard_price` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  `selling_price` VARCHAR(45) NOT NULL,\r\n"
		  		+ "  PRIMARY KEY (`ticket_id`, `movie_schedule_id`, `seat_id`, `screen_id`, `booking_id`),\r\n"
		  		+ "  INDEX `fk_tickets_movie_schedule1_idx` (`movie_schedule_id` ASC) VISIBLE,\r\n"
		  		+ "  INDEX `fk_tickets_seats1_idx` (`seat_id` ASC) VISIBLE,\r\n"
		  		+ "  INDEX `fk_tickets_booking1_idx` (`booking_id` ASC) VISIBLE,\r\n"
		  		+ "  INDEX `fk_tickets_screens1_idx` (`screen_id` ASC) VISIBLE,\r\n"
		  		+ "  CONSTRAINT `fk_tickets_movie_schedule1`\r\n"
		  		+ "    FOREIGN KEY (`movie_schedule_id`)\r\n"
		  		+ "    REFERENCES `movie_book`.`movie_schedule` (`movie_schedule_id`)\r\n"
		  		+ "    ON DELETE NO ACTION\r\n"
		  		+ "    ON UPDATE NO ACTION,\r\n"
		  		+ "  CONSTRAINT `fk_tickets_seats1`\r\n"
		  		+ "    FOREIGN KEY (`seat_id`)\r\n"
		  		+ "    REFERENCES `movie_book`.`seats` (`seat_id`)\r\n"
		  		+ "    ON DELETE NO ACTION\r\n"
		  		+ "    ON UPDATE NO ACTION,\r\n"
		  		+ "  CONSTRAINT `fk_tickets_booking1`\r\n"
		  		+ "    FOREIGN KEY (`booking_id`)\r\n"
		  		+ "    REFERENCES `movie_book`.`booking` (`booking_id`)\r\n"
		  		+ "    ON DELETE NO ACTION\r\n"
		  		+ "    ON UPDATE NO ACTION,\r\n"
		  		+ "  CONSTRAINT `fk_tickets_screens1`\r\n"
		  		+ "    FOREIGN KEY (`screen_id`)\r\n"
		  		+ "    REFERENCES `movie_book`.`screens` (`screen_id`)\r\n"
		  		+ "    ON DELETE NO ACTION\r\n"
		  		+ "    ON UPDATE NO ACTION)\r\n"
		  		+ "ENGINE = InnoDB;");

		  stmt.executeUpdate("INSERT INTO movies VALUES(1, '닥터 스트레인지', '2시간 20분', '15세', '조민수', '서진형', '판타지', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-01-09','%Y-%m-%d'));");
		  stmt.executeUpdate("INSERT INTO movies VALUES(2, '범죄도시1', '2시간 21분', '18세', '조민수', '이진형', '스릴러', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-02-09','%Y-%m-%d'));");
		  stmt.executeUpdate("INSERT INTO movies VALUES(3, '범죄도시3', '2시간 22분', '12세', '최민수', '국진형', '호러', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-03-09','%Y-%m-%d'));");
		  stmt.executeUpdate("INSERT INTO movies VALUES(4, '범죄도시4', '2시간 23분', '18세', '박민수', '오진형', '코미디', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-04-09','%Y-%m-%d'));");
		  stmt.executeUpdate("INSERT INTO movies VALUES(5, '범죄도시5', '2시간 24분', '15세', '김민수','임진형', '호러', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-05-09','%Y-%m-%d'));");
		  stmt.executeUpdate("INSERT INTO movies VALUES(6, '범죄도시6', '2시간 25분', '12세', '임민수', '김진형', '로맨스', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-06-09','%Y-%m-%d'));");
		  stmt.executeUpdate("INSERT INTO movies VALUES(7, '범죄도시6', '2시간 26분', '전체이용가', '오민수', '박진형', '판타지', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-07-09','%Y-%m-%d'));");
		  stmt.executeUpdate("INSERT INTO movies VALUES(8, '범죄도시7', '2시간 27분', '15세', '국민수', '최진형', '액션', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-08-09','%Y-%m-%d'));");
		  stmt.executeUpdate("INSERT INTO movies VALUES(9, '범죄도시8', '2시간 28분', '18세', '이민수', '조진형', '스릴러', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-09-09','%Y-%m-%d'));");
		  stmt.executeUpdate("INSERT INTO movies VALUES(10, '범죄도시2', '2시간 29분', '15세', '서민수', '리진형', '액션', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-10-09','%Y-%m-%d'));");

		  stmt.executeUpdate("insert into members values (1, 'Brooke', '735-214-8098', 'bmarlon0@constantcontact.com');");
		  stmt.executeUpdate("insert into members values (2, 'Martita', '964-716-5566', 'mbusk1@printfriendly.com');");
		  stmt.executeUpdate("insert into members values (3, 'Eziechiele', '768-705-1335', 'evan2@miitbeian.gov.cn');");
		  stmt.executeUpdate("insert into members values (4, 'Pacorro', '212-772-9242', 'phovard3@oracle.com');");
		  stmt.executeUpdate("insert into members values (5, 'Cori', '400-461-8374', 'caburrow4@163.com');");
		  stmt.executeUpdate("insert into members values (6, 'Ahmad', '555-584-2230', 'aparram5@yandex.ru');");
		  stmt.executeUpdate("insert into members values (7, 'Elliot', '550-149-1193', 'egiacubo6@elpais.com');");
		  stmt.executeUpdate("insert into members values (8, 'Barret', '432-886-2071', 'bleathwood7@cisco.com');");
		  stmt.executeUpdate("insert into members values (9, 'Ranique', '445-800-0015', 'rculter8@scientificamerican.com');");
		  stmt.executeUpdate("insert into members values (10, 'Jaclyn', '585-410-7043', 'jbouchier9@unesco.org');");

		  stmt.executeUpdate("insert into screens values (1, 500, 1);");
		  stmt.executeUpdate("insert into screens values (2, 250, 1);");
		  stmt.executeUpdate("insert into screens values (3, 500, 1);");
		  stmt.executeUpdate("insert into screens values (4, 650, 1);");
		  stmt.executeUpdate("insert into screens values (5, 200, 0);");
		  stmt.executeUpdate("insert into screens values (6, 155, 1);");
		  stmt.executeUpdate("insert into screens values (7, 140, 1);");
		  stmt.executeUpdate("insert into screens values (8, 124, 1);");
		  stmt.executeUpdate("insert into screens values (9, 450, 1);");
		  stmt.executeUpdate("insert into screens values (10, 123, 1);");
		  
		  stmt.executeUpdate("insert into seats values (1, 1, 1);");
		  stmt.executeUpdate("insert into seats values (2, 2, 1);");
		  stmt.executeUpdate("insert into seats values (3, 3, 0);");
		  stmt.executeUpdate("insert into seats values (4, 4, 1);");
		  stmt.executeUpdate("insert into seats values (5, 5, 0);");
		  stmt.executeUpdate("insert into seats values (6, 6, 1);");
		  stmt.executeUpdate("insert into seats values (7, 7, 0);");
		  stmt.executeUpdate("insert into seats values (8, 8, 1);");
		  stmt.executeUpdate("insert into seats values (9, 2, 0);");
		  stmt.executeUpdate("insert into seats values (10, 4, 0);");

		  stmt.executeUpdate("insert into movie_schedule values(1, 1, 1, STR_TO_DATE('2021-01-09','%Y-%m-%d'), '월요일', '1회차', '09시 15분');");
		  stmt.executeUpdate("insert into movie_schedule values(2, 2, 2, STR_TO_DATE('2021-02-09','%Y-%m-%d'), '화요일', '2회차', '10시 15분');");
		  stmt.executeUpdate("insert into movie_schedule values(3, 3, 3, STR_TO_DATE('2021-03-09','%Y-%m-%d'), '수요일', '2회차', '11시 15분');");
		  stmt.executeUpdate("insert into movie_schedule values(4, 4, 4, STR_TO_DATE('2021-04-09','%Y-%m-%d'), '목요일', '3회차', '10시 15분');");
		  stmt.executeUpdate("insert into movie_schedule values(5, 5, 5, STR_TO_DATE('2021-05-09','%Y-%m-%d'), '금요일', '5회차', '18시 15분');");
		  stmt.executeUpdate("insert into movie_schedule values(6, 6, 6, STR_TO_DATE('2021-06-09','%Y-%m-%d'), '월요일', '2회차', '10시 15분');");
		  stmt.executeUpdate("insert into movie_schedule values(7, 7, 7, STR_TO_DATE('2021-07-09','%Y-%m-%d'), '월요일', '7회차', '22시 15분');");
		  stmt.executeUpdate("insert into movie_schedule values(8, 8, 8, STR_TO_DATE('2021-08-09','%Y-%m-%d'), '수요일', '2회차', '11시 15분');");
		  stmt.executeUpdate("insert into movie_schedule values(9, 9, 9, STR_TO_DATE('2021-09-09','%Y-%m-%d'), '금요일', '4회차', '13시 15분');");
		  stmt.executeUpdate("insert into movie_schedule values(10, 10, 10, STR_TO_DATE('2021-10-09','%Y-%m-%d'), '일요일', '1회차', '09시 15분');");

		  stmt.executeUpdate("insert into booking values(1, 1, '카드', 1, '14,000원');");
		  stmt.executeUpdate("insert into booking values(2, 2, '카드', 1, '12,000원');");
		  stmt.executeUpdate("insert into booking values(3, 3, '현금', 1, '14,000원');");
		  stmt.executeUpdate("insert into booking values(4, 4, '카드', 1, '12,000원');");
		  stmt.executeUpdate("insert into booking values(5, 5, '현금', 1, '14,000원');");
		  stmt.executeUpdate("insert into booking values(6, 6, '카드', 1, '13,000원');");
		  stmt.executeUpdate("insert into booking values(7, 7, '무통장', 0, '14,000원');");
		  stmt.executeUpdate("insert into booking values(8, 8, '현금', 1, '14,000원');");
		  stmt.executeUpdate("insert into booking values(9, 9, '현금', 1, '12,000원');");
		  stmt.executeUpdate("insert into booking values(10, 10, '카드', 1, '14,000원');");
		  
		  stmt.executeUpdate("insert into tickets values(1, 1, 1, 1, 1, 1, '14,000원', '14,000원');");
		  stmt.executeUpdate("insert into tickets values(2, 2, 2, 2, 2, 2, '12,000원', '12,000원');");
		  stmt.executeUpdate("insert into tickets values(3, 3, 3, 3, 3, 3, '14,000원', '14,000원');");
		  stmt.executeUpdate("insert into tickets values(4, 4, 4, 4, 4, 4, '14,000원', '13,000원');");
		  stmt.executeUpdate("insert into tickets values(5, 5, 5, 5, 5, 5, '14,000원', '13,000원');");
		  stmt.executeUpdate("insert into tickets values(6, 6, 6, 6, 6, 6, '12,000원', '12,000원');");
		  stmt.executeUpdate("insert into tickets values(7, 7, 7, 7, 7, 7, '14,000원', '14,000원');");
		  stmt.executeUpdate("insert into tickets values(8, 8, 8, 8, 8, 8, '13,000원', '11,000원');");
		  stmt.executeUpdate("insert into tickets values(9, 9, 9, 9, 9, 9, '14,000원', '14,000원');");
		  stmt.executeUpdate("insert into tickets values(10, 10, 10, 10, 10, 10, '14,000원', '11,000원');");
	     } catch(SQLException e) {
	         e.printStackTrace();
	       }
	   }
   
   public void Insert(String tableName, String sql, JLabel result) {
	   if(tableName.equals("Doctors") || tableName.equals("Nurses") || tableName.equals("Patients") || tableName.equals("Treatments") || tableName.equals("Charts")) {
		   try {
		  	  	 Statement stmt=con.createStatement();
		  	  	 stmt.executeUpdate(sql);
		  	  } catch(SQLException e) { 
		  		  result.setText("SQL문을 다시 입력해주세요");
		  	    }	
	   }
	   else {
		   result.setText("테이블 이름을 다시 입력해주세요");
	   }
   }
   
   public void AllSearch(JTextArea resultLabel) {
	   
	   resultLabel.append("\t\tmovie_id \tmovie_title \t\trunning_time \t\tmovie_rating \t\tdirector \t\tactor\t\tgenre \t movie_introduction \t release_date\n");
	   
	   try {
		   Statement stmt=con.createStatement();
	  	  	 ResultSet rs=stmt.executeQuery("select * from movies");
	  	  	 int i=1;
	  	  	 
	  	  	 while(rs.next()) {
	  	  		resultLabel.append(i++ + "\t");
	  	  		resultLabel.append("\t"+rs.getInt(1));
	  	  		resultLabel.append("\t"+rs.getString(2));
	  	  		resultLabel.append("\t\t"+rs.getString(3));
	  	  		resultLabel.append("\t\t"+rs.getString(4));
	  	  		resultLabel.append("\t\t"+rs.getString(5));
	  	  		resultLabel.append("\t\t"+rs.getString(6));
	  	  		resultLabel.append("\t\t"+rs.getString(7));
	  	  		resultLabel.append("\t\t"+rs.getString(8));
	  	  		resultLabel.append("\t"+rs.getString(9)+"\n");
	  	  	 }
	  	  } catch(SQLException e) {
	  	  	   e.printStackTrace();
	  	    }
	   resultLabel.append("\n");
	   
	   try {
		   Statement stmt=con.createStatement();
		  	 ResultSet rs=stmt.executeQuery("select * from movie_schedule");
		  	 int i=1;
		  	 
		  	 resultLabel.append("\t\tmovie_schedule_id \tmovie_id \tscreen_id \tscreening_start_date \t\tscreening_day \t\tscreening_round \t\tscreening_start_time\n");
		  	 while(rs.next()) {
		  		resultLabel.append(i++ + "\t");
		  		resultLabel.append("\t"+rs.getObject(1));
		  		resultLabel.append("\t"+rs.getObject(2));
		  		resultLabel.append("\t"+rs.getObject(3));
		  		resultLabel.append("\t"+rs.getObject(4));
		  		resultLabel.append("\t\t"+rs.getObject(5));
		  		resultLabel.append("\t\t"+rs.getObject(6));
		  		resultLabel.append("\t\t"+rs.getObject(7)+"\n");
		  	 }
		  } catch(SQLException e) {
		  	   e.printStackTrace();
		    }
	   resultLabel.append("\n");
		   
	   try {
		   Statement stmt=con.createStatement();
	  	 ResultSet rs=stmt.executeQuery("select * from screens");
	  	 int i=1;
	  	 
	  	 resultLabel.append("\t\tscreen_id \tseats \t\tis_available\n");
	  	 while(rs.next()) {
	  		resultLabel.append(i++ + "\t");
	  		resultLabel.append("\t"+String.format("%06d",rs.getInt(1)));
	  		resultLabel.append("\t"+rs.getInt(2));
	  		resultLabel.append("\t\t"+rs.getInt(3)+"\n");
	  	 }
	   } catch(SQLException e) {
	  	   e.printStackTrace();
	    }
	   resultLabel.append("\n");
   
	   try {
		   Statement stmt=con.createStatement();
	  	 ResultSet rs=stmt.executeQuery("select * from tickets");
	  	 int i=1;
	  	 
	  	 resultLabel.append("\t\tticket_id \tmovie_schedule_id \tscreen_id \tseat_id \t\tbooking_id \t is_ticket_printed \t standard_price \t selling_price\n");
	  	 while(rs.next()) {
	  		resultLabel.append(i++ + "\t");
	  		resultLabel.append("\t"+rs.getObject(1));
	  		resultLabel.append("\t"+rs.getObject(2));
	  		resultLabel.append("\t"+rs.getObject(3));
	  		resultLabel.append("\t"+rs.getObject(4));
	  		resultLabel.append("\t"+rs.getObject(5));
	  		resultLabel.append("\t"+rs.getObject(6));
	  		resultLabel.append("\t"+rs.getObject(7));
	  		resultLabel.append("\t\t"+rs.getObject(8)+"\n");
	  	 }
	  } catch(SQLException e) {
	  	   e.printStackTrace();
	    }
	   resultLabel.append("\n");
   
   try {
	   Statement stmt=con.createStatement();
	  	 ResultSet rs=stmt.executeQuery("select * from seats");
	  	 int i=1;
	  	 
	  	 resultLabel.append("\t\tseat_id \tscreen_id \tis_available\n");
	  	 while(rs.next()) {
	  		resultLabel.append(i++ + "\t");
	  		resultLabel.append("\t"+rs.getObject(1));
	  		resultLabel.append("\t"+rs.getObject(2));
	  		resultLabel.append("\t\t"+rs.getObject(3)+"\n");
	  	 }
	  } catch(SQLException e) {
	  	   e.printStackTrace();
	    }
   resultLabel.append("\n");
   
   try {
	   Statement stmt=con.createStatement();
	  	 ResultSet rs=stmt.executeQuery("select * from members");
	  	 int i=1;
	  	 
	  	 resultLabel.append("\t\tmember_id \tname \tphone \temail\n");
	  	 while(rs.next()) {
	  		resultLabel.append(i++ + "\t");
	  		resultLabel.append("\t"+rs.getObject(1));
	  		resultLabel.append("\t"+rs.getObject(2));
	  		resultLabel.append("\t\t"+rs.getObject(3)+"\n");
	  	 }
	  } catch(SQLException e) {
	  	   e.printStackTrace();
	    }
	   resultLabel.append("\n");
	   
	   try {
		   Statement stmt=con.createStatement();
		  	 ResultSet rs=stmt.executeQuery("select * from booking");
		  	 int i=1;
		  	 
		  	 resultLabel.append("\t\tbooking_id \tpay_method \tpay_statement \t price \t member_id  \t date \n");
		  	 while(rs.next()) {
		  		resultLabel.append(i++ + "\t");
		  		resultLabel.append("\t"+rs.getObject(1));
		  		resultLabel.append("\t"+rs.getObject(2));
		  		resultLabel.append("\t"+rs.getObject(3));
		  		resultLabel.append("\t"+rs.getObject(4));
		  		resultLabel.append("\t"+rs.getObject(5));
		  		resultLabel.append("\t\t"+rs.getObject(6)+"\n");
		  	 }
		  } catch(SQLException e) {
		  	   e.printStackTrace();
		    }
		   resultLabel.append("\n");
   }
   
   public void Search1(JTextArea resultLabel) {
	   try {
		  	 Statement stmt=con.createStatement();
		  	 ResultSet rs=stmt.executeQuery("select pat_name, pat_addr, pat_phone, treat_contents, treat_date\r\n"
		  	 		+ "from Patients, treatments\r\n"
		  	 		+ "where patients.doc_id = (select doc_id from Doctors where doc_name = \"김병만\") and \r\n"
		  	 		+ "	patients.pat_id = treatments.pat_id;");
		  	 int i=1;
		  	resultLabel.append("\t\tpat_name \tpat_addr \tpat_phone \ttreat_contents \t\ttreat_date\n");
		  	 while(rs.next()) {
		  		resultLabel.append(i++ + "\t");
		  		resultLabel.append("\t"+rs.getString(1));
		  		resultLabel.append("\t "+rs.getString(2));
		  		resultLabel.append("\t"+rs.getString(3));
		  		resultLabel.append("\t"+rs.getString(4));
		  		resultLabel.append("\t\t"+rs.getString(5)+"\n");
		  	 }
		  } catch(SQLException e) {
		  	   e.printStackTrace();
		    }
	   resultLabel.append("\n");
   }
   
   public void Search2(JTextArea resultLabel) {
	   try {
		  	 Statement stmt=con.createStatement();
		  	 ResultSet rs=stmt.executeQuery("select *\r\n"
		  	 		+ "from Doctors\r\n"
		  	 		+ "where Doctors.doc_id Not IN (select doc_id from treatments);");
		  	 int i=1;
		  	 
		  	resultLabel.append("\t\tdoc_id \tmajor_treat \tdoc_name \tdoc_gen \tdoc_phone \t\tdoc_email\t\tdoc_position\n");
	  	  	 while(rs.next()) {
	  	  		resultLabel.append(i++ + "\t");
	  	  		resultLabel.append("\t"+String.format("%06d",rs.getInt(1)));
	  	  		resultLabel.append("\t"+rs.getString(2));
	  	  		resultLabel.append("\t"+rs.getString(3));
	  	  		resultLabel.append("\t"+rs.getString(4));
	  	  		resultLabel.append("\t"+rs.getString(5));
	  	  		resultLabel.append("\t\t"+rs.getString(6));
	  	  		resultLabel.append("\t"+rs.getString(7)+"\n");
	  	  	 }
		  } catch(SQLException e) {
		  	   e.printStackTrace();
		    }
	   resultLabel.append("\n");
   }
   
   public void Search3(JTextArea resultLabel) {
	   try {
		  	 Statement stmt=con.createStatement();
		  	 ResultSet rs=stmt.executeQuery("select *\r\n"
		  	 		+ "from Doctors\r\n"
		  	 		+ "where doctors.doc_id = (select doc_id \r\n"
		  	 		+ "						from (select count(*) AS cnt, doc_id from treatments group by treatments.doc_id) A\r\n"
		  	 		+ "						where cnt = (select max(cnt) from (select count(*) AS cnt, doc_id from treatments group by treatments.doc_id) B));");
		  	 int i=1;
		  	 
		  	resultLabel.append("\t\tdoc_id \tmajor_treat \tdoc_name \tdoc_gen \tdoc_phone \t\tdoc_email\t\tdoc_position\n");
	  	  	 while(rs.next()) {
	  	  		resultLabel.append(i++ + "\t");
	  	  		resultLabel.append("\t"+String.format("%06d",rs.getInt(1)));
	  	  		resultLabel.append("\t"+rs.getString(2));
	  	  		resultLabel.append("\t"+rs.getString(3));
	  	  		resultLabel.append("\t"+rs.getString(4));
	  	  		resultLabel.append("\t"+rs.getString(5));
	  	  		resultLabel.append("\t\t"+rs.getString(6));
	  	  		resultLabel.append("\t"+rs.getString(7)+"\n");
	  	  	 }
		  } catch(SQLException e) {
		  	   e.printStackTrace();
		    }
	   resultLabel.append("\n");
   }
}
   
public class databaseProject {
 public static void main(String args[]) {
  	new Title();
 }
}
