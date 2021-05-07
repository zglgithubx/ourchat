import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store =new Vuex.Store({
	state:{
		// serverUrl:'http://192.168.43.103:8087',
		serverUrl:'http://127.0.0.1:8087',
		systemInfo:null,
		authorizeState:true,
		config:null,
		token:null,
		userInfo:null,
		showLoginTip:false,
		SocketState:{},
		SocketStateErr:{},
	},
	mutations:{
		LOGIN_TIP(state,data)
		{
			state.showLoginTip=data
		},
		setSocketState(that, info) {
			that.SocketState = info
		},
		setSocketStateErr(that, info) {
			that.SocketStateErr = info;
		}
	},
})
export default store