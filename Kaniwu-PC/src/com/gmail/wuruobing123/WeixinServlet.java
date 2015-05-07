package com.gmail.wuruobing123;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeixinServlet extends HttpServlet
	{
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException
			{
				String signature = req.getParameter("signature");
				String timestamp = req.getParameter("timestamp");
				String nonce = req.getParameter("nonce");
				String echostring = req.getParameter("echostr");
				String token = "kaniwu"; // Note: 改成你自己的Token
				if (signature == null || timestamp == null || nonce == null
						|| echostring == null)
					{
						write(resp, "Error parameter count.");
						return;
					}
				// 1. 将token、timestamp、nonce三个参数进行字典序排序
				String[] strArr = new String[]
					{ token, timestamp, nonce };
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
					} catch (NoSuchAlgorithmException e)
					{
						e.printStackTrace();
					}
				mdSha1.update(sb.toString().getBytes());
				byte[] codedBytes = mdSha1.digest();
				String codedString = new BigInteger(1, codedBytes).toString(16);
				// 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
				if (codedString.equals(signature))
					{
						write(resp, echostring);
						return;
					} else
					{
						write(resp, "Check error.");
						return;
					}
			}

		/**
		 * 输出信息
		 * 
		 * @param resp
		 * @param msg
		 * @throws IOException
		 */
		private void write(HttpServletResponse resp, String msg)
				throws IOException
			{
				OutputStream os = resp.getOutputStream();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
						os));
				bw.write(msg);
				bw.flush();
				bw.close();
			}
	}
