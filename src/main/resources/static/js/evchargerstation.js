console.log("evchargerstation.js 입니다~~")

//let lat = $("#ev_read_hidden_lat").val();
//let lng = $("#ev_read_hidden_lng").val();
//let popupText = $("#ev_read_hidden_statNm").val();
//





// 숨겨진 필드 값 가져오기
let latElements = document.getElementsByName('lat');
let lngElements = document.getElementsByName('lng');
let statNmElements = document.getElementsByName('statNm');
let statIdElements = document.getElementsByName('statId');

// 값을 배열로 저장
let locations = [];
for (let i = 0; i < latElements.length; i++) {
	locations.push({
		lat: parseFloat(latElements[i].value),
		lng: parseFloat(lngElements[i].value),
		statNm: statNmElements[i].value,
		statId: statIdElements[i].value
	});
}

// 지도 초기화
function evstationMap() {

	let map = L.map('map').setView([33.386974, 126.533242], 10);

	// OpenStreetMap 타일 추가
	L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
		attribution: '&copy; OpenStreetMap contributors'
	}).addTo(map);

	return map;
}

// 마커추가 함수
function addMarkers(map, locations) {

	// locations 배열에 있는 각 위치를 반복하면서 마커 추가
	locations.forEach((location) => {
		let lat = location.lat;
		let lng = location.lng;
		let popupText = `<a href="/ev/read/${location.statId}">${location.statNm}</a>`;

		// 마커 생성 및 지도에 추가
		let marker = L.marker([lat, lng]).addTo(map);
		marker.bindPopup(popupText);

	});
}

// 모달이 열릴 때
$('#exampleModal').on('shown.bs.modal', function() {
	map.invalidateSize(); // 지도 크기 갱신
});

// 지도 초기화
map = evstationMap();

// 마커추가 호출
addMarkers(map, locations);







