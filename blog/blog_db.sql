/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50709
 Source Host           : localhost:3306
 Source Schema         : blog_db

 Target Server Type    : MySQL
 Target Server Version : 50709
 File Encoding         : 65001

 Date: 12/12/2018 20:30:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `user_id` bigint(16) DEFAULT NULL COMMENT '用户ID',
  `category_id` bigint(16) DEFAULT NULL COMMENT '分类ID',
  `summary` varchar(255) DEFAULT NULL COMMENT '摘要',
  `view_count` int(8) DEFAULT '0' COMMENT '阅读量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of blog
-- ----------------------------
BEGIN;
INSERT INTO `blog` VALUES (1, '2018-08-27 23:09:43', '2018-08-27 23:09:48', '测试标题', '测试内容测试内容', 1, 1, '测试摘要', 0);
INSERT INTO `blog` VALUES (2, '2018-08-27 23:17:06', '2018-08-27 23:17:06', '测试标题1', '测试内容1测试内容1', 3, 2, '测试摘要1', 0);
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `name` varchar(32) DEFAULT NULL COMMENT '分类名',
  `parent_id` bigint(16) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (1, '2018-08-27 23:08:18', '2018-08-27 23:08:18', '架构', 0);
INSERT INTO `category` VALUES (2, '2018-08-27 23:08:37', '2018-08-27 23:08:37', '编程语言', 0);
INSERT INTO `category` VALUES (3, '2018-08-27 23:08:41', '2018-08-27 23:08:41', '运维', 0);
INSERT INTO `category` VALUES (4, '2018-08-27 23:08:45', '2018-08-27 23:08:45', 'IT管理', 0);
COMMIT;

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `blog_id` bigint(16) DEFAULT NULL COMMENT '博客ID',
  `user_id` bigint(16) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of collect
-- ----------------------------
BEGIN;
INSERT INTO `collect` VALUES (1, '2018-08-27 23:17:23', '2018-08-27 23:17:23', 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `user_id` bigint(16) DEFAULT NULL COMMENT '用户ID',
  `content` text COMMENT '内容',
  `parent_id` bigint(16) DEFAULT NULL COMMENT '上级评论ID',
  `blog_id` bigint(16) DEFAULT NULL COMMENT '博客ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES (1, '2018-09-17 14:24:53', '2018-09-17 14:24:53', 1, '不错！', 0, 1);
INSERT INTO `comment` VALUES (2, '2018-09-19 21:10:48', '2018-09-19 21:11:05', 3, '可以的！', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for like
-- ----------------------------
DROP TABLE IF EXISTS `like`;
CREATE TABLE `like` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `comment_id` bigint(16) DEFAULT NULL COMMENT '评论ID',
  `user_id` bigint(16) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '2018-08-15 23:12:40', '2018-09-19 21:14:50', 'min', '8d2bd3dfb28c5bd08975670a131431a18528f853');
INSERT INTO `user` VALUES (3, '2018-08-27 23:03:09', '2018-08-27 23:03:09', 'lynn', '8d2bd3dfb28c5bd08975670a131431a18528f853');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
