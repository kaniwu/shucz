package com.kaniwu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;

import com.kaniwu.common.bean.ErrorMsgBean;


/**
 * 微信Oauth和支付工具类
 * @author wurb3
 * @date 2013-11-14 下午4:42:42
 */
public class OauthWeChat {

	private static final String CODE_URI = "http://open.weixin.qq.com/connect/oauth2/authorize";
	private static final String TOKEN_URI = "https://api.weixin.qq.com/sns/oauth2/access_token";
	private static final String REFRESH_TOKEN_URI = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
	private static final String USER_INFO_URI = "https://api.weixin.qq.com/sns/userinfo";
	
	private static final String ACCESS_TOKEN_URI = "https://api.weixin.qq.com/cgi-bin/token";
	private static final String USER_INFO_URI_1 = "https://api.weixin.qq.com/cgi-bin/user/info";
	
	private String appid;
	private String secret;
	
	Tools tools = new Tools();
	
	public OauthWeChat() {
		super();
		this.appid = ConfKit.getWechat("AppId");
		this.secret = ConfKit.getWechat("AppSecret");
		
		System.out.println("appid:"+this.appid);
		System.out.println("secret:"+this.secret);
	}

	public OauthWeChat(String appid, String secret) {
		super();
		this.appid = appid;
		this.secret = secret;
	}

	
	/**
	 * 请求code
	 */
	public String getCode() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", getAppid());
		params.put("response_type", "code");
		params.put("redirect_uri", ConfKit.getWechat("redirect_uri"));
		params.put("scope", "snsapi_base"); // snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo
		// （弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
		params.put("state", "wx#wechat_redirect");
		String para = createSign(params, false);
		return CODE_URI + "?" + para;
	}

	
	/**
	 * 通过code 换取 access_token
	 * @param code
	 * @return
	 */
	public String getToken(String code) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", getAppid());
		params.put("secret", getSecret());
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		return HttpKit.get(TOKEN_URI, params);
	}

	/**
	 * 刷新 access_token
	 * @param refreshToken
	 * @return
	 */
	public String getRefreshToken(String refreshToken) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", getAppid());
		params.put("grant_type", "refresh_token");
		params.put("refresh_token", refreshToken);
		return HttpKit.get(REFRESH_TOKEN_URI, params);
	}
	
	/**
	 * 获取access_token
	 * @return
	 */
	public  String getToken(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", getAppid());
		params.put("secret", getSecret());
		params.put("grant_type", "client_credential");
		return HttpKit.get(ACCESS_TOKEN_URI, params);
	}
	
	/**
	 * 获取用户信息
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public String getUserInfoString(String accessToken, String openid){
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("access_token", accessToken);
		params.put("openid", openid);
		String returnString = HttpKit.get(USER_INFO_URI_1, params);
		ErrorMsgBean errorMsgBean= tools.String2ErrorMsgBean(returnString);
		
		//如果是错误信息重新调一次获取access_token的接口
		if (!"".equals(errorMsgBean.getErrcode())&&null != errorMsgBean.getErrcode()) {
			params.put("access_token", getToken());
			returnString = HttpKit.get(USER_INFO_URI_1, params);
		}
		return returnString;
	}

	/**
	 * 拉取用户信息
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public String getUserInfo(String accessToken, String openid) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("openid", openid);
		return HttpKit.get(USER_INFO_URI, params);
	}

	// 参与 paySign 签名的字段包括：appid、timestamp、noncestr、package 以及 appkey。这
	// 里 signType 并不参与签名

	// 微信的Package参数
	public static String getPackage(Map<String, String> params) {
		String partnerKey = ConfKit.getWechat("partnerKey");
		String partnerId = ConfKit.getWechat("partnerId");
		String notifyUrl = ConfKit.getWechat("notify_url");
		// 公共参数
		params.put("bank_type", "WX");
		params.put("attach", "yongle");
		params.put("partner", partnerId);
		params.put("notify_url", notifyUrl);
		params.put("input_charset", "UTF-8");
		return packageSign(params, partnerKey);
	}

	// 构造签名
	public static String createSign(Map<String, String> params, boolean encode) {
		Set<String> keysSet = params.keySet();
		Object[] keys = keysSet.toArray();
		Arrays.sort(keys);
		StringBuffer temp = new StringBuffer();
		boolean first = true;
		for (Object key : keys) {
			if (first) {
				first = false;
			} else {
				temp.append("&");
			}
			temp.append(key).append("=");
			Object value = params.get(key);
			String valueString = "";
			if (null != value) {
				valueString = value.toString();
			}
			if (encode) {
				try {
					temp.append(URLEncoder.encode(valueString, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else {
				temp.append(valueString);
			}
		}
		return temp.toString();
	}

	// 构造package, 这是我见到的最草蛋的加密，尼玛文档还有错
	private static String packageSign(Map<String, String> params,
			String paternerKey) {
		String string1 = createSign(params, false);
		String stringSignTemp = string1 + "&key=" + paternerKey;
		String signValue = DigestUtils.md5Hex(stringSignTemp).toUpperCase();
		String string2 = createSign(params, true);
		return string2 + "&sign=" + signValue;
	}

	// 支付签名
	public static String paySign(String timestamp, String noncestr,
			String packages) {
		Map<String, String> paras = new HashMap<String, String>();
		paras.put("appid", ConfKit.getWechat("AppId"));
		paras.put("timestamp", timestamp);
		paras.put("noncestr", noncestr);
		paras.put("package", packages);
		paras.put("appkey", ConfKit.getWechat("paySignKey"));
		// appid、timestamp、noncestr、package 以及 appkey。
		String string1 = createSign(paras, false);
		String paySign = SHA1.encode(string1);
		return paySign;
	}

//	public static void main(String[] args) {
		// 支付的参数 , 测试 通过的...
		/*
		Map<String, String> params = new HashMap<String, String>();
		params.put("body", "test");   //商品描述。
		params.put("total_fee", "1");   //订单总金额
		params.put("fee_type", "1");	//现金支付币种,取值： 1 （人民币）
		params.put("out_trade_no", "44747358"); //商户系统内部的订单号
		params.put("spbill_create_ip", Common.getIp(request)); // ip
		// 限时支付参数
		// time_start  交 易 起 始 时 间 yyyyMMddHHmmss
		// time_expire 交 易 结 束 时 间 yyyyMMddHHmmss
		// package 参数------------------------------  //
		
		String timeStamp = System.currentTimeMillis() + "";
		String nonceStr = Common.getRandomString(8);
		String packagestring = OauthWeChat.getPackage(params);
		String paySign = OauthWeChat.paySign(timeStamp, nonceStr, packagestring);
		// appId
		model.addAttribute("appid", props.get("AppId"));
		// timeStamp
		model.addAttribute("timeStamp", timeStamp);
		// nonceStr
		model.addAttribute("nonceStr", nonceStr);
		// package
		model.addAttribute("package", packagestring);
		// paySign
		model.addAttribute("paySign", paySign);
		
		//result.put("signType",  "SHA1"); //以默认
		System.out.println(model.toString());
		*/
		OauthWeChat oauthWeChat = new OauthWeChat();
//		String token=oauthWeChat.getToken();
//		String code = oauthWeChat.getCode();
//		System.out.println("Code:"+code);
//		System.out.println("access_token:"+oauthWeChat.getToken());
//		
//		System.out.print(oauthWeChat.getUserInfoString(oauthWeChat.getToken(), "oT1YOt40ydMVHcJrTqrWKu4LhTvI"));
//	}
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	
}
