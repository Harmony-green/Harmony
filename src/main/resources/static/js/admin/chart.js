/**
 * 
 */
//판매량
Highcharts.chart('salesRate', {
    chart: {
        type: 'bar'
    },
    title: {
        text: '연도별 판매량',
        align: 'left'
    },
    xAxis: {
        categories: ['목걸이', '반지', '팔찌', '귀걸이'],
        title: {
            text: null
        },
        gridLineWidth: 1,
        lineWidth: 0
    },
    yAxis: {
        min: 0,
        title: {
            text: '판매량(건)',
            align: 'high'
        },
        labels: {
            overflow: 'justify'
        },
        gridLineWidth: 0
    },
    plotOptions: {
        bar: {
            borderRadius: '50%',
            dataLabels: {
                enabled: true
            },
            groupPadding: 0.1
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'top',
        x: -40,
        y: 80,
        floating: true,
        borderWidth: 1,
        backgroundColor:
            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
        shadow: true
    },
    credits: {
        enabled: false
    },
    series: [{
        name: 'Year 2021',
        data: [1050, 985, 1211, 2215]
    }, {
        name: 'Year 2022',
        data: [818, 965, 1262, 2348]
    }, {
        name: 'Year 2023',
        data: [1321, 1355, 1421, 2856]
    }]
});


//월별방문자
Highcharts.chart('visitors', {
    chart: {
        type: 'spline'
    },
    title: {
        text: '월별 방문자 추이',
        align: 'left'
    },
    xAxis: {
        categories: ['01월', '02월', '03월', '04월', '05월', '06월',
            '07월', '08월', '09월', '10월', '11월', '12월'],
        accessibility: {
            description: 'Months of the year'
        }
    },
    yAxis: {
        title: {
            text: '방문자'
        },
        labels: {
            format: '{value}명'
        }
    },
    tooltip: {
        crosshairs: true,
        shared: true
    },
    plotOptions: {
        spline: {
            marker: {
                radius: 4,
                lineColor: '#666666',
                lineWidth: 1
            }
        }
    },
    series: [{
        name: '회원',
        marker: {
            symbol: 'square'
        },
        data: [3444, 4211, 4575, 4950, 5111, 5842, 5844, {
            y: 6945,
            marker: {
                symbol: 'url(/img/common/thumbs.png)'
            },
            accessibility: {
                description: 'Sunny symbol, this is the warmest point in the chart.'
            }
        }, 5742, 5236, 6745, 6128]

    }, {
        name: '비회원',
        marker: {
            symbol: 'diamond'
        },
        data: [6215, 6754, 8425, 5145, {
            y: 8945,
            marker: {
                symbol: 'url(/img/common/thumbs.png)'
            },
            accessibility: {
                description: 'Snowy symbol, this is the coldest point in the chart.'
            }
        }, 7485, 7262, 6985, 8262, 6954, 6998, 7654]
    }]
});

//신규가입,탈퇴
Highcharts.chart('newOut', {
    chart: {
        type: 'column'
    },
    title: {
        text: '월별 신규가입자, 탈퇴자',
        align: 'left'
    },
    xAxis: {
        categories: ['01월', '02월', '03월', '04월', '05월', '06월',
            '07월', '08월', '09월', '10월', '11월', '12월'],
        crosshair: true,
        accessibility: {
            description: 'month'
        }
    },
    yAxis: {
        min: 0,
        title: {
            text: '건수'
        }
    },
    plotOptions: {
        column: {
            pointPadding: 0.2,
            borderWidth: 0
        }
    },
    series: [
        {
            name: '신규가입자',
            data: [101, 54, 75, 13, 84, 51, 87, 94, 12, 34, 22, 64]
        },
        {
            name: '탈퇴회원',
            data: [2, 5, 1, 0, 0 ,6, 3, 2, 7, 0, 2, 1]
        }
    ]
});




//등급현황
Highcharts.setOptions({
    colors: Highcharts.map(Highcharts.getOptions().colors, function (color) {
        return {
            radialGradient: {
                cx: 0.5,
                cy: 0.3,
                r: 0.7
            },
            stops: [
                [0, color],
                [1, Highcharts.color(color).brighten(-0.3).get('rgb')] // darken
            ]
        };
    })
});

// Build the chart
Highcharts.chart('rating', {
    chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
    },
    title: {
        text: '고객 등급현황',
        align: 'left'
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    accessibility: {
        point: {
            valueSuffix: '%'
        }
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: true,
                format: '<span style="font-size: 1.2em"><b>{point.name}</b></span><br>' +
                    '<span style="opacity: 0.6">{point.percentage:.1f} %</span>',
                connectorColor: 'rgba(128,128,128,0.5)'
            }
        }
    },
    series: [{
        name: 'Share',
        data: [
            { name: 'BRONZE', y: 60 },
            { name: 'SILVER', y: 30 },
            { name: 'GOLD', y: 10 },
        ]
    }]
});

