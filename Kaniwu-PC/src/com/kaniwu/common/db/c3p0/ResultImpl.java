/*$$Id*/
/*
 *@(#)ResultImpl.java    2010-4-13
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

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @title: 数据库返回结果集类
 * @description:
 * 
 * @author: yef
 */
final class ResultImpl implements Result, Serializable {

	private List rowMap;

	private List rowByIndex;

	private String[] columnNames;

	private boolean isLimited;

	public ResultImpl(ResultSet rs, int startRow, int maxRows)
			throws SQLException {
		rowMap = new ArrayList();
		rowByIndex = new ArrayList();
		ResultSetMetaData rsmd = rs.getMetaData();
		int noOfColumns = rsmd.getColumnCount();
		// Create the column name array
		columnNames = new String[noOfColumns];
		for (int i = 1; i <= noOfColumns; i++) {
			columnNames[i - 1] = rsmd.getColumnName(i);
		}
		// Throw away all rows upto startRow
		for (int i = 0; i < startRow; i++) {
			rs.next();
		}
		// Process the remaining rows upto maxRows
		int processedRows = 0;
		Object value = null;
		SortedMap columnMap = null;
		Object[] columns = null;
		while (rs.next()) {
			if ((maxRows != -1) && (processedRows == maxRows)) {
				isLimited = true;
				break;
			}
			columns = new Object[noOfColumns];
			columnMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
			// JDBC uses 1 as the lowest index!
			for (int i = 1; i <= noOfColumns; i++) {
				value = rs.getObject(i);
				if (rs.wasNull()) {
					value = null;
				}
				columns[i - 1] = value;
				columnMap.put(columnNames[i - 1], value);
			}
			rowMap.add(columnMap);
			rowByIndex.add(columns);
			processedRows++;
		}
	}

	public SortedMap[] getRows() {
		if (rowMap == null) {
			return null;
		}
		// should just be able to return SortedMap[] object
		return (SortedMap[]) rowMap.toArray(new SortedMap[0]);
	}

	public Object[][] getRowsByIndex() {
		if (rowByIndex == null) {
			return null;
		}
		// should just be able to return Object[][] object
		return (Object[][]) rowByIndex.toArray(new Object[0][0]);
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public int getRowCount() {
		if (rowMap == null) {
			return -1;
		}
		return rowMap.size();
	}

	public boolean isLimitedByMaxRows() {
		return isLimited;
	}

	public String getString(String columnName) {
		if (rowMap.isEmpty() || rowMap == null) {
			return null;
		}
		SortedMap columnMap = (SortedMap) rowMap.get(0);
		if (columnMap.get(columnName.toUpperCase()) == null) {
			return null;
		} else {
			return columnMap.get(columnName.toUpperCase()).toString();
		}
	}

	public String getString(String columnName, int rowid) {
		if (rowMap.isEmpty() || rowMap == null) {
			return null;
		}
		SortedMap columnMap = (SortedMap) rowMap.get(rowid);
		if (columnMap.get(columnName.toUpperCase()) == null) {
			return null;
		} else {
			return columnMap.get(columnName.toUpperCase()).toString();
		}
	}

	public Integer getInteger(String columnName) {
		if (rowMap.isEmpty() || rowMap == null) {
			return null;
		}
		SortedMap columnMap = (SortedMap) rowMap.get(0);
		if (columnMap.get(columnName.toUpperCase()) == null) {
			return null;
		} else {
			return Integer.parseInt(columnMap.get(columnName.toUpperCase())
					.toString().trim());
		}
	}

	public Integer getInteger(String columnName, int rowid) {
		if (rowMap.isEmpty() || rowMap == null) {
			return null;
		}
		SortedMap columnMap = (SortedMap) rowMap.get(rowid);
		if (columnMap.get(columnName.toUpperCase()) == null) {
			return null;
		} else {
			return Integer.parseInt(columnMap.get(columnName.toUpperCase())
					.toString().trim());
		}
	}

	public Object getColumn(String columnName) {
		if (rowMap.isEmpty() || rowMap == null) {
			return null;
		}
		SortedMap columnMap = (SortedMap) rowMap.get(0);
		return columnMap.get(columnName.toUpperCase());
	}

	public Object getColumnByRow(String columnName, int rowid) {
		if (rowMap.isEmpty() || rowMap == null) {
			return null;
		}
		SortedMap columnMap = (SortedMap) rowMap.get(rowid);
		return columnMap.get(columnName.toUpperCase());
	}

	public boolean isEmpty() {
		return rowMap.isEmpty();
	}

	public Long getLong(String columnName) {
		if (rowMap == null || rowMap.isEmpty()) {
			return null;
		}
		SortedMap columnMap = (SortedMap) rowMap.get(0);
		if (columnMap.get(columnName.toUpperCase()) == null) {
			return null;
		} else {
			return Long.parseLong(columnMap.get(columnName.toUpperCase())
					.toString().trim());
		}
	}

	public Long getLong(String columnName, int rowid) {
		if (rowMap == null || rowMap.isEmpty()) {
			return null;
		}
		SortedMap columnMap = (SortedMap) rowMap.get(rowid);
		if (columnMap.get(columnName.toUpperCase()) == null) {
			return null;
		} else {
			return Long.parseLong(columnMap.get(columnName.toUpperCase())
					.toString().trim());
		}
	}
}
