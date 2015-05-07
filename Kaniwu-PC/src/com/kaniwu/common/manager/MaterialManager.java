/*
 * �� �� ��:  MaterialManager.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-6
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
 * �زĹ���
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-6]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MaterialManager
	{
		/* ��־��¼����. */
		private static Logger log = Logger.getLogger(MaterialManager.class);
		
		/* �زļ� . */
		public static List<MaterialManageBean> MaterialBeans ;
		
		/*ָ���زĹ�ϵ�� .*/
//		public static HashMap<String, MaterialManageBean> MaterialMap = new HashMap<String, MaterialManageBean>();
		
		/* �ı��زļ��� .*/
		public static HashMap<String, MaterialManageBean> TextMaterialMap = new HashMap<String, MaterialManageBean>();
		
		/* ��ͼ���زļ��� .*/
		public static HashMap<String, List<NewsMaterialBeans>> PicMaterialMap = new HashMap<String, List<NewsMaterialBeans>>();
		
		/* ��ͼ��ͼ���زļ��� .*/
		public static HashMap<String, NewsMaterialBeans> NoPicMaterialMap = new HashMap<String, NewsMaterialBeans>();
		
		/* ��ͼ���زļ��� .*/
		public static HashMap<String, NewsMaterialBeans> NewsMaterialMap = new HashMap<String, NewsMaterialBeans>();
		
		/* ��ҳ��ͼ��ͼ���زļ��� .*/
		public static HashMap<String, NewsMaterialBeans> NoNewsMaterialMap = new HashMap<String, NewsMaterialBeans>();

		
		/**
		 * ����������ϢBean
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
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
					 * ����ͼ����Ϣ
					 */
					loadNews();
				} catch (Exception e)
				{
					// TODO: handle exception
					log.error("�زĹ�����ѯʧ��:" + e);
					return 1;
				}
			
			return 0;
		}
		
		/**
		 * �����زĻ���
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static void reload() throws Exception
		{
			MaterialManagerService materialManagerService = new MaterialManagerService();
			
			
			/* �زļ� . */
			  List<MaterialManageBean> MaterialBeansTemp ;
			  
			/* �ı��زļ� . */
			  HashMap<String, MaterialManageBean> TextMaterialMapTemp = new HashMap<String, MaterialManageBean>();
			  
			  /* ��ͼ���زļ��� .*/
				HashMap<String, List<NewsMaterialBeans>> PicMaterialMapTemp = new HashMap<String, List<NewsMaterialBeans>>();
				
//				/* ��ͼ��ͼ���زļ��� .*/
//				HashMap<String, NewsMaterialBeans> NoPicMaterialMapTemp = new HashMap<String, NewsMaterialBeans>();
//				
//				/* ��ͼ���زļ��� .*/
//				HashMap<String, NewsMaterialBeans> NewsMaterialMapTemp = new HashMap<String, NewsMaterialBeans>();
//				
//				/* ��ҳ��ͼ��ͼ���زļ��� .*/
//				HashMap<String, NewsMaterialBeans> NoNewsMaterialMapTemp = new HashMap<String, NewsMaterialBeans>();
			  
			/*ָ���زĹ�ϵ�� .*/
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
					
					//�������ָ��
					if(PicMaterialMapTemp.containsKey(newsBeans.getOrder()))
						{
							tempList = PicMaterialMapTemp.get(newsBeans.getOrder());
							
							tempList.add(newsBeans);
							
							PicMaterialMapTemp.put(newsBeans.getOrder(), tempList);
						}else {
							tempList.add(newsBeans);
							
							PicMaterialMapTemp.put(newsBeans.getOrder(), tempList);
						}
//					//��ͼ��
//					if ("SP".equalsIgnoreCase(newsBeans.getType()))
//						{
//							PicMaterialMapTemp.put(newsBeans.getOrder(), newsBeans);
//						}else //��ͼ��ͼ��
//							if ("SNP".equalsIgnoreCase(newsBeans.getType())) 
//								{
//									NoPicMaterialMapTemp.put(newsBeans.getOrder(), newsBeans);
//								}else //��ҳ��ͼ��ͼ��
//									if ("MP".equalsIgnoreCase(newsBeans.getType())) 
//										{
//											NewsMaterialMapTemp.put(newsBeans.getOrder(), newsBeans);
//										}else //��ҳ��ͼ��ͼ��
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
		 * ����ͼ���ز�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
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
					
					//�������ָ��
					if(PicMaterialMap.containsKey(newsBeans.getOrder()))
						{
							tempList = PicMaterialMap.get(newsBeans.getOrder());
							
							tempList.add(newsBeans);
							
							PicMaterialMap.put(newsBeans.getOrder(), tempList);
						}else {
							tempList.add(newsBeans);
							
							PicMaterialMap.put(newsBeans.getOrder(), tempList);
						}
					
//					//��ͼ��
//					if ("SP".equalsIgnoreCase(newsBeans.getType()))
//						{
//							PicMaterialMap.put(newsBeans.getOrder(), newsBeans);
//						}else //��ͼ��ͼ��
//							if ("SNP".equalsIgnoreCase(newsBeans.getType())) 
//								{
//									NoPicMaterialMap.put(newsBeans.getOrder(), newsBeans);
//								}else //��ҳ��ͼ��ͼ��
//									if ("MP".equalsIgnoreCase(newsBeans.getType())) 
//										{
//											NewsMaterialMap.put(newsBeans.getOrder(), newsBeans);
//										}else //��ҳ��ͼ��ͼ��
//											if ("MNP".equalsIgnoreCase(newsBeans.getType())) 
//												{
//													NoNewsMaterialMap.put(newsBeans.getOrder(), newsBeans);
//												}
				}
		}
		/**
		 * ȡ�ı��ز�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param order
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static MaterialManageBean getMaterial(String order)
			{
				
				return TextMaterialMap.get(order);
			}
		
		/**
		 * ȡ��ͼ���ز�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param order
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static List<NewsMaterialBeans> getPicMaterialBean(String order)
			{
				return PicMaterialMap.get(order);
			}
		
		/**
		 * ȡ��ͼ��ͼ��
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param order
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static NewsMaterialBeans getNoPicMaterialBean(String order)
			{
				return NoPicMaterialMap.get(order);
			}
		
		/**
		 * ȡ��ҳ��ͼ��ͼ��
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param order
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static NewsMaterialBeans getNewsMaterialBean(String order)
			{
				return NewsMaterialMap.get(order);
			}
		
		/**
		 * ȡ��ҳ��ͼ��ͼ��
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param order
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static NewsMaterialBeans getNoNewsMaterialBean(String order)
			{
				return NoNewsMaterialMap.get(order);
			}
		
	}
