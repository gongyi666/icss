## icss项目重构说明

### 主要技术栈：
- 1. jquery-1.9
- 2. jquery-tmpl 用于模板的搭建渲染(可以完全兼容ie8)
- 3. require.js 用于模块化管理
- 4. gulp 用于自动化构建，主要是用于 浏览器热加载和文件的转换和压缩合并等

### 兼容情况
- 1. 采取渐进增强原则，可以使用css3中的属性方法。ie8中可不考虑 “圆角” 、“ 阴影” 等特殊样式效果。

###  gulp使用方法
- 1. 安装node
- 2. 使用npm(node自带)全局安装 gulp. ---> 使用命令 'npm install -g gulp'
- 3. 在当前文件的根目录下（存在package.json的文件夹下） 使用 'npm install'，安装 package.json中的依赖。
- 4. 安装成功后，在cmd下 输入 'gulp watch'，启动gulp来执行文件的自动编译与浏览器的热加载等功能。

