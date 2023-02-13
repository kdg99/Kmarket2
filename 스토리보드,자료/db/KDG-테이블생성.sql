-- -----------------------------------------------------
-- Table `Kmarket1`.`km_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket2`.`km_member` (
  `uid` VARCHAR(20) NOT NULL PRIMARY KEY,
  `pass` VARCHAR(255) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `gender` TINYINT NOT NULL,
  `hp` CHAR(13) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `type` TINYINT NOT NULL,
  `point` INT NULL DEFAULT 0,
  `level` TINYINT NULL DEFAULT 1,
  `zip` VARCHAR(10) NULL,
  `addr1` VARCHAR(255) NULL,
  `addr2` VARCHAR(255) NULL,
  `company` VARCHAR(20) NULL,
  `ceo` VARCHAR(20) NULL,
  `bizRegNum` CHAR(12) NULL,
  `comRegNum` VARCHAR(100) NULL,
  `tel` VARCHAR(20) NULL,
  `manager` VARCHAR(20) NULL,
  `managerHp` CHAR(13) NULL,
  `fax` VARCHAR(20) NULL,
  `regip` VARCHAR(100) NOT NULL,
  `wdate` DATETIME NULL,
  `rdate` DATETIME NOT NULL
)


-- -----------------------------------------------------
-- Table `Kmarket1`.`km_product_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket2`.`km_product_order` (
  `ordNo` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `uid` VARCHAR(20) NOT NULL,
  `ordCount` INT NULL DEFAULT 0,
  `ordPrice` INT NULL DEFAULT 0,
  `ordDiscount` INT NULL DEFAULT 0,
  `ordDelivery` INT NULL DEFAULT 0,
  `savePoint` INT NULL DEFAULT 0,
  `usedPoint` INT NULL DEFAULT 0,
  `ordTotPrice` INT NULL DEFAULT 0,
  `recipName` VARCHAR(20) NOT NULL,
  `recipHp` CHAR(13) NOT NULL,
  `recipZip` CHAR(5) NOT NULL,
  `recipAddr1` VARCHAR(255) NOT NULL,
  `recipAddr2` VARCHAR(255) NOT NULL,
  `ordPayment` TINYINT NOT NULL,
  `ordComplete` TINYINT NOT NULL,
  `ordDate` DATETIME NOT NULL
)

-- -----------------------------------------------------
-- Table `Kmarket1`.`km_member_point`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket2`.`km_member_point` (
  `pointNo` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `uid` VARCHAR(20) NOT NULL,
  `ordNo` INT NOT NULL,
  `point` INT NOT NULL,
  `pointDate` DATETIME NOT NULL
)


-- -----------------------------------------------------
-- Table `Kmarket1`.`km_member_terms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket2`.`km_member_terms` (
  `terms` TEXT NOT NULL,
  `privacy` TEXT NOT NULL,
  `location` TEXT NOT NULL,
  `finance` TEXT NOT NULL,
  `tax` TEXT NOT NULL
)


-- -----------------------------------------------------
-- Table `Kmarket1`.`km_product_cate1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket2`.`km_product_cate1` (
  `cate1` TINYINT NOT NULL PRIMARY KEY,
  `c1Name` VARCHAR(20) NOT NULL
)


-- -----------------------------------------------------
-- Table `Kmarket1`.`km_product_cate2`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket2`.`km_product_cate2` (
  `cate1` TINYINT NOT NULL,
  `cate2` TINYINT NOT NULL,
  `c2Name` VARCHAR(20) NOT NULL
)


-- -----------------------------------------------------
-- Table `Kmarket1`.`km_product`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `Kmarket2`.`km_product` (
  `prodNo` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `cate1` TINYINT NOT NULL,
  `cate2` TINYINT NOT NULL,
  `prodName` VARCHAR(100) NOT NULL,
  `descript` VARCHAR(100) NOT NULL,
  `company` VARCHAR(100) NOT NULL,
  `seller` VARCHAR(20) NOT NULL,
  `price` INT NOT NULL,
  `discount` TINYINT NULL DEFAULT 0,
  `point` INT NULL DEFAULT 0,
  `stock` INT NULL DEFAULT 0,
  `sold` INT NULL DEFAULT 0,
  `delivery` INT NULL DEFAULT 0,
  `hit` INT NULL DEFAULT 0,
  `score` TINYINT NULL DEFAULT 0,
  `review` INT NULL DEFAULT 0,
  `thumb1` VARCHAR(255) NOT NULL,
  `thumb2` VARCHAR(255) NOT NULL,
  `thumb3` VARCHAR(255) NOT NULL,
  `detail` VARCHAR(255) NOT NULL,
  `status` VARCHAR(20) NULL DEFAULT '새상품',
  `duty` VARCHAR(20) NULL DEFAULT '과세상품',
  `receipt` VARCHAR(20) NULL DEFAULT '발행가능',
  `bizType` VARCHAR(20) NULL DEFAULT '사업자 판매자',
  `origin` VARCHAR(20) NULL DEFAULT '상세설명참고',
  `ip` VARCHAR(20) NOT NULL,
  `rdate` DATETIME NOT NULL
)

-- -----------------------------------------------------
-- Table `Kmarket1`.`km_product_cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket2`.`km_product_cart` (
  `cartNo` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `uid` VARCHAR(20) NOT NULL,
  `prodNo` INT NOT NULL,
  `count` INT NOT NULL,
  `price` INT NOT NULL,
  `discount` INT NOT NULL,
  `point` INT NOT NULL,
  `delivery` INT NOT NULL,
  `total` INT NOT NULL,
  `rdate` DATETIME NOT NULL
)


-- -----------------------------------------------------
-- Table `Kmarket1`.`km_product_order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket2`.`km_product_order_item` (
  `ordNo` INT NOT NULL,
  `prodNo` INT NOT NULL,
  `count` INT NOT NULL,
  `price` INT NOT NULL,
  `discount` TINYINT NOT NULL,
  `point` INT NOT NULL,
  `delivery` INT NOT NULL,
  `total` INT NOT NULL
)


-- -----------------------------------------------------
-- Table `Kmarket1`.`km_product_review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket2`.`km_product_review` (
  `revNo` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `prodNo` INT NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `uid` VARCHAR(20) NOT NULL,
  `rating` TINYINT NOT NULL,
  `regip` VARCHAR(100) NOT NULL,
  `rdate` DATETIME NOT NULL
)


Kmarket1