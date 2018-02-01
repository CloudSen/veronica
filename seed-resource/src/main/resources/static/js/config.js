!function () {
    /** 配置js文件路径 **/
    require.config({
        baseUrl: "/js/",
        paths: {
            "jquery": ["http://libs.baidu.com/jquery/2.0.3/jquery","jQuery/jquery-3.2.1.min"],
            "test4": "test/test4",
            "test5": "test/test5",
            "test6": "test/test6"
        }
    });

    /** 使用requireJS加载JS文件 **/
    require(["jquery","test4","test5","test6"],() => {
        console.log("js加载完毕");
    });
}();