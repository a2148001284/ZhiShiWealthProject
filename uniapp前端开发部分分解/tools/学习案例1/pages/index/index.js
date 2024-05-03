mybase = {
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
export default{
	mybase
}