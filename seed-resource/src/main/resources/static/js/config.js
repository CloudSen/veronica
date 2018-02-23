!function () {
    /** 配置js文件路径 **/
    require.config({
        baseUrl: "/js/",
        paths: {
            "jquery": ["http://libs.baidu.com/jquery/2.0.3/jquery","jQuery/jquery-3.2.1.min"],
            "test4": "learnrequirejs/test4",
            "test5": "learnrequirejs/test5",
            "test6": "learnrequirejs/test6",
            "h5sock": "learnsockjs/h5sock",
            "sockjs": "SockJS/sockjs.min"
        },
        shim: {
            "sockjs": {
                exports: "SockJS"
            }
        }
    });

    /** 使用requireJS加载JS文件 **/
    /*require(["jquery","test4","test5","test6"],() => {
        console.log("js加载完毕");
    });*/
    require(["h5sock","sockjs"],function () {
        console.log("js加载完毕");
    });
}();