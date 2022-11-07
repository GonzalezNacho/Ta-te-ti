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
  `descripcion` varchar(80) COLLATE utf8mb3_unicode_ci NOT NULL,
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
  `descripcion` varchar(120) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
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
INSERT INTO `Mensaje_por_idioma` VALUES (1,1,'Bienvenido al Ta-Te-Ti POO'),(1,2,'Welcome to Ta-Te-Ti OOP'),(1,3,'Bienvenue sur Tic Tac Toe POO'),(1,4,'Bem-vindo ao Tic Tac Toe OOP'),(2,1,'La jugada de la computadora es:'),(2,2,'The computer move is:'),(2,3,'Le mouvement de l\'ordinateur est :'),(2,4,'O movimento do computador é:'),(3,1,'El casillero se encuentra ocupado intente nuevamente'),(3,2,'The box is busy, please try again.'),(3,3,'La boîte aux lettres est occupée, veuillez réessayer.'),(3,4,'A caixa de correio está ocupada, tente novamente.'),(4,1,'El valor de la fila tiene que ser estrictamente un valor del 0 al 2 '),(4,2,'The value of the row has to be strictly a value from 0 to 2'),(4,3,'La valeur de la ligne doit être strictement une valeur comprise entre 0 et 2'),(4,4,'O valor da linha deve ser estritamente um valor de 0 a 2'),(5,1,'El valor de la columna tiene que ser estrictamente un valor del 0 al 2 '),(5,2,'The value of the column has to be strictly a value from 0 to 2'),(5,3,'La valeur de la colonne doit être strictement une valeur comprise entre 0 et 2'),(5,4,'O valor da coluna deve ser estritamente um valor de 0 a 2'),(6,1,'ingrese la fila de la jugada (del 0 al 2): '),(6,2,'enter the row of the move (from 0 to 2):'),(6,3,'entrer la ligne du coup (de 0 à 2) :'),(6,4,'digite a linha do movimento (de 0 a 2):'),(7,1,'ingrese la columna de la jugada (del 0 al 2): '),(7,2,'enter the column of the play (from 0 to 2):'),(7,3,'entrez la colonne du jeu (de 0 à 2) :'),(7,4,'entre na coluna da peça (de 0 a 2):'),(8,1,'¡Cuidado! Solo puedes insertar números.'),(8,2,' Watch out! You can only insert numbers.'),(8,3,'Fais attention! Vous ne pouvez insérer que des chiffres.'),(8,4,'Atenção! Você só pode inserir números.'),(9,1,'Gana el jugador'),(9,2,'the player wins'),(9,3,'le joueur gagne'),(9,4,'o jogador ganha'),(10,1,'Empate'),(10,2,'Tie'),(10,3,'Cravate'),(10,4,'Gravata');
/*!40000 ALTER TABLE `Mensaje_por_idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resutaldos_partidas`
--

DROP TABLE IF EXISTS `resutaldos_partidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resutaldos_partidas` (
  `idresutaldo_partida` int NOT NULL,
  `final_partida` datetime(6) NOT NULL,
  `comienzo_partida` datetime(6) NOT NULL,
  `Jugador_NOMBRE` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `ganador` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`idresutaldo_partida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resutaldos_partidas`
--

LOCK TABLES `resutaldos_partidas` WRITE;
/*!40000 ALTER TABLE `resutaldos_partidas` DISABLE KEYS */;
/*!40000 ALTER TABLE `resutaldos_partidas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-24 20:49:25
