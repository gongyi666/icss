(function($){
  $.fn.blueTip = function(msg){
    var el=this;

    var options={//fillnumber:在获取浮动元素位置时会有差额，这个数值用来补
        message:msg || "默认的tips的内容",
        putType:"TOP",
        fillnumber:0
    };

    if(typeof($(el).attr("data-symptom-name"))!="undefined" && $(el).attr("data-symptom-name").length>0){
        options.message = $(el).attr("data-symptom-name");
    }
    if(typeof($(el).attr("tip-type"))!="undefined" && $(el).attr("tip-type").length>0){
        options.putType = $(el).attr("tip-type");
    }
    $(el).on("mouseover",function(){
		if (true) {
			$(this).after('<div class="blue-tip" style="border-radius: 5px;max-width: 200px;min-width:80px;'
			+'background-color: #52bcec;color:#fff;padding:10px;text-align: center;position: absolute; z-index:500;"></div>');
			$(".blue-tip").text(options.message);
			if (options.putType=="RIGHT") {
				var x = $(this).position().left+options.fillnumber;
				x=x+parseInt($(this).css("margin-left"))+parseInt($(this).css("width"))+8;
				var y = $(this).position().top;
				y=y+parseInt($(this).css("margin-top"))+parseInt($(this).css("height"))/2-parseInt($(".blue-tip").css("height"))/2;
				$(".blue-tip").css({"top":y,"left":x});
			}else if (options.putType=="TOP") {
				var x = $(this).position().left;
				x=x+parseInt($(this).css("margin-left"))+parseInt($(this).css("width"))/2-parseInt($(".blue-tip").css("width"))/2;
				if(this.nodeName==="DIV"){
					x-=10;
				}
				var y = $(this).position().top-options.fillnumber;
				y=y+parseInt($(this).css("margin-top"))-parseInt($(".blue-tip").css("height"))-8;
				$(".blue-tip").css({"top":y,"left":x});
			}else if (options.putType=="LEFT") {
				var x = $(this).position().left-options.fillnumber;
				x=x+parseInt($(this).css("margin-left"))-parseInt($(".blue-tip").css("width"))-8;
				var y = $(this).position().top;
				y=y+parseInt($(this).css("margin-top"))+parseInt($(this).css("height"))/2-parseInt($(".blue-tip").css("height"))/2;
				$(".blue-tip").css({"top":y,"left":x});
			}else {
				var x = $(this).position().left;
				x=x+parseInt($(this).css("margin-left"))+parseInt($(this).css("width"))/2-parseInt($(".blue-tip").css("width"))/2;
				var y = $(this).position().top+options.fillnumber;
				y=y+parseInt($(this).css("margin-top"))+parseInt($(this).css("height"))+8;
				$(".blue-tip").css({"top":y,"left":x});
			}
		}
    })

    $(el).on("mouseout",function(){
        $(".blue-tip").remove();
    })
  }
})(jQuery);
