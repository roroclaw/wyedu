package com.cloud9.biz.util;

import com.cloud9.biz.models.SysToken;
import com.roroclaw.base.bean.MemoryCache;
import com.roroclaw.base.handler.BizException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import java.util.*;

public class BizConstants {

	public static Map<String,String> MENU_MAP = new HashMap<String,String>();

	public static String DEFAULT_PASSWORD = "4f4c17acaf24381e586a6bd41c6a43ef39c5dae63cb6fa473a1a6876ef1017f32801d2a7fced3e6f";

	public static Map<String,Map<String,String>> DICT_MAP;

	public static final String SESSION_CUR_SCHOOLYEAR = "curSchoolYear";

	public static final String GRADE_NAME[] = {"","一年级","二年级","三年级","四年级","五年级","六年级","初一年级","初二年级","初三年级","高一年级","高二年级","高三年级"};
	/**
	 * html 常量信息
	 *
	 *
	 */
	public interface HTML_VAL {
		String ERROR_MES = "err_mes";
		String ERROR_MES_NORESULT = "无匹配数据。";
		String ERROR_MES_REFUSE = "拒绝请求！";
		String ERROR_MES_DATA_PROCESSING = "数据处理出错！";
		String ERROR_MES_PARAM_ERROR = "参数错误！";
		String ERROR_MES_DATA_REL_EXIST = "存在数据依赖，无法完成操作！";
	}


	/**
	 * session 常量信息
	 *
	 *
	 */
	public interface SESSION_VAL {
		String CUR_MENU = "cur_menu";
	}


	/**
	 * html 错误信息CODE
	 *
	 *
	 */
	public interface HTML_ERROR_CODE {
		String ERROR_CODE = "01"; //login
	}

	/**
	 * 字典表类型
	 *
	 * @author Administrator
	 *
	 */
	public interface DICT_TYPE {
		String COM_STATUS = "COMMON_STATUS";
		String ROLE_TYPE = "ROLE_TYPE";
		String MENU_TYPE = "MENU_TYPE";
		String COMMON_STATUS = "COMMON_STATUS";
		String COMMON_BOOLEAN = "COMMON_BOOLEAN";
		String CLASS_STATUS = "CLASS_STATUS";
		String USER_TYPE = "USER_TYPE";
		String INFO_STATUS = "INFO_STATUS";
		String ARTI_STATUS = "ARTI_STATUS";
		String USER_SEX = "USER_SEX";
		String MINZU = "MINZU";
		String EDU_TYPE = "EDU_TYPE";
		String STU_SORT = "STU_SORT";
		String STU_TYPE = "STU_TYPE";
		String LEARN_TYPE = "LEARN_TYPE";
		String RECRUIT_TYPE = "RECRUIT_TYPE";
		String TEACH_PLACE = "TEACH_PLACE";
		String STUDENT_FROM = "STUDENT_FROM";
		String POLITICAL_STATUS = "POLITICAL_STATUS";
		String REGISTRY_TYPE = "REGISTRY_TYPE";
		String IMP_TYPE = "IMP_TYPE";
		String TEACHER_TYPE = "TEACHER_TYPE";
		String TEACH_TYPE = "TEACH_TYPE";
		String CLASSROOM_TYPE = "CLASSROOM_TYPE";
		String TEACHER_TITLE = "TEACHER_TITLE";
		String TEACHER_POST = "TEACHER_POST";
		String TEACHER_DEGREE = "DEGREE";
		String TEACHER_EDUCATION = "EDUCATION";
		String PERIOD = "PERIOD";
		String SUBJECT_TYPE = "SUBJECT_TYPE";
		String TERM = "TERM";
		String BUILDING_NO = "BUILDING_NO";
		String EXAM_TYPE = "EXAM_TYPE";
		String EXAM_STATUS = "EXAM_STATUS";
		String EXAM_STU_STATUS = "EXAM_STU_STATUS";//考试成绩状态
		String SCORES_TYPE = "SCORES_TYPE";//考试成绩状态
		String MAJOR_TYPE = "MAJOR_TYPE";//专业类型
		String RECORD_CONFIG_STATUS = "RECORD_CONFIG_STATUS";//专业类型
		String ATTENDANCE_CHECK_ITEMS = "ATTENDANCE_CHECK_ITEMS";
		String SCORES_SUBJECT_STATUS = "SCORES_SUBJECT_STATUS";
		String COMMENT_ITEMS_NAME="COMMENT_ITEMS_NAME";
		String COMMENT_ITEMS_MARK="COMMENT_ITEMS_MARK";
	}

