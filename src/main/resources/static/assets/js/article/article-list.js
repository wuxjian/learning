$(function () {
    $('#insert').click(function () {
        location.hash = 'article/add';
    });

    var pageSize = 10;

    getArticleList(1);

    function getArticleList(currentPage) {
        $.post('/article/list', {page: currentPage, size: pageSize}, function (res) {
            debugger;
            if (res.code === 0) {
                var $tbody =$('#userTable tbody');
                $tbody.empty();
                if (res.data.totalRecord){
                    for (var i = 0; i < res.data.list.length; i++) {
                        var item = res.data.list[i];
                        var $tr = '<tr>' +
                            '        <td>' + (i + 1) + '</td>' +
                            '        <td>'+ item.title +'</td>' +
                            '        <td>'+ item.subject +'</td>' +
                            '        <td>'+ (item.status  === '1' ? '草稿' : '已发布')  +'</td>' +
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
                    getArticleList(num);
                }
            }
        });
    }
});
