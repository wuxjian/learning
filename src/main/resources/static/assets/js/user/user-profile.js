$(function () {
    $('#chart-content').css('width', $("#calWith").width());
    var myChart = echarts.init(document.getElementById('chart-content'));

    renderChart();



    function renderChart() {
        $.get('/user/profileStatistic', {userId: $('#userId').val()}, function (res) {
            debugger;
            if(res.code === 0){
                $('#learnCount').text(res.data.learnCount);
                $('#learnTimes').text(res.data.learnTimes);
                $('#commentCount').text(res.data.commentCount);
                $('#commentReplyCount').text(res.data.commentReplyCount);
                var data = [
                    {value: res.data.learnCount, name: '学习次数'},
                    {value: res.data.learnTimes, name: '学习时间(min)'},
                    {value: res.data.commentCount, name: '留言数'},
                    {value: res.data.commentReplyCount, name: '回复数'},
                ];

                myChart.setOption({
                    tooltip: {},
                    series : [
                        {
                            name: '统计',
                            type: 'pie',    // 设置图表类型为饼图
                            radius: '55%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
                            data: data
                        }
                    ]
                })
            }
        });
    }
});
