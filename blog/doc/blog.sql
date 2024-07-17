/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 17/07/2024 11:58:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `post_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `user_id` int NOT NULL COMMENT '作者id',
  `created` datetime NOT NULL COMMENT '创建时间',
  `last_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`post_id`) USING BTREE,
  INDEX `id`(`user_id`) USING BTREE,
  CONSTRAINT `id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '测试1', 'yyyyxxxxxyy', 3, '2024-07-17 08:50:52', '2024-07-17 09:50:52');
INSERT INTO `article` VALUES (2, '测试2', 'yyyyyy', 3, '2024-07-17 08:51:07', '2024-07-17 09:51:07');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `pass_word` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `created` datetime NOT NULL COMMENT '创建时间',
  `last_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE COMMENT '唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user1', '$2a$10$jEgA2dhHDoIN9iPNxBM/K.Zd4BYkNXYnlT.TLtL1WlPCJQcVV5XK.', '45678@qq.com', '2024-07-17 08:45:04', '2024-07-17 08:45:08');
INSERT INTO `user` VALUES (2, 'user2', '$2a$10$ZEAIhmvYadsanPGwqqFah.q1OIGUya3.c8ajgDYnOKAbyDe4wJ7DC', 'xxxx@qq.com', '2024-07-17 08:46:01', '2024-07-17 08:46:01');
INSERT INTO `user` VALUES (3, 'user', '$2a$10$IXbGezM5arkv1ZiH5hDuUOknrQp3UHtO4.ZVlF6SoLF5DCM42zegO', 'xxxx@qq.com', '2024-07-17 08:47:06', '2024-07-17 08:47:06');

SET FOREIGN_KEY_CHECKS = 1;
