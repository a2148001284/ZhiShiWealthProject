<template>
	<view>

		<uni-section title="北向资金" type="line">

			<view>
				<text class="text1">
					当日北向资金总净流入
					<span :style="{color:(b >0?'red':'#52D017')}">
						{{b}}
					</span>
					亿元，沪股通净流入
					<span :style="{color:(h >0?'red':'#52D017')}">
						{{h}}
					</span>
					亿元，深股通净流入
					<span :style="{color:(s >0?'red':'#52D017')}">
						{{s}}
					</span>
					亿元。
				</text>
			</view>

			<view class="uni-padding-wrap uni-common-mt">
				<uni-segmented-control :current="current" :values="items" :style-type="styleType"
					:active-color="activeColor" @clickItem="onClickItem" />
			</view>

			<view class="content">
				<view v-if="current === 0">

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
				</view>

				<view v-if="current === 1">

					<view class="dataTable">
						<u-toast ref="uToast" />
						<view class="dataTable-canvas">
							<view class="dataTable-canvas-title">
								<!--  <text>
									page2
								</text> -->
							</view>
							<canvas id="myEcharts" width="200" height="200"></canvas>
						</view>
					</view>
				</view>

				<view v-if="current === 2">

					<view class="dataTable">
						<u-toast ref="uToast" />
						<view class="dataTable-canvas">
							<view class="dataTable-canvas-title">
								<!--  <text>
									page3
								</text> -->
							</view>
							<canvas id="myEcharts" width="200" height="200"></canvas>
						</view>
					</view>
					<text></text>
				</view>
			</view>

		</uni-section>
	</view>
</template>

<script>
	//import echarts from '@/components/echarts/echarts.vue'
	import * as echarts from './echarts.js';
	import axios from '../../axios.min.js'

	let SgtDatax = [];
	let SgtDatay = [];
	let HgtDatax = [];
	let HgtDatay = [];


	export default {
		data() {
			return {
				a: 0,
				h: 0,
				s: 0,
				myChart: null,
				items: ['深股通', '沪股通', '北向资金'],
				styles: [{
						value: 'button',
						text: '按钮',
						checked: true
					},
					{
						value: 'text',
						text: '文字'
					}
				],
				current: 0,
				colorIndex: 0,
				activeColor: '#007aff',
				styleType: 'button'
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
							//console.log(Datas.north_info);
							this.h = Datas.north_info.hgt;
							this.s = Datas.north_info.sgt;
							this.b = Datas.north_info.north;
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
				//console.log("--->"+Datas);
				SgtDatax = [];
				SgtDatay = [];
				for (var i = Datas.north_info.hgts.length - 1; i >= 0; i--) {
					Datas.north_info.sgts[i].x = this.timeStamp(Datas.north_info.sgts[i].x);
					Datas.north_info.hgts[i].x = this.timeStamp(Datas.north_info.hgts[i].x);

					if (this.current == 0) {
						//sgt
						SgtDatax.push(Datas.north_info.sgts[i].x);
						SgtDatay.push(Datas.north_info.sgts[i].y);
					} else if (this.current == 1) {
						//hgt
						//HgtDatax.push(Datas.north_info.hgts[i].x);
						//HgtDatay.push(Datas.north_info.hgts[i].y);
						SgtDatax.push(Datas.north_info.hgts[i].x);
						SgtDatay.push(Datas.north_info.hgts[i].y);
					} else if (this.current == 2) {
						SgtDatax.push(Datas.north_info.hgts[i].x);
						SgtDatay.push(Datas.north_info.hgts[i].y + Datas.north_info.sgts[i].y);
					}


				}
				//console.log(HgtDatax);


				let option = {
					// x轴数据
					xAxis: {
						type: 'category',
						data: SgtDatax,
						//data: [-1,-1,-1,-1]
					},
					// y轴数据
					yAxis: {
						type: 'value',
					},
					// 这里写2个对象是2条线条，3个则是3条
					series: [{
						// data使用刚才定义的数组，数据从后端接口中取得
						data: SgtDatay,
						//data:[15,24,-1,9],
						type: 'bar',
						smooth: true,
						itemStyle: {
							normal: {
								color: function(SgtDatay) {
									if (SgtDatay.value < 0) {
										return "#9BCA63";
									} else {
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
		height: 200px;

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
				height: 400px;
				position: absolute;
				left: 0px;
				top: 120px;
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
