CREATE DATABASE  IF NOT EXISTS `groupproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `groupproject`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: groupproject
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `myuser_id` int NOT NULL,
  `street_name` varchar(50) NOT NULL,
  `house_number` varchar(10) NOT NULL,
  `city` varchar(45) NOT NULL,
  `postal_code` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `billing` tinyint(1) DEFAULT NULL,
  `shipping` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_addresses_myusers_id__myusers_id_idx` (`myuser_id`),
  CONSTRAINT `fk_addresses_myuser_id__myusers_id` FOREIGN KEY (`myuser_id`) REFERENCES `myusers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,1,'Zemlak Fords','8247','Grahamview','96452','Poland',1,0),(2,2,'Abe Points','750','West Lexusstad','62094','Oman',0,1),(3,3,'Valentin Pines','526','Monahanberg','72985','Samoa',1,0),(4,4,'Agnes Route','8235','Donnellymouth','14437-3140','Andorra',1,0),(5,5,'Tommie Mountain','75089','Effertzside','18671-1480','Canada',0,1),(6,6,'Raul Valleys','864','Satterfieldfurt','10189','Christmas Island',1,0),(7,7,'Adolf Stravenue','93','Mayerburgh','92516-8466','Austria',1,0),(8,8,'Wiegand Locks','8814','Victoriachester','43164-6804','Belize',1,0),(9,9,'Schmidt Dam','47436','Harrismouth','76250-0639','Seychelles',0,1),(10,10,'Koss Gardens','94883','Albinaberg','82547-2493','Reunion',1,0),(11,11,'Champlin Junctions','3870','North Colinmouth','49640-9442','Burundi',0,1),(12,12,'Khalid Island','6788','Herzogshire','11435','Togo',1,0),(13,13,'Bruen Corner','98453','South Ward','48261-0333','Pakistan',1,0),(14,14,'Cordie Heights','36005','Dorisstad','37075-1382','Guadeloupe',1,0),(15,15,'Abigail Springs','8790','New Christineview','23283-3725','Colombia',1,0),(16,16,'Towne Estate','27724','East Kristian','05910','Russian Federation',1,0),(17,17,'Hintz Ridges','7054','Kennyborough','00419','Luxembourg',0,1),(18,18,'Okuneva Forks','179','Lake Jayce','26906','Samoa',0,1),(19,19,'Beer Ways','228','Adelleberg','24030','Eritrea',1,0),(20,20,'Taylor Island','7972','South Johan','68614-3281','Marshall Islands',1,0),(31,1,'Ada Union','936','Mertieport','98356','Northern Mariana Islands',0,1),(32,2,'Armstrong Highway','64960','Saulmouth','13281','Macao',1,0),(33,3,'Jonathan Shoals','805','Altenwerthshire','93576','Uruguay',0,1),(34,4,'Breitenberg Streets','15888','South Angiefurt','81894','Indonesia',0,1),(35,5,'Weimann Groves','15730','New Kianna','98813-4197','United Kingdom',1,0),(36,6,'McDermott Courts','2559','Lake Yvonneside','22010','Saint Helena',0,1),(37,7,'Balistreri Branch','8582','Boscochester','77334-7785','Slovenia',0,1),(38,8,'Farrell Crest','9137','New Annabel','36828','Luxembourg',0,1),(39,9,'Sarina Creek','266','East Pabloland','61935-6071','Azerbaijan',1,0),(40,10,'Hailee Turnpike','104','Leannonport','15645-3302','Rwanda',0,1),(41,11,'Irwin Rest','48940','Rathmouth','07134','Tonga',1,0),(42,12,'Gutmann Knolls','9082','Kertzmannport','02783','French Southern Territories',0,1),(43,13,'Jacobi Station','1922','West Edwinaton','03736','China',0,1),(44,14,'Stark Circle','7953','Port Roslyn','11268-9649','French Guiana',0,1),(45,15,'Satterfield Fords','832','New Selmer','10890','Trinidad and Tobago',0,1),(46,16,'Homenick Well','583','Port Thurman','18841-4124','Bahrain',0,1),(47,17,'Jacey Valleys','717','Kailynchester','35905','United Kingdom',1,0),(48,18,'Earlene Shore','91946','Port Hettie','94796','Estonia',1,0),(49,19,'Ciara Island','51505','North Adolf','04100','Saint Helena',0,1),(50,20,'Immanuel Mountains','521','New Georgiannaborough','17112-3247','Lao People\'s Democratic Republic',0,1),(64,37,'Panepistimiou','23','Athens','13234','Greece',0,1),(65,37,'Prikatorou','234','Athens','12345','Greece',1,0),(126,39,'PANEPISTIMIOU','34','ATHENS','14576','Greece',0,1),(127,39,'PROUSSIS','80','ATHENS','14576','Greece',1,0),(129,54,'PROUSSIS','80','ATHENS','14576','Greece',0,1),(130,53,'PANEPISTIMIOU','25','ATHENS','14576','Greece',0,1);
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_details`
--

