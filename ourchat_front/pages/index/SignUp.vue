<template>
	<view class="wrap">
		<u-form :model="model" :rules="rules" ref="uForm" :errorType="errorType">
			<uploadImage ref="photo"></uploadImage>
			<u-form-item :rightIconStyle="{color: '#0055ff', fontSize: '32rpx'}" right-icon="email-fill" :label-position="labelPosition" label="Eamil地址" prop="email" label-width="160" >
				<u-input :border="border" placeholder="请输入邮箱" v-model="model.email" type="email" placeholder-class="plcl"></u-input>
			</u-form-item>
			<u-form-item :label-position="labelPosition" label="密码" prop="password">
				<u-input :password-icon="true" :border="border" type="password" v-model="model.password" placeholder="请输入密码"></u-input>
			</u-form-item>
			<u-form-item :label-position="labelPosition" label="确认密码" label-width="150" prop="rePassword">
				<u-input :border="border" type="password" v-model="model.rePassword" placeholder="请确认密码"></u-input>
			</u-form-item>
			
			<u-form-item  :label-position="labelPosition" label="昵称" prop="name">
				<u-input :border="border" placeholder="请输入昵称" v-model="model.name" type="text"></u-input>
			</u-form-item>
			<u-form-item :label-position="labelPosition" label="性别" prop="sex">
				<u-input :border="border" type="select" :select-open="actionSheetShow" v-model="model.sex" placeholder="请选择性别" @click="actionSheetShow = true"></u-input>
			</u-form-item>
			<u-form-item :label-position="labelPosition" label="年龄" prop="age">
				<u-input type="number" :border="border" placeholder="请输入年龄" v-model="model.age" />
			</u-form-item>
			<u-form-item :label-position="labelPosition" label="所在地区" prop="region" label-width="150">
				<u-input :border="border" type="select" :select-open="pickerShow" v-model="model.region" placeholder="请选择地区" @click="pickerShow = true"></u-input>
			</u-form-item>
			<u-action-sheet :list="actionSheetList" v-model="actionSheetShow" @click="actionSheetCallback"></u-action-sheet>
			<u-picker mode="region" v-model="pickerShow" @confirm="regionConfirm"></u-picker>
			<u-button @click="submit('/pages/index/Login')" type="success" style="margin:40px 0">注册</u-button>
		</u-form>
	</view>
</template>

