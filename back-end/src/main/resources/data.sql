--oprema
INSERT INTO isa.equipments(
	id, description, name, type)
	VALUES (1, 'opisoprema1', 'oprema1', 1);
INSERT INTO isa.equipments(
	id, description, name, type)
	VALUES (2, 'opisoprema2', 'oprema2', 0);
--kompanije
INSERT INTO isa.companies(id, country, city, average_grade, description, name) VALUES (1, 'Stevana', 'Hatale 1', 3, 'opis1', 'kompanija 1');
INSERT INTO isa.companies(id, country, city, average_grade, description, name) VALUES (2, 'Ulica',  '1', 5, 'opis2', 'kompanija 2');
--veze kompanija i oprema
INSERT INTO isa.company_equipment(
	equipment_id, company_id)
	VALUES (1, 2);
INSERT INTO isa.company_equipment(
	equipment_id, company_id)
	VALUES (2, 2);
INSERT INTO isa.company_equipment(
	equipment_id, company_id)
	VALUES (1, 1);
--useri
INSERT INTO isa.users(
    id, city, company_info, country, email, first_name, last_name, password, phone, profession, role, company_id)
VALUES (1, 'BP', 'informacija 1', 'Srbija', 'ivanpartalo123@gmail.com', 'ivan', 'partalo', '123', 002, 'student', 0, 1);
INSERT INTO isa.users(
    id, city, company_info, country, email, first_name, last_name, password, phone, profession, role, company_id)
VALUES (2, 'NS', 'informacija 2', 'Srbija', 'bosko@gmail.com', 'bosko', 'kulusic', '123', 003, 'student', 0, 2);