DROP TABLE IF EXISTS `cart_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_details` (
  `cart_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`cart_id`,`product_id`),
  KEY `FK_cart_details_product_id__products_id` (`product_id`),
  CONSTRAINT `FK_cart_details_cart_id__carts_id` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`),
  CONSTRAINT `FK_cart_details_product_id__products_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_details`
--

LOCK TABLES `cart_details` WRITE;
/*!40000 ALTER TABLE `cart_details` DISABLE KEYS */;
INSERT INTO `cart_details` VALUES (500,1,2),(501,16,1),(502,17,6),(503,18,1),(504,19,1),(505,20,1),(506,21,1),(507,22,1),(508,23,2),(509,24,1),(510,25,1),(511,26,1),(512,27,1),(513,28,1),(514,29,1),(515,30,1),(516,32,1),(517,33,2),(518,1,1),(519,16,1),(532,18,2),(548,23,1);
/*!40000 ALTER TABLE `cart_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `myuser_id` int NOT NULL,
  `date` date DEFAULT NULL,
  `total_discount` decimal(10,3) DEFAULT NULL,
  `total_amount` decimal(10,3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_carts_myuser_id__myusers_id_idx` (`myuser_id`),
  CONSTRAINT `fk_carts_myuser_id__myusers_id` FOREIGN KEY (`myuser_id`) REFERENCES `myusers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=553 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (500,1,'2021-03-04',NULL,80.000),(501,2,'2021-03-04',NULL,125.000),(502,3,'2021-03-04',NULL,35.000),(503,4,'2021-03-04',NULL,200.000),(504,5,'2021-03-04',NULL,80.000),(505,6,'2021-03-04',NULL,65.000),(506,7,'2021-03-04',NULL,120.000),(507,8,'2021-03-04',NULL,135.000),(508,9,'2021-03-04',NULL,55.000),(509,10,'2021-03-04',NULL,85.000),(510,11,'2021-03-04',NULL,75.000),(511,12,'2021-03-04',NULL,230.000),(512,13,'2021-03-04',NULL,180.000),(513,14,'2021-03-04',NULL,210.000),(514,15,'2021-03-04',NULL,240.000),(515,16,'2021-03-04',NULL,190.000),(516,17,'2021-03-04',NULL,250.000),(517,18,'2021-03-04',NULL,300.000),(518,19,'2021-03-04',NULL,80.000),(519,20,'2021-03-04',NULL,125.000),(532,41,'2021-03-28',NULL,720.000),(545,39,'2021-04-11',0.000,0.000),(547,54,'2021-04-12',0.000,0.000),(548,52,'2021-04-12',0.000,90.000),(552,53,'2021-04-12',0.000,0.000);
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Chairs'),(2,'Tables'),(3,'Various'),(4,'Cabinets'),(5,'Beds'),(6,'Cabinets & Display Cabinets'),(7,'Couches');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (200,'wood'),(201,'fabric'),(202,'steel'),(203,'pvc'),(204,'glass'),(205,'rope'),(206,'leather');
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myusers`
--

DROP TABLE IF EXISTS `myusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myusers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(55) NOT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `paypal_acc` varchar(45) DEFAULT NULL,
  `title` varchar(15) DEFAULT NULL,
  `reset_password_token` varchar(65) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myusers`
--

LOCK TABLES `myusers` WRITE;
/*!40000 ALTER TABLE `myusers` DISABLE KEYS */;
INSERT INTO `myusers` VALUES (1,'bahringer.brain','4cb4fb961fd36a9c63c65eb62f8ca1f5888fe3bb','Eulah','Klein','gaetano88@example.com','1-712-766-4193x','4929988383168724','Prof.',NULL),(2,'eschumm','69a19cd031bb29337adfbc24e15e7bf7ef3d8ef9','Arely','Hyatt','dortha98@example.net','01017771904','5174509013493813','Prof.',NULL),(3,'romaguera.theresia','951b8bed6b9a41cc6f078c3e16aa1ef26432ef09','Emiliano','Abernathy','fredrick05@example.net','(249)855-7089','4929263540635425','Prof.',NULL),(4,'ernestine.o\'reilly','65e7c8b49b7e049790a91986ae379e232fb8965c','Alek','Mueller','hrowe@example.net','1-816-428-7994','4539623653251','Miss',NULL),(5,'gus.collins','1449ea3a870418206131e6f6dbaa2bb0a0bc8c8f','Keith','Cassin','otis.grady@example.org','779.566.9951','5183989101090541','Ms.',NULL),(6,'berneice.collins','3ef29317d2b8eefe5dc187b0f7f2d313f0b51ccc','Emerald','Roberts','akris@example.com','516-102-3707x30','4024007163956','Mrs.',NULL),(7,'crist.jany','bfbafd275b9b85a056950f6f9f0f6313a0cf7bed','Milo','Lubowitz','parisian.timmy@example.com','1-513-556-5786x','6011690092373672','Dr.',NULL),(8,'mgoyette','7210c0d7e59fb1667e273ada1d9c0ee1d1b7886f','Elouise','Collins','raleigh60@example.net','355-860-0854x36','4546797688074','Dr.',NULL),(9,'zoie84','bc133f43794029bd4ffafe3eb4738b1fb2408617','Shawn','Wunsch','kovacek.beth@example.org','305.842.9760x37','5416095855090104','Dr.',NULL),(10,'roma.steuber','50dc7ecbeaf7178a671f2ade7480edd0dc9e78bd','Melody','Ankunding','candice13@example.org','02972497460','5495475913961681','Prof.',NULL),(11,'qauer','080b2a0d87aae4058c6e2728d9406f8787f99dd4','Lauryn','Bartoletti','vwatsica@example.org','+17(2)765428090','349538703700267','Prof.',NULL),(12,'gladys17','206fae638a4503eea9125a0a355b96b0fdae6db0','Judy','McKenzie','ocruickshank@example.org','485.546.8622x34','5241881259392404','Dr.',NULL),(13,'herta.mohr','e3b8a4b9b161502ae3730106311f9cdd8c66e2a2','Sally','Douglas','jefferey10@example.net','+00(7)487615642','4024007181802','Prof.',NULL),(14,'pkoepp','3e27f4111ca2f402de7f38d9e3cea800a9c6a79e','Edna','Huels','nolson@example.com','01313753806','6011969674885165','Dr.',NULL),(15,'lang.mattie','38ff8880cb2124478da2df432a7fe340171b1736','Abe','Streich','sammy57@example.org','942.394.3578x18','5309045777631944','Dr.',NULL),(16,'ellen07','def67d37e28d3d936f8f5c3b619eb46405a98b70','Favian','Roob','kpaucek@example.net','554.751.4144','4024007187572800','Mr.',NULL),(17,'vernser','c61123c7a0bd2e4f8a969c5579f9ac3c487ce365','Janessa','Hamill','gibson.jackeline@example.org','1-104-175-2734x','4716221719851231','Dr.',NULL),(18,'ywiegand','4eb6fd8e77bf462cbf39fdf1a3c1a35129e7dea6','Kadin','Runte','powlowski.lela@example.org','+97(2)845179051','5469677101020010','Prof.',NULL),(19,'orn.dillon','c4d6d56cd50f1fe987f64602348c75fdca1932da','Sally','Marvin','chet.howell@example.net','(067)420-5026','6011715689321060','Mrs.',NULL),(20,'raynor.jevon','c18f1404f4d53ce9343c8caa377d35e6be5d5b13','Mariela','Kovacek','merl35@example.com','(081)822-3306','4302345559604266','Ms.',NULL),(21,'takis','$2a$10$KtckRypPHhIP4xBdJgWRquWL/URHRe6ftmOg1VTAUgD7ybe20Ezdu','Takis','Zaharatos','takis@gmail.com','+30-4646-4848','4302345559604266','null',NULL),(22,'maria','$2a$10$K8Gmdqq/FWqLed70AKpdtOtr1DIv7Tjol3kxm4gIZgViXA9coXwA2','Maria','Kanari','maria@gmail.com',NULL,'4302345559604266',NULL,NULL),(23,'bob','$2a$10$pPlqqaG7aYOKdMZROOkBbu916GBZYmHT32ps6iDOsxM/O/DLVRMtC','Bob','Bobby','bob@gmail.com','+30-4747-4747','4302345559604266','null',NULL),(24,'eleni','$2a$10$dZ6D4h28GPCg7Z.9/GxIzuj1YIGmfXrED1B6XK6nJRRTzx.urGjte','Eleni','Karpouza','eleni@gmial.com',NULL,'4302345559604266',NULL,NULL),(25,'tania','$2a$10$qlAQ6WXHB4pe99Anwdzi4u3HSYjfrnnzqozmt8OPTry8fKX01jsOu','KONSTANTINA','KARAGEORGI','taniakarageorgi@gmail.com','6946005647','4302345559604266','Mrs','qjGgfPfwNlaYpfBHLTxxQYScA0w5tFqH0neiviPrlIeYd'),(26,'taniakarageorgi6666@gmail.com','$2a$10$J8CaGZn4jcvRtSbBLVJ9Ie1O4anQj9CDqNoz.wO3EdtQ.R8wp5q6q','KARAGEORGI','Konstantina','+306946005763','Mrs.','4302345559604266','konstantina',NULL),(28,'jeff','$2a$10$o7tICULvMZodAzA6buUnr.gPqHetYVKP.aCWldLeH/YUBrcxMKjaK','Jeff','Bridges','jeff@gmail.com','43434343434','4302345559604266','Dr.',NULL),(30,'tania4VVVVV','$2a$10$uOHXp77MxIGhKO1Uc4s4jOQHNPeNmVa0rU64Ii59W8/mYdv0gC8u.','KONSTANTINA','KARAGEORGI','taniakarageorgVVVi@gmail.com','+306946005763','4302345559604266','Mr.',NULL),(32,'elli','$2a$10$PQGJjaAEZLLggyG6aP.p/O7elFSaI0u8WnMHbEv44VDB4y5b.xW/G','Ellina','Kakouli','elli@gmail.com','444444333444','4302345559604266','Mrs.','HCRALuVrHCmWlHfhWRarNlaj03NU9rVxg0HnWAJXR4GXp'),(33,'lia','$2a$10$1//tKnmgvfPN6thCdRmP/.ZVXhuBt.W/Pf84wE2BhNRwuja.T2MP6','Liaki','Kanouri','lia@gmail.com','34674757585','4302345559604266','Ms.',NULL),(34,'john','$2a$10$0GsADkif7NvC5cfee7KIkuIJw/6ARjsbN84qvdkeTTIRVEwZ5/jQC','John','John','ho@gmail.com','474747474774','4302345559604266','Dr.',NULL),(35,'will','$2a$10$ey9gN/fuL2gJBS54ajiF0emZxzCyi3niRQpai4k6Ug3FW3ihQM1Gm','Willliam','Smith','will@gmail.com','48484848488484',NULL,'Ms.',NULL),(37,'johnH','$2a$10$ZmDAlKMB9NvKyF5PZ9f5xu/.ZT2sJbQL99LSQ6j8RQ52oXhyTRa0W','John','Hopkins','johnH@gmail.com','46575647564',NULL,'Prof.',NULL),(38,'tania8','$2a$10$d0HvJJvSOMkDhnJDSeGn8.v2plv88dC.wfDrC0dIPvN0Tn2nFQmTa','KONSTANTINASS','KARAGEORGI','taniakarageorgi11111@gmail.com','+30694600576311',NULL,'Ms.',NULL),(39,'panagiwtis','$2a$10$QQj/JK6HmHQ4VuFJrFow9.1khdqARFErAj9uVSCJK.0AB.yvBDeh6','Panagiwtis','Gourdouros','pan@gmail.com','+30-5647-484848',NULL,'Ms.',NULL),(41,'rena','$2a$10$jzgQ0Gn.WLCSKODHS6YcjOS1wj85VQQuFkmbGrO4VhjQIn933oJxC','Rena','Renaki','rena@gmail.com','5757575757575',NULL,'Mrs.',NULL),(44,'rena1','$2a$10$a4IX2HoqXZtxUQNm65.ICuNIOwdjOFglvq3kydIhNZXLO5bHHtJFm','Rena','Renaki','aki@gmail.com','+30-6444-6984',NULL,'Mrs.',NULL),(46,'katerina','$2a$10$1QhssELMdtgKLqiO/hRP9OTKM5TJ/BtrGPTwaEAVYmLsP.At6IUje','KaterinaKI','Kakloudi','katerina@gmail.com','+30-3636-3838',NULL,'Mrs.',NULL),(51,'jake','$2a$10$ASrkKrZKsD5PQvSG6qUNyehvORtrpa4T6/WhkI/OCZyYgIzox1aUm','Jake','Don','jake@yahoo.com','+30-6464-494848',NULL,'Dr.',NULL),(52,'lucky','$2a$10$bIk5RrNcQmkEmigzx0CJZuQud2Fzs7IuwEEeIRRssCjETxhtuIqBK','Tania','Karageorgi','info@luckyhomes-oia.com','+30-6946-005763',NULL,'Mrs.',NULL),(53,'mcvakou','$2a$10$aDY26dJxc2qV1r6CBhz.b.vmwA.UkcGEG2iwdHEUD8/y38GshAnn.','Konstantinos','Vakouftsis','mc@hotmail.com','+30-6758-998764',NULL,'Mrs.',NULL),(54,'peoplecert','$2a$10$PX1cZfUfwO2hGfGXcw8bE.LrBMni9OnkHLTpUfs1s1pnGD6VgXS2C','People','Cert','p.gourdouros@gmail.com','+30-7464-847362',NULL,'Mr.',NULL),(55,'admin','$2a$10$t.2iAKW3TVFYof4ZQw82S.HZDenfPsSYriv3fJsBiw5q6XNsfdGmG','Admin','Admin','taniakarageorgi4@gmail.com','+306946005763',NULL,'Mr.',NULL),(56,'billm','$2a$10$F5/01tpMJeSvDrn8.C2HuO/pVUtIfHEN7hmvRPn48XGEjobgFayP.','Bill','Marrey','bil@yahoo.com','+30-4646-494949',NULL,'Mr.',NULL);
/*!40000 ALTER TABLE `myusers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myusers_roles`
--

DROP TABLE IF EXISTS `myusers_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myusers_roles` (
  `myuser_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`myuser_id`,`role_id`),
  KEY `fk_myusers_roles_roles_id__roles_id_idx` (`role_id`),
  CONSTRAINT `fk_myusers_roles_myuser_id__myusers_id` FOREIGN KEY (`myuser_id`) REFERENCES `myusers` (`id`),
  CONSTRAINT `fk_myusers_roles_role_id__roles_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myusers_roles`
--

LOCK TABLES `myusers_roles` WRITE;
/*!40000 ALTER TABLE `myusers_roles` DISABLE KEYS */;
INSERT INTO `myusers_roles` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(28,1),(30,1),(32,1),(33,1),(34,1),(35,1),(37,1),(38,1),(39,1),(41,1),(44,1),(46,1),(51,1),(52,1),(53,1),(54,1),(55,1),(56,1),(21,2),(22,2),(23,2),(39,2),(44,2),(46,2),(51,2),(53,2),(54,2),(55,2),(56,2),(39,3),(54,3);
/*!40000 ALTER TABLE `myusers_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_id` int NOT NULL,
  `myuser_id` int NOT NULL,
  `date_placed` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `total_discount` int DEFAULT NULL,
  `total_amount` decimal(8,2) DEFAULT NULL,
  `ship_address_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK_orders_user_id_users_id` (`myuser_id`),
  KEY `FK_orders_status_id__statuses_id` (`status_id`),
  KEY `FK_orders_ship_address_id__addresses_id_idx` (`ship_address_id`),
  CONSTRAINT `FK_orders_ship_address_id__addresses_id` FOREIGN KEY (`ship_address_id`) REFERENCES `addresses` (`id`),
  CONSTRAINT `FK_orders_status_id__statuses_id` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`),
  CONSTRAINT `FK_orders_user_id_users_id` FOREIGN KEY (`myuser_id`) REFERENCES `myusers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (7,1,1,'2020-03-22 22:00:00','2021-03-05 09:20:21',NULL,385.00,31),(8,2,7,'2020-11-11 22:00:00','2021-03-05 09:37:03',NULL,120.00,37),(9,3,12,'2021-01-11 22:00:00','2021-03-05 09:37:03',NULL,160.00,42),(10,2,5,'2021-02-10 22:00:00','2021-03-05 09:41:33',NULL,110.00,5),(11,1,11,'2020-02-20 22:00:00','2021-03-05 09:41:33',NULL,35.00,11),(12,3,18,'2020-09-26 21:00:00','2021-03-05 09:41:33',NULL,440.00,18),(21,2,39,'2021-04-11 20:39:15','2021-04-11 20:39:15',0,550.00,126),(22,2,54,'2021-04-12 10:48:54','2021-04-12 10:48:54',0,960.00,129),(23,2,53,'2021-04-12 13:29:30','2021-04-12 13:29:30',0,225.00,130),(24,2,53,'2021-04-12 13:52:16','2021-04-12 13:52:16',0,1130.00,130),(25,2,53,'2021-04-12 13:52:48','2021-04-12 13:52:48',0,0.00,130);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_details`
--

DROP TABLE IF EXISTS `orders_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_details` (
  `product_id` int NOT NULL,
  `order_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`product_id`,`order_id`),
  KEY `fk_products_orders_order_id__orders_id` (`order_id`),
  CONSTRAINT `fk_products_orders_order_id__orders_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `fk_products_orders_product_id__products_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `orders_details_chk_1` CHECK ((`quantity` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_details`
--

LOCK TABLES `orders_details` WRITE;
/*!40000 ALTER TABLE `orders_details` DISABLE KEYS */;
INSERT INTO `orders_details` VALUES (1,10,1),(1,23,1),(16,7,2),(17,7,1),(17,11,1),(17,21,1),(18,21,1),(19,9,2),(20,21,1),(21,8,1),(23,10,2),(30,12,1),(30,24,1),(32,12,1),(36,22,1),(37,22,1),(56,24,1),(57,24,1);
/*!40000 ALTER TABLE `orders_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_payments_order_id__orders_id` (`order_id`),
  KEY `FK_payments_status_id__statuses_id` (`status_id`),
  CONSTRAINT `FK_payments_order_id__orders_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_payments_status_id__statuses_id` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,7,2),(2,8,2),(3,9,2),(4,10,4),(5,11,5),(6,12,2);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `description` varchar(80) DEFAULT NULL,
  `price` decimal(8,2) NOT NULL,
  `sku` varchar(10) NOT NULL,
  `discount` decimal(10,3) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `stock` int unsigned NOT NULL,
  `category_id` int DEFAULT NULL,
  `style_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `sku` (`sku`),
  KEY `FK_category_category_id_id_idx` (`category_id`),
  KEY `FK_style_style_id_id_idx` (`style_id`),
  CONSTRAINT `FK_category_category_id_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_style_style_id_id` FOREIGN KEY (`style_id`) REFERENCES `style` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'DINOF12','Old style country vintage chair',225.00,'0019966881',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',12,1,1),(16,'VINTIGOARM','Living room armchair with vintage fabric',190.00,'0023123121',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',4,1,2),(17,'KITCHFAB','Industrial style steel & pvc kitchen chair',95.00,'0032312217',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',10,1,3),(18,'URBTAB12','Urban style dining table ',360.00,'0041982374',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',1,2,3),(19,'FOOTAB','Kitchen table for small area',120.00,'0059376262',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',6,2,3),(20,'COMODO','Modern bedside table with drawer',95.00,'0067433339',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',2,3,3),(21,'BALCONATO','Rustic outdoor dining table ',450.00,'0078176299',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',1,2,1),(22,'BYTHEBED','Wooden console table ',235.00,'0089653008',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',3,3,3),(23,'CAFFEDO','Rustic coffee table',90.00,'0099898769',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',4,2,1),(24,'SONGLAND','Bohemian cabinet',245.00,'0107564545',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',2,4,4),(25,'BRIMNES','Dressing table with drawers and Mirror',135.00,'0073599866',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',3,3,3),(26,'MALME','Contemporary style double bed with side tables',230.00,'0112988811',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',4,5,3),(27,'HYMN','Bohemian style single bed frame',245.00,'0128877665',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',6,5,4),(28,'TARVA','Rustic wood & steal double bed',280.00,'0118899265',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',5,5,1),(29,'LINDHOLT','Double tall storage cabinet',240.00,'0128837728',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',1,6,4),(30,'KERBY','Living room display cabinet ',230.00,'0129823321',NULL,'2021-03-04 11:55:58','2021-03-04 11:55:58',4,6,1),(32,'SUDOR','Comfy living room couch',460.00,'0112887722',NULL,'2021-03-04 14:09:31','2021-03-04 14:09:31',1,7,3),(33,'SETAR','Leather 3 seater couch',850.00,'0122233344',NULL,'2021-03-04 14:09:31','2021-03-04 14:09:31',2,7,2),(34,'BOHOARM','Bohemian Armchair ',350.00,'0123647584',NULL,'2021-03-28 13:04:33','2021-03-28 13:04:33',5,1,4),(35,'KORBYC','Colorfull vintage cabinet',335.00,'0134562876',NULL,'2021-03-28 13:49:49','2021-03-28 13:49:49',3,4,2),(36,'WONDERC','Stunning Bohemian Closet',580.00,'0125476587',NULL,'2021-03-28 19:49:35','2021-03-28 19:49:35',4,3,4),(37,'CLOSETV','Vintage Closet',380.00,'0154398785',NULL,'2021-03-28 19:52:09','2021-03-28 19:52:09',3,3,2),(38,'BEDDY','Stylish Country Bed',265.00,'0143276897',NULL,'2021-03-28 19:54:20','2021-03-28 19:54:20',5,5,1),(39,'RUSTY','Wood and Steal Cabinet',675.00,'0154673888',NULL,'2021-03-28 19:56:55','2021-03-28 19:56:55',3,4,1),(40,'COSMO','Beautiful Rustic Cabinet',385.00,'0172736483',NULL,'2021-03-28 19:59:36','2021-03-28 19:59:36',2,4,4),(41,'WHITEB','Bohemian Chic Carved Bed',270.00,'0132456798',NULL,'2021-04-07 22:07:20','2021-04-07 22:07:20',5,5,4),(42,'CABIN32','Tall Wooden Rustic Cabinet',190.00,'0132657889',NULL,'2021-04-07 22:10:53','2021-04-07 22:10:53',6,4,1),(43,'LEATARM','Leather ArmChair',760.00,'0132476578',NULL,'2021-04-07 22:26:26','2021-04-07 22:26:26',3,1,2),(44,'BOHOCAB','Boho Chick Side Table',165.00,'0235424568',NULL,'2021-04-07 22:28:23','2021-04-07 22:28:23',7,3,4),(45,'COLORCA','Colorfull Bohemian Chest',360.00,'0123659875',NULL,'2021-04-07 22:30:12','2021-04-07 22:30:12',4,4,4),(46,'GREENC','Vintage Display Cabinet',240.00,'0172634857',NULL,'2021-04-07 22:43:07','2021-04-07 22:43:07',3,4,2),(47,'GARRET','Vintage Display Cabinet',370.00,'0127364736',NULL,'2021-04-07 22:45:50','2021-04-07 22:45:50',4,4,2),(48,'TRENTB','Restored Green Chest',190.00,'0192847484',NULL,'2021-04-07 22:49:05','2021-04-07 22:49:05',4,3,2),(49,'FARGO','Elegant Coffe Table',460.00,'0183834748',NULL,'2021-04-07 22:50:42','2021-04-07 22:50:42',3,2,3),(50,'COMFYG','Leather Style Chair',260.00,'0192893373',NULL,'2021-04-07 22:52:13','2021-04-07 22:52:13',5,1,3),(51,'WHITE L','White Leather Couch',650.00,'0273647585',NULL,'2021-04-07 22:55:47','2021-04-07 22:55:47',3,7,3),(52,'FRANIK','Stylish Glass Coffee Table',160.00,'0182837373',NULL,'2021-04-07 22:57:35','2021-04-07 22:57:35',3,2,3),(53,'BECAB','Beautiful Vintage Side Table',180.00,'0182837348',NULL,'2021-04-07 22:59:10','2021-04-07 22:59:10',4,3,2),(54,'BRWONT','Two Drawer Vintage Side Table',160.00,'0182837333',NULL,'2021-04-08 14:20:02','2021-04-08 14:20:02',3,3,2),(55,'SKSTEEL','Industrial stool',120.00,'0123574647',NULL,'2021-04-09 20:54:31','2021-04-09 20:54:31',4,1,3),(56,'TOBOK','White Leather Armchair',670.00,'0123457428',NULL,'2021-04-09 20:55:18','2021-04-09 20:55:18',4,1,3),(57,'SHEEPT','Leather Chair',230.00,'0173645232',NULL,'2021-04-09 20:59:18','2021-04-09 20:59:18',4,1,3),(58,'STOPMY','Industrial feel side table',90.00,'0182736273',NULL,'2021-04-09 21:02:57','2021-04-09 21:02:57',6,3,3),(59,'GLASSKT','Stylish Glass Kitchen table',240.00,'0125433343',NULL,'2021-04-09 21:04:31','2021-04-09 21:04:31',3,2,3),(60,'DIRECT','Wooden Vintage Bed',460.00,'0123645432',NULL,'2021-04-09 21:06:32','2021-04-09 21:06:32',3,5,2),(61,'COMODOR','Wooden Vintage Bed',340.00,'0182843627',NULL,'2021-04-09 21:08:15','2021-04-09 21:08:15',4,5,2),(62,'BOHOB','Colourfool Bohemian poof',80.00,'0932817838',NULL,'2021-04-09 21:10:14','2021-04-09 21:10:14',10,3,4),(63,'BOHOC','Colourfool Bohemian poof',80.00,'0393948381',NULL,'2021-04-09 21:10:14','2021-04-09 21:10:14',4,3,4),(64,'SHAPES','Wood Glass dining Table',320.00,'0192929382',NULL,'2021-04-09 21:16:34','2021-04-09 21:16:34',4,2,3),(65,'TRIOB','Set of Wooden side tables',410.00,'0392843828',NULL,'2021-04-09 21:16:34','2021-04-09 21:16:34',3,2,3),(66,'TRUNK','Rustic side Table',210.00,'0192837823',NULL,'2021-04-09 21:32:57','2021-04-09 21:32:57',2,2,1),(70,'BRIGHT','Stylish Glass Middle Table',200.00,'0932899838',0.000,'2021-04-10 20:27:11','2021-04-11 16:35:52',3,2,3),(72,'LEATHGR','Leather Long Chair',240.00,'0123677589',0.000,'2021-04-11 10:17:15','2021-04-11 10:17:15',4,1,3),(73,'REDCH4','Modern Red PVC Chair',110.00,'0123645589',0.000,'2021-04-12 09:22:07','2021-04-12 09:22:07',7,1,3),(74,'VINTTV','Vintage Tv Stand',200.00,'0154348785',0.000,'2021-04-12 12:23:14','2021-04-12 12:24:27',5,3,2);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_images`
--

DROP TABLE IF EXISTS `products_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image_path` varchar(45) NOT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK_products_images_product_id__products_id` (`product_id`),
  CONSTRAINT `FK_products_images_product_id__products_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_images`
--

LOCK TABLES `products_images` WRITE;
/*!40000 ALTER TABLE `products_images` DISABLE KEYS */;
INSERT INTO `products_images` VALUES (1,'/images/16.jpg',16),(2,'/images/27.jpg',27),(3,'/images/30.jpg',30),(5,'/images/1.jpg',1),(6,'/images/17.jpg',17),(7,'/images/27b.jpg',27),(8,'/images/18.jpg',18),(9,'/images/19.jfif',19),(10,'/images/20.jpg',20),(11,'/images/21.jpg',21),(12,'/images/22.png',22),(13,'/images/23.webp',23),(14,'/images/24.jpg',24),(15,'/images/25.jpg',25),(16,'/images/26.jpg',26),(17,'/images/28.jpg',28),(18,'/images/28b.jpg',28),(19,'/images/28c.jpg',28),(20,'/images/29.jpg',29),(21,'/images/29b.jpg',29),(22,'/images/32.jpg',32),(23,'/images/32b.jpg',32),(24,'/images/32c.jpg',32),(25,'/images/33.jpg',33),(26,'/images/33b.jpg',33),(27,'/images/34.jpg',34),(28,'/images/35.jpg',35),(29,'/images/35b.jpg',35),(30,'/images/36.jpg',36),(31,'/images/37.jpg',37),(32,'/images/39.jpg',39),(33,'/images/40.jpg',40),(34,'/images/38.jpg',38),(35,'/images/41.jpeg',41),(36,'/images/42.jpg',42),(37,'/images/43.jpeg',43),(38,'/images/44.webp',44),(39,'/images/45.jpg',45),(40,'/images/46.jpg',46),(41,'/images/47.jpg',47),(42,'/images/48.jpg',48),(43,'/images/50.webp',50),(44,'/images/49.webp',49),(45,'/images/51.webp',51),(46,'/images/53.jfif',53),(47,'/images/52.webp',52),(48,'/images/54.jfif',54),(49,'/images/55.jfif',55),(50,'/images/56.jfif',56),(51,'/images/57.jfif',57),(52,'/images/58.jfif',58),(53,'/images/59.jfif',59),(54,'/images/60.jfif',60),(55,'/images/61.jfif',61),(56,'/images/62.jpg',62),(57,'/images/63.jpg',63),(58,'/images/64.webp',64),(59,'/images/65.webp',65),(60,'/images/66.webp',66),(63,'/images/67.webp',70),(65,'/images/72.webp',72),(66,'/images/74.webp',73),(67,'/images/75.webp',74);
/*!40000 ALTER TABLE `products_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_materials`
--

DROP TABLE IF EXISTS `products_materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_materials` (
  `product_id` int NOT NULL,
  `material_id` int NOT NULL,
  PRIMARY KEY (`product_id`,`material_id`),
  KEY `FK_materials_material_id__materials_id` (`material_id`),
  CONSTRAINT `FK_materials_material_id__materials_id` FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`),
  CONSTRAINT `FK_materials_product_id__products_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_materials`
--

LOCK TABLES `products_materials` WRITE;
/*!40000 ALTER TABLE `products_materials` DISABLE KEYS */;
INSERT INTO `products_materials` VALUES (1,200),(16,200),(18,200),(19,200),(20,200),(21,200),(22,200),(23,200),(24,200),(25,200),(26,200),(27,200),(28,200),(29,200),(30,200),(32,200),(34,200),(35,200),(36,200),(37,200),(38,200),(39,200),(40,200),(41,200),(42,200),(44,200),(45,200),(46,200),(47,200),(48,200),(49,200),(50,200),(53,200),(54,200),(58,200),(59,200),(60,200),(61,200),(64,200),(65,200),(66,200),(70,200),(74,200),(1,201),(16,201),(32,201),(34,201),(62,201),(63,201),(17,202),(22,202),(23,202),(28,202),(38,202),(39,202),(42,202),(52,202),(55,202),(56,202),(57,202),(58,202),(72,202),(17,203),(73,203),(21,204),(30,204),(47,204),(49,204),(52,204),(59,204),(64,204),(70,204),(33,206),(43,206),(50,206),(51,206),(56,206),(57,206),(72,206);
/*!40000 ALTER TABLE `products_materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'customer'),(2,'admin'),(3,'superuser');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statuses`
--

DROP TABLE IF EXISTS `statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statuses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statuses`
--

LOCK TABLES `statuses` WRITE;
/*!40000 ALTER TABLE `statuses` DISABLE KEYS */;
INSERT INTO `statuses` VALUES (1,'processing'),(2,'completed'),(3,'shipped'),(4,'pending'),(5,'denied'),(6,'refunded'),(7,'checkedout'),(8,'sent'),(9,'accepted'),(10,'closed');
/*!40000 ALTER TABLE `statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `style`
--

DROP TABLE IF EXISTS `style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `style` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `style`
--

LOCK TABLES `style` WRITE;
/*!40000 ALTER TABLE `style` DISABLE KEYS */;
INSERT INTO `style` VALUES (1,'Rustic'),(2,'Vintage'),(3,'Contemporary'),(4,'Bohemian'),(8,'Country');
/*!40000 ALTER TABLE `style` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `used_images`
--

DROP TABLE IF EXISTS `used_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `used_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `used_product_id` int NOT NULL,
  `image_path` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_images_used_product_id__used_products_id_idx` (`used_product_id`),
  CONSTRAINT `fk_images_used_product_id__used_products_id` FOREIGN KEY (`used_product_id`) REFERENCES `used_products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `used_images`
--

LOCK TABLES `used_images` WRITE;
/*!40000 ALTER TABLE `used_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `used_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `used_products`
--

DROP TABLE IF EXISTS `used_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `used_products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admin_id` int NOT NULL,
  `category` varchar(45) NOT NULL,
  `offer_ammount` decimal(10,3) NOT NULL,
  `offer_date` date NOT NULL,
  `buy_date` date DEFAULT NULL,
  `status_id` int NOT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_used_products_myuser_id__myusers_id_idx` (`admin_id`),
  KEY `FK_used_products_status_id__statuses_id_idx` (`status_id`),
  KEY `fk_used_products_customer_id__myusers_id` (`customer_id`),
  CONSTRAINT `fk_used_products_admin_id__myusers_id` FOREIGN KEY (`admin_id`) REFERENCES `myusers` (`id`),
  CONSTRAINT `fk_used_products_customer_id__myusers_id` FOREIGN KEY (`customer_id`) REFERENCES `myusers` (`id`),
  CONSTRAINT `FK_used_products_status_id__statuses_id` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `used_products`
--

LOCK TABLES `used_products` WRITE;
/*!40000 ALTER TABLE `used_products` DISABLE KEYS */;
INSERT INTO `used_products` VALUES (1,10,'chair',100.000,'2021-03-03','2021-03-05',9,21),(2,10,'closet',55.000,'2021-03-03','2021-03-05',9,21),(3,10,'chair',34.000,'2021-03-03','2021-03-05',9,21),(4,9,'coffee_table',25.500,'2021-02-05',NULL,5,22),(5,14,'desk_pc',20.000,'2021-03-01','2021-03-04',9,22),(6,15,'bookcase',200.000,'2021-03-02',NULL,4,23),(7,15,'chest',55.750,'2021-01-02',NULL,4,23),(8,19,'table',17.000,'2021-02-27','2021-03-05',9,23);
/*!40000 ALTER TABLE `used_products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-12 16:54:28
