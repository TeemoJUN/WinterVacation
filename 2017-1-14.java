//1:Tomcat启动时先创建Listener--->Application--->Config--->Filter--->Servlet;
//2 javaWeb应用的生命周期由servlet容器来控制的，而servlet作为javaWeb应用的最核心的组件，其生命周期也是由servlet容器来控制的。
//  TomCat------>Servlet容器。
/* 当请求来的时候先调用相应的servlet，调用其service()方法。*/

//service（）方法被在GenericServlet.java中被抽象定义。

public abstract class GenericServlet implements Servlet,ServletConfig,java.io.Serializable{
     //......................其它方法
     public abstract void service(ServletRequest req,ServletResponse res) throws ServletException,IOException;
}

//在HttpServlet.java中被实现。
public abstract class HttpServlet extends GenericServlet implements java.io.Serializable{
     private static final String METHOD_GET="GET";
     private static final String METHOD_POST="POST";
     //......其它定义
     public void service（ServletRequest req,ServletResponse res）throws ServletException,IOExecption{
          HttpServletRequest request;
          HttpServletResponse response;
          try{
               request=(HttpServletRequest) req;
               reponse=(HttpServletResponse) res;
          }
          service(reuest,response);
     }
     public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IoException{
          String method=req.getMethod();
          if(method.equals(METHOD_GET)){          
               long lastModified=getLastModified(req);
               if(lastModified==-1){
                   doGet(req,res); 
               }
               else{
                    long ifModifiedSince=req.getDataHeader(HEADER_IFMODSINCE);
                    if(ifModifiedSince<(lastModified/1000*1000)){
                         maybeSetlastModified(res,lastModified);     
                         doGet(req,res); 
                    }else{
                        res.setStatus(HttpServletResponse.SC_NOT_MODIFIED); 
                    }  
               }
          }
          else if(mothod.equals(METHOD_POST)){
               doPost(req,res);
          }
          else if(){
              //其它请求方法；
          }
     }
}

/* 可以看到当请求来时它会先调用service（ServletRequset req,ServletResponse res）方法，然后再HttpServlet.中将其处理转发成service(HttpServletRequest req,HttpSetvletResponse res)，然后再判断它的请求方式，调用相应的do方法，如doGet（），doPost();
* Jsp在第一次被访问时被翻译成Servlet源文件,被编译成Servlet类然后被，放置在<CATALINA_HOME>/work中。
* Jsp本质就是Servlet。
*/







