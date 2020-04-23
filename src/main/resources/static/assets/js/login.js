$(function () {
    $('#login').click(function () {
        var username = $('input[name="username"]').val();
        var password = $('input[name="password"]').val();
        if (!username) {
            alert("用户名必填");
            return
        }
        if (!password) {
            alert("密码必填");
            return
        }

        $.post("/login", {username: username, password: password}, function (res) {
            if(res.code === 0){
                window.location.href = "/index.html";
            }else {
                alert(res.msg);
            }
        });

    });
});
