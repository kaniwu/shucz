/*
 * �� �� ��:  WechatRobotBean.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-26
 */
package com.kaniwu.common.bean;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-26]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class WechatRobotBean
	{
		/** ���� .*/
		private String question;
		
		/** �� .*/
		private String answer;
		
		/** ״̬ .*/
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
