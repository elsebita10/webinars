/*All User's gets stored in USER table*/
create table USERS (
   id BIGINT NOT NULL AUTO_INCREMENT,
   code VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   phone VARCHAR(20),
   PRIMARY KEY (id),
   UNIQUE (code)
);
   
/* USER_PROFILE table contains all possible roles */ 
create table USERS_PROFILE(
   id BIGINT NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);
   
/* JOIN TABLE for MANY-TO-MANY relationship*/  
CREATE TABLE USERS_USERS_PROFILE (
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_USERS FOREIGN KEY (user_id) REFERENCES USERS (id),
    CONSTRAINT FK_USERS_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USERS_PROFILE (id)
);
  
/* Populate USER_PROFILE Table */
INSERT INTO USERS_PROFILE(type)
VALUES ('USER');
  
INSERT INTO USERS_PROFILE(type)
VALUES ('ADMIN');
  
  
/* Populate one Admin User which will further create other users for the application using GUI */
INSERT INTO USERS(code, password, first_name, last_name, email)
VALUES ('000001','abc123', 'Emiliano','Ayestaran','eayestaran@crayest.com.ar');
  
/* Populate one Normal User*/
INSERT INTO USERS(code, password, first_name, last_name, email)
VALUES ('000002','abc123', 'Juan','Cruce','jcruce@crayest.com.ar');
  
/* Populate JOIN Table */
INSERT INTO USERS_USERS_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM users user, users_profile profile
  where user.code='000001' and profile.type='ADMIN';
  
/* Populate JOIN Table */
INSERT INTO USERS_USERS_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM users user, users_profile profile
  where user.code='000002' and profile.type='USER';
 
/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE PERSISTENT_LOGINS (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    lastUsed TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);