package MyCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Phantomjs_test {
	public static String getContent(String url) throws IOException{
		 Runtime rt = Runtime.getRuntime();
		 Process p = rt.exec("phantomjs.exe F:/examples/test_three.js "+url);
		 InputStream a=p.getInputStream();
		 
		 BufferedReader br = new BufferedReader(new InputStreamReader(a,"UTF-8"));     
	        StringBuffer sbf = new StringBuffer();     
	        String tmp = "";     
	        while((tmp = br.readLine())!=null){     
	            sbf.append(tmp);
	            sbf.append("\n");
	        }    
	        return sbf.toString();     
	}
	
	public static void main(String[] args) throws IOException{
		String URL="https://www.douban.com";
		String content=getContent(URL);
		System.out.println(content);
	}
}




















