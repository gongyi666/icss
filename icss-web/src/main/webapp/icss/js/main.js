var dynamicUrl = "http://192.168.2.165:8090/icss-web/kl/questioninfo/get_questioninfo_by_subitemid?subitemId=";

//病历单input框大小
function inputSize(n){
    var inputPart = $(".medical-record-sheet li").eq(n),
        paddingWidth = inputPart.find(".choose-list").width(),
        inputHeight = inputPart.find(".choose-list").height();
    console.log(paddingWidth,inputHeight)
    inputPart.find("input").css({"width":755-paddingWidth,"hright":inputHeight,"padding-left":paddingWidth+10})

}
//*******************弹层*******************************//
/*fancyBox()
function fancyBox(){
    $(".more-bt").click(function(){
        $(this).parents(".medical-record-sheet li").addClass("apdCs");
        $("#fancy-box").show();
        return false;
    });
    val();
    $(window).resize(function(){
        val();
    });
    function val(){
        var WinWidth =$(window).width();
        var WinHeight  =$(window).height();
        var Popwidth =$("#fancy-ct").width();
        var Popheight =$("#fancy-ct").height();
        var Leftwidth = (WinWidth-Popwidth)/2+"px";
        var topheight = (WinHeight-Popheight)/2+"px";

        $("#fancy-box,#fancy-mask").css({width:WinWidth,height:WinHeight});
        $("#fancy-ct").css({left:Leftwidth,top:topheight});
    };
    $("#close").click(function(){
        $("#fancy-box").hide();
        $(".medical-record-sheet li").removeClass("apdCs");
        return false;
    });
}*/



/**
 * Created by Administrator on 2017/2/24.
 */
