-- init.sql
-- Create database tables for Society Security Solution
-- Run this in phpMyAdmin after creating the database `sss_db`

-- USE sss_db; -- ensure you selected the database in phpMyAdmin

CREATE TABLE IF NOT EXISTS buildings (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  address TEXT,
  num_floors INT DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS flats (
  id INT AUTO_INCREMENT PRIMARY KEY,
  building_id INT NOT NULL,
  flat_number VARCHAR(20) NOT NULL,
  floor INT DEFAULT 0,
  maintenance_amount DECIMAL(10,2) DEFAULT 0,
  maintenance_due BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (building_id) REFERENCES buildings(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role ENUM('admin','supervisor','resident') NOT NULL,
  name VARCHAR(100),
  flat_id INT DEFAULT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (flat_id) REFERENCES flats(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS visitors (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  phone VARCHAR(20),
  type ENUM('normal','regular') DEFAULT 'normal',
  flat_id INT, -- visitor intended flat (resident)
  status ENUM('pending','approved','rejected') DEFAULT 'pending',
  security_code VARCHAR(50), -- for regular visitors/vendors
  checkin_time DATETIME DEFAULT NULL,
  checkout_time DATETIME DEFAULT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (flat_id) REFERENCES flats(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS staff (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  role VARCHAR(50),
  phone VARCHAR(20),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS maintenance (
  id INT AUTO_INCREMENT PRIMARY KEY,
  flat_id INT,
  amount DECIMAL(10,2) NOT NULL,
  due_date DATE,
  paid BOOLEAN DEFAULT FALSE,
  paid_date DATE DEFAULT NULL,
  mode VARCHAR(50) DEFAULT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (flat_id) REFERENCES flats(id) ON DELETE CASCADE
);

-- sample building & flat (seed)
INSERT INTO buildings (name, address, num_floors) VALUES
('Block A', 'Near Main Gate', 5),
('Block B', 'Near Garden', 4);

INSERT INTO flats (building_id, flat_number, floor, maintenance_amount, maintenance_due) VALUES
(1, 'A-101', 1, 1200.00, TRUE),
(1, 'A-102', 1, 1200.00, FALSE),
(2, 'B-201', 2, 1500.00, TRUE);

-- basic staff
INSERT INTO staff (name, role, phone) VALUES
('Ramesh', 'Security', '9000000001'),
('Sita', 'Cleaner', '9000000002');

-- end of init.sql