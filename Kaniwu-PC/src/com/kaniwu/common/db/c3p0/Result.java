/*$$Id*/
/*
 *@(#)Result.java    2010-4-13
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

import java.util.SortedMap;

/**
 * @title: 数据库返回结果集接口
 * @description: 
 * 
 * @author: yef
 */
public interface Result {

	public SortedMap[] getRows();

	public Object[][] getRowsByIndex();

	public String[] getColumnNames();

	public int getRowCount();

	public boolean isLimitedByMaxRows();

	public String getString(String columnName);

	public String getString(String columnName, int rowid);

	public Integer getInteger(String columnName);

	public Integer getInteger(String columnName, int rowid);
	
	public Long getLong(String columnName);

	public Long getLong(String columnName, int rowid);

	public Object getColumn(String columnName);

	public Object getColumnByRow(String columnName, int rowid);

	public boolean isEmpty();

}
