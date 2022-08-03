--
-- Dumping data for table `customers`
--

LOCK
TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` (id, created_at, created_by, deleted, updated_at, updated_by, full_name, balance, email,
                         address, phone)
VALUES (1, '2021-09-02 20:09:25', NULL, 0, '2021-09-02 20:09:25', NULL, 'Doloribus laborum il', 0,
        'duzyqagahi@mailinator.com', 'Rosalyn Howe', '+1 (483) 202-2127'),
       (2, '2021-09-02 20:09:38', NULL, 0, '2021-09-02 20:09:38', NULL, 'Ex fugit exercitati', 0,
        'wijacab@mailinator.com', 'Moses Meadows', '+1 (596) 452-9922'),
       (3, '2021-09-02 20:09:58', NULL, 0, '2021-09-02 20:09:58', NULL, 'Dolore officia eaque', 0,
        'tusiger@mailinator.com', 'Imogene Dixon', '+1 (581) 924-7822'),
       (4, '2021-09-02 20:10:00', NULL, 0, '2021-09-02 20:10:00', NULL, 'Blanditiis occaecat ', 0,
        'putam@mailinator.com', 'Maxwell Pennington', '+1 (746) 439-7553'),
       (5, '2021-09-02 20:10:02', NULL, 0, '2021-09-02 20:10:02', NULL, 'Dignissimos amet ne', 0,
        'sirekel@mailinator.com', 'Garrison George', '+1 (881) 121-2407'),
       (6, '2021-09-02 20:10:04', NULL, 0, '2021-09-02 20:10:04', NULL, 'Magni aut sint eos ', 0,
        'bagukyrezy@mailinator.com', 'Irene Nolan', '+1 (989) 281-9342'),
       (7, '2021-09-03 01:06:15', NULL, 0, '2021-09-03 01:06:15', NULL, 'Quam eum reiciendis ', 0,
        'lemiliwub@mailinator.com', 'Joseph Davidson', '+1 (125) 607-7363'),
       (8, '2021-09-03 01:07:12', NULL, 0, '2021-09-03 01:07:12', NULL, 'Nihil debitis exerci', 0,
        'zywax@mailinator.com', 'Noelle Clark', '+1 (752) 233-4897'),
       (9, '2021-09-03 11:40:17', NULL, 0, '2021-09-03 11:40:17', NULL, 'Repellendus At volu', 0,
        'seximoliwi@mailinator.com', 'Hall Guy', '+1 (121) 958-8876'),
       (10, '2021-12-19 14:56:04', NULL, 0, '2021-12-19 14:56:04', NULL, 'Incididunt voluptas ', 0,
        'pabevigu@mailinator.com', 'Garrett Alvarez', '+1 (892) 613-2085'),
       (11, '2021-12-19 14:56:17', NULL, 0, '2021-12-19 14:56:17', NULL, 'Dicta neque eius dic', 0,
        'dahabemo@mailinator.com', 'Rogan Williamson', '+1 (921) 747-7892');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK
TABLES;



