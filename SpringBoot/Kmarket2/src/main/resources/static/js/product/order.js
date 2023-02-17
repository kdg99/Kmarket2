/*
날짜 : 2023/02/17
이름 : 김동근
내용 : Kmarket2 SpringBoot cart js
*/

 $(function(){
	let cartList= [];
	let count 	= 0;
	let price 	= 0;
	let discount= 0;
	let point 	= 0;
	let delivery= 0;
	let total 	= 0;
	
	$(document).ready(function(){
		sumAll();
	})
	
	/* 체크박스 처리 */
	$('input[name=all]').click(function(){
		let deleteList = $('input[name=cartNo]');
		
		if($(this).is(":checked")){
		    deleteList.prop("checked", true);
		}else{
		    deleteList.prop("checked", false);
		}
		sumChecked();
		check();
	});
	
	$('input[name=cartNo]').click(function(){
		let isAll = true;
		$("input[name=cartNo]").each(function(){
	        isAll = isAll && $(this).is(":checked");
	    });
	    $('input[name=all]').prop("checked", isAll);
	    sumChecked();
	    check();
	});
	
	//전부 체크 해제이면 실행
	function check() {
	if ($("input:checkbox[name='cartNo']").is(":checked")==false) {
			sumAll();
		}
	}
	
	/* 합계 함수 */
	function sumChecked(){
		initVal();
		$('input[name=cartNo]').each(function(){
			if($(this).is(":checked")){
				let tds = $(this).parent().parent().children();
				count 	+= makeNum(tds[2].innerText);
				price 	+= (makeNum(tds[3].innerText) * makeNum(tds[2].innerText));
				discount+= (makeNum(tds[3].innerText) * makeNum(tds[2].innerText) - makeNum(tds[7].innerText) + makeNum(tds[6].innerText));
				point 	+= (makeNum(tds[5].innerText) * makeNum(tds[2].innerText));
				delivery+= makeNum(tds[6].innerText);
				total	+= makeNum(tds[7].innerText);
			}
		});
		inputTotal();
	}
	
	function sumAll(){
		initVal();
		$('input[name=cartNo]').each(function(){
			let tds = $(this).parent().parent().children();
			count 	+= makeNum(tds[2].innerText);
			price 	+= (makeNum(tds[3].innerText) * makeNum(tds[2].innerText));
			discount+= (makeNum(tds[3].innerText) * makeNum(tds[2].innerText) - makeNum(tds[7].innerText));
			point 	+= (makeNum(tds[5].innerText) * makeNum(tds[2].innerText));
			delivery+= makeNum(tds[6].innerText);
			total	+= makeNum(tds[7].innerText);
		});
		inputTotal();
	}
	
	function initVal(){
		cartList.length = 0;
		count 	= 0;
		price 	= 0;
		discount= 0;
		point 	= 0;
		delivery= 0;
		total 	= 0;
	}
	
	function inputTotal(){
		let trs = $('#totalTable > tbody').children();
		//포인트 적용
		let pointDis = makeNum(trs[5].children[1].innerText);
		total -= pointDis;
		//값 넣기
		trs[0].children[1].innerText = count.toLocaleString();
		trs[1].children[1].innerText = price.toLocaleString();
		trs[2].children[1].innerText = '-' + discount.toLocaleString();
		trs[3].children[1].innerText = delivery.toLocaleString();
		trs[4].children[1].innerText = point.toLocaleString();
		trs[5].children[1].innerText = pointDis.toLocaleString();
		trs[6].children[1].innerText = total.toLocaleString();
	}
	
	function makeNum(nanValue){
		if(nanValue == '무료배송'){
			return 0;
		}
		return parseInt(nanValue.split(',').join("").replace("%",""));
	}
	
	/* 포인트, 수령정보, 결제방식 등 */
	//포인트
	$('input[name=point]').focusout(function(){
		let userPoint = parseInt($('#userPoint').text());
		if($(this).val() > userPoint){
			$('input[name=point]').val(userPoint);
		}
	});
	
	$('#btnPoint').click(function(){
		let usePoint = parseInt($('input[name=point]').val());
		if(usePoint < 5000){
			alert('5000원 이상인 경우에만 사용 가능합니다.');
			return;
		}else{
			$('#totalTable > tbody > tr:nth-child(6) > td:nth-child(2)').text(usePoint.toLocaleString());
			sumChecked();
	    	check();
		}
	});
	
	function makeOrderList(){
		let tds = $(this).parent().parent().children();
		count 	= makeNum(tds[2].innerText);
		price 	= (makeNum(tds[3].innerText) * makeNum(tds[2].innerText));
		discount= (makeNum(tds[3].innerText) * makeNum(tds[2].innerText) - makeNum(tds[7].innerText) + makeNum(tds[6].innerText));
		point 	= (makeNum(tds[5].innerText) * makeNum(tds[2].innerText));
		delivery= makeNum(tds[6].innerText);
		total	= makeNum(tds[7].innerText);
	}
	
	/* 구매 */
	$('.order').click(function(){
		let orderList = [];
		let prodInfo = [];
		//선택확인
		//전부 체크해제시 전부 추가
		if($("input:checkbox[name='cartNo']").is(":checked")==false) {
			$('input[name=cartNo]').each(function(){
				//함수
			});
		}else { // 선택된 것만 추가
			$('input[name=cartNo]').each(function(){
				//함수
			});
		}
		
		let prodNo 	= $('#prodNo').text().slice(4);
		for(var i = 0 ; i < 6 ; i++){
			if(prodNo[i] != 0){
				prodNo = prodNo.slice(i);
				break;
			}
		}		
		let count 	= $('#count').val();
		let price 	= $('#price').text().split(',').join("");
		let discount= $('#discount').text();
		let delivery= $('#delivery').text().replace('무료배송', '0').split(',').join("");
		let total 	= $('#total').text().split(',').join("");
		let point	= parseInt(price) / 100;
		let prodName= $('#prodName').text()
		let descript= $('#descript').text()
		let src = $('#thumb1').attr('src')
		let thumb1	= src.slice(src.lastIndexOf("/")+1);
		
		let prodInfod = {
			'prodNo'	: prodNo,
			'count' 	: count,
			'price'		: price,
			'discount' 	: discount,
			'point'		: point,
			'delivery' 	: delivery,
			'total' 	: total,
			'prodName'	: prodName,
			'descript'	: descript,
			'thumb1'	: thumb1
		};
		orderList.push(prodInfo);
		console.log(orderList);
		$.ajax({
			url: '/Kmarket2/product/complete',
			method: 'POST',
			data: JSON.stringify(orderList),
			dataType: 'json',
			contentType: 'application/json',
			success: function(data){
				if(confirm('구매하시겠습니까?')){
					location.href="/Kmarket2/product/complete"
				}
			},
			error: function() {
          		alert('error 주문하기');
        	}
		});
		
	});

	
});