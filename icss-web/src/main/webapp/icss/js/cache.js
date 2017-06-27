/**
 * 将推理结果存入缓存
 */

function flushDataIntoCache(params){
    if(params.type===Param.firstType){
        ajaxPostAsync(Param.hostUrl + "/reason/flush_reason_into_cache",params);
    }
}

/**
 * 清空缓存:当前页面为空白时
 */
function cleanCache(type){
	if($("div.group").find("div.choose").children("div.content").children().length === 0){
		// console.log("开始清除缓存");
        Param.firstType=type;
		ajaxPostAsync(Param.hostUrl + "/reason/clean_reason_cache",{"patientId":Param.patientId});
	}
}

/**
 * 清除由当前删除的项目推理出来的数据
 */
function cleanCacheByType($group){
	var index,$ps,params,
        inStandardIds = [];
    index = $group.index();
    if(index===Param.firstType){
        $ps=$group.find("div.choose").children("div.content").children();
        $ps.each(function () {
            inStandardIds.push($(this).attr("data-standard-id"));
        });
        params = {
            patientId:Param.patientId,
            type:index,
            inStandardIds:inStandardIds.join()
        };
        ajaxPostAsync(Param.hostUrl + "/reason/clean_reason_cache_by_type",params);
    }

}
