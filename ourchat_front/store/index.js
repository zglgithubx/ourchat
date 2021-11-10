import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store =new Vuex.Store({
	state:{
		serverUrl:'http://117.50.162.61',
		websocketUrl:'ws://117.50.162.61/websocket',
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