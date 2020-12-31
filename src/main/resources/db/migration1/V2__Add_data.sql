INSERT INTO public.cargo (id, name, number, status, weigh) VALUES (1, 'Flour', 'A1S2D3', 'PREPARED', null);

INSERT INTO public.city (id, latitude, longitude, name) VALUES (1, 59.57, 30.19, 'Saint-Petersburg');
INSERT INTO public.city (id, latitude, longitude, name) VALUES (2, 30.19, 37.36, 'Moscow');

INSERT INTO public.wagon (id, brand, capacity, drivernumber, registrynumber, status, currentcity_id) VALUES (1, 'Volvo', 10, 4, 'V0001OL', null, null);
INSERT INTO public.wagon (id, brand, capacity, drivernumber, registrynumber, status, currentcity_id) VALUES (2, 'Mercedes', 10, 4, 'M0002ER', null, null);
INSERT INTO public.wagon (id, brand, capacity, drivernumber, registrynumber, status, currentcity_id) VALUES (3, 'Renault', 5, 4, 'R0003EN', null, null);
INSERT INTO public.wagon (id, brand, capacity, drivernumber, registrynumber, status, currentcity_id) VALUES (4, 'Mack', 5, 4, 'S0004CA', null, null);
INSERT INTO public.wagon (id, brand, capacity, drivernumber, registrynumber, status, currentcity_id) VALUES (5, 'Harley Davidson', 20, 4, 'H005AD', null, null);

INSERT INTO public.distance (id, distance, first_id, second_id) VALUES (1, 705.60, 1, 2);

INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (1, 'ezra.christiansen@yahoo.com', null, null, null, 'FD99EZA', 'Frankie', null, 'Doyle', null, 1);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (2, 'leon.hintz@gmail.com', null, null, null, 'DG73HLW', 'Dexter', null, 'Gardner', null, 1);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (3, 'stephan.gibson@hotmail.com', null, null, null, 'RW12FEA', 'Rory', null, 'Warren', null, 1);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (4, 'milton.lakin@hotmail.com', null, null, null, 'IS54COB', 'Isaac', null, 'Smith', null, 1);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (5, 'sol.beer@gmail.com', null, null, null, 'LW02JII', 'Leon', null, 'Warren', null, 2);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (6, 'drew.sauer@hotmail.com', null, null, null, 'CR81MVH', 'Caleb', null, 'Reynolds', null, 2);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (7, 'glayds.waelchi@hotmail.com', null, null, null, 'BP85PIH', 'Blake', null, 'Page', null, 2);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (8, 'simon.reynolds@gmail.com', null, null, null, 'AH87WTI', 'Arthur', null, 'Hall', null, 2);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (9, 'adela.terry@hotmail.com', null, null, null, 'AW55ENK', 'Arlo', null, 'Wells', null, 3);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (10, 'many.pouros@hotmail.com', null, null, null, 'RJ14WRO', 'Ronnie', null, 'Joyce', null, 3);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (11, 'rod.wiegand@hotmail.com', null, null, null, 'EP66NIU', 'Ezra', null, 'Perry', null, 3);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (12, 'delbert.cummerata@hotmail.com', null, null, null, 'OJ48SUG', 'Ollie', null, 'Jones', null, 3);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (13, 'garfield.spinka@hotmail.com', null, null, null, 'GH51QIJ', 'George', null, 'Hunter', null, 4);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (14, 'dreama.boyer@gmail.com', null, null, null, 'AB95BTW', 'Arthur', null, 'Barber', null, 4);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (15, 'marcelina.schuster@gmail.com', null, null, null, 'LR34HVN', 'Louie', null, 'Reed', null, 4);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (16, 'maxie.beer@yahoo.com', null, null, null, 'LH35YGO', 'Luca', null, 'Heath', null, 4);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (17, 'destiny.crist@hotmail.com', null, null, null, 'JM17UCD', 'Jasper', null, 'Morton', null, 5);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (18, 'jc.friesen@hotmail.com', null, null, null, 'TA74BPV', 'Theodore', null, 'Andrews', null, 5);
INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (19, 'sofia.turner@yahoo.com', null, null, null, 'JB44MQN', 'James', null, 'Bates', null, 5);

INSERT INTO public.driver (id, email, login, password, hoursincurrentmonth, identitynumber, name, status, surname, currentcity_id, wagon_id) VALUES (20, 'cedrick.wehner@gmail.com', null, null, null, 'TH27RJJ', 'Theo', null, 'Harris', null, 5);

INSERT INTO public.orders (id, number, status, wagon_id) VALUES (1, '1A', 'WAITING', null);

INSERT INTO public.waypoint (id, type, cargo_id, city_id, order_id) VALUES (1, 'LOADING', null, null, null);
INSERT INTO public.waypoint (id, type, cargo_id, city_id, order_id) VALUES (2, 'UNLOADING', null, null, null);