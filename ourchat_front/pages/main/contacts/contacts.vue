<template>
	<view>
		<div class="down">
		  <ul>
			<li v-for="re in res" @key="re.id">
				<img v-bind:src="re.headPortrait">
				<p>{{re.remarks}}</p>
			</li>
		  </ul>
		</div>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				res:[
					{
						id:1,
						headPortrait:"../../../static/c1.png",
						remarks:"赵恩东"
					},
					{
						id:2,
						headPortrait:"../../../static/c2.png",
						remarks:"小芳"
					},
					{
						id:3,
						headPortrait:"../../../static/c3.png",
						remarks:"小浩"
					},
					{
						id:4,
						headPortrait:"../../../static/c4.png",
						remarks:"小红"
					},
					{
						id:5,
						headPortrait:"../../../static/c5.png",
						remarks:"小窦"
					},
					{
						id:6,
						headPortrait:"../../../static/c6.png",
						remarks:"小琦"
					}
				]
			}
		},
		methods: {
			
		}
	}
</script>

<style scoped>
.down{
  border-top:1px solid #e5e5e5;
  height:auto;
  width:auto;
  margin:15px 0 0 0;
  overflow:auto;
  background-color: #000000;
}
/* 滚动条高度 */
.down::-webkit-scrollbar{
  width:2px;
  height: 2px;
}
/* 滑块 */
.down::-webkit-scrollbar-thumb{
  border-radius: 2px;
  box-shadow:inset 0 0 5px rgba(0,0,0,0.2);
  background: rgba(0,0,0,0.1);
}
/* 轨道 */
.down::-webkit-scrollbar-track{
   box-shadow:inset 0 0 5px rgba(0,0,0,0.2);
   border-radius: 0;
   background: rgba(0,0,0,0.1);
}
.down>ul>li{
  width:auto;
  height: 60px;
  border-bottom:1px solid #e5e5e5;
  list-style: none;
}
li>img{
  height:60px;
  background-color: #ffffff;
  /* margin:10px; */
  border-radius: 5px;
  float: left;
}
li>p{
  height: 60px;
  line-height: 60px;
  margin:0 10px;
  font-size:25px;
  float: left;
}

</style>
