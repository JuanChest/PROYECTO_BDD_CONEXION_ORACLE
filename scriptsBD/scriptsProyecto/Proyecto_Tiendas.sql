/* Autores: Juan Cofre | Jhordy Parra
/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     1/6/2025 15:10:46                            */
/*==============================================================*/
/* Borrar tablas si existen */

DROP TABLE DETALLE_VENTA CASCADE CONSTRAINTS;
DROP TABLE EMPLEADO CASCADE CONSTRAINTS;
DROP TABLE INVENTARIO CASCADE CONSTRAINTS;
DROP TABLE PRODUCTO CASCADE CONSTRAINTS;
DROP TABLE PROVEEDOR CASCADE CONSTRAINTS;
DROP TABLE REGION CASCADE CONSTRAINTS;
DROP TABLE TIENDA CASCADE CONSTRAINTS;
DROP TABLE PRODUCTO_INVENTARIO CASCADE CONSTRAINTS;
DROP TABLE VENTAS CASCADE CONSTRAINTS;
DROP TABLE CATEGORIA CASCADE CONSTRAINTS;
DROP TABLE CLIENTE CASCADE CONSTRAINTS;

/*==============================================================*/
/* Tabla: CATEGORIA                                             */
/*==============================================================*/
CREATE TABLE CATEGORIA (
                           CATEGORIA_ID     INTEGER NOT NULL,
                           NOMBRE_CATEGORIA VARCHAR2(50) NOT NULL,
                           DESCRIPCION      CLOB,
                           CONSTRAINT PK_CATEGORIA PRIMARY KEY (CATEGORIA_ID)
);

/*==============================================================*/
/* Tabla: CLIENTE                                               */
/*==============================================================*/
CREATE TABLE CLIENTE (
                         CLIENTE_ID INTEGER NOT NULL,
                         NOMBRE     VARCHAR2(50) NOT NULL,
                         APELLIDO   VARCHAR2(50) NOT NULL,
                         EMAIL      VARCHAR2(50) NOT NULL,
                         TELEFONO   CHAR(10) NOT NULL,
                         CONSTRAINT PK_CLIENTE PRIMARY KEY (CLIENTE_ID)
);

/*==============================================================*/
/* Tabla: DETALLE_VENTA                                         */
/*==============================================================*/
CREATE TABLE DETALLE_VENTA (
                               VENTA_ID        INTEGER NOT NULL,
                               DETALLE_ID      INTEGER NOT NULL,
                               PRODUCTO_ID     INTEGER NOT NULL,
                               CANTIDAD        INTEGER,
                               PRECIO_UNITARIO NUMBER(7,2),
                               SUB_TOTAL       NUMBER(7,2),
                               CONSTRAINT PK_DETALLE_VENTA PRIMARY KEY (VENTA_ID, DETALLE_ID)
);

CREATE INDEX IDX_DETALLEVENTA_VENTAS ON DETALLE_VENTA (VENTA_ID ASC);
CREATE INDEX IDX_DETALLEVENTA_PRODUCTO ON DETALLE_VENTA (PRODUCTO_ID ASC);

/*==============================================================*/
/* Tabla: EMPLEADO                                              */
/*==============================================================*/
CREATE TABLE EMPLEADO (
                          EMPLEADO_ID INTEGER NOT NULL,
                          ID_TIENDA   INTEGER NOT NULL,
                          NOMBRE      VARCHAR2(50) NOT NULL,
                          APELLIDO    VARCHAR2(50) NOT NULL,
                          CARGO       VARCHAR2(50) NOT NULL,
                          CONSTRAINT PK_EMPLEADO PRIMARY KEY (EMPLEADO_ID)
);

CREATE INDEX IDX_EMPLEADO_TIENDA ON EMPLEADO (ID_TIENDA ASC);

/*==============================================================*/
/* Tabla: INVENTARIO                                            */
/*==============================================================*/
CREATE TABLE INVENTARIO (
                            INVENTARIO_ID INTEGER NOT NULL,
                            ID_TIENDA     INTEGER NOT NULL,
                            CANTIDAD      INTEGER NOT NULL,
                            CONSTRAINT PK_INVENTARIO PRIMARY KEY (INVENTARIO_ID)
);

CREATE INDEX IDX_INVENTARIO_TIENDA ON INVENTARIO (ID_TIENDA ASC);

/*==============================================================*/
/* Tabla: PRODUCTO                                              */
/*==============================================================*/
CREATE TABLE PRODUCTO (
                          PRODUCTO_ID  INTEGER NOT NULL,
                          PROVEEDOR_ID INTEGER NOT NULL,
                          CATEGORIA_ID INTEGER NOT NULL,
                          NOMBRE       VARCHAR2(50) NOT NULL,
                          PRECIO       NUMBER(7,2) NOT NULL,
                          CONSTRAINT PK_PRODUCTO PRIMARY KEY (PRODUCTO_ID)
);

CREATE INDEX IDX_PRODUCTO_PROVEEDOR ON PRODUCTO (PROVEEDOR_ID ASC);
CREATE INDEX IDX_PRODUCTO_CATEGORIA ON PRODUCTO (CATEGORIA_ID ASC);

