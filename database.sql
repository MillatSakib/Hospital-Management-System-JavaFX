-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2024 at 03:41 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7
SET
  SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

START TRANSACTION;

SET
  time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;

/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;

/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;

/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital-manament-system`
--
-- --------------------------------------------------------
--
-- Table structure for table `Roles`
--
DROP Database `hospital-manament-system`;

CREATE Database `hospital-manament-system`;

USE `hospital-manament-system`;

CREATE TABLE
  `Roles` (
    `RoleID` int (11) NOT NULL AUTO_INCREMENT,
    `RoleName` varchar(50) NOT NULL,
    PRIMARY KEY (`RoleID`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

--
-- Dumping data for table `Roles`
--
INSERT INTO
  `Roles` (`RoleName`)
VALUES
  ('admin'),
  ('doctor'),
  ('user');

-- --------------------------------------------------------
--
-- Table structure for table `Users`
--
CREATE TABLE
  `Users` (
    `UserID` int (11) NOT NULL AUTO_INCREMENT,
    `Name` varchar(200) DEFAULT NULL,
    `RoleID` int (11) DEFAULT NULL,
    `ImageURL` varchar(1000) DEFAULT NULL,
    `Email` varchar(100) DEFAULT NULL,
    `Password` varchar(50) DEFAULT NULL,
    `Age` varchar(2) DEFAULT NULL,
    `Gender` varchar(6) DEFAULT NULL,
    `Address` varchar(200) DEFAULT NULL,
    `ContactNumber` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`UserID`),
    FOREIGN KEY (`RoleID`) REFERENCES `Roles` (`RoleID`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

--
-- Dumping data for table `Users`
--
INSERT INTO
  `Users` (
    `Name`,
    `RoleID`,
    `ImageURL`,
    `Email`,
    `Password`,
    `Age`,
    `Gender`,
    `Address`,
    `ContactNumber`
  )
VALUES
  (
    'Md. Sohan Millat Sakib',
    1,
    'https://gitlab.com/millatsakib/funny-image/-/raw/main/sakib.png?ref_type=heads',
    'millatsakib01@gmail.com',
    '123456',
    '23',
    'Male',
    'Rajshahi, Pabna, Bera',
    '121'
  ),
  (
    'Mst. Mansina Isalm Mim',
    2,
    'https://gitlab.com/millatsakib/funny-image/-/raw/main/mansina.png?ref_type=heads',
    'mansina@gmail.com',
    '123456',
    '23',
    'Female',
    'Rajshahi, Pabna, Bera',
    '01125436288'
  ),
  (
    'Md. Sabbir Hossain',
    2,
    'https://gitlab.com/millatsakib/funny-image/-/raw/main/sabbir.png?ref_type=heads',
    'sabbirhossain@gmail.com',
    '123456',
    '24',
    'Male',
    'Chottogram',
    '01723654188'
  ),
  (
    'Rafy Ahmed Sishir',
    2,
    'https://gitlab.com/millatsakib/funny-image/-/raw/main/dipro.png?ref_type=heads',
    'rafyahmed@gmail.com',
    '123456',
    '25',
    'Male',
    'Chottogram',
    '01612392014'
  ),
  (
    'Mst. Sokina Begum',
    2,
    'https://gitlab.com/millatsakib/funny-image/-/raw/main/sokina.png?ref_type=heads',
    'sokinabegum@gmail.com',
    '123456',
    '35',
    'Female',
    NULL,
    '01845263147'
  ),
  (
    'Tamanna Islam Sithi',
    2,
    'https://gitlab.com/millatsakib/funny-image/-/raw/main/sithi.png?ref_type=heads',
    'sithitamanna@gmail.com',
    '123456',
    '22',
    'Female',
    'Rajshahi, Pabna, Bera',
    '01741256328'
  ),
  (
    'Mst. Mim Islam',
    2,
    'https://gitlab.com/millatsakib/funny-image/-/raw/main/mim.png?ref_type=heads',
    'mim@gmail.com',
    '123456',
    '26',
    'Female',
    'Chottogram, Cox-Bazar',
    '01472365214'
  ),
  (
    'Md. Abir Hossain',
    3,
    'https://gitlab.com/millatsakib/funny-image/-/raw/main/abir.png?ref_type=heads',
    'abirhossain@gmail.com',
    '123456',
    '24',
    'Male',
    NULL,
    '01541237422'
  ),
  (
    'Md. Abir Al Anan',
    3,
    'https://gitlab.com/millatsakib/funny-image/-/raw/main/anan.png?ref_type=heads',
    'anan@gmail.com',
    '123456',
    '24',
    'Male',
    NULL,
    '01613982475'
  ),
  (
    'Md. Sharukh Jaman Pial',
    3,
    'https://gitlab.com/millatsakib/funny-image/-/raw/main/pial.png?ref_type=heads',
    'pial@gmail.com',
    '123456',
    '32',
    'Male',
    NULL,
    '01617123641'
  ),
  (
    'Md. Ekra Islam Ohi',
    3,
    'https://gitlab.com/millatsakib/funny-image/-/raw/main/photo_2025-07-14_16-27-55-modified.png',
    'ohi@gmail.com',
    '123456',
    '24',
    'Male',
    NULL,
    '01645616126'
  );

-- --------------------------------------------------------
--
-- Table structure for table `Specializations`
--
CREATE TABLE
  `Specializations` (
    `SpecializationID` int (11) NOT NULL AUTO_INCREMENT,
    `SpecializationName` varchar(50) NOT NULL,
    PRIMARY KEY (`SpecializationID`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

--
-- Dumping data for table `Specializations`
--
INSERT INTO
  `Specializations` (`SpecializationName`)
VALUES
  ('Cardiologist'),
  ('Neurologist'),
  ('Dermatologist');

-- --------------------------------------------------------
--
-- Table structure for table `Doctors`
--
CREATE TABLE
  `Doctors` (
    `DoctorID` int (11) NOT NULL AUTO_INCREMENT,
    `UserID` int (11) NOT NULL,
    `DoctorCode` varchar(20) DEFAULT NULL,
    `SpecializationID` int (11) DEFAULT NULL,
    PRIMARY KEY (`DoctorID`),
    FOREIGN KEY (`UserID`) REFERENCES `Users` (`UserID`),
    FOREIGN KEY (`SpecializationID`) REFERENCES `Specializations` (`SpecializationID`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

--
-- Dumping data for table `Doctors`
--
INSERT INTO
  `Doctors` (`UserID`, `DoctorCode`, `SpecializationID`)
VALUES
  (2, '222902036', 1),
  (3, '221935412', 1),
  (4, '642051', 2),
  (5, '6341224', 2),
  (6, '220641', 3),
  (7, '6241185', 3);

-- --------------------------------------------------------
--
-- Table structure for table `Patients`
--
CREATE TABLE
  `Patients` (
    `PatientID` int (11) NOT NULL AUTO_INCREMENT,
    `UserID` int (11) NOT NULL,
    PRIMARY KEY (`PatientID`),
    FOREIGN KEY (`UserID`) REFERENCES `Users` (`UserID`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

--
-- Dumping data for table `Patients`
--
INSERT INTO
  `Patients` (`UserID`)
VALUES
  (8),
  (9),
  (10);

-- --------------------------------------------------------
--
-- Table structure for table `Appointments`
--
CREATE TABLE
  `Appointments` (
    `AppointmentID` int (11) NOT NULL AUTO_INCREMENT,
    `PatientID` int (11) NOT NULL,
    `DoctorID` int (11) NOT NULL,
    `Problem` varchar(1000) DEFAULT NULL,
    `Visited` tinyint (1) DEFAULT 0,
    `AppointmentDate` timestamp NOT NULL DEFAULT current_timestamp(),
    PRIMARY KEY (`AppointmentID`),
    FOREIGN KEY (`PatientID`) REFERENCES `Patients` (`PatientID`),
    FOREIGN KEY (`DoctorID`) REFERENCES `Doctors` (`DoctorID`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- --------------------------------------------------------
--
-- Table structure for table `Prescriptions`
--
CREATE TABLE
  `Prescriptions` (
    `PrescriptionID` int (11) NOT NULL AUTO_INCREMENT,
    `AppointmentID` int (11) NOT NULL,
    `PrescriptionText` varchar(10000) DEFAULT NULL,
    PRIMARY KEY (`PrescriptionID`),
    FOREIGN KEY (`AppointmentID`) REFERENCES `Appointments` (`AppointmentID`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

--
-- Table structure for table `NewDoctor`
--
CREATE TABLE
  `NewDoctor` (
    `NewDoctorID` INT (11) NOT NULL AUTO_INCREMENT,
    `UserID` INT (11) NOT NULL,
    `SpecializationID` INT (11) DEFAULT NULL,
    `DoctorCode` VARCHAR(20) DEFAULT NULL,
    PRIMARY KEY (`NewDoctorID`),
    FOREIGN KEY (`UserID`) REFERENCES `Users` (`UserID`),
    FOREIGN KEY (`SpecializationID`) REFERENCES `Specializations` (`SpecializationID`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;

/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
    

    
    CREATE TABLE
    `Departments` (
        `DepartmentID` INT (11) NOT NULL AUTO_INCREMENT,
        `DepartmentName` VARCHAR(50) NOT NULL,
        PRIMARY KEY (`DepartmentID`)
    );
    
CREATE TABLE
    `Rooms` (
        `RoomID` INT (11) NOT NULL AUTO_INCREMENT,
        `RoomNumber` VARCHAR(10) NOT NULL,
        `RoomType` VARCHAR(20) DEFAULT NULL, -- e.g. "ICU", "General", "Private"
        `IsAvailable` TINYINT (1) DEFAULT 1,
        PRIMARY KEY (`RoomID`)
    );
    
CREATE TABLE
    `LabTests` (
        `LabTestID` INT (11) NOT NULL AUTO_INCREMENT,
        `TestName` VARCHAR(100) NOT NULL,
        `TestDescription` VARCHAR(500) DEFAULT NULL,
        PRIMARY KEY (`LabTestID`)
    );

CREATE TABLE
    `LabTestResults` (
        `ResultID` INT (11) NOT NULL AUTO_INCREMENT,
        `PatientID` INT (11) NOT NULL,
        `LabTestID` INT (11) NOT NULL,
        `DoctorID` INT (11) DEFAULT NULL,
        `ResultText` VARCHAR(5000) DEFAULT NULL,
        `TestDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
        PRIMARY KEY (`ResultID`),
        FOREIGN KEY (`PatientID`) REFERENCES `Patients` (`PatientID`),
        FOREIGN KEY (`LabTestID`) REFERENCES `LabTests` (`LabTestID`),
        FOREIGN KEY (`DoctorID`) REFERENCES `Doctors` (`DoctorID`)
    );
    
    CREATE TABLE
    `Billing` (
        `BillID` INT (11) NOT NULL AUTO_INCREMENT,
        `PatientID` INT (11) NOT NULL,
        `AppointmentID` INT (11) DEFAULT NULL,
        `Amount` DECIMAL(10, 2) NOT NULL,
        `Paid` TINYINT (1) DEFAULT 0,
        `BillDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
        PRIMARY KEY (`BillID`),
        FOREIGN KEY (`PatientID`) REFERENCES `Patients` (`PatientID`),
        FOREIGN KEY (`AppointmentID`) REFERENCES `Appointments` (`AppointmentID`)
    );