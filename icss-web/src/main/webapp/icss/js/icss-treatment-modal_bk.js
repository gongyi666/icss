//所有药品单信息
var all_drugItem = [];
$(function(){
    //单条药品单信息
    var drugItem ={};
    //药品数组,用来维护开药信息
    var drugInfoList = [];
    //填写单推送条排除ids
    var outDrugIds = [];
	var drugInfoWrapper =
	{
		deptNo:"001",
		doctorId:"1",
		diseaseIds:[1],
		size:"40",
		sysType:null,
		hospitalCode:Param.hospitalCode,
        drugIds:[]
	};

	//治疗栏
	var $group=$(".container .group").eq(8);

	//页面初始化事件
    $(window).ready(function () {
        changeContainerShadowWidth();
    });
    //页面resize事件
    $(window).resize(function () {
        changeContainerShadowWidth();
    });

    /*** 动态改变两侧的遮罩的宽度 ***/
    function changeContainerShadowWidth() {
        var $container=$("div.container"),
            $container_shade=$container.siblings("div.container-shade");
        $container_shade.css("width",$container.css("marginLeft"));
    }

    var $symptom_wrap=$group.find('div.symptom-container:first').children("div.symptom-wrap"),
        $button=$symptom_wrap.siblings("div.button"),
        $arrow_detail=$button.children("div.arrow.detail");
    $arrow_detail.off("click.main");
    $arrow_detail.on("click.main",function () {
        treatmentDetailClickEvent();
        addTreatmentFocus($arrow_detail.parent().parent().siblings("div.symptom-all"));
    });

    /*** 下拉出现全部药品事件 ***/
    function treatmentDetailClickEvent() {
        var $symptom_wrap=$group.find('div.symptom-container:first').children("div.symptom-wrap");
        var $symptom_all=$symptom_wrap.parent().siblings("div.symptom-all"),
            $wrap=$symptom_all.find("div.wrap");
        $wrap.empty();
        $symptom_all.children("div.title").children("div.button-group").empty();
        $.ajax({
            url:Param.hostUrl+"/kl/transverse/get_transverse_portrait_drug",
            async:true,
            data:{
                type:8
            },
            dataType:"json",
            type:"post",
            success:function (data) {
                if ($wrap.children().length !== 0) {
                    return;
                }
                var TreatmentAllTmpl=getTmpl("TreatmentAllTmpl"),
                    treatmentAllTmpl=new TreatmentAllTmpl();
                treatmentAllTmpl.addButton(data.data);
                $.each(data.data,function (i,v) {
                    treatmentAllTmpl.addWrapContent(v);
                });
                $symptom_all.children("div.title").children("div.button-group").append(treatmentAllTmpl.button);
                $symptom_all.children("div.wrap").append(treatmentAllTmpl.wrapObject.html());
                resetRelationData($symptom_all);
                $symptom_all.siblings("div.symptom-container").fadeOut();
                $symptom_all.slideDown();
                addTreatmentAllEvent($symptom_all);
                addTreatmentFocus($symptom_all);
            },
            error:function () {
                alert("加载全部症状失败！！！");
            }
        })
    }

    /*** 因为数据原因 关联数据添加的位置不正确 需要订正 ***/
    function resetRelationData($symptom_all) {
        var $wrap_contents=$symptom_all.find("div.wrap-content"),
            $contents=$wrap_contents.find("div.content");
        $contents.each(function () {
            var $content=$(this),
                $symptoms=$content.find("p.symptom");
            $content.attr("data-portraitid",$symptoms.eq(0).attr('data-portraitid'));
            $symptoms.removeAttr("data-portraitid");
        });

        //修正常用的显示的症状
        var data;
        var treat = treatmentForBasis();
        var resl = ajaxPost(Param.hostUrl + "/rule_controller/start_drug",treat);
        if(resl.data){
            data =resl.data;
        }else{
            var d_drugInfoWrapper = deepcopy(drugInfoWrapper);
            var diseaseIds  =getDiagnosis();
            d_drugInfoWrapper.diseaseIds = diseaseIds;
            d_drugInfoWrapper.type = 8;
            var resl2 = ajaxSetContentType(Param.hostUrl + "/kl/druginfo/get_drug_by_Treatment", JSON.stringify(d_drugInfoWrapper));
            data =resl2.data;
        }
        var $use_wrap_content=$wrap_contents.eq(0),
            $html=$('<div class="content" style="width: 100%;"></div>'),
            p="";
        $use_wrap_content.children().remove();
        $.each(data,function (i,v) {
            p ='<p class="symptom" data-symptom-name="'+this.name+'">'+(this.name.length>6?(this.name.slice(0,5)+"..."):this.name)+'</p>';
            //标记上一步
            this.source = 1;
            $html.append($(p).data("drugData",this));
        });
        $use_wrap_content.append($html);
        $use_wrap_content.find("p[data-symptom-name]").each(function(){
            $(this).blueTip();
        });


        /*//修正常用的显示的症状
        $.ajax({
            url:Param.hostUrl+"/kl/druginfo/get_drug_by_Treatment",
            async:true,
            data:
                JSON.stringify({
                    deptNo:"001",
                    doctorId:"1",
                    diseaseIds:getDiagnosis(),
                    size:"40",
                    sysType:null,
                    hospitalCode:"331023",
                    drugIds:[],
                    type:8
                }),
            contentType:"application/json",
            type:"post",
            success:function (data) {
                var $use_wrap_content=$wrap_contents.eq(0),
                    $html=$('<div class="content" style="width: 100%;"></div>'),
                    p="";
                $use_wrap_content.children().remove();
                $.each(data.data,function (i,v) {
                    p ='<p class="symptom" data-symptom-name="'+this.name+'">'+(this.name.length>6?(this.name.slice(0,5)+"..."):this.name)+'</p>';
                    $html.append($(p).data("drugData",this));
                });
                $use_wrap_content.append($html);
            },
            error:function (req,info,err) {
                alert("加载常用列表失败");
            }
        });*/
    }

    /*** 给显示全部症状信息列表添加事件 ***/
    function addTreatmentAllEvent($symptom_all) {
        var $title=$symptom_all.children("div.title"),
            $arrow=$title.children("div.arrow"),
            $buttons=$title.find("button"),
            $wrap_contents=$symptom_all.find("div.wrap-content"),
            $menus=$wrap_contents.find("div.menu");

        $buttons.on("click.main",function () {
            var $button=$(this);
            $wrap_contents.hide().filter("[data-relationid="+$button.attr("data-id")+"]").fadeIn();
            $button.addClass("focus").siblings("button").removeClass("focus");
        });


        $menus.on("click.main","a",function (e) {
            var $a=$(e.target);
            menuClick($a);
        });

        $arrow.on("click.main",function () {
            treatmentAllUp($arrow);
        });

        $wrap_contents.on("click.main","p",function (e) {
            var $p=$(e.target);
            addTreatmentEvents();
            moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
            $arrow.click();
            click($p,e);
        });
        function treatmentAllUp($arrow) {
            $arrow.parent().parent().slideUp();
            $symptom_all.siblings("div.symptom-container").fadeIn();
        }

        function menuClick($a) {
            var $siblings_ul=$a.siblings("ul"),
                $contents,
                $menu=$a.parents("div.menu:first"),
                $a_siblings;
            if($siblings_ul.length>0){
                menuSlide($a,$siblings_ul);
                $a_siblings=$a.parent().siblings("li").children("a");
                $a_siblings.each(function () {
                    var $a=$(this);
                    menuSlideUp($a,$a.siblings("ul"));
                });
            }else{
                $contents=$menu.siblings("div.content");
                changeContent($a.attr("data-id"),$contents);
                changeMenuFocus($a,$menu);
            }
        }

        function changeMenuFocus($a,$menu) {
            if(!$a.hasClass("focus")){
                $menu.find("a.focus").removeClass("focus");
                $a.addClass("focus");
            }
        }
        function changeContent(data_id,$contents) {
            $contents.hide().filter("[data-portraitid="+data_id+"]").fadeIn();
        }
        function menuSlide($a,$siblings_ul) {
            if($siblings_ul.attr("data-drop")==="true"){
                menuSlideUp($a,$siblings_ul);
            }else{
                $siblings_ul.slideDown();
                $siblings_ul.attr("data-drop","true");
                $a.children("i").removeClass("icon-right-triangle").addClass("icon-triangle-down-copy");
            }
        }

        function menuSlideUp($a,$siblings_ul) {
            $siblings_ul.slideUp();
            $siblings_ul.attr("data-drop","false");
            $a.children("i").addClass("icon-right-triangle").removeClass("icon-triangle-down-copy");
        }

        $buttons.eq(0).click();
    }


    function addEvent() {
        $group.each(function (i) {
            var $_group=$(this),
                $input=$_group.children("div.box-r-s").children("input"),
                oldVal=null,
                $symptom_wrap=$_group.find("div.symptom-wrap"),
                $symptom_container=$_group.find("div.symptom-container:first"),
                $choose=$_group.find("div.choose");

            $_group.on("click.main",function (e) {
                var $_group=$(this);

                e.stopPropagation();
            });
            if(i===0){
            	$choose.parent().dropInput(function () {});
            }else{
                $input.on("focus click input propertychange",function (e) {
                    var $input=$(this),
                        val=$input.val().trim(),
                        $symptom_container=$input.siblings("div.symptom-container");
                    $_group.addClass("focus");
                    $symptom_container.slideDown();
                    if(oldVal!==val){
                        oldVal=val;
                    }
                    groupHide($_group);
                    e.stopPropagation();
                });
                $input.dropInput(function () {});
            }
            $choose.on("click.never-search",function (e) {
                var $choose=$(this),
                    val="",
                    $symptom_container=$choose.siblings("div.symptom-container:first");
                $_group.addClass("focus");
                $symptom_container.slideDown();
                if(oldVal!==val){
                    oldVal=val;
                }
                groupHide($_group);
                e.stopPropagation();
            });

            $symptom_wrap.on("click.main","div",function (e) {
                var $symptom=$(e.target).hasClass("symptom")?$(e.target):$(e.target).parents("div.symptom:first"),
                    symptom_name=$symptom.attr("data-symptom-name");
                if($symptom_container.find("div.symptom-form").length===0){
                    addSymptomForm($symptom_container,$symptom.attr("data-id"),symptom_name);
                }
                showSymptomForm($symptom_container.find("div.symptom-form"));
                clearInput($input);
                e.stopPropagation();
            });
        });
    }

    //给对应的症状加上已经选择的焦点
    function addTreatmentFocus($symptom_all) {
        var $ps=$symptom_all.siblings("div.choose").children("p");
        $symptom_all.find("p.symptom.focus").removeClass("focus");
        $ps.each(function () {
            var $p=$(this);
            $symptom_all.find("p.symptom[data-id="+$p.attr("data-relation-id")+"]").addClass("focus");
        });
    }

    /*** 关键字（拼音/中文）输入选择后，自动清空输入框 ***/
    function clearInput($input) {
        $input.val("");
    }

    /*** 隐藏其他group下展开的推送列表等 ***/
    function groupHide($_group) {
        var $not_groups=$group.not($_group);
        $not_groups.removeClass("focus").find("div.symptom-container").fadeOut().find("div.symptom-form").hide().end().siblings("div.symptom-all").fadeOut();
    }

    /*** 增加动态表单的函数 ***/
    function addSymptomForm($symptom_container,data_id,symptom_name){
        $.ajax({
            url:Param.hostUrl+"/kl/questioninfo/get_questioninfo_by_subitemid",
            async:true,
            data:{
                subitemId:data_id
            },
            dataType:"json",
            type:"post",
            success:function (data) {
                var DynamicForm=getTmpl("DynamicForm"),
                    dynamicFormTmpl=new DynamicForm(data_id);
                dynamicFormTmpl.setTitle(symptom_name);
                dynamicFormTmpl.setTitleRight($symptom_container.parent().parent().index());
                $.each(data.data,function (i1,v1) {
                    dynamicFormTmpl.addLabel(v1.id,v1.name,v1.questionContentList[0].type);
                    $.each(v1.questionContentList,function (i2,v2) {
                        dynamicFormTmpl.addControl(v2)
                    });
                    dynamicFormTmpl.addFormGroupFinish();
                });
                $symptom_container.append(dynamicFormTmpl.getDynamicForm());
                var $symptom_form=$symptom_container.find("div.symptom-form");
                showSymptomForm($symptom_form);
                common.symptomFormAddEvent($symptom_form);
            },
            error:function (req,info,err) {
                alert("加载症状列表失败");
            }
        });
    }
    /*** 显示动态表单的函数 ***/
    function showSymptomForm($symptom_form) {
        $symptom_form.parent().show();
        $symptom_form.slideDown().siblings("div.symptom-form").hide();
        hideSymptomElse($symptom_form.siblings("div.symptom-else"));
    }

    /*** 隐藏symptom-else ***/
    function hideSymptomElse($symptom_else) {
        $symptom_else.addClass("d-n");
    }
    /*** 显示symptom-else ***/
    function showSymptomElse($symptom_else) {
        $symptom_else.removeClass("d-n");
    }

    function reLoadSubItemInfo(data, $symptom_wrap) {
        var html = "",
            SubItemTmpl = getTmpl("SubItemTmpl"),
            subItemTmpl = new SubItemTmpl();
        $.each(data, function (i, v) {
            subItemTmpl.setDataId(v.id);
            //subItemTmpl.setTransCode(v.transCode);
            subItemTmpl.setName(v.name);
            html += subItemTmpl.getSymptom();
        });
        html+='<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';
        $symptom_wrap.html(html);
        common.changeContainerShadowWidth($symptom_wrap.find("div.container-shade"));
    }

    addEvent();

    /*** 获取模板类 ***/
    function getTmpl(name) {

        /*** 加载推送提示信息的模板类 ***/
        function SubItemTmpl() {}
        SubItemTmpl.prototype.divStart='<div class="symptom" data-id="';
        SubItemTmpl.prototype.divName='" data-symptom-name="',
            SubItemTmpl.prototype.divEnd='">';
        SubItemTmpl.prototype.iStart='<i class="iconfont icon-i"><div class="box-r-s detail-info"><p class="title">用药指南</p><p class="info">';
        SubItemTmpl.prototype.spanShow='<span class="';
        SubItemTmpl.prototype.spanDetail='"> ... </span><span class="d-n">';
        SubItemTmpl.prototype.divFinish='</span></p></div></i></div>';
        SubItemTmpl.prototype.setDataId=function (dataId) {
            this.dataId=dataId;
        };
        SubItemTmpl.prototype.setName=function (name) {
            this.symptomName=name;
            if(name.length>6){
                this.name=name.slice(0,7)+"...";
            }else{
                this.name=name;
            }
        };
        SubItemTmpl.prototype.setInfo=function (info) {
            if(info.length>112){
                this.infoSimple=info.slice(0,113);
                this.infoDetail=info.slice(113,info.length);
                this.spanClass="";
            }else{
                this.infoSimple=info;
                this.infoDetail="";
                this.spanClass="d-n";
            }
        };
        SubItemTmpl.prototype.getSymptom=function () {
            return this.divStart+this.dataId+this.divName+this.symptomName+this.divEnd+this.name+this.iStart+this.infoSimple+this.spanShow+this.spanClass+this.spanDetail+this.infoDetail+this.divFinish;
        };


        /*** 动态表单的模板类 ***/
        function DynamicForm(data_id) {
            this.detail='<div class="symptom-form d-n" data-relation-id="'+data_id+'"><div class="title"><span>';
        }
        DynamicForm.prototype.setTitle=function (title) {
            this.title=title;
        };
        DynamicForm.prototype.isMainSuit=function (index) {
            return !index?true:false;
        };
        DynamicForm.prototype.setTitleRight=function (index) {
            this.titleRight='</span><div class="c-r checkbox"><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>无</div></div>';
            this.detail+=this.title+this.titleRight;
        };
        DynamicForm.prototype.getDynamicForm=function () {
            return this.detail+'<div class="button"><button class="gray cancel">取消</button><button class="blue confirm">确定</button></div></div>';
        };
        DynamicForm.prototype.addLabel=function (id,name,type) {
            this.detail+=(type!=="7")?'<div class="form-group" data-id="'+id+'"><label>'+(name?name:"")+'</label>':
                '<div class="form-group" data-id="'+id+'" style="width: 100%"><label>'+(name?name:"")+'</label>';
        };
        DynamicForm.prototype.addFormGroupFinish=function () {
            this.detail+='</div>';
        };
        DynamicForm.prototype.addControl=function (data) {
            function textControl(data) {
                var html='<input class="box-r-s" type="text" name="'+data.code+'"',
                    labelPrefix="",
                    labelSuffix="";
                if(data.labelPrefix){
                    labelPrefix='<span>'+data.labelPrefix+'</span>';
                    html+="data-labelPrefix="+data.labelPrefix+" ";
                }
                if(data.labelSuffix){
                    labelSuffix='<span>'+data.labelSuffix+'</span>';
                    html+="data-labelSuffix="+data.labelSuffix+" ";
                }
                html+=">";
                this.detail+=labelPrefix+html+labelSuffix;
            }

            function selectControl(data) {
                var html='<div class="select box-r-s w-70px"><input type="text" readonly name="'+data.code+'"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n">';
                $.each(data.contentDetailList,function (i,v) {
                    html+='<li><a>'+v.name+'</a></li>';
                });
                html+='</ul></div>';
                this.detail+=html;
            }

            function checkboxAndRadio(data) {
                var html="",
                    clazz=(data.type==="4"?"checkbox":"radio");
                $.each(data.contentDetailList,function (i,v) {
                    html+='<div class="c-r '+clazz+'" data-name="'+v.code+'"><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>'+v.name+'</div>';
                });
                this.detail+=html;
            }

            function textAreaControl() {
                this.detail+='<textarea class="box-r-s"></textarea>';
            }

            function dateControl() {
                this.detail+='<div class="date-toggle box-r-s" data-name="date_toggle"><div class="background-color"></div><span class="focus">日期</span><span class="">时长</span></div><div><div class="select box-r-s w-70px"><input type="text" readonly><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n"><li><a>2000</a></li><li><a>2001</a></li><li><a>2002</a></li></ul></div><span>年</span><div class="select box-r-s w-70px"><input type="text" readonly><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n"><li><a>1</a></li><li><a>2</a></li><li><a>3</a></li><li><a>4</a></li><li><a>5</a></li><li><a>6</a></li><li><a>7</a></li><li><a>8</a></li><li><a>9</a></li><li><a>10</a></li><li><a>11</a></li><li><a>12</a></li></ul></div><span>月</span><div class="select box-r-s w-70px"><input type="text" readonly><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n"><li><a>1</a></li><li><a>2</a></li><li><a>3</a></li><li><a>4</a></li><li><a>5</a></li><li><a>6</a></li><li><a>7</a></li><li><a>8</a></li><li><a>9</a></li><li><a>10</a></li><li><a>11</a></li><li><a>12</a></li></ul></div><span>日</span></div><div class="d-n"><input class="box-r-s" type="number" style="width: 70px;height: 30px;float: left;margin-right: 5px"><div class="select box-r-s w-50px"><input type="text" readonly><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n"><li><a>天</a></li><li><a>月</a></li><li><a>年</a></li></ul></div></div>';
            }

            function selectCheckbox(data) {
                var html='<div class="select box-r-s w-130px"><input type="text" readonly name="'+data.code+'"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n">';
                $.each(data.contentDetailList,function (i,v) {
                    html+='<li data-id="'+v.id+'"><a><div class="c-r checkbox"><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>'+v.name+'</div></a></li>';
                });
                html+='</ul></div>';
                this.detail+=html;
            }
            function selectCheckboxAndText(data) {
                var html='<div class="select box-r-s w-240px"><input type="text" readonly name="'+data.code+'"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n">';
                $.each(data.contentDetailList,function (i1,v1) {
                    html+='<li data-id="'+v1.id+'"><a><div class="c-r checkbox"> <div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>'+v1.name+'</div>';
                    if(v1.questionInfos.length>0){
                        $.each(v1.questionInfos[0].questionContentList,function () {
                            html+='<input class="box-r-s d-n" type="text">';
                        });
                    }
                    html+='</a></li>';
                });
                html+='</ul></div>';
                this.detail+=html;
            }
            function selectUl(data) {
                var html='<div class="select box-r-s w-100px" id="aaa"><input type="text" readonly name="'+data.code+'"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n">';
                $.each(data.contentDetailList,function (i1,v1) {
                    html+='<li><a>'+v1.name;
                    if(v1.questionInfos.length>0){
                        html+='<i class="iconfont icon-right-triangle"></i></a><ul class="box-r-s">';
                        $.each(v1.questionInfos[0].questionContentList[0].contentDetailList,function (i2,v2) {
                            html+='<li><a>'+v2.name+'</a></li>';
                        });
                        html+='</ul></li>';
                    }else{
                        html+='</a></li>';
                    }
                });
                html+='</ul></div>';
                this.detail+=html;
            }

            var control={
                "2":textControl,//文本框
                "3":selectControl,//单选下拉框
                "4":checkboxAndRadio,//单选和复选框
                "5":checkboxAndRadio,
                "6":textAreaControl,
                "7":dateControl, //日期控件
                "8":selectCheckbox, // 下拉多选
                "9":selectCheckboxAndText, // 下拉多选带文本
                "10":selectUl
            };

            control[data.type].call(this,data);
        };


        /*** 全部症状的模板 ***/
        function TreatmentAllTmpl() {
            this.button="";
            this.wrap="";
            this.wrapObject =$("<div></div>");
        }
        TreatmentAllTmpl.prototype.addButton=function (data) {
            var treatmentAllTmpl=this;
            $.each(data,function (i,v) {
                treatmentAllTmpl.button+='<button data-id="'+v.id+'">'+v.name+'</button>';
            });
            treatmentAllTmpl.button+='<div class="setting"></div>';
        };
         TreatmentAllTmpl.prototype.addWrapContent=function (data) {
            var treatmentAllTmpl=this;
            if(data.portraitList.length===0){
                treatmentAllTmpl.wrap ='<div class="wrap-content d-n" style="width: 100%" data-relationId="'+data.id+'"><div class="content">';
                if(data.drugList){
                    $.each(data.drugList,function (i,v) {
                        content+='<p class="symptom" data-id="'+v.id+'" data-name="'+v.name+'" data-type="'+v.type+'" data-symptom-name="'+v.name+'" data-portraitid="'+v.portraitId+'">'+v.name.length>6?(v.name.slice(0,7)+"..."):v.name+'</p>';
                    });
                }
                treatmentAllTmpl.wrap+='</div></div>';
            }else{
                var $content=$("<div></div>");
                treatmentAllTmpl.wrap ='<div class="wrap-content d-n" data-relationId="'+data.id+'"><div class="menu"><ul class="first-level">';
                $.each(data.portraitList,function (i,v) {
                    treatmentAllTmpl.wrap+='<li><a data-id="'+v.id+'"><i class="iconfont icon-right-triangle"></i>'+v.name+'</a>';
                    if(v.portraitList.length>0){
                        treatmentAllTmpl.wrap+='<ul class="second-level d-n" data-drop="false">';
                        $.each(v.portraitList,function (i2,v2) {
                            treatmentAllTmpl.wrap+='<li><a data-id="'+v2.id+'">'+v2.name+'</a></li>';
                            if(v2.drugList.length>0){
                                $content.append(addContent(v2.id,v2.drugList));
                            }
                        });
                        treatmentAllTmpl.wrap+='</ul>';
                    }else{
                        if(v.drugList.length>0){
                            $content.append(addContent(v.id,v.drugList));
                        }
                    }
                    treatmentAllTmpl.wrap+='</li>';
                });
                treatmentAllTmpl.wrap+='</ul></div></div>';
                var $wrap = $(treatmentAllTmpl.wrap);
                $wrap.append($content);
                treatmentAllTmpl.wrapObject.append($wrap);


                function addContent(id,data) {
                    var $div = $("<div class='content d-n'></div>");
                    $.each(data,function (i,v) {
                        var p = '<p class="symptom" data-symptom-name="'+v.name+'" data-portraitid="'+id+'">'+(v.name.length>6?(v.name.slice(0,5)+"..."):v.name)+'</p>';
                        $div.append($(p).data("drugData",data))
                    });
                    return $div;
                }
            }
        };


        var temp={
            "SubItemTmpl":SubItemTmpl,
            "DynamicForm":DynamicForm,
            "TreatmentAllTmpl":TreatmentAllTmpl
        };

        return temp[name];
    }

    //------------------------------------------------------------------------------------------------------

	
    bindTreatmentClick();
    
    $group.find(".icon-sanjiaoxia").click(function(){
        $group.find(".symptom-all").slideDown();
    });
    
    $group.find(".icon-shangsanjiao").click(function(){
        $group.find(".symptom-all").slideUp();
    });

    function bindTreatmentClick(){
    	$group.click(function(e){
    		$(".box-r-s.detail-info").hide();
            e.stopPropagation();
            return false;
        });
    	
    	//获取焦点时获取药品信息
    	$group.find("input").click(function(){
    	    //收起更多
            $group.find(".symptom-all").slideUp();
    	    //高亮输入框
            $(this).parents(".group").addClass("focus");
            common.groupHide($(this).parents(".group"));
            common.autoScrollY($("body"),$(this).parents("div.group"),-300);
    	    //如果有输入内容调用keyup事件
    	    if($(this).val()!=""){
    	        $(this).keyup();
    	        return;
            }
            if ($group.find(".symptom-container .symptom-form").css("display") == "block") {
    	        //需要判断是否是组合药，如果不是组合要收起
                if(drugInfoList.length==1&&drugInfoList[0].groupDrugDetailWrapperList.length==1&&drugInfoList[0].type!=4&&drugInfoList[0].isGroup!=1){
                    $group.find(".symptom-form[active]").removeAttr("data-relation-id").empty().css("display", "none");
                    //从outDrugIds去除id
                    for(var i=0; i<outDrugIds.length; i++) {
                        if(outDrugIds[i] == drugInfoList[0].id) {
                            outDrugIds.splice(i, 1);
                            break;
                        }
                    }
                }else{
                    return false;
                }
            }
            drugInfoList = [];
    	    drugItem = {};
            //重新获取过滤ids
            outDrugIds = [];
            for(var i=0;i<all_drugItem.length;i++){
                var list = all_drugItem[i].drugInfoList;
                if(list.length==1){
                    outDrugIds.push(list[0].id);
                }
            }
            $group.find(".symptom-form[active]").removeAttr("data-relation-id").empty().css("display","none");//因为common.js里的groupHide会在document click时将其变为隐藏
            $group.find(".symptom-wrap").empty().css("display", "block");

            var data;
            var treat = treatmentForBasis();
            var resl = ajaxPost(Param.hostUrl + "/rule_controller/start_drug",treat);
            if(resl.data){
                data =resl.data;
            }else{
                var d_drugInfoWrapper = deepcopy(drugInfoWrapper);
                var diseaseIds  =getDiagnosis();
                d_drugInfoWrapper.diseaseIds = diseaseIds;
                var resl2 = ajaxSetContentType(Param.hostUrl + "/kl/druginfo/get_drug_by_Treatment", JSON.stringify(d_drugInfoWrapper));
                data =resl2.data;
            }
            //剔除已开药
            for(var i=0;i<data.length;i++){
                for(var j=0;j<outDrugIds.length;j++){
                    if(data[i].id == outDrugIds[j]){
                        data.splice(i,1);
                        i-=1;
                        break;
                    }
                }
            }

            var li = ajaxPushTreatmentList(data);

            $group.find(".symptom-wrap").append($(li));
            $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                $(this).blueTip();
            });
            //添加两测阴影
            var shadeHtml ='<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';
            $group.find(".symptom-wrap").append(shadeHtml);
            common.changeContainerShadowWidth($group.find("div.container-shade"));

            addDrugInformationEvents();
            $group.find(".symptom-container").slideUp().slideDown();
            moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
            drugInfoClick();

        });

    	//输入文本时获取药品信息
    	$group.find("input").keyup(function(){
    		$group.find(".symptom-all").slideUp();
            $group.find(".symptom-wrap").children().remove();
            if ($group.find(".symptom-container .symptom-form").css("display") == "block") {
            }else{
                drugItem ={};
                drugInfoList =[];
            }
            var word=$(this).val();
            var resl = ajaxPost(Param.hostUrl+"/kl/druginfo/get_drug_by_input",{size:40,inputstr:word});
            var data =resl.data;
            //剔除已开药
            for(var i=0;i<data.length;i++){
                for(var j=0;j<outDrugIds.length;j++){
                    if(data[i].id == outDrugIds[j]){
                        data.splice(i,1);
                        i-=1;
                        break;
                    }
                }
            }
            //判断是注射组合还是中药组合，也要过滤类型
            if(drugInfoList.length>0){
                //筛选注射类西药
                if(drugInfoList[0].type==1||drugInfoList[0].type==2||drugInfoList[0].type==3){
                    for(var i=0;i<data.length;i++){
                        if((data[i].type !=1||data[i].type !=2)&&data[i].drugFormulation!=1){
                            data.splice(i,1);
                            i-=1;
                            continue;
                        }
                    }
                }
                //筛选中药
                if(drugInfoList[0].type==4||drugInfoList[0].type==8){
                    for(var i=0;i<data.length;i++){
                        if(data[i].type !=4){
                            data.splice(i,1);
                            i-=1;
                            continue;
                        }
                    }
                }
            }
            var li = ajaxPushTreatmentList(data);
            //添加两测阴影
            var shadeHtml ='<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';
            $group.find(".symptom-wrap").append(shadeHtml);
            common.changeContainerShadowWidth($group.find("div.container-shade"));
            $group.find(".symptom-container").slideDown();
            $group.find(".symptom-wrap").append($(li)).slideDown();
            $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                $(this).blueTip();
            });
            addDrugInformationEvents();
            moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
            drugInfoClick();
        });
    }
    
    //删除标题
    function delTital(){
    	$(".delTital").off("click").click(function(e){
            if($(this).parents("p").siblings("p").length>0){

            }else{
                $(this).parents(".choose").addClass("d-n");
            }
            //高亮输入框
            $(this).parents(".group").addClass("focus");
            common.groupHide($(this).parents(".group"));
            //如果删除的是填写单的标题，要将填写单也一并移除
            var relationId = $(this).parents("p").attr("data-relation-id");
            var divRelationId = $group.find(".symptom-form[active]").attr("data-relation-id");
            if(relationId==divRelationId){
                $group.find(".symptom-form[active]").removeAttr("data-relation-id").empty();
                $group.find(".symptom-container").css("display", "none");
            }
            var index = $(this).parents("p").index();
            $(this).parents("p").remove();
            //
            if(all_drugItem[index-1].drugInfoList.length==1&&all_drugItem[index-1].drugInfoList[0].isGroup!=1){
                outDrugIds.splice(index-1,1);
            }
            //删除药品信息
            all_drugItem.splice(index-1,1);
            e.stopPropagation();
    	})
    }

    //填写单添加事件
    function addTreatmentEvents(){
        var $eventarea=$group.find(".symptom-form[active] .detail-list");
        $eventarea.click(function(){
            common.selectHide();
            common.removeFocus();
        });
        $eventarea.find("div.c-r.checkbox").each(function(){
            $(this).on("click.common",function () {
                var $checkbox=$(this);
                common.checkboxChangeState($checkbox);
            });
        });

        $eventarea.find(".down-arrow.box-r-s").on("click.common",function (e) {
           var $select=$(this).parent();
           
           //如果是选中药品div
           if($select.parents(".treatment-check-some:first").length > 0){
        	   if($select.find("ul table tbody").is(":empty")){
		       	   var d_drugInfoWrapper = deepcopy(drugInfoWrapper);
		       	   d_drugInfoWrapper.id = $select.find("input:first").attr("data-id");
		       	   var resu = ajaxSetContentType(Param.hostUrl+"/kl/druginfo/get_group_his_drug_info",JSON.stringify(d_drugInfoWrapper));
		       	   if(resu.data.hisDrugInfoDetail){
		       		   var hisDrugDiv = getHisDrugInfo(resu.data.hisDrugInfoDetail);
		       		   $select.find("ul div .drugListTbody").append($(hisDrugDiv));
		       		   
		       		var drgOnceDose = "";
		       		var drgDoseUnit = "";
		       		//切换默认选中
		       		$select.find("ul").find("div .drugListTbody").find("tr").click(function(e){
	                     $(this).find("td:first").html("<i class='iconfont icon-duigou1'></i>");
	                     $(this).siblings().find("td:first").html("");
	                     $(this).parents(".select.focus").removeClass("focus").children("ul").fadeOut();

	                     //选中后填充单次量，单次量单位(排序)
	                     var hisDrugData = $(this).data("hisDrugData");
	                     if(hisDrugData != null){
	                         //drugInfo.hisDrugData = hisDrugData; //将选中后的his药品存入drugInfoList中，重新渲染页面时，取剂量单位的时候需要
	                         //剂量单位下拉框
	                    	 $select.parents(".treatment-check-some:first").siblings("div.treatment-check-some2").find(".form-group ul").empty().append($(getHisDrgUnit(hisDrugData)));
	                         if(hisDrugData.drgDoseUnit!=null && hisDrugData.drgDoseUnit != ""){
	                             drgOnceDose = hisDrugData.drgDose;
	                             drgDoseUnit = hisDrugData.drgDoseUnit;
	                         }else if(hisDrugData.drgVolumeUnit!=null && hisDrugData.drgVolumeUnit != ""){
	                             drgOnceDose = hisDrugData.drgVolume;
	                             drgDoseUnit = hisDrugData.drgVolumeUnit;
	                         }else if(hisDrugData.drgMinUnit!=null && hisDrugData.drgMinUnit != ""){
	                             drgOnceDose = "1";
	                             drgDoseUnit = hisDrugData.drgMinUnit;
	                         }
	                     }
	                     $select.parents(".treatment-check-some:first").siblings("div.treatment-check-some2").find("input:first").val(drgOnceDose);
	                     $select.parents(".treatment-check-some:first").siblings("div.treatment-check-some2").find("input:eq(1)").val(drgDoseUnit);
	                     e.stopPropagation();
	                 })
		       	   }
           	   }
           }
           common.selectHide($select);
           $select.addClass("focus").children("ul").fadeIn();
           e.stopPropagation();
        });
        
        /*** select的下拉菜单的点击事件***/
        $eventarea.find(".row").not(".treatment-check-some").find('ul.box-r-s').on("click.common","a,input,div",function (e) {
           var $node=$(e.target),
                $a=$node.parents("a:first");
           if(e.target.nodeName==="A"){
               if($node.children("div.c-r.checkbox").length===0){
                   common.selectSingleChoiceByText($node);
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

        //给确定按钮添加事件，输出选择好的拼出来的信息
        $eventarea.children(".submit-bt").off("click","button").on("click", "button", function (e) {
            //取消
            if (e.target.className !== "make-sure usable") {
                $group.find(".symptom-form").slideUp().empty().removeAttr("data-relation-id");
                $(".treatment-main-input").val("");
                var source = drugInfoList[drugInfoList.length-1].source;
                drugInfoList = [];//取消时清空药品数组
                drugItem ={};
                //重新获取过滤ids
                outDrugIds = [];
                for(var i=0;i<all_drugItem.length;i++){
                    var list = all_drugItem[i].drugInfoList;
                    if(list.length==1){
                        outDrugIds.push(list[0].id);
                    }
                }
                //返回到常用
                if(source==1){
                    //var $symptom_wrap=$group.find('div.symptom-container:first div.button div.arrow.detail');
                    $arrow_detail.click();
                }else{
                    //取消键恢复推送条
                    $group.find(".symptom-wrap").css("display","block");
                    var d_drugInfoWrapper = deepcopy(drugInfoWrapper);
                    var diseaseIds  =getDiagnosis();
                    d_drugInfoWrapper.diseaseIds = diseaseIds;
                    var resl = ajaxSetContentType(Param.hostUrl+"/kl/druginfo/get_drug_by_Treatment",JSON.stringify(deepcopy(d_drugInfoWrapper)));
                    //剔除已开药
                    var data = resl.data;
                    for(var i=0;i<data.length;i++){
                        for(var j=0;j<outDrugIds.length;j++){
                            if(data[i].id == outDrugIds[j]){
                                data.splice(i,1);
                                i-=1;
                                break;
                            }
                        }
                    }
                    var li = ajaxPushTreatmentList(data);
                    $group.find(".symptom-wrap").empty().append($(li));
                    $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                        $(this).blueTip();
                    });
                    //添加两测阴影
                    var shadeHtml ='<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';
                    $group.find(".symptom-wrap").append(shadeHtml);
                    common.changeContainerShadowWidth($group.find("div.container-shade"));
                }
                //重新绑定药品点击事件
                moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
                drugInfoClick();
                return;
            }

            function save(name){
            	$group.find(".box-r-s .choose").removeClass("d-n");
                //确定键
                //确定键返回治疗拼装文本
                var info= getTitleVal($eventarea);
                var li='<p><span class="dot"></span>'+info
                +'<i style="display:none!important"></i><i class="delTital"></i></p>';
                var $li=$(li);
                $li.click(function(){
                    // 获取当前时间戳(以s为单位)
                    var timestamp = Date.parse(new Date());
                    var relationId = timestamp / 1000;
                    $(this).attr("data-relation-id",relationId);
                    $group.find(".symptom-form[active]").attr("data-relation-id",relationId);
                    //获取缓存的drugInfoList信息
                    var index = $(this).index()-1;
                    drugItem = all_drugItem[index];
                    drugInfoList = drugItem.drugInfoList;
                    renderFromArea();
                    $group.find(".symptom-container").find(".symptom-form").slideDown().find(".detail-list").css("display","block");
                    if(drugInfoList[0].isGroup==1){
                        //组合药恢复推送条
                        var drugInfo = getDrugInfo();
                        var resl = ajaxSetContentType(Param.hostUrl+"/kl/druginfo/get_relation_drug_info",JSON.stringify(drugInfo));
                        var data = resl.data;
                        var li = ajaxPushTreatmentList(data);
                        $group.find(".symptom-wrap").empty().append($(li));
                        $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                            $(this).blueTip();
                        });
                        //添加两测阴影
                        var shadeHtml ='<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';
                        $group.find(".symptom-wrap").append(shadeHtml);
                        common.changeContainerShadowWidth($group.find("div.container-shade"));
                        moveSymptomWrap($group.find(".symptom-wrap"));
                        drugInfoClick();
                    }else{
                        $group.find(".symptom-wrap").empty().css("display","none");
                    }
                });
                getFormData();
                //填写单名
                if(name){
                    drugItem.name =name;
                }
                //判断是修改还是新增的
                var relationId = $group.find(".symptom-form").attr("data-relation-id");
                if(relationId&&$group.find(".choose").find("p[data-relation-id="+relationId+"]").size()==1){//修改
                    var _index = $group.find(".choose").find("p[data-relation-id="+relationId+"]").index()-1;
                    $group.find(".choose").find("p[data-relation-id="+relationId+"]").after($li).remove();
                    all_drugItem[_index] = drugItem;
                }else{//新增
                    $group.find(".choose").append($li);
                    all_drugItem.push(drugItem);
                }
                drugInfoList =[];
                drugItem ={};

                $eventarea.slideUp();
                $group.find(".symptom-wrap").css("display","block");
                $group.find(".symptom-container").slideUp();
                $group.find(".symptom-container .symptom-form").css("display","none");
                //标题删除事件
                delTital();
            }

            //数据校验true
            var flag = true;
            var drgOnceDose,drgDoseUnit,modName,frequencyName,liaocheng;
            $group.find(".all-form-area").find(".form-area").each(function(index){
                var $this =$(this);
                drgOnceDose = $this.find(".treatment-check-some2").find("input:first").val();
                drgDoseUnit = $this.find(".treatment-check-some2").find("input:eq(1)").val();
                if(drgOnceDose==""){
                    flag = false;
                    alert("单次量不能为空且必须是数字!");
                    return false;
                }
                if(!/^[1-9]\d*(\.\d+)?$|^0\.(\d+)$/.test(drgOnceDose)){
                    flag = false;
                    alert("单次量请填写非零数字!");
                    return false;
                }
                if(drgDoseUnit==""){
                    flag = false;
                    alert("单次量单位不能为空!");
                    return false;
                }
            });
            if(!flag){
                return;
            }
            if($group.find(".all-form-area").find(".bottom-form-area").length == 0){//六列
                modName = $group.find(".treatment-check-some3").find("input:first").val();
                frequencyName = $group.find(".treatment-check-some4").find("input:first").val();
                liaocheng = $group.find(".treatment-check-some5").find("input:first").val();
            }else{//三列
                modName = $group.find(".all-form-area").find(".bottom-form-area").find(".treatment-check-some3").find("input:first").val();
                frequencyName = $group.find(".all-form-area").find(".bottom-form-area").find(".treatment-check-some4").find("input:first").val();
                liaocheng = $group.find(".all-form-area").find(".bottom-form-area").find(".treatment-check-some5").find("input:first").val();
            }
            if(modName==""){
                alert("用法不能为空!");
                return;
            }
            if(frequencyName==""){
                alert("频次不能为空!");
                return;
            }
            if(liaocheng==""){
                alert("疗程不能为空且必须是正整数!");
                return;
            }
            if(!/^\+?[1-9][0-9]*$/.test(liaocheng)){
                alert("疗程请填写正整数!");
                return;
            }


            //判断不止一种药才提示组合保存
            var groupDrugDetailList = [];
            //什么类型的药
            var type = "";
            $.each(drugInfoList,function(index,drugInfo) {
                groupDrugDetailList = groupDrugDetailList.concat(drugInfo.groupDrugDetailWrapperList);
                type = drugInfo.type;
            })
            if(groupDrugDetailList.length>1) {
                //构建模态框
                var modal = '<div class="modal fade" tabindex="-1" role="dialog" id="modal"> <div class="modal-dialog" role="document"> <div class="modal-content" style="width:400px;margin:50% auto;"> <div class="modal-body"> <p>将该组合命名为<input id="modal_input" type="text"/>，保存到药品目录？</p> </div>'
                    + '<div class="modal-footer"> <button type="button" class="btn btn-primary" id="modal_save">保存</button> <button type="button" class="btn btn-info" id="modal_cancal">忽略</button> </div> </div> </div> </div>'
                $("body").append(modal);
                $("#modal_input").val($group.find(".symptom-form[active] .select input").val());
                //防止事件传播
                $("#modal").click(function (e) {
                    e.stopPropagation();
                })
                //给保存按钮注册事件
                $("#modal_save").click(function () {
                    var name = $.trim($("#modal").find("input").val());
                    if(name==""){
                        alert("组合名称不能为空！");
                        return ;
                    }
                    var d_drugInfoWrapper = deepcopy(drugInfoWrapper);
                    d_drugInfoWrapper.name = $("#modal").find("input").val();
                    d_drugInfoWrapper.drugInfoList = drugInfoList;
                    //设置药品类型
                    if(type ==1||type==2||type==3){
                        d_drugInfoWrapper.type =3;
                    }else{
                        d_drugInfoWrapper.type=8;
                    }
                    var response = ajaxPost(Param.hostUrl+"/kl/druginfo/is_exist",{name:name});
                    if(response.data==1){
                        //保存信息
                        var resl = ajaxSetContentType(Param.hostUrl+"/kl/druginfo/save_group_drug_info",JSON.stringify(d_drugInfoWrapper));
                        save($("#modal").find("input").val());
                        //保存完删除modal
                        $("#modal").modal('hide').remove();
                    }else{
                        alert("该组合名称已经存在!");
                        return;
                    }
                })
                $("#modal_cancal").click(function () {
                    save();
                    //保存完删除modal
                    $("#modal").modal('hide').remove();
                })

                $('#modal').modal({
                    keyboard: false,
                    backdrop: 'static'
                })
            }else{
                save();
            }
        });
    }
    
    //组装填写单中的信息，返回到推送条
    function getTitleVal($detailList){
        var yongfa = $detailList.find(".treatment-check-some3 .form-group").find("input:first").val();
        var pinci = $detailList.find(".treatment-check-some4 .form-group").find("input:first").val();
        var liangcheng = $detailList.find(".treatment-check-some5 .form-group").find("input:first").val();
        //分方，精放毒，先不考虑组合
        var fenfang = ""
        $.each(drugInfoList,function(index,drugInfo) {
            if(drugInfo.drugPsychotropic==1){
                fenfang += "<img src='img/logo-psy.png' height='20' width='20' style='margin-left: 10px;vertical-align: middle;'>";
            }else if(drugInfo.drugPsychotropic==2){
                fenfang += "<img src='img/logo-fang.png' height='20' width='20'style='margin-left: 10px;vertical-align: middle;'>";
            }else if(drugInfo.drugPsychotropic==3){
                fenfang += "<img src='img/logo-ma.png' height='20' width='20' style='margin-left: 10px;vertical-align: middle;'>";
            }else{

            }
        })
    	if($detailList.find("h3 .toptitle input").length == 0){ //六行
    		var name = $detailList.find(".treatment-check-some .form-group").find("input:first").attr("temp");
            var danciliang = $detailList.find(".treatment-check-some2 .form-group").find("input:first").val();
            var danciliangUnit = $detailList.find(".treatment-check-some2 .form-group").find("input:eq(1)").val();
            var beizhu = $detailList.find(".treatment-check-some6 .form-group").find("input:first").val();
            var lc = "疗程"+$detailList.find(".treatment-check-some5 .form-group").find("input:first").val()+"天";
            var pishi ="";
            if($detailList.find(".treatment-check-some6 .form-group").find(":checkbox").prop("checked")){
                pishi ="(皮试)"
            }
            return name + "：" + danciliang + danciliangUnit + pishi +"，" + yongfa + "，"+  pinci+  "，" + lc + fenfang;
    	}else if($detailList.find("h3 .toptitle input").length == 1){ //三行
    		var $formAreas = $detailList.find(".form-area");
    		var $bottomFormArea = $detailList.find(".bottom-form-area");
    		var _val = [];
    		$.each($formAreas,function(){
    			var _name = $(this).find(".treatment-check-some .form-group").find("input:first").attr("temp");
    			var _danciliang = $(this).find(".treatment-check-some2 .form-group").find("input:first").val();
    			var _danciliangUnit = $(this).find(".treatment-check-some2 .form-group").find("input:eq(1)").val();
                var beizhu = $(this).find(".treatment-check-some6 .form-group").find("input:first").val();
                var pishi ="";
                if($(this).find(".treatment-check-some6 .form-group").find(":checkbox").prop("checked")){
                    pishi ="(皮试)"
                }
    			_val.push(_name+"："+_danciliang+_danciliangUnit + pishi );
    		});
            var lc = "疗程"+$bottomFormArea.find(".treatment-check-some5 .form-group").find("input:first").val()+"天";
    		return _val.join(" / ")+"，"+yongfa+ "，"+ pinci +"，"+lc + fenfang;
    	}
    }


    //渲染开药内容，根据药品不同数量渲染成不同格式
    function renderFromArea() {
        console.log(drugItem);
        console.log(drugInfoList);
        var $active = $group.find(".symptom-form[active]");
        if (drugInfoList && drugInfoList.length > 0) {
            var $html = detailListArea();
            if (drugInfoList.length > 1) { //多个药品
            	$html.find(".all-form-area").append(bottomFormArea());
                $html.find(".all-form-area").prepend(threeFormArea());
                //根据第一个药品判断是中药还是西药，生成不同的组合名称
                var groupName ="";
                if(drugItem.name){
                    groupName = drugItem.name;
                }else{
                    if(drugInfoList[0].type==4||drugInfoList[0].type==8){
                        groupName ="新中药配方";
                    }else{
                        groupName ="新注射组合";
                    }
                }
                //修改组合名字
                $html.find("h3 b:first").empty().append('<div class="select box-r-s w-100px" style="height:20px;padding-left:10px;"><input type="text" style="font-weight:bold" value="'+ groupName +'" /></div>');
            }else if(drugInfoList[0].type==3 || drugInfoList[0].type==8) { //单个组合
                $html.find(".all-form-area").append(bottomFormArea());
                $html.find(".all-form-area").prepend(threeFormArea());
                //组合名字
                $html.find("h3 b:first").empty().append('<div class="select box-r-s w-100px" style="height:20px;padding-left:10px;"><input type="text" style="font-weight:bold" value="'+drugInfoList[0].name+'" /></div>');
            } else { //单个药品
                $html.find(".all-form-area").append(sixFormArea());
            }
            //构造页面元素,第一次添加药品时要有动画效果
            if($active.children().length>0){
                $active.empty().append($html);
            }else{
                $active.append($html);
            }
            $active.slideDown();
            //其他操作，计算价格，注册事件等
            //填写单添加时间
            addTreatmentEvents();
            //用药指南
            addDrugInformationEvents();
            //药品删除事件
            delDrug();
        }else{
            //没有药品时，清空填写单，并恢复推送条，重新绑定药品点击事件
            $active.empty().slideDown();
            //取消键恢复推送条
            var d_drugInfoWrapper = deepcopy(drugInfoWrapper);
            var diseaseIds  =getDiagnosis();
            d_drugInfoWrapper.diseaseIds = diseaseIds;
            var resl = ajaxSetContentType(Param.hostUrl+"/kl/druginfo/get_drug_by_Treatment",JSON.stringify(deepcopy(d_drugInfoWrapper)));
            //剔除已开药
            var data = resl.data;
            for(var i=0;i<data.length;i++){
                for(var j=0;j<outDrugIds.length;j++){
                    if(data[i].id == outDrugIds[j]){
                        data.splice(i,1);
                        i-=1;
                        break;
                    }
                }
            }
            var li = ajaxPushTreatmentList(data);
            $group.find(".symptom-wrap").empty().append($(li));
            $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                $(this).blueTip();
            });
            //添加两测阴影
            var shadeHtml ='<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';
            $group.find(".symptom-wrap").append(shadeHtml);
            common.changeContainerShadowWidth($group.find("div.container-shade"));
            drugInfoClick();
        }
    }

    //获取detail-list外部框架
    function detailListArea(){
    	var drugInfo = drugItem.drugInfoList[0];
    	var jiaji = "";
    	var zibei ="";
    	var daijian ="";
        if(drugInfo.type==4||drugInfo.type==8){
            if(drugItem.daijian==1){
                daijian ='<div class="c-r checkbox treatment-checked-label checked" daijian><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>代煎</div>';
            }else{
                daijian ='<div class="c-r checkbox treatment-checked-label" daijian><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>代煎</div>';
            }
        }
        if(drugItem.jiaji==1){
            jiaji = '<div class="c-r checkbox treatment-checked-label checked" jiaji><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>加急</div>';
        }else{
            jiaji = '<div class="c-r checkbox treatment-checked-label" jiaji><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>加急</div>';
        }
        if(drugItem.zibei==1){
            zibei = '<div class="c-r checkbox treatment-checked-label checked" zibei><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>自备</div>'
        }else{
            zibei = '<div class="c-r checkbox treatment-checked-label" zibei><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>自备</div>'
        }
    	var detailListHtml = '<div class="detail-list" style=""><h3><b style="line-height:20px;" class="toptitle">'+drugInfo.name
        +'</b>' + jiaji + zibei
        + daijian +'<div style="float:right;">价格:150元</div></h3><div class="all-form-area"></div>'
        +'<p class="submit-bt"><button class="make-sure usable" type="button">确定</button><button class="cancle unusable" type="button">取消</button></p></div>';
    	return $(detailListHtml);
    }
    
    //底部bottom-form-area
    function bottomFormArea(){
    	var formArea = '<div class="bottom-form-area" style="border-top:1px dashed #ddd;margin-top:15px;"><div class="row treatment-check-some3" style="float:left;"></div>'
	        +'<div class="row treatment-check-some4" style="float:left;"></div>'
	        +'<div class="row treatment-check-some5" style="float:left"></div>'
	        +'<div style="clear:both"></div></div>';
        var drugInfo = drugItem.drugInfoList[0];
    	var $formArea = $(formArea);
    	
		//用法
    	var modType = "";
    	if(drugInfo.type == 4){
    		modType = 4;
    	}else{
    		modType = 1;
    	}
    	//var drugUseModRes = ajaxPost(Param.hostUrl+"/his/druguse/get_druguse_mod_list",{"type":modType,"hospitalCode":Param.hospitalCode}); //用法接口
        //var drugFrequency = ajaxPost(Param.hostUrl+"/his/druguse/get_drug_frequency_list",{"enName":"","hospitalCode":Param.hospitalCode});  //频次接口
    	//var drugUseModRes = ajaxGet(Param.hostUrl+"/his/druguse/get_druguse_mod_list","type="+modType+"&hospitalCode=1006"); //用法接口
        //var drugFrequency = ajaxGet(Param.hostUrl+"/his/druguse/get_drug_frequency_list","enName=&hospitalCode=1006");  //频次接口
    	var drugUseModRes = ajaxGet(Param.hostUrl+"/his/druguse/get_druguse_mod_list",{"type":modType,"hospitalCode":Param.hospitalCode}); //用法接口
        var drugFrequency = ajaxGet(Param.hostUrl+"/his/druguse/get_drug_frequency_list",{"enName":"","hospitalCode":Param.hospitalCode});  //频次接口

    	//数据
    	var inputModName = "";
    	var inputFrequencyName = "";
    	var inputLiaocheng = "";
    	if('modName' in drugItem && 'frequencyName' in drugItem && 'liaocheng' in drugItem ){
    		inputModName = drugItem.modName;
        	inputFrequencyName = drugItem.frequencyName;
        	inputLiaocheng = drugItem.liaocheng;
    	}else{
    		var groupDrugDetailWrapper = drugInfo.groupDrugDetailWrapperList[0]; //取第一条数据的用法，频次，疗程（如果保存组合药品时，组合药品的这些值应该是一致的）
    		inputModName = getModNameById(groupDrugDetailWrapper.modId,drugUseModRes.data);
    		inputFrequencyName = getFrequencyNameByEnName(groupDrugDetailWrapper.freEnName,drugFrequency.data);
    	}
       
        var $form_area = $formArea.find(".treatment-check-some3");
        $form_area.append($('<div class="form-group"><label>用法</label><div class="select box-r-s w-80px" >'
                +'<input type="text" readonly="" value="'+(inputModName==undefined?"":inputModName)+'"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i>'
                +'</div><ul class="box-r-s d-n"></ul></div></div>'));
        $form_area.find(".form-group ul").append($(getDrugUserOrFrequency(drugUseModRes.data)));

        //频次
        $form_area = $formArea.find(".treatment-check-some4");
        $form_area.append($('<div class="form-group"><label>频次</label><div class="select box-r-s w-80px" >'
                +'<input type="text" readonly="" value="'+(inputFrequencyName==undefined?"":inputFrequencyName)+'"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i>'
                +'</div><ul class="box-r-s d-n"></ul></div></div>'));
        $form_area.find(".form-group ul").append($(getDrugUserOrFrequency(drugFrequency.data)));

        //疗程
        $form_area = $formArea.find(".treatment-check-some5");
        $form_area.append($('<div class="form-group"><label>疗程</label><div class="select box-r-s w-100px"><input type="text" value="'+(inputLiaocheng==undefined?"":inputLiaocheng)+'"></div></div>'));
    	
        return $formArea;
    }

    //三行显示
    function threeFormArea(){
    	var formAreas = [];
    	$.each(drugInfoList,function(index,drugInfo){
            //循环
            for(var i=0;i<drugInfo.groupDrugDetailWrapperList.length;i++){
                 var groupDrugDetailWrapper = drugInfo.groupDrugDetailWrapperList[i];
                 var formArea =
                     '<div class="form-area"><div class="row treatment-check-some" style="float:left;"></div>'
                     +'<div class="row treatment-check-some2" style="float:left;"></div>'
                     +'<div class="row treatment-check-some6" style="float:left;"></div>'
                     +'<p style="float:left;padding-top:15px;margin-left:-10px;cursor:pointer"><i class="treatment-del"></i></p>'
                     +'<div style="clear:both"></div></div>';
                 var $formArea = $(formArea);
                 //药名
                 var $form_area = $formArea.find(".treatment-check-some");
                 //var name=drugInfo.name;
                 var name = groupDrugDetailWrapper.hisDrgName == null?drugInfo.name:groupDrugDetailWrapper.hisDrgName; //如果组合明细药品名字为空，取点击的药品名称
                 var inputName=name;
                 if(name&&name.length>6){
                     inputName = name.substring(0,5)+"...";
                 }
                 $form_area.append($('<div class="form-group"><div class="select w-100px">'
                     +'<div style="line-height:20px;padding: 4px 0;font-weight:normal;">'+inputName+'</div><div class="down-arrow box-r-s"><i class="iconfont icon-triangle-down-copy"></i>'
                     +'</div><ul class="box-r-s d-n" style="width:400px;">'
                     +'<div><table style="width:100%;" border="0">'
                     +'<thead><tr><td></td><td>商品名</td><td>单价（元）</td><td>医保</td><td>库存</td></tr></thead>'
                     +'<tbody class="drugListTbody"></tbody></table></div></ul></div></div>'));
                 
                 //得到his药品div
                 var hisDrugInfoDetailList = null;
                 if(groupDrugDetailWrapper.hisDrugInfoDetail){
                	 hisDrugInfoDetailList = groupDrugDetailWrapper.hisDrugInfoDetail;
                 }
                 var hisDrugDiv = getHisDrugInfo(hisDrugInfoDetailList);
                 $form_area.find(".form-group ul").find("div .drugListTbody").append($(hisDrugDiv));
                 
                var drgOnceDose = groupDrugDetailWrapper.drgOnceDose; //单次量
                var drgDoseUnit = groupDrugDetailWrapper.drgDoseUnit;//单次量单位
                var beizhu = groupDrugDetailWrapper.beizhu;//备注
                var hisDrugData = groupDrugDetailWrapper.hisDrugData;//
                 //切换默认选中
                 $form_area.find(".form-group ul").find("div .drugListTbody").find("tr").click(function(e){
                     $(this).find("td:first").html("<i class='iconfont icon-duigou1'></i>");
                     $(this).siblings().find("td:first").html("");
                     $(this).parents(".select.focus").removeClass("focus").children("ul").fadeOut();

                     //选中后填充单次量，单次量单位(排序)
                     var hisDrugData = $(this).data("hisDrugData");
                     if(hisDrugData != null){
                         groupDrugDetailWrapper.hisDrugData = hisDrugData; //将选中后的his药品存入drugInfoList中，重新渲染页面时，取剂量单位的时候需要
                         //剂量单位下拉框
                         $form_area.siblings("div.treatment-check-some2").find(".form-group ul").empty().append($(getHisDrgUnit(hisDrugData)));
                         if(hisDrugData.drgDoseUnit!=null && hisDrugData.drgDoseUnit != ""){
                             drgOnceDose = hisDrugData.drgDose;
                             drgDoseUnit = hisDrugData.drgDoseUnit;
                         }else if(hisDrugData.drgVolumeUnit!=null && hisDrugData.drgVolumeUnit != ""){
                             drgOnceDose = hisDrugData.drgVolume;
                             drgDoseUnit = hisDrugData.drgVolumeUnit;
                         }else if(hisDrugData.drgMinUnit!=null && hisDrugData.drgMinUnit != ""){
                             drgOnceDose = "1";
                             drgDoseUnit = hisDrugData.drgMinUnit;
                         }else{
                             drgOnceDose = hisDrugData.drgPackingNum;
                             drgDoseUnit = hisDrugData.drgPackingUnit;
                         }
                     }
                     $form_area.siblings("div.treatment-check-some2").find("input:first").val(drgOnceDose);
                     $form_area.siblings("div.treatment-check-some2").find("input:eq(1)").val(drgDoseUnit);
                     e.stopPropagation();
                 })
                /*//默认选中第一个，根据isCheck判断默认是哪个
                var _index = 0;
                $.each(hisDrugInfoDetailList,function(index,hisDrugInfoDetail){
                    if(hisDrugInfoDetail.isCheck==true){
                        _index = index;
                    }
                })
                $form_area.find(".form-group ul").find("div .drugListTbody").find("tr").eq(_index).click();*/

                 //单次量
                 $form_area = $formArea.find(".treatment-check-some2");
                 $form_area.append($('<div class="form-group"><div class="select box-r-s w-60px"><input type="text" value="'+ (drgOnceDose==null?"":drgOnceDose) +'"></div>'
                     +'<div class="select box-r-s w-50px">'
                     +'<input type="text" readonly="" value="'+ (drgDoseUnit==null?"":drgDoseUnit) +'"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i>'
                     +'</div><ul class="box-r-s d-n"></ul></div></div>'));
                 //$form_area.find(".form-group ul").empty().append((hisDrugData == null?$(getHisDrgUnit(drugInfo)):$(getHisDrgUnit(hisDrugData))));
                 $form_area.find(".form-group ul").empty().append($(getHisDrgUnit(hisDrugData)));

                 //备注
                 $form_area = $formArea.find(".treatment-check-some6");
                //皮试
                var skinTestHtml ="";
                /*if(groupDrugDetailWrapper.skinTest==1||groupDrugDetailWrapper.hisDrugInfoDetail[0].drgSkinTest==1){*/
                if(drugInfo.skinTest==1||groupDrugDetailWrapper.skinTest==1){
                        skinTestHtml = "<input type='checkbox' checked='checked' />皮试";
                }
                $form_area.append($('<div class="form-group"><div class="select box-r-s w-150px"><input type="text" treatment value="'+ (beizhu==undefined?"":beizhu) +'">'+ skinTestHtml +'</div></div>'));
                formAreas.push($formArea);
            }
    	})
    	//将第一行添加标题
    	var firstFormArea = formAreas[0];
    	firstFormArea.find(".form-group:eq(0)").prepend("<label>药名</label>");
    	firstFormArea.find(".form-group:eq(1)").prepend("<label>单次量</label>");
    	firstFormArea.find(".form-group:eq(2)").prepend("<label>备注</label>");
    	firstFormArea.find("p:first").css("padding-top","40px");
    	return formAreas;
    }

    //六行显示
    function sixFormArea(){
        var formArea = '<div class="form-area"><div class="row treatment-check-some" style="float:left;"></div>'
            +'<div class="row treatment-check-some2" style="float:left;"></div>'
            +'<div class="row treatment-check-some3" style="float:left;"></div>'
            +'<div class="row treatment-check-some4" style="float:left;"></div>'
            +'<div class="row treatment-check-some5" style="float:left;"></div>'
            +'<div class="row treatment-check-some6" style="float:left;"></div>'
            +'<div style="clear:both"></div></div>';
        var $formArea = $(formArea);
        var drugInfo = drugInfoList[0];
        var groupDrugDetailWrapper = drugInfo.groupDrugDetailWrapperList[0];
        var name = drugInfo.name;
        //药名
        var $form_area = $formArea.find(".treatment-check-some");
        var inputName=name;
        if(name.length>6){
            inputName = name.substring(0,5)+"...";
        }
        $form_area.append($('<div class="form-group"><label>药名</label><div class="select w-100px">'
        +'<div style="line-height:20px;padding: 4px 0;font-weight:normal;">'+inputName+'</div><div class="down-arrow box-r-s"><i class="iconfont icon-triangle-down-copy"></i></div>'
        +'<ul class="box-r-s d-n" style="width:400px;">'
        +'<div><table style="width:100%;" border="0">'
    	+'<thead><tr><td></td><td>商品名</td><td>单价（元）</td><td>医保</td><td>库存</td></tr></thead>'
    	+'<tbody class="drugListTbody"></tbody></table></div></ul></div></div>'));
        
        var modType = "";
        if(drugInfo.type==4){
        	modType = 4;
        }else{
        	modType = 1;
        }
        var drugUseModRes = ajaxPost(Param.hostUrl+"/his/druguse/get_druguse_mod_list",{"type":modType,"hospitalCode":Param.hospitalCode});//用法接口
        var drugFrequency = ajaxPost(Param.hostUrl+"/his/druguse/get_drug_frequency_list",{"enName":"","hospitalCode":Param.hospitalCode});//频次接口
        //得到his药品规格div
        var	hisDrugInfoDetailList = groupDrugDetailWrapper.hisDrugInfoDetail;
        var hisDrugDiv = getHisDrugInfo(hisDrugInfoDetailList);
        $form_area.find(".form-group ul").find("div .drugListTbody").append($(hisDrugDiv));

        //获取数据
        var drgOnceDose = groupDrugDetailWrapper.drgOnceDose; //单次量
        var drgDoseUnit = groupDrugDetailWrapper.drgDoseUnit; //单次量单位
        var modName = drugItem.modName; //用法
        var frequencyName = drugItem.frequencyName;//频次
        var liaocheng = drugItem.liaocheng;//疗程
        var beizhu = groupDrugDetailWrapper.beizhu;//备注
        var hisDrugData = groupDrugDetailWrapper.hisDrugData;
        //用法和频次
        if(modName == undefined){
            modName = getModNameById(groupDrugDetailWrapper.modId,drugUseModRes.data);
        }
        if(frequencyName == undefined){
            frequencyName = getFrequencyNameByEnName(groupDrugDetailWrapper.freEnName,drugFrequency.data);
        }

        //切换默认选中
        $form_area.find(".form-group ul").find("div .drugListTbody").find("tr").click(function(e){
            $(this).find("td:first").html("<i class='iconfont icon-duigou1'></i>");
            $(this).siblings().find("td:first").html("");
            $(this).parents(".select.focus").removeClass("focus").children("ul").fadeOut();
            
            //选中后填充单次量，单次量单位(排序)，用法，频次
            var hisDrugData = $(this).data("hisDrugData");
            if(hisDrugData != null){
                groupDrugDetailWrapper.hisDrugData = hisDrugData; //将选中后的his药品存入drugInfoList中，重新渲染页面时，取剂量单位的时候需要
            	//用法和频次
            	modName = getModNameById(hisDrugData.modId,drugUseModRes.data);
            	frequencyName = getFrequencyNameByEnName(hisDrugData.freEnName,drugFrequency.data);
            	//剂量单位下拉框
                $form_area.siblings("div.treatment-check-some2").find(".form-group ul").empty().append($(getHisDrgUnit(hisDrugData)));
            	if(hisDrugData.drgDoseUnit!=null && hisDrugData.drgDoseUnit != ""){
            		drgOnceDose = hisDrugData.drgDose;
            		drgDoseUnit = hisDrugData.drgDoseUnit;
            	}else if(hisDrugData.drgVolumeUnit!=null && hisDrugData.drgVolumeUnit != ""){
            		drgOnceDose = hisDrugData.drgVolume;
            		drgDoseUnit = hisDrugData.drgVolumeUnit;
            	}else if(hisDrugData.drgMinUnit!=null && hisDrugData.drgMinUnit != ""){
            		drgOnceDose = "1";
            		drgDoseUnit = hisDrugData.drgMinUnit;
            	}else{
                    drgOnceDose = hisDrugData.drgPackingNum;
                    drgDoseUnit = hisDrugData.drgPackingUnit;
                }
            }
            $form_area.siblings("div.treatment-check-some2").find("input:first").val(drgOnceDose);
            $form_area.siblings("div.treatment-check-some2").find("input:eq(1)").val(drgDoseUnit);
            $form_area.siblings("div.treatment-check-some3").find("input:first").val(modName);
            $form_area.siblings("div.treatment-check-some4").find("input:first").val(frequencyName);
            e.stopPropagation();
        })

        /*//默认选中第一个，根据isCheck判断默认是哪个
        var _index = 0;
        $.each(hisDrugInfoDetailList,function(index,hisDrugInfoDetail){
            if(hisDrugInfoDetail.isCheck==true){
                _index = index;
            }
        })
        $form_area.find(".form-group ul").find("div .drugListTbody").find("tr").eq(_index).click();*/

        $form_area = $formArea.find(".treatment-check-some2");
        $form_area.append($('<div class="form-group"><label>单次量</label><div class="select box-r-s w-60px"><input type="text" value="'+(drgOnceDose==null?"":drgOnceDose)+'"></div>'
			                +'<div class="select box-r-s w-50px">'
			                +'<input type="text" readonly="" value="'+(drgDoseUnit==null?"":drgDoseUnit)+'"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i>'
			                +'</div><ul class="box-r-s d-n"></ul></div></div>'));
        $form_area.find(".form-group ul").empty().append($(getHisDrgUnit(hisDrugData)));
        
        //用法
        $form_area = $formArea.find(".treatment-check-some3");
        $form_area.append($('<div class="form-group"><label>用法</label><div class="select box-r-s w-80px">'
                +'<input type="text" readonly="" value="'+modName+'"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i>'
                +'</div><ul class="box-r-s d-n"></ul></div></div>'));
        $form_area.find(".form-group ul").append($(getDrugUserOrFrequency(drugUseModRes.data)));
        
        //频次
        $form_area = $formArea.find(".treatment-check-some4");
        $form_area.append($('<div class="form-group"><label>频次</label><div class="select box-r-s w-80px" >'
                +'<input type="text" readonly="" value="'+frequencyName+'"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i>'
                +'</div><ul class="box-r-s d-n"></ul></div></div>'));
        $form_area.find(".form-group ul").append($(getDrugUserOrFrequency(drugFrequency.data)));
        
        //疗程
        $form_area = $formArea.find(".treatment-check-some5");
        $form_area.append($('<div class="form-group"><label>疗程</label><div class="select box-r-s w-100px"><input type="text" value="'+(liaocheng==undefined?"":liaocheng)+'"></div></div>'));
        
        //备注
        $form_area = $formArea.find(".treatment-check-some6");
        //皮试
        var skinTestHtml ="";
        if(drugInfo.skinTest==1){
            if(groupDrugDetailWrapper.skinTest==0){
                skinTestHtml = "<input type='checkbox'/>皮试";
            }else{
                skinTestHtml = "<input type='checkbox' checked='checked'/>皮试";
            }
        }
        $form_area.append($('<div class="form-group"><label>备注</label><div class="select box-r-s w-130px"><input type="text" treatment value="'+(beizhu==undefined?"":beizhu)+'">'+ skinTestHtml +'</div></div>'));
        
        return $formArea;
    }

    //获取推送条,药品相关信息存放在drugData中
    function ajaxPushTreatmentList(data){
    	var list=[];//list表示已选择的项目，不显示出来
        var $div = $("<div></div>");
        var li='';
        $(data).each(function(){
            if($.inArray(this.id.toString(), list)>=0){return true}
            if(this.name.length>6){
                li ='<div class="symptom" drg_id="'+this.id+'" data-symptom-name="'
                +this.name+'">'+this.name.substring(0,6)+"..."
                +'<i class="iconfont icon-i"></i></div>';
            }else{
                li ='<div class="symptom" drg_id="'+this.id+'">'+this.name
                +'<i class="iconfont icon-i"></i></div>';
            }
            $div.append($(li).data("drugData",this));
        })
        return $div.children();
    }

    //推送条左右翻页
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
        //下一页时翻几个
        setDistance("7");
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

    //用药指南
    function addDrugInformationEvents(){
    	$group.find(".symptom-wrap").children().hover(function(){
            $(this).children("i").css("visibility","visible");
        },function(){
            $(this).children("i").css("visibility","hidden");
        });
        $group.find(".symptom-wrap").children().children().click(function(e){
        	var resl=ajaxPost(Param.hostUrl+"/kl/druginfo/get_drug_instructions_by_drgId",{drgId:$(this).parent().attr("drg_id")});
            $("body").append($('<div id="informationShade"></div>'));
            $("#informationShade").height($(".container:first").height());
            var mark = new Date().getTime();
            var $info = $('#druginformation').tmpl(resl.data);
            $info.attr("id", mark).find(".information-list ul li:first").addClass("focus");
            if(resl.data){
            	if(resl.data.drgName){
                    $info.find(".information-drgName ul li:first").html(resl.data.drgName);
                }
                if(resl.data.drgRegionName){
                    $info.find(".information-drgRegionName ul li:first").html(resl.data.drgRegionName);
                }
                if(resl.data.drgComponent){
                    $info.find(".information-drgComponent ul li:first").html(resl.data.drgComponent);
                }
                if(resl.data.drgIndication){
                    $info.find(".information-drgIndication ul li:first").html(resl.data.drgIndication);
                }
                if(resl.data.drgUsageInstructions){
                    $info.find(".information-drgUsageInstructions ul li:first").html(resl.data.drgUsageInstructions);
                }
                if(resl.data.drgReactions){
                    $info.find(".information-drgReactions ul li:first").html(resl.data.drgReactions);
                }
                if(resl.data.drgConsiderations){
                    $info.find(".information-drgConsiderations ul li:first").html(resl.data.drgConsiderations);
                }
                if(resl.data.drgMutual){
                    $info.find(".information-drgMutual ul li:first").html(resl.data.drgMutual);
                }
                if(resl.data.drgIngredients){
                    $info.find(".information-drgIngredients ul li:first").html(resl.data.drgIngredients);
                }
                if(resl.data.drgAppDisease){
                    $info.find(".information-drgAppDisease ul li:first").html(resl.data.drgAppDisease);
                }
            }
            $info.appendTo('body');
            autoInformationHeight();
            palceModalMiddle(mark);
            common.informationControlToggle();
            $(".information .information-close").click(function (event) {
                event.stopPropagation();
                $(".information#" + mark).remove();
                $("#informationShade").remove();
            });
            e.stopPropagation();
        });
    }

    //删除药品条目事件
    function delDrug(){
    	$group.find(".symptom-form[active] i.treatment-del").off("click").each(function(index){
    	    $(this).click(function(){
                getFormData();
                //删除药品列表对应序号
                drugInfoList.splice(index,1);
                drugItem.drugInfoList = drugInfoList;
                refreshPush();
                //重新渲染页面
                renderFromArea();
            })
        })
    }
    
    //推送条药品点击事件：推送条数据发生变动时需要重新绑定该事件
    function drugInfoClick(){
    	$group.find(".symptom-wrap").children("div.symptom").off("click").click(function(e){
            click(this,e);
        });
    }

    function click(el,e){
        var drugData = $(el).data("drugData");
        if(drugInfoList.length>0){
            if(!(drugInfoList[0].type==8||drugInfoList[0].type==4) && drugInfoList[0].isGroup!=1){
                alert("该药不可组合");
                e.stopPropagation();
                return;
            }
            if(drugInfoList[0].type==1&&drugInfoList[0].isGroup==1&&drugData.drugFormulation!=1){
                alert("非针剂类药不可加入");
                e.stopPropagation();
                return;
            }
        }
        //这里做类型判断，调用不同接口获取药品数据
        //类型 1-西药 2-中成药,3-西药协定组合处方,4-中药 ,5疫苗,6材料 7-治疗项目,8中药协定组合'
        if(drugData.type==3||drugData.type==8){
            //组合药
            var d_drugInfoWrapper = deepcopy(drugInfoWrapper);
            d_drugInfoWrapper.id = drugData.id;
            //组合药，获取药品明细
            var resu = ajaxSetContentType(Param.hostUrl+"/kl/druginfo/get_group_list_drug_info",JSON.stringify(d_drugInfoWrapper));
            if(resu.ret!=0||resu.data.length<1){
                alert("获取组合明细失败");
                e.stopPropagation();
                return;
            }
            drugData.groupDrugDetailWrapperList=resu.data;
            drugInfoList.push(drugData);
            if(drugItem.type==undefined){
                if(drugData.type ==3 ){
                    drugItem.type = 1;
                }else{
                    drugItem.type = 4;
                }
            }
            drugItem.drugInfoList = drugInfoList;
        }else{
            //非组合
            //点单个药品，检索HIS有库存，并且默认产地和用法
            var d_drugInfoWrapper = deepcopy(drugInfoWrapper);
            d_drugInfoWrapper.id = drugData.id;
            var resu = ajaxSetContentType(Param.hostUrl+"/kl/druginfo/get_his_drug_info",JSON.stringify(d_drugInfoWrapper));
            if(resu.ret == 0){
                if(resu.data.hisDrugInfoDetail){
                    drugData.groupDrugDetailWrapperList=[];
                    drugData.groupDrugDetailWrapperList.push(resu.data);
                    outDrugIds.push(drugData.id);
                    drugInfoList.push(drugData);
                    if(drugItem.type==undefined){
                        drugItem.type = drugData.type;
                    }
                    drugItem.drugInfoList = drugInfoList;
                }else{
                    alert("此药没有库存");
                    e.stopPropagation();
                    return;
                }
            }else{
                alert(resu.errorMsg);
                e.stopPropagation();
                return;
            }
        }
        //选择推送条一项后清空输入框
        $(".treatment-main-input").val("");
        getFormData();
        refreshPush(drugData);
        renderFromArea();
        e.stopPropagation();
    }

    //获取填写单数据formData，存入drugInfoList
    function getFormData(){
        //获取明细list
        var groupDrugDetailList = [];
        $.each(drugInfoList,function(index,drugInfo) {
            groupDrugDetailList = groupDrugDetailList.concat(drugInfo.groupDrugDetailWrapperList);
        })
        $group.find(".all-form-area").find(".form-area").each(function(index){
            var $this =$(this);
            groupDrugDetailList[index].drgOnceDose = $this.find(".treatment-check-some2").find("input:first").val();
            groupDrugDetailList[index].drgDoseUnit = $this.find(".treatment-check-some2").find("input:eq(1)").val();
            groupDrugDetailList[index].beizhu = $this.find(".treatment-check-some6").find("input:first").val();
            //皮试
            if($this.find(".treatment-check-some6 :checkbox").prop("checked")){
                groupDrugDetailList[index].skinTest = 1;
            }else{
                groupDrugDetailList[index].skinTest = 0;
            }
        });
        if($group.find(".all-form-area").find(".bottom-form-area").length == 0){//六列
            drugItem.modName = $group.find(".treatment-check-some3").find("input:first").val();
            drugItem.frequencyName = $group.find(".treatment-check-some4").find("input:first").val();
            drugItem.liaocheng = $group.find(".treatment-check-some5").find("input:first").val();
        }else{//三列
            drugItem.modName = $group.find(".all-form-area").find(".bottom-form-area").find(".treatment-check-some3").find("input:first").val();
            drugItem.frequencyName = $group.find(".all-form-area").find(".bottom-form-area").find(".treatment-check-some4").find("input:first").val();
            drugItem.liaocheng = $group.find(".all-form-area").find(".bottom-form-area").find(".treatment-check-some5").find("input:first").val();
        }
        //更新drugInfoList
        var count = 0;
        $.each(drugInfoList,function(index,drugInfo) {
            var size = drugInfo.groupDrugDetailWrapperList.length;
            drugInfo.groupDrugDetailWrapperList = groupDrugDetailList.slice(count,count+size);
            count = count+size;
        })
        //名称
        drugItem.name = $group.find(".symptom-form .toptitle input ").val();
        //加急
        if($group.find(".symptom-form div[jiaji] ").hasClass("checked")){
            drugItem.jiaji = 1;
        }else{
            drugItem.jiaji = 0;
        }
        //自备
        if($group.find(".symptom-form div[zibei] ").hasClass("checked")){
            drugItem.zibei = 1;
        }else{
            drugItem.zibei = 0;
        }
        //代煎
        if($group.find(".symptom-form div[daijian] ").hasClass("checked")){
            drugItem.daijian = 1;
        }else{
            drugItem.daijian = 0;
        }
        drugItem.drugInfoList = drugInfoList;
    }

    //推送条
    function refreshPush(drugData){
        //点击的是非组合或者删除药品后
        if(!drugData||drugData.type!=3&&drugData.type!=8){
            var drugInfo = getDrugInfo();
            //没有可组合药
            if(drugInfo.id ==""){
                $group.find(".symptom-wrap").empty().slideUp();
            }else{//有可组合药
                //重新获取推送条
                var resl = ajaxSetContentType(Param.hostUrl+"/kl/druginfo/get_relation_drug_info",JSON.stringify(drugInfo));
                var data = resl.data;
                for(var i=0;i<data.length;i++){
                }
                var li = ajaxPushTreatmentList(data);
                $group.find(".symptom-wrap").empty().append($(li));
                $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                    $(this).blueTip();
                });

                //添加两测阴影
                var shadeHtml ='<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';
                $group.find(".symptom-wrap").append(shadeHtml);
                common.changeContainerShadowWidth($group.find("div.container-shade"));

                moveSymptomWrap($group.find(".symptom-wrap"));
                drugInfoClick();
            }
        }else{//点击的是组合
            $group.find(".symptom-wrap").empty().slideUp();
        }
    }


    //获取药品列表中最后一个可以多开的药的ID以及过滤药品ids，作为推送的依据
    function getDrugInfo(){
        //存放主药ID和过滤药ids的对象
        var drugInfo ={
            id:"",
            drugIds:[]
        };
        var drugId ="";
        drugInfoList.reverse();
        for(var i=0;i<drugInfoList.length;i++){
            //多开标识，主药
            if(drugInfoList[i].isGroup==1){
                drugInfo.id = drugId = drugInfoList[i].id;
                var tempList = drugInfoList.slice(0,i);
                for(var j =0;j<tempList.length;j++){
                    drugInfo.drugIds.push(tempList[j].id);
                }
                break;
            }
        }
        drugInfoList.reverse();
        return drugInfo;
    }
})

