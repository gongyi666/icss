var icssLis = (function(){
    //所有化验单信息

    var USUAL_URL = Param.hostUrl+Param.get_lis_by_doctorId;
    //  var USUAL_URL = Param.hostUrl+Param.start_lis;
    var $group=$(".container .group").eq(5);//化验

    $group.find(".arrow.detail").click(function(){
        initLisMore();
        initLisMoreEvent();
        
        $group.find(".symptom-all").slideDown();
        $group.find(".symptom-form").slideUp();
         //-为已选项目添加 样式
        updateSelect();
    });
    $group.find(".symptom-all>.title>.arrow").click(function(){
        $group.find(".symptom-all").slideUp();
        $group.find(".symptom-wrap").slideDown();
        $group.find(".symptom-form").css("display","block");
    });
    bindLisClick();
    function initLisData (allListItem) {
        var html = '';
        for(var i = 0; i < allListItem.length; i++){
            html += 
                '<div class="symptom" data-param-code="" method="" sample="" liscode='+ allListItem[i].itemId +' lisname="血常规" standard-id="2093" style="visibility: visible;">'+ 
                    ''+
                    '<i class="iconfont icon-i"></i>'+
                '</div>';
        }
        $group.find(".symptom-container .symptom-wrap").html(html);
        //为新生成的symptom添加事件
        $group.find(".symptom-wrap").children("div.symptom").click(function(){
                common.cancelReturnLastStep($group.find('div.choose'));
                var formId=dynamicLisForm(this);
                addLisEvents(formId);
                $(this).parents(".symptom-wrap").css("display","none");
                $(".lis-main-input").val("");
                var _this = this;
                //判断是否显示information
                function checkArrow(el,has,none){
                    if(el.siblings(".down-arrow").length > 0){
                        el.parents(".select-only").css({
                             width: has + "px"
                        });
                        el.css({
                            width: has + "px"
                        });
                    }else {
                        el.parents(".select-only").css({
                             width: none + "px"
                        });
                        el.css({
                            width: none + "px"
                        });
                    }
                }
                var valLen = $(_this).text().length;//获取点击时文本的长度
                var $form_area_input = $("#" + formId).find(".lis-check-some input");
                checkArrow($form_area_input,70,70);
            });
                //触发新生成的symptom的点击事件，生成表单
                $group.find(".symptom-wrap").children("div.symptom").each(function(i,v){
                    $(v).click();
                    if(allListItem[i].title.checkboxClass == "true" ){
                       $("#lisCode" + $(v).attr("liscode")).find(".lis-checked-label").click();
                    }
                    //为表单进行数据赋值
                    $("#lisCode" + $(v).attr("liscode")).find("input[model]").each(function(inp,val){
                        var data = allListItem[i].contents;
                        
                        if(allListItem[i].title.checkboxClass == "true" ) {
                            
                            for(var k=0;k<data.length;k++){
                                if(data[k].type == $(val).attr("model")) {
                                    $(val).val(data[k].value);
                                }
                            }
                        }else {
                            for(var k=0;k<data.length;k++){
                                if(data[k].type == $(val).attr("model")) {
                                    $(val).val(data[k].value);
                                }
                            }
                        }
                    }); 
                });
  
             //触发 确定按钮的 点击 事件
            setTimeout(function(){
                $group.find(".lis-check-some .down-arrow.info").css({
                    left: "32px"
                });
                $group.find(".submit-bt .make-sure").click();
            },0);
    }
    function bindLisClick(){
        var PUSHCOUNT = 40;
        $group.click(function(e){
            $(".box-r-s.detail-info").hide();
            e.stopPropagation();
            updateSelect();
              
            return false;
        });
        $group.find("input:first").click(function(){
            common.autoScrollY($("body"),$(this).parents("div.group"),-170);
        	cleanCache(5);
            $(this).parents(".group").addClass("focus");
            common.groupHide($(this).parents(".group"));
            $(".symptom-all").slideUp();
            //if($group.find(".symptom-container").css("display")=="block"){return false}
            $group.find(".symptom-form").css("display","block");//因为common.js里的groupHide会在document click时将其变为隐藏
            $group.find(".symptom-wrap").children().remove();
            var paras={
                doctorId:Param.doctorId,
                size:PUSHCOUNT,
                sexType:Param.sexType,
                deptNo:Param.deptNo,
                hospitalCode:Param.hospitalCode,
                age:Param.age,
                patientId:Param.patientId,
                type:5
            }
            var resl = ajaxPost(USUAL_URL,paras);
            //接口更改
            // var resl = ajaxPost(Param.hostUrl+Param.get_lis_by_doctorId,paras);
            var li = ajaxPushLisList(resl.data);
            li+='<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';

            $group.find(".symptom-wrap").append($(li));
            $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                $(this).blueTip();
            });
            common.changeContainerShadowWidth($group.find("div.container-shade"));

            $group.find(".symptom-form .detail-list").css("display","none");
            $group.find(".symptom-wrap").css("display","block");

            addLisInformationEvents();
            // $(".symptom-container").slideUp();//注释可解决 bug - 2180
            
            //$group.find(".symptom-container .symptom-form").css("display","none");
            $group.find(".symptom-container").slideDown();
            moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
            
            
            $group.find(".symptom-wrap").children("div.symptom").click(function(){
                common.cancelReturnLastStep($group.find('div.choose'));
                var formId=dynamicLisForm(this);
                addLisEvents(formId);
                $(this).parents(".symptom-wrap").css("display","none");
                $(".lis-main-input").val("");
                var _this = this;
                //判断是否显示information
                function checkArrow(el,has,none){
                    if(el.siblings(".down-arrow").length > 0){
                        el.parents(".select-only").css({
                             width: has + "px"
                        });
                        el.css({
                            width: has + "px"
                        });
                    }else {
                        el.parents(".select-only").css({
                             width: none + "px"
                        });
                        el.css({
                            width: none + "px"
                        });
                    }
                }
                var valLen = $(_this).text().length;//获取点击时文本的长度
                var $form_area_input = $("#" + formId).find(".lis-check-some input");
                 checkArrow($form_area_input,70,70);
            });
        });


        //$group.find("input").keyup(function(){
        $group.find("input").on("input propertychange" , function(){
            $group.find(".symptom-all").slideUp();
            $group.find(".symptom-wrap").children().remove();
            var word=$(this).val();
            // notIds:
            var idStr = "";
             $(this).siblings(".choose").find(".content>p,.content>div").each(function(i,v){

                 var temp = $(v).attr("lisCode");
                 console.log(temp);
                 if(temp){
                    idStr +=temp.slice(7) +",";
                 }
            }); 
            idStr =  idStr.slice(0, idStr.length-1);
            if(word.length==0||word==" "){return false}
            var resl = ajaxPost(Param.hostUrl+Param.get_lis_by_input,{notIds:idStr,inputstr:word});
            var li = ajaxPushLisList(resl.data);
            li+='<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';

            $group.find(".symptom-wrap").append($(li));
            $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                $(this).blueTip();
            });
            common.changeContainerShadowWidth($group.find("div.container-shade"));

            if(resl.data.length>1){
                moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
            }
            $group.find(".symptom-wrap").children("div").click(function(){
                common.cancelReturnLastStep($group.find('div.choose'));
                var formId=dynamicLisForm(this);
                addLisEvents(formId);
                $(this).parents(".symptom-wrap").css("display","none");
                $(".lis-main-input").val("");
            });
            
        });
    }
    $(".lis,.pacs,.diagnosis").find("label").on("click", function() {
        //模拟document click隐藏推送条
        $(document).click();
    });
    

    function addLisInformationEvents(){
        var toastHtml = 
            '<div class="box-r-s detail-info" style="left: -49px;">'+
                '<p class="title"></p>'+
                '<p class="info">'+
                    '<span class="">1111111111111</span>'+
                    '<span class="d-n">222222222222</span>'+
                '</p>'+
            '</div>';
        // $group.find(".symptom-wrap").children().hover(function(){
        //     $(this).children("i").css("visibility","visible");
        //     //  $(this).children("i").append(toastHtml);//添加toast
        // },function(){
        //     $(this).children("i").css("visibility","hidden");
        // });

        /**
         * 点击小i图标 ， 显示 诊断依据 
         */
        $group.find(".symptom-wrap").children().children().click(function(e){
            $("body").css({
                "overflow-y":'hidden'
            });
            var itemId = $(this).parent().attr("liscode");//以下写死的itemid需要替换
            var resl=ajaxPost(Param.hostUrl+Param.get_by_itemidAndType,{type:'5', itemId:itemId});
            $("body").append($('<div id="informationShade"></div>'));
            $("#informationShade").height($(".container:first").height());
            var mark=new Date().getTime();
            $('#information1').tmpl(resl.data).appendTo('body').attr("id",mark);
            autoInformationHeight();
            palceModalMiddle(mark);
            common.informationControlToggle();
            $(".information .information-close").click(function(event){
                event.stopPropagation();
                $(".information#"+mark).remove();
                $("#informationShade").remove();

                //由于弹出诊断依据后，滚动鼠标后底下的页面也会移动
                $("body").css({
                    "overflow-y":'scroll'
                });
            });
	    $(".information").fadeIn();
            e.stopPropagation();
        });
    }
  
    function ajaxPushLisList(data){
        var list=[];//list表示已选择的项目，不显示出来
        $group.find(".choose .content").children().each(function(){
            list.push($(this).attr("liscode").substring(7));
        });
        var li='';
        $(data).each(function(){
            if($.inArray(this.id.toString(), list)>=0){return true}
            if(this.name.length>5){
                li+='<div class="symptom" data-param-code="'+this.paramCode+'" method="'+this.method+'" sample="'+this.sample+'" liscode="'
                +this.id+'" lisname="'+this.name+'" standard-id="'+this.standardId+'" data-symptom-name="'+this.name+'">'+this.name
                +'<i class="iconfont icon-i"></i></div>';
            }else{
                li+='<div class="symptom" data-param-code="'+this.paramCode+'" method="'+this.method+'" sample="'+this.sample+'" liscode="'
                +this.id+'" lisname="'+this.name+'" standard-id="'+this.standardId+'">'+this.name
                +'<i class="iconfont icon-i"></i></div>';
            }
        });
        return li;
    }

    //-为已选项目添加 样式
    //-由于点击确定后，还未将选择的数据插入页面中，所以采用定时器
    function updateSelect(){
         //-为已选项目添加 样式
        //-由于点击确定后，还未将选择的数据插入页面中，所以采用定时器
        var timer = setTimeout(function(){
            var $p_list = $group.find('.choose .content p,.choose .content div');
            $p_list.each(function(i,v){
                var id = $(v).attr("standard-id");
                $group.find(".wrap p[standard-id="+ id +"]").addClass('high-light-color');
            })
        },0)
    }
    function initLisMoreEvent(){
        /*更多的切换事件*/
        $group.find(".button-group").children("button").click(function(){
            $(this).addClass("focus").siblings().removeClass("focus");
            $group.find(".div-group[tagnum="+$(this).attr("tagnum")+"]").removeClass("d-n").siblings().addClass("d-n");
            $group.find(".div-group[tagnum="+$(this).attr("tagnum")+"]").find(".menu li a:first").click();

            
                //-为已选项目添加 样式
                updateSelect();
            
        });


        

        /*更多的切换事件*/
        $group.find(".symptom-all p[data-symptom-name]").each(function(){
            $(this).blueTip();
        });
    }

    function initLisMore(){
        var row='';
        var col='';
        var colleft='';
        var colright='';
        var resl=ajaxPost(Param.hostUrl+Param.get_transverse_portrait_lis,{type:5});
        $(resl.data).each(function(index1){
            if(index1==0){
                row+='<button tagnum="'+index1+'" class="focus">'+this.name+'</button>';
                col+='<div class="div-group wrap-content" tagnum="'+index1+'">';
            }else{
                row+='<button tagnum="'+index1+'">'+this.name+'</button>';
                col+='<div class="div-group wrap-content d-n" tagnum="'+index1+'">';
            }


            if(this.portraitList.length>0){
                colleft='<div class="menu"><ul class="first-level">';
                colright='';

                $(this.portraitList).each(function(){
                    if(this.portraitList.length>0){
                        colleft+='<li><a tagid="'+this.id+'"><i class="iconfont icon-right-triangle"></i>'+this.name+'</a><ul class="second-level d-n">';
                        $(this.portraitList).each(function(){
                            colleft+='<li><a tagid="'+this.id+'">'+this.name+'</a></li>';
                            colright+='<div class="content d-n" tagid="'+this.id+'">';
                            $(this.detailList).each(function(){
                                if(this.name.length>5){
                                    colright+='<p class="symptom" method="'+this.method+'" sample="'+this.sample+'" liscode="'
                                    +this.id+'" lisname="'+this.name+'" standard-id="'+this.standardId+'" data-param-code="'+this.paramCode+'" data-symptom-name="'+this.name+'">'+this.name+'</p>'
                                }else{
                                    colright+='<p class="symptom" method="'+this.method+'" sample="'+this.sample+'" liscode="'
                                    +this.id+'" lisname="'+this.name+'" standard-id="'+this.standardId+'" data-param-code="'+this.paramCode+'">'+this.name+'</p>'
                                }
                            });
                            colright+='</div>';
                        });
                        colleft+='</ul></li>';

                    }else{
                        colleft+='<li><a tagid="'+this.id+'"><i class="iconfont icon-right-triangle"></i>'+this.name+'</a></li>'
                        colright+='<div class="content d-n" tagid="'+this.id+'">';
                        $(this.detailList).each(function(){
                            if(this.name.length>5){
                                colright+='<p class="symptom" method="'+this.method+'" sample="'+this.sample+'" liscode="'
                                +this.id+'" lisname="'+this.name+'" standard-id="'+this.standardId+'" data-param-code="'+this.paramCode+'" data-symptom-name="'+this.name+'">'+this.name+'</p>'
                            }else{
                                colright+='<p class="symptom" method="'+this.method+'" sample="'+this.sample+'" liscode="'
                                +this.id+'" lisname="'+this.name+'" standard-id="'+this.standardId+'" data-param-code="'+this.paramCode+'">'+this.name+'</p>'
                            }

                        });
                        colright+='</div>';

                    }
                });
                colleft+='</ul></div>';

            }
            col+=colleft+colright;
            col+='</div>';
        });

        $group.find(".symptom-all .button-group button").remove();
        $group.find(".symptom-all .wrap .wrap-content").remove();
        $group.find(".symptom-all .button-group").prepend($(row));
        $group.find(".symptom-all .wrap").prepend($(col));


        var li="";//常用
        var paras={
            doctorId:Param.doctorId,
            size:40,
            sexType:Param.sexType,
            deptNo:Param.deptNo,
            hospitalCode:Param.hospitalCode,
            age:Param.age,
            patientId:Param.patientId,
            type:5
        }
        //接口更改
        //var commonresl = ajaxPost(Param.hostUrl+Param.get_lis_by_doctorId,paras);
	    var commonresl = ajaxPost(USUAL_URL,paras);
        li+='<div class="content" style="width:100%">';
        $(commonresl.data).each(function(){
            if(this.name.length>5){
                li+='<p class="symptom" liscode="'+this.id+'" lisname="'+this.name+'" method="'+this.method+'" sample="'+this.sample+'" standard-id="'+this.standardId+'" data-param-code="'+this.paramCode+'" data-symptom-name="'+this.name+'">'+this.name+'</p>'
            }else{
                li+='<p class="symptom" liscode="'+this.id+'" lisname="'+this.name+'" method="'+this.method+'" sample="'+this.sample+'" standard-id="'+this.standardId+'" data-param-code="'+this.paramCode+'">'+this.name+'</p>'
            }
        });
        li+='</div>';
        $group.find(".wrap-content[tagnum=0]").append($(li));


        li="";//第一级tag的div
        $(resl.data[1].portraitList).each(function(){
            if(this.portraitList.length>0){
                li+='<div class="content d-n" tagid="'+this.id+'">';
                $(this.portraitList).each(function(){
                    li+='<p class="title">'+this.name+'</p>';
                    $(this.detailList).each(function(){
                        if(this.name.length>5){
                            li+='<p class="symptom" liscode="'+this.id+'" lisname="'+this.name+'" standard-id="'+this.standardId+'" data-param-code="'+this.paramCode+'"  data-symptom-name="'+this.name+'">'+this.name +'</p>'
                        }else{
                            li+='<p class="symptom" liscode="'+this.id+'" lisname="'+this.name+'" standard-id="'+this.standardId+'" data-param-code="'+this.paramCode+'">'+this.name+'</p>'
                        }
                    });
                });
                li+='</div>';
            }
        });
        $group.find(".wrap-content[tagnum=1]").append($(li));

        $group.find(".wrap-content a").click(function(){
            $(this).parents(".second-level").siblings("a").removeClass("focus");
            $(this).siblings(".second-level").find("a").removeClass("focus");
            $(this).siblings("ul").removeClass("d-n");$(this).parent().siblings().find("ul").addClass("d-n");
            $(this).addClass("focus");$(this).parent().siblings().find("a").removeClass("focus");

            $(this).parents(".wrap-content").find(".content[tagid="+$(this).attr("tagid")+"]").removeClass("d-n").siblings(".content").addClass("d-n");
        });
        //为以选择的选项添加颜色显示
        var addColorClass = function($el){
            $el.addClass("high-light-color");
        }
        $group.find(".wrap-content .content p.symptom").click(function(){
            common.cancelReturnLastStep($group.find('div.choose'));
            var formId=dynamicLisForm(this);
            addLisEvents(formId);
            $group.find(".symptom-all").slideUp();
            $group.find(".symptom-wrap").css("display","none");
            $group.find(".symptom-form").slideDown();

        });

    }

    function dynamicLisForm(el){
        
        var appendArea=$group.find(".symptom-form");

        var code=$(el).attr("liscode");
        var sample=$(el).attr("sample");
        var method=$(el).attr("method");
        var paramCode=$(el).attr("data-param-code");
        var standardId=$(el).attr("standard-id");
        if($("#lisCode"+code).length>0){
            //appendArea.slideDown();
            if($("#lisCode"+code).css("display")=="block"){return false}
            appendArea.children().css("display","none");
            appendArea.css("display","block");
            $("#lisCode"+code).slideDown();
            return false;
        }
        appendArea.children().css("display","none");
        var title=$(el).attr("lisname");
        // var checkNum =new Date().getDates() +"";
        var checkNum = "";
        appendArea.append(
            $('<div dis-id = '+ code +' standard-id="'+standardId+'" data-param-code="'+paramCode+'" id="lisCode'+code+'" class="detail-list" style="display:none">'+
                '<h3>'+
                    '<b style="line-height:20px;" class="toptitle">'+title+'</b>'+
                    '<div class="c-r checkbox lis-checked-label">'+
                        '<div class="box-r-s">'+
                            '<i class="iconfont icon-duigou1"></i>'+
                        '</div>'+
                        '已检'+
                    '</div>'+
                    '<div class="cost" style="float:right;">'
                        + checkNum +'费用: '+
                    '</div>'+
                '</h3>'+
                '<div class="form-area">'+
                    '<div class="row lis-check-some"   style="float:left;"></div>'+
                    '<div class="row lis-check-some2"  style="float:left;"></div>'+
                    '<div class="row lis-check-some3"  style="float:left;"></div>'+
                    '<div class="row lis-check-some4"  style="float:left;"></div>'+
                    '<div style="clear:both"></div>'+
                '</div>'+
                '<div class="form-area-checked-lis" style="display:none;"></div>'+
                '<p class="submit-bt">'+
                    '<button class="make-sure usable" type="button">确定</button>'+
                    '<button class="cancle unusable" type="button">取消</button>'+
                '</p>'+
            '</div>'));
        var a="lisCode"+code;//动态生成弹出的表单
        var $currentForm = $(document.getElementById(a));
        var $form_area = $currentForm.find(".form-area .lis-check-some");

        var result=ajaxPost(Param.hostUrl+Param.lis_get_by_infoid,{hospitalCode:Param.hospitalCode,id:code});//获取的明细，用于下拉显示和已检,因为数据关系，将id为1
        // var result=ajaxPost(Param.hostUrl+Param.lis_get_by_infoid,{hospitalCode:'1006',id:code});
        //- bug-2034 器查-没有明细的部位后面不显示i图标，且直接显示文本不需要显示文本框的外框。化验存在同样的问题。
        //思路： 直接判断子级是否存在数据，分情况进行渲染
        if(result.data.length > 1){
            $form_area.append(
                $('<div class="form-group">'+
                    '<label style="display: none;">项目</label>'+
                    '<div class="select-only" >'+
                        '<input style="display: none;" model="1" name="project" type="text" readonly="" value="'+title+'" code="'+code+'">'+
                        '<div class="down-arrow info">'+
                            '<div class="info-icon"></div>'+
                        '</div>'+
                        '<ul class="box-r-s d-n"></ul>'+
                    '</div>'+
                '</div>'));
            $form_area.find(".form-group ul").append($(getLisGetInfo(result.data)));
        }else {
            $form_area.append($('<div class="form-group"><label style="display: none;>项目</label><div class="select-only">'
            +'<input style="display: none;" model="1" name="project" type="text" readonly="" value="'+title+'" code="'+code+'">'
            +'<ul class="box-r-s d-n"></ul></div></div>'));

            $form_area.find("input").css("border","none");
            $form_area.find(".form-group ul").css({
                "border": "none"
            });
        }
        //- 项目详细信息
        function getLisGetInfo(data){
            var li='';
            $(data).each(function(){
                li+='<li><a temp="'+this.name+'" code="'+this.code+'">'+this.name+'</a>';
                li+='</li>'
            });
            return li
        }

        if(sample){
             var sampleList = sample.split(",");
        }else{
             var sampleList = [""];
        }
        // var sampleList = [1,2,3];//假数据
        if(method){
            var methodList = method.split(",");
        }else{
            var methodList = [""];
        }
        // var methodList = [11,22,33];//假数据
        /**
         * 生成列表li的函数
         * @param { arr} list 
         */ 
        function generateListHtml(list){
            var listHtml = "";
            for(var i = 0; i < list.length; i++) {
                listHtml += '<li><a totalid="" temp="sampleList" code="" ap-standard-id="">'+ list[i] +'</a></li>';
            }
            return listHtml;
        }
        /**
         * genrateDropHtml 生成 下拉框和列表的函数
         * @param {生成列表li的函数} generateListHtml 
         * @param {生成列表li所需要的数据 arr} listData 
         */ 
        function genrateDropHtml(generateListHtml,listData) {
             return '<div class="down-arrow">'+ 
                        '<i class="iconfont icon-triangle-down-copy"></i>'+
                    '</div>'+
                    '<ul class="box-r-s d-n">'+
                        generateListHtml(listData) +
                    '</ul>';
        }
        var arrowHtmlsample = genrateDropHtml(generateListHtml, sampleList);
        var arrowHtmlmethod = genrateDropHtml(generateListHtml, methodList);

        $form_area = $(document.getElementById(a)).find(".form-area .lis-check-some2");
        $form_area.append(
            $('<div class="form-group select w-70px">'+
                '<div class="select  w-70px">'+
                    '<label>标本</label>'+
                    '<input class="box-r-s" model="2"  name="sample" type="text" inputval="'+ sampleList[0] +'" data-symptom-name='+ sampleList[0] +' readonly="" value="'+sampleList[0]+'">'+
                '</div>'+
            '</div>'));

        $form_area = $(document.getElementById(a)).find(".form-area .lis-check-some3");
        $form_area.append(
            $('<div class="form-group select w-70px">'+
                '<div class="select w-70p" >'+
                    '<label>方法</label>'+
                    '<input class="box-r-s" model="3" name="method" type="text" inputval="'+ methodList[0] +'" data-symptom-name='+ methodList[0] +' readonly="" value="'+methodList[0]+'">'+
                '</div>'+
            '</div>'));
        
        $form_area = $(document.getElementById(a)).find(".form-area .lis-check-some4");
        $form_area.append(
            $('<div class="form-group">'+
                '<label>备注</label>'+
                '<input name="remark" class="box-r-s" model="4" type="text" style="width:230px;">'+
            '</div>'));
        
        var $form_area_checked_lis =$(document.getElementById(a)).find(".form-area-checked-lis");
        $('#dateunit1').tmpl().addClass("lis-checked-date").appendTo($form_area_checked_lis);//已检里面的时间事件在①
        $form_area_checked_lis.append($(getLisCheckedList()));
        $form_area_checked_lis.append($('<div style="clear:both;"></div>'));

        $currentForm.find(".lis-check-some").css({
                width: "85px"
            }); 
        //标本  此处进行判断是否是 单个 条目，如果是多个就显示列表
        if(sampleList.length > 1) {
            $currentForm.find(".lis-check-some2 .form-group .select").append(arrowHtmlsample);
        }else {
            $currentForm.find(".lis-check-some2 .form-group .select input").css({
                boxShadow: "none",
                paddingLeft: 0
            }).addClass("borderNone");
            $currentForm.find(".lis-check-some2").css({
                width: "85px"
            }); 
        }
        $currentForm.find(".lis-check-some ul").hide();
        //方法 此处进行判断是否是 单个 条目，如果是多个就显示列表
        if(methodList.length > 1) {
            $currentForm.find(".lis-check-some3 .form-group .select").append(arrowHtmlmethod);
        }else {
            $currentForm.find(".lis-check-some3 .form-group .select input").css({
                boxShadow: "none",
                paddingLeft: 0
            }).addClass("borderNone");
            $currentForm.find(".lis-check-some3").css({
                width: "85px"
            });
        }
        
        
        var some2Input = $currentForm.find(".lis-check-some2 .form-group input");
        var some3Input = $currentForm.find(".lis-check-some3 .form-group  input");
        
        if(some2Input.val()) {
             var valLength2 =  some2Input.val().length;
        }else {
            var valLength2 = 0;
        }
        if(some3Input.val()) {
            var valLength3 =  some3Input.val().length;
        }else {
            var valLength3 = 0;
        }

        if(valLength2 > 5){
            some2Input.val(some2Input.val().slice(0,5)+"...");
        }
        if(valLength3 > 5){
            some3Input.val(some3Input.val().slice(0,5)+"...");
        }

        setTimeout(function(){
             var textWidth = $currentForm.find("h3 b")[0].offsetWidth;
             $currentForm.find(".lis-check-some .down-arrow").css({'left':textWidth-5})
        },0);
        //显示下拉列表
        function showDropList() {
            
            $currentForm.find(".row").not(".lis-check-some").find("input").on("click", function(event) {
                toggleList(this);
                event.stopPropagation();
            });
            $currentForm.find(".row").not(".lis-check-some").find(".down-arrow").on("click", function(event) {
                 toggleList(this);
                 event.stopPropagation();
            });
             $currentForm.find(".lis-check-some").find(".down-arrow").on("click", function(event) {
                 toggleList(this);
                 event.stopPropagation();
            });
            /**
             * 切换 下拉list
             * @param {this} _this 
             */ 
            function toggleList(_this){
                var $currentUl = $(_this).siblings("ul");
                if($(_this).siblings("ul:visible").length > 0){
                    $currentUl.slideUp();
                }else {
                    $(_this).siblings("ul").slideDown();
                }
                $currentUl.one("click","li", function(event) {
                    $currentUl.siblings("input").val($(this).children("a").text())
                    $currentUl.hide();
                    event.stopPropagation();
                });
            }
        }
        $currentForm.on("click", function() {
             $(this).find("ul").hide();
             return false;
        });
        
        // 显示下拉列表函数调用
        showDropList();
        
        function getLisCheckedList(){
            
            var li="";
            $(result.data).each(function(index, value){
                
                if(value.type == 4){
                    for(var i=0,l=value.lisDetailContents;i < l.length; i++){   
                        li += '<div data-param-code='+ l[i].paramCode +' model=t'+ i +' orderNo = '+ l[i].orderNo +' standardId= '+l[i].standardId+' pacs-checked-list-id='+ l[i].id +' class="c-r checkbox" style="float:none;font-weight:700;margin-top:5px;">'+
                            '<div class="box-r-s">'+
                                '<i class="iconfont icon-duigou1"></i>'+
                            '</div>'+
                            l[i].name+
                        '</div>';
                    }
                }else if(value.type == 2) {
                     li+='<div class="form-group"><label>'+value.labelPrefix+'</label><input data-param-code="'+value.paramCode+'" model=in'+ index +' class="box-r-s" name="' //这里的model是表示input输入框
                    +value.textName+'" type="text"><span>'+(value.labelSuffix==null?" ":value.labelSuffix)+'</span></div>';
                }else if(value.type == 3){
                    var form_group = '';
                    for(var i=0; i < value.lisDetailContents.length; i++){
                        li +='<li><a totalid="" temp="" data-param-code='+ value.lisDetailContents[i].paramCode +' code="null" ap-standard-id="">'+ value.lisDetailContents[i].name +'</a></li>';
                    }

                    li =
                    '<div class="form-group" data-param-code=' + value.paramCode+ '>'+ 
                        '<label>'+ value.name +'</label>'+
                        '<div class="select box-r-s w-100">'+
                            '<input type="text" readonly="" value="" code=""   model=drop'+ index +' name="apparatus">'+ //model  表示下拉列表
                            '<div class="down-arrow">'+
                                '<i class="iconfont icon-triangle-down-copy"></i>'+
                            '</div>'+
                            '<ul class="box-r-s d-n" style="overflow-y: auto; display: none;">' + li
                            '</ul>'+
                        '</div>';
                    li = '<div '+
                             li +
                             '<span> </span>'+
                        '</div></div>';
                }
                // else if(this.type == 3) {
                //      li+='<div class="form-group"><label>'+this.labelPrefix+'</label><input data-param-code="'+this.paramCode+'" class="box-r-s" name="'
                //     +this.textName+'" type="text"><span>'+(this.labelSuffix==null?" ":this.labelSuffix)+'</span></div>'
                // }
            });
            return li;
        }
        
        //appendArea.slideDown();
        $("#lisCode"+code).slideDown();
        return a;
    }

    function addLisEvents(formId){
        var $eventarea=$(document.getElementById(formId));
        $eventarea.click(function(){
            common.selectOnlyHide();
            common.selectHide();
            common.removeFocus();
        });
        $eventarea.find("div.c-r.checkbox").each(function(){
            $(this).on("click.common",function () {
                var $checkbox=$(this);
                common.checkboxChangeState($checkbox);
            });
        });

        $eventarea.find("div.select-only").on("click.common",function (e) {
           if(e.target.nodeName=="DIV"||e.target.nodeName=="I"){
               var $select=$(this);
               common.selectOnlyHide($select);
               $select.children("ul").fadeIn();
               e.stopPropagation();
           }

        });

        $eventarea.find("div.select.box-r-s").on("click.common",function (e) {
           var $select=$(this);
           common.selectHide($select);
           $select.addClass("focus").children("ul").fadeIn();
           e.stopPropagation();
        });

        /*** select的下拉菜单的点击事件,已检里的时间，不包括项目的下拉框***/
        $eventarea.find('.form-area-checked-lis ul.box-r-s').on("click.common","a,input,div",function (e) {
           var $node=$(e.target),
                $a=$node.parents("a:first");

           if(e.target.nodeName==="A"){
               if($node.children("div.c-r.checkbox").length===0){
                   common.selectSingleChoiceByText($node);
                   if(lisValidateTime(e.target)){
                       $eventarea.find(".validate").removeClass("d-n");
                       $eventarea.find(".make-sure.usable").removeAttr("disabled").removeClass("disabled");
                       $eventarea.find(".validate").addClass("d-n");
                   }else{
                       $eventarea.find(".validate").addClass("d-n");
                    //    $eventarea.find(".make-sure.usable").addClass("disabled");
                       $eventarea.find(".validate").removeClass("d-n");
                   }
               }
           }else{
               if($node.siblings().length===0 && e.target.nodeName==="INPUT"){
                   common.selectSingleChoiceByText($a);
               }else{
                   common.selectMultipleChoiceByCheckbox($a);
               }
           }
           common.selectHide();
           e.stopPropagation();
        });

        $eventarea.find(".lis-checked-label").click(function(){
            
            // 时间格式化
            function   formatDate(now)   {     
              var   year=now.getFullYear();     
              var   month=now.getMonth()+1;     
              var   date=now.getDate();     
              var   hour=now.getHours();     
              var   minute=now.getMinutes();     
              var   second=now.getSeconds();     
              return   year+"-"+month+"-"+date+"   "+hour+":"+minute+":"+second;     
              }   
            
            function setInputDefaultDate($eventarea){

                var date = formatDate(new Date()).split(" ")[0].toString().split("-");
                // var year = date[0];
                // var month = date[1];
                // var day = date[2];
                $eventarea.find(".date input").each(function(i, v) {
                    $(v).val(date[i]);
                });

            }
            setInputDefaultDate($eventarea);
            // $(this).parents(".detail-list").find(".form-area-checked-lis").find("input").val("");//清空日期框-input的数据
            $eventarea.find(".validate").addClass("d-n");
            if($(this).hasClass("checked")){
                $(this).parents(".detail-list").find(".form-area").css("display","none");
                $(this).parents(".detail-list").find(".form-area-checked-lis").css("display","block");
                $(this).parents(".detail-list").find(".cost").css("display","none");

                //$eventarea.find(".make-sure.usable").attr("disabled","true").addClass("disabled");//①
            }else{
                $(this).parents(".detail-list").find(".form-area").css("display","block");
                $(this).parents(".detail-list").find(".form-area-checked-lis").css("display","none");
                $(this).parents(".detail-list").find(".cost").css("display","block");

                //$eventarea.find(".make-sure.usable").removeAttr("disabled").removeClass("disabled");//①
            }
            /**
             * 检测时间选项的是否填写
             */ 
            function checkDateStatus() {
                var count = 0;
                $eventarea.find(".date:visible .select input").each(function(i,v) {
                    if(!!$(v).val()){
                        count++;
                    }
                });
                if(count == 3){
                     $eventarea.find(".make-sure").removeClass("disabled");
                }
            }
            
            //- ① 判断如果切换 “已检” 重置 确定按钮
            var $submit_bt = $(this).parent().siblings(".submit-bt");
            if($(this).hasClass("checked")){
                var $down_arrow = $(this).parent().siblings(".form-area-checked-pacs").find(".down-arrow");
                vaildeDate({"$down_arrow": $down_arrow, "$submit_bt": $submit_bt});
            }else {
                $submit_bt.find(".make-sure").removeClass("disabled");
            }
            checkDateStatus();

        });
        
        //② 校验日期是否选择
        function vaildeDate(option){
            //校验是否选择全部日期
            if( !lisValidateTime(option.$down_arrow) ){
                // option.$submit_bt.find(".make-sure").addClass("disabled");
            }
        }

        //给确定按钮添加事件，输出选择好的拼出来的信息
        $eventarea.children(".submit-bt").on("click", "button",generateItem);
        
        /**
         * 生成化验项目列表
         * @param {*} e 
         */ 
        function generateItem(e) {
            //点击取消按钮
            if (e.target && e.target.className !== "make-sure usable") {
                if($group.find("div.choose").data("lastStep")=="symptom-wrap"){
                    $eventarea.slideUp();
                    $group.find(".symptom-wrap").css("display","block");
                    moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
                }else{
                    $eventarea.slideUp();
                    $group.find(".symptom-all").slideDown();
                }
                if($group.find(".symptom-container .symptom").length == 0) {
                    $group.find(".symptom-container").hide();
                     $group.find(".symptom-all").hide();
                }
                return;
            }
            
            $group.find(".choose").find("[liscode="+formId+"]").remove();
            if($eventarea.find(".lis-checked-label").hasClass("checked")){

                var checkedinfo='';

                var checkBoxHtml = '';
                 $eventarea.find(".form-area-checked-lis .checkbox.checked").each(function(i,v){
                     checkBoxHtml += '<li style="overflow:hidden;width:280px;">'+
                                        '<span>'+ $(v).text() +'</span>'+
                                    '</li>'
                 }); 

                checkedinfo+='<ul>'+$eventarea.find(".toptitle").text()+':</ul>';
                checkedinfo+='<ul>';
                $eventarea.find(".form-area-checked-lis .form-group").not(".lis-checked-date").each(function(){
                    if(getVal($(this).find("input:first")).length>0){
                        checkedinfo+='<li style="overflow:hidden;width:280px;"><span style="width:150px;">'
                        +$(this).find("label:first").text()+'</span><span>'+getVal($(this).find("input:first"))
                        +'</span><span style="font-weight:bold;">'+$(this).find("span:first").text()+'</span></li>'
                    }
                });
                checkedinfo+=checkBoxHtml +'</ul>';
                var checktime="";
                $eventarea.find(".form-area-checked-lis .lis-checked-date input").each(function(index){
                    checktime+=$(this).val();
                    if(index==0){checktime+="年"}
                    if(index==1){checktime+="月"}
                    if(index==2){checktime+="日"}
                });
                    
                checkedinfo+='<ul style="color:#ccc">'+checktime+'</ul>';
                var checkedli='<div standard-id="'+$group.find(".symptom-wrap").find("div[liscode="+formId.substring(7)+"]").attr("standard-id")
                +'" liscode="'+formId+'" style="overflow:hidden"><span class="dot"></span>'+checkedinfo+'<i></i></div>';
            }else{
                
                var titleH3 = $eventarea.find("h3 .toptitle").text(); 
                var info=titleH3;
                //修复bug - 2168
                $eventarea.find(".form-area .form-group").not(".lis-checked-date").each(function(index){
                    if(index > 0){
                        var $node=$(this).find("input:first");
                        if($node.length<1||($node.val() + '').trim().length<1) return true;
                        if(index == 1) {
                            info+= " : <span class='colorGray'>( ";
                        }
                        if(index>1){
                            info+=" , "
                        }//项目后面的内容添加上“，”
                        info+=getVal($node);
                    }
                });
                info = info.replace("undefined", "");
                info+=" , 待检查)";
                var resl = {
                    data:[]        
                }
                // var resl=ajaxPost(Param.hostUrl+Param.get_lis,{hospitalCode:Param.hospitalCode,id:1000000000000000000000});
                // if(resl.data == null ){
                //     //alert("未搜索到HIS项目");
                //     // return false;
                // }else if(resl.data[0] === null){ //-bug-1984
                //     //alert("未搜索到HIS项目数据");
                //     // return false;
                // }else{
                    
                // }
                //- 注释掉的 是 （）内的内容

                // info+="(";
                // console.log(resl);
                var mintotal=0;
                var minindex=0;
                $(resl.data).each(function(index){
                    if(index==0){mintotal=this.totalCount}
                    if(this.totalCount<mintotal){minindex=index}
                });

                // info+=resl.data[minindex].name;
                // info+=')'+'<span style="color:#999"></span>';
                info+='<span style="color:#999"></span>';
                var li='<p data-relation-id=" " liscode="'+formId+'" hiscode="resl.data[minindex].code" lisname="'+$eventarea.find(".toptitle").text()+'" hisname="resl.data[minindex].name" standard-id="'+$eventarea.attr("standard-id")+'">'+
                            '<span class="dot"></span>' + 
                            info+
                            '<i style="display:none!important"></i>'+
                            '<i></i>'+
                        '</p>';
            }
            var $li = $(li==undefined?checkedli:li);
            $li.click(function(){
                //模仿一次document。click
                common.selectHide();
                common.removeFocus();
                common.groupHide($group);

                $group.find(".symptom-container").css("display","block");
                $group.find(".symptom-form").fadeIn();
                $("#"+$(this).attr("liscode")).slideDown().siblings().css("display","none");
                $group.find(".symptom-wrap").css("display","none");
                $group.find(".symptom-all").slideUp();
            });
            $li.find("i:last").click(function(e){
                //var t=confirm('是否删除化验内容？');
                $(this).parents("[liscode]").remove();
                var t=true;

                if(!t){return false}
                e.stopPropagation();

                //点击删除时，删除掉原详细框
                $("#"+formId).remove();
                
                $group.find(".content p.symptom[liscode="+formId.substring(7)+"]").removeClass("focus");
                //重新生成推送
                $group.find(".symptom-wrap").children().remove();
                var paras={
                    doctorId:Param.doctorId,
                    size:40,
                    sexType:Param.sexType,
                    deptNo:Param.deptNo,
                    hospitalCode:Param.hospitalCode,
                    age:Param.age,
                    patientId:Param.patientId,
                    type:5
                }
                //接口更改
                // var resl = ajaxPost(Param.hostUrl+Param.get_lis_by_doctorId,paras);
                var resl = ajaxPost(USUAL_URL,paras);
                var li = ajaxPushLisList(resl.data);

                li+='<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';

                $group.find(".symptom-wrap").append($(li));
                $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                    $(this).blueTip();
                });
                common.changeContainerShadowWidth($group.find("div.container-shade"));
                $group.find(".symptom-wrap").css("display","block");
                moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));

                $group.find(".symptom-wrap").children("div").click(function(){
                    common.cancelReturnLastStep($group.find('div.choose'));
                    var formId=dynamicLisForm(this);
                    addLisEvents(formId);
                    $(this).parents(".symptom-wrap").css("display","none");
                    $(".lis-main-input").val("");
                });
                showOrHideChoose();
                forDeleteCache();
            });
            $group.find(".choose").find("p[liscode="+formId+"]").remove();
            $group.find(".choose .content").append($($li));

            $eventarea.slideUp();
            $group.find(".symptom-wrap").css("display","block");
            $group.find(".symptom-wrap").find("div[liscode="+formId.substring(7)+"]").remove();
            $group.find(".content p.symptom[liscode="+formId.substring(7)+"]").addClass("focus");
            moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
            $group.find(".symptom-container").css("display","none");
            showOrHideChoose();
            serverCache();
        }
        //给日期按钮的天添加事件，用于获取天数
        $eventarea.find(".form-area-checked-lis .get-date-own").click(function(){
            var y=0,m=0;
            $(this).siblings(".select").each(function(index){
                if(index==0){y=$(this).children("input[type=text]").val();}
                else if(index==1){m=$(this).children("input[type=text]").val();}
            });
            var d=getDateOwn(y,m);
            var li='';
            for(var i=1;i<=d;i++){
                li+='<li><a>'+i+'</a></li>'
            }
            $(this).children("ul").children().remove();
            $(this).children("ul").append($(li));
            $("body").scrollTop($(this).offset().top);
        });
        addBlueTipEventLis($("#"+formId).find("input"));
        //给被省略的疾病添加Tip鼠标移入显示全名
        function addBlueTipEventLis($nodes) {
            // console.log($("#"+formId).find("input"));
            $nodes.each(function () {
                var $node = $(this),
                    text = $node.text() || $node.val();
                if (text.slice(-3, text.length) === "...") {
                    $node.blueTip();
                }
            });
        }
    }

    function lisValidateTime(el){
        var count=0;
        $(el).parents(".lis-checked-date").find("input").each(function(){
            if($(this).val().length>0){count++}
        });
        if(count==3){return true}else{return false}
    }

    function getVal($node) {
        switch ($node[0].tagName) {
            case "SPAN":
                return $node.text() + " ";
            case "TEXTAREA":
                return $node.val();
            case "INPUT":
                if($node.attr("inputval")){
                    return $node.attr("inputval");
                }else {
                    return  $node.val();
                }
                
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

    /*** 症状移动同时隐藏 ***/
    function moveSymptomWrap($symptom_wrap) {
        var distance = [],
            $symptoms = $symptom_wrap.children("div.symptom"),
            $button = $symptom_wrap.siblings("div.button"),
            $arrow_left = $button.children("div.left"),
            $arrow_right = $button.children("div.arrow.right"),
            $arrow_detail = $button.children("div.arrow.detail"),
            start = 0,
            width = 0,
            _width=0;
        setDistance("3");
        //console.log(distance);
        $symptom_wrap.data("distance", 0).css("marginLeft", "0");
        $symptoms.each(function (i, v) {
            changeShowSymptom(i, v);
        });
        $arrow_left.addClass("disabled").off("click.main").on("click.main", function () {
            if ($symptom_wrap.data("distance") !== 0) {
                arrowClick(false);
            }
        });
        $arrow_right.removeClass("disabled").off("click.main").on("click.main", function () {
            if ($symptom_wrap.data("distance") !== distance.length - 1) {
                arrowClick(true);
            }
        });

        function arrowClick(flag) {
            var add;
            if(flag){
                add=1;
            }else{
                add=-1;
            }
            $symptoms.css("visibility","visible");
            $symptom_wrap.data("distance", $symptom_wrap.data("distance") + add);
            changeArrowState();
            $symptom_wrap.animate({"marginLeft": "-" + distance[$symptom_wrap.data("distance")][2]}, 300, "linear",function () {
                $symptoms.each(function (i, v) {
                    changeShowSymptom(i, v);
                });
            });
        }

        $symptoms.each(function () {
            _width+=$(this).outerWidth(true);
        });
        if(_width<736){
            $arrow_right.addClass("d-n");
            $arrow_left.addClass("d-n");
        }else{
            $arrow_right.removeClass("d-n");
            $arrow_left.removeClass("d-n");
        }

        function changeArrowState() {
            $symptom_wrap.data("distance") === 0 ? $arrow_left.addClass("disabled") : $arrow_left.removeClass("disabled");
            $symptom_wrap.data("distance") === distance.length - 1 ? $arrow_right.addClass("disabled") : $arrow_right.removeClass("disabled");
        }

        function changeShowSymptom(i, v) {
            var $symptom = $(v);
            if (i < distance[$symptom_wrap.data("distance")][0] || i >= distance[$symptom_wrap.data("distance")][1]) {
                $symptom.css("visibility", "hidden");
            } else {
                $symptom.css("visibility","visible");
            }
        }

        function setDistance(move_count) {

            function getMarginLeft($symptoms,i,margin_left) {
                var len=$symptoms.length,
                    j,
                    _margin_left=0,
                    index=i,
                    flag=false,
                    width=0;
                for (j=i;j<len;j++){
                    index=j;
                    if(_margin_left + $symptoms.eq(j).outerWidth()< 756){
                        _margin_left+=$symptoms.eq(j).outerWidth();
                    }else{
                        break;
                    }
                }
                if(index===len-1 && _margin_left+$symptoms.eq(len-1).outerWidth()<756){
                    index=len;
                }
                for(j=i;j<len;j++){
                    width+=$symptoms.eq(j).outerWidth();
                }
                if(width<680){
                    flag=true;
                }

                return [index,margin_left+_margin_left,flag];
            }


            if(move_count==="auto"){
                $symptoms.each(function (i) {
                    var $symptom = $(this),
                        len = $symptoms.length,
                        j,
                        temp_width,
                        $temp_symptom;
                    width += $symptom.outerWidth();
                    if (i < len - 1 && width + $symptom.next().outerWidth() > 681) {
                        temp_width = width;
                        for (j = i + 1; j < len; j++) {
                            $temp_symptom = $($symptoms[j]);
                            if (temp_width + $temp_symptom.outerWidth() < 756) {
                                temp_width += $temp_symptom.outerWidth();
                            } else {
                                distance.push([start, j, margin_left]);
                                break;
                            }
                            if (j === len - 1) {
                                distance.push([start, j + 1, margin_left]);
                            }
                        }
                        start = i + 1;
                        margin_left += width;
                        width = 0;
                    }
                    if (i === $symptoms.length - 1) {
                        distance.push([start, $symptoms.length, margin_left]);
                    }
                });
            }else{
                move_count=Number(move_count);
                var length=$symptoms.length,
                    num=parseInt(length/move_count),
                    i,
                    j,
                    get_margin_arr,
                    margin_left=0;
                move_count=parseInt(move_count);
                if(num===0){
                    get_margin_arr=getMarginLeft($symptoms,0,0);
                    distance.push([0,get_margin_arr[0], get_margin_arr[1]]);
                }else{
                    get_margin_arr=getMarginLeft($symptoms,0,0);
                    distance.push([0, get_margin_arr[0], 0]);
                    for(i=0;i+move_count<length;i+=move_count){
                        for(j=i;j<i+move_count;j++){
                            // console.log($symptoms.eq(j).outerWidth()+"  "+$symptoms.eq(j).text());
                            width+=$symptoms.eq(j).outerWidth();
                        }
                        get_margin_arr=getMarginLeft($symptoms,i+move_count,margin_left);
                        margin_left=get_margin_arr[1];
                        distance.push([i+move_count,get_margin_arr[0],width]);
                        if(get_margin_arr[2]){
                            return;
                        }
                    }
                }
            }

        }

    }
    //move

    function showOrHideChoose(){//是否显示.choose列表
        $group.find(".choose .content").children().length>0?$group.find(".choose").removeClass("d-n"):$group.find(".choose").addClass("d-n")
        common.addChooseBottom($group.find(".choose"));
    }

    function serverCache(){
        var list=$(".lis .choose .content").children();
        var inStandardIds = [];
        $(list).each(function(){
        	inStandardIds.push($(this).attr("standard-id"));
        });
        var param={
            patientId : Param.patientId,
            type: 5,
            inStandardIds : inStandardIds.join()
        }
        flushDataIntoCache(param);
    }

    function forDeleteCache(){
        if(Param.firstType!=5){return false}//推送依据不是这一栏的话，不进行推理
        var list=$(".lis .choose .content").children();
        var inStandardIds = [];
        $(list).each(function(){
        	inStandardIds.push($(this).attr("standard-id"));
        });
        var param={
            patientId : Param.patientId,
            type: 5,
            inStandardIds : inStandardIds.join()
        }
        ajaxPost(Param.hostUrl + "/reason/clean_reason_cache_by_type",param);
    }

    return {
        "bindLisClick":bindLisClick,
        "initLisData": initLisData
    }
})();

