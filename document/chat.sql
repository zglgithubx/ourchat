/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : chat

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 07/11/2021 15:37:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `friend_circle_id` bigint(0) NOT NULL COMMENT '朋友圈说说id',
  `replier_id` bigint(0) NULL DEFAULT NULL COMMENT '回复评论者id',
  `comment_id` bigint(0) NULL DEFAULT NULL COMMENT '回复评论的评论id',
  `reviewer_id` bigint(0) NOT NULL COMMENT '评论者id',
  `content` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `create_time` datetime(0) NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `gender` bit(1) NULL DEFAULT NULL COMMENT '性别，0是女，null为保密',
  `age` tinyint(0) NULL DEFAULT NULL COMMENT '年龄',
  `address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '本人交际花一枚，快来找我聊天吧' COMMENT '个性签名',
  `icon_path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像路径',
  `background_image_path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '朋友圈背景图路径',
  `is_roaming` bit(1) NULL DEFAULT b'0' COMMENT '是否消息漫游，0否',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `user_id` bigint(0) NOT NULL COMMENT '用户id',
  `friend_id` bigint(0) NOT NULL COMMENT '好友id',
  `friend_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '好友备注',
  `is_look` bit(1) NULL DEFAULT b'0' COMMENT '是否不看他/她，0是否',
  `is_look_me` bit(1) NULL DEFAULT b'0' COMMENT '是否不让她/他看我，0是否',
  `blacklist` bit(1) NULL DEFAULT b'0' COMMENT '是否进入黑名单，0是否',
  `create_time` datetime(0) NOT NULL COMMENT '添加好友时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for roaming_message
-- ----------------------------
DROP TABLE IF EXISTS `roaming_message`;
CREATE TABLE `roaming_message`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sender` bigint(0) NULL DEFAULT NULL COMMENT '发送者',
  `receiver` bigint(0) NULL DEFAULT NULL COMMENT '接收者',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `zone_id` bigint(0) NOT NULL COMMENT '说说id',
  `user_id` bigint(0) NOT NULL COMMENT '点赞者id',
  `create_time` datetime(0) NOT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for zone
-- ----------------------------
DROP TABLE IF EXISTS `zone`;
CREATE TABLE `zone`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `user_id` bigint(0) NOT NULL COMMENT '用户id',
  `content` bigint(0) NULL DEFAULT NULL COMMENT '文字内容',
  `image_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配图路径',
  `like_count` tinyint(0) NULL DEFAULT NULL COMMENT '点赞数',
  `comment_count` tinyint(0) NULL DEFAULT NULL COMMENT '评论数',
  `create_time` datetime(0) NOT NULL COMMENT '发表时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
