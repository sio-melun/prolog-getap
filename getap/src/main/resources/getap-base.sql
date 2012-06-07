CREATE TABLE IF NOT EXISTS `classe` (
  `id` int(11) NOT NULL auto_increment,
  `libelle` varchar(45) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `libelle` (`libelle`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=80;

CREATE TABLE IF NOT EXISTS `discipline` (
  `id` int(11) NOT NULL auto_increment,
  `libelle` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=29 ;

CREATE TABLE IF NOT EXISTS `ap` (
  `id` int(11) NOT NULL auto_increment,
  `libelle` varchar(45) default NULL,
  `origineEtat` smallint(6) default NULL COMMENT 'False par default (non validée)',
  `idUser` int(11) default NULL COMMENT 'NULL pour AP générique',
  PRIMARY KEY  (`id`),
  KEY `fk_AP_User1` (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `param_accueil` (
  `img` varchar(200) default NULL,
  `logo` varchar(200) default NULL,
  `titre` varchar(200) default NULL,
  `texte` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `param_annee` (
  `id` int(11) NOT NULL auto_increment,
  `anneeScolaire` varchar(9) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

CREATE TABLE IF NOT EXISTS `prof_principal` (
  `idUser` int(11) NOT NULL,
  `idClasse` int(11) NOT NULL,
  PRIMARY KEY  (`idUser`,`idClasse`),
  KEY `fk_User_has_Classe_Classe1` (`idClasse`),
  KEY `fk_User_has_Classe_User1` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1182 ;
