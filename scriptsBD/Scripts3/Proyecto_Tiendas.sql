/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     5/8/2025 4:06:18                             */
/*==============================================================*/


alter table CLIENTE
   drop constraint FK_CLIENTE_PERTENECE_PROVINCI;

alter table DETALLE_VENTA
   drop constraint FK_DETALLE__FK_DETALL_PRODUCTO;

alter table DETALLE_VENTA
   drop constraint FK_DETALLE__FK_DETALL_VENTAS;

alter table INVENTARIO
   drop constraint FK_INVENTAR_FK_INVENT_TIENDA;

alter table PRODUCTO
   drop constraint FK_PRODUCTO_FK_PRODUC_PROVEEDO;

alter table PRODUCTO_INVENTARIO
   drop constraint FK_PRODUCTO_PRODUCTO__INVENTAR;

alter table PRODUCTO_INVENTARIO
   drop constraint FK_PRODUCTO_PRODUCTO__PRODUCTO;

alter table TIENDA
   drop constraint FK_TIENDA_FK_TIENDA_PROVINCI;

alter table VENTAS
   drop constraint FK_VENTAS_FK_VENTAS_CLIENTE;

alter table VENTAS
   drop constraint FK_VENTAS_FK_VENTAS_TIENDA;

drop index PERTENECE_FK;

drop table CLIENTE cascade constraints;

drop index FK_DETALLEVENTA_VENTAS_FK;

drop index FK_DETALLEVENTA_PRODUCTO_FK;

drop table DETALLE_VENTA cascade constraints;

drop index FK_INVENTARIO_TIENDA_FK;

drop table INVENTARIO cascade constraints;

drop index FK_PRODUCTO_PROVEEDOR_FK;

drop table PRODUCTO cascade constraints;

drop index PRODUCTO_INVENTARIO_FK2;

drop index PRODUCTO_INVENTARIO_FK;

drop table PRODUCTO_INVENTARIO cascade constraints;

drop table PROVEEDOR cascade constraints;

drop table PROVINCIA cascade constraints;

drop index FK_TIENDA_REGION_FK;

drop table TIENDA cascade constraints;

drop index FK_VENTAS_TIENDA_FK;

drop index FK_VENTAS_CLIENTE_FK;

drop table VENTAS cascade constraints;

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE 
(
   CLIENTE_ID           INTEGER              not null,
   PROVINCIA_ID         INTEGER              not null,
   NOMBRE               VARCHAR2(50)         not null,
   APELLIDO             VARCHAR2(50)         not null,
   CEDULA               CHAR(10)             not null,
   EMAIL                VARCHAR2(50)         not null,
   TELEFONO             CHAR(10)             not null,
   constraint PK_CLIENTE primary key (CLIENTE_ID)
);

/*==============================================================*/
/* Index: PERTENECE_FK                                          */
/*==============================================================*/
create index PERTENECE_FK on CLIENTE (
   PROVINCIA_ID ASC
);

/*==============================================================*/
/* Table: DETALLE_VENTA                                         */
/*==============================================================*/
create table DETALLE_VENTA 
(
   VENTA_ID             INTEGER              not null,
   DETALLE_ID           INTEGER              not null,
   PRODUCTO_ID          INTEGER              not null,
   CANTIDAD             INTEGER,
   PRECIO_UNITARIO      NUMBER(7,2),
   SUB_TOTAL            NUMBER(7,2),
   constraint PK_DETALLE_VENTA primary key (VENTA_ID, DETALLE_ID)
);

/*==============================================================*/
/* Index: FK_DETALLEVENTA_PRODUCTO_FK                           */
/*==============================================================*/
create index FK_DETALLEVENTA_PRODUCTO_FK on DETALLE_VENTA (
   PRODUCTO_ID ASC
);

/*==============================================================*/
/* Index: FK_DETALLEVENTA_VENTAS_FK                             */
/*==============================================================*/
create index FK_DETALLEVENTA_VENTAS_FK on DETALLE_VENTA (
   VENTA_ID ASC
);

/*==============================================================*/
/* Table: INVENTARIO                                            */
/*==============================================================*/
create table INVENTARIO 
(
   INVENTARIO_ID        INTEGER              not null,
   ID_TIENDA            INTEGER              not null,
   CANTIDAD             INTEGER              not null,
   constraint PK_INVENTARIO primary key (INVENTARIO_ID)
);

/*==============================================================*/
/* Index: FK_INVENTARIO_TIENDA_FK                               */
/*==============================================================*/
create index FK_INVENTARIO_TIENDA_FK on INVENTARIO (
   ID_TIENDA ASC
);

/*==============================================================*/
/* Table: PRODUCTO                                              */
/*==============================================================*/
create table PRODUCTO 
(
   PRODUCTO_ID          INTEGER              not null,
   PROVEEDOR_ID         INTEGER              not null,
   NOMBRE               VARCHAR2(50)         not null,
   PRECIO               NUMBER(7,2)          not null,
   constraint PK_PRODUCTO primary key (PRODUCTO_ID)
);

/*==============================================================*/
/* Index: FK_PRODUCTO_PROVEEDOR_FK                              */
/*==============================================================*/
create index FK_PRODUCTO_PROVEEDOR_FK on PRODUCTO (
   PROVEEDOR_ID ASC
);

