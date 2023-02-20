/*
날짜 : 2023/02/15
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
	
	
	
	/* 선택구분용 함수 */
	function sumChecked(){
		initVal();
		$('input[name=cartNo]').each(function(){
			if($(this).is(":checked")){
				sum($(this));
			}
		});
		inputTotal();
	}
	
	function sumAll(){
		initVal();
		$('input[name=cartNo]').each(function(){
			sum($(this));
		});
		inputTotal();
	}
	
	/* 실제 기능 구현 함수 */
	function initVal(){
		count 	= 0;
		price 	= 0;
		discount= 0;
		point 	= 0;
		delivery= 0;
		total 	= 0;
	}
	function sum(arg){
		let tds = arg.parent().parent().children();
		count 	+= makeNum(tds[2].innerText);
		price 	+= (makeNum(tds[3].innerText) * makeNum(tds[2].innerText));
		discount+= (makeNum(tds[3].innerText) * makeNum(tds[2].innerText) - makeNum(tds[7].innerText) + makeNum(tds[6].innerText));
		point 	+= (makeNum(tds[5].innerText) * makeNum(tds[2].innerText));
		delivery+= makeNum(tds[6].innerText);
		total	+= makeNum(tds[7].innerText);
	}
	function inputTotal(){
		let trs = $('#totalTable > tbody').children();
		trs[0].children[1].innerText = count.toLocaleString();
		trs[1].children[1].innerText = price.toLocaleString();
		trs[2].children[1].innerText = discount.toLocaleString();
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
	
	/* 주문 */
	$('#btnOrder').click(function(e){
		e.preventDefault();
		let orderList = [];
		if ($("input:checkbox[name='cartNo']").is(":checked")==false) {
			$('input[name=cartNo]').each(function(){
				initVal();
				orderList.push(makeProdInfo($(this)));
			});
		}else{
			$('input[name=cartNo]').each(function(){
				if($(this).is(":checked")){
					initVal();
					orderList.push(makeProdInfo($(this)));
				}
			});
		}
		console.log(orderList);
		
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
	
	function makeProdInfo(arg){
		let prodNo 	= makeNum(arg.val());
		let tds 	= arg.parent().parent().children();
		count 		= makeNum(tds[2].innerText);
		price 		= (makeNum(tds[3].innerText) * makeNum(tds[2].innerText));
		discount	= makeNum(tds[4].innerText);
		point		= (makeNum(tds[5].innerText) * makeNum(tds[2].innerText));
		delivery	= makeNum(tds[6].innerText);
		total 		= makeNum(tds[7].innerText);
		let prodName= arg.parent().parent().children('td:nth-child(2)').children('article').children('div').children('h2').children('a').text();
		let descript= arg.parent().parent().children('td:nth-child(2)').children('article').children('div').children('p').text();
		let src 	= arg.parent().parent().children('td:nth-child(2)').children('article').children('a').children('img').attr('src');
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
		return prodInfo;
	}
	
});