/*
 * 文 件 名:  MenuManager.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-14
 */
package com.kaniwu.common.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.kaniwu.common.bean.menu.FirstButton;
import com.kaniwu.common.bean.menu.MainMenuBean;
import com.kaniwu.common.bean.menu.MenuBean;
import com.kaniwu.common.bean.menu.SubButton;
import com.kaniwu.common.bean.menu.SubMenuBean;
import com.kaniwu.common.process.GetAccessTokenThread;
import com.kaniwu.common.service.MenuManagerService;
import com.kaniwu.util.HttpKit;

/**
 * 菜单管理
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuManager
	{
		/* 日志记录器. */
		static Logger log = Logger.getLogger(MenuManager.class);
		
		/* 二级菜单集 .*/
		public static SubMenuBean[] subMenuBeans = null;
		
		/* 一级菜单集 .*/
		public static MainMenuBean[] mainMenuBeans = null;
		
		/* 一级二级菜单关系集. */
		public static HashMap<String, List<SubMenuBean>>  menuRelationMap= new HashMap<String, List<SubMenuBean>>(); 
		
		/* 创建菜单URL串. */
		private static String CREATE_MENU_URL = //ConfKit.getWechat("CREATE_MENU_URL");
		 "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		
		/**
		 * 加载菜单到缓存
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public static void load()throws Exception
		{
			MenuManagerService menuManagerService = new MenuManagerService();
			/**
			 * 取二级菜单
			 */
			subMenuBeans = menuManagerService.getSubMenu();
			
			/**
			 * 取一级菜单
			 */
			mainMenuBeans = menuManagerService.getMenuBeans();
			
			if (null != mainMenuBeans && null != subMenuBeans)
				{
					ArrayList<SubMenuBean> arrayListTemp  = null;
					
					ArrayList<SubButton> subButtons = null;
					
					SubButton subButton = null;
					
					MainMenuBean menuBeantemp = null;
					
					ArrayList<FirstButton> firstButtons = new ArrayList<FirstButton>();
					
					for (int i = 0; i < mainMenuBeans.length; i++)
						{
							 menuBeantemp = mainMenuBeans[i];
							 
							 arrayListTemp = new ArrayList<SubMenuBean>();
							 
							 subButtons = new ArrayList<SubButton>();
							
							for (int j = 0; j < subMenuBeans.length; j++)
								{
									SubMenuBean subMenuBeanTemp = subMenuBeans[j];
									
									if (String.valueOf(menuBeantemp.getMenu_id()).equals(subMenuBeanTemp.getMenu_parent()))
										{
											subButton = new SubButton();
											
											subButton.setKey(subMenuBeanTemp.getKey());
											
											subButton.setName(subMenuBeanTemp.getName());
											
											subButton.setType(subMenuBeanTemp.getType());
											
											subButton.setUrl(subMenuBeanTemp.getUrl());
											
											subButtons.add(subButton);
											
											arrayListTemp.add(subMenuBeanTemp);
										}
								}
							menuRelationMap.put(String.valueOf(menuBeantemp.getMenu_id()), arrayListTemp);
							
							mainMenuBeans[i].setSub_botten((SubMenuBean[]) arrayListTemp.toArray(new SubMenuBean[arrayListTemp.size()]));
							
							FirstButton firstButton = new FirstButton();
							
							firstButton.setName(mainMenuBeans[i].getName());
							
							firstButton.setSub_button((SubButton[]) subButtons.toArray(new SubButton[subButtons.size()]));
							
							firstButtons.add(firstButton);
						}
					MenuBean menuBean = new MenuBean();
					
					menuBean.setButton((FirstButton[]) firstButtons.toArray(new FirstButton[firstButtons.size()]));
					
					createMenu(menuBean,GetAccessTokenThread.accessTokenBean.getToken());
				}
		}
		
		/**
		 * 创建菜单
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param menuBean
		 * @param accessToken
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public static String createMenu (MenuBean menuBean, String accessToken) throws Exception
			{
				String resultcode = "";
				try
					{
						JSONObject menuJson = JSONObject.fromObject(menuBean);
						
						String requestUrl = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
						
						String returnJSonString = HttpKit.post(requestUrl, menuJson.toString());
						
						JSONObject returnJsonObject = JSONObject.fromObject(returnJSonString);
				
						if (null != returnJsonObject)
							{
								if (!"0".equals(returnJsonObject.getString("errcode"))) 
									{
										resultcode = returnJsonObject.getString("errcode");
										
										log.error("创建菜单失败:errorCode--"
											+ returnJsonObject.getString("errcode") + "--errmsg--"
											+ returnJsonObject.getString("errmsg"));
									}
								log.info("创建菜单成功！" + returnJsonObject.toString());
						
							}
					} catch (Exception e)
					{
						// TODO: handle exception
						log.error("创建菜单失败：" + e);
					}
				
				return resultcode;
			}
		
		public static void main(String[] args) 
			{
				try
					{
						GetAccessTokenThread getAccessTokenThread = new GetAccessTokenThread();
						
						new Thread(getAccessTokenThread).start();
						
						MenuManager.load();
						
						System.out.println("@@@@@"+mainMenuBeans.length);
						System.out.println("@@@@@"+subMenuBeans.length);
					} catch (Exception e)
					{
						// TODO: handle exception
						e.printStackTrace();
					}
				
			}
		
	}
