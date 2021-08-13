<template>
	<view class="container">
		<view class="button-sp-area">
			<button type="primary" plain="true" @click="goto('/pages/index/Login')">登录</button>
		</view>
		<view class="button-sp-area">
			<button type="primary" plain="true"  @click="goto('/pages/index/SignUp')">注册</button>
		</view>
		<view class="button-sp-area">
			<button type="primary" plain="true"  @click="test()">测试</button>
		</view>
	</view>
</template>

<script>
	import store from '@/store/index'
	import {openSqlite,
	userInfoSQL,
	addUserInformation,
	selectInformationType,
	deleteInformationType,
	pullSQL,
	isOpen,
	closeSQL,
	modifyInformation} from '../../store/useSQLite.js'
	export default {
		data() {
			return {
			}
		},
		methods:{
			goto(url){
				uni.reLaunch({
					url:url
				})
			},
			test(){
				alert(store.state.websocketUrl)
			}
		}
	}
</script>

<style>
	.container {
		padding: 20px;
		font-size: 14px;
		line-height: 24px;
	}
</style>
