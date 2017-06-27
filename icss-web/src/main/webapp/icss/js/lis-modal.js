(function(){
    rebindLisClick();
    $(document).on("click",function () {
        $(".medical-record-sheet>li").eq(5).find(".second-levl").hide();
    });
})()
function rebindLisClick(){
    $(".medical-record-sheet li").each(function(index){
        if(index==5){
            $(this).find("input").focus(function(){
                var html = '',
                    _thsParent = $(this).parents("li"),
                    apdArea = _thsParent.find(".second-levl"),
                    url = Param.hostUrl + "/kl/lis_project/get_lis_project?doctorId=1&size=16";
                    _thsParent.on("click.search",function (e) {
                       return false;
                    });
                    ajaxPushLisList(apdArea,this,url);
                    apdArea.show();
                    _thsParent.siblings().find(".second-levl").hide();

            });
            $(this).find("input").keyup(function(){
                var keyword = $(this).val().replace(/^\s\s*/, '').replace(/\s\s*$/, ''),
                        url = Param.hostUrl + '/kl/lis_project/search_lis_project?size=16&input='+keyword;
                ajaxPushLisList($(this).parents("li").find(".second-levl"),this,url);
            })
        }
    })
}

function ajaxPushLisList(area,el,url){

    $.ajax({
        type: "get",
        dataType: "json",
        url: url,
        success: function (resutl) {
            var data = resutl;
            $(area).html($("#templateLis").tmpl(data));
            $(area).find("li[tip-title]").each(function(){
                $(this).blueTip();
            });
            pushLisList(el);

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
            console.log("请求失败");
        }
    });
}

