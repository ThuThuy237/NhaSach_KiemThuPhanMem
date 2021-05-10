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
  PRIMARY KEY (`id`),
  KEY `cat_id` (`cat_id`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`cat_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Mắt Biếc','Nguyễn Nhật Ánh',72,50000,62500,'images/mat-biec.png',4),(2,'Đại Học Không Lạc Hướng ','Lý Thượng Long',109,70000,87500,'images/dai-hoc-khong-lac-huong.png',4),(3,'Thoát Khỏi Tâm Trí ','Steven C.Hayes & Spencer Smith',459,100000,125000,'images/thoat-khoi-tam-tri-va-buoc-vao-cuoc-song_113991_1.jpg',4),(4,'Từ điển Oxford Anh – Việt','Oxford',1001,200000,250000,'images/tudienoxford.png',3),(5,'Từ điển Tiếng Việt Hoàng Phê','Viện Ngôn ngữ học',400,350000,437500,'images/aba183a8895f54eb546fb97d7fbfbbdc.jpg',3),(6,'Tập  Sách Thay Đổi Lịch Sử','Nhiều tác giả ',301,200000,250000,'images/bc9cc397e3b6e2fded3710d09cfb0478.jpg',5),(7,'Mắt Thiên Phật','Tạ Tấn',411,75000,93750,'images/mat-thien-phat.jpg',1),(8,'Doraemon  Tập 10 ','Fujiko Fujio',1000,10000,12500,'images/001.png',2),(9,'Doraemon Tập 8','Fujiko Fujio',996,10000,12500,'images/8_68.jpg',2),(10,'Me Before You: A Novel','Jojo Moyes',990,200000,250000,'images/anh-bia-me-before-you-e1580920303980.jpg',1),(11,'The Alchemist','Paulo Coelho',500,350000,437500,'images/71aFt4+OTOL.jpg',1),(12,'The Fault in Our Stars','John Green',300,150000,187500,'images/81yAo5ElQlL.jpg',1),(13,'A Bend in the Road','Nicholas Sparks',500,100000,125000,'images/201610-bend-in-the-road.jpg',1),(14,'At First Sight','Nicholas Sparks',495,150000,187500,'images/201610-at-first-sight.jpg',1),(15,'Doraemon Truyện Dài (Tập 5)','Fujiko Fujio',1000,100000,125000,'images/c.jpg',2),(16,'Doraemon Truyện Dài (Tập 1)','Fujiko Fujio',1000,100000,125000,'images/1_163.jpg',2),(17,'Doraemon Truyện Dài (Tập 2)','Fujiko Fujio',1000,100000,125000,'images/10.u5762.d20171018.t104146.65072.jpg',2),(18,'Doraemon Truyện Dài (Tập 3)','Fujiko Fujio',1000,100000,125000,'images/3_160.jpg',2),(19,'Từ Điển Việt - Hàn','Khang Việt',280,200000,250000,'images/sach-tu-dien-viet-han.jpg',3),(20,'Từ Điển Toán học & Tin Học Anh - Việt','Ban từ điển KHKT',500,200000,250000,'images/1.jpg',3),(21,'Thiên Thần Nhỏ Của Tôi ','Nguyễn Nhật Ánh',750,20000,25000,'images/thien-than-nho-cua-toi.jpg',1),(23,'Trang Tí','unknow',300,20000,25000,'',2);
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
  `date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `total` float NOT NULL,
  `supplier_id` int DEFAULT NULL,
  `emm_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `supplier_id` (`supplier_id`),
  KEY `emm_id` (`emm_id`),
  CONSTRAINT `buy_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `buy_ibfk_2` FOREIGN KEY (`emm_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy`
--

LOCK TABLES `buy` WRITE;
/*!40000 ALTER TABLE `buy` DISABLE KEYS */;
INSERT INTO `buy` VALUES (1,'2020-12-16 18:14:46',1050000,1,1),(2,'2020-12-16 18:14:46',2400000,2,1),(7,'2021-05-09T21:14:00.597733500',2147480000,3,2),(8,'2021-05-09T21:15:29.932171600',2147480000,3,2),(9,'2021-05-09T21:17:33.987589900',50000000,2,2),(10,'2021-05-09T21:20:08.844038300',500000,2,2),(11,'2021-05-09T21:20:30.970102700',2147480000,4,2),(12,'2021-05-09T21:32:22.994220200',500000,4,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy_detail`
--

LOCK TABLES `buy_detail` WRITE;
/*!40000 ALTER TABLE `buy_detail` DISABLE KEYS */;
INSERT INTO `buy_detail` VALUES (1,2,1,15,70000),(2,1,2,18,50000),(3,3,2,15,100000),(8,3,10,2,250000),(9,4,11,200,50000000),(10,3,12,2,250000);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'romance',NULL),(2,'truyen tranh',NULL),(3,'dictionary',NULL),(4,'short story',NULL),(5,'thriller',NULL);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'myCustomer','male','1869-10-22','unknow','unkonw'),(2,'Khach 1','male','2000-10-11','200 Lê Thánh Tôn, P. Bến Thành, Quận 1, TP. HCM','0382367634'),(3,'Khach 2','female','2000-05-09','306 Nguyễn Trãi, P.8, Q.5','0372524817'),(4,'Khach 3','male','2001-11-23','412 Lê Văn Sỹ, P.14, Q.3','0338699707'),(5,'Khach 4','female','1985-05-30','433 Nơ Trang Long, P.13, Q. Bình Thạnh','0387742653'),(6,'Khach 5','male','1999-10-10','161A Nguyễn Văn Thủ, Q.1','0869578117'),(8,'thuthuy','Male','2000-09-25','Việt Nam','0941662403rhd');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` enum('CASHIER','STAFF','MANAGER') CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `hireDate` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Admin','MANAGER','1/1/2020'),(2,'ThuyThuy','MANAGER','11/11/2019'),(3,'mun hyun hwang','STAFF','10/10/2020'),(4,'Thu Thuy','STAFF','2/2/2018'),(5,'hihi','STAFF','1/1/2020'),(6,'haha','STAFF','5/6/2019');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `login_role` enum('ADMIN','USER') CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'admin','827ccb0eea8a706c4c34a16891f84e7b','admin@gmail.com','/src/main/resources/images/8025287921598811056-512.png','ADMIN'),(2,'thuy','3cf2b6b121d1726bc2cdc88c39e0b119','1851050140thuy@ou.edu.vn','/static/images/upload/15059724824858----nh-7.jpg','USER');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `total` float NOT NULL,
  `emm_id` int DEFAULT NULL,
  `cus_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cus_id` (`cus_id`),
  KEY `order_ibfk_1` (`emm_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`emm_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`cus_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'2021-05-09T21:32:34.336749300',9000000,2,4);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `book_id` (`book_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,4,1,6,1500000);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regulations`
--

DROP TABLE IF EXISTS `regulations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regulations` (
  `id` int NOT NULL,
  `import_min` int DEFAULT NULL,
  `inventory_max_when_import` int DEFAULT NULL,
  `inventory_min_when_sell` int DEFAULT NULL,
  `debt_max` decimal(10,0) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `user_role` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `regulations_chk_1` CHECK ((`active` in (0,1)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regulations`
--

LOCK TABLES `regulations` WRITE;
/*!40000 ALTER TABLE `regulations` DISABLE KEYS */;
INSERT INTO `regulations` VALUES (1,0,1000,20,2000000,1,1);
/*!40000 ALTER TABLE `regulations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Công Ty Cổ Phần Phát Hành Sách Tp. HCM','60-62 Lê Lợi, P. Bến Nghé, Q. 1, Tp. Hồ Chí Minh','(028) 38225798'),(2,'Trí Tuệ - Công Ty Cổ Phần Sách & Thiết Bị Giáo Dục Trí Tuệ','187 Giảng Võ, Q. Đống Đa, Hà Nội','(024) 38515567'),(3,' Công Ty TNHH Văn Hóa Việt Long','14/35, Đào Duy Anh, P.9, Q. Phú Nhuận, Tp. Hồ Chí Minh','(028) 38452708'),(4,' Nhà Sách Trực Tuyến BOOKBUY.VN','147 Pasteur, P. 6, Q. 3, Tp. Hồ Chí Minh','(028) 38207153'),(5,'Nobita.vn - Nhà Sách Trên Mạng','74/7 Nguyễn Cừ, Thảo Điền, Q. 2, Tp. Hồ Chí Minh (TPHCM)','(028) 0974941097'),(6,'FAHASHA','387-389 Hai Bà Trưng Quận 3 TP HCM','1900636467');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-10 12:25:02
