$(function () {
    $('#insert').click(function () {
        location.hash = 'examination/add';
    });


    //answer
    $('#examinationTable').delegate('.answer', 'click', function () {
        var id = $(this).parents('tr').attr('data-id');
        location.hash = 'examination/answer?examId=' + id;
    });



    var pageSize = 5;
    var page = 1;


    getList(page);
    function getList(currentPage) {
        $.post('/examination/list', {page: currentPage, size: pageSize}, function (res) {
            if (res.code === 0) {
                var $tbody =$('#examinationTable tbody');
                $tbody.empty();
                if (res.data.totalRecord){
                    for (var i = 0; i < res.data.list.length; i++) {
                        var item = res.data.list[i];
                        var $tr = '<tr data-id="' + item.id + '">' +
                            '        <td>' + (i + 1) + '</td>' +
                            '        <td>'+ item.examName +'</td>' +
                            '        <td>'+ item.examScore +'</td>' +
                            '        <td>'+
                                        // '<button class="btn btn-primary btn-sm toggle-status">' + action + '</button>' +
                                        '<button class="btn btn-info btn-sm answer">测评</button>' +
                                        // '<button class="btn btn-success btn-sm preview">预览</button>' +
                                    '</td>' +
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
                    getList(num);
                }
            }
        });
    }
});
