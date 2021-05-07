import Vue from 'vue'
import store from "./index.js"; //引入vuex，
import socket from "../tools/socket.js"; //引入socket.js，

export function connectWebSocket(){
	const Socket =new socket({
	    url: 'ws://127.0.0.1:8086/ws', //连接地址 必填
	    // onOpen(res) {
	    //     console.log('连接成功')
	    // },
	    // onClose(err) {
	    //     console.log('关闭了连接')
	    // },
	    // onReload(res) {
	    //     console.log('重载：' + res)
	    // },
	    // onMsg(msg) {
	    //     // console.log(msg)
	    // }
	});
	Socket.eventPatch.onOpen((msg,sk)=>{        //监听是否连接成功
	});
	Socket.eventPatch.onMsg((msg,sk)=>{    //监听是否接受消息
	 // console.log(msg)
	});
	Vue.prototype.$Socket = Socket;    //连接成功挂在到原型上
}