	/**
	 * 专业类型
	 * @author dxz
	 *
	 */
	public interface MAJOR_TYPE {
		String MAJOR  = "1"; //专业
		String SUB_MAJOR = "2"; //方向
	}

	/**
	 * 区域表类型
	 *
	 * @author Administrator
	 *
	 */
	public interface AREA_TYPE {
		String PROVINCE  = "1";
		String CITY = "2";
		String DISTRICT = "3";
	}

	/**
	 * 导入类型
	 *
	 * @author Administrator
	 *
	 */
	public interface IMP_TYPE {
		String STUDENT_INFO  = "1";
		String SUBJECT_SCORES_INFO  = "2";
	}

	/**
	 * 成绩类型
	 *
	 * @author Administrator
	 *
	 */
	public interface SCORES_TYPE {
		String  MID = "1"; //期中
		String FINAL  = "2"; //期末
		String USUAL  = "3"; //平时
		String ENTRANCE  = "4"; //入学
	}

	/**
	 * 科目成绩状态
	 *
	 * @author Administrator
	 *
	 */
	public interface SCORES_SUBJECT_STATUS {
		String  EXCAPTION = "0"; //异常
		String NORMAL = "1"; //正常
		String UNPUBLISH = "2"; //未发布
		String TEMP_STUDY = "11"; //请长假
		String MID_DELAY = "12"; //其中缓考
	}

	/**
	 * 通用状态
	 *
	 * @author dxz
	 *
	 */
	public interface COMMON_STATUS {
		int ACTIVE = 1;// 启用
		int STOP = 0;// 停用
		String ACTIVE_STR = "1";// 启用
		String STOP_STR = "0";// 停用
	}

	/**
	 * 考试状态
	 *
	 * @author dxz
	 *
	 */
	public interface EXAM_STATUS {
		String NEW = "0";// 新建
		String EXAMING = "1";// 考试中
		String CHECKING = "2";// 登分中
		String FINISHED = "3";// 完毕
	}

	/**
	 * 登分状态
	 *
	 * @author dxz
	 *
	 */
	public interface RECORD_CONFIG_STATUS {
		String UNSET = "0";// 未设置
		String SET_TEACHER = "1";//已设置教师
		String SET_STATUS = "2";//缺缓考设置
		String RECORDING = "3";//教师登分
		String END = "4";//結束
		String RECORDING_SUB = "5";//登分已提交
	}
	public interface RECORD_STATUS {
		String UNSET = "0";// 未提交
		String SETTED = "1";//已提交
		String END = "2";//結束
	}
	/**
	 * 考生状态
	 *
	 * @author dxz
	 *
	 */
	public interface EXAM_STU_STATUS {

		String NORMALE = "1";// 正常
		String DISMISSED = "3";// 缺考
		String DELAY = "2";// 缓考
		String CHEAT = "4";// 作弊
		String TEMP_STUDY = "5";// 借读
	}

	/**
	 * 信息状态
	 *
	 * @author zl
	 *
	 */
	public interface INFO_STATUS {
		String INVALID="0";// 无效
		String UNCHECKED ="2";// 未审核
		String CHECKED  ="3";// 已审核
		String ACTIVE  ="1";// 有效
		String SUSPERNSION  ="11";// 休学
		String DROP  ="12";// 退学
		String GOBACK  ="13";// 复学
	}

