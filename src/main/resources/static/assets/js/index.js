var app = {
    global: {

    },
    init: function () {
        this.initBinding();
        this.firstLoad();
    },
    initBinding: function () {
        window.onhashchange=function(event){
            var newURL = event.newURL;
            var hash = newURL.substring(newURL.lastIndexOf("#") + 1);
            var url = '/' + hash + '.html';
            $('#main').load(url);
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
    //第一次加载
    firstLoad: function () {
        var hash  = location.hash;
        if (hash){
            var url = '/' + hash.slice(1) + '.html';
            $('#main').load(url);
        }else {
            $('#main').load('/user/profile.html');
        }

    }

}

app.init()

