package com.kaniwu.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kaniwu.common.bean.MaterialManageBean;

public class Upload extends HttpServlet{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final int MAX_MEMORY_SIZE = 5 * 2 * 1024;
	private static final File REPOSITORY_PATH = new File("c:\\temp");
	
	File file;
	ServletContext application;
	
	public Upload(){
		
	}
	
	public void destroy() {
		super.destroy(); 
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
			String action =request.getParameter("action");
			if("uploadFile".equals(action)){
				try {
					this.uploadFile(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
}
	private void uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		String path=request.getContextPath();
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String nation = null;
		String title = null;
		String textarea = null;
		String type = null;
		MaterialManageBean mBean = new MaterialManageBean();
		
//		ConBean con = new ConBean();
		application = this.getServletContext();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MAX_MEMORY_SIZE);
		factory.setRepository(REPOSITORY_PATH);
		

		// 创建文件上传控制器
		ServletFileUpload servletfileupload = new ServletFileUpload(factory);
		try {
			List items = servletfileupload.parseRequest(request);
			Iterator iter = items.iterator();

			

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					String name = item.getFieldName();
					if ("title".equals(name)) {
						title = new String(item.getString().getBytes("ISO-8859-1"),"utf-8");
					}else if("nation".equals(name)){
						nation = item.getString();
					}else if ("textarea".equals(name)) {
						textarea = new String(item.getString().getBytes("ISO-8859-1"),"utf-8");
					}else if ("type".equals(name)) {
						type = item.getString();
					}
					
				}else{
					String filename = item.getName();
//					String filename = new String(item.getName().getBytes("UTF-8"), "GB2312");
					//判断图片类型
					String fileType = filename.substring(filename.lastIndexOf(".")+1, filename.length());
					List<String> typeList = new ArrayList<String>();
					typeList.add("JPG");
					typeList.add("PNG");
					if (!typeList.contains(filename)) {
						System.out.println("");
						//log.error("请确定图片格式为：jpg，png！");
					}
					
					long size = item.getSize();
					if (filename == null || filename.equals("") && size == 0) {
						continue;
					}
					file = new File(application.getRealPath("/") + "image/"
							);
					if (!file.exists()) {
						file.mkdirs();
					}
					
					item.write(new File(file, filename));
					
				}
			}
			response.getWriter().print("<script languge='javascript'>alert('文件上传成功！'); window.location.href='/Kaniwu-PC/MyHtml.html'</script>");
			System.out.println("title："+title);
			System.out.println("nation："+nation);
			System.out.println("type："+type);
			System.out.println("textarea："+textarea);
			
			
			if ("text".equals(type)) {
				mBean.setMaterial_content(textarea);
			} else if("image".equals(type)){
				mBean.setMaterial_desc(textarea);
				mBean.setImg_url("");
				mBean.setMaterial_url("");
			}
			
			mBean.setMaterial_name(title);
			mBean.setMaterial_type(type);
			mBean.setMaterial_order(nation);
			Date now = new Date();
			Timestamp update_time = new Timestamp(now.getTime());
			mBean.setUpdate_time(update_time);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//log.error(ConfKit.getTips("error_part1")+e.toString()+ConfKit.getTips("error_part2"));
		}

		out.flush();
		out.close();
	}
}
