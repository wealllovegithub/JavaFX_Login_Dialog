CREATE TABLE user_data
(
  id              INT PRIMARY KEY AUTO_INCREMENT,
  username        TEXT,
  hashed_password BINARY,
  salt            BINARY
)
  COMMENT 'A sample table setup to be used with the login dialog.'