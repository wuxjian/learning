
$(function () {
    var E = window.wangEditor;
    var editor2 = new E('#content');
    editor2.create();

    $('#examCount').on('change', function () {
        generateAnswerArea();
    });


    generateExamNum();

    generateAnswerArea();

    //生成习题数量
    function generateExamNum() {
        var $examCount = $('#examCount');
        for (var i = 0; i <= 45; i++) {
            var $option = '<option value="' + (5 + i) + '">' + (5 + i) + '</option>'
            $examCount.append($option);
        }
    }
    //生成习题答案框
    function generateAnswerArea() {
        $('#answerItems').empty();
        var examCount = $('#examCount').val();
        for (var i = 0; i < examCount; i++) {
            var item = '<span class="item">' +
                '                ' + (i+1) + '<input type="text" name="answer" style="width: 30px">' +
                '            </span>';
            $('#answerItems').append(item);
        }
    }


    $('#submit').click(function () {
        var examName =$('input[name="examName"]').val();
        var examPerScore =$('input[name="examPerScore"]').val();
        if (!examName) {
            alert('请输入习题名称');
            return;
        }
        if (!examPerScore) {
            alert('请输入每题分值');
            return;
        }
        var content = editor2.txt.html();
        debugger;
        if (!content) {
            alert('请输入试题');
            return;
        }
        //校验答案是不是都填写了
        //
        var flag = true;
        var answers = [];
        $('input[name="answer"]').each(function () {
            var item = $.trim($(this).val());
            answers.push(item);
            if (!item) {
                flag = false;
            }
        });
        if (!flag) {
            alert('请填写答案');
            return;
        }

        var param = {
            examName: examName,
            examCount: $('#examCount').val(),
            examPerScore: examPerScore,
            answer: answers.join(","),
            content: content
        };
        $.post('/examination/insert', param, function (res) {
            if (res.code === 0) {
                alert('保存成功');
                location.hash = 'examination/list';
            }
        })
    });
});
