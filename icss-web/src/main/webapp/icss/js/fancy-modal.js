/**
 * Created by Kiva on 17/3/2.
 */

(function ($){
    $.fn.extend({
        fancyModal:function(typeVal,event) {//一个可选参数，给按钮绑定事件
            var obj={
                "fancy.modal.show":function () {},//模态框显示前触发
                "fancy.modal.shown":function (data) {},//模态框显示后触发
                "fancy.modal.hide":function () {},//模态框隐藏前触发
                "fancy.modal.hidden":function () {},//模态框隐藏后触发
                "fancy.modal.loaded":function (data) {}//数据加载完成触发(这时已经将动态数据插入html页面)
            };
            if((typeof(event)!=="object")){
                throw new Error("请传入一个对象！！！");
            }
            obj=$.extend(obj,event);
            fancyModal(this,typeVal,obj);
            return this;
        }
    });

    //模板类
    var Tmpl=function () {
        this.constructor=Tmpl;
        this.list="";
        this.detail=[];
        this.contentDetail='';
    };
    Tmpl.prototype.listHead='<div class="detail-category clearfix" style="display: none"><div class="second-level" style="width: 119px;"><dl class="method">';
    Tmpl.prototype.listFoot='</dl></div>';
    Tmpl.prototype.contentHead='<div class="level-list" style="display: none;" style="width: 630px; float: left;"><ul class="push-list clearfix">';
    Tmpl.prototype.contentFoot='</ul></div>';
    Tmpl.prototype.detailFoot='</div>';
    Tmpl.prototype.getHtml=function getHtml() {
        var html=this.listHead+this.list+this.listFoot,
            i;
        for(i=0;i<this.detail.length;i++){
            html+=this.detail[i];
        }
        html+=this.detailFoot;
        return html;
    };
    //将详细信息右侧的一页内容加入到数组中
    Tmpl.prototype.pushContent=function pushContent() {
        var html=this.contentHead+this.contentDetail+this.contentFoot;
        this.detail.push(html);
        this.contentDetail='';
    };

    function fancyModal($button,typeVal,obj) {
        var html='',
            id=new Date().getTime();
        //绑定数据加载完成触发的事件
        $button.on("fancy.modal.loaded",function () {
            obj["fancy.modal.loaded"]();
        });

        //id用毫秒数定义，不会冲突，根据button的click事件可以直接引用到此id
        html+='<div id="'+id+'" class="fancy-box"><div class="fancy-mask"></div><div class="det-form"></div><div style="left: 133px; top: 170.5px;" class="fancy-ct"><div class="nav-bar clearfix"><ul class="first-level clearfix">';
        //加载数据

        $.ajax({
            url:"http://192.168.2.165:8090/icss-web/kl/transverse/get_transverse_by_type?type="+typeVal,
            dataType:"json",
            type:"get",
            success:function (data) {
                html+=addTitle(data.data);
                html+='</ul><div class="toolbar"><p class="seach-box"><input type="text"></p><p class="set-up"><img src="img/6.png"> </p><p id="close"><img src="img/7.png"> </p></div></div>';
                $.each(data.data,function (i1,v1) {
                    html+=addDetail(v1);
                });
                $('body').append(html);
                adjustRelation();
                addRelation();
                var $modal= $("#"+id);
                $button.on("click",function () {
                    var $ul=$modal.find("ul.first-level"),
                        $select_li=$ul.find("li.check-state");
                    if($select_li.length===0){
                        $ul.children("li:first").click();
                    }
                    $button.trigger("fancy.modal.show");
                    $("#"+id).css("display","block");
                    $("#"+id).siblings("div.fancy-box").remove();
                    $button.trigger("fancy.modal.shown");
                });
                $button.trigger("fancy.modal.loaded");
                var $list=$button.parents("li.list:first"),
                    liNum=$list.index(),
                    $push_list=$modal.find("ul.push-list");
                $push_list.find("li").removeClass("check-state");
                $list.find("div.choose-list").find("b").each(function () {
                   var $b=$(this),
                       data_id=$b.attr("data-id");
                   $push_list.find('li[data-id='+data_id+']').addClass("check-state");
                });
                //console.log(liNum);
                $("body").data("liNum",liNum);
            }
        });

        //根据数据加载上面的标题的函数
        function addTitle(data) {
            var html="";
            $.each(data,function (i,v) {
                html+='<li data-id="'+v.id+'">'+v.name+'</li>';
            });
            return html;
        }

        //加载详细数据
        function addDetail(data) {
            var tmpl=new Tmpl();
            $.each(data.portraitList,function (i1,v1) {
                if(v1.endLevel==="1"){
                    tmpl.list+='<dt data-id="'+v1.id+'" data-transverseId="'+v1.transverseId+'">'+v1.name;
                    addContentDetail(tmpl,v1.subitemList);
                }else if(v1.endLevel==="0"){
                    tmpl.list+='<dt class="third-level" data-id="'+v1.id+'" data-transverseId="'+v1.transverseId+'">'+v1.name;
                    $.each(v1.portraitList,function (i2,v2) {
                        tmpl.list+='<b data-id="'+v2.id+'" data-transverseId="'+v2.transverseId+'">'+v2.name+'</b>';
                        addContentDetail(tmpl,v2.subitemList);
                    });
                }
                tmpl.list+='</dt>';
            });
            return tmpl.getHtml();
        }

        //加载详细数据右侧内容
        function addContentDetail(tmpl,subitemList) {
            $.each(subitemList,function (i,v) {
                tmpl.contentDetail+='<li data-id="'+v.id+'" data-portraitId="'+v.portraitId+'">'+v.name+'</li>';
            });
            tmpl.pushContent();
        }

        //调整级联关系的id的标识位置，因为数据原因这个级联id在下一层数据中，要标识在外层div上
        function adjustRelation() {
            var $modal=$("#"+id),
                $details=$modal.find('div.detail-category'),
                $lists=$modal.find('div.level-list');
            $details.each(function () {
                var $detail=$(this),
                    $dts=$detail.find("dt");
                if(!$detail.attr('data-transverseid')){
                    $detail.attr('data-transverseid',$dts.eq(0).attr("data-transverseid"));
                }
                $dts.removeAttr("data-transverseid");
            });

            $lists.each(function () {
                var $list=$(this),
                    $lis=$list.find("li");
                if(!$list.attr('data-portraitid')){
                    $list.attr('data-portraitid', $lis.eq(0).attr("data-portraitid"));
                }
                $lis.removeAttr('data-portraitid');
            });
        }

        //添加关联事件
        function addRelation() {
            var $modal=$("#"+id),
                $first_level=$modal.find("ul.first-level"),
                $details=$modal.find("div.detail-category");

            //给button绑定各种事件
            $button.on("fancy.modal.show",function () {
                obj["fancy.modal.show"]($modal);
            });
            $button.on("fancy.modal.shown",function () {
                obj["fancy.modal.shown"]($modal);
            });
            $button.on("fancy.modal.hide",function () {
                obj["fancy.modal.hide"]($modal);
            });
            $button.on("fancy.modal.hidden",function () {
                obj["fancy.modal.hidden"]($modal);
            });

            //添加上侧标题的点击事件
            $first_level.find("li").on("click",function () {
                var $li=$(this),
                    data_id=$li.attr("data-id"),
                    $detail=$modal.find('div.detail-category[data-transverseid='+data_id+']'),
                    $dl=$detail.find("dl.method");
                $details.css("display","none");
                $detail.css("display","block");
                $li.addClass("check-state").siblings().removeClass("check-state");
                if($dl.children("dt.check-state").length===0){
                    $dl.children("dt:first").click();
                }
            });
            //添加详细左侧菜单的点击事件
            $modal.on("click","dt,b",function (e) {
                var $dt=$(e.target),
                    $detail=$dt.parents(".detail-category:first"),
                    data_portraitid=$dt.attr("data-id"),
                    $list=$detail.find("div.level-list[data-portraitid="+data_portraitid+"]"),
                    $lists=$detail.find("div.level-list");
                $dt.addClass("check-state").siblings().removeClass("check-state");
                $lists.css("display","none");
                $list.css("display","block");
            });


            //添加右侧疾病选中事件
            $details.find(".level-list li").on("click",function () {
                // $(this).addClass("check-state");
                var $fancy_ct=$(this).parents("div.fancy-ct:first"),
                    ttNews=$fancy_ct.siblings("div.det-form"),
                    id = $(this).attr("data-id"),
                    title = $(this).html(),
                    left=($(window).width()-$fancy_ct.width())/2,
                    top=($(window).height()-$fancy_ct.height())/2;
                dynamicForm(ttNews,title,id,typeVal);
                ttNews.css({left:left,top:top});
            });
            //添加关闭模态框的事件
            $modal.find("#close").on("click",function () {
                $button.trigger("fancy.modal.hide");
                $modal.css("display","none");
                $button.trigger("fancy.modal.hidden");
            });
            //透明遮罩层点击事件
            $modal.children("div.fancy-mask").on("click",function () {
                $button.trigger("fancy.modal.hide");
                $modal.css("display","none");
                $button.trigger("fancy.modal.hidden");
            });
        }
    }
})(jQuery);
