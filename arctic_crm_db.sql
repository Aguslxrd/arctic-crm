CREATE DATABASE arctic_crm_db CHARSET utf8mb4;
use arctic_crm_db;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(50),
    secondname VARCHAR(50),
    firstlastname VARCHAR(50),
    secondlastname VARCHAR(50),
    phone VARCHAR(20),
    address VARCHAR(255),
    email VARCHAR(100),
    identifier VARCHAR(50)
);