/*==============================================================*/
/* Tabla: PROVEEDOR                                             */
/*==============================================================*/
CREATE TABLE PROVEEDOR (
                           PROVEEDOR_ID INTEGER NOT NULL,
                           NOMBRE       VARCHAR2(50) NOT NULL,
                           EMAIL        VARCHAR2(50) NOT NULL,
                           TELEFONO     CHAR(10) NOT NULL,
                           CONSTRAINT PK_PROVEEDOR PRIMARY KEY (PROVEEDOR_ID)
);

/*==============================================================*/
/* Tabla: REGION                                                */
/*==============================================================*/
CREATE TABLE REGION (
                        REGION_ID     INTEGER NOT NULL,
                        NOMBRE_REGION CHAR(8) NOT NULL,
                        CONSTRAINT PK_REGION PRIMARY KEY (REGION_ID)
);

/*==============================================================*/
/* Tabla: TIENDA                                                */
/*==============================================================*/
CREATE TABLE TIENDA (
                        ID_TIENDA INTEGER NOT NULL,
                        REGION_ID INTEGER NOT NULL,
                        NOMBRE    VARCHAR2(50) NOT NULL,
                        DIRECCION VARCHAR2(50) NOT NULL,
                        CONSTRAINT PK_TIENDA PRIMARY KEY (ID_TIENDA)
);

CREATE INDEX IDX_TIENDA_REGION ON TIENDA (REGION_ID ASC);

/*==============================================================*/
/* Tabla: PRODUCTO_INVENTARIO (antes TIENE1)                    */
/*==============================================================*/
CREATE TABLE PRODUCTO_INVENTARIO (
                                     PRODUCTO_ID   INTEGER NOT NULL,
                                     INVENTARIO_ID INTEGER NOT NULL,
                                     CONSTRAINT PK_PRODUCTO_INVENTARIO PRIMARY KEY (PRODUCTO_ID, INVENTARIO_ID)
);

CREATE INDEX IDX_PROD_INV_INVENTARIO ON PRODUCTO_INVENTARIO (INVENTARIO_ID ASC);
CREATE INDEX IDX_PROD_INV_PRODUCTO ON PRODUCTO_INVENTARIO (PRODUCTO_ID ASC);

/*==============================================================*/
/* Tabla: VENTAS                                                */
/*==============================================================*/
CREATE TABLE VENTAS (
                        VENTA_ID  INTEGER NOT NULL,
                        ID_TIENDA INTEGER NOT NULL,
                        CLIENTE_ID INTEGER NOT NULL,
                        FECHA     DATE NOT NULL,
                        TOTAL     FLOAT NOT NULL,
                        CONSTRAINT PK_VENTAS PRIMARY KEY (VENTA_ID)
);

CREATE INDEX IDX_VENTAS_TIENDA ON VENTAS (ID_TIENDA ASC);
CREATE INDEX IDX_VENTAS_CLIENTE ON VENTAS (CLIENTE_ID ASC);

/*==============================================================*/
/* Relaciones (Foreign Keys)                                    */
/*==============================================================*/
ALTER TABLE DETALLE_VENTA
    ADD CONSTRAINT FK_DETALLEVENTA_PRODUCTO FOREIGN KEY (PRODUCTO_ID)
        REFERENCES PRODUCTO (PRODUCTO_ID);

ALTER TABLE DETALLE_VENTA
    ADD CONSTRAINT FK_DETALLEVENTA_VENTAS FOREIGN KEY (VENTA_ID)
        REFERENCES VENTAS (VENTA_ID);

ALTER TABLE EMPLEADO
    ADD CONSTRAINT FK_EMPLEADO_TIENDA FOREIGN KEY (ID_TIENDA)
        REFERENCES TIENDA (ID_TIENDA);

ALTER TABLE INVENTARIO
    ADD CONSTRAINT FK_INVENTARIO_TIENDA FOREIGN KEY (ID_TIENDA)
        REFERENCES TIENDA (ID_TIENDA);

ALTER TABLE PRODUCTO
    ADD CONSTRAINT FK_PRODUCTO_CATEGORIA FOREIGN KEY (CATEGORIA_ID)
        REFERENCES CATEGORIA (CATEGORIA_ID);

ALTER TABLE PRODUCTO
    ADD CONSTRAINT FK_PRODUCTO_PROVEEDOR FOREIGN KEY (PROVEEDOR_ID)
        REFERENCES PROVEEDOR (PROVEEDOR_ID);

ALTER TABLE TIENDA
    ADD CONSTRAINT FK_TIENDA_REGION FOREIGN KEY (REGION_ID)
        REFERENCES REGION (REGION_ID);

ALTER TABLE PRODUCTO_INVENTARIO
    ADD CONSTRAINT FK_PROD_INV_PRODUCTO FOREIGN KEY (PRODUCTO_ID)
        REFERENCES PRODUCTO (PRODUCTO_ID);

ALTER TABLE PRODUCTO_INVENTARIO
    ADD CONSTRAINT FK_PROD_INV_INVENTARIO FOREIGN KEY (INVENTARIO_ID)
        REFERENCES INVENTARIO (INVENTARIO_ID);

ALTER TABLE VENTAS
    ADD CONSTRAINT FK_VENTAS_CLIENTE FOREIGN KEY (CLIENTE_ID)
        REFERENCES CLIENTE (CLIENTE_ID);

ALTER TABLE VENTAS
    ADD CONSTRAINT FK_VENTAS_TIENDA FOREIGN KEY (ID_TIENDA)
        REFERENCES TIENDA (ID_TIENDA);


