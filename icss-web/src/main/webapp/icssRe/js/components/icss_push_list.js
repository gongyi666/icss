/** 推送条模块 **/
/*
* define的参数为匿名函数，该匿名函数返回一个对象
*/

define(['jquery','art_tmpl','tools_b'],function($,art_tmpl,tools_b){
    'use strict';
	//----------- 变量定义开始 -----------------------------
    var updateList,symptomHover,
        wrapMove,
        keyDown;
    //----------- 变量定义结束 -----------------------------

    //----------- DOM方法开始 ------------------------------------
    /**
     * 更新推送条的方法
     * @author Kiva
     * @Time 2017-06-19
     * @param $wrap:jQuery 推送条容器的jQuery对象
     *        data:JSON 要更新的数据
     */
    updateList=function ($wrap,data) {
        if(!data || data.length<=0){
            $wrap.empty();
            return;
        }
        var html=art_tmpl.template('push-list-tmpl',data);
        $wrap.html(html);
        $wrap.children(':first').addClass('focus');
    };
    //----------- DOM方法结束 ------------------------------------

	//----------- 事件处理开始 -------------------------------------
    /**
     * 鼠标移动到推送条对象症状上时 显示蓝色焦点
     * @author Kiva
     * @Time 2017-06-19
     * @param $symptom:jQuery 推送条容器的jQuery对象
     */
    symptomHover=function ($symptom) {
        tools_b.addFocus($symptom);
    };

    wrapMove=function ($wrap,num) {

    };

    //----------- 事件处理结束 -------------------------------------

    //----------- 公共方法开始 -------------------------------------


    //----------- 公共方法结束 -------------------------------------

    return {
	    'updateList': updateList
	};
});
