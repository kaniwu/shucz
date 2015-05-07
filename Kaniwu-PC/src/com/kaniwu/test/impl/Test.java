package com.kaniwu.test.impl;

import java.util.Set;

import com.kaniwu.common.manager.WechatRobotManager;

public class Test {
	
	public static void main (String[] args){
//    	String sql = "select SYSDATE() as sysdate from dual";
//    	
//    	List<Object> parasList = new ArrayList<Object>();
//    	
//    	for (int i = 0; i < 100; i++)
//			{
//				List<Map<String, Object>> list = DBConnManager.query(sql, parasList);
//				
//				System.out.println("-----conn"+i+"------"+list.get(0).get("sysdate"));
//			}
		
		try
			{
				WechatRobotManager.load();
				Set<String> keySet = WechatRobotManager.robotGreRspMap.keySet();
				
				for (String string : keySet)
					{
						System.out.println(WechatRobotManager.robotGreRspMap.get(string));
					}
				
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    }
}
