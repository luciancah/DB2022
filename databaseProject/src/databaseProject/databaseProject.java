package databaseProject;

import java.io.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;

class Title extends JFrame {
	SQL sql = new SQL();
	JButton button[] = new JButton[2];

	public Title() {
		Container c1 = getContentPane();
		c1.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(2000, 2000);
		setTitle("18011560/조민수");

		button[0] = new JButton("관리자");
		button[0].setSize(300, 300);
		button[0].setLocation(700, 300);
		c1.add(button[0]);

		button[1] = new JButton("회원");
		button[1].setSize(300, 300);
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
	JTextField jt2 = new JTextField(1000);
	JLabel error = new JLabel();
	JTextArea result = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(result);

	public Manager(SQL _sql) {
		sql = _sql;
		Container c1 = getContentPane();
		c1.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(2000, 2000);
		setTitle("18011560/조민수");

		button[0] = new JButton("초기화");
		button[0].setSize(200, 100);
		button[0].setLocation(0, 0);
		c1.add(button[0]);

		button[1] = new JButton("입력/삭제/변경");
		button[1].setSize(200, 100);
		button[1].setLocation(500, 150);
		c1.add(button[1]);

		button[2] = new JButton("전체 테이블 보기");
		button[2].setSize(200, 100);
		button[2].setLocation(200, 0);
		c1.add(button[2]);

		JLabel label = new JLabel("SQL");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setSize(100, 10);
		label.setLocation(0, 200);
		c1.add(label);

		jt2.setLocation(100, 200);
		jt2.setSize(400, 50);
		c1.add(jt2);

		error.setSize(300, 50);
		error.setLocation(0, 250);
		c1.add(error);

		scrollPane.setSize(1700, 500);
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

			sql.Controll(error, jt2.getText());
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
	DefaultTableModel model = new DefaultTableModel();
	JTable total = new JTable();
	boolean bookingtableopen = false;
	JScrollPane scrollPane = new JScrollPane(total);
	JTextField movieName = new JTextField(20);
	JTextField directorName = new JTextField(20);
	JTextField actorName = new JTextField(20);
	JTextField genre = new JTextField(20);
	Container c1 = getContentPane();
	JLabel error = new JLabel();
	int tableSelectRow;

	public Member(SQL _sql) {
		sql = _sql;

		c1.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(2000, 2000);
		setTitle("18011560/조민수");

		button[0] = new JButton("영화 조회");
		button[0].setSize(200, 100);
		button[0].setLocation(0, 0);
		c1.add(button[0]);

		button[1] = new JButton("예매");
		button[1].setSize(200, 100);
		button[1].setLocation(200, 0);
		c1.add(button[1]);

		button[2] = new JButton("예매 내역 조회");
		button[2].setSize(200, 100);
		button[2].setLocation(400, 0);
		c1.add(button[2]);

		button[3] = new JButton("예매 삭제");
		button[3].setSize(200, 100);
		button[3].setLocation(600, 0);
		c1.add(button[3]);

		button[4] = new JButton("영화 변경");
		button[4].setSize(200, 100);
		button[4].setLocation(800, 0);
		c1.add(button[4]);

		button[5] = new JButton("일정 변경");
		button[5].setSize(200, 100);
		button[5].setLocation(1000, 0);
		c1.add(button[5]);

		JLabel label1 = new JLabel("영화명");
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setSize(50, 50);
		label1.setLocation(0, 100);
		c1.add(label1);
		movieName.setSize(150, 30);
		movieName.setLocation(50, 110);
		c1.add(movieName);

		JLabel label2 = new JLabel("감독명");
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setSize(50, 50);
		label2.setLocation(200, 100);
		c1.add(label2);
		directorName.setSize(150, 30);
		directorName.setLocation(250, 110);
		c1.add(directorName);

		JLabel label3 = new JLabel("배우명");
		label3.setHorizontalAlignment(JLabel.CENTER);
		label3.setSize(50, 50);
		label3.setLocation(400, 100);
		c1.add(label3);
		actorName.setSize(150, 30);
		actorName.setLocation(450, 110);
		c1.add(actorName);

		JLabel label4 = new JLabel("장르");
		label4.setHorizontalAlignment(JLabel.CENTER);
		label4.setSize(50, 50);
		label4.setLocation(600, 100);
		c1.add(label4);
		genre.setSize(150, 30);
		genre.setLocation(650, 110);
		c1.add(genre);

		error.setHorizontalAlignment(JLabel.CENTER);
		error.setSize(300, 50);
		error.setLocation(800, 100);
		c1.add(error);

		scrollPane.setSize(1700, 400);
		scrollPane.setLocation(0, 150);
		c1.add(scrollPane);

		buttonFunction();
		TableListener();

		setVisible(true);
	}

	public void buttonFunction() {
		button[0].addActionListener(event -> {
			bookingtableopen = false;
			error.setText(null);

			String where[] = { "", "", "", "" };
			String query = "select m.movie_title, m.running_time, m.movie_rating, m.director, m.actor, m.genre,\r\n"
					+ "		ms.screening_start_date, ms.screening_start_time, sc.screen_id, s.seat_id\r\n"
					+ "from movies as m\r\n" + "left join movie_schedule as ms\r\n" + "on m.movie_id = ms.movie_id\r\n"
					+ "left join screens as sc\r\n" + "on ms.screen_id = sc.screen_id \r\n" + "left join seats as s\r\n"
					+ "on ms.screen_id = s.screen_id\r\n" + "where sc.is_available = 1 and s.is_available = 1";
			int f = 0;

			where[0] = movieName.getText();
			where[1] = directorName.getText();
			where[2] = actorName.getText();
			where[3] = genre.getText();

			if (!where[0].equals("")) {
				query = query + " and " + "movie_title = '" + where[0] + "'";
				f = 1;
			}
			if (!where[1].equals("")) {
				if (f == 1)
					query = query + " and ";
				query = query + "director = '" + where[1] + "'";
				f = 1;
			}
			if (!where[2].equals("")) {
				if (f == 1)
					query = query + " and ";
				query = query + "actor = '" + where[2] + "'";
				f = 1;
			}
			if (!where[3].equals("")) {
				if (f == 1)
					query = query + " and ";
				query = query + "genre = '" + where[3] + "'";
			}

			sql.MovieSearch(total, error, query);
		});

		button[1].addActionListener(event -> {
			bookingtableopen = false;
			
			String query;
			
			query = "insert into booking (pay_method, pay_statement, price, member_id, pay_date) values (0, 1, '13,000원', "
					+ 1 + ", '" + total.getValueAt(tableSelectRow, 6) + "');";
					//memberid
			sql.ExcecuteUpdateQuery(query);

			int lastinsertid = sql.GetSQLInt("select last_insert_id() as booking_id;");
			int moviescheduleid = sql.GetSQLInt("select movie_schedule_id\r\n" + "from movie_schedule \r\n"
					+ "where screen_id = " + total.getValueAt(tableSelectRow, 8) + " and screening_start_date = '"
					+ total.getValueAt(tableSelectRow, 6) + "' and screening_start_time =  '"
					+ total.getValueAt(tableSelectRow, 7) + "';");
			
			query = "insert into tickets (movie_schedule_id, screen_id, seat_id, booking_id, is_ticket_printed, standard_price, selling_price)\r\n"
					+ "values (" + moviescheduleid + ", " + total.getValueAt(tableSelectRow, 8) + ", "
					+ total.getValueAt(tableSelectRow, 9) + ", " + lastinsertid + ", 0, '15,000원', '13,000원');";
			sql.ExcecuteUpdateQuery(query);

			query = "update seats set is_available = 0 where seat_id = " + total.getValueAt(tableSelectRow, 9) + ";";
			sql.ExcecuteUpdateQuery(query);
		});

		button[2].addActionListener(event -> {
			bookingtableopen = true;
			String query = "select m.movie_title, ms.screening_start_date, t.screen_id, t.seat_id, b.price\r\n"
					+ "from booking as b\r\n"
					+ "left join tickets as t\r\n"
					+ "on b.booking_id = t.booking_id\r\n"
					+ "left join movie_schedule as ms\r\n"
					+ "on ms.movie_schedule_id = t.movie_schedule_id\r\n"
					+ "left join movies as m\r\n"
					+ "on m.movie_id = ms.movie_id\r\n"
					+ "where member_id = 1;";
			
			sql.BookingSearch(total, query);
		});

		button[3].addActionListener(event -> {
		});

		button[4].addActionListener(event -> {
		});

		button[5].addActionListener(event -> {
		});
	}

	public void TableListener() {
		total.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				tableSelectRow = total.getSelectedRow();
				
				String query = "select ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time,\r\n"
						+ "t.screen_id, t.seat_id, s.seats, t.is_ticket_printed, t.standard_price, t.selling_price\r\n"
						+ "from tickets as t\r\n"
						+ "left join movie_schedule as ms\r\n"
						+ "on t.movie_schedule_id = ms.movie_schedule_id\r\n"
						+ "left join screens as s\r\n"
						+ "on t.screen_id = s.screen_id\r\n"
						+ "where booking_id in (select booking_id from tickets\r\n"
						+ "					where screen_id = " + total.getValueAt(tableSelectRow, 2) + " and seat_id = " + total.getValueAt(tableSelectRow, 3) + ");";
				
				if(bookingtableopen)
					sql.BookingClickShow(total, query);
			}
		});
	}
}

