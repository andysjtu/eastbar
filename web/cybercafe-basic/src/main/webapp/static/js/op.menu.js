// 在Chrome中，使用$(document).ready()仍然无法获取到匹配的元素
// 必须使用$(window).load()来获取
//$(window).load(function(){
//    var $target = $(":target");
//}

var prefix = "_";
$(function () {
    var t = $.getUrlAnchors(prefix);
    if(t){
        var xm = $("#"+t);
        var ym = $("div ."+t);
        if(xm.length && ym.length){
            $("a[id^="+prefix+"]").attr({'class': "tit_btn02"});
            xm.attr({'class':"tit_btn"});

            $("div[class^="+prefix+"]").removeClass("current");
            ym.addClass("current");
        }else if(xm.length){
            $("a[id^="+prefix+"]").attr({'class': "tit_btn02"});
            xm.attr({'class':"tit_btn"});

            $("div[class^="+prefix+"]").removeClass("current");
        }
    }
});

