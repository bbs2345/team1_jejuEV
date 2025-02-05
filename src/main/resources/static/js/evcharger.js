
function makeTag(obj) {
	let tag =``;
	
	for (let el of obj) {
	    // chgerType 변환 로직
	    let chgerType = ""; 
	    switch (el.chgerType) {
	        case "01": chgerType = "DC차데모"; break;
	        case "02": chgerType = "AC완속"; break;
	        case "03": chgerType = "DC차데모+AC3상"; break;
	        case "04": chgerType = "DC콤보"; break;
	        case "05": chgerType = "DC차데모+DC콤보"; break;
	        case "06": chgerType = "DC차데모+AC3상+DC콤보"; break;
	        case "07": chgerType = "AC3상"; break;
	        case "08": chgerType = "DC콤보(완속)"; break;
	        default: chgerType = "알 수 없음"; break;
	    }
		
		// stat 변환 로직
		let stat = "";
		switch (el.stat){
			case "1": stat = "통신이상"; break;
			case "2": stat = "충전대기"; break;
			case "3": stat = "충전중"; break;
			case "4": stat = "운영중지"; break;
			case "5": stat = "점검중"; break;
			case "9": stat = "상태미확인"; break;
			default: stat = "알 수 없음"; break;
		}
		
		tag+=`
		<tr>
			<td>${el.chgerId}</td>
			<td>${chgerType}</td> 
			<td>${stat}</td> 
			<td>${el.statUpdDt}</td> 
			<td>${el.lastTsdt}</td> 
			<td>${el.lastTedt}</td> 
			<td>${el.nowTsdt}</td> 
			<td>${el.powerType}</td> 
			<td>${el.output}Kw</td> 
			<td>${el.method}</td> 
		</tr>
		`;
	}
	
	$("tbody").html(tag);
	
}

function getCharcerInfo(statId) {
	
		$.ajax({
			url : `/ev/getChargerInfo/${statId}`,
			type : "GET",
			dataType : "text",
			success : function(result){
				let obj = JSON.parse(result);
				console.log(obj);
				makeTag(obj);
			}
		});

}

$(function() {
	
	let statId = $("input[name='statId']").val();
	
	getCharcerInfo(statId);
	
});

document.addEventListener("DOMContentLoaded", function () {
    let backButton = document.getElementById("backList");

    let currentParams = window.location.search; // 현재 URL의 쿼리 스트링 가져오기
    let Url = "/ev/list";

    // 쿼리 스트링이 있으면 저장하고, 없으면 초기화
    if (currentParams) {
        sessionStorage.setItem("listPageUrl", Url + currentParams);
    } else {
        sessionStorage.removeItem("listPageUrl"); // 쿼리 스트링 없으면 기존 검색 조건 삭제
    }

    // 저장된 검색 조건 불러오기
    let savedUrl = sessionStorage.getItem("listPageUrl") || Url; // 기본값 설정

    // 목록 버튼 클릭 시 저장된 검색 조건 유지
    backButton.href = savedUrl;

});