function openSalesRate(){
	document.getElementById('salesRateBtn').style.backgroundColor="black";
	document.getElementById('salesRateBtn').style.color="white";
	document.getElementById('visitorsBtn').style.backgroundColor="white";
	document.getElementById('visitorsBtn').style.color="black";
	document.getElementById('newOutBtn').style.backgroundColor="white";
	document.getElementById('newOutBtn').style.color="black";
	document.getElementById('ratingBtn').style.backgroundColor="white";
	document.getElementById('ratingBtn').style.color="black";
	document.getElementById('salesRate').style.display="block";
	document.getElementById('visitors').style.display="none";
	document.getElementById('newOut').style.display="none";
	document.getElementById('rating').style.display="none";
}
function openVisitors(){
	document.getElementById('visitorsBtn').style.backgroundColor="black";
	document.getElementById('visitorsBtn').style.color="white";
	document.getElementById('salesRateBtn').style.backgroundColor="white";
	document.getElementById('salesRateBtn').style.color="black";
	document.getElementById('newOutBtn').style.backgroundColor="white";
	document.getElementById('newOutBtn').style.color="black";
	document.getElementById('ratingBtn').style.backgroundColor="white";
	document.getElementById('ratingBtn').style.color="black";
	document.getElementById('visitors').style.display="block";
	document.getElementById('salesRate').style.display="none";
	document.getElementById('newOut').style.display="none";
	document.getElementById('rating').style.display="none";
}
function openNewOut(){
	document.getElementById('newOutBtn').style.backgroundColor="black";
	document.getElementById('newOutBtn').style.color="white";
	document.getElementById('visitorsBtn').style.backgroundColor="white";
	document.getElementById('visitorsBtn').style.color="black";
	document.getElementById('salesRateBtn').style.backgroundColor="white";
	document.getElementById('salesRateBtn').style.color="black";
	document.getElementById('ratingBtn').style.backgroundColor="white";
	document.getElementById('ratingBtn').style.color="black";
	document.getElementById('newOut').style.display="block";
	document.getElementById('visitors').style.display="none";
	document.getElementById('salesRate').style.display="none";
	document.getElementById('rating').style.display="none";
}
function openRating(){
	document.getElementById('ratingBtn').style.backgroundColor="black";
	document.getElementById('ratingBtn').style.color="white";
	document.getElementById('visitorsBtn').style.backgroundColor="white";
	document.getElementById('visitorsBtn').style.color="black";
	document.getElementById('newOutBtn').style.backgroundColor="white";
	document.getElementById('newOutBtn').style.color="black";
	document.getElementById('salesRateBtn').style.backgroundColor="white";
	document.getElementById('salesRateBtn').style.color="black";
	document.getElementById('rating').style.display="block";
	document.getElementById('visitors').style.display="none";
	document.getElementById('newOut').style.display="none";
	document.getElementById('salesRate').style.display="none";
}




