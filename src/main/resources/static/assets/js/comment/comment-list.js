$(function () {
    $('#insert').click(function () {
        location.hash = 'comment/add';
    });


    var pageSize = 5;
    var page = 1;


    getArticleList(page);
    function getArticleList(currentPage) {
        $.post('/comment/list', {page: currentPage, size: pageSize}, function (res) {
            if (res.code === 0) {
                var $tbody =$('#commentTable tbody');
                $tbody.empty();
                if (res.data.totalRecord){
                    for (var i = 0; i < res.data.list.length; i++) {
                        var item = res.data.list[i];
                        var $tr = '<tr data-id="' + item.id + '">' +
                            '        <td>' + (i + 1) + '</td>' +
                            '        <td>'+
                                        '<a href="#comment/reply/list?commentId=' + item.id + '">' +
                                            '[' + item.subject + ']' + item.title +
                            '           </a>' +
                                    '</td>' +
                            '        <td>'+
                                            '<div class="column">' +
                                                '<span>' + item.author + '</span>' +
                                                '<span>' + item.createTime + '</span>' +
                                            '</div>'
                                      +'</td>' +
                            '        <td>'+ item.replyCount +'</td>' +
                            '        <td>'+
                                        '<div class="column">' +
                                            '<span>' + item.lastReply + '</span>' +
                                            '<span>' + item.lastReplyTime + '</span>' +
                                        '</div>'
                                    +'</td>' +
                            '      </tr>';
                        $tbody.append($tr);
                    }

                }
                page = currentPage;
                paginator(res.data.totalRecord, currentPage);
            }
        })
    }

    function paginator(totalCounts, currentPage) {
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
                    getArticleList(num);
                }
            }
        });
    }
});
