/*
날짜 : 2023/02/17
이름 : 김동근
내용 : Kmarket2 SpringBoot cart js
*/

 $(function(){
	let count 	= 0;
	let price 	= 0;
	let discount= 0;
	let point 	= 0;
	let delivery= 0;
	let total 	= 0;
	
	$(document).ready(function(){
		sumAll();
		$('input[name=payment][value="1"]').attr('checked', true);
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
			discount+= (makeNum(tds[3].innerText) * makeNum(tds[2].innerText) - makeNum(tds[7].innerText) + makeNum(tds[6].innerText));
			point 	+= (makeNum(tds[5].innerText) * makeNum(tds[2].innerText));
			delivery+= makeNum(tds[6].innerText);
			total	+= makeNum(tds[7].innerText);
		});
		inputTotal();
	}
	
	function initVal(){
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
		trs[2].children[1].innerText = discount.toLocaleString();
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
	
	
	/* 구매 */
	$('#btnOrder').click(function(e){
		e.preventDefault();
		let orderList = [];
		//선택확인
		//전부 체크해제시 전부 추가
		if($("input:checkbox[name='cartNo']").is(":checked")==false) {
			$('input[name=cartNo]').each(function(){
				orderList.push(makeProdInfo($(this)));
			});
		}else { // 선택된 것만 추가
			$('input[name=cartNo]').each(function(){
				orderList.push(makeProdInfo($(this)));
			});
		}
		let trs = $('#totalTable > tbody').children();
		let ordCount 	= makeNum(trs[0].children[1].innerText);
		let ordPrice 	= makeNum(trs[1].children[1].innerText);
		let ordDiscount	= makeNum(trs[2].children[1].innerText);
		let ordDelivery = makeNum(trs[3].children[1].innerText);
		let savePoint	= makeNum(trs[4].children[1].innerText);
		let usedPoint	= makeNum(trs[5].children[1].innerText);
		let ordTotPrice	= makeNum(trs[6].children[1].innerText);
		let recipName 	= $('#orderer').val();
		let recipHp		= $('#hp').val();
		let recipZip 	= $('#zip').val();
		let recipAddr1	= $('#addr1').val();
		let recipAddr2	= $('#addr2').val();
		let ordPayment 	= $('input[name=payment]:checked').val();
		
		let orderInfo = {
			'ordCount' 	: ordCount,
			'ordPrice' 	: ordPrice,
			'ordDiscount': ordDiscount,
			'ordDelivery': ordDelivery,
			'savePoint' : savePoint,
			'usedPoint' : usedPoint,
			'ordTotPrice': ordTotPrice,
			'recipName' : recipName,
			'recipHp'	: recipHp,
			'recipZip'	: recipZip,
			'recipAddr1': recipAddr1,
			'recipAddr2': recipAddr2,
			'orderList'	: orderList,
			'ordPayment': ordPayment
		}
		console.log(orderInfo);
		
		$.ajax({
			url: '/Kmarket2/product/complete',
			method: 'POST',
			data: JSON.stringify(orderInfo),
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
	
	function makeProdInfo(me){
		let tds = me.parent().parent().children();
		let prodNo	= makeNum(me.val());
		count 	= makeNum(tds[2].innerText);
		price 	= (makeNum(tds[3].innerText) * makeNum(tds[2].innerText));
		discount= makeNum(tds[4].innerText);
		point 	= (makeNum(tds[5].innerText) * makeNum(tds[2].innerText));
		delivery= makeNum(tds[6].innerText);
		total	= makeNum(tds[7].innerText);
		let prodInfo = {
			'prodNo'	: prodNo,
			'count' 	: count,
			'price'		: price,
			'discount' 	: discount,
			'point'		: point,
			'delivery' 	: delivery,
			'total' 	: total
		};
		return prodInfo
	}
	

	
});