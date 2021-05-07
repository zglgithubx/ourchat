<template>
	<view>
		<div>
			<view class="uni-padding-wrap uni-common-mt" style="padding:20px">
				<view class="uni-form-item uni-column">
					<view class="title">请输入ID</view>
					<input class="uni-input" type="text" placeholder="请输入" v-model="phonenumber"/>
				</view>
				<br>
				<view class="uni-form-item uni-column">
					<view class="title">请输入密码</view>
					<input class="uni-input" type="text" placeholder="请输入" v-model="password"/>
				</view>
				<br>
				<view class="uni-form-item uni-column">
					<view class="title">请输入短信验证吗</view>
					<input class="uni-input" type="number" placeholder="xxxx" v-model="code" style="display:inline-block" />
					<button type="primary" plain="true" @click="sendCode()" size="mini" class="mini-btn" >发送验证码</button>
				</view>
				<br>
				<view class="button-sp-area">
					<button type="primary" plain="true"  @click="login('/pages/main/message/message')">登录</button>
				</view>
			</view>
		</div>
	</view>
</template>

<script>
	import {connectWebSocket} from "../../store/useSocket.js"; //引入socket.js 重要
	export default {
		onLoad: function (option) { //option为object类型，会序列化上个页面传递的参数
				this.phonenumber=option.phonenumber;
				this.password=option.password;
		},
		data() {
			return {
				phonenumber:'',
				password:'',
				code:'',
			}
		},
		methods: {
			sendCode(){
				var phone=this.phonenumber;
				this.$api("user.sendCode",{mobile:phone}).then(res=>{
					console.log("发送验证码成功");
				})
			},
			login(ur){
				var sms=this.code;
				var mobile=this.phonenumber;
				var password=this.password;
				this.$api("user.login",{smsCode:sms,mobile:mobile,password:password}).then(res=>{
					if(res.content==undefined){
						connectWebSocket()
						this.$Socket.eventPatch.onOpen((msg,sk)=>{
							uni.reLaunch({
								url:ur+"?mobile="+mobile
							})
							// this.$Socket.nsend(mobile);
						})
						
					}
				})
			}
		}
	}
</script>

<style>

</style>
