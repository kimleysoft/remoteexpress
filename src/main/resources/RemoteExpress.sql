SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `del` bit(1) NOT NULL DEFAULT b'0' COMMENT '已删除',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录账号',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录密码',
  `nick` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `roles` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色，支持多个(admin,user)',
  `gmt_modified` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `gmt_create` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for login_history
-- ----------------------------
DROP TABLE IF EXISTS `login_history`;
CREATE TABLE `login_history`  (
  `id` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '登陸歷史記錄ID',
  `del` bit(1) NOT NULL DEFAULT b'0' COMMENT '已删除',
  `userid` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用戶ID',
  `nick` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `loginip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `gmt_modified` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `gmt_create` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '添加时间',
  `operating_system` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用戶的操作系統',
  `ref_type` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0普通日志，1登入，2登出',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for remote
-- ----------------------------
DROP TABLE IF EXISTS `remote`;
CREATE TABLE `remote`  (
  `id` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '桌面ID',
  `del` bit(1) NOT NULL DEFAULT b'0' COMMENT '已删除',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `port` int(5) NOT NULL DEFAULT 3389,
  `client_type` int(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '连接类型(1.RDP  2.SSH  3.VNC)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '備註',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `groupid` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '組ID',
  `localip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `localport` int(5) NOT NULL DEFAULT 3389,
  `gmt_modified` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `gmt_create` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '添加时间',
  `conne_break_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '斷開鏈接狀態標記',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for remote_history
-- ----------------------------
DROP TABLE IF EXISTS `remote_history`;
CREATE TABLE `remote_history`  (
  `id` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '遠程歷史ID',
  `del` bit(1) NOT NULL DEFAULT b'0' COMMENT '已删除',
  `userid` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `remoteid` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '遠程ID',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `userip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '远程ip',
  `gmt_modified` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `gmt_create` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '添加时间',
  `connect_break_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '斷開或連接標記',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group`  (
  `id` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组ID',
  `del` bit(1) NOT NULL DEFAULT b'0' COMMENT '已删除',
  `alias` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `typeflag` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '組類型，1用戶類，2電腦類',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `gmt_modified` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `gmt_create` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用戶ID',
  `del` bit(1) NOT NULL DEFAULT b'0' COMMENT '已删除',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录账号',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录密码',
  `nick` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `gmt_modified` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `gmt_create` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '添加时间',
  `group_id` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组ID',
  `last_login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '最後一次登陸的ip',
  `last_login_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '最後一次登陸時間',
  `login_out_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '登入登出標記',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_remote
-- ----------------------------
DROP TABLE IF EXISTS `user_remote`;
CREATE TABLE `user_remote`  (
  `id` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '桌面ID',
  `del` bit(1) NOT NULL DEFAULT b'0' COMMENT '已删除',
  `userid` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用戶ID',
  `remoteid` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '遠程ID',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '电脑账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '电脑密码',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '电脑名称',
  `userip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '远程ip',
  `gmt_modified` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `gmt_create` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
