CREATE DATABASE  IF NOT EXISTS `Tateti` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `Tateti`;
-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: localhost    Database: Tateti
-- ------------------------------------------------------
-- Server version	8.0.31-0ubuntu0.20.04.1

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
-- Table structure for table `Idiomas`
--

DROP TABLE IF EXISTS `Idiomas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Idiomas` (
  `codigo` int NOT NULL,
  `descripcion` varchar(80) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci COMMENT='Tabla de idiomas';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Idiomas`
--

LOCK TABLES `Idiomas` WRITE;
/*!40000 ALTER TABLE `Idiomas` DISABLE KEYS */;
INSERT INTO `Idiomas` VALUES (1,'español'),(2,'ingles'),(3,'frances'),(4,'portugues');
/*!40000 ALTER TABLE `Idiomas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Mensaje_por_idioma`
--

DROP TABLE IF EXISTS `Mensaje_por_idioma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Mensaje_por_idioma` (
  `cod_mensaje` int NOT NULL,
  `cod_idioma` int NOT NULL,
  `descripcion` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`cod_mensaje`,`cod_idioma`),
  KEY `fk_idioma_idx` (`cod_idioma`),
  CONSTRAINT `fk_idioma` FOREIGN KEY (`cod_idioma`) REFERENCES `Idiomas` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci COMMENT='Tabla ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Mensaje_por_idioma`
--

LOCK TABLES `Mensaje_por_idioma` WRITE;
/*!40000 ALTER TABLE `Mensaje_por_idioma` DISABLE KEYS */;
INSERT INTO `Mensaje_por_idioma` VALUES (1,1,'Bienvenido al Ta-Te-Ti POO'),(1,2,'Welcome to Ta-Te-Ti OOP'),(1,3,'Bienvenue sur Tic Tac Toe POO'),(1,4,'Bem-vindo ao Tic Tac Toe OOP'),(2,1,'La jugada de la computadora es:'),(2,2,'The computer move is:'),(2,3,'Le mouvement de l\'ordinateur est :'),(2,4,'O movimento do computador é:'),(3,1,'El casillero se encuentra ocupado intente nuevamente'),(3,2,'The box is busy, please try again.'),(3,3,'La boîte aux lettres est occupée, veuillez réessayer.'),(3,4,'A caixa de correio está ocupada, tente novamente.'),(4,1,'El valor de la fila tiene que ser estrictamente un valor del 0 al 2 '),(4,2,'The value of the row has to be strictly a value from 0 to 2'),(4,3,'La valeur de la ligne doit être strictement une valeur comprise entre 0 et 2'),(4,4,'O valor da linha deve ser estritamente um valor de 0 a 2'),(5,1,'El valor de la columna tiene que ser estrictamente un valor del 0 al 2 '),(5,2,'The value of the column has to be strictly a value from 0 to 2'),(5,3,'La valeur de la colonne doit être strictement une valeur comprise entre 0 et 2'),(5,4,'O valor da coluna deve ser estritamente um valor de 0 a 2'),(6,1,'ingrese la fila de la jugada (del 0 al 2): '),(6,2,'enter the row of the move (from 0 to 2):'),(6,3,'entrer la ligne du coup (de 0 à 2) :'),(6,4,'digite a linha do movimento (de 0 a 2):'),(7,1,'ingrese la columna de la jugada (del 0 al 2): '),(7,2,'enter the column of the play (from 0 to 2):'),(7,3,'entrez la colonne du jeu (de 0 à 2) :'),(7,4,'entre na coluna da peça (de 0 a 2):'),(8,1,'¡Cuidado! Solo puedes insertar números.'),(8,2,' Watch out! You can only insert numbers.'),(8,3,'Fais attention! Vous ne pouvez insérer que des chiffres.'),(8,4,'Atenção! Você só pode inserir números.'),(9,1,'Gana el jugador'),(9,2,'the player wins'),(9,3,'le joueur gagne'),(9,4,'o jogador ganha'),(10,1,'Empate'),(10,2,'Tie'),(10,3,'Cravate'),(10,4,'Gravata'),(11,1,'Ingrese su nombre:'),(11,2,'Enter your name:'),(11,3,'Entrez vottre nom:'),(11,4,'Digite seu nome:'),(12,1,'tabla de resultados:'),(12,2,'results table:'),(12,3,'tableau des résultats:'),(12,4,'Tabela de resultados:'),(13,1,'nro. de partida         nombre    comienzo de partida               final de partida            ganador'),(13,2,'Game number             Name       Start of the game                   End of the game           Winner'),(13,3,'numéro de jeu           nom            Début du jeu                       jeu terminé            gagnant'),(13,4,'número do jogo           Nome        início do jogo                    fim do jogo               vencedora'),(14,1,'Español:  ingrese el numero correspodiente al idioma (del 1 al 4): '),(14,2,'English: enter the number corresponding to the language (from 1 to 4):'),(14,3,'Français: saisir le chiffre correspondant à la langue (de 1 à 4):'),(14,4,'Português: digite o número correspondente ao idioma (de 1 a 4):'),(15,1,'ingrese un numero del 1 al 5:'),(15,2,'enter a number from 1 to 5:'),(15,3,'entrez un nombre de 1 à 5:'),(15,4,'digite um número de 1 a 5:'),(16,1,'Cambiar idioma'),(16,2,'Change language'),(16,3,'Changer la langue'),(16,4,'Mudar idioma'),(17,1,'Jugar partida'),(17,2,'Play game'),(17,3,'Jouer à un jeu'),(17,4,'Jogar um jogo'),(18,1,'Ver resultados generales'),(18,2,'See general results'),(18,3,'Voir les résultats généraux'),(18,4,'Ver resultados gerais'),(19,1,'Ver resultados por jugador'),(19,2,'See results by player'),(19,3,'Voir les résultats par joueur'),(19,4,'Veja os resultados por jogador'),(20,1,'Salir'),(20,2,'Exit'),(20,3,'Sortir'),(20,4,'Salir');
/*!40000 ALTER TABLE `Mensaje_por_idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Resultados_partidas`
--

DROP TABLE IF EXISTS `Resultados_partidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Resultados_partidas` (
  `idresultado_partida` int NOT NULL AUTO_INCREMENT,
  `final_partida` datetime(6) NOT NULL,
  `comienzo_partida` datetime(6) NOT NULL,
  `Jugador_NOMBRE` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `ganador` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`idresultado_partida`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Resultados_partidas`
--

LOCK TABLES `Resultados_partidas` WRITE;
/*!40000 ALTER TABLE `Resultados_partidas` DISABLE KEYS */;
INSERT INTO `Resultados_partidas` VALUES (1,'2022-11-07 19:10:28.118085','2022-11-07 19:08:39.069308','Nacho','Nacho'),(2,'2022-11-07 19:32:22.477733','2022-11-07 19:31:48.775224','Nacho','Nacho'),(3,'2022-11-07 19:38:36.342533','2022-11-07 19:38:05.259581','Nacho','Nacho'),(4,'2022-11-07 19:41:50.150318','2022-11-07 19:41:31.027139','Nacho','Nacho'),(5,'2022-11-07 19:43:37.281448','2022-11-07 19:43:12.678069','Nacho','Nacho'),(6,'2022-11-07 19:46:24.397358','2022-11-07 19:46:01.053411','Nacho','Nacho'),(7,'2022-11-07 19:48:41.630454','2022-11-07 19:47:57.820540','Nacho','Nacho'),(8,'2022-11-07 19:50:17.455378','2022-11-07 19:49:58.354130','Nacho','Nacho'),(9,'2022-11-07 19:55:13.567261','2022-11-07 19:54:51.728845','Nacho','Nacho'),(10,'2022-11-07 19:58:17.399019','2022-11-07 19:57:49.190597','Nacho','Nacho'),(11,'2022-11-07 22:16:51.081362','2022-11-07 22:16:13.853803','Naché','Naché'),(12,'2022-11-07 22:18:14.746112','2022-11-07 22:17:51.735490','Nacho','Nacho'),(13,'2022-11-07 22:32:17.285345','2022-11-07 22:26:11.508492','Nacho','Nacho'),(14,'2022-11-07 22:33:56.138171','2022-11-07 22:33:27.318622','Nacho','Nacho'),(15,'2022-11-07 22:36:20.419802','2022-11-07 22:35:30.990934','Nacho','Nacho'),(16,'2022-11-19 16:32:44.254509','2022-11-19 16:32:23.631057','Nacho1','Nacho1'),(17,'2022-11-19 16:43:49.209110','2022-11-19 16:43:33.298104','Nachinho','Nachinho');
/*!40000 ALTER TABLE `Resultados_partidas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-19 16:47:19
