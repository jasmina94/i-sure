INSERT INTO `isuredatacenter`.`isure_users` (`id`,`password`,`username`) VALUES (1,"pera","pera"), (2,"mika","mika"), (3,"zika","zika");

INSERT INTO `isuredatacenter`.`insurance_category` (`active`, `created`, `updated`, `category_name`) VALUES ( TRUE, NOW(), null, "International Travel"), ( TRUE, NOW(), null, "Roadside"), ( TRUE, NOW(), null, "Home");

INSERT INTO `isuredatacenter`.`risk_type` (`active`, `created`, `updated`, `risk_type_name`, `insurance_category_id`) VALUES (TRUE, NOW(), null, "Region", 1), (TRUE, NOW(), null, "Age", 1), (TRUE, NOW(), null, "Sport", 1), (TRUE, NOW(), null, "Value", 1), (TRUE, NOW(), null, "Towing", 2), (TRUE, NOW(), null, "Repair", 2), (TRUE, NOW(), null, "Accommodation", 2), (TRUE, NOW(), null, "Transport", 2), (TRUE, NOW(), null, "Surface area", 3), (TRUE, NOW(), null, "Property age", 3), (TRUE, NOW(), null, "Estimated value of property", 3), (TRUE, NOW(), null, "Property risks", 3);

INSERT INTO `isuredatacenter`.`risk` (`active`, `created`, `updated`, `risk_name`, `risk_type_id`) VALUES (TRUE, NOW(), null, "Europe",  1), (TRUE, NOW(), null, "Asia",  1), (TRUE, NOW(), null, "America", 1), (TRUE, NOW(), null, "Japan",  1), (TRUE, NOW(), null, "Up to 18 yo",  2), (TRUE, NOW(), null, "18 - 60 yo",  2), (TRUE, NOW(), null, "Over 60 yo",  2), (TRUE, NOW(), null, "Football", 3), (TRUE, NOW(), null, "Tennis", 3), (TRUE, NOW(), null, "Basketball", 3), (TRUE, NOW(), null, "1000 $ ", 4), (TRUE, NOW(), null, "5000 $", 4), (TRUE, NOW(), null, "10000", 4), (TRUE, NOW(), null, "25000", 4), (TRUE, NOW(), null, "50000",4), (TRUE, NOW(), null, "100000",4), (TRUE, NOW(), null, "50 m2", 5), (TRUE, NOW(), null, "100 m2",5), (TRUE, NOW(), null, "200 m2",  5), (TRUE, NOW(), null, "500 m2", 5), (TRUE, NOW(), null, "10000 m2",5), (TRUE, NOW(), null, "1 year", 6), (TRUE, NOW(), null, "5 years", 6), (TRUE, NOW(), null, "10 years", 6), (TRUE, NOW(), null, "25 years", 6), (TRUE, NOW(), null, "50 years", 6), (TRUE, NOW(), null, "100 years",6), (TRUE, NOW(), null, "150 years", 6), (TRUE, NOW(), null, "5000 $", 7), (TRUE, NOW(), null, "10000 $", 7), (TRUE, NOW(), null, "20000 $", 7), (TRUE, NOW(), null, "50000 $", 7), (TRUE, NOW(), null, "10000 $", 7), (TRUE, NOW(), null, "200000 $", 7), (TRUE, NOW(), null, "Flood", 8 ), (TRUE, NOW(), null, "Fire", 8), (TRUE, NOW(), null, "Theft", 8 ), (TRUE, NOW(), null, "100 $", 9),  (TRUE, NOW(), null, "200 $",  9), (TRUE, NOW(), null, "500 $", 9), (TRUE, NOW(), null, "1000 $",9), (TRUE, NOW(), null, "100 $", 10),  (TRUE, NOW(), null, "200 $",  10), (TRUE, NOW(), null, "500 $", 10), (TRUE, NOW(), null, "1000 $", 10), (TRUE, NOW(), null, "1 day", 11),  (TRUE, NOW(), null, "3 days", 11), (TRUE, NOW(), null, "5 days", 11), (TRUE, NOW(), null, "7 days", 11), (TRUE, NOW(), null, "Train", 12 ), (TRUE, NOW(), null, "Bus", 12), (TRUE, NOW(), null, "Taxi", 12);

INSERT INTO `isuredatacenter`.`pricelist` (`active`, `created`, `updated`, `date_from`, `date_to`) VALUES (TRUE, NOW(), null, '2017-11-1', '2018-1-1');
INSERT INTO `isuredatacenter`.`pricelist` (`active`, `created`, `updated`, `date_from`, `date_to`) VALUES (TRUE, NOW(), null, '2017-10-1', '2017-10-15');

INSERT INTO `isuredatacenter`.`permission` (`active`, `created`, `updated`, `name`) VALUES (TRUE, NOW(), null, 'P1');

INSERT INTO `isuredatacenter`.`payment_type` (`active`, `created`, `updated`, `label`) VALUES (TRUE, NOW(), null, 'acquirer');
INSERT INTO `isuredatacenter`.`payment_type` (`active`, `created`, `updated`, `label`) VALUES (TRUE, NOW(), null, 'paypal');
<<<<<<< HEAD
=======

INSERT INTO `isuredatacenter`.`pricelist_item` (`active`, `created`, `updated`, `coefficient`, `price`, `risk_id`, `pricelist_id`) VALUES (TRUE, NOW(), null, '1', '12', '1', '1');

INSERT INTO `isuredatacenter`.`pricelist_item` (`active`, `created`, `updated`, `coefficient`, `price`, `risk_id`, `pricelist_id`) VALUES (TRUE, NOW(), null, '0.8', '13', '2', '1');
>>>>>>> development
