/*
 * 文 件 名:  ICommonDAO.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-6
 */
package com.kaniwu.common.DAO.interfaces;

import java.util.List;

import com.kaniwu.common.bean.LoadInfoBean;

/**
 * 公用的一些DAO层操作
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ICommonDAO
	{
		/**
		 * 查询缓存加载情况
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public List<LoadInfoBean> queryLoadInfo() throws Exception;
		
		
		/**
		 * 更新重载状态
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public void updateReloadState(int cacheId,int state) throws Exception;
	}
