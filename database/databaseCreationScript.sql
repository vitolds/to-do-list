SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

create database if not exists java2 default character set utf8;
use java2;

-- Users table start

drop table if exists users;

create table if not exists users (
  UserId BIGINT(11) not null auto_increment,
  FirstName varchar(20),
  LastName varchar(20),
  UserName varchar(16) not null unique,
  PassW varchar(255) not null,
  Email varchar(255) not null unique,
  Coins float(9, 2) default 0,
  Visible BOOLEAN DEFAULT FALSE,
  TaskCount int default 0,
  TaskSlots int default 3,
  primary key (UserId)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

alter table users
add index UserNameI (UserName),
add index EmailI (Email),
add index PassWI (PassW);

-- Users table end

-- Users role table start

DROP TABLE IF EXISTS user_roles;

CREATE TABLE if NOT EXISTS user_roles(
  UserRoleId BIGINT(11) NOT NULL AUTO_INCREMENT,
  UserId BIGINT(11) NOT NULL,
  Role VARCHAR(20) NOT NULL,
  PRIMARY KEY (UserRoleId),
  UNIQUE KEY UserIdRoleU (Role, UserId),
  UNIQUE INDEX UserIdI (UserId ASC),
  CONSTRAINT UserIdFK FOREIGN KEY (UserId) REFERENCES users (UserId)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;
-- Users role table end

-- Tasks table start --
DROP TABLE IF EXISTS tasks ;

CREATE TABLE IF NOT EXISTS tasks (
  TaskId BIGINT(11) NOT NULL AUTO_INCREMENT,
  UserId BIGINT(11) NOT NULL,
  Name VARCHAR(60) NOT NULL,
  Text VARCHAR(255) NULL,
  CreationDateTime TIMESTAMP NOT NULL,
  Deadline TIMESTAMP NULL,
  MainTask BOOLEAN DEFAULT FALSE,
  Priority TINYINT DEFAULT 0,
  Done BOOLEAN DEFAULT FALSE,
  Coins FLOAT(9, 2) DEFAULT 0,

  PRIMARY KEY (TaskId),
  CONSTRAINT User_FK FOREIGN KEY (UserId) REFERENCES users (UserId)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;
-- Tasks table end --

-- -----------------------------------------------------
-- Table `parent`
-- -----------------------------------------------------
-- CREATE TABLE IF NOT EXISTS `parent` (
--   `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
--   `name` VARCHAR(60) NOT NULL,
--   `color` VARCHAR(45) NULL DEFAULT 0,
--   PRIMARY KEY (`id`),
--   UNIQUE INDEX `name_UNIQUE` (`name` ASC))
-- ENGINE = InnoDB;
-- Parent table end --

-- -----------------------------------------------------
-- Table `ToDoList_child`
-- -----------------------------------------------------
-- CREATE TABLE IF NOT EXISTS `ToDoList_child` (
--   `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
--   `name` VARCHAR(60) NOT NULL,
--   `Todo id` INT UNSIGNED NOT NULL,
--   `color` VARCHAR(45) NOT NULL DEFAULT 0,
--   PRIMARY KEY (`id`),
--   UNIQUE INDEX `name_UNIQUE` (`name` ASC),
--   CONSTRAINT `Todo id`
--     FOREIGN KEY (`id`)
--     REFERENCES `parent` (`id`)
--     ON DELETE NO ACTION
--     ON UPDATE NO ACTION)
-- ENGINE = InnoDB;
-- Child table end --


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
