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
);

alter table users
add index UserName (UserName),
add index PassW (PassW);

-- Users table end
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;