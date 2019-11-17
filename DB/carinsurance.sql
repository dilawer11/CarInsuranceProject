-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 17, 2019 at 11:20 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carinsurance`
--

-- --------------------------------------------------------

--
-- Table structure for table `Accident`
--

CREATE TABLE `Accident` (
  `accidentID` int(11) NOT NULL,
  `carID` int(11) NOT NULL,
  `type` varchar(10) NOT NULL,
  `city` varchar(15) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Accident`
--

INSERT INTO `Accident` (`accidentID`, `carID`, `type`, `city`, `date`) VALUES
(1, 1, 'Theft', 'Lahore', '2019-11-17');

-- --------------------------------------------------------

--
-- Table structure for table `AccidentReport`
--

CREATE TABLE `AccidentReport` (
  `claimID` int(11) NOT NULL,
  `inspectorID` int(11) DEFAULT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'submitted',
  `damageDetails` varchar(50) DEFAULT NULL,
  `comments` varchar(150) DEFAULT NULL,
  `decision` varchar(10) DEFAULT NULL,
  `clientPayment` int(32) DEFAULT NULL,
  `companyPayment` int(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `AccidentReport`
--

INSERT INTO `AccidentReport` (`claimID`, `inspectorID`, `status`, `damageDetails`, `comments`, `decision`, `clientPayment`, `companyPayment`) VALUES
(1, 1, 'wait_dec', 'Head light has been damaged', 'no comments', NULL, 12, 123);

-- --------------------------------------------------------

--
-- Table structure for table `Car`
--

CREATE TABLE `Car` (
  `carID` int(11) NOT NULL,
  `registrationNumber` varchar(15) NOT NULL,
  `makeCompany` varchar(25) NOT NULL,
  `registrationYear` varchar(4) NOT NULL,
  `clientID` int(11) NOT NULL,
  `makeYear` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Car`
--

INSERT INTO `Car` (`carID`, `registrationNumber`, `makeCompany`, `registrationYear`, `clientID`, `makeYear`) VALUES
(1, 'LXX-09-123', 'Honda', '2009', 1, '2009'),
(2, 'LVC-10-4560', 'Toyota', '2013', 2, '2012');

-- --------------------------------------------------------

--
-- Table structure for table `Claim`
--

CREATE TABLE `Claim` (
  `claimID` int(11) NOT NULL,
  `policyID` int(11) NOT NULL,
  `accidentID` int(11) NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'submitted'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Client`
--

CREATE TABLE `Client` (
  `clientID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Client`
--

INSERT INTO `Client` (`clientID`, `name`, `address`, `phone`, `email`, `dateOfBirth`) VALUES
(1, 'Muhammad Asad', 'LUMS, Lahore', '0923127897890', 'asad@gcrow.pk', '2017-01-04'),
(2, 'Dilawer Ahmed', 'House No. 123 ABC Town, Lahore', '0921231231234', 'dilawer11@gmail.com', '2019-11-18');

-- --------------------------------------------------------

--
-- Table structure for table `InitialReport`
--

CREATE TABLE `InitialReport` (
  `policyID` int(11) NOT NULL,
  `inspectorID` int(11) DEFAULT NULL,
  `carCondition` tinyint(4) DEFAULT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'submitted',
  `comments` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `InitialReport`
--

INSERT INTO `InitialReport` (`policyID`, `inspectorID`, `carCondition`, `status`, `comments`) VALUES
(1, 1, 6, 'WAIT_DEC', 'Car is in not so bad condition');

-- --------------------------------------------------------

--
-- Table structure for table `Inspector`
--

CREATE TABLE `Inspector` (
  `inspectorID` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Inspector`
--

INSERT INTO `Inspector` (`inspectorID`, `name`, `phone`, `email`) VALUES
(1, 'Hameed', '0927896542', 'hameed@carinsurance.com.pk'),
(2, 'Paracha ', '090078601', 'paracha@carinsurance.com.pk'),
(3, 'Romoss Arbunt', '09231231231', 'romoss@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `Policy`
--

CREATE TABLE `Policy` (
  `policyID` int(11) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `coverage` int(32) DEFAULT NULL,
  `carID` int(11) NOT NULL,
  `charge` int(32) DEFAULT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'initial'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Policy`
--

INSERT INTO `Policy` (`policyID`, `title`, `description`, `coverage`, `carID`, `charge`, `status`) VALUES
(1, 'Silver Policy', 'This is the silver policy', 10000, 1, 100, 'active'),
(2, 'Golden Policy', 'This is the Golden Policy', 1000000, 1, 1000, 'inactive');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Accident`
--
ALTER TABLE `Accident`
  ADD PRIMARY KEY (`accidentID`);

--
-- Indexes for table `AccidentReport`
--
ALTER TABLE `AccidentReport`
  ADD PRIMARY KEY (`claimID`);

--
-- Indexes for table `Car`
--
ALTER TABLE `Car`
  ADD PRIMARY KEY (`carID`);

--
-- Indexes for table `Claim`
--
ALTER TABLE `Claim`
  ADD PRIMARY KEY (`claimID`);

--
-- Indexes for table `Client`
--
ALTER TABLE `Client`
  ADD PRIMARY KEY (`clientID`);

--
-- Indexes for table `InitialReport`
--
ALTER TABLE `InitialReport`
  ADD PRIMARY KEY (`policyID`);

--
-- Indexes for table `Inspector`
--
ALTER TABLE `Inspector`
  ADD PRIMARY KEY (`inspectorID`);

--
-- Indexes for table `Policy`
--
ALTER TABLE `Policy`
  ADD PRIMARY KEY (`policyID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Accident`
--
ALTER TABLE `Accident`
  MODIFY `accidentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Car`
--
ALTER TABLE `Car`
  MODIFY `carID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Claim`
--
ALTER TABLE `Claim`
  MODIFY `claimID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Client`
--
ALTER TABLE `Client`
  MODIFY `clientID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Inspector`
--
ALTER TABLE `Inspector`
  MODIFY `inspectorID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `Policy`
--
ALTER TABLE `Policy`
  MODIFY `policyID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
