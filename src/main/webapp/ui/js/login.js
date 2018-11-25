$(function () {
    $("#submit").on("click",function () {
        var name = $("#loginName").val();
        var password = $("#loginPassword").val();
        var verify = $("#verifyCode").val();
        $.ajax({
            type: "POST",
            url:"/login",
            data: {"name":name,"password":password,"verify":verify},
            success: function (flag) {
                console.info("flag:" + flag);
                // if(flag == "false"){
                //     alert("用户名或密码错误");
                // }else{
                //     window.location.href = "/student/studentList";
                // }
            }
        });
    });
});