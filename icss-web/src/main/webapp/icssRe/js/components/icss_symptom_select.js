//症状选择模块
/*
 * define的参数为匿名函数，该匿名函数返回一个对象
 */
define(["jquery", "jquery.tmpl"], function($, tmpl) {

    //template 使用示例
    // var data = {};
    // var tmpl = template('sample_selected', data);
    // $('#exapmle_content').html(tmpl);

    var selectedTmpl = {
        // 获取到 对应id下的模板
        "getTmpl": function(id, data) {
            return $("#" + id).tmpl(data);
        },
        // 通过type 来设置 需要的 模板
        "setTmplType": function(types, data) {
            if (typeof types !== "number") {
                console.log("types 必须是数值");
                return false;
            }
            // 主要用于主诉， 现病史, 体征， 诊断， 治疗
            if (types === 0 || types === 1 || types === 4 || types === 7 || types === 8) {
                return {
                    singleItem: this.singleItem(data)
                }
                // 主要用于 化验和器查的已检项目
            } else if (types === 5 || types === 6) {
                return {
                    singleItem: this.singleItem(data),
                    checkedItem: this.checkedItem(data)
                }
                // 主要用于 既往史，其他史
            } else {
                return {
                    mutliItem: this.mutliItem(data)
                }
            }
        },
        // 主要用于主诉， 现病史, 体征， 化验中的非已检 ，器查中的非已检， 诊断， 治疗
        "singleItem": function(data) {
            return this.getTmpl("mutli_selected", data);
        },
        // 主要用于 化验和器查的已检项目
        "checkedItem": function(data) {
            return this.getTmpl("mutli_selected", data);
        },
        // 主要用于 既往史，其他史
        "mutliItem": function(data) {
            return this.getTmpl("mutli_selected", data);
        }
    }
    /**
     * 选择症状的构造函数
     * @param {*参数：object 类型，当前组别，数据，是否显示上移图标} obj 
     */ 
    var SelectedSymptom = function(obj) {
        this.index = obj.index; //表示 是第几个模块
        this.allGroup = $(".group");
        this.moveTop = obj.moveTop; // 是否 显示 上移图标 
        this.checked = obj.checked; // 判别显示 化验和器查中的哪个模板
        this.$group = obj.$group; // 当前的 group
        var tmplObj = selectedTmpl.setTmplType(obj.types, obj.data);
        if (typeof tmplObj.singleItem === "object") {
            this.singleItem = tmplObj.singleItem;
        }
        if (typeof tmplObj.checkedItem === "object") {
            this.checkedItem = tmplObj.checkedItem;
        }
        if (typeof tmplObj.mutliItem === "object") {
            this.mutliItem = tmplObj.mutliItem;
        }
    };
    // 增加 
    SelectedSymptom.prototype.add = function() {
         var html = "";
        if(this.checked){
            html = this.checkedItem;
        }else {
            html = this.singleItem || this.mutliItem;
        }
        
        this.$group.find(".group__wrapper").prepend(html);
    }
    // 删除
    SelectedSymptom.prototype.remove = function() {
        // 添加点击删除当前项目事件
        var _t = this;
        this.$group.on("click",".icon-remove", function() {
            var $self = $(this);
            if(_t.index === 0){//说明是主诉，主诉由于有 totop 需求所以这里不能将.wrapper__choose 删除掉，否则后续 添加的条目有问题
                $self.parent("div.symptom-item").remove();
            }else {
                // 为了实现在 既往史 和 现病史 中的全部删除列表后，将标题也要删除掉
                if($self.parents(".wrapper__choose__content").children(".symptom-item").length === 1){
                    $self.parents(".wrapper__choose").remove();
                }
                $self.parent("div.symptom-item").remove();
            }
            //更新 列表数组，下一次 hover时， 才不会导致 其他问题
            _t.hasExist();
        })
    }
    // 判别 主诉是否 存在 将要 移动的 item。
    SelectedSymptom.prototype.hasExist = function() {
        // 添加点击删除当前项目事件
        var existArr = [];
        var id = this.$group.find(".symptom-item").attr("data-relation-id");
        this.allGroup.eq(0).find(".wrapper__choose__content").children().each(function(i,v) {
            var id = $(v).attr("data-relation-id");
            if(id) {
                existArr.push(id);
            }
        });
        return existArr;
    }
    // 点击 moveTop时， 移动 现病史的 条目 到 主诉中 
    SelectedSymptom.prototype.toTop = function() {
        // 添加点击当前项目将现病史添加到主诉事件 
        var _t = this;
        
        this.$group.find(".icon-move-top").on("click", function() {
            var $group0 = $(".group").eq(0);
            var html = $(this).parent(); 
            $group0.find("div.wrapper__choose__content").prepend(html);
            $group0.find(".symptom-item i").hide();
            return;
        })
    }

    // 点击展示form
    SelectedSymptom.prototype.showForm = function($el) {
        $el.slideDown();
    }

    // 添加 自定义 内容 
    SelectedSymptom.prototype.addAttr = function(attrObj) {
        for(var k in attrObj) {
            if(Object.prototype.hasOwnProperty.call(attrObj, k)) {
                this.$group.find(".symptom-item").attr(k, attrObj[k])
            }
        }
    }
    // 鼠标悬浮显示 图标 
    var hoverIcon = {
        showMoveTop: function(_t) {
            _t.$group.find(".wrapper__choose").each(function(i, v) {
        		// 当 主诉存在 将要移动的元素时，就将图标隐藏
        		$(v).on("mouseover mouseout", ".symptom-item",function(event){
                    var self = this;
                    var isReturn = false;
                    var tempArr = _t.hasExist();
                    $.each(tempArr, function(inp, val) {
                        if($(self).attr("data-relation-id") == val) {
                            $(v).find("i").hide();
                            isReturn = true;
                            return false;
                        }
                    });
                    if(isReturn) {
                        return;
                    }
	                event.stopPropagation();//阻止冒泡 
                    var target = event.target.nodeName;
	                var hoverEl =  target === "DIV" || target === "I" || target === "SPAN" ;
	                if( hoverEl && event.type == "mouseover"){
	                    //鼠标悬浮
	                    $(this).find("i").show();
	                }else if(hoverEl && event.type == "mouseout"){
	                    //鼠标离开
	                    $(this).find("i").hide();
	                }
	            })
        	});
        },
        showDel: function(_t) {
            _t.$group.find(".wrapper__choose").each(function(i, v) {
        		$(v).on("mouseover mouseout",".symptom-item", function(event){
	                event.stopPropagation();//阻止冒泡 
                    var target = event.target.nodeName;
	                var hoverEl =  target === "DIV" || target === "I" || target === "SPAN" || target === "UL" || target === "LI" ;
	                if( hoverEl && event.type == "mouseover"){
	                    //鼠标悬浮
	                    $(this).find("i:last").show();
	                }else if(hoverEl && event.type == "mouseout"){
	                    //鼠标离开
	                    $(this).find("i:last").hide();
	                }
	            })
        	});
        }
    }
    // hover 显示 删除 和 上移图标
    SelectedSymptom.prototype.hover = function($el, moveTop) {
        $el.find("i").hide();//首先隐藏 图标
        var _t = this;
        if(moveTop){ //当存在 moveTop的时候显示 全部图标
            // 悬浮 显示全部 icon
            hoverIcon.showMoveTop(_t);
        }else {//否则只显示 remove图标
        	// 悬浮 只显示删除 icon
            hoverIcon.showDel(_t);
        }
    }

    // 选择症状的初始化函数
    SelectedSymptom.prototype.init = function() {
        this.add();
        this.remove();
        this.hover(this.$group.find(".wrapper__choose div.symptom-item"), this.moveTop);
        // this.showForm();
        this.toTop();
    }





    //////////////////////////////---------- 方法使用示例-------- /////////////////////////////////////

    var data = [{
        "isHistory": false,
        "hasChecked": false,
        "title": "测试标题",
        "text":[ "测试文本","测试文本2"],
        "attr":{
            "data-relation-id":1
        }
    }];
    
    var se0 = new SelectedSymptom({ types: 0, $group: $(".group").eq(0), data: data,index:0})
    se0.init();
    var se1 = new SelectedSymptom({ types: 1, $group: $(".group").eq(1), data: data, moveTop: true})
    se1.init();
    var data2 = [{
        "isHistory": true, // 是否显示 标题
        "title": "测试标题20",
        "text":[ "测试文本20","测试文本","测试文本2"],
        "attr":{
            "data-relation-id":20
        }
    }];
    var data21 = [{
        "isHistory": true,
        "title": "测试标题21",
        "text":[ "测试文本21"],
        "attr":{
            "data-relation-id":21
        }
    }];
    var se2 = new SelectedSymptom({ types: 2, $group: $(".group").eq(2), data: data2})
    se2.init();
     var se21 = new SelectedSymptom({ types: 2, $group: $(".group").eq(2), data: data21})
    se21.init();

    
    var se3 = new SelectedSymptom({ types: 3, $group: $(".group").eq(3), data: data})
    se3.init();

    var se4 = new SelectedSymptom({ types: 4, $group: $(".group").eq(4), data: data})
    se4.init();
    var data5 = [
        {
            "isHistory": false,
            "hasChecked": true, //判断是否是 化验或者器查 已选项
            "title": "测试标题",
            "name": "测试名称",
            "text":[{ txt:"测试文本1", vol:"1",unit:"单位1"},{ txt:"测试文本2", vol:"2",unit:"单位2"}],
            "attr": {
                    "data-relation-id":1
            },
        },
        {
            "isHistory": false,
            "hasChecked": false, //判断是否是 化验或者器查 已选项
            "title": "测试标题21",
            "text":[ "测试文本21"],
            "attr":{
                "data-relation-id":21
            }
        }
    ];
    var se5 = new SelectedSymptom({ types: 5, $group: $(".group").eq(5), data: data5, checked: true})
    se5.init();
    var se6 = new SelectedSymptom({ types: 6, $group: $(".group").eq(6), data: data})
    se6.init();
    var se7 = new SelectedSymptom({ types: 7, $group: $(".group").eq(7), data: data})
    se7.init();
    var se8 = new SelectedSymptom({ types: 8, $group: $(".group").eq(8), data: data})
    se8.init();
    ////////////////////////////////////////-------------////////////////////////////

    //在此处返回方法属性即可
    return {
        SelectedSymptom: SelectedSymptom
    };
});