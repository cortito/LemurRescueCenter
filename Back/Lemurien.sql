-- phpMyAdmin SQL Dump
-- version 4.1.14.8
-- http://www.phpmyadmin.net
--
-- Client :  db660916030.db.1and1.com
-- Généré le :  Lun 19 Décembre 2016 à 14:47
-- Version du serveur :  5.5.52-0+deb7u1-log
-- Version de PHP :  5.4.45-0+deb7u5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `db660916030`
--

-- --------------------------------------------------------

--
-- Structure de la table `Lemurien`
--

CREATE DATABASE DB_LEMURIEN;

USE DB_LEMURIEN;

CREATE TABLE IF NOT EXISTS `Lemurien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sexe` tinyint(1) DEFAULT NULL,
  `nom` varchar(32) COLLATE latin1_general_ci DEFAULT NULL,
  `dateDeNaissance` date DEFAULT NULL,
  `taille` int(11) DEFAULT NULL,
  `poids` double DEFAULT NULL,
  `caracteristiques` varchar(128) COLLATE latin1_general_ci DEFAULT NULL,
  `etat` varchar(32) COLLATE latin1_general_ci DEFAULT NULL,
  `famille` varchar(32) COLLATE latin1_general_ci DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `parent1` int(11) DEFAULT NULL,
  `parent2` int(11) DEFAULT NULL,
  `dernierScan` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=3 ;

--
-- Contenu de la table `Lemurien`
--

INSERT INTO `Lemurien` (`id`, `sexe`, `nom`, `dateDeNaissance`, `taille`, `poids`, `caracteristiques`, `etat`, `famille`, `latitude`, `longitude`, `parent1`, `parent2`, `dernierScan`) VALUES
(1, 1, 'Jack', '2016-12-13', 42, 8.12, 'Tache noire oreille droite', 'Vivant', 'Jack', -23.1221667, 43.62144439999997, NULL, NULL, '2016-12-16');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


