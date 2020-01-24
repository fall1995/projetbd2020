drop table LesLogementsCampings;
drop table LesEmplacementsCollectifs;
drop table LesEmplacementsChambres;
drop table LesEmplacementsVillages;
drop table LesLogementsResidences;
drop table LesChambres;
drop table LesVillageVacances;
drop table LesCampings;
drop table LesResidences;
drop table LesHotels;
drop table LesPhotos;
drop table LesAvis;
drop table LesReservationLogements;
drop table LesPeriodeDeDisponibilites;
drop table LesLogements;
drop table LesReservationPaquetsPlaces;
drop table LesPaquetsPlaces;
drop table LesReservations;
drop table LesFestivals;
drop table LesHebergements FORCE;
drop table LesHebergeurs FORCE;
drop table LesClients;
drop table LesOrganisateurs;
drop table LesUtilisateurs FORCE;


CREATE TABLE LesUtilisateurs (
    idUtilisateur   VARCHAR(40),
    nomUtilisateur  VARCHAR2(20) ,
    nom VARCHAR2(20) ,
    prenom     VARCHAR2(20),
    mail      VARCHAR2(30),
    tel        VARCHAR2(10),
    adresse  VARCHAR2(70),
    CONSTRAINT Utilisateur_C1 PRIMARY KEY ( idUtilisateur ),
    constraint Utilisateur_C2 check (idUtilisateur between 0 and 99999)
);


CREATE TABLE LesOrganisateurs (
    idUtilisateur   VARCHAR(40),
    CONSTRAINT  Organisateu_C1 PRIMARY KEY ( idUtilisateur ),
    CONSTRAINT    fk_Organisateur_C2 
	FOREIGN KEY (idUtilisateur) 
    	REFERENCES LesUtilisateurs ( idUtilisateur ) on delete cascade,
    constraint Organisateur_C3 check (idUtilisateur between 0 and 99999)
);


CREATE TABLE LesClients (
    idUtilisateur   VARCHAR(40),
    CONSTRAINT Client_C1 PRIMARY KEY ( idUtilisateur ),
    CONSTRAINT fk_Client_C2 
	FOREIGN KEY (idUtilisateur) 
        REFERENCES LesUtilisateurs ( idUtilisateur ) on delete cascade,
    constraint Client_3 check (idUtilisateur between 0 and 99999)
);



CREATE TABLE LesHebergeurs (
    idUtilisateur   VARCHAR(40),
    CONSTRAINT Hebergeur_C1 PRIMARY KEY ( idUtilisateur ),
    CONSTRAINT    fk_Hebergeur_C2 FOREIGN KEY (idUtilisateur) REFERENCES LesUtilisateurs ( idUtilisateur ) on delete cascade,
         constraint Hebergeur_C3 check (idUtilisateur between 0 and 99999)
);

CREATE TABLE LesHebergements (
  idHebergement   NUMBER(10),
  dateDePublication       DATE,
  nomCommercial VARCHAR2(80),
  dateDeClassement     DATE,
  nomCategorie      VARCHAR2(80),
  classement        VARCHAR2(80),
  adresse    VARCHAR2(100),
  categorie    VARCHAR2(100),
  codePostal    VARCHAR(100),
  commune   VARCHAR2(100),
  numTel    VARCHAR(100),
  courriel    VARCHAR2(200),
  siteNet    VARCHAR2(200),
  coordonnee1    VARCHAR2(100),
  coordonnee2    VARCHAR2(100),
  nomDep     VARCHAR2(100),
  nomRegion     VARCHAR2(100),
  Description      VARCHAR2(100),
  dateAjout       DATE,
  idUtilisateur VARCHAR(5),
  CONSTRAINT LesHebergements_C1 PRIMARY KEY ( idHebergement ),
  CONSTRAINT    fk_LesHebergements_C2  FOREIGN KEY (idUtilisateur) REFERENCES LesHebergeurs ( idUtilisateur ) on delete cascade   
);

drop SEQUENCE my_seq_LesHebergement;
CREATE SEQUENCE my_seq_LesHebergement; 
create trigger auto_incr_LesHebergements before insert on LesHebergements
for each row
   
   begin
     select my_seq_LesHebergement.nextval into :new.idHebergement from dual;
   end;
/

