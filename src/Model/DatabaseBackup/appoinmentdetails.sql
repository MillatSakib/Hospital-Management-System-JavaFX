-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2024 at 03:41 AM
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
-- Table structure for table `appoinmentdetails`
--

CREATE TABLE `appoinmentdetails` (
  `PatientName` varchar(200) DEFAULT NULL,
  `DoctorName` varchar(200) DEFAULT NULL,
  `PatientID` int(11) DEFAULT NULL,
  `DoctorID` int(11) DEFAULT NULL,
  `Problem` varchar(1000) DEFAULT NULL,
  `Prescription` varchar(10000) DEFAULT NULL,
  `Visited` tinyint(1) DEFAULT NULL,
  `GenerateID` int(11) NOT NULL,
  `ContactNumber` varchar(20) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Gender` varchar(20) DEFAULT NULL,
  `Age` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appoinmentdetails`
--

INSERT INTO `appoinmentdetails` (`PatientName`, `DoctorName`, `PatientID`, `DoctorID`, `Problem`, `Prescription`, `Visited`, `GenerateID`, `ContactNumber`, `Email`, `Gender`, `Age`) VALUES
('Tamanna Islam Sithi', 'Mst. Mansina Isalm Mim', 44, 222902036, '143', NULL, 0, 32, NULL, NULL, NULL, NULL),
('Rafy Ahmed Sishir', 'Mst. Mansina Isalm Mim', 17, 222902036, 'gfdg', NULL, 0, 34, NULL, NULL, NULL, NULL),
('Rafy Ahmed Sishir', 'Mst. Mansina Isalm Mim', 17, 222902036, 'dfsa', NULL, 0, 35, '0', 'rafyahmed@gmail.com', 'Male', '25'),
('Rafy Ahmed Sishir', 'Mst. Mansina Isalm Mim', 17, 222902036, 'fasdf', NULL, 0, 36, '01612392014', 'rafyahmed@gmail.com', 'Male', '25'),
('Mst. Mim Islam', 'Mst. Mansina Isalm Mim', 45, 222902036, 'No porblem. I am also a doctor.', NULL, 0, 39, '01472365214', 'mim@gmail.com', 'Female', '26'),
('Mst. Mansina Isalm Mim', 'Tamanna Islam Sithi', 11, 220641, '4545', NULL, 0, 33, NULL, NULL, NULL, NULL),
('Md. Sharukh Jaman Pial', 'Mst. Mim Islam', 46, 6241185, 'xdz', 'This is your prescriptiol', 1, 37, '01617123641', 'pial@gmail.com', 'Male', '32'),
('Md. Sharukh Jaman Pial', 'Mst. Mansina Isalm Mim', 46, 222902036, 'No problem', 'This is your prescriptiol', 1, 38, '01617123641', 'pial@gmail.com', 'Male', '32'),
('Md. Abir Al Anan', 'Mst. Sokina Begum', 39, 6341224, 'আমার চিন্তা ভাবনায় সমস্যা। আমি অন্যের পোস্টে উল্টা পাল্টা কমেন্ট করি। ', 'আপনার যেহেতু চিন্তাভাবনায় সমস্যা\nআপনি পাবনা মেন্টাল হসপিটালে ভর্তি\nহতে পারেন। সাথে নিচের ওষুধ গুলো \nখাবেন।\nNo Anxity-50mg- তিন বেলা \nNo GF - 10mg - রাতে খাবার পর\nNo Ulta Palta - 20 mg - ৩ বেলা ', 1, 40, '01613982475', 'anan@gmail.com', 'Male', '24');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appoinmentdetails`
--
ALTER TABLE `appoinmentdetails`
  ADD PRIMARY KEY (`GenerateID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appoinmentdetails`
--
ALTER TABLE `appoinmentdetails`
  MODIFY `GenerateID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