	/**
	 * 班级状态
	 *
	 * @author zl
	 *
	 */
	public interface CLASS_STATUS {
		String ACTIVE = "1";// 有效
		String ENDED ="2";// 毕业结束
		String STOP  ="-1";// 停用
	}

	/**
	 * 成绩登记状态
	 *
	 * @author zl
	 *
	 */
	public interface RECORDE_STATUS {
		String UNSUB = "0";//未提交
		String SUBED ="1";//已提交
		String PUBLISH ="2";//发布
	}

	/**
	 * 期末总评状态
	 *
	 * @author zl
	 *
	 */
	public interface SCO_COMMENT_STATUS {
		String SUBED ="1";//已提交
		String PUBLISH ="2";//发布
	}


	/**
	 * 文章状态
	 *
	 * @author zl
	 *
	 */
	public interface ARTI_STATUS {
		String NEW = "0";// 新建
		String ACTIVE = "1";// 发布
		String  STOP="2";// 禁用

	}
//	/**
//	 * 科目成绩表FLAG
//	 *
//	 * @author roroclaw
//	 *
//	 */
//	public interface SUBJECT_SCORE_FLAG {
//		int IMP = 1;// 导入
//		int COLLECT = 0;// 汇总合成
//	}

	/**
	 * ID前缀
	 *
	 * @author zl
	 *
	 */
	public interface ID_PRECODE {
		String ROLEREL_ID_PRECODE = "101";//
		String USER_ID_PRECODE = "201";//
		String STUDENT_ID_PRECODE = "202";//
		String COURSE_ID_PRECODE = "202";//
		String TEACHER_ID_PRECODE = "203";//
		String SYS_BASEINFO_ID_PRECODE = "204";//
		String COURSEOPEN_ID_PRECODE = "205";//
		String SCHEDULE_ID_PRECODE = "206";//
	}


	/**
	 * 通用布尔
	 *
	 * @author dxz
	 *
	 */
	public interface COMMON_BOOLEAN {
		int TRUE = 1;// true
		int FALSE = 0;// false
	}

	/**
	 * 角色类型
	 *
	 * @author dxz
	 *
	 */
	public interface ROLE_TYPE {
		String SYS = "-1"; // 系统定义
		String CUSTOME = "2"; // 自定义
	}

	/**
	 * 用户类型
	 *
	 * @author dxz
	 *
	 */
	public interface USER_TYPE {
		int SUPER = -1;// 超级管理员
		int SYSTEM_STUDENT = 1;// 系统学员类型
		int SYSTEM_TEACHER = 2;// 系统教师类型
		int SYSTEM_ADMIN = 0;// 系统管理员
	}

	/**
	 * 学段
	 *
	 * @author dxz
	 *
	 */
	public interface PERIOD {
		String PRIMARY_SCHOOL = "1";//小学
		String MIDDLE_SCHOOL = "2";//中学
		String HIGH_SCHOOL = "3";//高中

	}

	/**
	 * 菜单类型
	 */
	public interface MENU_TYPE {
		String FIRST = "1";  //一级菜单
		String SECOND = "2"; //二级菜单
	}

	/**
	 * 角色ID
	 */
	public interface SYS_ROLE_ID {
		String ADMIN  = "1"; //管理员
		String TEACHER = "2"; //教师
		String STUDENT = "3";//学生
		String CLASSTEACHER = "4"; //班主任
		String INFO_ADMIN = "5"; //信息管理员
		String XIAOWUHUI = "a2e9294110d74a3fa8ead41d2f3b913b"; //校务会成员
		String XUEJIGUANLIHUI = "fb68dcb17f6e4f1db8d555218a49cc0b"; //学籍管理员
		String JIAOYANSHIZHUREN = "8069ca4b99c44a3a9df06d276390e47b"; //教研室主任
	}


	public static String SUPER_ADMIN_ID = "00";///超级管理员ID
	/**
	 * 性别
	 *
	 * @author Administrator
	 *
	 */
	public interface USER_SEX {
		int MAN = 1;// 男
		int WOMEN = 2;// 女
	}

