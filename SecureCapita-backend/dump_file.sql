-- MySQL dump 10.13  Distrib 8.0.37, for Win64 (x86_64)
--
-- Host: localhost    Database: securecapita
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accountverifications`
--

DROP TABLE IF EXISTS `accountverifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accountverifications` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_AccountVerifications_User_Id` (`user_id`),
  UNIQUE KEY `UQ_AccountVerifications_Url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountverifications`
--

LOCK TABLES `accountverifications` WRITE;
/*!40000 ALTER TABLE `accountverifications` DISABLE KEYS */;
INSERT INTO `accountverifications` VALUES (1,22,'http://localhost:8080/user/verify/ACCOUNT/26b7edb1-441b-4e46-bb34-6abc19d66301'),(2,23,'http://localhost:8080/user/verify/ACCOUNT/da3cb65c-7ecc-45d9-9e7d-48ede84d7e60'),(3,24,'http://localhost:8080/user/verify/ACCOUNT/b90d8afe-db74-4bb2-9442-ef8264d9c1ff'),(4,25,'http://localhost:8080/user/verify/account/f39c02d0-9862-42a3-941e-e34376a50711'),(5,26,'http://localhost:8080/user/verify/account/6ee19868-efc7-47a7-ba7d-31cb06b0f0ab'),(6,27,'http://localhost:8080/user/verify/account/8cfd86ce-9608-4045-ad32-337e6fab9fda'),(7,28,'http://localhost:8080/user/verify/account/6c897bba-080c-4ea8-9311-7c23a6f69c1c'),(8,29,'http://localhost:8080/user/verify/account/3bce1300-f93b-4b29-ba1e-5a906408905d'),(9,30,'http://localhost:8080/user/verify/account/53a133c5-8017-4c92-8d1b-341131c70fc1'),(10,31,'http://localhost:8080/user/verify/account/66106273-0b2f-4489-b15e-54f519c5db1c'),(11,32,'http://localhost:8080/user/verify/account/2ad4ec6b-e236-4a59-b69b-8c5232b6141a');
/*!40000 ALTER TABLE `accountverifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'sg palaya','2024-08-23 16:06:30.097000','nari@gmail.com','https://plus.unsplash.com/premium_photo-1664536392896-cd1743f9c02c?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cGVyc29ufGVufDB8fDB8fHww','Naresh Chavan','123456789','ACTIVE','INDIVIDUAL'),(102,'Room 478','2024-08-25 11:39:01.000000','jrenshaw0@blogtalkradio.com','http://dummyimage.com/195x100.png/cc0000/ffffff','Julee','933-527-1143',NULL,NULL),(103,'Apt 205','2024-08-25 11:39:01.000000','hhemms1@deliciousdays.com','http://dummyimage.com/119x100.png/cc0000/ffffff','Henrik','348-341-2612',NULL,NULL),(104,'Suite 30','2024-08-25 11:39:01.000000','aburniston2@networkadvertising.org','http://dummyimage.com/145x100.png/5fa2dd/ffffff','Audre','892-311-0012',NULL,NULL),(105,'Apt 158','2024-08-25 11:39:01.000000','mhugo3@bloomberg.com','http://dummyimage.com/249x100.png/cc0000/ffffff','Miguelita','660-537-7317',NULL,NULL),(106,'Apt 1759','2024-08-25 11:39:01.000000','caires4@parallels.com','http://dummyimage.com/136x100.png/dddddd/000000','Caro','583-881-0772',NULL,NULL),(107,'PO Box 24271','2024-08-25 11:39:01.000000','dfortun5@drupal.org','http://dummyimage.com/157x100.png/ff4444/ffffff','Dwight','687-124-6702',NULL,NULL),(108,'PO Box 59175','2024-08-25 11:39:01.000000','kasel6@ask.com','http://dummyimage.com/143x100.png/cc0000/ffffff','Kippar','906-644-4761',NULL,NULL),(109,'PO Box 66260','2024-08-25 11:39:01.000000','mshambrooke7@pbs.org','http://dummyimage.com/147x100.png/dddddd/000000','Marice','818-814-7718',NULL,NULL),(110,'Room 1113','2024-08-25 11:39:01.000000','amcfall8@xrea.com','http://dummyimage.com/139x100.png/5fa2dd/ffffff','Ashbey','520-488-5382',NULL,NULL),(111,'3rd Floor','2024-08-25 11:39:01.000000','agemlbett9@shutterfly.com','http://dummyimage.com/247x100.png/5fa2dd/ffffff','Anissa','959-824-9535',NULL,NULL),(112,'Apt 1249','2024-08-25 11:39:01.000000','vkordaa@state.tx.us','http://dummyimage.com/242x100.png/dddddd/000000','Vivian','928-204-9716',NULL,NULL),(113,'Apt 1745','2024-08-25 11:39:01.000000','wcrommettb@ibm.com','http://dummyimage.com/162x100.png/cc0000/ffffff','Wendi','630-998-1616',NULL,NULL),(114,'Room 362','2024-08-25 11:39:01.000000','clearoydc@census.gov','http://dummyimage.com/191x100.png/ff4444/ffffff','Carmon','826-131-3829',NULL,NULL),(115,'Room 1645','2024-08-25 11:39:01.000000','kaaronsd@devhub.com','http://dummyimage.com/206x100.png/cc0000/ffffff','Kirsti','919-604-8593',NULL,NULL),(116,'17th Floor','2024-08-25 11:39:01.000000','jstanese@cnn.com','http://dummyimage.com/155x100.png/ff4444/ffffff','Jenelle','290-142-1138',NULL,NULL),(117,'6th Floor','2024-08-25 11:39:01.000000','lsnookf@amazon.com','http://dummyimage.com/181x100.png/5fa2dd/ffffff','Lenee','582-884-0807',NULL,NULL),(118,'PO Box 27576','2024-08-25 11:39:01.000000','ssaggg@networkadvertising.org','http://dummyimage.com/211x100.png/ff4444/ffffff','Staffard','511-763-1143',NULL,NULL),(119,'13th Floor','2024-08-25 11:39:01.000000','fslyneh@ca.gov','http://dummyimage.com/161x100.png/dddddd/000000','Freeland','478-316-9397',NULL,NULL),(120,'14th Floor','2024-08-25 11:39:01.000000','cmartti@paginegialle.it','http://dummyimage.com/243x100.png/dddddd/000000','Caria','473-674-9968',NULL,NULL),(121,'Suite 7','2024-08-25 11:39:01.000000','btippettsj@japanpost.jp','http://dummyimage.com/194x100.png/5fa2dd/ffffff','Base','209-387-8310',NULL,NULL),(122,'Room 689','2024-08-25 11:39:01.000000','kjindrakk@bloomberg.com','http://dummyimage.com/125x100.png/dddddd/000000','Krystal','225-339-2214',NULL,NULL),(123,'12th Floor','2024-08-25 11:39:01.000000','mjerransl@nationalgeographic.com','http://dummyimage.com/212x100.png/cc0000/ffffff','Marco','527-397-8306',NULL,NULL),(124,'PO Box 26926','2024-08-25 11:39:01.000000','csutterbym@ted.com','http://dummyimage.com/219x100.png/ff4444/ffffff','Caddric','172-946-6095',NULL,NULL),(125,'3rd Floor','2024-08-25 11:39:01.000000','hellikern@go.com','http://dummyimage.com/211x100.png/dddddd/000000','Hugibert','434-525-0553',NULL,NULL),(126,'16th Floor','2024-08-25 11:39:01.000000','trolando@bbc.co.uk','http://dummyimage.com/161x100.png/5fa2dd/ffffff','Trisha','183-701-1107',NULL,NULL),(127,'2nd Floor','2024-08-25 11:39:01.000000','ralbrop@state.gov','http://dummyimage.com/168x100.png/cc0000/ffffff','Reade','800-309-4928',NULL,NULL),(128,'16th Floor','2024-08-25 11:39:01.000000','bmaccallq@squarespace.com','http://dummyimage.com/101x100.png/ff4444/ffffff','Barny','497-874-4910',NULL,NULL),(129,'Apt 1575','2024-08-25 11:39:01.000000','rdewarr@youku.com','http://dummyimage.com/138x100.png/ff4444/ffffff','Roscoe','389-341-7351',NULL,NULL),(130,'4th Floor','2024-08-25 11:39:01.000000','jkingzeths@hc360.com','http://dummyimage.com/194x100.png/dddddd/000000','Juliana','740-533-2479',NULL,NULL),(131,'Apt 588','2024-08-25 11:39:01.000000','hitzcovicht@cornell.edu','http://dummyimage.com/186x100.png/ff4444/ffffff','Harlie','524-660-0498',NULL,NULL),(132,'Apt 1022','2024-08-25 11:39:01.000000','udatonu@netscape.com','http://dummyimage.com/213x100.png/dddddd/000000','Urbano','959-633-5730',NULL,NULL),(133,'Suite 32','2024-08-25 11:39:01.000000','mshallov@ed.gov','http://dummyimage.com/153x100.png/5fa2dd/ffffff','Mada','790-157-3070',NULL,NULL),(134,'2nd Floor','2024-08-25 11:39:01.000000','hzavattariw@house.gov','http://dummyimage.com/154x100.png/cc0000/ffffff','Horton','560-477-7430',NULL,NULL),(135,'PO Box 94156','2024-08-25 11:39:01.000000','cshinglesx@samsung.com','http://dummyimage.com/215x100.png/ff4444/ffffff','Cleve','644-253-5842',NULL,NULL),(136,'Suite 34','2024-08-25 11:39:01.000000','tallcotty@webeden.co.uk','http://dummyimage.com/174x100.png/dddddd/000000','Tobey','493-190-7982',NULL,NULL),(137,'Room 1306','2024-08-25 11:39:01.000000','tbrucez@hud.gov','http://dummyimage.com/237x100.png/dddddd/000000','Therine','968-891-8090',NULL,NULL),(138,'PO Box 78593','2024-08-25 11:39:01.000000','lblair10@joomla.org','http://dummyimage.com/158x100.png/dddddd/000000','Leonie','967-708-2078',NULL,NULL),(139,'18th Floor','2024-08-25 11:39:01.000000','rfarrall11@dion.ne.jp','http://dummyimage.com/168x100.png/cc0000/ffffff','Rancell','969-681-8881',NULL,NULL),(140,'Apt 1771','2024-08-25 11:39:01.000000','tbrinson12@posterous.com','http://dummyimage.com/160x100.png/ff4444/ffffff','Tanner','939-618-8577',NULL,NULL),(141,'Apt 775','2024-08-25 11:39:01.000000','tswancott13@google.pl','http://dummyimage.com/245x100.png/ff4444/ffffff','Town','899-211-8152',NULL,NULL),(142,'Suite 21','2024-08-25 11:39:01.000000','amonget14@admin.ch','http://dummyimage.com/229x100.png/ff4444/ffffff','Augusto','814-399-5050',NULL,NULL),(143,'Room 1075','2024-08-25 11:39:01.000000','mmcdavid15@hostgator.com','http://dummyimage.com/132x100.png/dddddd/000000','Marlene','387-282-0161',NULL,NULL),(144,'Suite 18','2024-08-25 11:39:01.000000','nvaggs16@cbsnews.com','http://dummyimage.com/173x100.png/cc0000/ffffff','Norah','522-531-9681',NULL,NULL),(145,'20th Floor','2024-08-25 11:39:01.000000','nparidge17@theatlantic.com','http://dummyimage.com/175x100.png/5fa2dd/ffffff','Noel','489-747-5057',NULL,NULL),(146,'5th Floor','2024-08-25 11:39:01.000000','hbarkhouse18@csmonitor.com','http://dummyimage.com/116x100.png/5fa2dd/ffffff','Hermie','312-955-8126',NULL,NULL),(147,'5th Floor','2024-08-25 11:39:01.000000','rgorvette19@seesaa.net','http://dummyimage.com/100x100.png/ff4444/ffffff','Ruperto','115-353-4315',NULL,NULL),(148,'Room 1359','2024-08-25 11:39:01.000000','azuppa1a@jiathis.com','http://dummyimage.com/152x100.png/cc0000/ffffff','Adah','319-797-9673',NULL,NULL),(149,'Apt 1768','2024-08-25 11:39:01.000000','fheadford1b@barnesandnoble.com','http://dummyimage.com/210x100.png/dddddd/000000','Falito','392-672-7874',NULL,NULL),(150,'6th Floor','2024-08-25 11:39:01.000000','jathersmith1c@comcast.net','http://dummyimage.com/245x100.png/5fa2dd/ffffff','Janeen','230-424-4619',NULL,NULL),(151,'19th Floor','2024-08-25 11:39:01.000000','hfowgies1d@google.com.hk','http://dummyimage.com/165x100.png/5fa2dd/ffffff','Harcourt','761-151-8591',NULL,NULL),(152,'10th Floor','2024-08-25 11:39:01.000000','smargach1e@tumblr.com','http://dummyimage.com/170x100.png/cc0000/ffffff','Sasha','453-447-7637',NULL,NULL),(153,'18th Floor','2024-08-25 11:39:01.000000','nbirden1f@pagesperso-orange.fr','http://dummyimage.com/234x100.png/cc0000/ffffff','Neila','580-733-3862',NULL,NULL),(154,'PO Box 76240','2024-08-25 11:39:01.000000','klambillion1g@imageshack.us','http://dummyimage.com/148x100.png/cc0000/ffffff','Katheryn','338-826-0366',NULL,NULL),(155,'Apt 758','2024-08-25 11:39:01.000000','cfardy1h@php.net','http://dummyimage.com/131x100.png/cc0000/ffffff','Carny','891-748-4492',NULL,NULL),(156,'13th Floor','2024-08-25 11:39:01.000000','gfirminger1i@desdev.cn','http://dummyimage.com/119x100.png/cc0000/ffffff','Gustaf','286-408-9022',NULL,NULL),(157,'14th Floor','2024-08-25 11:39:01.000000','mharding1j@examiner.com','http://dummyimage.com/215x100.png/5fa2dd/ffffff','Morganne','555-996-0400',NULL,NULL),(158,'Apt 1093','2024-08-25 11:39:01.000000','wrenzini1k@cbsnews.com','http://dummyimage.com/126x100.png/5fa2dd/ffffff','Willyt','764-418-6510',NULL,NULL),(159,'Room 1610','2024-08-25 11:39:01.000000','lbridgens1l@imageshack.us','http://dummyimage.com/244x100.png/5fa2dd/ffffff','Lezlie','555-128-7713',NULL,NULL),(160,'PO Box 28039','2024-08-25 11:39:01.000000','uwoolmore1m@alibaba.com','http://dummyimage.com/224x100.png/cc0000/ffffff','Urban','164-899-9098',NULL,NULL),(161,'PO Box 19287','2024-08-25 11:39:01.000000','mbroxton1n@huffingtonpost.com','http://dummyimage.com/212x100.png/cc0000/ffffff','Meier','668-867-6601',NULL,NULL),(162,'8th Floor','2024-08-25 11:39:01.000000','madam1o@nsw.gov.au','http://dummyimage.com/169x100.png/ff4444/ffffff','Mara','549-922-4957',NULL,NULL),(163,'Room 1443','2024-08-25 11:39:01.000000','mhartgill1p@dropbox.com','http://dummyimage.com/159x100.png/5fa2dd/ffffff','Magdalen','153-971-1802',NULL,NULL),(164,'17th Floor','2024-08-25 11:39:01.000000','xmarley1q@cargocollective.com','http://dummyimage.com/129x100.png/cc0000/ffffff','Xenos','746-160-9597',NULL,NULL),(165,'Suite 11','2024-08-25 11:39:01.000000','mclarey1r@deviantart.com','http://dummyimage.com/226x100.png/dddddd/000000','Melamie','907-129-2980',NULL,NULL),(166,'6th Floor','2024-08-25 11:39:01.000000','cmacknocker1s@ifeng.com','http://dummyimage.com/128x100.png/5fa2dd/ffffff','Colette','536-234-9785',NULL,NULL),(167,'Apt 860','2024-08-25 11:39:01.000000','glethieulier1t@bigcartel.com','http://dummyimage.com/137x100.png/5fa2dd/ffffff','Gavrielle','962-826-5542',NULL,NULL),(168,'PO Box 77929','2024-08-25 11:39:01.000000','hchazelle1u@redcross.org','http://dummyimage.com/128x100.png/dddddd/000000','Hoebart','516-252-1209',NULL,NULL),(169,'Room 511','2024-08-25 11:39:01.000000','rbettison1v@behance.net','http://dummyimage.com/225x100.png/ff4444/ffffff','Robinet','573-453-3542',NULL,NULL),(170,'Suite 16','2024-08-25 11:39:01.000000','rcranefield1w@dion.ne.jp','http://dummyimage.com/112x100.png/cc0000/ffffff','Ross','372-160-7062',NULL,NULL),(171,'Room 1968','2024-08-25 11:39:01.000000','akislingbury1x@psu.edu','http://dummyimage.com/244x100.png/dddddd/000000','Armand','924-232-6537',NULL,NULL),(172,'Room 273','2024-08-25 11:39:01.000000','furch1y@wisc.edu','http://dummyimage.com/221x100.png/cc0000/ffffff','Florina','170-337-8955',NULL,NULL),(173,'Suite 21','2024-08-25 11:39:01.000000','fgaitley1z@qq.com','http://dummyimage.com/163x100.png/cc0000/ffffff','Francisca','997-342-1072',NULL,NULL),(174,'3rd Floor','2024-08-25 11:39:01.000000','gfumagallo20@cpanel.net','http://dummyimage.com/118x100.png/cc0000/ffffff','Garreth','678-844-2798',NULL,NULL),(175,'Room 193','2024-08-25 11:39:01.000000','epankhurst21@wufoo.com','http://dummyimage.com/100x100.png/cc0000/ffffff','Eleni','440-973-6882',NULL,NULL),(176,'Room 288','2024-08-25 11:39:01.000000','akeson22@pcworld.com','http://dummyimage.com/240x100.png/5fa2dd/ffffff','Astrix','206-470-7524',NULL,NULL),(177,'Apt 95','2024-08-25 11:39:01.000000','adoblin23@mayoclinic.com','http://dummyimage.com/240x100.png/5fa2dd/ffffff','Alexandr','953-407-4800',NULL,NULL),(178,'Room 648','2024-08-25 11:39:01.000000','rruddock24@bbb.org','http://dummyimage.com/189x100.png/5fa2dd/ffffff','Rosetta','279-292-2940',NULL,NULL),(179,'7th Floor','2024-08-25 11:39:01.000000','hgrewer25@ycombinator.com','http://dummyimage.com/114x100.png/5fa2dd/ffffff','Harland','137-478-5290',NULL,NULL),(180,'PO Box 44483','2024-08-25 11:39:01.000000','tpavett26@kickstarter.com','http://dummyimage.com/134x100.png/cc0000/ffffff','Thor','526-312-2921',NULL,NULL),(181,'8th Floor','2024-08-25 11:39:01.000000','hcaslett27@boston.com','http://dummyimage.com/227x100.png/5fa2dd/ffffff','Horacio','321-138-7883',NULL,NULL),(182,'PO Box 46229','2024-08-25 11:39:01.000000','cbuckthought28@dailymail.co.uk','http://dummyimage.com/162x100.png/cc0000/ffffff','Claudetta','198-710-0026',NULL,NULL),(183,'Room 899','2024-08-25 11:39:01.000000','fgandar29@nasa.gov','http://dummyimage.com/185x100.png/5fa2dd/ffffff','Farrand','360-426-7164',NULL,NULL),(184,'Room 1662','2024-08-25 11:39:01.000000','zlarvent2a@mapy.cz','http://dummyimage.com/100x100.png/dddddd/000000','Zsa zsa','240-285-2156',NULL,NULL),(185,'10th Floor','2024-08-25 11:39:01.000000','khaysar2b@prweb.com','http://dummyimage.com/236x100.png/cc0000/ffffff','Karel','932-405-7838',NULL,NULL),(186,'PO Box 93554','2024-08-25 11:39:01.000000','mcarmont2c@com.com','http://dummyimage.com/243x100.png/cc0000/ffffff','Meghan','431-302-9054',NULL,NULL),(187,'Apt 1047','2024-08-25 11:39:01.000000','kknappitt2d@latimes.com','http://dummyimage.com/141x100.png/cc0000/ffffff','Kristoforo','413-432-7598',NULL,NULL),(188,'3rd Floor','2024-08-25 11:39:01.000000','kajam2e@va.gov','http://dummyimage.com/226x100.png/ff4444/ffffff','Kip','561-131-7624',NULL,NULL),(189,'Apt 134','2024-08-25 11:39:01.000000','tdepka2f@craigslist.org','http://dummyimage.com/131x100.png/dddddd/000000','Tori','962-609-2061',NULL,NULL),(190,'Suite 92','2024-08-25 11:39:01.000000','aossipenko2g@list-manage.com','http://dummyimage.com/106x100.png/ff4444/ffffff','Alene','999-226-7271',NULL,NULL),(191,'Suite 96','2024-08-25 11:39:01.000000','rarch2h@posterous.com','http://dummyimage.com/222x100.png/5fa2dd/ffffff','Rolando','990-217-6831',NULL,NULL),(192,'Apt 1917','2024-08-25 11:39:01.000000','aponton2i@behance.net','http://dummyimage.com/142x100.png/5fa2dd/ffffff','Amalee','790-876-2620',NULL,NULL),(193,'PO Box 16469','2024-08-25 11:39:01.000000','dbickersteth2j@imageshack.us','http://dummyimage.com/187x100.png/cc0000/ffffff','Dione','336-637-2086',NULL,NULL),(194,'PO Box 46821','2024-08-25 11:39:01.000000','gcadden2k@de.vu','http://dummyimage.com/166x100.png/dddddd/000000','Gleda','234-456-9875',NULL,NULL),(195,'Suite 49','2024-08-25 11:39:01.000000','mgermain2l@infoseek.co.jp','http://dummyimage.com/130x100.png/dddddd/000000','Marcel','220-269-7299',NULL,NULL),(196,'6th Floor','2024-08-25 11:39:01.000000','dgonnin2m@webeden.co.uk','http://dummyimage.com/185x100.png/cc0000/ffffff','Derrek','816-485-9282',NULL,NULL),(197,'Room 1768','2024-08-25 11:39:01.000000','thannah2n@intel.com','http://dummyimage.com/216x100.png/5fa2dd/ffffff','Teodora','673-454-0365',NULL,NULL),(198,'PO Box 60407','2024-08-25 11:39:01.000000','hkleinzweig2o@1688.com','http://dummyimage.com/124x100.png/cc0000/ffffff','Hamlen','570-694-8732',NULL,NULL),(199,'Room 1885','2024-08-25 11:39:01.000000','scopsey2p@usgs.gov','http://dummyimage.com/238x100.png/5fa2dd/ffffff','Sky','683-251-2953',NULL,NULL),(200,'Apt 1989','2024-08-25 11:39:01.000000','sdow2q@columbia.edu','http://dummyimage.com/104x100.png/dddddd/000000','Sigfrid','389-693-1861',NULL,NULL),(201,'20th Floor','2024-08-25 11:39:01.000000','swickling2r@simplemachines.org','http://dummyimage.com/114x100.png/ff4444/ffffff','Susanetta','711-619-4832','INACTIVE','INDIVIDUAL'),(202,'Google office','2024-08-25 11:39:01.000000','Pichai@gmail.com','https://images.unsplash.com/photo-1522075469751-3a6694fb2f61?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cHJvZmlsZXxlbnwwfHwwfHx8MA%3D%3D','Sundar','5788466985','ACTIVE','INDIVIDUAL');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_Events_Type` (`type`),
  CONSTRAINT `events_chk_1` CHECK ((`type` in (_utf8mb4'LOGIN_ATTEMPT',_utf8mb4'LOGIN_ATTEMPT_FAILURE',_utf8mb4'LOGIN_ATTEMPT_SUCCESS',_utf8mb4'PROFILE_UPDATE',_utf8mb4'PROFILE_PICTURE_UPDATE',_utf8mb4'ROLE_UPDATE',_utf8mb4'ACCOUNT_SETTINGS_UPDATE',_utf8mb4'PASSWORD_UPDATE',_utf8mb4'MFA_UPDATE')))
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (1,'LOGIN_ATTEMPT','User attempted to log in'),(2,'LOGIN_ATTEMPT_FAILURE','User failed to log in'),(3,'LOGIN_ATTEMPT_SUCCESS','User successfully logged in'),(4,'PROFILE_UPDATE','User updated their profile'),(5,'PROFILE_PICTURE_UPDATE','User updated their profile picture'),(6,'ROLE_UPDATE','User role updated'),(7,'ACCOUNT_SETTINGS_UPDATE','User updated account settings'),(8,'PASSWORD_UPDATE','User updated their password'),(9,'MFA_UPDATE','User updated multi-factor authentication settings');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `invoice_number` varchar(255) DEFAULT NULL,
  `services` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total` double NOT NULL,
  `customer_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgex6n9u81uvaprblppjsndxth` (`customer_id`),
  CONSTRAINT `FKgex6n9u81uvaprblppjsndxth` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,'2024-08-07 05:30:00.000000','ZPR4BGV5','Development','OVERDUE',50000,157);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resetpasswordverifications`
--

DROP TABLE IF EXISTS `resetpasswordverifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resetpasswordverifications` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `expiration_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_ResetPasswordVerifications_User_Id` (`user_id`),
  UNIQUE KEY `UQ_ResetPasswordVerifications_Url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resetpasswordverifications`
--

LOCK TABLES `resetpasswordverifications` WRITE;
/*!40000 ALTER TABLE `resetpasswordverifications` DISABLE KEYS */;
INSERT INTO `resetpasswordverifications` VALUES (16,28,'http://localhost:8080/user/verify/password/baa9ed50-6a81-4299-b23a-a82f9754e380','2024-08-19 22:01:48'),(17,30,'http://localhost:8080/user/verify/password/600d4452-33f6-406c-9b9b-5a5eed56eed9','2024-08-26 12:14:24'),(18,32,'http://localhost:8080/user/verify/password/3dd810be-61d5-41c9-a7c7-3e6776cae27b','2024-08-26 03:28:29');
/*!40000 ALTER TABLE `resetpasswordverifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `permissions` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_Roles_Name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (17,'ROLE_USER','READ: USER, READ: CUSTOMER'),(18,'ROLE MANAGER','READ: USER, READ: CUSTOMER, UPDATE: USER, UPDATE: CUSTOMER'),(19,'ROLE_ADMIN','READ: USER, READ: CUSTOMER, CREATE: USER, CREATE: CUSTOMER, UPDATE: USER, UPDATE: CUSTOMER'),(20,'ROLE_SYSADMIN','READ: USER, READ: CUSTOMER, CREATE: USER, CREATE: CUSTOMER, UPDATE: USER, UPDATE: CUSTOMER, DELETE: USER, DELETE:┬áCUSTOMER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `twofactorverifications`
--

DROP TABLE IF EXISTS `twofactorverifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `twofactorverifications` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `code` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `expiration_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_TwoFactorVerifications_User_Id` (`user_id`),
  UNIQUE KEY `UQ_TwoFactorVerifications_Code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `twofactorverifications`
--

LOCK TABLES `twofactorverifications` WRITE;
/*!40000 ALTER TABLE `twofactorverifications` DISABLE KEYS */;
INSERT INTO `twofactorverifications` VALUES (18,24,'YWNQOXOS','2024-08-21 12:04:07');
/*!40000 ALTER TABLE `twofactorverifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userevents`
--

DROP TABLE IF EXISTS `userevents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userevents` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `event_id` bigint unsigned NOT NULL,
  `device` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ip_address` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `event_id` (`event_id`),
  CONSTRAINT `userevents_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userevents`
--

LOCK TABLES `userevents` WRITE;
/*!40000 ALTER TABLE `userevents` DISABLE KEYS */;
INSERT INTO `userevents` VALUES (5,25,1,'PostmanRuntime - Postman Runtime','0:0:0:0:0:0:0:1','2024-08-22 18:26:30'),(6,25,3,'PostmanRuntime - Postman Runtime','0:0:0:0:0:0:0:1','2024-08-22 18:26:32'),(7,25,1,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 18:29:03'),(8,25,3,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 18:29:06'),(9,25,6,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 22:45:43'),(10,25,6,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 22:47:04'),(11,25,1,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 22:47:28'),(12,25,3,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 22:47:30'),(13,25,1,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 23:04:26'),(14,25,3,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 23:04:28'),(15,25,6,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 23:04:56'),(16,25,9,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 23:05:06'),(17,25,9,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 23:05:09'),(18,25,2,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 23:23:07'),(19,25,2,'PostmanRuntime - Postman Runtime','0:0:0:0:0:0:0:1','2024-08-22 23:24:50'),(20,25,2,'PostmanRuntime - Postman Runtime','0:0:0:0:0:0:0:1','2024-08-22 23:26:49'),(21,25,1,'PostmanRuntime - Postman Runtime','0:0:0:0:0:0:0:1','2024-08-22 23:49:46'),(22,25,3,'PostmanRuntime - Postman Runtime','0:0:0:0:0:0:0:1','2024-08-22 23:49:48'),(23,25,1,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 23:50:06'),(24,25,3,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 23:50:09'),(25,25,9,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 23:50:30'),(26,25,9,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-22 23:50:38'),(27,25,1,'PostmanRuntime - Postman Runtime','0:0:0:0:0:0:0:1','2024-08-22 23:54:43'),(28,25,3,'PostmanRuntime - Postman Runtime','0:0:0:0:0:0:0:1','2024-08-22 23:54:47'),(29,25,9,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-23 08:41:27'),(30,25,9,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-23 08:41:41'),(31,25,1,'PostmanRuntime - Postman Runtime','0:0:0:0:0:0:0:1','2024-08-23 15:41:03'),(32,25,3,'PostmanRuntime - Postman Runtime','0:0:0:0:0:0:0:1','2024-08-23 15:41:06'),(33,25,1,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-25 10:16:01'),(34,25,3,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-25 10:16:03'),(35,25,1,'PostmanRuntime - Postman Runtime','0:0:0:0:0:0:0:1','2024-08-25 11:26:56'),(36,25,3,'PostmanRuntime - Postman Runtime','0:0:0:0:0:0:0:1','2024-08-25 11:26:58'),(37,30,1,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-25 14:10:20'),(38,30,3,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-25 14:10:26'),(39,32,1,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-25 15:23:43'),(40,32,3,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-25 15:23:46'),(41,25,1,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-25 15:40:02'),(42,25,3,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-25 15:40:04'),(43,25,1,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-27 10:53:24'),(44,25,3,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-27 10:53:30'),(45,25,1,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-27 13:14:41'),(46,25,3,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-27 13:14:46'),(47,25,1,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-28 12:15:33'),(48,25,3,'Chrome - Desktop','0:0:0:0:0:0:0:1','2024-08-28 12:15:36');
/*!40000 ALTER TABLE `userevents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userroles`
--

DROP TABLE IF EXISTS `userroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userroles` (
  `user_role_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `role_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `Unique_UserRoles_User` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `userroles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userroles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userroles`
--

LOCK TABLES `userroles` WRITE;
/*!40000 ALTER TABLE `userroles` DISABLE KEYS */;
INSERT INTO `userroles` VALUES (1,22,17),(2,23,17),(3,24,17),(4,25,20),(5,26,17),(6,27,17),(7,28,17),(8,29,17),(9,30,17),(10,31,17),(11,32,17);
/*!40000 ALTER TABLE `userroles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bio` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '0',
  `non_locked` tinyint(1) DEFAULT '1',
  `using_mfa` tinyint(1) DEFAULT '0',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `image_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT 'https://cdn-icons-png.flaticon.com/512/149/149071.png',
  `created_on` datetime(6) DEFAULT NULL,
  `mfa_enabled` bit(1) DEFAULT NULL,
  `is_not_locked` bit(1) DEFAULT NULL,
  `is_using_mfa` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_Users_Email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Naveen','John','n@gmail.com','$2a$12$PLnCqgRYp7CrVOUi6HSyiOP9H/l8L4IqgLjzmVwtrP6wJzL63rhOm',NULL,NULL,NULL,NULL,0,1,0,'2024-08-03 18:58:32','https://cdn-icons-png.flaticon.com/512/149/149071.png',NULL,NULL,NULL,NULL),(22,'Nveen','Jhn','bss@gmail.com','$2a$12$beUSmi2DbrAWRvncC0w5oO3qkPNq9W1v9i6NsMt34y/C3XTqQx98O',NULL,NULL,NULL,NULL,1,1,0,'2024-08-03 23:54:04','https://cdn-icons-png.flaticon.com/512/149/149071.png',NULL,NULL,NULL,NULL),(23,'Manuel','Joshy','manu@gmail.com','$2a$12$eLjOMb7Jkcqhx3Hi4lmc7O39.CSa53qw/QN7Q1AUKPS63hPiIKtui',NULL,NULL,NULL,NULL,1,1,0,'2024-08-04 00:33:30','https://cdn-icons-png.flaticon.com/512/149/149071.png',NULL,NULL,NULL,NULL),(24,'Jimmy','jyothis','jimmy@gmail.com','$2a$12$hG.KCjqjqXJtMmLXcqKb8OLuuBI2I9/IPJAleQUXd6eF32nPjv.Mu',NULL,NULL,NULL,NULL,1,1,1,'2024-08-04 00:35:44','https://cdn-icons-png.flaticon.com/512/149/149071.png',NULL,_binary '',NULL,NULL),(25,'Naveen','John','naveen@gmail.com','$2a$12$UVFGJ49gAJxLcVCJ6gZhqOvoXKGqg4o48pHQjEaIEmD9Q2c1hycLa','Christ Hostel KE','9458764555','updation testing','done',1,1,0,'2024-08-13 12:33:06','http://localhost:8080/user/image/naveen@gmail.com.png',NULL,NULL,NULL,NULL),(26,'test','code','test@gmail.com','$2a$12$1rDF1wz3vCSxLLUGyqq/7u4aY3/vmVYPysu7vcQttIHMZ4vznpIXi',NULL,NULL,NULL,NULL,1,1,1,'2024-08-17 12:48:18','https://cdn-icons-png.flaticon.com/512/149/149071.png',NULL,NULL,NULL,NULL),(27,'test','code','test1@gmail.com','$2a$12$m.y/V1fucs6qtad7x9k4CebTbkHLH0OfQBJgxby.C/zmz4361y9aW',NULL,NULL,NULL,NULL,1,1,1,'2024-08-17 12:49:31','https://cdn-icons-png.flaticon.com/512/149/149071.png',NULL,NULL,NULL,NULL),(28,'rahael','ann','ann@gmail.com','$2a$12$69/dFQH7iWsU0UPy8aUeFeQQ1OE4n6AAKSh8gI11gpzGxsgJpnTZq',NULL,NULL,NULL,NULL,1,1,0,'2024-08-17 22:07:50','https://cdn-icons-png.flaticon.com/512/149/149071.png',NULL,NULL,NULL,NULL),(29,'aa','adn','naveenjohn942@gmail.com','$2a$12$Io.jOOvN1FthpVpENw.JXOvm6YRLCKjh5LhZWkIgiurHFbX8KNsKm',NULL,NULL,NULL,NULL,0,1,0,'2024-08-17 22:09:00','https://cdn-icons-png.flaticon.com/512/149/149071.png',NULL,NULL,NULL,NULL),(30,'Alice','Wonder Land','alice@gmail.com','$2a$12$D2TRZqGDRUEDw0hrPtngguieCFtPVMnw3lOY8wPWzUEyDvO3XthIW',NULL,NULL,NULL,NULL,1,1,0,'2024-08-25 12:04:54','https://cdn-icons-png.flaticon.com/512/149/149071.png',NULL,NULL,NULL,NULL),(31,'kaushik','balla','k@gmail.com','$2a$12$TIGxAbTG54qez339.BTj.OsLY.0iIdswNqloKhLvCLzdAooelOQFa',NULL,NULL,NULL,NULL,0,1,0,'2024-08-25 15:20:48','https://cdn-icons-png.flaticon.com/512/149/149071.png',NULL,NULL,NULL,NULL),(32,'Naveen','Mathew','naveenmathew942@gmail.com','$2a$12$lt5MtWWKYyRCzAlzUC11Muo.2DC1s40uUPjRRw26wxVrzA1ml18F.',NULL,NULL,NULL,NULL,1,1,0,'2024-08-25 15:21:49','https://cdn-icons-png.flaticon.com/512/149/149071.png',NULL,NULL,NULL,NULL);
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

-- Dump completed on 2024-08-28 12:31:55
