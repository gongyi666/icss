/**
 * Created by Kiva on 17/3/29.
 */
(function ($) {
    var isFirst = true;
    var $groups = $("div.group").filter(":lt(5)"),
        //主诉
        $main_suit = $groups.eq(0),
        //现病史
        $present_illness = $groups.eq(1),
        //既往史
        $previous_history = $groups.eq(2),
        //其他史
        $else_history = $groups.eq(3),
        //体征
        $sign = $groups.eq(4);

    $groups.children("label").on("click.main", function () {
        $(document).click();
    });
    addSymptomElse();
    addEvent();

    //因为数据恢复有问题 因此第一次页面恢复数据后初始化一次页面数据作为初始的比较是否有修改的比较对象
    var initParamDataJson = (function () {
        var arr = [false, false];
        return function (obj) {
            if (!obj) {
                return;
            }
            $.each(obj, function (key, val) {
                arr[key] = val;
            });
            if (arr[0] && arr[1]) {
                commitData(true);
                arr = [false,false];
            }
        }
    }());

    /*** 页面加载时恢复上次的数据 ***/
    function loadSymptomGroups(arr) {
        var url = Param.hostUrl + "/at/inquiry_info/index",
            // _arr = [],
            param = {
                hospitalCode: Param.hospitalCode,
                patientId: Param.patientId,
                hisCode: Param.serial
            };
        arr = arr || [2, 3];
        // callback = callback || function () {
        //     };
        //
        // $.each(arr, function (i, v) {
        //     _arr[i] = v;
        // });
        $.ajax({
            url: url,
            async: true,
            data: param,
            dataType: "json",
            type: "post",
            success: function (data) {
                
                if (data.data && data.data.length > 0) {
                    console.log(window.top.index);
                    if (!window.top.index) {
                        recoverSymptomGroups(arr, data.data[0], true);
                    }
                    //console.log(dataJson);
                    // console.log(Param.regVisitedState);
                    // console.log(data.data[0].regVisitedState);
                    if (Param.regVisitedState == '0') {
                        if (data.data[0].regVisitedState == 1) {
                            window.top.index.recoverLast(recoverSymptomGroups, [[0, 1, 2, 3, 4, 5, 6, 7], data.data[0]]);
                        } else {
                            window.top.index2 ? (window.top.index2.initializationDetailList(window.top.index2.patData, false)) : ("");
                        }
                        return;

                    } else if (Param.regVisitedState == '1' || Param.regVisitedState == '2') {
                        recoverSymptomGroups([0, 1, 2, 3, 4, 5, 6, 7], data.data[0], true);

                        window.top.index2 ? (window.top.index2.initializationDetailList(window.top.index2.patData, true)) : ("");
                    }
                    //恢复 器查和化验
                    var jsonData = JSON.parse(data.data[0].dataJson);
                    if(jsonData[5] && jsonData[5].details) {
                        var lisDetails = jsonData[5].details;
                        icssLis.initLisData(lisDetails);
                    }
                    if(jsonData[6] && jsonData[6].details) {
                        var pacsDetails = jsonData[6].details;
                        icssPacs.initPacsData(pacsDetails);
                    }
                    if(jsonData[7] && jsonData[7].details) {
                        var diagDetails = jsonData[7].details;
                        icssDiag.initDiaData(diagDetails);
                    }
                    return;
                }

                if (window.top.index2) {
                    window.top.index2.initializationDetailList(window.top.index2.patData, false);
                }
            },
            error: function (req, info, err) {
                alert("恢复失败");
            }
        });
    }

    function recoverSymptomGroups(_arr, data, is_recover) {
        var itemIds = "",
            dataJson = JSON.parse(data.dataJson),
            diagnose = data.diagnose,
            time = data.clinicTime;


        if (!dataJson) {
            return;
        }

        if (!is_recover) {
            _arr = [2, 3];
        }
        //恢复加载数据
        function diaLis(dataJson) {
            var tmplId = ["#lis_form_tmpl", "#pacs_form_tmpl", "#trate_form_tmpl"];
            var insertId = ['.lis .symptom-form', '.pacs .symptom-form', '.diagnosis .symptom-form'];
            for (var i = 5; i < dataJson.length; i++) {
                // $(tmplId[i - 5]).tmpl([11]).appendTo($(insertId[i - 5]));//将数据载入
            }
        }

        //点击确定咩有反应是由于动态生成，只是当前的那一个，需要重新绑定
        if (isFirst) {
            diaLis(dataJson);
        }
        isFirst = false;
        Param.saveId = data.id;
        if (is_recover) {
            $.each(_arr, function (i, v) {
                if (v === 0) {
                    var data = dataJson[0];
                    if (data.details) {
                        $.each(data.details, function () {
                            itemIds += this.itemId + ",";
                        });
                        itemIds = itemIds.slice(0, -1);
                    }
                    recoverSymptomForms(itemIds, true, dataJson, initParamDataJson, {"0": true});
                    _arr.splice(i, 1);
                    return false;
                }
            });
        } else {
            initParamDataJson({0: true});
        }

        itemIds = "";
        $.each(_arr, function (i, v) {
            var data = dataJson[v];
            if (data.details) {
                $.each(data.details, function () {
                    if (this.itemId) {
                        itemIds += this.itemId + ",";
                    }

                });
            }
        });
        itemIds = itemIds.slice(0, -1);
        recoverSymptomForms(itemIds, false, dataJson, initParamDataJson, {"1": true});
        for (var i = 0; i < dataJson[8].details.length; i++) {
            var drugItem = dataJson[8].details[i];
            all_drugItem.push(drugItem.contents);
        }
        treatment_init();
        // console.log(all_drugItem);
    }


    function recoverSymptomForms(itemIds, is_main, dataJson, callback, callback_arg) {
        callback = callback || function () {
            };
        callback_arg = callback_arg || null;
        if (!itemIds) {
            return;
        }
        $.ajax({
            url: Param.hostUrl + "/kl/questioninfo/get_questioninfo_by_subitemids",
            async: true,
            data: {
                subitemIds: itemIds
            },
            dataType: "json",
            type: "post",
            success: function (data) {
                if (data.data && data.data.length > 0) {
                    var $symptom_container;
                    if (is_main) {
                        $symptom_container = $("div.group:first").find("div.symptom-container");
                        $.each(data.data, function () {
                            addSymptomForm($symptom_container, this.questionList, this.id, this.name, this.transCode, this.standardId, this.paramCode);
                        });
                    } else {
                        $.each(data.data, function () {
                            $symptom_container = $("div.group").eq(this.type).find("div.symptom-container");
                            addSymptomForm($symptom_container, this.questionList, this.id, this.name, this.transCode, this.standardId, this.paramCode);
                        });
                    }
                    $.each(dataJson, function (i) {
                        if (i > 4) {
                            return false;
                        }
                        common.recoverGroup(this);
                    });
                }
                callback(callback_arg);
            },
            error: function (req, info, err) {
                alert("加载既往史其他史失败");
            }
        });
    }


    /*** 加载症状数据 ***/
    function initSubItemInfo($group) {
        var url, param;

        param = {
            doctorId: Param.doctorId,
            size: 40,
            sexType: Param.sexType,
            deptNo: Param.deptNo,
            hospitalCode: Param.hospitalCode,
            age: Param.age,
            type: $group.index() === 0 ? 1 : $group.index(),
            notIds: $group.data("notIds"),
            notCodes: $group.data("notCodes"),
            patId: Param.patientId
        };
        if ($group.data('inputstr') === "") {
            url = Param.hostUrl + "/kl/subitem/init_subiteminfo";
            // url = Param.hostUrl + "/rule_controller/start_subitem";
            // param=$.extend(param,commitDataForBasis());
        } else {
            url = Param.hostUrl + "/kl/retrieval/get_retrieval";
            param["inputstr"] = $group.data('inputstr');
            param.notCodes='';
        }

        $.ajax({
            url: url,
            async: false,
            data: param,
            dataType: "json",
            type: "post",
            crossDomain: true,
            success: function (data) {
                // data=$.parseJSON(data);
                // $.each(data.data, function (i, v) {
                //     $('#IE8ceshi').append('<p>' + v.name + '</p>');
                // });
                if (!data.data) {
                    return;
                }
                var subItemInfo = data.data.subitemInfo ? data.data.subitemInfo : data.data;
                var $symptom_wrap = $group.find('div.symptom-container:first').children("div.symptom-wrap");
                reLoadSubItemInfo(subItemInfo, $symptom_wrap);
                addBlueTipEvent($symptom_wrap.find("div.symptom"));
                moveSymptomWrap($symptom_wrap);
            },
            error: function (req, info, err) {
                alert(req);
                alert(info);
                alert(err);
            }
        });
    }

    //给被省略的疾病添加Tip鼠标移入显示全名
    function addBlueTipEvent($nodes) {
        $nodes.each(function () {
            var $node = $(this),
                text = $node.text();
            if (text.slice(-3, text.length) === "...") {
                $node.blueTip();
            }
        });
    }

    function refreshUseList($symptom_wrap, fn) {
        var $wrap_content = $symptom_wrap.parent().siblings("div.symptom-all").find("div.wrap-content:first"),
            html = '<div class="content" style="width: 100%">',
            url,
            param,
            $group = $symptom_wrap.parents("div.group"),
            $input = $group.children("div.box-r-s").children("input"),
            notIds = "",
            $ps = $input.siblings("div.choose").find("p"),
            fn = fn || function () {
                },
            is_change_notIds;
        $ps.each(function () {
            var $p = $(this);
            notIds += "," + $p.attr("data-relation-id");
        });
        notIds = notIds.slice(1);
        is_change_notIds = (notIds !== $group.data("notIds"));
        if ($group.data("data") && !is_change_notIds) {
            return;
        }
        url = Param.hostUrl + "/kl/subitem/get_usual";
        param = {
            doctorId: Param.doctorId,
            size: 42,
            sexType: Param.sexType,
            deptNo: Param.deptNo,
            hospitalCode: Param.hospitalCode,
            age: Param.age,
            type: $group.index() === 0 ? 1 : $group.index(),
            // notIds: $group.data("notIds"),
            // notCodes: $group.data("notCodes"),
            patId: Param.patientId
        };
        $wrap_content.html("");
        $.ajax({
            url: url,
            async: true,
            data: param,
            dataType: "json",
            type: "post",
            success: function (data) {
                $.each(data.data, function () {
                    html += '<p class="symptom" data-id="' + this.id + '" data-symptom-name="' + this.name + '"' + (this.transCode ? (' data-transCode="' + this.transCode + '"') : '') + (this.standardId ? (' data-standard-Id="' + this.standardId + '"') : '') + '>' + (this.name.length > 5 ? this.name.slice(0, 5) + "..." : this.name) + '</p>';
                });
                html += '</div>';
                $wrap_content.html(html);
                addBlueTipEvent($wrap_content.find("p.symptom"));
                addSymptomFocus($wrap_content.parents('div.symptom-all:first'));
                fn();
            },
            error: function (req, info, err) {
                alert("加载症状列表失败");
            }
        });

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
        // console.log(distance);
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
                arrowClick(true, $symptom_wrap.children('div.symptom.hover'));
            }
        });

        function arrowClick(flag, $hover) {
            var add;
            if (flag) {
                add = 1;
            } else {
                add = -1;
            }
            $symptoms.css("visibility", "visible");
            $symptom_wrap.data("distance", $symptom_wrap.data("distance") + add);
            changeArrowState();
            $symptom_wrap.stop().animate({"marginLeft": "-" + distance[$symptom_wrap.data("distance")][2]}, 300, "linear", function () {
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

        $arrow_detail.off("click.main").on("click.main", function () {
            symptomDetailClickEvent($arrow_detail.parents("div.group").index());
            addSymptomFocus($arrow_detail.parent().parent().siblings("div.symptom-all"));
        });

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
            if (distance[distance.length - 1][1] < $symptoms.length) {
                distance[distance.length - 1][1] = $symptoms.length;
            }

        }


        /*** 下拉出现全部症状事件 ***/
        function symptomDetailClickEvent(type) {
            var $symptom_all = $symptom_wrap.parent().siblings("div.symptom-all"),
                $wrap = $symptom_all.find("div.wrap"),
                type=(type === 0 ? 1 : type),
                param={
                    type:type,
                    sexType:Param.sexType
                };

            if ($wrap.children().length > 0) {
                $symptom_all.slideDown();
                return;
            }
            $wrap.empty();
            $symptom_all.children("div.title").children("div.button-group").empty();

            $.ajax({
                url: Param.hostUrl + "/kl/transverse/get_transverse_by_type",
                cache:false,
                async: true,
                data: {
                    type:type,
                    sexType:Param.sexType
                },
                dataType: "json",
                type: "post",
                success: function (data) {
                    if(!data || !data.data || data.data.length===0){
                        alert(data.data.length);
                        return;
                    }

                    //
                    if ($wrap.children().length !== 0) {
                        return;
                    }
                    var SymptomAllTmpl = getTmpl("SymptomAllTmpl"),
                        symptomAllTmpl = new SymptomAllTmpl();
                    symptomAllTmpl.addButton(data.data);
                    $.each(data.data, function (i, v) {
                        symptomAllTmpl.addWrapContent(v);
                    });
                    $symptom_all.children("div.title").children("div.button-group").append(symptomAllTmpl.button);
                    $symptom_all.children("div.wrap").append(symptomAllTmpl.wrap);
                    resetRelationData($symptom_all);
                    $symptom_all.siblings("div.symptom-container").fadeOut();
                    addSymptomAllEvent($symptom_all);
                    addSymptomFocus($symptom_all);
                    addMenuFirstContent($symptom_all);
                    hiddenMenuArrow($symptom_all);
                    addBlueTipEvent($symptom_all.find("p.symptom"));
                    refreshUseList($symptom_all.siblings("div.symptom-container").children("div.symptom-wrap"), function () {
                        $symptom_all.slideDown();
                    });
                    deleteOneMenu($symptom_all);
                    if (type === 3) {
                        if (Param.sexType !== '2') {
                            $symptom_all.children('div.title').find('button[data-id=13]').addClass("disabled");
                        }
                        if (parseInt(Param.age) && parseInt(Param.age) < 12) {
                            $symptom_all.children('div.title').find('button[data-id=14]').addClass("disabled");
                        }
                    }
                },
                error: function () {
                    alert("加载全部症状失败！！！");
                }
            });

        }

    }


    /*** 校验的提示信息应该也得数据库提供，数据库无此数据，因此校验信息暂时写死 ***/
    function addValidateText($symptom_form) {
        var $form_groups = $symptom_form.find("div.form-group[data-require=1]"),
            index = $symptom_form.parents("div.group").index();
        if (index !== 0 && index !== 1) {
            $form_groups.each(function () {
                var $form_group = $(this);
                if ($form_group.attr("data-type") === "7") {
                    $form_group.append('<div class="validate d-n"><p>请填写正确的时间!!!</p></div>');
                } else {
                    $form_group.append('<div class="validate d-n"><p>请填写必填项!!!</p></div>');
                }
            });

            if ($form_groups.length > 0) {
                $symptom_form.find("button.confirm").addClass("disabled");
            }

        }

    }

    /*** 删除掉只有全部菜单的菜单 ***/
    function deleteOneMenu($symptom_all) {
        var $menus = $symptom_all.find("div.menu");
        $menus.each(function () {
            var $menu = $(this),
                $content;
            if ($menu.find("li").length <= 1) {
                $content = $menu.siblings("div.content");
                $menu.remove();
                $content.removeClass("d-n").css("width", "100%");
            }
        });
    }


    /*** 给有子菜单的menu隐藏掉箭头***/
    function hiddenMenuArrow($symptom_all) {
        var $wrap_contents = $symptom_all.find("div.wrap-content"),
            $as = $wrap_contents.find("ul.first-level").find("a");
        $as.each(function () {
            var $a = $(this);
            if ($a.siblings("ul").length === 0) {
                $a.children("i").css("visibility", "hidden");
            }
        });
    }

    /*** 给menu的一级菜单也添加显示的数据 ***/
    function addMenuFirstContent($symptom_all) {
        var $menus = $symptom_all.find("div.menu"),
            $as = $menus.children("ul").children("li").children("a");
        $as.each(function () {
            var $a = $(this),
                $wrap_content = $a.parent().parent().parent().parent(),
                $second_as = $a.siblings("ul").find("a"),
                content_html = "<div class='content d-n' data-portraitid='" + $a.attr("data-id") + "'>";
            if ($second_as.length > 0) {
                $second_as.each(function () {
                    var $second_a = $(this);
                    content_html += '<p class="title">' + $second_a.text() + '</p>';
                    content_html += $wrap_content.find("div.content[data-portraitid=" + $second_a.attr("data-id") + "]").html();
                });
                content_html += '</div>';
                $wrap_content.append(content_html);
            }
        });

    }

    /*** 因为数据原因 关联数据添加的位置不正确 需要订正 ***/
    function resetRelationData($symptom_all, notIds) {
        var $wrap_contents = $symptom_all.find("div.wrap-content"),
            $contents = $wrap_contents.find("div.content"),
            index = $symptom_all.parent().parent().index();
        $contents.each(function () {
            var $content = $(this),
                $symptoms = $content.find("p.symptom");
            $content.attr("data-portraitid", $symptoms.eq(0).attr('data-portraitid'));
            $symptoms.removeAttr("data-portraitid");
        });

        addSymptomFocus($symptom_all);
        //修正常用的显示的症状
        /*  $.ajax({
         url:Param.hostUrl+"/kl/subitem/init_subiteminfo",
         async:true,
         data:{
         doctorId:1,
         size:20,
         sexType:1,
         deptNo:001,
         hospitalCode:1001,
         age:20,
         type:index===0?1:index,
         notIds:notIds
         },
         dataType:"json",
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
         });*/
    }


    /*** 给显示全部症状信息列表添加事件 ***/
    function addSymptomAllEvent($symptom_all) {
        var $title = $symptom_all.children("div.title"),
            $arrow = $title.children("div.arrow"),
            $buttons = $title.find("button"),
            $wrap_contents = $symptom_all.find("div.wrap-content"),
            $menus = $wrap_contents.find("div.menu");

        $buttons.on("click.main", function () {
            var $button = $(this);
            if ($button.hasClass('disabled')) {
                return;
            }
            if (!$button.hasClass("focus")) {
                $wrap_contents.hide().filter("[data-relationid=" + $button.attr("data-id") + "]").fadeIn();
                $button.addClass("focus").siblings("button").removeClass("focus");
            }
        });

        $buttons.one("click.one", function () {
            var $button = $(this);
            $wrap_contents.filter("[data-relationid=" + $button.attr("data-id") + "]").find("div.menu").find("a:first").click();
        });

        $menus.on("click.main", "a", function (e) {
            var $a = $(e.target);
            menuClick($a);
        });

        $arrow.on("click.main", function () {
            symptomAllUp($arrow);
        });

        $wrap_contents.on("click.main", "p", function (e) {
            var $p = $(e.target),
                $symptom_container = $symptom_all.siblings("div.symptom-container"),
                $choose = $symptom_container.siblings("div.choose");
            if ($p.hasClass('title')) {
                return;
            }
            common.cancelReturnLastStep($choose.filter(":last"));
            if ($symptom_container.find("div.symptom-form[data-relation-id=" + $p.attr("data-id") + "]").length === 0) {
                addSymptomFormAjax($symptom_container, $p.attr("data-id"), $p.attr("data-symptom-name"), $p.attr("data-transCode"), $p.attr("data-standard-id"));
            }
            $arrow.click();
            showSymptomForm($symptom_container.find("div.symptom-form[data-relation-id=" + $p.attr("data-id") + "]"));
        });
        function symptomAllUp($arrow) {
            $arrow.parent().parent().slideUp();
            $symptom_all.siblings("div.symptom-container").slideDown();
        }

        function menuClick($a) {
            var $siblings_ul = $a.siblings("ul"),
                $menu = $a.parents("div.menu:first"),
                $contents = $menu.siblings("div.content"),
                $content,
                $a_siblings;
            if ($siblings_ul.length > 0) {
                menuSlide($a, $siblings_ul);
                $a_siblings = $a.parent().siblings("li").children("a");
                $a_siblings.each(function () {
                    var $a = $(this);
                    menuSlideUp($a, $a.siblings("ul"));
                });
            } else {
                changeMenuFocus($a, $menu);
            }
            $content = changeContent($a.attr("data-id"), $contents);
            changeMenuHeight($menu, $content);
        }

        function changeMenuHeight($menu) {
            var $wrap_content = $menu.parent(),
                menu_height = $menu.height(),
                wrap_height = $wrap_content.height();
            if (menu_height < wrap_height) {
                $menu.css("height", wrap_height);
            } else if (menu_height > wrap_height) {
                $menu.removeAttr("style");
            }
        }

        function changeMenuFocus($a, $menu) {
            if (!$a.hasClass("focus")) {
                $menu.find("a.focus").removeClass("focus");
                $a.addClass("focus");
            }
        }

        function changeContent(data_id, $contents) {
            return $contents.hide().filter("[data-portraitid=" + data_id + "]").fadeIn();
        }

        function menuSlide($a, $siblings_ul) {
            if ($siblings_ul.attr("data-drop") === "true") {
                menuSlideUp($a, $siblings_ul);
            } else {
                $siblings_ul.slideDown();
                $siblings_ul.attr("data-drop", "true");
                $a.children("i").removeClass("icon-right-triangle").addClass("icon-triangle-down-copy");
            }
        }

        function menuSlideUp($a, $siblings_ul) {
            $siblings_ul.slideUp();
            $siblings_ul.attr("data-drop", "false");
            $a.children("i").addClass("icon-right-triangle").removeClass("icon-triangle-down-copy");
        }

        $buttons.eq(0).click();
    }


    /*** choose和input的click事件 ***/
    function chooseAndInputClickEvent($ps, $group, $symptom_all, $symptom_container, is_change) {
        cleanCache($group.index());
        var val = {};
        val.inputstr = "";
        val.notIds = "";
        $ps.each(function () {
            var $p = $(this);
            val.notIds += "," + $p.attr("data-relation-id");
        });
        val.notIds =String(val.notIds).slice(1);
        if($group.children("div.box-r-s").children("input").length>0){
            val.inputstr = ($group.children("div.box-r-s").children("input").val()+"").trim();
        }
        $group.addClass("focus");
        if (!is_change) {
            if ($symptom_container.children("div.symptom-wrap").is(":visible") && !$symptom_container.hasClass("symptom") && $symptom_container.is(":visible")) {
                if($symptom_all.is(":visible")){
                    $symptom_all.slideUp();
                }else{
                    $symptom_container.fadeOut();
                }
                return;
            }
        }

        $symptom_container.slideDown();
        // if ($group.data("inputstr") !== val.inputstr || $group.data("notIds") !== val.notIds) {
        $group.data("inputstr", val.inputstr);
        $group.data("notIds", val.notIds);

        // }
        if ($symptom_container.children("div.symptom-form:visible").length > 0) {
            $symptom_container.children("div.symptom-form:visible").fadeOut(function () {
                showSymptomWrap($symptom_container);
                initSubItemInfo($group);
            });
        } else {
            initSubItemInfo($group);
        }


        common.groupHide($group);

    }

    /*** 给动态表单在副本域 增加副本 没修改点击取消时 从副本域拿来表单把修改的表单替换掉 ***/
    function symptomFormCopyToDomain($symptom_form) {
        var $domain=$('#symptom-form-copy-domain'),
            $symptom_form_copy=$domain.find('.symptom-form[data-relation-id='+$symptom_form.attr('data-relation-id')+']');
        if($symptom_form_copy.length>0){
            $symptom_form_copy.remove();
        }
        $domain.append($symptom_form.clone(true)).find('.symptom-form[data-relation-id='+$symptom_form.attr('data-relation-id')+']').hide();
    }

    function addEvent() {
        $groups.each(function (i) {
            if (i <= 4) {
                var $group = $(this),
                    $input = $group.children("div.box-r-s").children("input"),
                    $symptom_wrap = $group.find("div.symptom-wrap"),
                    $symptom_container = $group.find("div.symptom-container:first"),
                    $choose = $group.find("div.choose");
                $group.data("inputstr", null);
                $group.data("notIds", null);
                $group.data("notCodes", null);
                $group.on("click.main", function (e) {
                    common.groupHide($group);
                    return false;
                });

                $choose.on("click.never-search", function (e) {
                    var $choose = $(this),
                        $ps = $choose.find("p"),
                        $symptom_container = $choose.siblings("div.symptom-container:first"),
                        $symptom_all = $input.siblings("div.symptom-all");
                    chooseAndInputClickEvent($ps, $group, $symptom_all, $symptom_container, false);
                    common.autoScrollY($("body"), $choose.parents("div.group"), -170);
                    e.stopPropagation();
                });


                $input.on("click", function () {
                    var $input = $(this),
                        $symptom_container = $input.siblings("div.symptom-container"),
                        $ps = $symptom_container.siblings("div.choose").find("p"),
                        $symptom_all = $input.siblings("div.symptom-all");
                    chooseAndInputClickEvent($ps, $group, $symptom_all, $symptom_container, false);
                    common.autoScrollY($("body"), $input.parents("div.group"), -170);
                });

                $input.on("input propertychange", function () {
                    var $input = $(this),
                        $symptom_container = $input.siblings("div.symptom-container"),
                        $ps = $symptom_container.siblings("div.choose").find("p"),
                        $symptom_all = $input.siblings("div.symptom-all");
                    chooseAndInputClickEvent($ps, $group, $symptom_all, $symptom_container, true);
                });

                if (i === 0 || i === 1) {
                    $input.parent().dropInput(function () {
                    });
                }

                $symptom_wrap.on("click.main", "div", function (e) {
                    var $symptom = $(e.target).hasClass("symptom") ? $(e.target) : $(e.target).parents("div.symptom:first"),
                        symptom_name = $symptom.attr("data-symptom-name");
                    if ($symptom_container.find("div.symptom-form[data-relation-id=" + $symptom.attr("data-id") + "]").length === 0) {
                        addSymptomFormAjax($symptom_container, $symptom.attr("data-id"), symptom_name, $symptom.attr("data-transCode"), $symptom.attr("data-standard-id"), $symptom.attr("data-param-code"));
                    }
                    showSymptomForm($symptom_container.find("div.symptom-form[data-relation-id=" + $symptom.attr("data-id") + "]"));
                    clearInput($input);
                    common.cancelReturnLastStep($symptom_container.siblings("div.choose"));
                    e.stopPropagation();
                });

                $choose.on("click.main", 'span,i', function (e) {
                    var $node = $(e.target),
                        $choose = $node.parents("div.choose:first"),
                        i_index,
                        $group = $choose.parent().parent(),
                        group_index = $group.index(),
                        $symptom_form,
                        $symptom_container = $choose.siblings("div.symptom-container"),
                        $p = $node.parent(),
                        id = $p.attr("data-relation-id");

                    $group.addClass("focus");
                    if ($node.prop("nodeName") === "SPAN" || $node.prop("nodeName") === "P") {
                        $choose.data("lastStep", common.cancelReturnLastStep($choose));
                        $symptom_form = $symptom_container.find("div.symptom-form[data-relation-id=" + $p.attr("data-relation-id") + "]");
                        symptomFormCopyToDomain($symptom_form);
                        showSymptomForm($symptom_form);
                        if (!$choose.parent().parent().hasClass("focus")) {
                            $choose.click();
                        }
                        common.autoScrollY($("body"), $group, -170);
                    } else if ($node.prop("nodeName") === "I") {
                        i_index = $node.index();
                        $symptom_form = $symptom_container.find("div.symptom-form[data-relation-id=" + $p.attr("data-relation-id") + "]");
                        if (i_index === 2) {
                            presentIllnessUp($group, $symptom_form, $p);
                            $choose.siblings("input").click();
                        } else {
                            if (removeChooseElse($p)) {
                                common.groupHide($group);
                                return;
                            }

                            if (group_index === 0) {
                                showDeleteAll($p);
                            } else {
                                removeSymptomChoose($group, $p, $choose);
                                common.addChooseBottom($choose);
                                cleanCacheByType($group);
                            }
                            $symptom_container.siblings("div.symptom-all").find("p.symptom[data-id=" + id + "]").removeClass("focus");
                            if ($symptom_container.children("div.symptom-wrap").is(":visible")) {
                                chooseAndInputClickEvent($symptom_container.siblings("div.choose").find("p"), $group, $symptom_container.siblings("div.symptom-all"), $symptom_container, true);
                            }
                        }
                        e.stopPropagation();
                    }
                    e.stopPropagation();
                    common.groupHide($group);
                });


                function removeChooseElse($p) {
                    var trans_code,
                        $else_p;
                    if ($p.attr("data-relation-id") === "-1") {
                        trans_code = $p.attr("data-transCode");
                        $else_p = $p.parent().siblings("div.symptom-container").children("div.symptom-else").find("p[data-transCode=" + trans_code + "]");
                        $else_p.removeClass("focus");
                        $else_p.siblings("p.all").removeClass("focus");
                        if (trans_code === "all") {
                            $else_p.siblings("p").removeClass("focus");
                        }
                        $p.remove();
                        return true;
                    }
                    return false;
                }


                // 主诉只有一条,现病史有多条,当删除主诉时,现病史排序第一的自动设置为主诉
                function addFirstMainSuit($group, $choose, data_id) {
                    var $group_next = $group.next(),
                        $choose_next = $group_next.find("div.choose"),
                        $ps = $choose_next.children("p").not("[data-relation-id=" + data_id + "]");
                    if ($choose.children().length === 0) {
                        $ps.each(function () {
                            var $p = $(this),
                                $i = $p.children("i:first");
                            if (!$i.hasClass("d-n")) {
                                $i.click();
                                return false;
                            }
                        });
                    }
                }

                // 将现病史加入到主诉
                function presentIllnessUp($group, $symptom_form, $p) {
                    var $symptom_container = $group.prev().find("div.symptom-container"),
                        _$symptom_form,
                        choose_arr = [],
                        $choose = $symptom_form.parent().siblings('.choose:first'),
                        $ps = $choose.find('p');
                    $symptom_form.children("div.title").find("div.checkbox").addClass("d-n");

                    $ps.each(function () {
                        choose_arr.push($(this).attr('data-relation-id'));
                    });

                    _$symptom_form = $symptom_container.append($symptom_form.clone(true)).find("div.symptom-form[data-relation-id=" + $p.attr("data-relation-id") + "]");
                    _$symptom_form.find("button.confirm").click();
                    $ps = $choose.find('p');
                    // 暂时进行一次重排序处理
                    $.each(choose_arr, function (i, v) {
                        $choose.children('div.content').append($ps.filter('[data-relation-id=' + v + ']'));
                    });
                    _$symptom_form.hide().parent().hide();
                }

                //删除填写的症状和对应的动态表单
                function removeSymptomChoose($group, $p, $choose) {
                    var notIds = "";
                    $group.find("div.symptom-form[data-relation-id=" + $p.attr("data-relation-id") + "]").remove();
                    $group.find("div.symptom-else p[data-relation-id=" + $p.attr("data-relation-id") + "]").removeClass("focus").siblings("p.symptom.all").removeClass("focus");
                    $p.remove();
                    if (hideChoose($choose)) {
                        $choose.siblings("input").removeClass("d-n");
                    }
                    $choose.add($choose.siblings(".choose")).find("p").each(function () {
                        notIds += "," + $(this).attr("data-relation-id");
                    });
                    $group.data("notIds", notIds.slice(1));
                }
            }
        });


    }

    //当没有选择症状时隐藏choose 因为choose的border是有高度的 不隐藏可以看见细微的不和谐
    function hideChoose($choose) {
        if ($choose.find("p").length === 0) {
            $choose.addClass("d-n");
            if ($choose.closest("div.group").index() === 0) {
                return true;
            }
        }
        return false;
    }

    //给对应的症状加上已经选择的焦点
    function addSymptomFocus($symptom_all) {
        var $ps = $symptom_all.siblings("div.choose").find("p");
        $symptom_all.find("p.symptom.focus").removeClass("focus");
        $ps.each(function () {
            var $p = $(this);
            $symptom_all.find("p.symptom[data-id=" + $p.attr("data-relation-id") + "]").addClass("focus");
        });
    }

    /*** 关键字（拼音/中文）输入选择后,自动清空输入框 ***/
    function clearInput($input) {
        $input.val("");
    }


    /*** 增加动态表单的函数 ***/
    function addSymptomFormAjax($symptom_container, data_id, symptom_name, data_transCode, data_standard_id, data_param_code) {
        var param={
            subitemId: data_id
        };
        $.ajax({
            url: Param.hostUrl + "/kl/questioninfo/get_questioninfo_by_subitemid",
            async: true,
            data: param,
            dataType: "json",
            type: "post",
            success: function (data) {
                addSymptomForm($symptom_container, data.data, data_id, symptom_name, data_transCode, data_standard_id, data_param_code);
                showSymptomForm($symptom_container.find("div.symptom-form[data-relation-id=" + data_id + "]"));
            },
            error: function (req, info, err) {
                alert("加载症状列表失败");
            }
        });
    }


    function addSymptomForm($symptom_container, data, data_id, symptom_name, data_transCode, data_standard_id, data_param_code) {
        var DynamicForm = getTmpl("DynamicForm"),
            dynamicFormTmpl = new DynamicForm(data_id, data_transCode, data_standard_id, data_param_code),
            index = $symptom_container.parents("div.group:first").index();
        dynamicFormTmpl.setTitle(symptom_name, data_transCode);
        dynamicFormTmpl.setTitleRight(index);
        $.each(data, function (i1, v1) {
            dynamicFormTmpl.addLabel(v1.id, v1.name, v1.questionContentList[0].type, v1.addLine, v1.code, v1.require);
            $.each(v1.questionContentList, function (i2, v2) {
                dynamicFormTmpl.addControl(v2);
            });
            dynamicFormTmpl.addFormGroupFinish();
        });
        $symptom_container.append(dynamicFormTmpl.getDynamicForm(index));
        var $symptom_form = $symptom_container.find("div.symptom-form[data-relation-id=" + data_id + "]");
        common.addDateControlTime($symptom_form.find("div.date-toggle"));
        addLine($symptom_form);
        hideRelationFormGroup($symptom_form);
        addValidateText($symptom_form);
        common.symptomFormAddEvent($symptom_form);
        if (index === 2 || index === 3 || index === 4) {
            $symptom_form.find("div.date-toggle").siblings("label").children("span").addClass('d-n');
            $symptom_form.find("div.date-toggle").siblings("div.date:first").children("div.select").children("input").val("年");
        }
        addDateControlValidateEvent($symptom_form.find("div.date-toggle"));
        if (index === 3) {
            if (data_transCode === "SJFL003003") {
                hideDateControlOne($symptom_form.find("div.form-group[data-type=7]"), 1);//隐藏月经史时间控件能切换的功能
                addSelectByTextSign($symptom_form.find("div.form-group[data-id=171]:first"));//痛经
                addFormatTextOrderSign($symptom_form.find("div.form-group[data-id=171]:first input"), "last")//痛经
            } else if (data_transCode === "SJFL003001") {
                addPlaceholder($symptom_form.find("input"), "其他");
            } else if (data_transCode === "SJFL003004") {
                addPlaceholder($symptom_form.find("input[name=question_354]"), "疾病备注...");

            }
        }

        if (index === 4) {
            if (data_id === "1497") {
                $symptom_form.find("div.form-group").children("input").attr("type", "number");
                $symptom_form.find("div.form-group[data-id=228]").children("input").on("input.relationVal propertychange.relationVal", calculatePulse);
                $symptom_form.find("div.form-group[data-id=229]").children("input").on("input.relationVal propertychange.relationVal", calculatePulse);
                $symptom_form.find("div.form-group[data-id=230]").children("input").attr("readonly",'readonly');
            }
        }
        deleteNullLabel($symptom_form);
        addInformationModalEvent($symptom_form.children('div.title:first').children('i'));
        common.dateControlRelation($symptom_form.find("div.date-toggle"));
        // common.dateControlDefault($symptom_form.find("div.date-toggle"));
        //
        // // FIXME: 通过触发input控件的input事件和propertychange事件无法改变确定按钮的状态，原因暂时未知，
        // // FIXME: 先直接移除disabled处理
        // if(index===0 || index===1){
        //     $symptom_form.find('button.confirm').removeClass('disabled');
        // }
    }


    /*** 给体征血压BP增加自动计算脉压差功能 ***/
    function calculatePulse() {
        var $symptom_form = $(this).parents("div.symptom-form:first"),
            $input_SBP = $symptom_form.find("div.form-group[data-id=228]").children("input"),
            $input_DSP = $symptom_form.find("div.form-group[data-id=229]").children("input"),
            $input_DP = $symptom_form.find("div.form-group[data-id=230]").children("input"),
            val_SBP = parseInt($input_SBP.val()),
            val_DSP = parseInt($input_DSP.val());

        if (val_SBP && val_DSP && (val_DSP - val_SBP) > 0) {
            $input_DP.val(val_DSP - val_SBP);
        } else {
            $input_DP.val('');
        }
    }


    /*** 给input添加placeholder属性***/
    function addPlaceholder($input, placeholder) {
        $input.attr("placeholder", placeholder);
    }

    /*** 给单选下拉文本框加上点击选择后上级的文本也需要添加的标识 ***/
    function addSelectByTextSign($form_group) {
        $form_group.attr("data-select", "all");
    }

    /*** 需要添加格式化数据时input中的文字到底是放在哪的标识 ***/
    function addFormatTextOrderSign($input, order) {
        $input.attr("data-order", order);
    }


    /*** 让date控件不能切换 只使用其中一种 ***/
    function hideDateControlOne($date_group, span_index) {
        var $date_toggle = $date_group.find("div.date-toggle");
        $date_toggle.children("span").eq(span_index).click();
        $date_toggle.addClass("d-n");
    }


    /*** 将label为空格标识的控件的标题移除 ***/
    function deleteNullLabel($symptom_form) {
        var $form_groups = $symptom_form.find("div.form-group").not("[data-relation=y]");
        $form_groups.each(function () {
            var $label = $(this).children("label");
            if ($label.html() === "&nbsp;") {
                $label.remove();
            }
        });

    }


    /*** 给表单里需要换行的form-group换行 ***/
    function addLine($symptom_form) {
        var $form_groups = $symptom_form.find("div.form-group[data-addLine=1]");
        $form_groups.each(function () {
            $(this).after('<br>');
        });
        $form_groups.removeAttr("data-addLine");
    }


    /*** 给时间控件添加校验事件 ***/
    function addDateControlValidateEvent($date_toggle) {
        $date_toggle.off("click.validate");
        var $div = $date_toggle.siblings("div").not("div.validate");
        $div.find("ul").on("click.validate", "a", function (e) {
            var $a = $(e.target),
                $div = $a.parents("div.select:first").parent(),
                $input = $div.find("input:first"),
                $validate = $div.siblings("div.validate"),
                $button = $div.parents("div.form-group:first").siblings("div.button").children("button.confirm");
            validate($input, $validate, $button);
        });

        $div.find("input").on("input propertychange", function () {
            var $date = $(this).parents("div.date:first"),
                $input = $date.find("input:first"),
                $validate = $date.siblings("div.validate"),
                $button = $date.parents("div.form-group:first").siblings("div.button").children("button.confirm");
            validate($input, $validate, $button);
        });


        function validate($input, $validate) {
            if(!$input.closest('.form-group').is('[data-require=1]')){
                return;
            }

            var flag = true,
                val = parseInt($input.val().trim());
            if (val !== val || val <= 0) {
                flag = false;
                $input.val("");
            }
            if (flag) {
                $validate.addClass("d-n");
            } else {
                $validate.removeClass("d-n");
            }
        }
    }

    /*** 隐藏掉那些被关联才会显示的控件 ***/
    function hideRelationFormGroup($symptom_form) {
        $symptom_form.children("div.form-group").each(function () {
            var $form_group = $(this),
                $form_groups = $form_group.children().siblings("div.form-group").hide();
            $form_group.after($form_groups.attr("data-relation", "y"));
        });
    }

    /*** 显示动态表单的函数 ***/
    function showSymptomForm($symptom_form) {
        $symptom_form.parent().show().addClass("symptom");
        $symptom_form.slideDown().siblings("div.symptom-form").hide();
        $symptom_form.parent().addClass("symptom");
        $symptom_form.parent().siblings("div.symptom-all").fadeOut();
        //hideSymptomElse($symptom_form.siblings("div.symptom-else"));
    }

    /*** 隐藏symptom-else ***/
    function hideSymptomElse($symptom_else) {
        $symptom_else.addClass("d-n");
    }

    /*** 显示symptom-else ***/
    function showSymptomElse($symptom_else) {
        $symptom_else.removeClass("d-n");
    }


    function showSymptomWrap($symptom_container) {
        if ($symptom_container) {
            $symptom_container.removeClass("symptom");
        } else {
            $groups.find("div.symptom-container").removeClass("symptom");
        }
    }

    function reLoadSubItemInfo(data, $symptom_wrap) {
        var html = "",
            SubItemTmpl = getTmpl("SubItemTmpl"),
            subItemTmpl = new SubItemTmpl();
        $.each(data, function (i, v) {
            subItemTmpl.setDataId(v.id);
            subItemTmpl.setTransCode(v.transCode);
            subItemTmpl.setStandardId(v.standardId);
            subItemTmpl.setParamCode(v.paramCode);
            subItemTmpl.setName(v.name, v.subName);
            html += subItemTmpl.getSymptom($symptom_wrap.parents('div.group:first').index());
        });
        html += '<div class="container-shade" style="left: 0"></div><div class="container-shade" style="right: 0"></div>';
        $symptom_wrap.html(html);
        $symptom_wrap.children(':first').addClass('hover');
        common.changeContainerShadowWidth($symptom_wrap.find("div.container-shade"));
        addInformationModalEvent($symptom_wrap.find('i'));
        addInformationHoverEvent($symptom_wrap.find('i').filter(function () {
            return $(this).children().length > 0;
        }));
    }

    /*** 反序遍历 ***/
    function reverseEach($node, fn) {
        for (var i = $node.length - 1; i >= 0; i--) {
            fn(i, $node[i]);
        }
    }

    /*** 增加既往史和其他史推送下面的无 ***/
    function addSymptomElse() {
        var $pre_symptom_container = $previous_history.find("div.symptom-container"),
            $else_symptom_container = $else_history.find("div.symptom-container");
        $pre_symptom_container.append('<div class="symptom-else"><p class="symptom" data-id="-1" data-transCode="SJFL002001">疾病史无殊</p><p class="symptom" data-transCode="SJFL002002" data-id="-1">传染病史无殊</p><p class="symptom " data-transCode="waishangshi" data-id="-1">手术外伤史无殊</p><p class="symptom " data-transCode="SJFL002003" data-id="-1">过敏史无殊</p><p class="symptom " data-transCode="shuxueshi" data-id="-1">输血史无殊</p><p class="symptom " data-transCode="SJFL002004" data-id="-1" style="border-right: 1px solid #e5e5e5;">接种史无殊</p><p class="symptom all box-r-s d-n" data-id="-1" data-transCode="all">全部</p></div>');
        $else_symptom_container.append('<div class="symptom-else"><p class="symptom " data-transCode="SJFL003001" data-id="-1" style="border-right: 1px solid #e5e5e5;">家族史无殊</p></div>');
        //<p class="symptom d-n" data-transCode="SJFL003003" data-id="-1">月经史</p> <p class="symptom d-n" data-transCode="SJFL003004" data-id="-1">婚育史</p></div>
        //<div class="c-r checkbox"><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>无殊</div>
        //<div class="c-r checkbox"><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>无殊</div>
        //
        addSymptomElseEvent($pre_symptom_container.children("div.symptom-else"));
        addSymptomElseEvent($else_symptom_container.children("div.symptom-else"));
    }

    //TODO
    // /*** 重写增加既往史和其他史推送下面的无中各史选择的事件 ***/
    // function addSymptomElseEvent($symptom_else) {
    //     // 改变notCodes的函数
    //     function changeNotCodes($group) {
    //         var $choose = $group.find("div.choose"),
    //             $ps = $choose.find("p[data-relation-id=-1]"),
    //             $all_p = $ps.filter("[data-transCode=all]"),
    //             $symptom_else = $group.find("div.symptom-else"),
    //             $else_ps,
    //             notCodes = "";
    //         if ($all_p.length > 0) {
    //             $else_ps = $symptom_else.find("p.symptom").not("[data-transCode=all]");
    //             $else_ps.each(function () {
    //                 var $p = $(this);
    //                 notCodes += ',' + $p.attr("data-transCode");
    //             });
    //
    //         } else {
    //             $ps.each(function () {
    //                 var $p = $(this);
    //                 notCodes += ',' + $p.attr("data-transCode");
    //             });
    //         }
    //         $group.data("notCodes", notCodes.slice(1));
    //         initSubItemInfo($group);
    //     }
    //
    //     //生成选择的症状的文本的函数
    //     function generatePHtml(data_id, text, transCode) {
    //         return '<p data-relation-id="' + data_id + '" data-transCode="' + transCode + '"><span class="dot"></span><span>' + text + '</span><i class="d-n"></i><i></i></p>';
    //     }
    //
    //
    //     //清理对应疾病的symptom_form
    //     function clearSymptomForm($symptom_container,transCode) {
    //         $symptom_container.find('div.symptom-form[data-transcode='+transCode+']').not('.disabled').remove();
    //     }
    //     // 增加选择的else到choose的函数
    //     function addElseChoose($choose,$else_p) {
    //         var $else_p_siblings=$else_p.siblings('p.symptom'),
    //             $symptom_forms=$choose.siblings('.symptom-container').children('.symptom-form'),
    //             $group=$choose.closest('.group'),
    //             notIds_arr=$group.data('notIds').split(","),
    //             $content=$choose.filter('[data-transcode='+$else_p.attr('data-transcode')+']').children('div.content');
    //         $else_p.addClass('focus');
    //         $content.children().each(function () {
    //            var $p=$(this),
    //                data_relation_id=$p.attr('data-relation-id'),
    //                $symptom_form=$symptom_forms.filter("[data-transcode="+$p.attr('data-transcode')+"][data-relation-id="+data_relation_id+"]");
    //            if(!$symptom_form.hasClass('disabled')){
    //                $.each(notIds_arr,function (i,v) {
    //                    if(v===data_relation_id){
    //                        notIds_arr.splice(i,1);
    //                        return false;
    //                    }
    //                })
    //                $p.remove();
    //                $group.data("notIds",notIds_arr.join(","));
    //            }
    //         }).end().prepend(generatePHtml("-1",$else_p.text(),$else_p.attr('data-transcode'))).end().removeClass('d-n');
    //         if ($else_p_siblings.length === $else_p_siblings.filter('.focus').length) {
    //             $choose.filter('[data-transCode]').addClass('d-n').end().filter(':last').children("div.content").append(generatePHtml("-1","既往史无殊","all")).find('p[data-transCode=all]').children('i:last')
    //                 .one('click',function () {
    //
    //                 var $all_choose=$(this).closest('.choose');
    //                 $all_choose.addClass('d-n').children('div.content').empty();
    //                 $else_p.add($else_p.siblings()).removeClass('focus');
    //                 $choose.children("div.content").empty();
    //                 changeNotCodes($choose.closest('.group'));
    //                 return false;
    //             })
    //                 .end().end().end().removeClass('d-n');
    //         }
    //         clearSymptomForm($choose.siblings('.symptom-container'),$else_p.attr('data-transcode'));
    //     }
    //     // 移除选择的else到choose的函数
    //     function removeElseChoose($choose_p) {
    //         var $p_choose = $choose_p.closest(".choose"),
    //             $else_p=$p_choose.siblings('.symptom-container').children('.symptom-else').find('p[data-transcode='+$choose_p.attr('data-transcode')+']'),
    //             $else_p_siblings=$else_p.siblings('p.symptom');
    //         if($else_p_siblings.length === $else_p_siblings.filter('.focus').length){
    //             $p_choose.siblings('.choose:last').children('div.content').empty().end().addClass('d-n');
    //             $p_choose.siblings('[data-transCode]').removeClass('d-n');
    //         }
    //         $else_p.removeClass("focus");
    //         $choose_p.remove();
    //         hideChoose($p_choose);
    //     }
    //
    //     var $ps = $symptom_else.find("p"),
    //         $all_p = $ps.filter(".all"),
    //         $else_ps = $ps.not($all_p),
    //         $choose = $symptom_else.parent().siblings("div.choose");
    //
    //     $else_ps.on('click.changeNotCodes', function () {
    //         var $p = $(this),
    //             $symptom_container = $choose.siblings("div.symptom-container"),
    //             $p_choose,
    //             $choose_p,
    //             $p_siblings = $p.siblings('p.symptom');
    //
    //         if ($p.hasClass('focus')) {
    //             $choose_p=$choose.find('[data-transcode='+$p.attr('data-transcode')+']');
    //             $p_choose=$choose_p.closest('div.choose');
    //             if($p_choose.children('.content').children().length===1){
    //                 $p_choose.addClass('d-n');
    //             }else{
    //
    //             }
    //             removeElseChoose($choose_p);
    //         }else {
    //             $p_choose = $choose.filter('p[data-transcode=' + $p.attr('data-transcode') + ']');
    //             addElseChoose($choose, $p);
    //             $choose_p = $choose.find('[data-transcode=' + $p.attr('data-transcode') + '][data-relation-id=-1]');
    //             $choose_p.children("i:last").one("click.changeNotCodes", function () {
    //                 var $p_choose=$choose_p.closest('.choose')
    //                 removeElseChoose($choose_p);
    //                 changeNotCodes($symptom_container.closest('div.group'));
    //                 common.addChooseBottom($choose);
    //                 return false;
    //             });
    //         }
    //         changeNotCodes($symptom_container.closest('div.group'));
    //         common.addChooseBottom($choose);
    //     });
    // }


    /*** 增加既往史和其他史推送下面的无中各史选择的事件 ***/
    function addSymptomElseEvent($symptom_else) {

        //选择了和删除无殊后对推送的过滤
        function addNotCodes($group) {
            var $choose = $group.find("div.choose"),
                $ps = $choose.find("p[data-relation-id=-1]"),
                $all_p = $ps.filter("[data-transCode=all]"),
                $symptom_else = $group.find("div.symptom-else"),
                $else_ps,
                notCodes = "";
            if ($all_p.length > 0) {
                $else_ps = $symptom_else.find("p.symptom").not("[data-transCode=all]");
                $else_ps.each(function () {
                    var $p = $(this);
                    notCodes += ',' + $p.attr("data-transCode");
                });

            } else {
                $ps.each(function () {
                    var $p = $(this);
                    notCodes += ',' + $p.attr("data-transCode");
                });
            }
            $group.data("notCodes", notCodes.slice(1));
            initSubItemInfo($group);
        }


        var $checkbox = $symptom_else.find("div.checkbox"),
            $ps = $symptom_else.find("p"),
            $all_p = $ps.filter(".all"),
            $else_ps = $ps.not($all_p),
            $choose = $symptom_else.parent().siblings("div.choose"),
            $input=$choose.siblings('input');
        // $checkbox.on("click.main", function () {
        //     var $checkbox = $(this),
        //         $ps = $checkbox.siblings("p"),
        //         $all_p = $ps.filter(".all"),
        //         $else_ps = $ps.not($all_p),
        //         $choose = $symptom_else.parent().siblings("div.choose");
        //     if ($checkbox.hasClass("checked")) {
        //         $checkbox.removeClass("checked");
        //         $ps.addClass("d-n");
        //         $choose.find("p[data-relation-id=-1]").each(function () {
        //             $(this).children("i:last").click();
        //         });
        //         $else_ps.removeClass("focus");
        //         $all_p.removeClass("focus");
        //     } else {
        //         $checkbox.addClass("checked");
        //         $ps.removeClass("d-n");
        //     }
        //     common.addChooseBottom($choose);
        // });

        $else_ps.on("click.main", function (e) {
            var $p = $(this),
                flag = true,
                $symptom_container = $choose.siblings("div.symptom-container"),
                $choose_p,
                _$choose;

            _$choose = $choose.filter("[data-transCode=" + $p.attr("data-transCode") + "]");
            e.stopPropagation();
            if ($p.hasClass("focus")) {
                _$choose.find("p[data-relation-id=-1][data-transCode=" + $p.attr("data-transCode") + "]").children("i:last").click();
                if ($choose.find("p[data-relation-id=-1][data-transCode=all]").length > 0) {
                    $all_p.click();
                    $else_ps.not($p).click();
                }
                common.addChooseBottom(_$choose);
                return;
            }
            if (_$choose.find("p[data-relation-id=-1][data-transCode=" + $p.attr("data-transCode") + "]").length === 0) {
                $symptom_container.find("div.symptom-form[data-transCode=" + $p.attr("data-transCode") + "]").not(".disabled").each(function () {
                    _$choose.find("p[data-relation-id=" + $(this).attr("data-relation-id") + "]").children("i:last").click();
                });
                _$choose.children("div.content").append(generatePHtml($p.attr("data-id"), "无殊", $p.attr("data-transCode")));
            }

            _$choose.removeClass("d-n");
            $p.addClass("focus");
            $else_ps.each(function () {
                if (!$(this).hasClass("focus")) {
                    flag = false;
                    return false;
                }
            });
            if (flag) {
                $all_p.click();
            } else {
                $all_p.removeClass("focus");
                $choose.find("p[data-transCode=all]").remove();
            }
            $choose_p = _$choose.find("p[data-relation-id=-1][data-transCode=" + $p.attr("data-transCode") + "]");
            addNotCodes($symptom_container.parent().parent());
            $choose_p.children("i:last").one("click.addNotCodes", function () {
                var $choose = $choose_p.parents("div.choose:first");
                $choose_p.remove();
                $p.removeClass("focus");
                addNotCodes($symptom_container.parent().parent());
                hideChoose($choose);
                common.addChooseBottom(_$choose);
                return false;
            });
            common.addChooseBottom(_$choose);
            $input.focus();
        });


        $all_p.on("click.main", function (e) {
            var $symptom_container = $choose.siblings("div.symptom-container"),
                $choose_p,
                _$choose;
            e.stopPropagation();
            _$choose = $choose.not("[data-transCode]");
            if ($all_p.hasClass("focus")) {
                _$choose.find("p[data-relation-id=-1][data-transCode=all]").children("i:last").click();
                common.addChooseBottom(_$choose);
                return;
            }
            _$choose.removeClass("d-n");
            if (!$all_p.hasClass("focus")) {
                $symptom_container.children("div.symptom-form").not(".disabled").each(function () {
                    $choose.find("p[data-relation-id=" + $(this).attr("data-relation-id") + "]").children("i:last").click();
                });
                $else_ps.each(function () {
                    var $p = _$choose.siblings("div.choose").find("p[data-relation-id=-1][data-transCode=" + $(this).attr("data-transCode") + "]"),
                        $else_p_choose = $p.parents("div.choose:first");
                    $p.remove();
                    hideChoose($else_p_choose);
                });
                $else_ps.addClass("focus");
                $all_p.addClass("focus");
                _$choose.children("div.content").append(generatePHtml(-1, $all_p.parents("div.group").children("label").text() + "无殊", "all")).end().removeClass("d-n");
            }
            addNotCodes($symptom_container.parent().parent());
            $choose_p = $choose.find("p[data-transCode=all]");
            $choose_p.children("i:last").one("click.addNotCodes", function () {
                var $choose = $choose_p.parents("div.choose:first");
                $choose_p.remove();
                $all_p.removeClass("focus");
                $else_ps.removeClass("focus");
                addNotCodes($symptom_container.parent().parent());
                hideChoose($choose);
                common.addChooseBottom(_$choose);
                return false;
            });
            common.addChooseBottom(_$choose);
            $input.focus();
        });

        function generatePHtml(data_id, text, transCode) {
            return '<p data-relation-id="' + data_id + '" data-transCode="' + transCode + '"><span class="dot"></span><span>' + text + '</span><i class="d-n"></i><i></i></p>';
        }
    }


    /*** 获取模板类 ***/
    function getTmpl(name) {

        /*** 加载推送提示信息的模板类 ***/
        function SubItemTmpl() {

        }

        SubItemTmpl.prototype.divStart = '<div class="symptom" data-id="';
        SubItemTmpl.prototype.divName = '" data-symptom-name="';
        SubItemTmpl.prototype.divTransCode = '" data-transCode="';
        SubItemTmpl.prototype.divStandardId = '" data-standard-id="';
        SubItemTmpl.prototype.divParamCode = '" data-param-code="';
        SubItemTmpl.prototype.divEnd = '">';
        SubItemTmpl.prototype.iStart = '<i class="iconfont icon-i">';
        SubItemTmpl.prototype.divFinish = '</i></div>';
        SubItemTmpl.prototype.setDataId = function (dataId) {
            this.dataId = dataId;
        };
        SubItemTmpl.prototype.setParamCode = function (paramCode) {
            this.paramCode = paramCode;
        };
        SubItemTmpl.prototype.setName = function (name, sub) {
            var subName = "";
            if (sub && sub !== name) {
                subName = "<span style='color: #aaa'>(" + sub + ")</span>";
            }
            this.symptomName = name;
            if (name.length > 6) {
                this.name = name.slice(0, 6) + "..." + subName;
            } else {
                this.name = name + subName;
            }
        };
        SubItemTmpl.prototype.setTransCode = function (transCode) {
            this.transCode = transCode;
        };
        SubItemTmpl.prototype.setStandardId = function (standardId) {
            this.standardId = standardId;
        };
        SubItemTmpl.prototype.getSymptom = function (index) {
            return this.divStart + this.dataId + this.divParamCode + this.paramCode + this.divName + this.symptomName + (this.transCode ? (this.divTransCode + this.transCode) : "") + (this.standardId ? (this.divStandardId + this.standardId) : "") + this.divEnd + this.name + this.iStart + (index === 4 ? (' <div class="box-r-s detail-info d-n"><p class="title"></p><p class="info"></p></div>') : '') + this.divFinish;
        };


        /*** 动态表单的模板类 ***/
        function DynamicForm(data_id, data_transCode, data_standard_id, data_param_code) {
            this.detail = '<div class="symptom-form d-n" data-param-code="' + data_param_code + '" data-relation-id="' + data_id + '"' + (data_transCode ? (" data-transCode=" + data_transCode) : "") + (data_standard_id ? (" data-standard-id=" + data_standard_id) : "") + '><div class="title"><span>';
        }

        DynamicForm.prototype.setTitle = function (title, data_transCode) {
            this.title = title;
            if (data_transCode === "SJFL002003") {
                this.title += "过敏";
            }
        };
        // DynamicForm.prototype.isMainSuit = function (index) {
        //     return (index===0 || index===4) ? true : false;
        // };
        DynamicForm.prototype.setTitleRight = function (index) {
            this.titleRight = '</span>' + ((index !== 0 && index !== 1) ? '<i class="iconfont icon-i" title="指南"></i>' : '') + '<div class="c-r checkbox ' + ((index === 0 || index === 4) ? 'd-n' : '') + '"><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>无</div></div>';
            this.detail += this.title + this.titleRight;
        };
        DynamicForm.prototype.getDynamicForm = function (index) {
            return this.detail + '<div class="button"><button class="blue cancel">取消</button><button class="blue confirm ' + (index !== 2 ? "disabled" : "") + '">确定</button></div></div>';
        };
        DynamicForm.prototype.addLabel = function (id, name, type, addLine, code, require) {
            this.detail += (require !== "1") ? '<div class="form-group" data-code="' + code + '" data-id="' + id + '" data-type="' + type + '"' + (addLine === "1" && type !== "6" ? (' data-addLine="' + addLine + '"') : "") + '><label>' + (name ? name : "&nbsp;") + '</label>' :
                '<div class="form-group" data-code="' + code + '" data-id="' + id + '" data-type="' + type + '"' + (addLine === "1" && type !== "6" ? (' data-addLine="' + addLine + '"') : "") + ' data-require="' + require + '"><label>' + (name ? (name + "<span style='color: red;'>*</span>") : "&nbsp;") + '</label>';
        };
        DynamicForm.prototype.addFormGroupFinish = function () {
            this.detail += '</div>';
        };
        DynamicForm.prototype.addControl = function (data) {
            var that = this;


            var control = {
                "2": textControl,//文本框
                "3": selectControl,//单选下拉框
                "4": checkboxAndRadio,//单选和复选框
                "5": checkboxAndRadio,
                "6": textAreaControl,
                "7": dateControl, //日期控件
                "8": selectCheckbox, // 下拉多选
                "9": selectCheckboxAndText, // 下拉多选带文本
                "10": selectUl
            };


            function textControl(data) {
                var html = '<input class="box-r-s" type="text" name="' + data.code + '" data-param-code="' + data.paramCode + '"',
                    labelPrefix = "",
                    labelSuffix = "",
                    addLine = "";
                if (data.labelPrefix) {
                    labelPrefix = '<span>' + data.labelPrefix + '</span>';
                    html += " data-labelPrefix=" + data.labelPrefix;
                }
                if (data.labelSuffix) {
                    labelSuffix = '<span>' + data.labelSuffix + '</span>';
                    html += " data-labelSuffix=" + data.labelSuffix;
                }
                if (data.maxValue) {
                    html += " data-maxValue=" + data.maxValue;
                }
                if (data.minValue) {
                    html += " data-minValue=" + data.minValue;
                }
                if (addLine === '1') {
                    addLine = '<br>';
                }
                html += ">";
                html += addLine;
                this.detail += labelPrefix + html + labelSuffix;
            }

            function selectControl(data) {
                var html = '<div class="select box-r-s w-100px"><input type="text" readonly name="' + data.code + '" data-param-code="' + data.paramCode + '"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n">',
                    addLine = "";
                if (data.addLine === "1") {
                    addLine = '<br>';
                }
                $.each(data.contentDetailList, function (i, v) {
                    html += '<li><a data-id="' + v.id + '"' + (v.relationId ? (" data-relation-id=" + v.relationId) : "") + ' data-param-code="' + v.paramCode + '">' + (v.name.trim() === "其他" || v.name.trim() === "其它" ? ('<input class="box-r-s" style="width: 100%;" type="text" placeholder="其它...">') : v.name) + '</a></li>';
                    if (v.questionInfos.length > 0) {
                        $.each(v.questionInfos, function (i2, v2) {
                            DynamicForm.prototype.addLabel.call(that, v2.id, v2.name, v2.questionContentList[0].type, v2.code, v2.require);
                            $.each(v2.questionContentList, function (i3, v3) {
                                DynamicForm.prototype.addControl.call(that, v3);
                            });
                            DynamicForm.prototype.addFormGroupFinish.call(that);
                        });
                    }
                });
                html += '</ul></div>' + addLine;
                this.detail += html;
            }

            function checkboxAndRadio(data) {
                var html = '<input type="text" data-param-code="' + data.paramCode + '" class="d-n" data-none="true">',
                    clazz = (data.type === "4" ? "checkbox" : "radio"),
                    addLine = "",
                    labelPrefix = "",
                    labelSuffix = "";
                if (data.addLine === "1") {
                    addLine = '<br>';
                }
                if (data.labelPrefix) {
                    labelPrefix = '<span class="m-t10p">' + data.labelPrefix + '</span>';
                }
                if (data.labelSuffix) {
                    labelSuffix = '<span class="m-t10p">' + data.labelSuffix + '</span>';
                }
                html += labelPrefix;
                $.each(data.contentDetailList, function (i, v) {
                    html += '<div class="c-r ' + clazz + '" name="' + v.code + '" data-id="' + v.id + '" data-relation-id="' + (v.relationId || -1) + '" data-param-code="' + v.paramCode + '"><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>' + v.name + '</div>';

                });
                html += labelSuffix + addLine;
                this.detail += html;
                $.each(data.contentDetailList, function (i, v) {
                    if (v.questionInfos.length > 0) {
                        $.each(v.questionInfos, function (i2, v2) {
                            DynamicForm.prototype.addLabel.call(that, v2.id, v2.name, v2.questionContentList[0].type, v2.code, v2.require);
                            $.each(v2.questionContentList, function (i3, v3) {
                                DynamicForm.prototype.addControl.call(that, v3);
                            });
                            DynamicForm.prototype.addFormGroupFinish.call(that);
                        });
                    }
                });
            }

            function textAreaControl(data) {
                this.detail += '<textarea class="box-r-s" name="' + data.code + '" data-param-code="' + data.paramCode + '"></textarea>';
            }

            function dateControl(data) {
                var year = new Date().getFullYear(),
                    min_year = year - 100;
                this.detail += '<div class="date-toggle box-r-s" data-name="date_toggle"><div class="background-color"></div><span class="focus">时长</span><span>日期</span></div><div class="date"><input class="box-r-s" name="date_one_time" data-param-code="' + data.paramCode + '" type="number" style="width: 70px;height: 30px;float: left;margin-right: 5px"><div class="select box-r-s w-70px"><input type="text" data-param-code="zzty000002" name="date_one_unit" readonly value="天"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n"><li><a>天</a></li><li><a>周</a></li><li><a>月</a></li><li><a>年</a></li><li><a>小时</a></li></ul></div></div><div class="date d-n"><div class="select box-r-s w-70px"><input type="text" name="date_to_year" readonly data-param-code="zzty000003"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n">';
                for (var i = year; i > min_year; i--) {
                    this.detail += '<li><a>' + i + '</a></li>';
                }
                this.detail += '</ul></div><span>年</span><div class="select box-r-s w-70px"><input type="text" readonly name="date_to_month" data-param-code="zzty000004"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n"><li><a>1</a></li><li><a>2</a></li><li><a>3</a></li><li><a>4</a></li><li><a>5</a></li><li><a>6</a></li><li><a>7</a></li><li><a>8</a></li><li><a>9</a></li><li><a>10</a></li><li><a>11</a></li><li><a>12</a></li></ul></div><span>月</span><div class="select box-r-s w-70px"><input type="text" readonly name="date_to_day" data-param-code="zzty000005"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n" data-month-contain="4,6,9,11"><li><a>1</a></li><li><a>2</a></li><li><a>3</a></li><li><a>4</a></li><li><a>5</a></li><li><a>6</a></li><li><a>7</a></li><li><a>8</a></li><li><a>9</a></li><li><a>10</a></li><li><a>11</a></li><li><a>12</a></li><li><a>13</a></li><li><a>14</a></li><li><a>15</a></li><li><a>16</a></li><li><a>17</a></li><li><a>18</a></li><li><a>19</a></li><li><a>20</a></li><li><a>21</a></li><li><a>22</a></li><li><a>23</a></li><li><a>24</a></li><li><a>25</a></li><li><a>26</a></li><li><a>27</a></li><li><a>28</a></li><li><a>29</a></li><li><a>30</a></li></ul><ul class="box-r-s d-n" data-month-contain="1,3,5,7,8,10,12"><li><a>1</a></li><li><a>2</a></li><li><a>3</a></li><li><a>4</a></li><li><a>5</a></li><li><a>6</a></li><li><a>7</a></li><li><a>8</a></li><li><a>9</a></li><li><a>10</a></li><li><a>11</a></li><li><a>12</a></li><li><a>13</a></li><li><a>14</a></li><li><a>15</a></li><li><a>16</a></li><li><a>17</a></li><li><a>18</a></li><li><a>19</a></li><li><a>20</a></li><li><a>21</a></li><li><a>22</a></li><li><a>23</a></li><li><a>24</a></li><li><a>25</a></li><li><a>26</a></li><li><a>27</a></li><li><a>28</a></li><li><a>29</a></li><li><a>30</a></li><a>31</a></li></ul><ul class="box-r-s d-n" data-month-contain="2"><li><a>1</a></li><li><a>2</a></li><li><a>3</a></li><li><a>4</a></li><li><a>5</a></li><li><a>6</a></li><li><a>7</a></li><li><a>8</a></li><li><a>9</a></li><li><a>10</a></li><li><a>11</a></li><li><a>12</a></li><li><a>13</a></li><li><a>14</a></li><li><a>15</a></li><li><a>16</a></li><li><a>17</a></li><li><a>18</a></li><li><a>19</a></li><li><a>20</a></li><li><a>21</a></li><li><a>22</a></li><li><a>23</a></li><li><a>24</a></li><li><a>25</a></li><li><a>26</a></li><li><a>27</a></li><li><a>28</a></li></ul><ul class="box-r-s d-n" data-month-contain="2"><li><a>1</a></li><li><a>2</a></li><li><a>3</a></li><li><a>4</a></li><li><a>5</a></li><li><a>6</a></li><li><a>7</a></li><li><a>8</a></li><li><a>9</a></li><li><a>10</a></li><li><a>11</a></li><li><a>12</a></li><li><a>13</a></li><li><a>14</a></li><li><a>15</a></li><li><a>16</a></li><li><a>17</a></li><li><a>18</a></li><li><a>19</a></li><li><a>20</a></li><li><a>21</a></li><li><a>22</a></li><li><a>23</a></li><li><a>24</a></li><li><a>25</a></li><li><a>26</a></li><li><a>27</a></li><li><a>28</a></li><li><a>29</a></li></ul></div><span>日</span></div><div class="validate d-n"><p>请填写正确的时间!!!</p></div>'

            }

            function selectCheckbox(data) {
                var html = '<div class="select box-r-s w-130px"><input type="text" readonly name="' + data.code + '" data-param-code="' + data.paramCode + '"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n">',
                    addLine = data.addLine || "";
                $.each(data.contentDetailList, function (i, v) {
                    html += '<li><a>' + (v.name.trim() === '其他' || v.name.trim() === '其它' ? ('<input class="box-r-s" type="text" placeholder="其它..." style="width:100%;">') : ('<div class="c-r checkbox" data-id="' + v.id + '" data-param-code="' + v.paramCode + '"><div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>' + v.name + '</div>')) + '</a></li>';
                });
                html += '</ul></div>' + addLine;
                this.detail += html;

            }

            function selectCheckboxAndText(data) {
                var html = '<div class="select box-r-s w-240px"><input type="text" readonly name="' + data.code + '" data-param-code="' + data.paramCode + '"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n">',
                    addLine = data.addLine || "";
                $.each(data.contentDetailList, function (i1, v1) {
                    html += '<li><a><div class="c-r checkbox" data-id="' + v1.id + '" data-param-code="' + v1.paramCode + '"> <div class="box-r-s"><i class="iconfont icon-duigou1"></i></div>' + v1.name + '</div>';
                    // if (v1.questionInfos.length > 0) {
                    //     $.each(v1.questionInfos[0].questionContentList, function () {
                    html += '<input class="box-r-s d-n" type="text">';
                    //     });
                    // }
                    html += '</a></li>';
                });
                html += '</ul></div>' + addLine;
                this.detail += html;
            }

            function selectUl(data) {
                var html = '<div class="select box-r-s w-100px"><input type="text" readonly name="' + data.code + '" data-param-code="' + data.paramCode + '"><div class="down-arrow"><i class="iconfont icon-triangle-down-copy"></i></div><ul class="box-r-s d-n">',
                    addLine = "";
                if (data.addLine === "1") {
                    addLine = '<br>'
                }
                $.each(data.contentDetailList, function (i1, v1) {
                    html += '<li><a data-id="' + v1.id + '" data-param-code="' + v1.paramCode + '">' + v1.name;
                    if (v1.questionInfos.length > 0) {
                        html += '<i class="iconfont icon-right-triangle"></i></a><ul class="box-r-s">';
                        $.each(v1.questionInfos[0].questionContentList[0].contentDetailList, function (i2, v2) {
                            html += '<li><a data-id="' + v2.id + '" data-param-code="' + v2.paramCode + '">' + v2.name + '</a></li>';
                        });
                        html += '</ul></li>';
                    } else {
                        html += '</a></li>';
                    }
                });
                html += '</ul></div>' + addLine;
                this.detail += html;
            }

            control[data.type].call(this, data);
        };


        /*** 全部症状的模板 ***/
        function SymptomAllTmpl() {
            this.button = "";
            this.wrap = "";
        }

        SymptomAllTmpl.prototype.addButton = function (data) {
            var symptomAllTmpl = this;
            $.each(data, function (i, v) {
                symptomAllTmpl.button += '<button data-id="' + v.id + '">' + v.name + '</button>';
            });
            symptomAllTmpl.button += '<div class="setting"></div>';
        };
        SymptomAllTmpl.prototype.addWrapContent = function (data) {
            var symptomAllTmpl = this;
            if (data.portraitList.length === 0) {
                symptomAllTmpl.wrap += '<div class="wrap-content d-n" style="width: 100%" data-relationId="' + data.id + '">';
                if (data.subitemList) {
                    symptomAllTmpl.wrap += addContent(data.subitemList);
                }
                symptomAllTmpl.wrap += '</div>';
            } else {
                var content = "";
                symptomAllTmpl.wrap += '<div class="wrap-content d-n" data-relationId="' + data.id + '"><div class="menu"><ul class="first-level">';
                $.each(data.portraitList, function (i, v) {
                    symptomAllTmpl.wrap += '<li><a data-id="' + v.id + '"><i class="iconfont icon-right-triangle"></i>' + v.name + '</a>';
                    if (v.portraitList.length > 0) {
                        symptomAllTmpl.wrap += '<ul class="second-level d-n" data-drop="false">';
                        $.each(v.portraitList, function (i2, v2) {
                            symptomAllTmpl.wrap += '<li><a data-id="' + v2.id + '">' + v2.name + '</a></li>';
                            if (v2.subitemList.length > 0) {
                                content += addContent(v2.subitemList);
                            }
                        });
                        symptomAllTmpl.wrap += '</ul>';
                    } else if (v.subitemList.length > 0) {
                        content += addContent(v.subitemList);
                    }
                    symptomAllTmpl.wrap += '</li>';
                });
                symptomAllTmpl.wrap += '</ul></div>' + content + '</div>';


                function addContent(data) {
                    var html = '<div class="content d-n">';
                    $.each(data, function (i, v) {
                        html += '<p class="symptom" data-id="' + v.id + '" data-symptom-name="' + v.name + '" data-portraitid="' + v.portraitId + '"' + (v.transCode ? ' data-transCode="' + v.transCode + '"' : '') + (v.standardId ? ' data-standard-id="' + v.standardId + '"' : '') + '>' + (v.name.length > 6 ? (v.name.slice(0, 5) + "...") : v.name) + '</p>';
                    });
                    html += '</div>';
                    return html;
                }
            }
        };


        var temp = {
            "SubItemTmpl": SubItemTmpl,
            "DynamicForm": DynamicForm,
            "SymptomAllTmpl": SymptomAllTmpl
        };

        return temp[name];
    }

    function addInformationHoverEvent($is) {
        //<span class=""> ... </span><span class="d-n"></span>
        $is.on('mouseenter.subInfo', function () {
            var $i = $(this),
                left = $i.offset().left - $i.parent().siblings('.container-shade:first').outerWidth(true),
                $detail_info = $i.children('.detail-info'),
                width = $i.parent().width(),
                $spans = $detail_info.find('span'),
                item_id = $i.parent().attr('data-id'),
                type = $i.parents('div.group').index();
            if ($detail_info.children('p.title').text() === '') {
                $.ajax({
                    url: Param.hostUrl + '/kl/introduce/get_by_itemidAndType',
                    async: true,
                    data: {type: type, itemId: item_id},
                    dataType: "json",
                    type: "post",
                    success: function (data) {
                        $detail_info.children('.title').text('知识说明');
                        if (!data.data || !data.data.shortName) {
                            $detail_info.children('.info').html('<span class="d-n"> ... </span><span class="d-n"></span>');
                            return;
                        }
                        if (data.data.shortName.length > 112) {
                            $detail_info.removeClass('d-n').children('.info').html(data.data.shortName.slice(0, 113) + '<span class=""> ... </span><span class="d-n">' + data.data.shortName.slice(113, data.data.shortName.length) + '</span>');
                        } else {
                            $detail_info.removeClass('d-n').children('.info').html(data.data.shortName.slice(0, 113) + '<span class="d-n"> ... </span><span class="d-n"></span>');
                        }
                        if (left > 98) {
                            $detail_info.css('left', 98 - left - width);
                        } else {
                            $detail_info.css('left', 20 - width);
                        }
                    },
                    err: function () {
                        alert('加载症状指南失败');
                    }
                });
            } else {
                if ($detail_info.hasClass('d-n')) {
                    return;
                }

                if (!($spans.eq(0).hasClass('d-n') && $spans.eq(1).hasClass('d-n'))) {
                    $spans.eq(0).removeClass('d-n');
                    $spans.eq(1).addClass('d-n');
                }

                if (left > 98) {
                    $detail_info.css('left', 98 - left - width);
                } else {
                    $detail_info.css('left', 20 - width);
                }
            }

        });

        $is.children('.detail-info').on('click', function (e) {
            return false;
        });

        $is.children('.detail-info').find('span:first').on('click', function (e) {
            var $span = $(this);
            $span.addClass('d-n');
            $span.next().removeClass('d-n');
        });
    }

    /***
     * 使用陈恩铭的information弹窗
     * ***/
    function addInformationModalEvent($is) {
        $is.click(function (e) {
            var $i = $(this),
                index = $i.parents('div.group').index(),
                type = index ? index : 1,
                itemId;

            if ($i.parent().hasClass('symptom')) {
                itemId = $i.parent().attr('data-id');
            } else {
                itemId = $i.parents('div.symptom-form:first').attr('data-relation-id');
            }
            //由于弹出诊断依据后，滚动鼠标后底下的页面也会移动
            $("body").css({
                "overflow-y": 'hidden'
            });

            var resl = ajaxPost(Param.hostUrl + Param.get_by_itemidAndType, {type: type, itemId: itemId});
            $("body").append($('<div id="informationShade"></div>'));
            $("#informationShade").height($(".container:first").height());
            var mark = new Date().getTime();
            $('#information1').tmpl(resl.data).appendTo('body').attr("id", mark).fadeIn();
            autoInformationHeight();
            palceModalMiddle(mark);
            common.informationControlToggle();
            $(".information .information-close div,#informationShade").click(function (event) {
                event.stopPropagation();
                $("#" + mark).fadeOut(function () {
                    $("#" + mark).remove();
                    $("#informationShade").remove();
                    //由于弹出诊断依据后，滚动鼠标后底下的页面也会移动
                    $("body").css({
                        "overflow-y": 'scroll'
                    });
                });
            });
            $(window).resize(function () {
                palceModalMiddle(mark);
            });
            e.stopPropagation();

        });
    }

    // 弹窗增加事件
    (function ($) {
        var $confirm_models = $("#confirm-model-one,#confirm-model-to"),
            $close = $confirm_models.find("p.close"),
            $buttons = $confirm_models.find("div.button").children(),
            $shade = $("#shade");
        $close.on("click.index", function () {
            fadeOut($confirm_models, $buttons, $shade);
        });
        $shade.on("click", function () {
            fadeOut($confirm_models, $buttons, $shade);
        });

        function fadeOut($confirm_models, $buttons, $shade) {
            $confirm_models.removeClass("animated flipInY").fadeOut();
            $buttons.off("click");
            $shade.fadeOut();
        }
    })(jQuery);

    function showDeleteAll($p) {
        var $shade = $("#shade"),
            $model = $("#confirm-model-one"),
            $buttons = $model.find("div.button"),
            $left_button = $buttons.children(".left"),
            $right_button = $buttons.children(".right"),
            $choose = $p.parents("div.choose:first"),
            $group = $choose.parents("div.group:first"),
            $group_next = $group.next();

        $model.addClass("animated flipInY").show();
        $shade.fadeIn();
        $left_button.off("click");
        $right_button.off("click");
        $left_button.on("click", function () {
            var data_relation_id = $p.attr("data-relation-id"),
                $symptom_form = $choose.siblings("div.symptom-container").children("div.symptom-form[data-relation-id=" + data_relation_id + "]"),
                $choose_next = $group_next.find("div.choose:first"),
                $symptom_form_next = $choose_next.siblings("div.symptom-container").children("div.symptom-form[data-relation-id=" + data_relation_id + "]"),
                $p_next = $choose_next.find("p[data-relation-id=" + data_relation_id + "]");
            // 删除主诉
            $p.remove();
            $symptom_form.remove();
            if ($choose.children("div.content").children().length === 0) {
                $choose.addClass("d-n");
                $choose.siblings("input").removeClass("d-n");
            }

            //改变现病史选择症状后显示的图标
            $p_next.find("i").removeClass("d-n");
            //改变现病史动态表单中无的显示
            $symptom_form_next.find('.c-r:first').removeClass("d-n");

            //隐藏弹窗和遮罩
            $model.removeClass("animated flipInY").fadeOut();
            $buttons.off("click");
            $shade.fadeOut();
            cleanCacheByType($group);
        });

        $right_button.on("click", function () {
            var data_relation_id = $p.attr("data-relation-id"),
                $symptom_form = $choose.siblings("div.symptom-container").children("div.symptom-form[data-relation-id=" + data_relation_id + "]"),
                $choose_next = $group_next.find("div.choose:first"),
                $symptom_form_next = $choose_next.siblings("div.symptom-container").children("div.symptom-form[data-relation-id=" + data_relation_id + "]");
            // 删除主诉
            $p.remove();
            $symptom_form.remove();
            if ($choose.children("div.content").children().length === 0) {
                $choose.addClass("d-n");
                $choose.siblings("input").removeClass("d-n");
            }

            //同时删除现病史
            $choose_next.find("p[data-relation-id=" + data_relation_id + "]").remove();
            $symptom_form_next.remove();
            if ($choose_next.children("div.content").children().length === 0) {
                $choose_next.addClass("d-n");
            }

            //隐藏弹窗和遮罩
            $model.removeClass("animated flipInY").fadeOut();
            $buttons.off("click");
            $shade.fadeOut();
            cleanCacheByType($group);
            cleanCacheByType($group_next);
        });
    }


    common.hideMenstrualAndMarital();


    function symptomWrapKeyboardEvent() {
        var $inputs = $groups.children("div.box-r-s").children("input"),
            $symptom_wraps = $groups.find("div.symptom-wrap");
        $symptom_wraps.on("mouseenter", "div.symptom", function (e) {
            var $div;
            if ($(e.target).prop("nodeName") === "DIV") {
                $div = $(e.target);
            } else {
                $div = $(e.target).parents("div.symptom:first");
            }
            $div.addClass("hover").siblings().removeClass("hover");
        });

        $symptom_wraps.on("mouseleave", "div.symptom", function (e) {
            var $div;
            if ($(e.target).prop("nodeName") === "DIV") {
                $div = $(e.target);
            } else {
                $div = $(e.target).parents("div.symptom:first");
            }
            $div.removeClass("hover");
        });

        $inputs.on("keydown.main", function (event) {
            var $input = $(this),
                $symptom_wrap = $input.siblings("div.symptom-container").children("div.symptom-wrap");
            if (!$symptom_wrap.is(":visible")) return;
            symptomWrapKeyDown(event.keyCode, $symptom_wrap);
            event.stopPropagation();
        });

        $(document).on("keydown", function (event) {
            var $symptom_wrap = $groups.filter(".focus").find("div.symptom-wrap");
            if (!$symptom_wrap.is(":visible")) return;
            symptomWrapKeyDown(event.keyCode, $symptom_wrap);
        });

        function symptomWrapKeyDown(keyCode, $symptom_wrap) {
            if (keyCode === 37) {
                move($symptom_wrap, "left");
            } else if (keyCode === 39) {
                move($symptom_wrap, "right");
            } else if (keyCode === 13) {
                $symptom_wrap.find("div.symptom.hover").click();
            }
        }

        function move($symptom_wrap, direction) {
            var $symptoms = $symptom_wrap.children("div.symptom"),
                $visible_symptoms = $symptoms.filter(function () {
                    return $(this).css("visibility") !== "hidden";
                }),
                $hover_symptom = $visible_symptoms.filter(".hover"),
                $button = $symptom_wrap.siblings("div.button").children("." + direction),
                index_all = $hover_symptom.index(),
                index_visible = $visible_symptoms.index($hover_symptom),
                is_move,
                is_click,
                is_next;

            if (direction === "left") {
                is_move = index_all !== 0;
                is_click = index_visible <= 2;
                is_next = false;
            } else if (direction === "right") {
                is_move = index_all !== $symptoms.length - 1;
                is_click = index_visible >= 3;
                is_next = true;
            } else {
                return;
            }

            if ($hover_symptom.length === 0) {
                $visible_symptoms.eq(0).addClass("hover");
            } else if (is_move) {

                (function ($hover_symptom) {
                    if (is_next) {
                        return $hover_symptom.next();
                    } else {
                        return $hover_symptom.prev();
                    }
                })($hover_symptom.removeClass("hover").siblings("div.symptom").removeClass("hover").end()).addClass("hover");

                if (is_click) {
                    $button.click();
                }
            }
        }

    }

    symptomWrapKeyboardEvent();


    loadSymptomGroups([0,1,2, 3,4]);
})(jQuery);