/*** 反序遍历 ***/
function reverseEach($node, fn) {
    for (var i = $node.length - 1; i >= 0; i--) {
        fn(i, $node[i]);
    }
}

//用法频次
function getDrugUserOrFrequency(data){
	var li='';
    $(data).each(function(){
    	li+='<li><a temp="'+this.name+'" code="'+this.id+'">'+this.name+'</a></li>';
    });
    return li;
}

//获取hisDrugDiv
function getHisDrugInfo(hisDrugInfoDetailList){
    var trHtml ="";
    var $div = $("<div></div>");
    if(hisDrugInfoDetailList&&hisDrugInfoDetailList.length>0){
        for(var i=0;i<hisDrugInfoDetailList.length;i++){
            hisDrugInfoDetailList[i].drgScale = hisDrugInfoDetailList[i].drgScale == null ? "" : hisDrugInfoDetailList[i].drgScale;
        	trHtml ='<tr><td></td><td>'+hisDrugInfoDetailList[i].drgName+'</td><td>'+hisDrugInfoDetailList[i].drugPrice+'</td><td>'+hisDrugInfoDetailList[i].drgScale+'</td><td>'+hisDrugInfoDetailList[i].totalQuantity+hisDrugInfoDetailList[i].drgPackingUnit+'</td>';
            $div.append($(trHtml).data("hisDrugData",hisDrugInfoDetailList[i]));
        }
    }
    return $div.children();  //hisDrugDiv
}

