package com.kaniwu.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.kaniwu.common.bean.ErrorMsgBean;
import com.kaniwu.common.bean.SubscribeUserInfoBean;
import com.thoughtworks.xstream.XStream;




public class Tools {
	private static final Logger logger=Logger.getLogger(Tools.class);
	
	/**
	 * 输入流转换成字符串
	 * @param in
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static final String inputStream2String(InputStream in) throws UnsupportedEncodingException, IOException{
		if(in == null)
			return "";
		
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n, "UTF-8"));
		}
		return out.toString();
	}
	
	/**
	 * 校验token
	 * @param token
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static final boolean checkSignature(String token,String signature,String timestamp,String nonce){
		logger.debug("Tools in checkSignature!");
		// 1. 将token、timestamp、nonce三个参数进行字典序排序
        String[] strArr = new String[] { token, timestamp, nonce };
        java.util.Arrays.sort(strArr);
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuffer sb = new StringBuffer();
        for (String str : strArr)
        {
            sb.append(str);
        }
        MessageDigest mdSha1 = null;
        try
        {
            mdSha1 = MessageDigest.getInstance("SHA-1");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        mdSha1.update(sb.toString().getBytes());
        byte[] codedBytes = mdSha1.digest();
        String codedString = new BigInteger(1, codedBytes).toString(16);
        // 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (codedString.equals(signature))
        {
            return true;
        }else {
			return false;
		}
		
	}
	
	/**
	 * json串转换成用户信息Bean
	 * @param jsonStr
	 * @return
	 */
	public static SubscribeUserInfoBean String2SubInfoBean(String jsonStr){
		JSONObject json = JSONObject.fromObject(jsonStr);
		logger.info(jsonStr);
		SubscribeUserInfoBean subInfoBean = new SubscribeUserInfoBean();
		
		subInfoBean.setSubscribe(Integer.parseInt(json.getString("subscribe")));
		subInfoBean.setSubscribe_openid(json.getString("openid"));
		subInfoBean.setSubscribe_nickname(json.getString("nickname"));
		subInfoBean.setSubscribe_sex(Integer.parseInt(json.getString("sex")));
		subInfoBean.setCity(json.getString("city"));
		subInfoBean.setCountry(json.getString("country"));
		subInfoBean.setProvince(json.getString("province"));
		subInfoBean.setLanguage(json.getString("language"));
		subInfoBean.setHeadimgurl(json.getString("headimgurl"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(new Date(json.getLong("subscribe_time")*1000));
		subInfoBean.setSubscribe_time(date);
		
		
		
		return subInfoBean;
	}
	
	/**
	 * 将错误信息串转换成错误信息Bean
	 * @param jsonStr
	 * @return
	 */
	public static ErrorMsgBean String2ErrorMsgBean(String jsonStr){
		JSONObject json = JSONObject.fromObject(jsonStr);
		
		ErrorMsgBean errorMsgBean = new ErrorMsgBean();
		
		if (null!=json.getString("errcode")&&!"".equals(json.getString("errcode"))) {
			errorMsgBean.setErrcode(json.getString("errcode"));
			errorMsgBean.setErrmsg(json.getString("errmsg"));
		}
		return errorMsgBean;
	}
	
	/**
	 * 将Bean转化成Xml
	 * @param obj
	 * @return
	 */
	public static String  beanToXml(Object obj){
		XStream xStream = XStreamFactory.init(false);
		xStream.alias("xml", obj.getClass());
		return xStream.toXML(obj);
	}
	
}
