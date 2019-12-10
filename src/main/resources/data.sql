INSERT INTO user(Correo, Nombres, Apellidos,DNI,Edad,Alergia, Clave) VALUES ('junior@tecsup', 'alfredo', 'vilchez','75600611','18','NA', '$2a$04$tOkXfqhxaZQA3JgGIGO7out4IRH07Q50UGMWdcLALVZd5vBt5OI/e');
INSERT INTO user(Correo, Nombres, Apellidos,DNI,Edad,Alergia, Clave) VALUES ('alfredo@tecsup', 'alfredo', 'vilchez','75600611','18','NA', '$2a$04$tOkXfqhxaZQA3JgGIGO7out4IRH07Q50UGMWdcLALVZd5vBt5OI/e');
INSERT INTO user(Correo, Nombres, Apellidos,DNI,Edad,Alergia, Clave) VALUES ('uno@tecsup', 'Junior', 'vilchez','75600611','18','NA', '123');
INSERT INTO user(Correo, Nombres, Apellidos,DNI,Edad,Alergia, Clave) VALUES ('dos@tecsup', 'Junior', 'vilchez','75600611','18','NA', '1234');


INSERT INTO role (description, name) VALUES ('ROLE_ADMIN', 'ADMIN');
INSERT INTO role (description, name) VALUES ('ROLE_USER', 'USER');
INSERT INTO role (description, name) VALUES ('ROLE_SUPERVISOR', 'SUPERVISOR');

INSERT INTO user_roles (user_id, role_id) VALUES ('1', '1');
INSERT INTO user_roles (user_id, role_id) VALUES ('2', '2');
INSERT INTO user_roles (user_id, role_id) VALUES ('3', '1');
INSERT INTO user_roles (user_id, role_id) VALUES ('4', '2');