/** 现病史 **/
/*
* define的参数为匿名函数，该匿名函数返回一个对象
* []中引入模块，回调函数中引用模块使用名
*/

define(["jquery", "jquery.blueTip"],function($,blueTip){
	// 下面就可以直接使用 $ 和 blueTip插件
	
	//在此处返回方法属性即可
    return {
	    key: 'common'
	};
});
