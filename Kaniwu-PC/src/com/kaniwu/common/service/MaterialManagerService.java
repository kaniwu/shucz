/*
 * �� �� ��:  MaterialManagerService.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-6
 */
package com.kaniwu.common.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.kaniwu.common.DAO.impl.MaterialManagerDAOImpl;
import com.kaniwu.common.DAO.interfaces.IMaterialManagerDAO;
import com.kaniwu.common.bean.MaterialManageBean;
import com.kaniwu.common.bean.NewsMaterialBeans;

/**
 * �زĹ�������
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-6]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MaterialManagerService extends BaseService
	{
		/**
		 * ��ѯ�ı��ز�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<MaterialManageBean> queryMaterialManageBean() throws Exception
		{
			IMaterialManagerDAO meterialManagerDAO = new MaterialManagerDAOImpl();
			
			return makeMaterialManageBean(meterialManagerDAO.queryMaterialManageBeans());
		}
		
		/**
		 * ��ѯͼ���ز�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<NewsMaterialBeans> queryNewsMaterialBean() throws Exception
		{
			IMaterialManagerDAO meterialManagerDAO = new MaterialManagerDAOImpl();
			
			return makeNewsMaterialBeans(meterialManagerDAO.queryNewsMaterialBean());
		}
		
		
		/**
		 * ��ѯ���ת����Bean
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param resultSet
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		private List<MaterialManageBean> makeMaterialManageBean(
				List<Map<String, Object>> resultSet)
			{
				Map<String, Object> map;
				List<MaterialManageBean> list = new ArrayList<MaterialManageBean>();
				for (int i = 0; i < resultSet.size(); i++)
					{
						MaterialManageBean materialManageBean = new MaterialManageBean();
						
						map = resultSet.get(i);

						materialManageBean.setMaterial_id((Integer)map.get("material_id"));
						materialManageBean.setMaterial_name((String)map.get("material_name"));
						materialManageBean.setMaterial_content((String)map.get("material_content"));
						materialManageBean.setMaterial_order((String)map.get("material_order"));
						materialManageBean.setMaterial_desc((String)map.get("material_desc"));
						materialManageBean.setUpdate_time((Timestamp)map.get("update_time"));
						materialManageBean.setRsrv_str1((String)map.get("rsrv_str1"));
						materialManageBean.setRsrv_str2((String)map.get("rsrv_str2"));
						materialManageBean.setRsrv_str3((String)map.get("rsrv_str3"));
						

						list.add(materialManageBean);
					}
				return list;
			}
		
		/**
		 * ����ѯ���ת����ͼ����Ϣbean
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param resultSet
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		private List<NewsMaterialBeans> makeNewsMaterialBeans (List<Map<String, Object>> resultSet)
		{
			Map<String, Object> map;
			List<NewsMaterialBeans> list = new ArrayList<NewsMaterialBeans>();
			for (int i = 0; i < resultSet.size(); i++)
				{
					NewsMaterialBeans newsMaterialBeans = new NewsMaterialBeans();
					
					map = resultSet.get(i);

					newsMaterialBeans.setTitle(((String)map.get("material_title")));
					newsMaterialBeans.setDescription(((String)map.get("material_desc")));
					newsMaterialBeans.setOrder(((String)map.get("material_order")));
					newsMaterialBeans.setType(((String)map.get("material_type")));
					newsMaterialBeans.setPicUrl(((String)map.get("material_pic")));
					newsMaterialBeans.setUrl(((String)map.get("material_url")));
					newsMaterialBeans.setSort((String.valueOf(map.get("material_sort"))));
					
					
					list.add(newsMaterialBeans);
				}
			return list;
		}
		
		/**
		 * �����زı����ȡ�ز�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param mediaId
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		private void getMaterialById(String mediaId) throws Exception
		{
			JSONObject materialJson = getMaterial(mediaId);
			
			
		}
		
	}
