DROP TABLE AUDITORIA_OPERACIONES;
CREATE TABLE AUDITORIA_OPERACIONES (
    ID_AUDIT NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TABLA      VARCHAR2(50),
    OPERACION  VARCHAR2(10),
    USUARIO    VARCHAR2(30),
    FECHA      DATE,
    INFO       VARCHAR2(4000)
);

CREATE OR REPLACE TRIGGER trg_audit_provincia_pichincha
AFTER INSERT OR UPDATE OR DELETE ON provincia_pichincha
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('PROVINCIA_PICHINCHA', v_operacion, USER, SYSDATE, v_info);
END;
/

CREATE OR REPLACE TRIGGER trg_audit_provincia_cotopaxi
AFTER INSERT OR UPDATE OR DELETE ON provincia_cotopaxi
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('PROVINCIA_COTOPAXI', v_operacion, USER, SYSDATE, v_info);
END;
/

CREATE OR REPLACE TRIGGER trg_audit_provincia_tungurahua
AFTER INSERT OR UPDATE OR DELETE ON provincia_tungurahua
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('PROVINCIA_TUNGURAHUA', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_tienda_pichincha
AFTER INSERT OR UPDATE OR DELETE ON tienda_pichincha
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('TIENDA_PICHINCHA', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_tienda_cotopaxi
AFTER INSERT OR UPDATE OR DELETE ON tienda_cotopaxi
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('TIENDA_COTOPAXI', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_tienda_tungurahua
AFTER INSERT OR UPDATE OR DELETE ON tienda_tungurahua
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('TIENDA_TUNGURAHUA', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_inventario_pichincha
AFTER INSERT OR UPDATE OR DELETE ON inventario_pichincha
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('INVENTARIO_PICHINCHA', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_inventario_cotopaxi
AFTER INSERT OR UPDATE OR DELETE ON inventario_cotopaxi
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('INVENTARIO_COTOPAXI', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_inventario_tungurahua
AFTER INSERT OR UPDATE OR DELETE ON inventario_tungurahua
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('INVENTARIO_TUNGURAHUA', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_cliente_pichincha
AFTER INSERT OR UPDATE OR DELETE ON cliente_pichincha
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('CLIENTE_PICHINCHA', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_cliente_cotopaxi
AFTER INSERT OR UPDATE OR DELETE ON cliente_cotopaxi
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('CLIENTE_COTOPAXI', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_cliente_tungurahua
AFTER INSERT OR UPDATE OR DELETE ON cliente_tungurahua
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('CLIENTE_TUNGURAHUA', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_ventas_pichincha
AFTER INSERT OR UPDATE OR DELETE ON ventas_pichincha
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('VENTAS_PICHINCHA', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_ventas_cotopaxi
AFTER INSERT OR UPDATE OR DELETE ON ventas_cotopaxi
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('VENTAS_COTOPAXI', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_ventas_tungurahua
AFTER INSERT OR UPDATE OR DELETE ON ventas_tungurahua
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('VENTAS_TUNGURAHUA', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_detalle_venta_pichincha
AFTER INSERT OR UPDATE OR DELETE ON detalle_venta_pichincha
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('DETALLE_VENTA_PICHINCHA', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_detalle_venta_cotopaxi
AFTER INSERT OR UPDATE OR DELETE ON detalle_venta_cotopaxi
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('DETALLE_VENTA_COTOPAXI', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_detalle_venta_tungurahua
AFTER INSERT OR UPDATE OR DELETE ON detalle_venta_tungurahua
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

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('DETALLE_VENTA_TUNGURAHUA', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_producto
AFTER INSERT OR UPDATE OR DELETE ON producto
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'PRODUCTO_ID=' || :NEW.producto_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'PRODUCTO_ID=' || :NEW.producto_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'PRODUCTO_ID=' || :OLD.producto_id;
    END IF;

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('PRODUCTO', v_operacion, USER, SYSDATE, v_info);
END;
/
CREATE OR REPLACE TRIGGER trg_audit_proveedor
AFTER INSERT OR UPDATE OR DELETE ON proveedor
FOR EACH ROW
DECLARE
    v_operacion VARCHAR2(10);
    v_info VARCHAR2(4000);
BEGIN
    IF INSERTING THEN
        v_operacion := 'INSERT';
        v_info := 'PROVEEDOR_ID=' || :NEW.proveedor_id;
    ELSIF UPDATING THEN
        v_operacion := 'UPDATE';
        v_info := 'PROVEEDOR_ID=' || :NEW.proveedor_id;
    ELSIF DELETING THEN
        v_operacion := 'DELETE';
        v_info := 'PROVEEDOR_ID=' || :OLD.proveedor_id;
    END IF;

    INSERT INTO auditoria_operaciones (tabla, operacion, usuario, fecha, info)
    VALUES ('PROVEEDOR', v_operacion, USER, SYSDATE, v_info);
END;
/
