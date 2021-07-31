
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
		 addFriend:{
			 url:'/common/addFriend',
			 auth:true,
			 method:'POST'
		 },
		 searchFriend:{
			 url:'/common/searchFriend',
			 auth:true,
			 method:'POST'
		 }
		 
	 }
}