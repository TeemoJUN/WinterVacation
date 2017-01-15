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
/*
	1. 启动阶段：加载Web应用的相关数据，创建ServletContext对象，对Filter和Servlet进行初始化
	2. 运行阶段：为客户端提供服务；
	3. 终止阶段：释放Web应用所占用的各种资源

在启动阶段：

	* 把web.xml文件中数据加载到内存中。
	* 为javaweb应用创建第一个ServlerContext对象。
	* 把所有的Filter进行初始化。
	* 对那些因在启动时初始化的Servlet进行初始化。

在运行阶段：

* 他的所有Servlet都处于待命状态，随时可以响应客户端的特定请求，提供相应的服务。假如客户端的相应的Servlet不存在，Servlet会初始化Servlet。然后调用他的service()方法；

在终止阶段：

	* 销毁Servlet
	* 销毁Filter
	* 销毁ServlteContext等javaweb相关对象

*/

