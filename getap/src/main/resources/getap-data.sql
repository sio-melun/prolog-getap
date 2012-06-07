INSERT INTO `user` (`id`, `nom`, `prenom`, `ine`, `login`, `mdp`, `hash`, `role`, `idClasse`, `mail`, `idDiscipline`) VALUES
(1, 'ADMIN', 'Admin', NULL, 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', NULL, NULL, NULL),
(2, 'CAPUOZZO', 'Olivier', NULL, 'kpu', 'kpu', '46a7033a2bc3074cbbb7bb76a2e2731f', 'prof-principal', NULL, NULL, 5),
(3, 'GUILLIEN', 'Yann', NULL, 'yann', 'yann', '182b7817e82d6eb4a6107a431b92fa2b', 'prof-intervenant', NULL, NULL, 1),
(4, 'DALLAT  ', 'Gilles', NULL, 'gil', 'gil', '0d7d3a24242c6d235735b98149c6b35b', 'prof-principal', NULL, NULL, 4),
(5, 'LAFOREST', 'Florian', '01234567896', 'flo', 'flo', '7e1e91156f7c4e1bd0831cf008ad5fdf', 'eleve', 3, NULL, NULL),
(6, 'GEFFARD', 'Quentin', '01234567895', 'quent', 'quent', '12178f9c143bc496a230e893e838db26', 'eleve', 3, NULL, NULL),
(7, 'FIDALGO', 'Antoine', '01234567894', 'ant', 'ant', '63b07e828bf016e976ff95d6ee07a105', 'eleve', 3, NULL, NULL),
(8, 'MEUNIER', 'Christopher', '01234567893', 'cris', 'cris', '7bb0bb352ffb2f5245f25149889a0c76', 'eleve', 2, NULL, NULL),
(9, 'BARROSO', 'Nicolas', '01234567892', 'nico', 'nico', '410ec15153a6dff0bed851467309bcbd', 'eleve', 2, NULL, NULL);

INSERT INTO `dctap` (`id`, `anneeScolaire`, `dateAction`, `dureeAP`, `Etat`, `idProf`, `idEleve`, `idAP`) VALUES
(6, '2011-2012', '2012-05-04', 35, 32, 2, 6, 2),
(7, '2011-2012', '2012-05-18', 85, 32, 2, 6, 3),
(8, '2011-2012', '2012-05-26', 10, 2, 2, 6, 4),

(9, '2011-2012', '2012-05-02', 245, 0, 2, 7, 1),
(10, '2011-2012', '2012-10-03', 50, 1024, 2, 7, 1),
(11, '2011-2012', '2012-02-14', 85, 2048, 4, 7, 1),
(12, '2011-2012', '2012-06-30', 300, 4096, 3, 7, 3),

(13, '2011-2012', '2012-06-13', 20, 0, 2, 9, 2),
(14, '2011-2012', '2012-06-07', 10, 0, 2, 9, 4),
(15, '2011-2012', '2012-06-07', 10, 0, 4, 9, 4),

(16, '2011-2012', '2012-06-13', 20, 0, 3, 8, 3),
(17, '2011-2012', '2012-06-07', 10, 1024, 3, 8, 1);

INSERT INTO `ap` (`id`, `libelle`, `origineEtat`, `idUser`) VALUES
(1, 'SOS Matiere', 0, NULL),
(2, 'Sortie théatre', 0, NULL),
(3, 'Tutorat', 0, NULL),
(4, 'Entretien Conseilère d''Orientation', 0, NULL);

INSERT INTO `classe` (`id`, `libelle`) VALUES
(1, 'SIO11'),
(2, 'SIO12'),
(3, 'SIO-SLAM'),
(4, 'SIO-SISR');

INSERT INTO `discipline` (`id`, `libelle`) VALUES
(1, 'Philosophie'),
(2, 'Lettres'),
(3, 'Allemand'),
(4, 'Anglais'),
(5, 'Espagnol');

INSERT INTO `param_accueil` (`img`, `logo`, `titre`, `texte`) VALUES
('/images/lycee2.jpg', '/images/logolycee.jpg', 'Bienvenu sur GeTAP.', 'En informatique, une application Web (aussi appelée WebApp) est un logiciel applicatif manipulable grâce à un navigateur Web. De la même manière que les sites Web, une application Web est généralement placée sur un serveur et se manipule en actionnant des widgets à l aide d un navigateur Web, via un réseau informatique (Internet, intranet, réseau local, etc.).');

INSERT INTO `param_annee` (`id`, `anneeScolaire`) VALUES
(1, '2011-2012');

INSERT INTO `prof_principal` (`idUser`, `idClasse`) VALUES
(2, 3),
(4, 2);