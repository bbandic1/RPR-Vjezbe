BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Grad" (
	"Grad_ID"	INTEGER,
	"Naziv"	TEXT,
	"Broj_Stanovnika"	TEXT,
	"Drzava_ID"	INTEGER,
	PRIMARY KEY("Grad_ID"),
	FOREIGN KEY("Drzava_ID") REFERENCES "Drzava"("Drzava_ID")
);
CREATE TABLE IF NOT EXISTS "Drzava" (
	"Drzava_ID"	INTEGER,
	"Naziv"	TEXT,
	"Glavni_Grad"	INTEGER,
	PRIMARY KEY("Drzava_ID"),
	FOREIGN KEY("Glavni_Grad") REFERENCES "Grad"("Grad_ID")
);
INSERT INTO "Grad" VALUES (1,'Pariz','2102650',1);
INSERT INTO "Grad" VALUES (2,'London','1714142',2);
INSERT INTO "Grad" VALUES (3,'Beč','552000',3);
INSERT INTO "Grad" VALUES (4,'Manchester','552000',2);
INSERT INTO "Grad" VALUES (5,'Graz','280800',3);
INSERT INTO "Drzava" VALUES (1,'Francuska',1);
INSERT INTO "Drzava" VALUES (2,'Velika Britanija',2);
INSERT INTO "Drzava" VALUES (3,'Austrija',3);
COMMIT;
