// aside 하위항목 숨기기 및 보이기 기능
    $(function(){
		$('#gnb > li > ol').hide();
		
        $('#gnb > li > a').click(function(){
            $(this).siblings().slideToggle(300);
        });
    });

// 상품 카테고리
function optionChange(){

	// category2에 속하는 것들 배열로 추가
	let brand = ['브랜드 여성의류', '브랜드 남성의류', '브랜드 진/캐쥬얼', '브랜드 신발/가방', '브랜드 쥬얼리/시계', '브랜드 아웃도어'];
	let cloth = ['여성의류', '남성의류', '언더웨어', '신발', '가방/잡화', '쥬얼리/시계', '화장품/향수', '바디/헤어'];
	let kids = ['출산/육아', '장난감/완구', '유아동 의류', '유아동 신발/잡화'];
	let daily = ['신선식품', '가공식품', '건강식품', '커피/음료', '생필품', '바디/헤어'];
	let hobby = ['가구/DIY', '침구/커튼', '조명/인테리어', '생활용품', '주방용품', '문구/사무용품', '사무기기', '악기/취미', '반려동물용품'];
	let elect = ['노트북/PC', '모니터/프린터', 'PC주변기기', '모바일/태블릿', '카메라', '게임', '영상가전', '주방가전', '계절가전', '생활/미용가전', '음향가전', '건강가전'];
	let sports = ['스포츠의류/운동화', '휘트니스/수영', '구기/라켓', '골프', '자전거/보드/기타레저', '캠핑/낚시', '등산/아웃도어', '건강/의료용품', '건강식품', '렌탈서비스'];
	let car = ['자동차용품', '공구/안전/산업용품'];
	let ticket = ['여행/항공권', '도서/음반/e교육', '공연티켓', 'e쿠폰', '상품권'];

	// category1의 select option에서 value값 받아오기
	// 여기서 value값 = DB에 들어갈 cate1의 고유번호
	let cate1 = $('.category1').val();

	let change;

	switch (cate1) {
		case '10':
			change = brand;
			break;
		case '11':
			change = cloth;
			break;
		case '12':
			change = kids;
			break;
		case '13':
			change = daily;
			break;
		case '14':
			change = hobby;
			break;
		case '15':
			change = elect;
			break;
		case '16':
			change = sports;
			break;
		case '17':
			change = car;
			break;
		case '18':
			change = ticket;
			break;
	}

	// 옵션을 추가하기 전에 select box를 비워준다.
	$('.category2').empty();
	let option;
	option = $("<option value='' disabled selected>2차 분류 선택</option>");
	$('.category2').append(option);

	for (let i=0; i < change.length; i++){
		option = $("<option value="+[i+10]+">"+change[i]+"</option>");
		$('.category2').append(option);
	}
}


