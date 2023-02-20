/*
날짜 : 2023/02/13
이름 : 김동근
내용 : Kmarket2 SpringBoot product view js
*/

$(function(){
	/* 수량 */
	$('#increase').click(function(){
		//값 가져오기
		let amount = parseInt($('#count').val());
		let price = parseInt($('#disPrice').text().split(',').join(""));
		//계산
		$('#count').attr('value', ++amount);
		let total = amount*price;
		//값 넣기
		$('#total').text(total.toLocaleString());
	});
	
	$('#decrease').click(function(){
		//값 가져오기
		let amount = parseInt($('#count').val());
		if(amount<=1){ return; }
		let price = parseInt($('#disPrice').text().split(',').join(""));
		//계산
		$('#count').attr('value', --amount);
		let total = amount*price;
		//값 넣기
		$('#total').text(total.toLocaleString());
	});
	
	/* 카트 */
	$('.cart').click(function(){

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
		
		let cartData = {
			'prodNo'	: prodNo,
			'count' 	: count,
			'price'		: price,
			'discount' 	: discount,
			'point'		: point,
			'delivery' 	: delivery,
			'total' 	: total
		};
		
		$.ajax({
			url: '/Kmarket2/product/addCart',
			method: 'POST',
			data: cartData,
			dataType: 'json',
			success: function(data){
				if(data){
					if(confirm('장바구니에 추가되었습니다. 장바구니로 이동하시겠습니까?')){
						location.href="/Kmarket2/product/cart"
					}
				}else{
					alert('이미 장바구니에 있는 상품입니다.');
				}
			},
			error: function() {
          		alert('error 장바구니');
        	}
		});
		
	});
	
	/* 주문 */
	$('.order').click(function(){
		let orderList = [];
		let prodInfoList = [];
		
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
		
		let prodInfo = {
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
		$.ajax({
			url: '/Kmarket2/product/order',
			method: 'POST',
			data: JSON.stringify(orderList),
			dataType: 'json',
			contentType: 'application/json',
			success: function(data){
				if(confirm('구매하시겠습니까?')){
					location.href="/Kmarket2/product/order"
				}
			},
			error: function() {
          		alert('error 주문하기');
        	}
		});
		
	});
	
});