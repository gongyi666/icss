/*
    1. require.config执行baseUrl为'js'，
    2. baseUrl指的模块文件的根目录，可以是绝对路径或相对路径
*/
require.config({
    baseUrl: 'js',
    paths: {
        'jquery': '../lib/jquery-1.9.1.min',
        'template': '../lib/template-web',
        'jquery.blueTip': '../lib/blueTip',
        'jquery.tmpl': '../lib/jquery.tmpl'
    },
    // 配置依赖 
    shim: {
        'jquery.blueTip': ['jquery'], //配置 blueTip依赖于jq
        'jquery.tmpl': ['jquery'] //配置 blueTip依赖于jq
    }
});

/*
    1. 通过require，来引入其它模块（ *在引入时不用写后缀 ‘ .js ’），
    2. 通过后面的匿名函数给他们分配参数
*/
require(['jquery', 
        './components/icss_more', 
        './components/icss_form', 
        './components/icss_symptom_select',
        './components/icss_no_special',
        './components/icss_popup',
        './components/icss_print',
        './components/icss_date',
        './components/icss_common',
        './modules/common'],
        function(
            $, icss_more, icss_form, icss_symptom_select,
            icss_no_special, icss_popup, icss_print, icss_push_list,
            common) {   
	//下面就可以使用相应的对象里的方法，参数等
});
