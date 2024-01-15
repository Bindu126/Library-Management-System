-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 03, 2021 at 11:50 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `book list database`
--

-- --------------------------------------------------------

--
-- Table structure for table `book information`
--

CREATE TABLE `book information` (
  `Book No.` int(11) NOT NULL,
  `BookName` varchar(100) NOT NULL,
  `AuthorName` varchar(100) NOT NULL,
  `Category` varchar(100) NOT NULL,
  `FilePath` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book information`
--

INSERT INTO `book information` (`Book No.`, `BookName`, `AuthorName`, `Category`, `FilePath`) VALUES
(12, 'Half Girlfriend.pdf', 'test1', 'asdf', 'C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\project1\\src\\Book list\\Half Girlfriend.pdf'),
(16, 'NOSTO MEYE.pdf', 'bindu', 'adult', 'C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\project1\\src\\Book list\\NOSTO MEYE.pdf');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book information`
--
ALTER TABLE `book information`
  ADD PRIMARY KEY (`Book No.`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book information`
--
ALTER TABLE `book information`
  MODIFY `Book No.` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
