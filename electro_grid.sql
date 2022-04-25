-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2022 at 11:26 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electro_grid`
--

-- --------------------------------------------------------

--
-- Table structure for table `branches`
--

CREATE TABLE `branches` (
  `branchID` varchar(10) NOT NULL,
  `branchName` varchar(30) NOT NULL,
  `branchAddress` varchar(50) NOT NULL,
  `branchContact` int(11) NOT NULL,
  `branchEmail` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `branches`
--

INSERT INTO `branches` (`branchID`, `branchName`, `branchAddress`, `branchContact`, `branchEmail`) VALUES
('C100', 'Homagama', 'No 30/1, Perera Mawatha, Homagama', 112756732, 'homagamaceb@gmail.com'),
('C105', 'Colombo', 'No 3, Tea avenue, Colombo 3', 112756789, 'colomceb3@gmail.com'),
('C108', 'Colombo', 'No 3, Tea avenue, Colombo 3', 112756789, 'colomceb3@gmail.com'),
('G170', 'Gampaha', 'No 9, Veyangoda road, Gampaha', 336765974, 'gampahaceb@gmail.com'),
('K160', 'Bentara', 'No 5, Galle road, Bentara', 112756744, 'bentaraceb@gmail.com'),
('T110', 'Trincomalee', 'No 111, Trincomalee', 356775674, 'trinco5ceb@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `cusID` int(11) NOT NULL,
  `cusAccount` int(11) NOT NULL,
  `cusName` varchar(25) NOT NULL,
  `cusEmail` varchar(25) NOT NULL,
  `cusAddress` varchar(25) NOT NULL,
  `cusCity` varchar(25) NOT NULL,
  `cusMobile` int(11) NOT NULL,
  `regDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`cusID`, `cusAccount`, `cusName`, `cusEmail`, `cusAddress`, `cusCity`, `cusMobile`, `regDate`) VALUES
(4, 6778, 'Mashi Fernando', 'mash@yahoo.com', 'Baddegama,Galle', 'Galle', 771234567, '2012-05-05'),
(5, 1234, 'Kamal Silva', 'kamal@gmail.com', 'Borella,Kollupitiya', 'Colombo', 719383457, '2018-09-27'),
(6, 4918, 'Athri Perera', 'athri@yahoo.com', 'Ragama,Negombo', 'Colombo', 918783784, '2021-10-03'),
(7, 6778, 'Anu Silva', 'anusi@gmail.com', 'Weligama,Matara', 'Matara', 769909865, '2020-07-05'),
(8, 6099, 'Sithum Liyanage', 'liyan99@gmail.com', 'Ratmalana,Galkissa', 'Colombo', 719898956, '2022-04-08');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `messageID` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `contactNo` int(11) NOT NULL,
  `email` varchar(25) NOT NULL,
  `message` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`messageID`, `name`, `contactNo`, `email`, `message`) VALUES
(1, 'Sahas Athukorala', 772344367, 'sahash@gmail.com', 'I need to get the updated powercut Schedule '),
(2, 'Udani Gunarathne', 774532889, 'udani@gmail.com', 'I need to get the power unit prices chart'),
(3, 'Tharusha Alwis', 772346789, 'tharu12@gmail.com', 'I need to get the new telephone no of the gampaha branch');

-- --------------------------------------------------------

--
-- Table structure for table `newconnection`
--

