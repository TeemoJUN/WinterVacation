/*在看就《你不知道的javascript中卷》，看第二部分，了解异步。

要控制   race condition（竟态条件）
启用    gate（门）  latch（门闩）

在异步的时候要注意竟太bug


永远异步调用回调，即使就在事件循环的下一轮，这样所有的回调就都是可以预测的啦*/
//在你不知道javascript中卷P176
function asyncify(fn){
  
  var orig_fn=fn,intv=setTimeout(function(){
    intv=null;
    if(fn){
      fn();
    }
  },0);
  fn=null;

  return function(){
    //触发太快，在定时器intv之前指示异步转换发生之前？
    if(intv){
      //把封装的this添加到bind调用参数中
      //以及克里化（curring）所传入参数
      fn=orig_fn.bind.apply(orig_fn,[this].concat([].slice(arguments))]);
    }
    //已经是异步
    else{
      //调用原来的函数
      orig_fn.apply(this,arguments);
    }
  }
}

function result(){
  console.log(a);
}
var a=0;
ajax("",asyncify(result));
a++;




