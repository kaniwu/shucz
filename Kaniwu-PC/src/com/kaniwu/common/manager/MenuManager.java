/*
 * �� �� ��:  MenuManager.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-14
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
 * �˵�����
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-14]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MenuManager
	{
		/* ��־��¼��. */
		static Logger log = Logger.getLogger(MenuManager.class);
		
		/* �����˵��� .*/
		public static SubMenuBean[] subMenuBeans = null;
		
		/* һ���˵��� .*/
		public static MainMenuBean[] mainMenuBeans = null;
		
		/* һ�������˵���ϵ��. */
		public static HashMap<String, List<SubMenuBean>>  menuRelationMap= new HashMap<String, List<SubMenuBean>>(); 
		
		/* �����˵�URL��. */
		private static String CREATE_MENU_URL = //ConfKit.getWechat("CREATE_MENU_URL");
		 "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		
		/**
		 * ���ز˵�������
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static void load()throws Exception
		{
			MenuManagerService menuManagerService = new MenuManagerService();
			/**
			 * ȡ�����˵�
			 */
			subMenuBeans = menuManagerService.getSubMenu();
			
			/**
			 * ȡһ���˵�
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
		 * �����˵�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param menuBean
		 * @param accessToken
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
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
										
										log.error("�����˵�ʧ��:errorCode--"
											+ returnJsonObject.getString("errcode") + "--errmsg--"
											+ returnJsonObject.getString("errmsg"));
									}
								log.info("�����˵��ɹ���" + returnJsonObject.toString());
						
							}
					} catch (Exception e)
					{
						// TODO: handle exception
						log.error("�����˵�ʧ�ܣ�" + e);
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
