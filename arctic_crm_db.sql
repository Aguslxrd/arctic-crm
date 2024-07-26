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
    email VARCHAR(100) UNIQUE,
    identifier VARCHAR(50) NOT NULL,
    softDelete tinyint(1) DEFAULT(0)
);

CREATE TABLE auth (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(25) NOT NULL,
    email VARCHAR(100) UNIQUE,
    passwd VARCHAR(255),
    userrole enum('ADMIN', 'SUPPORT', 'USER') DEFAULT 'USER'
);

INSERT INTO users (firstname, secondname, firstlastname, secondlastname, phone, address, email, identifier)
VALUES 
('Juan', 'Carlos', 'García', 'López', '555-1234', 'Calle Principal 123, Ciudad', 'juan.garcia@email.com', 'JG12345'),
('María', NULL, 'Rodríguez', 'Martínez', '555-5678', 'Avenida Central 456, Pueblo', 'maria.rodriguez@email.com', 'MR67890'),
('Pedro', 'Antonio', 'Sánchez', NULL, '555-9876', 'Plaza Mayor 789, Villa', 'pedro.sanchez@email.com', 'PS13579');


CREATE TABLE enterprise (
    enterpriseId INT PRIMARY KEY AUTO_INCREMENT,
    name_enterprise VARCHAR(100) NOT NULL UNIQUE,
    rut VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    web_site VARCHAR(100),
    softDelete tinyint(1) DEFAULT(0)
);

CREATE TABLE user_enterprise (
    userId INT,
    enterpriseId INT,
    PRIMARY KEY (userId, enterpriseId),
    FOREIGN KEY (userId) REFERENCES users(userId),
    FOREIGN KEY (enterpriseId) REFERENCES enterprise(enterpriseId)
);

CREATE TABLE cases (
    caseId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description_case TEXT,
    date_created DATETIME DEFAULT CURRENT_TIMESTAMP,
    case_status ENUM('ABIERTO', 'EN_PROGRESO', 'CERRADO') DEFAULT 'ABIERTO',
    FOREIGN KEY (userId) REFERENCES users(userId)
);

CREATE TABLE interactions (
    interactionId INT PRIMARY KEY AUTO_INCREMENT,
    caseId INT NOT NULL,
    authId INT NOT NULL,
    interaction_text TEXT NOT NULL,
    interaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (caseId) REFERENCES cases(caseId),
    FOREIGN KEY (authId) REFERENCES auth(id)
);

INSERT INTO interactions (caseId, authId, interaction_text)
VALUES 
(6, 1, 'Se ha revisado la factura y se ha encontrado un error en el cálculo del IVA. Se procederá a emitir una factura corregida.'),
(7, 1, 'Se ha emitido una nueva factura corregida y se ha enviado al cliente por correo electrónico.'),
(8, 1, 'Se ha actualizado la dirección del cliente en el sistema. Se enviará una confirmación por correo.'),
(8, 1, 'Se han proporcionado las especificaciones detalladas del producto XYZ al cliente vía correo electrónico.'),
(9, 1, 'El reembolso ha sido procesado y debería reflejarse en la cuenta del cliente en 3-5 días hábiles.');

INSERT INTO cases (userId, title, description_case, case_status)
VALUES 
(1, 'Problema con facturación', 'El cliente reporta un error en su última factura.', 'ABIERTO'),
(1, 'Solicitud de cambio de dirección', 'El cliente desea actualizar su dirección de envío.', 'EN_PROGRESO'),
(2, 'Consulta sobre producto', 'El cliente tiene preguntas sobre las especificaciones del producto XYZ.', 'ABIERTO'),
(3, 'Reembolso pendiente', 'El cliente solicita información sobre el estado de su reembolso.', 'CERRADO'),
(2, 'Problema técnico con la aplicación', 'La aplicación móvil se cierra inesperadamente al intentar realizar un pago.', 'EN_PROGRESO');


INSERT INTO enterprise (name_enterprise, rut, address, phone, email, web_site)
VALUES 
('Empresa A', 'A12345678', 'Calle Comercial 1, Ciudad', '555-1111', 'contacto@empresaa.com', 'www.empresaa.com'),
('Empresa B', 'B87654321', 'Avenida Industrial 2, Pueblo', '555-2222', 'info@empresab.com', 'www.empresab.com');


INSERT INTO user_enterprise (userId, enterpriseId)
VALUES 
(1, 1),  
(1, 2),  
(2, 2);