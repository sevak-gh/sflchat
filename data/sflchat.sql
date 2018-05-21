-- MySQL dump 10.13  Distrib 5.6.14, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: sflchat
-- ------------------------------------------------------
-- Server version	5.6.14-log

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
-- Table structure for table `chat_room`
--

DROP TABLE IF EXISTS `chat_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `bad_word` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_room`
--

LOCK TABLES `chat_room` WRITE;
/*!40000 ALTER TABLE `chat_room` DISABLE KEYS */;
INSERT INTO `chat_room` VALUES (1,'Chat1','fuck');
/*!40000 ALTER TABLE `chat_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_room_user`
--

DROP TABLE IF EXISTS `chat_room_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_room_user` (
  `fk_chat_room` bigint(20) NOT NULL,
  `fk_user` bigint(20) NOT NULL,
  PRIMARY KEY (`fk_chat_room`,`fk_user`),
  KEY `FKkvtr7efleqio1veqa2u2ac49g` (`fk_user`),
  CONSTRAINT `FKkvtr7efleqio1veqa2u2ac49g` FOREIGN KEY (`fk_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FKt2k0t8417rpxmrn24h2wh4fd9` FOREIGN KEY (`fk_chat_room`) REFERENCES `chat_room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_room_user`
--

LOCK TABLES `chat_room_user` WRITE;
/*!40000 ALTER TABLE `chat_room_user` DISABLE KEYS */;
INSERT INTO `chat_room_user` VALUES (1,1),(1,7),(1,8),(1,10);
/*!40000 ALTER TABLE `chat_room_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `created_date_time` datetime NOT NULL,
  `fk_chat_room` bigint(20) NOT NULL,
  `fk_user` bigint(20) NOT NULL,
  `deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqn1wuc12fxsk57kwk04fof78j` (`fk_chat_room`),
  KEY `FKcafnvjjoftajegqg41crgqi4j` (`fk_user`),
  CONSTRAINT `FKcafnvjjoftajegqg41crgqi4j` FOREIGN KEY (`fk_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqn1wuc12fxsk57kwk04fof78j` FOREIGN KEY (`fk_chat_room`) REFERENCES `chat_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'haha','2018-05-20 17:20:35',1,1,'\0'),(2,'that is right','2018-05-20 17:21:05',1,5,''),(3,'yes','2018-05-20 18:53:03',1,5,'\0'),(4,'what do you do','2018-05-20 18:53:09',1,1,''),(5,'hi there','2018-05-20 19:00:57',1,5,'\0'),(6,'what','2018-05-20 19:01:01',1,5,'\0'),(7,'e','2018-05-20 19:01:02',1,5,'\0'),(8,'e','2018-05-20 19:01:02',1,5,'\0'),(9,'e','2018-05-20 19:01:03',1,5,'\0'),(10,'e','2018-05-20 19:01:03',1,5,'\0'),(11,'e','2018-05-20 19:01:03',1,5,'\0'),(12,'e','2018-05-20 19:01:03',1,5,'\0'),(13,'e','2018-05-20 19:01:04',1,5,'\0'),(14,'e','2018-05-20 19:01:04',1,5,'\0'),(15,'e','2018-05-20 19:01:04',1,5,'\0'),(16,'e','2018-05-20 19:01:04',1,5,'\0'),(17,'e','2018-05-20 19:01:04',1,5,'\0'),(18,'e','2018-05-20 19:01:05',1,5,'\0'),(19,'e','2018-05-20 19:01:05',1,5,'\0'),(20,'e','2018-05-20 19:01:05',1,5,'\0'),(21,'e','2018-05-20 19:01:05',1,5,'\0'),(22,'e','2018-05-20 19:01:06',1,5,'\0'),(23,'e','2018-05-20 19:01:06',1,5,'\0'),(24,'e','2018-05-20 19:01:06',1,5,'\0'),(25,'e','2018-05-20 19:01:06',1,5,'\0'),(26,'hola','2018-05-20 20:09:44',1,1,'\0'),(27,'hell there','2018-05-20 20:43:04',1,1,'\0'),(28,'yoooooooooooooooo','2018-05-20 20:43:11',1,5,'\0'),(29,'fuck you','2018-05-20 20:43:17',1,1,''),(30,'fuckkkkk','2018-05-20 20:43:22',1,1,''),(31,'hooooaaassdddd','2018-05-20 20:46:40',1,1,'\0'),(32,'fuck','2018-05-20 20:46:45',1,1,''),(33,'hahaha','2018-05-20 20:51:11',1,5,'\0'),(34,'fuck','2018-05-20 20:51:19',1,5,'');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'profile_view'),(2,'profile_list'),(3,'profile_create'),(4,'profile_update'),(5,'profile_delete'),(6,'admin_view'),(7,'admin_profile'),(8,'admin_chat');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'','USER'),(2,'','ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `fk_role` bigint(20) NOT NULL,
  `fk_permission` bigint(20) NOT NULL,
  PRIMARY KEY (`fk_role`,`fk_permission`),
  KEY `FKk638db7sqf9ghfcnigqhjm45p` (`fk_permission`),
  CONSTRAINT `FKk638db7sqf9ghfcnigqhjm45p` FOREIGN KEY (`fk_permission`) REFERENCES `permission` (`id`),
  CONSTRAINT `FKme1ne8q6r5a675x5n5e5wh2ib` FOREIGN KEY (`fk_role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL,
  `expiration_date` tinyblob,
  `locked` bit(1) NOT NULL,
  `password` varchar(100) NOT NULL,
  `remote_ip` varchar(15) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `picture_file_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'',NULL,'\0','$2a$06$sG1H8HYp54q5pQsmpVe1dOCI6uKqZ7/uutucshNF2vw/baGcnWwie',NULL,'user','Sevak','Gharibian','1_1.jpg'),(2,'',NULL,'\0','$2a$06$sG1H8HYp54q5pQsmpVe1dOCI6uKqZ7/uutucshNF2vw/baGcnWwie',NULL,'admin','','',NULL),(5,'',NULL,'\0','$2a$10$aHFNrEVvmegcQ8Uo40qUzeF2ThAiyl6VVZntGq9HdZhwph4u9Ey72',NULL,'jsmith','John','Smithson','5_elinor_7.jpg'),(6,'\0',NULL,'\0','$2a$10$N81yftxnTf82T4aW48iuwOCyCw4IcyOuKIGij3kg2Bd1hK2h/3S4S',NULL,'billg','','',NULL),(7,'',NULL,'\0','$2a$10$RVWCGmfTYC/SrYVyDe.F1.1oPa.em7Ysr9/p/zwUEZOHX9EMw/0gW',NULL,'sjobs','Steve','Jobs',NULL),(8,'',NULL,'\0','$2a$10$K0EZ2Mk6NFVsNtzRFDjRHe2Xowr094luYrwGPV6oydgF8OBlCefou',NULL,'dtrump','Donald','Trump','8_DSC_0074.JPG'),(9,'',NULL,'\0','$2a$10$jfl8NNM1KbOscaBS/ueO2u.EhbNMMa982rM4BtiIFLqNBcar7EI7q',NULL,'jlondon','Jack','London',NULL),(10,'',NULL,'\0','$2a$10$QxhDPz8qTWslur13UCCROOHaHpn9szBGFHbr7GNGG7YtXyjX1yE0e',NULL,'sevak.gharibian@gmail.com','SevakKKK','Gharibiannnn','10_sevak.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `fk_user` bigint(20) NOT NULL,
  `fk_role` bigint(20) NOT NULL,
  PRIMARY KEY (`fk_user`,`fk_role`),
  KEY `FKgbu0pflljt9n66vaqmpdh304n` (`fk_role`),
  CONSTRAINT `FK2h2x3xjadotxe72nh4h0all64` FOREIGN KEY (`fk_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FKgbu0pflljt9n66vaqmpdh304n` FOREIGN KEY (`fk_role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(2,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-21  8:20:53
