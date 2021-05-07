<template>
	<view>
		<input type="number" placeholder="请输入手机号" v-model="phonenumber"/>
		<input type="password" v-model="password" placeholder="请输入密码"/>
		<button @click="submit('/pages/index/Login')">完成注册</button>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				phonenumber:"",
				password:''
			}
		},
		methods: {
			submit(url){
				uni.redirectTo({
					url:url+"?phonenumber="+this.phonenumber+"&password="+this.password
				});
			}
		}
	}
</script>

<style>

</style>
