drop table LesHebergementVillageVacances;
drop table LesHebergementCampings;
drop table LesHebergementResidences;
drop table LesHebergementHotels;
drop table LesCampings;
drop table LesVillageVacancesCollectifs;
drop table LesVillageVacancesChambres;
drop table LesVillageVacances;
drop table LesResidences;
drop table LesChambres;
drop table LesLogements;
drop table LesPhotos;
drop table LesAvis;
drop table LesReservationHebergements;
drop table LesPeriodeDeDisponibilite;
drop table LesHebergements FORCE;
drop table LesReservationPlaces;
drop table LesPlaces;
drop table LesReservations;
drop table LesFestivals;
drop table LesHebergeurs FORCE;
drop table LesClients;
drop table LesOrganisateurs;
drop table LesUtilisateurs ;



CREATE TABLE LesUtilisateurs (
    idUtilisateur   number(5),
    nomUtilisateur  VARCHAR2(20) NOT NULL,
    nom VARCHAR2(20) NOT NULL,
    prenom     VARCHAR2(20) NOT NULL,
    mail      VARCHAR2(30),
    tel        VARCHAR2(10),
    adresse  VARCHAR2(30),
    CONSTRAINT Utilisateur_C1 PRIMARY KEY ( idUtilisateur ),
    constraint Utilisateur_C2 check (idUtilisateur between 1 and 99999)
);


CREATE TABLE LesOrganisateurs (
    idUtilisateur   number(5),
    CONSTRAINT  Organisateu_C1 PRIMARY KEY ( idUtilisateur ),
    CONSTRAINT    fk_Organisateur_C2 
	FOREIGN KEY (idUtilisateur) 
    	REFERENCES LesUtilisateurs ( idUtilisateur ) on delete cascade,
    constraint Organisateur_C3 check (idUtilisateur between 1 and 99999)
);


CREATE TABLE LesClients (
    idUtilisateur   NUMBER(5),
    CONSTRAINT Client_C1 PRIMARY KEY ( idUtilisateur ),
    CONSTRAINT fk_Client_C2 
	FOREIGN KEY (idUtilisateur) 
        REFERENCES LesUtilisateurs ( idUtilisateur ) on delete cascade,
    constraint Client_3 check (idUtilisateur between 1 and 99999)
);



CREATE TABLE LesHebergeurs (
    idUtilisateur   NUMBER(5),
    CONSTRAINT Hebergeur_C1 PRIMARY KEY ( idUtilisateur ),
    CONSTRAINT    fk_Hebergeur_C2 FOREIGN KEY (idUtilisateur) REFERENCES LesUtilisateurs ( idUtilisateur ) on delete cascade,
         constraint Hebergeur_C3 check (idUtilisateur between 1 and 99999)
);

CREATE TABLE LesFestivals (
    idFestival   VARCHAR2(10),
    nomFestival  VARCHAR2(20) NOT NULL,
    domaine      VARCHAR2(20) NOT NULL,
    complementDomaine  VARCHAR2(20) NOT NULL,
    departement      NUMBER(2),
    periodicite        VARCHAR2(20),
    moiHabDebut    VARCHAR2(20),
    siteWeb    VARCHAR2(30),
    commune    VARCHAR2(30),
    dateDebut   DATE,
    dateFin    DATE,
    dateCreation    DATE,
    codeINSEE    NUMBER(5),
    coordonneesINSEE    FLOAT(20),
    nomDepartement    VARCHAR2(100),
    nbPlaceLouees    NUMBER(3),
    dateAjout    DATE,
    idUtilisateur NUMBER(5),
    CONSTRAINT Festival_C1 PRIMARY KEY ( idFestival ),
    CONSTRAINT fk_Festival_C2 FOREIGN KEY (idUtilisateur) REFERENCES LesOrganisateurs ( idUtilisateur ) on delete cascade  
);

CREATE TABLE LesReservations (
    idReservation   number(5),
    prix number (10) NOT NULL,
    status CHAR(1),
    idUtilisateur number(5),
    CONSTRAINT Reservation_C1  PRIMARY KEY ( idReservation ),
    CONSTRAINT fk_Reservation_C2 FOREIGN KEY (idUtilisateur) REFERENCES LesClients ( idUtilisateur ) on delete cascade,
    constraint Reservation_C3 check (prix >= 0),
    constraint Reservation_C4 check (idReservation between 1 and 99999)
);

CREATE TABLE LesPlaces (
    idPlace   number(10),
    tarif        number(5),
    tarifAdditionnel number(5),
    categoriePlace number(1),
    idFestival   VARCHAR2(10),
    CONSTRAINT Place_C1  PRIMARY KEY ( idPlace ),
    CONSTRAINT fk_Place_C2  FOREIGN KEY (idFestival) REFERENCES LesFestivals ( idFestival ) on delete cascade
);

