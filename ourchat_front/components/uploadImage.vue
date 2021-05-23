<template>
	<view>
		<view>上传图片{{imageList.length}}/{{count}}</view>
		<view class="uni-list list-pd">
			<view class="uni-uploader__files">
				<block v-for="(image,index) in imageList" :key="index">
					<view class="uni-uploader__file">
						<image class="uni-uploader__img" :src="image" :data-src="image" @tap="previewImage"></image>
					</view>
				</block>
				<view class="uni-uploader__input-box">
					<view class="uni-uploader__input" @tap="chooseImage"></view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name:"uploadImage",
		data() {
			return {
				imageList:[],
				count:3,//能上传的最大数量
			};
		},
		methods:{
			async chooseImage(){
				const _self=this;
				if(_self.imageList.length===_self.count){
					let isContinue=await _self.isFullImag();
					if(!isContinue){
						return;
					}
				}
			
				uni.chooseImage({
					sourceType:['camera','album'],
					sizeType:['original','compressed'],
					count:_self.count,
					success:(res)=>{
						const tempFilePaths=res.tempFilePaths;//获取到本地图片地址
						//上传图片
						const uploadTask=uni.uploadFile({
							url:'http://localhost:8013/123123',
							filePath:temFilePaths[0],
							name:'file',
							header:{
								'Authorization':token
							},
							formData:{
								'user':'test'
							},
							success:function(res){
								let toJsonRes=JSON.parse(res.data)//获取到后台处理过的地址
								let list=[]
								list.path("http://xxxx"+toJsonRes.data)
								_self.imageList=_self.imageList.concat(list);
							}
						})
					}
				})
			},
			isFullImg(){
				return new Promise((res)=>{
					uni.showModal({
						content: "已经有"+this.count+"张图片了,是否清空现有图片？",
						success: (e) => {
							if (e.confirm) {
								this.imageList = [];
							} else {
								
							}
						},
						fail: () => {
							res(false)
						}
						
					})
				})
			},
			previewImage(e){
				let current=e.target.dataset.src
				 uni.previewImage({
					 current:current,
					 urls:this.imageList
				 })
			},
			delImage(e){
				uni.showModal({
					title: '删除照片',
					content: '确定要删除这张照片吗？',
					cancelText: '取消',
					confirmText: '确定',
					success: res => {
						if (res.confirm) {
							this.imageList.splice(e.currentTarget.dataset.index, 1);
						}
					}
			
				})
			}
		}
	}
</script>

<style>

</style>
