let index= {
    init: function () {
        //바인드
        $("#btn-save").on("click", () => {
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
        }

        //console.log(data);

        $.ajax().done().fail(); // ajax 통신으로 3개의 데이터를 json으로 변경하여 insert
    }
}

index.init();