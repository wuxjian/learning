var app = {
    global: {
        currentUrl: '/user/profile.html'
    },
    init: function () {
        this.initBinding();
        this.firstLoad();
    },
    initBinding: function () {
        $('#logout').click(function () {
            $.get('/logout');
            location.href = "/login.html";
        });
        //菜单点击事件
        $('#sidebar-menu .child_menu li').click(function () {
            var clickUrl = $(this).attr('data-url');
            if (clickUrl  === app.global.currentUrl){
                return
            }
            app.global.currentUrl = clickUrl;
            $('#sidebar-menu .child_menu li').removeClass('active');
            $(this).addClass('active');
            $('#main').load(clickUrl);
        });
        $('.first-menu').click();
    },
    //第一次加载
    firstLoad: function () {
        $('#main').load(this.global.currentUrl);
    }

}

app.init()