//绑定详细表单展开项事件
function pushLisList(el){
    $(el).parents("li.list").find(".push-list li").click(function(){
        var ttNews = $(this).parents(".medical-record-sheet li").find(".third-levl"),
            nhtml = $(".detail-form-area").html(),
            code = $(this).attr("data-code"),
            title = $(this).attr("tip-title")==undefined?$(this).html():$(this).attr("tip-title");
        dynamicLisForm(ttNews,title,code);
        //getLisCheckedList(ttNews)
        ttNews.show();
    })
}
function getLisCheckedList(code){
    var resl=ajaxPost( Param.hostUrl + "/kl/lis_detail/get_detail",{hospitalCode:331023,doctorCode:111,deptNo:001,code:code});
    var li='';
    $(resl.data).each(function(){
        li+='<dl style="width:300px;float:left;"><dt style="margin-top:6px;">'+this.labelPrefix+'</dt><dd><input name="'+this.textName+'" type="text">'+this.labelSuffix+'</dd></dl>'
    });
    return li;
}
function dynamicLisForm(appendArea,title,code){
    appendArea.html('<div id="third-levl'+code+'" class="detail-list" style="display:block"><h3 class="pro-name"><b>'+title
    +'</b><div class="pro-name-box"><span class="check-box lis-checked-label">已检</span></div></h3><div class="form-area"><div class="row lis-check-some" style="float:left;"></div>'
    +'<div class="row lis-check-some2" style="float:left;"></div><div class="row lis-check-some3" style="float:left;"></div><div class="row lis-check-some4" style="float:left;"></div>'
    +'<div style="clear:both"></div></div><div class="form-area-checked-lis" style="display:none;overflow:hidden;"></div>'
    +'<p class="submit-bt"><button class="make-sure usable" type="button">确定</button><button class="cancle unusable" type="button">取消</button></p></div>');
    var a="third-levl"+code;
    var $form_area = $(document.getElementById(a)).find(".form-area .lis-check-some");

    var resl=ajaxPost(Param.hostUrl + '/kl/lis/get_lis',{hospitalCode:'331023',detailCode:'2'});
    $form_area.append($('<div class="form-group"><p class="">项目</p><input style="width:120px" type="text" value="" class="p-r-20" readonly><div class="input-arrow"></div>'
    +'<div style="position: absolute;left: 0;right: 0;height: auto;background-color: #fff;border: 1px solid #ccc;z-index: 3;display: none" class="input-drop"><ul></ul></div>'
    +'<i class="iconfont icon-xiangxia input-arrow-font" style="margin-right:10px;"></i></div>'));
    $form_area.find(".form-group ul").append($(getLisGetInfo(resl.data)));
    function getLisGetInfo(data){
        var li='';
        $(data).each(function(){
            li+='<li class="p-l-10"><span class="check-box" temp="'+this.name+'" code="'+this.hospitalCode+'">'+this.name+'</span>';
            li+='</li>'
        });
        return li
    }

    $form_area = $(document.getElementById(a)).find(".form-area .lis-check-some2");
    $form_area.append($('<div class="form-group"><p class="">标本</p><input name="lismethod2" type="text" value="血清" class="p-r-20" readonly><div class="input-arrow"></div></div>'));

    $form_area = $(document.getElementById(a)).find(".form-area .lis-check-some3");
    $form_area.append($('<div class="form-group"><p class="">方法</p><input name="lismethod3" type="text" value="免疫法" class="p-r-20" readonly><div class="input-arrow"></div></div>'));

    $form_area = $(document.getElementById(a)).find(".form-area .lis-check-some4");
    $form_area.append($('<div class="form-group"><p class="">备注</p><input name="lismethod4" type="text" value="" class="p-r-20" readonly style="width:150px;"><div class="input-arrow"></div></div>'));

    var $form_area_checked_lis =$(document.getElementById(a)).find(".form-area-checked-lis");
    $form_area_checked_lis.append($(getLisCheckedList(code)));

    var $li = $(".medical-record-sheet>li").eq(5),
        $second_levl = $li.find("div.second-levl"),
        $third_levl = $li.find("div.third-levl"),
        $detail_list = $third_levl.find("div.detail-list");
    $li.find("input").off("click focus");
/*
    $detail_list.on("click", "input", function (e) {
        var $input = $(e.target);
        inputFocus($input);
        e.stopPropagation();
    });
*/

    $detail_list.find("div.input-arrow").on("click", function (e) {
        var $div = $(this);
        inputFocus($div.siblings("input"));
        e.stopPropagation();
    });

    function inputFocus($input) {
        $third_levl.find("input").removeClass("focus").siblings(".input-drop").css("display", "none");
        $input.addClass("focus").siblings(".input-drop").fadeIn();
    }

    $detail_list.find("div.input-drop").on("mouseleave", function () {
        $(this).css("display", "none");
    });

    $detail_list.find("span.check-box,span.radio").on("click", function () {
        checkboxRelationRadio($(this));
        //showFormAreaChecked($(this));
    });
    function checkboxRelationRadio($span) {
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
    }
    /*
    function showFormAreaChecked($span){
        if($span.hasClass("check-state")){
            $span.parents(".detail-list").find(".form-area").css("display","none");
            $span.parents(".detail-list").find(".form-area-checked-lis").css("display","block");
        }else{
            $span.parents(".detail-list").find(".form-area").css("display","block");
            $span.parents(".detail-list").find(".form-area-checked-lis").css("display","none");
        }
    }
    */

    $detail_list.find("div.input-drop").children("ul").children("li").find("span").on("click", function () {//部位赋值
        var $span = $(this),
            val = "";
        val=$span.hasClass("check-state")?$span.attr("temp"):"";
        $span.parents("div.input-drop:last").siblings("input").val(val);
    });

    $(".lis-checked-label").click(function(){
        if($(this).hasClass("check-state")){
            $(this).parents(".detail-list").find(".form-area").css("display","none");
            $(this).parents(".detail-list").find(".form-area-checked-lis").css("display","block");
        }else{
            $(this).parents(".detail-list").find(".form-area").css("display","block");
            $(this).parents(".detail-list").find(".form-area-checked-lis").css("display","none");
        }

    });

    $form_area = $(document.getElementById(a)).find(".form-area");
    //给确定按钮添加事件，输出选择好的拼出来的信息
    $form_area.parent().on("click.make-sure", "button", function (e) {
        //console.log(e.target.className);
        if (e.target.className !== "make-sure usable") {
            $(document.getElementById(a)).hide();
            return;
        }
        var $dls = $form_area.find("dl"),
            info = $form_area.siblings("h3.pro-name").text() + ":";
        $dls.each(function () {
            var $dl = $(this),
                $dd = $dl.find("dd");
            info += $dl.find("dt").text() + " : ";
            $dd.children().each(function () {
                var $this = $(this);
                info += getVal($this) + "、";
            });
            info = info.slice(0, -1) + ",";
        });


        $(this).parents("li").find(".choose-list").append("<span><b data-id='"+code+"'>" + info.slice(0, -1) + "</b><i style='display:none'></i><i></i></span>");
        $(".apdCs").find(".choose-list").append("<span><b>" + info.slice(0, -1) + "</b><i style='display:none'></i><i></i></span>");
        $(document.getElementById(a)).hide();
        changDelet();
        $(".push-list li").each(function () {
            var inTxt = $(this).html(),
                title = $form_area.siblings("h3.pro-name").text();
            if (inTxt == title) {
                $(this).addClass("check-state");
            }
        })

    });

    function getVal($node) {
        switch ($node[0].tagName) {
            case "SPAN":
                if (1) {
                    return $node.text() + " ";
                }
                break;
            case "TEXTAREA":
                return $node.val();
            case "INPUT":
                return $node.val();
            case "SELECT":
                var info = "";
                $node.find("option").each(function () {
                    var val = $node.val(),
                        $option = $(this);
                    if (val === $option.val()) {
                        info = $option.text();
                        return false;
                    }
                });
                return info;
        }
    }
}
