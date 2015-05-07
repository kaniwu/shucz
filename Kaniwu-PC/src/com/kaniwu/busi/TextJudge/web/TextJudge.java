package com.kaniwu.busi.TextJudge.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kaniwu.busi.common.WechatBaseClass;
import com.kaniwu.common.bean.ArticlesBean;
import com.kaniwu.common.bean.MaterialManageBean;
import com.kaniwu.common.bean.NewsMaterialBeans;
import com.kaniwu.common.bean.NewsOutMessageBean;
import com.kaniwu.common.bean.TextOutMessageBean;
import com.kaniwu.common.manager.MaterialManager;

public class TextJudge extends WechatBaseClass
	{
		/**
		 * ��־��¼��
		 */
		private Logger log = Logger.getLogger(TextJudge.class);
		/**
		 * ָ��Ĵ�����
		 * ����������Ϣ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param type
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public TextOutMessageBean queryTradeReturnText(String type)
			{
				
				TextOutMessageBean oms = new TextOutMessageBean();
				log.info("��ʼȡ�ز�"+type);
				MaterialManageBean materialBean = MaterialManager.getMaterial(type);
				log.info("ȥ�ز����"+materialBean.getMaterial_content());
				
				String content = materialBean.getMaterial_content();
				if (null == content || content.isEmpty() )
					{
						content = "�ز�δ���ã�������ز�����";
					}
				oms.setContent(content);
				
				return oms;
			}

		/**
		 * ָ�����
		 * ����ͼ����Ϣ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param type
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public NewsOutMessageBean queryTradeReturnNews(String type)
			{
				NewsOutMessageBean oms = new NewsOutMessageBean();
				
				ArticlesBean article;
				
				NewsMaterialBeans newsMaterialBean ;
				
				List<NewsMaterialBeans> newsMaterialBeans = MaterialManager.getPicMaterialBean(type);
				
				List<ArticlesBean> articlesBeans = new ArrayList<ArticlesBean>();

				for (int i = 0; i < newsMaterialBeans.size(); i++)
					{
						article = new ArticlesBean();
						
						newsMaterialBean = newsMaterialBeans.get(i);
						
						if (0==i)
							{
								oms.setTitle(newsMaterialBean.getTitle());
								oms.setPicUrl(newsMaterialBean.getPicUrl());
								oms.setUrl(newsMaterialBean.getUrl());
								oms.setDescription(newsMaterialBean.getDescription());
							}
						article.setTitle(newsMaterialBean.getTitle());
						article.setPicUrl(newsMaterialBean.getPicUrl());
						article.setUrl(newsMaterialBean.getUrl());
						article.setDescription(newsMaterialBean.getDescription());
						
						articlesBeans.add(article);
					}
				
				oms.setArticles(articlesBeans);

				return oms;
			}
		
		public static void main(String[] args) throws Exception
			{
				MaterialManager.loadNews();
				
				TextJudge textJudge = new TextJudge();
				
				textJudge.queryTradeReturnNews("TW");
			}
	}
