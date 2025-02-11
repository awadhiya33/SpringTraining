DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
username VARCHAR(50)  NOT NULL,
password VARCHAR(100) NOT NULL,
enabled  Boolean      NOT NULL DEFAULT TRUE,
PRIMARY KEY (username)
);

CREATE TABLE authorities
(
username  VARCHAR(50) NOT NULL,
authority VARCHAR(50) NOT NULL,
FOREIGN KEY (username) REFERENCES users (username)
);
INSERT INTO users 
VALUES 
('abhi','{noop}test123',TRUE),
('amit','{noop}test123',TRUE),
('mishra','{noop}test123',TRUE);
-- Insert data into 'authorities'
INSERT INTO authorities (username, authority) VALUES
('abhi', 'ROLE_Employee'),
('amit', 'ROLE_Employee'),
('amit', 'ROLE_Manager'),
('mishra', 'ROLE_Employee'),
('mishra', 'ROLE_Manager'),
('mishra', 'ROLE_Admin');

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);