/*==============================================================*/
/* Table: PRODUCTO_INVENTARIO                                   */
/*==============================================================*/
create table PRODUCTO_INVENTARIO 
(
   PRODUCTO_ID          INTEGER              not null,
   INVENTARIO_ID        INTEGER              not null,
   constraint PK_PRODUCTO_INVENTARIO primary key (PRODUCTO_ID, INVENTARIO_ID)
);

/*==============================================================*/
/* Index: PRODUCTO_INVENTARIO_FK                                */
/*==============================================================*/
create index PRODUCTO_INVENTARIO_FK on PRODUCTO_INVENTARIO (
   PRODUCTO_ID ASC
);

/*==============================================================*/
/* Index: PRODUCTO_INVENTARIO_FK2                               */
/*==============================================================*/
create index PRODUCTO_INVENTARIO_FK2 on PRODUCTO_INVENTARIO (
   INVENTARIO_ID ASC
);

/*==============================================================*/
/* Table: PROVEEDOR                                             */
/*==============================================================*/
create table PROVEEDOR 
(
   PROVEEDOR_ID         INTEGER              not null,
   NOMBRE               VARCHAR2(50)         not null,
   EMAIL                VARCHAR2(50)         not null,
   TELEFONO             CHAR(10)             not null,
   constraint PK_PROVEEDOR primary key (PROVEEDOR_ID)
);

/*==============================================================*/
/* Table: PROVINCIA                                             */
/*==============================================================*/
create table PROVINCIA 
(
   PROVINCIA_ID         INTEGER              not null,
   NOMBRE_PROVINCIA     CHAR(30)              not null,
   constraint PK_PROVINCIA primary key (PROVINCIA_ID)
);

/*==============================================================*/
/* Table: TIENDA                                                */
/*==============================================================*/
create table TIENDA 
(
   ID_TIENDA            INTEGER              not null,
   PROVINCIA_ID         INTEGER              not null,
   NOMBRE               VARCHAR2(50)         not null,
   DIRECCION            VARCHAR2(50)         not null,
   constraint PK_TIENDA primary key (ID_TIENDA)
);

/*==============================================================*/
/* Index: FK_TIENDA_REGION_FK                                   */
/*==============================================================*/
create index FK_TIENDA_REGION_FK on TIENDA (
   PROVINCIA_ID ASC
);

/*==============================================================*/
/* Table: VENTAS                                                */
/*==============================================================*/
create table VENTAS 
(
   VENTA_ID             INTEGER              not null,
   ID_TIENDA            INTEGER              not null,
   CLIENTE_ID           INTEGER              not null,
   FECHA                DATE                 not null,
   TOTAL                FLOAT                not null,
   constraint PK_VENTAS primary key (VENTA_ID)
);

/*==============================================================*/
/* Index: FK_VENTAS_CLIENTE_FK                                  */
/*==============================================================*/
create index FK_VENTAS_CLIENTE_FK on VENTAS (
   CLIENTE_ID ASC
);

/*==============================================================*/
/* Index: FK_VENTAS_TIENDA_FK                                   */
/*==============================================================*/
create index FK_VENTAS_TIENDA_FK on VENTAS (
   ID_TIENDA ASC
);

alter table CLIENTE
   add constraint FK_CLIENTE_PERTENECE_PROVINCI foreign key (PROVINCIA_ID)
      references PROVINCIA (PROVINCIA_ID);

alter table DETALLE_VENTA
   add constraint FK_DETALLE__FK_DETALL_PRODUCTO foreign key (PRODUCTO_ID)
      references PRODUCTO (PRODUCTO_ID);

alter table DETALLE_VENTA
   add constraint FK_DETALLE__FK_DETALL_VENTAS foreign key (VENTA_ID)
      references VENTAS (VENTA_ID);

alter table INVENTARIO
   add constraint FK_INVENTAR_FK_INVENT_TIENDA foreign key (ID_TIENDA)
      references TIENDA (ID_TIENDA);

alter table PRODUCTO
   add constraint FK_PRODUCTO_FK_PRODUC_PROVEEDO foreign key (PROVEEDOR_ID)
      references PROVEEDOR (PROVEEDOR_ID);

alter table PRODUCTO_INVENTARIO
   add constraint FK_PRODUCTO_PRODUCTO__INVENTAR foreign key (INVENTARIO_ID)
      references INVENTARIO (INVENTARIO_ID);

alter table PRODUCTO_INVENTARIO
   add constraint FK_PRODUCTO_PRODUCTO__PRODUCTO foreign key (PRODUCTO_ID)
      references PRODUCTO (PRODUCTO_ID);

alter table TIENDA
   add constraint FK_TIENDA_FK_TIENDA_PROVINCI foreign key (PROVINCIA_ID)
      references PROVINCIA (PROVINCIA_ID);

alter table VENTAS
   add constraint FK_VENTAS_FK_VENTAS_CLIENTE foreign key (CLIENTE_ID)
      references CLIENTE (CLIENTE_ID);

alter table VENTAS
   add constraint FK_VENTAS_FK_VENTAS_TIENDA foreign key (ID_TIENDA)
      references TIENDA (ID_TIENDA);

