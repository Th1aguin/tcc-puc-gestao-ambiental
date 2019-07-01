insert into role (name) values('ADMIN');
insert into role (name) values('ENGENHEIRO');
insert into role (name) values('CONSULTOR');
insert into user (password,username) values('$2a$10$WZS3w7/BSsWpI94LBoZ.juVhBq5KXK16quEiV7iLt91sPCLKNWLAC','admin');
insert into user_roles (users_id,roles_id) values(1,1);
insert into barragem (nome,latitude,longitude,minerio,metodo) values('Barragem 01','15.456123','-15.123456','Areia','ETAPA_UNICA' );