var gulp = require("gulp");
// var uglify = require("gulp-uglify");//js压缩
var concat = require("gulp-concat");//合并
var cssnano = require("gulp-cssnano");//css压缩
// var htmlmin = require("gulp-htmlmin");//html压缩
var browserSync = require("browser-sync");//浏览器同步
var less = require('gulp-less');//less转换

//js压缩
// gulp.task("js", function() {
// 	gulp.src(["./js/app.js", "./js/main.js"])
// 		.pipe(concat("index.js"))
// 		.pipe(uglify())
// 		.pipe(gulp.dest("./dest"))
// });

//css 压缩合并处理
gulp.task("css", function() {

	gulp.src(["./css/icss_date.css","./css/icss_form_common.css", 
				"./css/icss_more.css", "./css/icss_no_special.css", "./css/icss_popup.css",
				"./css/icss_print.css", "./css/icss_push_list.css","./css/icss_symptom_select.css"])
		.pipe(concat("./main.css"))
		// .pipe(cssnano())
		.pipe(gulp.dest("./css"))
});
//html压缩
// gulp.task("html", function() {
// 	gulp.src("./index.html")
// 		.pipe(htmlmin({
// 			collapseWhitespace: true
// 		}))
// 		.pipe(gulp.dest("./dest"))
// });
// 
//less编译为css
gulp.task("less", function() {
	gulp.src("./less/*.less")
		.pipe(less())
		.pipe(gulp.dest("./css"))
});

// 开启静态服务器
gulp.task('browser-sync', function() {
    browserSync.init({
        server: {
            baseDir: "./"
        },
        files:["*.html"]
    });
});

gulp.task("watch", function() {
	// 初始化browser-sync
	  browserSync.init({
	    server:'./', // 指定一个网站的根目录
	    files:["*.html","./js/**.js","./less/*.less","./js/modules/*.js","./js/components/*.js"]
	  });
	gulp.watch(["*.html","./js/**.js","./less/*.less","./js/modules/*.js","./js/components/*.js"],["less","css"]);
});


