(function ($) { //n表示的毫秒数
    $.sleep = function (msec) {
        var start = new Date().getTime();
        while (true) if (new Date().getTime() - start > msec) break;
    };

    $.getUrlAnchors = function (prefix) {
        var reg = new RegExp(prefix + "\\d+");
        var r = window.location.hash.match(reg);
        if (r != null) return unescape(r[0]);
        return null;
    };

    $.formParams = function (form) {
        var f = $(form).serializeArray();
        var p = {};
        $.each(f, function (i, n) {
            p[n.name] = n.value;
        });
        return p;
    };
})(jQuery);

(function($){
    $.fn.cacheApply = function(target,callback){
        var p = $(this).attr("id");
        var t = $.get(target);
        $.each(t,function(k,v){
            if($.isArray(v)){
                if(callback && callback[k]){
                    callback[k](v);
                }else{
                    console.warn("Not found the corresponding callback methods.");
                }
            }else{
                $("#"+p+" #"+k).html(v);
            }
        });
    }

    $("#myModal").on('hidden', function () {
        var t = $("#myModal .mlr8 span");
        $.each(t,function(k,v){
            v.innerHTML="";
        });
    });

    var pageCache = {};
    $.cachePut = function (key, row) {
        pageCache[key] = row;
    };

    $.get = function (key) {
        return pageCache[key];
    };
})(jQuery);
