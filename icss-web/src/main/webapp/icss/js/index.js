/**
 * Created by Kiva on 17/3/15.
 */
var index2 = (function($) {
	var patient = {};
	var patData = {};
	var loginData = {
		departId: Param.deptCode,
		hospitalId: Param.hospitalId,
		staffId: Param.doctorId
	};

	//doctorNo转doctorId
	ajaxPost(
		Param.hostUrl + "/at/doctorinfo/get_doctor_from_his", {
			doctorNo: loginData.staffId,
			hospitalCode: loginData.hospitalId,
			deptNo: loginData.departId
		},
		function(results) {
			if(results && results.data) {
				loginData.doctorId = results.data.id;
			}
		});

	$("#clinic_date").val(time(new Date(), 1));
	$(".patient").hide();

	var $simple_info = $("div.simple-info"),
		$menu_drop = $("div.menu-drop"),
		$menu_change = $("div.menu-change"),
		$patients_ul = $menu_drop.eq(0).find("ul.first-level"),
		$patients_li = $menu_drop.eq(0).find("ul.first-level>li>ul.second-level>li"),
		$cases_ul = $menu_drop.eq(1).find("ul.first-level"),
		//右侧上面的门诊 一级目录的div
		$content_nav = $("div.content-nav"),
		$content_diagnosis = $("div.content-diagnosis"),
		$patient = null,
		timer = null;

	//初始化患者列表
	initializationList();

	$("#clinic_date").on('change', function() {
		initializationList();
	});

	/***************初始化患者列表starting****************/
	function initializationList() {
		var inputVal = $(".search-input").eq(0).val();
		var inputDate = (!!inputVal) ? new Date(inputVal) : new Date();
		var curDate = new Date();

		//左侧病人接口实现：
		var simpleInfo = "";
		var infoDetail = "";

		var contact = "";
		var company = "";
		var relatives = "";
		$.ajax({
			type: "POST",
			url: Param.hostUrl + "/at/waiting_list/get",
			dataType: "json",
			data: {
				"hospitalId": loginData.hospitalId,
				"stffId": loginData.doctorId,
				"departId": loginData.departId
					//"inputVal": inputVal||""
					//"regDate": inputDate
			},
			success: function(data) {
				var data = data.data; //获取病人数组
				if(data == null || data.length == 0) {
					return;
				} else {
					var wait = 0; //待诊人数
					var start = 0; //诊断人数
					var finish = 0; //完诊人数
					var waitStr = ""; //待诊人名
					var startStr = ""; //诊断中人名
					var finishStr = ""; //完诊人名

					$.each(data, function(index, value) {
						if(value.regVisitedState == 0) {
							wait++;
							waitStr += "<a data-name='0'  data-regVisitedState='" + value.regVisitedState + "' patName='" + value.patName + "' patBirthday='" + value.patBirthday + "' patAge='" + value.patAge + "' patSex='" + value.patSex + "' data-patId='" + value.patId + "' data-id=" + value.id + ">" + value.patName + "</a>";
						}
						if(value.regVisitedState == 1) {
							start++;
							startStr += "<a data-name='0'   data-regVisitedState='" + value.regVisitedState + "' patName='" + value.patName + "' patBirthday='" + value.patBirthday + "' patAge='" + value.patAge + "' patSex='" + value.patSex + "' data-patId='" + value.patId + "' data-id=" + value.id + ">" + value.patName + "</a>";
						}
						if(value.regVisitedState == 2) {
							finish++;
							finishStr += "<a data-name='2'   data-regVisitedState='" + value.regVisitedState + "' patName='" + value.patName + "' patBirthday='" + value.patBirthday + "' patAge='" + value.patAge + "' patSex='" + value.patSex + "' data-patId='" + value.patId + "' data-id=" + value.id + "><span class='font'>" + value.patName + "</span><span class='icon'></span></a>";
						}
					});
					//待诊人数实现
					$("nav.container-left .menu-drop ul.first-level:first a:first span:eq(2)").html(wait);
					//诊中人数实现
					$("nav.container-left .menu-drop ul.first-level:eq(0) li:eq(2) span:eq(2)").html(start);
					//完诊人数实现
					$("nav.container-left .menu-drop ul.first-level:eq(0) li:eq(4) span:eq(2)").html(finish);
					//待诊人名实现
					$("nav.container-left .menu-drop ul.first-level:eq(0) ul:first li").html(waitStr);
					//诊中人名实现
					$("nav.container-left .menu-drop ul.first-level:eq(0) ul:eq(1) li").html(startStr);
					//完诊人名实现
					$("nav.container-left .menu-drop ul.first-level:eq(0) ul:eq(2) li").html(finishStr);

					if(time(inputDate, 1) < time(curDate, 1)) {
						$("nav.container-left .menu-drop ul.first-level:first a:first span:eq(2)").html(0);
						$("nav.container-left .menu-drop ul.first-level:eq(0) ul:first li").html("");
					} else if(time(inputDate, 1) > time(curDate, 1)) {
						//诊中人数实现
						$("nav.container-left .menu-drop ul.first-level:eq(0) li:eq(2) span:eq(2)").html(0);
						//完诊人数实现
						$("nav.container-left .menu-drop ul.first-level:eq(0) li:eq(4) span:eq(2)").html(0);
						//诊中人名实现
						$("nav.container-left .menu-drop ul.first-level:eq(0) ul:eq(1) li").html("");
						//完诊人名实现
						$("nav.container-left .menu-drop ul.first-level:eq(0) ul:eq(2) li").html("");
					}
				}
			}
		});

		setTimeout(function() {
			$patient = selectPatient();
		}, 500);
		patientClickEvent();
	}
	/***************初始化患者列表ending****************/

	/***************进入诊中starting****************/
	function patientClickEvent() {
		$patients_ul.find("ul.second-level").find("li").on("click.index.left-move", "a,#search_res_box>span", function() {
			$patient = $(this);
			// 判断能否点击 data-name=0(待诊/诊中，可点击) data-name=1(完诊，不可点击)
			var getClick = $(this).attr("data-name");
			var patId = $(this).attr("data-id") || -1;
			var regVisitedState = $(this).attr("data-regVisitedState") || '';
			var hisCode = $(this).attr("data-patId") || '';
			var patSex = $(this).attr("patSex");
			var age = $(this).attr("patAge");

			patient.patId = patId;
			patient.regVisitedState = regVisitedState;
			patient.hisCode = hisCode;
			patient.patSex = patSex;
			patient.age = age;
			console.info(patient);

			//当前选中病人
			var selectedPatients = $patients_li.find("a.select");
			//判断病历是否修改
			var isChange = icss.window.common.isChange();
			//切换病人
			if(selectedPatients.length > 0 && isChange) {
				//提示是否保存当前病历
				var $shade = $("#shade"),
					$save_model = $("#confirm-model-save"),
					$save_buttons = $save_model.find("div.button"),
					$left_button = $save_buttons.children(".left"),
					$right_button = $save_buttons.children(".right");

				$save_model.addClass("animated flipInY").show();
				$shade.fadeIn();
				$save_buttons.off("click");

				$left_button.on("click", function() {
					icss.window.saveRecord(function(treatStatus) {
						console.info(treatStatus);
						reloadPatientList();
						console.info("保存成功");
						changePatient($patients_li.find("a[data-id='" + patient.getPatId + "']"));
					}, function() {
						console.info("保存失败");
					});
					//隐藏弹窗和遮罩
					$save_model.removeClass("animated flipInY").fadeOut();
					$save_buttons.off("click");
					$shade.fadeOut();
				});
				$right_button.on("click", function() {
					//隐藏弹窗和遮罩
					$save_model.removeClass("animated flipInY").fadeOut();
					$save_buttons.off("click");
					changePatient($patients_li.find("a.select"));
					$shade.fadeOut();
				});
			} else {
				changePatient($(this));
			}
		});

		function changePatient($a) {
			var getId = $a.attr("data-id") || -1;
			//判断当前病历是否空白
			$a.parent().parent().find('a').removeClass('select');
			$a.addClass('select');
			clearTimeout(timer);
			timer = setTimeout(function() {
				$menu_change.eq(0).removeClass("d-n");
				menuLeftMove();
			}, 300);
			patData = {
				"hospitalCode": loginData.hospitalId,
				"patientId": getId,
				"doctorId": loginData.doctorId
			};
			//获取病人详细信息
			getPatientInfo(patient.hisCode);

			$(".patient").show();
			$("div.simple-info div.icon,div.name,div.age,div.sex").show().click(function() {
				$(".patient").css({
					"background-color": "#fff",
					"border-radius": "5px",
					"max-width": "828px",
					"padding": "5px 25px 5px 25px",
					"box-shadow": "2px 2px 6px #ccc"
				});
			});

			initializationMenuMenz(patData);
			if($("#clinic_date").val() == time(new Date(), 1)) {
				$(".content-diagnosis").find("iframe#icss").attr("src", "icss.html?clinicId=" + patient.hisCode + "&doctorNo=" + loginData.staffId + "&deptId=" + loginData.departId + "&brjzid=&hospitalId=" + loginData.hospitalId + "&regVisitedState=" + patient.regVisitedState + "&sexType=" + patient.patSex + "&age=" + patient.age);
				$(".content-diagnosis").find("iframe#diagnose").hide();
				$(".content-diagnosis").find("iframe#icss").show();
			} else {
				$(".content-diagnosis").find("iframe#diagnose").attr("src", "diagnose.html");
				$(".content-diagnosis").find("iframe#icss").hide();
				$(".content-diagnosis").find("iframe#diagnose").show();
			}

			$a.addClass("select");
		}
	}
	/***************进入诊中ending****************/

	/***************获取患者详细信息starting****************/
	function getPatientInfo(hisCode) {
		$.ajax({
			url: Param.hostUrl + '/at/patientinfo/get_patient_from_his',
			type: 'GET',
			dataType: 'json',
			contentType: "application/json",
			data: {
				"hisCode": hisCode,
				"hospitalCode": loginData.hospitalId
			},
			success: function(res) {
				var data = res.data; //获取病人信息数组；
				var name = data.name || '';
				var sex = data.sex || '';
				var sexWrapper = '';
				if(sex == "1") {
					sexWrapper = "男";
				} else if(sex == "2") {
					sexWrapper = "女";
				} else {
					sexWrapper = sex;
				}
				var identityNum = data.identityNum || '';
				var country = data.country || '';
				var matrimony = data.country || '';
				var idNo = data.idNo || '';
				var id = data.id || '';
				var address = data.address || '';
				var operation = data.operation || '';
				var nationality = data.nationality || '';
				var phone = data.phone || '';
				var workUnit = data.workUnit || '';
				var postCode = data.postCode || '';
				var contactPhone = data.contactPhone || '';
				var contacts = data.contacts || '';
				var phone = data.phone || '';
				infoDetail = "<span class='font'>身份证:" + identityNum + "</span>" + "<span class='font'>国籍:" + country + "</span>" + "<span class='font'>婚姻:" + matrimony + "</span>" + " <span class='font'>卡号(ID号):" + idNo + "</span>" + "<span class='font'>户籍:" + address + "</span>" + "<span class='font'>学历:" + address + "</span>" + "<span class='font'>医疗卡号:" + idNo + "</span>" + "<span class='font'>籍贯：" + address + "</span>" + "<span class='font'>职业:" + data.operation + "</span>" + "<span class='font'>医疗享受：" + "" + "</span>" + "<span class='font'>民族:" + nationality + "</span>";
				contact = "<span class='font'>手机:" + phone + "</span>" + "<span class='font'>地址:" + workUnit + "</span>" + "<span class='font'>Email:" + id + "</span>" + "<span class='font'>邮编:" + postCode + "</span>";
				company = "<span class='font'>工作单位:" + workUnit + "</span>" + "<span class='font'>单位电话:" + contactPhone + "</span>" + "<span class='font'>单位地址:" + address + "</span>" + "<span class='font'>邮编:" + postCode + "</span>";
				relatives = "<span class='font'>联系人:" + contacts + "</span>" + "<span class='font'>关系:" + contacts + "</span>" + "<span class='font'>联系电话:" + phone + "</span>";
				//onload默认加载诊中用户诊疗信息
				$(".content-diagnosis .patient .simple-info .name").html(name);
				$(".content-diagnosis .patient .simple-info .sex").html(sexWrapper);
				$(".content-diagnosis .patient .simple-info .age").html(birthday(data.birthday) + "岁");
				$(".content-diagnosis .patient .info-detail:first").html(infoDetail);
				$(".content-diagnosis .patient .info-detail:eq(1)").html(contact);
				$(".content-diagnosis .patient .info-detail:eq(2)").html(company);
				$(".content-diagnosis .patient .info-detail:eq(3)").html(relatives);
				addEvent();
			}
		});
	}
	/***************获取患者详细信息ending****************/

	/*** 添加事件的函数 外部添加的事件统一写在这里函数里 ***/
	function addEvent() {
		// 点击搜索触发的事件
		$(".search-img").click(function(event) {
			initializationList();
		});
		//点击简要信息  下拉出现和缩起详细信息的事件
		$("div.simple-info div.icon,div.name,div.age,div.sex").on("click", function() {
			$simple_info.siblings("div.detail").slideToggle(function() {
				changePatientProps();
			});
		});

		function changePatientProps() {
			if($simple_info.siblings("div.detail").css("display") == "none") {
				$(".patient").css({
					"background-color": "#f1f4f9",
					"border-radius": "0",
					"max-width": "100%",
					"padding": "5px calc(50% - 389px) 5px calc(50% - 389px)",
					"box-shadow": '1px 1px 2px rgba(154, 152, 152, 0.35)'
				});
			}
		}

		//历史病历
		$("div.simple-info div#lishijilu").off("click");
		$("div.simple-info div#lishijilu").on("click", function() {
			$(".patient div.detail").hide();
			if($(this).hasClass("lishijilu")) {
				$(this).removeClass("lishijilu");
				$(this).addClass("lishijilu_hover_clicked");
				$(".content-diagnosis").find("iframe#diagnose_log").attr("src", "diagnose_log.html");
				$(".content-diagnosis").find("iframe#diagnose_log").show();
				$(".content-diagnosis").find("iframe#icss").hide();
				$(".content-diagnosis").find("iframe#diagnose").hide();
			} else {
				$(this).removeClass("lishijilu_hover_clicked");
				$(this).addClass("lishijilu");
				$(".content-diagnosis").find("iframe#diagnose_log").hide();
				var first_index_id = $("#second_level_ul li:eq(0)").attr("data-id");
				if(sessionStorage.getItem("getIndexId") == first_index_id) {
					$(".content-diagnosis").find("iframe#icss").show();
					$(".content-diagnosis").find("iframe#diagnose").hide();
				} else {
					$(".content-diagnosis").find("iframe#icss").hide();
					$(".content-diagnosis").find("iframe#diagnose").show();
				}
			}
			setTimeout(function() {
				$(".patient").css({
					"background-color": "#f1f4f9",
					"border-radius": "0",
					"max-width": "100%",
					"padding": "5px calc(50% - 389px) 5px calc(50% - 389px)",
					"box-shadow": '1px 1px 2px rgba(154, 152, 152, 0.35)'
				});
			}, 500);
		});

		//打印
		$("div.simple-info div.print").on("click", function() {

		});

		//返回
		$("div.simple-info div.fanhui").on("click", function() {
			//判断数据是否有修改
			var ischange = icss.window.common.isChange();
			if(ischange) {
				//有数据修改，提示是否保存
				//提示是否保存当前病历
				var $shade = $("#shade"),
					$save_model = $("#confirm-model-save"),
					$save_buttons = $save_model.find("div.button"),
					$left_button = $save_buttons.children(".left"),
					$right_button = $save_buttons.children(".right");

				$save_model.addClass("animated flipInY").show();
				$shade.fadeIn();
				$save_buttons.off("click");

				//是----保存
				$left_button.on("click", function() {
					icss.window.saveRecord(function(treatStatus) {
						console.info(treatStatus);
						reloadPatientList();
						console.info("保存成功");
						goBackPatientList();
					}, function() {
						console.info("保存失败");
					});
					//隐藏弹窗和遮罩
					$save_model.removeClass("animated flipInY").fadeOut();
					$save_buttons.off("click");
					$shade.fadeOut();
				});

				//否---不保存
				$right_button.on("click", function() {
					//隐藏弹窗和遮罩
					$save_model.removeClass("animated flipInY").fadeOut();
					$save_buttons.off("click");
					goBackPatientList();
					$shade.fadeOut();
				});
			} else {
				goBackPatientList();
			}
		});

		//提交
		$("div.simple-info div.tijiao").on("click", function() {
			//判断是否保存当前病历,保存完成或放弃保存时返回
			ajaxPost(Param.hostUrl + Param.save_insert, icss.window.commitData());
			$(".content-diagnosis").find("iframe").attr("src", "icss.html?brjzid=-1&sexType=3");
			goBackPatientList();
		});

		//完诊 不能点击的
		$("body").on('click', "#over_user a", function(event) {
			//event.stopPropagation();
		});
		// 点击小旗 触发的事件
		$("body").on('click', "#second_level_ul .case-list> a .add", function(event) {

			$li = $(this).parents().parents();
			var id = $li.attr("data-id");
			if($(this).hasClass('focus')) {
				$(this).removeClass('focus');
				var data = {
					"id": id,
					"sign": "0"
				};
			} else {
				$(this).addClass('focus');
				var data = {
					"id": id,
					"sign": "1"
				};
			}
			updateInquirySign(data);
			event.stopPropagation();
		});
		//更新病历标签
		function updateInquirySign(data) {
			$.ajax({
				type: "POST",
				url: Param.hostUrl + "/at/inquiry_info/update_info",
				dataType: "json",
				data: data,
				success: function(data) {}
			});
		}
	}

	$menu_change.eq(0).on("click.index.right-move", function() {
		menuLeftMove();
	});

	$menu_change.eq(1).on("click.index.right-move", function() {
		menuRightMove();
		$menu_change.eq(0).removeClass("d-n");
		$("#serach_menz").hide();
		$(".serach_menz_second_level").hide();

	});

	$content_nav.find("span").on("click.index.nav", function() {
		var $underline = $content_nav.find("div.underline"),
			$span = $(this),
			data_url = $span.attr("data-url").replace(/[./]/g, '-'),
			$iFrame = $content_diagnosis.find("iframe[data-url=" + data_url + "]");
		navChange($span, $underline);
		iFrameChange($iFrame, $span, data_url);
	});

	$("body").on('click', "#second_level_ul li", function(event) {
		var getIndexId = $(this).attr("data-id");
		var getIndexVal = $(this).index();
		sessionStorage.setItem("getIndexId", getIndexId);
		$("#second_level_ul li a").find(".arrow").addClass("d-n");
		$(this).find('a').find(".arrow").removeClass("d-n");
		$(".detail").hide("500");
		if($(this).find("a").hasClass("select")) {
			$(".content-diagnosis").find("iframe#icss").show();
			$(".content-diagnosis").find("iframe#diagnose").hide();
			$(".content-diagnosis").find("iframe#diagnose_log").hide();
		} else {
			$(".content-diagnosis").find("iframe#diagnose").attr("src", "diagnose.html");
			$(".content-diagnosis").find("iframe#icss").hide();
			$(".content-diagnosis").find("iframe#diagnose").show();
			$(".content-diagnosis").find("iframe#diagnose_log").hide();
		}
		console.info(getIndexId);
		if(getIndexId != undefined) {
			$(".patient div#lishijilu").show();
		} else {
			$(".patient div#lishijilu").hide();
		}
	});

	$("body").on('click', ".first-level ul>li a", function(event) {
		$(".first-level ul>li a").removeClass('select');
		$(this).addClass('select');
	});

	//问诊界面搜索
	$(".c-p").focus(function(event) {
		$("#serach_menz").hide();
	});
	$(".menu-search").eq(1).find(".search-arrow").click(function() {
		$("#serach_menz").show();
	});

	aClickEvent();

	//病历过滤条件置空
	function resetFilter() {
		patData.deptCode = '';
		patData.diseaseId = '';
		patData.type = '';
		patData.sign = '';
		delete patData["startTime"];
		delete patData["endTime"];
	}

	/**************根据type分类(1:门诊,2:住院,3:标记)过滤历史病历starting***********/
	function aClickEvent() {
		$("#serach_menz a").off('click');
		$("#serach_menz a").click(function(event) {
			var getVal = $(this).text();

			//病历过滤条件置空
			resetFilter();

			if($(this).siblings("ul").length == 0) {
				var $li = $(this).parents();
				var type = $li.attr("data-type") || '';
				var getId = $(this).attr("data-id");

				//级联选项（疾病/部门）
				if(type == "disease") {
					patData.diseaseId = $li.attr("data-id");
				} else if(type == "dept") {
					patData.deptCode = $li.attr("data-code");
				} else if(type == "sign") {
					patData.sign = "1";
				} else {
					patData.type = type;
				}

				if(patient.regVisitedState == "0") {
					initializationDetailList(patData, false);
				} else {
					initializationDetailList(patData, true);
				}

				$("#search_dis_dept").val(getVal);

				$("#serach_menz").hide();
			}
		});
	}
	/**************根据type分类(1:门诊,2:住院,3:标记)过滤历史病历ending***********/

	$("#serach_menz").find("li:lt(3)").hover(function(event) {
		$(".serach_menz_second_level").hide();
	});

	$("#serach_menz").mouseleave(function(event) {
		$(".serach_menz_second_level").hide();
		//$("#serach_menz").hide();
	});

	$("#serach_menz_second_level").mouseleave(function(event) {
		$(".serach_menz_second_level").hide();
	});

	searchDiseaseHover();
	/**************根据疾病过滤历史病历starting***********/
	function searchDiseaseHover() {
		$("#search_disease").hover(function(event) {
			$(".serach_menz_second_level").hide();
			$(".ol_disease").show();
			moveDiseaseWrap($("#search_disease ol:first"), $("#search_disease ol>ul>li[data-type='disease']"));
		});
	}
	/**************根据疾病过滤历史病历ending***********/

	searchDeptHover();
	/*************根据科室过滤历史病历starting***********/
	function searchDeptHover() {
		$("#search_dept").hover(function(event) {
			$(".serach_menz_second_level").hide();
			$(".ol_dept").show();
			moveDiseaseWrap($("#search_dept ol:first"), $("#search_dept ol>ul>li[data-type='dept']"));
		});
	}
	/*************根据科室过滤历史病历ending***********/

	// 根据姓名/诊疗卡/医疗卡搜索患者
	$(".search-input").eq(0).focus(function(event) {
		// 回车搜索
		document.onkeydown = function(event) {
			var e = event || window.event || arguments.callee.caller.arguments[0];
			if(e && e.keyCode == 13) {
				initializationList();
			}
		};
	});

	// 根据疾病/科室搜索病历
	$("#search_dis_dept").focus(function(event) {
		// 回车搜索
		document.onkeydown = function(event) {
			var e = event || window.event || arguments.callee.caller.arguments[0];
			if(e && e.keyCode == 13) {
				resetFilter();
				patData.deptOrDiagnose = $("#search_dis_dept").val();
				initializationDetailList(patData);
			}
		};
	});

	/***********病历疾病科室过滤条件start*********/
	function moveDiseaseWrap($ol_disease, $diseases) {
		var distance = [];
		var $ul_disease = $ol_disease.find("ul:eq(0)");
		var $ul_disease_icon = $ol_disease.find("ul:eq(1)");
		$icon = $ul_disease_icon.find('.iconfont'),
			$arrow_up = $icon.eq(0),
			$arrow_down = $icon.eq(1),
			start = 0,
			height = 0,
			_height = 0;
		setDistance("5");

		$ul_disease.data("distance", 0).css("marginTop", "0");
		$diseases.each(function(i, v) {
			changeShowDisease(i, v);
		});
		$arrow_up.addClass("disabled").off("click.main").on("click.main", function() {
			if($ul_disease.data("distance") !== 0) {
				arrowClick(false);
			}
		});
		$arrow_down.removeClass("disabled").off("click.main").on("click.main", function() {
			if($ul_disease.data("distance") !== distance.length - 1) {
				arrowClick(true);
			}
		});

		function arrowClick(flag) {
			var add;
			if(flag) {
				add = 1;
			} else {
				add = -1;
			}
			$diseases.css("visibility", "visible");
			$ul_disease.data("distance", $ul_disease.data("distance") + add);
			changeArrowState();
			$ul_disease.animate({
				"marginTop": "-" + distance[$ul_disease.data("distance")][2]
			}, 300, "linear", function() {
				$diseases.each(function(i, v) {
					changeShowDisease(i, v);
				});
			});
		}

		$diseases.each(function() {
			_height += $(this).outerHeight(true);
		});
		if(_height <= 120) {
			$arrow_up.addClass("d-n");
			$arrow_down.addClass("d-n");
		} else {
			$arrow_up.removeClass("d-n");
			$arrow_down.removeClass("d-n");
		}

		function changeArrowState() {
			$ul_disease.data("distance") === 0 ? $arrow_up.addClass("disabled") : $arrow_up.removeClass("disabled");
			$ul_disease.data("distance") === distance.length - 1 ? $arrow_down.addClass("disabled") : $arrow_down.removeClass("disabled");
		}

		function changeShowDisease(i, v) {
			var $disease = $(v);
			if(i < distance[$ul_disease.data("distance")][0] || i >= distance[$ul_disease.data("distance")][1]) {
				$disease.css("visibility", "hidden");
			} else {
				$disease.css("visibility", "visible");
			}
		}

		function setDistance(move_count) {

			function getMarginTop($diseases, i, margin_top) {
				var len = $diseases.length,
					j,
					_margin_top = 0,
					index = i,
					flag = false,
					height = 0;
				for(j = i; j < len; j++) {
					index = j;
					if(_margin_top + $diseases.eq(j).outerHeight() <=120){
						_margin_top += $diseases.eq(j).outerHeight();
					} else {
						break;
					}
				}
				if(index === len - 1 && _margin_top + $diseases.eq(len - 1).outerHeight() < 144) {
					index = len;
				}
				for(j = i; j < len; j++) {
					height += $diseases.eq(j).outerHeight();
				}
				if(height <= 120) {
					flag = true;
				}

				return [index, margin_top + _margin_top, flag];
			}

			if(move_count === "auto") {
				$diseases.each(function(i) {
					var $disease = $(this),
						len = $diseases.length,
						j,
						temp_height,
						$temp_disease;
					height += $disease.outerHeight();
					if(i < len - 1 && height + $disease.next().outerHeight() > 120) {
						temp_height = height;
						for(j = i + 1; j < len; j++) {
							$temp_disease = $($diseases[j]);
							if(temp_height + $temp_disease.outerHeight() <= 120) {
								temp_height += $temp_disease.outerHeight();
							} else {
								distance.push([start, j, margin_top]);
								break;
							}
							if(j === len - 1) {
								distance.push([start, j + 1, margin_top]);
							}
						}
						start = i + 1;
						margin_top += height;
						height = 0;
					}
					if(i === $diseases.length - 1) {
						distance.push([start, $diseases.length, margin_top]);
					}
				});
			} else {
				move_count = Number(move_count);
				var length = $diseases.length,
					num = parseInt(length / move_count),
					i,
					j,
					get_margin_arr,
					margin_top = 0;
				move_count = parseInt(move_count);
				if(num === 0) {
					get_margin_arr = getMarginTop($diseases, 0, 0);
					distance.push([0, get_margin_arr[0], get_margin_arr[1]]);
				} else {
					get_margin_arr = getMarginTop($diseases, 0, 0);
					if(parseInt(length % move_count) == 0 && get_margin_arr[0] == length - 1) {
						distance.push([0, length, 0]);
					} else {
						distance.push([0, get_margin_arr[0], 0]);
					}
					for(i = 0; i + move_count < length; i += move_count) {
						for(j = i; j < i + move_count; j++) {
							height += $diseases.eq(j).outerHeight();
						}
						get_margin_arr = getMarginTop($diseases, i + move_count, margin_top);
						margin_top = get_margin_arr[1];
						distance.push([i + move_count, get_margin_arr[0], height]);
						if(get_margin_arr[2]) {
							return;
						}
					}
				}
			}
		}
	}
	/***********病历疾病科室过滤条件end*********/

	/***********列表切换starting*********/
	function menuLeftMove() {
		$menu_drop.eq(0).parent().animate({
			"left": "-200px"
		}, "normal", "swing");
		$menu_drop.eq(1).parent().animate({
			"left": "0px"
		}, "normal", "swing");
	}

	function menuRightMove() {
		$menu_drop.eq(0).parent().animate({
			"left": "0px"
		}, "normal", "swing");
		$menu_drop.eq(1).parent().animate({
			"left": "2000px"
		}, "normal", "swing");
	}
	/***********列表切换ending*********/

	//门诊 、 一级目录等span的点击
	function navChange($span, $underline) {
		var left = $span.position().left,
			width = $span.css("width"),
			margin_left = parseInt($span.css("margin-left"));
		$span.addClass("focus").siblings().removeClass("focus");
		$underline.animate({
			"left": left + margin_left,
			"width": width
		}, 300, "swing");
	}

	//iframe的切换
	function iFrameChange($iFrame, $span, data_url) {
		if($iFrame.length <= 0) {
			$content_diagnosis.append('<iframe frameborder="0" seamless="" src="' + $span.attr("data-url") + '" data-url="' + data_url + '"></iframe>');
			$iFrame = $content_diagnosis.find("iframe[data-url=" + data_url + "]");
		}
		$iFrame.siblings("iframe").css("display", "none");
		$iFrame.fadeIn();
	}

	/*********返回患者列表starting********/
	function goBackPatientList() {
		//清空icss
		$(".content-diagnosis").find("iframe").attr("src", "icss.html?clinicId=-1&sexType=3&age=0");
		//隐藏病人详细信息
		$(".patient").find(".detail").hide();
		$(".patient").hide();
		$("ul.first-level>li>ul.second-level").find("a").removeClass("select");

		//返回到患者列表
		$("#serach_menz").hide();
		$(".serach_menz_second_level").hide();
		menuRightMove();
		$menu_change.eq(0).addClass("d-n");
	}
	/*********返回患者列表ending********/

	/***************更新患者就诊状态starting****************/
	function updateReceptionState(patId, regVisitedState) {
		$.ajax({
				url: Param.hostUrl + '/at/reception_state/update',
				type: 'GET',
				dataType: 'json',
				data: {
					hospitalId: loginData.hospitalId,
					registrationId: patId,
					regVisitedState: regVisitedState
				},
			})
			.done(function(res) {})
			.fail(function() {
				console.log("error");
			});
	}
	/***************更新患者就诊状态ending****************/

	/***************重新加载患者列表(当天)starting****************/
	function reloadPatientList() {
		$.ajax({
			type: "GET",
			url: Param.hostUrl + "/at/waiting_list/get",
			dataType: "json",
			data: loginData,
			success: function(data) {
				var data = data.data; //获取病人数组
				if(data == null || data.length == 0) {
					return;
				} else {
					var wait = 0; //待诊人数
					var start = 0; //诊断人数
					var finish = 0; //完诊人数
					var waitStr = ""; //待诊人名
					var startStr = ""; //诊断中人名
					var finishStr = ""; //完诊人名

					$.each(data, function(index, value) {
						if(value.regVisitedState == 0) {
							wait++;
							waitStr += "<a data-name='0' data-reg=" + value.id + " data-regVisitedState='" + value.patBirthday + "' patAge=" + value.patAge + " patName=" + value.patName + " patBirthday=" + value.patBirthday + " patSex=" + value.patSex + " data-patId=" + value.patId + " data-id=" + value.id + ">" + value.patName + "</a>";
						}
						if(value.regVisitedState == 1) {
							start++;
							startStr += "<a data-name='0' data-reg=" + value.id + " data-regVisitedState='" + value.patBirthday + "' patAge=" + value.patAge + " patName=" + value.patName + " patBirthday=" + value.patBirthday + " patSex=" + value.patSex + " data-patId=" + value.patId + " data-id=" + value.id + ">" + value.patName + "</a>";
						}
						if(value.regVisitedState == 2) {
							finish++;
							finishStr += "<a data-name='2' data-reg=" + value.id + " data-regVisitedState='" + value.patBirthday + "' patAge=" + value.patAge + " patName=" + value.patName + " patBirthday=" + value.patBirthday + " patSex=" + value.patSex + " data-patId=" + value.patId + " data-id=" + value.id + "><span class='font'>" + value.patName + "</span><span class='icon'></span></a>";
						}
					});
					//待诊人数实现
					$("nav.container-left .menu-drop ul.first-level:first a:first span:eq(2)").html(wait);
					//诊中人数实现
					$("nav.container-left .menu-drop ul.first-level:eq(0) li:eq(2) span:eq(2)").html(start);
					//完诊人数实现
					$("nav.container-left .menu-drop ul.first-level:eq(0) li:eq(4) span:eq(2)").html(finish);
					//待诊人名实现
					$("nav.container-left .menu-drop ul.first-level:eq(0) ul:first li").html(waitStr);
					//诊中人名实现
					$("nav.container-left .menu-drop ul.first-level:eq(0) ul:eq(1) li").html(startStr);
					//完诊人名实现
					$("nav.container-left .menu-drop ul.first-level:eq(0) ul:eq(2) li").html(finishStr);
					$patient = selectPatient();
				}
			},
		});

	}
	/***************重新加载患者列表ending****************/

	function selectPatient() {
		if(!$.isEmptyObject(patient)) {
			$patients_li.find("a[data-id='" + patient.patId + "']").addClass("select");
			patData.patientId = patient.patId;
			return $patients_li.find("a.select").parent();
		}
	}

	/***************初始化病历明细starting****************/
	function initializationDetailList(data, isRecover) {
		if($.isEmptyObject(patData)) {
			return;
		}

		// add 获取患者病例信息
		$(".case-list").remove();
		$(".detail").hide(400);
		$.ajax({
			url: Param.hostUrl + '/at/inquiry_info/index',
			type: 'GET',
			dataType: 'json',
			data: patData,
			success: function(res) {
				var data1 = res.data;
				var lastCase = false;
				if(data1.length == 0) {
					$("div.simple-info div#lishijilu").hide();
				} else {
					$("div.simple-info div#lishijilu").show();
					$("div.simple-info div#lishijilu").addClass("lishijilu");
					$("div.simple-info div#lishijilu").removeClass("lishijilu_hover_clicked");
				}
				for(var i = 0; i < data1.length; i++) {
					var str = "<li class='case-list' data-id=" + res.data[i].id + ">\
                                            <a>\
                                                <span class='arrow d-n'></span>\
                                                <span class='font'>" + res.data[i].diagnose + "</span>\
                                                <span class='add " + (res.data[i].sign == "1" ? "focus" : "") + "'></span>\
                                                <span class='date'>" + time(res.data[i].clinicTime) + "</span>\
                                            </a>\
                                        </li>";

					if(res.data[i].deptCode == loginData.departId && isRecover && !lastCase) {
						str = "<li class='case-list' data-id=" + res.data[i].id + ">\
                                            <a class='select'>\
                                                <span class='arrow d-n'></span>\
                                                <span class='font'>" + res.data[i].diagnose + "</span>\
                                                <span class='add " + (res.data[i].sign == "1" ? "focus" : "") + "'></span>\
                                                <span class='date'>" + time(new Date()) + "</span>\
                                            </a>\
                                        </li>";
						$("#second_level_ul").prepend(str);
						lastCase = true;
					} else {
						$("#second_level_ul").append(str);
					}
				}

				if(!lastCase && $("#clinic_date").val() == time(new Date(), 1)) {
					var str = "<li class='case-list'>\
                                            <a class='select'>\
                                                <span class='arrow d-n'></span>\
                                                <span class='font'>诊中...</span>\
                                                <span class='add' style='visibility:hidden'></span>\
                                                <span class='date'>" + time(new Date()) + "</span>\
                                            </a>\
                                        </li>";
					$("#second_level_ul").prepend(str);
					$(".patient div.lishijilu").hide();
				}
				var getIndexId = $("#second_level_ul li a.select").parent().attr("data-id");
				$("#second_level_ul li:first>a>.arrow").removeClass("d-n");
				sessionStorage.setItem("getIndexId", getIndexId);
				aClickEvent();
			}
		});

	}
	/***************初始化病历明细ending****************/

	/***************初始化门诊病历选择条件starting****************/
	function initializationMenuMenz(patData) {
		var clinicDates = [];
		$.ajax({
			url: Param.hostUrl + '/at/inquiry_info/index',
			type: 'GET',
			dataType: 'json',
			data: patData,
			success: function(data) {
				//历史病历
				var data = data.data;

				$(data).each(function(index, inquiry) {
					clinicDates.push(new Date(inquiry.clinicTime));
				});
				$("#search_disease ul:first").children().remove();
				$("#search_dept ul:first").children().remove();

				if(data.length == 0) {
					$("#search_disease").unbind("mouseenter").unbind("mouseleave");
					$("#search_dept").unbind("mouseenter").unbind("mouseleave");
				} else {
					searchDiseaseHover();
					searchDeptHover();
					$(data).each(function(index, detail) {
						//疾病
						var disStr = "<li data-type='disease' data-id='" + detail.diseaseId + "'><a><span>" + detail.diagnose + "</span></a></li>";
						var $disli = $("#search_disease ul:first").find("li[data-id='" + detail.diseaseId + "']");
						if($disli.length == 0) {
							$("#search_disease ul:first").prepend(disStr);
						}

						//部门
						var deptStr = "<li data-type='dept' data-code='" + detail.deptCode + "'><a><span>" + detail.dept + "</span></a></li>";
						var $deptli = $("#search_dept ul:first").find("li[data-code='" + detail.deptCode + "']");
						if($deptli.length == 0) {
							$("#search_dept ul:first").append(deptStr);
						}
					});
				}
				aClickEvent();

				initDateRangePicker(clinicDates);

				function initDateRangePicker(clinicDates) {
					var locale = {
						"format": 'YYYY/MM/DD',
						"separator": " - ",
						"applyLabel": "确定",
						"cancelLabel": "取消",
						"fromLabel": "起始时间",
						"toLabel": "结束时间'",
						"customRangeLabel": "自定义",
						"weekLabel": "W",
						"daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
						"monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
						"firstDay": 1
					};
					var options = {
						"locale": locale,
						"startDate": moment(),
						"endDate": moment(),
						"markDates": clinicDates
					};
					$('#clinic_time_range').daterangepicker(options, function(start, end, label) {
						resetFilter();
						patData.startTime = new Date(start);
						patData.endTime = new Date(end);
						initializationDetailList(patData);
						$("#serach_menz").hide();
					});
				}
			}
		});
	}
	/***************初始化门诊病历选择条件ending****************/

	$("#icss").contents().find("body").scroll(function() {
		console.info(111);
	});

	// 弹窗增加事件
	(function($) {
		var $confirm_models = $("#confirm-model-one,#confirm-model-to,#confirm-model-recover,#confirm-model-save"),
			$close = $confirm_models.find("p.close"),
			$buttons = $confirm_models.find("div.button").children(),
			$shade = $("#shade");
		$close.on("click.index", function() {
			fadeOut($confirm_models, $buttons, $shade);
		});
		$shade.on("click", function() {
			fadeOut($confirm_models, $buttons, $shade);
		});

		function fadeOut($confirm_models, $buttons, $shade) {
			$confirm_models.removeClass("animated flipInY").fadeOut();
			$buttons.off("click");
			$shade.fadeOut();
		}
	})(jQuery);

	//保存弹窗事件
	(function($) {
		var $confirm_models = $("#confirm-model-save-main,#confirm-model-save-diagnosis,#confirm-model-save-treatment"),
			$close = $confirm_models.find("p.close"),
			$buttons = $confirm_models.find("div.button").children(),
			$shade = $("#shade");
		$close.on("click.index", function() {
			fadeOut($confirm_models, $buttons, $shade);
		});
		$shade.on("click", function() {
			fadeOut($confirm_models, $buttons, $shade);
		});

		function fadeOut($confirm_models, $buttons, $shade) {
			$confirm_models.removeClass("animated flipInY").fadeOut();
			$buttons.off("click");
			$shade.fadeOut();
		}
	})(jQuery);

	return {
		'patData': patData,
		'initializationDetailList': initializationDetailList,
		'initializationList': initializationList
	}

})(jQuery);
var index = {
	showDeleteMain: function($p) {
		var $shade = $("#shade"),
			$model = $("#confirm-model-to"),
			$buttons = $model.find("div.button"),
			$left_button = $buttons.children(".left"),
			$right_button = $buttons.children(".right");

		$model.addClass("animated flipInY").show();
		$shade.fadeIn();
		$left_button.off("click");
		$right_button.off("click");
		$left_button.on("click", function() {
			var data_relation_id = $p.attr("data-relation-id"),
				$choose = $p.parent(),
				$symptom_form = $choose.siblings("div.symptom-container").children("div.symptom-form[data-relation-id=" + data_relation_id + "]");
			$p.remove();
			$symptom_form.remove();
			if($choose.children().length === 0) {
				$choose.addClass("d-n");
			}
			$model.addClass("animated flipInY").show();
			$shade.fadeIn();
		});

		$right_button.on("click", function() {
			$model.removeClass("animated flipInY").fadeOut();
			$buttons.off("click");
			$shade.fadeOut();
		});
	},
	showDeleteAll: function($p) {
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
		$left_button.on("click", function() {
			var data_relation_id = $p.attr("data-relation-id"),
				$symptom_form = $choose.siblings("div.symptom-container").children("div.symptom-form[data-relation-id=" + data_relation_id + "]"),
				$choose_next = $group_next.find("div.choose:first"),
				$symptom_form_next = $choose_next.siblings("div.symptom-container").children("div.symptom-form[data-relation-id=" + data_relation_id + "]"),
				$p_next = $choose_next.find("p[data-relation-id=" + data_relation_id + "]");
			// 删除主诉
			$p.remove();
			$symptom_form.remove();
			if($choose.children("div.content").children().length === 0) {
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
			window.frames[0].contentWindow.cleanCacheByType($group);
		});

		$right_button.on("click", function() {
			var data_relation_id = $p.attr("data-relation-id"),
				$symptom_form = $choose.siblings("div.symptom-container").children("div.symptom-form[data-relation-id=" + data_relation_id + "]"),
				$choose_next = $group_next.find("div.choose:first"),
				$symptom_form_next = $choose_next.siblings("div.symptom-container").children("div.symptom-form[data-relation-id=" + data_relation_id + "]");
			// 删除主诉
			$p.remove();
			$symptom_form.remove();
			if($choose.children("div.content").children().length === 0) {
				$choose.addClass("d-n");
				$choose.siblings("input").removeClass("d-n");
			}

			//同时删除现病史
			$choose_next.find("p[data-relation-id=" + data_relation_id + "]").remove();
			$symptom_form_next.remove();
			if($choose_next.children("div.content").children().length === 0) {
				$choose_next.addClass("d-n");
			}

			//隐藏弹窗和遮罩
			$model.removeClass("animated flipInY").fadeOut();
			$buttons.off("click");
			$shade.fadeOut();
			window.frames[0].contentWindow.cleanCacheByType($group);
			window.frames[0].contentWindow.cleanCacheByType($group_next);
		});
	},
	recoverLast: function(fn, fn_arg) {
		var $shade = $("#shade"),
			$model = $("#confirm-model-recover"),
			$buttons = $model.find("div.button"),
			$confirm_button = $buttons.children(".left"),
			$cancel_button = $buttons.children(".right"),
			$body_p = $model.children('.body').children('p'),
			time = new Date(fn_arg[1].clinicTime);
		$body_p.text('是否恢复上次诊中数据:' + fn_arg[1].diagnose + '-' + time.getFullYear() + '/' + (time.getMonth() + 1) + '/' + time.getDate());
		$model.addClass("animated flipInY").show();
		$shade.fadeIn();
		$confirm_button.off("click");
		$cancel_button.off("click");
		$confirm_button.on("click", function() {
			fn_arg.push(true);
			fn.apply(null, fn_arg);
			index2.initializationDetailList(index2.patData, true);
			$model.removeClass("animated flipInY").fadeOut();
			$buttons.off("click");
			$shade.fadeOut();
		});
		$cancel_button.on("click", function() {
			fn_arg.push(false);
			fn.apply(null, fn_arg);
			index2.initializationDetailList(index2.patData, false);
			$model.removeClass("animated flipInY").fadeOut();
			$buttons.off("click");
			$shade.fadeOut();
		});
	}

};