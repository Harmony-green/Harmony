/**
 * 
 */


//수정버튼 클릭시 - 수정가능한 화면
function clickedBtnEdit(){
	var trTag=$(this).parents("tr");
	//trTag.find(".edit").each((el)=>{});
	trTag.find(".edit").each(function(){
		$(this).css("display", "block");
	});
	trTag.find(".default").each(function(){
		$(this).css("display", "none");
	});
}
function clickedBtnCancel(){
	var trtag=$(this).parents("tr");
	//trTag.find(".edit").each((el)=>{});
	trtag.find(".edit").each(function(){
		$(this).css("display", "none");
	});
	trtag.find(".default").each(function(){
		$(this).css("display", "block");
	});
}
function clickedBtnEditDone(){
	//비동기통신 : 새로고침이 발생하지않고 통신하는방법 - 백그라운드로 요청하고 필요한 일부분만 변경할 수 있어요
	//ajax : Asynchronous JavaScript and XML.
	//JSON : 자바스크립트 객체 표현식
	//클릭된 수정완료버튼
	var no = $(this).siblings(".no").val(); // form 내부에 오는 입력요소들
	var title = $(this).parents("tr").find(".title").val();
	var price = $(this).parents("tr").find(".price").val();
	var stock = $(this).parents("tr").find(".stock").val();
	var category = $(this).parents("tr").find(".category").val();
	var name="홍길동";
	$.ajax({
		//url:"/memo/"+no,s
		url:`/productList/${no}`, // `` : 백틱
		type:"put",
		data:{title : title, price : price, stock : stock, category : category}, // {name : value, name : value ...} 
		success:function(result){console.log(result);
		location.href="/productList";
		}, // 정상처리되면 실행되는 함수
	//	error:function(e){} // 문제발생하면 실행되는 함수
	});
}