package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadServlet extends HttpServlet {
	private String filePath;
	private String tempFilePath;
	 private ServletContext sc;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter outNet=response.getWriter();
		
		
		try{
			DiskFileItemFactory factory=new DiskFileItemFactory();
			
			factory.setSizeThreshold(4*1024);
			
			factory.setRepository(new File(tempFilePath));
			
			ServletFileUpload upload=new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");//防止中文乱码		
			upload.setSizeMax(4*1024*1024);
			
			List items=upload.parseRequest(request);
			
			Iterator iter=items.iterator();
			
			while(iter.hasNext()){
				FileItem item=(FileItem) iter.next();
				
				if(item.isFormField()){
					processFormField(item,outNet);
				}
				else{
					processUploadedFile(item,outNet);
				}
			}
			outNet.close();
			
			
		}
		catch(Exception e){
			throw new ServletException(e);
		}
		
	}
	
	
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		
		filePath=config.getInitParameter("filePath");
		
		tempFilePath=config.getInitParameter("tempFilePath");
		
		filePath=getServletContext().getRealPath(filePath);
		
		
		tempFilePath=getServletContext().getRealPath(tempFilePath);
		
	}
	
	private void processFormField(FileItem item,PrintWriter outNet){
		String name=item.getFieldName();
		String value=item.getString();
		outNet.println(name+" £º "+value+"\r\n");
	}
	
	private void processUploadedFile(FileItem item,PrintWriter outNet) throws Exception{
		String filename=item.getName();
		
		int index=filename.lastIndexOf("\\");
		filename=filename.substring(index+1, filename.length());
		
		long fileSize=item.getSize();
		
		if(filename.equals("")&&fileSize==0){
			return;
		}
		File uploadedFile=new File(filePath+"/"+filename);
		
		item.write(uploadedFile);
		
		outNet.println(filename+" is saved");
		
		outNet.println("The size of "+filename+" is "+fileSize+"\r\n");
		
	}

}
