/**
 * Created by Kiva on 17/2/27.
 */
(function ($) {

    var $assay_input=$("#main-ct").find("input").eq(5),
        $li=$assay_input.parent(),
        html,
        $drop_container,
        $textarea;

    html='<div class="drop-input-container" data-drop="false" style="display:none"><div class="drop-input-center"><i class="iconfont icon-jiantou "></i></div><textarea style="display: none"></textarea></div>';

    $drop_container=$li.append(html).find("div.drop-input-container");
    $textarea=$drop_container.find("textarea");

    dropLocationAndSize();

    $assay_input.on("mouseenter",function () {
        $drop_container.css("display","block");
    });

    $textarea.on("mouseleave",function () {
        var $drop_arrow=$drop_container.find("div.drop-input-arrow");
        textareaUp();
        arrowUp($drop_arrow);
        $drop_container.css("display","none");
    });

    $drop_container.on("click",function (e) {
        var $drop_arrow=$drop_container.find("div.drop-input-arrow");
        if($drop_container.attr('data-drop')==="false"){
            textareaDown();
            arrowDown($drop_arrow);
        }else{
            textareaUp();
            arrowUp($drop_arrow);
        }
        e.stopPropagation();
    });

    $textarea.on("click",function (e) {
       e.stopPropagation();
    });

    $(window).resize(function () {
       dropLocationAndSize();
    });

    // $(document).on("click",function () {
    //     var $drop_arrow=$drop_container.find("div.drop-input-arrow");
    //     textareaUp();
    //     arrowUp($drop_arrow);
    //     $drop_container.css("display","none");
    // });


    //显示文本输入框
    function textareaDown() {
        $textarea.slideDown();
        $textarea.focus();
        $drop_container.attr('data-drop',"true");
    }

    //隐藏文本输入框
    function textareaUp() {
        $textarea.blur();
        $textarea.slideUp();
        $drop_container.attr('data-drop',"false");
    }

    //中间箭头变为向上
    function arrowDown($drop_arrow) {
        $drop_arrow.css({
            "borderBottomWidth":"5px",
            "borderTopWidth":"0"
        });
    }

    //中间箭头变为向下
    function arrowUp($drop_arrow) {
        $drop_arrow.css({
            "borderBottomWidth":"0",
            "borderTopWidth":"5px"
        });
    }

    //定位drop-input-container这个div，并设置其大小
    function dropLocationAndSize() {
        var width=$assay_input.innerWidth();
            // offset=$assay_input.offset(),
            // offset_left=offset.left,
            // offset_top=offset.top,
            // height=$assay_input.outerHeight();
        $drop_container.css({
            // "top":offset_top+height,
            // "left":offset_left,
            "width":width
        });
    }



    html=null;
}(jQuery));