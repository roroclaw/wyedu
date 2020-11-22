/*学生填报志愿菜单*/
INSERT INTO `wyedu`.`sys_menus`(`ID`, `NAME`, `URL`, `SORT`, `TYPE`, `STATUS`, `PARENT_CODE`, `IMG_URL`, `SYS_TAG`) VALUES ('26', '学籍信息填报', 'student/initStuEdit.do', 10, '2', '1', '2', NULL, NULL);
/*期末总评*/
INSERT INTO `wyedu`.`sys_menus`(`ID`, `NAME`, `URL`, `SORT`, `TYPE`, `STATUS`, `PARENT_CODE`, `IMG_URL`, `SYS_TAG`) VALUES ('401', '期末总评', 'sco/score/scoComment.html', 10, '2', '1', '4', NULL, NULL);

INSERT INTO `wyedu`.`sys_dict_item`(`CODE`, `DICT_TYPE`, `TEXT`, `SEQ`) VALUES ('1', 'SCO_COMMENT_STATUS', '已提交', '1');
INSERT INTO `wyedu`.`sys_dict_item`(`CODE`, `DICT_TYPE`, `TEXT`, `SEQ`) VALUES ('2', 'SCO_COMMENT_STATUS', '已发布', '2');

DROP TABLE IF EXISTS `sco_comment`;
CREATE TABLE `sco_comment`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STU_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TERM` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典表',
  `SCHOOL_YEAR` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计算出来,例如2017-2018',
  `COMMENT` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `CREATOR` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  `UPDATER` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STATUS` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0.异常(详细信息记录备注中) 1.正常',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