function getLisForSave(){
    if($(".lis .choose .content").children().length==0){return {}}
    var data={
        "type":5,
        "choose":[],
        "details": [],
    };

    $(".lis .choose .content").children().each(function(){
        var optionId=$(this).attr("liscode").substring(7);
        var optionName=$(this).attr("lisname");
        var chooseOption={
            "itemId": optionId,
            "itemName":optionName,
            "itemDescribe":$(this).text(),
            "type":5,

            "remark":""
        };
        data.choose.push(chooseOption);

        var option={
            "itemId": optionId,
            "title": {
              "itemName": "",
              "checkboxClass": ""
            },
            "contents": []
        };

        var $appendArea=$("#"+$(this).attr("liscode"));
        if($appendArea.find(".lis-checked-label").hasClass("checked")){
            option.title.checkboxClass = "true";
            $appendArea.find(".form-area-checked-lis  input").each(function(i,v){
                var contentDetail={
                    "type": $(v).attr("model"),
                };
                // contentDetail[$(this).attr("name")]=$(this).val();
                contentDetail["value"]=$(this).val();
                option.contents.push(contentDetail);
            });
        }else{
            $appendArea.find(".form-area input").each(function(i,v){
                var contentDetail={
                    "type": $(v).attr("model"),
                };
                // contentDetail[$(this).attr("name")]=$(this).val();
                contentDetail["value"]=$(this).val();
                option.contents.push(contentDetail);
            });
        }
        data.details.push(option);
    });
    return data;
}


