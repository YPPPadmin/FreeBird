$(function () {
    $(".list-item-active").on("click", function () {
        $(this).css("backgroundColor", "#f2f6f5");
        $(this).siblings().css("backgroundColor", "#fff");
    });

    $(".hover").on("mouseover mouseout", function (even) {
        if (event.type == "mouseover") {
            $(this).css("boxShadow", "rgba(0,0,0, 0.1) 0 0 3px 2px");
            $(this).siblings().css("boxShadow", "none");
        } else if (event.type == "mouseout") {
            $(this).css("boxShadow", "none");
        }
    });

    /* 定制代码 */

    $("#inbox-showEmails").on("click", function () {
        $("#inbox-emailList").show();
        $("#inbox-replyList").hide();
        $("#inbox-sysEmailList").hide();
        $("#inbox-emailList").siblings().slideUp(200);
		$(".inbox-msgReplyList").hide();
    });

    $("#inbox-showReplies").on("click", function () {
        $("#inbox-emailList").hide();
        $("#inbox-replyList").show();
        $("#inbox-sysEmailList").hide();
        $("#inbox-replyList").siblings().slideUp(200);
		$(".inbox-msgReplyList").hide();
    });

    $("#inbox-showSysEmails").on("click", function () {
        $("#inbox-emailList").hide();
        $("#inbox-replyList").hide();
        $("#inbox-sysEmailList").show();
        $("#inbox-sysEmailList").siblings().slideUp(200);
		$(".inbox-msgReplyList").hide();
    });

    $(".inbox-msgList>.list>.list-item").on("click", function () {
        $(this).parents(".inbox-msgList").next().slideDown(400);
    });

    $(".inbox-msgDetail .inbox-msgDetail-close").on("click", function () {
        $(this).parents(".inbox-msgDetail").slideUp(200);
		$(".inbox-msgReplyList").hide();
    });
	
	$(".inbox-msgEnd>.open").on("click", function () {
		$(this).parent().parent().next().show();
	});
});

