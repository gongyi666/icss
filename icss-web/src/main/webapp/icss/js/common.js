/**
 * Created by Kiva on 17/3/28.
 */

var common = (function ($) {

    /*** 页面加载解析url中携带的参数 ***/
    function parseUrl() {
        var r = window.location.search.substr(1).split("&"),
            obj = {};
        $.each(r, function () {
            var arr = this.split("=");
            obj[arr[0]] = arr[1];
        });
        return obj;
    }

    /*** 将解析后的参数添加到全局变量Param中 ***/
    function getPatientInfo() {
        var patient_info = parseUrl();
        var counts = 0;//查看是否有路由参数
        for (var i in patient_info) {
            counts++;
        }
        function getLocalTime(nS) {
            nS = nS / 1000;
            return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/, ' ');
        }

        setParamVal("hospitalCode", patient_info.hospitalId);
        setParamVal("hospitalId", patient_info.hospitalId);
        setParamVal("brjzxh", patient_info.brjzxh);
        setParamVal("hisCode", patient_info.brjzid);//病人就诊id
        // setParamVal("hisCode", patient_info.hisCode);
        //邵逸夫患者id
        setParamVal("hisCode", patient_info.clinicId);
        setParamVal("patientId", patient_info.clinicId);
        //setParamVal("patientId", patient_info.brjzid);
        //病人姓名
        setParamVal("patientName", patient_info.brdaxm);
        //病人介质ID
        setParamVal("idNo", patient_info.patientNo);
        //病人介质id
        setParamVal("idType", patient_info.brjzid);
        //病人档案id
        setParamVal("recordId", patient_info.brdaid);
        //病人就诊序列号
        // setParamVal("serial", patient_info.brjzxh);
        setParamVal("serial", patient_info.brjzid);
        setParamVal("sexType", patient_info.sexType);
        setParamVal("age", patient_info.age);
        //邵逸夫医生编号和科室编号
        //setParamVal("doctorName", patient_info.kdysxm);
        setParamVal("doctorId", patient_info.doctorNo);
        setParamVal("doctorCode", patient_info.doctorNo);
        

        setParamVal("deptCode", patient_info.deptId);
        setParamVal("deptId", patient_info.deptId);
        setParamVal("deptNo", patient_info.deptId);
        //病人出生日期
        setParamVal("patBirthday", patient_info.patBirthday);
        //部门名称
        setParamVal("dept", patient_info.kdksmc);
        //接诊状态
        setParamVal('regVisitedState', patient_info.regVisitedState);

        // 这个接口是 获取和保存医生信息, 由于是 同步请求 所以 有可能会导致
        // 浏览器假死。
         ajaxPost(
             Param.hostUrl + "/at/doctorinfo/get_doctor_from_his",
                                    {
                                        doctorNo:patient_info.doctorNo,
                                        hospitalCode: patient_info.hospitalId,
                                        deptNo: patient_info.deptId
                                    },function(results) {
                                        if(results && results.data) {
                                            setParamVal("doctorCode", results.data.id);
                                            setParamVal("doctorId", results.data.id);
                                        }
                                    });
        
        //如果有就获取病人信息
        if (counts > 0) {
            $.ajax({
                url: Param.hostUrl + "/at/patientinfo/get_patient_from_his",
                data: {
                    "hisCode": Param.hisCode, //"[brjzid]",
                    "hospitalCode": Param.hospitalId//"[ZZJGDM]"
                },
                success: function (res) {
                    if (res.ret == 0 || res.result == 0) {
                        var patientData = res.data;
                        if(res.hospitalCode == "A001"){//如果是邵逸夫医院就会进入这块
                            
                            //转换年龄                      
                            // var patAge = getLocalTime(Date.now()).split("-")[0] - getLocalTime(patientData.birthday).split("-")[0];
                            var patAge =new Date().getFullYear() -  patientData.patBirthday.split(" ")[0].split("-")[0];
                            setParamVal("hospitalCode", patientData.hospitalCode);
                            setParamVal("patientHisCode", patientData.hisCode);
                            setParamVal("patientId", patientData.patientId);

                            //病人介质ID
                            setParamVal("cardId", patientData.hisCode);
                            if(patientData.patSex == "M"){
                                 setParamVal("sexType", "男");//性别
                            }else {
                                setParamVal("sexType", "女");//性别
                            }
                           
                            setParamVal("patientName", patientData.patName);//姓名

                            setParamVal("age", patAge);//年龄
                            setParamVal("patBirthday", patientData.birthday);//出生日期
                            setParamVal("patIdentityNum", patientData.idNo);//身份证号
                            setParamVal("patFamAddress", patientData.address);//家庭住址
                            setParamVal("patPostcode", patientData.postcode);//家庭邮编
                            setParamVal("phone", patientData.phone);//联系人电话
                            setParamVal("patContacts", patientData.contacts);//联系人

                            setParamVal("unit", patientData.workUnit);//就职单位
                            setParamVal("occupation", patientData.operation);//职业
                            setParamVal("patCountry", patientData.country);//国籍
                            setParamVal("nation", patientData.nationality);//民族
                            setParamVal("marry", patientData.matrimony);//婚姻状况
                            setParamVal("patHisPrevious", patientData.hisPrevious);//既往史
                            setParamVal("patHisAllergic", patientData.hisAllergic);//过敏史
                            setParamVal("patHisFamily", patientData.hisFamily);//家族史
                            setParamVal("patRecordDate", patientData.recordDate);//建档日期
                            setParamVal("patMemGrade", patientData.memGrade);//会员级别
                            setParamVal("patRecordDate", patientData.patRecordDate);//病人就诊时间
                            if (patientData.natureId == "医保") {
                                setParamVal("cardId", patientData.cardNum);//病人卡号
                                setParamVal("insuranceId", patientData.cardNum);//医保号
                            } else {
                                setParamVal("cardId", patientData.cardNum);//病人卡号
                                setParamVal("insuranceId", "无");//医保号
                            }

                            setParamVal("insurance", patientData.natureId);//是否医保
                            setParamVal("feeId", patientData.feeId);//费用id
                        }else {
                            
                            //转换年龄                      
                            // var patAge = getLocalTime(Date.now()).split("-")[0] - getLocalTime(patientData.birthday).split("-")[0];
                            var patAge = birthday(patientData.birthday);
                            setParamVal("hospitalCode", patientData.hospitalCode);
                            setParamVal("patientHisCode", patientData.hisCode);
                            setParamVal("patientId", patientData.id);

                            //病人介质ID
                            setParamVal("cardId", patientData.hisCode);

                            setParamVal("sexType", patientData.sex);//性别

                            setParamVal("patientName", patientData.name);//姓名
                            setParamVal("age", patAge);//年龄
                            setParamVal("patBirthday", patientData.birthday);//出生日期
                            setParamVal("patIdentityNum", patientData.idNo);//身份证号
                            setParamVal("patFamAddress", patientData.address);//家庭住址
                            setParamVal("patPostcode", patientData.postcode);//家庭邮编
                            setParamVal("phone", patientData.phone);//联系人电话
                            setParamVal("patContacts", patientData.contacts);//联系人

                            setParamVal("unit", patientData.workUnit);//就职单位
                            setParamVal("occupation", patientData.operation);//职业
                            setParamVal("patCountry", patientData.country);//国籍
                            setParamVal("nation", patientData.nationality);//民族
                            setParamVal("marry", patientData.matrimony);//婚姻状况
                            setParamVal("patHisPrevious", patientData.hisPrevious);//既往史
                            setParamVal("patHisAllergic", patientData.hisAllergic);//过敏史
                            setParamVal("patHisFamily", patientData.hisFamily);//家族史
                            setParamVal("patRecordDate", patientData.recordDate);//建档日期
                            setParamVal("patMemGrade", patientData.memGrade);//会员级别
                            if (patientData.natureId == "医保") {
                                setParamVal("cardId", patientData.cardNum);//病人卡号
                                setParamVal("insuranceId", patientData.cardNum);//医保号
                            } else {
                                setParamVal("cardId", patientData.cardNum);//病人卡号
                                setParamVal("insuranceId", "无");//医保号
                            }

                            setParamVal("insurance", patientData.natureId);//是否医保
                            setParamVal("feeId", patientData.feeId);//费用id
                        }
                        //判断是否过滤月经史和婚育史
                        hideMenstrualAndMarital();
                    }
                },
                error: function () {
                    console.log("未获取到数据");
                }
            });
        }
    }

    getPatientInfo();

    function setParamVal(name, info) {
        if (info) {
            Param[name] = info;
        }
    }


    /*** 动态改变两侧的遮罩的宽度 ***/
    function changeContainerShadowWidth($container_shade) {
        // var $container = $("div.container"),
        //     $container_shade = $container.siblings("div.container-shade");
        // $container_shade.css("width", $container.css("marginLeft"));
        var $container = $("div.container"),
            $container_shade = $container_shade || $("div.group").find(".container-shade");
        $container_shade.css("width", $container.css("marginLeft"));
        $container_shade.css("height", $container.css("height"));
        $container_shade.off("click");
        $container_shade.on("click", function (e) {
            $(document).click();
            return false;
        });
    }

    $(window).ready(function () {
        changeContainerShadowWidth();
    });
    $(window).resize(function () {
        changeContainerShadowWidth();
    });

    // $("body,html").click(function (e) {
    //     $(document).click();
    //     return false;
    // });

    function getMonthUl($select, $uls) {
        var $year_select = $select.siblings(".select:first"),
            val = parseInt($year_select.children("input").val().trim()),
            $ul;
        if (val === new Date().getFullYear()) {
            $ul = $uls.eq(1);
        } else if (val === val) {
            $ul = $uls.eq(0);
        } else {
            $ul = $();
        }
        return $ul;
    }

    /*** 时间控件select的下拉菜单的点击事件 ***/
    function getDayUl($select, $uls) {
        var monthMap = {
            4: 0,
            6: 0,
            9: 0,
            11: 0,
            1: 1,
            3: 1,
            5: 1,
            7: 1,
            8: 1,
            10: 1,
            12: 1,
            2: 2
        };
        var $select_siblings = $select.siblings("div.select"),
            year = parseInt($select_siblings.eq(0).children("input").val().trim()),
            month = parseInt($select_siblings.eq(1).children("input").val().trim()),
            $ul,
            date = new Date();
        if (month !== month || year !== year) {
            $ul = $();
        } else if (year === date.getFullYear() && month === date.getMonth() + 1) {
            $ul = $uls.filter(":last");
        } else {
            $ul = $uls.eq(monthMap[month]);
            if (month === 2) {
                $ul = year % 400 === 0 || (year % 4 === 0 && year % 100 !== 0) ? $ul.next() : $ul;
            }
        }
        return $ul;
    }


    $(document).on("click", function hideSelect() {
        selectHide();
        removeFocus();
        documentGroupHide();
        $(document).find("div.symptom-else").removeClass("d-n");
    });


    function validateAll($symptom_form) {
        var $checks = $symptom_form.find("div.c-r.checked"),
            $inputs = $symptom_form.find("input:visible").not($symptom_form.find("div.date:first").children("div.select").children("input")),
            $textarea = $symptom_form.find("textarea"),
            $confirm = $symptom_form.find("button.confirm");
        if ($checks.length > 0 || inputsIsEmpty($inputs) || ($textarea.length > 0 && $textarea.val().trim() !== "")) {
            $confirm.removeClass("disabled");
        } else {
            $confirm.addClass("disabled");
        }

        function inputsIsEmpty($inputs) {
            var flag = false;
            $inputs.each(function () {
                if ($(this).val().trim() !== "") {
                    flag = true;
                    return false;
                }
            });
            return flag;
        }
    }
    /*** 点击取消从副本域拿来symptom-form替换 ***/
    function replaceSymptomForm($symptom_form) {
        var $domain=$("#symptom-form-copy-domain"),
            $symptom_form_copy=$domain.find('.symptom-form[data-relation-id='+$symptom_form.attr('data-relation-id')+']');
        if($symptom_form_copy.length>0){
            $symptom_form.parent().append($symptom_form_copy);
            $symptom_form.remove();
        }
    }



    /*** 暴露给外面给form表单空间添加事件的函数 ***/
    function symptomFormAddEvent($symptom_form) {
        var $form_groups = $symptom_form.find("div.form-group"),
            $check_boxes = $form_groups.find("div.checkbox"),
            $radios = $form_groups.find("div.radio"),
            $selects = $form_groups.find("div.select"),
            $selects_children_ul = $selects.find('ul.box-r-s'),
            $date_toggle = $symptom_form.find("div.date-toggle"),
            $confirm_button = $symptom_form.find("button.confirm"),
            $cancel_button = $symptom_form.find("button.cancel"),
            $title_checkbox = $symptom_form.children("div.title").children("div.checkbox"),
            $textarea = $symptom_form.find("textarea"),
            $inputs = $symptom_form.find("div.form-group").children("input");
        $selects_children_ul.find("div.checkbox").siblings('input.box-r-s').on("input.common propertychange.common", function () {
            var $select = $(this).parents("div.select"),
                $text = $select.children("input[type=text]"),
                index = $select.parents("div.group").index();
            $text.val(getSelectText($select));
            if (index !== 2) {
                validateAll($select.parents("div.symptom-form:first"));
                validateControl($select.parents("div.form-group:first"));
            }
        });

        $form_groups.filter("[data-type=8]").find("div.select").find("a").children("input").on("input.common propertychange.common", function () {
            selectMultipleChoiceByCheckbox($(this).parent());
        });

        $inputs.on("input.common propertychange.common", function () {
            var $input = $(this),
                index = $input.parents("div.group:first").index();
            if (index !== 2) {
                validateAll($input.parents("div.symptom-form:first"));
                validateControl($input.parents("div.form-group:first"));
            }
        });
        $selects_children_ul.on("click.common", "a,input,div", function (e) {
            var $node = $(e.target),
                $a = $node.parents("a:first"),
                index = $node.parents("div.group").index();
            //console.log($node);
            if (e.target.nodeName === "A") {
                if ($node.children("div.c-r.checkbox").length === 0 && $(this).parents("div.form-group:first").attr("data-type") !== "8") {
                    selectSingleChoiceByText($node);
                }
                if ($node.siblings("ul").length > 0) {
                    e.stopPropagation();
                }
            } else {
                if ($node.siblings().length === 0 && e.target.nodeName === "INPUT" && $(this).parents("div.form-group:first").attr("data-type") !== "8") {
                    selectSingleChoiceByText($a);
                } else {
                    selectMultipleChoiceByCheckbox($a);
                }
                e.stopPropagation();
            }
            if (index !== 2) {
                validateAll($node.parents("div.symptom-form:first"));
                validateControl($node.parents("div.form-group:first"));
            }
        });

        $selects_children_ul.on("click.relation", "a", function (e) {
            var $a = $(e.target),
                $a_siblings = $a.parent().siblings().children("a[data-relation-id]"),
                $relation_form_group = $a.parents("div.form-group:first").siblings('div.form-group[data-relation=y]'),
                relation_id_arr,
                $input = $a.parents("div.select:first").children("input");
            $a_siblings.each(function () {
                var $a_sibling = $(this),
                    relation_id_arr = $a_sibling.attr("data-relation-id").split(",");
                $.each(relation_id_arr, function () {
                    $relation_form_group.filter("[data-id=" + this + "]").hide();
                });
            });
            if ($a.is("[data-relation-id]")) {
                relation_id_arr = $a.attr("data-relation-id").split(",");
                $.each(relation_id_arr, function () {
                    $.each(relation_id_arr, function () {
                        $relation_form_group.filter("[data-id=" + this + "]").show();
                    });
                });
                $input.attr("data-relation-id", $a.attr("data-relation-id"));
            } else {
                $input.removeAttr("data-relation-id");
            }
        });

        $symptom_form.on("click.common", function (e) {
            removeFocus();
            selectHide();
            e.stopPropagation();
        });
        $selects.on("click.common", function (e) {
            var $select = $(this),
                $ul = $select.children("ul");
            if (!$select.parents("div.symptom-form:first").hasClass("disabled")) {
                selectHide($select);
                if ($select.index() === 2 && $select.parent().siblings("div.date-toggle").length > 0) {
                    ulToggle($select, getMonthUl($select, $ul));
                } else if ($select.index() === 4 && $select.parent().siblings("div.date-toggle").length > 0) {
                    ulToggle($select, getDayUl($select, $ul));
                } else {
                    ulToggle($select, $ul.eq(0));
                }
            }
            e.stopPropagation();
        });
        $check_boxes.on("click.common", function () {
            var $checkbox = $(this),
                index = $checkbox.parents("div.group").index(),
                $input = $checkbox.prevAll("input:first"),
                $form_group = $checkbox.parent(),
                text = "";
            if (!$checkbox.parents("div.symptom-form:first").hasClass("disabled")) {
                checkboxChangeState($checkbox);
                showRelationFormGroup($checkbox);
            }
            if (index !== 2) {
                validateAll($checkbox.parents("div.symptom-form:first"));
                validateControl($checkbox.parents("div.form-group:first"));
            }
            if ($input.attr("data-none") && $form_group.is(".form-group")) {
                var $children = $form_group.children("div.checkbox,input"),
                    index_first = $children.index($input),
                    index_last;
                if (index_first) {
                    $children = $children.filter(":gt(" + index_first + ")");
                }

                index_last = $children.index($checkbox.nextAll("input:first"));
                if (index_last > 0) {
                    $children = $children.filter(":lt(" + index_last + ")");
                }

                $children.filter(".checked").each(function () {
                    text += "^" + $(this).attr("data-param-code");
                });
                $input.attr("data-param-value", text.slice(1));
            }
        });

        $radios.on("click.common", function () {
            var $radio = $(this),
                index = $radio.parents("div.group").index(),
                $input = $radio.siblings("input"),
                $form_group = $radio.parent();
            if (!$radio.parents("div.symptom-form:first").hasClass("disabled")) {
                radioChangeState($radio);
                showRelationFormGroup($radio, true);
            }
            if (index !== 2) {
                validateAll($radio.parents("div.symptom-form:first"));
                validateControl($radio.parents("div.form-group:first"));
            }
            if ($input.attr("data-none") && $form_group.is(".form-group")) {
                $input.attr("data-param-value", $form_group.children("div.radio.checked").attr("data-param-code"));
            }
        });
        $date_toggle.data("span_index", 0);
        $date_toggle.children("span").on("click.common", function (e) {
            var $span = $(this),
                $date_toggle = $span.parent(),
                group_index = $(this).parents("div.group").index();
            if (!$span.parents("div.symptom-form:first").hasClass("disabled")) {
                dateControlToggle($date_toggle, $span, function () {
                    if (group_index === 0 || group_index === 1) {
                        validateDate($date_toggle);
                        $date_toggle.siblings(".validate").addClass("d-n");
                    } else if (group_index !== 2) {
                        validateAll($span.parents("div.symptom-form:first"));
                    }
                });
            }
            selectHide();
            e.stopPropagation();
        });

        $date_toggle.siblings("div").not("div.validate").eq(1).find("input:first").on("input propertychange", function () {
            var $input = $(this),
                input_val = $input.val().trim(),
                unit_val = String($input.next().children("input").val()).trim(),
                index = $input.parents("div.group").index();
            if (input_val !== "" && unit_val !== "") {
                $input.parent().siblings("div.validate").addClass("d-n");
            }
            if (index !== 2) {
                validateAll($input.parents("div.symptom-form:first"));
                validateDate($input.parent().siblings("div.date-toggle"));
            }
        });
        $date_toggle.siblings("div.date:first").find("input[type=number]").on("input propertychange", function () {
            var $input = $(this),
                val = parseInt($input.val().trim()),
                index = $input.parents("div.group").index();
            if (val !== val || val <= 0) {
                $input.val("");
            }
            if (index !== 2) {
                validateAll($input.parents("div.symptom-form:first"));
                validateDate($input.parents("div.date:first").siblings("div.date-toggle"));
            }
        });

        $confirm_button.on("click.common", function () {
            symptomFormConfirm($(this));
        });
        $cancel_button.on("click.common", function () {
            var $symptom_form = $(this).parents("div.symptom-form:first"),
                $choose = $symptom_form.parent().siblings("div.choose"),
                $detail_button = $symptom_form.siblings("div.button").find(".detail");

            $symptom_form.fadeOut(function () {
                replaceSymptomForm($symptom_form);
                $symptom_form.parent().removeClass("symptom");
                // if ($choose.find("p[data-relation-id=" + $symptom_form.attr("data-relation-id") + "]").length === 0) {
                //     $symptom_form.remove();
                // }
                if ($choose.filter(":last").data('lastStep') === "symptom-all") {
                    $detail_button.click();
                    return;
                }
                if ($choose.filter(":last").data('lastStep') === "symptom-wrap") {
                    return;
                }
                if ($choose.filter(":last").data('lastStep') === "") {
                    $choose.siblings("div.symptom-all").hide().siblings("div.symptom-container").hide();
                }

            });

        });

        $title_checkbox.on("click.common", function () {
            var $checkbox = $(this),
                $symptom_form = $checkbox.parent().parent(),
                index = $symptom_form.parents("div.group").index();
            if ($checkbox.hasClass("checked")) {
                $checkbox.removeClass("checked");
                $symptom_form.removeClass("disabled");
                $symptom_form.find("button.confirm").addClass($checkbox.data("buttonState"));
                $symptom_form.find("input,textarea").removeAttr("disabled");
            } else {
                $checkbox.addClass("checked");
                $symptom_form.addClass("disabled");
                if ($symptom_form.find("button.confirm").hasClass('disabled')) {
                    $checkbox.data("buttonState", "disabled");
                } else {
                    $checkbox.data("buttonState", "");
                }
                $symptom_form.find("button.confirm").removeClass("disabled");
                $symptom_form.find("input,textarea").attr("disabled", "");
            }
            if (index !== 2) {
                validateAll($symptom_form);
                validateControl($checkbox.parent());
            }
        });

        $textarea.on("input propertychange", function () {
            var index = $(this).parents("div.group:first").index();
            if (index !== 2) {
                validateAll($(this).parents("div.symptom-form"));
                validateControl($(this).parents("div.form-group:first"));
            }
        });

        //    动态修改时间控件切换的left
        (function ($date_toggles) {
            $date_toggles.each(function () {
                var $date_toggle = $(this),
                    text_length = $date_toggle.siblings("label").text().trim().length - 1,
                    left = 20 + text_length * 10;
                $date_toggle.css("left", left);
            });
        })($date_toggle);

    }

    /*** 让时间控件增加默认值 ***/
    function dateControlDefault($date_toggle) {
        var $date = $date_toggle.siblings('div.date:last'),
            $inputs = $date.find('input'),
            date = new Date();

        $inputs.eq(0).val(date.getFullYear()).end().eq(1).val(date.getMonth() + 1).end().eq(2).val(date.getDate());
        $date.find("ul:last").find("a:last").click();
        console.log($date_toggle.siblings('div.date:first').find('input:first').trigger('input'));
    }

    /*** 让时间控件切换样式两侧进行关联 ***/
    function dateControlRelation($date_toggle) {
        var $dates = $date_toggle.siblings('div.date'),
            $one_inputs = $dates.eq(0).find('input'),
            $one_as = $dates.eq(0).children("div.select").find('a'),
            $to_inputs = $dates.eq(1).find('input'),
            $to_as = $dates.eq(1).children('div.select').find('a');

        $one_inputs.eq(0).on('input.common.dateRelationTo propertychange.common.dateRelationTo', function () {
            relationTo($one_inputs, $to_inputs);
        });

        $one_as.parents('ul:first').on('click.common.dateRelationTo', 'a', function () {
            relationTo($one_inputs, $to_inputs);
        });

        $to_as.parent().parent().on('click.common.dateRelationOne', 'a', function () {
            relationOne($one_inputs, $to_inputs);
        });


        function relationTo($one_inputs, $to_inputs) {
            var unit_map = {
                    '小时': 3600000,
                    '天': 86400000,
                    '周': 604800000,
                    '月': 2592000000,
                    '年': 31536000000
                },
                time = parseInt($one_inputs.eq(0).val().trim()),
                date;
            if (!time) {
                return;
            }

            date = new Date(new Date().getTime() - time * unit_map[$one_inputs.eq(1).val().trim()]);
            $to_inputs.eq(0).val(date.getFullYear()).end().eq(1).val(date.getMonth() + 1).end().eq(2).val(date.getDate());
        }

        function relationOne($one_inputs, $to_inputs) {
            var year = parseInt($to_inputs.eq(0).val().trim()),
                month = parseInt($to_inputs.eq(1).val().trim()),
                day = parseInt($to_inputs.eq(2).val().trim()),
                time,
                date;

            if (!year) {
                return;
            }
            month = month ? (month - 1) : 0;
            day = day ? day : 1;
            time = (new Date().getTime() - new Date(year, month, day).getTime()) / 1000 / 60;
            date = getDateUnit(time);
            $one_inputs.eq(0).val(date[0]).end().eq(1).val(date[1]);


            function getDateUnit(dateTime) {
                var unit, date;
                dateTime = dateTime / 60;
                if (dateTime < 24) {
                    date = Math.floor(dateTime);
                    unit = "小时";
                    return [date, unit];
                }
                if (dateTime >= 24 && dateTime < 168) {
                    date = Math.floor(dateTime / 24);
                    unit = "天";
                    return [date, unit];
                }
                dateTime /= 24;
                if (dateTime >= 7 && dateTime < 30) {
                    date = Math.floor(dateTime / 7);
                    unit = "周";
                    return [date, unit];
                }
                if (dateTime >= 30 && dateTime < 365) {
                    date = Math.floor(dateTime / 30);
                    unit = "月";
                    return [date, unit];
                }
                if (dateTime >= 365) {
                    date = Math.floor(dateTime / 365);
                    unit = "年";
                    return [date, unit];
                }
            }
        }

    }

    /*** 隐藏symptom-else ***/
    function hideSymptomElse($symptom_else) {
        $symptom_else.addClass("d-n");
    }

    /*** 显示symptom-else ***/
    function showSymptomElse($symptom_else) {
        $symptom_else.removeClass("d-n");
    }

    /*** 将症状按照时间由远到近排序 无排在最后 ***/
    function sortChoose($choose) {
        var $ps = $choose.children("div.content").children("p").filter("[data-time][data-time-unit]"),
            arr = $ps.toArray().sort(function (a, b) {
                var time_a = toTime(a.getAttribute("data-time"), a.getAttribute("data-time-unit")),
                    time_b = toTime(b.getAttribute("data-time"), b.getAttribute("data-time-unit"));
                // console.log(time_a - time_b);
                return time_a - time_b;
            });
        $.each(arr, function () {
            $choose.children("div.content").prepend(this);
        });

        function toTime(data_time, data_time_unit) {
            var unit_obj = {
                    "年": 8640,
                    "个月": 720,
                    "天": 24,
                    "周": 168,
                    "小时": 1
                },
                time_arr,
                unit_arr,
                time = 0;
            if (data_time) {
                time_arr = data_time.split(",");
                unit_arr = data_time_unit.split(",");
                $.each(time_arr, function (i, v) {
                    time += v * unit_obj[unit_arr[i]];
                });
            }
            if (time === 0) {
                time = Infinity;
            }
            return time;
        }
    }

    /*** 校验必填项是否已经填写 ***/
    function validateRequire($symptom_form) {
        var $form_groups = $symptom_form.find("div.form-group[data-require=1]"),
            validate = true;
        $form_groups.each(function () {
            var $form_group = $(this);
            if ($form_group.attr("data-type") === "7") {
                validate = validateDate($form_group.find("div.date-toggle")) ? true : false;
            } else {
                validate = validateControl($form_group) ? true : false;
            }
            if (!validate) {
                return false;
            }
        });
        return validate;
    }

    function validateControl($form_group) {
        var $input = $form_group.find("input:first"),
            $validate = $form_group.children("div.validate"),
            validate = false;
        if ($input.length) {
            if ($input.val().trim()) {
                validate = true;
            }
        }
        if (validate) {
            $validate.addClass("d-n");
        } else {
            $validate.removeClass("d-n");
        }
        return validate;
    }

    function serverCache($group) {
        var $sibling_groups = $group.siblings("div.group"),
            inStandardIds = [],
            params,
            $ps = $group.find("div.choose p");
        $ps.each(function () {
            inStandardIds.push($(this).attr("data-standard-id"));
        });
        params = {
            patientId: Param.patientId,
            type: $group.index(),
            inStandardIds: inStandardIds.join()
        };
        flushDataIntoCache(params);
    }


    /*** 动态表单点击之后增加症状 ***/
    function symptomFormConfirm($button) {
        var $symptom_form = $button.parent().parent(),
            $choose = $symptom_form.parent().siblings("div.choose"),
            $group = $choose.parent().parent(),
            index = $group.index(),
            data_relation_id = $symptom_form.attr("data-relation-id"),
            $p = $choose.find("p[data-relation-id=" + data_relation_id + "]"),
            data_transCode = $symptom_form.attr("data-transCode"),
            data_standard_id = $symptom_form.attr("data-standard-id"),
            $symptom_container = $symptom_form.parent(),
            $detail_button;
        if ($button.hasClass("disabled")) {
            return;
        }
        if (data_transCode) {
            $choose = $choose.filter("[data-transCode=" + data_transCode + "]");
        } else {
            $choose = $choose.not("[data-transCode]");
        }

        if (!$symptom_form.hasClass("disabled")) {
            if (!validateRequire($symptom_form)) {
                return false;
            }
        }

        var addChoose = {
            "0": addMainSuitChoose,
            "1": addMainSuitChoose,
            "2": addPreviousHistoryChoose,
            "3": addElseHistoryChoose,
            "4": addSignChoose
        };


        addChoose[index](index);
        // if (!addChoose[index](index)) {
        //     return;
        // }

        if (index !== 0) {
            common.addChooseBottom($choose);
        }
        $symptom_form.fadeOut(function () {
            $symptom_container.removeClass("symptom");
            if ($symptom_form.parent().siblings("div.choose").find("p").length === 1 && index === 0) {
                $group.next().find("input:first").click();
                // } else if ($choose.data("lastStep") === "symptom-all") {
                //     $detail_button = $symptom_container.children("div.button").find(".detail");
                //     $detail_button.click();
            } else {
                $symptom_container.hide();
            }
            // console.log($choose.data("lastStep"));
        });

        serverCache($group);

        //addSymptomFocus($symptom_form.parent());
        //添加主诉和现病史的症状详情
        function addMainSuitChoose(index) {
            var $date_toggle = $symptom_form.find("div[data-name=date_toggle]"),
                text = "",
                title = text = $symptom_form.children("div.title").children("span").text(),
                time,
                $group_relation;
            text += addParts($symptom_form);

            //$p.remove();
            if (index === 0) {
                if (!$symptom_form.hasClass("disabled")) {
                    time = addTime($date_toggle);
                    text += time[0];
                }
                if ($p.length > 0) {
                    changeP($p, data_relation_id, text, true, title, data_transCode, data_standard_id, time);
                } else {
                    $choose.children("div.content").append(generatePHtml(data_relation_id, text, true, title, data_transCode, data_standard_id, time)).end().removeClass("d-n");
                }
                $choose.siblings("input").addClass("d-n");
                $group_relation = $group.next();
                relationChoose($group_relation, index);
            } else {
                $group_relation = $group.prev();
                if (!$symptom_form.hasClass("disabled")) {
                    time = addTime($date_toggle);
                    text += time[0];
                }

                if ($p.length > 0) {
                    changeP($p, data_relation_id, ($symptom_form.hasClass("disabled") ? "无" + title : text + generateContent()), true, title, data_transCode, data_standard_id, time);
                } else {
                    $choose.children("div.content").append(generatePHtml(data_relation_id, ($symptom_form.hasClass("disabled") ? "无" + title : text + generateContent()), $symptom_form.hasClass("disabled"), title, data_transCode, data_standard_id, time)).end().removeClass("d-n");
                }

                if ($symptom_form.hasClass("disabled")) {
                    $symptom_form.find("input").not("[name=date_one_unit]").val("");
                    removeMainSuitChoose($group_relation.find("div.choose"), data_relation_id);
                } else if ($group_relation.find("div.choose").find("p[data-relation-id=" + data_relation_id + "]").length > 0) {
                    relationChoose($group_relation, index, $choose);
                }
                sortChoose($choose);
            }


            return true;

            function relationChoose($group_relation, index) {
                var $symptom_container_relation = $group_relation.find("div.symptom-container"),
                    relation_text = (index === 1 ? (text) : (text + generateContent())),
                    $choose_relation = $group_relation.find("div.choose"),
                    $p = $choose_relation.find("p[data-relation-id=" + $symptom_form.attr("data-relation-id") + "]"),
                    data_transCode = $symptom_form.attr("data-transCode"),
                    data_standard_id = $symptom_form.attr("data-standard-id"),
                    $symptom_form_relation;

                $symptom_form.hasClass("disabled") ? relation_text = "无" + title : relation_text;
                $symptom_container_relation.find("div.symptom-form[data-relation-id=" + $symptom_form.attr("data-relation-id") + "]").remove();
                $symptom_container_relation.append($symptom_form.clone(true));
                $symptom_form_relation = $symptom_container_relation.find("div.symptom-form[data-relation-id=" + $symptom_form.attr("data-relation-id") + "]");
                if (index === 0) {
                    $p.remove();
                    $choose_relation.children("div.content").append(generatePHtml($symptom_form.attr("data-relation-id"), relation_text, false, title, data_transCode, data_standard_id, time)).end().removeClass("d-n");
                    $choose_relation.find("p[data-relation-id=" + $symptom_form.attr("data-relation-id") + "]").children("i").addClass("d-n");
                    sortChoose($choose_relation);
                }
                if (index === 1) {
                    changeP($p, data_relation_id, relation_text, true, title, data_transCode, data_standard_id, time);
                    $symptom_form_relation.children("div.title").find("div.checkbox").addClass("d-n");
                    $choose_relation.find("p[data-relation-id=" + $symptom_form.attr("data-relation-id") + "]").children("i:first").addClass("d-n");
                    $choose.find("p[data-relation-id=" + $symptom_form.attr("data-relation-id") + "]").children("i").addClass("d-n");
                }
                $symptom_container_relation.find("div.symptom-form[data-relation-id=" + $symptom_form.attr("data-relation-id") + "]").hide();
                if (index === 0) {
                    addChooseBottom($choose_relation);
                }
            }

            //如果现病史勾选为无 则去除主诉的对应疾病
            function removeMainSuitChoose($choose, data_id) {
                var $symptom_container = $choose.siblings("div.symptom-container");
                $choose.find("p[data-relation-id=" + data_id + "]").remove();
                $symptom_container.children("div.symptom-form[data-relation-id=" + data_id + "]").remove();
                if ($choose.find("p").length === 0) {
                    $choose.addClass("d-n");
                    $choose.siblings("input").removeClass("d-n");
                }
            }

            function generateContent() {
                var textArea_val = $symptom_form.find("textarea").val().trim();
                // return ($symptom_form.find("input[name=question_cause]").val() !== '' ? ',在' + $symptom_form.find("input[name=question_cause]").val() + "情况下" : '') + ($symptom_form.find("input[name=question_degree]").val() !== '' ? ',' + $symptom_form.find("input[name=question_degree]").val() : '') + ($symptom_form.find("input[name=question_nature]").val() !== '' ? ",呈" + $symptom_form.find("input[name=question_nature]").val() : '') + (textArea_val === "" ? "" : ("(" + textArea_val + ")"));
                return ($symptom_form.find("input[name=question_degree]").val() !== '' ? ',' + $symptom_form.find("input[name=question_degree]").val() : '') + ($symptom_form.find("input[name=question_nature]").val() !== '' ? "," + $symptom_form.find("input[name=question_nature]").val() : '') + ($symptom_form.find("input[name=question_cause]").val() !== '' ? ',' + $symptom_form.find("input[name=question_cause]").val() + "引起" : '') + (textArea_val === "" ? "" : ("(" + textArea_val + ")"));
            }


            function addParts($symptom_form) {
                var $form_group = $symptom_form.find("div.form-group:first"),
                    $check_boxes = $form_group.find("div.c-r.checked"),
                    text = "(";
                if ($form_group.attr("data-type") === '4' || $form_group.attr("data-type") === '5') {
                    $check_boxes.each(function () {
                        text += $(this).text() + ",";
                    });
                }
                text = text.slice(0, -1);
                if ($check_boxes.length > 0) {
                    text += ")";
                }
                return text;
            }
        }


        /*** 添加疾病时移除冲突的无殊 ***/
        function removeElseChoose($choose, data_transCode) {
            var _$choose = $choose.add($choose.siblings("div.choose")),
                $else_all_p = _$choose.find("p[data-transCode=all]"),
                $else_p = _$choose.find("p[data-relation-id=-1][data-transCode=" + data_transCode + "]");
            if ($else_all_p.length > 0) {
                $else_all_p.children("i:last").click();

            } else if ($else_p.length > 0) {
                $else_p.children("i:last").click();
            }
        }


        /***添加选择的既往史 ***/
        function addPreviousHistoryChoose() {
            var $date_toggle = $symptom_form.find("div[data-name=date_toggle]"),
                text,
                title = text = $symptom_form.children("div.title").children("span").text(),
                time;
            $p.remove();

            if ($symptom_form.hasClass("disabled")) {
                if ($symptom_form.attr("data-transCode") === "SJFL002004") {
                    title = "接种" + title;
                }
                $choose.children("div.content").append(generatePHtml($symptom_form.attr("data-relation-id"), "无" + title, true, title, data_transCode, data_standard_id)).end().removeClass("d-n");
                return true;
            }
            // if (!validateDate($date_toggle)) {
            //     addDateControlValidateEvent($date_toggle);
            //     return false;
            // }
            removeElseChoose($choose, data_transCode);
            time = addTime($date_toggle);
            $choose.children("div.content").append(generatePreviousHistoryPHtml($symptom_form, data_transCode, data_standard_id, time)).end().removeClass("d-n");
            return true;


            function generatePreviousHistoryPHtml($symptom_form, data_transCode, data_standard_id, time) {
                var data_time = "",
                    data_unit = "",
                    val,
                    textarea_val;
                if (time) {
                    data_time = time[1];
                    data_unit = time[2];
                }

                //传染病史 疾病史 手术史 输血史中的治疗方式添加文字
                function addText(val) {
                    var arr = val.split("、");

                    $.each(arr, function (i) {
                        arr[i] = arr[i].split("(");
                    });

                    $.each(arr, function (i) {
                        if (Object.prototype.toString.call(arr[i]) === "[object Array]" && arr[i].length === 2) {
                            arr[i] = arr[i][0] + "治疗(" + arr[i][1];
                        }
                    });
                    return arr.join(",");
                }

                //传染病史 疾病史 手术史 输血史确定选择症状的生成规则
                function diseasesHistoryP() {
                    val = $symptom_form.find("div.form-group[data-type=9]").find("input:first").val();
                    text = (time[0] ? (time[0] + "前患有") : "") + text;
                    if (val) {
                        val = addText(val);
                        text += "," + val;
                    }
                    // textarea_val = $symptom_form.find("textarea").val().trim();
                    // textarea_val = (textarea_val ? "[" + textarea_val + "]" : "");
                    // text += textarea_val;
                    return '<p data-relation-id="' + $symptom_form.attr("data-relation-id") + '" ' + ((data_time && data_unit) ? ('data-time="' + data_time + '" data-time-unit="' + data_unit + '"') : "") + ' data-symptom-name="' + title + '" data-transCode="' + data_transCode + '" data-standard-id="' + data_standard_id + '"><span class="dot"></span><span>' + text + '</span><i class="d-n"></i><i></i></p>';
                }

                //接种史确定选择症状的生成规则
                function inoculateP() {
                    val = $symptom_form.find("div.form-group[data-type=9]").find("input:first").val();
                    text = (time[0] ? (time[0] + "前接种") : "") + text + (val ? ("," + val) : "");
                    // textarea_val = $symptom_form.find("textarea").val().trim();
                    // textarea_val = (textarea_val ? "[" + textarea_val + "]" : "");
                    // text += textarea_val;
                    return '<p data-relation-id="' + $symptom_form.attr("data-relation-id") + '" ' + ((data_time && data_unit) ? ('data-time="' + data_time + '" data-time-unit="' + data_unit + '"') : "") + ' data-symptom-name="' + title + '" data-transCode="' + data_transCode + '" data-standard-id="' + data_standard_id + '"><span class="dot"></span><span>' + text + '</span><i class="d-n"></i><i></i></p>';
                }

                //过敏史确定选择症状的生成规则
                function allergicHistoryP() {
                    val = $symptom_form.find("div.form-group[data-type=9]").find("input:first").val();
                    text = (time[0] ? (time[0] + "前") : "") + text + (val ? ("," + val) : "");
                    // textarea_val = $symptom_form.find("textarea").val().trim();
                    // textarea_val = (textarea_val ? "[" + textarea_val + "]" : "");
                    // text += textarea_val;
                    return '<p data-relation-id="' + $symptom_form.attr("data-relation-id") + '" ' + ((data_time && data_unit) ? ('data-time="' + data_time + '" data-time-unit="' + data_unit + '"') : "") + ' data-symptom-name="' + title + '" data-transCode="' + data_transCode + '" data-standard-id="' + data_standard_id + '"><span class="dot"></span><span>' + text + '</span><i class="d-n"></i><i></i></p>';
                }

                switch (data_transCode) {
                    case "SJFL002001":
                    case "SJFL002002":
                        return diseasesHistoryP();
                    case "SJFL002004":
                        return inoculateP();
                    case "SJFL002003":
                        return allergicHistoryP();
                }

            }
        }


        /***添加选择的其他史 ***/
        function addElseHistoryChoose() {
            var text,
                title = text = $symptom_form.children("div.title").children("span").text();
            $p.remove();

            if ($symptom_form.hasClass("disabled")) {
                if ($symptom_form.attr("data-transCode") === "SJFL003001") {
                    text = "无" + title + "家族史";
                } else if ($symptom_form.attr("data-transCode") === "SJFL003004" && $symptom_form.attr("data-relation-id") === "1015") {
                    text = "未婚";
                } else {
                    text = "无" + title;
                }

                $choose.children("div.content").append(generatePHtml($symptom_form.attr("data-relation-id"), text, true, title, data_transCode, data_standard_id)).end().removeClass("d-n");
                return true;
            }
            removeElseChoose($choose, data_transCode);
            $choose.children("div.content").append(generateElseHistoryPHtml($symptom_form, data_transCode, data_standard_id, title)).end().removeClass("d-n");
            return true;


            function generateElseHistoryPHtml($symptom_form, data_transCode, data_standard_id, title) {


                // 家族史
                function familyHistoryP() {
                    var $checkeds = $symptom_form.find("div.checked"),
                        $input = $symptom_form.find("input").not("[data-none]"),
                        val,
                        text = "",
                        textarea = $symptom_form.find("textarea");
                    $checkeds.each(function () {
                        text += "、" + $(this).text().trim();
                    });
                    if ($input.length > 0) {
                        val = $input.val().trim();
                        text += val ? ("、" + val) : "";
                    }
                    text += "患有" + title;
                    if (textarea.length > 0) {
                        val = textarea.val().trim();
                        val ? (text += ("(" + val + ")")) : "";
                    }

                    return '<p data-relation-id="' + $symptom_form.attr("data-relation-id") + '"' + ' data-symptom-name="' + title + '" data-transCode="' + data_transCode + '" data-standard-id="' + data_standard_id + '"><span class="dot"></span><span>' + text.slice(1) + '</span><i class="d-n"></i><i></i></p>';
                }

                // 个人史
                function personalHistoryP() {
                    var $form_groups = $symptom_form.find("div.form-group").not("[data-relation=y]"),
                        $inputs = $form_groups.find("input"),
                        $input,
                        val,
                        text = "",
                        last_text = "";
                    if ($symptom_form.attr("data-relation-id") === "1004" || $symptom_form.attr("data-relation-id") === "1005") {
                        if ($symptom_form.attr("data-relation-id") === "1004") {
                            text = "吸";
                            last_text = '烟';
                            $input = $inputs.filter("[name=question_102]");
                            val = $input.val().trim();
                            text += val ? (text + val) : (text + last_text);
                            text += addControlPText($form_groups.filter("[data-type=7]")).slice(5);
                            $input = $inputs.filter("[name=question_101]");
                            // text += addControlPText($input.parent());
                            text += $input.val() ? (",平均" + $input.val().trim() + "毫升/天") : ("");
                            text += addControlPText($form_groups.find("textarea").parent());
                        } else {
                            text = "饮";
                            last_text = '酒';
                            $input = $inputs.filter("[name=question_74]");
                            val = $input.val().trim();
                            text += val ? (text + val) : (text + last_text);
                            text += addControlPText($form_groups.filter("[data-type=7]")).slice(5);
                            $input = $inputs.filter("[name=question_73]");
                            // text += addControlPText($input.parent());
                            text += $input.val() ? (",平均" + $input.val().trim() + "支/天") : ("");
                            text += addControlPText($form_groups.find("textarea").parent());
                        }
                    } else if ($symptom_form.attr("data-relation-id") === "1012") {
                        var $form_groups = $symptom_form.find("div.form-group");
                        val = addTime($form_groups.filter(":eq(1)").find("div.date-toggle:first"))[0];
                        text = ",受" + $form_groups.filter(":first").children("div.select").children("input").val() + "影响" + (val ? val : "");
                        val = $form_groups.filter(":eq(2)").find("textarea").val().trim();
                        text += val ? ("(" + val + ")") : "";
                    }else if($symptom_form.attr("data-relation-id") === "1013"){
                        var $form_groups = $symptom_form.find("div.form-group");
                        val = addTime($form_groups.filter(":eq(1)").find("div.date-toggle:first"))[0];
                        text =" "+(val ? val : "")+ "前" +$form_groups.filter(":first").children("div.select").children("input").val();
                    }
                    else {
                        $form_groups.each(function () {
                            text += addControlPText($(this));
                        })
                    }


                    return '<p data-relation-id="' + $symptom_form.attr("data-relation-id") + '"' + ' data-symptom-name="' + title + '" data-transCode="' + data_transCode + '" data-standard-id="' + data_standard_id + '"><span class="dot"></span><span>' + text.slice(1) + '</span><i class="d-n"></i><i></i></p>';

                }


                //月经史
                function menstrualHistoryP() {
                    var text = "",
                        $form_groups = $symptom_form.find("div.form-group");
                    $form_groups.each(function () {
                        var $form_group = $(this),
                            _text,
                            $inputs,
                            val;
                        if ($form_group.attr("data-type") !== "7") {
                            text += addControlPText($form_group);
                        } else {
                            _text = "";
                            $inputs = $form_group.find("input");
                            val = $inputs.filter("[name=date_to_year]").val().trim();
                            _text += val ? (val + "年") : "";
                            val = $inputs.filter("[name=date_to_month]").val().trim();
                            _text += val ? (val + "月") : "";
                            val = $inputs.filter("[name=date_to_day]").val().trim();
                            _text += val ? (val + "日") : "";
                            text += _text ? ("," + $form_group.find("label").text() + _text) : "";
                        }
                    });
                    return '<p data-relation-id="' + $symptom_form.attr("data-relation-id") + '"' + ' data-symptom-name="' + title + '" data-transCode="' + data_transCode + '" data-standard-id="' + data_standard_id + '"><span class="dot"></span><span>' + text.slice(1) + '</span><i class="d-n"></i><i></i></p>';
                }

                // 婚姻史
                function maritalHistory() {
                    var $form_groups = $symptom_form.find("div.form-group"),
                        $inputs = $form_groups.find("input"),
                        val,
                        text,
                        $input,
                        has_comma = false;
                    if ($symptom_form.attr("data-relation-id") === "1015") {
                        val = $inputs.filter("[name=question_186]").val().trim();
                        text = val ? ("," + val) : "";
                        text += addControlPText($symptom_form.find("div.form-group[data-type=7]")).slice(5);
                        $input = $inputs.filter("[name=question_188]");
                        val = $input.val().trim();
                        text += val ? (",配偶" + val) : "";
                        if ($input.attr("data-relation-id") && $form_groups.filter("[data-id=" + $input.attr("data-relation-id") + "]").find("input").val().trim()) {
                            text += "(" + $form_groups.filter("[data-id=" + $input.attr("data-relation-id") + "]").find("input").val().trim() + ")";
                        }
                        if ($inputs.filter("[name=question_189]").val().trim() || $inputs.filter("[name=question_190]").val().trim()) {
                            text += ",现有子女:";
                            val = $inputs.filter("[name=question_189]").val().trim();
                            val ? (text += val + "男", has_comma = true) : "";
                            val = $inputs.filter("[name=question_190]").val().trim();
                            text += val ? (has_comma ? ("," + val + "女") : (val + "女")) : "";
                        }
                        val = $form_groups.find("textarea").val().trim();
                        text += val ? ("(" + val + ")") : "";
                    } else {
                        text = "";
                        $form_groups.not("[data-relation=y]").each(function () {
                            text += addControlPText($(this));
                        });
                    }


                    return '<p data-relation-id="' + $symptom_form.attr("data-relation-id") + '"' + ' data-symptom-name="' + title + '" data-transCode="' + data_transCode + '" data-standard-id="' + data_standard_id + '"><span class="dot"></span><span>' + text.slice(1) + '</span><i class="d-n"></i><i></i></p>';
                }


                switch (data_transCode) {
                    case "SJFL003001":
                        return familyHistoryP();
                    case "SJFL003002":
                        return personalHistoryP();
                    case "SJFL003003":
                        return menstrualHistoryP();
                    case "SJFL003004":
                        return maritalHistory();
                }
            }
        }

        /***添加选择的体征 ***/
        function addSignChoose() {
            var text,
                title = text = $symptom_form.children("div.title").children("span").text();
            $p.remove();
            if ($symptom_form.hasClass("disabled")) {
                $choose.children("div.content").append(generatePHtml($symptom_form.attr("data-relation-id"), "无" + title, true, title, data_transCode, data_standard_id)).end().removeClass("d-n");
                $choose.click();
                return true;
            }
            $choose.children("div.content").append(signP($symptom_form)).end().removeClass("d-n");
            $choose.click();
            return true;


            function signP($symptom_form) {
                var $form_groups = $symptom_form.children("div.form-group").not("[data-relation=y]"),
                    _text = "";
                $form_groups.each(function () {
                    var $form_group = $(this);
                    if ($form_group.css("display") !== "none") {
                        _text += addControlPText($form_group);
                        // console.log(_text);
                    }
                });
                if (_text[0] !== "(") {
                    _text = _text.slice(1);
                }
                if (_text !== "") {
                    _text = ":" + _text;
                }

                text = text + _text;

                return '<p data-relation-id="' + $symptom_form.attr("data-relation-id") + '"' + ' data-symptom-name="' + title + '" data-standard-id="' + $symptom_form.attr("data-standard-id") + '"><span class="dot"></span><span>' + text + '</span><i class="d-n"></i><i></i></p>';
            }

        }


        function addControlPText($form_group) {

            function addPreAndNextText($node, val) {
                var text = "",
                    maxValue = parseInt($node.attr("data-maxValue")),
                    minValue = parseInt($node.attr("data-minValue")),
                    has_span_finish = false;

                if (!val) {
                    return text;
                }

                if ($node.attr("data-labelprefix")) {
                    text += $node.attr("data-labelprefix");
                }

                if ((maxValue && val > maxValue) || (minValue && val < minValue)) {
                    val = '<span style="color: red;">' + val;
                    has_span_finish = true;
                }

                text += val;
                if ($node.attr("data-labelsuffix")) {
                    text += $node.attr("data-labelsuffix");
                }

                if (has_span_finish) {
                    text += '</span>';
                }

                return text;
            }


            function textControl($inputs, $selects) {
                var text = "",
                    is_relation = false;

                if ($form_group.attr("data-relation") === "y") {
                    is_relation = true;
                }


                $form_group.children().not("span").each(function () {
                    var $child = $(this);
                    if ($child.is("input")) {
                        text += addPreAndNextText($child, $child.val().trim());
                    } else if ($child.is(".select")) {
                        text += addPreAndNextText($child, $child.children("input").val().trim());
                    } else {
                        text += addPreAndNextText($child, $child.text().trim());
                    }
                });

                if (is_relation) {
                    text = (text.trim() ? ( "," + "(" + text + ")" ).slice(1).trim() : "");
                } else {
                    text = (text.trim() ? ( "," + text ) : "");
                }

                text = text.trim().length === $form_group.children("label").text().trim().length + 1 ? "" : text;

                return text;
            }

            function dateControl($date_toggle) {
                var title = $form_group.children("label").text().replace("*", "");
                return addTime($date_toggle)[0] ? ("," + title + addTime($date_toggle)[0]) : ("");
            }

            function checkboxAndRadioControl($checkeds) {
                var title = $form_group.children("label").text().replace("*", ""),
                    text = "",
                    left = "、",
                    is_relation = false;

                $checkeds.each(function () {
                    var $checkbox = $(this),
                        $form_siblings,
                        $relation_forms = $();
                    text += left + $checkbox.text();
                    //console.log(text);
                    if ($checkbox.attr("data-relation-id") !== "-1") {
                        text += "[";
                        $form_siblings = $checkbox.parent().siblings("[data-relation=y]");
                        $.each($checkbox.attr("data-relation-id").split(","), function () {
                            $relation_forms = $relation_forms.add($form_siblings.filter("[data-id=" + this + "]"));
                        });
                        $relation_forms.each(function () {
                            text += addControlPText($(this));
                        });
                        text += "]";
                    }
                });
                text = text.slice(1);
                if (text) {
                    text = title ? (title + ":" + text) : "" + text;
                }
                if (is_relation) {
                    text = (text.trim() ? ("," + text).slice(1) : "");
                } else {
                    text = (text.trim() ? ("," + text) : "");
                }
                return text;
            }

            function textareaControl($textarea) {
                var val = $textarea.val().trim(),
                    text = "";
                if (val !== "") {
                    text += "(" + val + ")";
                }
                return text;
            }

            function selectControl($select) {
                var title = $select.siblings("label").text().replace("*", ""),
                    text = "",
                    $input = $select.children("input"),
                    $pre_inputs,
                    $next_inputs,
                    pre_text = "",
                    $sibling_relation_forms = $select.parent().siblings("[data-relation=y]"),
                    $all_inputs,
                    flag = false,
                    $relation_forms = $();
                if ($input.length > 0) {
                    text += addPreAndNextText($input, $input.val());
                }
                $pre_inputs = $select.prevAll("input");
                $next_inputs = $select.nextAll("input");
                $pre_inputs.each(function () {
                    var $input = $(this);
                    pre_text += addPreAndNextText($input, $input.val());
                });
                $next_inputs.each(function () {
                    var $input = $(this);
                    text += addPreAndNextText($input, $input.val());
                });
                $all_inputs = $input.add($pre_inputs).add($next_inputs);
                $all_inputs.each(function () {
                    if ($(this).val().trim() === "") {
                        flag = true;
                        return false;
                    }
                });

                if (flag) {
                    return "";
                }

                if ($input.attr("data-order") === "last") {
                    text += title;
                } else {
                    text = title + text;
                }

                if ($input.attr("data-relation-id")) {
                    $.each($input.attr("data-relation-id").split(","), function () {
                        $relation_forms = $relation_forms.add($sibling_relation_forms);
                    });
                    $relation_forms.each(function () {
                        text += addControlPText($(this));
                    });
                }

                text = (text.trim().length > title.trim().length ? ("," + text ) : "");
                return text;
            }

            switch ($form_group.attr('data-type')) {
                case "2":
                    return textControl($form_group.children("input"), $form_group.children("div.select"));
                case "7":
                    return dateControl($form_group.find("div.date-toggle"));
                case "3":
                case "8":
                case "10":
                    return selectControl($form_group.children("div.select"));
                case "4":
                case "5":
                    return checkboxAndRadioControl($form_group.children("div.c-r.checked"));
                case "6":
                    return textareaControl($form_group.find("textarea"));
            }
        }

        /*** 将时间控件信息格式化成文本 ***/
        function addTime($date_toggle) {
            var span_index = $date_toggle.data("span_index"),
                $div,
                $inputs,
                text,
                symptom_date = new Date(),
                now_date = new Date().getTime(),
                date,
                month_unit,
                month,
                day,
                text_first = "";

            if (span_index === 1) {
                $div = $date_toggle.siblings("div").eq(1);
                $inputs = $div.find("input");
                month = parseInt($inputs.eq(1).val().trim()) - 1 || 0;
                day = parseInt($inputs.eq(2).val().trim()) || 0;
                symptom_date.setFullYear($inputs.eq(0).val().trim(), month, day);
                symptom_date.setHours(0);
                symptom_date.setMinutes(0);
                symptom_date.setSeconds(0);
                symptom_date.setMilliseconds(0);
                date = getDateUnit(parseInt((now_date - symptom_date) / 1000 / 60));
                $.each(date[0], function (i, v) {
                    text_first += v + date[1][i];
                });
                text = [text_first, date[0].join(","), date[1].join(",")];
            } else {
                $div = $date_toggle.siblings("div").eq(0);
                $inputs = $div.find("input");
                month_unit = $inputs.eq(1).val() === "月" ? "个月" : $inputs.eq(1).val() ? $inputs.eq(1).val() : "天";
                text = [$inputs.eq(0).val() + month_unit, $inputs.eq(0).val(), month_unit];

            }
            if ($inputs.eq(0).val().trim() === "") {
                text = ["", "", ""];
            }
            //console.log(span_index);
            return text;


            function getDateUnit(dateTime) {
                var unit = [],
                    date = [];
                dateTime = dateTime / 60;
                if (dateTime < 24) {
                    date.push(Math.round(dateTime));
                    unit.push("小时");
                    return [date, unit];
                }
                if (dateTime >= 24 && dateTime < 168) {
                    date.push(Math.round(dateTime / 24));
                    unit.push("天");
                    return [date, unit];
                }
                dateTime /= 24;
                if (dateTime >= 7 && dateTime < 30) {
                    date.push(Math.floor(dateTime / 7));
                    unit.push("周");
                    dateTime %= 7;
                    if (dateTime >= 1) {
                        date.push(Math.round(dateTime));
                        unit.push("天");
                    }
                    return [date, unit];
                }
                if (dateTime >= 30 && dateTime < 365) {
                    date.push(Math.floor(dateTime / 30));
                    unit.push("个月");

                    dateTime %= 30;
                    if (dateTime >= 7) {
                        date.push(Math.floor(dateTime / 7));
                        unit.push("周");
                        dateTime %= 7;
                    }
                    if (dateTime >= 1) {
                        date.push(Math.round(dateTime));
                        unit.push("天");
                    }

                    return [date, unit];
                }
                if (dateTime >= 365) {
                    date.push(Math.floor(dateTime / 365));
                    unit.push("年");
                    dateTime %= 365;
                    if (dateTime >= 30) {
                        date.push(Math.floor(dateTime / 30));
                        unit.push("个月");
                        dateTime %= 30;
                    }
                    if (dateTime >= 7) {
                        date.push(Math.floor(dateTime / 7));
                        unit.push("周");
                        dateTime %= 7;
                    }
                    if (dateTime >= 1) {
                        date.push(Math.round(dateTime));
                        unit.push("天");
                    }
                    return [date, unit];
                }

            }
        }

        function changeP($p, data_id, text, none_flag, title, data_transCode, data_standard_id, time) {
            var data_time = "",
                data_unit = "";
            if (time) {
                data_time = time[1];
                data_unit = time[2];
            }

            (data_time && data_unit) ? ($p.attr("data-time", data_time), $p.attr("data-time-unit", data_unit)) : "";
            data_transCode ? $p.attr("data-transCode", data_transCode) : "";
            data_standard_id ? $p.attr("data-standard-id", data_standard_id) : "";
            $p.children("span:last").html(text);
        }


        function generatePHtml(data_id, text, none_flag, title, data_transCode, data_standard_id, time) {
            var data_time = "",
                data_unit = "";
            if (time) {
                data_time = time[1];
                data_unit = time[2];
            }

            return '<p data-relation-id="' + data_id + '" ' + ((data_time && data_unit) ? ('data-time="' + data_time + '" data-time-unit="' + data_unit + '"') : "") + ' data-symptom-name="' + title + '" ' + (data_transCode ? ('data-transCode="' + data_transCode + '"') : "") + (data_standard_id ? ('data-standard-id="' + data_standard_id + '"') : "") + '><span class="dot"></span><span>' + text + '</span><i class="' + (none_flag ? "d-n" : "") + '"></i><i></i></p>';
        }

    }


    /*** 校验时间是否填写 ***/
    function validateDate($date_toggle) {
        if(!$date_toggle.closest('.form-group').is('[data-require=1]')){
            return;
        }

        var $validate = $date_toggle.siblings("div.validate"),
            span_index = $date_toggle.children("span.focus").index(),
            $input = $date_toggle.siblings("div.date").eq(span_index - 1).find("input:first"),
            validate = true,
            val = parseInt($input.val());
        if (val !== val || val <= 0) {
            validate = false;
            $input.val("");
        }
        if (validate) {
            $validate.addClass("d-n");
        } else {
            $validate.removeClass("d-n");
        }
        return validate;
    }


    // 切换select下的ul出现和隐藏
    function ulToggle($select, $ul) {
        if ($select.hasClass("focus") && $ul.css("display") === "block") {
            $ul.slideUp();
        } else {
            $select.addClass("focus");
            $ul.slideDown();
        }
    }

    //移除焦点
    function removeFocus() {
        $("div.select.focus").removeClass("focus");
    }

    /*** 隐藏select ***/
    function selectHide($select) {
        $("div.select.box-r-s").not($select).removeClass("focus").children("ul").fadeOut();
    }

    /*** 隐藏group ***/
    function groupHide($group) {
        var $groups = $("div.group").not($group),
            $symptom_container = $groups.find("div.symptom-container"),
            $symptom_form = $symptom_container.children("div.symptom-form:visible"),
            $symptom_all = $symptom_container.siblings("div.symptom-all");
        if ($symptom_form.length > 0) {
            $symptom_form.fadeOut(function () {
                $(this).parent().removeClass("symptom").hide();
            });
        } else {
            $symptom_container.removeClass("symptom").fadeOut();
            $symptom_all.fadeOut();
        }
        $groups.removeClass("focus");
    }

    /*** 隐藏group ***/
    function documentGroupHide($group) {
        var $groups = $("div.group").not($group),
            $symptom_containers = $groups.find("div.symptom-container"),
            $symptom_form = $symptom_containers.children("div.symptom-form:visible"),
            $symptom_alls = $symptom_containers.siblings("div.symptom-all:visible");
        // console.log($symptom_alls.length);
        if($symptom_form.length > 0){
            return;
        } else if ($symptom_alls.length === 0) {
            $symptom_containers.removeClass("symptom").fadeOut();
        } else {
            $symptom_alls.each(function () {
                var $symptom_all = $(this);
                $symptom_all.slideUp();
                $symptom_all.siblings('div.symptom-container').removeClass("symptom").show();
            });
        }
        $groups.removeClass("focus");
    }


    /*** 日期控件切换日期和天数 ***/
    function dateControlToggle($date_toggle, $span, callback) {
        var $background_color = $date_toggle.children("div.background-color"),
            index = $span.add($span.siblings("span")).index($span),
            callback = callback || function () {
                };

        var fn = {
            "0": function () {
                $background_color.animate({"left": 0, "width": "72px"}, 200, "swing", function () {
                    $background_color.animate({"width": "37px"}, 200, "swing", function () {
                        $span.siblings("span").removeClass("focus");
                        callback();
                    });
                });
            },
            "1": function () {
                $background_color.animate({"width": "72px"}, 200, "swing", function () {
                    $background_color.animate({"width": "37px", "left": "36px"}, 200, "swing", function () {
                        $span.siblings("span").removeClass("focus");
                        callback();
                    });
                });
            }
        };
        if (!$span.hasClass("focus")) {
            $date_toggle.siblings("div.date").eq(1 - index).hide().end().eq(index).fadeIn();
            $date_toggle.data("span_index", index);
            $span.addClass("focus");
            fn[index]();
        }
    }

    /*** 给单选框添加改变选择状态 ***/
    function radioChangeState($radio) {
        if (!$radio.hasClass("checked")) {
            $radio.addClass("checked").siblings("div.c-r.radio").removeClass("checked");
        }
    }

    /*** 给复选框添加改变选择状态 ***/
    function checkboxChangeState($checkbox) {
        $checkbox.hasClass("checked") ? $checkbox.removeClass("checked") : $checkbox.addClass("checked");
    }

    /*** 让复选框关联的formgroup显示或隐藏 ***/
    function showRelationFormGroup($checkbox, flag) {
        if (flag) {
            $checkbox.parent().siblings("div.form-group[data-relation=y]").hide();
        }

        if ($checkbox.attr("data-relation-id")) {
            var relation_arr = $checkbox.attr("data-relation-id").split(",");
            if (relation_arr.length > 0 && relation_arr[0] !== "") {
                $.each(relation_arr, function (i, v) {
                    var $check_boxes = $checkbox.parent().find("div.c-r[data-relation-id*=" + v + "]"),
                        $arr = $(""),
                        $relations = $checkbox.parent().siblings("div.form-group[data-id=" + v + "]"),
                        index;
                    $check_boxes.each(function () {
                        var _$checkbox = $(this),
                            arr = _$checkbox.attr("data-relation-id").split(",");
                        $.each(arr, function (i3, v3) {
                            if (v3 === v) {
                                $arr = $arr.add(_$checkbox);
                            }
                        });
                    });
                    index = $arr.index($checkbox);
                    if (flag) {
                        $relations.hide().eq(index).show();
                    } else {
                        $checkbox.hasClass("checked") ? $relations.eq(index).show() : $relations.eq(index).hide();
                    }
                });
            }
        }
    }

    /*** 点击症状填写的取消返回上一步 ***/
    function cancelReturnLastStep($choose) {
        var $symptom_container = $choose.siblings("div.symptom-container:first"),
            $symptom_all = $choose.siblings("div.symptom-all:first");
        if ($symptom_all.is(":visible")) {
            $choose.data("lastStep", "symptom-all");
        } else if ($symptom_container.children("div.symptom-wrap").is(":visible")) {
            $choose.data("lastStep", "symptom-wrap");
        } else {
            $choose.data("lastStep", "");
        }
    }

    /*** 单选下拉框的点击事件 ***/
    function selectSingleChoiceByText($a) {
        var $text = $a.parents("div.select:first").children("input[type=text]"),
            $form_group = $a.parents("div.form-group:first"),
            text;
        if ($a.siblings("ul").length === 0) {
            if ($a.children().length > 0 && $a.children(":first").prop("nodeName") === "INPUT") {
                $text.val($a.children("input").val());
                $text.attr("data-param-value", "");
            } else if ($a[0].firstChild && $a[0].firstChild.nodeName === "#text") {
                if ($form_group.attr("data-select") === "all") {
                    text = $a[0].firstChild.data;
                    $text.attr("data-param-value", $a.attr("data-param-code"));
                    $a = $a.parents("ul:first").siblings("a");
                    while ($a.length > 0) {
                        text = $a[0].firstChild.data + text;
                        $a = $a.parents("ul:first").siblings("a");
                    }
                    $text.val(text);

                } else {
                    $text.val($a[0].firstChild.data);
                    $text.attr("data-param-value", $a.attr("data-param-code"));
                }
            }
        }
    }

    /*** 复选下拉框的复选框选择 ***/
    function selectMultipleChoiceByCheckbox($a) {
        var $checkbox = $a.children("div.checkbox"),
            $select = $a.parent().parent().parent(),
            $text = $select.children("input[type=text]"),
            param_val = "";
        $checkbox.hasClass("checked") ? $checkbox.siblings().removeClass("d-n") : $checkbox.siblings().addClass("d-n");
        $text.val(getSelectText($select));
        $a.parents("ul").find("div.checkbox.checked").each(function () {
            param_val += "^" + $(this).attr("data-param-code");
        });
        $text.attr("data-param-value", param_val.slice(1));
    }

    /*** 给复选框添加文本 ***/
    function getSelectText($select) {
        var text = "";
        $select.find("a").each(function () {
            var $a = $(this),
                $checkbox = $a.children("div.checkbox"),
                $siblings = $checkbox.siblings(),
                val,
                $input = $a.children("input");
            if ($checkbox.hasClass("checked")) {
                if ($siblings.hasClass("radio")) {
                    text += $siblings.filter(".checked").text().trim() + $checkbox.text().trim() + ",";
                } else if ($siblings.eq(0).hasClass("box-r-s")) {
                    val = "(" + $siblings.eq(0).val().trim() + ")";
                    text += $checkbox.text().trim() + (val === "()" ? "" : val) + "、";
                } else if ($siblings.length === 0) {
                    text += $checkbox.text().trim() + "、";
                }
            }
            if ($checkbox.length === 0 && $input.length > 0) {
                val = $input.val().trim();
                text += val ? (val + "、") : "";
            }
        });
        return text.slice(0, -1);
    }

    //给对应的症状加上已经选择的焦点
    function addSymptomFocus($symptom_container) {
        var $ps = $symptom_container.siblings("div.choose").children("p");
        $ps.each(function () {
            var $p = $(this);
            $symptom_container.children("div.symptom-wrap").find("div.symptom[data-id=" + $p.attr("data-relation-id") + "]").addClass("focus");
        });
    }

    /*information*/
    function informationControlToggle() {
        $(".information-list > ul > li").click(function () {
            $(this).addClass("focus");
            $(this).siblings().removeClass("focus");
            var top = $(this).attr("tagTop");
            $(".information-main").scrollTop(top);
        });


        $(".information-list .information-link").each(function (index) {
            if (index == 0) {
                $(this).attr("tagTop", 0);
                return true
            }
            $(this).attr("tagTop", $(".information-content dl:eq(" + (index - 1) + ")").height());
        });


        $(".information-main").scroll(function () {
            var top = $(this).scrollTop();
            var focustop = $(".information-list .information-link.focus").attr("tagtop");
            if (top > focustop) {
                var $next = $(".information-list .information-link.focus").nextAll(".information-link:first");
                if (top > $next.attr("tagtop")) {
                    $next.addClass("focus").siblings().removeClass("focus");
                    return false
                }
            } else if (top < focustop) {
                var $prev = $(".information-list .information-link.focus").prevAll(".information-link:first");
                if (top <= $prev.attr("tagtop")) {
                    $prev.addClass("focus").siblings().removeClass("focus");
                    return false
                }
            }
        });
    }

    /*information*/

    /*** 隐藏select-only ***/
    function selectOnlyHide($select) {
        $("div.select-only").not($select).children("ul").fadeOut();
    }

    /*** 给最末尾的choose添加底边 ***/
    function addChooseBottom($choose) {
        $choose = $choose.add($choose.siblings("div.choose")).removeAttr("style").filter(":visible");
        if ($choose.children().length > 0) {
            $choose.eq($choose.length - 1).css({
                "borderBottomStyle": "solid",
                "borderBottomWidth": "1px",
                "borderBottomColor": "#eee"
            });
        }
    }

    function getOuterHeight($nodes) {
        var height = 0;
        $nodes.each(function () {
            height += $(this).outerHeight(true);
        });
        return height;
    }

    /*** 控制页面的自动滚动的动画效果  $scroll_node 滚动的jQ对象 比如body ,$move_node 要以此为基础进行滚动的jQ对象 如 group ,top_offset 滚动的偏移量 因为默认是会让$move_node滚动到最高点 可以增加top_offset来更改,$context 环境 也就是$scroll_node之下那个长的节点 比如container***/
    function autoScrollY($scroll_node, $move_node, top_offset, $context) {
        var context_height, move_distance, children_height, move_offset;
        $context = $context || $("#container");
        top_offset = (top_offset + 70) || 0;
        context_height = $context.outerHeight(true);
        move_offset = $move_node.offset();
        move_distance = move_offset.top + top_offset - $scroll_node.scrollTop();
        children_height = getOuterHeight($context.children());

        if ($scroll_node.scrollTop() + $scroll_node.outerHeight(true) + move_distance >= context_height) {
            $context.css("height", $scroll_node.scrollTop() + $scroll_node.outerHeight(true) + move_distance);
            $scroll_node.animate({
                "scrollTop": move_offset.top + top_offset
            }, "slow", "swing");
            // console.log("111");
        } else if ($scroll_node.scrollTop() + $scroll_node.outerHeight(true) + move_distance > children_height) {
            $scroll_node.animate({
                "scrollTop": move_offset.top + top_offset
            }, "slow", "swing", function () {
                $context.css("height", $scroll_node.scrollTop() + $scroll_node.outerHeight(true));
            });
            // console.log("222");
        } else {
            $scroll_node.animate({
                "scrollTop": move_offset.top + top_offset
            }, "slow", "swing", function () {
                $context.css("height", "auto");
            });
        }
    }

    function allIsNull() {
        var count = 0;
        $(".choose").each(function () {
            count += $(this).find(".content").children().length;
        });
        if (count > 0) {
            return false;
        } else if (count == 0) {
            return true;
        }
    }

    /*** 保存单个控件数据的方法 返回单个控件输出数据的对象 ***/
    function saveControl($form_group) {
        function saveInput($form_group) {
            var $inputs = $form_group.find("input,textarea").filter(function () {
                    return $(this).css("display") !== "none";
                }),
                obj = {
                    "id": $form_group.attr("data-id"),
                    "type": $form_group.attr("data-type")
                };

            if ($inputs.length === 0) {
                return "";
            }
            $inputs.each(function () {
                var $input = $(this),
                    val = $input.val().trim();
                if ($input.attr("name") && val !== "") {
                    obj[$input.attr("name")] = val;
                }
            });

            return obj;
        }

        function saveChecked($form_group) {
            var $checkeds = $form_group.children(".checked"),
                name = $checkeds.filter(":first").attr("name"),
                $inputs = $form_group.children("input[data-none!=true]"),
                obj = {
                    "id": $form_group.attr("data-id"),
                    "type": $form_group.attr("data-type")
                },
                text = "";

            if ($checkeds.length === 0) {
                return "";
            }

            $checkeds.each(function () {
                var $checked = $(this);
                text += $checked.attr("data-id") + ",";
            });

            if ($inputs.length > 0) {
                $inputs.each(function () {
                    var $input = $(this);
                    obj[$input.attr("name")] = $input.val();
                });
            }


            obj[name] = text.slice(0, -1);
            return obj;
        }


        function saveDateControl($form_group) {
            var span_index = $form_group.find(".date-toggle").children("span.focus").index(),
                $inputs = $form_group.find("div.date").eq(span_index - 1).find("input"),
                obj = {
                    "id": $form_group.attr("data-id"),
                    "type": $form_group.attr("data-type"),
                    "date_toggle": span_index
                };

            $inputs.each(function () {
                var $input = $(this),
                    val = $input.val().trim();
                if (val) {
                    obj[$input.attr("name")] = val;
                }
            });
            return obj;
        }

        function saveSelectChecked($form_group) {
            var $checkeds = $form_group.find("div.c-r.checked"),
                name = $checkeds.eq(0).attr("name"),
                obj = {
                    "id": $form_group.attr("data-id"),
                    "type": $form_group.attr("data-type")
                },
                id = "",
                text = "",
                has_input = false;
            if ($checkeds.length === 0) {
                return "";
            }

            if ($form_group.attr("data-type") === "9") {
                has_input = true;
            }

            $checkeds.each(function () {
                var $checked = $(this);
                id += $checked.attr("data-id") + ",";
                if (has_input) {
                    text += $checked.siblings("input").val().trim() + ",";
                }
            });

            obj[name] = id.slice(0, -1);

            if (has_input) {
                obj["text"] = text.slice(0, -1);
            }

            return obj;
        }

        function saveUl($form_group) {
            var $input = $form_group.find("input:first"),
                obj = {
                    "id": $form_group.attr("data-id"),
                    "type": $form_group.attr("data-type")
                },
                input_val = $input.val(),
                $as = $form_group.children("div.select").children("ul").find("a");

            $as.each(function () {
                var $a = $(this),
                    val = $a.text();
                if (val.trim() === input_val.trim()) {
                    obj[$input.attr("name")] = $a.attr("data-id");
                    return false;
                }
            });


            return obj;
        }

        switch ($form_group.attr("data-type")) {
            case "2":
            case "6":
                return saveInput($form_group);
            case "10":
            case "3":
                return saveUl($form_group);
            case "4":
            case "5":
                return saveChecked($form_group);
            case "8":
            case "9":
                return saveSelectChecked($form_group);
            case "7":
                return saveDateControl($form_group);
        }
    }

    /*** 保存一个动态表单数据的方法 返回一个动态表单输出数据的对象 ***/
    function saveSymptomForm($symptom_form) {
        var $title = $symptom_form.find("div.title"),
            obj = {
                "itemId": $symptom_form.attr("data-relation-id"),
                "title": {
                    "itemName": $title.children("span").text(),
                    "checkboxClass": $title.children(".checkbox").attr("class")
                },
                "contents": []
            },
            $controls = $symptom_form.children("div.form-group");

        $controls.each(function () {
            var control = saveControl($(this));
            if (control) {
                obj.contents.push(control);
            }

        });
        return obj;
    }

    /*** 保存一个group的方法 返回一个group输出数据的对象  ***/
    function saveSymptomGroup($group) {
        var index = $group.index(),
            $symptom_forms = $group.find("div.symptom-form"),
            $ps = $group.find("div.choose").find("p"),
            obj = {
                "type": index,
                "choose": [],
                "details": []
            },
            choose_index,
            $else_ps = $group.find("div.symptom-else:first").find("p.symptom.focus");

        if ($ps.length === 0) {
            return obj;
        }

        if (index === 0) {
            choose_index = 1;
        } else if (index === 1) {
            choose_index = 9;
        } else {
            choose_index = index;
        }


        $ps.each(function () {
            var $p = $(this),
                symptom_title = "",
                $title = $p.parent().siblings(".title");
            if ($title.length > 0) {
                symptom_title = $title.children("span").text().slice(0, -1);
            }
            obj.choose.push({
                "itemId": $p.attr("data-relation-id"),
                "itemName": $p.attr("data-symptom-name"),
                "itemDescribe": $p.text() + (symptom_title ? ("$title$" + symptom_title) : symptom_title),
                "type": choose_index,
                "remark": ""
            });

            obj.details.push(saveSymptomForm($symptom_forms.filter("[data-relation-id=" + $p.attr('data-relation-id') + "]")));
        });


        if ($else_ps.length > 0) {
            obj.symptomElse = [];
            $else_ps.each(function () {
                obj.symptomElse.push($(this).attr("data-transCode"));
            });
        }

        //console.log(obj);
        return obj;
    }

    function savePatBody() {
        var obj = {
            "toPat": {
                "name": Param.patientName,
                "idNo": Param.idNo,
                "idType": Param.idType,
                "id": Param.recordId
            }
        }
        return obj;
    }

    function saveHistoryBody($groups) {
        var obj = {
                "toHistory": [
                    {
                        "itemDescribe": "",
                        "type": 1
                    },
                    {
                        "itemDescribe": "",
                        "type": 2
                    },
                    {
                        "itemDescribe": "",
                        "type": 3
                    },
                    {
                        "itemDescribe": "",
                        "type": 4
                    },
                    {
                        "itemDescribe": "",
                        "type": 5
                    },
                    {
                        "itemDescribe": "",
                        "type": 9
                    }
                ]
            },
            obj2 = {
                "toHistory": []
            };

        $groups.eq(0).find("div.choose p").each(function () {
            obj.toHistory[0].itemDescribe += $(this).text() + "&";
        });
        $groups.eq(1).find("div.choose p").each(function () {
            obj.toHistory[1].itemDescribe += $(this).text() + "&";
        });
        $groups.eq(2).find("div.choose p").each(function () {
            obj.toHistory[4].itemDescribe += $(this).text() + "&";
        });
        $groups.eq(3).find("div.choose p").each(function () {
            obj.toHistory[3].itemDescribe += $(this).text() + "&";
        });
        $groups.eq(4).find("div.choose p").each(function () {
            obj.toHistory[2].itemDescribe += $(this).text() + "&";
        });
        $.each(obj.toHistory, function () {
            if (this.itemDescribe !== "") {
                this.itemDescribe = this.itemDescribe.slice(0, -1);
                obj2.toHistory.push(this);
            }
        });

        return obj2;
    }

    /*** 恢复单个表单数据的方法  ***/
    function recoverSymptomForm($symptom_form, detail) {
        // console.log(detail);
        var $form_groups = $symptom_form.find("div.form-group");
        $symptom_form.children(".title").children(".c-r").addClass(detail.title.checkboxClass);
        $.each(detail.contents, function () {
            if (this.type === "7") {
                recoverDateControl($form_groups.filter("[data-id=" + this.id + "][data-type=7]"), this);
            } else if (this.type === "4" || this.type === "5") {
                recoverChecked($form_groups.filter("[data-id=" + this.id + "][data-type=" + this.type + "]"), this);
            } else if (this.type === "8" || this.type === "9") {
                recoverSelectCheckbox($form_groups.filter("[data-id=" + this.id + "][data-type=" + this.type + "]"), this);
            } else if (this.type === "3" || this.type === "10") {
                recoverSelectUl($form_groups.filter("[data-id=" + this.id + "][data-type=" + this.type + "]"), this);
            } else {
                recoverInput($form_groups.filter("[data-id=" + this.id + "][data-type=" + this.type + "]"), this);
            }
        });
        $symptom_form.find("button.confirm").removeClass("disabled").click();

        function recoverInput($form_group, content) {
            var $inputs = $form_group.find("input,textarea").not(function () {
                return $(this).attr("data-none") === "true";
            });
            $inputs.each(function () {
                var $input = $(this);
                $input.val(content[$input.attr("name")] ? content[$input.attr("name")] : "");
            });
        }

        function recoverSelectUl($form_group, content) {
            var $input = $form_group.find("input"),
                $as = $form_group.children("div.select").children("ul").find("a");
            if (content[$input.attr("name")]) {
                $as.filter("[data-id=" + content[$input.attr("name")] + "]").click();
            }
        }

        function recoverSelectCheckbox($form_group, content) {
            var $checkeds = $form_group.find("div.c-r"),
                name = $checkeds.eq(0).attr("name"),
                text,
                has_input = false;

            if (!content[name]) {
                return;
            }

            if ($checkeds.eq(0).siblings("input").length > 0) {
                has_input = true;
                text = content['text'].split(",");
            }

            $.each(content[name].split(","), function (i) {
                var $checked = $checkeds.filter("[data-id=" + this + "]");
                $checked.click();
                if (has_input) {
                    $checked.siblings("input").val(text[i]).trigger("input").trigger("propertychange");
                }

            });
        }


        function recoverDateControl($form_group, content) {
            if (content["date_toggle"] === 1) {
                $form_group.find("div.date-toggle").find("span:first").click();
                addFormGroupInputVal($form_group, content["date_one_time"], "date_one_time");
                addFormGroupInputVal($form_group, content["date_one_unit"], "date_one_unit");

            } else if (content["date_toggle"] === 2) {
                $form_group.find("div.date-toggle").find("span:last").click();
                addFormGroupInputVal($form_group, content["date_to_year"], "date_to_year");
                addFormGroupInputVal($form_group, content["date_to_month"], "date_to_month");
                addFormGroupInputVal($form_group, content["date_to_day"], "date_to_day");
            }

        }


        function recoverChecked($form_group, content) {
            var $checks = $form_group.children("div.c-r"),
                $inputs = $form_group.children("input[data-none!=true]"),
                name = $checks.eq(0).attr("name");
            if (content[name]) {
                $.each(content[name].split(","), function () {
                    $checks.filter("[data-id=" + this + "]").click();
                });
            }
            if ($inputs.length > 0) {
                $inputs.each(function () {
                    var $input = $(this);
                    $input.val(content[$input.attr("name")] ? (content[$input.attr("name")]) : "");
                });
            }
        }
    }

    /*** 恢复表单中对应name的input加入数据的方法  ***/
    function addFormGroupInputVal($form_group, val, name) {
        if (val) {
            $form_group.find("input[name=" + name + "]").val(val);
        }
    }


    function recoverGroup(data) {

        var $group = $("div.group").eq(data.type),
            $symptom_forms = $group.find("div.symptom-form"),
            $else_ps,
            $else_checkbox;

        if (data['symptomElse'] && data.symptomElse.length > 0) {
            $else_checkbox = $group.find("div.symptom-else:first").children("div.checkbox:first");
            $else_ps = $else_checkbox.siblings("p.symptom");
            $else_checkbox.click(); // FIXME: 由于后端主诉和现病史的type一样 因此恢复数据必须主诉单独恢复 后在恢复中调用click()会主诉恢复调用一次   现病史等疾病恢复又调用一次

            $else_checkbox.addClass("checked");
            $else_ps.removeClass("d-n");
            $.each(data.symptomElse, function (i, v) {
                $else_ps.filter("[data-transCode=" + v + "]").click();
            });
        }
        if (data.details.length > 0) {
            $.each(data.details, function () {
                var $symptom_form = $symptom_forms.filter("[data-relation-id=" + this.itemId + "]");
                if ($symptom_form.length > 0) {
                    recoverSymptomForm($symptom_form, this);
                }
            });
        }
    }


    function getSymptomForBasis($group) {
        var index = $group.index(),
            $ps = $group.find("div.choose").find("p"),
            ob = {};

        if ($ps.length === 0) {
            //return "";
        }

        $ps.each(function () {
            var $now_form = $group.find("div.symptom-form[data-relation-id=" + $(this).attr("data-relation-id") + "]");
            var obj = {}
            $now_form.find("input[data-param-code],textarea[data-param-code]").each(function () {
                obj[$(this).attr("data-param-code")] = $(this).val();
            });

            $now_form.find(".checkbox[data-param-code]").each(function () {
                obj[$(this).attr("data-param-code")] = $(this).attr("data-id");
            });

            $now_form.find(".radio[data-param-code]").each(function () {
                obj[$(this).attr("data-param-code")] = $(this).attr("data-id");
            });

            ob[$now_form.attr("data-param-code")] = obj;
        });
        return jsonTOstring(ob);
    }

    function getSymptomFormForBasis($symptom_form) {
        var $controls = $symptom_form.find("div.form-group"),
            obj = {};
        $controls.each(function () {
            var $form_group = $(this);
            if ($form_group.css("display") !== "none") {
                if ($form_group.attr("data-type") === "2" || $form_group.attr("data-type") === "6") {
                    setText($form_group);
                } else if ($form_group.attr("data-type") === "4" || $form_group.attr("data-type") === "5") {
                    setChecked($form_group);
                } else if ($form_group.attr("data-type") === "3" || $form_group.attr("data-type") === "8" || $form_group.attr("data-type") === "9" || $form_group.attr("data-type") === "10") {
                    setSelect($form_group);
                } else if ($form_group.attr("data-type") === "7") {
                    setDate($form_group);
                }
            }
        });
        return obj;


        function setText($form_group) {
            var $inputs = $form_group.children("input,textarea");
            $inputs.each(function () {
                var $input = $(this),
                    val = $input.val().trim();
                if (val) {
                    obj[$input.attr("data-param-code")] = val;
                }
            });
        }


        function setChecked($form_group) {
            var $inputs = $form_group.children("input");
            $inputs.each(function () {
                var $input = $(this);
                if ($input.is("[data-none]") && $input.attr("data-param-value") && $input.attr("data-param-value") !== "undefined" && $input.attr("data-param-value") !== "null") {
                    obj[$input.attr("data-param-code")] = $input.attr("data-param-value");
                }
            });
        }

        function setSelect($form_group) {
            var $input = $form_group.children("div.select").children("input"),
                val = $input.attr("data-param-value");
            if (val !== "undefined" && val !== "null" && val) {
                obj[$input.attr("data-param-code")] = val;
            }
        }

        function setDate($form_group) {
            var $inputs = $form_group.find('div.date').find('input'),
                val = parseInt($inputs.eq(0).val().trim()),
                unit = $inputs.eq(1).val().trim(),
                unit_map = {
                    '天': 1,
                    "小时": 0.0417,
                    "周": 7,
                    "月": 30,
                    "年": 365
                };

            obj[$inputs.eq(0).attr("data-param-code")] = val * unit_map[unit];
        }
    }


    function getGroupForBasis($group) {
        var $ps = $group.find("div.choose p"),
            $symptom_forms = $group.find("div.symptom-form"),
            obj = {};
        $ps.each(function () {
            var $symptom_form = $symptom_forms.filter("[data-relation-id=" + $(this).attr('data-relation-id') + "]");
            if ($symptom_form.length > 0 && !$symptom_form.hasClass('disabled')) {
                obj[$symptom_form.attr("data-param-code")] = getSymptomFormForBasis($symptom_form);
            }
        });
        return obj;
    }

    //判断整个icss是否修改的函数
    function isChange() {
        var dataJson = '';
        if ($('div.choose').children('div.content').children().length === 0) {
            return false;
        }
        // $(".group:lt(5)").each(function () {
        //     dataJson.push(common.saveSymptomGroup($(this)));
        // });
        // dataJson.push(getLisForSave());
        // dataJson.push(getPacsForSave());
        // dataJson.push(getDiagnosisForSave());
        dataJson = commitData(false).dataJson;

        return dataJson !== Param.dataJson ? true : false;
    }

    /*** 给表单里的日期控件添加年份、当前的月份和当月的天数 ***/
    function addDateControlTime($date_toggle) {
         
        var date = new Date(),
            month = date.getMonth() + 1,
            day = date.getDate(),
            $date = $date_toggle.siblings("div.date:last"),
            $selects = $date.children(".select"),
            month_ul = '<ul class="box-r-s d-n">',
            day_ul = '<ul class="box-r-s d-n">',
            i;
        
        for (i = 1; i <= month; i++) {
            month_ul += '<li><a>' + i + '</a></li>';
        }
        month_ul += '</ul>';
        $selects.eq(1).append(month_ul);
        for (i = 1; i <= day; i++) {
            day_ul += '<li><a>' + i + '</a></li>';
        }
        $selects.eq(2).append(day_ul);
    }

    /*** 判断是否过滤月经史和婚育史 ***/
    function hideMenstrualAndMarital() {
        var $symptom_else;
        if (Param.sexType !== "2") {
            $symptom_else = $("div.group").eq(3).find("div.symptom-else:first");
            $symptom_else.find("p[data-transcode=SJFL003003]").css("display", "none");
        }
        if (parseInt(Param.age) && parseInt(Param.age) < 12) {
            $symptom_else = $symptom_else ? $symptom_else : $("div.group").eq(3).find("div.symptom-else:first");
            $symptom_else.find("p[data-transcode=SJFL003004]").css("display", "none");
        }
    }

    /*** 提示弹窗 ***/
    function alert(src) {

    }
    function createYear(count){
        function getLocalTime(nS) {
            nS = nS / 1000;
            return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/, ' ');
        }
        var year = getLocalTime(Date.now()).split("-")[0];
        var tmpl = '<li><a>'+ year +'</a></li>';
        for(var i=0;i< count;i++){
            tmpl += '<li><a>'+ year-- +'</a></li>';
        }
        return tmpl;
    }
    function chooseDropList($clickEle,$wrap) {
        $clickEle.on("click","a", function() {
            $wrap.val($(this).text());
        });
    }
    function commitDataForBasis() {

        // var symptom='{"SYMPTOM":';//症状
        // symptom+=common.getGroupForBasis($(".group:eq(1)"));
        // symptom += "}";
        //
        // var past='{"PAST":';//既往史
        // past+=common.getGroupForBasis($(".group:eq(2)"));
        // past += "}";
        //
        // var other='{"OTHER":';//其他史
        // other+=common.getGroupForBasis($(".group:eq(3)"));
        // other += "}";
        //
        // var vital='{"VITAL":';//体征
        // vital+=common.getGroupForBasis($(".group:eq(4)"));
        // vital += "}";
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

        var param = {
            symptomJsonString: JSON.stringify(symptom),
            pastJsonString: JSON.stringify(past),
            otherJsonString: JSON.stringify(other),
            vitalsJsonString: JSON.stringify(vital),
            labsJsonString: lis,
            pacsJsonString: pacs
        };
        //console.log(param);
        return param;
    }
    /**
    * 为input添加失焦隐藏事件
    * @param {需要添加的元素}  
     */
    function hidePushOnblur($el) {
        $el.on("blur", function() {
            $el.siblings(".symptom-container").fadeOut();;
        });
    }
    return {
        "removeFocus": removeFocus,
        "selectHide": selectHide,
        "dateControlToggle": dateControlToggle,
        "radioChangeState": radioChangeState,
        "checkboxChangeState": checkboxChangeState,
        "selectSingleChoiceByText": selectSingleChoiceByText,
        "selectMultipleChoiceByCheckbox": selectMultipleChoiceByCheckbox,
        "getSelectText": getSelectText,
        "symptomFormAddEvent": symptomFormAddEvent,
        "informationControlToggle": informationControlToggle,
        "changeContainerShadowWidth": changeContainerShadowWidth,
        "groupHide": groupHide,
        "cancelReturnLastStep": cancelReturnLastStep,
        "selectOnlyHide": selectOnlyHide,
        "addChooseBottom": addChooseBottom,
        "autoScrollY": autoScrollY,
        "allIsNull": allIsNull,
        "saveSymptomGroup": saveSymptomGroup,
        "savePatBody": savePatBody,
        "saveHistoryBody": saveHistoryBody,
        "recoverGroup": recoverGroup,
        "getSymptomForBasis": getSymptomForBasis,
        "getGroupForBasis": getGroupForBasis,
        "isChange": isChange,
        "addDateControlTime": addDateControlTime,
        "dateControlRelation": dateControlRelation,
        "hideMenstrualAndMarital": hideMenstrualAndMarital,
        "dateControlDefault": dateControlDefault,
        "alert": alert,
        "createYear":createYear,
        "chooseDropList":chooseDropList,
        "commitDataForBasis": commitDataForBasis,
        "hidePushOnblur": hidePushOnblur
    }

})(jQuery);

