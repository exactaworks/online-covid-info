CREATE TABLE `continent` (
  `id` varchar(45) NOT NULL,
  `date` timestamp NULL DEFAULT NULL,
  `continent` varchar(255) DEFAULT NULL,
  `cases` int DEFAULT 0,
  `death` int DEFAULT 0,
  `recovered` int DEFAULT 0,
  `population` bigint DEFAULT 0,
  PRIMARY KEY (`id`)
);


