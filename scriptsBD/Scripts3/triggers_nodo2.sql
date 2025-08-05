DROP TABLE AUDITORIA_OPERACIONES;
CREATE TABLE AUDITORIA_OPERACIONES (
    ID_AUDIT NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TABLA      VARCHAR2(50),
    OPERACION  VARCHAR2(10),
    USUARIO    VARCHAR2(30),
    FECHA      DATE,
    INFO       VARCHAR2(4000)
);
-- PROVINCIA_GUAYAS
CREATE OR REPLACE TRIGGER trg_audit_provincia_guayas
AFTER INSERT OR UPDATE OR DELETE ON provincia_guayas
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'PROVINCIA_ID=' || :NEW.provincia_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'PROVINCIA_ID=' || :NEW.provincia_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'PROVINCIA_ID=' || :OLD.provincia_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('PROVINCIA_GUAYAS', v_operacion, USER, SYSDATE, v_info);
END;
/

-- PROVINCIA_MANABI
CREATE OR REPLACE TRIGGER trg_audit_provincia_manabi
AFTER INSERT OR UPDATE OR DELETE ON provincia_manabi
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'PROVINCIA_ID=' || :NEW.provincia_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'PROVINCIA_ID=' || :NEW.provincia_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'PROVINCIA_ID=' || :OLD.provincia_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('PROVINCIA_MANABI', v_operacion, USER, SYSDATE, v_info);
END;
/

-- PROVINCIA_ESMERALDAS
CREATE OR REPLACE TRIGGER trg_audit_provincia_esmeraldas
AFTER INSERT OR UPDATE OR DELETE ON provincia_esmeraldas
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'PROVINCIA_ID=' || :NEW.provincia_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'PROVINCIA_ID=' || :NEW.provincia_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'PROVINCIA_ID=' || :OLD.provincia_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('PROVINCIA_ESMERALDAS', v_operacion, USER, SYSDATE, v_info);
END;
/

-- TIENDA_GUAYAS
CREATE OR REPLACE TRIGGER trg_audit_tienda_guayas
AFTER INSERT OR UPDATE OR DELETE ON tienda_guayas
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'ID_TIENDA=' || :NEW.id_tienda;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'ID_TIENDA=' || :NEW.id_tienda;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'ID_TIENDA=' || :OLD.id_tienda;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('TIENDA_GUAYAS', v_operacion, USER, SYSDATE, v_info);
END;
/

-- TIENDA_MANABI
CREATE OR REPLACE TRIGGER trg_audit_tienda_manabi
AFTER INSERT OR UPDATE OR DELETE ON tienda_manabi
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'ID_TIENDA=' || :NEW.id_tienda;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'ID_TIENDA=' || :NEW.id_tienda;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'ID_TIENDA=' || :OLD.id_tienda;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('TIENDA_MANABI', v_operacion, USER, SYSDATE, v_info);
END;
/

-- TIENDA_ESMERALDAS
CREATE OR REPLACE TRIGGER trg_audit_tienda_esmeraldas
AFTER INSERT OR UPDATE OR DELETE ON tienda_esmeraldas
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'ID_TIENDA=' || :NEW.id_tienda;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'ID_TIENDA=' || :NEW.id_tienda;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'ID_TIENDA=' || :OLD.id_tienda;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('TIENDA_ESMERALDAS', v_operacion, USER, SYSDATE, v_info);
END;
/

-- INVENTARIO_GUAYAS
CREATE OR REPLACE TRIGGER trg_audit_inventario_guayas
AFTER INSERT OR UPDATE OR DELETE ON inventario_guayas
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'INVENTARIO_ID=' || :NEW.inventario_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'INVENTARIO_ID=' || :NEW.inventario_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'INVENTARIO_ID=' || :OLD.inventario_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('INVENTARIO_GUAYAS', v_operacion, USER, SYSDATE, v_info);
END;
/

-- INVENTARIO_MANABI
CREATE OR REPLACE TRIGGER trg_audit_inventario_manabi
AFTER INSERT OR UPDATE OR DELETE ON inventario_manabi
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'INVENTARIO_ID=' || :NEW.inventario_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'INVENTARIO_ID=' || :NEW.inventario_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'INVENTARIO_ID=' || :OLD.inventario_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('INVENTARIO_MANABI', v_operacion, USER, SYSDATE, v_info);
END;
/

-- INVENTARIO_ESMERALDAS
CREATE OR REPLACE TRIGGER trg_audit_inventario_esmeraldas
AFTER INSERT OR UPDATE OR DELETE ON inventario_esmeraldas
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'INVENTARIO_ID=' || :NEW.inventario_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'INVENTARIO_ID=' || :NEW.inventario_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'INVENTARIO_ID=' || :OLD.inventario_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('INVENTARIO_ESMERALDAS', v_operacion, USER, SYSDATE, v_info);
END;
/

