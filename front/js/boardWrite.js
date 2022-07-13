$(function(){
	//전송버튼을 누르면 제목과 내용을 서버로 보내서 DB에 저장시켜보자
	$(".complete").click(function(){
		let $title=$("#title");
		let $boardText=$(".boardText");
		let InputTitleValue=$title.val();
		let InputBoardTextValue=$boardText.val();
		let url="http://localhost:8888/back4/boardwrite";
		let data="title="+InputTitleValue+"&content="+InputBoardTextValue;
		$.ajax({
			url: url,
			method: 'get',
			data: data,
			success:function(jsonObj){
				location.href="http://localhost:8888/front/project_html/boardList.html";
			},
			error:function(){}
		});
	});
	
	
	//취소버튼 누르면 처음 글작성 페이지로 초기화된 상태로 보여줍시다
	$(".cancle").click(function(){
		location.href="http://localhost:8888/front/project_html/boardWrite.html";
		return false;
	});
});