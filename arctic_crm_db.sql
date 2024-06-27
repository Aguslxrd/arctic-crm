DROP DATABASE IF EXISTS arctic_crm_db;
CREATE DATABASE arctic_crm_db CHARSET utf8mb4;
use arctic_crm_db;


CREATE TABLE users (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(50) NOT NULL,
    secondname VARCHAR(50),
    firstlastname VARCHAR(50) NOT NULL,
    secondlastname VARCHAR(50),
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(255),
    email VARCHAR(100),
    identifier VARCHAR(50) NOT NULL
);

CREATE TABLE auth (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100),
    passwd VARCHAR(255),
    userrole enum('ADMIN', 'SUPPORT', 'USER') DEFAULT 'USER'
);
