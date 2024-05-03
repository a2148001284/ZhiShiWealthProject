<template>
	<view>
		<view class="login" v-show="isAuthorization">
			<view class="uni-common-mt">
				<view class="uni-form-item uni-column">
					<view class="login_label">登录</view>
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
					<view @click.native="gotable()">
						<u style="color:blue">没有账号？注册一个</u>
					</view>
					<!-- 点击注册 -->
				</view>
				<view class="uni-form-item uni-column">
					<view>
						<button type="primary" @click="user_login">登录</button>
						<!--登录按钮，调用user_login函数-->
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
				"messages": [],
				"base_url": this.$BASE_URL_MEDIA,

			}
		},
		async onLoad() {
			var access = uni.getStorageSync("Access_token")
			if (access) {
				this.isAuthorization = false;
				const response_message = await this.$myRequest({
					url: '/rest-auth/devholder/holddevmessages/',
					method: 'GET',
				})
				if (response_message.statusCode === 200) {
					this.messages = response_message.data;
				}
			} else {
				this.isAuthorization = true;

			}
		},
		filters: {
			formatDate: function(val) {
				var value = new Date(val);
				//var year=value.getFullYear();
				var month = padDate(value.getMonth() + 1);
				var day = padDate(value.getDate());
				var hour = padDate(value.getHours());
				var minutes = padDate(value.getMinutes());
				//var seconds=padDate(value.getSeconds());
				return month + '-' + day + ' ' + hour + ':' + minutes;
				//return year+'-'+month+'-'+day+' '+hour+':'+minutes+':'+seconds;

			}
		},
		methods: {
			gotable(){
				uni.navigateTo({
					url:"/pages/Page4/Register/Register"
				})
			},
			
			async user_login() {
				await uni.request({
					url: this.$BASE_URL + '/token-api/token/',
					//url:'https://www.yingcloud.com:8000/token-api/token/',
					method: 'POST',
					data: {
						'username': this.username,
						'password': this.password
					},
					success: async (res) => {
						if (res.statusCode !== 200 && res.statusCode !== 201) {
							return uni.showToast({
								title: "请求失败"
							})
						} else {
							this.isAuthorization = false
							console.log(res.data)
							uni.setStorageSync("Access_token", res.data.access)
							const response_message = await this.$myRequest({
								url: '/rest-auth/devholder/holddevmessages/',
								method: 'GET',
							})
							if (response_message.statusCode === 200) {
								console.log(response_message.data)
								this.messages = response_message.data;
							}
						}
					},
					fail: (err) => {
						return uni.showToast({
							title: "账号或密码错误"
						})
					},
				})
			}

		}
	}
</script>

<style lang="scss">
	//<!--样式-->
	.login {
		//<!--类>
		padding: 20rpx 40rpx; //整个类
		margin: 200rpx 20rpx;
		text-align: center; //整个居中
		background-color: #eee;

		.login_label {
			color: #333333;
			margin: 0 auto;
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

		button {
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
