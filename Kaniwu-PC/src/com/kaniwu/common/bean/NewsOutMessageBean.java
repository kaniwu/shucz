
package com.kaniwu.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 输出图文消息
 * 
 * @author wurb3
 * 
 */
public class NewsOutMessageBean extends OutMessageBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String			MsgType	= "news";
	private Integer			ArticleCount;
	private String			Title;
	private String			Description;
	private String			PicUrl;
	private String			Url;

	private List<ArticlesBean>	Articles;

	public String getMsgType() {
		return MsgType;
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public List<ArticlesBean> getArticles() {
		return Articles;
	}

	public void setArticles(List<ArticlesBean> articles) {
		if (articles != null) {
			if (articles.size() > 10)
				articles = new ArrayList<ArticlesBean>(articles.subList(0, 10));

			ArticleCount = articles.size();
		}
		Articles = articles;
	}
}
