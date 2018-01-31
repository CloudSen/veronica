$(() => {
    let date = new Date();
    $('body').append("test3:" + date.toLocaleString()
        + ":" + date.getMilliseconds() + "</br>");
});