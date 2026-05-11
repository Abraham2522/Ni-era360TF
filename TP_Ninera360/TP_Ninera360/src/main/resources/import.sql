INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO users(username, password) VALUES ('user1','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06');
INSERT INTO users(username, password) VALUES ('admin','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06');
INSERT INTO users(username, password) VALUES ('cliente2','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06');
INSERT INTO users(username, password) VALUES ('cuidadora1','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06');
INSERT INTO users(username, password) VALUES ('cuidadora2','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 1);

INSERT INTO usuarios(nombre, dni, direccion, telefono, correo, user_id) VALUES ('Juan Perez', 12345678, 'Calle Falsa 123', 777777777, 'jperez@gmail.com', 1);
INSERT INTO usuarios(nombre, dni, direccion, telefono, correo, user_id) VALUES ('Administrador', 87654321, 'Av. Principal 100', 999999999, 'admin@gmail.com', 2);
INSERT INTO usuarios(nombre, dni, direccion, telefono, correo, user_id) VALUES ('Maria Torres', 11223344, 'Av. Las Flores 456', 988776655, 'mtorres@gmail.com', 3);
INSERT INTO usuarios(nombre, dni, direccion, telefono, correo, user_id) VALUES ('Laura Mendoza', 44556677, 'Av. Los Jardines 200', 987654321, 'laura.mendoza@gmail.com', 4);
INSERT INTO usuarios(nombre, dni, direccion, telefono, correo, user_id) VALUES ('Carmen Rojas', 55667788, 'Jr. Los Olivos 300', 976543210, 'carmen.rojas@gmail.com', 5);

INSERT INTO clientes(id_usuario, id_rol, descripcion) VALUES (1, 1, 'Padre de familia que busca servicios de niñera.');
INSERT INTO clientes(id_usuario, id_rol, descripcion) VALUES (3, 1, 'Madre de familia que requiere apoyo para el cuidado infantil.');

INSERT INTO cuidadores(id_usuario, id_rol, descripcion, tarifa, experiencia, antecedentes) VALUES (4, 1, 'Niñera especializada en cuidado de niños pequeños.', 25.00, '5 años de experiencia en cuidado infantil.', 'Sin antecedentes registrados.');
INSERT INTO cuidadores(id_usuario, id_rol, descripcion, tarifa, experiencia, antecedentes) VALUES (5, 1, 'Cuidadora con experiencia en bebés y apoyo familiar.', 30.00, '3 años de experiencia con bebés y niños menores de 5 años.', 'Sin antecedentes registrados.');

INSERT INTO reservas(id_cliente, id_cuidador, hora_inicio, hora_fin, estado) VALUES (1, 4, '2026-05-12 09:00:00-05', '2026-05-12 13:00:00-05', 'PENDIENTE');
INSERT INTO reservas(id_cliente, id_cuidador, hora_inicio, hora_fin, estado) VALUES (1, 5, '2026-05-13 14:00:00-05', '2026-05-13 18:00:00-05', 'CONFIRMADA');
INSERT INTO reservas(id_cliente, id_cuidador, hora_inicio, hora_fin, estado) VALUES (3, 4, '2026-05-14 08:00:00-05', '2026-05-14 12:00:00-05', 'FINALIZADA');
INSERT INTO reservas(id_cliente, id_cuidador, hora_inicio, hora_fin, estado) VALUES (3, 5, '2026-05-15 15:00:00-05', '2026-05-15 20:00:00-05', 'CANCELADA');

INSERT INTO chats(id_cliente, id_cuidador) VALUES (1, 4);
INSERT INTO chats(id_cliente, id_cuidador) VALUES (1, 5);
INSERT INTO chats(id_cliente, id_cuidador) VALUES (3, 4);
INSERT INTO chats(id_cliente, id_cuidador) VALUES (3, 5);

INSERT INTO mensajes(id_chat, id_usuario, contenido, fecha) VALUES (1, 1, 'Hola Laura, quisiera confirmar la reserva para mañana.', '2026-05-11 18:00:00-05');
INSERT INTO mensajes(id_chat, id_usuario, contenido, fecha) VALUES (1, 4, 'Hola, claro. Confirmo mi disponibilidad.', '2026-05-11 18:05:00-05');
INSERT INTO mensajes(id_chat, id_usuario, contenido, fecha) VALUES (2, 1, 'Buenas tardes Carmen, necesito apoyo por la tarde.', '2026-05-12 10:00:00-05');
INSERT INTO mensajes(id_chat, id_usuario, contenido, fecha) VALUES (2, 5, 'Buenas tardes, sí tengo disponibilidad.', '2026-05-12 10:10:00-05');

INSERT INTO pagos(monto_pago, estado_pago, pagada_en) VALUES ('100.00', true, '2026-05-12 13:10:00');
INSERT INTO pagos(monto_pago, estado_pago, pagada_en) VALUES ('120.00', true, '2026-05-13 18:15:00');
INSERT INTO pagos(monto_pago, estado_pago, pagada_en) VALUES ('100.00', false, 'Pendiente');

INSERT INTO resenas(calificacion, comentario, creado_en) VALUES (5, 'Excelente servicio, muy puntual y amable.', '2026-05-14 13:30:00');
INSERT INTO resenas(calificacion, comentario, creado_en) VALUES (4, 'Buen servicio, cumplió con lo solicitado.', '2026-05-15 18:30:00');
INSERT INTO resenas(calificacion, comentario, creado_en) VALUES (5, 'Muy buena cuidadora, transmite confianza.', '2026-05-16 20:00:00');