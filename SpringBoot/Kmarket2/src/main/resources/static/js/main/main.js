/**
 * 
 */

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
$('#product > section > article.info > div.summary > nav:nth-child(2) > h5 > a').click(function(){
	let location = document.querySelector("#product > section > article.review > nav > h1").offsetTop;
	window.scrollTo({top:location, behavior:'smooth'});
});