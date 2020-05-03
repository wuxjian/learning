$(function () {
    $('#closeAnswer').click(function () {
        var flag = true;
        var answers = [];
        $('input[name="answer"]').each(function () {
            var a =$.trim($(this).val());
            answers.push(a);
            if (!a) {
                flag = false;
            }
        });
        if (!flag) {
            alert('有习题未答');
            return;
        }


        var param = {
            yourAnswer: answers.join(','),
            examId: $('input[name="examId"]').val()
        };
        debugger;
        $.post('/examination/answer', param, function (res) {
            if (res.code === 0){
                alert('本次成绩是:' + res.data);
                location.hash = 'examination/answer/list';
            }
        });
    });
});
