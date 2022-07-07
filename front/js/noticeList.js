$(function(){
    //제목이 클릭되었을 때
    $(".title").click(function(){
        //제목이 포함되어 있는 부모태그를 찾고
        let $notice=$this.parent()//
        //다시 부모태그 밑에 있는 게시글번호를 받자 
        let $boardNo=$notice.firstChild();
        let boardNo=$boardNo.val();

        //공지사항 글 번호를 서버로 ajax 요청으로 전달하고 
        //서버에선 해당 글 번호에 맞는 글의 상세페이지로 이동하게 만들어준다.
        let url="back/noticelist";
        $.ajax({
            url:url,
            method:'get',
            data: boardNo,
            success:function(){

            },
            error:function(jqXHR){
                alert(jqXHR.error);
            }
        });
    });
});