	/**
	 * 主键成器
	 *
	 * @return
	 */
	public static String generatorPid() {
		String pid = UUID.randomUUID().toString().replace("-", "");
		return pid;
	}

	/**
	 * 主键成器
	 *
	 * @return
	 */
	public static String generatorPid32() {
			String s = UUID.randomUUID().toString();
			Random rand = new Random();
			String s1 = s.substring(0, 8) + s.substring(9, 13)
					+ s.substring(14, 18) + s.substring(19, 23) + s.substring(24)+System.currentTimeMillis()+rand.nextInt(9999);
		return s1;
	}

	/**
	 * 主键成器
	 *
	 * @return
	 */
	public static String generatorPid(String tableCode) {
		String pid = tableCode + UUID.randomUUID().toString().replace("-", "");
		return pid;
	}

	/**
	 * Token成器
	 *
	 * @return
	 */
	public static String generatorAccToken() {
		String pid = "";
		String uuid = UUID.randomUUID().toString();
		pid = "T" + uuid.replace("-", "");
		return pid;
	}

	/**
	 * 获取文件后缀名
	 */
	public static String getFileSuffix(String filePath) throws BizException {
		int lastDot = filePath.lastIndexOf(".");
		if (lastDot == -1)
			throw new BizException("文件路径错误");
		return filePath.substring(lastDot + 1);
	}

	/**
	 * 生成访问token
	 * @param sysToken
	 * @return
	 */
	public static String generatorAccToken(SysToken sysToken) {
		String accToken = null;
		PasswordEncoder encoder = new StandardPasswordEncoder();
		String plaintext = sysToken.getTokenTime()+"_"+sysToken.getAddress();
		accToken = encoder.encode(plaintext);
		return accToken;
	}

	/**
	 * 获取token失效节点
	 *
	 * @return
	 */
	public static Date getTokenTime() throws BizException {
		int loseTime = Integer.valueOf(MemoryCache
				.getSysConfigKey("ACCTOKEN_LOSE_TIME"));
		Date d = new Date();
		return new Date(d.getTime() + loseTime);
	}


	public static String getItemCodeByText(String typeCode,String text){
		String code = "";
		Map itemMap = BizConstants.DICT_MAP.get(typeCode);
		if(itemMap != null){
			Set<String> kset = itemMap.keySet();
			for(String ks:kset){
				if(text.equals(itemMap.get(ks))){
					code = ks;
					break;
				}
			}
		}else{
			throw new BizException("typeCode非法!");
		}
		return code;
	}

	/**
	 * AB座转数值
	 *
	 *
	 */
	public static String ABSeatVal (String typeWord){
		Map map=new HashMap();
		map.put("A","1");
		map.put("B","2");
		map.put("C","3");
		map.put("D","4");
		return (String)map.get(typeWord);
	}

	/**
	 * 学段年级数目
	 *
	 *
	 */
	public static String periodGradeNum (String period){
		Map map=new HashMap();
		map.put("1","6");
		map.put("2","12");
		return (String)map.get(period);
	}

	public interface ARTICLE_NOTICE_FOLDER {
		String teacherNotice ="2131451137876977830";// 教师公告
		String stuNotice ="2131455968989343029";// 学生公告
		String allNotice ="2131449406230748070";// 全校公告
	}

	public interface SUBJECT_IDS{
		String yuwen = "204c78425d8fe5b49718a89823d5ac59490";//语文
		String shuxue = "204b500d0a336fb44d692f6b347ea20e70c";//数学
		String yingyu = "2049b7b9200a0164001bfaa3cc19bc38677";//英语

		String zhengzhi = "2043ddf54d5b45f46aca0a50b0a42c433db";//政治
		String lishi = "204fdb6d9fdfe35456e80083e907c440046";//历史
		String dili = "204561c06f1821e47d492162edc297665ae";//地理
	}
}
