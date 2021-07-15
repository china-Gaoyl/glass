/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : glass

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 15/07/2021 17:14:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `PARENT_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级id',
  `RESOURCE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名',
  `TYPE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源类型（PACKAGE：包，ITEM：单项，SUBMENU：按钮）',
  `AUTHORITY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
  `DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源说明',
  `URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源路径',
  `SORT` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '排序',
  `CREATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `CREATED_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '修改时间',
  `UPDATED_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `DELETE_FLAG` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标识（0:有效 1:删除）',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `UNI_RESOURCE_NAME`(`RESOURCE_NAME`) USING BTREE,
  UNIQUE INDEX `UNI_AUTHORITY`(`AUTHORITY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('88fa50d5305641b0a6f809b4b7cd5162', 'a039d9d6496b42b9922094c2082093b5', '用户删除', 'SUBMENU', 'USER_DELETE', '用户管理-删除', '/user/delete', '11', '2021-07-14 09:15:28.480275', 'admin', '2021-07-14 09:16:53.787171', NULL, '0');
INSERT INTO `resources` VALUES ('a039d9d6496b42b9922094c2082093b5', 'f55e8c46382e40708498f0071a047d37', '用户管理', 'PACKAGE', 'USER_MANAGE', '系统-用户管理', '', '1', '2021-07-13 17:35:43.772309', 'admin', '2021-07-13 17:35:43.772309', NULL, '0');
INSERT INTO `resources` VALUES ('d257a599318a434bb3076c3863da35b3', 'a039d9d6496b42b9922094c2082093b5', '查询用户集合', 'SUBMENU', 'USER_SELECT_LIST', '用户管理-查询用户集合', '/user/selectList', '13', '2021-07-14 09:25:40.075952', 'admin', '2021-07-14 09:25:40.075952', NULL, '0');
INSERT INTO `resources` VALUES ('e220ee2d82cb436db97f9e6716853a28', 'a039d9d6496b42b9922094c2082093b5', '用户新增', 'SUBMENU', 'USER_ADD', '用户管理-新增', '/user/add', '10', '2021-07-14 09:13:52.819180', 'admin', '2021-07-14 09:15:10.190583', NULL, '0');
INSERT INTO `resources` VALUES ('e75393058d6e4abead1b3d8678338017', 'a039d9d6496b42b9922094c2082093b5', '查询用户信息', 'SUBMENU', 'USER_SELECT_OBJ', '用户管理-查询用户信息', '/user/selectObj', '12', '2021-07-14 09:25:04.696119', 'admin', '2021-07-14 09:25:04.696119', NULL, '0');
INSERT INTO `resources` VALUES ('f55e8c46382e40708498f0071a047d37', '', '系统', 'PACKAGE', 'SYSTEM', '系统', '', '0', '2021-07-13 17:34:15.247195', 'admin', '2021-07-13 17:34:15.247195', NULL, '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `ROLE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色说明',
  `CREATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `CREATED_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '修改时间',
  `UPDATED_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `DELETE_FLAG` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标识（0:有效 1:删除）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('9c4ed456f0c1476ab41f202328cc7a82', 'ROOT', '超级管理员', '2021-07-15 16:20:02.184963', 'admin', '2021-07-15 16:23:35.681969', NULL, '0');

-- ----------------------------
-- Table structure for role_resources
-- ----------------------------
DROP TABLE IF EXISTS `role_resources`;
CREATE TABLE `role_resources`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `ROLE_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `RESOURCE_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源id',
  `CREATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `CREATED_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_resources
-- ----------------------------
INSERT INTO `role_resources` VALUES ('20c030d2b1e54524987e69ca5f6a075c', '9c4ed456f0c1476ab41f202328cc7a82', 'd257a599318a434bb3076c3863da35b3', '2021-07-15 16:25:54.521004', 'admin');
INSERT INTO `role_resources` VALUES ('4e3c607260c8468b9e5d9055eb0ddc88', '9c4ed456f0c1476ab41f202328cc7a82', 'e220ee2d82cb436db97f9e6716853a28', '2021-07-15 16:25:54.521004', 'admin');
INSERT INTO `role_resources` VALUES ('70aef4c0ae0c4708bb1d0ae8e6bd78a4', '9c4ed456f0c1476ab41f202328cc7a82', 'e75393058d6e4abead1b3d8678338017', '2021-07-15 16:25:54.521004', 'admin');
INSERT INTO `role_resources` VALUES ('950eeac8e88947bd82eac44e64401228', '9c4ed456f0c1476ab41f202328cc7a82', 'f55e8c46382e40708498f0071a047d37', '2021-07-15 16:25:54.521004', 'admin');
INSERT INTO `role_resources` VALUES ('a5c84f42fe8d4871a304b2351338568e', '9c4ed456f0c1476ab41f202328cc7a82', 'a039d9d6496b42b9922094c2082093b5', '2021-07-15 16:25:54.521004', 'admin');
INSERT INTO `role_resources` VALUES ('f3e5af981c6740a08903457e45adc7c2', '9c4ed456f0c1476ab41f202328cc7a82', '88fa50d5305641b0a6f809b4b7cd5162', '2021-07-15 16:25:54.521004', 'admin');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `USERNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `MOBILE` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `EMAIL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `GENDER` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `IS_ACCOUNT_NON_EXPIRED` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账号过期',
  `IS_ACCOUNT_NON_LOCKED` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账号锁定',
  `IS_CREDENTIALS_NON_EXPIRED` tinyint(1) NOT NULL DEFAULT 1 COMMENT '凭证过期',
  `IS_ENABLED` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账号禁用',
  `CREATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `CREATED_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '修改时间',
  `UPDATED_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `DELETE_FLAG` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标识（0:有效 1:删除）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2fdaf967eb1d4d81a7d3f920e58d7ee2', 'admin', '$2a$10$eIK2H1kBOgE7c.IM7BEVVevce/FbmXoHKGuFN1IzNMxNYkMg6KBiq', '17712345678', 'admin@163.com', '1', 1, 1, 1, 1, '0000-00-00 00:00:00.000000', '', '2021-07-15 16:24:02.310160', NULL, '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `USER_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `CREATED_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `CREATED_BY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('32c376659b2c4c2691165e68f6ed4a4b', '2fdaf967eb1d4d81a7d3f920e58d7ee2', '9c4ed456f0c1476ab41f202328cc7a82', '2021-07-15 16:24:18.284133', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
