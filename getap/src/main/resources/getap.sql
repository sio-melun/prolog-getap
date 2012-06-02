-- phpMyAdmin SQL Dump
-- version 2.11.3deb1ubuntu1.3
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Dim 08 Avril 2012 à 06:45
-- Version du serveur: 5.0.51
-- Version de PHP: 5.2.4-2ubuntu5.18

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de données: `getap`
--

-- --------------------------------------------------------

--
-- Structure de la table `ap`
--

CREATE TABLE IF NOT EXISTS `ap` (
  `id` int(11) NOT NULL auto_increment,
  `libelle` varchar(45) default NULL,
  `origineEtat` smallint(6) default NULL COMMENT 'False par default (non validée)',
  `idUser` int(11) default NULL COMMENT 'NULL pour AP générique',
  PRIMARY KEY  (`id`),
  KEY `fk_AP_User1` (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `ap`
--

INSERT INTO `ap` (`id`, `libelle`, `origineEtat`, `idUser`) VALUES
(1, 'SOS Matiere', 0, NULL),
(2, 'Sortie théatre', 0, NULL); 

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE IF NOT EXISTS `classe` (
  `id` int(11) NOT NULL auto_increment,
  `libelle` varchar(45) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `libelle` (`libelle`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=65 ;

--
-- Contenu de la table `classe`
--

INSERT INTO `classe` (`id`, `libelle`) VALUES
(15, '1.CTA1'),
(31, '1.CTA2'),
(22, '1.ELE1'),
(37, '1.ELE2'),
(44, '1.S'),
(16, '1.SCT'),
(10, '1.TT'),
(11, '1SI'),
(17, '1STG1'),
(58, '1STG2'),
(51, '1STI2D1'),
(24, '1STI2D2'),
(9, '1TU'),
(64, '2D1'),
(36, '2D2'),
(34, '2D3'),
(32, '2D4'),
(62, '2D5'),
(35, '2ELE1'),
(43, '2ELE2'),
(42, '2MSA1'),
(54, '2MSA2'),
(13, '2SU'),
(12, '2TI'),
(28, '2TU'),
(33, 'CG1'),
(25, 'CLA'),
(48, 'CPI1'),
(57, 'CPI2'),
(49, 'CRSA1'),
(45, 'DCG1'),
(59, 'DCG2'),
(27, 'DCG3'),
(18, 'IG2D'),
(38, 'IG2R'),
(50, 'MAI2'),
(23, 'MUC1'),
(30, 'MUC2'),
(60, 'NRC1'),
(55, 'NRC2'),
(14, 'null'),
(21, 'Prem.ES'),
(63, 'Prem.L'),
(4, 'SIO-SISR'),
(3, 'SIO-SLAM'),
(1, 'SIO11'),
(2, 'SIO12'),
(7, 'STG'),
(8, 'STI'),
(47, 'T.ES'),
(53, 'T.S'),
(20, 'TCTA1'),
(52, 'TCTA2'),
(46, 'TELE1'),
(19, 'TELE2'),
(26, 'TELEC'),
(41, 'TGCFE'),
(39, 'TGE'),
(40, 'TGM'),
(56, 'TMCT'),
(29, 'TSCT'),
(61, 'TTU');

-- --------------------------------------------------------

--
-- Structure de la table `dctap`
--

CREATE TABLE IF NOT EXISTS `dctap` (
  `id` int(11) NOT NULL auto_increment,
  `anneeScolaire` varchar(9) NOT NULL,
  `dateAction` date NOT NULL,
  `dureeAP` int(11) NOT NULL,
  `Etat` int(11) NOT NULL,
  `idProf` int(11) NOT NULL,
  `idEleve` int(11) NOT NULL,
  `idAP` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `idProf` (`idProf`),
  KEY `idEleve` (`idEleve`),
  KEY `idAP` (`idAP`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;



-- --------------------------------------------------------

--
-- Structure de la table `discipline`
--

CREATE TABLE IF NOT EXISTS `discipline` (
  `id` int(11) NOT NULL auto_increment,
  `libelle` varchar(30) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=29 ;

--
-- Contenu de la table `discipline`
--

INSERT INTO `discipline` (`id`, `libelle`) VALUES
(1, 'Philosophie'),
(2, 'Lettres'),
(3, 'Allemand'),
(4, 'Anglais'),
(5, 'Espagnol'),
(6, 'Italien'),
(7, 'Histoire Géographie'),
(8, 'Mathématiques'),
(9, 'Sciences Physiques'),
(10, 'Science et Vie de la Terre'),
(11, 'Education Physique et Sportive'),
(12, 'Physique apppliquée'),
(13, 'Economie Gestion'),
(14, 'Management des Unités Commerciales (MUC)'),
(15, 'Services Informatiques aux Organisations (SIO)'),
(16, 'Géni mécanique Construction'),
(17, 'Géni mécanique Production'),
(18, 'Electrotechnique'),
(19, 'Lettres Histoire'),
(20, 'Lettres Anglais'),
(21, 'Maths Science'),
(22, 'Art appliqué'),
(23, 'Biotechnologie'),
(24, 'Communication Bureautique'),
(25, 'Comptabilité Bureautique'),
(26, 'Géni mécanique Construction Pr'),
(27, 'Géni mécanique Production Prof'),
(28, 'Electrotechnique Professionnel');

-- --------------------------------------------------------

--
-- Structure de la table `param_annee`
--

CREATE TABLE IF NOT EXISTS `param_annee` (
  `id` int(11) NOT NULL auto_increment,
  `anneeScolaire` varchar(9) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `param_annee`
--

INSERT INTO `param_annee` (`id`, `anneeScolaire`) VALUES
(1, '2011-2012');

-- --------------------------------------------------------

--
-- Structure de la table `prof_principal`
--

CREATE TABLE IF NOT EXISTS `prof_principal` (
  `idUser` int(11) NOT NULL,
  `idClasse` int(11) NOT NULL,
  PRIMARY KEY  (`idUser`,`idClasse`),
  KEY `fk_User_has_Classe_Classe1` (`idClasse`),
  KEY `fk_User_has_Classe_User1` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL auto_increment,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `ine` varchar(20) default NULL,
  `login` varchar(50) NOT NULL,
  `mdp` varchar(45) NOT NULL,
  `hash` varchar(50) NOT NULL,
  `role` varchar(20) NOT NULL COMMENT 'Admin \n\nProfPrin\n\nProfInter\n\nEleve',
  `idClasse` int(11) default NULL,
  `mail` varchar(50) default NULL,
  `idDiscipline` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------

--
-- Structure de la table `param_accueil`
--

CREATE TABLE IF NOT EXISTS `param_accueil` (
  `img` varchar(200) NULL,
  `logo` varchar(200) NULL,
  `titre` varchar(200) NULL,
  `texte` longtext NULL,
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

--
-- Contenu de la table `param_annee`
--

INSERT INTO `param_accueil` (`img`, `logo`, `titre`, `texte`) VALUES
('/images/lycee2.jpg', '/images/logolycee.jpg', 'Bienvenue sur ProLog-GeTAP', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam lorem nulla, sagittis sit amet ullamcorper laoreet, dapibus a elit. Nulla a tellus leo. Vestibulum at dolor sed purus tincidunt bibendum. Integer lorem sem, aliquam id accumsan at, vulputate ac nibh. Morbi eget lacinia metus. Suspendisse imperdiet tincidunt urna in interdum. Donec dignissim fringilla diam, id hendrerit mi tempor id. Aenean non purus ut nulla congue condimentum quis vitae arcu. Nam mattis elit ut velit porttitor rutrum. Integer pretium egestas quam, vitae mattis elit aliquet in. Duis vel euismod nulla. Pellentesque vitae leo velit, nec bibendum risus. Sed non ligula orci. Mauris bibendum congue massa ut feugiat. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed enim felis, tempor nec molestie nec, vehicula sed diam. ');