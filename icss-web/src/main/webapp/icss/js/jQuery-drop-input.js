/**
 * Created by Kiva on 17/3/1.
 */

(function ($) {

    $.fn.extend({
        dropInput:function(callback) {
            dropInput(this,callback);
            return this;
        }
    });


    function dropInput($div,callback) {
        var $group=$div.parent(),
            html,
            $drop_container,
            $textarea,
            $drop_arrow,
            $drop_center;

        html='<div class="drop-input-container" data-drop="false" style="display:none"><div class="drop-input-center"><i class="iconfont icon-xiangxia"></i></div><textarea style="display: none" placeholder="请自由输入..."></textarea></div>';

        $drop_container=$group.append(html).children("div.drop-input-container");
        $textarea=$drop_container.find("textarea");
        $drop_center=$drop_container.children("div.drop-input-center");
        $drop_arrow=$drop_container.find("i");
        dropLocationAndSize();

        $group.on("mouseenter.drop-input",function () {
            $drop_container.show();
            $drop_center.show();
        });

        $group.on("mouseleave.drop-input",function () {
            $drop_center.hide();
        });
        $drop_center.on("click.drop-input",function () {
            var $drop_center=$(this),
                $textarea=$drop_center.siblings("textarea");
            if($drop_container.attr('data-drop')==="false"){
                textareaDown($textarea,arrowDown);
            }else{
                textareaUp($textarea,arrowUp);
            }
            common.groupHide();
            return false;
        });

        $textarea.on("click.drop-input",function (e) {
            common.groupHide();
            return false;
        });

        $(window).resize(function () {
            dropLocationAndSize();
        });


        //显示文本输入框
        function textareaDown($textarea,fn) {
            $drop_container.show();
            $textarea.slideDown(function () {
                fn();
                $textarea.focus();
            });
            $drop_container.attr('data-drop',"true");
            $drop_container.css("margin-top","3px");
        }

        //隐藏文本输入框
        function textareaUp($textarea,fn) {
            var $drop_container=$textarea.parent();
            $textarea.slideUp(function () {
                fn();
            });
            $drop_container.attr('data-drop',"false");
            $drop_container.css("margin-top","0");
        }

        //中间箭头变为向上
        function arrowDown() {
            $drop_arrow.addClass("icon-er-up").removeClass("icon-xiangxia");
            $drop_arrow.parent().css({
                "bottom":"0",
                "border-top-left-radius": "10px",
                "border-top-right-radius": "10px",
                "border-bottom-left-radius": "0",
                "border-bottom-right-radius": "0"
            });
        }

        //中间箭头变为向下
        function arrowUp() {
            $drop_arrow.addClass("icon-xiangxia").removeClass("icon-er-up");
            $drop_arrow.parent().css({
                "bottom":"-18px",
                "border-bottom-left-radius": "10px",
                "border-bottom-right-radius": "10px",
                "border-top-left-radius": "0",
                "border-top-right-radius": "0"
            });
        }

        //定位drop-input-container这个div，并设置其大小
        function dropLocationAndSize() {
            var width=$div.innerWidth();
                // offset=$assay_input.offset(),
                // height=$assay_input.outerHeight();
            $drop_container.css({
                // "top":offset.top+height,
                // "left":offset.left,
                "width":width
            });
        }

    }

    // $(".medical-record-sheet li").each(function(){
    //     var n = $(this).length;
    //     for(var i= 0;i<n;i++){
    //         $(this).eq(i).find("div.txt-area").find("input").dropInput(function () {});
    //     }
    // })

}(jQuery));