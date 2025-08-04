/* Autores: Juan Cofre | Jhordy Parra
/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     1/6/2025 15:10:46                            */
/*==============================================================*/

/*==============================================================*/
/* Eliminación de tablas si existen                             */
/*==============================================================*/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE DETALLE_VENTA CASCADE CONSTRAINTS';
   EXECUTE IMMEDIATE 'DROP TABLE VENTAS CASCADE CONSTRAINTS';
   EXECUTE IMMEDIATE 'DROP TABLE INVENTARIO CASCADE CONSTRAINTS';
   EXECUTE IMMEDIATE 'DROP TABLE TIENDA CASCADE CONSTRAINTS';
   EXECUTE IMMEDIATE 'DROP TABLE REGION CASCADE CONSTRAINTS';
   EXECUTE IMMEDIATE 'DROP TABLE PRODUCTO CASCADE CONSTRAINTS';
   EXECUTE IMMEDIATE 'DROP TABLE PROVEEDOR CASCADE CONSTRAINTS';
   EXECUTE IMMEDIATE 'DROP TABLE CLIENTE CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

/*==============================================================*/
/* Tabla: REGION                                                */
/*==============================================================*/
CREATE TABLE REGION (
   REGION_ID     INTEGER      NOT NULL,
   NOMBRE_REGION VARCHAR2(20) NOT NULL,
   CONSTRAINT PK_REGION PRIMARY KEY (REGION_ID)
);

/*==============================================================*/
/* Tabla: TIENDA                                                */
/*==============================================================*/
CREATE TABLE TIENDA (
   ID_TIENDA INTEGER      NOT NULL,
   REGION_ID INTEGER      NOT NULL,
   NOMBRE    VARCHAR2(50) NOT NULL,
   DIRECCION VARCHAR2(50) NOT NULL,
   CONSTRAINT PK_TIENDA PRIMARY KEY (ID_TIENDA),
   CONSTRAINT FK_TIENDA_REGION FOREIGN KEY (REGION_ID)
      REFERENCES REGION (REGION_ID)
);

/*==============================================================*/
/* Tabla: PROVEEDOR                                             */
/*==============================================================*/
CREATE TABLE PROVEEDOR (
   PROVEEDOR_ID INTEGER      NOT NULL,
   NOMBRE       VARCHAR2(50) NOT NULL,
   EMAIL        VARCHAR2(50) NOT NULL,
   TELEFONO     CHAR(10)     NOT NULL,
   CONSTRAINT PK_PROVEEDOR PRIMARY KEY (PROVEEDOR_ID)
);

/*==============================================================*/
/* Tabla: PRODUCTO                                              */
/*==============================================================*/
CREATE TABLE PRODUCTO (
   PRODUCTO_ID  INTEGER      NOT NULL,
   PROVEEDOR_ID INTEGER      NOT NULL,
   NOMBRE       VARCHAR2(50) NOT NULL,
   PRECIO       NUMBER(10,2) NOT NULL,
   CONSTRAINT PK_PRODUCTO PRIMARY KEY (PRODUCTO_ID),
   CONSTRAINT FK_PRODUCTO_PROVEEDOR FOREIGN KEY (PROVEEDOR_ID)
      REFERENCES PROVEEDOR (PROVEEDOR_ID)
);

/*==============================================================*/
/* Tabla: CLIENTE                                               */
/*==============================================================*/
CREATE TABLE CLIENTE (
   CLIENTE_ID INTEGER      NOT NULL,
   NOMBRE     VARCHAR2(50) NOT NULL,
   APELLIDO   VARCHAR2(50) NOT NULL,
   EMAIL      VARCHAR2(50) NOT NULL,
   TELEFONO   CHAR(10)     NOT NULL,
   CONSTRAINT PK_CLIENTE PRIMARY KEY (CLIENTE_ID)
);

/*==============================================================*/
/* Tabla: INVENTARIO                                            */
/*==============================================================*/
CREATE TABLE INVENTARIO (
   INVENTARIO_ID INTEGER      NOT NULL,
   ID_TIENDA     INTEGER      NOT NULL,
   PRODUCTO_ID   INTEGER      NOT NULL,
   CANTIDAD      INTEGER      NOT NULL,
   CONSTRAINT PK_INVENTARIO PRIMARY KEY (INVENTARIO_ID),
   CONSTRAINT FK_INVENTARIO_TIENDA FOREIGN KEY (ID_TIENDA)
      REFERENCES TIENDA (ID_TIENDA),
   CONSTRAINT FK_INVENTARIO_PRODUCTO FOREIGN KEY (PRODUCTO_ID)
      REFERENCES PRODUCTO (PRODUCTO_ID)
);

/*==============================================================*/
/* Tabla: VENTAS                                                */
/*==============================================================*/
CREATE TABLE VENTAS (
   VENTA_ID   INTEGER      NOT NULL,
   ID_TIENDA  INTEGER      NOT NULL,
   CLIENTE_ID INTEGER      NOT NULL,
   FECHA      DATE         NOT NULL,
   TOTAL      NUMBER(10,2) NOT NULL,
   CONSTRAINT PK_VENTAS PRIMARY KEY (VENTA_ID),
   CONSTRAINT FK_VENTAS_TIENDA FOREIGN KEY (ID_TIENDA)
      REFERENCES TIENDA (ID_TIENDA),
   CONSTRAINT FK_VENTAS_CLIENTE FOREIGN KEY (CLIENTE_ID)
      REFERENCES CLIENTE (CLIENTE_ID)
);

/*==============================================================*/
/* Tabla: DETALLE_VENTA                                         */
/*==============================================================*/
CREATE TABLE DETALLE_VENTA (
   VENTA_ID        INTEGER      NOT NULL,
   DETALLE_ID      INTEGER      NOT NULL,
   PRODUCTO_ID     INTEGER      NOT NULL,
   CANTIDAD        INTEGER,
   PRECIO_UNITARIO NUMBER(10,2),
   SUB_TOTAL       NUMBER(10,2),
   CONSTRAINT PK_DETALLE_VENTA PRIMARY KEY (VENTA_ID, DETALLE_ID),
   CONSTRAINT FK_DETALLE_VENTA_PRODUCTO FOREIGN KEY (PRODUCTO_ID)
      REFERENCES PRODUCTO (PRODUCTO_ID),
   CONSTRAINT FK_DETALLE_VENTA_VENTAS FOREIGN KEY (VENTA_ID)
      REFERENCES VENTAS (VENTA_ID)
);
