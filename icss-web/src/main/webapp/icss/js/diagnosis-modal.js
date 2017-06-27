(function(){
    rebindDiagnosisClick();
    /*转诊的加急按钮，因为在页面写着静态的，所以默认直接加载click事件，等改成动态数据获取后，获取之后再添加*/
    var $li = $(".medical-record-sheet>li").eq(7);
    $li.find(".diagnosis-forth-levl").find("span.check-box,span.radio").click(function(){
        var $span=$(this);
        if ($span.hasClass("check-box")) {
            if ($span.hasClass("check-state")) {
                $span.removeClass("check-state");
                $span.siblings(".relation").css("display", "none");
            } else {
                $span.addClass("check-state");
                $span.siblings(".relation").css("display", "");
            }
        } else if ($span.hasClass("radio")) {
            $span.addClass("check-state").siblings("span.radio").removeClass("check-state");
        }
    });
    $(document).on("click",function () {
        $(".medical-record-sheet>li").eq(7).find(".second-levl").hide();
    });
})()
function rebindDiagnosisClick(){
    $(".medical-record-sheet li").each(function(index){
        if(index==7){

            $(this).find("input[type=text]").focus(function(){
                var html = '',
                    _thsParent = $(this).parents("li"),
                    apdArea = _thsParent.find(".second-levl"),
                    url = 'http://192.168.2.165:8090/icss-web/his/his_diseaseinfo/search_his_diseases?size=14&hospitalCode=331023&inputstr=z';
                    _thsParent.on("click.search",function (e) {
                       return false;
                    });
                    ajaxPushDiagnosisList(apdArea,this,url);
                    apdArea.show();
                    _thsParent.siblings().find(".second-levl").hide();
                    addDiagnosisSecondLevlButtonClick(apdArea,this);

            });


            $(this).find("input[type=text]").keyup(function(){
                var keyword = $(this).val().replace(/^\s\s*/, '').replace(/\s\s*$/, ''),
                        url = 'http://192.168.2.165:8090/icss-web/his/his_diseaseinfo/search_his_diseases?size=14&hospitalCode=331023&inputstr='+keyword;
                ajaxPushDiagnosisList($(this).parents("li").find(".second-levl"),this,url);
            })


        }
    })
}
function addDiagnosisSecondLevlButtonClick(apdArea,el){

    $(apdArea).on("click", "button", function (e) {
        e.stopPropagation();
        if (e.target.className == "come-again") {
            if($(".medical-record-sheet .list").eq(7).find(".choose-list").children("span").length>0){console.log("已经有诊断，不添加待查");return false }//此click事件会多次绑定，避免生成多次待查语句
            if($(".medical-record-sheet .choose-list:first").find("b:first").length==0){console.log("需要选择主症状");return false}
            var str='';
            $(".medical-record-sheet .choose-list:first").find("b:first").each(function(){
                str+=$(this).attr("data-name");
            });
            str+="&nbsp;&nbsp;待查";

            var li ='<span><b>'+str+'</b><i style="display:none"></i><i></i></span>';
            $(".medical-record-sheet .list").eq(7).find(".choose-list").append($(li));
            changDelet();
        }
        if(e.target.className == "go-away"){
            $(el).parents(".list").find(".diagnosis-forth-levl").children().show();
        }


    });
}

function ajaxPushDiagnosisList(area,el,url){

    $.ajax({
        type: "get",
        dataType: "json",
        url: url,
        success: function (resutl) {
            var data = resutl;
            $(area).html($("#templateDiagnosis").tmpl(data));
            $(area).find("li[tip-title]").each(function(){
                $(this).blueTip();
            });
            pushDiagnosisList(el);

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
            $(area).find("p.more-bt").fancyModal(1,obj);
        },
        error: function () {
            alert("请求失败");
        }
    });
}

//绑定详细表单展开项事件
function pushDiagnosisList(el){
    $(el).parents("li.list").find(".push-list li").click(function(){
        var ttNews = $(this).parents(".medical-record-sheet li").find(".third-levl"),
            nhtml = $(".detail-form-area").html(),
            code = $(this).attr("data-code"),
            title = title = $(this).attr("tip-title")==undefined?$(this).html():$(this).attr("tip-title");
        dynamicDiagnosisForm(ttNews,title,code);
        ttNews.show();
    })

}
function dynamicDiagnosisForm(appendArea,title,code){
    appendArea.html('<div id="third-levl'+code+'" class="detail-list" style="display:block"><h3 class="pro-name"><b>'+title
    +'</b></h3><div class="form-area"></div>'
    +'<p class="submit-bt"><button class="make-sure usable" type="button">确定</button><button onclick="this.parentNode.parentNode.style.display=\'none\'" class="cancle unusable" type="button">取消</button></p></div>');
    var a="third-levl"+code;
    var $form_area =$(document.getElementById(a)).find(".form-area");
    $form_area.append($('<dl style="height:50px;"><dt></dt><dd><label class="checkbox-label"><input type="radio" value="初诊" checked name="diagnosisradio1">初诊</label>'
    +'<label class="checkbox-label"><input type="radio" value="复诊" name="diagnosisradio1">复诊</label></dd></dl>'));

    //给确定按钮添加事件，输出选择好的拼出来的信息
    $form_area.parent().on("click.make-sure", "button", function (e) {
        var $button=$(e.target);
        if (e.target.className !== "make-sure usable") {
            $button.parents(".third-levl:first").hide();
            return;
        }
        var li='<span><b data-id="'+code+'">'+title;
        $(appendArea).find("input[type=radio]").each(function(){
            if($(this).is(":checked")){
                li+="（"+$(this).val()+"）";
            }
        });
        li+='</b><i style="display:none"></i><i></i></span>';
        $(".medical-record-sheet .list").eq(7).find(".choose-list").append($(li));
        changDelet();
        document.getElementById(a).style.display="none"
    });

}
