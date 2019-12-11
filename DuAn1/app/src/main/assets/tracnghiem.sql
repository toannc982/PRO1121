BEGIN TRANSACTION;
DROP TABLE IF EXISTS "tracnghiem";
CREATE TABLE IF NOT EXISTS "tracnghiem" (
	"_id"	INTEGER NOT NULL DEFAULT (null),
	"question"	TEXT,
	"ans_a"	TEXT,
	"ans_b"	TEXT,
	"ans_c"	TEXT,
	"ans_d"	TEXT,
	"result"	TEXT,
	"num_exam"	INTEGER,
	"subject"	TEXT,
	"image"	TEXT,
	PRIMARY KEY("_id")
);
INSERT INTO "tracnghiem" VALUES (1,'Số liền trước và liền sau của số 17 là:','15 và 16','15 và 18','16 và 18    ','15 và 17','C',1,'toan','help');
INSERT INTO "tracnghiem" VALUES (2,'Số gồm 8 chục và 2 đơn vị là','28   ','92 ','82 ','80','C',1,'toan','your');
INSERT INTO "tracnghiem" VALUES (3,'Phép tình nào dưới đây có kết quả bằng 80','50 + 40','40 + 40','90 - 20','50 - 30','B',1,'toan','cong');
INSERT INTO "tracnghiem" VALUES (4,'Phép tính nào dưới đây có kết quả lớn hơn 60','40 + 20','40 + 30','90 - 30','80 - 30','B',1,'toan','cong');
INSERT INTO "tracnghiem" VALUES (5,'Số liền sau số 36 là','37','35','47','25','A',1,'toan','number');
INSERT INTO "tracnghiem" VALUES (6,'Số liền trước số 33 là','34','31','44','32','D',1,'toan','number');
INSERT INTO "tracnghiem" VALUES (7,'Số tròn chục lớn nhất có 2 chữ số','10','90','100','99','B',1,'toan','so');
INSERT INTO "tracnghiem" VALUES (8,'Số bé nhất có hai chữ số giống nhau là','10','98','19','11','D',1,'toan','small');
INSERT INTO "tracnghiem" VALUES (9,'Số lớn nhất có hai chữ số','100','98','99','90','C',1,'toan','big');
INSERT INTO "tracnghiem" VALUES (10,'Cả hai chị em có 50 cái kẹo, chị có 10 cái kẹo. Vậy em có:','60 ','40','50','30','B',1,'toan','family');
INSERT INTO "tracnghiem" VALUES (11,'What is the most popular sport?','BaseBall','BacketBall','Golf','Football','D',1,'tienganh','football');
INSERT INTO "tracnghiem" VALUES (12,'The football match was postponed _________the bad weather.
despite',' in spite ','because ','because of','aren’t working','D',1,'tienganh','weather');
INSERT INTO "tracnghiem" VALUES (13,'How many cards can you see?','One','Two','Three','Four','D',1,'tienganh','card');
INSERT INTO "tracnghiem" VALUES (14,'If I had the map now, I _________a short-cut across the desert.','could have taken','take','could take','can take','C',1,'tienganh','map');
INSERT INTO "tracnghiem" VALUES (15,'
What is this character?','Angle','Devil','Kid','Doctor','B',1,'tienganh','devil');
INSERT INTO "tracnghiem" VALUES (16,'What is this?','Ball','Cube','Dice','Chair','C',1,'tienganh','dice');
INSERT INTO "tracnghiem" VALUES (17,'The capital of Thailand is_________.','London','Paris','Bangkok',' Phnom Penh','C',1,'tienganh','bangkok');
INSERT INTO "tracnghiem" VALUES (18,'If I _________you, I’d take some rest before the exam tomorrow.','is','are','were','would be','C',1,'tienganh','sleep');
INSERT INTO "tracnghiem" VALUES (19,'What is this country?','France','Italy','United Kingdom','Japan','C',1,'tienganh','england');
INSERT INTO "tracnghiem" VALUES (20,'What is this?','T-Shirt','Hat','Shirt','Shoe','A',1,'tienganh','tshirt');


COMMIT;