CREATE TABLE LesReservationPlaces (
    idReservation   number(5),
    idPlace      number(10),
    nbPlace  number(2),
    dateReservation DATE,
    CONSTRAINT LesReservationPlaces_C1  PRIMARY KEY ( idReservation, idPlace ),
    CONSTRAINT  fk_LesReservationPlaces_C2 
	FOREIGN KEY (idReservation) 
        REFERENCES LesReservations ( idReservation ) on delete cascade,
    CONSTRAINT    fk_LesReservationPlaces_C3  FOREIGN KEY (idPlace) REFERENCES LesPlaces ( idPlace ) on delete cascade,
    constraint ReservationPlace_C6 check (nbPlace between 1 and 99)
);



CREATE TABLE LesHebergements (
  idHebergement   number(10),
  dateDePublication       DATE,
  nomCommercial VARCHAR2(50) NOT NULL,
  dateDeClassement     DATE,
  nomCategorie      VARCHAR2(50),
  classement        VARCHAR2(20),
  adresse    VARCHAR2(40),
  categorie    VARCHAR2(30),
  codePostal    number(5),
  commune   VARCHAR2(40),
  numTel    number(20),
  courreil    VARCHAR2(50),
  siteNet    VARCHAR2(50),
  coordonnees    FLOAT(10),
  nomEPCI    FLOAT(10),
  nomDep     VARCHAR2(40),
  nomRegion     VARCHAR2(40),
  Description      VARCHAR2(100),
  dateAjout       DATE,
  idUtilisateur number(5),
  CONSTRAINT LesHebergements_C1 PRIMARY KEY ( idHebergement ),
  CONSTRAINT    fk_LesHebergements_C2  FOREIGN KEY (idUtilisateur) REFERENCES LesHebergeurs ( idUtilisateur ) on delete cascade   
);

CREATE TABLE LesPeriodeDeDisponibilites (
    idPerdiode   number(5),
    dateDebutDispo  DATE,
    dateFinDispo  DATE,
    idHebergement number(10),
    CONSTRAINT PeriodeDeDisponibilite_C1  PRIMARY KEY ( idPerdiode ),
    CONSTRAINT fk_PeriodeDeDisponibilite_C2 FOREIGN KEY (idHebergement) REFERENCES LesHebergements ( idHebergement ) on delete cascade
);


CREATE TABLE LesReservationHebergements (
    idReservation   number(5),
    idHebergement       number(10),
    nbPlaceAdulte  number(2),
    nbPlaceEnfant  number(2),
    dateDHebergement  DATE,
    dateFHebergement DATE,
    dateReservation DATE,
    CONSTRAINT LesReservationH_C1 PRIMARY KEY ( idReservation,  idHebergement),
    CONSTRAINT fk_LesReservationH_C2 FOREIGN KEY (idReservation) REFERENCES LesReservations ( idReservation ) on delete cascade,
    CONSTRAINT    fk_LesReservationH_C3 FOREIGN KEY (idHebergement) REFERENCES LesHebergements ( idHebergement ) on delete cascade
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
    CONSTRAINT Avis_C4 check (idAvis between 1 and 99999 )
);


CREATE TABLE LesPhotos (
    idPhoto   number(5),
     idHebergement number(10),
    CONSTRAINT Photo_C1  PRIMARY KEY ( idPhoto ),
    CONSTRAINT    fk_Photo_C2 
	FOREIGN KEY (idHebergement) 
        REFERENCES LesHebergements ( idHebergement ) on delete cascade,
    CONSTRAINT Photo_C4 check (idPhoto between 1 and 99999 )
);


CREATE TABLE LesLogements (
    idLogement   number(10),
   CONSTRAINT LesLogement_C1  PRIMARY KEY ( idLogement )
);


CREATE TABLE LesChambres (
    idLogement   number(10),
    tarifAdulte        number(5),
     tarifEnfant number(5),
    nbAdultes        number(2),
     nbEnfants number(2),
     idHebergement number(10),
    CONSTRAINT Chambre_C1  PRIMARY KEY ( idLogement ),
    CONSTRAINT    fk_Chambre_C2 FOREIGN KEY (idHebergement)REFERENCES LesHebergements ( idHebergement ) on delete cascade,
    CONSTRAINT    fk_Chambre_C3 FOREIGN KEY (idLogement) REFERENCES LesLogements ( idLogement ) on delete cascade,
    constraint Chambre_C4 check (tarifAdulte > 0),
    constraint Chambre_C5 check (tarifEnfant > 0),
    constraint Chambre_C6 check (nbAdultes >= 0),
    constraint Chambre_C7 check (nbEnfants >= 0)
);

