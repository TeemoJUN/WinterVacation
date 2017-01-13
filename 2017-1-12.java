继续学生信息管理系统，将各个层次剥离。成功搞定了信息信息管理和成绩管理模块，发现了一些东西：
1.  就是对每次数据库中每张表对应一个bean
2   返回来的一些数据用bean接收
3   用单例加同步的思想创建一个业务对象UserManager.java使其可以高并发，创建一个访问数据库DB.java,
  创建一些对应bean的访问发访问
4   对象其中业务对象和和访问数据库的层次用接口来进行调用.降低耦合性。
感想：
1  不停的重启tomcat，control+Alt+R，和F12，F5来找bug，要不停的在层次中设置System.out.println(".......进入....")来找寻是否进入了执行了。
发现追踪bug才是开发中的重要内容。然后对应的查看http权威指南中的返回200-505之间的错误。学到了很多
2 当然有一个缺陷就我将两张bean建立到一起去啦，当然问题是没有，但这不符合规范。这是极不好的，代码是写给人看的，所以命名必须规范.........下次注意啦！！！

//单例加同步
public class UserManager {
	  static private UserManager instance=null;
    private UserManager(){}
    static public UserManager getInstance()
    {
	    if(instance==null){
		      instance=new UserManager(); 
		  }
      return instance;
     }
}





