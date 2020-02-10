function disposeComment(parentId,type,content){
    if(!content){
        alert("评论内容不能为空~~~~");
        return;
    }
    $.ajax({
        url:"/submitComment",
        type:"post",
        dataType:"json",
        contentType: "application/json",
        data:JSON.stringify({"parentId":parentId,"type":type,"content":content}),
        success:function(result){
            if (result.code==100){
                if(type == "1"){
                    window.location.reload();
                    $("#comment-area").hide();
                }else {
                    $("input[name=sunCommentContent-"+parentCommentId+"]").val("");
                    sunCommentHtml(parentId,type);
                }
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
        },
        error:function () {
            alert("提交问题失败");
        }
    });
}
function submitComment(){
    var parentId=$("input[name='parentId']").val();
    var type=$("input[name='type']").val();
    var content=$("textarea[name='content']").val();
    disposeComment(parentId,type,content);
}
function submitSunComment(ele) {
    var e = $(ele);
    var parentCommentId = e.attr("data-parentCommentId");
    var sunCommentContent=$("input[name=sunCommentContent-"+parentCommentId+"]").val();
    disposeComment(parentCommentId,2,sunCommentContent);
}
function controllerCollapse(ele) {
    var e = $(ele);
    var id = e.attr("data-id");
    var collapse = e.attr("data-collapse");
    var eCollapse = $("#comment-"+id);
    if (collapse == "1"){
        eCollapse.addClass("in");
        e.attr("data-collapse","2");
        sunCommentHtml(id,2);
        e.removeClass("myActive");
    }else{
        eCollapse.removeClass("in");
        e.attr("data-collapse","1");
        $("input[name=sunCommentContent"+id+"]").val("");
        e.addClass("myActive");
    }
}
function sunCommentHtml(id,type){
     $.ajax({
         url:"/comment/"+id+"/"+type,
         type:"get",
         success:function(result){
             var commentDtos = result.extend.commentDtos;
             var sunCommentArea = $("#comment-"+id);
             var commentDiv = $("#comment-div").empty();
             $.each(commentDtos,function (index,commentDto) {
                var comment = $("<div></div>").addClass("media comment");
                var left = $("<div></div>",{"class":"media-left media-middle"});
                var img = $("<img>",{"class":"media-object avatar-img","alt":"头像"}).attr("src",commentDto.avatarUrl);
                var body = $("<div></div>",{"class":"media-body"});
                var a = $("<a></a>");
                var hName = $("<h4></h4>",{"class":"media-heading"}).text(commentDto.name);
                var content = $("<span></span>").text(commentDto.content);
                left.append(img);
                body.append(a.append(hName)).append(content);
                comment.append(left).append(body);
                 commentDiv.append(comment);
             });
             sunCommentArea.prepend(commentDiv);
         },
         error:function () {
             alert("访问失败");
         }
     })
}
function showSelectTagTab() {
    $("#select-tag-tab").show();
}
function selectTag(ele) {
    var textTag = $("input[name='tag']").val();
    var tag = $(ele).attr("data-tag");
    if (textTag){
        if (textTag.indexOf(tag) <0){
            $("input[name='tag']").val(textTag+","+tag);
        }
    } else {
        $("input[name='tag']").val(tag);
    }

}

