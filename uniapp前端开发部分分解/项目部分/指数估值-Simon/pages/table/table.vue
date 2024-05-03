<template>
	<view class="container">
		<view class="inner-container">
			<view class="top">
				
				<scroll-view scroll-y="true" class="table-head" scroll-x="true"  scroll-left="200">
					<view class="uni-container">
						<uni-section>
							<view class="uni-padding-wrap uni-common-mt">
								<uni-segmented-control :current="current" :values="items" :style-type="styleType"
									:active-color="activeColor" @clickItem="onClickItem" />
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
										<uni-tr v-for="item in Data">
											<uni-td class='fixed1'>
												<view class="uni-group">    <!-- {item.type==1?primary:warn} -->
													<uni-fav :checked="false" class="favBtn" circle="true" bgColor="#dd524d" bg-color-checked="#007aff" fg-color="#ffffff" fg-color-checked="#ffffff" @click="favClick(2)"/>
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
									<uni-tr v-for="item in Data1">
										<uni-td class='fixed3'>
											<view class="uni-group">    <!-- {item.type==1?primary:warn} -->
												<uni-fav :checked="false" class="favBtn" circle="true" bgColor="#dd524d" bg-color-checked="#007aff" fg-color="#ffffff" fg-color-checked="#ffffff" @click="favClick(2)"/>
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
										<uni-tr v-for="item in Data2">
											<uni-td class='fixed3'>
												<view class="uni-group">    <!-- {item.type==1?primary:warn} -->
													<uni-fav :checked="false" class="favBtn" circle="true" bgColor="#dd524d" bg-color-checked="#007aff" fg-color="#ffffff" fg-color-checked="#ffffff" @click="favClick(2)"/>
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
									<uni-tr v-for="item in Data3">
										<uni-td class='fixed3'>
											<view class="uni-group">    <!-- {item.type==1?primary:warn} -->
												<uni-fav :checked="false" class="favBtn" circle="true" bgColor="#dd524d" bg-color-checked="#007aff" fg-color="#ffffff" fg-color-checked="#ffffff" @click="favClick(2)"/>
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
			current: 0
			}
		},
		onLoad() {
			
				//启动时从数据接口读取数据并且存入相应的数组中
				var url = 'http://www.zsxg.cn/api/v2/index/list?type=1';
				var url2 = 'http://www.zsxg.cn/api/v2/index/list?type=0';
				var url3 = 'http://www.zsxg.cn/api/v2/index/list?type=2';
				var url4 = 'http://www.zsxg.cn/api/v2/index/list?type=3';
				
				axios.get(url).then((response) => {
								var Datas = response.data.datas;
								this.Data=Datas;
								//console.log(this.Data);
								//console.log(this.Data[2].name);
								
								//console.log(Data);
								//console.log(Data[2].name);
								/*
								for(var i=0;i<=11;i++){
									this.Data[i].stock=response.data.datas[i].name;
									this.Data[i].price=response.data.datas[i].value;
									this.Data[i].pe=response.data.datas[i].pe;
									this.Data[i].pePre=response.data.datas[i].pePer;
									this.Data[i].pb=response.data.datas[i].pb;
									this.Data[i].pbPre=response.data.datas[i].pbPer;
									this.Data[i].ROE=response.data.datas[i].roe;
									this.Data[i].rate=Number((response.data.datas[i].guxilv)*100).toFixed(2);  //获取到的数据乘以100后取两位小数加上百分号  
									this.Data[i].month1=Number((response.data.datas[i].one_mouth_close_rate)*100).toFixed(2);
									this.Data[i].month3=Number((response.data.datas[i].three_mouth_close_rate)*100).toFixed(2);
									this.Data[i].month6=Number((response.data.datas[i].six_mouth_close_rate)*100).toFixed(2);
									this.Data[i].year1=Number((response.data.datas[i].one_year_close_rate)*100).toFixed(2);
									this.Data[i].id=response.data.datas[i].codePre;
								}*/
						})
							.catch(function (error) {
							console.log(error);
						});
				axios.get(url2).then((response) => {
								var Datas = response.data.datas;
								this.Data1 = Datas;
						})
							.catch(function (error) {
							console.log(error);
						});
				
				axios.get(url3).then((response) => {
								var Datas = response.data.datas;
								this.Data2 = Datas;
						})
							.catch(function (error) {
							console.log(error);
						});
				
				axios.get(url4).then((response) => {
								var Datas = response.data.datas;
								this.Data3 = Datas;
						})
							.catch(function (error) {
							console.log(error);
						});
				
			
		},

				
				
		methods: {
			onClickItem(e) {
							if (this.current !== e.currentIndex) {
								this.current = e.currentIndex
							}
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
			}

			//一级行业对应的需要使用到的方法
			
		}
	}
</script>

<style>
	@import url("@/pages/table/table1.css");
	@import url("@/pages/table/table2.css");
</style>
