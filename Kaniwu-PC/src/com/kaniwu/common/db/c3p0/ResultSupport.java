/*$$Id*/
/*
 *@(#)ResultSupport.java    2010-4-13
 *
 *Copyright (c) 2010-2012 Linkage.
 *All Rights Reserved.
 *
 *This software is the confidential and proprietary information of Linkage. 
 *("Confidential Information").  You shall not disclose such Confidential 
 *Information and shall use it only in accordance with the terms of the 
 *license agreement you entered into with Linkage. 
 */

package com.kaniwu.common.db.c3p0;



import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @title: 数据库返回结果集辅助类
 * @description:
 * 
 * @author: yef
 */
public final class ResultSupport {

	public final static Result toResult(ResultSet rs) {
		try {
			return new ResultImpl(rs, -1, -1);
		} catch (SQLException ex) {
			
			return null;
		}
	}

	public final static Result toResult(ResultSet rs, int maxRows) {
		try {
			return new ResultImpl(rs, -1, maxRows);
		} catch (SQLException ex) {
			
			return null;
		}
	}
}
