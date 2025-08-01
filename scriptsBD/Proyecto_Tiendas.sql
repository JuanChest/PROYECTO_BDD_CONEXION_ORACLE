/* Autores: Juan Cofre | Jhordy Parra
/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     1/6/2025 15:10:46                            */
/*==============================================================*/


alter table DETALLE_VENTA
   drop constraint FK_DETALLE__CORRESPON_PRODUCTO;

alter table DETALLE_VENTA
   drop constraint FK_DETALLE__TIENE3_VENTAS;

alter table EMPLEADO
   drop constraint FK_EMPLEADO_TIENE_TIENDA;

alter table INVENTARIO
   drop constraint FK_INVENTAR_GESTIONA_TIENDA;

alter table PRODUCTO
   drop constraint FK_PRODUCTO_ES_CATEGORI;

alter table PRODUCTO
   drop constraint FK_PRODUCTO_TIENE2_PROVEEDO;

alter table TIENDA
   drop constraint FK_TIENDA_PERTENECE_REGION;

alter table TIENE1
   drop constraint FK_TIENE1_TIENE1_PRODUCTO;

alter table TIENE1
   drop constraint FK_TIENE1_TIENE4_INVENTAR;

alter table VENTAS
   drop constraint FK_VENTAS_HACE_CLIENTE;

alter table VENTAS
   drop constraint FK_VENTAS_REALIZA_TIENDA;

drop table CATEGORIA cascade constraints;

drop table CLIENTE cascade constraints;

drop index CORRESPONDE_FK;

drop index TIENE3_FK;

drop table DETALLE_VENTA cascade constraints;

drop index TIENE_FK;

drop table EMPLEADO cascade constraints;

drop index GESTIONA_FK;

drop table INVENTARIO cascade constraints;

drop index ES_FK;

drop index TIENE2_FK;

drop table PRODUCTO cascade constraints;

drop table PROVEEDOR cascade constraints;

drop table REGION cascade constraints;

drop index PERTENECE_FK;

drop table TIENDA cascade constraints;

drop index TIENE1_FK;

drop index TIENE4_FK;

drop table TIENE1 cascade constraints;

drop index HACE_FK;

drop index REALIZA_FK;

drop table VENTAS cascade constraints;

