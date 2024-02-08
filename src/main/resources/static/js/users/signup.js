function selectAll(selectAll) {

    const checkboxes = document.getElementsByName("a-check1")
    
    checkboxes.forEach((checkbox) => {
      checkbox.checked = selectAll.checked;
    })

}

function allChecked() {

    const allCheck = document.querySelector('input[name="all"]');
    const checkboxes = document.querySelectorAll('input[name="a-check1"]');
    const checkboxesChecked = document.querySelectorAll('input[name="a-check1"]:checked');
    
    if (checkboxes.length === checkboxesChecked.length) {
        allCheck.checked = true;
    } else {
        allCheck.checked = false;
    }

}


function selectAll2(selectAll2) {

    const checkboxes = document.getElementsByName("a-check2")
    
    checkboxes.forEach((checkbox) => {
      checkbox.checked = selectAll2.checked;
    })

}

function allChecked2() {

    const allCheck = document.querySelector('input[name="all2"]');
    const checkboxes = document.querySelectorAll('input[name="a-check2"]');
    const checkboxesChecked = document.querySelectorAll('input[name="a-check2"]:checked');
    
    if (checkboxes.length === checkboxesChecked.length) {
        allCheck.checked = true;
    } else {
        allCheck.checked = false;
    }

}

document.addEventListener("DOMContentLoaded", function () {
	
	var form = document.querySelector("form");

	form.addEventListener("submit", function (event) {
		var email = document.querySelector(".email").value;
		var password = document.querySelector(".password").value;
		var passworda = document.querySelector(".password-a").value;
		var name = document.querySelector(".name").value;
		var phone = document.querySelector(".phone").value;

		var errorMessage = "";

		if (!email.trim()) {
			errorMessage += "이메일을 작성하지 않았습니다.\n";
		}

		if (!password.trim()) {
			errorMessage += "비밀번호를 작성하지 않았습니다.\n";
		}

		if (password.trim()!=passworda.trim()){
			errorMessage += "비밀번호가 맞지 않습니다.\n"
		}

		if (!name.trim()) {
			errorMessage += "이름을 작성하지 않았습니다.\n";
}

		if (!phone.trim()) {
			errorMessage += "전화번호를 작성하지 않았습니다.\n";
		}
		if (!email.trim() || !password.trim() || !name.trim() || !phone.trim()) {
			errorMessage = "모든 요소를 작성해주세요.\n";
		}

		if (errorMessage) {
			alert(errorMessage);
			event.preventDefault();
		}
	});
});

document.addEventListener("DOMContentLoaded", function() {
    const emailInput = document.querySelector(".email");
    const checkEmailButton = document.getElementById("checkEmail");
    const confirmCodeInput = document.getElementById("memailconfirm");
    const checkEmailAgainButton = document.getElementById("checkEmailagain");

    let confirmationCode; // 수신한 확인 코드를 저장할 변수

    checkEmailButton.addEventListener("click", function() {
        const email = emailInput.value;

        // Ajax 요청
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "/emailConfirm", true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // 서버에서 이메일 전송 성공한 경우
                    alert("이메일이 발송되었습니다. 인증 코드를 입력해주세요.");
                    // 수신한 확인 코드 저장
                    confirmationCode = xhr.responseText;
           			 console.log(confirmationCode);
                } else {
                    // 서버에서 이메일 전송 실패한 경우
                    alert("이메일 발송에 실패했습니다. 다시 시도해주세요.");
                }
            }
        };

        // JSON 형태로 데이터 전송
         const data = JSON.stringify({ email: email });
        xhr.send(data);
        document.getElementById('checkarea').style.display="flex";
        console.log(xhr);
    });

    confirmBtn.addEventListener("click", function() {
        const enteredCode = confirmCodeInput.value;

        // 입력된 코드와 수신한 코드를 비교
        if (enteredCode === confirmationCode) {
            alert("인증이 완료되었습니다. 가입을 완료해주세요.");
            $("#checkarea").hide();
            $("#checkEmail").text("인증완료");
            $("#checkEmail").prop("disabled",enteredCode === confirmationCode);
            $(".email").prop("readonly",enteredCode === confirmationCode)
            $(".email").css({'border':'1.9px solid #3fe87f'})
        } else {
            alert("인증 코드가 올바르지 않습니다. 다시 시도해주세요.");
        }
    });
});