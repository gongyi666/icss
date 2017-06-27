(function(){
    rebindPacsClick();
    $(document).on("click",function () {
        $(".medical-record-sheet>li").eq(6).find(".second-levl").hide();
    });
})()
var pacsUsedData={};//这是一个全局变量，用于存放常用检查，用于在详细框里使用,目前没用到
function rebindPacsClick(){
    $(".medical-record-sheet li").each(function(index){
        if(index==6){

            $(this).find("input").focus(function(){
                var html = '',
                    _thsParent = $(this).parents("li"),
                    apdArea = _thsParent.find(".second-levl"),
                    url = "http://192.168.2.165:8090/icss-web/kl/pacs/init_pacsinfo?doctorId=1&size=6";
                    _thsParent.on("click.search",function (e) {
                       return false;
                    });
                    ajaxPushPacsList(apdArea,this,url);
                    apdArea.show();
                    _thsParent.siblings().find(".second-levl").hide();

            });
            $(this).find("input").keyup(function(){
                var keyword = $(this).val().replace(/^\s\s*/, '').replace(/\s\s*$/, ''),
                        url = 'http://192.168.2.165:8090/icss-web/kl/pacs/search_pacs?size=6&inputstr='+keyword;
                ajaxPushPacsList($(this).parents("li").find(".second-levl"),this,url);
            })

        }
    })
}

function ajaxPushPacsList(area,el,url){

    $.ajax({
        type: "get",
        dataType: "json",
        url: url,
        success: function (resutl) {
            var data = resutl;
            $(area).html($("#templatePacs").tmpl(data));
            $(area).find("li[tip-title]").each(function(){
                $(this).blueTip();
            });
            pushPacsList(el);


            fancyPacsbox();
        },
        error: function () {
            alert("请求失败");
        }
    });
}

//绑定详细表单展开项事件
function pushPacsList(el){
    $(el).parents("li.list").find(".push-list li").click(function(){
        var ttNews = $(this).parents(".medical-record-sheet li").find(".third-levl"),
            nhtml = $(".detail-form-area").html();
            pacode = $(this).attr("pacode"),
            title = $(this).html();
        var wholeid=$(this).attr("wholeid");
        var apcode=$(this).attr("apcode");
        dynamicPacsForm(ttNews,title,pacode,apcode,wholeid);
        ttNews.show();
    })
}

