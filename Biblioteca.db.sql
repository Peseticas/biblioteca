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


INSERT INTO libro (código, isbn, título, escritor, año_publicación, puntuación) VALUES
(1, '978-3-16-148410-0', 'Cien años de soledad', 'Gabriel García Márquez', 1967, 4.8),
(2, '978-0-14-143951-8', 'Orgullo y prejuicio', 'Jane Austen', 1813, 4.7),
(3, '978-0-452-28423-4', '1984', 'George Orwell', 1949, 4.6),
(4, '978-84-376-0494-7', 'El Quijote', 'Miguel de Cervantes', 1605, 4.9),
(5, '978-0-7432-7356-5', 'Los juegos del hambre', 'Suzanne Collins', 2008, 4.5),
(6, '978-84-670-6006-6', 'La sombra del viento', 'Carlos Ruiz Zafón', 2001, 4.7),
(7, '978-84-204-9886-1', 'El nombre del viento', 'Patrick Rothfuss', 2007, 4.8),
(8, '978-84-206-8192-4', 'Los pilares de la Tierra', 'Ken Follett', 1989, 4.6),
(9, '978-0-06-112008-4', 'Matar a un ruiseñor', 'Harper Lee', 1960, 4.9),
(10, '978-84-376-0493-0', 'Don Quijote de la Mancha', 'Miguel de Cervantes', 1615, 4.9);

INSERT INTO socio (código, dni, nombre, domicilio, teléfono, correo) VALUES
(1, '12345678A', 'Juan Pérez', 'Calle Falsa 123', '600123456', 'juan.perez@email.com'),
(2, '87654321B', 'María Gómez', 'Avenida Siempre Viva 742', '601987654', 'maria.gomez@email.com'),
(3, '11223344C', 'Carlos López', 'Paseo del Prado 23', '602345678', 'carlos.lopez@email.com'),
(4, '22334455D', 'Ana Fernández', 'Calle Gran Vía 45', '603567890', 'ana.fernandez@email.com'),
(5, '33445566E', 'Luis Martínez', 'Plaza Mayor 12', '604678901', 'luis.martinez@email.com'),
(6, '44556677F', 'Elena Rodríguez', 'Calle de la Luna 8', '605789012', 'elena.rodriguez@email.com'),
(7, '55667788G', 'Pedro Sánchez', 'Calle Sol 15', '606890123', 'pedro.sanchez@email.com'),
(8, '66778899H', 'Sofía Ramírez', 'Avenida del Mar 22', '607901234', 'sofia.ramirez@email.com'),
(9, '77889900I', 'Jorge Díaz', 'Paseo de la Estrella 5', '608012345', 'jorge.diaz@email.com'),
(10, '88990011J', 'Lucía Torres', 'Calle Olvido 7', '609123456', 'lucia.torres@email.com');

INSERT INTO préstamo (código_libro, código_socio, fecha_inicio, fecha_fin, fecha_devolución) VALUES
(1, 1, '2025-03-01', '2025-03-15', '2025-03-14'),
(2, 2, '2025-03-05', '2025-03-20', NULL),
(3, 3, '2025-03-10', '2025-03-25', NULL),
(4, 4, '2025-03-15', '2025-03-30', '2025-03-29'),
(5, 5, '2025-03-20', '2025-04-05', NULL),
(6, 6, '2025-03-22', '2025-04-05', NULL),
(7, 7, '2025-03-23', '2025-04-07', '2025-04-06'),
(8, 8, '2025-03-24', '2025-04-08', NULL),
(9, 9, '2025-03-25', '2025-04-09', '2025-04-08'),
(10, 10, '2025-03-26', '2025-04-10', NULL);


