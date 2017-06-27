/*同步的ajax，常用的,调用这个函数获得的返回对象，判断errorCode不存在，说明ajax执行成功，否则判断errorCode==islost
则弹出系统返回错误信息，errorCode==iserror，弹出自编辑的错误信息*/
jQuery.support.cors = true;
function ajaxPost(uri, parameters,callback) {
	var jsons = {};
	var iserror = 0;
	var msg = "";
	callback = callback || function () {};
	$.ajax({
		async : false,
		type : "POST",
		dataType : "json",
		url : uri,
		data : parameters,
		success : function(data, textStatus) {
			jsons = data;
			if (data.ret != 0) {
				iserror = 2;
				msg = data.msg;
			}
			callback(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest);
			alert(textStatus);
			alert(errorThrown);
			iserror = 1;
		}
	});

	if (iserror == 0) {
		return jsons;
	} else if (iserror == 1) {
		return {
			"errorCode" : "isERROR"
		};
	} else if (iserror == 2) {
		return {
			"errorCode" : "isLost",
			"errorMsg" : msg
		};
	}
}
function ajaxPostAsync(uri, parameters,callback) {
    var jsons = {};
    var iserror = 0;
    var msg = "";
    callback = callback || function () {};
    $.ajax({
        async : true,
        type : "POST",
        dataType : "json",
        url : uri,
        data : parameters,
        success : function(data, textStatus) {
            jsons = data;
            if (data.ret != 0) {
                iserror = 2;
                msg = data.msg;
            }
            callback(data);
        },
        error : function() {
            iserror = 1;
        }
    });

    if (iserror == 0) {
        return jsons;
    } else if (iserror == 1) {
        return {
            "errorCode" : "isERROR"
        };
    } else if (iserror == 2) {
        return {
            "errorCode" : "isLost",
            "errorMsg" : msg
        };
    }
}
function ajaxGet(uri, parameters,callback) {
	var jsons = {};
	var iserror = 0;
	var msg = "";
	callback = callback || function () {};
	$.ajax({
		async : false,
		  type: 'get',
		dataType : "json",
		//url : uri+"?"+parameters,
		url : uri,
		data : parameters,
		success : function(data, textStatus) {
			jsons = data;
			if (data.ret != 0) {
				iserror = 2;
				msg = data.msg;
			}
			callback(data);
		},
		error : function() {
			iserror = 1;
		}
	});

	if (iserror == 0) {
		return jsons;
	} else if (iserror == 1) {
		return {
			"errorCode" : "isERROR"
		};
	} else if (iserror == 2) {
		return {
			"errorCode" : "isLost",
			"errorMsg" : msg
		};
	}
}
/*获取鼠标位置*/
function getMousePosition(e){
    var x = e.pageX
    var y = e.pageY
    return [x,y];
}

/*计算年龄，*/
function figureAge(now,birth){
	var t=new Date(now);
	var i=new Date(birth);
	var year=t.getYear()-i.getYear();
	return year;
}

/*在parameters里面有属性为对象时，调用这个ajax请求方法，在调用方法里需要将参数用JSON.stringify(data)转换*/
function ajaxSetContentType(uri,parameters){
    var jsons={};
	var iserror=0;
	var msg = "";
	$.ajax({
			async: false,
			type: "POST",
      		contentType:"application/json",
			url: uri,
			data: parameters,
			success: function(data, textStatus) {
				jsons=data;
		        if(data.ret!=0){
		          msg = data.msg;
		          iserror=1;
		        }
			},
			error: function(){
		        iserror=1;
			}
	});
	if(iserror==0){
		return jsons;
	}else if (iserror==1) {
		return {
			"errorCode" : "isERROR",
			"errorMsg" : msg
		};
	}else if (iserror == 2) {
		return {
			"errorCode" : "isLost",
			"errorMsg" : msg
		};
	}
}

/*json转string*/
function jsonTOstring(jsonobj){
	var jsonstr=JSON.stringify(jsonobj);
	return jsonstr;
}
/*string转json*/
function stringTOjson(jsonstr){
    var jsonobj=JSON.parse(jsonstr);
    return jsonobj;
}
/*array转json*/
function arrayTOjson(jsonarr){
    var jsonobj=JSON.parse(jsonstr);
    return jsonobj;
}
//将时间戳转换成2000-00-00类型
function getCommonDate(date){
    var t="";
	var d=new Date(date);
	t=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
	return t;
}
/*获取地址栏参数，并返回一个参数的对象*/
function getRequest() {
	var url = location.search;
	var r = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for(var i = 0; i < strs.length; i ++) {
		    r[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
	    }
	}
	return r;
}

/*判断浏览器类型*/
function myBrowser(){
    var userAgent = window.navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
    var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
	var isChrome = userAgent.indexOf("Chrome") > -1; //判断是否Chrome浏览器

    if (isIE) {
        var IE8 = IE9 = IE10 = IE11 = false;
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        IE8 = fIEVersion == 8.0;
		IE9 = fIEVersion == 9.0;
		IE10 = fIEVersion == 10.0;
		IE11 = fIEVersion == 11.0;
        if (IE8) {
            return "IE8";
        }
				if (IE9) {
            return "IE9";
        }
				if (IE10) {
            return "IE10";
        }
				if (IE11) {
            return "IE11";
        }
    }//isIE end
    if (isFF) {
        return "FF";
    }
    if (isOpera) {
        return "Opera";
    }
	if (isChrome) {
		return "Chrome"
	}
}

//根据ID使元素居中,
function palceModalMiddle(id){
	var winHeight = $(window).height();
	var winWidth = $(window).width();
	var divHeight = $("#"+id).height();
	var divWidth = $("#"+id).width();
	$("#"+id).css("top",(winHeight-divHeight)/2+"px");
	$("#"+id).css("left",(winWidth-divWidth)/2+"px");
}


//少用，根据年月获取当月天数
function getDateOwn(y,m){
	if([1,3,5,7,8,10,12].indexOf(m)>=0){
		return 31;
	}
	if([4,6,9,11].indexOf(m)>=0){
		return 30;
	}
	if(m==2&&y%4==0){
		return 29
	}else if(m==2&&y%4!=0){
		return 28
	}
	return 30;
}

function autoInformationHeight(){
	var t=0;//诊断依据内容的高度
	var h=40;//上下的留空距离
	var winHeight = $(window).height();
	$(".information-main").height((winHeight-40-h*2-t));
}
