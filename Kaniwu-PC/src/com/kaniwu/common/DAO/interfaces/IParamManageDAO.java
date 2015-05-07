/*
 * 文 件 名:  IParamManageDAO.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-18
 */
package com.kaniwu.common.DAO.interfaces;

import java.util.List;
import java.util.Map;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IParamManageDAO 
	{
		/**
		 * 获取BeansDAO层
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public  List<Map<String, Object>> queryParamBeans() throws Exception;
	}
