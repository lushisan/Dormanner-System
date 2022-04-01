/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : luss

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2022-03-31 22:09:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', '000', 'lushisan', '男', '123');

-- ----------------------------
-- Table structure for t_dorm
-- ----------------------------
DROP TABLE IF EXISTS `t_dorm`;
CREATE TABLE `t_dorm` (
  `dormId` int(11) NOT NULL AUTO_INCREMENT,
  `dormBuildId` int(11) DEFAULT NULL,
  `dormName` varchar(20) DEFAULT NULL,
  `dormType` varchar(20) DEFAULT NULL,
  `dormNumber` int(11) DEFAULT NULL,
  `dormTel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`dormId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dorm
-- ----------------------------
INSERT INTO `t_dorm` VALUES ('1', '1', '220', '男', '6', '110');

-- ----------------------------
-- Table structure for t_dormbuild
-- ----------------------------
DROP TABLE IF EXISTS `t_dormbuild`;
CREATE TABLE `t_dormbuild` (
  `dormBuildId` int(11) NOT NULL AUTO_INCREMENT,
  `dormBuildName` varchar(20) DEFAULT NULL,
  `dormBuildDetail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dormBuildId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dormbuild
-- ----------------------------
INSERT INTO `t_dormbuild` VALUES ('1', '1号楼', '男生公寓1#');
INSERT INTO `t_dormbuild` VALUES ('4', '2号楼', '男生公寓2#');
INSERT INTO `t_dormbuild` VALUES ('5', '3号楼', '男生公寓3#');
INSERT INTO `t_dormbuild` VALUES ('6', '4号楼', '女生公寓4#');
INSERT INTO `t_dormbuild` VALUES ('7', '5号楼', '女生公寓5#');
INSERT INTO `t_dormbuild` VALUES ('8', '6号楼', '无');

-- ----------------------------
-- Table structure for t_dormmanager
-- ----------------------------
DROP TABLE IF EXISTS `t_dormmanager`;
CREATE TABLE `t_dormmanager` (
  `dormManId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `dormBuildId` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`dormManId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dormmanager
-- ----------------------------
INSERT INTO `t_dormmanager` VALUES ('2', 'manager4', '123', '6', '小卢', '男', '123');
INSERT INTO `t_dormmanager` VALUES ('3', 'manager3', '123', '5', '小李', '女', '123');
INSERT INTO `t_dormmanager` VALUES ('4', 'manager2', '123', '4', '小陈', '男', '123');
INSERT INTO `t_dormmanager` VALUES ('8', 'manager1', '123', '1', '小白', '男', '123');
INSERT INTO `t_dormmanager` VALUES ('10', 'manager5', '123', '7', '老王', '男', '123');
INSERT INTO `t_dormmanager` VALUES ('11', 'manager6', '123', null, '老张', '男', '123');
INSERT INTO `t_dormmanager` VALUES ('12', 'manager666', '123', null, '卢仕散', '男', '10086');
INSERT INTO `t_dormmanager` VALUES ('13', 'manager7', '123', null, 'tt', '男', '100');

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record` (
  `recordId` int(11) NOT NULL AUTO_INCREMENT,
  `studentNumber` varchar(20) DEFAULT NULL,
  `studentName` varchar(30) DEFAULT NULL,
  `dormBuildId` int(11) DEFAULT NULL,
  `dormName` varchar(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `detail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES ('8', '002', '李四', '4', '120', '2021-06-12', '晚归');
INSERT INTO `t_record` VALUES ('9', '010', '小强', '4', '222', '2021-06-12', '夜不归宿');
INSERT INTO `t_record` VALUES ('10', '002', '小明', '1', '201', '2021-06-15', '缺勤');
INSERT INTO `t_record` VALUES ('13', '006', '关羽', '4', '201', '2021-06-15', '缺勤');
INSERT INTO `t_record` VALUES ('14', '020', '彭德华', '5', '232', '2021-06-16', '夜不归宿');
INSERT INTO `t_record` VALUES ('16', '021', '张孟晨', '5', '228', '2021-06-16', '缺勤');
INSERT INTO `t_record` VALUES ('17', '003', '小绿', '1', '301', '2021-06-16', '缺勤');
INSERT INTO `t_record` VALUES ('18', '003', '小绿', '1', '301', '2021-06-17', '晚归');
INSERT INTO `t_record` VALUES ('19', '022', '猪八戒', '5', '250', '2021-06-18', '缺勤');
INSERT INTO `t_record` VALUES ('20', '021', '张孟晨', '5', '228', '2021-06-18', '夜不归宿');
INSERT INTO `t_record` VALUES ('21', '020', '彭德华', '5', '232', '2021-06-19', '缺勤');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `studentId` int(11) NOT NULL AUTO_INCREMENT,
  `stuNum` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `dormBuildId` int(11) DEFAULT NULL,
  `dormName` varchar(11) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('2', '001', '123', '李四', '1', '101', '男', '10086');
INSERT INTO `t_student` VALUES ('31', '010', '123', '小强', '4', '222', '男', '11111');
INSERT INTO `t_student` VALUES ('32', '002', '123', '小明', '1', '201', '男', '10086');
INSERT INTO `t_student` VALUES ('33', '003', '123', '小绿', '1', '301', '男', '10086');
INSERT INTO `t_student` VALUES ('34', '005', '123', '刘备', '4', '101', '男', '00000');
INSERT INTO `t_student` VALUES ('35', '006', '123', '关羽', '4', '201', '男', '11111');
INSERT INTO `t_student` VALUES ('36', '007', '123', '张飞', '4', '301', '男', '33333');
INSERT INTO `t_student` VALUES ('37', '020', '123', '彭德华', '5', '232', '男', '10086');
INSERT INTO `t_student` VALUES ('38', '021', '123', '张孟晨', '5', '228', '男', '10010');
INSERT INTO `t_student` VALUES ('39', '022', '123', '猪八戒', '5', '250', '男', '88888');
INSERT INTO `t_student` VALUES ('42', '023', '000', '小卢', '5', '101', '男', '10086');
