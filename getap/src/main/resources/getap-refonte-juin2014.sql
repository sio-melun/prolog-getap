CREATE TABLE IF NOT EXISTS `parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `keyValue` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

INSERT INTO `parameter` (`id`, `keyName`, `keyValue`) VALUES
(1, 'img', '/images/lycee2.jpg'),
(2, 'logo', '/images/logolycee.jpg'),
(3, 'titre', 'Bienvenu sur GeTAP'),
(4, 'texte', 'GeTAP est une application de Gestion de Temps d''Accompagnement Personnalisé pour les besoins d''un établissement scolaire.<br/> L''objectif principal est de reconnaître l''investissement d''un élève dans des activités vécues  <span class="T1">hors du cadre</span> habituel réservé à cet usage (inscrit dans son emploi du temps hebdomadaire)<br/><br/>L''élève est au centre de ce dispositif, il en est l''acteur principal.<br/><br/><span class="T2">L''élève, </span><span class="T1">après</span> s''être investi dans une activité d''accompagnement, déclare sa participation à cette activé, en renseignant :<br/><br/><span style="position:absolute;left:NaNcm;">La nature de l''activité </span> <br/><span style="position:absolute;left:NaNcm;">Le jour</span><br/><span style="position:absolute;left:NaNcm;">La durée</span><br/><span style="position:absolute;left:NaNcm;">Le professeur intervenant </span><br/><br/>C''est le professeur intervenant concerné qui valide la présence de l''élève à l''activité en question.<br/><br/>Le professeur se connecte à l''application GeTAP pour être informé des demandes en attentes de validation.  (Une communication automatique par courriel est prévue, selon un intervalle qui reste à définir, mais cette fonctionnalité n''est pas implémentée pour le moment)<br/><br/>L''élève et le professeur peuvent, à tout moment, consulter le nombre d''heures consommé en accompagnement personnalisé pour l''année scolaire, ainsi que les demandes en attentes de validation et les autres.');