//获取药品单次剂量单位下拉框
function getHisDrgUnit(drugData){
	var li ="";
	if(drugData != null){
		if(drugData.drgDoseUnit != null && drugData.drgDoseUnit != ""){
			li += '<li><a>'+drugData.drgDoseUnit+'</a></li>';
		}
		if(drugData.drgVolumeUnit != null && drugData.drgVolumeUnit != ""){
			li += '<li><a>'+drugData.drgVolumeUnit+'</a></li>';
		}
		if(drugData.drgMinUnit != null && drugData.drgMinUnit != ""){
			li += '<li><a>'+drugData.drgMinUnit+'</a></li>';
		}
	}
    return li;
}

//获取用法值
function getModNameById(modId,drugUseModRes){
	var modName="";
	$.each(drugUseModRes,function(){
		if(modId == this.id){
			modName = this.name;
		}
	})
	return modName;
}

//获取频次值
function getFrequencyNameByEnName(enName,DrugFrequencys){
	var frequencyName="";
	$.each(DrugFrequencys,function(){
		if(enName == this.enName){
			frequencyName = this.name;
		}
	})
	return frequencyName;
}

//拷贝对象方法
function deepcopy(obj){
    return $.extend(true, {}, obj);
}

function getDiagnosis(){
    var diagnosis = getDiagnosisForSave();
    var diagnosisArray =[];
    if(diagnosis.choose){
        $.each(diagnosis.choose,function(index,c){
            diagnosisArray.push(c.itemId);
        })
    }
    return diagnosisArray;
}

