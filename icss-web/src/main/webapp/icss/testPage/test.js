/**
 * Created by Kiva on 17/4/28.
 */
(function ($) {
    var $symptom_form = $("div.symptom-form[data-relation-id=3]");
    common.symptomFormAddEvent($symptom_form);
    $.getJSON("save.json", function (data) {
        var details = data.data[1].details,
            $group = $("div.group:first");
        recoverData(details, $group);
    });


    function recoverData(data, $group) {
        $.each(data, function (i, v) {
            var $symptom_form = $group.find("div.symptom-form[data-relation-id=" + v.itemId + "]"),
                $form_groups = $symptom_form.find("div.form-group");
            $.each(v.contents, function (i2, v2) {
                var $form_group,$inputs;
                if(v2.type==="7"){
                    $form_group=$form_groups.filter("[data-id="+v2.id+"][data-type=7]");
                    if(v2.dateToggle==="1"){

                    }else{
                        $form_group.find("input[name=date_to_year]").val(v2["date_to_year"]);
                        $form_group.find("input[name=date_to_month]").val(v2["date_to_month"]);
                        $form_group.find("input[name=date_to_day]").val(v2["date_to_day"]);
                        $form_group.find("div.date-toggle").children("span:last").click();
                    }
                    return;
                }

                if(v2.type==="9"){
                    $form_group=$form_groups.filter("[data-id="+v2.id+"][data-type=9]");
                    $inputs=$form_group.find("input");
                    console.log($inputs);
                    $inputs.each(function () {
                        var $input=$(this),
                            name=$input.attr("name"),
                            arr,
                            $check_boxes;
                        if(!v2[name]){
                            return;
                        }

                        arr=v2[name].split(",");
                        $check_boxes=$input.siblings("ul").find("div.checkbox");
                        $input.val(v2[name]);
                        $.each(arr,function (i,v) {
                            arr[i]=v.split("(");
                        });
                        $check_boxes.each(function () {
                           var $checkbox=$(this),
                               checkbox_val=$checkbox.text().trim();
                           $.each(arr,function (i) {
                               if(arr[i][0]===checkbox_val){
                                   $checkbox.siblings("input").val(arr[i][1].slice(0,-1));
                                   $checkbox.addClass("checked");
                                   $checkbox.siblings("input").removeClass("d-n");
                               }
                           });
                        });
                    });
                    return;
                }


            });
        });
    }
})(jQuery);