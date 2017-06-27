/****展开一级推送列表***/
$(".medical-record-sheet li").each(function(){
    var _this = $(this);
    _this.click(function(){
        $(".open-list1").appendTo(_this).toggle();
    })
    $(".choose-list").siblings("input").hide();
})
/****展开二级详细列表****/
$(".push-list").each(function(){
    $(this).children("li").click(function(){
        var ttNews = $(this).parents(".medical-record-sheet li");
        $(".detail-list").appendTo(ttNews).show();
        $(this).addClass("check-state");
        $(this).siblings().removeClass("check-state");
    })
})

$(".choose-list span").each(function(){
    /***修改文本***/
    $(this).children("b").click(function(){
        var ttNews = $(this).parents(".medical-record-sheet li");
        $(".detail-list").appendTo(ttNews).show();
    })
    /***删除文本***/
    $(this).children("i").click(function(){
        $(this).parent("span").remove();
    })
})

$(".make-sure,.cancle").click(function(){
    $(".detail-list").hide();
})
$(".check-box,.cr-hsy li,.all").each(function(){
    $(this).click(function(){
        $(this).toggleClass("check-state");
    })
})
//*******************弹层*******************************//
fancyBox()
function fancyBox(){
    $(".more-bt").click(function(){
        $("#fancy-box").show();
        return false;
    });
    val();
    $(window).resize(function(){
        val();
    });
    function val(){
        var WinWidth =$(window).width();
        var WinHeight  =$(window).height();
        var Popwidth =$("#fancy-ct").width();
        var Popheight =$("#fancy-ct").height();
        var Leftwidth = (WinWidth-Popwidth)/2+"px";
        var topheight = (WinHeight-Popheight)/2+"px";

        $("#fancy-box,#fancy-mask").css({width:WinWidth,height:WinHeight});
        $("#fancy-ct").css({left:Leftwidth,top:topheight});
    };
    $("#close").click(function(){
        $("#fancy-box").hide();
        return false;
    });
}
//********************树形表单**********************************//
/***判断有无侧导航****/
$(".second-level").each(function(){
    var content = $(this).html();
    if(content == null || content.length == 0){
        $(this).remove();
    }else{
        $(this).css("width","119px");
        $(this).siblings(".level-list").css({"width":"630px","float":"left"});
    }
})

$(".first-level li").each(function(){
    $(this).click(function () {
        var num = $(this).index();
        $(this).addClass("check-state").siblings().removeClass("check-state");
        $(".detail-category").hide();
        $(".detail-category").eq(num).show();
    })
})

$(".second-level dt").each(function(){
    $(this).click(function () {
        var num = $(this).index();
        $(this).addClass("check-state").siblings().removeClass("check-state");
        $(this).parents(".second-level").siblings(".level-list").hide();
        $(this).parents(".second-level").siblings(".level-list").eq(num).show();
        $(".third-level b").css("display","none");
        $(this).children("b").css("display","block");

        $(this).children("b").each(function(){
            $(this).click(function () {
                var bnum = $(this).index();
                $(this).parents(".second-level").siblings(".level-list").eq(num).attr("n",bnum);
                $(this).addClass("check-state").siblings().removeClass("check-state");
                $(this).parents(".second-level").siblings(".level-list").eq(num).children(".push-list").hide();
                $(this).parents(".second-level").siblings(".level-list").eq(num).children(".push-list").eq(bnum).show();
            })
        })
    });



})



/**
 * Created by Administrator on 2017/2/24.
 */
