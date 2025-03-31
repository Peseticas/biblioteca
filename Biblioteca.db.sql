BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Libro" (
	"Codigo"	INTEGER NOT NULL,
	"isbn"	TEXT NOT NULL UNIQUE,
	"titulo"	TEXT NOT NULL,
	"año_publicacion"	INTEGER NOT NULL,
	"puntuacion"	REAL NOT NULL,
	PRIMARY KEY("Codigo")
);
CREATE TABLE IF NOT EXISTS "Socio" (
	"Codigo"	INTEGER NOT NULL,
	"dni"	TEXT NOT NULL,
	"domicilio"	TEXT NOT NULL,
	"telefono"	TEXT NOT NULL,
	"correo"	TEXT NOT NULL,
	PRIMARY KEY("Codigo")
);
CREATE TABLE IF NOT EXISTS "prestamo" (
	"codigo_libro"	INTEGER NOT NULL,
	"codigo_socio"	INTEGER,
	"fecha_inicio"	TEXT NOT NULL,
	"fecha_fin"	TEXT NOT NULL,
	"fecha_devolucion"	TEXT,
	PRIMARY KEY("codigo_libro","codigo_socio","fecha_inicio"),
	FOREIGN KEY("codigo_libro") REFERENCES "Libro"("Codigo"),
	FOREIGN KEY("codigo_socio") REFERENCES "Socio"("Codigo")
);
COMMIT;
