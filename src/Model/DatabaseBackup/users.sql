-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2024 at 03:40 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital-manament-system`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `Name` varchar(200) DEFAULT NULL,
  `Role` varchar(30) DEFAULT NULL,
  `ImageURL` varchar(1000) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `Age` varchar(2) DEFAULT NULL,
  `Gender` varchar(6) DEFAULT NULL,
  `Specialization` varchar(50) DEFAULT NULL,
  `DoctorID` varchar(20) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `ContactNumber` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `Name`, `Role`, `ImageURL`, `Email`, `Password`, `Age`, `Gender`, `Specialization`, `DoctorID`, `Address`, `ContactNumber`) VALUES
(1, 'Md. Sohan Millat Sakib', 'admin', 'https://gitlab.com/millatsakib/funny-image/-/raw/main/sakib.png?ref_type=heads', 'millatsakib01@gmail.com', '123456', '23', 'Male', NULL, NULL, 'Rajshahi, Pabna, Bera', '121'),
(11, 'Mst. Mansina Isalm Mim', 'doctor', 'https://gitlab.com/millatsakib/funny-image/-/raw/main/mansina.png?ref_type=heads', 'mansina@gmail.com', '123456', '23', 'Female', 'Cardiologist', '222902036', 'Rajshahi, Pabna, Bera', '01125436288'),
(16, 'Md. Sabbir Hossain', 'doctor', 'https://gitlab.com/millatsakib/funny-image/-/raw/main/sabbir.png?ref_type=heads', 'sabbirhossain@gmail.com', '123456', '24', 'Male', 'Cardiologist', '221935412', NULL, '01723654188'),
(17, 'Rafy Ahmed Sishir', 'doctor', 'https://gitlab.com/millatsakib/funny-image/-/raw/main/dipro.png?ref_type=heads', 'rafyahmed@gmail.com', '123456', '25', 'Male', 'Neurologist', '642051', 'Chottogram', '01612392014'),
(30, 'Mst. Sokina Begum', 'doctor', 'https://gitlab.com/millatsakib/funny-image/-/raw/main/sokina.png?ref_type=heads', 'sokinabegum@gmail.com', '123456', '35', 'Female', 'Neurologist', '6341224', NULL, '01845263147'),
(44, 'Tamanna Islam Sithi', 'doctor', 'https://gitlab.com/millatsakib/funny-image/-/raw/main/sithi.png?ref_type=heads', 'sithitamanna@gmail.com', '123456', '22', 'Female', 'Dermatologist', '220641', 'Rajshahi, Pabna, Bera', '01741256328'),
(45, 'Mst. Mim Islam', 'doctor', 'https://gitlab.com/millatsakib/funny-image/-/raw/main/mim.png?ref_type=heads', 'mim@gmail.com', '123456', '26', 'Female', 'Dermatologist', '6241185', 'Chottogram, Cox-Bazar', '01472365214'),
(15, 'Md. Abir Hossain', 'user', 'https://gitlab.com/millatsakib/funny-image/-/raw/main/abir.png?ref_type=heads', 'abirhossain@gmail.com', '123456', '24', 'Male', '', NULL, NULL, '01541237422'),
(39, 'Md. Abir Al Anan', 'user', 'https://gitlab.com/millatsakib/funny-image/-/raw/main/anan.png?ref_type=heads', 'anan@gmail.com', '123456', '24', 'Male', NULL, NULL, NULL, '01613982475'),
(46, 'Md. Sharukh Jaman Pial', 'user', 'https://gitlab.com/millatsakib/funny-image/-/raw/main/pial.png?ref_type=heads', 'pial@gmail.com', '123456', '32', 'Male', '', '', NULL, '01617123641');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
