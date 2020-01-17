function submitComment(){
    var parentId=$("input[name='parentId']").val();
    var type=$("input[name='type']").val();
    var content=$("textarea[name='content']").val();
    $.ajax({
        url:"/submitComment",
        type:"post",
        dataType:"json",
        contentType: "application/json",
        data:JSON.stringify({"parentId":parentId,"type":type,"content":content}),
        success:function(result){
            if (result.code==100){
                $("#comment-area").hide();
                console.log(result.msg);
            }else {
                if (result.code==205){
                    if (confirm(result.msg)){
                        window.open("https://github.com/login/oauth/authorize?client_id=4bbe59b576eea0d10c54&redirect_uri=http://localhost:8887/callback&scope=1");
                        window.localStorage.setItem("isClose","true");
                    }
                }else{
                    alert(result.msg);
                }
            }
        }
    });
}