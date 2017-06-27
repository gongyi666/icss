/** 工具模块  存放不依赖DOM的通用方法 **/
define(['jquery','param'],function ($,Param) {
    'use strict';
    // -----------  变量定义开始 ----------------------------------------------------------
    var AwaitAsync,
        omitSrc,firstUpperCase,getCharLength,
        judgeType,setParamVal,
        throttle,
        debounce,
        format,
        macroCommand;

    // -----------  变量定义结束 ----------------------------------------------------------

    // -----------  Begin judgeType ----------------------------------------------------------
    /**
     * 让一个字符串首字母大写的方法
     * @author Kiva
     * @Time 2017-06-19
     * @param src:String
     * @return :String
     */
    firstUpperCase=function (src) {
        if(typeof src!=='string'){
            throw new Error('请传入一个字符串');
        }
        if(src.length<=1){
            return src.toUpperCase();
        }
        src=src.toLowerCase();
        return src.slice(0,1).toUpperCase()+src.slice(1,src.length);
    };
    // -----------  End judgeType ----------------------------------------------------------

    // -----------  Begin judgeType ----------------------------------------------------------
    /**
     * 判断类型的方法  比如 判断对象是否是String类型等
     * @author Kiva
     * @Time 2017-06-19
     * @param obj:Object
     * @return :Boolean
     */
    judgeType = function (obj, type) {
        if(typeof type!=='string'){
            throw new Error('type必须为字符串，传入的值是要判断的类型 比如 要判断是不是为数组 则传入"Array" ');
        }
        type=firstUpperCase(type);
        return Object.prototype.toString.call(obj) === '[object ' + type + ']';
    };
    // -----------  End judgeType ----------------------------------------------------------
    /**
     * 给全局变量添加参数的方法
     * @author Kiva
     * @Time 2017-06-19
     * @param key:String  key为一个字符串，可以是xx.xx.xx这种类型(如果带上.表示属性往深度遍历赋值)
     */
    setParamVal=function (key,value) {
        if(typeof key!=='string'){
            throw new Error('类型错误，key必须为字符串');
        }
        value=value?value:'';
        var key_arr=key.split('.'),
            now_obj=Param;
        $.each(key_arr,function (i,v) {
            if(!now_obj[v]){
                now_obj[v]={};
            }
            if(i===key_arr.length-1){
                now_obj[v]=value;
                return false;
            }
            now_obj=now_obj[v];
        });
        console.log(Param);
    }

    // -----------  Begin getCharLength ----------------------------------------------------------
    /**
     * 获得字符长度的方法 一个汉字算两个字符
     * @author Kiva
     * @Time 2017-06-19
     * @param src:String
     * @return :Number
     */
    getCharLength=function (src) {
        if(typeof type!=='string'){
            throw new Error('错误的数据类型,参数必须为String');
        }

        var reg=/[\u4E00-\u9FFF]+/g,
            matchs=src.match(reg),
            chinese_length=0;
        $.each(matchs,function (i,v) {
            chinese_length+=v.length;
        });
        return src.length+chinese_length;
    }

    // -----------  End getCharLength ----------------------------------------------------------

    // -----------  Begin AwaitAsync ----------------------------------------------------------
    /**
     * 使异步函数能串行的方法,new一个新的AwaitAsync对象  在构造器中传入一个回调函数组成的数组
     * 在每个异步函数的回调函数中 执行该对象的next方法
     * @author Kiva
     * @Time 2017-06-19
     * @param [arr]:Array 存放了回到函数的数组
     */
    AwaitAsync = function (arr) {
        this.callbackArr =[];
        this.count = 0;
        arr=arr||[];
        var callback_arr=this.callbackArr,callback_obj;

        if (!judgeType(arr,'Array')) {
            throw new Error('请传入一个数组进行初始化!');
        }
        this.add(arr);
    };
    /**
     * @param arr:Array 加入顺序执行队列的数组，数组参数中的的对象是函数
     */
    AwaitAsync.prototype.add=function (arr) {
        var callback_arr=this.callbackArr,callback_obj;
        $.each(arr, function (i, v) {
            if (!judgeType(v,'Function')) {
                throw new Error('数组中必须是函数!');
            }
            callback_obj = {
                'fn': v,
                'state': false,
                'arg': []
            };
            callback_arr.push(callback_obj);
        });
       // console.log(this.callbackArr);
        return this;
    };
    /**
     * @param index:Number 初始化时传入的第n个回调函数
     *        [arg]:Array 执行时要传入这个回调函数的的参数，可选,可以其中的某个函数执行多次next()，传入的参数会一直叠加
     *                    比如 next(1,参数一)  然后又 next(1,参数二)  这样该类中第二个执行的回调函数将会传入参数一和参数二
     */
    AwaitAsync.prototype.next = function (index, arg) {
        arg = arg || [];
        if (this.callbackArr[index] === undefined) {
            throw new Error('不存在此回调函数!');
        }
        if (!judgeType(arg,'Array')) {
            throw new Error('回调函数所需参数请以数组方式传入!');
        }
        this.callbackArr[index].state = true;
        this.callbackArr[index].arg=this.callbackArr[index].arg.concat(arg);
        this.count++;

        if (this.count === this.callbackArr.length) {
            $.each(this.callbackArr, function (i, v) {
                if (v.state) {
                    v.fn.apply(null, arg);
                }
            });
        }
        return this;
    };
    // -----------  End AwaitAsync ----------------------------------------------------------
    // -----------  Begin omitSrc ----------------------------------------------------------
    /**
     * 让超出的字符串变成前面12个字符加上省略号的方法
     * @author Kiva
     * @Time 2017-06-19
     * @param src:String
     * @return :String
     */
    omitSrc = function (src) {
        var omit='',
            length=0;

        if (getCharLength(src)<= 12){
            return src;
        }

        $.each(src,function (i,v) {
            if(getCharLength(v)===2){
                length+=2;
            }else{
                length++;
            }
            omit+=v;

            if(length>=12){
                return false;
            }
        });

        return omit+'...';
    };
    // -----------  End omitSrc ----------------------------------------------------------

    // -----------  Begin throttle  ----------------------------------------------------------
    /*
    * 频率控制 返回函数连续调用时，fn 执行频率限定为每多少时间执行一次
    * @param fn {function}  需要调用的函数
    * @param delay  {number}    延迟时间，单位毫秒
    * @param immediate  {bool} 给 immediate参数传递false 绑定的函数先执行，而不是delay后后执行。
    * @return {function}实际调用函数
    *   eg: window.onresize = throttle(function(){
                console.log(111)
            },1000,false,false);
    */
    throttle = function(fn, delay, immediate, debounce) {
        var curr = +new Date(),//当前事件
            last_call = 0,
            last_exec = 0,
            timer = null,
            diff,//时间差
            context,//上下文
            args,
            exec = function() {
                last_exec = curr;
                fn.apply(context, args);
            };
        return function() {
            curr = +new Date();
            context = this;
            args = arguments;
            diff = curr - (debounce ? last_call : last_exec) -delay;
            clearTimeout(timer);
            if(debounce) {
                if(immediate) {
                    timer = setTimeout(exec, delay);
                }else if(diff >= 0){
                    exec();
                }
            }else {
                if(diff >= 0) {
                    exec();
                }else if(immediate) {
                    timer = setTimeout(exec, -diff);
                }
            }
            last_call = curr; 
        }
    }
    // -----------  End throttle  ----------------------------------------------------------

    // -----------  Begin debounce ----------------------------------------------------------
    /*
    * 空闲控制 返回函数连续调用时，空闲时间必须大于或等于 delay，fn 才会执行
    * @param fn {function}  要调用的函数
    * @param delay   {number}    空闲时间
    * @param immediate  {bool} 给 immediate参数传递false 绑定的函数先执行，而不是delay后后执行。
    * @return {function}实际调用函数
    */
    
    debounce = function(fn, delay, immediate) {
        return throttle(fn, delay, immediate, true);
    };

    // -----------  End debounce ----------------------------------------------------------

    // -----------  Begin Date Format ----------------------------------------------------------
    /**
     * 时间转换函数， 可以将时间戳或者new Date()转换为 指定格式时间。
     * @param {*可以传入 new Date()/new Date(xxx)/时间戳} dates 
     * @param {*转换的格式 比如：yy-MM--dd hh:mm:ss} fmt 
     */ 
     format = function(dates, fmt) {
 		var date = typeof dates == "object" ? new Date() : new Date(dates);
        if(date.constructor === Date) {
            var o = {
		        "M+": date.getMonth() + 1, //月份 
		        "d+": date.getDate(), //日 
		        "h+": date.getHours(), //小时 
		        "m+": date.getMinutes(), //分 
		        "s+": date.getSeconds(), //秒 
		        "q+": Math.floor((date.getMonth() + 3) / 3), //季度 
		        "S": date.getMilliseconds() //毫秒 
    		};
            if(/(y+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var k in o){
    			if (new RegExp("(" + k + ")").test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            } 
        }
        return fmt;
    }

    // -----------  End debounce ----------------------------------------------------------------

    // -----------  Begin macroCommand ----------------------------------------------------------
    /**
     *  添加指定的一系列任务，然后将任务依次执行，必需同步的任务
     */ 
    macroCommand = function() {
        return {
            tasksList: [],
            // 添加任务
            add: function(fn) {
                if(typeof fn === "function" || (Object.prototype.toString.call(fn) === "[object Object]" && fn.execute)) {
                    this.tasksList.push(fn);
                }else {
                    throw new Error("必须传入函数或带excute方法的对象!");
                }
                return this;
            },
            // 删除已添加任务
            remove: function(fn) {
                for(var i = 0, len = this.tasksList.length; i < len; i++) {
                    if(fn === this.tasksList[i]) {
                        this.tasksList.splice(i, 1);
                    }
                }
                return this;
            },
            // 依次执行已添加任务
            execute: function() {
                for(var i = 0, len = this.tasksList.length; i < len; i++) {
                    if(Object.prototype.toString.call(this.tasksList[i]) === "[object Object]") {
                        this.tasksList[i].execute();
                    }else  {
                        this.tasksList[i]();
                    }
                }
                return this;
            }
        }
    }
    // -----------  End macroCommand ----------------------------------------------------------

    return {
        'AwaitAsync': AwaitAsync,// 使异步函数的回调能串行的类
        'omitSrc': omitSrc, // 让超出的字符串后面加上省略号的方法
        'judgeType':judgeType, // 判断类型的方法  比如 判断对象是否是String类型等
        'firstUpperCase':firstUpperCase, //让一个字符串首字母大写的函数
        'getCharLength':getCharLength,//获得字符长度的方法  一个汉字会算2个字符
        'setParamVal':setParamVal,// 给全局Param数据存放对象添加参数
        'throttle': throttle, // 节流函数
        'debounce': debounce, // 防抖函数，在高频操作下可以调用
        'format': format, // 时间转换函数， 1. 时间戳---> 指定格式  2. new Date(xxx)---> 指定格式
        'macroCommand': macroCommand,//添加指定的一系列任务，然后将任务依次执行，必需同步的任务
    }
});
