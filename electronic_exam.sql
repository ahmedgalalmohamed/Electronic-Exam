-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 01, 2022 at 01:19 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electronic_exam`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `Email` varchar(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Email`, `Name`, `Password`, `Address`) VALUES
('Ahmed.Saker123@yahoo.com', 'Ahmed Saker', 'admin1', 'go to scholl'),
('MohamedMalhat123admin@yahoo.com', 'Mohamed Malhat', 'admin', 's');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `ID` int(11) NOT NULL,
  `Email_Doctor` varchar(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `grad_course` int(11) NOT NULL DEFAULT 100
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`ID`, `Email_Doctor`, `Name`, `grad_course`) VALUES
(1, 'NaderMohamed34@yahoo.com', 'DataBase', 100),
(2, 'AhmedSaker12@yahoo.com', 'Cloud Computing', 100),
(3, 'AsharfAlsysy123@gmail.com', 'Operating System', 100),
(4, 'MohamedMlhate123@yahoo.com', 'Software Engineering', 100),
(5, 'Eman.Mesalhey123@gamil.com', 'Java Programming', 100),
(6, 'Ashraf.Alatrapy@gamil.com', 'Artificial Intelligence', 100);

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `Email` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`Email`, `Password`, `Name`, `Address`) VALUES
('AhmedSaker12@yahoo.com', '1', 'Ahmed Saker', 'AhmedSaker12@yahoo.com go to school'),
('AsharfAlsysy123@gmail.com', '123123123', 'Asharf Alsysy', 'forward to go college'),
('Ashraf.Alatrapy@gamil.com', '123412341234', 'Ashraf Alatrapy', 'go to school and play video'),
('Eman.Mesalhey123@gamil.com', '123451234512345', 'Eman Mesalhey', 'go play video with me'),
('MohamedMlhate123@yahoo.com', '123123123', 'Mohamed Mlhate', 'Mohamed Mlhate go home'),
('NaderMohamed34@yahoo.com', '123456789', 'NaderMoahmed', 'Mohasn Galal Ibrahem');

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `ID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Email_doctor` varchar(100) NOT NULL,
  `grade_exam` int(11) NOT NULL,
  `long_exam` int(11) NOT NULL,
  `ID_Course` int(11) NOT NULL,
  `ques_grade` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`ID`, `Name`, `Email_doctor`, `grade_exam`, `long_exam`, `ID_Course`, `ques_grade`) VALUES
(2, 'exam1', 'AsharfAlsysy123@gmail.com', 10, 15, 3, 2),
(8, 'exam1', 'AhmedSaker12@yahoo.com', 10, 15, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `exam_resulte`
--

CREATE TABLE `exam_resulte` (
  `id_exam` int(11) NOT NULL,
  `grade` int(11) DEFAULT 0,
  `Email_stud` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `exam_resulte`
--

INSERT INTO `exam_resulte` (`id_exam`, `grade`, `Email_stud`) VALUES
(8, 2, 'ahmed.Ebrahem@yahoo.com'),
(8, 4, 'ahmed.galal224@yahoo.com'),
(8, 0, 'Ahmed.Mohamed.Khaled@yahoo.com');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `ID` int(11) NOT NULL,
  `id_exam` int(11) NOT NULL,
  `Question_Content` text NOT NULL,
  `Choose1` text NOT NULL,
  `Choose2` text NOT NULL,
  `Choose3` text NOT NULL,
  `Choose4` text NOT NULL,
  `ans_correct` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`ID`, `id_exam`, `Question_Content`, `Choose1`, `Choose2`, `Choose3`, `Choose4`, `ans_correct`) VALUES
(1, 2, 'I GO School', 'me', 'you', 'i', 'we', 'we'),
(1, 8, 'ahmed go school', 'y', 'n', 's', 'o', 'o'),
(2, 8, 'ibrahem go scholl', 'y', 'n', 's', 'o', 'o'),
(3, 8, 'amr go scool', 'y', 'n', 'o', 's', 'y'),
(4, 8, 'mohamed go scool', 't', 'y', 'f', 'n', 'f'),
(5, 8, 'english is ', 'lan', 'spe', 'no', 'yes', 'lan');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `Email` varchar(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Address` text NOT NULL,
  `Password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`Email`, `Name`, `Address`, `Password`) VALUES
('ahmed.Ebrahem@yahoo.com', 'ahmed.Ebrahem', 'dededqdqwd', '1'),
('ahmed.galal224@yahoo.com', 'Ahmed Galal', 'asdf', '123123123'),
('Ahmed.Mohamed.Khaled@yahoo.com', 'Ahmed Mohamed Khaled', 'Hi Iam Ahmed MOhamed', '123123123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`Email`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Name` (`Name`),
  ADD KEY `Email_course_doctor_fk` (`Email_Doctor`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`Email`);

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_course_exam_fk` (`ID_Course`),
  ADD KEY `Email_exam_exam_fk` (`Email_doctor`);

--
-- Indexes for table `exam_resulte`
--
ALTER TABLE `exam_resulte`
  ADD PRIMARY KEY (`id_exam`,`Email_stud`),
  ADD KEY `ID_student_exam_resulte_fk` (`Email_stud`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`ID`,`id_exam`),
  ADD KEY `ID_ques_exam_fk` (`id_exam`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `exam`
--
ALTER TABLE `exam`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `Email_course_doctor_fk` FOREIGN KEY (`Email_Doctor`) REFERENCES `doctor` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `Email_exam_exam_fk` FOREIGN KEY (`Email_doctor`) REFERENCES `doctor` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ID_course_exam_fk` FOREIGN KEY (`ID_Course`) REFERENCES `course` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `exam_resulte`
--
ALTER TABLE `exam_resulte`
  ADD CONSTRAINT `ID_exam_exam_resulte_fk` FOREIGN KEY (`id_exam`) REFERENCES `exam` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ID_student_exam_resulte_fk` FOREIGN KEY (`Email_stud`) REFERENCES `student` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `ID_ques_exam_fk` FOREIGN KEY (`id_exam`) REFERENCES `exam` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
