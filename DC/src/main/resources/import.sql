INSERT INTO `isuredatacenter`.`insurance_category` (`active`, `created`, `updated`, `category_name`) VALUES( TRUE, NOW(), null, "International Travel"),( TRUE, NOW(), null, "Roadside"),( TRUE, NOW(), null, "Home");
INSERT INTO `isuredatacenter`.`risk_type` (`active`, `created`, `updated`, `risk_type_name`, `insurance_category_id`) VALUES(TRUE, NOW(), null, "Region", 1),(TRUE, NOW(), null, "Age", 1),(TRUE, NOW(), null, "Sport", 1),(TRUE, NOW(), null, "Max insurance cover (to price)", 1),(TRUE, NOW(), null, "Towing (to km)", 2),(TRUE, NOW(), null, "Repair (to price $)", 2),(TRUE, NOW(), null, "Accommodation (in days)", 2),(TRUE, NOW(), null, "Alternative ride", 2),(TRUE, NOW(), null, "Surface area", 3),(TRUE, NOW(), null, "Property age", 3),(TRUE, NOW(), null, "Estimated value of property", 3),(TRUE, NOW(), null, "Ensured from", 3);
INSERT INTO `isuredatacenter`.`risk` (`active`, `created`, `updated`, `risk_name`, `risk_type_id`) VALUES(TRUE, NOW(), null, "Europe",  1),(TRUE, NOW(), null, "Asia",  1),(TRUE, NOW(), null, "America", 1),(TRUE, NOW(), null, "Japan",  1),(TRUE, NOW(), null, "Up to 18 yo",  2),(TRUE, NOW(), null, "18 - 60 yo",  2),(TRUE, NOW(), null, "Over 60 yo",  2),(TRUE, NOW(), null, "Football", 3),(TRUE, NOW(), null, "Tennis", 3),(TRUE, NOW(), null, "Basketball", 3),(TRUE, NOW(), null, "Skiing", 3),(TRUE, NOW(), null, "1000 $ ", 4),(TRUE, NOW(), null, "5000 $", 4),(TRUE, NOW(), null, "10000 $", 4),(TRUE, NOW(), null, "25000 $", 4),(TRUE, NOW(), null, "50000 $",4),(TRUE, NOW(), null, "over 50000 $",4),(TRUE, NOW(), null, "up to 50km", 5),(TRUE, NOW(), null, "up to 100km", 5),(TRUE, NOW(), null, "up to 500km", 5),(TRUE, NOW(), null, "50 $", 6),(TRUE, NOW(), null, "100 $", 6),(TRUE, NOW(), null, "200 $", 6),(TRUE, NOW(), null, "500 $", 6),(TRUE, NOW(), null, "1", 7),(TRUE, NOW(), null, "5", 7),(TRUE, NOW(), null, "10", 7),(TRUE, NOW(), null, "Taxi", 8),(TRUE, NOW(), null, "Bus", 8),(TRUE, NOW(), null, "Train", 8),(TRUE, NOW(), null, "Plane", 8),(TRUE, NOW(), null, "up to 30 m2", 9),(TRUE, NOW(), null, "up to 50 m2",9),(TRUE, NOW(), null, "up to 100 m2",9),(TRUE, NOW(), null, "over 100 m2",9),(TRUE, NOW(), null, "up to 1 year", 10),(TRUE, NOW(), null, "up to 5 years", 10),(TRUE, NOW(), null, "up to 10 years", 10),(TRUE, NOW(), null, "over 10 years", 10),(TRUE, NOW(), null, "up to 5000 $", 11),(TRUE, NOW(), null, "up to 10000 $", 11),(TRUE, NOW(), null, "up to 50000 $", 11),(TRUE, NOW(), null, "up to 100000 $", 11),(TRUE, NOW(), null, "over 100000 $", 11),(TRUE, NOW(), null, "Flood", 12),(TRUE, NOW(), null, "Fire", 12),(TRUE, NOW(), null, "Theft", 12),(TRUE, NOW(), null, "Breakage", 12),(TRUE, NOW(), null, "All", 12);
INSERT INTO `isuredatacenter`.`pricelist` (`active`, `created`, `updated`, `date_from`, `date_to`) VALUES(TRUE, NOW(), null, '2017-11-1', '2018-12-1');
INSERT INTO `isuredatacenter`.`pricelist_item` (`active`, `created`, `updated`, `coefficient`, `price`, `risk_id`, `pricelist_id`) VALUES (TRUE, NOW(), null, '1', '50', '1', '1'),  (TRUE, NOW(), null, '1.5', '50', '2', '1'),  (TRUE, NOW(), null, '1.3', '50', '3', '1'),  (TRUE, NOW(), null, '1.3', '50', '4', '1'),  (TRUE, NOW(), null, '0.5', '20', '5', '1'),  (TRUE, NOW(), null, '1', '20', '6', '1'),  (TRUE, NOW(), null, '1.5', '20', '7', '1'),  (TRUE, NOW(), null, '1.5', '100', '8', '1'),  (TRUE, NOW(), null, '1', '100', '9', '1'),  (TRUE, NOW(), null, '0.5', '100', '10', '1'),  (TRUE, NOW(), null, '2', '100', '11', '1'),  (TRUE, NOW(), null, '0.1', '10', '12', '1'),  (TRUE, NOW(), null, '0.2', '10', '13', '1'),  (TRUE, NOW(), null, '0.3', '10', '14', '1'),  (TRUE, NOW(), null, '0.4', '10', '15', '1'),  (TRUE, NOW(), null, '0.5', '10', '16', '1'),  (TRUE, NOW(), null, '0.6', '10', '17', '1'),  (TRUE, NOW(), null, '1', '10', '18', '1'),  (TRUE, NOW(), null, '1.1', '10', '19', '1'),  (TRUE, NOW(), null, '1.2', '10', '20', '1'),  (TRUE, NOW(), null, '1', '5', '21', '1'),  (TRUE, NOW(), null, '1.1', '5', '22', '1'),  (TRUE, NOW(), null, '1.2', '5', '23', '1'),  (TRUE, NOW(), null, '1.3', '5', '24', '1'),  (TRUE, NOW(), null, '1.3', '5', '25', '1'),  (TRUE, NOW(), null, '1.3', '5', '26', '1'),  (TRUE, NOW(), null, '1.5', '30', '27', '1'),  (TRUE, NOW(), null, '1', '30', '28', '1'),  (TRUE, NOW(), null, '1.2', '30', '29', '1'),  (TRUE, NOW(), null, '2', '30', '30', '1'),  (TRUE, NOW(), null, '1', '50', '31', '1'),  (TRUE, NOW(), null, '1.1', '50', '32', '1'),  (TRUE, NOW(), null, '1.2', '50', '33', '1'), (TRUE, NOW(), null, '1.3', '50', '34', '1'),  (TRUE, NOW(), null, '1', '10', '35', '1'),  (TRUE, NOW(), null, '1.1', '20', '36', '1'),  (TRUE, NOW(), null, '1.2', '30', '37', '1'),  (TRUE, NOW(), null, '1.3', '40', '38', '1'),  (TRUE, NOW(), null, '1', '10', '39', '1'),  (TRUE, NOW(), null, '1.1', '10', '40', '1'),  (TRUE, NOW(), null, '1.2', '10', '41', '1'),  (TRUE, NOW(), null, '1.3', '10', '42', '1'),  (TRUE, NOW(), null, '1.4', '10', '43', '1'),  (TRUE, NOW(), null, '0.9', '10', '44', '1'),  (TRUE, NOW(), null, '1', '10', '45', '1'),  (TRUE, NOW(), null, '1.1', '10', '46', '1'),  (TRUE, NOW(), null, '1.2', '10', '47', '1'),  (TRUE, NOW(), null, '1.3', '10', '48', '1'), (TRUE, NOW(), null, '1.4', '10', '49', '1');
INSERT INTO `isuredatacenter`.`payment_type` (`active`, `created`, `updated`, `label`) VALUES(TRUE, NOW(), null, 'acquirer'), (TRUE, NOW(), null, 'paypal');

