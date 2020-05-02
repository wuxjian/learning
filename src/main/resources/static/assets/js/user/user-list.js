$(function () {
    var pageSize = 10;

    getUserList(1);

    var page = 1;
    //明细
    $('#userTable').delegate('.show-detail', 'click', function () {
        debugger;
        var id = $(this).attr('data-userId');
        location.hash = 'user/profile?userId=' + id;
    });

    $('#userTable').delegate('.operate', 'click', function () {
        debugger;
        var id = $(this).attr('data-userId');
        $.post('/user/toggleStatus', {userId: id}, function (res) {
            if (res.code === 0) {
                alert('success');
                getUserList(page);
            }else {
                alert(res.msg);
            }
        })
    });

    function getUserList() {
        $.post('/user/list', {page: page, size: pageSize}, function (res) {
            if (res.code === 0) {
                var $tbody =$('#userTable tbody');
                $tbody.empty();
                if (res.data.totalRecord){
                    for (var i = 0; i < res.data.list.length; i++) {
                        var item = res.data.list[i];
                        debugger;
                        var operationName;
                        if (item.enabled === '1'){
                            operationName = '<button class="btn btn-danger btn-sm operate" data-userId="' + item.id + '">' + '禁用' + '</button>';
                        }else {
                            operationName = '<button class="btn btn-primary btn-sm operate" data-userId="' + item.id + '">' + '启用' + '</button>';
                        }
                        var $tr = '<tr>' +
                            '        <td>' + (i + 1) + '</td>' +
                            '        <td>'+ item.username +'</td>' +
                            '        <td>'+ item.nickname +'</td>' +
                            '        <td>'+ (item.sex  === '1' ? '男' : '女')  +'</td>' +
                            '        <td>'+ item.age +'</td>' +
                            '        <td>'+ (item.role  === '1' ? '管理员' : '普通用户') +'</td>' +
                            '        <td>'+ (item.enabled  === '1' ? '有效' : '无效') +'</td>' +
                            '        <td>'+
                                            operationName +
                                            '<button class="btn btn-success btn-sm show-detail" data-userId="' + item.id + '">明细</button>' +
                                     '</td>' +
                            '      </tr>';
                        $tbody.append($tr);
                    }

                }
                paginator(res.data.totalRecord, page);
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
                    page = num;
                    getUserList();
                }
            }
        });
    }
});
