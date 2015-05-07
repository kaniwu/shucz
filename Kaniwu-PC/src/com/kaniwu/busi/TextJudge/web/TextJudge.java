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
		 * 日志记录器
		 */
		private Logger log = Logger.getLogger(TextJudge.class);
		/**
		 * 指令的处理方法
		 * 返回文字消息
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param type
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public TextOutMessageBean queryTradeReturnText(String type)
			{
				
				TextOutMessageBean oms = new TextOutMessageBean();
				log.info("开始取素材"+type);
				MaterialManageBean materialBean = MaterialManager.getMaterial(type);
				log.info("去素材完成"+materialBean.getMaterial_content());
				
				String content = materialBean.getMaterial_content();
				if (null == content || content.isEmpty() )
					{
						content = "素材未配置！请进行素材配置";
					}
				oms.setContent(content);
				
				return oms;
			}

		/**
		 * 指令处理方法
		 * 返回图文消息
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param type
		 * @return
		 * @see [类、类#方法、类#成员]
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
