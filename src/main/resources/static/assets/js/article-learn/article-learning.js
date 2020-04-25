$(function () {
    $('#closeLearn').click(function () {
        var param = {
            startTime: $('input[name="startTime"]').val(),
            articleId: $('input[name="articleId"]').val()
        };
        $.post('/article/learn/insert', param, function (res) {
            if (res.code === 0){
                alert('请再次学习,加油~');
                location.hash = 'article/learn/list';
            }
        });
    });
});
