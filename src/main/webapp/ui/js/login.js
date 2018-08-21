$(function () {
    $("#submit").on("click",function () {
        var name = $("#loginName").val();
        var password = $("#loginPassword").val();
        $.ajax({
            type: "POST",
            url:"/user/login",
            data: {"name":name,"password":password},
            success: function (flag) {
                if(flag == "false"){
                    alert("用户名或密码错误");
                }else{
                    window.location.herf = "localhost:8080/student/studentList";
                }
            }
        });
    });
});