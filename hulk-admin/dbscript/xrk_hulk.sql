/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : xrk_hulk

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2017-06-01 16:23:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hulk_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `hulk_admin_user`;
CREATE TABLE `hulk_admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '系统用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置中心系统用户表';

-- ----------------------------
-- Table structure for hulk_app
-- ----------------------------
DROP TABLE IF EXISTS `hulk_app`;
CREATE TABLE `hulk_app` (
  `app_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '应用ID(主键,自增长)',
  `app_name` varchar(100) NOT NULL DEFAULT '' COMMENT 'APP名称',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '介绍',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `emails` varchar(255) NOT NULL DEFAULT '' COMMENT '应用负责人邮箱列表逗号分隔',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='配置中心应用表';

-- ----------------------------
-- Table structure for hulk_config
-- ----------------------------
DROP TABLE IF EXISTS `hulk_config`;
CREATE TABLE `hulk_config` (
  `config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '配置ID(主键,自增长)',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '配置文件/配置项',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '配置文件名/配置项KeY名',
  `value` text NOT NULL COMMENT '0 配置文件：文件的内容，1 配置项：配置值',
  `app_id` bigint(20) NOT NULL COMMENT '应用ID',
  `version` varchar(255) NOT NULL DEFAULT 'DEFAULT_VERSION' COMMENT '版本',
  `env_id` bigint(20) NOT NULL COMMENT '环境ID',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`config_id`),
  KEY `FKnd94gk2wq9ibhqdg7gor9x65p` (`app_id`),
  KEY `FKf81va3q1h63o76lap95f7h4iq` (`env_id`),
  CONSTRAINT `FKf81va3q1h63o76lap95f7h4iq` FOREIGN KEY (`env_id`) REFERENCES `hulk_env` (`env_id`),
  CONSTRAINT `FKnd94gk2wq9ibhqdg7gor9x65p` FOREIGN KEY (`app_id`) REFERENCES `hulk_app` (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8 COMMENT='配置中心环境信息表';

-- ----------------------------
-- Table structure for hulk_env
-- ----------------------------
DROP TABLE IF EXISTS `hulk_env`;
CREATE TABLE `hulk_env` (
  `env_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '环境ID(主键,自增长)',
  `env_name` varchar(50) NOT NULL DEFAULT '' COMMENT '环境名称',
  `description` varchar(100) NOT NULL DEFAULT '' COMMENT '环境描述',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`env_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='配置中心环境信息表';
SET FOREIGN_KEY_CHECKS=1;
