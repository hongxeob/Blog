let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
			
		
	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		//console.log(data);

		//ajax호출시 디폴트는 비동기 호출 
		//ajax통신을 이용하여 3개의 데이터를 jso으로 변경하여 insert요청
		$.ajax({
			type:"POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8",//body데이터가 어떤 타입인지(MIME)
			dataType:"json"//요청을 서버로 왔을때 기본적으로 문자열(생긴게 json이라면)=>자바스크립트 오브젝트로 변경을 해줌.
		}).done(function(resp){
			alert("회원가입이 완료 되었습니다.");
			console.log(resp);
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
}

index.init();