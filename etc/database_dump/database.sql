CREATE TABLE tb01_user (
  tb01_user_id int AUTO_INCREMENT,
  tb01_name varchar(255) DEFAULT NULL,
  tb01_active boolean,
  tb01_email varchar(500) DEFAULT NULL,
  tb01_city varchar(255) DEFAULT NULL,
  tb01_dob date DEFAULT NULL,
  tb01_created_on date DEFAULT NULL,
  tb01_surname varchar(255) DEFAULT NULL,
  PRIMARY KEY(tb01_user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla tb01_user
--

INSERT INTO tb01_user (tb01_name, tb01_active, tb01_email, tb01_city, tb01_dob, tb01_created_on, tb01_surname) VALUES
('Stanley', 0, 'stanley@fifa.com', 'Stoke-on-Trent', '1915-02-01', '2020-12-07', ' Matthews'),
('Alfredo', 0, 'alfedo@fifa.com', 'Buenos Aires', '1926-07-04', '2020-12-07', 'Di Stefan'),
('Raymond ', 0, 'raymond@fifa.com', 'Nœux-les-Mines', '1931-10-13', '2020-12-07', 'Kopa'),
('Luis', 1, 'luissuarez@fifa.com', 'La Coruña', '1935-05-02', '2020-12-07', 'Suarez'),
('Omar', 0, 'omar@fifa.com', 'San Nicolás de Los Arroyos', '1935-10-02', '2020-12-07', 'Sívori'),
('Josef', 0, 'josef@fifa.com', 'Praga', '1931-02-09', '2020-12-07', 'Masopust'),
('Lev', 0, 'lev@fifa.com', 'Moscú', '1929-10-22', '2020-12-07', 'Yashin'),
('Denis', 1, 'denis@fifa.com', 'Abeerdeen', '1940-02-24', '2020-12-07', 'Law'),
('Eusébio', 0, 'eusebio@fifa.com', 'Maputo', '1942-01-25', '2020-12-07', 'Da Silva Ferreira'),
('Bobby', 1, 'boby@fifa.com', 'Ashington', '1932-10-11', '2020-12-07', 'Charlton'),
('George', 0, 'george@fifa.com', 'Belfast', '1946-05-22', '2020-12-07', 'Best'),
('Gianni', 1, 'gianni@fifa.com', 'Alessandria', '1943-08-19', '2020-12-07', 'Rivera'),
('Gerd', 1, 'gerd@fifa.com', 'Nördlingen', '1945-11-03', '2020-12-07', 'Müller'),
('Johan', 0, 'johan@fifa.com', 'Ámsterdam', '1947-04-25', '2020-12-07', 'Cruyff'),
('Franz', 1, 'franz@fifa.com', 'Múnich', '1947-04-25', '2020-12-07', 'Beckenbauer'),
('Oleg', 1, 'oleg@fifa.com', 'Kiev', '1952-11-05', '2020-12-07', 'Blokhin'),
('Allan', 1, 'allan@fifa.com', 'Vejle', '1952-12-15', '2020-12-07', 'Simonsen'),
('Kevin', 1, 'kevin@fifa.com', 'Armthorpe', '1951-02-14', '2020-12-07', 'Keegan'),
('Karl-Heinz', 1, 'karl@fifa.com', 'Lippstadt', '1955-09-25', '2020-12-07', 'Rummenigge'),
('Paolo', 1, 'paolo@gmail.com', 'Prato', '1956-09-23', '2020-12-07', 'Rossi'),
('Michael', 1, 'platini@gmail.com', 'Jœuf ', '1955-06-21', '2020-12-07', 'Platini'),
('Ruud', 1, 'ruud@fifa.com', 'Amsterdam', '1962-09-01', '2020-12-07', 'Gullit'),
('Marco ', 1, 'vanbasten@fifa.com', 'Utretch', '1964-10-31', '2020-12-07', 'Van Basten'),
('Lothar', 1, 'lothar@fifa.com', 'Erlangen', '1961-03-31', '2020-12-07', 'Matthäus'),
('Jean-Pierre', 1, 'jean-pierre@fifa.com', 'Boulogne-sur-Mer', '1963-12-05', '2020-12-07', 'Papin'),
('Roberto', 1, 'roberto@fifa.com', 'Caldogno', '1967-02-18', '2020-12-07', 'Baggio');