CREATE TABLE LesFestivals (
    idFestival   VARCHAR(10),
    nomFestival  VARCHAR2(50) ,
    domaine      VARCHAR2(50) ,
    complementDomaine  VARCHAR2(50),
    region VARCHAR(50),
    departement      NUMBER(2),
    periodicite        VARCHAR2(20),
    moiHabDebut    VARCHAR2(20),
    siteWeb    VARCHAR2(30),
    commune    VARCHAR2(30),
    dateDebut   DATE,
    dateFin    DATE,
    dateCreation    DATE,
    codepost NUMBER(6),
    codeINSEE    VARCHAR(5),
    coordonneesINSEE VARCHAR2   (20),
    nomDepartement    VARCHAR2(100),
    nbPlaceLouees    NUMBER(3),
    dateAjout    DATE,
    idUtilisateur VARCHAR(5),
    CONSTRAINT Festival_C1 PRIMARY KEY ( idFestival ),
    CONSTRAINT Festival_C3 check (nbPlaceLouees >= 0),
    CONSTRAINT fk_Festival_C2 FOREIGN KEY (idUtilisateur) REFERENCES LesOrganisateurs ( idUtilisateur ) on delete cascade  
);

CREATE TABLE LesReservations (
    idReservation   number(5),
    prix number (10) NOT NULL,
    status CHAR(1),
    idUtilisateur VARCHAR(5),
    CONSTRAINT Reservation_C1  PRIMARY KEY ( idReservation ),
    CONSTRAINT fk_Reservation_C2 FOREIGN KEY (idUtilisateur) REFERENCES LesClients ( idUtilisateur ) on delete cascade,
    constraint Reservation_C3 check (prix >= 0),
    constraint Reservation_C4 check (idReservation between 0 and 99999)
);

CREATE TABLE LesPaquetsPlaces (
    jour   DATE,
    tarifSansCateg        number(5),
    tarifCateg1        number(5),
    tarifCateg2        number(5),
    nbPlacesCateg1        number(5),
    nbPlacesCateg2        number(5),
    nbPlacesRestantesCateg1 number(5),
    nbPlacesRestantesCateg2 number(5),
    nbPlacesRestantesSansCateg number(5),
    idFestival   VARCHAR(10),
    CONSTRAINT Place_C1  PRIMARY KEY ( jour, idFestival),
     constraint LesPaquetsPlaces_C3 check (nbPlacesCateg1 >= 0),
   constraint LesPaquetsPlaces_C4 check (nbPlacesCateg2 >= 0),
    constraint LesPaquetsPlaces_C5 check (nbPlacesRestantesCateg1 >= 0),
     constraint LesPaquetsPlaces_C6 check (nbPlacesRestantesCateg2 >= 0),
     constraint LesPaquetsPlaces_C7 check (nbPlacesRestantesSansCateg >= 0),
    constraint LesPaquetsPlaces_C8 check ( tarifSansCateg>= 0),
   constraint LesPaquetsPlaces_C9 check ( tarifCateg1>= 0),
    constraint LesPaquetsPlaces_C10 check ( tarifCateg2>= 0),
    CONSTRAINT fk_Place_C2  FOREIGN KEY (idFestival) REFERENCES LesFestivals ( idFestival ) on delete cascade
);

CREATE TABLE LesLogements (
   numLogement   number(10),
   CONSTRAINT LesLogement_C1  PRIMARY KEY ( numLogement )
);

CREATE TABLE LesPeriodeDeDisponibilites (
    numLogement   number(10),
    dateDispo  DATE ,
    CONSTRAINT PeriodeDeDisponibilite_C1  PRIMARY KEY ( numLogement, dateDispo ),
    CONSTRAINT fk_PeriodeDeDisponibilite_C2 FOREIGN KEY (numLogement) REFERENCES LesLogements ( numLogement ) on delete cascade
);

CREATE TABLE LesReservationLogements (
    idReservation   number(5),
    numLogement       number(10),
    prixResLogement number(5),
    nbPlaceAdulte  number(2),
    nbPlaceEnfant  number(2),
    dateDHebergement  DATE,
    dateFHebergement DATE,
    dateReservation DATE,
    CONSTRAINT LesReservationH_C1 PRIMARY KEY ( idReservation, numLogement),
    CONSTRAINT fk_LesReservationH_C2 FOREIGN KEY (idReservation) REFERENCES LesReservations ( idReservation ) on delete cascade,
    constraint LesReservation_C4 check ( prixResLogement>= 0),
    constraint LesReservation_C5 check ( nbPlaceAdulte>= 0),
      constraint LesReservation_C6 check ( nbPlaceEnfant>= 0),
    CONSTRAINT    fk_LesReservationH_C3 FOREIGN KEY (numLogement) REFERENCES LesLogements ( numLogement ) on delete cascade
);


CREATE TABLE LesAvis (
    idAvis   number(5),
    note        number(2),
     idReservation number(5),
    CONSTRAINT Avis_C1  PRIMARY KEY ( idAvis ),
    CONSTRAINT    fk_Avis_C2 
	FOREIGN KEY (idReservation) 
        REFERENCES LesReservations ( idReservation ) on delete cascade,
    CONSTRAINT Avis_C3 check (note between 0 and 10 ),
    CONSTRAINT Avis_C4 check (idAvis between 0 and 99999 )
);


