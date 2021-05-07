import Vue from 'vue'
import App from './App'
import uView from 'uview-ui'
import api from '@/common/request/index'
import store from '@/store'
import './store/useSocket.js'
Vue.prototype.$api=api
Vue.prototype.$store=store

Vue.config.productionTip = false

const msg=(title,duration=1500,msk=false,icon='none')=>{
	//统一提示方便全局修改
	if(Boolean(title)===false){
		return;
	}
	uni.showToast({
		title,
		duration,
		mask,
		icon
	})
}
Vue.prototype.$msg=msg;
Vue.use(uView);
App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
