CREATE TABLE `USER` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `role` int NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `USER_UN` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci