function myAjax(method,url,data,deal200,async) {
	var request = getRequest();
//复写onreayStatechange
	request.onreadystatechange=function(){
		//处理响应
		if(request.readyState==4){
			if(request.status==200){
				if(deal200){
					deal200(request);
				}
				
			}else if(request.status==404){
				if(deal404){
					deal404();
				}
				
			}else if(request.status==500){
				if(deal500){
					deal500();
				}   						
			}
		}
	}	
	if ("get"===method.toLowerCase()) {
		request.open(method, url+(data==null?"":"?"+data),async);
		request.send(null);
	}else if ("post"===method.toLowerCase()) {
		//post
		request.open(method, url, async);
		request.setRequestHeade("Content-Type","application/x-www-form-urlencoded");
		//post
		request.send(data);
	}
}

function getRequest() {
	//创建ajax引擎对象
	var req;
	if(window.XMLHttpRequest){
		req=new XMLHttpRequest();
	}else if(window.ActiveXObject){
		req=new ActiveXObject("Msxml2.XMLHTTP");
	}
	return req;
}