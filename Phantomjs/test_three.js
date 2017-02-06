"use strict";

var page=require("webpage").create();
var system=require("system");

var url=system.args[1];

page.open(url,function(status){
	if(status!=="success"){
		console.log("FAIL.......");
	}
	else{
		console.log("Start:")
		var result=page.evaluate(function(){
			var a=document.getElementsByTagName("a");
			var b="";
            for(var i=0;i<a.length;i++){
                b+=a[i].getAttribute("href")+"\n";
            }
           /* b=b+"以下的是img：...."+"\n";
            var img=document.getElementsByTagName("img");
            for(var i=0;i<img.length;i++){
            	b+=img[i].getAttribute("src")+"\n";
            }*/
            return b;
		});
		console.log(result);
	}
	phantom.exit();
});

