/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50632
Source Host           : 127.0.0.1:3306
Source Database       : mydubbo

Target Server Type    : MYSQL
Target Server Version : 50632
File Encoding         : 65001

Date: 2017-06-28 10:20:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `age` int(5) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户维护';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', '23');
INSERT INTO `user` VALUES ('2', '小李', '28');
