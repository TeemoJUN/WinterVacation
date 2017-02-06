# 关于Phantomjs


```javascript
//.......这是一个沙箱........
var title = page.evaluate(function(s) {
    return document.querySelector(s).innerText;
  }, 'title');
```
+ 在这**沙箱**里就是一个看不见得浏览器
+ 里面可以像操作普通页面一样操作它
用时记得查阅api
