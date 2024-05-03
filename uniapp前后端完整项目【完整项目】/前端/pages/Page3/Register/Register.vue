
<template>
	<view>
		<view class="register" v-show="isAuthorization">
			<view class="uni-common-mt">
				<view class="uni-form-item uni-column">
					<view class="register_label">注册</view>
				</view>
				<view class="uni-form-item uni-column">
					<view>
						<input class="uni-input" v-model="username" focus placeholder="账号" />
						<!--账号输入框-->
					</view>
				</view>
				<view class="uni-form-item uni-column">
					<view>
						<input class="uni-input" v-model="password" hold-keyboard placeholder="密码" password=true>
						<!--密码输入框-->
					</view>
				</view>
				<view class="uni-form-item uni-column">
					<view>
						<input class="uni-input" v-model="password1" hold-keyboard placeholder="请再次输入密码" password=true>
						<!--确认密码输入框-->
					</view>
				</view>
				<view class="uni-form-item uni-column">
					<view>
						<button type="primary" @click="user_register">注册</button>
						<!--注册按钮-->
					</view>
				</view>
			</view>
		</view>
		<view class="message" v-show="!isAuthorization">
			<view class="message_item" v-for="(item,index) in messages" :key="index">
				<image :src="base_url+item.holddeviceimg"></image>
				<view class="right">
					<view class="message_content">
						{{item.message.slice(0,22)}}
					</view>
					<view class="message_channel">
						<view>
							<text class="device">{{item.holddevice}}</text> -{{item.datachannel}}
						</view>
						<view class="info">
							<text class="timestamp">{{item.timestamp | formatDate}}</text>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import axios from '../../../axios.min.js';
	function sleep(ms){
				return new Promise(resolve =>
				setTimeout(resolve,ms))
	};
	var padDate = function(va) {
		va = va < 10 ? '0' + va : va;
		return va
	}
	export default {
		data() {
			return {
				isAuthorization: true,
				username: '',
				password: '',
				password1:'',
				"messages": [],
				"base_url": this.$BASE_URL_MEDIA,

			}
		},
		methods:{
			user_register(){
			if(this.password!=this.password1){
			uni.showToast({
				title: "密码不一样"
			})
			return;
			}
            console.log(this.username)
			console.log(this.password)
				// axios({
				// 		// 请求方法
				// 		method: 'POST',
				// 		// 请求地址
				// 		url: 'http://127.0.0.1:8888/register',
				// 		// 请求头（其实如果是json格式可以不写headers）
				// 		headers: {
				// 			'content-type': 'application/json;'
				// 		},
				// 		// 请求体，注意这里直接使用JavaScript的对象即可
				// 		data: {
				// 			account: this.username,
				// 			password: this.password
				// 		}
				// 	})
				// 	// 接受响应并处理
				// 	.then((response) => {
				// 		// 注意，response中的data属性才是响应体内容
				// 		console.log(response.data);
				// 		if(response.data.code=='200'){
				// 			uni.showToast({
				// 				title: "注册成功"
				// 				});
								
				// 			localStorage.setItem(account,password);
				// 		}
				// 	})
				// 	// 发生异常时执行（非必须）
				// 	.catch((error) => {
				// 		// 注意，发生异常时，捕获error中的response属性的data才是响应体内容
				// 		console.log(error.response.data);
				// 	});
				var data = JSON.stringify({
				    "account": this.username,
				    "password": this.password
				});
				
				var config = {
				    method: 'post',
				    url: 'http://localhost:8888/register',
				    headers: {
				        'Content-Type': 'application/json'
				    },
				    data: data
				};
				
				axios(config)
				    .then(function (response) {
				        console.log(JSON.stringify(response.data));
						if(response.data.msg==="注册成功"){
							uni.showToast({
								title:'注册成功',
							});
							sleep(3000).then(()=>{
								uni.redirectTo({
									url:"/pages/Page3/Login/Login"
								})
							})
						}else{
							uni.showToast({
								title:'该账号已注册',
								
							})
						}
	
				    })
				    .catch(function (error) {
						uni.showToast({
							title:'Error',
						})
				        console.log(error);
				    });

			}
		}
		
	}
</script>

<style lang="scss">
	//<!--样式-->
	.register {
		//<!--类>
		padding: 20rpx 40rpx; //整个类
		margin: 200rpx 20rpx;
		text-align: center; //整个居中
		background-color: #eee;

		.register_label {
			color: #5500ff;
			margin: 0 auto;
			size:10px;
		}

		.uni-input {
			background-color: #fff;
			height: 80rpx;
			text-align: left;
			padding-left: 10rpx;
			border-radius: 10rpx;
			font-size: 30rpx;
		}

		.uni-form-item {
			//外部的边界
			margin: 30rpx 0;
		}

		.button {
			font-size: 30rpx;
			background-color: #0055ff;
		}
	}

	.message {
		.message_item {
			display: flex;
			padding: 15rpx 20rpx;
			border-bottom: 1px solid #eee;

			image {
				border-radius: 10rpx;
				width: 100rpx;
				min-width: 100rpx;
				max-width: 100rpx;
				height: 80rpx;
			}

			.right {
				margin-left: 15rpx;

				.message_content {
					font-size: 27rpx;
					color: #555555;
					margin-bottom: 10rpx;
				}

				.message_channel {
					display: flex;
					font-size: 25rpx;
					color: #999999;

					.info {
						float: left;
						margin-left: 30rpx;
						font-size: 25rpx;
						color: #999999;

					}

				}
			}
		}
	}
</style>
