/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50702
 Source Host           : localhost:3306
 Source Schema         : demo_druid

 Target Server Type    : MySQL
 Target Server Version : 50702
 File Encoding         : 65001

 Date: 28/08/2020 16:49:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mp_user
-- ----------------------------
DROP TABLE IF EXISTS `mp_user`;
CREATE TABLE `mp_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `openid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `deleted` tinyint(3) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mp_user
-- ----------------------------
INSERT INTO `mp_user` VALUES (1, 'yuan', '广东深圳', 'openid', '2019-05-11 22:20:08', '2020-06-03 21:20:41', 1);
INSERT INTO `mp_user` VALUES (2, 'yuan', '广东深圳', 'openid', '2020-06-03 21:18:45', '2020-06-03 21:18:45', 0);
INSERT INTO `mp_user` VALUES (3, 'yuan', '广东深圳', 'openid', '2020-06-03 21:35:20', '2020-06-03 21:35:20', 0);
INSERT INTO `mp_user` VALUES (4, 'yuan', '广东深圳', 'openid', '2020-06-03 21:35:30', '2020-06-03 21:35:30', 0);
INSERT INTO `mp_user` VALUES (5, 'yuan', '广东深圳', 'openid', '2020-06-03 21:35:30', '2020-06-03 21:35:30', 0);
INSERT INTO `mp_user` VALUES (6, 'yuan', '广东深圳', 'openid', '2020-06-03 21:35:30', '2020-06-03 21:35:30', 0);
INSERT INTO `mp_user` VALUES (7, 'yuan', '广东深圳', 'openid', '2020-06-03 21:35:30', '2020-06-03 21:35:30', 0);
INSERT INTO `mp_user` VALUES (8, 'yuan', '广东深圳', 'openid', '2020-06-03 21:35:30', '2020-06-03 21:35:30', 0);
INSERT INTO `mp_user` VALUES (9, 'yuan', '广东深圳', 'openid', '2020-06-03 21:35:31', '2020-06-03 21:35:31', 0);
INSERT INTO `mp_user` VALUES (10, 'yuan', '上海', 'openid', '2020-06-03 21:35:31', '2020-06-04 16:17:44', 0);
INSERT INTO `mp_user` VALUES (11, 'yuan', '广东', 'openid', '2020-06-03 21:35:31', '2020-06-04 16:17:47', 0);
INSERT INTO `mp_user` VALUES (12, 'yuan', '广东深圳', 'openid', '2020-06-03 21:35:31', '2020-06-03 21:35:31', 0);
INSERT INTO `mp_user` VALUES (13, 'yuan', '福州', 'openid', '2020-06-03 21:35:31', '2020-06-04 16:17:56', 0);
INSERT INTO `mp_user` VALUES (14, 'yuan', '泉州', 'openid', '2020-06-03 21:35:31', '2020-06-04 16:18:07', 0);
INSERT INTO `mp_user` VALUES (15, 'yuan', '广东深圳', 'openid', '2020-06-03 21:35:32', '2020-06-03 21:35:32', 0);
INSERT INTO `mp_user` VALUES (16, 'yuan', '广东深圳', 'openid', '2020-06-03 21:35:32', '2020-06-03 21:35:32', 0);
INSERT INTO `mp_user` VALUES (17, 'update', '福建', 'openid', '2020-06-03 21:35:32', '2020-06-04 21:02:01', 0);
INSERT INTO `mp_user` VALUES (18, 'update', '福建', 'openid', '2020-06-03 16:51:20', '2020-06-04 21:00:43', 0);
INSERT INTO `mp_user` VALUES (19, 'yuan', '广东深圳', 'openid', '2020-06-04 21:48:33', '2020-06-04 21:48:33', 0);
INSERT INTO `mp_user` VALUES (23, '小李111', '宁德', 'aaa', '2020-06-08 09:35:37', '2020-06-08 14:47:41', 0);
INSERT INTO `mp_user` VALUES (24, 'string', 'string', 'string', '2020-06-08 09:44:35', '2020-06-08 14:48:34', 1);
INSERT INTO `mp_user` VALUES (25, 'string', 'string', 'string', '2020-06-08 09:56:01', '2020-06-08 14:50:07', 1);
INSERT INTO `mp_user` VALUES (26, '小陈', 'xiamen', 'test', '2020-06-08 14:51:24', '2020-06-08 14:51:24', 0);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `tel` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(6) NULL DEFAULT NULL COMMENT '性别',
  `appellation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '称谓',
  `reference` int(11) NULL DEFAULT NULL COMMENT '推荐人ID',
  `state` tinyint(4) NULL DEFAULT 1 COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `channel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道',
  `channel_uid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道用户ID',
  `app_platform` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用平台',
  `phone_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `app_version` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'APP版本号',
  `grade` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '会员等级',
  `invite_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请码',
  `newer_time` datetime(0) NULL DEFAULT NULL COMMENT '新人活动开始时间',
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `index_inviteCode`(`invite_code`) USING BTREE,
  INDEX `index_tel`(`tel`) USING BTREE,
  INDEX `index_reference`(`reference`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 99371 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (99363, '15396211658', 'http://120.77.67.179:8200/blbl/uploads/icon/20170618095416397.png', 'bbbbb', 0, 'test', NULL, 1, '2017-03-16 16:46:06', 'TEL', NULL, 'APP STORE', 'IOS|iPhone7,1|11.4.1', '3.1.4', '0', 'DC3670', NULL);
INSERT INTO `t_user` VALUES (99365, '15396211611', 'http://120.77.67.179:8200/blbl/uploads/icon/20170618095416397.png', 'bbbbb', 0, 'test', NULL, 1, '2017-03-16 16:46:06', 'TEL', NULL, 'APP STORE', 'IOS|iPhone7,1|11.4.1', '3.1.4', '0', 'DC3670', NULL);
INSERT INTO `t_user` VALUES (99367, '15396211611', 'http://120.77.67.179:8200/blbl/uploads/icon/20170618095416397.png', 'bbbbb', 0, 'test', NULL, 1, '2017-03-16 16:46:06', 'TEL', NULL, 'APP STORE', 'IOS|iPhone7,1|11.4.1', '3.1.4', '0', 'DC3670', NULL);
INSERT INTO `t_user` VALUES (99368, '15396211611', 'http://120.77.67.179:8200/blbl/uploads/icon/20170618095416397.png', 'bbbbb', 0, 'test', NULL, 1, '2017-03-16 16:46:06', 'TEL', NULL, 'APP STORE', 'IOS|iPhone7,1|11.4.1', '3.1.4', '0', 'DC3670', NULL);
INSERT INTO `t_user` VALUES (99369, '15396211611', 'http://120.77.67.179:8200/blbl/uploads/icon/20170618095416397.png', 'bbbbb', 0, 'test', NULL, 1, '2017-03-16 16:46:06', 'TEL', NULL, 'APP STORE', 'IOS|iPhone7,1|11.4.1', '3.1.4', '0', 'DC3670', NULL);
INSERT INTO `t_user` VALUES (99370, '15396211611', 'http://120.77.67.179:8200/blbl/uploads/icon/20170618095416397.png', 'bbbbb', 0, 'test', NULL, 1, '2017-03-16 16:46:06', 'TEL', NULL, 'APP STORE', 'IOS|iPhone7,1|11.4.1', '3.1.4', '0', 'DC3670', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(5) NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户维护' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', 23);
INSERT INTO `user` VALUES (2, '小李', 28);
INSERT INTO `user` VALUES (4, '小张', 26);
INSERT INTO `user` VALUES (5, '小张', 26);

SET FOREIGN_KEY_CHECKS = 1;
