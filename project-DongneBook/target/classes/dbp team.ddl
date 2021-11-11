
DROP SEQUENCE idseq;

CREATE SEQUENCE idseq
	INCREMENT BY 1
	START WITH 1;

DROP TABLE condition CASCADE CONSTRAINTS PURGE;

DROP TABLE transaction CASCADE CONSTRAINTS PURGE;

DROP TABLE wishlist CASCADE CONSTRAINTS PURGE;

DROP TABLE book CASCADE CONSTRAINTS PURGE;

DROP TABLE category CASCADE CONSTRAINTS PURGE;

DROP TABLE chat CASCADE CONSTRAINTS PURGE;

DROP TABLE users CASCADE CONSTRAINTS PURGE;

CREATE TABLE book
(
	title                VARCHAR2(60) NOT NULL ,
	author               VARCHAR2(60) NOT NULL ,
	publisher            VARCHAR2(60) NOT NULL ,
	publication_date     DATE NOT NULL ,
	book_id              NUMBER NOT NULL ,
	price                NUMBER NOT NULL  CONSTRAINT  price_1262001079 CHECK (price BETWEEN 0 AND 1000000),
	description          VARCHAR2(600) NULL ,
	image                VARCHAR2(600) NOT NULL ,
	user_id              VARCHAR2(20) NOT NULL ,
	category_id          CHAR(18) NOT NULL 
);

CREATE UNIQUE INDEX XPKbook ON book
(book_id   ASC);

ALTER TABLE book
	ADD CONSTRAINT  XPKbook PRIMARY KEY (book_id);

CREATE TABLE condition
(
	writing              NUMBER NOT NULL ,
	page_discoloration   NUMBER NOT NULL ,
	page_damage          NUMBER NOT NULL ,
	cover_damage         NUMBER NOT NULL ,
	book_id              NUMBER NOT NULL 
);

CREATE UNIQUE INDEX XPKcondition ON condition
(book_id   ASC);

ALTER TABLE condition
	ADD CONSTRAINT  XPKcondition PRIMARY KEY (book_id);

CREATE TABLE category
(
	cd_name              VARCHAR2(30) NOT NULL ,
	category_id          CHAR(18) NOT NULL 
);

CREATE UNIQUE INDEX XPKcategory ON category
(category_id   ASC);

ALTER TABLE category
	ADD CONSTRAINT  XPKcategory PRIMARY KEY (category_id);

CREATE TABLE users
(
	user_id              VARCHAR2(20) NOT NULL ,
	password             VARCHAR2(13) NOT NULL  CONSTRAINT  password_1124548248 CHECK (password BETWEEN 6 AND 13),
	name                 VARCHAR2(10) NOT NULL  CONSTRAINT  name_1344818611 CHECK (name BETWEEN 2 AND 10),
	location             VARCHAR2(300) NOT NULL ,
	phone                VARCHAR2(40) NOT NULL  CONSTRAINT  phone_1254537123 CHECK (phone BETWEEN 11 AND 11),
	email                VARCHAR2(40) NOT NULL  CONSTRAINT  email_1102294692 CHECK (email >= 5)
);

CREATE UNIQUE INDEX XPKuser ON users
(user_id   ASC);

ALTER TABLE users
	ADD CONSTRAINT  XPKuser PRIMARY KEY (user_id);

CREATE TABLE transaction
(
	last_price           NUMBER NOT NULL  CONSTRAINT  price_2089030658 CHECK (last_price BETWEEN 0 AND 1000000),
	meeting_date         DATE NOT NULL ,
	meeting_place        VARCHAR2(100) NOT NULL ,
	meeting_memo         VARCHAR2(300) NULL ,
	book_id              NUMBER NOT NULL ,
	user_id              VARCHAR2(20) NOT NULL ,
	sold                 NUMBER NOT NULL 
);

CREATE UNIQUE INDEX XPKtransaction ON transaction
(book_id   ASC,user_id   ASC);

ALTER TABLE transaction
	ADD CONSTRAINT  XPKtransaction PRIMARY KEY (book_id,user_id);

CREATE TABLE wishlist
(
	user_id              VARCHAR2(20) NOT NULL ,
	book_id              NUMBER NOT NULL 
);

CREATE UNIQUE INDEX XPKwishlist ON wishlist
(user_id   ASC,book_id   ASC);

ALTER TABLE wishlist
	ADD CONSTRAINT  XPKwishlist PRIMARY KEY (user_id,book_id);

CREATE TABLE chat
(
	created_at           TIMESTAMP NOT NULL ,
	contents             VARCHAR2(400) NOT NULL ,
	chat_id              NUMBER NOT NULL ,
	sender_id            VARCHAR2(20) NOT NULL ,
	receiver_id          VARCHAR2(20) NOT NULL 
);

CREATE UNIQUE INDEX XPKchat ON chat
(chat_id   ASC);

ALTER TABLE chat
	ADD CONSTRAINT  XPKchat PRIMARY KEY (chat_id);

ALTER TABLE book
	ADD (CONSTRAINT R_4 FOREIGN KEY (user_id) REFERENCES users (user_id));

ALTER TABLE book
	ADD (CONSTRAINT R_37 FOREIGN KEY (category_id) REFERENCES category (category_id));

ALTER TABLE condition
	ADD (CONSTRAINT R_1 FOREIGN KEY (book_id) REFERENCES book (book_id));

ALTER TABLE transaction
	ADD (CONSTRAINT R_2 FOREIGN KEY (book_id) REFERENCES book (book_id));

ALTER TABLE transaction
	ADD (CONSTRAINT R_22 FOREIGN KEY (user_id) REFERENCES users (user_id));

ALTER TABLE wishlist
	ADD (CONSTRAINT R_14 FOREIGN KEY (user_id) REFERENCES users (user_id));

ALTER TABLE wishlist
	ADD (CONSTRAINT R_29 FOREIGN KEY (book_id) REFERENCES book (book_id));

ALTER TABLE chat
	ADD (CONSTRAINT R_49 FOREIGN KEY (sender_id) REFERENCES users (user_id) ON DELETE SET NULL);

ALTER TABLE chat
	ADD (CONSTRAINT R_50 FOREIGN KEY (receiver_id) REFERENCES users (user_id) ON DELETE SET NULL);
