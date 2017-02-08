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

## 这都是一些常见的
