--INSERT INTO usuario (ID, NOMBRE, APELLIDOS, EMAIL, ES_ADMIN) VALUES (NEXTVAL('hibernate_sequence'), 'Luis Miguel', 'Lopez', 'mllopez@gmail.com', false);
--INSERT INTO categoria (ID, NOMBRE) VALUES (NEXTVAL('hibernate_sequence'), 'Categoria 1');
--INSERT INTO producto (ID, NOMBRE, PRECIO, ESTADO, CATEGORIA_ID) VALUES (NEXTVAL('hibernate_sequence'), 'Producto 1', '50', true, 2);
--INSERT INTO pedido (ID, FECHA, ESTADO, USER_ID) VALUES (NEXTVAL('hibernate_sequence'), sysdate,'Enviado', 1);
--INSERT INTO linea_pedido (ID, PEDIDO_ID, PRODUCTO_ID) VALUES (NEXTVAL('hibernate_sequence'), 4, 3);
--DROP TABLE IF EXISTS USUARIO_LISTA
--DROP TABLE IF EXISTS USUARIO_LISTA_DE_PEDIDOS
--DROP TABLE IF EXISTS PEDIDO_LINEA_PEDIDOS

