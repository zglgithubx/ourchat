
export default{
	 user:{
		 login:{
			 url:'/auth/email',
			 auth:false,
			 method:'POST'
		 },
		 sendCode:{
			 url:'/auth/sms',
			 auth:false,
			 method:'GET'
		 },
		 signUp:{
		 	url:'/auth/sign-up'
		 }
		 addFriend:{
			 url:'/api/common/addFriend',
			 auth:true,
			 method:'POST'
		 },
		 searchFriend:{
			 url:'/api/common/searchFriend',
			 auth:true,
			 method:'POST'
		 }
		 
	 }
}