/*
 * 文 件 名:  MaterialManager.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-6
 */
package com.kaniwu.common.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.kaniwu.common.bean.MaterialManageBean;
import com.kaniwu.common.bean.NewsMaterialBeans;
import com.kaniwu.common.service.MaterialManagerService;

/**
 * 素材管理
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MaterialManager
	{
		/* 日志记录对象. */
		private static Logger log = Logger.getLogger(MaterialManager.class);
		
		/* 素材集 . */
		public static List<MaterialManageBean> MaterialBeans ;
		
		/*指令素材关系集 .*/
//		public static HashMap<String, MaterialManageBean> MaterialMap = new HashMap<String, MaterialManageBean>();
		
		/* 文本素材集合 .*/
		public static HashMap<String, MaterialManageBean> TextMaterialMap = new HashMap<String, MaterialManageBean>();
		
		/* 单图文素材集合 .*/
		public static HashMap<String, List<NewsMaterialBeans>> PicMaterialMap = new HashMap<String, List<NewsMaterialBeans>>();
		
		/* 无图单图文素材集合 .*/
		public static HashMap<String, NewsMaterialBeans> NoPicMaterialMap = new HashMap<String, NewsMaterialBeans>();
		
		/* 多图文素材集合 .*/
		public static HashMap<String, NewsMaterialBeans> NewsMaterialMap = new HashMap<String, NewsMaterialBeans>();
		
		/* 首页无图多图文素材集合 .*/
		public static HashMap<String, NewsMaterialBeans> NoNewsMaterialMap = new HashMap<String, NewsMaterialBeans>();

		
		/**
		 * 启动载入消息Bean
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public static int load() throws Exception
		{
			MaterialManagerService materialManagerService = new MaterialManagerService();
			
			try
				{
					MaterialBeans = materialManagerService.queryMaterialManageBean(); 
					
					for (MaterialManageBean mmBean : MaterialBeans )
						{
							TextMaterialMap.put(mmBean.getMaterial_order(), mmBean);
							
						}
					/**
					 * 加载图文消息
					 */
					loadNews();
				} catch (Exception e)
				{
					// TODO: handle exception
					log.error("素材管理集查询失败:" + e);
					return 1;
				}
			
			return 0;
		}
		
		/**
		 * 重载素材缓存
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public static void reload() throws Exception
		{
			MaterialManagerService materialManagerService = new MaterialManagerService();
			
			
			/* 素材集 . */
			  List<MaterialManageBean> MaterialBeansTemp ;
			  
			/* 文本素材集 . */
			  HashMap<String, MaterialManageBean> TextMaterialMapTemp = new HashMap<String, MaterialManageBean>();
			  
			  /* 单图文素材集合 .*/
				HashMap<String, List<NewsMaterialBeans>> PicMaterialMapTemp = new HashMap<String, List<NewsMaterialBeans>>();
				
//				/* 无图单图文素材集合 .*/
//				HashMap<String, NewsMaterialBeans> NoPicMaterialMapTemp = new HashMap<String, NewsMaterialBeans>();
//				
//				/* 多图文素材集合 .*/
//				HashMap<String, NewsMaterialBeans> NewsMaterialMapTemp = new HashMap<String, NewsMaterialBeans>();
//				
//				/* 首页无图多图文素材集合 .*/
//				HashMap<String, NewsMaterialBeans> NoNewsMaterialMapTemp = new HashMap<String, NewsMaterialBeans>();
			  
			/*指令素材关系集 .*/