-- CLIENTE_GUAYAS
CREATE OR REPLACE TRIGGER trg_audit_cliente_guayas
AFTER INSERT OR UPDATE OR DELETE ON cliente_guayas
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'CLIENTE_ID=' || :NEW.cliente_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'CLIENTE_ID=' || :NEW.cliente_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'CLIENTE_ID=' || :OLD.cliente_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('CLIENTE_GUAYAS', v_operacion, USER, SYSDATE, v_info);
END;
/

-- CLIENTE_MANABI
CREATE OR REPLACE TRIGGER trg_audit_cliente_manabi
AFTER INSERT OR UPDATE OR DELETE ON cliente_manabi
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'CLIENTE_ID=' || :NEW.cliente_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'CLIENTE_ID=' || :NEW.cliente_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'CLIENTE_ID=' || :OLD.cliente_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('CLIENTE_MANABI', v_operacion, USER, SYSDATE, v_info);
END;
/

-- CLIENTE_ESMERALDAS
CREATE OR REPLACE TRIGGER trg_audit_cliente_esmeraldas
AFTER INSERT OR UPDATE OR DELETE ON cliente_esmeraldas
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'CLIENTE_ID=' || :NEW.cliente_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'CLIENTE_ID=' || :NEW.cliente_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'CLIENTE_ID=' || :OLD.cliente_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('CLIENTE_ESMERALDAS', v_operacion, USER, SYSDATE, v_info);
END;
/

-- VENTAS_GUAYAS
CREATE OR REPLACE TRIGGER trg_audit_ventas_guayas
AFTER INSERT OR UPDATE OR DELETE ON ventas_guayas
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'VENTA_ID=' || :NEW.venta_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'VENTA_ID=' || :NEW.venta_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'VENTA_ID=' || :OLD.venta_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('VENTAS_GUAYAS', v_operacion, USER, SYSDATE, v_info);
END;
/

-- VENTAS_MANABI
CREATE OR REPLACE TRIGGER trg_audit_ventas_manabi
AFTER INSERT OR UPDATE OR DELETE ON ventas_manabi
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'VENTA_ID=' || :NEW.venta_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'VENTA_ID=' || :NEW.venta_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'VENTA_ID=' || :OLD.venta_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('VENTAS_MANABI', v_operacion, USER, SYSDATE, v_info);
END;
/

-- VENTAS_ESMERALDAS
CREATE OR REPLACE TRIGGER trg_audit_ventas_esmeraldas
AFTER INSERT OR UPDATE OR DELETE ON ventas_esmeraldas
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'VENTA_ID=' || :NEW.venta_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'VENTA_ID=' || :NEW.venta_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'VENTA_ID=' || :OLD.venta_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('VENTAS_ESMERALDAS', v_operacion, USER, SYSDATE, v_info);
END;
/

-- DETALLE_VENTA_GUAYAS
CREATE OR REPLACE TRIGGER trg_audit_detalle_venta_guayas
AFTER INSERT OR UPDATE OR DELETE ON detalle_venta_guayas
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'DETALLE_ID=' || :NEW.detalle_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'DETALLE_ID=' || :NEW.detalle_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'DETALLE_ID=' || :OLD.detalle_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('DETALLE_VENTA_GUAYAS', v_operacion, USER, SYSDATE, v_info);
END;
/

-- DETALLE_VENTA_MANABI
CREATE OR REPLACE TRIGGER trg_audit_detalle_venta_manabi
AFTER INSERT OR UPDATE OR DELETE ON detalle_venta_manabi
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'DETALLE_ID=' || :NEW.detalle_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'DETALLE_ID=' || :NEW.detalle_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'DETALLE_ID=' || :OLD.detalle_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('DETALLE_VENTA_MANABI', v_operacion, USER, SYSDATE, v_info);
END;
/

-- DETALLE_VENTA_ESMERALDAS
CREATE OR REPLACE TRIGGER trg_audit_detalle_venta_esmeraldas
AFTER INSERT OR UPDATE OR DELETE ON detalle_venta_esmeraldas
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'DETALLE_ID=' || :NEW.detalle_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'DETALLE_ID=' || :NEW.detalle_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'DETALLE_ID=' || :OLD.detalle_id;
    END IF;
    INSERT INTO auditoria_operaciones(tabla, operacion, usuario, fecha, info)
    VALUES ('DETALLE_VENTA_ESMERALDAS', v_operacion, USER, SYSDATE, v_info);
END;
/

