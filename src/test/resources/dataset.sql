
insert into cuenta values(1, 'mariaperez@gmail.com', '1');
insert into cuenta values(2, 'juanita@gmail.com', '2');
insert into cuenta values(3, 'carlos@gmail.com', 'carlos');
insert into cuenta values(4, 'laura@gmail.com', '4');
insert into cuenta values(5, 'andres@gmail.com', '5');
insert into cuenta values (6, 'ricardo@gmail.com', 'contrasenia');
insert into cuenta values (7, 'lorena@gmail.com', 'fgag');
insert into cuenta values (8, 'superalejo20@gmail.com', 'paciente1234');
insert into cuenta values (9, 'jaime@gmail.com', 'hjkghklog');
insert into cuenta values (10, 'brian@gmail.com', 'gfdgdgf');
insert into cuenta values (11, 'admin@gmail.com', 'admin1234');



INSERT INTO alergia  VALUES (1,'Maní');
INSERT INTO alergia  VALUES (2,'Mariscos');
INSERT INTO alergia VALUES (3,'Gatos');
INSERT INTO alergia VALUES (4,'Polvo');

INSERT INTO eps VALUES (1,'medimas');
INSERT INTO eps VALUES (2,'sanitas');
INSERT INTO eps VALUES (3,'sura');
INSERT INTO eps VALUES (4,'cafe salud');
INSERT INTO eps VALUES (5,'sos');


insert into medico values('1',1, 0, 'Maria Perez', '315678900', 'URLFOTO', 1, 1 );
insert into medico values('2', 2, 0, 'Juanita', '315454578', 'URLFOTO', 0, 2);
insert into medico values('321', 3, 0, 'carlos marin', '315454578', 'URLFOTO', 2, 3);
insert into medico values('4', 3, 0, 'laura suarez', '31551454', 'URLFOTO', 3, 4);
insert into medico values('5', 0, 0, 'andres escobar', '3156941654', 'URLFOTO', 3, 5);

insert into paciente values('554',1,0,'ricardo martinez', '3112457854', 'url_foto', '1991-05-19', 0, 6, 1, 1);
insert into paciente values('10949527',0,0,'lorena diaz', '31045451', 'url_foto', '1992-08-19', 1, 7, 2, 5);
insert into paciente values('10874982',1,0,'roberto gomez', '31454545', 'url_foto', '1984-12-19', 2, 8, 1, 1);
insert into paciente values('121664874',2,0,'jaime agudelo', '315649848', 'url_foto', '1960-11-19', 4, 9, 2, 2);
insert into paciente values('15478499',3,0,'brian castaño', '316879848', 'url_foto', '2000-05-19', 3, 10, 3, 2);

insert into administrador values('1234567890',0,0,'admin principal', '315678900',  'URLFOTO',11);

insert into cita values (1, 2, '2023-11-10 13:44', '2023-11-03 07:45', 'dolor de cabeza', 2, 6);
insert into cita values (2, 3, '2023-09-10 08:44', '2023-09-01 09:55', 'control embarazo', 3, 7);
insert into cita values (3, 1, '2023-03-10 14:44', '2023-03-01 08:59', 'extraccion muela', 4, 8);
insert into cita values (4, 0, '2023-03-10 15:44', '2023-03-02 08:25', 'diarrea', 3, 9);
insert into cita values (5, 1, '2023-07-07 09:58', '2023-06-28 08:20', 'infarto', 4, 10);

insert into pqrs values (1, 'demora en servicio', 0, '2023-11-03 07:45', 'queja', 1);
insert into pqrs values (2, 'solicitud de traslado', 0, '2023-09-11 07:45', 'peticion', 2);
insert into pqrs values (3, 'reclamo de medicamentos', 0, '2023-03-03 07:45', 'reclamo', 3);
insert into pqrs values (4, 'demora en servicio', 0, '2023-03-03 07:45', 'queja', 3);
insert into pqrs values (5, 'solicitud de traslado', 0, '2023-06-29 07:45', 'solicitud', 5);

insert into mensaje values (1, 'buen dia requiero atencion', null,  '2023-11-03 07:45', 6, null, 1);
insert into mensaje values (2, 'solicito mejor servicio', null,  '2023-09-11 07:45', 7, null, 2);
insert into mensaje values (3, 'mis medicamentos estan vencidos', null,  '2023-03-03 07:45', 8, null, 3);
insert into mensaje values (4, 'el servicio es muy malo', null,  '2023-03-03 07:45', 9, null, 3);
insert into mensaje values (5, 'solicito traslado a cali', null,  '2023-06-29 07:45', 10, null, 5);
