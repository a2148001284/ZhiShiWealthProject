<template>
	<uni-section title="个股案例" type="line" padding>
		<uni-grid :column="3" :square="false" :highlight="false" @change="change">
			<uni-grid-item v-for="(item, index) in list" :index="index" :key="index" >
				<view class="grid-item-box" style="background-color: antiquewhite;">
					<image :src="item.url" class="image" mode="aspectFill" />
					<text class="text" @click="change(item.id)" style="color:blueviolet">{{ item.text }}</text>
				</view>
			</uni-grid-item>
		</uni-grid>
		
		<view class="content">
			<view v-if="current === 0">
				
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表1]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">营业利用率图</text>如下：
						<canvas id="myEchartsA"></canvas>
					</view>
				</view>
				
				<br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表2]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">营业利用率图</text>如下：
						<canvas id="myEchartsB"></canvas>
					</view>
				</view>
				
				<br><br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表1]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">费用率图</text>如下：
						<canvas id="myEchartsC"></canvas>
					</view>
				</view>
				
				<br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表2]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">费用率图</text>如下：
						<canvas id="myEchartsD"></canvas>
					</view>
				</view>
				
			</view>
				
			<view v-if="current === 1">
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表1]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">营业利用率图</text>如下：
						<canvas id="myEchartsA"></canvas>
					</view>
				</view>
				
				<br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表2]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">营业利用率图</text>如下：
						<canvas id="myEchartsB"></canvas>
					</view>
				</view>
				
				<br><br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表1]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">费用率图</text>如下：
						<canvas id="myEchartsC"></canvas>
					</view>
				</view>
				
				<br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表2]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">费用率图</text>如下：
						<canvas id="myEchartsD"></canvas>
					</view>
				</view>
			</view>
				
			<view v-if="current === 2">
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表1]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">营业利用率图</text>如下：
						<canvas id="myEchartsA"></canvas>
					</view>
				</view>
				
				<br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表2]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">营业利用率图</text>如下：
						<canvas id="myEchartsB"></canvas>
					</view>
				</view>
				
				<br><br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表1]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">费用率图</text>如下：
						<canvas id="myEchartsC"></canvas>
					</view>
				</view>
				
				<br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表2]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">费用率图</text>如下：
						<canvas id="myEchartsD"></canvas>
					</view>
				</view>
			</view>
			
			<view v-if="current === 3">
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表1]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">营业利用率图</text>如下：
						<canvas id="myEchartsA"></canvas>
					</view>
				</view>
				
				<br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表2]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">营业利用率图</text>如下：
						<canvas id="myEchartsB"></canvas>
					</view>
				</view>
				
				<br><br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表1]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">费用率图</text>如下：
						<canvas id="myEchartsC"></canvas>
					</view>
				</view>
				
				<br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表2]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">费用率图</text>如下：
						<canvas id="myEchartsD"></canvas>
					</view>
				</view>
			</view>
			
			<view v-if="current === 4">
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表1]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">营业利用率图</text>如下：
						<canvas id="myEchartsA"></canvas>
					</view>
				</view>
				
				<br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表2]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">营业利用率图</text>如下：
						<canvas id="myEchartsB"></canvas>
					</view>
				</view>
				
				<br><br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表1]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">费用率图</text>如下：
						<canvas id="myEchartsC"></canvas>
					</view>
				</view>
				
				<br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表2]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">费用率图</text>如下：
						<canvas id="myEchartsD"></canvas>
					</view>
				</view>
			</view>
			
			<view v-if="current === 5">
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表1]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">营业利用率图</text>如下：
						<canvas id="myEchartsA"></canvas>
					</view>
				</view>
				
				<br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表2]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">营业利用率图</text>如下：
						<canvas id="myEchartsB"></canvas>
					</view>
				</view>
				
				<br><br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表1]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">费用率图</text>如下：
						<canvas id="myEchartsC"></canvas>
					</view>
				</view>
				
				<br>
				<view class="dataTable">
					<u-toast ref="uToast" />
					<view class="dataTable-canvas">
						[表2]股票：<text style="color: crimson;">{{list[current].text}}</text>的<text style="color:blueviolet;">费用率图</text>如下：
						<canvas id="myEchartsD"></canvas>
					</view>
				</view>
			</view>
		</view>
		
	</uni-section>
	
