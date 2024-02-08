function mainImg(){
	var imgsrc=$(".img-wrap .target").attr("src");
	$(".img-wrap .main-img").css("background-image", `url(${imgsrc})`)
}

mainImg();
$(".img-wrap img").click(function(){
	$(".img-wrap img").removeClass("target");
	$(this).addClass("target");
	mainImg();
})