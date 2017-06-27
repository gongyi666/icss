/* 
//日期组件
* define的参数为匿名函数，该匿名函数返回一个对象
* []中引入模块，回调函数中引用模块使用名
*/
define(["jquery"],function($){
    // 下拉框构造器

    var DropList = function(obj) {
        this.dropItems = obj.dropItems;
    }
    var isAddClick = false;//标示 是否第一次添加 点击事件

    DropList.prototype = {
        constructor: DropList,
        //获取 下拉列表的字符串
        getHtml: function() {
            var dropItems = this.dropItems,
                dropHtml="";
            for (var i = 0, dropItems; dropItem = dropItems[i++];) {
                dropHtml += '<li><a>'+ dropItem +'</a></li>';
            }

            var html = '<div class="select box-r-s w-70px">'+
                            '<input type="text" data-param-code="" name="" readonly value="">'+
                            '<div class="down-arrow">'+
                                '<i class="iconfont icon-triangle-down-copy"></i>'+
                            '</div>'+
                            '<ul class="box-r-s">'+
                                dropHtml
                            '</ul>'+
                        '</div>';
            return html;
        },
        // 将 字符串插入 指定的元素位置
        append: function($el) {
            var html = this.getHtml();
            $el.append(html);
            return this;
        },
        // 添加 input 中的点击事件
        addClick: function() {
            var clickfn = function(e) {
                var $this = $(this);
                if(e.target.nodeName === "INPUT") {
                    if($this.find(".box-r-s:visible").length === 0){
                        $this.find(".box-r-s").slideDown();
                    }else {
                        $this.find(".box-r-s").slideUp();
                    }
                }
            };
            // 处理 
            if(!isAddClick) {
                isAddClick = true;
                $("body").on("click",".select", clickfn);
            }
            return this;
        },
        addInputText: function() {
            return this;
        }
    }
    new DropList({dropItems:[1,2,3,4]}).append($("body")).addClick();
    new DropList({dropItems:[1,2,3,4]}).append($("body")).addClick();
    return {
        DropList: DropList
    }
})