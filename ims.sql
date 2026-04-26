-- MySQL dump 10.17  Distrib 10.3.16-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: airtel_inventory
-- ------------------------------------------------------
-- Server version	10.3.16-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assigned_date` date DEFAULT NULL,
  `condition_on_assign` varchar(255) DEFAULT NULL,
  `condition_on_return` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `returned_date` date DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg1gfh24b5pud7o2v2dje9i6uw` (`device_id`),
  KEY `FK1nwyrfx52nof06vnvfmacbja5` (`employee_id`),
  CONSTRAINT `FK1nwyrfx52nof06vnvfmacbja5` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKg1gfh24b5pud7o2v2dje9i6uw` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (1,'2026-04-25','FAIR',NULL,'2026-04-25 20:57:43','Questioning',NULL,2,3);
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `current_status` varchar(255) DEFAULT NULL,
  `device_type` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `serial_number` varchar(255) NOT NULL,
  `specifications` varchar(1000) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `warranty_expiry` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4776vaiywo1kdis4lp8jkm0av` (`serial_number`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (1,'HP','2026-04-25 20:43:04','AVAILABLE','LAPTOP','Elite',NULL,'LP123PF','8RAM\n500GB','2026-04-25 20:43:04',NULL),(2,'Dell','2026-04-25 20:43:55','UNDER_MAINTENANCE','LAPTOP','XPS 15',NULL,'DEL001',NULL,'2026-04-25 20:58:15',NULL),(3,'HP','2026-04-25 20:43:55','AVAILABLE','LAPTOP','Pavilion',NULL,'HP002',NULL,'2026-04-25 20:43:55',NULL),(4,'Apple','2026-04-25 20:43:55','AVAILABLE','LAPTOP','MacBook Pro',NULL,'MAC003',NULL,'2026-04-25 20:43:55',NULL);
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employee_code` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_70okqib3h08m5eb1jdwld7bu9` (`employee_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (3,'2026-04-25 20:44:21','IT','Developer','chriss@gmail.com','EMP001','christian muneza','0790358591'),(4,'2026-04-25 20:44:21','IT','Analyst','belise@gmail.com','EMP002','Marie Belyse NIYITANGA','0790123452');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_log`
--

DROP TABLE IF EXISTS `issue_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `issue_description` varchar(255) DEFAULT NULL,
  `reported_date` date DEFAULT NULL,
  `resolution_notes` varchar(255) DEFAULT NULL,
  `resolution_status` varchar(255) DEFAULT NULL,
  `resolved_date` date DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `reported_by_employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrbd23yl8b5r08msn1a71ddw00` (`device_id`),
  KEY `FKe4w24imy2w4d0qb1w6wjapff` (`reported_by_employee_id`),
  CONSTRAINT `FKe4w24imy2w4d0qb1w6wjapff` FOREIGN KEY (`reported_by_employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKrbd23yl8b5r08msn1a71ddw00` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_log`
--

LOCK TABLES `issue_log` WRITE;
/*!40000 ALTER TABLE `issue_log` DISABLE KEYS */;
INSERT INTO `issue_log` VALUES (1,'2026-04-25 20:58:15','Screen display','2026-04-25','Wait till one month','IN_PROGRESS',NULL,2,3);
/*!40000 ALTER TABLE `issue_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-25 21:00:17