insert into  `isuredatacenter`.`role` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'ROLE_analyst');
insert into  `isuredatacenter`.`role` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'ROLE_salesman');

INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'READ_ALL_PRICELISTS');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'CREATE_PRICELIST');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'UPDATE_PRICELIST');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'DELETE_PRICELIST');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'FIND_PRICELIST_BY_ID');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'FIND_CURRENTLY_ACTIVE_PRICELIST');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'FIND_MAX_DATE_TO_PRICELIST');

INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'READ_ALL_RISK_TYPES');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'CREATE_RISK_TYPE');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'UPDATE_RISK_TYPE');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'DELETE_RISK_TYPE');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'FIND_RISK_TYPE_BY_ID');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'FIND_RISK_TYPE_BY_NAME');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'FIND_RISK_TYPE_BY_CATEGORY');

INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'READ_ALL_RISKS');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'CREATE_RISK');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'UPDATE_RISK');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'DELETE_RISK');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'FIND_RISK_BY_ID');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'FIND_RISK_BY_NAME');
INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'FIND_RISK_BY_RISK_TYPE');


insert into `isuredatacenter`.`role_permissions` values (1, 1);
insert into `isuredatacenter`.`role_permissions` values (1, 2);
insert into `isuredatacenter`.`role_permissions` values (1, 3);
insert into `isuredatacenter`.`role_permissions` values (1, 4);
insert into `isuredatacenter`.`role_permissions` values (1, 5);
insert into `isuredatacenter`.`role_permissions` values (1, 6);
insert into `isuredatacenter`.`role_permissions` values (1, 7);
insert into `isuredatacenter`.`role_permissions` values (1, 8);
insert into `isuredatacenter`.`role_permissions` values (1, 9);
insert into `isuredatacenter`.`role_permissions` values (1, 10);
insert into `isuredatacenter`.`role_permissions` values (1, 11);
insert into `isuredatacenter`.`role_permissions` values (1, 12);
insert into `isuredatacenter`.`role_permissions` values (1, 13);
insert into `isuredatacenter`.`role_permissions` values (1, 14);
insert into `isuredatacenter`.`role_permissions` values (1, 15);
insert into `isuredatacenter`.`role_permissions` values (1, 16);
insert into `isuredatacenter`.`role_permissions` values (1, 17);
insert into `isuredatacenter`.`role_permissions` values (1, 18);
insert into `isuredatacenter`.`role_permissions` values (1, 19);
insert into `isuredatacenter`.`role_permissions` values (1, 20);
insert into `isuredatacenter`.`role_permissions` values (1, 21);
insert into `isuredatacenter`.`role_permissions` values (2, 1);
insert into `isuredatacenter`.`role_permissions` values (2, 2);
insert into `isuredatacenter`.`role_permissions` values (2, 3);
insert into `isuredatacenter`.`role_permissions` values (2, 4);
insert into `isuredatacenter`.`role_permissions` values (2, 5);
insert into `isuredatacenter`.`role_permissions` values (2, 6);
insert into `isuredatacenter`.`role_permissions` values (2, 7);
insert into `isuredatacenter`.`role_permissions` values (2, 8);
insert into `isuredatacenter`.`role_permissions` values (2, 9);
insert into `isuredatacenter`.`role_permissions` values (2, 10);
insert into `isuredatacenter`.`role_permissions` values (2, 11);
insert into `isuredatacenter`.`role_permissions` values (2, 12);
insert into `isuredatacenter`.`role_permissions` values (2, 13);
insert into `isuredatacenter`.`role_permissions` values (2, 14);
insert into `isuredatacenter`.`role_permissions` values (2, 15);
insert into `isuredatacenter`.`role_permissions` values (2, 16);
insert into `isuredatacenter`.`role_permissions` values (2, 17);
insert into `isuredatacenter`.`role_permissions` values (2, 18);
insert into `isuredatacenter`.`role_permissions` values (2, 19);
insert into `isuredatacenter`.`role_permissions` values (2, 20);
insert into `isuredatacenter`.`role_permissions` values (2, 21);


