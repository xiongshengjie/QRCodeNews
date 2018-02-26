/*
Navicat MySQL Data Transfer

Source Server         : MyDataBase
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : qrcode_news

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2018-02-26 21:55:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `news_id` varchar(36) NOT NULL COMMENT '主键id',
  `news_title` varchar(64) NOT NULL COMMENT '新闻标题',
  `news_url` varchar(128) NOT NULL COMMENT '新闻内容地址',
  `news_author` varchar(36) NOT NULL COMMENT '新闻作者',
  `news_img` varchar(1024) DEFAULT NULL,
  `news_category` int(11) NOT NULL COMMENT '新闻类别',
  `create_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1c2a89a5-5cc0-4166-b6d7-5d5283eadcfb', '', 'html/2018/2/26/c0df07fc-5832-40af-a008-c1b3a98f0fae.html', 'admin', '', '1', '2018-02-26 11:49:08');
INSERT INTO `news` VALUES ('8caffde3-ba6a-4601-a185-57295785866c', '终身姐', 'html/2018/2/22/597344d9-8f10-4039-ab89-df7fd4809256.html', 'admin', 'img/2018/2/22/948be1a4-90ba-415b-b7c1-7b4c12d31ea7.jpg|img/2018/2/22/181e2124-c557-4c8c-ad43-975b82f68262.jpg|', '1', '2018-02-26 11:49:09');
INSERT INTO `news` VALUES ('8f42e078-d834-4b72-a326-351e1b2fb56c', '', 'html/2018/2/26/1d957a68-5268-4a7e-93e9-1f65effb8eb1.html', 'admin', 'img/2018/2/26/372bdee3-7086-44fd-bb0e-ae52ad73c5af.jpg|', '1', '2018-02-26 11:49:10');
INSERT INTO `news` VALUES ('c55a19b9-c848-43b2-97e3-0bc31d6d55ef', '终身姐', 'html/2018/2/22/83db3a7a-f8de-4db7-9e0e-4c8a4e189766.html', 'admin', 'img/2018/2/22/6b58fffa-0e74-49a9-867b-ed201f79f0b1.jpg|img/2018/2/22/3c8c90d2-9ae4-410b-aeaf-ed867afaa1c1.jpg|', '1', '2018-02-26 11:49:10');
INSERT INTO `news` VALUES ('cbe2837f-cb7e-40be-a764-d3bd3aeaaf21', '', 'html/2018/2/26/e5cfc4b1-5d46-4d37-86f0-85bcbe81f6c8.html', 'admin', 'img/2018/2/26/3c32a24c-9cae-4456-a719-3e955601c7c2.jpg|', '1', '2018-02-26 11:49:11');
INSERT INTO `news` VALUES ('d350a996-8af0-4723-9f71-3050a64ca43a', '', 'html/2018/2/26/1cbc8f8b-14d9-4041-aa70-ea606264b8cc.html', 'admin', '', '1', '2018-02-26 11:49:11');
INSERT INTO `news` VALUES ('d5cec05e-d6d1-45b2-a536-8893ff3ab311', '震惊，扫码看新闻应用就要发布了', 'html/2018/2/22/87708191-c874-4351-abe6-4a305b1a3b74.html', 'admin', 'img/2018/2/22/6ec4ac89-3863-4604-ac47-0f00b10cc1d6.jpg|', '1', '2018-02-26 11:49:14');

-- ----------------------------
-- Table structure for `news_category`
-- ----------------------------
DROP TABLE IF EXISTS `news_category`;
CREATE TABLE `news_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别主键',
  `category_name` varchar(16) NOT NULL COMMENT '类别名称',
  `create_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_category
-- ----------------------------
INSERT INTO `news_category` VALUES ('1', '头条', '2018-02-22 18:03:52');
INSERT INTO `news_category` VALUES ('2', '社会', '2018-02-26 20:19:11');
INSERT INTO `news_category` VALUES ('3', '国内', '2018-02-26 20:19:17');
INSERT INTO `news_category` VALUES ('4', '国际', '2018-02-26 20:19:20');
INSERT INTO `news_category` VALUES ('5', '娱乐', '2018-02-26 20:19:24');
INSERT INTO `news_category` VALUES ('6', '体育', '2018-02-26 20:19:28');
INSERT INTO `news_category` VALUES ('7', '军事', '2018-02-26 20:19:32');
INSERT INTO `news_category` VALUES ('8', '科技', '2018-02-26 20:19:37');
INSERT INTO `news_category` VALUES ('9', '财经', '2018-02-26 20:19:41');
INSERT INTO `news_category` VALUES ('10', '时尚', '2018-02-26 20:19:50');

-- ----------------------------
-- Table structure for `news_comment`
-- ----------------------------
DROP TABLE IF EXISTS `news_comment`;
CREATE TABLE `news_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `new_id` varchar(32) NOT NULL COMMENT '新闻id',
  `comment` varchar(256) NOT NULL COMMENT '评论详情',
  `user_id_from` varchar(32) NOT NULL COMMENT '评论发出用户id',
  `user_id_to` varchar(32) DEFAULT NULL COMMENT '被评论用户id',
  `create_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `news_user`
-- ----------------------------
DROP TABLE IF EXISTS `news_user`;
CREATE TABLE `news_user` (
  `user_id` varchar(36) NOT NULL COMMENT '用户id',
  `user_name` varchar(32) NOT NULL COMMENT '登录名',
  `user_password` varchar(32) NOT NULL COMMENT '用户密码',
  `user_nickname` varchar(32) NOT NULL COMMENT '昵称',
  `user_mobile` varchar(32) DEFAULT NULL COMMENT '用户手机号',
  `user_sex` int(1) NOT NULL DEFAULT '0' COMMENT '用户性别 0.不告诉你 1.男 2.女',
  `user_description` varchar(64) DEFAULT NULL COMMENT '用户自我介绍',
  `user_head` varchar(32) NOT NULL COMMENT '头像地址',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_user
-- ----------------------------
INSERT INTO `news_user` VALUES ('admin', 'admin', '123456', 'admin', null, '0', '管理员', '/head/default.png');
