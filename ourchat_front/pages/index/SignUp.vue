<template>
	<view>
		<uploadImage></uploadImage>
		<!-- <input type="number" placeholder="请输入手机号" v-model="phonenumber"/>
		<input type="password" v-model="password" placeholder="请输入密码"/>
		<button @click="submit('/pages/index/Login')">完成注册</button> -->
		
	</view>
</template>

<script>
	import uploadImage from '../../components/uploadImage.vue'
	export default {
		components:{uploadImage},
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