class SQL {
	Connection con;

	public SQL() {
		String Driver = "";
		String url = "jdbc:mysql://localhost:3306/hospital?&serverTimezone=Asia/Seoul";
		String userid = "hospital";
		String pwd = "hospital";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			System.out.println("데이터베이스 연결 준비...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Initialize() {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("drop table if exists tickets;");
			stmt.executeUpdate("drop table if exists movie_schedule;");
			stmt.executeUpdate("drop table if exists seats;");
			stmt.executeUpdate("drop table if exists booking;");
			stmt.executeUpdate("drop table if exists screens;");
			stmt.executeUpdate("drop table if exists members;");
			stmt.executeUpdate("drop table if exists movies;");

			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS movies (\r\n"
					+ "  `movie_id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `movie_title` VARCHAR(45) NOT NULL,\r\n"
					+ "  `running_time` VARCHAR(45) NOT NULL,\r\n" + "  `movie_rating` VARCHAR(45) NOT NULL,\r\n"
					+ "  `director` VARCHAR(45) NOT NULL,\r\n" + "  `actor` VARCHAR(45) NOT NULL,\r\n"
					+ "  `genre` VARCHAR(45) NOT NULL,\r\n" + "  `movie_introduction` TEXT NOT NULL,\r\n"
					+ "  `release_date` DATE NOT NULL,\r\n" + "  PRIMARY KEY (`movie_id`))\r\n" + "ENGINE = InnoDB;");
			stmt.executeUpdate(
					"CREATE TABLE IF NOT EXISTS screens (\r\n" + "  `screen_id` INT NOT NULL AUTO_INCREMENT,\r\n"
							+ "  `seats` INT NOT NULL,\r\n" + "  `is_available` TINYINT NOT NULL DEFAULT 0,\r\n"
							+ "  PRIMARY KEY (`screen_id`))\r\n" + "ENGINE = InnoDB;");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS movie_schedule (\r\n"
					+ "  `movie_schedule_id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `movie_id` INT NOT NULL,\r\n"
					+ "  `screen_id` INT NOT NULL,\r\n" + "  `screening_start_date` DATE NOT NULL,\r\n"
					+ "  `screening_day` VARCHAR(45) NOT NULL,\r\n" + "  `screening_round` VARCHAR(45) NOT NULL,\r\n"
					+ "  `screening_start_time` VARCHAR(45) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`movie_schedule_id`, `screen_id`, `movie_id`),\r\n"
					+ "  INDEX `fk_movie_schedule_movies1_idx` (`movie_id` ASC) VISIBLE,\r\n"
					+ "  INDEX `fk_movie_schedule_screens1_idx` (`screen_id` ASC) VISIBLE,\r\n"
					+ "  CONSTRAINT `fk_movie_schedule_movies1`\r\n" + "    FOREIGN KEY (`movie_id`)\r\n"
					+ "    REFERENCES `movies` (`movie_id`)\r\n" + "    ON DELETE CASCADE\r\n"
					+ "    ON UPDATE CASCADE,\r\n" + "  CONSTRAINT `fk_movie_schedule_screens1`\r\n"
					+ "    FOREIGN KEY (`screen_id`)\r\n" + "    REFERENCES `screens` (`screen_id`)\r\n"
					+ "    ON DELETE CASCADE\r\n" + "    ON UPDATE CASCADE)\r\n" + "ENGINE = InnoDB;");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS seats (\r\n" + "  `seat_id` INT NOT NULL AUTO_INCREMENT,\r\n"
					+ "  `screen_id` INT NOT NULL,\r\n" + "  `is_available` TINYINT NOT NULL DEFAULT 0,\r\n"
					+ "  PRIMARY KEY (`seat_id`, `screen_id`),\r\n"
					+ "  INDEX `fk_seats_screens1_idx` (`screen_id` ASC) VISIBLE,\r\n"
					+ "  CONSTRAINT `fk_seats_screens1`\r\n" + "    FOREIGN KEY (`screen_id`)\r\n"
					+ "    REFERENCES `screens` (`screen_id`)\r\n" + "    ON DELETE CASCADE\r\n"
					+ "    ON UPDATE CASCADE)\r\n" + "ENGINE = InnoDB;");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS members (\r\n"
					+ "  `member_id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
					+ "  `phone` VARCHAR(45) NULL,\r\n" + "  `email` VARCHAR(45) NULL,\r\n"
					+ "  PRIMARY KEY (`member_id`))\r\n" + "ENGINE = InnoDB;");
			stmt.executeUpdate(
					"CREATE TABLE IF NOT EXISTS booking (\r\n" + "  `booking_id` INT NOT NULL AUTO_INCREMENT,\r\n"
							+ "  `pay_method` VARCHAR(45) NOT NULL,\r\n" + "  `pay_statement` TINYINT NOT NULL,\r\n"
							+ "  `price` VARCHAR(45) NOT NULL,\r\n" + "  `member_id` INT NOT NULL,\r\n"
							+ "  `pay_date` DATE NOT NULL,\r\n" + "  PRIMARY KEY (`booking_id`, `member_id`),\r\n"
							+ "  INDEX `fk_booking_members1_idx` (`member_id` ASC) VISIBLE,\r\n"
							+ "  CONSTRAINT `fk_booking_members1`\r\n" + "    FOREIGN KEY (`member_id`)\r\n"
							+ "    REFERENCES `members` (`member_id`)\r\n" + "    ON DELETE CASCADE\r\n"
							+ "    ON UPDATE CASCADE)\r\n" + "ENGINE = InnoDB;");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS tickets (\r\n"
					+ "  `ticket_id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `movie_schedule_id` INT NOT NULL,\r\n"
					+ "  `screen_id` INT NOT NULL,\r\n" + "  `seat_id` INT NOT NULL,\r\n"
					+ "  `booking_id` INT NOT NULL,\r\n" + "  `is_ticket_printed` TINYINT NOT NULL DEFAULT 0,\r\n"
					+ "  `standard_price` VARCHAR(45) NOT NULL,\r\n" + "  `selling_price` VARCHAR(45) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`ticket_id`, `movie_schedule_id`, `screen_id`, `seat_id`, `booking_id`),\r\n"
					+ "  INDEX `fk_tickets_movie_schedule1_idx` (`movie_schedule_id` ASC) VISIBLE,\r\n"
					+ "  INDEX `fk_tickets_seats1_idx` (`seat_id` ASC) VISIBLE,\r\n"
					+ "  INDEX `fk_tickets_booking1_idx` (`booking_id` ASC) VISIBLE,\r\n"
					+ "  INDEX `fk_tickets_screens1_idx` (`screen_id` ASC) VISIBLE,\r\n"
					+ "  CONSTRAINT `fk_tickets_movie_schedule1`\r\n" + "    FOREIGN KEY (`movie_schedule_id`)\r\n"
					+ "    REFERENCES `movie_schedule` (`movie_schedule_id`)\r\n" + "    ON DELETE CASCADE\r\n"
					+ "    ON UPDATE CASCADE,\r\n" + "  CONSTRAINT `fk_tickets_seats1`\r\n"
					+ "    FOREIGN KEY (`seat_id`)\r\n" + "    REFERENCES `seats` (`seat_id`)\r\n"
					+ "    ON DELETE CASCADE\r\n" + "    ON UPDATE CASCADE,\r\n"
					+ "  CONSTRAINT `fk_tickets_booking1`\r\n" + "    FOREIGN KEY (`booking_id`)\r\n"
					+ "    REFERENCES `booking` (`booking_id`)\r\n" + "    ON DELETE CASCADE\r\n"
					+ "    ON UPDATE CASCADE,\r\n" + "  CONSTRAINT `fk_tickets_screens1`\r\n"
					+ "    FOREIGN KEY (`screen_id`)\r\n" + "    REFERENCES `screens` (`screen_id`)\r\n"
					+ "    ON DELETE CASCADE\r\n" + "    ON UPDATE CASCADE)\r\n" + "ENGINE = InnoDB;");

			stmt.executeUpdate(
					"INSERT INTO movies VALUES(1, '닥터 스트레인지', '2시간 20분', '15세', '조민수', '서진형', '판타지', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-01-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(2, '범죄도시1', '2시간 21분', '18세', '조민수', '이진형', '스릴러', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-02-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(3, '범죄도시3', '2시간 22분', '12세', '최민수', '국진형', '호러', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-03-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(4, '범죄도시4', '2시간 23분', '18세', '박민수', '오진형', '코미디', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-04-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(5, '범죄도시5', '2시간 24분', '15세', '김민수','임진형', '호러', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-05-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(6, '범죄도시6', '2시간 25분', '12세', '임민수', '김진형', '로맨스', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-06-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(7, '범죄도시6', '2시간 26분', '전체이용가', '오민수', '박진형', '판타지', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-07-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(8, '범죄도시7', '2시간 27분', '15세', '국민수', '최진형', '액션', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-08-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(9, '범죄도시8', '2시간 28분', '18세', '이민수', '조진형', '스릴러', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-09-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(10, '범죄도시2', '2시간 29분', '15세', '서민수', '리진형', '액션', '어쩔티비 안궁티비 안물티비', STR_TO_DATE('2021-10-09','%Y-%m-%d'));");

			stmt.executeUpdate(
					"insert into members values (1, 'Brooke', '735-214-8098', 'bmarlon0@constantcontact.com');");
			stmt.executeUpdate(
					"insert into members values (2, 'Martita', '964-716-5566', 'mbusk1@printfriendly.com');");
			stmt.executeUpdate(
					"insert into members values (3, 'Eziechiele', '768-705-1335', 'evan2@miitbeian.gov.cn');");
			stmt.executeUpdate("insert into members values (4, 'Pacorro', '212-772-9242', 'phovard3@oracle.com');");
			stmt.executeUpdate("insert into members values (5, 'Cori', '400-461-8374', 'caburrow4@163.com');");
			stmt.executeUpdate("insert into members values (6, 'Ahmad', '555-584-2230', 'aparram5@yandex.ru');");
			stmt.executeUpdate("insert into members values (7, 'Elliot', '550-149-1193', 'egiacubo6@elpais.com');");
			stmt.executeUpdate("insert into members values (8, 'Barret', '432-886-2071', 'bleathwood7@cisco.com');");
			stmt.executeUpdate(
					"insert into members values (9, 'Ranique', '445-800-0015', 'rculter8@scientificamerican.com');");
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

			stmt.executeUpdate("insert into seats values (1, 1, 0);");
			stmt.executeUpdate("insert into seats values (2, 2, 0);");
			stmt.executeUpdate("insert into seats values (3, 3, 0);");
			stmt.executeUpdate("insert into seats values (4, 4, 0);");
			stmt.executeUpdate("insert into seats values (5, 5, 0);");
			stmt.executeUpdate("insert into seats values (6, 6, 0);");
			stmt.executeUpdate("insert into seats values (7, 7, 0);");
			stmt.executeUpdate("insert into seats values (8, 8, 0);");
			stmt.executeUpdate("insert into seats values (9, 9, 0);");
			stmt.executeUpdate("insert into seats values (10, 10, 0);");
			stmt.executeUpdate("insert into seats values (11, 1, 1);");
			stmt.executeUpdate("insert into seats values (12, 2, 1);");
			stmt.executeUpdate("insert into seats values (13, 3, 1);");
			stmt.executeUpdate("insert into seats values (14, 4, 1);");
			stmt.executeUpdate("insert into seats values (15, 5, 1);");
			stmt.executeUpdate("insert into seats values (16, 6, 1);");
			stmt.executeUpdate("insert into seats values (17, 7, 1);");
			stmt.executeUpdate("insert into seats values (18, 8, 1);");
			stmt.executeUpdate("insert into seats values (19, 2, 1);");
			stmt.executeUpdate("insert into seats values (20, 4, 1);");

			stmt.executeUpdate(
					"insert into movie_schedule values(1, 1, 1, STR_TO_DATE('2021-01-09','%Y-%m-%d'), '월요일', '1회차', '09시 15분');");
			stmt.executeUpdate(
					"insert into movie_schedule values(2, 2, 2, STR_TO_DATE('2021-02-09','%Y-%m-%d'), '화요일', '2회차', '10시 15분');");
			stmt.executeUpdate(
					"insert into movie_schedule values(3, 3, 3, STR_TO_DATE('2021-03-09','%Y-%m-%d'), '수요일', '2회차', '11시 15분');");
			stmt.executeUpdate(
					"insert into movie_schedule values(4, 4, 4, STR_TO_DATE('2021-04-09','%Y-%m-%d'), '목요일', '3회차', '10시 15분');");
			stmt.executeUpdate(
					"insert into movie_schedule values(5, 5, 5, STR_TO_DATE('2021-05-09','%Y-%m-%d'), '금요일', '5회차', '18시 15분');");
			stmt.executeUpdate(
					"insert into movie_schedule values(6, 6, 6, STR_TO_DATE('2021-06-09','%Y-%m-%d'), '월요일', '2회차', '10시 15분');");
			stmt.executeUpdate(
					"insert into movie_schedule values(7, 7, 7, STR_TO_DATE('2021-07-09','%Y-%m-%d'), '월요일', '7회차', '22시 15분');");
			stmt.executeUpdate(
					"insert into movie_schedule values(8, 8, 8, STR_TO_DATE('2021-08-09','%Y-%m-%d'), '수요일', '2회차', '11시 15분');");
			stmt.executeUpdate(
					"insert into movie_schedule values(9, 9, 9, STR_TO_DATE('2021-09-09','%Y-%m-%d'), '금요일', '4회차', '13시 15분');");
			stmt.executeUpdate(
					"insert into movie_schedule values(10, 10, 10, STR_TO_DATE('2021-10-09','%Y-%m-%d'), '일요일', '1회차', '09시 15분');");
			stmt.executeUpdate(
					"insert into movie_schedule values(11, 1, 8, STR_TO_DATE('2021-01-10','%Y-%m-%d'), '화요일', '2회차', '09시 20분');");

			stmt.executeUpdate(
					"insert into booking values(1, '카드', 1, '13,000원', 1, STR_TO_DATE('2021-01-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(2, '카드', 1, '13,000원', 2, STR_TO_DATE('2021-02-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(3, '현금', 1, '13,000원', 3, STR_TO_DATE('2021-03-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(4, '카드', 1, '13,000원', 4, STR_TO_DATE('2021-04-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(5, '현금', 1, '13,000원', 5, STR_TO_DATE('2021-05-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(6, '카드', 1, '13,000원', 6, STR_TO_DATE('2021-06-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(7, '무통장', 0, '13,000원', 7, STR_TO_DATE('2021-07-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(8, '현금', 1, '13,000원', 8, STR_TO_DATE('2021-08-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(9, '현금', 1, '13,000원', 9, STR_TO_DATE('2021-09-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(10, '카드', 1, '13,000원', 10, STR_TO_DATE('2021-10-08','%Y-%m-%d'));");

			stmt.executeUpdate("insert into tickets values(1, 1, 1, 1, 1, 1, '15,000원', '13,000원');");
			stmt.executeUpdate("insert into tickets values(2, 2, 2, 2, 2, 2, '15,000원', '13,000원');");
			stmt.executeUpdate("insert into tickets values(3, 3, 3, 3, 3, 3, '15,000원', '13,000원');");
			stmt.executeUpdate("insert into tickets values(4, 4, 4, 4, 4, 4, '15,000원', '13,000원');");
			stmt.executeUpdate("insert into tickets values(5, 5, 5, 5, 5, 5, '15,000원', '13,000원');");
			stmt.executeUpdate("insert into tickets values(6, 6, 6, 6, 6, 6, '15,000원', '13,000원');");
			stmt.executeUpdate("insert into tickets values(7, 7, 7, 7, 7, 7, '15,000원', '13,000원');");
			stmt.executeUpdate("insert into tickets values(8, 8, 8, 8, 8, 8, '15,000원', '13,000원');");
			stmt.executeUpdate("insert into tickets values(9, 9, 9, 9, 9, 9, '15,000원', '13,000원');");
			stmt.executeUpdate("insert into tickets values(10, 10, 10, 10, 10, 10, '15,000원', '13,000원');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Controll(JLabel error, String sql) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			error.setText("완료했습니다");
		} catch (SQLException e) {
			error.setText("SQL문을 다시 입력해주세요");
		}
	}

	public void AllSearch(JTextArea resultLabel) {

		resultLabel.append(
				"\t\tmovie_id \tmovie_title \t\trunning_time \t\tmovie_rating \t\tdirector \t\tactor\t\tgenre \t movie_introduction \t release_date\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from movies");
			int i = 1;

			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + rs.getInt(1));
				resultLabel.append("\t" + rs.getString(2));
				resultLabel.append("\t\t" + rs.getString(3));
				resultLabel.append("\t\t" + rs.getString(4));
				resultLabel.append("\t\t" + rs.getString(5));
				resultLabel.append("\t\t" + rs.getString(6));
				resultLabel.append("\t\t" + rs.getString(7));
				resultLabel.append("\t\t" + rs.getString(8));
				resultLabel.append("\t" + rs.getString(9) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from movie_schedule");
			int i = 1;

			resultLabel.append(
					"\t\tmovie_schedule_id \tmovie_id \tscreen_id \tscreening_start_date \t\tscreening_day \t\tscreening_round \t\tscreening_start_time\n");
			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + rs.getObject(1));
				resultLabel.append("\t" + rs.getObject(2));
				resultLabel.append("\t" + rs.getObject(3));
				resultLabel.append("\t" + rs.getObject(4));
				resultLabel.append("\t\t" + rs.getObject(5));
				resultLabel.append("\t\t" + rs.getObject(6));
				resultLabel.append("\t\t" + rs.getObject(7) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from screens");
			int i = 1;

			resultLabel.append("\t\tscreen_id \tseats \t\tis_available\n");
			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + String.format("%06d", rs.getInt(1)));
				resultLabel.append("\t" + rs.getInt(2));
				resultLabel.append("\t\t" + rs.getInt(3) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tickets");
			int i = 1;

			resultLabel.append(
					"\t\tticket_id \tmovie_schedule_id \tscreen_id \tseat_id \t\tbooking_id \t is_ticket_printed \t standard_price \t selling_price\n");
			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + rs.getObject(1));
				resultLabel.append("\t" + rs.getObject(2));
				resultLabel.append("\t" + rs.getObject(3));
				resultLabel.append("\t" + rs.getObject(4));
				resultLabel.append("\t" + rs.getObject(5));
				resultLabel.append("\t" + rs.getObject(6));
				resultLabel.append("\t" + rs.getObject(7));
				resultLabel.append("\t\t" + rs.getObject(8) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from seats");
			int i = 1;

			resultLabel.append("\t\tseat_id \tscreen_id \tis_available\n");
			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + rs.getObject(1));
				resultLabel.append("\t" + rs.getObject(2));
				resultLabel.append("\t\t" + rs.getObject(3) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from members");
			int i = 1;

			resultLabel.append("\t\tmember_id \tname \tphone \temail\n");
			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + rs.getObject(1));
				resultLabel.append("\t" + rs.getObject(2));
				resultLabel.append("\t\t" + rs.getObject(3) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from booking");
			int i = 1;

			resultLabel.append("\t\tbooking_id \tpay_method \tpay_statement \t price \t member_id  \t date \n");
			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + rs.getObject(1));
				resultLabel.append("\t" + rs.getObject(2));
				resultLabel.append("\t" + rs.getObject(3));
				resultLabel.append("\t" + rs.getObject(4));
				resultLabel.append("\t" + rs.getObject(5));
				resultLabel.append("\t\t" + rs.getObject(6) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");
	}

	public void ExcecuteUpdateQuery(String sql) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int GetSQLInt(String sql) {
		int result = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
				result = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void MovieSearch(JTable table, JLabel error, String sql) {
		Object[] attribute = { "영화명", "상영시간", "상영등급", "감독명", "배우명", "장르", "상영시작일", "상영시작시간", "상영관", "좌석번호" };
		DefaultTableModel model = new DefaultTableModel(attribute, 0);

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Vector<Object> v = new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				v.add(rs.getString(8));
				v.add(rs.getString(9));
				v.add(rs.getString(10));
				model.addRow(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			error.setText("잘못 입력했습니다.");
		}

		table.setModel(model);
	}

	public void BookingSearch(JTable table, String sql) {
		String[] attribute = { "영화명", "상영일", "상영관번호", "좌석번호", "판매가격" };
		DefaultTableModel model = new DefaultTableModel(attribute, 0);
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Vector<Object> v = new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				model.addRow(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		table.setModel(model);
	}

	public void BookingClickShow(JTable table, String sql) {
		String[] attribute = {"상영일", "상영요일", "상영회차", "상영시작일", "상영관번호", "좌석번호", "좌석수", "발권여부", "표준가격", "판매가격"};
		DefaultTableModel model = new DefaultTableModel(attribute, 0);
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Vector<Object> v = new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				v.add(rs.getString(8));
				v.add(rs.getString(9));
				v.add(rs.getString(10));
				model.addRow(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		table.setModel(model);
	}
}

public class databaseProject {
	public static void main(String args[]) {
		new Title();
	}
}
