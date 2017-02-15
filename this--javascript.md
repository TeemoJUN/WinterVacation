# this
在javascript中this的理解

## 默认
```javascript
var a=1;
function foo(){
    console.log(this.a);
}
foo()//1
```
## 隐失
```javascript
var a=1;
function foo(){
    console.log(this.a);
}

var obj={
    a:2,
    foo:foo
};

obj.foo();//2
```
## 显示
* fn.apply(作用域,参数)
* fn.call(作用域，参数)
作用域可以是null，undefined；时就相当于传入了window
fun.apply(thisArg[, argsArray])//
fun.call(thisArg[, arg1[, arg2[, ...]]])
两个传参方式不一样

## new

因为new相当于创建新空间，所以this就指向它的那个空间

## 箭头函数
ES6


----------
```javascript

var count=0;

function foo(){
    this.count++;
}

foo.count=0;
foo();

console.log(foo.count);//0
console.log(count);//1
```
这里你会发现foo.count===0;count==1
虽然foo下面有count属性；foo是在外面（window）调用的，所以this指向的是window，所以在只改变啦window.count;



```javascript
var a=2
function baz(){
    foo();
}
baz.a=1;
function foo(){
    console.log(this.a);
}
baz();//2
```

这个怎么解释呢？？
foo()不是在baz（）中调用的啊；那为什么this不是指向baz（）对象呢？
其实一直困扰啦好久

```javascript
//相当于
var a=2
function baz(){
    function foo(){
    console.log(this.a);
}
    foo();
}
baz.a=1;
baz();//2
```
函数本身是没有this的，除非new它，否则，this将一直上传
*函数baz里面执行啦foo，但foo不是baz调用的*
----------
***this只与调用对象有关***谁调指谁





