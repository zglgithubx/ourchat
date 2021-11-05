<template>
	<view>
		<u-search placeholder="请输入邮箱/昵称" v-model="keyword" :action-style="action" @search="find" @custom="find" margin="10px"></u-search>
		
	</view>
</template>

<script>
	export default {
			data() {
				return {
					value: '',
					type: 'text',
					border: true,
					keyword: '',
					action:{
						color:"white",
					},
					objectList:[]
					
				}
			},
			methods:{
				find(value){
					this.$api("user.searchFriend",{email:value}).then(res=>{
						console.log("res: ",res);
					})
				}
			}
		}
</script>

<style>
</style>
