$(function () {

    //begin learn
    $('#articleLearnTable').delegate('.begin-learn', 'click', function () {
        var id = $(this).parents('tr').attr('data-id');
        location.hash = 'article/learn/learning?articleId=' + id;
    });

    var pageSize = 5;

    getUserList(1);

    function getUserList(currentPage) {
        $.post('/article/learn/list', {page: currentPage, size: pageSize}, function (res) {
            if (res.code === 0) {
                var $tbody =$('#articleLearnTable tbody');
                $tbody.empty();
                if (res.data.totalRecord){
                    for (var i = 0; i < res.data.list.length; i++) {
                        var item = res.data.list[i];
                        var button = item.userId ? '<button class="btn btn-success btn-sm begin-learn">再次学习</button>'
                            : '<button class="btn btn-primary btn-sm begin-learn">开始学习</button>';
                        var $tr = '<tr data-id="' + item.articleId + '">' +
                            '        <td>' + (i + 1) + '</td>' +
                            '        <td>'+ item.subject +'</td>' +
                            '        <td>'+ item.title +'</td>' +
                            '        <td>'+ (item.userId ? '是' : '否')  +'</td>' +
                            '        <td>'+ button +'</td>' +
                            '      </tr>';
                        $tbody.append($tr);
                    }

                }
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
                    getUserList(num);
                }
            }
        });
    }
});
