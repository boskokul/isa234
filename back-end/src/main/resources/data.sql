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
	id, city, country, email, first_name, last_name, password, phone, role)
	VALUES (-1, 'ns', 'srb', 'bfd@gmail.com', 'marko', 'nikic', 'ftn1', '06314222', 1);
INSERT INTO isa.base_users(
	id, city, country, email, first_name, last_name, password, phone, role)
	VALUES (-2, 'bg', 'srb', 'aad@gmail.com', 'nemanja', 'zigic', 'ftn2', '064324562', 1);
INSERT INTO isa.base_users(
    id, city, country, email, first_name, last_name, password, phone, role)
VALUES (-3, 'nis', 'srb', 'huj@gmail.com', 'nika', 'nikic', 'ftn3', '06303222', 0);
INSERT INTO isa.base_users(
    id, city, country, email, first_name, last_name, password, phone, role)
VALUES (-4, 'sub', 'srb', 'dad@gmail.com', 'nikola', 'zigic', 'ftn4', '061324562', 0);
INSERT INTO isa.base_users(
    id, city, country, email, first_name, last_name, password, phone, role)
VALUES (-5, 'zr', 'srb', 'admin@gmail.com', 'nemanja', 'matic', 'ftn5', '061324567', 1);
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
	
	