/**
 * Created by Administrator on 2017/3/2.
 */

function ajaxPushList(apdArea ,ajxUrl,typeVal,liNum,param,callback){
    $.ajax({
        type: "post",
        dataType: "json",
        url: ajxUrl,
        async:true,
        data:param,
        success: function (resutl) {
            var data = resutl;
            apdArea.html($("#template1").tmpl(data));
            if(liNum!=2 && liNum!=3){
                $(".past-history").remove();
            }
            if(liNum==3){
                $(".past-history").find("ul.cr-hsy").html("<li>家族史</li><li>月经史</li><li>婚育史</li>");
            }
            var $button=apdArea.find("p.more-bt");
            var obj={
                "fancy.modal.show":function () {
                    //console.log("show");
                },
                "fancy.modal.shown":function () {
                    //console.log("shown");
                },
                "fancy.modal.hide":function () {
                    //console.log("hide");
                },
                "fancy.modal.hidden":function () {
                    //console.log("hiden");
                },
                "fancy.modal.loaded":function () {
                    val();
                    $(window).resize(function(){
                        val();
                    });
                    function val(){
                        var WinWidth =$(window).width();
                        var WinHeight  =$(window).height();
                        var Popwidth =$(".fancy-ct").width();
                        var Popheight =$(".fancy-ct").height();
                        var Leftwidth = (WinWidth-Popwidth)/2+"px";
                        var topheight = (WinHeight-Popheight)/2+"px";

                        $(".fancy-box,.fancy-mask").css({width:WinWidth,height:WinHeight});
                        $(".fancy-ct").css({left:Leftwidth,top:topheight});
                    };
                }
            };

            pushList(liNum);
            conEffect();
            allHist();
            crHssyList();
            $button.fancyModal(typeVal,obj);
            // //删掉主诉和现病史的无
            // var $past_history=apdArea.find(".past-history"),
            //     $label=$past_history.parents("li:first").children("label");
            // if($label.text()==="主诉" || $label.text()==="现病史"){
            //     $past_history.remove();
            // }
            callback=callback || function () {};
            callback();
        },
        error: function () {
            alert("请求失败");
        }
    });
}

//详细表单展开项
function pushList(liNum){
    $(".push-list").each(function(){
        $(this).children("li").click(function(){
            var ttNews = $(this).parents(".medical-record-sheet li").find(".third-levl"),
                id = $(this).attr("data-id"),
                title = $(this).html(),
                $third_levl=ttNews.find("#third-levl"+id);
            if($third_levl.length>0){
                $third_levl.show();
                return;
            }
            dynamicForm(ttNews,title,id,liNum);
        })
    })
}
//疾病史详细列表
function crHssyList(){
    $(".cr-hsy li").each(function(){
        $(this).click(function(){
            var state = $(this).attr("class"),
                idx = "check-state",
                addHml = "<span><b>无"+$(this).html()+"</b><i></i></span><br>",
                choList = $(this).parents("li").find(".choose-list");
            $(choList).append(addHml);
            changDelet();
        })
    })
}
//疾病史控件效果
function conEffect(){
    // $(".check-box,.cr-hsy li,.all").each(function(){
    //     $(this).off("click.check-state");
    //     $(this).on("click.check-state",function(){
    //         $(this).toggleClass("check-state");
    //     })
    // });
    $(".past-history .none").click(function(){
        var state = $(this).attr("class"),
            idx = "check-state";
        if(state.indexOf(idx)>0){
            $(this).siblings().css("display","block");
        }else{
            $(this).siblings().css("display","none");
        }
    })
}
//修改删除文本
changDelet();
function changDelet(){
    $(".choose-list span").each(function(){
        /***修改文本***/
        $(this).children("b").click(function(){
            var ttNews = $(this).parents("li:first");
            ttNews.find("#third-levl"+$(this).attr("data-id")).show().siblings().hide();
        });

        /***给现病史向上移动到主诉的图标添加 ***/
        $(this).children("i").eq(0).off("click.arrow");
        $(this).children("i").eq(0).on("click.arrow",function () {
            var $i=$(this),
                $list_li=$i.parents("li.list"),
                $b=$i.siblings("b"),
                data_id=$b.attr("data-id"),
                $span=$b.parent(),
                $list_main_suit=$list_li.siblings("li.list:first"),
                $third_levl,
                $b_clone;

            if($list_main_suit.find("b[data-id="+data_id+"]").length===0){
                $third_levl=$list_li.find("div.third-levl").find("#third-levl"+data_id);
                $list_main_suit.find("div.third-levl").append($third_levl.clone(true));
                $list_main_suit.find("div.choose-list").append($span.clone(true));
                $b_clone=$list_main_suit.find("b[data-id="+data_id+"]");
                $b_clone.siblings("i:first").css("display","none");
                $b_clone.text($b_clone.text().split(",")[0]);
            }
            chooseListSort($list_main_suit.find("div.choose-list"));
        });

        /***删除文本***/
        $(this).children("i").eq(1).click(function(){
            var $i=$(this),
                $b=$i.siblings("b"),
                id=$b.attr("data-id"),
                ttAll = $b.html(),
                index=$i.parents("li.list").index(),
                $spans,
                $b2;
            $b.parents("li.list:first").find("#third-levl"+id).remove();
            $i.parent().remove();
            if(ttAll=="无所有疾病史"){
                $(".cr-hsy li,.all").removeClass("check-state");
            }
            $spans=$(".choose-list span");
            $b2=$spans.find("b[data-id="+id+"]");
            if(index===0 && $b2.length>0 && confirm("是否删除现病史对应症状？")){
                    $b2.siblings("i:last").click();
            }

        });
    })
}
//无所有疾病史
function allHist(){
    $(".past-history .all").click(function(){
        var state = $(this).attr("class"),
            idx = "check-state",
            choList = $(this).parents("li").find(".choose-list"),
            allNone = "<span class='all-none'><b>无所有疾病史</b><i></i></span><br>";
        if(state.indexOf(idx)>0){
            $(this).siblings(".cr-hsy").find("li").addClass("check-state");
            $(allNone).appendTo(choList);
        }else{
            $(this).siblings(".cr-hsy").find("li").removeClass("check-state");
            $(choList).find(".all-none").remove();
        }
        changDelet();
    });

}
