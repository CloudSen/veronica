$(() => {
    let date = new Date();
    $('body').append("test1:" + date.toLocaleString()
        + ":" + date.getMilliseconds() + "</br>");
});