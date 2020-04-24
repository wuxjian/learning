var app = {
    global: {

    },
    init: function () {
        this.initBinding();
        this.loadUrl();
    },
    initBinding: function () {
        window.onhashchange=function(event){
            app.loadUrl();
        };
        $('#logout').click(function () {
            $.get('/logout');
            location.href = "/login.html";
        });
        //菜单点击事件
        $('#sidebar-menu .child_menu li').click(function () {
            $('#sidebar-menu .child_menu li').removeClass('active');
            $(this).addClass('active');
        });

    },
    loadUrl: function () {
        var hash  = location.hash;
        if (hash){
            hash = hash.slice(1);
            var queryIndex = hash.indexOf("?")
            if (queryIndex === - 1){
                var url = '/' + hash + '.html';
                $('#main').load(url);
            }else {
                var url = '/' + hash.substring(0, queryIndex) + '.html' + hash.substring(queryIndex);
                $('#main').load(url);
            }

        }else {
            $('#main').load('/user/profile.html');
        }

    }

}

app.init()