/**
 * [saveTreatmentGroup 提交时获取治疗form的数据]
 * @return {[obj]} [返回一个对象]
 */
function saveTreatmentGroup() {
    var obj = {
        "type": 8,
        "choose": [],
        "details": []
    };
    //记录details中content数据的顺序

    $('.treatment .choose p').each(function (i, v) {
        var idNum = 0;
        if(all_drugItem.length<=0)
        	return;

        var drugInfoItem = all_drugItem[i];
        var drugInfoList = drugInfoItem.drugInfoList;
        // console.log(drugInfoItem);
        // console.log(drugInfoList);
        // //获取当前id的jq对象以便获取其form上的数据；
        // var id = $(v).attr("wholeid");
        // console.log(id);
        // var $id = $('p[wholeid='+ id +']');
        // var $current = $('div[data-relation-id='+ id +']');
        //-组合药品
        if (drugInfoList.length > 0) {
            var valueName = null;
            //  var idTxt =$(v).text(); //$id.text();
            //获取到已经选择的checkbox（即 加急 或 自备等）
            var newChooseObjList = [];
            for (var i = 0; i < drugInfoList.length; i++) {
                valueName = drugInfoList[i].drugName;
                var newChooseObj = {
                    "isGroup": drugInfoList.length == 1 ? 0 : 1,
                    "itemId": drugInfoList[i].drgId,
                    "itemName": valueName,
                    "itemDescribe": valueName + ":" + drugInfoList[i].drgOnceDose + drugInfoList[i].drgDoseUnit + "," + drugInfoItem.modName + "," + drugInfoItem.frequencyName + ",疗程" + drugInfoItem.liaocheng + "天",
                    "type": 8,
                    "remark": drugInfoList[i].beizhu
                    /* "drugGroupList":drugInfoList*/
                };
                newChooseObjList.push(newChooseObj)
            }

            // var additional = {};
            // if($current.find('.checkbox').length > 0){
            //     $current.find('.checkbox').each(function(i,v){
            //         var text = $(v).text();
            //         if($(v).hasClass('checked')){
            //             additional[text] = 1;//1表示选中
            //         }else{
            //             additional[text] = 0;//1表示未选中
            //         }
            //     });
            // }
            //- choose 数据格式


            var newDetailsObj = {
                "isGroup": drugInfoList.length == 1 ? 0 : 1,
                "itemId": "",
                "title": {
                    "itemName": drugInfoItem.name,
                },
                "contents": drugInfoItem
            };
            //判断如果不是组合药品就添加上id
            // if(drugInfoList.length === 1){
            //    newDetailsObj.itemId =  newChooseObj.itemId = drugInfoList[0].id;
            //    //为非组合药品设置标志
            //    newChooseObj.isGroup = newDetailsObj.isGroup = 0;
            // }else{
            //     //组合药品设置标志
            //     newChooseObj.isGroup = newDetailsObj.isGroup = 1;
            // }
            /**
             * [getInputVal 获取药品的详细信息]
             * @param  {[type]} i [description]
             * @param  {[type]} v [description]
             * @return {[type]}   [description]
             */
            // function getInputVal(i,v){
            //     var drugDetailInfo = {
            //         "id": idNum,
            //         'drugName': '',
            //         'drgDose': '',
            //         'drgDoseUnit': '',
            //         'drugUseModList': '',
            //         'drugFrequencyList': '',
            //         'drugCycle': '',
            //         'remark': ''
            //     }

            //     //分离出逻辑函数，由于组合和单个药品html结构不同，主要用于单个药品时，0，1, 2 可用于设置组合药品信息，
            //     var setInfo = {
            //         "0":function($input){
            //             drugDetailInfo.drugName = $input.val();
            //         },
            //         "1":function($input){
            //             drugDetailInfo.drgDose = $input.eq(0).val();
            //             drugDetailInfo.drgDoseUnit = $input.eq(1).val();
            //         },
            //         "2":function($input){
            //             drugDetailInfo.drugUseModList = $input.val();

            //             if(drugInfoList.length > 1){
            //                 drugDetailInfo.remark = $input.val();
            //             }
            //         },
            //         "3":function($input){
            //             drugDetailInfo.drugFrequencyList = $input.val();
            //         },
            //         "4":function($input){
            //             drugDetailInfo.drugCycle = $input.val();
            //         },
            //         "5":function($input){
            //             drugDetailInfo.remark = $input.val();
            //         },
            //     }

            //     //委托Context执行对应的逻辑函数
            //     function setDrugInfo(type,$input){
            //         return setInfo[type]($input);
            //     }
            //     var $row = $(v).find('.row');
            //      //由于药品的单次量有两个需要获取的数据，单独判断
            //      $row.each(function(index, el) {
            //         var $input = $(el).find('input');
            //         console.log($input.val());
            //         setDrugInfo(index, $input);
            //      });
            //     $current.find('.bottom-form-area').children('.row').each(function(index,value){
            //         var $input = $(value).find('input');
            //         if(index == 0){//用法
            //             drugDetailInfo.drugUseModList = $input.val();
            //         }else if(index == 1){//频次
            //             drugDetailInfo.drugFrequencyList = $input.val();
            //         }else if(index == 2){//疗程
            //             drugDetailInfo.drugCycle = $input.val();
            //         }
            //     });
            //      return drugDetailInfo;
            // }

            //获取组合药品的数据
            // $current.find('.form-area').each(function (i,v) {
            //     var drugDetailInfo = getInputVal(i,v);
            //     drugDetailInfo.id = idNum++;
            //     newDetailsObj.contents.push(drugDetailInfo);
            // });

            //组合药品
            /*  if(drugInfoList.length > 1){
             */
            /**
             * [由于组合药品数据结构比较特殊，所以新添加了一个字段，用来保存详细药品数据]
             * @param  {[type]} i    [each 索引]
             * @param  {[type]} v)   [已经选择的药品列表，是一个数组]
             * @return {[type]}      []
             */
            /*
             //修正itemDescribe数据，
             var inputTxt = $id.text();
             var inputArr = inputTxt.split('/');
             $.each(drugInfoList,function(i,v){
             newChooseObj.drugGroupList.push({
             "itemId": v.id,
             "itemName": v.name,
             "itemDescribe": inputArr[i],
             "type": 8,
             "remark": ""
             });
             });
             }*/
            obj.choose.push(newChooseObj);
            obj.details.push(newDetailsObj);
        }
    });
    return obj;
}