<script>
	import uploadImage from '../../components/uploadImage.vue'
	import api from '@/common/request/api'
	import store from '@/store/index'
	export default {
		components:{uploadImage},
		data() {
			return {
				model: {
					name: '',
					sex: '',
					age: '',
					region: '',
					email: '',
					password: '',
					rePassword: ''
				},
				rules: {
					name: [
						{
							required: true,
							message: '请输入昵称',
							trigger: 'blur' ,
						},
						{
							min: 1,
							max: 9,
							message: '昵称长度在1到9个字符',
							trigger: ['change','blur'],
						},
						{
							// 此为同步验证，可以直接返回true或者false，如果是异步验证，稍微不同，见下方说明
							validator: (rule, value, callback) => {
								// 调用uView自带的js验证规则，详见：https://www.uviewui.com/js/test.html
								return this.$u.test.chinese(value);
							},
							message: '昵称必须为中文',
							// 触发器可以同时用blur和change，二者之间用英文逗号隔开
							trigger: ['change','blur'],
						},
						// 异步验证，用途：比如用户注册时输入完账号，后端检查账号是否已存在
						// {
						// 	trigger: ['blur'],
						// 	// 异步验证需要通过调用callback()，并且在里面抛出new Error()
						// 	// 抛出的内容为需要提示的信息，和其他方式的message属性的提示一样
						// 	asyncValidator: (rule, value, callback) => {
						// 		this.$u.post('/ebapi/public_api/index').then(res => {
						// 			// 如果验证出错，需要在callback()抛出new Error('错误提示信息')
						// 			if(res.error) {
						// 				callback(new Error('姓名重复'));
						// 			} else {
						// 				// 如果没有错误，也要执行callback()回调
						// 				callback();
						// 			}
						// 		})
						// 	},
						// }
					],
					sex: [
						{
							required: true,
							message: '请选择性别',
							trigger: 'change'
						},
					],
					age: [
						{
							required: true,
							message: '请输入年龄'
						},
						{
							min: 1,
							max:3,
							message: '年龄少于3位数',
							trigger: 'change' ,
						},
						{
							// 此为同步验证，可以直接返回true或者false，如果是异步验证，稍微不同，见下方说明
							validator: (rule, value, callback) => {
								return this.$u.test.range(value,[12,120]);
							},
							message: '年龄必须合法',
							// 触发器可以同时用blur和change，二者之间用英文逗号隔开
							trigger: ['change','blur'],
		
						},
					],
					region: [
						{
							required: true,
							message: '请选择地区',
							trigger: 'change',
						}
					],
					email: [
						{
							required: true,
							message: '请输入邮箱',
							trigger: ['change','blur'],
						},
						{
							validator: (rule, value, callback) => {
								// 调用uView自带的js验证规则，详见：https://www.uviewui.com/js/test.html
								return this.$u.test.email(value);
							},
							message: '邮箱地址不正确',
							// 触发器可以同时用blur和change，二者之间用英文逗号隔开
							trigger: ['change','blur'],
						}
					],
					password: [
						{
							required: true,
							message: '请输入密码',
							trigger: ['change','blur'],
						},
						{
							// 正则不能含有两边的引号
							pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+\S{5,12}$/,
							message: '需同时含有字母和数字，长度在6-12之间',
							trigger: ['change','blur'],
						}
					],
					rePassword: [
						{
							required: true,
							message: '请重新输入密码',
							trigger: ['change','blur'],
						},
						{
							validator: (rule, value, callback) => {
								return value === this.model.password;
							},
							message: '两次输入的密码不相等',
							trigger: ['change','blur'],
						}
					],
				},
				border: false,
				actionSheetList: [
					{
						text: '男'
					},
					{
						text: '女'
					},
					{
						text: '保密'
					}
				],
				actionSheetShow: false,
				pickerShow: false,
				labelPosition: 'left',
				errorType: ['message']
			}
		},
		computed: {
			borderCurrent() {
				return this.border ? 0 : 1;
			}
		},
		onReady() {
				this.$refs.uForm.setRules(this.rules);
		},
		methods: {
			submit(url) {
				var that=this;
				console.log(this.$refs.photo,"文件参数")
				this.$refs.uForm.validate(valid => {
					if (valid) {
						const uploadTask=uni.uploadFile({
							url:store.state.serverUrl+api.user.signUp.url,
							filePath:this.$refs.photo.imageSrc,
							name:'file',
							header:{
							},
							formData:that.model,
							success:function(res){
								uni.redirectTo({
									url:url+"?phonenumber="+that.model.email+"&password="+that.model.password
								});
								console.log("res：",res)
								// that.imageSrc=res.data;
								console.log("上传成功")
								// let toJsonRes=JSON.parse(res.data)//获取到后台处理过的地址
								// let list=[]
								// list.path("http://xxxx"+toJsonRes.data)
								// that.imageList=that.imageList.concat(list);
							}
						})
					} else {
						console.log('验证失败');
					}
				});
			},
			// 点击actionSheet回调
			actionSheetCallback(index) {
				uni.hideKeyboard();
				this.model.sex = this.actionSheetList[index].text;
			},
			// 选择地区回调
			regionConfirm(e) {
				this.model.region = e.province.label + ' ' + e.city.label + ' ' + e.area.label;
			},
			errorChange(index) {
				if(index == 0) this.errorType = ['message'];
				if(index == 1) this.errorType = ['toast'];
				if(index == 2) this.errorType = ['border-bottom'];
				if(index == 3) this.errorType = ['border'];
			}
		}
	}
</script>

<style scoped lang="scss">
.wrap {
	padding: 30rpx;
}

.agreement {
	display: flex;
	align-items: center;
	margin: 40rpx 0;
	.agreement-text {
		padding-left: 8rpx;
		color: $u-tips-color;
	}
}
.u-form-item[data-v-5e7216f1]{
	color:$u-type-primary;
}
.wrap /deep/ .uni-input-input{
	color: $u-light-color;
}
</style>
