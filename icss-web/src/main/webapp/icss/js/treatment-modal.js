$(function(){
	rebindTreatmentClick();
	$(document).on("click",function () {
        $(".medical-record-sheet>li").eq(8).find(".second-levl").hide();
    });
})

function rebindTreatmentClick(){
    $(".medical-record-sheet li").each(function(index){
    	if(index == 8){
    		var drugInfoWrapper = {};
            drugInfoWrapper["deptNo"]="001";
            drugInfoWrapper["doctorId"]="1";
            drugInfoWrapper["diseaseIds"]=[];
            drugInfoWrapper["size"]="14";
            drugInfoWrapper["sysType"]=null;
    		$(this).find("input[type=text]").focus(function(){
                _thsParent = $(this).parents("li"),
                apdArea = _thsParent.find(".second-levl"),
                url = host_url+'kl/druginfo/get_drug_by_Treatment';
                _thsParent.on("click.search",function (e) {
                   return false;
                });
                ajaxPushTreatmentList(apdArea,this,url,drugInfoWrapper);
                apdArea.show();
                _thsParent.siblings().find(".second-levl").hide();
                //addDiagnosisSecondLevlButtonClick(apdArea,this);
            });
    		
    		$(this).find("input[type=text]").keyup(function(){
                var keyword = $(this).val().replace(/^\s\s*/, '').replace(/\s\s*$/, ''),
                        url = host_url+'kl/druginfo/get_drug_by_input?size=14&inputstr='+keyword;
                ajaxPushTreatmentList($(this).parents("li").find(".second-levl"),this,url,drugInfoWrapper);
            })
    	}
    });
}

function ajaxPushTreatmentList(area,el,url,drugInfoWrapper){
	$.ajax({
        type: "post",
        dataType: "json",
		contentType: "application/json",
		data: JSON.stringify(drugInfoWrapper),
        url: url,
        success: function (data) {
            $(area).html($("#templateDrug").tmpl(data));
            $(area).find("li[tip-title]").each(function(){
                $(this).blueTip();
            });
           // pushDiagnosisList(el);

            var obj={
                "fancy.modal.show":function () {
                    //console.log("show");
                },
                "fancy.modal.shown":function () {
                    //console.log("shown");
                },
                "fancy.modal.hide":function () {
                    //console.log("hide");
                },
                "fancy.modal.hidden":function () {
                    //console.log("hiden");
                },
                "fancy.modal.loaded":function () {
                    val();
                    $(window).resize(function(){
                        val();
                    });
                    function val(){
                        var WinWidth =$(window).width();
                        var WinHeight  =$(window).height();
                        var Popwidth =$(".fancy-ct").width();
                        var Popheight =$(".fancy-ct").height();
                        var Leftwidth = (WinWidth-Popwidth)/2+"px";
                        var topheight = (WinHeight-Popheight)/2+"px";

                        $(".fancy-box,.fancy-mask").css({width:WinWidth,height:WinHeight});
                        $(".fancy-ct").css({left:Leftwidth,top:topheight});
                    };
                }
            };
            $(area).find("p.more-bt").fancyModal(1,obj);
        },
        error: function () {
            alert("请求失败");
        }
    });
}