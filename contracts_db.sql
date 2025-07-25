-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: contracts_db
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer_contract`
--

DROP TABLE IF EXISTS `customer_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_contract` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `addr_line_1` varchar(255) DEFAULT NULL,
  `addr_line_2` varchar(255) DEFAULT NULL,
  `addr_line_3` varchar(255) DEFAULT NULL,
  `service_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `request_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_contract`
--

LOCK TABLES `customer_contract` WRITE;
/*!40000 ALTER TABLE `customer_contract` DISABLE KEYS */;
INSERT INTO `customer_contract` VALUES (1,'Cleaning needed','Just some light cleaning for good pay','Siayne school rd','Palugama','Domp[e',1,1,'Pending'),(3,'Porfessional Wrestler needed','For personal entertainment. Good pay guranteed','189/A','Siyane School rd','Dompe',4,5,'Cancelled'),(4,'Lawn Mowing','Mow the lawn please','Siayne school rd','Palugama','Domp[e',2,2,'Cancelled');
/*!40000 ALTER TABLE `customer_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker_contract`
--

DROP TABLE IF EXISTS `worker_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `worker_contract` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cust_contract_id` varchar(255) DEFAULT NULL,
  `worker_id` varchar(255) DEFAULT NULL,
  `job_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker_contract`
--

LOCK TABLES `worker_contract` WRITE;
/*!40000 ALTER TABLE `worker_contract` DISABLE KEYS */;
INSERT INTO `worker_contract` VALUES (1,'1','1','Cancelled'),(3,'3','3','Cancelled'),(4,'4','14','Cancelled'),(5,'4','2','Cancelled'),(6,'3','12','Cancelled'),(7,'3','12','Cancelled');
/*!40000 ALTER TABLE `worker_contract` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-30  3:43:14
