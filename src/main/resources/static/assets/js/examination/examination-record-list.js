$(function () {
    $('#chart-content').css('width', $("#chart").width());
    var myChart = echarts.init(document.getElementById('chart-content'));

    $('#chart button').click(function () {
        $('#chart button').removeClass('active');
        $(this).addClass('active');
        renderChart($(this).attr('data-counts'));
    });

    var hasRender = false;

    $('#chart-tab').click(function () {
        if (!hasRender) {
            renderChart(7);
        }
        hasRender = true;
    });


    function renderChart(recentCounts) {

        $.post('/examination/record/chart', {recentCounts: recentCounts}, function (res) {

            if (res.code === 0) {
                var xAxis = [];
                var data = [];
                for (var i = 0; i < res.data.length; i++) {
                    var item = res.data[i];
                    xAxis.push(item.createTime);
                    data.push(item.score);
                }

                myChart.setOption({
                    color: ['#749f83', '#ca8622', '#bda29a', '#6e7074', '#546570', '#c4ccd3'],
                    title: {
                        text: '最近' + recentCounts + '次学习记录'
                    },
                    tooltip: {},
                    legend: {
                        data: ['成绩(分)']
                    },
                    xAxis: {
                        data: xAxis,
                        axisLabel: {
                            interval: 0,
                            rotate: 45
                        }
                    },
                    grid: {
                        left: '5%',
                        bottom: '25%'
                    },
                    yAxis: {},
                    series: [{
                        name: '成绩(分)',
                        type: 'bar',
                        data: data
                    }]
                });
            }
        });
    }

    var pageSize = 5;

    getUserList(1);

    function getUserList(currentPage) {
        $.post('/examination/record/list', {page: currentPage, size: pageSize}, function (res) {
            if (res.code === 0) {
                var $tbody = $('#articleRecordTable tbody');
                $tbody.empty();
                if (res.data.totalRecord) {
                    for (var i = 0; i < res.data.list.length; i++) {
                        var item = res.data.list[i];
                        var $tr = '<tr data-id="' + item.articleId + '">' +
                            '        <td>' + (i + 1) + '</td>' +
                            '        <td>' + item.examName + '</td>' +
                            '        <td>' + item.examScore + '</td>' +
                            '        <td>' + item.score + '</td>' +
                            '        <td>' + item.createTime + '</td>' +
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
                if (type != 'init') {
                    getUserList(num);
                }
            }
        });
    }
});
