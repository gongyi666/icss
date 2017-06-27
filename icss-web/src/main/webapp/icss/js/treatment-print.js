(function($){
    var $group = $("#container").children(".group");//获取所有的打印组合
    var print_data = {
        main_suit: [],//主诉
        current_history: [],//现病史
        bypast_history: [],//既往史
        other_history: [],//其他史
        body_sign: [],//体征
        assay: [],//化验
        instrument_test: [],//器查
        diagnose: [],//诊断
        treat: []//治疗
    };

    /** 
     *  获取group组的打印数据函数
     * */ 
    var GroupData = function(){}

    //获取group的需要打印项
    GroupData.prototype.getItem = function(num,sel){
        if(sel == undefined){
            return $group.eq(num).find(".choose p");
        }else {
            return $group.eq(num).find(".choose .content " + sel);
        }
    }
    //获取打印项的数据
    GroupData.prototype.getData = function(option){
        var $itemsP = this.getItem(option.groupNum, "p");
        var $itemsDiv = this.getItem(option.groupNum, "div");
        var $item = this.getItem(option.groupNum);
        //由于各group的生成的dom结构不同，所以需要分情况进行获取
        if($itemsP.length > 0 || $itemsDiv.length > 0 ||  $item.length > 0){
            if(option.groupNum === 0 || option.groupNum === 1 ||option.groupNum === 4 
            ){
                groupDiff.commen($itemsP,option.item);
            }else if( option.groupNum === 2 || option.groupNum === 3 ){
                var $itemChoose = $group.eq(option.groupNum).find(".choose:not(.d-n)");
                groupDiff.pastAndOther($itemChoose ,option.item);
            }else if(option.groupNum === 5 ){
                if($itemsP.length > 0){
                     groupDiff.diffSpan($itemsP,option.item);
                }
                if($itemsDiv.length > 0){
                     groupDiff.spanAndDiv($itemsDiv,option.item);
                }
            }else if(option.groupNum === 6){
                if($itemsP.length > 0){
                     groupDiff.diffSpan($itemsP,option.item);
                }
                if($itemsDiv.length > 0){
                     groupDiff.diffSpan($itemsDiv,option.item);
                }
            }
            else if(option.groupNum === 7){
                groupDiff.diffSpan($itemsP,option.item);
            }else if(option.groupNum === 8){
                groupDiff.diffSpan($item,option.item)
            }
        }else {
            // console.log("未获取到"+ option.item +"的数据");
        }
    };
    var treat_id = 0;
    var dis_id = 0;
    // 由于group中有dom结构有差异，所以分为了几种情况
    var groupDiff = {
        //当点击保存时，有可能会重复保存，所以进行去重复处理
        'trim':function(itemName,$value){
            for(var i = 0; i < print_data[itemName].length;i++){
                // if($value != print_data[itemName][i]){
                //     print_data[itemName].push( $value);
                // }
                if(print_data[itemName].indexOf($value) == -1){
                    print_data[itemName].push( $value);
                }
            }
            if(print_data[itemName].length==0){
                print_data[itemName].push( $value );
            }
        },
        'commen':function( $items, itemName){
            $items.each(function(index, value){
                groupDiff.trim(itemName,$(value).find("span").eq(1).text());
            });
        },
        "diffSpan":function($items,itemName){
            $items.each(function(index, value){
                groupDiff.trim(itemName, $(value).text());
            });
        },
        //拥有span和div的dom，即选择了化验中的已检
        "spanAndDiv":function($items,itemName){
            
            $items.each(function(index, value){
                var $lis = $(value).children("ul").eq(1).children("li");
                var arr = [];
                $lis.each(function(i,v){
                    arr.push( $(v).text() );
                });
                var title = $(value).children("ul:first").text(); 
                var time =  $(value).children("ul").eq(2).text();
                
                print_data[itemName].push({
                    "diagnosePast":1,
                    "id":dis_id++,
                    "title": title,
                    "time": time,
                    "value": arr
                });
            });
        },
        "pastAndOther": function($chooses,itemName){
            $chooses.each(function(index, value){
                var $title = $(value).children(".title").text().trim();
                var $content = $(value).children(".content").find("p"); 
                var arr = [];
                $content.each(function(i,v){
                    var $p = $(v);
                   arr.push($p.text());
                });
                 print_data[itemName].push({
                    "title":$title,
                    "value":arr,
                    "id":treat_id++
                });
            });
        }
    };

        /**
         * 打印预览
         */
		var groupDatas = {};
		//获取到打印的数据
		function getPrintData() {
            function getLocalTime(nS) {
                nS = nS / 1000;
                return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/, ' ');
            }
			// if(localStorage.getItem("groupData")) {
			// 	groupDatas = JSON.parse(localStorage.getItem("groupData"));
			// };
			// localStorage.removeItem("groupData");

			var $patient = $(".patient");
			var $simple_info = $patient.children(".simple-info");

			var $detail = $patient.children(".detail");

			var $info_detail = $detail.find(".info-detail");
			var $span = $info_detail.children("span");
			//病人信息
            var pat_sex =( Param.sexType == 1 || Param.sexType == "男" )? "男" : "女";
			groupDatas.patient = {
				"cardId": Param.cardId,
				"patientName": Param.patientName,
				"age": Param.age,
				"sex": pat_sex,
				"marry": Param.marry,
				"nation": Param.nation,
				"occupation": Param.occupation,
				"unit": Param.unit,
				"phone": Param.phone,
				"insurance": Param.insurance,
				"insuranceId": Param.insuranceId
			};
			//医生信息
			groupDatas.doctor = {
				"section": Param.dept,
				"doctor": Param.doctorName,
				"outpatientId": Param.outpatientId,

				"treatmentTime": getLocalTime(Date.now()).split(" ")[0],//Param.treatmentTime

				"type": Param.type
			};
		}

    //点击保存时，将数据存入localStorage中，因为存在跨ifream传递数据。
    $("#beforePrint").on("click", function(){
        $("#treate-container").removeClass("d-n");
        
        var data = new GroupData();
        data.getData({groupNum:0, item:"main_suit"});
        data.getData({groupNum:1, item:"current_history"});
        data.getData({groupNum:2, item:"bypast_history"});
        data.getData({groupNum:3, item:"other_history"});
        data.getData({groupNum:4, item:"body_sign"});
        data.getData({groupNum:5, item:"assay"});
        data.getData({groupNum:6, item:"instrument_test"});
        data.getData({groupNum:7, item:"diagnose"});
        data.getData({groupNum:8, item:"treat"});
        groupDatas = print_data;

        //重置数据，不重置的话，会造成数据重复叠加。
        print_data = {
            main_suit: [],//主诉
            current_history: [],//现病史
            bypast_history: [],//既往史
            other_history: [],//其他史
            body_sign: [],//体征
            assay: [],//化验
            instrument_test: [],//器查
            diagnose: [],//诊断
            treat: []//治疗
        };
        getPrintData();
       
        $('#treate-container').html($("#treatement").tmpl(groupDatas));
        //$(window).scrollTop(0);
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