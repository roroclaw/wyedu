INSERT INTO `sys_menus`(`ID`, `NAME`, `URL`, `SORT`, `TYPE`, `STATUS`, `PARENT_CODE`, `IMG_URL`, `SYS_TAG`) VALUES ('432', '成绩规则配置', 'sysScoreRule/tchScoreRuleConfig.do', 3, '2', '1', '4', NULL, NULL);

CREATE TABLE `sys_tch_scores_rule_conf`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TEACHER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SUBJECT_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `GRADE_SCOPE_ID` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MIDDEL_RATIO` double(5, 2) NULL DEFAULT NULL,
  `END_RATIO` double(5, 2) NULL DEFAULT NULL,
  `USUAL_RATIO` double(5, 2) NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `CREATOR` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  `UPDATER` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师成绩规则配置表' ROW_FORMAT = Dynamic;

/*alter table sco_subject_scores add TEACHER_ID varchar(64);*/

CREATE TABLE `sys_scores_rule_rel`  (
  `RULE_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `COURSE_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`RULE_ID`,`COURSE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '成绩规则开课配置表' ROW_FORMAT = Dynamic;

CREATE TABLE `sco_graduation_scores`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `EXAM_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXAM_NUM` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `STU_EXAM_INFO_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `COURSE_OPEN_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `STU_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '冗余字段',
  `CLASS_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCORE` decimal(10, 1) NULL DEFAULT NULL,
  `SCHOOL_YEAR` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TERM` varchar(3) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典表',
  `TEACHER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `CREATOR` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  `UPDATER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TYPE` varchar(3) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `RECORDE_STATUS` varchar(3) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `STATUS` varchar(3) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '正常,缺考,缓考',
  `REMARK` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CLASS_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `GRADE_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
