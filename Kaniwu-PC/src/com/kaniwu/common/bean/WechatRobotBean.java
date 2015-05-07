/*
 * 文 件 名:  WechatRobotBean.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-26
 */
package com.kaniwu.common.bean;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WechatRobotBean
	{
		/** 问题 .*/
		private String question;
		
		/** 答案 .*/
		private String answer;
		
		/** 状态 .*/
		private String states;

		public String getQuestion()
			{
				return question;
			}

		public void setQuestion(String question)
			{
				this.question = question;
			}

		public String getAnswer()
			{
				return answer;
			}

		public void setAnswer(String answer)
			{
				this.answer = answer;
			}

		public String getStates()
			{
				return states;
			}

		public void setStates(String states)
			{
				this.states = states;
			}
		
	}
