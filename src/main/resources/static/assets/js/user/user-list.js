$(function () {
    var pageSize = 10;

    getUserList(1);

    function getUserList(currentPage) {
        $.post('/user/list', {page: currentPage, size: pageSize}, function (res) {
            debugger;
            if (res.code === 0) {
                var $tbody =$('#userTable tbody');
                $tbody.empty();
                if (res.data.totalRecord){
                    for (var i = 0; i < res.data.list.length; i++) {
                        var item = res.data.list[i];
                        var $tr = '<tr>' +
                            '        <td>' + (i + 1) + '</td>' +
                            '        <td>'+ item.username +'</td>' +
                            '        <td>'+ item.nickname +'</td>' +
                            '        <td>'+ (item.sex  === '1' ? '男' : '女')  +'</td>' +
                            '        <td>'+ item.age +'</td>' +
                            '        <td>'+ (item.role  === '1' ? '管理员' : '普通用户') +'</td>' +
                            '        <td>'+ (item.enabled  === '1' ? '有效' : '无效') +'</td>' +
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
