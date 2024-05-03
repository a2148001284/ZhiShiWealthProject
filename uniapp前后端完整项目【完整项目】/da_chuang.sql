/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : da_chuang

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 08/01/2023 16:21:38
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '表自增id',
  `item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收藏的股票id',
  `closing_price` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '最新收盘价',
  `pe` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'PE',
  `pb` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'PB',
  `item_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '股票名称',
  `nearly_a_month` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '近一个月',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收藏对应的用户id,外键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorite
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账户',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'https://pica.zhimg.com/v2-048952d70f37dc3d14502a1e91b189f3_r.jpg?source=1940ef5c' COMMENT '用户头像',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `gmt_created` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '202083290136', 'a236d6521b122bbd5df2a6dda4554c2f', 'https://pica.zhimg.com/v2-048952d70f37dc3d14502a1e91b189f3_r.jpg?source=1940ef5c', NULL, NULL, '2022-10-18 18:34:05', '2022-10-18 18:34:05');
INSERT INTO `user` VALUES (9, 'aliqua', 'a3186974780b5e2c27eff825e049222d', 'https://pica.zhimg.com/v2-048952d70f37dc3d14502a1e91b189f3_r.jpg?source=1940ef5c', NULL, NULL, '2023-01-08 12:28:29', '2023-01-08 12:28:29');
INSERT INTO `user` VALUES (10, 'lwj', 'a3186974780b5e2c27eff825e049222d', 'https://pica.zhimg.com/v2-048952d70f37dc3d14502a1e91b189f3_r.jpg?source=1940ef5c', NULL, NULL, '2023-01-08 12:44:32', '2023-01-08 12:44:32');
INSERT INTO `user` VALUES (11, 'wj', 'a3186974780b5e2c27eff825e049222d', 'https://pica.zhimg.com/v2-048952d70f37dc3d14502a1e91b189f3_r.jpg?source=1940ef5c', NULL, NULL, '2023-01-08 12:47:50', '2023-01-08 12:47:50');
INSERT INTO `user` VALUES (12, '123', '202cb962ac59075b964b07152d234b70', 'https://pica.zhimg.com/v2-048952d70f37dc3d14502a1e91b189f3_r.jpg?source=1940ef5c', NULL, NULL, '2023-01-08 13:34:50', '2023-01-08 13:34:50');
INSERT INTO `user` VALUES (13, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'https://pica.zhimg.com/v2-048952d70f37dc3d14502a1e91b189f3_r.jpg?source=1940ef5c', NULL, NULL, '2023-01-08 15:58:15', '2023-01-08 15:58:15');
INSERT INTO `user` VALUES (14, 'test', '098f6bcd4621d373cade4e832627b4f6', 'https://pica.zhimg.com/v2-048952d70f37dc3d14502a1e91b189f3_r.jpg?source=1940ef5c', NULL, NULL, '2023-01-08 15:59:04', '2023-01-08 15:59:04');

SET FOREIGN_KEY_CHECKS = 1;
