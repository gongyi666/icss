/**
 * Created by Kiva on 17/3/29.
 */
var Param = {
	"diseaseBase": {}, //点击诊断栏获取到的有诊断依据疾病信息存在这里，用于获取诊断依据
	"id": "",

	"hisCode": "",
	"patientId": "-1",
	"age": "",
	"sexType": "3",
	"patientName": "",
	"cardId": "",
	"marry": "",
	"nation": "",
	"occupation": "",
	"unit": "",
	"phone": "",
	"insurance": "",
	"insuranceId": "",

    "hospitalCode" : "A001",
    "hospitalId" : "A001",

	"doctorId": "1",//* 模拟his传值，因类型不一致，需要本地数据库转换
	"doctorCode": "1",
	"doctorName": "",
	"outpatientId": "",
	"treatmentTime": "",
	"type": "初诊",

	"dept": "",	
	"deptNo": "12", //* 模拟his传值，默认对应全科
	"deptCode": "12",//* 模拟his传值，默认对应全科
	"serial": "123456",//* 模式his传值，对应就诊编号

	"dataJson": "",






    "hostUrl":"/icss-web",

    // "hostUrl":"http://192.168.2.165:8080/icss-web",
    // "hostUrl":"http://192.168.2.224:8080/icss-web",
    // "hostUrl":"http://192.168.2.194:8080/icss-web",//
    // "hostUrl":"http://192.168.3.131:8080/icss-web",//吴文阳
	// "hostUrl":"http://192.168.3.6:8081/icss-web",//章群燕
	// "hostUrl":"http://192.168.3.22:8080/icss-web",//
	"computationalFormula": {
		//眼球突度
		"yq04":{
			"formula":['key','-','key']
		},
		//胸围
		"xb0102":{
			"sex":{
				"sex=0":['key',]
			}
		}
	},

	/*
	    "typeNum" : {//用于吴文洋那边的推理的typeid,与之前写在js里的数值上可能一样，但不同用处
	        "symptom" : 1,
	        "past" : 2,
	        "other" : 3,
	        "vital" : 4,
	        "lis" : 5,
	        "pacs" : 6
	    }，
	*/
	"get_lis_usual": "/kl/lis/get_usual", //lis常用列表
	"get_lis_by_doctorId": "/kl/lis/get_lis_by_doctorId",
	/*lis列表内容*/

	/*"lis_type_get_more" : "/kl/listype/get_more",lie获取更多*/

	"get_transverse_portrait_lis": "/kl/transverse/get_transverse_portrait_lis",

	"get_lis_by_input": "/kl/lis/get_lis_by_input",
	/*lis检索*/


	"get_lis": "/kl/lis/get_lis",
	/*lis获取his套餐*/

	"lis_get_by_infoid": "/kl/lis_detail/get_by_infoid",
	/*lis获取明细/已检*/

	"get_pacs_usual": "/kl/pacs/get_usual", //pacs常用列表
	"init_pacsinfo": "/kl/pacs/init_pacsinfo",
	/*pacs列表内容*/

	/*"get_part_by_parentId" : "/kl/pacs/get_part_by_parentId",部位获取明细部位*/

	/*"get_pacs_by_category" : "/kl/pacs/get_pacs_by_category",pacs更多*/

	"get_transverse_portrait_pacs": "/kl/transverse/get_transverse_portrait_pacs",
	/*pacs更多*/

	/*"get_apparatus_by_partCode" : "/kl/pacs/get_apparatus_by_partCode",根据部位获取手段*/

	/*"get_apparatus_by_partId" : "/kl/pacs/get_apparatus_by_partId",根据部位获取手段*/

	"pacs_get_by_infoId": "/kl/pacs/get_by_infoId",
	/*根据部位获取手段和详细部位,现在改成根据数据id*/

	"get_his_pacs": "/kl/pacs/get_his_pacs",
	/*pacs获取his套餐*/

	"pacs_search_pacs": "/kl/pacs/search_pacs",
	/*pacs检索*/

	"get_diseaseinfo_usual": "/kl/diseaseinfo/get_usual", //诊断常用列表
	"search_his_diseases": "/kl/diseaseinfo/get_disease_by_input", //诊断搜索

	"get_disease_push": "/kl/diseaseinfo/get_disease_push", //诊断推送

	/*"get_disease_type" : "/kl/diseaseinfo/get_disease_type",更多里获取疾病系统栏目*/

	/*"get_disease_by_type" : "/kl/diseaseinfo/get_disease_by_type",根据疾病系统id获取疾病*/

	"get_transverse_portrait_disease": "/kl/transverse/get_transverse_portrait_disease",
	/*疾病更多*/

	"get_by_diseaseid": "/kl/diagnose/get_by_diseaseid",

	"get_mapping_disease": "/his/his_diseaseinfo/get_mapping_disease", //诊断获取his疾病

	"get_by_itemidAndType": "/kl/introduce/get_by_itemidAndType", //名词解释

	/*"inquiry_info_index" : "/at/inquiry_info/index",//在所有项目为空时调用的诊断借口*/

	"save_insert": "/at/inquiry_info/insert", //保存
	// "save_insert" : "/at/inquiry_info/insert_his_and_local",//保存

	"rule_controller_start": "/rule_controller/start_reasoning", //在不为空的情况下，获取疾病并且获取诊断依据

	"pacs_get_result": "/kl/pacs_result/get_result" ,//pacs获取已检,
	"start_lis": "/rule_controller/start_lis",
	"start_pacs": "/rule_controller/start_pacs"
};

var storage = window.localStorage;