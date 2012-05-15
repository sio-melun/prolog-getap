CREATE TABLE Classe (
id int(11) not null auto_increment PRIMARY KEY,
libelle varchar(50));

CREATE TABLE user(
id int(11) not null auto_increment PRIMARY KEY,
nom varchar(50) not null,
prenom varchar(50) not null,
login varchar(50) not null,
mdp varchar(50) not null,
role varchar(20) not null,
idClasse int(11) not null,
mail varchar(50),
Foreign Key (idClasse) references Classe(id));

CREATE TABLE ProfPrincipal (
idUser int(11) not null,
idClasse int(11) not null,
Foreign Key (idUser) references user(id),
Foreign Key (idClasse) references Classe(id));

CREATE TABLE AP (
id int(11) not null auto_increment PRIMARY KEY,
libelle varchar(50) not null,
origineEtat smallint not null,
idUser int(11) not null,
Foreign Key (idUser) references user(id));

CREATE TABLE DCTAP (
id int(11) not null auto_increment PRIMARY KEY,
anneeScolaire char(9) not null,
dateAction date not null,
dureeAP TIME not null,
Etat int(11) not null,
idProf int(11) not null,
idEleve int(11) not null,
idAP int(11) not null,
Foreign Key (idEleve) references user(id),
Foreign Key (idAP) references AP(id));

CREATE TABLE paramAnnee (
anneeScolaire char(9) not null);