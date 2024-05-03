<template>
	<view class="container">
		<view class="inner-container">
			<view class="top">
				
				<scroll-view scroll-y="true" class="table-head" scroll-x="true"  scroll-left="200">
					<view class="uni-container">
							<view class="content">
									<uni-table class='table_style' ref="table" :loading="loading" border stripe  emptyText="暂无更多数据" @selection-change="selectionChange">
										<uni-tr>
											<uni-th width="90" align="center" class='fixed1'>操作</uni-th>
											<uni-th width="80" align="center" class='fixed2'>股票</uni-th>
											<uni-th width="100" align="center">
												最新收盘价
												<view class="topIcon" @click="priceRev(1)"></view>
												<view class="botIcon" @click="priceSort(1)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												PE
												<view class="topIcon" @click="peRev(1)"></view>
												<view class="botIcon" @click="peSort(1)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												PB
												<view class="topIcon" @click="pbRev(1)"></view>
												<view class="botIcon" @click="pbSort(1)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												近1月
												<view class="topIcon" @click="month1Rev(1)"></view>
												<view class="botIcon" @click="month1Sort(1)"></view>
											</uni-th>
											
										</uni-tr>
										<uni-tr v-for="item in Data">
											<uni-td class='fixed1'>
												<view>
													<button class="button-biankuang" hover-class="bg-click" @click="deleteItem(item.codePre)">删除</button>
												</view>
											</uni-td>
											<uni-td align="center" class='fixed2'>
												{{item.name}}<view  style="margin-left: 1px;">{{item.codePre}}</view>
											</uni-td>
											<uni-td align="center" class='colorcontrol'><view class="name">{{item.value}}</view></uni-td>
											<uni-td align="center" class='colorcontrol'>{{item.pe}}</uni-td>
											<uni-td align="center" class='colorcontrol'>{{item.pb}}</uni-td>
											<uni-td class='colorcontrol' align="center">{{Number(item.one_mouth_close_rate * 100).toFixed(2)}}%</uni-td>  
											<uni-td>
												
											</uni-td>
										</uni-tr>
									</uni-table>
								
								<!-- 页码的部分 -->
								<!-- <view class="uni-pagination-box"><uni-pagination show-icon :page-size="pageSize" :current="pageCurrent" :total="total" @change="change" /></view> -->
							</view>
					</view>
					
				</scroll-view>
				
			</view>
		</view>
	</view>
</template>

<script>
	// import tableData from './tableData.js'
	
	import axios from '../../axios.min.js';

	export default {
		data() {
			return {
			Data: [{name:"aaa",codePre:"254",value:"8888",pe:"33",pb:"25",one_mouth_close_rate:"0.095"},{name:"aaa",codePre:"254",value:"8888",pe:"33",pb:"25",one_mouth_close_rate:"0.095"}],   //数据库中的数据，读入后用于显示到主界面上
			
			}
			
		},
		onLoad() {
			
		},

				
				
		methods: {
			//用于根据相应的股票编号触发相应的delete的SQL语句
			deleteItem(x){
				/* function delete  用于后端交互
				select * from xxx where codePre = x ;
				then: refresh 
				刷新界面即可  
				*/
			},
	
	
			//沪深界面对应的需要使用到的方法
			priceRev(x){
				if(x==1){
					this.Data.sort(function(a,b){return b.value-a.value});
				}else if(x==2){
					this.Data1.sort(function(a,b){return b.value-a.value});
				}else if(x==3){
					this.Data2.sort(function(a,b){return b.value-a.value});
				}else if(x==4){
					this.Data3.sort(function(a,b){return b.value-a.value});
				}
				
			},
			priceSort(x){
				if(x==1){
					this.Data.sort(function(a,b){return a.value-b.value});
				}else if(x==2){
					this.Data1.sort(function(a,b){return a.value-b.value});
				}else if(x==3){
					this.Data2.sort(function(a,b){return a.value-b.value});
				}else if(x==4){
					this.Data3.sort(function(a,b){return a.value-b.value});
				}
				
			},
			peRev(x){
				if(x==1){
					this.Data.sort(function(a,b){return b.pe-a.pe});
				}else if(x==2){
					this.Data1.sort(function(a,b){return b.pe-a.pe});
				}else if(x==3){
					this.Data2.sort(function(a,b){return b.pe-a.pe});
				}else if(x==4){
					this.Data3.sort(function(a,b){return b.pe-a.pe});
				}
				
			},
			peSort(x){
				if(x==1) this.Data.sort(function(a,b){return a.pe-b.pe});
				else if(x==2) this.Data1.sort(function(a,b){return a.pe-b.pe});
				else if(x==3) this.Data2.sort(function(a,b){return a.pe-b.pe});
				else if(x==4) this.Data3.sort(function(a,b){return a.pe-b.pe});
				
			},
			
			pbRev(x){
				if(x==1) this.Data.sort(function(a,b){return b.pb-a.pb});
				else if(x==2) this.Data1.sort(function(a,b){return b.pb-a.pb});
				else if(x==3) this.Data2.sort(function(a,b){return b.pb-a.pb});
				else if(x==4) this.Data3.sort(function(a,b){return b.pb-a.pb});
			},
			pbSort(x){
				if(x==1) this.Data.sort(function(a,b){return a.pb-b.pb});
				else if(x==2) this.Data1.sort(function(a,b){return a.pb-b.pb});
				else if(x==3) this.Data2.sort(function(a,b){return a.pb-b.pb});
				else if(x==4) this.Data3.sort(function(a,b){return a.pb-b.pb});
			},
			
			month1Rev(x){
				if(x==1) this.Data.sort(function(a,b){return b.one_mouth_close_rate-a.one_mouth_close_rate});
				else if(x==2) this.Data1.sort(function(a,b){return b.one_mouth_close_rate-a.one_mouth_close_rate});
				else if(x==3) this.Data2.sort(function(a,b){return b.one_mouth_close_rate-a.one_mouth_close_rate});
				else if(x==4) this.Data3.sort(function(a,b){return b.one_mouth_close_rate-a.one_mouth_close_rate});
			},
			month1Sort(x){
				if(x==1) this.Data.sort(function(a,b){return a.one_mouth_close_rate-b.one_mouth_close_rate});
				else if(x==2) this.Data1.sort(function(a,b){return a.one_mouth_close_rate-b.one_mouth_close_rate});
				else if(x==3) this.Data2.sort(function(a,b){return a.one_mouth_close_rate-b.one_mouth_close_rate});
				else if(x==4) this.Data3.sort(function(a,b){return a.one_mouth_close_rate-b.one_mouth_close_rate});
			}


			
		}
	}
</script>

<style>
	@import url("@/pages/table/table1.css");
	@import url("@/pages/table/table2.css");
</style>
