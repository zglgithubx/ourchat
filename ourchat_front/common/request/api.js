
export default{
	 user:{
		 login:{
			 url:'/authentication/email',
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
		 },
		 uploadImage:{
			 url:'/upload',
			 auth:false,
			 method:'UPLOAD'
		 }
	 }
}