function getTreatmentData(){
    var data = [];
    var d_dataItem ={
        brcflx :"",
        brcfts:"",
        prespcriptXy :[],
        prespcriptCy :[],
        prespcriptZcy :[]
    };
    //类型 1-西药 2-中成药,3-西药协定组合处方,4-中药 ,5疫苗,6材料 7-治疗项目,8中药协定组合'
    $.each(all_drugItem,function(index,drugItem){
        var drugInfoList = drugItem.drugInfoList;
        var dataItem = deepcopy(d_dataItem);
        var groupDrugDetailList = [];
        $.each(drugInfoList,function(index,drugInfo) {
            groupDrugDetailList = groupDrugDetailList.concat(drugInfo.groupDrugDetailWrapperList);
        })
        if(drugItem.type==1){
            dataItem.brcflx =1;
            $.each(groupDrugDetailList,function(index,groupDrugDetail) {
                var prespcript={};
                //明细序号
                prespcript.cfmxxh = index+1;
                //药品产地	
                if(groupDrugDetail.hisDrugData){
                     prespcript.ypcdid = groupDrugDetail.hisDrugData.id;
                }
                //药品组号
                prespcript.ypxszh =1;
                //数量
                prespcript.cfypsl = drugItem.liaocheng*drugItem.pingci;
                //给药方式
                prespcript.gyfsid = groupDrugDetail.modId;
                //频率
                prespcript.syplid = groupDrugDetail.freEnName;
                //频率次数
                prespcript.syplcs = groupDrugDetail.freEnName;
                //单次剂量
                prespcript.ypdcjl = groupDrugDetail.drgOnceDose;
                //剂量单位
                prespcript.ypjldw = groupDrugDetail.drgDoseUnit;
                //使用天数
                prespcript.ypsyts = drugItem.liaocheng;
                //嘱托
                prespcript.ysztsm = groupDrugDetail.beizhu;
                dataItem.prespcriptXy.push(prespcript);
                data.push(dataItem);
            })
        }else if(drugItem.type==2){
            dataItem.brcflx =2;
            $.each(groupDrugDetailList,function(index,groupDrugDetail) {
                var prespcript={};
                //明细序号
                prespcript.cfmxxh = index+1;
                //药品产地	
                prespcript.ypcdid = groupDrugDetail.hisDrugData.id;
                //药品组号
                prespcript.ypxszh =1;
                //数量
                prespcript.cfypsl = drugItem.liaocheng*drugItem.pingci;
                //给药方式
                prespcript.gyfsid = groupDrugDetail.modId;
                //频率
                prespcript.syplid = groupDrugDetail.freEnName;
                //频率次数
                prespcript.syplcs = groupDrugDetail.freEnName;
                //单次剂量
                prespcript.ypdcjl = groupDrugDetail.drgOnceDose;
                //剂量单位
                prespcript.ypjldw = groupDrugDetail.drgDoseUnit;
                //使用天数
                prespcript.ypsyts = drugItem.liaocheng;
                //嘱托
                prespcript.ysztsm = groupDrugDetail.beizhu;
                dataItem.prespcriptZcy.push(prespcript);
                data.push(dataItem);
            })
        }else if(drugItem.type==4){
            dataItem.brcflx =4;
            dataItem.brcfts =drugItem.liaocheng;
            $.each(groupDrugDetailList,function(index,groupDrugDetail) {
                var prespcript={};
                //明细序号
                prespcript.cfmxxh = index+1;
              //药品产地	
                prespcript.ypcdid = groupDrugDetail.hisDrugData.id;
                //药品组号
                prespcript.ypxszh =1;
                //数量
                prespcript.cfypsl = drugItem.liaocheng * drugItem.pingci;
                //给药方式
                prespcript.gyfsid = groupDrugDetail.modId;
                //频率
                prespcript.syplid = groupDrugDetail.freEnName;
                //频率次数
                prespcript.syplcs = groupDrugDetail.freEnName;
                //单次剂量
                prespcript.ypdcjl = groupDrugDetail.drgOnceDose;
                //剂量单位
                prespcript.ypjldw = groupDrugDetail.drgDoseUnit;
                //使用天数
                prespcript.ypsyts = 1;
                //嘱托
                prespcript.ysztsm = groupDrugDetail.beizhu;
                dataItem.prespcriptCy.push(prespcript);
                data.push(dataItem);
            })
        }
    })

    var toPrescript =[];
    var toPrescriptXy =[];
    var toPrescriptCy =[];
    var toPrescriptZcy =[];
    $.each(data,function(index,dataItem) {
        var prescript ={};
        prescript.brcflx = dataItem.brcflx;
        prescript.brcfts = dataItem.brcfts;
        toPrescript.push(prescript);
    })
    $.each(data,function(index,dataItem) {
        toPrescriptXy = toPrescriptXy.concat(dataItem.prespcriptXy);
    })
    $.each(data,function(index,dataItem) {
        toPrescriptCy = toPrescriptCy.concat(dataItem.prespcriptCy);
    })
    $.each(data,function(index,dataItem) {
        toPrescriptZcy = toPrescriptZcy.concat(dataItem.prespcriptZcy);
    })
    var object ={};
    object.toPrescript = toPrescript;
    object.toPrescriptXy = toPrescriptXy;
    object.toPrescriptCy = toPrescriptCy;
    object.toPrescriptZcy = toPrescriptZcy;
    return object;
}


