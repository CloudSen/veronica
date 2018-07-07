/** 配置js文件路径 **/
require.config({
    baseUrl: "/js/",
    paths: {
        "jquery": ["http://libs.baidu.com/jquery/3.2.1/jquery","jQuery/jquery-3.2.1.min"],
        "test4": "learnrequirejs/test4",
        "test5": "learnrequirejs/test5",
        "test6": "learnrequirejs/test6",
        "h5sock": "learnsockjs/h5sock",
        "sockjs": "SockJS/sockjs.min"
    },
    shim: {
        "sockjs": {
            exports: "h5sock"
        }
    }
});

/** 使用requireJS加载JS文件 **/
// require(["jquery","test4","test5","test6"],() => {
//     console.log("js加载完毕");
// });
require(["jquery","h5sock","sockjs"],function ($,h5sock,sockjs) {
    console.log("js加载完毕");
    h5sock.h5sock();
});