
export default{
	 user:{
		 login:{
			 url:'/authentication/mobile',
			 auth:false,
			 method:'POST'
		 },
		 sendCode:{
			 url:'/code/sms',
			 auth:false,
			 method:'GET'
		 },
		 test:{
			 url:'/userLogin',
			 auth:false,
			 method:'GET'
		 }
	 }
}