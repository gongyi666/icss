/**
 * Created by Kiva on 17/2/28.
 */
(function ($) {
    var $li=$("div.medical-record-sheet").children("li").eq(5);
    $li.on("click.detail-list-confirm","button",function (e) {
        console.log(e.target.className);
        if(e.target.className!=="make-sure usable") return;
        var $confirm_button=$(e.target),
            $detail_list=$confirm_button.parent().parent().parent(),
            $pro_name_h3=$detail_list.find("h3.pro-name:first"),
            $form_area_dl=$detail_list.find("div.form-area:first").find("dl"),
            info="";
        info+=$pro_name_h3.find("b").text()+": "+getInfo($pro_name_h3.find("span:first"))+",";
        $form_area_dl.each(function () {
            var $dl=$(this),
                $dt=$dl.children("dt"),
                $dd=$dl.children("dd");
            info+=$dt.text()+": ";
            $dd.children().each(function () {
               var $this=$(this);
               info+=getInfo($this);
            });
            info+=",";
        });
        alert(info.slice(0,-1));
    });

    function getInfo($node) {
        var info="";
       // console.log($node);
        switch ($node[0].tagName){
            case "SPAN":
                if($node.hasClass("check-state")){
                    info+=$node.text()+" ";
                }
                return info;
            case "TEXTAREA":
                info+=$node.val()+" ";
                return info;
        }
    }

})(jQuery);