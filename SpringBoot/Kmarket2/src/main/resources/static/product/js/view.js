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
		let delivery= $('#delivery').text();
		let total 	= $('#total').text().split(',').join("");
		let point	= parseInt(total) / 100;
		
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
				if(confirm('장바구니에 추가되었습니다. 장바구니로 이동하시겠습니까?')){
					location.href="/Kmarket2/product/cart"
				}
			},
			error: function() {
          		alert('이미 장바구니에 있는 상품입니다.');
        	}
		});
		
	});
	
	/* 주문 */
	$('.order').click(function(){
		let orderList = [];
		let prodInfoList = [];
				
		let prodNo 	= $('input[name=prodNo]').val();
		let count 	= $('input[name=num]').val();
		let price 	= $('input[name=price]').val();
		let discount= $('input[name=discount]').val();
		let point 	= $('input[name=point]').val();
		let delivery= $('input[name=delivery]').val();
		let total 	= $('div.total > span').text();
		
		//orderList.push('{prodNo: '+prodNo+', count:'+count+', price:'+price+', discount:'+discount+', point:'+point+', delivery:'+delivery+', total:'+total+'}')
		prodInfoList.push(prodNo);
		prodInfoList.push(count);
		prodInfoList.push(price);
		prodInfoList.push(discount);
		prodInfoList.push(point);
		prodInfoList.push(delivery);
		prodInfoList.push(total.split(',').join(""));
		prodInfoList.push('fromView');
		orderList.push(prodInfoList);
		
		console.log(orderList);
		
		if(confirm('주문하시겠습니까?')){
			$.redirectPost('/Kmarket1/product/productOrder.do', orderList);
		}
	});
	
	$.extend({
	    redirectPost: function (location, args) {
	        let form = $('<form></form>');
	        form.attr("method", "post");
	        form.attr("action", location);
	        
	        // key value 형식으로 바꾼 후에 form 으로 변환
	        $.each(args, function (key, value) {
	            let field = $('<input></input>');
	            
	            field.attr('type', 'hidden');
	            field.attr("name", 'orderList');
	            field.attr("value", value);
	            
	            form.append(field);
	        });
	        
	        // 위에서 생성된 폼을 제출 한다
	        $(form).appendTo('body').submit();
	    }
	});
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
	$('#product > section > article.info > div.summary > nav:nth-child(2) > h5 > a').click(function(){
		let location = document.querySelector("#product > section > article.review > nav > h1").offsetTop;
		window.scrollTo({top:location, behavior:'smooth'});
	});
	
});