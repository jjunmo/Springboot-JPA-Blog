let index= {
    init: function () {
        //바인드
        $("#btn-save").on("click", () => { //function(){} , ()=>{} this를 바인딩하기 위해서
            this.save();
        });
    },

    save: function () {
        //alert('user의 save함수 호출');
        //test

        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        //console.log(data);
        
        // ajax 호출시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
        // ajax 통신 성공시 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
        $.ajax({
            type:"POST",
            url:"/blog/api/user",
            data:  JSON.stringify(data), //HttpBody에 JSON 문자열로 변환
            contentType:"application/json;charset=utf-8" //Body데이터가 어떤 타입인지
            //회원가입 요청 결과에 다른 실행
        }).done(function(resp){
            alert("회원가입 완료");
            location.href="/blog";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();