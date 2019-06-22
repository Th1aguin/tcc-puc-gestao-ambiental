function randomNumber(min, max) {
			return Math.random() * (max - min) + min;
		}

		function randomBar(date, lastClose) {
			var open = randomNumber(lastClose * 0.95, lastClose * 1.05).toFixed(2);
			var close = randomNumber(open * 0.95, open * 1.05).toFixed(2);
			return {
				t: date.valueOf(),
				y: close
			};
		}

		function makeData(days, initial){
			var date = moment();
			date = date.subtract(days,'d')
			var data = [randomBar(date, initial)];
			while (data.length < days) {
				date = date.clone().add(1, 'd');			
					data.push(randomBar(date, data[data.length - 1].y));	
			}
			return data;
		}

		function makeConfig(dados, header, title, cor){
				var cfg = {
				type: 'bar',
				data: {
					datasets: [{
						label: header,
						backgroundColor: color(cor).alpha(0.5).rgbString(),
						borderColor: cor,
						data: dados,
						type: 'line',
						pointRadius: 0,
						fill: false,
						lineTension: 0,
						borderWidth: 2
					}]
				},
				options: {
					scales: {
						xAxes: [{
							type: 'time',
							distribution: 'series',
							time: {
			                    displayFormats: {
			                        day: 'MMM D'
			                    }
			                }
						}],
						yAxes: [{
							scaleLabel: {
								display: true,
								labelString: title
							}
						}]
					},
					tooltips: {
						intersect: false,
						mode: 'index',
						callbacks: {
							label: function(tooltipItem, myData) {
								var label = myData.datasets[tooltipItem.datasetIndex].label || '';
								if (label) {
									label += ': ';
								}
								label += parseFloat(tooltipItem.value).toFixed(2);
								return label;
							}
						}
					}
				}
			};
			return cfg;
		}

		var ctx = document.getElementById('chart1').getContext('2d');
		var ctx2 = document.getElementById('chart2').getContext('2d');
		var ctx3 = document.getElementById('chart3').getContext('2d');
		var ctx4 = document.getElementById('chart4').getContext('2d');
		var ctx5 = document.getElementById('chart5').getContext('2d');

		var color = Chart.helpers.color;

		var dados = makeData(60,30)
		var cfg = makeConfig(dados,'Temperatura ', 'indice', window.chartColors.red)
		var chart = new Chart(ctx, cfg);

		var dados2 = makeData(60,30)
		var cfg2 = makeConfig(dados2,'Nivel agua', 'indice', window.chartColors.blue)
		var chart2 = new Chart(ctx2, cfg2);

		var dados3 = makeData(60,30)
		var cfg3 = makeConfig(dados3,'Movimentação', 'indice', window.chartColors.green)
		var chart3 = new Chart(ctx3, cfg3);

		var dados4 = makeData(60,30)
		var cfg4 = makeConfig(dados4,'Inclinometro', 'indice', window.chartColors.yellow)
		var chart4 = new Chart(ctx4, cfg4);

		var dados5 = makeData(60,30)
		var cfg5 = makeConfig(dados5,'Pressào da agua', 'indice', window.chartColors.purple)
		var chart5 = new Chart(ctx5, cfg5);