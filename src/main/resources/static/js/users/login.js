document.addEventListener("DOMContentLoaded", function () {
	
	var form = document.querySelector("form");

	form.addEventListener("submit", function (event) {
		var email = document.querySelector("#emaillog").value;
		var password = document.querySelector("#passlog").value;

		var errorMessage = "";

		if (!email.trim()) {
			errorMessage += "이메일을 작성하지 않았습니다.\n";
		}

		if (!password.trim()) {
			errorMessage += "비밀번호를 작성하지 않았습니다.\n";
		}
		if (!email.trim() && !password.trim()) {
			errorMessage = "모든 요소를 작성해주세요.\n";
		}

		if (errorMessage) {
			alert(errorMessage);
			event.preventDefault();
		}
	});
});