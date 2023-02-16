/*
날짜 : 2023/02/15
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
	
	/* 삭제 */
	$('input[name=del]').click(function(){
		let deleteList = [];
		$('input[name=cartNo]:checked').each(function(){
			let cartNo = parseInt($(this).val());
			deleteList.push(cartNo);
		})
		
		$.ajax({
			url:'/Kmarket2/product/deleteCart',
			type:'POST',
			traditional: true,
			data:{'deleteList': deleteList},
			dataType:'json',
			success:function(data){
				location.reload();
			}
		});
	});
	
	
	
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
		trs[0].children[1].innerText = count.toLocaleString();
		trs[1].children[1].innerText = price.toLocaleString();
		trs[2].children[1].innerText = '-' + discount.toLocaleString();
		trs[3].children[1].innerText = delivery.toLocaleString();
		trs[4].children[1].innerText = point.toLocaleString();
		trs[5].children[1].innerText = total.toLocaleString();
	}
	
	function makeNum(nanValue){
		if(nanValue == '무료배송'){
			return 0;
		}
		return parseInt(nanValue.split(',').join("").replace("%",""));
	}
	
	/* 제출 */
	$('.orderForm').submit(function(e){
		e.preventDefault();
		let orderList = [];
		let i = 1;
		$('input[name=cartNo]').each(function(){
			if($(this).is(":checked")){
				let prodInfoList = [];
				
				let prodNo = $('.orderList tr:nth-child('+i+') td:nth-child(1) > input[type=checkbox]').val();
				let count = $('.orderList tr:nth-child('+i+') td:nth-child(3)').text();
				let price = $('.orderList tr:nth-child('+i+') td:nth-child(4)').text();
				let discount = $('.orderList tr:nth-child('+i+') td:nth-child(5)').text();
				let point = $('.orderList tr:nth-child('+i+') td:nth-child(6)').text();
				let delivery = $('.orderList tr:nth-child('+i+') td:nth-child(7)').text();
				let total = $('.orderList tr:nth-child('+i+') td:nth-child(8)').text();
				
				//orderList.push('{prodNo: '+prodNo+', count:'+count+', price:'+price+', discount:'+discount+', point:'+point+', delivery:'+delivery+', total:'+total+'}')
				prodInfoList.push(prodNo.split(',').join(""));
				prodInfoList.push(count.split(',').join(""));
				prodInfoList.push(price.split(',').join(""));
				prodInfoList.push(discount.split(',').join("").replace('%', ''));
				prodInfoList.push(point.split(',').join(""));
				prodInfoList.push(delivery.split(',').join(""));
				prodInfoList.push(total.split(',').join(""));
				orderList.push(prodInfoList);
			}
			i++;
		})
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
	
});