</template>

<script>
	import axios from '../../axios.min.js';
	import * as echarts from './echarts.js';
	let Data11 = [];
	let Data12 = [];
	let Data13 = [];
	let Data14 = [];
	
	let commonX = [2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022];
	let commonY1 = [];
	let commonY2 = [];
	let commonY3 = [];
	let commonY4 = [];
	
	let urlfinal = "http://www.zsxg.cn/api/v2/quarter/profitability?code=002555.SZ&compareCode=&types=roi%2CprofitMargin%2CcostMargin%2Cdupont&yearNum=12";

	
	export default {
		data() {
			return {
				myChart1: null,
				myChart2: null,
				myChart3: null,
				myChart4: null,
				current:0,
				url1:"http://www.zsxg.cn/api/v2/quarter/profitability?code=002555.SZ&compareCode=&types=roi%2CprofitMargin%2CcostMargin%2Cdupont&yearNum=12",
				url2:"http://www.zsxg.cn/api/v2/quarter/profitability?code=002603.SZ&compareCode=&types=roi%2CprofitMargin%2CcostMargin%2Cdupont&yearNum=12",
				url3:"http://www.zsxg.cn/api/v2/quarter/profitability?code=000333.SZ&compareCode=&types=roi%2CprofitMargin%2CcostMargin%2Cdupont&yearNum=12",
				url4:"http://www.zsxg.cn/api/v2/quarter/profitability?code=002241.SZ&compareCode=&types=roi%2CprofitMargin%2CcostMargin%2Cdupont&yearNum=12",
				url5:"http://www.zsxg.cn/api/v2/quarter/profitability?code=002271.SZ&compareCode=&types=roi%2CprofitMargin%2CcostMargin%2Cdupont&yearNum=12",
				url6:"http://www.zsxg.cn/api/v2/quarter/profitability?code=002317.SZ&compareCode=&types=roi%2CprofitMargin%2CcostMargin%2Cdupont&yearNum=12",
				list: [{
					id:1,
					text: '三七互娱',
				},
				{
					id:2,
					text: '以岭药业',
				},
				{
					id:3,
					text: '美的集团',
				},
				{
					id:4,
					text: '歌尔股份',
				},
				{
					id:5,
					text: '东方雨虹'
				},
				{
					id:6,
					text: '众生药业'
				}
			]
			}
		},
		onLoad() {
			let that = this;
			// 通过nextTick异步画图
			this.$nextTick(() => {
				that.drawLines1();
				that.drawLines2();
				that.drawLines3();
				that.drawLines4();
			});
			
		},
		methods: {
			change(x){
				let condition = x.detail.index;
				if(condition == 0) urlfinal = this.url1;
				if(condition == 1) urlfinal = this.url2;
				if(condition == 2) urlfinal = this.url3;
				if(condition == 3) urlfinal = this.url4;
				if(condition == 4) urlfinal = this.url5;
				if(condition == 5) urlfinal = this.url6;
				this.current = condition;
				this.myChart1.clear();
				this.myChart1.dispose();
				this.myChart2.clear();
				this.myChart2.dispose();
				this.myChart3.clear();
				this.myChart3.dispose();
				this.myChart4.clear();
				this.myChart4.dispose();
				
				let that = this;
				// 通过nextTick异步画图
				this.$nextTick(() => {
					that.drawLines1();
					that.drawLines2();
					that.drawLines3();
					that.drawLines4();
				});
			},
			
			getDates1() {
				return new Promise(resolve => {
					var url = urlfinal;
					axios.get(url).then((response) => {
							var Datas = response.data.datas;
							resolve(Datas);
							Data11=response.data.datas.origin.optoor;
							Data12=response.data.datas.compare.optoor;
							Data13=response.data.datas.origin.allExpense;
							Data14=response.data.datas.compare.allExpense;
						})
						.catch(function(error) {
							//console.log(error);
						});
					//console.log(SgtDatax);
				});
			
			},
			
			async drawLines1() {
				this.myChart1 = echarts.init(document.getElementById('myEchartsA'));
				let Datas = await this.getDates1();
				commonY1 = [];
				//commonX = [];
				for(var i= 0 ; i< Data11.length ; i++){
					commonY1.push(Data11[i].value);
					//commonY2.push(Data12[i].value);
					//commonY3.push(Data13[i].value);
					//commonY4.push(Data14[i].value);
				}
				
				let option = {
					// x轴数据
					xAxis: {
						type: 'category',
						data: commonX,
						//data: [-1,-1,-1,-1]
					},
					// y轴数据
					yAxis: {
						type: 'value',
					},
					// 这里写2个对象是2条线条，3个则是3条
					series: [{
						// data使用刚才定义的数组，数据从后端接口中取得
						data: commonY1,
						//data:[15,24,-1,9],
						type: 'line',
						smooth: true,
						itemStyle: {
							normal: {
								color: 
								function() {
									return "#9BCA63";
								}
							}
						}
					}],
					grid: {
						// 这里可以防止Y轴显示不全
						containLabel: true
					}
				};
			
				this.myChart1.setOption(option);
				window.addEventListener('resize', () => {
					this.myChart1.resize();
					myChartBox.resize();
				});
			},
			
			getDates2() {
				return new Promise(resolve => {
					var url = urlfinal;
					axios.get(url).then((response) => {
							var Datas = response.data.datas;
							resolve(Datas);
							Data11=response.data.datas.origin.optoor;
							Data12=response.data.datas.compare.optoor;
							Data13=response.data.datas.origin.allExpense;
							Data14=response.data.datas.compare.allExpense;
						})
						.catch(function(error) {
							//console.log(error);
						});
					//console.log(SgtDatax);
				});
			
			},
			
			async drawLines2() {
				this.myChart2 = echarts.init(document.getElementById('myEchartsB'));
				let Datas = await this.getDates2();
				commonY2 = [];
				//commonX = [];
				for(var i= 0 ; i< Data12.length ; i++){
					commonY2.push(Data12[i].value);
				}
				
				let option = {
					// x轴数据
					xAxis: {
						type: 'category',
						data: commonX,
						//data: [-1,-1,-1,-1]
					},
					// y轴数据
					yAxis: {
						type: 'value',
					},
					// 这里写2个对象是2条线条，3个则是3条
					series: [{
						// data使用刚才定义的数组，数据从后端接口中取得
						data: commonY2,
						//data:[15,24,-1,9],
						type: 'line',
						smooth: true,
						itemStyle: {
							normal: {
								color: 
								function() {
									return "#9BCA63";
								}
							}
						}
					}],
					grid: {
						// 这里可以防止Y轴显示不全
						containLabel: true
					}
				};
			
				this.myChart2.setOption(option);
				window.addEventListener('resize', () => {
					this.myChart2.resize();
					myChartBox.resize();
				});
			},
			
			getDates3() {
				return new Promise(resolve => {
					var url = urlfinal;
					axios.get(url).then((response) => {
							var Datas = response.data.datas;
							resolve(Datas);
							Data11=response.data.datas.origin.optoor;
							Data12=response.data.datas.compare.optoor;
							Data13=response.data.datas.origin.allExpense;
							Data14=response.data.datas.compare.allExpense;
						})
						.catch(function(error) {
							//console.log(error);
						});
					//console.log(SgtDatax);
				});
			
			},
			
			async drawLines3() {
				this.myChart3 = echarts.init(document.getElementById('myEchartsC'));
				let Datas = await this.getDates3();
				commonY3 = [];
				//commonX = [];
				for(var i= 0 ; i< Data13.length ; i++){
					commonY3.push(Data13[i].value);
				}
				
				let option = {
					// x轴数据
					xAxis: {
						type: 'category',
						data: commonX,
						//data: [-1,-1,-1,-1]
					},
					// y轴数据
					yAxis: {
						type: 'value',
					},
					// 这里写2个对象是2条线条，3个则是3条
					series: [{
						// data使用刚才定义的数组，数据从后端接口中取得
						data: commonY3,
						//data:[15,24,-1,9],
						type: 'line',
						smooth: true,
						itemStyle: {
							normal: {
								color: 
								function() {
									return "#9BCA63";
								}
							}
						}
					}],
					grid: {
						// 这里可以防止Y轴显示不全
						containLabel: true
					}
				};
			
				this.myChart3.setOption(option);
				window.addEventListener('resize', () => {
					this.myChart3.resize();
					myChartBox.resize();
				});
			},
			
			getDates4() {
				return new Promise(resolve => {
					var url = urlfinal;
					axios.get(url).then((response) => {
							var Datas = response.data.datas;
							resolve(Datas);
							Data11=response.data.datas.origin.optoor;
							Data12=response.data.datas.compare.optoor;
							Data13=response.data.datas.origin.allExpense;
							Data14=response.data.datas.compare.allExpense;
						})
						.catch(function(error) {
							//console.log(error);
						});
					//console.log(SgtDatax);
				});
			
			},
			
			async drawLines4() {
				this.myChart4 = echarts.init(document.getElementById('myEchartsD'));
				let Datas = await this.getDates4();
				commonY4 = [];
				//commonX = [];
				for(var i= 0 ; i< Data14.length ; i++){
					//commonX.push(Data11[i].reportPeriod);
					commonY4.push(Data14[i].value);
				}
				
				let option = {
					// x轴数据
					xAxis: {
						type: 'category',
						data: commonX,
						//data: [-1,-1,-1,-1]
					},
					// y轴数据
					yAxis: {
						type: 'value',
					},
					// 这里写2个对象是2条线条，3个则是3条
					series: [{
						// data使用刚才定义的数组，数据从后端接口中取得
						data: commonY4,
						//data:[15,24,-1,9],
						type: 'line',
						smooth: true,
						itemStyle: {
							normal: {
								color: 
								function() {
									return "#9BCA63";
								}
							}
						}
					}],
					grid: {
						// 这里可以防止Y轴显示不全
						containLabel: true
					}
				};
			
				this.myChart4.setOption(option);
				window.addEventListener('resize', () => {
					this.myChart4.resize();
					myChartBox.resize();
				});
			},
			
			beforeDestroy() {
				// 页面关闭时销毁echarts实例
				this.myChart1.clear();
				this.myChart1.dispose();
				this.myChart2.clear();
				this.myChart2.dispose();
				this.myChart3.clear();
				this.myChart3.dispose();
				this.myChart4.clear();
				this.myChart4.dispose();
			},
		}
	}
</script>

<style scope lang="scss">
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
	
			#myEchartsA {
				width: 100%;
				height: 300px;
				position: absolute;
				left: 0px;
				top: 100px;
			}
			
			#myEchartsB {
				width: 100%;
				height: 300px;
				position: absolute;
				left: 0px;
				top: 350px;
			}
			
			#myEchartsC {
				width: 100%;
				height: 300px;
				position: absolute;
				left: 0px;
				top: 600px;
			}
			
			#myEchartsD {
				width: 100%;
				height: 300px;
				position: absolute;
				left: 0px;
				top: 850px;
			}
		}
	}
	
	.image {
		width: 25px;
		height: 25px;
	}
	
	.text {
		font-size: 16px;
		margin-top: 5px;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
</style>