(function($) {
	//问诊记录表内容JSON字符串
var inquiryDatas = 
	[
	    {
	        "type": 0,
	        "choose": [
	            {
	                "itemId": "1346",
	                "itemName": "腹胀",
	                "itemDescribe": "腹胀1天",
	                "type": 1,
	                "remark": ""
	            }
	        ],
	        "details": [
	            {
	                "itemId": "1346",
	                "title": {
	                    "itemName": "腹胀",
	                    "checkboxClass": "c-r checkbox d-n"
	                },
	                "contents": [
	                    {
	                        "id": "1",
	                        "type": "7",
	                        "date_toggle": 1,
	                        "date_one_time": "1",
	                        "date_one_unit": "天"
	                    },
	                    {
	                        "id": "2",
	                        "type": "10"
	                    },
	                    {
	                        "id": "3",
	                        "type": "3"
	                    },
	                    {
	                        "id": "4",
	                        "type": "3"
	                    },
	                    {
	                        "id": "5",
	                        "type": "6"
	                    }
	                ]
	            }
	        ]
	    },
	    {
	        "type": 1,
	        "choose": [
	            {
	                "itemId": "1346",
	                "itemName": "腹胀",
	                "itemDescribe": "腹胀1天",
	                "type": 9,
	                "remark": ""
	            }
	        ],
	        "details": [
	            {
	                "itemId": "1346",
	                "title": {
	                    "itemName": "腹胀",
	                    "checkboxClass": "c-r checkbox d-n"
	                },
	                "contents": [
	                    {
	                        "id": "1",
	                        "type": "7",
	                        "date_toggle": 1,
	                        "date_one_time": "1",
	                        "date_one_unit": "天"
	                    },
	                    {
	                        "id": "2",
	                        "type": "10"
	                    },
	                    {
	                        "id": "3",
	                        "type": "3"
	                    },
	                    {
	                        "id": "4",
	                        "type": "3"
	                    },
	                    {
	                        "id": "5",
	                        "type": "6"
	                    }
	                ]
	            }
	        ]
	    },
	    {
	        "type": 2,
	        "choose": [],
	        "details": []
	    },
	    {
	        "type": 3,
	        "choose": [],
	        "details": []
	    },
	    {
	        "type": 4,
	        "choose": [],
	        "details": []
	    },
	    {},
	    {},
	    {
	        "type": 7,
	        "choose": [
	            {
	                "itemId": "1798",
	                "itemName": "糖尿病性胃轻瘫",
	                "itemDescribe": "糖尿病性胃轻瘫（初诊,拟诊）",
	                "type": 7,
	                "remark": ""
	            }
	        ],
	        "details": [
	            {
	                "itemId": "1798",
	                "diagnosisname": "糖尿病性胃轻瘫（初诊,拟诊）",
	                "title": {
	                    "itemName": "糖尿病性胃轻瘫",
	                    "checkboxClass": ""
	                },
	                "contents": [
	                    {
	                        "type": "willTreat",
	                        "value": "初诊"
	                    }
	                ]
	            }
	        ]
	    },
	    {
	        "type": 8,
	        "choose": [
	            {
	                "isGroup": 1,
	                "itemId": 14856,
	                "itemName": "甘露醇注射液",
	                "itemDescribe": "甘露醇注射液:0.02ml,静脉滴注,每日三次,疗程123天",
	                "type": 8,
	                "remark": ""
	            },
	            {
	                "isGroup": 0,
	                "itemId": 14998,
	                "itemName": "罗红霉素分散片",
	                "itemDescribe": "罗红霉素分散片:0.05mg,雾化吸入,每日一次,疗程1天",
	                "type": 8,
	                "remark": ""
	            },
	            {
	                "isGroup": 0,
	                "itemId": 15001,
	                "itemName": "螺内酯片",
	                "itemDescribe": "螺内酯片:40mg,口服,每日一次,疗程1天",
	                "type": 8,
	                "remark": ""
	            }
	        ],
	        "details": [
	            {
	                "isGroup": 1,
	                "itemId": "",
	                "title": {
	                    "itemName": "新注射组合"
	                },
	                "contents": {
	                    "type": "1",
	                    "drugInfoList": [
	                        {
	                            "id": null,
	                            "drgId": 16408,
	                            "drugName": "5%葡萄糖氯化钠注射液",
	                            "grpNum": 0,
	                            "speId": null,
	                            "manId": null,
	                            "drgOnceDose": "100",
	                            "drgDoseUnit": "mh",
	                            "modId": "29",
	                            "freEnName": "QD",
	                            "drgUseDay": 0,
	                            "drgGroupNum": 0,
	                            "drgQuantity": 0,
	                            "hisDrgName": null,
	                            "doctorId": null,
	                            "hospitalCode": null,
	                            "skinTest": null,
	                            "drgGroupId": null,
	                            "hisDrugInfoDetail": null,
	                            "crowdType": 1,
	                            "type": "1",
	                            "isGroup": "1",
	                            "drugPsychotropic": "0",
	                            "drugFormulation": "1",
	                            "minDosage": 200,
	                            "maxDosage": 500,
	                            "commonDosage": null,
	                            "drugCourse": 1,
	                            "maxDayDosage": null,
	                            "minDayDosage": null,
	                            "hisDrgId": null,
	                            "beizhu": ""
	                        },
	                        {
	                            "id": null,
	                            "drgId": 17271,
	                            "drugName": "氨溴索注射液",
	                            "grpNum": 0,
	                            "speId": null,
	                            "manId": null,
	                            "drgOnceDose": "0.01",
	                            "drgDoseUnit": "ml",
	                            "modId": "116",
	                            "freEnName": "TID",
	                            "drgUseDay": 0,
	                            "drgGroupNum": 0,
	                            "drgQuantity": 0,
	                            "hisDrgName": null,
	                            "doctorId": null,
	                            "hospitalCode": null,
	                            "skinTest": null,
	                            "drgGroupId": null,
	                            "hisDrugInfoDetail": null,
	                            "crowdType": 1,
	                            "type": "1",
	                            "isGroup": null,
	                            "drugPsychotropic": "0",
	                            "drugFormulation": "1",
	                            "minDosage": 0.05,
	                            "maxDosage": 0.1,
	                            "commonDosage": null,
	                            "drugCourse": 2,
	                            "maxDayDosage": null,
	                            "minDayDosage": null,
	                            "hisDrgId": null,
	                            "beizhu": ""
	                        },
	                        {
	                            "id": null,
	                            "drgId": 14856,
	                            "drugName": "甘露醇注射液",
	                            "grpNum": 0,
	                            "speId": null,
	                            "manId": null,
	                            "drgOnceDose": "0.02",
	                            "drgDoseUnit": "ml",
	                            "modId": "101",
	                            "freEnName": "QD",
	                            "drgUseDay": 0,
	                            "drgGroupNum": 0,
	                            "drgQuantity": 0,
	                            "hisDrgName": null,
	                            "doctorId": null,
	                            "hospitalCode": null,
	                            "skinTest": null,
	                            "drgGroupId": null,
	                            "hisDrugInfoDetail": null,
	                            "crowdType": 1,
	                            "type": "1",
	                            "isGroup": null,
	                            "drugPsychotropic": "0",
	                            "drugFormulation": "1",
	                            "minDosage": 0.05,
	                            "maxDosage": 0.1,
	                            "commonDosage": null,
	                            "drugCourse": 2,
	                            "maxDayDosage": null,
	                            "minDayDosage": null,
	                            "hisDrgId": null,
	                            "beizhu": ""
	                        }
	                    ],
	                    "modName": "静脉滴注",
	                    "frequencyName": "每日三次",
	                    "frequencyNum": "1",
	                    "liaocheng": "123",
	                    "jiaji": 0,
	                    "zibei": 0,
	                    "daijian": 0,
	                    "psychotropic": "0",
	                    "name": "新注射组合",
	                    "source": -1
	                }
	            },
	            {
	                "isGroup": 0,
	                "itemId": "",
	                "title": {},
	                "contents": {
	                    "type": "1",
	                    "drugInfoList": [
	                        {
	                            "id": null,
	                            "drgId": 14998,
	                            "drugName": "罗红霉素分散片",
	                            "grpNum": 0,
	                            "speId": null,
	                            "manId": null,
	                            "drgOnceDose": "0.05",
	                            "drgDoseUnit": "mg",
	                            "modId": "101",
	                            "freEnName": "QD",
	                            "drgUseDay": 0,
	                            "drgGroupNum": 0,
	                            "drgQuantity": 0,
	                            "hisDrgName": null,
	                            "doctorId": null,
	                            "hospitalCode": null,
	                            "skinTest": null,
	                            "drgGroupId": null,
	                            "hisDrugInfoDetail": null,
	                            "crowdType": 1,
	                            "type": "1",
	                            "isGroup": null,
	                            "drugPsychotropic": "0",
	                            "drugFormulation": null,
	                            "minDosage": 0.05,
	                            "maxDosage": 0.1,
	                            "commonDosage": null,
	                            "drugCourse": 1,
	                            "maxDayDosage": null,
	                            "minDayDosage": null,
	                            "hisDrgId": null,
	                            "beizhu": ""
	                        }
	                    ],
	                    "modName": "雾化吸入",
	                    "frequencyName": "每日一次",
	                    "frequencyNum": "1",
	                    "liaocheng": "1",
	                    "jiaji": 0,
	                    "zibei": 1,
	                    "daijian": 0,
	                    "psychotropic": "0",
	                    "source": -1
	                }
	            },
	            {
	                "isGroup": 0,
	                "itemId": "",
	                "title": {},
	                "contents": {
	                    "type": "1",
	                    "drugInfoList": [
	                        {
	                            "id": null,
	                            "drgId": 15001,
	                            "drugName": "螺内酯片",
	                            "grpNum": 0,
	                            "speId": null,
	                            "manId": null,
	                            "drgOnceDose": "40",
	                            "drgDoseUnit": "mg",
	                            "modId": "31",
	                            "freEnName": "QD",
	                            "drgUseDay": 0,
	                            "drgGroupNum": 0,
	                            "drgQuantity": 0,
	                            "hisDrgName": null,
	                            "doctorId": null,
	                            "hospitalCode": null,
	                            "skinTest": null,
	                            "drgGroupId": null,
	                            "hisDrugInfoDetail": null,
	                            "crowdType": 1,
	                            "type": "1",
	                            "isGroup": null,
	                            "drugPsychotropic": "0",
	                            "drugFormulation": null,
	                            "minDosage": 20,
	                            "maxDosage": 80,
	                            "commonDosage": null,
	                            "drugCourse": 1,
	                            "maxDayDosage": null,
	                            "minDayDosage": null,
	                            "hisDrgId": null,
	                            "beizhu": ""
	                        }
	                    ],
	                    "modName": "口服",
	                    "frequencyName": "每日一次",
	                    "frequencyNum": "1",
	                    "liaocheng": "1",
	                    "jiaji": 0,
	                    "zibei": 0,
	                    "daijian": 0,
	                    "psychotropic": "0",
	                    "source": -1
	                }
	            }
	        ]
	    }
	];
	
	var psychotropicType = 0; //上级页面传过来的处方类型：0普通，1精神，2麻醉，3放射
	var prescripDatas = {};   
	//获取处方单所需信息
	function getDatas(){
		var	diagnose = [],
		    treat = [];
		function getLocalTime(nS) {
	        nS = nS / 1000;
	        return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/, ' ');
	    }
		
		//病人信息
	    var pat_sex =( Param.sexType == 1 || Param.sexType == "男" )? "男" : "女";
		prescripDatas.patient = {
			"patientName": Param.patientName,
			"insurance": Param.insurance,
			"sex": pat_sex,
			"phone": Param.phone,
			"age": Param.age,
			"address":"浙江省杭州市西湖区",
			"cardId": Param.cardId
		};
		//医生信息
		prescripDatas.doctor = {
			"section": Param.dept,
			"outpatientId": Param.outpatientId,
			"doctor": Param.doctorName,
			"issueTime": getLocalTime(Date.now()).split(" ")[0]
		};
		
		//诊断
		var diagnoseDatas = inquiryDatas[7];
		$.each(diagnoseDatas.choose,function(){
			diagnose.push(this.itemDescribe);
		});
		prescripDatas.diagnose = diagnose;
		
		
		//治疗
		var treatDatas = inquiryDatas[8];
		    drugDetails = treatDatas.details;
		$.each(drugDetails,function(i,drugDetail){
			var contents = drugDetail.contents;
			var psychotropic = contents.psychotropic;
			var drugInfoList = contents.drugInfoList;
			if(psychotropic == psychotropicType){
				//如果勾选自备:只显示药品名称
				if(contents.zibei == 1){
					$.each(drugInfoList,function(){
						treat.push(this.drugName);
					})
				}else{
					$.each(drugInfoList,function(j,drugInfo){
						//如果有单次量和单次量单位（暂不判断）
						var val = "";
						if(drugInfo.skinTest == null){
							val = drugInfo.drugName+":"+drugInfo.drgOnceDose+drugInfo.drgDoseUnit+"，"+contents.modName
							+"，"+contents.frequencyName+"，疗程"+contents.liaocheng+"天";
						}else{
							val = drugInfo.drugName+":"+drugInfo.drgOnceDose+drugInfo.drgDoseUnit+"(皮试)，"+contents.modName
							+"，"+contents.frequencyName+"，疗程"+contents.liaocheng+"天";
						}
						treat.push(val);
					});
				}
			}else{
				return;
			}
		})
		prescripDatas.treat = treat;
	}
	
    //点击保存时，将数据存入localStorage中，因为存在跨ifream传递数据。
    $("#beforePrint").on("click", function(){
        $("#treate-container").removeClass("d-n");
        getDatas();
        $('#treate-container').html($("#prescription").tmpl(prescripDatas));
        var overflowVal = $("body").css("overflow-y");
        $("body").css({
            "overflow-y":"hidden"
        });
        //关闭layer层
		$(".print__head--close").on("click", function() {
            $("body").css({
                "overflow-y":overflowVal
            });
			$("#treate-container").addClass("d-n");
		});
    });
})(jQuery);