CREATE TABLE `newconnection` (
  `ncID` int(11) NOT NULL,
  `ncName` varchar(20) NOT NULL,
  `ncEmail` varchar(20) NOT NULL,
  `ncAddress` varchar(20) NOT NULL,
  `ncCity` varchar(20) NOT NULL,
  `ncMobile` int(10) NOT NULL,
  `ncType` varchar(10) NOT NULL,
  `ncRegDate` date NOT NULL,
  `ncStatus` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `newconnection`
--

INSERT INTO `newconnection` (`ncID`, `ncName`, `ncEmail`, `ncAddress`, `ncCity`, `ncMobile`, `ncType`, `ncRegDate`, `ncStatus`) VALUES
(4, 'Shanila', 'shanila@gmail.com', 'Piliyandala', 'Colombo', 112519800, 'Domestic', '2022-04-12', 'Pending'),
(8, 'Nalaka', 'Nalaka@gmail.com.lk', 'Dehiwala', 'Colombo', 112519344, 'Industrial', '2022-04-13', 'Pending'),
(9, 'Udaraka', 'Udaraka@gmail.com.lk', 'Kottawa', 'Colombo', 112519000, 'Industrial', '2022-01-13', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `powergenerators`
--

CREATE TABLE `powergenerators` (
  `gID` int(11) NOT NULL,
  `gCode` varchar(25) NOT NULL,
  `gName` varchar(25) NOT NULL,
  `gType` varchar(25) NOT NULL,
  `gLocation` varchar(25) NOT NULL,
  `gUnitPrice` double(5,2) NOT NULL,
  `gRegDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `powergenerators`
--

INSERT INTO `powergenerators` (`gID`, `gCode`, `gName`, `gType`, `gLocation`, `gUnitPrice`, `gRegDate`) VALUES
(1, 'g1225', 'Kelanitissa', 'Oil-fired', 'Colombo', 24.75, '2012-12-12'),
(4, 'g572', 'Lakvijaya', 'Coal-fired', 'Puttalam', 27.50, '2007-07-25'),
(5, 'g212', 'Sampur', 'Coal-fired', 'Trincomalee', 26.00, '2015-09-22'),
(6, 'g2393', 'Kotmale', 'Hydroelectric', 'Kotmale', 26.00, '2009-03-02'),
(7, 'g792', 'Victoria', 'Hydroelectric', 'Adhikarigama', 24.75, '2017-02-21'),
(9, 'g475', 'Hambantota', 'Solar-powered', 'Hambantota', 27.90, '2008-02-07'),
(11, 'g1234', 'test_generator', 'test_type', 'test_location', 123.45, '2022-04-23');

-- --------------------------------------------------------

--
-- Table structure for table `reg_cus_billing`
--

CREATE TABLE `reg_cus_billing` (
  `invoiceNo` int(5) NOT NULL,
  `date` date NOT NULL,
  `cusName` varchar(25) NOT NULL,
  `accNo` int(8) NOT NULL,
  `noOfUnits` int(11) NOT NULL,
  `unitPrice` double NOT NULL,
  `tax` double NOT NULL,
  `totalAmount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reg_cus_billing`
--

INSERT INTO `reg_cus_billing` (`invoiceNo`, `date`, `cusName`, `accNo`, `noOfUnits`, `unitPrice`, `tax`, `totalAmount`) VALUES
(1, '2022-04-01', 'H.L.Fernando', 236589123, 152, 32, 480, 5344),
(4, '2022-02-01', 'P.K.Oman', 985463255, 32, 5, 60, 220),
(5, '2022-02-01', 'Jhon', 978954628, 100, 28, 480, 3280),
(6, '2022-03-05', 'N Somapala', 125478412, 100, 28, 480, 3280),
(7, '2022-02-01', 'Nimali Kumara', 974812628, 60, 50, 60, 90),
(9, '2022-04-24', 'Kumara Y.K.', 745814782, 134, 32, 480, 4768);

-- --------------------------------------------------------

--
-- Table structure for table `shedules`
--

CREATE TABLE `shedules` (
  `sID` int(11) NOT NULL,
  `sPeriod` varchar(20) NOT NULL,
  `sTotHrs` varchar(20) NOT NULL,
  `sFromTime` varchar(20) NOT NULL,
  `sToTime` varchar(20) NOT NULL,
  `sArea` varchar(20) NOT NULL,
  `sSub` varchar(20) NOT NULL,
  `sProvince` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shedules`
--

INSERT INTO `shedules` (`sID`, `sPeriod`, `sTotHrs`, `sFromTime`, `sToTime`, `sArea`, `sSub`, `sProvince`) VALUES
(8, '12toMidnight', '3', '10pm', '12pm', 'Kottawa', 'Colombo Zone 3ii', 'Western'),
(10, '12toMidnight', '3', '5pm', '8pm', 'Maharagama', 'Colombo Zone 3', 'Western'),
(11, '12toMidnight', '3', '5pm', '8pm', 'Demodara', 'Uwa zone A', 'Uwa');

-- --------------------------------------------------------

--
-- Table structure for table `unitprice_tax`
--

CREATE TABLE `unitprice_tax` (
  `ID` varchar(1) NOT NULL,
  `startingUnitRange` int(11) NOT NULL,
  `endingUnitRange` int(11) NOT NULL,
  `unitPrice` int(11) NOT NULL,
  `tax` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `unitprice_tax`
--

INSERT INTO `unitprice_tax` (`ID`, `startingUnitRange`, `endingUnitRange`, `unitPrice`, `tax`) VALUES
('A', 0, 30, 3, 30),
('B', 31, 60, 5, 60),
('C', 61, 90, 10, 90),
('D', 91, 120, 28, 480),
('E', 121, 180, 32, 480),
('F', 180, 10000, 32, 480);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `branches`
--
ALTER TABLE `branches`
  ADD PRIMARY KEY (`branchID`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`cusID`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`messageID`);

--
-- Indexes for table `newconnection`
--
ALTER TABLE `newconnection`
  ADD PRIMARY KEY (`ncID`);

--
-- Indexes for table `powergenerators`
--
ALTER TABLE `powergenerators`
  ADD PRIMARY KEY (`gID`);

--
-- Indexes for table `reg_cus_billing`
--
ALTER TABLE `reg_cus_billing`
  ADD PRIMARY KEY (`invoiceNo`);

--
-- Indexes for table `shedules`
--
ALTER TABLE `shedules`
  ADD PRIMARY KEY (`sID`);

--
-- Indexes for table `unitprice_tax`
--
ALTER TABLE `unitprice_tax`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `cusID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `messageID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `newconnection`
--
ALTER TABLE `newconnection`
  MODIFY `ncID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `powergenerators`
--
ALTER TABLE `powergenerators`
  MODIFY `gID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `reg_cus_billing`
--
ALTER TABLE `reg_cus_billing`
  MODIFY `invoiceNo` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `shedules`
--
ALTER TABLE `shedules`
  MODIFY `sID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
