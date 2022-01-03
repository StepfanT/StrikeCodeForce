-- MySQL Script generated by MySQL Workbench
-- Mon Jan  3 14:06:32 2022
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
-- Table `Group-Activity-Organizer`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group-Activity-Organizer`.`Users` ;

CREATE TABLE IF NOT EXISTS `Group-Activity-Organizer`.`Users` (
  `idUsers` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`idUsers`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group-Activity-Organizer`.`Activities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group-Activity-Organizer`.`Activities` ;

CREATE TABLE IF NOT EXISTS `Group-Activity-Organizer`.`Activities` (
  `idActivities` INT NOT NULL,
  `activityName` VARCHAR(35) NOT NULL,
  `Description` VARCHAR(45) NULL,
  `Location` VARCHAR(77) NOT NULL,
  `Time` DATETIME NOT NULL,
  `Points` INT NOT NULL,
  `Users_idUsers` INT NOT NULL,
  PRIMARY KEY (`idActivities`),
  INDEX `fk_Activities_Users_idx` (`Users_idUsers` ASC) VISIBLE,
  CONSTRAINT `fk_Activities_Users`
    FOREIGN KEY (`Users_idUsers`)
    REFERENCES `Group-Activity-Organizer`.`Users` (`idUsers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group-Activity-Organizer`.`Points`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group-Activity-Organizer`.`Points` ;

CREATE TABLE IF NOT EXISTS `Group-Activity-Organizer`.`Points` (
  `idPoints` INT NOT NULL,
  `Users_idUsers` INT NOT NULL,
  PRIMARY KEY (`idPoints`),
  INDEX `fk_Points_Users1_idx` (`Users_idUsers` ASC) VISIBLE,
  CONSTRAINT `fk_Points_Users1`
    FOREIGN KEY (`Users_idUsers`)
    REFERENCES `Group-Activity-Organizer`.`Users` (`idUsers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group-Activity-Organizer`.`User Data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group-Activity-Organizer`.`User Data` ;

CREATE TABLE IF NOT EXISTS `Group-Activity-Organizer`.`User Data` (
  `Users_idUsers` INT NOT NULL,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `user_location` VARCHAR(45) NULL,
  PRIMARY KEY (`Users_idUsers`),
  INDEX `fk_User Data_Users1_idx` (`Users_idUsers` ASC) VISIBLE,
  CONSTRAINT `fk_User Data_Users1`
    FOREIGN KEY (`Users_idUsers`)
    REFERENCES `Group-Activity-Organizer`.`Users` (`idUsers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
