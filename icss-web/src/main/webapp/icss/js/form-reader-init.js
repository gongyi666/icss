/**
 * Created by Kiva on 17/3/1.
 */


function dynamicForm(appendArea,title,subId,liNum) {
    var $third_levl=appendArea.find("#third-levl"+subId);
    if($third_levl.length>0){
        $third_levl.show();
        $third_levl.parent().siblings("div.fancy-ct").hide();
        return;
    }
    
    appendArea.append('<div id="third-levl'+subId+'" class="detail-list" style="display:block"><h3 class="pro-name"><b>'+title+'</b></h3><div class="form-area"></div></div>');
    $third_levl=appendArea.find("#third-levl"+subId);
    var $form_area = $third_levl.find("div.form-area"),
        $fancy_ct=appendArea.siblings("div.fancy-ct");
    //获取动态表单数据
    $.ajax({
        url: host_url+"kl/questioninfo/get_questioninfo_by_subitemid?subitemId="+subId,
        dataType: "json",
        type: "get",
        success: function (data) {

            var html = '',
                relation = {"1": [], "2": [], "3": [], "4": [], "5": [], "6": []};//因为html代码是拼完后一起加入的，所以得记录在哪需要添加事件，比如现有数据的select级联事件，key为type，可以在以后为不同控件定义不同event，现在只有select的事件
            $.each(data.data, function (i) {
                if(!this.name){
                    html += '<dl><dt></dt><dd>';
                }else{
                    html += '<dl><dt>' + this.name + '</dt><dd>';
                }
                $.each(this.questionContentList, function () {
                    html += addControl(this, relation, i);
                });
                html += '</dd></dl>';
            });

            $form_area.html(html);
            $form_area.parent().append('<p class="submit-bt"><button class="make-sure usable" type="button">确定</button><button class="cancle unusable" type="button">取消</button></p>');
            //遍历relation添加事件
            $.each(relation, function (key, value) {
                if (value.length > 0) {
                    var $dds = $form_area.find('dd');
                    if ("3" === key) {
                        $.each(value, function (i, v) {
                            selectRelation($dds.eq(v));
                        });
                    }else if("4"===key){
                        $.each(value,function (i,v) {
                            addCheckboxOrRadioEvent($dds.eq(v));
                        });
                    }else if("5"===key){
                        $.each(value,function (i,v) {
                            addCheckboxOrRadioEvent($dds.eq(v));
                        });
                    }
                }
            });
            $fancy_ct.hide();
            addAsterisk("question_time_value");
        },
        error: function () {
            alert("请求失败");
        }
    });

    function addAsterisk(name) {
        var $input=$form_area.find("input[name="+name+"]"),
            $dd=$input.parent(),
            $dt=$dd.siblings("dt");
        $input.attr("type","number");
        $dt.html("<span style='color: red;position: absolute;left: 10px'> *</span>"+$dt.html());
        $input.on("input.validate propertychange.validate",function () {
            var $validate=$input.siblings("div.validate");
            if($validate.length===0){
                $dd.append("<div class='validate' style='color: red;display: none'>时间不能为空！！！</div>");
            }
            if($input.val().trim().length>0){
                $validate.css("display","none");
            }else{
                $validate.css("display","block");
            }
        });
    }

    function asteriskValidate($input) {
        if($input.length>0 && $input.val().trim().length === 0){
            return false;
        }
        return true;
    }



    //根据数据类型的不同添加不同的控件
    function addControl(data, relation, i) {
        var html = '';
        switch (data.type) {
            case "1"://按钮
                return html;
            case "2"://文本框
                return addText(data);
            case "3"://下拉
                return addSelect(data, relation, i);
            case "4"://复选框
                return addRadioOrCheckbox(data,relation,i,"4");
            case "5"://单选
                return addRadioOrCheckbox(data,relation,i,"5");
            case "6"://多行文本框
                return addTextarea(data);
            case "7": //日期控件
                return addData(data);
        }
    }

    function addData(data) {
        var html = "<input name='" + data.code + "'>";
        return html;
    }


    /*** 单选框和复选框添加 ***/
    function addRadioOrCheckbox(data, relation,i,type) {
        var html="",
            clazz;
        if(type==="4"){
            clazz="check-box";
        }else if(type==="5"){
            clazz="radio";
        }
        $.each(data.contentDetailList,function () {
            html+='<span class="'+clazz+'" data-id="'+this.id+'" name="'+this.code+'" data-relationId="'+this.relationId+'">'+this.name+'</span>';
            if(this.questionInfos.length>0){
                $.each(this.questionInfos[0].questionContentList,function () {
                   html+=addControl(this,relation,i);
                });
            }
        });
        relation[type].push(i);
        return html;
    }

    /*** 单选框和复选框事件 ***/

    function addCheckboxOrRadioEvent($dd) {
        hideRelation();
        $dd.off("click.checkbox");
        $dd.on("click.checkbox","span",function (e) {
            var $span=$(e.target);
            if($span.hasClass("check-box")){
                $span.hasClass("check-state")?$span.removeClass("check-state"):$span.addClass("check-state");
            }else if($span.hasClass("radio")){
                $span.addClass("check-state").siblings("span.radio").removeClass("check-state");
            }

            hideRelation();
            if($span.attr("data-relationid")!=="null" && $span.attr("data-relationid")!=="" && $span.hasClass("check-state")){
                $span.siblings("[data-questionid="+$span.attr("data-relationid")+"]").show();
            }
        });
        function hideRelation() {
            $dd.children().each(function () {
                var $node=$(this);
                if($node.attr("data-questionid")!=="null" && $node.attr("data-questionid")!=="" && $node.attr("data-questionid")!==undefined){
                    $node.hide();
                }
            });
        }
    }

    //添加input type=text的控件
    function addText(data) {
        var labelPrefix="",
            labelSuffix="",
            html = "<input name='" + data.code + "' data-id='"+data.id+"' data-questionId='"+data.questionId+"' ";
        if(data.labelPrefix){
            labelPrefix=data.labelPrefix;
            html+="data-labelPrefix="+labelPrefix+" ";
        }
        if(data.labelSuffix){
            labelSuffix=data.labelSuffix;
            html+="data-labelSuffix="+labelSuffix+" ";
        }
        html+=">";
        return labelPrefix+html+labelSuffix;
    }

    //添加select控件
    function addSelect(data, relation, i) {
        var html = '<select name="' + data.code + '" data-id="' + data.id + '" data-questionId="'+data.questionId+'">',
            arr = [];
        $.each(data.contentDetailList, function (i) {
            html += '<option value="' + this.id + '" data-relationId="'+this.relationId + '">' + this.name + '</option>';
            if (this.questionInfos.length>0) {
                arr.push(i);
            }
        });
        html += '</select>';
        if (arr.length > 0) {
            if(relation){
                relation[3].push(i);
            }
            $.each(arr, function (i, v1) {
                html += addSelect(data.contentDetailList[v1].questionInfos[0].questionContentList[0]);
            });
        }
        return html;
    }

    //添加textarea控件
    function addTextarea(data) {
        var html = '<textarea name="' + data.code + '"></textarea>';
        return html;
    }

    //级联select的函数
    function selectRelation($dd) {
        var $select = $dd.children("select:first"),
            select_val = $select.find("option[value=" + $select.val() + "]").attr("data-relationId"),
            $select_siblings=$select.siblings("select");
        changeShowRelationSelect(select_val,$select_siblings);
        $select.on("change.relation", function () {
            var $select=$(this),
                $select_siblings = $select.siblings("select"),
                select_val = $select.find("option[value=" + $select.val() + "]").attr("data-relationId");
            changeShowRelationSelect(select_val,$select_siblings);
        });

        function changeShowRelationSelect(select_val,$select_siblings) {
            $select_siblings.css("display", "none");
            $select_siblings.each(function () {
                var $this = $(this);
                if (select_val === $this.attr("data-questionid")) {
                    $this.css("display", "");
                    return false;
                }
            });
        }
    }

    //给确定按钮添加事件，输出选择好的拼出来的信息
    $form_area.parent().on("click.make-sure", "button", function (e) {
        //console.log(e.target.className);

        var $button=$(e.target),
            $form_area=$button.parent().siblings("div.form-area"),
            $fancy_ct=$form_area.parent().parent().siblings("div.fancy-ct"),
            $det_form=$form_area.parent().parent(),
            $input=$form_area.find("input[name=question_time_value]"),
            $dd=$input.parent();


        $fancy_ct.show();
        if (e.target.className !== "make-sure usable") {
            $button.parents("#third-levl"+subId+":first").hide();
            return;
        }

        if(!asteriskValidate($input)){
            if($input.siblings("div.validate").length===0){
                $dd.append("<div class='validate' style='color: red;'>时间不能为空！！！</div>");
            }else{
                $input.siblings("div.validate").css("display","block");
            }
            return;
        }

        $fancy_ct.find("li[data-id="+subId+"]").addClass("check-state");
        // var $dls = $form_area.find("dl"),
        //     info1=$form_area.siblings("h3.pro-name").text()+$dls.find("[name=question_time_value]").val()+$dls.find("[name=question_time_unit]").find("option:selected").text(),
        //     info2="",
        //     areatext_text=$dls.find("[name=question_supplement]").text().trim();
        //
        // info2+=info1+" ,在"+$dls.find("[name^=question_cause]:visible").eq(1).find("option:selected").text()+"情况下 ,"+$dls.find("[name=question_degree]").find("option:selected").text()+" ,呈"+$dls.find("[name=question_nature]").find("option:selected").text();
        // if(areatext_text.length>0){
        //     info2+="("+areatext_text+")";
        // }
        // info1=info1.replace("undefined","");
        // //info2=info2.replace(/,(.*?)undefined([^,]*),*?/g,"");
        // var nHtml = "<span><b data-id='"+subId+"' data-name='"+$form_area.siblings("h3.pro-name").text()+"' data-time-value='"+$dls.find("[name=question_time_value]").val()+"' data-time-unit='"+$dls.find("[name=question_time_unit]").find("option:selected").text()+"'>" + info2 + "</b><i style='display: none'></i><i></i></span>",
        var _this = $(".medical-record-sheet .list"),
            $choose_list0=_this.eq(0).find(".choose-list"),
            $choose_list1=_this.eq(1).find(".choose-list"),
            $choose_list_liNum=$(".medical-record-sheet .list").eq($("body").data("liNum")).find(".choose-list");
       // console.log($("body").data("liNum"));
        if($("body").data("liNum")==0){
            $choose_list0.find("b[data-id="+subId+"]").parent().remove();
            $choose_list1.find("b[data-id="+subId+"]").parent().remove();
            $choose_list0.append(structuring($form_area,"0"));
            $choose_list1.append(structuring($form_area,"1"));
            $choose_list1.find("i").removeAttr("style");
            $choose_list1.parent().find("#third-levl"+subId).remove();
            $choose_list1.siblings("div.third-levl").append($choose_list0.parent().find("#third-levl"+subId).clone(true));
            if($det_form.hasClass("det-form")){
                var $third_levl0=$choose_list0.siblings("div.third-levl"),
                    $third_levl1=$choose_list1.siblings("div.third-levl");
                $third_levl0.append($det_form.find("#third-levl"+subId).clone(true));
                $third_levl1.append($det_form.find("#third-levl"+subId).clone(true));
                $third_levl0.find("#third-levl"+subId).css("display","none");
                $third_levl1.find("#third-levl"+subId).css("display","none");
            }
            chooseListSort($choose_list0);
            chooseListSort($choose_list1);
        }else{
            $choose_list_liNum.find("b[data-id="+subId+"]").parent().remove();

            $choose_list_liNum.append(structuring($form_area,$("body").data("liNum")));
            if($("body").data("liNum")==1){
                $choose_list_liNum.find("i").removeAttr("style");
            }
            if($det_form.hasClass("det-form")){
                var $third_levl_Num=$choose_list_liNum.siblings("div.third-levl");
                $third_levl_Num.append($det_form.find("#third-levl"+subId).clone(true));
                $third_levl_Num.find("#third-levl"+subId).css("display","none");
            }
            chooseListSort($choose_list_liNum);
        }
        appendArea.siblings("div.txt-area").find("div.second-levl").find("li[data-id="+subId+"]").addClass("check-state");
        $button.parents("#third-levl"+subId+":first").hide();
        changDelet();
        $(".push-list li").each(function () {
            var inTxt = $(this).html(),
                title = $form_area.siblings("h3.pro-name").text();
            if (inTxt == title) {
                $(this).addClass("check-state");
            }
        });

        

        $(this).parents("li:first").find("input:first").focus();
        
        
        
        /*** 根据不同类别生成不同的结构化内容的函数 ***/
        function structuring($form_area,type) {
            var $dls = $form_area.find("dl");
            function mainSuit() { //主诉规则
                var pro_name=$form_area.siblings("h3.pro-name").text(),
                    question_time_value=$dls.find("[name=question_time_value]").val(),
                    question_time_unit=$dls.find("[name=question_time_unit]").find("option:selected").text(),
                    html=pro_name+question_time_value+question_time_unit;
                return "<span><b data-id='"+subId+"' data-name='"+pro_name+"' data-time-value='"+question_time_value+"' data-time-unit='"+question_time_unit+"'>" + html + "</b><i style='display: none'></i><i></i></span>";
            }
            function presentIllness() { // 现病史规则
                var pro_name=$form_area.siblings("h3.pro-name").text(),
                    question_time_value=$dls.find("[name=question_time_value]").val(),
                    question_time_unit=$dls.find("[name=question_time_unit]").find("option:selected").text(),

                    html=pro_name+question_time_value+question_time_unit+" ,在"+$dls.find("[name^=question_cause]:visible").eq(1).find("option:selected").text()+"情况下 ,"+$dls.find("[name=question_degree]").find("option:selected").text()+" ,呈"+$dls.find("[name=question_nature]").find("option:selected").text(),
                    area_text=$dls.find("[name=question_supplement]").text().trim();

                if(area_text.length>0){
                    html+="("+area_text+")";
                }
                return "<span><b data-id='"+subId+"' data-name='"+pro_name+"' data-time-value='"+question_time_value+"' data-time-unit='"+question_time_unit+"'>" + html + "</b><i style='display: none'></i><i></i></span>";
            }
            function previous() { // 既往史规则
                var input=$form_area.siblings("h3.pro-name").text()+" "+$dls.eq(0).find("input").val()+" ",
                    checkbox_val="",
                    area_val=$dls.eq(2).find("textarea").val(),
                    html="";
                $dls.eq(1).find("span").each(function () {
                    var $span=$(this);
                    if($span.hasClass("check-state")){
                        checkbox_val+=$span.text()+" ";
                    }
                });
                html+=input+" "+checkbox_val+area_val;
                if(html[0]===" "){
                    html=html.slice(1,html.length);
                }
                if(html[html.length-1]===" "){
                    html=html.slice(0,html.length-1);
                }
                return "<span><b data-id='"+subId+"'>" + html + "</b><i style='display: none'></i><i></i></span>";
            }
            function other() { // 其他史规则
                var html=$form_area.siblings("h3.pro-name").text()+" ";
                $dls.each(function () {
                   var $dd=$(this).children("dd");
                   $dd.children().each(function () {
                       var $node=$(this);
                       if($node.get(0).tagName==="INPUT"){
                           if($node.attr("data-labelprefix")){
                               html+=$node.attr("data-labelprefix");
                           }
                           html+=$node.val();
                           if($node.attr("data-labelsuffix")){
                               html+=$node.attr("data-labelsuffix");
                           }
                           html+=" ";
                       }else if($node.get(0).tagName==="SPAN"){
                           if($node.hasClass("check-state")){
                               html+=$node.text()+" ";
                           }
                       }else if($node.get(0).tagName==="SELECT"){
                           html+=$node.children("option:selected").text()+" ";
                       }else if($node.get(0).tagName==="TEXTAREA"){
                           var val=$node.val().trim();
                           if(val!==""){
                               html+=val+" ";
                           }
                       }
                   });
                });
                if(html[0]===" "){
                    html=html.slice(1,html.length);
                }
                if(html[html.length-1]===" "){
                    html=html.slice(0,html.length-1);
                }
                return "<span><b data-id='"+subId+"'>" + html + "</b><i style='display: none'></i><i></i></span>";
            }

            function sign() { // 体征规则
                var pro_name=$form_area.siblings("h3.pro-name").text(),
                    checkbox_val="",
                    html;
                $dls.eq(0).find("span").each(function () {
                    var $span=$(this);
                    if($span.hasClass("check-state")){
                        checkbox_val+=$span.text()+" ";
                    }
                });
                html=pro_name+":"+checkbox_val;
                return "<span><b data-id='"+subId+"'>" + html + "</b><i style='display: none'></i><i></i></span>";
            }
            
            function assay() { //化验
                
            }

            function implement() { //器查

            }
            
            function diagnose() { // 诊断
                
            }
            
            function cure() { // 治疗
                
            }
            
            var structuring={
                "0":mainSuit,
                "1":presentIllness,
                "2":previous,
                "3":other,
                "4":sign,
                "5":assay,
                "6":implement,
                "7":diagnose,
                "8":cure
            };

            return structuring[type]();

        }


    });

}

/*** 现病史根据结构化内容根据时长排序 ***/
function chooseListSort($choose_list) {

    var time_trans={
            "分钟":1,
            "小时":60,
            "天":1440,
            "月":43200,
            "年":518400
        },
        $spans=$choose_list.find("span"),
        spans_arr=[];

    $spans.each(function () {
        spans_arr.push($(this).get(0));
    });

    spans_arr.sort(function (a,b) {
        var b_o1=a.getElementsByTagName("b")[0],
            b_o2=b.getElementsByTagName("b")[0],
            a_time=parseInt(b_o1.getAttribute("data-time-value")) * time_trans[b_o1.getAttribute("data-time-unit")],
            b_time=parseInt(b_o2.getAttribute("data-time-value")) * time_trans[b_o2.getAttribute("data-time-unit")];

        if(a_time>b_time){
            return -1;
        } else if(a_time < b_time){
            return 1;
        }else{
            return 0;
        }
    });
    $.each(spans_arr,function () {
        $choose_list.get(0).appendChild(this);
    });
}