$(document).click(function(){//在点击空白区收起下拉框时，commonjs里需要symptom-form收起后才将symptom-container收起，会卡顿
    if( $(".lis .symptom-container .symptom-form:visible").height() > 0|| //这里的 0 表示 如果高度为0，那么就是表单看不见，所以可以往下执行隐藏推送条的行为
        $(".pacs .symptom-container .symptom-form:visible").height() >  0 ||
        $(".diagnosis .symptom-container .symptom-form:visible").height() >  0
    ) {
        return false;
    }
    $(".lis .symptom-container").fadeOut();
    $(".pacs .symptom-container").fadeOut();
    $(".diagnosis .symptom-container").fadeOut();
    $(".lis ul.box-r-s").hide();//由于化验infomation点击后没有收起来，所以需要给document添加隐藏效果
})

function saveLisForHis(){
    var $group=$(".container .group").eq(5).find(".choose .content");
    if($group.children().length==0){return []}
    var arr=[];
    $group.children().each(function(){
        arr.push({id:$(this).attr("hiscode"),name:$(this).attr("hisname"),num:1})
    });
    return arr;
}
Date.prototype.getDates = function (part) { //author: meizz 
    var o = {
        "Y": this.getFullYear(),
        "M": this.getMonth() + 1, //月份 
        "D": this.getDate(), //日 
    };
    var fmt = "";
    var add0 = function(n) {
        return n > 10 ? n : "0"+ n;
    }
    if(part){
        for(var k in o){
            fmt +=(part + add0(o[k]))
        }
        fmt = fmt.substr(1);
    }else {
        for(var k in o){
            fmt += add0(o[k])
        }
    }
    return fmt;
}

function getLisForBasis(){//获取信息用于诊断依据
    var $group=$(".container .group").eq(5);
    var ob={};
    $group.find(".choose .content").children().each(function(){
        var $now_form=$("#"+$(this).attr("liscode"));
        var obj={};
        $now_form.find(".form-area-checked-lis").find("input[data-param-code]").each(function(){
            if($(this).val().length==0){return true}
            obj[$(this).attr("data-param-code")]=$(this).val();
        });
        ob[$now_form.attr("data-param-code")]=obj;
    });
    
    return jsonTOstring(ob);
}

