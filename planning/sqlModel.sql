-- MySQL Script generated by MySQL Workbench
-- Thu Jan  6 13:47:42 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Group-Activity-Organizer
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Group-Activity-Organizer
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Group-Activity-Organizer` DEFAULT CHARACTER SET utf8 ;
USE `Group-Activity-Organizer` ;

-- -----------------------------------------------------
-- Table `Group-Activity-Organizer`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group-Activity-Organizer`.`user` ;

CREATE TABLE IF NOT EXISTS `Group-Activity-Organizer`.`user` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `userRole` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group-Activity-Organizer`.`activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group-Activity-Organizer`.`activity` ;

CREATE TABLE IF NOT EXISTS `Group-Activity-Organizer`.`activity` (
  `activityId` INT NOT NULL AUTO_INCREMENT,
  `activityName` VARCHAR(35) NOT NULL,
  `description` VARCHAR(65) NULL,
  `location` VARCHAR(77) NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  `userId` INT NOT NULL,
  `maxParticipant` INT NOT NULL,
  `minParticipant` INT NOT NULL,
  `createBy` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`activityId`),
  INDEX `fk_Activities_Users_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_Activities_Users`
    FOREIGN KEY (`userId`)
    REFERENCES `Group-Activity-Organizer`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group-Activity-Organizer`.`point`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group-Activity-Organizer`.`point` ;

CREATE TABLE IF NOT EXISTS `Group-Activity-Organizer`.`point` (
  `points` INT NOT NULL DEFAULT 1,
  `userId` INT NOT NULL,
  `activityCompleted` TINYINT NOT NULL DEFAULT 0,
  INDEX `fk_Points_Users1_idx` (`userId` ASC) VISIBLE,
  PRIMARY KEY (`userId`),
  CONSTRAINT `fk_Points_Users1`
    FOREIGN KEY (`userId`)
    REFERENCES `Group-Activity-Organizer`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group-Activity-Organizer`.`contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group-Activity-Organizer`.`contact` ;

CREATE TABLE IF NOT EXISTS `Group-Activity-Organizer`.`contact` (
  `userId` INT NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `location` VARCHAR(75) NOT NULL,
  PRIMARY KEY (`userId`),
  INDEX `fk_User Data_Users1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_User Data_Users1`
    FOREIGN KEY (`userId`)
    REFERENCES `Group-Activity-Organizer`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
