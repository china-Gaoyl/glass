/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 127.0.0.1:3306
 Source Schema         : glass

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 10/06/2021 20:21:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for RESOURCES
-- ----------------------------
DROP TABLE IF EXISTS `RESOURCES`;
CREATE TABLE `RESOURCES` (
  `ID` varchar(64) NOT NULL COMMENT '主键id',
  `PARENT_ID` varchar(64) NOT NULL COMMENT '父级id',
  `RESOURCE_NAME` varchar(255) NOT NULL COMMENT '资源名',
  `TYPE` varchar(64) NOT NULL COMMENT '父级id',
  `AUTHORITY` varchar(64) NOT NULL COMMENT '权限',
  `DESCRIPTION` varchar(255) NOT NULL COMMENT '资源说明',
  `URL` varchar(255) NOT NULL COMMENT '资源路径',
  `CREATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `CREATED_BY` varchar(64) NOT NULL COMMENT '创建人',
  `UPDATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '修改时间',
  `UPDATED_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `DELETE_FLAG` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标识（0:有效 1:删除）',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='资源表';

-- ----------------------------
-- Records of RESOURCES
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ROLE
-- ----------------------------
DROP TABLE IF EXISTS `ROLE`;
CREATE TABLE `ROLE` (
  `ID` varchar(64) NOT NULL COMMENT '主键id',
  `ROLE_NAME` varchar(255) NOT NULL COMMENT '角色名',
  `DESCRIPTION` varchar(255) NOT NULL COMMENT '角色说明',
  `CREATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `CREATED_BY` varchar(64) NOT NULL COMMENT '创建人',
  `UPDATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '修改时间',
  `UPDATED_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `DELETE_FLAG` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标识（0:有效 1:删除）',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色表';

-- ----------------------------
-- Records of ROLE
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ROLE_RESOURCES
-- ----------------------------
DROP TABLE IF EXISTS `ROLE_RESOURCES`;
CREATE TABLE `ROLE_RESOURCES` (
  `ID` varchar(64) NOT NULL COMMENT '主键id',
  `ROLE_ID` varchar(64) NOT NULL COMMENT '角色id',
  `RESOURCE_ID` varchar(255) NOT NULL COMMENT '资源id',
  `CREATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `CREATED_BY` varchar(64) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色资源关联表';

-- ----------------------------
-- Records of ROLE_RESOURCES
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for USER
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `ID` varchar(64) NOT NULL COMMENT '主键id',
  `USERNAME` varchar(255) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(255) NOT NULL COMMENT '密码',
  `MOBILE` varchar(11) DEFAULT NULL COMMENT '手机号',
  `EMAIL` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `GENDER` varchar(1) DEFAULT NULL COMMENT '性别',
  `IS_ACCOUNT_NON_EXPIRED` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账号过期',
  `IS_ACCOUNT_NON_LOCKED` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账号锁定',
  `IS_CREDENTIALS_NON_EXPIRED` tinyint(1) NOT NULL DEFAULT '1' COMMENT '凭证过期',
  `IS_ENABLED` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账号禁用',
  `CREATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `CREATED_BY` varchar(64) NOT NULL COMMENT '创建人',
  `UPDATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '修改时间',
  `UPDATED_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `DELETE_FLAG` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标识（0:有效 1:删除）',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of USER
-- ----------------------------
BEGIN;
INSERT INTO `USER` VALUES ('1', 'admin', '123456', '17712345678', 'admin@163.com', '1', 1, 1, 1, 1, '0000-00-00 00:00:00.000000', '', '2021-06-10 04:36:38.301990', NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for USER_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `USER_ROLE`;
CREATE TABLE `USER_ROLE` (
  `ID` varchar(64) NOT NULL COMMENT '主键id',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(255) NOT NULL COMMENT '角色ID',
  `CREATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `CREATED_BY` varchar(64) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of USER_ROLE
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
