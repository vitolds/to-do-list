SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

create database if not exists java2 default character set utf8;
use java2;

-- Users table start

drop table if exists users;

create table if not exists users (
	UserID int not null auto_increment,
  FirstName varchar(20),
  LastName varchar(20),
  UserName varchar(16) not null,
  PassW varchar(255) not null, -- Password hash + salt ( ~255 )
  Email varchar(255) not null,
  Coins float(9, 2) default 0,
  primary key (UserID)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

alter table users
add index UserName (UserName),
add index PassW (PassW);

-- Users table end


-- Tasks table start --
DROP TABLE IF EXISTS tasks ;

CREATE TABLE IF NOT EXISTS tasks (
  TaskID INT NOT NULL AUTO_INCREMENT,
  Name VARCHAR(60) NOT NULL,
  Text VARCHAR(255) NULL,
  CreationDateTime TIMESTAMP NOT NULL,
  Deadline TIMESTAMP NULL,
  UserID INT NOT NULL,
  MainTask BOOLEAN DEFAULT FALSE,
  Priority TINYINT DEFAULT 0,
  Done BOOLEAN DEFAULT FALSE,

  PRIMARY KEY (TaskID),
  CONSTRAINT User_FK
	FOREIGN KEY (UserID)
    REFERENCES java2.users(UserID)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;
-- Tasks table end --

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;