function dynamicPacsForm (appendArea,title,partCode,apparatusCode,wholeid) {
    appendArea.html('<div id="third-levl'+wholeid+'" class="detail-list" style="display:block"><h3 class="pro-name"><b>'+title
    +'</b><div class="pro-name-box"><span class="check-box pacs-checked-label">已检</span></div></h3><div class="form-area"><div class="row pacs-check-parts" style="float:left;"></div>'
    +'<div class="row pacs-check-method" style="float:left;"></div><div style="clear:both"></div>'
    +'<div class="row"><div class="form-group" style="width: 96%"><p class="">补充</p><input type="text" style="width: 100%"></div></div></div><div class="form-area-checked-pacs" style="display:none;"></div>'
    +'<div class="pacs-cue" style="color:red"></div><p class="submit-bt"><button class="make-sure usable" type="button">确定</button><button class="cancle unusable" type="button">取消</button></p></div>');
    var a="third-levl"+wholeid;
    var $form_area = $(document.getElementById(a)).find(".form-area .pacs-check-parts");

    var resl=ajaxPost('http://192.168.2.165:8090/icss-web/kl/pacs/get_pacs_content',{partCode:partCode});
    var apparatus=resl.data.Apparatus;
    var apparatusType=resl.data.ApparatusType;

    $form_area.append($('<div class="form-group"><p class="">部位</p><input style="width:120px" type="text" value="" class="p-r-20" readonly><div class="input-arrow"></div>'
    +'<div style="position: absolute;left: 0;right: 0;height: auto;background-color: #fff;border: 1px solid #ccc;z-index: 3;display: none" class="input-drop"><ul></ul></div>'
    +'<i class="iconfont icon-xiangxia input-arrow-font" style="margin-right:10px;"></i></div>'));
    $form_area.find(".form-group ul").append($(getBodyParts(apparatus)));

    function getBodyParts(data){
        var data=data[0].partList[0];
        var li='';
        $(data).each(function(){
            li+='<li class="p-l-10"><span class="check-box pacs-partdata" temp="'+this.name+'" code="'+this.code+'">'+this.name+'</span>';
            if(this.organList&&this.organList.length>0){
                li+='<div class="" style="padding-left:20px;display:none;"><ul>';
                $(this.organList).each(function(){
                    li+='<li class=""><span class="check-box pacs-organdata" temp="'+this.name+'" code="'+this.code+'">'+this.name+'</span></li>'
                });
                li+='</ul></div>'
            }
            li+='</li>'
        });
        return li
    }


    var $form_area = $(document.getElementById(a)).find(".form-area .pacs-check-method");

    $form_area.append($('<div class="form-group"><p class="">手段</p><input name="pacsmethod1" type="text" value="" class="p-r-20" readonly="" data-code="'+apparatusCode+'"><div class="input-arrow"></div>'
    +'</div>'));
    var pacsApparatusModel=0;
    var pacsApparatusPosition=0;
    var pacsApparatusRequirement=0;
    $(apparatusType).each(function(){
        $(this.apparatusList).each(function(){
            if(this.code==apparatusCode){
                $form_area.find("input[name=pacsmethod1]").val(this.name);
                this.model==1?pacsApparatusModel=1:pacsApparatusModel=0;
                this.position==1?pacsApparatusPosition=1:pacsApparatusPosition=0;
                this.requirement==1?pacsApparatusRequirement=1:pacsApparatusRequirement=0;
            }
        });
    });
    if(pacsApparatusRequirement){
        $form_area.append($('<div class="form-group"><p class="">检查方法</p><input name="pacsmethod2" type="text" value="平扫" class="p-r-20" readonly="" data-code=1><div class="input-arrow"><i class="iconfont icon-xiangxia input-arrow-font"></i></div>'
        +'<div style="width: 162px;position: absolute;left: 0;height: auto;background-color: #fff;border: 1px solid #ccc;z-index: 3;display: none" class="input-drop"><ul class="pacs-check-method-second"></ul></div></div>'));
        $form_area.find(".form-group .pacs-check-method-second").append($('<li><a data-code=1>平扫</a><li><li><a data-code=2>增强</a><li><li><a data-code=3>三维</a><li><li><a data-code=4>三期</a><li>'));
    }

    if(pacsApparatusPosition){
        $form_area.append($('<div class="form-group"><p class="">体位</p><input name="pacsmethod3" type="text" value="前后（矢状、冠状位）" class="p-r-20" readonly="" data-code=1><div class="input-arrow"><i class="iconfont icon-xiangxia input-arrow-font"></i></div>'
        +'<div style="width: 162px;position: absolute;left: 0;height: auto;background-color: #fff;border: 1px solid #ccc;z-index: 3;display: none" class="input-drop"><ul class="pacs-check-method-third"></ul></div></div>'));
        $form_area.find(".form-group .pacs-check-method-third").append($('<li><a data-code=1>前后（矢状、冠状位）</a><li><li><a data-code=2>正后</a><li><li><a data-code=3>侧位</a><li><li><a data-code=4>左斜</a><li>'
        +'<li><a data-code=5>右斜</a><li><li><a data-code=6>切线位</a><li><li><a data-code=7>轴位</a><li>'));
    }

    if(pacsApparatusModel){
        $form_area.append($('<div class="form-group"><p class="">机型</p><input  name="pacsmethod4" type="text" value="国产" class="p-r-20" readonly="" data-code=1><div class="input-arrow"><i class="iconfont icon-xiangxia input-arrow-font"></i></div>'
        +'<div style="width: 162px;position: absolute;left: 0;height: auto;background-color: #fff;border: 1px solid #ccc;z-index: 3;display: none" class="input-drop"><ul class="pacs-check-method-forth"></ul></div></div><div class="pacs-add-more-part add-drug">+</div>'));
        $form_area.find(".form-group .pacs-check-method-forth").append($('<li><a data-code=1>国产</a><li><li><a data-code=2>进口</a><li><li><a data-code=3>床旁机</a><li>'));
    }

    var $li = $(".medical-record-sheet>li").eq(6),
        $second_levl = $li.find("div.second-levl"),
        $third_levl = $li.find("div.third-levl"),
        $detail_list = $third_levl.find("div.detail-list");
    $li.find("input").off("click focus");

    $li.find("input").on("click", function () {
        $second_levl.toggle();
        $li.siblings().find("div.second-levl").hide();
    });

    $second_levl.find("li").eq(0).on("click", function () {
        $(document.getElementById(a)).show().siblings().hide();
    });

    $third_levl.on("click", function () {
        $third_levl.find("input").removeClass("focus").siblings(".input-drop").css("display", "none");
    });
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

    $detail_list.find("div.input-drop").on("click", function (e) {
        return false;
    });

    $detail_list.find("div.input-drop").on("mouseleave", function () {
        $(this).css("display", "none");
    });


    $detail_list.find("span.check-box,span.radio").on("click", function () {
        checkboxRelationRadio($(this));
    });




    $li.find(".pacs-check-parts .pacs-partdata").click(function(){
        if($(this).hasClass("check-state")){
            $(this).siblings("div").show();
        }else {
            $(this).siblings("div").hide().find("span").removeClass("check-state")
        }
    });

    $detail_list.find("div.input-drop").children("ul").children("li").find("span").on("click", function () {//部位赋值
        var $span = $(this),
            $spans,
            val = "";
        $spans = $span.parents("ul").children("li").children("span.check-state");

        val=addCheckboxVal($detail_list.find("div.input-drop"));
        $span.parents("div.input-drop:last").siblings("input").val(val.slice(0, -1));
    });

    $detail_list.find("div.input-drop").find("a").on("mouseenter", function () {
        var $a = $(this),
            $a_siblings = $a.parent().siblings().children("a");
        $a_siblings.children("i").css("display", "none");
        $a_siblings.siblings("div.input-drop").css("display", "none");
        $a.children("i").css("display", "block");
        $a.siblings("div.input-drop").fadeIn();
    });

    $detail_list.find("div.input-drop").find("a").on("click", function () {
        var $a = $(this),
            $input_drop,
            $input;
        if ($a.siblings("div.input-drop").length === 0) {
            $input_drop = $a.parents("div.input-drop:last");
            $input = $input_drop.siblings("input");
            $input_drop.css("display", "none");
            $input.val($a.text());
            $input.attr("data-code",$a.attr("data-code"))
        }
    });

    $detail_list.find("div.pacs-add-more-part").on("click", function () {
        fancyPacsMorePartbox(apparatusCode)
    });


    function addCheckboxVal($span) {

        var str1="";
        var str2="";
        $span.find(".check-state").each(function(){
            if($(this).hasClass("pacs-partdata")){
                str1+="（"+$(this).text()+"）"+"+";
            }else if ($(this).hasClass("pacs-organdata")) {
                str2+=$(this).text()+"+";
            }
        });
        return str1+str2

    }

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


    $(".pacs-checked-label").click(function(){
        if($(this).hasClass("check-state")){
            $(this).parents(".detail-list").find(".form-area").css("display","none");
            $(this).parents(".detail-list").find(".form-area-checked-pacs").css("display","block");
        }else{
            $(this).parents(".detail-list").find(".form-area").css("display","block");
            $(this).parents(".detail-list").find(".form-area-checked-pacs").css("display","none");
        }

    });


    /*默认选中选择的项，如选择腹部CT，默认选中腹部和CT*/
    //$(document.getElementById(a)).find("span[code="+partCode+"]").click();
    var $selspan=$(document.getElementById(a)).find("span[code="+partCode+"]");
    if($selspan.hasClass("pacs-organdata")){
        $selspan.parent().parents("li:first").children(".pacs-partdata").click();
    }
    $selspan.click();


    $(document.getElementById(a)).find("li a[data-code="+apparatusCode+"]").click();


    //给确定按钮添加事件，输出选择好的拼出来的信息
    $form_area = $(document.getElementById(a));

    $form_area.on("click.make-sure", "button", function (e) {
        //关于bTagMark和wholeid，wholeid用于在点击“腹部CT”时，生成详细框的id，此id用于绑定详细框内元素的事件时用作选择器；
        //选择完选项后，点击确定，按照选项生成bTagMark，首先用于判断是否已选择该项目，若未选择，将详细框id用bTagMark赋值，
        //并且将生成的器查条目添加此id赋值的btagmark和data-id，btagmark属性用于下次点击判断是否已选择该项目，data-id用于修改时的关联
        var bTagMark="";
        var $button=$(e.target);
        if (e.target.className !== "make-sure usable") {//取消按钮
            $button.parents(".detail-list:first").hide();
            return;
        }
        var datas=[];
        var pacsData={};
        pacsData.hospitalCode='331023';
        pacsData.code=$form_area.find("input[name=pacsmethod1]").attr("data-code");
        bTagMark+=pacsData.code;
        pacsData.name=$form_area.find("input[name=pacsmethod1]").val();
        pacsData.model=$form_area.find("input[name=pacsmethod4]").attr("data-code");
        bTagMark+=pacsData.model;
        pacsData.position=[$form_area.find("input[name=pacsmethod3]").attr("data-code")];
        bTagMark+=pacsData.position[0];
        pacsData.requirement=[$form_area.find("input[name=pacsmethod2]").attr("data-code")];
        bTagMark+=pacsData.requirement[0];
        pacsData.partDatas=[];

        $form_area.find(".pacs-check-parts").find("span.pacs-partdata").each(function(){
            if($(this).hasClass("check-state")){
                var partData={};
                partData.code=$(this).attr("code");
                partData.name=$(this).attr("temp");
                bTagMark+=partData.code;
                partData.direction="";
                partData.organDatas=[];
                $(this).parent().find("span.pacs-organdata").each(function(){
                    if($(this).hasClass("check-state")){
                        var organData={};
                        organData.code=$(this).attr("code");
                        organData.name=$(this).attr("temp");
                        bTagMark+=organData.code;
                        organData.direction="";
                        partData.organDatas.push(organData);
                    }
                });
                pacsData.partDatas.push(partData);
            }
        });
        datas.push(pacsData);
        var resl=ajaxSetContentType("http://192.168.2.165:8090/icss-web/kl/pacs/post_pacs_structing",jsonTOstring(datas));
        if(resl.data.length==0){
            $(".pacs-cue").text("*His中未查找到响应检查项目");
            var t=setTimeout("$('.pacs-cue').text(' ')",2000);
            return false
        }
        var has=0;
        $(".medical-record-sheet .list").eq(6).find(".choose-list span").each(function(){
            if($(this).attr("btagmark")==bTagMark){
                has++;
            }
        });
        if(has>0){
            $(".pacs-cue").text("*您已添加该项目");
            var t=setTimeout("$('.pacs-cue').text(' ')",2000);
            return false
        }
        var li='';
        li+='<span btagmark="'+bTagMark+'"><b data-id="'+bTagMark+'">';
        var str=title;
        $(resl.data).each(function(){
            str+="("+this.structuringName+")"
        });
        li+=str+'</b><i style="display:none"></i><i></i></span>';

        $(".medical-record-sheet .list").eq(6).find(".choose-list").append($(li));
        changDelet();

        //$("#third-levl"+wholeid).hide();
        $(this).parent().parent().attr("id","third-levl"+bTagMark).hide();

    });

}


