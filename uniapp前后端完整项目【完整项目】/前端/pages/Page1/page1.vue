<template>
	
	<view class="container">
		
		<!-- 涨跌统计部分 part1 -->
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
						
						<canvas id="myEcharts" width="1000" height="1000"></canvas>
					</view>
				</view>
			</uni-section> 
		</view>
		
		
		<!-- 北向资金部分 part2 -->
		<view>
			<uni-section title="北向资金" type="line">
				<view>
					<text class="textaa">
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
					<uni-segmented-control :current="current2" :values="items2" :style-type="styleType"
						:active-color="activeColor" @clickItem="onClickItem2" />
				</view>
		
				<view class="content">
					<view v-if="current2 === 0">
		
						<view class="dataTable2">
							<u-toast ref="uToast" />
							<view class="dataTable2-canvas">
								<canvas id="myEcharts2"></canvas>
							</view>
						</view>
					</view>
		
					<view v-if="current2 === 1">
		
						<view class="dataTable2">
							<u-toast ref="uToast" />
							<view class="dataTable2-canvas">
								<canvas id="myEcharts2"></canvas>
							</view>
						</view>
					</view>
		
					<view v-if="current2 === 2">
		
						<view class="dataTable2">
							<u-toast ref="uToast" />
							<view class="dataTable2-canvas">
								<canvas id="myEcharts2"></canvas>
							</view>
						</view>
					</view>
				</view>
		
			</uni-section>
		</view>
		
		
		<!-- 指数估值部分 part3 -->
		<view class="inner-container">
			
			<view class="top">
				
				<scroll-view scroll-y="true" class="table-head" scroll-x="true"  scroll-left="200">
					<view class="uni-container">
						<uni-section>
							<view class="uni-padding-wrap uni-common-mt">
								<uni-segmented-control :current="current" :values="items" :style-type="styleType"
									:active-color="activeColor" @clickItem="onClickItem3" />
							</view>
							<view class="content">
								
								<!-- page1 ********************************** -->
								<view v-if="current === 0"><text class="content-text">
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
											<uni-th width="100" align="center">
												PE分位
												<view class="topIcon" @click="pePreRev(1)"></view>
												<view class="botIcon" @click="pePreSort(1)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												PB
												<view class="topIcon" @click="pbRev(1)"></view>
												<view class="botIcon" @click="pbSort(1)"></view>
											</uni-th>
											<uni-th width="100" align="center">
												PB分位
												<view class="topIcon" @click="pbPreRev(1)"></view>
												<view class="botIcon" @click="pbPreSort(1)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												ROE
												<view class="topIcon" @click="ROERev(1)"></view>
												<view class="botIcon" @click="ROESort(1)"></view>
											</uni-th>
											<uni-th width="100" align="center">
												股票率
												<view class="topIcon" @click="rateRev(1)"></view>
												<view class="botIcon" @click="rateSort(1)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												近1月
												<view class="topIcon" @click="month1Rev(1)"></view>
												<view class="botIcon" @click="month1Sort(1)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												近3月
												<view class="topIcon" @click="month3Rev(1)"></view>
												<view class="botIcon" @click="month3Sort(1)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												近6月
												<view class="topIcon" @click="month6Rev(1)"></view>
												<view class="botIcon" @click="month6Sort(1)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												近1年
												<view class="topIcon" @click="year1Rev(1)"></view>
												<view class="botIcon" @click="year1Sort(1)"></view>
											</uni-th>
											
										</uni-tr>
										<uni-tr v-for="(item,index) in Data" :key="index">
											<uni-td class='fixed1'>
												<view class="uni-group">    <!-- {item.type==1?primary:warn} -->
													<uni-fav :checked="false" class="favBtn" circle="true" bgColor="#dd524d" bg-color-checked="#007aff" fg-color="#ffffff" fg-color-checked="#ffffff" @click="favClick1(index)"/>
												</view>
											</uni-td>
											<uni-td align="center" class='fixed2'>
												{{item.name}}<view  style="margin-left: 1px;">{{item.codePre}}</view>
											</uni-td>
											<uni-td align="center" class='colorcontrol'><view class="name">{{item.value}}</view></uni-td>
											<uni-td align="center" class='colorcontrol'>{{item.pe}}</uni-td>
											<uni-td align="center" class='colorcontrol'>{{Number(item.pePer * 100).toFixed(2)}}%</uni-td>
											<uni-td align="center" class='colorcontrol'>{{item.pb}}</uni-td>
											<uni-td align="center" class='colorcontrol'>{{Number(item.pbPer * 100).toFixed(2)}}%</uni-td>
											<uni-td align="center" class='colorcontrol'>{{Number(item.roe * 100).toFixed(2)}}%</uni-td>
											<uni-td align="center" class='colorcontrol'>{{Number(item.guxilv * 100).toFixed(2)}}%</uni-td> 
											<!-- Number((response.data.datas[i].guxilv)*100).toFixed(2) -->
											<uni-td class='colorcontrol' align="center">{{Number(item.one_mouth_close_rate * 100).toFixed(2)}}%</uni-td>  
											<uni-td class='colorcontrol' align="center">{{Number(item.three_mouth_close_rate * 100).toFixed(2)}}%</uni-td>
											<uni-td class='colorcontrol' align="center">{{Number(item.six_mouth_close_rate * 100).toFixed(2)}}%</uni-td>
											<uni-td class='colorcontrol' align="center">{{Number(item.one_year_close_rate * 100).toFixed(2)}}%</uni-td>
											<uni-td>
												
											</uni-td>
										</uni-tr>
									</uni-table>
								</text></view>
								
								<!-- page2 ********************************** -->
								<view v-if="current === 1"><text class="content-text">
									<uni-table class='table_style' ref="table" :loading="loading" border stripe  emptyText="暂无更多数据" @selection-change="selectionChange">
									<uni-tr>
										<uni-th width="90" align="center" class='fixed3'>操作</uni-th>
										<uni-th width="80" align="center" class='fixed4'>股票</uni-th>
										<uni-th width="100" align="center">
											最新收盘价
											<view class="topIcon" @click="priceRev(2)"></view>
											<view class="botIcon" @click="priceSort(2)"></view>
											
										</uni-th>
										<uni-th width="90" align="center">
											PE
											<view class="topIcon" @click="peRev(2)"></view>
											<view class="botIcon" @click="peSort(2)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											PE分位
											<view class="topIcon" @click="pePreRev(2)"></view>
											<view class="botIcon" @click="pePreSort(2)"></view>
										</uni-th>
										<uni-th width="90" align="center">
											PB
											<view class="topIcon" @click="pbRev(2)"></view>
											<view class="botIcon" @click="pbSort(2)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											PB分位
											<view class="topIcon" @click="pbPreRev(2)"></view>
											<view class="botIcon" @click="pbPreSort(2)"></view>
										</uni-th>
										<uni-th width="90" align="center">
											ROE
											<view class="topIcon" @click="ROERev(2)"></view>
											<view class="botIcon" @click="ROESort(2)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											股票率
											<view class="topIcon" @click="rateRev(2)"></view>
											<view class="botIcon" @click="rateSort(2)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											RPS10
											<view class="topIcon" @click="rps10Rev(2)"></view>
											<view class="botIcon" @click="rps10Sort(2)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											RPS20
											<view class="topIcon" @click="rps20Rev(2)"></view>
											<view class="botIcon" @click="rps20Sort(2)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											RPS60
											<view class="topIcon" @click="rps60Rev(2)"></view>
											<view class="botIcon" @click="rps60Sort(2)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											RPS120
											<view class="topIcon" @click="rps120Rev(2)"></view>
											<view class="botIcon" @click="rps120Sort(2)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											RPS250
											<view class="topIcon" @click="rps250Rev(2)"></view>
											<view class="botIcon" @click="rps250Sort(2)"></view>
										</uni-th>
										<uni-th width="90" align="center">
											近1月
											<view class="topIcon" @click="month1Rev(2)"></view>
											<view class="botIcon" @click="month1Sort(2)"></view>
										</uni-th>
										<uni-th width="90" align="center">
											近3月
											<view class="topIcon" @click="month3Rev(2)"></view>
											<view class="botIcon" @click="month3Sort(2)"></view>
										</uni-th>
										<uni-th width="90" align="center">
											近6月
											<view class="topIcon" @click="month6Rev(2)"></view>
											<view class="botIcon" @click="month6Sort(2)"></view>
										</uni-th>
										<uni-th width="90" align="center">
											近1年
											<view class="topIcon" @click="year1Rev(2)"></view>
											<view class="botIcon" @click="year1Sort(2)"></view>
										</uni-th>
										
									</uni-tr>
									<uni-tr v-for="(item,index) in Data1" :key="index">
										<uni-td class='fixed3'>
											<view class="uni-group">    <!-- {item.type==1?primary:warn} -->
												<uni-fav :checked="false" class="favBtn" circle="true" bgColor="#dd524d" bg-color-checked="#007aff" fg-color="#ffffff" fg-color-checked="#ffffff" @click="favClick2(index)"/>
											</view>
										</uni-td>
										<uni-td align="center" class='fixed4'>
											{{item.name}}<view  style="margin-left: 1px;">{{item.codePre}}</view>
										</uni-td>
										<uni-td align="center" class='colorcontrol'><view class="name">{{item.value}}</view></uni-td>
										<uni-td align="center" class='colorcontrol'>{{item.pe}}</uni-td>
										<uni-td align="center" class='colorcontrol'>{{Number(item.pePer * 100).toFixed(2)}}%</uni-td>
										<uni-td align="center" class='colorcontrol'>{{item.pb}}</uni-td>
										<uni-td align="center" class='colorcontrol'>{{Number(item.pbPer * 100).toFixed(2)}}%</uni-td>
										<uni-td align="center" class='colorcontrol'>{{Number(item.roe * 100).toFixed(2)}}%</uni-td>
										<uni-td align="center" class='colorcontrol'>{{Number(item.guxilv * 100).toFixed(2)}}%</uni-td> 
										<uni-td align="center" class='colorcontrol'>{{Number(item.rps10_today * 100).toFixed(2)}}%</uni-td> 
										<uni-td align="center" class='colorcontrol'>{{Number(item.rps20_today * 100).toFixed(2)}}%</uni-td> 
										<uni-td align="center" class='colorcontrol'>{{Number(item.rps60_today * 100).toFixed(2)}}%</uni-td> 
										<uni-td align="center" class='colorcontrol'>{{Number(item.rps120_today * 100).toFixed(2)}}%</uni-td> 
										<uni-td align="center" class='colorcontrol'>{{Number(item.rps250_today * 100).toFixed(2)}}%</uni-td> 
										<!-- Number((response.data.datas[i].guxilv)*100).toFixed(2) -->
										<uni-td class='colorcontrol' align="center">{{Number(item.one_mouth_close_rate * 100).toFixed(2)}}%</uni-td>  
										<uni-td class='colorcontrol' align="center">{{Number(item.three_mouth_close_rate * 100).toFixed(2)}}%</uni-td>
										<uni-td class='colorcontrol' align="center">{{Number(item.six_mouth_close_rate * 100).toFixed(2)}}%</uni-td>
										<uni-td class='colorcontrol' align="center">{{Number(item.one_year_close_rate * 100).toFixed(2)}}%</uni-td>
										<uni-td>
											
										</uni-td>
									</uni-tr>
									</uni-table>
								</text></view>
								
								<!-- page3 ********************************** -->
								<view v-if="current === 2"><text class="content-text">
										<uni-table class='table_style' ref="table" :loading="loading" border stripe  emptyText="暂无更多数据" @selection-change="selectionChange">
										<uni-tr>
											<uni-th width="90" align="center" class='fixed3'>操作</uni-th>
											<uni-th width="80" align="center" class='fixed4'>股票</uni-th>
											<uni-th width="100" align="center">
												最新收盘价
												<view class="topIcon" @click="priceRev(3)"></view>
												<view class="botIcon" @click="priceSort(3)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												PE
												<view class="topIcon" @click="peRev(3)"></view>
												<view class="botIcon" @click="peSort(3)"></view>
											</uni-th>
											<uni-th width="100" align="center">
												PE分位
												<view class="topIcon" @click="pePreRev(3)"></view>
												<view class="botIcon" @click="pePreSort(3)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												PB
												<view class="topIcon" @click="pbRev(3)"></view>
												<view class="botIcon" @click="pbSort(3)"></view>
											</uni-th>
											<uni-th width="100" align="center">
												PB分位
												<view class="topIcon" @click="pbPreRev(3)"></view>
												<view class="botIcon" @click="pbPreSort(3)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												ROE
												<view class="topIcon" @click="ROERev(3)"></view>
												<view class="botIcon" @click="ROESort(3)"></view>
											</uni-th>
											<uni-th width="100" align="center">
												股票率
												<view class="topIcon" @click="rateRev(3)"></view>
												<view class="botIcon" @click="rateSort(3)"></view>
											</uni-th>
											<uni-th width="100" align="center">
												RPS10
												<view class="topIcon" @click="rps10Rev(3)"></view>
												<view class="botIcon" @click="rps10Sort(3)"></view>
											</uni-th>
											<uni-th width="100" align="center">
												RPS20
												<view class="topIcon" @click="rps20Rev(3)"></view>
												<view class="botIcon" @click="rps20Sort(3)"></view>
											</uni-th>
											<uni-th width="100" align="center">
												RPS60
												<view class="topIcon" @click="rps60Rev(3)"></view>
												<view class="botIcon" @click="rps60Sort(3)"></view>
											</uni-th>
											<uni-th width="100" align="center">
												RPS120
												<view class="topIcon" @click="rps120Rev(3)"></view>
												<view class="botIcon" @click="rps120Sort(3)"></view>
											</uni-th>
											<uni-th width="100" align="center">
												RPS250
												<view class="topIcon" @click="rps250Rev(3)"></view>
												<view class="botIcon" @click="rps250Sort(3)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												近1月
												<view class="topIcon" @click="month1Rev(3)"></view>
												<view class="botIcon" @click="month1Sort(3)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												近3月
												<view class="topIcon" @click="month3Rev(3)"></view>
												<view class="botIcon" @click="month3Sort(3)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												近6月
												<view class="topIcon" @click="month6Rev(3)"></view>
												<view class="botIcon" @click="month6Sort(3)"></view>
											</uni-th>
											<uni-th width="90" align="center">
												近1年
												<view class="topIcon" @click="year1Rev(3)"></view>
												<view class="botIcon" @click="year1Sort(3)"></view>
											</uni-th>
											
										</uni-tr>
										<uni-tr v-for="(item,index) in Data2" :key="index">
											<uni-td class='fixed3'>
												<view class="uni-group">    <!-- {item.type==1?primary:warn} -->
													<uni-fav :checked="false" class="favBtn" circle="true" bgColor="#dd524d" bg-color-checked="#007aff" fg-color="#ffffff" fg-color-checked="#ffffff" @click="favClick3(index)"/>
												</view>
											</uni-td>
											<uni-td align="center" class='fixed4'>
												{{item.name}}<view  style="margin-left: 1px;">{{item.codePre}}</view>
											</uni-td>
											<uni-td align="center" class='colorcontrol'><view class="name">{{item.value}}</view></uni-td>
											<uni-td align="center" class='colorcontrol'>{{item.pe}}</uni-td>
											<uni-td align="center" class='colorcontrol'>{{Number(item.pePer * 100).toFixed(2)}}%</uni-td>
											<uni-td align="center" class='colorcontrol'>{{item.pb}}</uni-td>
											<uni-td align="center" class='colorcontrol'>{{Number(item.pbPer * 100).toFixed(2)}}%</uni-td>
											<uni-td align="center" class='colorcontrol'>{{Number(item.roe * 100).toFixed(2)}}%</uni-td>
											<uni-td align="center" class='colorcontrol'>{{Number(item.guxilv * 100).toFixed(2)}}%</uni-td> 
											<uni-td align="center" class='colorcontrol'>{{Number(item.rps10_today * 100).toFixed(2)}}%</uni-td> 
											<uni-td align="center" class='colorcontrol'>{{Number(item.rps20_today * 100).toFixed(2)}}%</uni-td> 
											<uni-td align="center" class='colorcontrol'>{{Number(item.rps60_today * 100).toFixed(2)}}%</uni-td> 
											<uni-td align="center" class='colorcontrol'>{{Number(item.rps120_today * 100).toFixed(2)}}%</uni-td> 
											<uni-td align="center" class='colorcontrol'>{{Number(item.rps250_today * 100).toFixed(2)}}%</uni-td> 
											<!-- Number((response.data.datas[i].guxilv)*100).toFixed(2) -->
											<uni-td class='colorcontrol' align="center">{{Number(item.one_mouth_close_rate * 100).toFixed(2)}}%</uni-td>  
											<uni-td class='colorcontrol' align="center">{{Number(item.three_mouth_close_rate * 100).toFixed(2)}}%</uni-td>
											<uni-td class='colorcontrol' align="center">{{Number(item.six_mouth_close_rate * 100).toFixed(2)}}%</uni-td>
											<uni-td class='colorcontrol' align="center">{{Number(item.one_year_close_rate * 100).toFixed(2)}}%</uni-td>
											<uni-td>
												
											</uni-td>
										</uni-tr>
										</uni-table>
								</text></view>
								
								<!-- page4 ********************************** -->
								<view v-if="current === 3"><text class="content-text">
									<uni-table class='table_style' ref="table" :loading="loading" border stripe  emptyText="暂无更多数据" @selection-change="selectionChange">
									<uni-tr>
										<uni-th width="90" align="center" class='fixed3'>操作</uni-th>
										<uni-th width="80" align="center" class='fixed4'>股票</uni-th>
										<uni-th width="100" align="center">
											最新收盘价
											<view class="topIcon" @click="priceRev(4)"></view>
											<view class="botIcon" @click="priceSort(4)"></view>
										</uni-th>
										<uni-th width="90" align="center">
											PE
											<view class="topIcon" @click="peRev(4)"></view>
											<view class="botIcon" @click="peSort(4)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											PE分位
											<view class="topIcon" @click="pePreRev(4)"></view>
											<view class="botIcon" @click="pePreSort(4)"></view>
										</uni-th>
										<uni-th width="90" align="center">
											PB
											<view class="topIcon" @click="pbRev(4)"></view>
											<view class="botIcon" @click="pbSort(4)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											PB分位
											<view class="topIcon" @click="pbPreRev(4)"></view>
											<view class="botIcon" @click="pbPreSort(4)"></view>
										</uni-th>
										<uni-th width="90" align="center">
											ROE
											<view class="topIcon" @click="ROERev(4)"></view>
											<view class="botIcon" @click="ROESort(4)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											股票率
											<view class="topIcon" @click="rateRev(4)"></view>
											<view class="botIcon" @click="rateSort(4)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											RPS10
											<view class="topIcon" @click="rps10Rev(4)"></view>
											<view class="botIcon" @click="rps10Sort(4)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											RPS20
											<view class="topIcon" @click="rps20Rev(4)"></view>
											<view class="botIcon" @click="rps20Sort(4)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											RPS60
											<view class="topIcon" @click="rps60Rev(4)"></view>
											<view class="botIcon" @click="rps60Sort(4)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											RPS120
											<view class="topIcon" @click="rps120Rev(4)"></view>
											<view class="botIcon" @click="rps120Sort(4)"></view>
										</uni-th>
										<uni-th width="100" align="center">
											RPS250
											<view class="topIcon" @click="rps250Rev(4)"></view>
											<view class="botIcon" @click="rps250Sort(4)"></view>
										</uni-th>
										<uni-th width="90" align="center">
											近1月
											<view class="topIcon" @click="month1Rev(4)"></view>
											<view class="botIcon" @click="month1Sort(4)"></view>
										</uni-th>
										<uni-th width="90" align="center">
											近3月
											<view class="topIcon" @click="month3Rev(4)"></view>
											<view class="botIcon" @click="month3Sort(4)"></view>
										</uni-th>
										<uni-th width="90" align="center">
											近6月
											<view class="topIcon" @click="month6Rev(4)"></view>
											<view class="botIcon" @click="month6Sort(4)"></view>
										</uni-th>
										<uni-th width="90" align="center">
											近1年
											<view class="topIcon" @click="year1Rev(4)"></view>
											<view class="botIcon" @click="year1Sort(4)"></view>
										</uni-th>
										
									</uni-tr>
									<uni-tr v-for="(item,index) in Data3" :key="index">
										<uni-td class='fixed3'>
											<view class="uni-group">    <!-- {item.type==1?primary:warn} -->
												<uni-fav :checked="false" class="favBtn" circle="true" bgColor="#dd524d" bg-color-checked="#007aff" fg-color="#ffffff" fg-color-checked="#ffffff" @click="favClick4(index)"/>
											</view>
										</uni-td>
										<uni-td align="center" class='fixed4'>
											{{item.name}}<view  style="margin-left: 1px;">{{item.codePre}}</view>
										</uni-td>
										<uni-td align="center" class='colorcontrol'><view class="name">{{item.value}}</view></uni-td>
										<uni-td align="center" class='colorcontrol'>{{item.pe}}</uni-td>
										<uni-td align="center" class='colorcontrol'>{{Number(item.pePer * 100).toFixed(2)}}%</uni-td>
										<uni-td align="center" class='colorcontrol'>{{item.pb}}</uni-td>
										<uni-td align="center" class='colorcontrol'>{{Number(item.pbPer * 100).toFixed(2)}}%</uni-td>
										<uni-td align="center" class='colorcontrol'>{{Number(item.roe * 100).toFixed(2)}}%</uni-td>
										<uni-td align="center" class='colorcontrol'>{{Number(item.guxilv * 100).toFixed(2)}}%</uni-td> 
										<uni-td align="center" class='colorcontrol'>{{Number(item.rps10_today * 100).toFixed(2)}}%</uni-td> 
										<uni-td align="center" class='colorcontrol'>{{Number(item.rps20_today * 100).toFixed(2)}}%</uni-td> 
										<uni-td align="center" class='colorcontrol'>{{Number(item.rps60_today * 100).toFixed(2)}}%</uni-td> 
										<uni-td align="center" class='colorcontrol'>{{Number(item.rps120_today * 100).toFixed(2)}}%</uni-td> 
										<uni-td align="center" class='colorcontrol'>{{Number(item.rps250_today * 100).toFixed(2)}}%</uni-td> 
										<!-- Number((response.data.datas[i].guxilv)*100).toFixed(2) -->
										<uni-td class='colorcontrol' align="center">{{Number(item.one_mouth_close_rate * 100).toFixed(2)}}%</uni-td>  
										<uni-td class='colorcontrol' align="center">{{Number(item.three_mouth_close_rate * 100).toFixed(2)}}%</uni-td>
										<uni-td class='colorcontrol' align="center">{{Number(item.six_mouth_close_rate * 100).toFixed(2)}}%</uni-td>
										<uni-td class='colorcontrol' align="center">{{Number(item.one_year_close_rate * 100).toFixed(2)}}%</uni-td>
										<uni-td>
											
										</uni-td>
									</uni-tr>
									</uni-table>
								</text></view>
							</view>
						</uni-section>
								
								<!-- 页码的部分 -->
								<!-- <view class="uni-pagination-box"><uni-pagination show-icon :page-size="pageSize" :current="pageCurrent" :total="total" @change="change" /></view> -->
					</view>
					
				</scroll-view>
				
			</view>
		</view>
	
	</view>
