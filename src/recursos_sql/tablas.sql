-- Describe MESA
CREATE TABLE mesa (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombre" TEXT,
    "descripcion" TEXT
, "estado" INTEGER   DEFAULT (1));

CREATE TABLE categoria (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombre" TEXT,
    "descripcion" TEXT
, "estado" INTEGER   DEFAULT (1));

-- Describe PLATO
CREATE TABLE plato (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "tipo_id" INTEGER NOT NULL,
    "nombre" TEXT NOT NULL,
    "descripcion" TEXT,
    "precio" TEXT
, "estado" INTEGER   DEFAULT (1));

-- Describe TIPO
CREATE TABLE tipo (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "categoria_id" INTEGER NOT NULL,
    "nombre" TEXT,
    "descripcion" TEXT,
    "color" TEXT NOT NULL
, "estado" INTEGER   DEFAULT (1));

-- Describe TRABAJADOR
CREATE TABLE trabajador (
    "id"   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "cedula" TEXT NOT NULL,
    "nombre" TEXT NOT NULL,
    "apellidos" TEXT NOT NULL,
    "correo" TEXT,
    "telefono" TEXT
, "user_id" INTEGER);

-- Describe TIPO
CREATE TABLE tipo (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "categoria_id" INTEGER NOT NULL,
    "nombre" TEXT,
    "descripcion" TEXT,
    "estado" INTEGER DEFAULT (1),
    "azul" INTEGER,
    "rojo" INTEGER,
    "verde" INTEGER
);

insert into categoria (nombre,descripcion) values ('Bebidas','Liquidos y mas');
insert into categoria (nombre,descripcion) values ('Jugos','Liquidos y mas');
insert into categoria (nombre,descripcion) values ('Entradas','Liquidos y mas');
