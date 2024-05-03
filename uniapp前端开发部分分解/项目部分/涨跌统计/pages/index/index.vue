<template>
	<view>

		<uni-section title="涨跌统计" type="line">
		<view>
			<text class="text1">
				当前跌停<text style="color:#52D017 ;">{{fall}}</text>,当前涨停<text style="color:red;">{{rise}}</text>
			</text>
		</view>
			
			<view class="dataTable">
				<u-toast ref="uToast" />
				<view class="dataTable-canvas">
					<view class="dataTable-canvas-title">
						<!--  <text>
							page1
						</text> -->
			
					</view>
					<canvas id="myEcharts" width="200" height="200"></canvas>
				</view>
			</view>
		</uni-section> 
	</view>
</template>

<script>
	//import echarts from '@/components/echarts/echarts.vue'
	import * as echarts from './echarts.js';
	import axios from '../../axios.min.js'

	let datay = [];
	let datax = [];
	let k=0;

	export default {
		data() {
			return {
				fall:0,
				rise:0,
				myChart: null,
			}
		},
		onLoad(options) {

			let that = this;
			// 通过nextTick异步画图
			this.$nextTick(() => {
				that.drawLines();
			});
		},

		beforeDestroy() {
			// 页面关闭时销毁echarts实例
			this.myChart.clear();
			this.myChart.dispose();
		},
		methods: {
			getDates() {
				return new Promise(resolve => {
					var url = 'http://www.zsxg.cn/api/v2/index/northAndRfStat?container=pc';
					axios.get(url).then((response) => {
							var Datas = response.data.datas;
							resolve(Datas);
							/* console.log(Datas.rise_fall_stat.f);//跌停，绿色
							console.log(Datas.rise_fall_stat.list);//数据
							console.log(Datas.rise_fall_stat.r);//涨停，红色
							console.log(Datas.rise_fall_stat.p);//中间量 */
							datay=Datas.rise_fall_stat.list;
							datax=['跌停',' ','9',' ','7',' ','5',' ','3',' ','1',' ','1',' ','3',' ','5',' ','7',' ','9',' ','涨停']
							this.fall=Datas.rise_fall_stat.f;
							this.rise=Datas.rise_fall_stat.r;
						})
						.catch(function(error) {
							console.log(error);
						});
					//console.log(SgtDatax);
				});

			},

			echartsClick(params) {
				console.log('点击数据', params)
			},
			/**
			 * 切换数据
			 */

			onClickItem(e) {
				this.myChart.clear();
				this.myChart.dispose();
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex
				}
				let that = this;
				// 通过nextTick异步画图
				this.$nextTick(() => {
					that.drawLines();
				});
			},

			async drawLines() {
				this.myChart = echarts.init(document.getElementById('myEcharts'));
				let Datas = await this.getDates();
				
				let option = {
					// x轴数据
					xAxis: {
						type: 'category',
						data: datax,
						//data: [-1,-1,-1,-1]
					},
					// y轴数据
					yAxis: {
						type: 'value',
					},
					// 这里写2个对象是2条线条，3个则是3条
					series: [{
						// data使用刚才定义的数组，数据从后端接口中取得
						data: datay,
						//data:[15,24,-1,9],
						type: 'bar',
						smooth: true,
						itemStyle: {
							normal: {
								color: 
								function() {
									
									console.log(5/2);
									k++;
									if (k < datay.length/2) {
										return "#9BCA63";
									}
									else if(k < datay.length/2 +1 ){
										return "#778899";
									}
									 else {
										return "#FE8463";
									}
									
								}
							}
						}
					}],
					grid: {
						// 这里可以防止Y轴显示不全
						containLabel: true
					}
				};

				this.myChart.setOption(option);
				window.addEventListener('resize', () => {
					this.myChart.resize();
					myChartBox.resize();
				});

				/* window.onresize = function() {
					myChart.resize();
				} */

			},


			timeStamp(value) {
				if (!value) return '';
				var now = new Date(value);
				//console.log("time:" + JSON.stringify(now));
				var year = now.getFullYear();
				var month = now.getMonth() + 1;
				if (month < 10) {
					month = '0' + month
				}
				var date = now.getDate();
				if (date < 10) {
					date = '0' + date
				}
				return year + "-" + month + "-" + date
			}

		},


	};
</script>

<style scope lang="scss">
	.text1 {
		font-size: 5px;
		position: relative;
		left: 30px;
	}
	
	.dataTable {
		padding: 10px;
		width: 400px;
		height: 100px;

		&-canvas {
			&-title {
				padding: 20rpx 0rpx;

				>text {
					padding-left: 20rpx;
					width: 100%;
					height: 100%;
					font-size: 32rpx;
					font-weight: 550;
					line-height: 32rpx;
					border-left: 10rpx solid #28b5b1;
				}

				position: absolute;
				left: 0px;
			}

			#myEcharts {
				width: 100%;
				height: 300px;
				position: absolute;
				left: 0px;
				top: 50px;
			}
		}
	}

	.example-body {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		padding: 0;
	}

	.uni-common-mt {
		margin-top: 30px;
	}

	.uni-padding-wrap {
		// width: 750rpx;
		padding: 0px 30px;
	}

	.content {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		justify-content: center;
		align-items: center;
		height: 150px;
		text-align: center;
	}

	.content-text {
		font-size: 14px;
		color: $uni-text-color;
	}

	.color-tag {
		width: 25px;
		height: 25px;
	}

	.uni-list {
		flex: 1;
	}

	.uni-list-item {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex: 1;
		flex-direction: row;
		background-color: #FFFFFF;
	}


	.uni-list-item__container {
		padding: 12px 15px;
		width: 100%;
		flex: 1;
		position: relative;
		/* #ifndef APP-NVUE */
		display: flex;
		box-sizing: border-box;
		/* #endif */
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		border-bottom-style: solid;
		border-bottom-width: 1px;
		border-bottom-color: #eee;
	}

	.uni-list-item__content-title {
		font-size: 14px;
	}
</style>
