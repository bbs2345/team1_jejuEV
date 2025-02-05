console.log("common.js파일 불러옴.");

function getSearchParam(key) {
	return new URLSearchParams(location.search).get(key);
}

function getHiddenTag(name, value) {
	return $("<input/>", { type: "hidden", "name": name, "value": value });

}