function fancyPacsbox(){
    $.fn.extend({
        fancyModal2:function(event) {//一个可选参数，给按钮绑定事件
            var obj={
                "fancy.modal.show":function () {},//模态框显示前触发
                "fancy.modal.shown":function (data) {},//模态框显示后触发
                "fancy.modal.hide":function () {},//模态框隐藏前触发
                "fancy.modal.hidden":function () {},//模态框隐藏后触发
                "fancy.modal.loaded":function (data) {}//数据加载完成触发(这时已经将动态数据插入html页面)
            };
            if((typeof(event)!=="object")){
                return false;
                throw new Error("请传入一个对象！！！");
            }
            obj=$.extend(obj,event);
            fancyModal2(this,obj);
            return this;
        }
    });
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
    $(".more-bt.pacs-more-bt").fancyModal2(obj);
    //模板类
    var Tmpl=function () {
        this.constructor=Tmpl;
        this.list="";
        this.detail=[];
        this.contentDetail='';
    };
    Tmpl.prototype.listHead='<div class="detail-category clearfix" style="display: none"><div class="second-level" style="width: 119px;"><dl class="method">';
    Tmpl.prototype.listFoot='</dl></div>';
    Tmpl.prototype.contentHead='<div class="level-list" style="display: none;" style="width: 630px; float: left;"><ul class="push-list clearfix">';
    Tmpl.prototype.contentFoot='</ul></div>';
    Tmpl.prototype.detailFoot='</div>';
    Tmpl.prototype.getHtml=function getHtml() {
        var html=this.listHead+this.list+this.listFoot,
            i;
        for(i=0;i<this.detail.length;i++){
            html+=this.detail[i];
        }
        html+=this.detailFoot;
        return html;
    };
    //将详细信息右侧的一页内容加入到数组中
    Tmpl.prototype.pushContent=function pushContent() {
        var html=this.contentHead+this.contentDetail+this.contentFoot;
        this.detail.push(html);
        this.contentDetail='';
    };

    function fancyModal2($button,obj) {
        var html='',
            id=new Date().getTime();
        //绑定数据加载完成触发的事件
        $button.on("fancy.modal.loaded",function () {
            obj["fancy.modal.loaded"]();
        });

        //id用毫秒数定义，不会冲突，根据button的click事件可以直接引用到此id
        html+='<div id="'+id+'" class="fancy-box"><div class="fancy-mask"></div><div class="det-form"></div><div style="left: 133px; top: 170.5px;" class="fancy-ct"><div class="nav-bar clearfix"><ul class="first-level clearfix">';
        //加载数据

        $.ajax({
            url:"http://192.168.2.165:8090/icss-web/kl/pacs/get_pacs_by_category",
            dataType:"json",
            type:"get",
            success:function (data) {
                html+=addTitle(data.data);
                html+='</ul><div class="toolbar"><p class="seach-box"><input type="text"></p><p class="set-up"><img src="img/6.png"> </p><p id="close"><img src="img/7.png"> </p></div></div>';
                /*
                $.each(data.data,function (i1,v1) {
                    html+=addDetail(v1);
                });
                */

                html+='<div class="detail-category clearfix" data-transverseid="call10085"><ul class="push-list clearfix">';
                $(".medical-record-sheet li").eq(6).find(".second-levl ul li").each(function(){
                    html+=this.outerHTML;
                });
                html+='</ul></div>';
                html+=addDetail(data.data.pacsPart,1);
                html+=addDetail(data.data.apparatusType,0);
                $('body').append(html);
                adjustRelation();
                addRelation();
                $button.on("click",function () {
                    var $modal= $("#"+id),
                        $ul=$modal.find("ul.first-level"),
                        $select_li=$ul.find("li.check-state");
                    if($select_li.length===0){
                        $ul.children("li:first").click();
                    }
                    $button.trigger("fancy.modal.show");
                    $("#"+id).css("display","block");
                    $button.trigger("fancy.modal.shown");
                });
                $button.trigger("fancy.modal.loaded");
            }
        });

        //根据数据加载上面的标题的函数
        function addTitle(data) {
            var html="";
            /*
            $.each(data,function (i,v) {
                html+='<li data-id="'+v.id+'">'+v.name+'</li>';
            });
            */
            html+='<li data-id="call10085">常用</li>';
            html+='<li data-id="call10086">手段</li>';
            html+='<li data-id="call10087">部位</li>';
            return html;
        }

        //加载详细数据
        function addDetail(data,hasendLevel) {
            var tmpl=new Tmpl();
            if(hasendLevel==1){
                $.each(data,function (i1,v1) {
                    if(v1.endLevel==="1"){
                        tmpl.list+='<dt data-id="'+v1.id+'" data-transverseId="call10087">'+v1.name;
                        addContentDetail(tmpl,v1.organList,v1.id);
                    }else if(v1.endLevel==="0"){
                        tmpl.list+='<dt class="third-level" data-id="'+v1.id+'" data-transverseId="call10087">'+v1.name;
                        $.each(v1.partList,function (i2,v2) {
                            tmpl.list+='<b data-id="'+v2.id+'" data-transverseId="call10087">'+v2.name+'</b>';
                            addContentDetail(tmpl,v2.organList,v2.id);
                        });
                    }
                    tmpl.list+='</dt>';
                });
            }else {
                $.each(data,function (i1,v1) {
                    tmpl.list+='<dt data-id="'+v1.id+'" data-transverseId="call10086">'+v1.name;
                    addContentDetail(tmpl,v1.apparatusList,v1.id);
                    tmpl.list+='</dt>';
                });
            }

            return tmpl.getHtml();
        }

        //加载详细数据右侧内容
        function addContentDetail(tmpl,subitemList,parentId) {
            $.each(subitemList,function (i,v) {
                tmpl.contentDetail+='<li data-id="'+v.id+'" data-portraitId="'+parentId+'">'+v.name+'</li>';
            });
            tmpl.pushContent();
        }

        //调整级联关系的id的标识位置，因为数据原因这个级联id在下一层数据中，要标识在外层div上
        function adjustRelation() {
            var $modal=$("#"+id),
                $details=$modal.find('div.detail-category'),
                $lists=$modal.find('div.level-list');
            $details.each(function (index) {
                var $detail=$(this),
                    $dts=$detail.find("dt");
                if(!$detail.attr('data-transverseid')){
                    $detail.attr('data-transverseid',$dts.eq(0).attr("data-transverseid"));
                }
                $dts.removeAttr("data-transverseid");

            });

            $lists.each(function (index) {
                var $list=$(this),
                    $lis=$list.find("li");
                if(!$list.attr('data-portraitid')){
                    $list.attr('data-portraitid',$lis.eq(0).attr("data-portraitid"));
                }
                $lis.removeAttr('data-portraitid');
            });

        }

        //添加关联事件
        function addRelation() {
            var $modal=$("#"+id),
                $first_level=$modal.find("ul.first-level"),
                $details=$modal.find("div.detail-category");

            //给button绑定各种事件
            $button.on("fancy.modal.show",function () {
                obj["fancy.modal.show"]($modal);
            });
            $button.on("fancy.modal.shown",function () {
                obj["fancy.modal.shown"]($modal);
            });
            $button.on("fancy.modal.hide",function () {
                obj["fancy.modal.hide"]($modal);
            });
            $button.on("fancy.modal.hidden",function () {
                obj["fancy.modal.hidden"]($modal);
            });

            //添加上侧标题的点击事件
            $first_level.find("li").on("click",function () {
                var $li=$(this),
                    data_id=$li.attr("data-id"),
                    $detail=$modal.find('div.detail-category[data-transverseid='+data_id+']'),
                    $dl=$detail.find("dl.method");
                $details.css("display","none");
                $detail.css("display","block");
                $li.addClass("check-state").siblings().removeClass("check-state");
                if($dl.children("dt.check-state").length===0){
                    $dl.children("dt:first").click();
                }
            });
            //添加详细左侧菜单的点击事件
            $modal.on("click","dt,b",function (e) {
                var $dt=$(e.target),
                    $detail=$dt.parents(".detail-category:first"),
                    data_portraitid=$dt.attr("data-id"),
                    $list=$detail.find("div.level-list[data-portraitid="+data_portraitid+"]"),
                    $lists=$detail.find("div.level-list");
                $dt.addClass("check-state").siblings().removeClass("check-state");
                $lists.css("display","none");
                $list.css("display","block");
            });


            //添加右侧疾病选中事件
            $details.find(".level-list li").on("click",function () {
                $(this).addClass("check-state").siblings().removeClass("check-state");
                var $fancy_ct=$(this).parents("div.fancy-ct:first"),
                    ttNews=$(".medical-record-sheet li").eq(6).find(".third-levl"),
                    id = $(this).attr("data-id"),
                    title = $(this).html(),
                    left=($(window).width()-$fancy_ct.width())/2,
                    top=($(window).height()-$fancy_ct.height())/2;
                dynamicPacsForm(ttNews,title);
                ttNews.css({left:left,top:top});
                $("#close").click();
            });
            //添加关闭模态框的事件
            $modal.find("#close").on("click",function () {
                $button.trigger("fancy.modal.hide");
                $modal.css("display","none");
                $button.trigger("fancy.modal.hidden");
            });
            //透明遮罩层点击事件
            $modal.children("div.fancy-mask").on("click",function () {
                $button.trigger("fancy.modal.hide");
                $modal.css("display","none");
                $button.trigger("fancy.modal.hidden");
            });
        }
    }
}

