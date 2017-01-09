//利用监听用户的关闭页面触发window.onunload来向服务器发送最后一次请求，来注销sessionID 发现其压根没有用，只有IE有用
<script type="text/javascript">
	window.onunload= unloadPage;
		function unloadPage()
		{
			location.href="http://127.0.0.1:8080/Ajax/SimpleServlet";
		}
 </script>
 
 
 
 //第二个练习
 //同学问的问题
 //本来应该是undefined的，但之所以是空串是因为在window下有name属性
//折腾啦老半天，因为在它丫的还能输出qwe，估计就是执行环境的影响
 (function(){
    var name="qwe";
    window.Person = function(value) {
	    //不推荐这样写.
        name = value;
    }
    window.Person.prototype.getName = function() {
        return name;
    }
    window.Person.prototype.setName = function(value) {
        name = value;
    }
})();
new Person("qwe");
console.log(name);//空串;
new Person();
console.log(name);//空串;
 
 
 
