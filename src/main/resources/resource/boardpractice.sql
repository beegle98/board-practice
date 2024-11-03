-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema boardpractice
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema boardpractice
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `boardpractice` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `boardpractice` ;

-- -----------------------------------------------------
-- Table `boardpractice`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boardpractice`.`User` (
                                                      `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `oauth_id` CHAR(255) NULL DEFAULT NULL,
    `oauth_type` CHAR(255) NULL DEFAULT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `profile_image` VARCHAR(255) NULL DEFAULT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `deleted` TINYINT(1) NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    UNIQUE INDEX `UKob8kqyqqgmefl0aco34akdtpe` (`email` ASC) VISIBLE,
    UNIQUE INDEX `oauthId_UNIQUE` (`oauth_id` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 5
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boardpractice`.`Todo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boardpractice`.`Todo` (
                                                      `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `user_id` BIGINT NOT NULL,
                                                      `content` VARCHAR(50) NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `completed` TINYINT(1) NOT NULL DEFAULT '0',
    `deleted` TINYINT(1) NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `boardpractice`.`User` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 9
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;