CREATE TABLE LesResidences (
    idLogement   number(10),
    tarif        number(5),
     nbPersonneConseille number(5),
     idHebergement number(10),
    CONSTRAINT Residence_C1  PRIMARY KEY ( idLogement ),
    CONSTRAINT fk_Residence_C2 
	FOREIGN KEY (idHebergement) 
        REFERENCES LesHebergements ( idHebergement ) on delete cascade,
    CONSTRAINT  fk_Residence_C3 
	FOREIGN KEY (idLogement) 
        REFERENCES LesLogements ( idLogement ) on delete cascade,
    constraint Residence_C4 check (tarif >= 0)
);

CREATE TABLE LesVillageVacances (
    idLogement   number(10),
    idHebergement number(10),
    CONSTRAINT VillageVacance_C1 PRIMARY KEY ( idLogement ),
    CONSTRAINT fk_VillageVacance_C2
	 FOREIGN KEY (idHebergement) 
         REFERENCES LesHebergements ( idHebergement ) on delete cascade,
    CONSTRAINT fk_VillageVacance_C3
	 FOREIGN KEY (idLogement) 
         REFERENCES LesLogements ( idLogement ) on delete cascade
);

CREATE TABLE LesVillageVacancesChambres (
    idLogement   number(5),
    tarifAdulte  number(5),
    tarifEnfant number(5),
    nbAdultes   number(2),
    nbEnfants number(2),
    CONSTRAINT VillageVacancesChambre_C1  PRIMARY KEY ( idLogement ),
    CONSTRAINT  fk_VillageVacancesChambre_C2  
	FOREIGN KEY (idLogement) 
        REFERENCES LesLogements ( idLogement ) on delete cascade,
    constraint VillageVacancesChambre_C3 check (tarifAdulte > 0),
    constraint VillageVacancesChambre_C4 check (tarifEnfant > 0),
    constraint VillageVacancesChambre_C5 check (nbAdultes >= 0),
    constraint VillageVacancesChambre_C6 check (nbEnfants >= 0)
);



CREATE TABLE LesVillageVacancesCollectifs (
    idLogement   number(10),
    tarif        number(5),
    CONSTRAINT VillageVacancesCollectif_C1  PRIMARY KEY ( idLogement ),
    CONSTRAINT    fk_VillageVacancesCollectif_C2 
	FOREIGN KEY (idLogement) 
        REFERENCES LesLogements ( idLogement ) on delete cascade
);


CREATE TABLE LesCampings (
    idLogement   number(10),
    tarif        number(5),
    idHebergement        number(10),
    CONSTRAINT Camping_C1  PRIMARY KEY ( idLogement ),
    CONSTRAINT fk_Camping_C2 
	FOREIGN KEY (idLogement) 
        REFERENCES LesLogements ( idLogement ) on delete cascade,
    CONSTRAINT    fk_Camping_C3  
	FOREIGN KEY (idHebergement) 
        REFERENCES LesHebergements ( idHebergement ) on delete cascade
);


CREATE TABLE LesHebergementHotels (
   idHebergement   number(10),
   capaciteAccueil number(5),
   nbChambre        number(5),
   CONSTRAINT HebergementHotel_C1  PRIMARY KEY ( idHebergement ),
   CONSTRAINT fk_HebergementHotel_C2 
	FOREIGN KEY (idHebergement) 
        REFERENCES LesHebergements ( idHebergement ) on delete cascade
);


CREATE TABLE LesHebergementResidences (
    idHebergement   number(10),
    capaciteAccueil        number(5),
    nbUniteHabitationRes        number(5),
    CONSTRAINT HebergementResidence_C1  PRIMARY KEY ( idHebergement ),
    CONSTRAINT fk_HebergementResidence_C2
	 FOREIGN KEY (idHebergement) 
         REFERENCES LesHebergements ( idHebergement ) on delete cascade
);

CREATE TABLE LesHebergementCampings (
    idHebergement   number(10),
    nbEmplacement        number(10),
    CONSTRAINT HebergementCamping  PRIMARY KEY ( idHebergement ),
    CONSTRAINT fk_HebergementCamping_C2 
	FOREIGN KEY (idHebergement) 
        REFERENCES LesHebergements ( idHebergement ) on delete cascade
);


CREATE TABLE LesHebergementVillageVacances (
    idHebergement   number(10),
    capaciteAccueil        number(5),
    nbUniteHabitationVil number(5),
    CONSTRAINT HebergementVillageVacances_C1  PRIMARY KEY ( idHebergement ),
    CONSTRAINT  fk_HebergementVillageV_C2
	 FOREIGN KEY (idHebergement) 
         REFERENCES LesHebergements ( idHebergement ) on delete cascade
);


show errors;