function commitData(is_set_param) {
    var allNull = common.allIsNull();
    if (allNull) {
        console.log("表单为空");
        return false;
    } else {
        var diseaseId = "";
        var diseaseName = "";
        var $p_first = $(".group:eq(7)").find("p:first");
        if ($p_first.attr("diagnosiscode") && $p_first.length > 0) {
            diseaseId = $p_first.attr("diagnosiscode").substring(13);
            diseaseName = $p_first.attr("diagnosisname");
        } else {
            diseaseId = diseaseName = "";
        }
        var commitData = {
            "id": Param.saveId ? Param.saveId : -1,
            "type": "1",
            "sign": "0",
            "patientId": Param.patientId,
            "hisCode": Param.serial,
            "doctorName": Param.doctorName,
            "dept": Param.dept,
            "deptCode": Param.deptNo,
            "doctorId": Param.doctorId,
            "hospitalCode": Param.hospitalCode,
            "regVisitedState": Param.regVisitedState,
            "diagnose": diseaseName,
            "diseaseId": diseaseId,
            "detailStr": [],
            "dataJson": [],
            "toRequest": [],
            "toLisDetail": [],
            "toPacsDetail": [],
            "toDiagnose": [],
            "toPrescript": [],
            "toPrescriptXy": [],
            "toPrescriptCy": [],
            "toPrescriptZcy": [],
            "checkType": []//判断填写了哪些类型数据
        }
        $(".group:lt(5)").each(function () {
            commitData.dataJson.push(common.saveSymptomGroup($(this)));
        });
        commitData.toPat = JSON.stringify(common.savePatBody().toPat);
        commitData.toHistory = JSON.stringify(common.saveHistoryBody($(".group:lt(5)")).toHistory);
        commitData.dataJson.push(getLisForSave());
        commitData.dataJson.push(getPacsForSave());
        commitData.dataJson.push(getDiagnosisForSave());
        // commitData.dataJson.push("治疗内容");
        /*** 这里push进治疗内容 ↑ */
        $(".group:gt(7)").each(function () {
            commitData.dataJson.push(saveTreatmentGroup());
        });
        /* var object = getTreatmentData();
         commitData.toPrescript = (JSON.stringify(object.toPrescript));
         commitData.toPrescriptXy = (JSON.stringify(object.toPrescriptXy));
         commitData.toPrescriptCy = (JSON.stringify(object.toPrescriptCy));
         commitData.toPrescriptZcy = (JSON.stringify(object.toPrescriptZcy));*/

        $(commitData.dataJson).each(function (i,v) {
            
            
            $(this.choose).each(function () {
                commitData.detailStr.push(this);
            });
            if(v.details.length > 0){
                $(this).each(function () {
                    commitData.checkType.push(Number(this.type));
                });
            }
        });

        commitData.detailStr = jsonTOstring(commitData.detailStr);
        // console.log(Param.dataJson);
        commitData.dataJson = jsonTOstring(commitData.dataJson);
        if (is_set_param) {
            Param.dataJson = commitData.dataJson;
        }

        if ($(".container .group").eq(5).find(".choose .content").children().length > 0) {
            commitData.toRequest.push({hisType: 5, sqflid: 'L'})
        }
        if ($(".container .group").eq(6).find(".choose .content").children().length > 0) {
            commitData.toRequest.push({hisType: 6, sqflid: 'P0101'})
        }

        commitData.toLisDetail = saveLisForHis();
        commitData.toPacsDetail = savePacsForHis();
        commitData.toDiagnose = saveDiagnosisForHis();

        commitData.toLisDetail = JSON.stringify(commitData.toLisDetail);
        commitData.toPacsDetail = JSON.stringify(commitData.toPacsDetail);
        commitData.toDiagnose = JSON.stringify(commitData.toDiagnose);
        commitData.toRequest = JSON.stringify(commitData.toRequest);
        // console.log(commitData);
        return commitData;
    }
}
//判断当前状态表单填写状态
function checkStatusFn() {
    var treatStatus = "";
    //判断病人就诊状态
    var commitDataType = commitData(true).checkType;//[0,1,2,3,...]
    if (common.allIsNull() || commitDataType.indexOf(0) == -1 || JSON.parse(commitData(true).dataJson)[0].choose.length == 0) {

        // alert("请填写主诉");//提示需要填写的内容
        common.alert("请填写主诉");
        $("#shade").removeClass("d-n").removeAttr("style");
        $("body").css("overflow-y", "hidden");
        $("#save-modal").find("h3").html("请填写主诉").end().removeAttr("style").removeClass("d-n");
        setTimeout(function () {
            $("#save-modal").fadeOut(500, function () {
                $("#shade").addClass("d-n");
                $("body").css("overflow-y", "scroll");
            });
        }, 1000);

        return false;

    } else {
        if (commitDataType.indexOf(7) != -1) {
            //已填主诉和诊断
            treatStatus = "treating";

            Param.regVisitedState = '1';

        } else if (commitDataType.indexOf(7) == -1) {
            // alert("请填写诊断");//提示需要填写的内容
            $("#shade").removeClass("d-n").removeAttr("style");
            $("body").css("overflow-y", "hidden");
            $("#save-modal").find("h3").html("请填写诊断").end().removeAttr("style").removeClass("d-n");
            setTimeout(function () {
                $("#save-modal").fadeOut(500, function () {
                    $("#shade").addClass("d-n");
                    $("body").css("overflow-y", "scroll");
                });
            }, 1000);

            return false;
        }

        if (commitDataType.indexOf(8) != -1) {
            treatStatus = "treated";
            Param.regVisitedState = '2';
        }
    }
    return treatStatus;
}

