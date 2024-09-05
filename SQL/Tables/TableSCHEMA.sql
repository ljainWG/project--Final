-- current working one--

show databases;
create database project;
show databases;
use project;
show tables;

CREATE TABLE User (
    uuid VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    gender ENUM ('MALE', 'FEMALE', 'OTHER') DEFAULT 'OTHER',
    role ENUM('DOCTOR', 'RECEPTIONIST', 'ADMIN', 'PATIENT') DEFAULT 'PATIENT',
    department ENUM('CARDIOLOGY', 'NEUROLOGY', 'ORTHOPEDICS', 'PEDIATRICS', 'GENERAL_MEDICINE', 'DERMATOLOGY', 'GYNECOLOGY', 'ADMINISTRATION', 'HOSPITALITY', 'NONE') DEFAULT 'NONE',
    phone_no VARCHAR(15),
    address TEXT,
    avg_rating DECIMAL(3, 2),
    no_of_review INT, -- Assuming review count is an integer
    experience DECIMAL(10, 2),
    dob DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE Appointment (
    uuid VARCHAR(255) PRIMARY KEY,
    doctor_id VARCHAR(255) NOT NULL,
    patient_id VARCHAR(255) NOT NULL,
    status ENUM('SCHEDULED', 'COMPLETED', 'CANCELED', 'NO_SHOW_PATIENT', 'NO_SHOW_DOCTOR') DEFAULT 'SCHEDULED',
    scheduled_date DATE NOT NULL,
    slot_no ENUM('SLOT_8AM_830AM', 'SLOT_830AM_9AM', 'SLOT_9AM_930AM', 'SLOT_930AM_10AM', 'SLOT_10AM_1030AM',
    'SLOT_1030AM_11AM', 'SLOT_11AM_1130AM', 'SLOT_1130AM_12PM', 'SLOT_12PM_1230PM', 'SLOT_1230PM_1PM',
    'SLOT_2PM_230PM', 'SLOT_230PM_3PM', 'SLOT_3PM_330PM', 'SLOT_330PM_4PM', 'SLOT_4PM_430PM',
    'SLOT_430PM_5PM', 'SLOT_5PM_530PM', 'SLOT_530PM_6PM', 'SLOT_6PM_630PM', 'SLOT_630PM_7PM') NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    status_updation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES User(uuid),
    FOREIGN KEY (patient_id) REFERENCES User(uuid)    
);

CREATE TABLE Prescription (
    uuid VARCHAR(255) PRIMARY KEY,
    appointment_id VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (appointment_id) REFERENCES Appointment(uuid)
);

CREATE TABLE Review (
    uuid VARCHAR(255) PRIMARY KEY,
    reviewee_id VARCHAR(255) NOT NULL,
    reviewer_id VARCHAR(255) NOT NULL,
    description TEXT,
    rating DECIMAL(3, 2) NOT NULL, -- Assuming rating is between 0 and 5
    is_reported BOOLEAN DEFAULT false NOT NULL,
    is_hidden BOOLEAN DEFAULT false NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (reviewee_id) REFERENCES User(uuid),
    FOREIGN KEY (reviewer_id) REFERENCES User(uuid)
);

CREATE TABLE Notification (
    uuid VARCHAR(255) PRIMARY KEY,
    generator_id VARCHAR(255) NOT NULL,
    receiver_id VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (generator_id) REFERENCES User(uuid),
    FOREIGN KEY (receiver_id) REFERENCES User(uuid)
);

select * from user;
select * from appointment;
select * from prescription;
select * from review;
select * from notification;