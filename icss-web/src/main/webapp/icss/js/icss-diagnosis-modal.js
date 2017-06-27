var icssDiag =  (function () {
    var $group = $(".container .group").eq(7);
    bindDiagnosisClick();
    // initDiaData(temp);
    function initDiaData (allListItem) {
        var html = '';
        
        for(var i = 0; i < allListItem.length; i++){
            if(allListItem[i].willCheck == "true") {
                var html = '<p class="willCheck" diagnosiscode="diagnosiscode'+ allListItem[i].itemId +'" style="height:30px;margin-top:6px;"><span class="dot"></span>'+ allListItem[i].diagnosisname +'<i style="display:none!important"></i><i></i></p>';
                $group.find(".content").html(html);
                $group.find(".choose").show();
                $group.find(".content").on("click",".willCheck i",function() {
                    $(this).parents(".choose").hide().find("p").remove();
                });
                return false;
            }
            
            if(allListItem[i].itemId == "goaway") {
                $group.find("div-for-go-away .row").each(function(ind, val) {
                    $(v).find("input").val(allListItem[i].contents[ind].value);
                    
                });
            }else {
                html += 
                '<div class="symptom" diagnosiscode='+ allListItem[i].itemId +' diagnosisname='+ allListItem[i].title.itemName +' standard-id="" data-param-code="" isdiag="null" style="visibility: visible;">'+
                    '<span class="tick--right" style="display: none"></span>'+
                    '<i class="iconfont icon-i" style="visibility: hidden;"></i>'+
                '</div>';
            }
        }
        
        
        $group.find(".symptom-container .symptom-wrap").html(html);
        //为新生成的symptom添加事件
        $group.find(".symptom-wrap").children("div.symptom").click(function () {
            common.cancelReturnLastStep($group.find('div.choose'));
            var formId = dynamicDiagnosisForm(this);
            addDiagnosisEvents(formId, 1);
            $(this).parents(".symptom-wrap").css("display", "none");
            $(".diagnose-main-input").val("");
        });
        //判断 是否有 转院 
        for(var j = 0; j < allListItem.length; j++){
            if(allListItem[j].itemId == "goaway") {
                $group.find(".div-for-go-away .row").each(function(ind, val) {
                    $(val).find("input").val(allListItem[j].contents[ind].value);
                });
                if(allListItem[j].title.checkboxClass == "true") {
                    $(".div-for-go-away .checkbox").addClass("checked");
                }
            }else {
                $group.find(".choose ").find("p").each(function(i,v) {
                   ;
                    if($(v).attr("diagnosiscode")=="goaway"){
                        $(v).hide();
                    }
                });
            }
        }
        var isTrans = false;//是否转院
            //触发新生成的symptom的点击事件，生成表单
            $group.find(".symptom-wrap").children(".symptom").each(function(i,v){
                 $(v).click();
            });
             //触发 确定按钮的 点击 事件
            setTimeout(function(){
                $group.find(".lis-check-some .down-arrow.info").css({
                    left: "32px"
                });
                $group.find(".submit-bt .make-sure.usable").click();
                for(var j = 0; j < allListItem.length; j++){
                    if(allListItem[j].itemId == "goaway") {
                        isTrans = true;
                    }
                }
                if(!isTrans){
                    $group.find(".choose ").find("p").each(function(i,v) {
                        if($(v).attr("diagnosiscode")=="goaway"){
                            $(v).hide();
                        }
                    });
                }
            },0);
    }
    $group.find(".arrow.detail").click(function () {
        initDiagnosisMore();
        initDiagnosisMoreEvent();
        $group.find(".symptom-all").slideDown();
        $group.find(".symptom-form").slideUp();
        updateSelect();
    });

    //-为已选项目添加 样式
    //-由于点击确定后，还未将选择的数据插入页面中，所以采用定时器
    function updateSelect(){
         //-为已选项目添加 样式
        //-由于点击确定后，还未将选择的数据插入页面中，所以采用定时器
        var timer = setTimeout(function(){
            var $p_list = $group.find('.choose .content p,.choose .content div');
            $p_list.each(function(i,v){
                var id = $(v).attr("diagnosiscode").slice(13);//截取diagnosiscode123中的数组id，作为下面查找的唯一id
                $group.find(".wrap p[diagnosiscode="+ id +"]").addClass('high-light-color');
            })
        },0)
    }

    $group.find(".symptom-all>.title>.arrow").click(function () {
        $group.find(".symptom-all").slideUp();
        //$group.find(".symptom-wrap").slideDown();
        $group.find(".symptom-form").css("display","block");
    });

    /*给转诊的确定按钮添加事件*/
    $group.find(".div-for-go-away").find(".submit-bt").on("click", "button", function (e) {
        var hname = $group.find(".div-for-go-away .diagnosis-check-hospital input:first").val();
        var tname = $group.find(".div-for-go-away .diagnosis-check-dept input:first").val();
        var dname = $group.find(".div-for-go-away .diagnosis-check-doctor input:first").val();
        if (!$(this).hasClass("make-sure")) {
            //$group.find(".symptom-container").slideUp();
            $group.find(".div-for-go-away").slideUp();
            $group.find(".symptom-wrap").css("display", "block");
            return;
        }
        $group.find(".choose").find("p[diagnosiscode=goaway]").remove();
        var info = "转诊：" + hname + "，" + tname + "，" + dname;

        var li = '<p diagnosiscode="goaway"><span class="dot"></span>' + info + '<i style="display:none!important"></i><i></i></p>';
        var $li = $(li);
        $li.click(function () {
            //模仿一次document。click
            common.selectHide();
            common.removeFocus();
            common.groupHide($group);

            $group.find(".symptom-form .detail-list").css("display", "none");
            $group.find(".symptom-container").fadeIn();
            $group.find(".div-for-go-away").slideDown();
            $group.find(".symptom-wrap").css("display", "none");
        });

        $li.find("i:last").click(function (e) {
            this.parentNode.parentNode.removeChild(this.parentNode)
            e.stopPropagation();
            showOrHideChoose();
        });
        $group.find(".choose .content").append($($li));

        $group.find(".symptom-wrap").css("display", "block");
        $group.find(".symptom-container").css("display","none");
        showOrHideChoose();

        $group.find(".div-for-go-away").slideUp();
    })
    /*给转诊详细框添加事件*/
    $group.find(".div-for-go-away").click(function () {
        common.selectHide();
        common.removeFocus();
    });

    /*给转诊下拉框添加事件*/
    $group.find(".div-for-go-away div.select.box-r-s").unbind();
    $group.find(".div-for-go-away div.select.box-r-s").on("click.common", function (e) {
        var $select = $(this);
        common.selectHide($select);
        $select.addClass("focus").children("ul").fadeIn();
        e.stopPropagation();
    });

    /*** 转诊select的下拉菜单的点击事件 ***/
    $group.find('.div-for-go-away ul.box-r-s').on("click.common", "a,input,div", function (e) {
        var $node = $(e.target),
            $a = $node.parents("a:first");
        //console.log($node);
        if (e.target.nodeName === "A") {
            if ($node.children("div.c-r.checkbox").length === 0) {
                common.selectSingleChoiceByText($node);
            }
        } else {
            if ($node.siblings().length === 0 && e.target.nodeName === "INPUT") {
                common.selectSingleChoiceByText($a);
            } else {
                common.selectMultipleChoiceByCheckbox($a);
            }
        }
        common.selectHide();
        e.stopPropagation();
    });

    /*给转诊复选框添加事件*/
    $group.find("div.checkbox").on("click.common", function () {
        var $checkbox = $(this),
            index = $checkbox.parents("div.group").index();
        if (!$checkbox.parents("div.symptom-form:first").hasClass("disabled")) {
            common.checkboxChangeState($checkbox);
        }
    });
    $group.find(".come-again").click(function () {
        //转诊信息
        if($group.find("p[diagnosiscode=goaway]").length > 0){
            // alert("删除转诊信息！") //删除疾病和转诊信息的提示信息暂不添加
        }
        var mainsuit = $(".container .choose:first").find("p:first");
        var li = '<p class="willCheck" diagnosiscode=diagnosiscode'+ mainsuit.attr("data-relation-id") +' style="height:30px;margin-top:6px;"><span class="dot"></span>' + mainsuit.attr("data-symptom-name")
            + '待查<i style="display:none!important"></i><i></i></p>';
        $group.find(".choose .content").children().remove();
        var $li = $(li);
        $li.find("i:last").click(function () {
            this.parentNode.parentNode.parentNode.nextSibling.style.display = 'block';
            this.parentNode.parentNode.removeChild(this.parentNode);
            showOrHideChoose();
            event.stopPropagation();
        });
        $group.find(".choose .content").append($li);
        showOrHideChoose();
        $group.find(".symptom-container").fadeOut(function () {
            // alert("疾病信息已删除") //删除疾病和转诊信息的提示信息暂不添加
        });
        
        $(".diagnosis-main-input").css("display", "none");

    });

    $group.find(".go-away").click(function () {
        $group.find(".symptom-wrap").css("display", "none");
        $group.find(".div-for-go-away").slideDown();
    });

    function bindDiagnosisClick() {
        $group.click(function (e) {
            $(".box-r-s.detail-info").hide();
            e.stopPropagation();
            return false;
        });
        $group.find("input:first").click(function () {
            common.autoScrollY($("body"), $(this).parents("div.group"), -170);
            cleanCache(7);
            $(this).parents(".group").addClass("focus");
            common.groupHide($(this).parents(".group"));
            //若没有主诉/现病史/体征，则隐藏待查和转诊
            var showbtn = $(".group:first .choose:first").find("p:first").length + $(".group:eq(1) .choose:first").find("p:first").length
                + $(".group:eq(4) .choose:first").find("p:first").length;
            if (showbtn < 3) {
                $(".come-again").css("display", "none");
                $(".go-away").css("display", "none");
                $(".diagnosis .symptom-container").css("min-height", "auto");
            } else {
                $(".come-again").css("display", "inline-block");
                $(".go-away").css("display", "inline-block");
                $(".diagnosis .symptom-container").css("min-height", "80px");
            }
            
            $(".div-for-go-away").slideUp();

            $(".symptom-all").slideUp();
            if ($group.find(".symptom-container").css("display") == "block") {
                return false
            }
            
            $group.find(".symptom-form").css("display", "block");//因为common.js里的groupHide会在document click时将其变为隐藏
            $group.find(".symptom-wrap").children().remove();
            var isStartReason = false;//设置标示，表示是否是startREason接口获取的数据
            var paras = {};
            var resl = {};
            var useBase = 1;//用参数表示需不需要诊断依据系列事件，包括显示百分比图标，详细框中放入诊断依据
            if (common.allIsNull()) {
                console.log("其他项为空，获取疾病")
                
                paras = {
                    patientId: Param.patientId,
                    doctorId: Param.doctorId,
                    size: 40,
                    sexType: Param.sexType,
                    deptNo: Param.deptNo,
                    hospitalCode: Param.hospitalCode,
                    age: Param.age,
                    type: 7
                }
            
                resl = ajaxPost(Param.hostUrl + Param.get_disease_push, paras);
            } else {
                console.log("其他项不为空，获取疾病")
                useBase = 1;
                paras = commitDataForBasis();
                resl = ajaxPost(Param.hostUrl + Param.rule_controller_start, paras);
                isStartReason = true;
                if(resl.data=="null"||resl.data==undefined||resl.data.length==0){
                    console.log("诊断依据获取疾病为空，获取默认")
                    paras = {
                        patientId: Param.patientId,
                        doctorId: Param.doctorId,
                        size: 40,
                        sexType: Param.sexType,
                        deptNo: Param.deptNo,
                        hospitalCode: Param.hospitalCode,
                        age: Param.age,
                        type: 7
                    }
                    isStartReason = false;
                    resl = ajaxPost(Param.hostUrl + Param.get_disease_push, paras);
                }
                Param.diseaseBase = resl.data;
            }
            var li = ajaxPushDiagnosisList(resl.data, useBase,isStartReason);
            li += '<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';

            $group.find(".symptom-wrap").append($(li));
            $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                $(this).blueTip();
            });
            common.changeContainerShadowWidth($group.find("div.container-shade"));

            $group.find(".symptom-form .detail-list").css("display", "none");
            $group.find(".symptom-wrap").css("display", "block");

            addDiagnosisInformationEvents(useBase);
            addDiagnosisBaseClick();
            $(".symptom-container").slideUp();
            //$group.find(".symptom-container .symptom-form").css("display","none");
            $group.find(".symptom-container").slideDown();
            moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
            $group.find(".symptom-wrap").children("div.symptom").click(function () {
                common.cancelReturnLastStep($group.find('div.choose'));
                var formId = dynamicDiagnosisForm(this);
                addDiagnosisEvents(formId, useBase);
                $(this).parents(".symptom-wrap").css("display", "none");
                $(".diagnose-main-input").val("");
            });

            
        });

        //$group.find("input").keyup(function () {
        $group.find("input").on("input propertychange" , function(){
            $group.find(".symptom-all").slideUp();
            $group.find(".symptom-wrap").children().remove();
            var word = $(this).val();
            if (word.length == 0) {
                return false
            }
            // notIds:
            var idStr = "";
             $(this).siblings(".choose").find(".content>p,.content>div").each(function(i,v){

                 var temp = $(v).attr("diagnosiscode");
                 if(temp){
                    idStr +=temp.slice(13) +",";
                 }
            }); 
            idStr =  idStr.slice(0, idStr.length-1);
            var paras = {
                patientId: Param.patientId,
                doctorId: Param.doctorId,
                size: 40,
                sexType: Param.sexType,
                deptNo: Param.deptNo,
                hospitalCode: Param.hospitalCode,
                age: Param.age,
                inputstr: word,
                notIds: idStr
            }
            var resl = ajaxPost(Param.hostUrl + Param.search_his_diseases, paras);

            var li = ajaxPushDiagnosisList(resl.data);
            li += '<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';

            $group.find(".symptom-wrap").append($(li));
            $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                $(this).blueTip();
            });
            common.changeContainerShadowWidth($group.find("div.container-shade"));
            addDiagnosisInformationEvents();
            addDiagnosisBaseClick();

            moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
            $group.find(".symptom-wrap").children("div").click(function () {
                common.cancelReturnLastStep($group.find('div.choose'));
                var formId = dynamicDiagnosisForm(this);
                addDiagnosisEvents(formId);
                $(this).parents(".symptom-wrap").css("display", "none");
                $(".diagnosis-main-input").val("");
            });
        });
    }
    function addDiagnosisInformationEvents(useBase) {
        $group.find(".symptom-wrap").children().hover(function () {
            $(this).children("i").css("visibility", "visible");
        }, function () {
            $(this).children("i").css("visibility", "hidden");
        });
        $group.find(".symptom-wrap").children().children().click(function (e) {

            //由于弹出诊断依据后，滚动鼠标后底下的页面也会移动
            $("body").css({
                "overflow-y":'hidden'
            });

            var itemId = $(this).parent().attr("diagnosiscode");//以下写死的itemid需要替换
            var resl = ajaxPost(Param.hostUrl + Param.get_by_itemidAndType, {type: '7', itemId: itemId});

            $("body").append($('<div id="informationShade"></div>'));
            $("#informationShade").height($(".container:first").height());
            var mark = new Date().getTime();
            
            if (useBase === 1) {
                var li = '';
                li += '<li class="information-link focus"><span></span><span>诊断依据</span></li>'
                    + '<li class="information-line"></li>';
                var dl = '';
                dl += '<dl class="pushList"><dt><span>诊断依据</span><hr></dt><dd><ul>';
                $(Param.diseaseBase).each(function () {
                    if (this.id == itemId) {
                        $(this.diagnoseInfos).each(function (index) {
                            if (this.accordWithCode === "NO") {
                                
                                dl += '<li><span class="information-false"></span><span>' + (index + 1) + '.</span>'
                                    + '<span>' + this.description + '</span></li>'
                            } else if (this.accordWithCode === "YES") {
                                dl += '<li><span class="information-true"></span><span>' + (index + 1) + '.</span>'
                                    + '<span>' + this.description + '</span></li>'
                            }
                        });
                    }
                });
                dl += '</ul></dd></dl>';
                var $info = $('#information1').tmpl(resl.data);
                // console.log(resl.data);
                $info.attr("id", mark);
                $info.find(".information-list ul").prepend($(li));
                $info.find(".information-content").prepend($(dl));
                if(resl.data && resl.data.description){
                    $info.find(".information-zdzn ul li:first").html(resl.data.description);
                }
                if(resl.data && resl.data.nice){
                    $info.find(".information-nice ul li:first").html(resl.data.nice);
                }
                $info.appendTo('body');
                
            } else {
                var $info = $('#information1').tmpl(resl.data);
                $info.attr("id", mark).find(".information-list ul li:first").addClass("focus");
                if(resl.data&&resl.data.description){
                    $info.find(".information-zdzn ul li:first").html(resl.data.description);
                }
                if(resl.data&&resl.data.nice){
                    $info.find(".information-nice ul li:first").html(resl.data.nice);
                }
                $info.appendTo('body');
            }
            autoInformationHeight();
            palceModalMiddle(mark);
            common.informationControlToggle();
            
            $(".information .information-close").click(function (event) {
                event.stopPropagation();
                $(".information#" + mark).remove();
                $("#informationShade").remove();
                $("body").css({
                    "overflow-y" : 'auto'
                });
            });
            
            e.stopPropagation();
            $(".information").fadeIn();
        });
    }

    function ajaxPushDiagnosisList(data, useBase,isStartReason) {
        var list = [];//list表示已选择的项目，不显示出来
        $group.find(".choose p").each(function () {
            if ($(this).attr("diagnosiscode") == "comeagain") {
                return false
            }
            list.push($(this).attr("diagnosiscode").substring(13));
        });
        var li = '';
        
        $(data).each(function (i, v) {
            if ($.inArray(this.id.toString(), list) >= 0) {
                return true
            }
            //当全部符合 就渲染有 √ 的字符串
            if(v.isdiag != "y"){
                //当字数大于6时，就将多余 ... 表示
                if (this.name.length > 6) {
                    li += '<div class="symptom" diagnosiscode="' + this.id + '" diagnosisname="' + this.name + '" tip-title="'
                        + this.name + '" standard-id="' + this.standardId + '" data-symptom-name="'+this.name+'" data-param-code="'+this.paramCode+'" isdiag="'+this.isdiag+'">' + this.name.substring(0, 6) + "..."
                        +'<span class="tick--right" style="display: none"></span>' +'<i class="iconfont icon-i"></i></div>';
                } else {
                    li += '<div class="symptom" diagnosiscode="' + this.id + '" diagnosisname="' + this.name
                        + '" standard-id="' + this.standardId + '" data-param-code="'+this.paramCode+'" isdiag="'+this.isdiag+'">' + this.name
                        + '<span class="tick--right" style="display: none"></span>'+'<i class="iconfont icon-i"></i></div>';
                }
            }else {
                //当字数大于6时，就将多余 ... 表示
                if (this.name.length > 6) {
                    li += '<div class="symptom" diagnosiscode="' + this.id + '" diagnosisname="' + this.name + '" tip-title="'
                        + this.name + '" standard-id="' + this.standardId + '" data-symptom-name="'+this.name+'" data-param-code="'+this.paramCode+'" isdiag="'+this.isdiag+'">' + this.name.substring(0, 6) + "..."
                        +'<span class="tick--right"></span>' +'<i class="iconfont icon-i"></i></div>';
                } else {
                    li += '<div class="symptom" diagnosiscode="' + this.id + '" diagnosisname="' + this.name
                        + '" standard-id="' + this.standardId + '" data-param-code="'+this.paramCode+'" isdiag="'+this.isdiag+'">' + this.name
                        + '<span class="tick--right"></span>'+'<i class="iconfont icon-i"></i></div>';
                }
            }
        });
        return li;
    }

    function initDiagnosisMoreEvent(){
        /*更多的切换事件*/
        $group.find(".button-group").children("button").click(function () {
            $(this).addClass("focus").siblings().removeClass("focus");
            $group.find(".div-group[tagnum=" + $(this).attr("tagnum") + "]").removeClass("d-n").siblings().addClass("d-n");
            
            //- bug 修改-- 诊断-点击分类时直接显示第一项
            if($(this).attr("tagnum") == 1){
                var $wrap = $(this).parents(".title").siblings(".wrap");
                $wrap.find("div[tagnum=1] .content:first").removeClass("d-n");
                $wrap.find("div[tagnum=1] .menu:first a:first").addClass("focus").parent().siblings().children("a").removeClass("focus");
            }
            updateSelect();
        });
        /*更多的切换事件*/
        $group.find(".symptom-all p[data-symptom-name]").each(function(){
            $(this).blueTip();
        });
    }

    function initDiagnosisMore() {

        var row = '';
        var col = '';
        var colleft = '';
        var colright = '';
        var resl = ajaxPost(Param.hostUrl + Param.get_transverse_portrait_disease, {type: 7});
        $(resl.data).each(function (index1) {
            if (index1 == 0) {
                row += '<button tagnum="' + index1 + '" class="focus">' + this.name + '</button>';
                col += '<div class="div-group wrap-content" tagnum="' + index1 + '">';
            } else {
                row += '<button tagnum="' + index1 + '">' + this.name + '</button>';
                col += '<div class="div-group wrap-content d-n" tagnum="' + index1 + '">';
            }


            if (this.portraitList.length > 0) {
                colleft = '<div class="menu"><ul class="first-level">';
                colright = '';

                $(this.portraitList).each(function () {
                    if (this.portraitList.length > 0) {
                        colleft += '<li><a tagid="' + this.id + '"><i class="iconfont icon-right-triangle"></i>' + this.name + '</a><ul class="second-level d-n">';
                        $(this.portraitList).each(function () {
                            colleft += '<li><a tagid="' + this.id + '">' + this.name + '</a></li>';
                            colright += '<div class="content d-n" tagid="' + this.id + '">';
                            $(this.detailList).each(function () {
                                if (this.name.length > 5) {
                                    colright += '<p class="symptom"  diagnosiscode="' + this.id + '" diagnosisname="' + this.name + '" data-symptom-name="'+this.name+'" data-param-code="'+this.paramCode+'">' + this.name.substring(0, 5) + '...</p>';
                                } else {
                                    colright += '<p class="symptom"  diagnosiscode="' + this.id + '" diagnosisname="' + this.name + '" data-param-code="'+this.paramCode+'">' + this.name + '</p>';
                                }
                            });
                            colright += '</div>';
                        });
                        colleft += '</ul></li>';

                    } else {
                        colleft += '<li><a tagid="' + this.id + '"><i class="iconfont icon-right-triangle"></i>' + this.name + '</a></li>'
                        colright += '<div class="content d-n" tagid="' + this.id + '">';
                        $(this.detailList).each(function () {
                            if (this.name.length > 5) {
                                colright += '<p class="symptom"  diagnosiscode="' + this.id + '" diagnosisname="' + this.name + '" data-symptom-name="'+this.name+'" data-param-code="'+this.paramCode+'">' + this.name.substring(0, 5) + '...</p>';
                            } else {
                                colright += '<p class="symptom"  diagnosiscode="' + this.id + '" diagnosisname="' + this.name + '" data-param-code="'+this.paramCode+'">' + this.name + '</p>';
                            }

                        });
                        colright += '</div>';

                    }
                });
                colleft += '</ul></div>';

            }
            col += colleft + colright;
            col += '</div>';
        });

        $group.find(".symptom-all .button-group button").remove();
        $group.find(".symptom-all .wrap .wrap-content").remove();

        $group.find(".symptom-all .button-group").prepend($(row));
        $group.find(".symptom-all .wrap").prepend($(col));

        var li = "";//常用
        var paras = {
            doctorId: Param.doctorId,
            size: 40,
            sexType: Param.sexType,
            deptNo: Param.deptNo,
            hospitalCode: Param.hospitalCode,
            age: 20
        }
        var commonresl = ajaxPost(Param.hostUrl + Param.get_diseaseinfo_usual, paras);
        li += '<div class="content" style="width:100%">';
        $(commonresl.data).each(function () {
            if (this.name.length > 5) {
                li += '<p class="symptom"  diagnosiscode="' + this.id + '" diagnosisname="' + this.name + '" data-symptom-name="'+this.name+'" data-param-code="'+this.paramCode+'">' + this.name.substring(0, 5) + '...</p>';
            } else {
                li += '<p class="symptom"  diagnosiscode="' + this.id + '" diagnosisname="' + this.name + '" data-param-code="'+this.paramCode+'">' + this.name + '</p>';
            }
        });
        li += '</div>';
        $group.find(".wrap-content[tagnum=0]").append($(li));


        li = "";//第一级tag的div
        if( resl.data ){
            $(resl.data[1].portraitList).each(function () {
                if (this.portraitList.length > 0) {
                    li += '<div class="content d-n" tagid="' + this.id + '">';
                    $(this.portraitList).each(function () {
                        li += '<p class="title">' + this.name + '</p>';
                        $(this.detailList).each(function () {
                            if (this.name.length > 5) {
                                li += '<p class="symptom"  diagnosiscode="' + this.id + '" diagnosisname="' + this.name + '" data-symptom-name="'+this.name+'" data-param-code="'+this.paramCode+'">' + this.name.substring(0, 5) + '...</p>';
                            } else {
                                li += '<p class="symptom"  diagnosiscode="' + this.id + '" diagnosisname="' + this.name + '" data-param-code="'+this.paramCode+'">' + this.name + '</p>';
                            }
                        });
                    });
                    li += '</div>';
                }
            });
        }
        $group.find(".wrap-content[tagnum=1]").append($(li));

        $group.find(".wrap-content a").click(function () {
            $(this).parents(".second-level").siblings("a").removeClass("focus");
            $(this).siblings(".second-level").find("a").removeClass("focus");
            $(this).siblings("ul").removeClass("d-n");
            $(this).parent().siblings().find("ul").addClass("d-n");
            $(this).addClass("focus");
            $(this).parent().siblings().find("a").removeClass("focus");

            $(this).parents(".wrap-content").find(".content[tagid=" + $(this).attr("tagid") + "]").removeClass("d-n").siblings(".content").addClass("d-n");
        });
        $group.find(".wrap-content .content p.symptom").click(function () {
            common.cancelReturnLastStep($group.find('div.choose'));
            var formId = dynamicDiagnosisForm(this);
            addDiagnosisEvents(formId);
            $group.find(".symptom-all").slideUp();
            $group.find(".symptom-wrap").css("display", "none");
            $group.find(".symptom-form").slideDown();
        
        });
    }

    function addDiagnosisBaseClick() {
            $group.find(".symptom-wrap>div>i").on("click", function(e){
                e.stopPropagation();
                var diacode = $(this).parent().attr("diagnosiscode");
                var resl = ajaxPost(Param.hostUrl + Param.get_by_diseaseid, {diseaseId: diacode});
                
                setTimeout(function(){
                    // 只有当不显示正常推理 才会显示这个
                    if($(".pushList dd li").length == 0){
                        for(var i=0,d=resl.data; i < d.length;i++){
                            if(d[i].accordWithCode == "YES") {
                                d[i].className = "information-true";
                            }else{
                                d[i].className = "information-false";
                            }
                        }
                        $('.information-list').html($("#informationSample").tmpl([1]));
                        $('.information-dependence').html($("#informationLists").tmpl(resl));
                        $(".information-content .pushList").remove();
                    }
                    
                });
            });
            // $group.find(".symptom-wrap>div>i:last").click(function (e) {
            //     e.stopPropagation();
            //     var diacode = $(this).parent().attr("diagnosiscode");
            //     var resl = ajaxPost(Param.hostUrl + Param.get_by_diseaseid, {diseaseId: diacode});
            // });
    }

    function dynamicDiagnosisForm(el) {
        var appendArea = $group.find(".symptom-form");

        var code = $(el).attr("diagnosiscode");
        var paramCode=$(el).attr("data-param-code");
        if ($("#diagnosiscode" + code).length > 0) {
            //appendArea.slideDown();
            if ($("#diagnosiscode" + code).css("display") == "block") {
                return false
            }
            appendArea.children().css("display", "none");
            $("#diagnosiscode" + code).slideDown();
            return false;
        }
        appendArea.children().css("display", "none");
        var title = $(el).attr("diagnosisname");

        appendArea.append($('<div id="diagnosiscode' + code + '" data-param-code="'+paramCode+'" class="detail-list" style="display:none"><h3><b style="float:left;" class="toptitle">' + title
            + '</b></h3><div class="form-area"><div class="row diagnosis-check-some" style="overflow:hidden"></div>'
            + '</div>'
            + '<p class="submit-bt"><button class="make-sure usable" type="button">确定</button><button class="cancle unusable" type="button">取消</button></p></div>'));
        var a = "diagnosiscode" + code;
        var $form_area = $(document.getElementById(a)).find(".form-area .diagnosis-check-some");
        $form_area.append(
            $('<div name="visittime" model="willTreat" class="c-r radio checked">'+
                '<div class="box-r-s">'+
                    '<i class="iconfont icon-yuandian"></i>'+
                '</div>'+
                '初诊'+
            '</div>'+ 
            '<div name="visittime" model="treatAg" class="c-r radio">'+
                '<div class="box-r-s">'+
                    '<i class="iconfont icon-yuandian"></i>'+
                '</div>'+
                '复诊'+
            '</div>'));
        //appendArea.slideDown();
        $("#diagnosiscode" + code).slideDown();
        return a;

    }

    function addDiagnosisEvents(formId) {
        var $eventarea = $(document.getElementById(formId));
        $eventarea.click(function () {
            common.selectHide();
            common.removeFocus();
        });
        $eventarea.find("div.c-r.radio").each(function () {
            $(this).on("click.common", function () {
                var $radio = $(this);
                common.radioChangeState($radio)
            });
        });

        //给确定按钮添加事件，输出选择好的拼出来的信息
        $eventarea.children(".submit-bt").on("click", "button", function (e) {
            if (e.target.className !== "make-sure usable") {
                if ($group.find("div.choose").data("lastStep") == "symptom-wrap") {
                    $eventarea.slideUp();
                    $group.find(".symptom-wrap").css("display", "block");
                    moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
                } else {
                    $eventarea.slideUp();
                    $group.find(".symptom-all").slideDown();
                }
                return {
                    "initDiaData": initDiaData
                };
            }
            
            $group.find(".choose").find("p[diagnosiscode=" + formId + "]").remove();
            var info = "";

            var tit = $(this).parent().siblings("h3").text();

            var resl = {data:false};
//            var resl = ajaxPost(Param.hostUrl + Param.get_mapping_disease, {
//                hospitalCode: Param.hospitalCode,
//                icssDiseaseId: formId.substring(13)
//            });
//            if (resl.data.length == 0) {
//                // alert("his无对应项");
//                // return false
//            }else if(resl.data[0] == null){
//                // alert("his无对应项数据");
//                // return false
//            }

            var isdiag="拟诊";
            if($group.find(".symptom-wrap").find("div[diagnosiscode=" + formId.substring(13) + "]").attr("isdiag")=="y"){
                isdiag="确诊"
            }

            // info += tit + "（" + resl.data[0].name + "）" + "（" + getVal($eventarea) + ","+isdiag+"）";
            info += tit  + "（" + getVal($eventarea) + ","+isdiag+"）";
            
            if(resl.data){
                var li = '<p diagnosisname="'+$eventarea.find(".toptitle").text()+'" diagnosiscode="' + formId + '" standard-id="' + $group.find(".symptom-wrap").find("div[diagnosiscode=" + formId.substring(13) + "]").attr("standard-id")
                     + '" hiscode="' + resl.data[0].hisDiseaseCode + '" hisname="' + resl.data[0].name + '"  icdcode="' + resl.data[0].code + '">'+
                        '<span class="dot"></span>' + 
                        info + 
                        '<i style="display:none!important"></i>'+
                        '<i></i>'+
                    '</p>';
            }else {
                var li = '<p diagnosisname="'+$eventarea.find(".toptitle").text()+'" diagnosiscode="' + formId + '" standard-id="' + $group.find(".symptom-wrap").find("div[diagnosiscode=" + formId.substring(13) + "]").attr("standard-id")
                     + '" hiscode="" hisname=""  icdcode="">'+
                        '<span class="dot"></span>' + 
                        info + 
                        '<i style="display:none!important"></i>'+
                        '<i></i>'+
                    '</p>';
            }
            var $li = $(li);
            $li.click(function () {
                //模仿一次document。click
                common.selectHide();
                common.removeFocus();
                common.groupHide($group);

                $group.find(".div-for-go-away").slideUp();
                $group.find(".symptom-container").css("display", "block");
                $group.find(".symptom-form").fadeIn();
                $("#" + $(this).attr("diagnosiscode")).slideDown().siblings().css("display", "none");
                $group.find(".symptom-wrap").css("display", "none");
                $group.find(".symptom-all").slideUp();
            });
            $li.find("i:last").click(function (e) {
                this.parentNode.parentNode.removeChild(this.parentNode)
                e.stopPropagation();
                //点击删除时，删除掉原详细框
                $("#"+formId).remove();
                $group.find(".content p.symptom[diagnosiscode=" + formId.substring(13) + "]").removeClass("focus");
                //重新生成推送
                $group.find(".symptom-wrap").children().remove();

                var resl = {};

                if (common.allIsNull()) {
                    console.log("其他项为空，获取疾病")
                    paras = {
                        patientId: Param.patientId,
                        doctorId: Param.doctorId,
                        size: 40,
                        sexType: Param.sexType,
                        deptNo: Param.deptNo,
                        hospitalCode: Param.hospitalCode,
                        age: Param.age,
                        type: 7
                    }
                    resl = ajaxPost(Param.hostUrl + Param.get_disease_push, paras);
                } else {
                    console.log("其他项不为空，获取疾病")
                    useBase = 1;
                    paras = commitDataForBasis();
                    resl = ajaxPost(Param.hostUrl + Param.rule_controller_start, paras);
                    if(resl.data=="null"||resl.data==undefined||resl.data.length==0){
                        // console.log("诊断依据获取疾病为空，获取默认")
                        paras = {
                            patientId: Param.patientId,
                            doctorId: Param.doctorId,
                            size: 40,
                            sexType: Param.sexType,
                            deptNo: Param.deptNo,
                            hospitalCode: Param.hospitalCode,
                            age: Param.age,
                            type: 7
                        }
                        resl = ajaxPost(Param.hostUrl + Param.get_disease_push, paras);
                    }
                    Param.diseaseBase = resl.data;
                }

                var li = ajaxPushDiagnosisList(resl.data);
                li += '<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';
                // console
                $group.find(".symptom-wrap").append($(li));
                $group.find(".symptom-wrap div[data-symptom-name]").each(function(){
                    $(this).blueTip();
                });
                common.changeContainerShadowWidth($group.find("div.container-shade"));
                $group.find(".symptom-wrap").css("display", "block");
                addDiagnosisInformationEvents();
                addDiagnosisBaseClick();
                moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
                $group.find(".symptom-wrap").children("div").click(function () {
                    common.cancelReturnLastStep($group.find('div.choose'));
                    var formId = dynamicDiagnosisForm(this);
                    addDiagnosisEvents(formId);
                    $(this).parents(".symptom-wrap").css("display", "none");
                    $(".diagnose-main-input").val("");
                });
                showOrHideChoose();
                forDeleteCache();
                serverCache();
            });
            $group.find(".choose").children("[diagnosiscode=comeagain]").remove();
            $group.find(".choose .content").append($($li));

            $eventarea.slideUp();
            $group.find(".symptom-wrap").css("display", "block");
            $group.find(".symptom-wrap").find("div[diagnosiscode=" + formId.substring(13) + "]").remove();
            $group.find(".content p.symptom[diagnosiscode=" + formId.substring(13) + "]").addClass("focus");

            addDiagnosisInformationEvents();
            addDiagnosisBaseClick();
            moveSymptomWrap($group.find('div.symptom-container:first').children("div.symptom-wrap"));
            $group.find(".symptom-container").css("display","none");
            showOrHideChoose();
            serverCache();

        });
    }

    function getVal($node) {
        var t = "";
        $node.find(".diagnosis-check-some").children().each(function () {
            if ($(this).hasClass("checked")) {
                t = $(this).text();
            }
        });
        return t;
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
            _width = 0;
        setDistance("3");
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
            if (flag) {
                add = 1;
            } else {
                add = -1;
            }
            $symptoms.css("visibility", "visible");
            $symptom_wrap.data("distance", $symptom_wrap.data("distance") + add);
            changeArrowState();
            $symptom_wrap.animate({"marginLeft": "-" + distance[$symptom_wrap.data("distance")][2]}, 300, "linear", function () {
                $symptoms.each(function (i, v) {
                    changeShowSymptom(i, v);
                });
            });
        }

        $symptoms.each(function () {
            _width += $(this).outerWidth(true);
        });
        if (_width < 736) {
            $arrow_right.addClass("d-n");
            $arrow_left.addClass("d-n");
        } else {
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
                $symptom.css("visibility", "visible");
            }
        }

        function setDistance(move_count) {

            function getMarginLeft($symptoms, i, margin_left) {
                var len = $symptoms.length,
                    j,
                    _margin_left = 0,
                    index = i,
                    flag = false,
                    width = 0;
                for (j = i; j < len; j++) {
                    index = j;
                    if (_margin_left + $symptoms.eq(j).outerWidth() < 756) {
                        _margin_left += $symptoms.eq(j).outerWidth();
                    } else {
                        break;
                    }
                }
                if (index === len - 1 && _margin_left + $symptoms.eq(len - 1).outerWidth() < 756) {
                    index = len;
                }
                for (j = i; j < len; j++) {
                    width += $symptoms.eq(j).outerWidth();
                }
                if (width < 680) {
                    flag = true;
                }

                return [index, margin_left + _margin_left, flag];
            }


            if (move_count === "auto") {
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
            } else {
                move_count = Number(move_count);
                var length = $symptoms.length,
                    num = parseInt(length / move_count),
                    i,
                    j,
                    get_margin_arr,
                    margin_left = 0;
                move_count = parseInt(move_count);
                if (num === 0) {
                    get_margin_arr = getMarginLeft($symptoms, 0, 0);
                    distance.push([0, get_margin_arr[0], get_margin_arr[1]]);
                } else {
                    get_margin_arr = getMarginLeft($symptoms, 0, 0);
                    distance.push([0, get_margin_arr[0], 0]);
                    for (i = 0; i + move_count < length; i += move_count) {
                        for (j = i; j < i + move_count; j++) {
                            // console.log($symptoms.eq(j).outerWidth()+"  "+$symptoms.eq(j).text());
                            width += $symptoms.eq(j).outerWidth();
                        }
                        get_margin_arr = getMarginLeft($symptoms, i + move_count, margin_left);
                        margin_left = get_margin_arr[1];
                        distance.push([i + move_count, get_margin_arr[0], width]);
                        if (get_margin_arr[2]) {
                            return;
                        }
                    }
                }
            }

        }

    }

    //move

    function showOrHideChoose() {//是否显示.choose列表
        $group.find(".choose .content").children().length > 0 ? $group.find(".choose").removeClass("d-n") : $group.find(".choose").addClass("d-n")
        common.addChooseBottom($group.find(".choose"))
    }

    function serverCache() {
        var list=$(".diagnosis .choose .content").children();
        inStandardIds = [];
        $(list).each(function () {
            inStandardIds.push($(this).attr("standard-id"));
        });
        var param = {
            patientId: Param.patientId,
            type: 7,
            inStandardIds: inStandardIds.join()
        }
        // flushDataIntoCache(param);
        diagnosisFocus(param);
    }

    function forDeleteCache() {
        if(Param.firstType!=7){return false}//推送依据不是这一栏的话，不进行推理
        var list=$(".diagnosis .choose .content").children();
        inStandardIds = [];
        $(list).each(function () {
            inStandardIds.push($(this).attr("standard-id"));
        });
        var param = {
            patientId: Param.patientId,
            type: 7,
            inStandardIds: inStandardIds.join()
        }
        ajaxPost(Param.hostUrl + "/reason/clean_reason_cache_by_type", param);
    }
    return {
        "initDiaData": initDiaData
    };
})();
function commitDataForBasis() {
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


    function getLisCheckBoxForBasis(){//获取信息用于诊断依据
        var $group=$(".container .group").eq(5);
        var ob={};
        $group.find(".choose .content").children().each(function(){
            var $now_form=$("#"+$(this).attr("liscode"));
            var obj={};
            var val="";
            $now_form.find(".form-area-checked-lis").find("[data-param-code]").not($(".detail-list")).each(function(i, v){
                // if($(this).text().length==0 || $(this).val().length==0){return true}
                if($(v).hasClass("checked")) {
                    if($(v).text().length==0) return true;
                    val += $(v).attr("data-param-code")+"^";
                    obj[$now_form.attr("data-param-code")]=val.substring(0,val.length-1);
                }else {
                    if($(v).find("input").attr("data-param-value")){
                         obj[$(v).attr("data-param-code")]=$(v).find("input").attr("data-param-value");
                    }
                    if($(v).val().length==0){return true}
                    obj[$(v).attr("data-param-code")]=$(v).val();
                }
            });
            ob[$now_form.attr("data-param-code")]=obj;
        });
        return jsonTOstring(ob);
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

    
    var lis = '{"LIS":';//LIS
    lis += getLisCheckBoxForBasis();
    lis += "}";

    var pacs = '{"PACS":';
    pacs += getPacsForBasis();
    pacs += "}";

    var param = {
        symptomJsonString: JSON.stringify(symptom),
        pastJsonString: JSON.stringify(past),
        otherJsonString: JSON.stringify(other),
        vitalsJsonString: JSON.stringify(vital),
        labsJsonString: lis,
        pacsJsonString: pacs
    };
    return param;
}

function getDiagnosisForSave() {
    if ($(".diagnosis .choose .content p").length == 0) {
        return {}
    }
    if ($(".diagnosis .choose .content p:first").attr("diagnosiscode") == "comeagain") {//待查返回数据
        return {
            "type": 7,
            "choose": [
                {
                    "itemId": "",
                    "itemName": "",
                    "itemDescribe": $(".diagnosis .choose .content p:first").text(),
                    "type": "",
                    "contentJson": {},
                    "remark": ""
                }
            ],
            "details": [],
        }
    }

    var data = {
        "type": 7,
        "choose": [],
        "details": [],
    };

    $(".diagnosis .choose .content p").each(function () {
        var optionId = $(this).attr("diagnosiscode").substring(13);
        var diagnosisname=$(this).attr("diagnosisname");
        var chooseOption = {
            "itemId": optionId,
            "itemName": diagnosisname,
            "itemDescribe": $(this).text(),
            "type": 7,
            "remark": ""
        };
        data.choose.push(chooseOption);
        if ($(this).attr("diagnosiscode") == "goaway") {//转诊返回数据
            optionId = $(this).attr("diagnosiscode");
            var option = {
                "itemId": optionId,
                "title": {
                    "itemName": "",
                    "checkboxClass": "true"
                },
                "contents": []
            };
            var $appendArea = $(".diagnosis .div-for-go-away");
            
            $appendArea.find(".form-area input").each(function (i,v) {
                var contentDetail = {
                    "type": $(v).attr("model")
                };
                contentDetail["value"] = $(this).val();
                option.contents.push(contentDetail);
            });
            data.details.push(option);
        } else {
            var option = {
                "itemId": optionId,
                "diagnosisname":"",
                "title": {
                    "itemName": "",
                    "checkboxClass": ""
                },
                "contents": []
            };
            var $appendArea = $("#" + $(this).attr("diagnosiscode"));
            option.title.itemName = $appendArea.find("h3 b").text();
             
            // option.diagnosisname = $appendArea.find
            $appendArea.find(".form-area .radio").each(function (i,v) {
                var contentDetail = {
                    "type": $(v).attr("model")
                };
                if ($(this).hasClass("checked")) {
                   contentDetail["value"] = $(this).text();
                   option.contents.push(contentDetail);
                }
            });
            // console.log($(this));
            option.diagnosisname = $(this).text();
            if($(this).hasClass("willCheck")){
                option.willCheck = "true";
            }
            data.details.push(option);
        }
    });

    return data;
}

function saveDiagnosisForHis() {
    var $group = $(".container .group").eq(7).find(".choose .content");
    if ($group.children().length == 0) {
        return []
    }
    var arr = [];
    $group.children().each(function () {
        arr.push({id: $(this).attr("hiscode"), name: $(this).attr("hisname"), num: 1,icd: $(this).attr("icdcode"),jbqzlx:2})
    });
    return arr;
}

function diagnosisFocus(params){
	//1.清空原有的缓存  2.根据诊断重新推送
	if(Param.firstType != 7){
		Param.firstType = 7;
		ajaxPost(Param.hostUrl + "/reason/clean_reason_cache",{"patientId":Param.patientId});
	}
	var datas = ajaxPost(Param.hostUrl + "/reason/flush_reason_into_cache",params);
}
