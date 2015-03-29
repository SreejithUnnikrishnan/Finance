create database finance;

use finance;

create table users(
	id INTEGER(4) NOT NULL AUTO_INCREMENT,
	name VARCHAR(15) NOT NULL,
	password VARCHAR(15) NOT NULL,
	security_question VARCHAR(50),
	security_answer VARCHAR(20),
	CONSTRAINT id_users_pk PRIMARY KEY (id)
); 

ALTER TABLE users AUTO_INCREMENT = 1001;

create table categories(
	category_id INTEGER(4) AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	cat_type VARCHAR(20) NOT NULL,
	CONSTRAINT category_id_categories_pk PRIMARY KEY (category_id)
	);
	
ALTER TABLE categories AUTO_INCREMENT = 5001;
	
create table income(
	id INTEGER(4) AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	budget DECIMAL(7,2) NOT NULL,
	start_date DATE NOT NULL,
	user_id INTEGER(4),
	CONSTRAINT id_income_pk PRIMARY KEY (id)
	);
ALTER TABLE income AUTO_INCREMENT = 1;	
alter table income add CONSTRAINT user_id_income_fk FOREIGN KEY (user_id) REFERENCES users(id);
alter table income add amount DECIMAL(5,2) NOT NULL;
	
create table expense(
	id INTEGER(4) AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	budget DECIMAL(7,2) NOT NULL,
	start_date DATE NOT NULL,
	user_id INTEGER(4),
	CONSTRAINT id_expense_pk PRIMARY KEY (id)
	);
ALTER TABLE expense AUTO_INCREMENT = 1;	
alter table expense add CONSTRAINT user_id_expense_fk FOREIGN KEY (user_id) REFERENCES users(id);
alter table expense add amount DECIMAL(5,2) NOT NULL;

/*
create table income_details(
	id INTEGER(4) AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	amount DECIMAL(5,2) NOT NULL,
	date_added DATE NOT NULL,
	user_id INTEGER(4),
	CONSTRAINT id_income_details_pk PRIMARY KEY (id)
);
ALTER TABLE income_details AUTO_INCREMENT = 1;	
alter table income_details add CONSTRAINT user_id_income_details_fk FOREIGN KEY (user_id) REFERENCES users(id);

create table expense_details(
	id INTEGER(4) AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	amount DECIMAL(5,2) NOT NULL,
	date_added DATE NOT NULL,
	user_id INTEGER(4),
	CONSTRAINT id_expense_details_pk PRIMARY KEY (id)
);
ALTER TABLE expense_details AUTO_INCREMENT = 1;	
alter table expense_details add CONSTRAINT user_id_expense_details_fk FOREIGN KEY (user_id) REFERENCES users(id);
*/
	
-- find first and last day
select * from income where start_date >= DATE_FORMAT(NOW() ,'%Y-%m-01') AND start_date <= LAST_DAY(now());