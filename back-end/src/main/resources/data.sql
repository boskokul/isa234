--role
INSERT INTO isa.ROLE(id, name) VALUES (1, 'ROLE_REGISTERED_USER');
INSERT INTO isa.ROLE(id, name) VALUES (2, 'ROLE_COMPANY_ADMIN');
INSERT INTO isa.ROLE(id, name) VALUES (3, 'ROLE_SYSTEM_ADMIN');
--oprema
INSERT INTO isa.equipments(
	id, description, name, type)
	VALUES (-1, 'opisoprema1', 'oprema1', 1);
INSERT INTO isa.equipments(
	id, description, name, type)
	VALUES (-2, 'opisoprema2', 'oprema2', 0);
INSERT INTO isa.equipments(
    id, description, name, type)
    VALUES (-3, 'opisoprema3', 'oprema3', 0);
INSERT INTO isa.equipments(
    id, description, name, type)
    VALUES (-4, 'opisoprema4', 'oprema4', 1);
--kompanije
INSERT INTO isa.companies(id, country, city, average_grade, description, name) VALUES (-1, 'Srbija', 'BG', 3, 'opis1', 'kompanija 1');
INSERT INTO isa.companies(id, country, city, average_grade, description, name) VALUES (-2, 'Hrvatska',  'ZG', 5, 'opis2', 'kompanija 2');
--veze kompanija i oprema
INSERT INTO isa.company_equipment(
	equipment_id, company_id)
	VALUES (-1, -2);
INSERT INTO isa.company_equipment(
	equipment_id, company_id)
	VALUES (-2, -2);
INSERT INTO isa.company_equipment(
	equipment_id, company_id)
	VALUES (-1, -1);
INSERT INTO isa.company_equipment(
    equipment_id, company_id)
    VALUES (-3, -1);
INSERT INTO isa.company_equipment(
    equipment_id, company_id)
    VALUES (-4, -2);
--useri
INSERT INTO isa.base_users(
	id, city, country, username, first_name, last_name, password, phone, verified)
	VALUES (-1, 'ns', 'srb', 'bfd@gmail.com', 'marko', 'nikic', '$2a$12$ztIwerX104rJ41MYLl7GDeaKMsK3ENPxFdqnwgLk2GjqEz2Re/qsW', '06314222', true);
INSERT INTO isa.base_users(
	id, city, country, username, first_name, last_name, password, phone, verified)
	VALUES (-2, 'bg', 'srb', 'aad@gmail.com', 'nemanja', 'zigic', '$2a$12$ztIwerX104rJ41MYLl7GDeaKMsK3ENPxFdqnwgLk2GjqEz2Re/qsW', '064324562', true);
INSERT INTO isa.base_users(
    id, city, country, username, first_name, last_name, password, phone, verified)
VALUES (-3, 'nis', 'srb', 'huj@gmail.com', 'nika', 'nikic', '$2a$12$ztIwerX104rJ41MYLl7GDeaKMsK3ENPxFdqnwgLk2GjqEz2Re/qsW', '06303222', true);
INSERT INTO isa.base_users(
    id, city, country, username, first_name, last_name, password, phone, verified)
VALUES (-4, 'sub', 'srb', 'dad@gmail.com', 'nikola', 'zigic', '$2a$12$ztIwerX104rJ41MYLl7GDeaKMsK3ENPxFdqnwgLk2GjqEz2Re/qsW', '061324562', true);
INSERT INTO isa.base_users(
    id, city, country, username, first_name, last_name, password, phone, verified)
VALUES (-5, 'zr', 'srb', 'admin@gmail.com', 'nemanja', 'matic', '$2a$12$ztIwerX104rJ41MYLl7GDeaKMsK3ENPxFdqnwgLk2GjqEz2Re/qsW', '061324567', true);
INSERT INTO isa.companys_admins(
	id, company_id)
	VALUES (-1, -2);
INSERT INTO isa.companys_admins(
	id, company_id)
	VALUES (-2, -1);
INSERT INTO isa.companys_admins(
    id, company_id)
    VALUES (-5, -1);
INSERT INTO isa.registered_users(penal_points, category, company_info, profession, id) VALUES (0, 0, 'info1', 'profesija1', -3);
INSERT INTO isa.registered_users(penal_points, category, company_info, profession, id) VALUES (0, 0, 'info2', 'profesija2', -4);
INSERT INTO isa.user_role(user_id, role_id) VALUES (-1, 2);
INSERT INTO isa.user_role(user_id, role_id) VALUES (-2, 2);
INSERT INTO isa.user_role(user_id, role_id) VALUES (-3, 1);
INSERT INTO isa.user_role(user_id, role_id) VALUES (-4, 1);
INSERT INTO isa.user_role(user_id, role_id) VALUES (-5, 2);