</template>

<script>
	// import tableData from './tableData.js'
	
	import axios from '../../axios.min.js';
	import * as echarts from './echarts.js';
	let datay = [];
	let datax = [];
	let SgtDatax = [];
	let SgtDatay = [];
	let HgtDatax = [];
	let HgtDatay = [];
	let k=0;

	export default {
		data() {
			//console.log("aaaaa");
			return {
			//judge:[false,false,false,false,false,false,false,false,false,false,false,false],
			Data: [],   //page1的数据
			Data1:[],   //page2的数据
			Data2:[],   //page3的数据
			Data3:[],	//page4的数据
			items: ['沪深', '一级行业', '二级行业','三级行业'],
			checkList: [false, false, false, false, false, false],
			current: 0,
			fall:0,
			rise:0,
			myChart: null,
			a: 0,
			h: 0,
			s: 0,
			myChart2: null,
			items2: ['深股通', '沪股通', '北向资金'],
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
			current2: 0,
			colorIndex: 0,
			activeColor: '#007aff',
			styleType: 'button'
			}
		},
		beforeDestroy() {
			// 页面关闭时销毁echarts实例
			this.myChart.clear();
			this.myChart.dispose();
			this.myChart2.clear();
			this.myChart2.dispose();
		},
		onLoad() {
			
				let that = this;
				// 通过nextTick异步画图
				this.$nextTick(() => {
					that.drawLines();
					that.drawLines2();
				});
				
				//启动时从数据接口读取数据并且存入相应的数组中
				var url = 'http://www.zsxg.cn/api/v2/index/list?type=1';
				var url2 = 'http://www.zsxg.cn/api/v2/index/list?type=0';
				var url3 = 'http://www.zsxg.cn/api/v2/index/list?type=2';
				var url4 = 'http://www.zsxg.cn/api/v2/index/list?type=3';
				
				axios.get(url).then((response) => {
								var Datas = response.data.datas;
								this.Data=Datas;
						})
							.catch(function (error) {
							//console.log(error);
						});
				axios.get(url2).then((response) => {
								var Datas = response.data.datas;
								this.Data1 = Datas;
						})
							.catch(function (error) {
							//console.log(error);
						});
				
				axios.get(url3).then((response) => {
								var Datas = response.data.datas;
								this.Data2 = Datas;
						})
							.catch(function (error) {
							//console.log(error);
						});
				
				axios.get(url4).then((response) => {
								var Datas = response.data.datas;
								this.Data3 = Datas;
						})
							.catch(function (error) {
							//console.log(error);
						});
				
			
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
							this.h = Datas.north_info.hgt;
							this.s = Datas.north_info.sgt;
							this.b = Datas.north_info.north;
						})
						.catch(function(error) {
							//console.log(error);
						});
					//console.log(SgtDatax);
				});
			
			},
			echartsClick(params) {
				console.log('点击数据', params)
			},
			onClickItem2(e) {
				this.myChart2.clear();
				this.myChart2.dispose();
				if (this.current2 !== e.currentIndex) {
					this.current2 = e.currentIndex
				}
				let that = this;
				// 通过nextTick异步画图
				this.$nextTick(() => {
					that.drawLines2();
				});
			},
			onClickItem3(e) {
							if (this.current !== e.currentIndex) {
								this.current = e.currentIndex
							}
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
									//console.log(5/2);
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
			async drawLines2() {
				this.myChart2 = echarts.init(document.getElementById('myEcharts2'));
				let Datas = await this.getDates();
				//console.log("--->"+Datas);
				SgtDatax = [];
				SgtDatay = [];
				for (var i = Datas.north_info.hgts.length - 1; i >= 0; i--) {
					Datas.north_info.sgts[i].x = this.timeStamp(Datas.north_info.sgts[i].x);
					Datas.north_info.hgts[i].x = this.timeStamp(Datas.north_info.hgts[i].x);
					if (this.current2 == 0) {
						//sgt
						SgtDatax.push(Datas.north_info.sgts[i].x);
						SgtDatay.push(Datas.north_info.sgts[i].y);
					} else if (this.current2 == 1) {
						//hgt
						//HgtDatax.push(Datas.north_info.hgts[i].x);
						//HgtDatay.push(Datas.north_info.hgts[i].y);
						SgtDatax.push(Datas.north_info.hgts[i].x);
						SgtDatay.push(Datas.north_info.hgts[i].y);
					} else if (this.current2 == 2) {
						SgtDatax.push(Datas.north_info.hgts[i].x);
						SgtDatay.push(Datas.north_info.hgts[i].y + Datas.north_info.sgts[i].y);
					}
			
			
				}
			
			
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
			
				this.myChart2.setOption(option);
				window.addEventListener('resize', () => {
					this.myChart2.resize();
					myChartBox.resize();
				});
			
				/* window.onresize = function() {
					myChart2.resize();
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
			},
			onClickItem1(e) {
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
			pePreRev(x){
				if (x==1) this.Data.sort(function(a,b){return b.pePer-a.pePer});
				else if(x==2) this.Data1.sort(function(a,b){return b.pePer-a.pePer});
				else if(x==3) this.Data2.sort(function(a,b){return b.pePer-a.pePer});
				else if(x==4) this.Data3.sort(function(a,b){return b.pePer-a.pePer});
			},
			pePreSort(x){
				if (x==1) this.Data.sort(function(a,b){return a.pePer-b.pePer});
				else if(x==2) this.Data1.sort(function(a,b){return a.pePer-b.pePer});
				else if(x==3) this.Data2.sort(function(a,b){return a.pePer-b.pePer});
				else if(x==4) this.Data3.sort(function(a,b){return a.pePer-b.pePer});
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
			pbPreRev(x){
				if(x==1) this.Data.sort(function(a,b){return b.pbPer-a.pbPer});
				else if(x==2) this.Data1.sort(function(a,b){return b.pbPer-a.pbPer});
				else if(x==3) this.Data2.sort(function(a,b){return b.pbPer-a.pbPer});
				else if(x==4) this.Data3.sort(function(a,b){return b.pbPer-a.pbPer});
			},
			pbPreSort(x){
				if(x==1) this.Data.sort(function(a,b){return a.pbPer-b.pbPer});
				else if(x==2) this.Data1.sort(function(a,b){return a.pbPer-b.pbPer});
				else if(x==3) this.Data2.sort(function(a,b){return a.pbPer-b.pbPer});
				else if(x==4) this.Data3.sort(function(a,b){return a.pbPer-b.pbPer});
			},
			ROERev(x){
				if(x==1) this.Data.sort(function(a,b){return b.roe-a.roe});
				else if(x==2) this.Data1.sort(function(a,b){return b.roe-a.roe});
				else if(x==3) this.Data2.sort(function(a,b){return b.roe-a.roe});
				else if(x==4) this.Data3.sort(function(a,b){return b.roe-a.roe});
			},
			ROESort(x){
				if(x==1) this.Data.sort(function(a,b){return a.roe-b.roe});
				else if(x==2) this.Data1.sort(function(a,b){return a.roe-b.roe});
				else if(x==3) this.Data2.sort(function(a,b){return a.roe-b.roe});
				else if(x==4) this.Data3.sort(function(a,b){return a.roe-b.roe});
			},
			rateRev(x){
				if(x==1) this.Data.sort(function(a,b){return b.guxilv-a.guxilv});
				else if(x==2) this.Data1.sort(function(a,b){return b.guxilv-a.guxilv});
				else if(x==3) this.Data2.sort(function(a,b){return b.guxilv-a.guxilv});
				else if(x==4) this.Data3.sort(function(a,b){return b.guxilv-a.guxilv});
			},
			rateSort(x){
				if(x==1) this.Data.sort(function(a,b){return a.guxilv-b.guxilv});
				else if(x==2) this.Data1.sort(function(a,b){return a.guxilv-b.guxilv});
				else if(x==3) this.Data2.sort(function(a,b){return a.guxilv-b.guxilv});
				else if(x==4) this.Data3.sort(function(a,b){return a.guxilv-b.guxilv});
			},
			
			rps10Rev(x){
				if(x==1) this.Data.sort(function(a,b){return b.rps10_today-a.rps10_today});
				else if(x==2) this.Data1.sort(function(a,b){return b.rps10_today-a.rps10_today});
				else if(x==3) this.Data2.sort(function(a,b){return b.rps10_today-a.rps10_today});
				else if(x==4) this.Data3.sort(function(a,b){return b.rps10_today-a.rps10_today});
			},
			rps10Sort(x){
				if(x==1) this.Data.sort(function(a,b){return a.rps10_today-b.rps10_today});
				else if(x==2) this.Data1.sort(function(a,b){return a.rps10_today-b.rps10_today});
				else if(x==3) this.Data2.sort(function(a,b){return a.rps10_today-b.rps10_today});
				else if(x==4) this.Data3.sort(function(a,b){return a.rps10_today-b.rps10_today});
			},
			
			rps20Rev(x){
				if(x==1) this.Data.sort(function(a,b){return b.rps20_today-a.rps20_today});
				else if(x==2) this.Data1.sort(function(a,b){return b.rps20_today-a.rps20_today});
				else if(x==3) this.Data2.sort(function(a,b){return b.rps20_today-a.rps20_today});
				else if(x==4) this.Data3.sort(function(a,b){return b.rps20_today-a.rps20_today});
			},
			rps20Sort(x){
				if(x==1) this.Data.sort(function(a,b){return a.rps20_today-b.rps20_today});
				else if(x==2) this.Data1.sort(function(a,b){return a.rps20_today-b.rps20_today});
				else if(x==3) this.Data2.sort(function(a,b){return a.rps20_today-b.rps20_today});
				else if(x==4) this.Data3.sort(function(a,b){return a.rps20_today-b.rps20_today});
			},
			
			rps60Rev(x){
				if(x==1) this.Data.sort(function(a,b){return b.rps60_today-a.rps60_today});
				else if(x==2) this.Data1.sort(function(a,b){return b.rps60_today-a.rps60_today});
				else if(x==3) this.Data2.sort(function(a,b){return b.rps60_today-a.rps60_today});
				else if(x==4) this.Data3.sort(function(a,b){return b.rps60_today-a.rps60_today});
			},
			rps60Sort(x){
				if(x==1) this.Data.sort(function(a,b){return a.rps60_today-b.rps60_today});
				else if(x==2) this.Data1.sort(function(a,b){return a.rps60_today-b.rps60_today});
				else if(x==3) this.Data2.sort(function(a,b){return a.rps60_today-b.rps60_today});
				else if(x==4) this.Data3.sort(function(a,b){return a.rps60_today-b.rps60_today});
			},
			
			rps120Rev(x){
				if(x==1) this.Data.sort(function(a,b){return b.rps120_today-a.rps120_today});
				else if(x==2) this.Data1.sort(function(a,b){return b.rps120_today-a.rps120_today});
				else if(x==3) this.Data2.sort(function(a,b){return b.rps120_today-a.rps120_today});
				else if(x==4) this.Data3.sort(function(a,b){return b.rps120_today-a.rps120_today});
			},
			rps120Sort(x){
				if(x==1) this.Data.sort(function(a,b){return a.rps120_today-b.rps120_today});
				else if(x==2) this.Data1.sort(function(a,b){return a.rps120_today-b.rps120_today});
				else if(x==3) this.Data2.sort(function(a,b){return a.rps120_today-b.rps120_today});
				else if(x==4) this.Data3.sort(function(a,b){return a.rps120_today-b.rps120_today});
			},
			
			rps250Rev(x){
				if(x==1) this.Data.sort(function(a,b){return b.rps250_today-a.rps250_today});
				else if(x==2) this.Data1.sort(function(a,b){return b.rps250_today-a.rps250_today});
				else if(x==3) this.Data2.sort(function(a,b){return b.rps250_today-a.rps250_today});
				else if(x==4) this.Data3.sort(function(a,b){return b.rps250_today-a.rps250_today});
			},
			rps250Sort(x){
				if(x==1) this.Data.sort(function(a,b){return a.rps250_today-b.rps250_today});
				else if(x==2) this.Data1.sort(function(a,b){return a.rps250_today-b.rps250_today});
				else if(x==3) this.Data2.sort(function(a,b){return a.rps250_today-b.rps250_today});
				else if(x==4) this.Data3.sort(function(a,b){return a.rps250_today-b.rps250_today});
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
			},
			month3Rev(x){
				if(x==1) this.Data.sort(function(a,b){return b.three_mouth_close_rate-a.three_mouth_close_rate});
				else if(x==2) this.Data1.sort(function(a,b){return b.three_mouth_close_rate-a.three_mouth_close_rate});
				else if(x==3) this.Data2.sort(function(a,b){return b.three_mouth_close_rate-a.three_mouth_close_rate});
				else if(x==4) this.Data3.sort(function(a,b){return b.three_mouth_close_rate-a.three_mouth_close_rate});
			},
			month3Sort(x){
				if(x==1) this.Data.sort(function(a,b){return a.three_mouth_close_rate-b.three_mouth_close_rate});
				else if(x==2) this.Data1.sort(function(a,b){return a.three_mouth_close_rate-b.three_mouth_close_rate});
				else if(x==3) this.Data2.sort(function(a,b){return a.three_mouth_close_rate-b.three_mouth_close_rate});
				else if(x==4) this.Data3.sort(function(a,b){return a.three_mouth_close_rate-b.three_mouth_close_rate});
			},
			month6Rev(x){
				if(x==1) this.Data.sort(function(a,b){return b.six_mouth_close_rate-a.six_mouth_close_rate});
				else if(x==2) this.Data1.sort(function(a,b){return b.six_mouth_close_rate-a.six_mouth_close_rate});
				else if(x==3) this.Data2.sort(function(a,b){return b.six_mouth_close_rate-a.six_mouth_close_rate});
				else if(x==4) this.Data3.sort(function(a,b){return b.six_mouth_close_rate-a.six_mouth_close_rate});
			},
			month6Sort(x){
				if(x==1) this.Data.sort(function(a,b){return a.six_mouth_close_rate-b.six_mouth_close_rate});
				else if(x==2) this.Data1.sort(function(a,b){return a.six_mouth_close_rate-b.six_mouth_close_rate});
				else if(x==3) this.Data2.sort(function(a,b){return a.six_mouth_close_rate-b.six_mouth_close_rate});
				else if(x==4) this.Data3.sort(function(a,b){return a.six_mouth_close_rate-b.six_mouth_close_rate});
			},
			year1Rev(x){
				if(x==1) this.Data.sort(function(a,b){return b.one_year_close_rate-a.one_year_close_rate});
				else if(x==2) this.Data1.sort(function(a,b){return b.one_year_close_rate-a.one_year_close_rate});
				else if(x==3) this.Data2.sort(function(a,b){return b.one_year_close_rate-a.one_year_close_rate});
				else if(x==4) this.Data3.sort(function(a,b){return b.one_year_close_rate-a.one_year_close_rate});
			},
			year1Sort(x){
				if(x==1) this.Data.sort(function(a,b){return a.one_year_close_rate-b.one_year_close_rate});
				else if(x==2) this.Data1.sort(function(a,b){return a.one_year_close_rate-b.one_year_close_rate});
				else if(x==3) this.Data2.sort(function(a,b){return a.one_year_close_rate-b.one_year_close_rate});
				else if(x==4) this.Data3.sort(function(a,b){return a.one_year_close_rate-b.one_year_close_rate});
			},
					
				
			//page1的收藏
			favClick1(index){
			console.log(index)
				console.log(this.Data[index])
				let arr = this.Data[index]
				
				var data = JSON.stringify({
				   "itemId": arr.codePre,
				    "userId": localStorage.getItem('account'),
				   "closingPrice":arr.value ,
				   "pe": arr.pe,
				   "pb": arr.pb,
				   "nearlyAMonth": arr.one_mouth_close_rate,
				   "itemName": arr.name
				});
				
				var config = {
				   method: 'post',
				   url: 'http://localhost:8888/addFavorite',
				   headers: { 
				      'Content-Type': 'application/json'
				   },
				   data : data
				};
				
				axios(config)
				.then(function (response) {
				   uni.showToast({
				   	title:'添加成功'
				   })
				})
				.catch(function (error) {
				   console.log(error);
				});
				},
				
	    //page2的收藏
		favClick2(index){
			console.log(index)
				console.log(this.Data1[index])
				let arr = this.Data1[index]
				var data = JSON.stringify({
				   "itemId": arr.codePre,
					"userId": localStorage.getItem('account'),
				   "closingPrice":arr.value ,
				   "pe": arr.pe,
				   "pb": arr.pb,
				   "nearlyAMonth": arr.one_mouth_close_rate,
				   "itemName": arr.name
				});
				
				var config = {
				   method: 'post',
				   url: 'http://localhost:8888/addFavorite',
				   headers: { 
				      'Content-Type': 'application/json'
				   },
				   data : data
				};
				
				axios(config)
				.then(function (response) {
				   uni.showToast({
				   	title:'添加成功'
				   })
				})
				.catch(function (error) {
				   console.log(error);
				});
			},
			
			//page3的收藏
			favClick3(index){
				console.log(index)
					console.log(this.Data2[index])
					let arr = this.Data2[index]
					var data = JSON.stringify({
					   "itemId": arr.codePre,
					   "userId": localStorage.getItem('account'),
					   "closingPrice":arr.value ,
					   "pe": arr.pe,
					   "pb": arr.pb,
					   "nearlyAMonth": arr.one_mouth_close_rate,
					   "itemName": arr.name
					});
					
					var config = {
					   method: 'post',
					   url: 'http://localhost:8888/addFavorite',
					   headers: { 
					      'Content-Type': 'application/json'
					   },
					   data : data
					};
					
					axios(config)
					.then(function (response) {
					   console.log(JSON.stringify(response.data));
					   uni.showToast({
					   	title:'添加成功'
					   })
					})
					.catch(function (error) {
					   console.log(error);
					});
				},
				
				
				//page4的收藏
				favClick4(index){
					console.log(index)
						console.log(this.Data3[index])
						let arr = this.Data3[index]
						var data = JSON.stringify({
						   "itemId": arr.codePre,
						   "userId": localStorage.getItem('account'),
						   "closingPrice":arr.value ,
						   "pe": arr.pe,
						   "pb": arr.pb,
						   "nearlyAMonth": arr.one_mouth_close_rate,
						   "itemName": arr.name
						});
						
						var config = {
						   method: 'post',
						   url: 'http://localhost:8888/addFavorite',
						   headers: { 
						      'Content-Type': 'application/json'
						   },
						   data : data
						};
						
						axios(config)
						.then(function (response) {
						   console.log(JSON.stringify(response.data));
						   uni.showToast({
						   	title:'添加成功'
						   })
						})
						.catch(function (error) {
						   console.log(error);
						});
					}
			
		}
	}
</script>

<style scope lang="scss">

	.dataTable2 {
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
	
			#myEcharts2 {
				width: 100%;
				height: 350px;
				position: absolute;
				left: 0px;
				top: 400px;
			}
		}
	}
	
	.dataTable {
		padding: 10px;
		width: 400px;
		height: 180px;
		
	
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
				height: 285px;
				position: absolute;
				left: 0px;
				top: 50px;
			}
		}
	}
	

	@import url("@/pages/Page1/table1.css");
	
</style>
