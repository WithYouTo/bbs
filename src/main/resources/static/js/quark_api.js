/*链接*/
var basePath = "http://127.0.0.1:8061";
basePath = basePath + "/rest";
var quark_login_api = basePath + "/user/login";
var quark_getUser_api = basePath + "/user/message/";
var quark_logout_api = basePath + "/user/logout";
var quark_register_api = basePath + "/user";
var quark_upload_api = basePath + "/upload/image";
var quark_upload_icon_api = basePath + "/upload/usericon/";
var quark_posts_add_api = basePath + "/posts";
var quark_posts_get_api = basePath + "/posts";
var quark_posts_detail_api = basePath + "/posts/detail/";
var quark_reply_add_api = basePath + "/reply";
var quark_label_getall_api = basePath + "/label";
var quark_rank_posts_api = basePath + "/rank/topPosts";
var quark_rank_users_api = basePath + "/rank/newUsers";
var quark_user_detail_api = basePath + "/user/detail/";
var quark_user_update_api = basePath + "/user/";
var quark_user_update_psd_api = basePath + "/user/password/";
var quark_label_posts_api = basePath + "/posts/label/";
var quark_webSocket_api = basePath + "/quarkServer";
var quark_notification_api = basePath + "/notification/";
var quark_chat_webSocket_api = "ws://127.0.0.1:8083/websocket";

function setCookie(data) {
    var expiresDate = new Date();
    expiresDate.setTime(expiresDate.getTime() + (60 * 60 * 1000));
    $.cookie("QUARK_TOKEN", data, {
        path: '/',
        expires: 1
    });
}

function getCookie() {
    return $.cookie('QUARK_TOKEN');
}

function deleteCookie() {
    $.cookie("QUARK_TOKEN", null, {
        path: '/'
    });
}

(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
})(jQuery);

function applyHeader() {
    var htm;
    $.get(quark_getUser_api + getCookie(), function (data) {
        if (data.status == 200) {
            htm = "<a class='upload' href='/user/setting'>" +
                "<img src='" + data.data.icon + "'>" +
                "<cite>" + data.data.username + "</cite>" +
                "</a>" +
                "<div class='nav'>" +
                "<a href='/user/setting'><i class='iconfont icon-shezhi'></i>设置</a>" +
                "<a href='' onclick='logout()'><i class='iconfont icon-tuichu' style='top: 0; font-size: 22px;'></i>退了</a>" +
                "</div>";
        } else {
            htm = "<a class='unlogin' href='#'><i class='iconfont icon-touxiang'></i></a>" +
                "<span><a href='/user/login'>登入</a><a href='/user/register'>注册</a></span>";
        }

        $(".nav-user").append(htm);
    });
}

function logout() {

    $.post(quark_logout_api, {
        token: getCookie()
    }, function (obj) {
        if (obj.status == 200) {
            deleteCookie();
            location.href = "/index";
        } else {
            layer.msg(obj.error, {icon: 5});
        }
    });

}