<template>
	
	<view>
		<!-- <view>
		  <view class="status_bar">
			  这里是状态栏
		  </view>
		  <view> 状态栏下的文字 </view>
		</view> -->
		<div>
			<view class="uni-padding-wrap uni-common-mt" style="padding:20px">
				<view class="uni-form-item uni-column">
					<view class="title">收到的消息内容</view>
					<input class="uni-input" type="text" placeholder="xxx:xxxxx" :disabled="true" v-model="console" />
				</view>
				<br>
				<view class="uni-form-item uni-column">
					<view class="title">请输入朋友ID</view>
					<input class="uni-input" type="text" required placeholder="xxx" v-model="friendId" />
				</view>
				<br>
				<view class="uni-form-item uni-column">
					<view class="title">输入发送内容</view>
					<input class="uni-input" required placeholder="这个周日你有空吗" v-model="message" />
				</view>
				<br>
				<view class="button-sp-area">
					<button type="primary" plain="true" @click="sendMessage()">发送</button>
				</view>
				
				<view class="button-sp-area">
					<button type="primary" plain="true" @click="test()">测试</button>
				</view>
			</view>
		</div>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				myId:'',
				friendId:'',
				message:'',
				console:''
			}
		},
		onLoad(obj){
			this.$data.myId=obj.mobile;
			this.$Socket.nsend(this.$data.myId);
		},
		onShow(){
			this.onmessage();
		},
		methods: {
			onmessage(){
				this.$Socket.eventPatch.onMsg((msg,sk)=>{    //监听是否接受消息
					var backMessage=msg.data.split(":");
					this.console="来自你的好友:"+backMessage[0]+"，"+"发来的消息:"+backMessage[1];
				});
				
				
			},
			sendMessage(){
				var mess=this.myId+","+this.friendId+","+this.message;
				this.$Socket.nsend(mess);
				
			},
			test(){
				this.$api("user.test");
			}
		}
	}
</script>

<style>
.status_bar {
      height: var(--status-bar-height);
      width: 100%;
  }

</style>
