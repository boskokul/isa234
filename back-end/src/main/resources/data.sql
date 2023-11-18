--oprema
INSERT INTO isa.equipments(
	id, description, name, type)
	VALUES (-1, 'opisoprema1', 'oprema1', 1);
INSERT INTO isa.equipments(
	id, description, name, type)
	VALUES (-2, 'opisoprema2', 'oprema2', 0);
--kompanije
INSERT INTO isa.companies(id, adress, average_grade, description, name) VALUES (-1, 'Stevana Hatale 1', 3, 'opis1', 'kompanija 1');
INSERT INTO isa.companies(id, adress, average_grade, description, name) VALUES (-2, 'Ulica 1', 5, 'opis2', 'kompanija 2');
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
--useri
INSERT INTO isa.base_users(
	id, city, country, email, first_name, last_name, password, phone, role)
	VALUES (-1, 'ns', 'srb', 'bfd@gmail.com', 'marko', 'nikic', 'ftn', '06314222', 1);
INSERT INTO isa.base_users(
	id, city, country, email, first_name, last_name, password, phone, role)
	VALUES (-2, 'bg', 'srb', 'aad@gmail.com', 'nemanja', 'zigic', 'ftn', '064324562', 1);
INSERT INTO isa.base_users(
    id, city, country, email, first_name, last_name, password, phone, role)
VALUES (-3, 'nis', 'srb', 'huj@gmail.com', 'nika', 'nikic', 'ftn', '06303222', 0);
INSERT INTO isa.base_users(
    id, city, country, email, first_name, last_name, password, phone, role)
VALUES (-4, 'sub', 'srb', 'dad@gmail.com', 'nikola', 'zigic', 'ftn', '061324562', 0);
INSERT INTO isa.companys_admins(
	id, company_id)
	VALUES (-1, -2);
INSERT INTO isa.companys_admins(
	id, company_id)
	VALUES (-2, -1);
INSERT INTO isa.registered_users(company_info, profession, id) VALUES ('info1', 'profesija1', -3);
INSERT INTO isa.registered_users(company_info, profession, id) VALUES ('info2', 'profesija2', -4);
	
	