// 转换时间戳的方法
function time(time, type) {
	var myDate = new Date();
	var Y1 = myDate.getFullYear(); //获取完整的年份(4位,1970-????)
	var date = new Date(time);
	Y = date.getFullYear() + '-';
	M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
	D = (date.getDate() < 10 ? '0' : '') + date.getDate() + ' ';
	h = date.getHours() + ':';
	m = date.getMinutes() + ':';
	s = date.getSeconds();
	if(type == 1) {
		timer = Y + M + D;
	} else if(type == 2) {
		timer = Y + M + D + h + m + s;
	} else {
		if(Y1 == parseInt(Y)) {
			timer = M + D;
		} else {
			timer = Y + M + D;
		}
	}
	timer = $.trim(timer);
	return timer;
}
// 时间戳转换年龄的方法
function birthday(time) {
	var birthDay = new Date(time).getTime();
	var now = new Date().getTime();
	var hours = (now - birthDay) / 1000 / 60 / 60;
	var year = Math.floor(hours / (24 * 30 * 12));
	hours = hours % (24 * 30 * 12);
	var months = Math.floor(hours / (24 * 30));
	hours = hours % (24 * 30);
	var days = Math.floor(hours / (24));
	return year;
}