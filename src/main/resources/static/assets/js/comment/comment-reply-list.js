
$(function () {
    var pageSize = 5;
    getList(1);

    var E = window.wangEditor
    var editor1 = new E('#toolbar', '#editor')  // 两个参数也可以传入 elem 对象，class 选择器
    editor1.create()

    var page = 1;
    $('#reply').click(function () {
        debugger;
        var content = editor1.txt.html();
        if (!content) {
            alert('请输入内容');
            return;
        }
        var param = {
            content: content,
            commentId: $('input[name="commentId"]').val()
        };
        $.post('/comment/reply', param, function (res) {
            if (res.code === 0) {
                alert('回复成功');
                getList();
                editor1.txt.clear()
            }
        });

    });

    function getList() {
        var commentId = $('input[name="commentId"]').val();
        $.post('/comment/reply/list', {page: page, size: pageSize, commentId: commentId}, function (res) {
            if (res.code === 0) {
                debugger;
                var $replyList =$('#reply-list');
                $replyList.empty();
                for (var i = 0; i < res.data.list.length; i++) {
                    var item = res.data.list[i];
                    var image = item.admin ? '/assets/images/1.jpeg' : '/assets/images/2.jpeg';
                    var lozhu = item.self ? '/assets/images/louzhu.png' : '/assets/images/user.gif';
                    var $replyItem = '<div class="reply-item">' +
                            '            <div class="left">' +
                            '                <img src="'+ image +'" alt="">' +
                            '                <span class="nickname">' + item.author + '</span>' +
                            '            </div>' +
                            '            <div class="right">' +
                            '                <div class="header">' +
                            '                    <div><img class="author" src="'+ lozhu +'" alt=""> 发表于 ' + item.createTime + '</div>' +
                            '                    <div>'+ item.floor +'</div>' +
                            '                </div>' +
                            '               <div class="comment-contnt">' +
                                                 item.content +
                            '               </div>' +
                            '            </div>' +
                            '        </div>';

                    $replyList.append($replyItem);
                }


                paginator(res.data.totalRecord, page);
            }
        })
    }

    function paginator(totalCounts, currentPage) {
        page = currentPage;
        $('#paginator').jqPaginator({
            totalCounts: totalCounts,
            visiblePages: 3,
            pageSize: pageSize,
            currentPage: currentPage,
            first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
            prev: '<li class="prev"><a href="javascript:void(0);">上一页<\/a><\/li>',
            next: '<li class="next"><a href="javascript:void(0);">下一页<\/a><\/li>',
            last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
            onPageChange: function (num, type) {
                if( type != 'init'){
                    page = num;
                    getList(num);
                }
            }
        });
    }
});
