-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: betapp
-- ------------------------------------------------------
-- Server version	5.6.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bets`
--

DROP TABLE IF EXISTS `bets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bets` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserId` int(10) unsigned NOT NULL,
  `MatchId` int(10) unsigned NOT NULL,
  `Result` varchar(1) DEFAULT NULL,
  `BetTime` datetime NOT NULL,
  `Selection` int(10) unsigned DEFAULT NULL,
  `LoseMoney` int(11) DEFAULT NULL,
  `CustomerId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Bets_Users1_idx` (`UserId`),
  KEY `fk_Bets_Matchs1_idx` (`MatchId`),
  KEY `fk_Bets_Customers1_idx` (`CustomerId`),
  CONSTRAINT `fk_Bets_Customers1` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`Id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bets_Matchs1` FOREIGN KEY (`MatchId`) REFERENCES `matchs` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bets_Users1` FOREIGN KEY (`UserId`) REFERENCES `users` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bets`
--

LOCK TABLES `bets` WRITE;
/*!40000 ALTER TABLE `bets` DISABLE KEYS */;
INSERT INTO `bets` VALUES (1,1,26,NULL,'2016-06-08 12:40:56',1,NULL,1),(2,1,27,NULL,'2016-06-08 14:03:38',2,NULL,1);
/*!40000 ALTER TABLE `bets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `NoP` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'ER',100),(2,'SYSTEM',1);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'Group A'),(2,'Group B'),(3,'Group C'),(4,'Group D'),(5,'Group E'),(6,'Group F');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matchs`
--

DROP TABLE IF EXISTS `matchs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matchs` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Team1Id` int(10) unsigned NOT NULL,
  `Team2Id` int(10) unsigned NOT NULL,
  `MatchRate` double DEFAULT NULL,
  `MatchTime` datetime NOT NULL,
  `ScoreTeam1` int(11) DEFAULT NULL,
  `ScoreTeam2` int(11) DEFAULT NULL,
  `CustomerId` int(10) unsigned NOT NULL,
  `MatchTypeId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Matchs_Teams1_idx` (`Team1Id`),
  KEY `fk_Matchs_Teams2_idx` (`Team2Id`),
  KEY `fk_Matchs_Customers1_idx` (`CustomerId`),
  KEY `fk_Matchs_MatchTypes1_idx` (`MatchTypeId`),
  CONSTRAINT `fk_Matchs_Customers1` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`Id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Matchs_MatchTypes1` FOREIGN KEY (`MatchTypeId`) REFERENCES `matchtypes` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Matchs_Teams1` FOREIGN KEY (`Team1Id`) REFERENCES `teams` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Matchs_Teams2` FOREIGN KEY (`Team2Id`) REFERENCES `teams` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchs`
--

LOCK TABLES `matchs` WRITE;
/*!40000 ALTER TABLE `matchs` DISABLE KEYS */;
INSERT INTO `matchs` VALUES (26,2,3,NULL,'2016-06-11 02:00:00',1,2,1,1),(27,1,4,NULL,'2016-06-11 20:00:00',2,3,1,1),(28,8,7,NULL,'2016-06-11 23:00:00',NULL,NULL,1,1),(29,5,6,NULL,'2016-06-12 02:00:00',NULL,NULL,1,1),(30,16,13,NULL,'2016-06-12 20:00:00',NULL,NULL,1,1),(31,11,10,NULL,'2016-06-12 23:00:00',NULL,NULL,1,1),(32,9,12,NULL,'2016-06-13 02:00:00',NULL,NULL,1,1),(33,15,14,NULL,'2016-06-13 20:00:00',NULL,NULL,1,1),(34,20,19,NULL,'2016-06-13 23:00:00',NULL,NULL,1,1),(35,17,18,NULL,'2016-06-14 02:00:00',NULL,NULL,1,1),(36,21,22,NULL,'2016-06-14 23:00:00',NULL,NULL,1,1),(37,24,23,NULL,'2016-06-15 02:00:00',NULL,NULL,1,1),(38,6,7,NULL,'2016-06-15 20:00:00',NULL,NULL,1,1),(39,3,4,NULL,'2016-06-15 23:00:00',NULL,NULL,1,1),(40,2,1,NULL,'2016-06-16 02:00:00',NULL,NULL,1,1),(41,5,8,NULL,'2016-06-16 20:00:00',NULL,NULL,1,1),(42,12,10,NULL,'2016-06-16 23:00:00',NULL,NULL,1,1),(43,9,11,NULL,'2016-06-17 02:00:00',NULL,NULL,1,1),(44,18,20,NULL,'2016-06-17 20:00:00',NULL,NULL,1,1),(45,14,13,NULL,'2016-06-17 23:00:00',NULL,NULL,1,1),(46,15,16,NULL,'2016-06-18 02:00:00',NULL,NULL,1,1),(47,17,19,NULL,'2016-06-18 20:00:00',NULL,NULL,1,1),(48,23,22,NULL,'2016-06-18 23:00:00',NULL,NULL,1,1),(49,24,21,NULL,'2016-06-19 02:00:00',NULL,NULL,1,1),(50,2,4,NULL,'2016-06-20 02:00:00',NULL,NULL,1,1),(51,3,1,NULL,'2016-06-20 02:00:00',NULL,NULL,1,1),(52,7,5,NULL,'2016-06-21 02:00:00',NULL,NULL,1,1),(53,6,8,NULL,'2016-06-21 02:00:00',NULL,NULL,1,1),(54,12,11,NULL,'2016-06-21 23:00:00',NULL,NULL,1,1),(55,19,17,NULL,'2016-06-21 23:00:00',NULL,NULL,1,1),(56,13,15,NULL,'2016-06-22 02:00:00',NULL,NULL,1,1),(57,14,16,NULL,'2016-06-22 02:00:00',NULL,NULL,1,1),(58,22,24,NULL,'2016-06-22 23:00:00',NULL,NULL,1,1),(59,23,21,NULL,'2016-06-22 23:00:00',NULL,NULL,1,1),(60,18,19,NULL,'2016-06-23 02:00:00',NULL,NULL,1,1),(61,20,17,NULL,'2016-06-23 02:00:00',NULL,NULL,1,1);
/*!40000 ALTER TABLE `matchs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matchtypes`
--

DROP TABLE IF EXISTS `matchtypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matchtypes` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Stake` int(10) unsigned NOT NULL,
  `CustomerId` int(10) unsigned NOT NULL,
  `TypeId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Types_Customers1_idx` (`CustomerId`),
  KEY `fk_Stakes_Types1_idx` (`TypeId`),
  CONSTRAINT `fk_Stakes_Types1` FOREIGN KEY (`TypeId`) REFERENCES `types` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Types_Customers1` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`Id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchtypes`
--

LOCK TABLES `matchtypes` WRITE;
/*!40000 ALTER TABLE `matchtypes` DISABLE KEYS */;
INSERT INTO `matchtypes` VALUES (1,10000,1,1),(2,20000,1,2),(3,50000,1,3),(4,50000,1,4);
/*!40000 ALTER TABLE `matchtypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teams` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Flag` longtext NOT NULL,
  `GroupId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Teams_Groups1_idx` (`GroupId`),
  CONSTRAINT `fk_Teams_Groups1` FOREIGN KEY (`GroupId`) REFERENCES `groups` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (1,'Albania','albania',1),(2,'France','france',1),(3,'Romania','romania',1),(4,'Switzerland','switzerland',1),(5,'England','england',2),(6,'Russia','russia',2),(7,'Slovakia','slovakia',2),(8,'Wales','wales',2),(9,'Germany','germany',3),(10,'Northern Ireland','northernIreland',3),(11,'Poland','poland',3),(12,'Ukraine','ukraine',3),(13,'Croatia','croatia',4),(14,'Czech Republic','czechRepublic',4),(15,'Spain','spain',4),(16,'Turkey','turkey',4),(17,'Belgium','belgium',5),(18,'Italy','italy',5),(19,'Republic of Ireland','republicIreland',5),(20,'Sweden','sweden',5),(21,'Austria','austria',6),(22,'Hungary','hungary',6),(23,'Iceland','iceland',6),(24,'Portugal','portugal',6);
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `types`
--

DROP TABLE IF EXISTS `types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `types` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `types`
--

LOCK TABLES `types` WRITE;
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` VALUES (1,'Qualification'),(2,'Semi-Final'),(3,'Third Place'),(4,'Final');
/*!40000 ALTER TABLE `types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Username` varchar(100) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `CustomerId` int(10) unsigned NOT NULL,
  `Role` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  KEY `fk_Users_Teams_idx` (`CustomerId`),
  CONSTRAINT `fk_Users_Teams` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`Id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tvo35','c4ca4238a0b923820dcc509a6f75849b',1,'ROLE_ADMIN'),(2,'site_admin','c4ca4238a0b923820dcc509a6f75849b',2,'ROLE_SITE_ADMIN'),(5,'tnguyen380','e10adc3949ba59abbe56e057f20f883e',1,'ROLE_USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-08 16:39:03