/*==============================================================*/
/* Table: CATEGORIA                                             */
/*==============================================================*/
create table CATEGORIA 
(
   CATEGORIA_ID         INTEGER              not null,
   NOMBRE_CATEGORIA     VARCHAR2(50)         not null,
   DESCRIPCION          CLOB,
   constraint PK_CATEGORIA primary key (CATEGORIA_ID)
);

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE 
(
   CLIENTE_ID           INTEGER              not null,
   NOMBRE               VARCHAR2(50)         not null,
   APELLIDO             VARCHAR2(50)         not null,
   EMAIL                VARCHAR2(50)         not null,
   TELEFONO             CHAR(10)             not null,
   constraint PK_CLIENTE primary key (CLIENTE_ID)
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
/* Index: TIENE3_FK                                             */
/*==============================================================*/
create index TIENE3_FK on DETALLE_VENTA (
   VENTA_ID ASC
);

/*==============================================================*/
/* Index: CORRESPONDE_FK                                        */
/*==============================================================*/
create index CORRESPONDE_FK on DETALLE_VENTA (
   PRODUCTO_ID ASC
);

/*==============================================================*/
/* Table: EMPLEADO                                              */
/*==============================================================*/
create table EMPLEADO 
(
   EMPLEADO_ID          INTEGER              not null,
   ID_TIENDA            INTEGER              not null,
   NOMBRE               VARCHAR2(50)         not null,
   APELLIDO             VARCHAR2(50)         not null,
   CARGO                VARCHAR2(50)         not null,
   constraint PK_EMPLEADO primary key (EMPLEADO_ID)
);

/*==============================================================*/
/* Index: TIENE_FK                                              */
/*==============================================================*/
create index TIENE_FK on EMPLEADO (
   ID_TIENDA ASC
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
/* Index: GESTIONA_FK                                           */
/*==============================================================*/
create index GESTIONA_FK on INVENTARIO (
   ID_TIENDA ASC
);

/*==============================================================*/
/* Table: PRODUCTO                                              */
/*==============================================================*/
create table PRODUCTO 
(
   PRODUCTO_ID          INTEGER              not null,
   PROVEEDOR_ID         INTEGER              not null,
   CATEGORIA_ID         INTEGER              not null,
   NOMBRE               VARCHAR2(50)         not null,
   PRECIO               NUMBER(7,2)          not null,
   constraint PK_PRODUCTO primary key (PRODUCTO_ID)
);

/*==============================================================*/
/* Index: TIENE2_FK                                             */
/*==============================================================*/
create index TIENE2_FK on PRODUCTO (
   PROVEEDOR_ID ASC
);

/*==============================================================*/
/* Index: ES_FK                                                 */
/*==============================================================*/
create index ES_FK on PRODUCTO (
   CATEGORIA_ID ASC
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
/* Table: REGION                                                */
/*==============================================================*/
create table REGION 
(
   REGION_ID            INTEGER              not null,
   NOMBRE_REGION        CHAR(8)              not null,
   constraint PK_REGION primary key (REGION_ID)
);

/*==============================================================*/
/* Table: TIENDA                                                */
/*==============================================================*/
create table TIENDA 
(
   ID_TIENDA            INTEGER              not null,
   REGION_ID            INTEGER              not null,
   NOMBRE               VARCHAR2(50)         not null,
   DIRECCION            VARCHAR2(50)         not null,
   constraint PK_TIENDA primary key (ID_TIENDA)
);

/*==============================================================*/
/* Index: PERTENECE_FK                                          */
/*==============================================================*/
create index PERTENECE_FK on TIENDA (
   REGION_ID ASC
);

/*==============================================================*/
/* Table: TIENE1                                                */
/*==============================================================*/
create table TIENE1 
(
   PRODUCTO_ID          INTEGER              not null,
   INVENTARIO_ID        INTEGER              not null,
   constraint PK_TIENE1 primary key (PRODUCTO_ID, INVENTARIO_ID)
);

/*==============================================================*/
/* Index: TIENE4_FK                                             */
/*==============================================================*/
create index TIENE4_FK on TIENE1 (
   INVENTARIO_ID ASC
);

/*==============================================================*/
/* Index: TIENE1_FK                                             */
/*==============================================================*/
create index TIENE1_FK on TIENE1 (
   PRODUCTO_ID ASC
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
/* Index: REALIZA_FK                                            */
/*==============================================================*/
create index REALIZA_FK on VENTAS (
   ID_TIENDA ASC
);

/*==============================================================*/
/* Index: HACE_FK                                               */
/*==============================================================*/
create index HACE_FK on VENTAS (
   CLIENTE_ID ASC
);

alter table DETALLE_VENTA
   add constraint FK_DETALLE__CORRESPON_PRODUCTO foreign key (PRODUCTO_ID)
      references PRODUCTO (PRODUCTO_ID);

alter table DETALLE_VENTA
   add constraint FK_DETALLE__TIENE3_VENTAS foreign key (VENTA_ID)
      references VENTAS (VENTA_ID);

alter table EMPLEADO
   add constraint FK_EMPLEADO_TIENE_TIENDA foreign key (ID_TIENDA)
      references TIENDA (ID_TIENDA);

alter table INVENTARIO
   add constraint FK_INVENTAR_GESTIONA_TIENDA foreign key (ID_TIENDA)
      references TIENDA (ID_TIENDA);

alter table PRODUCTO
   add constraint FK_PRODUCTO_ES_CATEGORI foreign key (CATEGORIA_ID)
      references CATEGORIA (CATEGORIA_ID);

alter table PRODUCTO
   add constraint FK_PRODUCTO_TIENE2_PROVEEDO foreign key (PROVEEDOR_ID)
      references PROVEEDOR (PROVEEDOR_ID);

alter table TIENDA
   add constraint FK_TIENDA_PERTENECE_REGION foreign key (REGION_ID)
      references REGION (REGION_ID);

alter table TIENE1
   add constraint FK_TIENE1_TIENE1_PRODUCTO foreign key (PRODUCTO_ID)
      references PRODUCTO (PRODUCTO_ID);

alter table TIENE1
   add constraint FK_TIENE1_TIENE4_INVENTAR foreign key (INVENTARIO_ID)
      references INVENTARIO (INVENTARIO_ID);

alter table VENTAS
   add constraint FK_VENTAS_HACE_CLIENTE foreign key (CLIENTE_ID)
      references CLIENTE (CLIENTE_ID);

alter table VENTAS
   add constraint FK_VENTAS_REALIZA_TIENDA foreign key (ID_TIENDA)
      references TIENDA (ID_TIENDA);

