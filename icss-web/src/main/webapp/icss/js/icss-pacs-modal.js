var icssPacs = (function(){
    var USUAL_URL = Param.hostUrl+Param.init_pacsinfo;
    // var USUAL_URL = Param.hostUrl+Param.start_pacs;
    var json = [
            {
                "itemId": "577",
                "apcode": "APP015 ",
                "title": {
                    "itemName": "",
                    "checkboxClass": ""
                },
                "contents": [
                    {
                        "type": "1",
                        "value": "腹膜后"
                    },
                    {
                        "type": "code11",
                        "value": ""
                    },
                    {
                        "type": "codeAPP015",
                        "value": "B超"
                    },
                    {
                        "type": "code21",
                        "value": ""
                    },
                    {
                        "type": "code2",
                        "value": "床旁机"
                    },
                    {
                        "type": "4",
                        "value": ""
                    }
                ],
                "pacode": "C086"
            },
            {
                "itemId": "640",
                "title": {
                    "itemName": "",
                    "checkboxClass": "true"
                },
                "contents": [
                    {
                        "type": "y",
                        "checked": false,
                        "value": "2017"
                    },
                    {
                        "type": "m",
                        "checked": false,
                        "value": "1"
                    },
                    {
                        "type": "d",
                        "checked": false,
                        "value": "7"
                    },
                    {
                        "type": "t0",
                        "checked": true,
                        "value": "B超提示输卵管、卵巢炎症变"
                    },
                    {
                        "type": "text",
                        "checked": false,
                        "value": ""
                    }
                ]
            }
        ]
    var $group=$(".container .group").eq(6);//器查

    bindPacsClick();
    // initPacsData(json);
    function initPacsData (allListItem) {
        var html = '';
        for(var i = 0; i < allListItem.length; i++){
            html += 
                '<div totalid=' + allListItem[i].itemId + '  class="symptom" data-param-code="" param-id="" partid="" apcode='+ allListItem[i].apcode +' pacode='+ allListItem[i].pacode +' pacsname="直肠B超" partname="直肠" appname="B超" pa-standard-id="2035" ap-standard-id="null" style="visibility: visible;">'+
                    '<i class="iconfont icon-i"></i>'+
                '</div>';
        }
        $group.find(".symptom-container .symptom-wrap").html(html);
        //为新生成的symptom添加事件
        $group.find(".symptom-wrap").children("div.symptom").click(function(){
                common.cancelReturnLastStep($group.find('div.choose'));
                var formId=dynamicPacsForm(this);
                addPacsEvents(formId,this);
                $(this).parents(".symptom-wrap").css("display","none");
                $(".pacs-main-input").val("");
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
                initSymptomForm();
            });
        //触发新生成的symptom的点击事件，生成表单
        $group.find(".symptom-wrap").children("div.symptom").each(function(i,v){
            
            $(v).click();
            if(allListItem[i].title.checkboxClass == "true" ){
               $("[data-pacs-id =" + $(v).attr("totalid") +"]").find(".pacs-checked-label").click();
            }
            //为表单进行数据赋值
            
            $("[data-pacs-id =" + $(v).attr("totalid") +"]").find("input[model]").each(function(inp,val){
                // console.log(val);
                var data = allListItem[i].contents;
                // console.log(data);
                for(var k=0;k<data.length;k++){
                    if(data[k].type == $(val).attr("model")) {
                        
                        $(val).val(data[k].value);
                        if(data[k].checked == "true") {
                            $(val).parent(".checkbox").addClass("checked");
                        }
                    }
                }
            }); 
            var $current = $("[data-pacs-id =" + $(v).attr("totalid") +"]");
            $current.find(".pacs-check-some4").show();
            $current.find(".make-sure.usable").removeClass("disabled");
            $current.find(".submit-bt .make-sure").click();
        });

         //触发 确定按钮的 点击 事件
        setTimeout(function(){
            $group.find(".lis-check-some .down-arrow.info").css({
                left: "32px"
            });
            
        },0);
    }
    $group.find(".arrow.detail").click(function(){
        initPacsMore();
        initPacsMoreEvent();
        $group.find(".symptom-all").slideDown();
        $group.find(".symptom-form").slideUp();
        updateSelect();
    });
    $group.find(".symptom-all>.title>.arrow").click(function(){
        $group.find(".symptom-all").slideUp();
        //$group.find(".symptom-wrap").slideDown();
        $group.find(".symptom-form").css("display","block");
    });

    function bindPacsClick(){
        $group.click(function(e){
            $(".box-r-s.detail-info").hide();
            e.stopPropagation();
            return false;
        });
        $group.find("input:first").click(function(){
            common.autoScrollY($("body"),$(this).parents("div.group"),-170);
        	cleanCache(6);
            $(this).parents(".group").addClass("focus");
            common.groupHide($(this).parents(".group"));
            $(".symptom-all").slideUp();
            //if($group.find(".symptom-container").css("display")=="block"){return false}
            $group.find(".symptom-form").css("display","block");//因为common.js里的groupHide会在document click时将其变为隐藏
            $group.find(".symptom-wrap").children().remove();
            var paras={
                doctorId:Param.doctorId,
                size:40,
                sexType:Param.sexType,
                deptNo:Param.deptNo,
                hospitalCode:Param.hospitalCode,
                age:Param.age,
                patientId : Param.patientId,
                type : 6
            }
            var resl = ajaxPost(USUAL_URL,paras);
            var li = ajaxPushPacsList(resl.data);
            li+='<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';

            $group.find(".symptom-wrap").append($(li));
            $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                $(this).blueTip();
            });

            common.changeContainerShadowWidth($group.find("div.container-shade"));

            $group.find(".symptom-form .detail-list").css("display","none");
            $group.find(".symptom-wrap").css("display","block");

            addPacsInformationEvents();
            $(".symptom-container").slideUp();
            //$group.find(".symptom-container .symptom-form").css("display","none");
            $group.find(".symptom-container").slideDown();
            moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
            $group.find(".symptom-wrap").children("div.symptom").click(function(){
                common.cancelReturnLastStep($group.find('div.choose'));
                var formId=dynamicPacsForm(this);
                addPacsEvents(formId,this);
                $(this).parents(".symptom-wrap").css("display","none");
                $(".pacs-main-input").val("");
                
                initSymptomForm();
            });
        });
        /*检索pacs*/
        //$group.find("input").keyup(function(){
        $group.find("input").on("input propertychange" , function(){
            $group.find(".symptom-all").slideUp();
            $group.find(".symptom-wrap").children().remove();
            var word=$(this).val();
            // notIds:
            var idStr = "";
             $(this).siblings(".choose").find(".content>p,.content>div").each(function(i,v){
                idStr += $(v).attr("totalid")+",";
            }); 
            idStr =  idStr.slice(0, idStr.length-1);
            if(word.length==0){return false}
            var resl = ajaxPost(Param.hostUrl+Param.pacs_search_pacs,{notIds:idStr,inputstr:word,size:40});
            var li = ajaxPushPacsList(resl.data);
            li+='<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';

            $group.find(".symptom-wrap").append($(li));
            $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                $(this).blueTip();
            });
            common.changeContainerShadowWidth($group.find("div.container-shade"));
            moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
            $group.find(".symptom-wrap").children("div").click(function(){
                common.cancelReturnLastStep($group.find('div.choose'));
                var formId=dynamicPacsForm(this);
                addPacsEvents(formId,this);
                $(this).parents(".symptom-wrap").css("display","none");
                $(".pacs-main-input").val("");
            });

        });
    }
    function addPacsInformationEvents(){
        
        // $group.find(".symptom-wrap").children().hover(function(){
        //     $(this).children("i").css("visibility","visible");
        //     console.log(1);
        // },function(){
        //     $(this).children("i").css("visibility","hidden");
        // });
        $group.find(".symptom-wrap").children().children().click(function(e){
            //由于弹出诊断依据后，滚动鼠标后底下的页面也会移动
            $("body").css({
                "overflow-y":'hidden'
            });
            var itemId = $(this).parent().attr("wholeid");//以下写死的itemid需要替换
            var resl=ajaxPost(Param.hostUrl+Param.get_by_itemidAndType,{type:'6', itemId:'1'});
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

    function ajaxPushPacsList(data){
        var list=[];//list表示已选择的项目，不显示出来
        $group.find(".choose p").each(function(){
            list.push($(this).attr("wholeid"));
        });
        var li='';
        $(data).each(function(){
            if($.inArray(this.partCode+this.apparatusCode, list)>=0){return true}
            if(this.name.length>6){
                li+='<div totalid="'+this.id+'"  class="symptom" data-param-code="'+this.paramCode+'" param-id="'+this.id+'" partid="'+this.partId+'" apcode="'+this.apparatusCode+'" pacode="'
                +this.partCode+'" pacsname="'+this.name+'" partname="'+this.partName+'" appname="'
                +this.apparatusName+'" pa-standard-id="'+this.partStandardId+'" ap-standard-id="'+this.apparatusStandardId+'" data-symptom-name="'+this.name+'">'
                +this.name.substring(0,6)+"..."+'<i class="iconfont icon-i"></i></div>';
                
            }else{
                li+='<div totalid="'+this.id+'" class="symptom" data-param-code="'+this.paramCode+'" param-id="'+this.id+'" partid="'+this.partId+'" apcode="'+this.apparatusCode+'" pacode="'
                +this.partCode+'" pacsname="'+this.name+'" partname="'+this.partName+'" appname="'
                +this.apparatusName+'" pa-standard-id="'+this.partStandardId+'" ap-standard-id="'+this.apparatusStandardId+'">'
                +this.name+'<i class="iconfont icon-i"></i></div>';
            }

        });
        return li;
    }
    //-为已选项目添加 样式
    //-由于点击确定后，还未将选择的数据插入页面中，所以采用定时器
    function updateSelect(){
        var timer = setTimeout(function(){
            var $p_list = $group.find('.choose .content p,.choose .content div');
            $p_list.each(function(i,v){
                var id = $(v).attr("pa-standard-id");
                $group.find(".wrap div[tagnum=1] p[pa-standard-id="+ id +"]").addClass('high-light-color');//为部位添加高亮
                //这里需要筛选出 手段和部位的对应项，当常用项下的目录没有apcode时，就是推理出的，反之则是常规推送的
                var pacode = $(v).attr("pacode").trim();
                var apcode = $(v).attr("apcode").trim();
                
                var $all_pacode = $group.find(".wrap div[tagnum=0] p[pacode = "+ pacode +"]");
                
                //筛选 常用给只有pacode项的添加高亮，或者唯一的对应项添加
                if(!$all_pacode.attr("apcode") || $all_pacode.attr("apcode") == "null" ){
                    $all_pacode.addClass('high-light-color');
                }else{
                    $group.find(".wrap div[tagnum=0] [pacode = "+ pacode +"][apcode = "+ apcode +"]").addClass('high-light-color');
                }
            })
        },0)
    }
    function initPacsMoreEvent(){
        /*更多的切换事件*/
        $group.find(".button-group").children("button").click(function(){
            $(this).addClass("focus").siblings().removeClass("focus");
            $group.find(".div-group[tagnum="+$(this).attr("tagnum")+"]").removeClass("d-n").siblings().addClass("d-n");
            $group.find(".div-group[tagnum="+$(this).attr("tagnum")+"]").find(".menu li a:first").click();
            //为已选项目添加 样式
            updateSelect();
        });
        /*更多的切换事件*/
        $group.find(".symptom-all p[data-symptom-name]").each(function(){
            $(this).blueTip();
        });
    }

    function initPacsMore(){
        var row='';
        var col='';
        var colleft='';
        var colright='';
        var resl = ajaxPost(Param.hostUrl+Param.get_transverse_portrait_pacs,{type:6});
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
                                    colright+='<p class="symptom" totalid="'+this.id+'" param-id="'+this.id+'" partid="'+this.partId+'" pacode="'+this.partCode+'" partname="'+this.partName+'" pa-standard-id="'+this.partStandardId+'" ap-standard-id="'+this.apparatusStandardId+'" data-param-code="'+this.paramCode+'" data-symptom-name="'+this.name+'">'+this.name.substring(0,5)+'...</p>';
                                }else{
                                    colright+='<p class="symptom" totalid="'+this.id+'" param-id="'+this.id+'" partid="'+this.partId+'" pacode="'+this.partCode+'" partname="'+this.partName+'" pa-standard-id="'+this.partStandardId+'" ap-standard-id="'+this.apparatusStandardId+'" data-param-code="'+this.paramCode+'">'+this.name+'</p>';
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
                                colright+='<p class="symptom" totalid="'+this.id+'" param-id="'+this.id+'" partid="'+this.partId+'" pacode="'+this.partCode+'" partname="'+this.partName+'" pa-standard-id="'+this.partStandardId+'" ap-standard-id="'+this.apparatusStandardId+'" data-param-code="'+this.paramCode+'" data-symptom-name="'+this.name+'">'+this.name.substring(0,5)+'...</p>';
                            }else{
                                colright+='<p class="symptom" totalid="'+this.id+'" param-id="'+this.id+'" partid="'+this.partId+'" pacode="'+this.partCode+'" partname="'+this.partName+'" pa-standard-id="'+this.partStandardId+'" ap-standard-id="'+this.apparatusStandardId+'" data-param-code="'+this.paramCode+'">'+this.name+'</p>';
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
            patientId : Param.patientId,
            type : 6
        }
        
        var commonresl = ajaxPost(Param.hostUrl+Param.get_pacs_usual,paras);
        li+='<div class="content" style="width:100%">';
        $(commonresl.data).each(function(){
            if(this.name.length>5){
                li+='<p class="symptom" totalid="'+this.id+'" param-id="'+this.id+'" partid="'+this.partId+'" apcode="'+this.apparatusCode+'" pacode="'
                +this.partCode+'" pacsname="'+this.name+'" partname="'+this.partName+'" appname="'
                +this.apparatusName+'" pa-standard-id="'+this.partStandardId+'" ap-standard-id="'+this.apparatusStandardId+'" data-param-code="'+this.paramCode+'" data-symptom-name="'+this.name+'">'+this.name.substring(0,5)+'...</p>';
            }else{
                li+='<p class="symptom" totalid="'+this.id+'" param-id="'+this.id+'" partid="'+this.partId+'" apcode="'+this.apparatusCode+'" pacode="'
                +this.partCode+'" pacsname="'+this.name+'" partname="'+this.partName+'" appname="'
                +this.apparatusName+'" pa-standard-id="'+this.partStandardId+'" ap-standard-id="'+this.apparatusStandardId+'" data-param-code="'+this.paramCode+'">'+this.name+'</p>';
            }
        });
        li+='</div>';
        $group.find(".wrap-content[tagnum=0]").append($(li));

        li="";//第一级tag的div=
        $(resl.data[1].portraitList).each(function(){
            if(this.portraitList.length>0){
                li+='<div class="content d-n" tagid="'+this.id+'">';
                $(this.portraitList).each(function(){
                    li+='<p class="title">'+this.name+'</p>';
                    $(this.detailList).each(function(){
                        if(this.name.length>5){
                            li+='<p class="symptom" totalid="'+this.id+'" param-id="'+this.id+'" partid="'+this.partId+'" pacode="'+this.partCode+'" partname="'+this.partName+'" pa-standard-id="'+this.partStandardId+'" ap-standard-id="'+this.apparatusStandardId+'" data-param-code="'+this.paramCode+'" data-symptom-name="'+this.name+'">'+this.name.substring(0,5)+'...</p>';
                        }else{
                            li+='<p class="symptom" totalid="'+this.id+'" param-id="'+this.id+'" partid="'+this.partId+'" pacode="'+this.partCode+'" partname="'+this.partName+'" pa-standard-id="'+this.partStandardId+'" ap-standard-id="'+this.apparatusStandardId+'" data-param-code="'+this.paramCode+'">'+this.name+'</p>';
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

        //- 点击器查 - 常用 下的列表
        $group.find(".wrap-content .content p.symptom").click(function(){
            common.cancelReturnLastStep($group.find('div.choose'));
            var formId=dynamicPacsForm(this);
            addPacsEvents(formId,this);
            $group.find(".symptom-all").slideUp();
            $group.find(".symptom-wrap").css("display","none");
            $group.find(".symptom-form").slideDown();
            //初始化样式
            initSymptomForm();
        });
    }
    //点击器查 - 常用 下的列表 进入时初始化样式
    function initSymptomForm(){
        //-隐藏 进入时 ， 标题和 药费
            var $form_visible = $group.find(".symptom-form>div:visible");
            var $input = $form_visible.find(".form-area .pacs-check-some2 input");
            
            if($input.val() && !$input.val().trim()){
                $input.val("检查手段").css("color","#aaa");
                $form_visible.find(".form-area .pacs-check-some4").hide();
                $form_visible.find("h3 .toptitle, h3 .cost").hide();
                $form_visible.find("h3 .box-r-s").addClass("disabled").css({
                    "background": "#e5e5e5"
                });
                $form_visible.find(".pacs-checked-label").css({
                    "color": "#aaa"
                });
            }
    }

    function dynamicPacsForm(el){
        var appendArea=$group.find(".symptom-form");
        appendArea.find("div[isdisabled=true]").remove();

        var code=$(el).attr("pacscode");
        // var wholeid=new Date().getTime();//时间戳用于唯一标识，在绑定事件时用,formid值就是pacsCode+wholeid
        var wholeid = $(el).attr("wholeid");
        var partCode=$(el).attr("pacode");
        var partName=$(el).attr("partname");
        var apparatusCode=$(el).attr("apcode");
            apparatusCode=apparatusCode === undefined ? "" : apparatusCode;
        var apparatusName=$(el).attr("appname");
            apparatusName=apparatusName === undefined ? "" : apparatusName;
            apparatusCode=apparatusCode === "null" ? "" : apparatusCode;
            apparatusName=apparatusName === "null" ? "" : apparatusName;
        var partStandardId=$(el).attr("pa-standard-id");
        var appStandardId=$(el).attr("ap-standard-id");
        var partId=$(el).attr("partid");//本来用于获取子部位，现在没用到
        var paramId=$(el).attr("param-id");//本来用于关联获取paramCode，现在paramCode当参数传，paramId只用于获取已检项,现在还用于获取子部位和手段
        var paramCode=$(el).attr("data-param-code");
        var totalId=$(el).attr("totalid");
        if($("#"+wholeid).length>0){
            if($("#"+wholeid).css("display")=="block"){return false}
            appendArea.children().css("display","none");
            $("#"+wholeid).slideDown();
            return false;
        }
        appendArea.children().css("display","none");
        //表单html模板
        appendArea.append($('<div totalid="'+totalId+'" data-pacs-id='+ totalId +' data-param-code="'+paramCode+'" pa-standard-id="'+partStandardId+'" ap-standard-id="'+appStandardId+'" id="pacsCode'+wholeid+'" class="detail-list j-pacs-disabled" isdisabled="true" style="display:none">'+
                                '<h3>'+
                                    '<b style="line-height:20px;" class="toptitle">'
                                        + partName + apparatusName +
                                    '</b>'+
                                    '<div class="c-r checkbox pacs-checked-label">'+
                                        '<div class="box-r-s">'+
                                            '<i class="iconfont icon-duigou1"></i>'+
                                        '</div>'+
                                        '已检'+
                                    '</div>'+
                                    '<div class="cost d-n" style="float:right;">'+
                                        '费用:'+
                                    '</div>'+
                                '</h3>'+
                                '<div class="form-area">'+
                                '<div class="row pacs-check-some" style="float:left;"></div>'+
                                '<div class="row pacs-check-some11 d-n" style="float:left;"></div>'+
                                '<div class="row pacs-check-some2" style="float:left;"></div>'+
                                '<div class="row pacs-check-some21 bodyDirection" style="float:left;" ></div>'+
                                '<div class="row pacs-check-some3" style="float:left;"></div>'+
                                '<div style="clear:both"></div>'+
                                '<div class="row pacs-check-some4 d-n">'+
                                '<div class="form-group">'+
                                    '<label>补充</label>'+
                                    '<input name="remark" class="box-r-s" model="4" type="text" style="width:730px;">'+
                                '</div>'+
                            '</div>'+
                            '<div style="clear:both"></div>'+
                        '</div>'+
                        '<div class="form-area-checked-pacs" style="display:none;"></div>'+
                        '<p class="submit-bt">'+
                            '<button class="make-sure usable" type="button">确定</button>'+
                            '<button class="cancle unusable" type="button">取消</button>'+
                        '</p>'+
                    '</div>'));
        var a="pacsCode"+wholeid;
        var $form_area = $(document.getElementById(a)).find(".form-area .pacs-check-some");
        var $form_area11 = $(document.getElementById(a)).find(".form-area .pacs-check-some11");//器官数量显示
        var $form_area21 = $(document.getElementById(a)).find(".form-area .pacs-check-some21");
        var $form_area3 = $(document.getElementById(a)).find(".form-area .pacs-check-some3");
        
        
        //var result=ajaxPost(Param.hostUrl+Param.get_his_pacs,{apparatusCode:'APP001',partCode:'A001',hospitalCode:'331023'});
        /*因为手段现在要选择，这个接口要放到点击确定的时候调用*/

        var resu=ajaxPost(Param.hostUrl+Param.pacs_get_by_infoId,{id:paramId});
       
        //- bug-2034 器查-没有明细的部位后面不显示i图标，且直接显示文本不需要显示文本框的外框。化验存在同样的问题。
        //思路： 直接判断子级是否存在数据，分情况进行渲染
        if(resu.data && resu.data.sonPart && resu.data.sonPart.length > 1){
            $form_area.append(
                $('<div class="form-group"><label>项目</label>'+
                    '<div class="select-only">'+
                        '<input name="part" model="1" type="text" readonly="" data-symptom-name= '+ partName +' value="'+partName+'" code="'+partCode+'">'+
                        '<div class="down-arrow">'+
                            '<div class="info-icon"></div>'+
                        '</div>'+
                        '<ul class="box-r-s d-n"></ul>'+
                    '</div>'+
                '</div>'));
            $form_area.find(".form-group ul").append($(getPacsGetPartDetail(resu.data.sonPart)));
        }else {
            $form_area.append(
                $('<div class="form-group">'+
                    '<label>项目</label>'+
                        '<div class="select-only">'+
                        '<input name="part" model="1" type="text" readonly=""  data-symptom-name= '+ partName +'  value="'+partName+'" code="'+partCode+'">'+
                        '<ul class="box-r-s d-n"></ul>'+
                    '</div>'+
                '</div>'));
            $form_area.find("input").css({
                "border": "none"
            });
            $form_area.find(".select").css({
                "border": "none"
            });
            $form_area.find(".form-group ul").css({
                "border": "none"
            });
        }
        
        function getPacsGetPartDetail(data){
            var li='';
            $(data).each(function(){
                li+='<li><a temp="'+this.name+'" code="'+this.id+'">'+this.name+'</a>';
                li+='</li>'
            });
            return li
        }

        $form_area = $(document.getElementById(a)).find(".form-area .pacs-check-some2");
        //
        setTimeout(function(){
            if($form_area.find("input").val() === "检查手段"){
                $(document.getElementById(a)).find(".pacs-check-some21").hide();
            }
        },0);
        /**
         * 生成groupform模板函数
         * @param {string} data 
         */
        function groupFormHtml(value, code, title) {
            return '<div class="form-group">'+
                '<label>'+ title +'</label>'+
                '<div class="select box-r-s w-100px">'+
                    '<input type="text" model=code'+ code +' readonly="" value="'+ value +'" data-symptom-name= '+ value +' code="'+code+'" name="apparatus">'+
                    '<div class="down-arrow">'+
                        '<i class="iconfont icon-triangle-down-copy"></i>'+
                    '</div>'+
                    '<ul class="box-r-s d-n" style="overflow-y: auto;"></ul>'+
                '</div>'+
            '</div>';
        }

        $form_area.append(
            $( groupFormHtml(apparatusName, apparatusCode,"&nbsp;") )
        );

        // 如果有默认值 就显示要求和体位
        if($form_area.find("input").val() !== "检查手段"){
            $form_area21.show();
        };

        $form_area11.append(
            $( groupFormHtml("", "11","&nbsp;") )
        );
        //
        $form_area21.append(
            $( groupFormHtml("", "21","&nbsp;") )
        );
        $form_area3.append(
            $( groupFormHtml("", "2","机型") )
        );
        //器官 部位 数量
        var partCounts = 0;
        if(resu.data && resu.data.part){
            partCounts = resu.data.part.direction;
        }
        if(resu.data && resu.data.part && partCounts != 0) {//0表示是单器官 
            var partItems = [
                {name: "双"},
                {name: "左"},
                {name: "右"},
                ];
            $(document.getElementById(a)).find(".pacs-check-some11").show();
            $(document.getElementById(a)).find(".pacs-check-some11 input").val(partItems[0].name);
            $form_area11.find(".form-group ul").html($(getPacsGetAppDetail(partItems)));
        }
        if(resu.data && resu.data.apparatus && resu.data.apparatus.length > 0){
             var apparatus = resu.data.apparatus;
            $form_area.find(".form-group ul").html($(getPacsGetAppDetail(apparatus)));
        }else{
            apparatus = [];
        }

        var sellctedIndex = null;
         
        //体位 要求
        $(document.getElementById(a)).find(".bodyDirection input").on("click", function() {
             var $currentForm = $(document.getElementById(a));
             var some2InputVal = $currentForm.find(".pacs-check-some2 input").val();
             var some21InputVal = $currentForm.find(".pacs-check-some21 input").val();
             var tempData = "";//存储要求或体位数据
             var tempStr= "";
             if(apparatus){
                for(var i = 0; i < apparatus.length; i++) {
                    if(apparatus[i].name === some2InputVal) {
                        sellctedIndex = i;
                    }
                }
                if(apparatus[sellctedIndex] && apparatus[sellctedIndex].requirementList.length > 0){
                    tempData = apparatus[sellctedIndex].requirementList;
                    tempStr = "要求";
                    $form_area21.find(".form-group ul").html($(getPacsGetAppDetail(tempData)));
                    if(some21InputVal == "" || some21InputVal == "体位" || some21InputVal == "要求") {
                        $currentForm.find(".pacs-check-some21 input").val(tempStr);
                    }
                    
                }else if(apparatus[sellctedIndex] && apparatus[sellctedIndex].positionList.length > 0){
                    tempData = apparatus[sellctedIndex].positionList;
                    $form_area21.find(".form-group ul").html($(getPacsGetAppDetail(tempData)));
                    if(some21InputVal == "" || some21InputVal == "体位" || some21InputVal == "要求") {
                        $currentForm.find(".pacs-check-some21 input").val("体位");
                    }
                }else {
                    $currentForm.find(".pacs-check-some21").hide();
                }
            }
            /*
            if(apparatus){
                for(var i = 0; i < apparatus.length; i++) {
                    if(apparatus[i].name === some2InputVal) {
                        sellctedIndex = i;
                    }
                }
                if(some2InputVal == "CT" && (some21InputVal == "" || some21InputVal == "要求") ) {//CT时显示要求
                    $form_area21.find(".form-group ul").html($(getPacsGetAppDetail(apparatus[sellctedIndex].requirementList)));
                    $currentForm.find(".pacs-check-some21 input").val("要求");
                }else if( some2InputVal == "B超" || 
                            some2InputVal == "彩超(CDFI)" || 
                            some2InputVal == "磁图" || 
                            some2InputVal == "磁共振MR"|| 
                            some2InputVal == "心电图"|| 
                            some2InputVal == "动态心电图"|| 
                            some2InputVal == "脑电图"|| 
                            some2InputVal == "肌电图"|| 
                            some2InputVal == "眼底镜"|| 
                            some2InputVal == "裂隙灯"|| 
                            some2InputVal == "鼻镜" || 
                            some2InputVal == "喉镜" || 
                            some2InputVal == "胃镜GSK" || 
                            some2InputVal == "肠镜"  || 
                            some2InputVal == "膀胱镜"     ) {
                //选择“超声、电图、磁图、内窥镜”类时，无需“体位”和“要求”；
                    $currentForm.find(".pacs-check-some21").hide();
                }else {
                    //选择除以上类别外（即需要拍片的项目），都需要“体位”选项；
                    if( apparatus[sellctedIndex]){
                        $form_area21.find(".form-group ul").html($(getPacsGetAppDetail(apparatus[sellctedIndex].positionList)));
                    }   
                    if(some21InputVal == "" || some21InputVal == "体位" || some21InputVal == "要求") {
                        $currentForm.find(".pacs-check-some21 input").val("体位");
                    }
                }
            }
            */
        //    if(some21InputVal !== "要求" || some21InputVal !== "体位" || some21InputVal == "") {
        //         $currentForm.find(".pacs-check-some21 input").val(some21InputVal);
        //     }
            //机型选择
            if(apparatus[sellctedIndex] && apparatus[sellctedIndex].modelList.length > 0){
                $currentForm.find(".pacs-check-some3 input").on("click", function() {
                    if($currentForm.find(".pacs-check-some2 input").val().trim() != "检查手段") {
                        $form_area3.find(".form-group ul").html($(getPacsGetAppDetail(apparatus[sellctedIndex].modelList)));
                    }
                });
            }
        });
        $(document.getElementById(a)).find(".bodyDirection input").click();
         $(document.getElementById(a)).find(".pacs-check-some2 li").on("click", function(){
             setTimeout(function(){
                 $(document.getElementById(a)).find(".bodyDirection input").click();
                 $currentForm.find(".pacs-check-some21 ul").hide();
                 if($currentForm.find(".pacs-check-some2").val() !== "检查手段") {
                    $currentForm.find(".pacs-checked-label .box-r-s").removeClass("disabled").css({
                        color: "",
                        background: ""
                    });
                }
             },0);
         })
        /**
         * 生成下拉列表
         * @param {array} data 
         */
        function getPacsGetAppDetail(data){
            var li='';
            $(data).each(function(){
                li+='<li><a totalid="'+this.infoId+'" temp="'+this.name+'" code="'+this.code+'" ap-standard-id="'+this.standardId+'">'+this.name+'</a>';
                li+='</li>';
            });
            return li;
        }

        var $form_area_checked_pacs =$(document.getElementById(a)).find(".form-area-checked-pacs");
        $('#dateunit1').tmpl().addClass("pacs-checked-date").appendTo($form_area_checked_pacs);//已检 里面的时间事件在①
        $form_area_checked_pacs.append($(getPacsCheckedList()));
        $form_area_checked_pacs.append($('<div style="clear:both;"></div>'));
        function getPacsCheckedList(){
            var result=ajaxPost(Param.hostUrl+Param.pacs_get_result,{id : paramId})
            var li='<div class="form-group" style="margin-top:10px;width:700px;">';
            $(result.data).each(function(index){
                li+='<div data-param-code="'+this.paramCode+'" pacs-checked-list-id="'+this.id+'" class="c-r checkbox" style="float:none;font-weight:700;margin-top:5px;">'
                +'<div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>'+this.name+'</div>';
            });
            li+='</div>'
            return li;
        }
        //-
        var select = appendArea.find(".pacs-check-some2 .select");
        var selInput = select.find("input");
        if(selInput.length > 0 && selInput.val()){
            selInput.css({
                color: "#aaa"
            });
            select.find("i").css({
                color: "#aaa"
            });
        }
        if(selInput.val() == "检查手段"){//当为默认值时，小箭头正常显示
            select.find("i").css({
                color: "#000"
            });
        }else {
            
            selInput.css({
                color: "#000"
            });

            if( appendArea.find(".pacs-check-some2 input").val()== ""){
                appendArea.find(".pacs-check-some2 input").val("检查手段").css("color","#aaa");
                appendArea.find(".pacs-check-some21").hide();
            }
            if( appendArea.find(".pacs-check-some21").val() =="要求" ||
                appendArea.find(".pacs-check-some21").val() =="体位" ||
                appendArea.find(".pacs-check-some21").val() =="" ||
                appendArea.find(".pacs-check-some3").val() == "机型" || 
                appendArea.find(".pacs-check-some3").val() == ""
              ) {
                appendArea.find(".make-sure").addClass("disabled");
            }else {
                appendArea.find(".make-sure").removeClass("disabled");
            }
            appendArea.find("h3 .pacs-checked-label .box-r-s").addClass("disabled").css({
                "color": "#aaa",
                "background": "rgb(229, 229, 229)"
            });
             appendArea.find("h3 .pacs-checked-label");
        }
        var $currentForm = $(document.getElementById(a));
        if($currentForm.find(".pacs-check-some21 input").val() == "体位" ||$currentForm.find(".pacs-check-some21 input").val() == "要求" ){
            $currentForm.find(".pacs-check-some21 input").css({
                "color":"#aaa"
            })
        }
        if($currentForm.find(".pacs-check-some2").val() !== "检查手段") {
            $currentForm.find(".pacs-checked-label .box-r-s").removeClass("disabled").css({
                color: "",
                background: ""
            });
        }
        
        /**
         *  设置 三点的构造函数
         * @param {*} el 
         */
        function SetInputLength(el) {
            this.el = el;
            this.valLength = this.getLength(el);
        }
        SetInputLength.prototype.getLength = function() {
            return this.getCurrentDom(this.el).val().length;
        }
        SetInputLength.prototype.getCurrentDom = function() {
            return $(document.getElementById(a)).find(this.el);
        }
        /**
         * count 设置 字数不能超过的长度
         */
        SetInputLength.prototype.setThreeDot = function(count) {
            if(this.valLength > count) {
                this.getCurrentDom(this.el).val(this.getCurrentDom( this.el).val().substring(0,count)+"...");
            }
            return this;
        }
        new SetInputLength(".pacs-check-some input").setThreeDot(7);

        new SetInputLength(".pacs-check-some11 input").setThreeDot(7);

        new SetInputLength(".pacs-check-some2 input").setThreeDot(5);

        new SetInputLength(".pacs-check-some21 input").setThreeDot(7), 

        new SetInputLength(".pacs-check-some3 input").setThreeDot(7);



        $("#pacsCode"+wholeid).slideDown();
        if(apparatusCode==""){$(document.getElementById(a)).find(".make-sure.usable").addClass("disabled").attr("disabled","disabled")}
        return a;
    }
    
    function addPacsEvents(formId,el){
        var $eventarea=$(document.getElementById(formId));
        var notHasApcode=$(el).attr("apcode")===undefined||$(el).attr("apcode")==="null"||$(el).attr("apcode")==="";
        $eventarea.click(function(){
            common.selectOnlyHide();
            common.selectHide();
            common.removeFocus();
        });
        $eventarea.find("div.c-r.checkbox").each(function(){
            var _t = this;
            $(this).on("click.common",function () {
                var $checkbox=$(this);
                //判断是否有disabled ，如果有就直接返回
                if($checkbox.children(".box-r-s").hasClass("disabled")){
                    return false;
                };
                common.checkboxChangeState($checkbox);
                setTimeout(function(){
                    var totalid = $(_t).parents(".detail-list").attr("totalid");
                    $.ajax({
                        url: Param.hostUrl +"/kl/pacs_result/get_result",
                        data:{
                            id:totalid
                        },
                        success: function(res) {
                            if(res.ret === 0) {
                                var html = $("#tmplPacsForm").tmpl(res);
                               
                                $eventarea.find(".form-area-checked-pacs .form-group").eq(1).html(html);
                                $eventarea.find(".form-area-checked-pacs .c-r.checkbox").on("click", function(){
                                    $(this).toggleClass("checked","");
                                });
                                 var $checksBox = $eventarea.find(".form-area-checked-pacs .form-group .form-group").children("div");
                                $checksBox.each(function(i,v){
                                    //状态值
                                    if($(v).attr("status") && $(v).attr("status") != 1){
                                        $(v).removeClass("d-n");
                                    }
                                });
                                $eventarea.find(".date-toggle.box-r-s").on("click", "span",function() {
                                    $(this).addClass("focus").siblings().removeClass("focus");
                                    if($(this).text() == "异常"){
                                        $(this).siblings("div.background-color").css({
                                            left:39
                                        })
                                        $checksBox.each(function(i,v){
                                        //状态值
                                            if($(v).attr("status") && $(v).attr("status") == 1){
                                                $(v).removeClass("d-n");
                                            }
                                        });
                                    }else {
                                        $(this).siblings("div.background-color").css({
                                            left:0
                                        })
                                        $checksBox.each(function(i,v){
                                            //状态值
                                            if($(v).attr("status") && $(v).attr("status") == 1){
                                                $(v).addClass("d-n");
                                            }
                                        });
                                    }
                                });
                            }
                        },
                        error: function(err) {
                            console.log("pacs.js" + err);
                        }
                    });
                },0);
            });
        });

        $eventarea.find(".row").find("div.select-only").on("click.common",function (e) {
            if(e.target.nodeName=="DIV"||e.target.nodeName=="I"){
                var $select=$(this);
                common.selectOnlyHide($select);
                $select.children("ul").fadeIn();
                e.stopPropagation();
            }
        });
        
        $eventarea.find(".row").find("div.select.box-r-s").on("click.common",function (e) {
           var $select=$(this);
           common.selectHide($select);
           $select.addClass("focus").children("ul").fadeIn();
           e.stopPropagation();
        });

        if(!notHasApcode){
            $eventarea.find(".pacs-check-some2 div.select.box-r-s").unbind("click");
        }

        /*手段未选择时，确定按钮不可用，选择器查手段，使确定显示后续*/
        $eventarea.find(".pacs-check-some2 ul a").click(function(){
            $eventarea.attr("totalid",$(this).attr("totalid"));
            
            $eventarea.find(".form-area .pacs-check-some21").show();
            // $eventarea.find(".form-area .pacs-check-some21 input").val("体位或要求");
            //- 清除 就诊费用
            $eventarea.find("h3 .cost").text("费用：");
        });
      
        /*** select的下拉菜单的点击事件***/
        $eventarea.find(".row").not(".pacs-check-some").find('ul.box-r-s').on("click.common","a,input,div",function (e) {
           var $node=$(e.target),
                $a=$node.parents("a:first");
            if($(this).parents(".pacs-check-some3").length > 0){
                
                $eventarea.attr("ap-standard-id",$node.attr("ap-standard-id"));
                //- 点击时将单价和标题等显示,并修改其状态
                $eventarea.find("h3 .cost").text("费用：").show();
                
                $eventarea.find("h3 .pacs-checked-label").children().removeClass("disabled").css({
                    "background": ""
                });
                $eventarea.find(".make-sure.usable").removeClass("disabled").removeAttr("disabled");
                $eventarea.find(".pacs-check-some4").fadeIn();
            }
           if(e.target.nodeName==="A"){

               if($node.children("div.c-r.checkbox").length===0){//如果没有选择框就采用列表选择的方法
                   common.selectSingleChoiceByText($node);
                   $node.parents("div.select:first").children("input[type=text]").attr("code",$node.attr("code"));
                   if($eventarea.find(".pacs-check-some2 input").val() !== "检查手段"){
                        $eventarea.find(".toptitle").show().text(
                            $eventarea.find(".pacs-check-some input:first").val()
                            + $eventarea.find(".pacs-check-some2 input:first").val()
                        );
                        
                        // $eventarea.find(".pacs-check-some21").show();
                        //- 由于点击记录 进入时，是将字体颜色设置为 浅色提示语， 所以 这里重置为黑色
                        var $form_visible = $group.find(".symptom-form>div:visible");
                            $form_visible.find(".form-area .pacs-check-some2 input").css("color","#000");
                            $form_visible.find(".pacs-checked-label").css({
                                "color": "#000"
                            })
                   }

                   
                   setTimeout(function(){
                       var $current21Input = $eventarea.find(".form-area .pacs-check-some21 input");
                        var $current3Input = $eventarea.find(".form-area .pacs-check-some3 input");
                       if($current21Input.val() === "体位" || $current21Input.val() == "要求") {
                            $eventarea.find(".form-area .pacs-check-some21 input").css({
                                color: "#aaa"
                            });
                        }else {
                            $eventarea.find(".form-area .pacs-check-some21 input").css({
                                color: "#000"
                            });
                        }
                        
                        //修复 选择机型而没有选择体位要求 按钮变色问题
                        var some21ValVisible = $eventarea.find(".pacs-check-some21 input:visible").val();
                        if(some21ValVisible) {
                            if(
                                some21ValVisible =="要求" ||
                                some21ValVisible =="体位" ||
                                some21ValVisible ==""||
                                $current3Input.val() ==""
                              ){
                                $eventarea.find(".make-sure").addClass("disabled");
                            } else {
                                 $eventarea.find(".make-sure").removeClass("disabled");
                            }
                        }else {
                            if($eventarea.find(".pacs-check-some3 input:visible").val() != ""){
                                 $eventarea.find(".make-sure").removeClass("disabled");
                            }
                        }
                    },0);
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
        
        /*** select的点击事件,已检里的时间，不包括项目的下拉框,与项目的分开写***/
        $eventarea.find(".form-area-checked-pacs").find("div.select.box-r-s").on("click.common",function (e) {
           var $select=$(this);
           common.selectHide($select);
           $select.addClass("focus").children("ul").fadeIn();
           e.stopPropagation();
        });

        /*** select的下拉菜单的点击事件,已检里的时间，不包括项目的下拉框***/
        $eventarea.find('.form-area-checked-pacs ul.box-r-s').on("click.common","a,input,div",function (e) {
           var $node=$(e.target),
                $a=$node.parents("a:first");
                
           if(e.target.nodeName==="A"){
               if($node.children("div.c-r.checkbox").length===0){
                   common.selectSingleChoiceByText($node);
                   if(pacsValidateTime(e.target)){
                       $eventarea.find(".validate").removeClass("d-n");
                       $eventarea.find(".make-sure.usable").removeAttr("disabled").removeClass("disabled");
                       $eventarea.find(".validate").addClass("d-n");
                   }else{
                       $eventarea.find(".validate").addClass("d-n");
                       $eventarea.find(".make-sure.usable").attr("disabled","true").addClass("disabled");
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

        $eventarea.find(".pacs-checked-label").click(function(){
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
            
            var $checkbox=$(this);
            //-判断是否有disabled ，如果有就直接返回
            if($checkbox.children(".box-r-s").hasClass("disabled")){
                return false;
            };
            $(this).parents(".detail-list").find(".pacs-checked-label").find("input").val("");
            if($(this).hasClass("checked")){
                $(this).parents(".detail-list").find(".form-area").css("display","none");
                $(this).parents(".detail-list").find(".form-area-checked-pacs").css("display","block");
                $(this).parents(".detail-list").find(".cost").css("display","none");

                //$eventarea.find(".make-sure.usable").attr("disabled","true").addClass("disabled");//①如果这条取消注释，把②号注释掉
                $eventarea.find(".make-sure.usable").removeAttr("disabled").removeClass("disabled");//②手段没选时，确定禁用,所以切换后，确定可用，
            }else{
                $(this).parents(".detail-list").find(".form-area").css("display","block");
                $(this).parents(".detail-list").find(".form-area-checked-pacs").css("display","none");
                $(this).parents(".detail-list").find(".cost").css("display","block");

                //$eventarea.find(".make-sure.usable").removeAttr("disabled").removeClass("disabled");//①如果这条取消注释，把②号注释掉
                if($eventarea.find(".pacs-check-some2 input").val().length==0){//②手段没选时，确定禁用,所以切换后，判断是否禁用，
                    $eventarea.find(".make-sure.usable").attr("disabled","true").addClass("disabled");
                }
            }
            // common.addDateControlTime($eventarea.find(".pacs-checked-date>label"));
            // var yearTmpl = common.createYear(100);
            
            // $eventarea.find(".form-area-checked-pacs .date .select").eq(0).find("ul").html(yearTmpl);;
            // common.dateControlRelation($eventarea.find(".pacs-checked-date>label"));
            // common.chooseDropList();

            //- 判断 如果切换 “已检” 重置 确定按钮
            var $submit_bt = $(this).parent().siblings(".submit-bt");
            var $checkInput = $(this).parents("h3").siblings(".form-area").find(".pacs-check-some2 input");//获取到手段的值
            if($(this).hasClass("checked")){
                var $down_arrow = $(this).parent().siblings(".form-area-checked-pacs").find(".down-arrow");
                vaildeDate({"$down_arrow": $down_arrow, "$submit_bt": $submit_bt});
            }else if($checkInput.val()){
                $submit_bt.find(".make-sure").prop("disabled", false).removeClass("disabled");
            }
        });
        //校验日期是否选择
        function vaildeDate(option){
            //校验是否选择全部日期
            if( !pacsValidateTime(option.$down_arrow) ){
                
                option.$submit_bt.find(".make-sure").prop("disabled", "disabled").addClass("disabled");
                // console.log(option.$submit_bt.find(".make-sure").prop("disabled"));
            }
        }
        //给确定按钮添加事件，输出选择好的拼出来的信息
        $eventarea.children(".submit-bt").on("click", "button", function (e) {
            if (e.target.className !== "make-sure usable") { //如果不是确定按钮
                if($group.find("div.choose").data("lastStep")=="symptom-wrap"){
                    $eventarea.slideUp();//模态框消失
                    $group.find(".symptom-wrap").css("display","block");//滚动条显示
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
            
            var pacode=$eventarea.find(".pacs-check-some input:first").attr("code");
            var apcode=$eventarea.find(".pacs-check-some2 input:first").attr("code");
            if($eventarea.find(".pacs-checked-label").hasClass("checked")){//如果选中已检

                var checkedinfo='';
                checkedinfo+='<ul>'+$eventarea.find(".toptitle").text()+':</ul>';
                checkedinfo+='<ul>';
                //添加checkbox已选数据
                $eventarea.find(".form-area-checked-pacs .form-group").not(".pacs-checked-date").find(".checkbox").each(function(){
                    if($(this).hasClass("checked") && $(this).find("input:visible").length > 0){
                        checkedinfo+='<li style="overflow:hidden;width:280px;"><span>'
                        +$(this).find("input:visible").val();
                        +'</span></li>'
                    }
                });
                checkedinfo+='</ul>';
                var checktime="";
                $eventarea.find(".form-area-checked-pacs .pacs-checked-date input").each(function(index){
                    checktime+=$(this).val();
                    if(index==0){checktime+="年"}
                    if(index==1){checktime+="月"}
                    if(index==2){checktime+="日"}
                });
                checkedinfo+='<ul style="color:#ccc">'+checktime+'</ul>';

                // checkedinfo+='  <span>  '+ $eventarea.find(".form-area-checked-pacs textarea").val() +'</span>  ';

                var checkedli='<div pacsname="'+$eventarea.find(".toptitle").text()+'" totalid="'+$eventarea.attr("totalid")+'" wholeid="'+pacode+apcode+'" pa-standard-id="'+$eventarea.attr("pa-standard-id")+'" ap-standard-id="'
                +$eventarea.attr("ap-standard-id")+'" style="overflow:hidden"><span class="dot"></span>'+checkedinfo
                +'<i></i></div>';
            }else{
                var info="";
                $eventarea.find(".form-area .form-group").each(function(index){
                    var $node=$(this).find("input");
                    ////  需要拼接出 ---> 踝关节双X线 ( 双 ) : ( 平扫, 国产待检查 )
                    if($node.length<1||$node.val().length<1) return true;
                    if(index>3){info+=", "}//项目后面的内容添加上“，”
                    // if(index != 1){
                        info+=getVal($node);
                    // }
                    var inputVal = $eventarea.find(".pacs-check-some11 input:first").val();
                    if(inputVal && index == 2){info+=" ( "}//项目后面的内容添加上“(”
                    if(index == 2){
                        info += inputVal;
                    }
                    if(inputVal && index == 2){info+=" ) "}//项目后面的内容添加上“)”
                    if(index == 2){
                        info += ": <span style='color:#aaa;'> ( ";
                    }
                });
                var willCheck = " ,待检查"
                info += willCheck + " ) </span> ";
                var regDot = /\(\s*,/g;//匹配到 （， ,说明是没有其他数据，需要将 ，去掉
                if(regDot.test(info)){
                     info = info.replace(",", "");
                }
                info = info.replace("undefined", "");
                // 获取his数据，先注释掉
                // var resl=ajaxPost(Param.hostUrl+Param.get_his_pacs,{apparatusCode:apcode,partCode:pacode,hospitalCode:Param.hospitalCode});
                var resl = {
                    data:[]
                };// ↑
                /*后续可能会用到
                // if(!resl.data){
                //     // alert("his器查项目为空");
                //     // return false
                // }
                // info+="(" //这里是将his（）去掉
                // $(resl.data).each(function(index){
                //     info+=this.structuringName;
                // });
                // info+=')'+'<span style="color:#999"></span>';//这里是将his（）去掉
                info+='<span style="color:#999"></span>';
                */
                //- 判断的是如果有his就存储没有就不会保存his数据
                if(resl.data[0]){
                     var li='<p pacsname="'+$eventarea.find(".toptitle").text()+'" totalid="'+$eventarea.attr("totalid")+'" hiscode="'+resl.data[0].hisApparatusCode+'" hisname="'+resl.data[0].structuringName+'" apcode="'+apcode+' "pacode="'+pacode+'" wholeid="'+pacode+apcode+'" pa-standard-id="'+$eventarea.attr("pa-standard-id")+'" ap-standard-id="'+$eventarea.attr("ap-standard-id")+'">'+
                            '<span class="dot"></span>'+
                                info +
                            '<i style="display:none!important"></i>'+
                            '<i></i>'+
                        '</p>';
                }else {
                    var li='<p pacsname="'+$eventarea.find(".toptitle").text()+'" totalid="'+$eventarea.attr("totalid")+'" hiscode="" hisname="" apcode="'+apcode+' "pacode="'+pacode+'" wholeid="'+pacode+apcode+'" pa-standard-id="'+$eventarea.attr("pa-standard-id")+'" ap-standard-id="'+$eventarea.attr("ap-standard-id")+'">'+
                            '<span class="dot"></span>'+
                                info +
                            '<i style="display:none!important"></i>'+
                            '<i></i>'+
                        '</p>';
                }
            }

            var $li = $(li==undefined?checkedli:li);
            $li.click(function(){
                //模仿一次document.click
                common.selectHide();
                common.removeFocus();
                common.groupHide($group);
                isclickP = true;//标示点击的是选择的p项目
                $group.find(".symptom-container").css("display","block");
                $group.find(".symptom-form").fadeIn();
                $("#"+$(this).attr("wholeid")).slideDown().siblings().css("display","none");
                 
                
                $group.find(".symptom-wrap").css("display","none");
                $group.find(".symptom-all").slideUp();
                var $currentFormDiv = $group.find(".symptom-container .symptom-form>div:visible");
               
                var $currentCheck3Input =  $currentFormDiv.find(".pacs-check-some3 input:visible")
                
                $("#"+$(this).attr("wholeid")).find(".make-sure").removeClass("disabled");
                
                if($("#"+$(this).attr("wholeid")).find(".pacs-checked-label.checked>.box-r-s").hasClass("disabled")) {
                    $("#"+$(this).attr("wholeid")).find(".pacs-checked-label.checked>.box-r-s").removeClass("disabled");
                }else{
                    $("#"+$(this).attr("wholeid")).find(".pacs-checked-label.checked>.box-r-s").addClass("disabled");
                }
                if($currentCheck3Input.length > 0 && ($currentCheck3Input.val().trim() == "机型" || $currentCheck3Input.val().trim() == "")) {
                    $currentFormDiv.find(".make-sure").addClass("disabled");
                    // $currentFormDiv.find(".pacs-checked-label>div").addClass("disabled");
                }else {
                    $currentFormDiv.find(".make-sure:visible").removeClass("disabled");
                    $currentFormDiv.find(".pacs-checked-label>div:first").removeClass("disabled").prop("style","");
                }
            });
            $li.find("i:last").click(function(e){//点击小x删除已选择的器查项
                //var t=confirm('是否删除器查内容？');
                var t=true;
                t?this.parentNode.parentNode.removeChild(this.parentNode):void(0);
                if(!t){return false}
                e.stopPropagation();

                $("#"+pacode+apcode).remove();//删除对应的弹出层
                $group.find(".content p.symptom[apcode="+apcode+"][pacode="+pacode+"]").removeClass("focus");//找到已选择项对应全部项的那一项移除focus
                //重新生成推送
                $group.find(".symptom-wrap").children().remove();
                var paras={
                    doctorId:Param.doctorId,
                    size:40,
                    sexType:Param.sexType,
                    deptNo:Param.deptNo,
                    hospitalCode:Param.hospitalCode,
                    age:Param.age,
                    patientId : Param.patientId,
                    type : 6
                }
                var resl = ajaxPost(USUAL_URL,paras);
                var li = ajaxPushPacsList(resl.data);
                li+='<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';

                $group.find(".symptom-wrap").append($(li));
                $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                    $(this).blueTip();
                });
                common.changeContainerShadowWidth($group.find("div.container-shade"));
                $group.find(".symptom-wrap").css("display","block");
                moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
                $group.find(".symptom-wrap").children("div").click(function(){//推送条各项目
                    common.cancelReturnLastStep($group.find('div.choose'));
                    var formId=dynamicPacsForm(this);
                    addPacsEvents(formId,this);
                    $(this).parents(".symptom-wrap").css("display","none");
                    $(".pacs-main-input").val("");
                });
                showOrHideChoose();
                forDeleteCache();


            });
            //修复 bug-2054 器查-选择部位头颅骨后选择手段“磁共振MR”，点击确定，
            //文本框中生成一条记录“头颅骨磁共振MR(常规心电图)”，点击该记录修改手段为CT确定文本框中新增一条记录，
            //如下图所示，期望是在原记录上修改手段，不是新增。。
            //思路：修改后会产生两个记录，一个有对应的id，一个没有，所以需要判断哪个没有，然后将其移除。
            setTimeout(function(){
                $group.find(".choose .content p").each(function(i, v){
                    if($( "#" +　$(v).attr("wholeid")).length == 0){
                        $(this).remove();
                    }
                })
                $group.find(".choose .content>div").each(function(i, v){
                    if($( "#" +　$(v).attr("wholeid")).length == 0){
                        $(this).remove();
                    }
                })
            },0)


            $group.find(".choose").find("[wholeid="+pacode+apcode+"]").remove();
            $group.find(".choose .content").append($($li));
            
            $eventarea.slideUp();
            $group.find(".symptom-wrap").css("display","block");

            if(apcode==""){
                $group.find(".symptom-wrap").find("div[apcode][pacode="+pacode+"]").remove();//删除列表中的该项
            }else{
                $group.find(".symptom-wrap").find("div[apcode="+apcode+"][pacode="+pacode+"]").remove();//删除列表中的该项
            }
            if(apcode==""){
                $group.find(".content p.symptom[apcode][pacode="+pacode+"]").addClass("focus");//更多里标记已选
            }else{
                $group.find(".content p.symptom[apcode="+apcode+"][pacode="+pacode+"]").addClass("focus");//更多里标记已选
            }

            //点击确定后，用pacode和apcode作为id给div赋值，并清除isdisabled属性
            $eventarea.removeAttr("isdisabled");
            $eventarea.attr("id",pacode+apcode);

            moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
            $group.find(".symptom-container").css("display","none");
            showOrHideChoose();

            serverCache();
        });

        //给日期按钮的天添加事件，用于获取天数
        $eventarea.find(".form-area-checked-pacs .get-date-own").click(function(){
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

        addBlueTipEventPacs($("#"+formId).find("input"));
        //给被省略的疾病添加Tip鼠标移入显示全名
        function addBlueTipEventPacs($nodes) {
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

    function pacsValidateTime(el){//校验日期是否全部填写
        var count=0;
        $(el).parents(".pacs-checked-date").find("input").each(function(){
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
        common.addChooseBottom($group.find(".choose"))
    }

    function serverCache(){
        var list=$(".pacs .choose .content").children();
        inStandardIds = [];
        $(list).each(function(){
        	inStandardIds.push($(this).attr("pa-standard-id"));
        	if($(this).attr("ap-standard-id") != null && $(this).attr("ap-standard-id") != "null"){
        		inStandardIds.push($(this).attr("ap-standard-id"));
        	}
        });
        var param={
            patientId : Param.patientId,
            type: 6,
            inStandardIds : inStandardIds.join()
        }
        flushDataIntoCache(param);
    }

    function forDeleteCache(){
        if(Param.firstType!=6){return false}//推送依据不是这一栏的话，不进行推理
        var list=$(".pacs .choose .content").children();
        inStandardIds = [];
        $(list).each(function(){
        	inStandardIds.push($(this).attr("pa-standard-id"));
        	if($(this).attr("ap-standard-id") != null && $(this).attr("ap-standard-id") != "null"){
        		inStandardIds.push($(this).attr("ap-standard-id"));
        	}
        });
        var param={
            patientId : Param.patientId,
            type: 6,
            inStandardIds : inStandardIds.join()
        }
        ajaxPost(Param.hostUrl + "/reason/clean_reason_cache_by_type",param);
    }
    return {
        "initPacsData": initPacsData,
        "bindPacsClick": bindPacsClick
    }
})()
function getPacsForSave(){
    
    if($(".pacs .choose .content p,.pacs .choose .content div").length==0){return {}}
    var data={
        "type":6,
        "choose":[],
        "details": [],
    };
    
    $(".pacs .choose .content p,.pacs .choose .content div").each(function(){
        var optionId=$(this).attr("totalid");
        var pacsName=$(this).attr("pacsname");
        var chooseOption={
            "itemId":optionId,
            "itemName":pacsName,
            "itemDescribe":$(this).text(),
            "type":6,

            "remark":""
        };
        data.choose.push(chooseOption);

        var option={
            "itemId": optionId,
            "apcode":"",
            "title": {
              "itemName": "",
              "checkboxClass": ""
            },
            "contents": []
        };

        var $appendArea=$("#"+$(this).attr("wholeid"));
        option.apcode = $(this).attr("apcode");
        option.pacode = $(this).attr("pacode");
        
        if($appendArea.find(".pacs-checked-label").hasClass("checked")){
            
            option.title.checkboxClass = "true";
            $appendArea.find(".form-area-checked-pacs input,.form-area-checked-pacs textarea").each(function(i,v){
                
                var contentDetail={
                    
                    "type": $(v).attr("model"),
                    "checked": $(v).parent().hasClass("checked")
                };
                contentDetail["value"]=$(this).val();
                option.contents.push(contentDetail);
            });
        }else{
            $appendArea.find(".form-area input,.form-area textarea").each(function(i,v){
                var contentDetail={
                    "type": $(v).attr("model")
                };
                contentDetail["value"]=$(this).val();
                option.contents.push(contentDetail);
            });
        }
        data.details.push(option);
    });
    return data;
}

function getPacsForBasis(){
    var $group=$(".container .group").eq(6);
    var ob={};
    $group.find(".choose .content").children().each(function(){
        var $now_form=$("#"+$(this).attr("wholeid"));
        var obj={};
        var val="";
        $now_form.find(".form-area-checked-pacs").find("div[data-param-code]").each(function(){
            if($(this).hasClass("checked")){
                val+=$(this).attr("data-param-code")+"^";
            }
        });
        obj[$now_form.attr("data-param-code")]=val.substring(0,val.length-1);
        ob[$now_form.attr("data-param-code")]=obj;

    });
    return jsonTOstring(ob);
}

function savePacsForHis(){
    var $group=$(".container .group").eq(6).find(".choose .content");
    if($group.children().length==0){return []}
    var arr=[];
    $group.children().each(function(){
        arr.push({id:$(this).attr("hiscode"),name:$(this).attr("hisname"),num:1})
    });
    return arr;
}


