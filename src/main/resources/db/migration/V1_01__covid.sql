CREATE TABLE `covid` (
  `id` varchar(45) NOT NULL,
  `date` timestamp NULL DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `cases` int DEFAULT 0,
  `death` int DEFAULT 0,
  `recovered` int DEFAULT 0,
  `population` int DEFAULT 0,
  PRIMARY KEY (`id`)
);


