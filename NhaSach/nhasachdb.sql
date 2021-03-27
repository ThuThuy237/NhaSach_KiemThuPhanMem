-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: nhasach
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `inventory` int NOT NULL,
  `import_price` float NOT NULL,
  `price` float DEFAULT NULL,
  `image` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `cat_id` int NOT NULL,
  `publisher_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cat_id` (`cat_id`),
  KEY `publisher_id` (`publisher_id`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`cat_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `books_ibfk_2` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Mắt Biếc','Nguyễn Nhật Ánh',72,50000,62500,'images/mat-biec.png',4,3),(2,'Đại Học Không Lạc Hướng ','Lý Thượng Long',109,70000,87500,'images/dai-hoc-khong-lac-huong.png',4,3),(3,'Thoát Khỏi Tâm Trí ','Steven C.Hayes & Spencer Smith',459,100000,125000,'images/thoat-khoi-tam-tri-va-buoc-vao-cuoc-song_113991_1.jpg',4,1),(4,'Từ điển Oxford Anh – Việt','Oxford',1001,200000,250000,'images/tudienoxford.png',3,4),(5,'Từ điển Tiếng Việt Hoàng Phê','Viện Ngôn ngữ học',400,350000,437500,'images/aba183a8895f54eb546fb97d7fbfbbdc.jpg',3,4),(6,'Tập  Sách Thay Đổi Lịch Sử','Nhiều tác giả ',301,200000,250000,'images/bc9cc397e3b6e2fded3710d09cfb0478.jpg',5,3),(7,'Mắt Thiên Phật','Tạ Tấn',411,75000,93750,'images/mat-thien-phat.jpg',1,1),(8,'Doraemon  Tập 10 ','Fujiko Fujio',1000,10000,12500,'images/001.png',2,2),(9,'Doraemon Tập 8','Fujiko Fujio',996,10000,12500,'images/8_68.jpg',2,2),(10,'Me Before You: A Novel','Jojo Moyes',990,200000,250000,'images/anh-bia-me-before-you-e1580920303980.jpg',1,1),(11,'The Alchemist','Paulo Coelho',500,350000,437500,'images/71aFt4+OTOL.jpg',1,1),(12,'The Fault in Our Stars','John Green',300,150000,187500,'images/81yAo5ElQlL.jpg',1,3),(13,'A Bend in the Road','Nicholas Sparks',500,100000,125000,'images/201610-bend-in-the-road.jpg',1,3),(14,'At First Sight','Nicholas Sparks',495,150000,187500,'images/201610-at-first-sight.jpg',1,2),(15,'Doraemon Truyện Dài (Tập 5)','Fujiko Fujio',1000,100000,125000,'images/c.jpg',2,2),(16,'Doraemon Truyện Dài (Tập 1)','Fujiko Fujio',1000,100000,125000,'images/1_163.jpg',2,2),(17,'Doraemon Truyện Dài (Tập 2)','Fujiko Fujio',1000,100000,125000,'images/10.u5762.d20171018.t104146.65072.jpg',2,2),(18,'Doraemon Truyện Dài (Tập 3)','Fujiko Fujio',1000,100000,125000,'images/3_160.jpg',2,4),(19,'Từ Điển Việt - Hàn','Khang Việt',280,200000,250000,'images/sach-tu-dien-viet-han.jpg',3,4),(20,'Từ Điển Toán học & Tin Học Anh - Việt','Ban từ điển KHKT',500,200000,250000,'images/1.jpg',3,4),(21,'Thiên Thần Nhỏ Của Tôi ','Nguyễn Nhật Ánh',750,20000,25000,'images/thien-than-nho-cua-toi.jpg',1,4);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buy`
--

DROP TABLE IF EXISTS `buy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buy` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `total` float NOT NULL,
  `supplier_id` int DEFAULT NULL,
  `emm_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `supplier_id` (`supplier_id`),
  KEY `emm_id` (`emm_id`),
  CONSTRAINT `buy_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `buy_ibfk_2` FOREIGN KEY (`emm_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy`
--

LOCK TABLES `buy` WRITE;
/*!40000 ALTER TABLE `buy` DISABLE KEYS */;
INSERT INTO `buy` VALUES (1,'2020-12-16 18:14:46',1050000,1,1),(2,'2020-12-16 18:14:46',2400000,2,1);
/*!40000 ALTER TABLE `buy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buy_detail`
--

DROP TABLE IF EXISTS `buy_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buy_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int DEFAULT NULL,
  `buy_id` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `book_id` (`book_id`),
  KEY `buy_id` (`buy_id`),
  CONSTRAINT `buy_detail_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `buy_detail_ibfk_2` FOREIGN KEY (`buy_id`) REFERENCES `buy` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy_detail`
--

LOCK TABLES `buy_detail` WRITE;
/*!40000 ALTER TABLE `buy_detail` DISABLE KEYS */;
INSERT INTO `buy_detail` VALUES (1,2,1,15,70000),(2,1,2,18,50000),(3,3,2,15,100000);
/*!40000 ALTER TABLE `buy_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'romance',NULL),(2,'comic',NULL),(3,'dictionary',NULL),(4,'short story',NULL),(5,'thriller',NULL);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collect_debts`
--

DROP TABLE IF EXISTS `collect_debts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collect_debts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `total` float NOT NULL,
  `cus_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cus_id` (`cus_id`),
  CONSTRAINT `collect_debts_ibfk_1` FOREIGN KEY (`cus_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collect_debts`
--

LOCK TABLES `collect_debts` WRITE;
/*!40000 ALTER TABLE `collect_debts` DISABLE KEYS */;
INSERT INTO `collect_debts` VALUES (1,'2020-12-16 13:07:13',1000,2),(2,'2020-12-16 13:20:57',1000,2),(3,'2020-12-16 13:20:57',5000000,1),(4,'2020-12-17 00:56:39',50000,1);
/*!40000 ALTER TABLE `collect_debts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'newCustomer','unknow','unknow','unknow','unkonw'),(2,'Khach 1','male','11/10/2000','200 Lê Thánh Tôn, P. Bến Thành, Quận 1, TP. HCM','0382367634'),(3,'Khach 2','female','15/09/2000','306 Nguyễn Trãi, P.8, Q.5','0372524817'),(4,'Khach 3','male','5/09/2001','412 Lê Văn Sỹ, P.14, Q.3','0338699707'),(5,'Khach 4','female','02/03/1985','433 Nơ Trang Long, P.13, Q. Bình Thạnh','0387742653'),(6,'Khach 5','male','12/12/1996','161A Nguyễn Văn Thủ, Q.1','0869578117');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-27 15:28:45
