$(document).ready(function () {
    $(".production-detail .thumbs .img").on("click", function (e) {
        $(this).closest(".images").find(".big .img").css("background-image", "url('" + $(this).data("img") + "')");
        e.preventDefault();
        return false;
    })
});