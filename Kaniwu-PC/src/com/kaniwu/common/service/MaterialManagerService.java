/*
 * 文 件 名:  MaterialManagerService.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-6
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
 * 素材管理服务层
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MaterialManagerService extends BaseService
	{
		/**
		 * 查询文本素材
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public List<MaterialManageBean> queryMaterialManageBean() throws Exception
		{
			IMaterialManagerDAO meterialManagerDAO = new MaterialManagerDAOImpl();
			
			return makeMaterialManageBean(meterialManagerDAO.queryMaterialManageBeans());
		}
		
		/**
		 * 查询图文素材
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public List<NewsMaterialBeans> queryNewsMaterialBean() throws Exception
		{
			IMaterialManagerDAO meterialManagerDAO = new MaterialManagerDAOImpl();
			
			return makeNewsMaterialBeans(meterialManagerDAO.queryNewsMaterialBean());
		}
		
		
		/**
		 * 查询结果转换成Bean
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param resultSet
		 * @return
		 * @see [类、类#方法、类#成员]
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
		 * 将查询结果转换成图文消息bean
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param resultSet
		 * @return
		 * @see [类、类#方法、类#成员]
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
		 * 根据素材编码获取素材
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param mediaId
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		private void getMaterialById(String mediaId) throws Exception
		{
			JSONObject materialJson = getMaterial(mediaId);
			
			
		}
		
	}
