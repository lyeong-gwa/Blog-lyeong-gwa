create database if not exists BlogDB;

use BlogDB;

CREATE TABLE IF NOT EXISTS `imageuploadtry` (
  `id_date` DATE NOT NULL,
  `id_hour` VARCHAR(3) NOT NULL,
  `cnt` INT NULL DEFAULT 0,
  PRIMARY KEY (`id_date`, `id_hour`));

CREATE TABLE IF NOT EXISTS `corecalctry` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `job` VARCHAR(10) NULL,
  `core_num` INT NULL,
  `select_skill` VARCHAR(50) NULL,
  `combi_list_len` INT NULL,
  PRIMARY KEY (`id`));
