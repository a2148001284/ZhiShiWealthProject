
	
	
	function assessData(){
			var url = 'http://www.zsxg.cn/api/v2/index/northAndRfStat?container=pc';
				axios.get(url)
					.then((response) => {
										//console.log("bbbb");
						var Datas = response.data.datas;
						//console.log(Datas);
						var da = response.data.datas.north_info.sgts;
										//console.log(da);
						//console.log(da[0].y);
						//this.Data = Datas;
			
			
						for (var i = 0; i < Datas.north_info.hgts.length; i++) {
							SgtDatax.push(Datas.north_info.sgts[i].x);
							SgtDatay.push(Datas.north_info.sgts[i].y);
							HgtDatax.push(Datas.north_info.hgts[i].x);
							HgtDatay.push(Datas.north_info.hgts[i].y);
						}
										
						
						/* this.sgtDatax = SgtDatax;
						this.sgtDatay = SgtDatay;
						this.hgtDatax = HgtDatax;
						this.hgtDatay = HgtDatay; */
						
						/* this.option2.xAxis.data = [1, 2, 3, 4, 5, 6];
						this.option2.series.data = [2, 5, 13, 7, 46, 2];
						console.log('--->' + this.option2.xAxis.data);
						console.log('--->' + this.option2.series.data); */
					})
					.catch(function(error) {
						console.log(error);
					});	
			} 
