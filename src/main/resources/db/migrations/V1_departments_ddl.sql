DROP DATABASE IF EXISTS department_service;
CREATE DATABASE department_service;
USE department_service;

CREATE TABLE department_heads (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15),
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
-- ======================================================
-- Create Departments Table
-- ======================================================
CREATE TABLE departments (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    code VARCHAR(6) NOT NULL UNIQUE,
    department_head_id BIGINT NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    PRIMARY KEY (id),

    CONSTRAINT fk_department_head 
        FOREIGN KEY (department_head_id) 
        REFERENCES department_heads(id)
);

INSERT INTO department_heads (name, email, phone, status)
VALUES 
('Rahul Sharma', 'rahul.sharma@company.com', '9876543210', 'ACTIVE'),
('Priya Verma', 'priya.verma@company.com', '9123456780', 'ACTIVE'),
('Amit Kumar', 'amit.kumar@company.com', '9988776655', 'ACTIVE');
