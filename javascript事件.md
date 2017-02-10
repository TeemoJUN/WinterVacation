# 事件处理


## DOM事件流
+ 捕获
+ 冒泡

## DOM0级

```javascript
var btn=document.getElementById("mybtn");
btn.onclick=function(){
    //.......
}
```
## DOM2级
+ addEventListener("事件名"，"处理事件函数","是否冒泡")
+ removeEventListener("事件名"，"处理事件函数","是否冒泡")



```javascript
var btn=document.getElementById("mybtn");
var handler=function(){
    //........
}
btn.addEventListener("click",handler,false);
btn.removeEventListener("click",handler,false)
```


## 事件对象

**currentTarget,target指向当前时间对象**
```javascript
var btn=document.getElementById("mybtn");
btn.onclick=function(event){
    event.currentTarget===this;//true
    event.target===this;//true
}
//this指向的就是当前点击的对象；
```

**preventDefault()取消默认行为**
```javascript
    var link=document.getElementById("myLink");
    link.onclik=function(event){
        event.preventDefault();
    };
```

**stopPropagation()阻止是事件上传**
因为事件传播Document-->body--input:捕获
input-->body-->body-->document:冒泡
```HTML
<!DOCTYPE html>
<html>
<head>
    <title>DOM Event Object Example</title>
</head>
<body>
    <input type="button" value="Click Me" id="myBtn" />
    <script type="text/javascript">
        var btn = document.getElementById("myBtn");
        btn.onclick = function(event){
            alert("Clicked");
            //event.stopPropagation();
        };
        
        document.body.onclick = function(event){
            alert("Body clicked");
        };
        //先弹Clicked再Body clicked
    </script>
</body>
</html>
```

## 事件类型

* load页面完全加载完在window上触发。img,object元素也有可触发
* unload同理
* abort

----------


## IE 事件(IE8之前只支持事件冒泡)

+ attachEvent("事件名"，"函数");
+ detachEvent("事件名"，"函数");
 
**在IE中事件处理会在全局运行，所以this===window**
```javascript
var btn=document.getElementById("mybtn");
var handler=function(){
    //........
}
btn.attachEvent("onclick",handler);
btn.detachEvent("onclick",handler);
```
    
**IE的event对象是window对象的一个属性，所以var event=window.event**
```javascript
var btn=document.getElementById("mybtn");
btn.onclick=function(){
    var event=window.event;
    event.type//click
}
```
**returnValue属性相当于preventDefault();window.event.returnValue==false;来取消默认行为**
**cancelBubble属性相当于stopPropgation();取消冒泡**

## 模拟事件

crateEvent();

 1. type 为要触发的事件类型
 2. bubbles 是否冒泡
 3. cancelable 是否可以取消
 4. view 与事件关联的视图 document.defaultView
 5. detail 事件有关的详细信息
 6. screenX 相对屏幕的X坐标
 7. screenY 同上
 8. clientX 相对视口的X坐标
 9. clientY 同上
 10. ctrlKey 表示是否按下Ctrl 默认false
 11. altKey 同上
 12. shift 同上
 13. metaKey 同上
 14. button 默认0
 15. relatedTarget 表示与实践相关的对象 在mouseover和mouseout时使用

```javascript
//创建事件
var event = document.createEvent("MouseEvents");
 //初始化事件
 event.initMouseEvent(上面的参数);
 //触发事件
 btn.dispatchEvent(event);
```
## 这都是一些常见的







