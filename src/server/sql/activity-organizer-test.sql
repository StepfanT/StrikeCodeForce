-- MySQL Script generated by MySQL Workbench
-- Tue Jan  4 11:52:30 2022
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
DROP DATABASE IF EXISTS `Group-Activity-Organizer`;
CREATE SCHEMA IF NOT EXISTS `Group-Activity-Organizer` DEFAULT CHARACTER SET utf8 ;
USE `Group-Activity-Organizer` ;

-- -----------------------------------------------------
-- Table `Group-Activity-Organizer`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group-Activity-Organizer`.`user` ;

CREATE TABLE IF NOT EXISTS `Group-Activity-Organizer`.`user` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(2048) NOT NULL,
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
DROP TABLE IF EXISTS `Group-Activity-Organizer`.`points` ;

CREATE TABLE IF NOT EXISTS `Group-Activity-Organizer`.`points` (
  `points` INT NOT NULL DEFAULT 1,
  `userId` INT NOT NULL,
  `activityId` INT NOT NULL,
  `activityCompleted` TINYINT NOT NULL DEFAULT 0,
  constraint fk_point_activityId
        foreign key (activityId)
        references activity(activityId),
  CONSTRAINT `fk_Points_Users1`
    FOREIGN KEY (`userId`)
    REFERENCES `Group-Activity-Organizer`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Group-Activity-Organizer`.`user_activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group-Activity-Organizer`.`user_activity` ;

CREATE TABLE IF NOT EXISTS `Group-Activity-Organizer`.`user_activity` (
  `userId` INT NOT NULL,
  `activityId` INT NOT NULL,
  PRIMARY KEY (userId, activityId),
  constraint fk_user_activity_activityId
        foreign key (activityId)
        references activity(activityId),
  CONSTRAINT `fk_user_activity_userId`
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


delimiter //
create procedure set_known_good_state()
begin  

    delete from contact;	
    delete from points;
    delete from user_activity;
    delete from activity;
    alter table activity auto_increment = 1;    
    delete from user;
    alter table user auto_increment = 1;
      
      
      insert into user(username, password, userRole) values
        ('frigiid', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin'),
        ('loneWolf', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin'),
        ('guardians', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa','admin'),
        ('ello', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin'),
        ('Poppet', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin'),
        ('ThisisA', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin'),
        ('referenceTo', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin'),
        ('PirateOf', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin'),
        ('TheCarribean', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin'),
        ('BecauseItwas', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin'),
        ('AModernMasterpiece', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin'),
        ('OfBlockbusterStory', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin'),
        ('TellingAnd', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin'),
        ('Itshouldbeknown', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin'),
        ('ThankYou', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin');
        
	insert into activity(activityName, description, location, date, time, userId, maxParticipant, minParticipant, createBy)
    values
	('soccer','9v9', 'That one place in Milwaukee','2022-01-11', '15:00', '1', '25', '18', 'Stepfan Thelemaque'),
    ('Paintball','5v5', 'Court near Waterfall: Hope, Washington','2022-01-15','05:00', '2', '15','10','John Rambo'),
	('football','5v5, flag football', 'Outer Space','2022-01-23', '12:00', '3', '25','10','Mister Blue Sky'),
    ('Hot Lava Tag','Anyone can join!', 'Mount Vesuvius','2022-01-25', '15:00', '5', '25', '18', 'Noah Ark'),
    ('basketball','5v5', 'Crypto Arena','2022-02-15','05:00', '4', '15','10','Oprah Winfrey'),
	('football','Iron bar tackle football', 'Gridiron Gang','2022-03-23', '12:00', '10', '25','10','John Connor'),
    ('Hockey','9v9', 'H-E-Double Hockeysticks','2022-03-11', '15:00', '11', '25', '18', 'Emilio Estevez Estevehz'),
    ('curling','2v2, contact curling', 'Ice Age Plaza','2022-03-13', '2:00', '9', '6','4','The Dude'),
    ('Darts','3v3, ft Chad Bloominshine', 'Coyote Ugly','2022-03-11', '15:00', '8', '25', '18', 'Edgar Poe'),
     ('basketball','5v5', 'Court near Waterfall: Hope, Washington','2022-04-15','05:00', '7', '15','10','Julius Caesar'),
	('football','12v5, Unwinnable', 'Outer Space','2022-05-23', '12:00', '6', '25','10','Gordon Ramsay'),
    ('soccer','11v11', 'Kansas City','2022-06-11', '15:00', '13', '25', '13', 'Meghan Markle'),
    ('Skeet Shooting','Duck Hunt time!', 'If you know you know','2022-08-11', '15:00', '12', '25', '18', 'Neil Armstrong'),
    ('Ice run','Feel the rhythm ', 'Jamaican Bobsled Team','2022-12-13', '2:00', '15', '6','4','Homer Simpson'),    
    ('Running','free run', 'Mount Colossus','2023-01-25','06:00', '14', '15','5','LeRon James');
    
    
    insert into contact (userId, firstName, lastName, email, location)
    values 
	('1','Stepfan', 'Thelemaque', null, 'Milwaukee'),
  	('2','John', 'Rambo', null, 'The Forest'),
    ('3','Mister', 'Blue Sky', 'ELO@old.com', 'Sydney'),
    ('4','Oprah', 'Winfrey', 'ELO@old.com', 'Los Angeles'),
    ('5','Noah', 'Ark', null, 'The literal ocean'),
  	('6','Gordon', 'Ramsay', 'IdiotSandwich@chefRus.com', 'Fox Network, Sunday nights'),
    ('7','Julius', 'Ceasar', 'EtTu@dead.com', 'Idk Rome or something'),
    ('8','Edgar', 'Poe', 'POEtry@raven.com', 'Cellar'),
    ('9','The', 'Dude', null, 'Bowling Alley'),
  	('10','John', 'Connor', null, 'Do not look for me'),
    ('11','Emilio', 'Estevez Estevehz', 'Falco@song.com', 'Rock me Wolfgang'),
    ('12','Neil', 'Armstrong', 'SpaceLuvr@nasa.com', 'Moon'),
    ('13','Meghan', 'Markle', 'TheSuperiorPrincess@Windsor.com' , 'London'),
  	('14','LeRon', 'James', 'notThatOne@typo.com', 'Kansas City'),
    ('15','Homer', 'Simpson', 'Odyssey@epic.com', 'Greece');  
    
    insert into user_activity
    values
    (1,1),(1,2),(2,2),(3,1),(2,3);
    
    insert into points
    values
    (2,1,1,0),(1,1,2,0),(4,1,3,1);
end //
-- 4. Change the statement terminator back to the original.
delimiter ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;