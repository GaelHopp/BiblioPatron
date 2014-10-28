-- phpMyAdmin SQL Dump
-- version 4.0.6
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mar 28 Octobre 2014 à 13:14
-- Version du serveur: 5.5.33
-- Version de PHP: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données: `Bibliotheque`
--

-- --------------------------------------------------------

--
-- Structure de la table `Employe`
--

CREATE TABLE `Employe` (
  `idEmploye` int(10) NOT NULL,
  PRIMARY KEY (`idEmploye`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Employe`
--

INSERT INTO `Employe` (`idEmploye`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `Emprunt`
--

CREATE TABLE `Emprunt` (
  `idUsager` int(11) NOT NULL,
  `idExemplaire` int(11) NOT NULL,
  `dateEmprunt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `statut` int(11) NOT NULL,
  PRIMARY KEY (`idUsager`,`idExemplaire`,`dateEmprunt`),
  KEY `idExemplaireEmprunt` (`idExemplaire`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Exemplaire`
--

CREATE TABLE `Exemplaire` (
  `idExemplaire` int(10) NOT NULL AUTO_INCREMENT,
  `idOeuvre` int(10) NOT NULL,
  `etat` varchar(64) NOT NULL,
  PRIMARY KEY (`idExemplaire`,`idOeuvre`),
  KEY `idOeuvreExemplaire` (`idOeuvre`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=49 ;

-- --------------------------------------------------------

--
-- Structure de la table `Oeuvre`
--

CREATE TABLE `Oeuvre` (
  `idOeuvre` int(10) NOT NULL AUTO_INCREMENT,
  `titre` varchar(128) NOT NULL,
  `auteur` varchar(64) NOT NULL,
  PRIMARY KEY (`idOeuvre`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

-- --------------------------------------------------------

--
-- Structure de la table `Personne`
--

CREATE TABLE `Personne` (
  `idPersonne` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(64) NOT NULL,
  `prenom` varchar(64) NOT NULL,
  `age` int(3) NOT NULL,
  `adresse` varchar(64) NOT NULL,
  PRIMARY KEY (`idPersonne`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Contenu de la table `Personne`
--

INSERT INTO `Personne` (`idPersonne`, `nom`, `prenom`, `age`, `adresse`) VALUES
(1, 'admin', 'admin', 99, 'Bibliotheque'),
(18, 'Hopp', 'Gael', 21, 'RUE X');

-- --------------------------------------------------------

--
-- Structure de la table `Reservation`
--

CREATE TABLE `Reservation` (
  `idUsager` int(11) NOT NULL,
  `idOeuvre` int(11) NOT NULL,
  `dateRes` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `statut` int(11) NOT NULL,
  PRIMARY KEY (`idUsager`,`idOeuvre`,`dateRes`),
  KEY `idOeuvreRes` (`idOeuvre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Usager`
--

CREATE TABLE `Usager` (
  `idUsager` int(10) NOT NULL,
  PRIMARY KEY (`idUsager`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Employe`
--
ALTER TABLE `Employe`
  ADD CONSTRAINT `idEmploye` FOREIGN KEY (`idEmploye`) REFERENCES `Personne` (`idPersonne`);

--
-- Contraintes pour la table `Emprunt`
--
ALTER TABLE `Emprunt`
  ADD CONSTRAINT `idExemplaireEmprunt` FOREIGN KEY (`idExemplaire`) REFERENCES `Exemplaire` (`idExemplaire`),
  ADD CONSTRAINT `idUsagerEmprunt` FOREIGN KEY (`idUsager`) REFERENCES `Usager` (`idUsager`);

--
-- Contraintes pour la table `Exemplaire`
--
ALTER TABLE `Exemplaire`
  ADD CONSTRAINT `idOeuvreExemplaire` FOREIGN KEY (`idOeuvre`) REFERENCES `Oeuvre` (`idOeuvre`);

--
-- Contraintes pour la table `Reservation`
--
ALTER TABLE `Reservation`
  ADD CONSTRAINT `idOeuvreRes` FOREIGN KEY (`idOeuvre`) REFERENCES `Oeuvre` (`idOeuvre`),
  ADD CONSTRAINT `idUsagerRes` FOREIGN KEY (`idUsager`) REFERENCES `Usager` (`idUsager`);

--
-- Contraintes pour la table `Usager`
--
ALTER TABLE `Usager`
  ADD CONSTRAINT `idUsager` FOREIGN KEY (`idUsager`) REFERENCES `Personne` (`idPersonne`);

