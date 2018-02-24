define(["jquery"],function ($) {
    var h5sock = function ($) {
        var websocket = establishConnection();
        window.onbeforeunload = function (ev) {
            closeWebSocket();
        };

        function closeWebSocket() {
            if (websocket !== null) {
                websocket.close();
            } else {
                alert("连接为空");
            }
        }

        function sendMessage() {
            if (websocket.readyState === 3) {
                alert("您已断线，请重新连接。");
            } else {
                websocket.send($("#text").val());
            }
        }

        function reconnection() {
            websocket = establishConnection();
        }

        function establishConnection() {
            var websocket = null;
            var $content = $('#content');
            $content.empty();
            //判断当前浏览器是否支持WebSocket
            if ('WebSocket' in window) {
                websocket = new WebSocket("ws://localhost:7001/websocket");
            } else {
                var $p = $("<p>当前浏览器不支持 websocket</p>");
                $p.appendTo($content);
            }
            websocket.onopen = function (ev) {
                var $p = $("<p>socket连接建立</p>");
                $p.appendTo($content);
            };
            websocket.onclose = function (ev) {
                var $p = $("<p>socket连接关闭</p>");
                $p.appendTo($content);
            };
            websocket.onmessage = function (ev) {
                var $p = $("<p>" + ev.data + "</p>");
                $p.appendTo($content);
            };
            websocket.onerror = function (ev) {
                var $p = $("<p>websocket发生错误</p>");
                $p.appendTo($content);
            };
            return websocket;
        }
    };
    return {
        h5sock: h5sock
    }
});