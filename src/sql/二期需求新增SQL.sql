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

alter table sco_subject_scores add TEACHER_ID varchar(64);