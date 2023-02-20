/*
 * 날짜 : 2023/02/20
 * 이름 : 최아영
 * 내용 : Kmarket STS cs js
 */
window.addEventListener('load',()=>{
	const hides = document.getElementsByClassName('hide');
	for(const hide of hides ) hide.setAttribute('style','display:none;');
});

function moreView(tag) {
	event.preventDefault();
	let text = tag.innerText;
	if (text == '더보기') {
		const ele = tag.parentElement;
		const hides = ele.getElementsByClassName('hide');
		for(const hide of hides) hide.removeAttribute('style');
		tag.children[0].innerText = '간단히 보기';
	} else {
		const ele = tag.parentElement;
		const hides = ele.getElementsByClassName('hide');
		for(const hide of hides) hide.setAttribute('style','display:none;');
		tag.children[0].innerText = '더보기';
	}
}
