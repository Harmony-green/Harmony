$(document).ready(function() {
	// 페이지 로드 시 저장된 라디오 버튼, 라벨, select 상태를 확인하여 스타일 적용
	const savedSexualState = localStorage.getItem('selectedSexual');
	if (savedSexualState) {
		$(`#${savedSexualState}`).prop('checked', true);
		changeColor(`${savedSexualState}Label`);
	}

	const savedLabelState = localStorage.getItem('selectedLabel');
	if (savedLabelState) {
		$(`#${savedLabelState}`).css({
			backgroundColor: '#343b4d',
			color: 'white'
		});
	}

	// 라디오 버튼 클릭 이벤트
	$('input[type="radio"]').on('change', function() {
		changeColor($(this).attr('id') + 'Label');
	});

	// 함수 정의
	function changeColor(labelId) {
		// 모든 라벨 초기화
		$('label').css({
			backgroundColor: '',
			color: ''
		});

		// 선택된 라벨 스타일 적용
		$(`#${labelId}`).css({
			backgroundColor: '#343b4d',
			color: 'white'
		});

		// 선택된 성별을 localStorage에 저장
		localStorage.setItem('selectedSexual', labelId.replace('Label', ''));

		// 선택된 라벨을 localStorage에 저장
		localStorage.setItem('selectedLabel', labelId);
	}
});

function passchange() {
	var currentPass = document.getElementById("currentpass").value;
	var newPass = document.getElementById("newpass").value;
	var newPassAgain = document.getElementById("newpassagain").value;

	// 클라이언트 측 유효성 검사 (단순 예시)
	if (currentPass === "" || newPass === "" || newPassAgain === "") {
		alert("모든 필드를 입력하세요.");
		return;
	}

	if (newPass !== newPassAgain) {
		alert("새 패스워드가 일치하지 않습니다.");
		return;
	}

	// Ajax를 사용하여 서버에 비밀번호 변경 요청 전송
	var xhr = new XMLHttpRequest();
	xhr.open("PUT", "/users/{no}", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); // url에 폼 정보를 인코딩하여 보내기 " 보안성 "
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			// 서버 응답을 받았을 때의 처리 (예: 리다이렉트)
			alert("비밀번호 변경에 성공하셨습니다!");
			window.location.href = "/myharmony";
		} else if (xhr.readyState === 4 && xhr.status !== 200) {
			// 서버 응답이 실패한 경우의 처리 (예: 에러 메시지 표시)
			alert("비밀번호 변경에 실패했습니다.");
		}
	};

	// 서버로 보낼 데이터 설정
	var formData = "currentpass=" + encodeURIComponent(currentPass) +
		"&newpass=" + encodeURIComponent(newPass) +
		"&newpassagain=" + encodeURIComponent(newPassAgain);
	xhr.send(formData);
}
function handleProfileImgChange(element) {
	var formData = new FormData();
	formData.append("profileImg", element.files[0]);
	$.ajax({
		url: "/profile-temp-upload",
		data: formData,
		type: "post",
		contentType: false,
		processData: false,
		success: function(map) {
			$("#previewImage").attr("src", map.url);
			//$(element).parents(".btn-file")
			$(element).parent().css("background-image", `url(${map.url})`);
			$(element).siblings(".tempKey").val(map.tempKey);
			$(element).siblings(".orgName").val(map.orgName);
		}
	});
}
function deletepage() {
	window.open('/users/delete', 'popupWindow', 'width=600,height=400');
	
}