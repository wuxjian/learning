
$(function () {
    var E = window.wangEditor;
    var editor2 = new E('#content');
    editor2.create();

    $('#submitArticle').click(function () {
        var title =$('input[name="title"]').val();
        var subject =$('select[name="subject"]').val();
        if (!title) {
            alert('请输入标题');
            return;
        }
        if (!subject) {
            alert('请选择主题');
            return;
        }
        var content = editor2.txt.html();
        if (!content) {
            alert('请输入内容');
            return;
        }
        var param = {
            title: title,
            subject: subject,
            content: content
        }
        $.post('/article/insert', param, function (res) {
            if (res.code === 0) {
                alert('保存成功');
                location.hash = 'article/list';
            }
        })
    });
});
