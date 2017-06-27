/**
 * Created by Kiva on 17/3/29.
 */
(function ($) {

    var $groups=$("div.group").filter(":last");

    $(window).ready(function () {
        changeContainerShadowWidth();
    });
    $(window).resize(function () {
        changeContainerShadowWidth();
    });

    /*** 动态改变两侧的遮罩的宽度 ***/
    function changeContainerShadowWidth() {
        var $container=$("div.container"),
            $container_shade=$container.siblings("div.container-shade");
        $container_shade.css("width",$container.css("marginLeft"));
    }

    var $symptom_wrap=$groups.find('div.symptom-container:first').children("div.symptom-wrap"),
        $button=$symptom_wrap.siblings("div.button"),
        $arrow_detail=$button.children("div.arrow.detail");
    $arrow_detail.off("click.main");
    $arrow_detail.on("click.main",function () {
        treatmentDetailClickEvent();
        addTreatmentFocus($arrow_detail.parent().parent().siblings("div.symptom-all"));
    });

    /*** 下拉出现全部症状事件 ***/
    function treatmentDetailClickEvent() {
        var $symptom_wrap=$groups.find('div.symptom-container:first').children("div.symptom-wrap");
        var $symptom_all=$symptom_wrap.parent().siblings("div.symptom-all"),
            $wrap=$symptom_all.find("div.wrap");
        if($wrap.children().length===0){
            $.ajax({
                url:Param.hostUrl+"/kl/transverse/get_transverse_portrait_drug",
                async:true,
                data:{
                    type:8
                },
                dataType:"json",
                type:"post",
                success:function (data) {
                    var TreatmentAllTmpl=getTmpl("TreatmentAllTmpl"),
                        treatmentAllTmpl=new TreatmentAllTmpl();
                    treatmentAllTmpl.addButton(data.data);
                    $.each(data.data,function (i,v) {
                        treatmentAllTmpl.addWrapContent(v);
                    });
                    $symptom_all.children("div.title").children("div.button-group").append(treatmentAllTmpl.button);
                    $symptom_all.children("div.wrap").append(treatmentAllTmpl.wrap);
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
        }else{
            $symptom_all.siblings("div.symptom-container").fadeOut();
            $symptom_all.slideDown();
        }
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
        $.ajax({
            url:Param.hostUrl+"/kl/druginfo/get_drug_by_Treatment",
            async:true,
            data:
                JSON.stringify({
                    doctorId:1,
                    size:20,
                    sexType:1,
                    deptNo:001,
                    hospitalCode:1001,
                    age:20,
                    type:8
                }),
            contentType:"application/json",
            type:"post",
            success:function (data) {
                var $use_wrap_content=$wrap_contents.eq(0),
                    html='<div class="content" style="width: 100%;">';
                $use_wrap_content.children().remove();
                $.each(data.data,function (i,v) {
                    html+='<p class="symptom" data-id="'+this.id+'" data-symptom-name="'+this.name+'">'+(this.name.length>6?(this.name.slice(0,5)+"..."):this.name)+'</p>';
                });
                html+='</div>';
                $use_wrap_content.append(html);
            },
            error:function (req,info,err) {
                alert("加载常用列表失败");
            }
        });
    }

    /*** 给显示全部症状信息列表添加事件 ***/
    function addTreatmentAllEvent($symptom_all) {
        var $title=$symptom_all.children("div.title"),
            $arrow=$title.children("div.arrow"),
            $buttons=$title.find("button"),
            $wrap_contents=$symptom_all.find("div.wrap-content"),
            $menus=$wrap_contents.find("div.menu");

        console.log($buttons.size())
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
            var $p=$(e.target),
                $symptom_container=$symptom_all.siblings("div.symptom-container");
            $arrow.click();
            addSymptomForm($symptom_container,$p.attr("data-id"),$p.attr("data-symptom-name"));
            showSymptomForm($symptom_container.find("div.symptom-form[data-relation-id="+$p.attr("data-id")+"]"));
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
        $groups.each(function (i) {
                var $group=$(this),
                    $input=$group.children("div.box-r-s").children("input"),
                    oldVal=null,
                    $symptom_wrap=$group.find("div.symptom-wrap"),
                    $symptom_container=$group.find("div.symptom-container:first"),
                    $choose=$group.find("div.choose");

                $group.on("click.main",function (e) {
                    var $group=$(this);

                    e.stopPropagation();
                });
                if(i===0){
                    $choose.dropInput(function () {});
                }else{
                    $input.on("focus click input propertychange",function (e) {
                        var $input=$(this),
                            val=$input.val().trim(),
                            $symptom_container=$input.siblings("div.symptom-container");
                        $group.addClass("focus");
                        $symptom_container.slideDown();
                        if(oldVal!==val){
                            oldVal=val;
                        }
                        groupHide($group);
                        e.stopPropagation();
                    });
                    $input.dropInput(function () {});
                }
                $choose.on("click.never-search",function (e) {
                    var $choose=$(this),
                        val="",
                        $symptom_container=$choose.siblings("div.symptom-container:first");
                    $group.addClass("focus");
                    $symptom_container.slideDown();
                    if(oldVal!==val){
                        oldVal=val;
                    }
                    groupHide($group);
                    e.stopPropagation();
                });

                $symptom_wrap.on("click.main","div",function (e) {
                    var $symptom=$(e.target).hasClass("symptom")?$(e.target):$(e.target).parents("div.symptom:first"),
                        symptom_name=$symptom.attr("data-symptom-name");
                    if($symptom_container.find("div.symptom-form[data-relation-id="+$symptom.attr("data-id")+"]").length===0){
                        addSymptomForm($symptom_container,$symptom.attr("data-id"),symptom_name);
                    }
                    showSymptomForm($symptom_container.find("div.symptom-form[data-relation-id="+$symptom.attr("data-id")+"]"));
                    clearInput($input);
                    e.stopPropagation();
                });

                $choose.on("click.main",'span,i',function (e) {
                    var $node=$(e.target),
                        $choose=$node.parents("div.choose"),
                        i_index,
                        $group=$choose.parent().parent(),
                        group_index=$group.index(),
                        $symptom_form,
                        $p=$node.parent();
                    $group.addClass("focus");
                    if(e.target.nodeName==="SPAN"){
                        $symptom_form=$choose.siblings("div.symptom-container").find("div.symptom-form[data-relation-id="+$p.attr("data-relation-id")+"]");
                        showSymptomForm($symptom_form);
                    }else if(e.target.nodeName==="I"){
                        i_index=$node.index();
                        $symptom_form=$choose.siblings("div.symptom-container").find("div.symptom-form[data-relation-id="+$p.attr("data-relation-id")+"]");
                        if(i_index===2){
                            presentIllnessUp($group,$symptom_form,$p);
                        }else{
                            if(group_index===0 && confirm("是否删除现病史内容？")){
                                removeSymptomChoose($group.next(),$p.attr("data-relation-id"));
                            }
                            removeSymptomChoose($group,$p.attr("data-relation-id"));
                        }
                    }
                    groupHide($group);
                    e.stopPropagation();
                });
                // 将现病史加入到主诉
                function presentIllnessUp($group,$symptom_form,$p) {
                    $group.prev().find("div.symptom-container").append($symptom_form.clone(true)).find("div.symptom-form[data-relation-id="+$p.attr("data-relation-id")+"]").find("button.confirm").click();
                }
                //删除填写的症状和对应的动态表单
                function removeSymptomChoose($group,data_id) {
                    $group.find("div.symptom-form[data-relation-id="+data_id+"]").remove();
                    $group.find("div.choose p[data-relation-id="+data_id+"]").remove();
                    $group.find("div.symptom-else p[data-relation-id="+data_id+"]").removeClass("focus");
                }

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
    function groupHide($group) {
        var $not_groups=$groups.not($group);
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
                var $symptom_form=$symptom_container.find("div.symptom-form[data-relation-id="+data_id+"]");
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

    function reLoadSubItemInfo(data,$symptom_wrap) {
        var html="",
            SubItemTmpl=getTmpl("SubItemTmpl"),
            subItemTmpl=new SubItemTmpl();
        $.each(data,function (i,v) {
            subItemTmpl.setDataId(v.id);
            subItemTmpl.setName(v.name);
            subItemTmpl.setInfo("");
            html+=subItemTmpl.getSymptom();
        });
        $symptom_wrap.html(html);
    }

    /*** 反序遍历 ***/
    function reverseEach($node,fn) {
        for (var i=$node.length-1;i>=0;i--){
            fn(i,$node[i]);
        }
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
                this.detail+='<div class="date-toggle box-r-s" data-name="date_toggle"><div class="background-color"></div><span class="focus">日期</span><span class="">时长</span></div><div><div class="select box-r-s w-70px"><input type="text" readonly><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n"><li><a>2000</a></li><li><a>2001</a></li><li><a>2002</a></li></ul></div><span>年</span><div class="select box-r-s w-70px"><input type="text" readonly><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n"><li><a>1</a></li><li><a>2</a></li><li><a>3</a></li><li><a>4</a></li><li><a>5</a></li><li><a>6</a></li><li><a>7</a></li><li><a>8</a></li><li><a>9</a></li><li><a>10</a></li><li><a>11</a></li><li><a>12</a></li></ul></div><span>月</span><div class="select box-r-s w-70px"><input type="text" readonly><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n"><li><a>1</a></li><li><a>2</a></li><li><a>3</a></li><li><a>4</a></li><li><a>5</a></li><li><a>6</a></li><li><a>7</a></li><li><a>8</a></li><li><a>9</a></li><li><a>10</a></li><li><a>11</a></li><li><a>12</a></li></ul></div><span>日</span></div><div class="d-n"><input class="box-r-s" type="number" style="width: 70px;height: 30px;float: left;margin-right: 5px"><div class="select box-r-s w-70px" style="width: 50px"><input type="text" readonly><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n"><li><a>天</a></li><li><a>月</a></li><li><a>年</a></li></ul></div></div>';
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
                treatmentAllTmpl.wrap+='<div class="wrap-content d-n" style="width: 100%" data-relationId="'+data.id+'"><div class="content">';
                if(data.drugList){
                    $.each(data.drugList,function (i,v) {
                        content+='<p class="symptom" data-id="'+v.id+'" data-symptom-name="'+v.name+'" data-portraitid="'+v.portraitId+'">'+v.name.length>6?(v.name.slice(0,7)+"..."):v.name+'</p>';
                    });
                }
                treatmentAllTmpl.wrap+='</div></div>';
            }else{
                var content="";
                treatmentAllTmpl.wrap+='<div class="wrap-content d-n" data-relationId="'+data.id+'"><div class="menu"><ul class="first-level">';
                $.each(data.portraitList,function (i,v) {
                    treatmentAllTmpl.wrap+='<li><a data-id="'+v.id+'"><i class="iconfont icon-right-triangle"></i>'+v.name+'</a>';
                    if(v.portraitList.length>0){
                        treatmentAllTmpl.wrap+='<ul class="second-level d-n" data-drop="false">';
                        $.each(v.portraitList,function (i2,v2) {
                            treatmentAllTmpl.wrap+='<li><a data-id="'+v2.id+'">'+v2.name+'</a></li>';
                            if(v2.drugList.length>0){
                                content+=addContent(v2.id,v2.drugList);
                            }
                        });
                        treatmentAllTmpl.wrap+='</ul>';
                    }else{
                        if(v.drugList.length>0){
                            content+=addContent(v.id,v.drugList);
                        }
                    }
                    treatmentAllTmpl.wrap+='</li>';
                });
                treatmentAllTmpl.wrap+='</ul></div>'+content+'</div>';



                function addContent(id,data) {
                    var html='<div class="content d-n">';
                    $.each(data,function (i,v) {
                        html+='<p class="symptom" data-id="'+v.id+'" data-symptom-name="'+v.name+'" data-portraitid="'+id+'">'+(v.name.length>6?(v.name.slice(0,5)+"..."):v.name)+'</p>';
                    });
                    html+='</div>';
                    return html;
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


})(jQuery);
