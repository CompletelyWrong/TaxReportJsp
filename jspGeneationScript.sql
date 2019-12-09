-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`action_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`action_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`inspectors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`inspectors` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `patronymic` VARCHAR(255) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_inspectors_role1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `1`
    FOREIGN KEY (`role_id`)
    REFERENCES `mydb`.`role` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`report_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`report_status` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `patronymic` VARCHAR(255) NOT NULL,
  `ind_code` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_users_role1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `mydb`.`role` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`reports`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`reports` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `file_link` VARCHAR(255) NOT NULL,
  `date_of_add` DATETIME NOT NULL,
  `status_id` INT(11) NOT NULL,
  `users_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_report_report_status1_idx` (`status_id` ASC) VISIBLE,
  INDEX `fk_report_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_report_report_status`
    FOREIGN KEY (`status_id`)
    REFERENCES `mydb`.`report_status` (`id`),
  CONSTRAINT `fk_report_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `mydb`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`actions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`actions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `inspector_id` INT(11) NOT NULL,
  `date` DATETIME NOT NULL,
  `message` VARCHAR(255) NULL DEFAULT NULL,
  `action_type_id` INT(11) NOT NULL,
  `report_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_action_inspector` (`inspector_id` ASC) VISIBLE,
  INDEX `fk_action_action_type1_idx` (`action_type_id` ASC) VISIBLE,
  INDEX `fk_action_report1_idx` (`report_id` ASC) VISIBLE,
  CONSTRAINT `fk_action_action_type`
    FOREIGN KEY (`action_type_id`)
    REFERENCES `mydb`.`action_type` (`id`),
  CONSTRAINT `fk_action_inspector`
    FOREIGN KEY (`inspector_id`)
    REFERENCES `mydb`.`inspectors` (`id`),
  CONSTRAINT `fk_action_report`
    FOREIGN KEY (`report_id`)
    REFERENCES `mydb`.`reports` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`inspectors_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`inspectors_users` (
  `inspector_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `active` ENUM('true', 'false') NULL DEFAULT NULL,
  PRIMARY KEY (`inspector_id`, `user_id`),
  INDEX `reftouser_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `reftoinspe`
    FOREIGN KEY (`inspector_id`)
    REFERENCES `mydb`.`inspectors` (`id`),
  CONSTRAINT `reftouser`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`requests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`requests` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `message` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_requests_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_requests_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
