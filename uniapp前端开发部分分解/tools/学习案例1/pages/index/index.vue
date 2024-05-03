<template>

	<div class="main">
		<!-- 不加这个根标签会报下面这个错，template下面只能有一个根标签。说人话就是，所有的盒子，都要由一个不是template的标签包裹。 -->
		<!-- Component template should contain exactly one root element. If you are using v-if on multiple elements, use v-else-if to chain them instead. -->
		<!-- H5的table标签是一行一行渲染的，因此我决定自己写一个按列渲染的 -->
		<view class="table">
			<view class="tableHead"> 题目：股票交易数据 </view>
			<view class="tableBody">
				<!-- 渲染列 -->
				<view class="tableColumn" v-for="(item,index) in TableData" :key='index'>
					<view class="tableColHead" @click="delCol(index,$event)">{{index}}:{{item.TableHead}}</view>
					<view class="tableColItem " v-for="(it,i) in item.TableValue">
						{{i}}：{{it}}
					</view>
				</view>
			</view>
		</view>
		<!-- 上面的方法适合和后端配套，如果用户想要看完整数据，就get请求去后端拿一下数据-->

		<!-- 上面实验过后发现属性无法恢复，因此重新写一个 -->
		<view class="table">
			<view class="tableHead"> 题目：股票交易数据 </view>
			<view class="tableBody">
				<!-- 渲染列 -->
				<!-- 这里除了使用 v-show 还可以使用 v-bind 增加标签的class。v-bind有两种写法，如下-->
				<!-- 这里注意 如果class里面还有其他不需要v-bind绑定的class，需要分开写class=  :class= -->
				<!-- <view class="tableColumn" v-bind:class="TableData[index].Flag_Show==true ? '':'hidden'"
					v-for="(item,index) in TableData" :key='index'> -->
				<!-- <view class="tableColumn" :class="{hidden:!TableData[index].Flag_Show}" v-for="(item,index) in TableData"
					:key='index'> -->
				<view class="tableColumn" v-for="(item,index) in TableData" :key='index'
					v-show="TableData[index].Flag_Show">
					<view class="tableColHead" @click="addhidden(index)">{{index}}:{{item.TableHead}}</view>
					<view class="tableColItem " v-for="(it,i) in item.TableValue">
						{{i}}：{{it}}
					</view>
				</view>
			</view>
		</view>
		
		
		
		
		
		
		
		<!-- 开始 下拉框-->
		<!-- 用于选择上述哪些列恢复 -->
		<view class='scroll-view'>
			<view class='scroll-left'>选择要显示的股票属性</view>
		
			<!-- 下拉框 -->
			<view class='scroll-right' @click='showScrollview()'>
				<!-- 默认显示第一个属性的名字 -->
				<text>{{TableData[0].TableHead}}</text> 
				<!-- 搞一个箭头图片，或者搞 阿里icon -->
				<image src='../../static/down_arrow.jpg'></image>
			</view>
		
			<!-- 下拉显示的列表 -->
			<view class="scroll-list" v-show="Flag_srcoll_view_selected">
				<view v-for="(item,index) in TableData" :key="index">
					<view class="scroll-item" @click="changeFlag_Show(index)">{{item.TableHead}}</view>
				</view>
			</view>
		</view>
		<!-- 下拉框结束 -->
		
	</div>

	

</template>

<script>
// 暂时不会js与vue分离
export default {
	data() {
				return {
					Flag_srcoll_view_selected : false, //为true的时候下拉框展开
					TableData: [ //里面的每一个对象代表一个列，两个属性，一个是string类型的列名，一个是数组属性的列数据
						//第一列
						{
							TableHead: "股票名称",
							//  Vue 字符如果不加引号，不会报错。但是会有许多bug
							TableValue: ["茅台", "哔哩哔哩", "腾讯", "阿里", "特斯拉"],
							Flag_Show: true,
							id: 1
						},
	
						//第二列
						{
							TableHead: "股票单价",
							TableValue: [1, 2, 3, 4, 5],
							Flag_Show: true,
							id: 1
						},
	
	
						//第三列
						{
							TableHead: "股票涨幅",
							TableValue: [0.1, 0.2, 0.3, 0.44, 0.05],
							Flag_Show: true,
							id: 1
						},
						//第四列
						{
							TableHead: "股票颜色",
							TableValue: ["红", "橙", "黄", "绿", "青"],
							Flag_Show: true,
							id: 1
						},
						//第五列
						{
							TableHead: "买卖额度",
							TableValue: [5001, 2500, 643, 784, 9505],
							Flag_Show: true,
							id: 1
						},
						//第六列
						{
							TableHead: "企业评级",
							TableValue: [10, 4, 3, 4, 3],
							Flag_Show: true,
							id: 1
						}
					]
	
				}
			},
			methods: {
				delCol(index, e) {
					// 这样的方式使得不能恢复，所以我决定写一个可以恢复的，也就是使用display：none来实现。
					console.log('索引是', index)
					if (index != 0) {
						uni.showModal({
							title: "警告",
							content: '确定删除本列？',
							cancelText: "取消", // 取消按钮的文字  
							confirmText: "确认", // 确认按钮的文字  
							showCancel: true, // 是否显示取消按钮，默认为 true
							confirmColor: '#f55850',
							cancelColor: '#39B54A',
	
							success: (res) => {
								if (res.confirm) {
									this.TableData.splice(index, 1)
									console.log('comfirm' + e) //点击确定之后执行的代码
								} else {
									console.log('cancel') //点击取消之后执行的代码
								}
							}
						})
					} else {
						uni.showModal({
							title: "警告",
							content: '第一列不能删除！',
	
						})
					}
	
	
				},
				addhidden(index) {
					console.log("触发了addhidden", index, this.TableData)
					//将数据的Flag_Show属性改为false，这样就会把这个属性隐藏
					this.$data.TableData[index].Flag_Show = false;
				},
				changeFlag_Show(index){
					// 和addhidden刚好相反，显示某一个
					console.log("触发了changeFlag_Show", index)
					//将数据的Flag_Show属性改为 true，这样就会把这个属性重新显示出来
					this.$data.TableData[index].Flag_Show = true;
				},
				showScrollview(e){
					console.log("触发了showScrollview")
					// 把Flag_srcoll_view_selected属性改为true，这样就会把下拉框显示出来
					this.$data.Flag_srcoll_view_selected = !this.$data.Flag_srcoll_view_selected;
				},
			},
	
}
	
</script>

<style>
	@import url("@/pages/index/index.css");
</style>