//			  HashMap<String, MaterialManageBean> MaterialMapTemp = new HashMap<String, MaterialManageBean>();
			
			  MaterialBeansTemp = materialManagerService.queryMaterialManageBean(); 
			
			for (MaterialManageBean mmBean : MaterialBeansTemp )
				{
					TextMaterialMapTemp.put(mmBean.getMaterial_order(), mmBean);
					
				}
			
			List<NewsMaterialBeans> newsMaterialBeans = materialManagerService.queryNewsMaterialBean();
			
			NewsMaterialBeans newsBeans = null;
			
			List<NewsMaterialBeans> tempList = new ArrayList<NewsMaterialBeans>();
			
			for (int i = 0; i < newsMaterialBeans.size(); i++)
				{
					newsBeans = newsMaterialBeans.get(i);
					
					//如果含有指令
					if(PicMaterialMapTemp.containsKey(newsBeans.getOrder()))
						{
							tempList = PicMaterialMapTemp.get(newsBeans.getOrder());
							
							tempList.add(newsBeans);
							
							PicMaterialMapTemp.put(newsBeans.getOrder(), tempList);
						}else {
							tempList.add(newsBeans);
							
							PicMaterialMapTemp.put(newsBeans.getOrder(), tempList);
						}
//					//单图文
//					if ("SP".equalsIgnoreCase(newsBeans.getType()))
//						{
//							PicMaterialMapTemp.put(newsBeans.getOrder(), newsBeans);
//						}else //无图单图文
//							if ("SNP".equalsIgnoreCase(newsBeans.getType())) 
//								{
//									NoPicMaterialMapTemp.put(newsBeans.getOrder(), newsBeans);
//								}else //首页有图多图文
//									if ("MP".equalsIgnoreCase(newsBeans.getType())) 
//										{
//											NewsMaterialMapTemp.put(newsBeans.getOrder(), newsBeans);
//										}else //首页无图多图文
//											if ("MNP".equalsIgnoreCase(newsBeans.getType())) 
//												{
//													NoNewsMaterialMapTemp.put(newsBeans.getOrder(), newsBeans);
//												}
				}
			synchronized (TextMaterialMap)
				{
					TextMaterialMap = TextMaterialMapTemp;
				}
			synchronized (PicMaterialMap)
			{
				PicMaterialMap = PicMaterialMapTemp;
			}
//			synchronized (NoPicMaterialMap)
//			{
//				NoPicMaterialMap = NoPicMaterialMapTemp;
//			}
//			synchronized (NewsMaterialMap)
//			{
//				NewsMaterialMap = NewsMaterialMapTemp;
//			}
//			synchronized (NoNewsMaterialMap)
//			{
//				NoNewsMaterialMap = NoNewsMaterialMapTemp;
//			}
			
		}
		
		/**
		 * 加载图文素材
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public static void loadNews() throws Exception
		{
			MaterialManagerService managerService = new MaterialManagerService();
			
			List<NewsMaterialBeans> newsMaterialBeans = managerService.queryNewsMaterialBean();
			
			NewsMaterialBeans newsBeans = null;
			
			List<NewsMaterialBeans> tempList = new ArrayList<NewsMaterialBeans>();
			
			for (int i = 0; i < newsMaterialBeans.size(); i++)
				{
					newsBeans = newsMaterialBeans.get(i);
					
					//如果含有指令
					if(PicMaterialMap.containsKey(newsBeans.getOrder()))
						{
							tempList = PicMaterialMap.get(newsBeans.getOrder());
							
							tempList.add(newsBeans);
							
							PicMaterialMap.put(newsBeans.getOrder(), tempList);
						}else {
							tempList.add(newsBeans);
							
							PicMaterialMap.put(newsBeans.getOrder(), tempList);
						}
					
//					//单图文
//					if ("SP".equalsIgnoreCase(newsBeans.getType()))
//						{
//							PicMaterialMap.put(newsBeans.getOrder(), newsBeans);
//						}else //无图单图文
//							if ("SNP".equalsIgnoreCase(newsBeans.getType())) 
//								{
//									NoPicMaterialMap.put(newsBeans.getOrder(), newsBeans);
//								}else //首页有图多图文
//									if ("MP".equalsIgnoreCase(newsBeans.getType())) 
//										{
//											NewsMaterialMap.put(newsBeans.getOrder(), newsBeans);
//										}else //首页无图多图文
//											if ("MNP".equalsIgnoreCase(newsBeans.getType())) 
//												{
//													NoNewsMaterialMap.put(newsBeans.getOrder(), newsBeans);
//												}
				}
		}
		/**
		 * 取文本素材
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param order
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public static MaterialManageBean getMaterial(String order)
			{
				
				return TextMaterialMap.get(order);
			}
		
		/**
		 * 取单图文素材
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param order
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public static List<NewsMaterialBeans> getPicMaterialBean(String order)
			{
				return PicMaterialMap.get(order);
			}
		
		/**
		 * 取无图单图文
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param order
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public static NewsMaterialBeans getNoPicMaterialBean(String order)
			{
				return NoPicMaterialMap.get(order);
			}
		
		/**
		 * 取首页有图多图文
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param order
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public static NewsMaterialBeans getNewsMaterialBean(String order)
			{
				return NewsMaterialMap.get(order);
			}
		
		/**
		 * 取首页无图多图文
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param order
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public static NewsMaterialBeans getNoNewsMaterialBean(String order)
			{
				return NoNewsMaterialMap.get(order);
			}
		
	}