function treatmentForBasis(){
    var symptom = {
            "SYMPTOM": common.getGroupForBasis($(".group:eq(1)"))
        },
        past = {
            "PAST": common.getGroupForBasis($(".group:eq(2)"))
        },
        other = {
            "OTHER": common.getGroupForBasis($(".group:eq(3)"))
        },
        vital = {
            "VITAL": common.getGroupForBasis($(".group:eq(4)"))
        },
        sex;
    if(Param.sexType==="1"){
        sex="WZNR001180";
    }else if(Param.sexType==="2"){
        sex="WZNR001181";
    }
    vital.VITAL["FFFFF00000"]={};

    vital.VITAL["FFFFF00000"]["WZYD001105"]=sex;
    vital.VITAL["FFFFF00000"]["WZYD001106"]=Param.age;
    var lis = '{"LIS":';//LIS
    lis += getLisForBasis();
    lis += "}";

    var pacs = '{"PACS":';
    pacs += getPacsForBasis();
    pacs += "}";

    var diagnosis ='{"DIS":';
    diagnosis += getDiagnosisForBasis();
    diagnosis += "}";


    var param = {
        symptomJsonString: JSON.stringify(symptom),
        pastJsonString: JSON.stringify(past),
        otherJsonString: JSON.stringify(other),
        vitalsJsonString: JSON.stringify(vital),
        labsJsonString: lis,
        pacsJsonString: pacs,
        disJsonString:diagnosis
    };
    return param;
}

function getDiagnosisForBasis(){
    var $group=$(".container .group").eq(7);
    var ob={};
    $group.find(".choose .content").children().each(function(){
        var $now_form=$("#"+$(this).attr("diagnosiscode"));
        var obj={"HYBM001012":"1"};
        ob[$now_form.attr("data-param-code")]=obj;
    });

    return jsonTOstring(ob);
}