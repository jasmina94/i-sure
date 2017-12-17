INSERT INTO `isurebank1`.`bank`
(`swiftcode`, `name`, `bank_account`, `bank_code`)
VALUES
  ("VBUBRS22", "Vojvodjanska Banka Novi Sad", "265-1090310000009-72", 265),
  ("CONARS22", "Continental Banka Novi Sad", "205-2200148913177-71", 205);

INSERT INTO `isurebank1`.`account` (`account_number`, `balance`, `reserved`, `bank_id`)
VALUES
  ("265-2234522100014-72", 25000.00, 0.0, 1),
  ("265-2234522100015-72", 200000.00, 11000.0, 1),
  ("265-2234522100016-72", 0.0, 20000.00, 1),
  ("205-2200148916622-71", 100, 0.0, 2),
  ("205-2200148916623-71", 10000.0, 5000.00, 2),
  ("205-2200148916624-71", 50000.00, 0.0, 2);


INSERT INTO `isurebank1`.`company`
(`account_number`, `name`, `url`) VALUES
  ("205-2200148916622-71", "Pionir d.o.o.", "http://localhost:8090/ws"),
  ("265-2234522100015-72", "Imlek", "http://localhost:8091/ws");