CREATE TABLE LesPhotos (
    idPhoto   number(5),
     idHebergement NUMBER(10),
    CONSTRAINT Photo_C1  PRIMARY KEY ( idPhoto ),
    CONSTRAINT    fk_Photo_C2 
	FOREIGN KEY (idHebergement) 
        REFERENCES LesHebergements ( idHebergement ) on delete cascade,
    CONSTRAINT Photo_C4 check (idPhoto between 0 and 99999 )
);


CREATE TABLE LesHotels (
   idHebergement   NUMBER(10),
   capaciteAccueil number(20),
   nbChambres        number(20),
   CONSTRAINT HebergementHotel_C1  PRIMARY KEY ( idHebergement ),
   CONSTRAINT fk_HebergementHotel_C2 FOREIGN KEY (idHebergement) REFERENCES LesHebergements ( idHebergement ) on delete cascade
);


CREATE TABLE LesResidences (
    idHebergement   NUMBER(10),
    capaciteAccueil        number(20),
    nbUniteHabitationRes        number(20),
    CONSTRAINT HebergementResidence_C1  PRIMARY KEY ( idHebergement ),
    CONSTRAINT fk_HebergementResidence_C2 FOREIGN KEY (idHebergement) REFERENCES LesHebergements ( idHebergement ) on delete cascade
);

CREATE TABLE LesCampings (
    idHebergement   NUMBER(10),
    nbEmplacement        number(10),
    CONSTRAINT HebergementCamping  PRIMARY KEY ( idHebergement ),
    CONSTRAINT fk_HebergementCamping_C2 FOREIGN KEY (idHebergement) REFERENCES LesHebergements ( idHebergement ) on delete cascade
);


CREATE TABLE LesVillageVacances (
    idHebergement   NUMBER(10),
    capaciteAccueil        number(20),
    nbUniteHabitationVil number(20),
    CONSTRAINT HebergementVillageVacances_C1  PRIMARY KEY ( idHebergement ),
    CONSTRAINT  fk_HebergementVillageV_C2 FOREIGN KEY (idHebergement) REFERENCES LesHebergements ( idHebergement ) on delete cascade
);


CREATE TABLE LesChambres (
    numLogement   number(10),
    tarifAdulte        number(5),
    tarifEnfant number(5),
    nbAdultes        number(2),
    nbEnfants number(2),
    typeChambre Varchar(1),
    idHebergement NUMBER(10),
    CONSTRAINT Chambre_C1  PRIMARY KEY ( numLogement,idHebergement ),
    CONSTRAINT    fk_Chambre_C2 FOREIGN KEY (idHebergement)REFERENCES LesHotels ( idHebergement ) on delete cascade,
    CONSTRAINT    fk_Chambre_C3 FOREIGN KEY (numLogement) REFERENCES LesLogements ( numLogement ) on delete cascade,
    constraint Chambre_C4 check (tarifAdulte > 0),
    constraint Chambre_C5 check (tarifEnfant > 0),
    constraint Chambre_C6 check (nbAdultes >= 0),
    constraint Chambre_C7 check (nbEnfants >= 0)
);

CREATE TABLE LesLogementsResidences (
    numLogement   number(10),
    tarif        number(5),
     nbPersonneConseille number(5),
     idHebergement NUMBER(10),
    CONSTRAINT Residence_C1  PRIMARY KEY ( numLogement, idHebergement),
    CONSTRAINT fk_Residence_C2 FOREIGN KEY (idHebergement) REFERENCES LesResidences ( idHebergement ) on delete cascade,
    CONSTRAINT  fk_Residence_C3 FOREIGN KEY (numLogement) REFERENCES LesLogements ( numLogement ) on delete cascade,
    constraint Residence_C4 check (tarif >= 0)
);

CREATE TABLE LesEmplacementsVillages (
    numLogement   number(10),
    idHebergement NUMBER(10),
    CONSTRAINT VillageVacance_C1 PRIMARY KEY ( numLogement,idHebergement ),
    CONSTRAINT fk_VillageVacance_C2 FOREIGN KEY (idHebergement) REFERENCES LesVillageVacances ( idHebergement ) on delete cascade,
    CONSTRAINT fk_VillageVacance_C3 FOREIGN KEY (numLogement) REFERENCES LesLogements ( numLogement ) on delete cascade
);