/**
 * 提交保存函数
 * @param {*} successFn 成功的回调函数
 * @param {*} failFn 失败的回调函数
 */
function saveRecord(successFn, failFn) {
    var date = new Date().getTime();
    //判断当前状态
    var treatStatus = checkStatusFn();
    if (!treatStatus) {

        return false;
    }
    // console.log(commitData(true));

    ajaxPostAsync(Param.hostUrl + Param.save_insert, commitData(true), function (data) {
        var now = new Date().getTime();

        //提交--遮罩
        $("#shade").removeClass("d-n").removeAttr("style");
        $("#loading").removeClass("d-n");
        $("body").css("overflow-y", "hidden");

        if (data.ret !== 1) {
            Param.saveId = data.data.localId;
            if (now - date > 1000) {
                $("#loading").addClass("d-n");
                $("#save-modal").find("h3").html("保存成功").end().removeAttr("style").removeClass("d-n").fadeOut(1000, function () {
                    $("#shade").addClass("d-n");
                    $("body").css("overflow-y", "scroll");
                });
                if (typeof successFn == "function") {
                    successFn(treatStatus);
                }
            } else {
                setTimeout(function () {
                    $("#loading").addClass("d-n");
                    $("#save-modal").find("h3").html("保存成功").end().removeAttr("style").removeClass("d-n").fadeOut(1000, function () {
                        $("#shade").addClass("d-n");
                        $("body").css("overflow-y", "scroll");
                    });
                    if (typeof successFn == "function") {
                        successFn(treatStatus);
                    }
                }, 50);
            }

            $.ajax({
                url: Param.hostUrl + "/at/reception_state/update?hospitalId=" + Param.hospitalCode + "&registrationId=" + Param.patientId + "&regVisitedState=" + Param.regVisitedState,
                success: function (data) {
                    if (window.top.index2) {
                        window.top.index2.initializationList();
                        window.top.index2.initializationDetailList(window.top.index2.patData, true);
                    }
                }
            });

        } else {
            $("#loading").addClass("d-n");
            $("#save-modal").find("h3").html("保存失败").end().removeAttr("style").removeClass("d-n").fadeOut(1000, function () {
                $("#shade").addClass("d-n");
                $("body").css("overflow-y", "scroll");
            });
            if (typeof failFn == "function") {
                failFn(treatStatus);
            }
        }
    });

}

// 时间戳转换年龄的方法
function birthday(time) {
    var birthDay = new Date(time).getTime();
    var now = new Date().getTime();
    var hours = (now - birthDay) / 1000 / 60 / 60;
    var year = Math.floor(hours / (24 * 30 * 12));
    hours = hours % (24 * 30 * 12);
    var months = Math.floor(hours / (24 * 30));
    hours = hours % (24 * 30);
    var days = Math.floor(hours / (24));
    return year;
}

//保存治疗表单
$("#lastSave").click(function () {
    saveRecord(function (s) {
        console.log(s);
    });
});