function fancyPacsMorePartbox(apparatusCode){
    $.fn.extend({
        fancyModal3:function(event) {//一个可选参数，给按钮绑定事件
            var obj={
                "fancy.modal.show":function () {},//模态框显示前触发
                "fancy.modal.shown":function (data) {},//模态框显示后触发
                "fancy.modal.hide":function () {},//模态框隐藏前触发
                "fancy.modal.hidden":function () {},//模态框隐藏后触发
                "fancy.modal.loaded":function (data) {}//数据加载完成触发(这时已经将动态数据插入html页面)
            };
            if((typeof(event)!=="object")){
                throw new Error("请传入一个对象！！！");
            }
            obj=$.extend(obj,event);
            fancyModal3(this,obj);
            return this;
        }
    });
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
    $(".pacs-add-more-part").fancyModal3(obj);
    //模板类
    var Tmpl=function () {
        this.constructor=Tmpl;
        this.list="";
        this.detail=[];
        this.contentDetail='';
    };
    Tmpl.prototype.listHead='<div class="detail-category clearfix" style="display: none"><div class="second-level" style="width: 119px;"><dl class="method">';
    Tmpl.prototype.listFoot='</dl></div>';
    Tmpl.prototype.contentHead='<div class="level-list" style="display: none;" style="width: 630px; float: left;"><ul class="push-list clearfix">';
    Tmpl.prototype.contentFoot='</ul></div>';
    Tmpl.prototype.detailFoot='</div>';
    Tmpl.prototype.getHtml=function getHtml() {
        var html=this.listHead+this.list+this.listFoot,
            i;
        for(i=0;i<this.detail.length;i++){
            html+=this.detail[i];
        }
        html+=this.detailFoot;
        return html;
    };
    //将详细信息右侧的一页内容加入到数组中
    Tmpl.prototype.pushContent=function pushContent() {
        var html=this.contentHead+this.contentDetail+this.contentFoot;
        this.detail.push(html);
        this.contentDetail='';
    };

    function fancyModal3($button,obj) {
        var html='',
            id=new Date().getTime();
        //绑定数据加载完成触发的事件
        $button.on("fancy.modal.loaded",function () {
            obj["fancy.modal.loaded"]();
        });

        //id用毫秒数定义，不会冲突，根据button的click事件可以直接引用到此id
        //点击多次部位会重新生成，先删除再生成
        $(".pacs-for-delete").remove();
        html+='<div id="'+id+'" class="fancy-box pacs-for-delete"><div class="fancy-mask"></div><div class="det-form"></div><div style="left: 133px; top: 170.5px;" class="fancy-ct"><div class="nav-bar clearfix"><ul class="first-level clearfix">';
        //加载数据

        $.ajax({
            url:"http://192.168.2.165:8090/icss-web/kl/pacs/get_pacs_part_by_apparatuscode?apparatusCode="+apparatusCode,
            dataType:"json",
            type:"get",
            success:function (data) {
                html+=addTitle(data.data);
                html+='</ul><div class="toolbar"><p class="seach-box"><input type="text"></p><p class="set-up"><img src="img/6.png"> </p><p id="close"><img src="img/7.png"> </p></div></div>';
                /*
                $.each(data.data,function (i1,v1) {
                    html+=addDetail(v1);
                });
                */
                html+=addDetail(data.data);
                $('body').append(html);
                adjustRelation();
                addRelation();
                $button.on("click",function () {
                    var $modal= $("#"+id),
                        $ul=$modal.find("ul.first-level"),
                        $select_li=$ul.find("li.check-state");
                    if($select_li.length===0){
                        $ul.children("li:first").click();
                    }
                    $button.trigger("fancy.modal.show");
                    $("#"+id).css("display","block");
                    $button.trigger("fancy.modal.shown");
                });
                $button.trigger("fancy.modal.loaded");
                $(".pacs-for-delete").css("display","block");
                $(".pacs-for-delete .first-level li:first").click();
            }
        });

        //根据数据加载上面的标题的函数
        function addTitle(data) {
            var html="";
            html+='<li data-id="call10087">部位</li>';
            return html;
        }

        //加载详细数据
        function addDetail(data) {
            var tmpl=new Tmpl();

            $.each(data,function (i1,v1) {
                if(v1.endLevel==="1"){
                    tmpl.list+='<dt data-id="'+v1.id+'" data-transverseId="call10087">'+v1.name;
                    addContentDetail(tmpl,v1.organList,v1.id);
                }else if(v1.endLevel==="0"){
                    tmpl.list+='<dt class="third-level" data-id="'+v1.id+'" data-transverseId="call10087">'+v1.name;
                    $.each(v1.partList,function (i2,v2) {
                        tmpl.list+='<b data-id="'+v2.id+'" data-transverseId="call10087">'+v2.name+'</b>';
                        addContentDetail(tmpl,v2.organList,v2.id);
                    });
                }
                tmpl.list+='</dt>';
            });


            return tmpl.getHtml();
        }

        //加载详细数据右侧内容
        function addContentDetail(tmpl,subitemList,parentId) {
            $.each(subitemList,function (i,v) {
                tmpl.contentDetail+='<li data-id="'+v.id+'" data-portraitId="'+parentId+'">'+v.name+'</li>';
            });
            tmpl.pushContent();
        }

        //调整级联关系的id的标识位置，因为数据原因这个级联id在下一层数据中，要标识在外层div上
        function adjustRelation() {
            var $modal=$("#"+id),
                $details=$modal.find('div.detail-category'),
                $lists=$modal.find('div.level-list');
            $details.each(function () {
                var $detail=$(this),
                    $dts=$detail.find("dt");
                if(!$detail.attr('data-transverseid')){
                    $detail.attr('data-transverseid',$dts.eq(0).attr("data-transverseid"));
                }
                $dts.removeAttr("data-transverseid");
            });

            $lists.each(function () {
                var $list=$(this),
                    $lis=$list.find("li");
                if(!$list.attr('data-portraitid')){
                    $list.attr('data-portraitid',$lis.eq(0).attr("data-portraitid"));
                }
                $lis.removeAttr('data-portraitid');
            });
        }

        //添加关联事件
        function addRelation() {
            var $modal=$("#"+id),
                $first_level=$modal.find("ul.first-level"),
                $details=$modal.find("div.detail-category");

            //给button绑定各种事件
            $button.on("fancy.modal.show",function () {
                obj["fancy.modal.show"]($modal);
            });
            $button.on("fancy.modal.shown",function () {
                obj["fancy.modal.shown"]($modal);
            });
            $button.on("fancy.modal.hide",function () {
                obj["fancy.modal.hide"]($modal);
            });
            $button.on("fancy.modal.hidden",function () {
                obj["fancy.modal.hidden"]($modal);
            });

            //添加上侧标题的点击事件
            $first_level.find("li").on("click",function () {
                var $li=$(this),
                    data_id=$li.attr("data-id"),
                    $detail=$modal.find('div.detail-category[data-transverseid='+data_id+']'),
                    $dl=$detail.find("dl.method");
                $details.css("display","none");
                $detail.css("display","block");
                $li.addClass("check-state").siblings().removeClass("check-state");
                if($dl.children("dt.check-state").length===0){
                    $dl.children("dt:first").click();
                }
            });
            //添加详细左侧菜单的点击事件
            $modal.on("click","dt,b",function (e) {
                var $dt=$(e.target),
                    $detail=$dt.parents(".detail-category:first"),
                    data_portraitid=$dt.attr("data-id"),
                    $list=$detail.find("div.level-list[data-portraitid="+data_portraitid+"]"),
                    $lists=$detail.find("div.level-list");
                $dt.addClass("check-state").siblings().removeClass("check-state");
                $lists.css("display","none");
                $list.css("display","block");
            });


            //添加右侧疾病选中事件
            $details.find(".level-list li").on("click",function () {
                $(this).addClass("check-state").siblings().removeClass("check-state");
                var $fancy_ct=$(this).parents("div.fancy-ct:first"),
                    ttNews=$(".medical-record-sheet li").eq(6).find(".third-levl"),
                    id = $(this).attr("data-id"),
                    title = $(this).html(),
                    left=($(window).width()-$fancy_ct.width())/2,
                    top=($(window).height()-$fancy_ct.height())/2;
                dynamicPacsForm(ttNews,title);
                ttNews.css({left:left,top:top});
                $("#close").click();
            });
            //添加关闭模态框的事件
            $modal.find("#close").on("click",function () {
                $button.trigger("fancy.modal.hide");
                $modal.css("display","none");
                $button.trigger("fancy.modal.hidden");
            });
            //透明遮罩层点击事件
            $modal.children("div.fancy-mask").on("click",function () {
                $button.trigger("fancy.modal.hide");
                $modal.css("display","none");
                $button.trigger("fancy.modal.hidden");
            });
        }
    }
}
