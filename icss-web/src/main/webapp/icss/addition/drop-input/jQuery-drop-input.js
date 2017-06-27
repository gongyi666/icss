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


    function dropInput($input,callback) {
        var $assay_input=$input,
            $li=$assay_input.parent(),
            html,
            $drop_container,
            $textarea,
            $drop_arrow;

        html='<div class="drop-input-container" data-drop="false" style="display:none"><div class="drop-input-center"><i class="iconfont icon-xiangxia"></i></div><textarea style="display: none"></textarea></div>';

        $drop_container=$li.append(html).find("div.drop-input-container");
        $textarea=$drop_container.find("textarea");
        $drop_arrow=$drop_container.find("i");
        dropLocationAndSize();

        $assay_input.on("mouseenter",function () {
            $drop_container.css("display","block");
        });

        $li.on("mouseleave",function () {
            textareaUp(arrowUp);
            $drop_container.css("display","none");
            callback();
        });

        $drop_container.on("click",function (e) {
            if($drop_container.attr('data-drop')==="false"){
                textareaDown(arrowDown);
            }else{
                textareaUp(arrowUp);
            }
            e.stopPropagation();
        });

        $textarea.on("click",function (e) {
            e.stopPropagation();
        });

        $(window).resize(function () {
            dropLocationAndSize();
        });

        $(document).off("click.drop-input");
        $(document).on("click.drop-input",function () {
            textareaUp(arrowUp);
            $drop_container.css("display","none");
        });


        //显示文本输入框
        function textareaDown(fn) {
            $textarea.slideDown(function () {
                fn();
            });
            $textarea.focus();
            $drop_container.attr('data-drop',"true");
        }

        //隐藏文本输入框
        function textareaUp(fn) {
            $textarea.blur();
            $textarea.slideUp(function () {
                fn();
            });
            $drop_container.attr('data-drop',"false");
        }

        //中间箭头变为向上
        function arrowDown() {
            $drop_arrow.addClass("icon-er-up").removeClass("icon-xiangxia");
            $drop_arrow.parent().css({
                "bottom":"3px",
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
                "bottom":"-17px",
                "border-bottom-left-radius": "10px",
                "border-bottom-right-radius": "10px",
                "border-top-left-radius": "0",
                "border-top-right-radius": "0"
            });
        }

        //定位drop-input-container这个div，并设置其大小
        function dropLocationAndSize() {
            var width=$assay_input.innerWidth();
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