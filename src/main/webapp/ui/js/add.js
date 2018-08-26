$(function () {
    $("#prov").on("change", function () {
        var provName = $(this).find("option:selected").text();
        console.log("provName:" + provName);
        $("#provName").val(provName);
        var pCode = $(this).val();
        select("city",pCode);
    });

    $("#city").on("change", function () {
        var cityName = $(this).find("option:selected").text();
        $("#cityName").val(cityName);
        var pCode = $(this).val();
        select("area",pCode);
    });

    $("#area").on("change", function () {
        var areaName = $(this).find("option:selected").text();
        $("#areaName").val(areaName);
    });


});

function select(id,pCode) {
        $.ajax({
            url: "/area/getByPcode",
            data: {"pCode": pCode},
            success: function (data) {
                $("#" + id).empty();
                var option = "<option>-请选择-</option>";
                $("#" + id).append(option);
                for (var i = 0; i < data.length; i++) {
                    var area = data[i];
                    var option = "<option value='" + area.code + "'>" + area.name + "</option>";
                    $("#" + id).append(option);
                }
            }
    });


}

/*
function upload(obj){
    var f=$(obj).val();
    if(f == null || f ==undefined || f == ''){
        return false;
    }
    if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f)){
        alert("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
        $(obj).val('');
        return false;
    }

    console.log("$(obj)[0].files:" + $(obj)[0].files);
    console.log("$(obj)[0]:" + $(obj)[0]);
    console.log("$(obj):" + $(obj));
    console.log("obj:" + obj);


    var data = new FormData();
    $.each($(obj)[0].files,function(i,file){
        console.log("i:"+ i + ",file:" + file);
        data.append('file', file);
    });

    $.ajax({
        type: "POST",
        url: "/file/upload",
        data: data,
        cache: false,
        contentType: false,    //不可缺
        processData: false,    //不可缺
        dataType:"json",
        success: function(suc) {
            if(suc.code==0){
                $("#thumbUrl").val(suc.message);//将地址存储好
                $("#thumburlShow").attr("src",suc.message);//显示图片
            }else{
                alert("上传失败");
                $("#url").val("");
                $(obj).val('');
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("上传失败，请检查网络后重试");
            $("#url").val("");
            $(obj).val('');
        }
    });



}*/
