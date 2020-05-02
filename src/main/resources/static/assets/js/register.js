
$(function () {
    $('#register').click(function () {
        var username = $('input[name="username"]').val();
        var password = $('input[name="password"]').val();
        var nickname = $('input[name="nickname"]').val();
        var sex = $('input[name="sex"]:checked').val();
        var age = $('input[name="age"]').val();
        if (!username) {
            alert("用户名必填");
            return
        }
        if (!password) {
            alert("密码必填");
            return
        }
        if (!nickname) {
            alert("昵称必填");
            return
        }
        if (!age) {
            alert("年龄必填");
            return
        }
        var param = {
            username: username,
            password: password,
            nickname: nickname,
            sex: sex,
            age: age
        }

        debugger;
        $.post("/user/register", param, function (res) {
            if(res.code === 0){
                alert('注册成功');
                window.location.href = "/login.html";
            }else {
                alert(res.msg);
            }
        });

    });
});
