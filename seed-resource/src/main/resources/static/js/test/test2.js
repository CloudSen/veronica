$(() => {
    let date = new Date();
    $('body').append("test2:" + date.toLocaleString()
        + ":" + date.getMilliseconds() + "</br>");
});