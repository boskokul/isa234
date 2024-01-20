--role
INSERT INTO isa.ROLE(id, name) VALUES (1, 'ROLE_REGISTERED_USER');
INSERT INTO isa.ROLE(id, name) VALUES (2, 'ROLE_COMPANY_ADMIN');
INSERT INTO isa.ROLE(id, name) VALUES (3, 'ROLE_SYSTEM_ADMIN');
--kompanije
INSERT INTO isa.companies(id, country, city, average_grade, description, name, start_time, end_time, lat, lon, street, house_number)
	VALUES (-1, 'Serbia', 'Belgrade', 3, 'opis1', 'kompanija 1', '08:00:00', '15:00:00', 44.798466, 20.471237, 'Mutapova', 19);
INSERT INTO isa.companies(id, country, city, average_grade, description, name, start_time, end_time, lat, lon, street, house_number)
	VALUES (-2, 'Serbia',  'Novi Sad', 5, 'opis2', 'kompanija 2', '09:00:00', '16:00:00', 45.244743, 19.836466, 'Alekse Santica', 3);
--oprema
INSERT INTO isa.equipments(
	id, description, name, type, company_id, free_amount, reserved_amount)
	VALUES (-1, 'hirurske maske', 'oprema1', 1, -1, 100, 0);
INSERT INTO isa.equipments(
	id, description, name, type, company_id, free_amount, reserved_amount)
	VALUES (-2, 'lateks rukavice', 'oprema2', 0, -1, 50, 0);
INSERT INTO isa.equipments(
    id, description, name, type, company_id, free_amount, reserved_amount)
    VALUES (-3, 'spricevi', 'oprema3', 0, -1, 40, 0);
INSERT INTO isa.equipments(
    id, description, name, type, company_id, free_amount, reserved_amount)
    VALUES (-4, 'slusni aparati', 'oprema4', 1, -2, 45, 0);
--useri
INSERT INTO isa.base_users(
    id, city, country, username, first_name, last_name, password, phone, verified)
VALUES (-1, 'ns', 'srb', 'bfd@gmail.com', 'marko', 'nikic', '$2a$12$ztIwerX104rJ41MYLl7GDeaKMsK3ENPxFdqnwgLk2GjqEz2Re/qsW', '06314222', true);
INSERT INTO isa.base_users(
	id, city, country, username, first_name, last_name, password, phone, verified)
	VALUES (-2, 'bg', 'srb', 'aad@gmail.com', 'nemanja', 'zigic', '$2a$12$ztIwerX104rJ41MYLl7GDeaKMsK3ENPxFdqnwgLk2GjqEz2Re/qsW', '064324562', false);
INSERT INTO isa.base_users(
    id, city, country, username, first_name, last_name, password, phone, verified)
VALUES (-3, 'nis', 'srb', 'huj@gmail.com', 'nika', 'nikic', '$2a$12$ztIwerX104rJ41MYLl7GDeaKMsK3ENPxFdqnwgLk2GjqEz2Re/qsW', '06303222', true);
INSERT INTO isa.base_users(
    id, city, country, username, first_name, last_name, password, phone, verified)
VALUES (-4, 'sub', 'srb', 'dad@gmail.com', 'nikola', 'zigic', '$2a$12$ztIwerX104rJ41MYLl7GDeaKMsK3ENPxFdqnwgLk2GjqEz2Re/qsW', '061324562', true);
INSERT INTO isa.base_users(
    id, city, country, username, first_name, last_name, password, phone, verified)
VALUES (-5, 'zr', 'srb', 'admin@gmail.com', 'nemanja', 'matic', '$2a$12$ztIwerX104rJ41MYLl7GDeaKMsK3ENPxFdqnwgLk2GjqEz2Re/qsW', '061324567', true);
INSERT INTO isa.base_users(
    id, city, country, username, first_name, last_name, password, phone, verified)
VALUES (-6, 'bg', 'srb', 'sysadmin@gmail.com', 'nemanja', 'nikolic', '$2a$12$ztIwerX104rJ41MYLl7GDeaKMsK3ENPxFdqnwgLk2GjqEz2Re/qsW', '061324567', false);
INSERT INTO isa.base_users(id, city, country, username, first_name, last_name, password, phone, verified)
VALUES (-7, 'bg', 'srb', 'nemanja@gmail.com', 'stefan', 'nemanjic', '$2a$12$ztIwerX104rJ41MYLl7GDeaKMsK3ENPxFdqnwgLk2GjqEz2Re/qsW', '061324567', true);


INSERT INTO isa.system_admins(
    id)
VALUES (-6);
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
INSERT INTO isa.registered_users(penal_points, category, company_info, profession, id) VALUES (0, 0, 'info3', 'profesija3', -7);
INSERT INTO isa.user_role(user_id, role_id) VALUES (-1, 2);
INSERT INTO isa.user_role(user_id, role_id) VALUES (-2, 2);
INSERT INTO isa.user_role(user_id, role_id) VALUES (-3, 1);
INSERT INTO isa.user_role(user_id, role_id) VALUES (-4, 1);
INSERT INTO isa.user_role(user_id, role_id) VALUES (-7, 1);
INSERT INTO isa.user_role(user_id, role_id) VALUES (-5, 2);
INSERT INTO isa.user_role(user_id, role_id) VALUES (-6, 3);
--apointmenti (predefinisani termini)
INSERT INTO isa.appointments(
	id, date_time, duration, company_admin_id)
	VALUES (-1, '2024-1-27T15:18:12', 30, -5);

INSERT INTO isa.appointments(
    id, date_time, duration, company_admin_id)
VALUES (-2, '2024-1-21T16:48:12', 30, -5);

INSERT INTO isa.appointments(
    id, date_time, duration, company_admin_id)
VALUES (-3, '2024-1-28T15:18:12', 30, -5);

INSERT INTO isa.appointments(
    id, date_time, duration, company_admin_id)
VALUES (-4, '2024-1-21T11:18:12', 30, -2);
INSERT INTO isa.appointments(
    id, date_time, duration, company_admin_id)
VALUES (-5, '2024-1-17T15:18:12', 30, -5);
INSERT INTO isa.appointments(
    id, date_time, duration, company_admin_id)
VALUES (-6, '2024-1-27T15:18:12', 30, -2);
--rezervacije (one koriste te predefinisane termine u sebi)
	
