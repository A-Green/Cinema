-- -----------------------------------------------------
-- Schema cinema
-- -----------------------------------------------------
-- CREATE SCHEMA IF NOT EXISTS  cinema  DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
-- USE  cinema  ;

-- -----------------------------------------------------
-- Table  cinema . user 
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS user (
  id INT NOT NULL IDENTITY,
  name VARCHAR(45) NULL,
  birthday TIMESTAMP NULL,
  email VARCHAR(100) NULL);

-- -----------------------------------------------------
-- Table  cinema . auditorium 
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS auditorium  (
   id  INT NOT NULL  IDENTITY,
   name  VARCHAR(45) NULL,
   seatsAmount  INT NOT NULL);
 
-- -----------------------------------------------------
-- Table  cinema . event 
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS event (
   id  INT NOT NULL  IDENTITY,
   name  VARCHAR(150) NULL,
   rating  VARCHAR(45) DEFAULT 'MID',
   base_ticket_price  DOUBLE NULL);

-- -----------------------------------------------------
-- Table  cinema . ticket 
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ticket  (
   id  INT NOT NULL  IDENTITY,
   seat  INT NOT NULL,
   price  DOUBLE NOT NULL,
   auditorium_id  INT NOT NULL,
   event_id  INT NOT NULL,
   user_id  INT NOT NULL,
  CONSTRAINT  fk_ticket_auditorium1 
    FOREIGN KEY ( auditorium_id )
    REFERENCES  auditorium  ( id )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT  fk_ticket_event1 
    FOREIGN KEY ( event_id )
    REFERENCES  event  ( id )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT  fk_user_id1 
    FOREIGN KEY ( user_id )
    REFERENCES  user  ( id )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
 
-- -----------------------------------------------------
-- Table  cinema . vip_to_auditorium 
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS  vip_to_auditorium  (
   auditorium_id  INT NOT NULL,
   seatNumber  VARCHAR(45) NOT NULL,
  CONSTRAINT  fk_vip_to_auditorium_auditorium1 
    FOREIGN KEY ( auditorium_id )
    REFERENCES auditorium  ( id )
    ON DELETE CASCADE
    ON UPDATE CASCADE)

-- -----------------------------------------------------
-- Table  cinema . affiche 
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS  affiche  (
   id  INT NOT NULL  IDENTITY,
   event_id  INT NOT NULL,
   auditorium_id  INT NOT NULL,
   date  TIMESTAMP NOT NULL,
  CONSTRAINT  fk_affiche_event1 
    FOREIGN KEY ( event_id )
    REFERENCES event ( id )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT  fk_affiche_auditorium1 
    FOREIGN KEY ( auditorium_id )
    REFERENCES  auditorium  ( id )
    ON DELETE CASCADE
    ON UPDATE CASCADE)