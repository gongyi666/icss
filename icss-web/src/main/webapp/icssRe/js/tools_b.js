/**
 * Created by Kiva on 17/6/19.
 */
/** 浏览器工具模块  存放依赖于浏览器实现的通用方法 **/
define(['jquery','tools'],function ($,tools) {
    'use strict';
    // -----------  变量定义开始 ----------------------------------------------------------
    var setAnimateState,isAnimating,
        parseUrl,
        getDistance,autoScrollY,
        ajaxPostSync,ajaxPost,ajaxGetSync,ajaxGet,
        addFocus;

    // -----------  变量定义结束 ----------------------------------------------------------

    // -----------  Begin setAnimateState ----------------------------------------------------------
    /**
     * 设置动画冲突状态，当动画正在执行过程中，设置此状态为true,记住动画执行完毕在回调函数中置为false
     * @author Kiva
     * @Time 2017-06-19
     * @param $node:jQuery  记录动画状态的一个jQ对象
     *        state:Boolean 动画状态 传入true或者false
     */
    setAnimateState=function ($node,state) {
        $node.data('isAnimating',!!state);
    };
    // -----------  End setAnimateState ----------------------------------------------------------
    // -----------  Begin isAnimating ----------------------------------------------------------
    /**
     * 得到某个节点是否依旧在执行动画的函数，如果未设置动画执行状态，则返回false
     * @author Kiva
     * @Time 2017-06-19
     * @param $node:jQuery 记录动画状态的一个jQ对象
     * @return :Boolean 返回true或者false(可能是undefined或是null)
     */
    isAnimating=function ($node) {
        return !!$node.data('isAnimating');
    };
    // -----------  End isAnimating ----------------------------------------------------------

    // -----------  Begin parseUrl ----------------------------------------------------------
    /**
     * 解析url参数的方法
     * @author Kiva
     * @Time 2017-06-19
     * @return :Object 返回一个解析了url对应参数的key和value的对象
     */
    parseUrl=function () {
        var r = window.location.search.substr(1).split("&"),
            obj = {};
        $.each(r, function (i,v) {
            var arr = v.split("=");
            obj[arr[0]] = arr[1];
        });
        return obj;
    };
    // -----------  End parseUrl ----------------------------------------------------------

    // -----------  Begin ajax ----------------------------------------------------------
    /**
     * 验证ajax参数是否合法,私有函数
     * @author Kiva
     * @Time 2017-06-19
     * @return :Array 合法就返回一个带着ajax所需参数的数组
     */
    function validateAjax(url,data,success,error) {
        var _data,_success,_error;
        if(arguments.length<1 || !(typeof arguments[0]==='string')){
            throw new Error('第一个参数必须是url地址！类型为字符串类型');
        }

        if(arguments.length===1){
            _data={};
            _success=function () {};
            _error=function () {};
            return [_data,_success,_error];
        }

        if(tools.judgeType(arguments[1],'Object')){
            _data=data;
            _success=success||function () {};
            _error=error||function () {};
        }else if(tools.judgeType(arguments[1],'Function')){
            _data={};
            _success=arguments[1];
            _error=arguments[2]||function () {};
        }else{
            throw new Error('不支持的参数类型');
        }
        return [_data,_success,_error];
    }

    /**
     * 封装的ajax方法  Sync结尾的为同步方法，尽量不要使用同步的ajax
     * @author Kiva
     * @Time 2017-06-19
     * @param url:String 请求地址
     *        [data]:Object 请求参数
     *        [success]:Function 请求成功后的回调函数
     *        [error]:Function 请求失败后的回调函数
     * @return :Object 只有同步的方法才会有返回，返回的是自己传入的回调函数的返回值
     */
    ajaxGet=function (url,data,success,error) {
        var validate=validateAjax(url,data,success,error);
        $.ajax({
            url: url,
            async: true,
            data: validate[0],
            dataType: "json",
            type: "get",
            success:function (data) {
                validate[1](data);
            },
            error:function (req, info, err) {
                validate[2](req, info, err);
            }
        });
    };
    ajaxPost=function (url,data,success,error) {
        var validate=validateAjax(url,data,success,error);

        $.ajax({
            url: url,
            async: true,
            data: validate[0],
            dataType: "json",
            type: "post",
            success:function (data) {
                validate[1](data);
            },
            error:function (req, info, err) {
                validate[2](req, info, err);
            }
        });
    };
    ajaxGetSync=function (url,data,success,error) {
        var validate=validateAjax(url,data,success,error);

        $.ajax({
            url: url,
            async: false,
            data: validate[0],
            dataType: "json",
            type: "get",
            success:function (data) {
                return validate[1](data);
            },
            error:function (req, info, err) {
                return validate[2](req, info, err);
            }
        });
    };
    ajaxPostSync=function (url,data,success,error) {
        var validate=validateAjax(url,data,success,error);
        $.ajax({
            url: url,
            async: false,
            data: validate[0],
            dataType: "json",
            type: "post",
            success:function (data) {
                return validate[1](data);
            },
            error:function (req, info, err) {
                return validate[2](req, info, err);
            }
        });
    };
    // -----------  End ajax ----------------------------------------------------------

    // -----------  Begin addFocus ----------------------------------------------------------
    /**
     * 为该jQuery对象添加focus类同时移除它兄弟节点的focus类
     * @author Kiva
     * @Time 2017-06-19
     * @param $node:jQuery  jQuery数组
     */
    addFocus=function ($node) {
        $node.addClass('focus').siblings().removeClass('focus');
    }
    // -----------  End addFocus ----------------------------------------------------------

    // -----------  Begin getDistance ----------------------------------------------------------
    /**
     * 得到一组jQ数组中节点的高度和
     * @author Kiva
     * @Time 2017-06-19
     * @param $nodes:Array  jQuery数组
     *        method:String 得到距离的jQuery方法  比如 outerWidth
     *        arg1:传入method这个方法的参数
     *        arg2:传入method这个方法的参数
     *        arg...:传入method这个方法的参数
     * @return :Number
     */
    getDistance=function ($nodes,method) {
        var distance = 0,
            arg=Array.prototype.slice.apply(arguments,[2,arguments.length]);
        $nodes.each(function () {
            var $this=$(this);
            distance += $this[method].apply($this,arg);
        });
        return distance;
    }
    // -----------  End getDistance ----------------------------------------------------------

    // -----------  Begin autoScrollY ----------------------------------------------------------
    /**
     * 使选中的节点能自动滚动到适当位置的函数
     * @author Kiva
     * @Time 2017-06-19
     * @param $scroll_node:jQuery 滚动的jQuery对象   比如body
     *        $context:jQuery 装着$move_node的环境对象  比如#container
     *        $move_node:jQuery 以这个为标准进行移动的对象  比如.group
     *        top_offset:Number 滚动至距离$scroll_node顶部负top_offset的距离停止，传入负数
     */
    autoScrollY=function ($scroll_node,$context, $move_node, top_offset) {
        var context_height, move_distance, children_height, move_offset;
        $context = $context;
        top_offset = (top_offset + 70) || 0;
        context_height = $context.outerHeight(true);
        move_offset = $move_node.offset();
        move_distance = move_offset.top + top_offset - $scroll_node.scrollTop();
        children_height = getDistance($context.children(),'outerHeight',true);

        if ($scroll_node.scrollTop() + $scroll_node.outerHeight(true) + move_distance >= context_height) {
            $context.css("height", $scroll_node.scrollTop() + $scroll_node.outerHeight(true) + move_distance);
            $scroll_node.animate({
                "scrollTop": move_offset.top + top_offset
            }, "slow", "swing");
            // console.log("111");
        } else if ($scroll_node.scrollTop() + $scroll_node.outerHeight(true) + move_distance > children_height) {
            $scroll_node.animate({
                "scrollTop": move_offset.top + top_offset
            }, "slow", "swing", function () {
                $context.css("height", $scroll_node.scrollTop() + $scroll_node.outerHeight(true));
            });
            // console.log("222");
        } else {
            $scroll_node.animate({
                "scrollTop": move_offset.top + top_offset
            }, "slow", "swing", function () {
                $context.css("height", "auto");
            });
        }
    }
    // -----------  End autoScrollY ----------------------------------------------------------


    return {
        'setAnimateState':setAnimateState,//设置动画冲突状态，当动画正在执行过程中，设置此状态为true,记住动画执行完毕在回调函数中置为false
        'isAnimating':isAnimating,//得到某个节点是否依旧在执行动画的函数，如果未设置动画执行状态，则返回false
        "parseUrl":parseUrl,//解析url参数的方法
        'addFocus':addFocus,//为该jQuery对象添加focus类同时移除它兄弟节点的focus类
        'getDistance':getDistance,//得到一组jQ数组中节点的高度或宽度和
        'autoScrollY':autoScrollY,//使选中的节点能自动滚动到适当位置的函数
        'ajaxPost':ajaxPost,//封装的ajax方法  Sync结尾的为同步方法，尽量不要使用同步的ajax
        'ajaxGet':ajaxGet,
        'ajaxPostSync':ajaxPostSync,
        'ajaxGetSync':ajaxGetSync
    }
});