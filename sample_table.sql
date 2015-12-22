CREATE TABLE user_data
(
  id       INT PRIMARY KEY AUTO_INCREMENT,
  username TEXT,
  password BLOB,
  salt     BLOB
)
  COMMENT 'A sample table setup to be used with the login dialog.'