CREATE TABLE LesEmplacementsChambres (
    numLogement   number(5),
    tarifAdulte  number(5),
    tarifEnfant number(5),
    nbAdultes   number(2),
    nbEnfants number(2),
    idHebergement NUMBER(10),
    CONSTRAINT VillageVacancesChambre_C1  PRIMARY KEY ( numLogement, idHebergement),
    CONSTRAINT  fk_VillageVacancesChambre_C2 FOREIGN KEY (numLogement, idHebergement) REFERENCES LesEmplacementsVillages ( numLogement, idHebergement ) on delete cascade,
    constraint VillageVacancesChambre_C3 check (tarifAdulte > 0),
    constraint VillageVacancesChambre_C4 check (tarifEnfant > 0),
    constraint VillageVacancesChambre_C5 check (nbAdultes >= 0),
    constraint VillageVacancesChambre_C6 check (nbEnfants >= 0)
);



CREATE TABLE LesEmplacementsCollectifs (
    numLogement   number(10),
    tarif        number(5),
    idHebergement NUMBER(10),
    CONSTRAINT VillageVacancesCollectif_C1  PRIMARY KEY ( numLogement ,idHebergement),
    CONSTRAINT    fk_VillageVacancesCollectif_C2 FOREIGN KEY (numLogement ,idHebergement) REFERENCES LesEmplacementsVillages ( numLogement,  idHebergement) on delete cascade
);


CREATE TABLE LesLogementsCampings (
    numLogement   number(10),
    tarif        number(5),
    idHebergement        NUMBER(10),
    CONSTRAINT Camping_C1  PRIMARY KEY ( numLogement,idHebergement ),
    CONSTRAINT fk_Camping_C2 FOREIGN KEY (numLogement) REFERENCES LesLogements ( numLogement ) on delete cascade,
    CONSTRAINT    fk_Camping_C3  FOREIGN KEY (idHebergement) REFERENCES LesCampings ( idHebergement ) on delete cascade
);





insert into LesUtilisateurs(idUtilisateur,nomUtilisateur,nom,prenom,mail,tel,adresse) values ('1','LamineBOUAZIZ','BOUAZIZ','Lamine','lam.bouaziz96@gmail.com','0768385020','8 chemin Duhamel, 38700 La Tronche');
insert into LesUtilisateurs(idUtilisateur,nomUtilisateur,nom,prenom,mail,tel,adresse) values ('2','ILYESBEL','BELEHADJI','Ilyes','il@gmail.com','0768388020','8 chemin Duhamel, 38700 La Tronche');
insert into LesUtilisateurs(idUtilisateur,nomUtilisateur,nom,prenom,mail,tel,adresse) values ('3','AlyFall','FALL','Aly','fallAly96@gmail.com','0788385020','8 chemin Duhamel, 38700 La Tronche');
insert into LesUtilisateurs(idUtilisateur,nomUtilisateur,nom,prenom,mail,tel,adresse) values ('4','JHONMAS','MASSOU','JHON','JM@gmail.com','0668385020','8 chemin Duhamel, 38700 La Tronche');
insert into LesUtilisateurs(idUtilisateur,nomUtilisateur,nom,prenom,mail,tel,adresse) values ('5','BERNARDFUJ','FUJ','Bernard','FB6@gmail.com','0764585020','8 chemin Duhamel, 38700 La Tronche');
insert into LesUtilisateurs(idUtilisateur,nomUtilisateur,nom,prenom,mail,tel,adresse) values ('6','CARLOSG','GHON','CARLOS','CG6@gmail.com','0788385020','8 chemin Duhamel, 38700 La Tronche');
insert into LesUtilisateurs(idUtilisateur,nomUtilisateur,nom,prenom,mail,tel,adresse) values ('7','MANUMAC','BRUNON','SEBASTIEN','MB@gmail.com','0768986020','8 chemin Duhamel, 38700 La Tronche');
insert into LesUtilisateurs(idUtilisateur,nomUtilisateur,nom,prenom,mail,tel,adresse) values ('8','MANBLA','BLANC','Manon','MM@gmail.com','0745455020','8 chemin Duhamel, 38700 La Tronche');
insert into LesUtilisateurs(idUtilisateur,nomUtilisateur,nom,prenom,mail,tel,adresse) values ('9','jaja','NAKAMURA','Michelle','NM@gmail.com','0768385020','8 chemin Duhamel, 38700 La Tronche');


insert into LesOrganisateurs(idUtilisateur) values ('1');
insert into LesOrganisateurs(idUtilisateur) values ('2');
insert into LesOrganisateurs(idUtilisateur) values ('3');

insert into LesHebergeurs(idUtilisateur) values ('4');
insert into LesHebergeurs(idUtilisateur) values ('5');
insert into LesHebergeurs(idUtilisateur) values ('6');


insert into LesClients(idUtilisateur) values ('7');
insert into LesClients(idUtilisateur) values ('8');
insert into LesClients(idUtilisateur) values ('9');

show errors;









