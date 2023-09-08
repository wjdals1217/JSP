<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${ctxPath}/js/zipcode.js"></script>
<script>
	$(function(){
		$('#btnBuy').click(function(){
			$('#orderForm').submit();
			console.log("submit");
		});
		
	});
</script>
        <div id="sub">
            <div><img src="../images/sub_top_tit2.png" alt="MARKET"></div>
            <section class="market">
                <aside>
                    <img src="../images/sub_aside_cate2_tit.png" alt="장보기"/>

                    <ul class="lnb">
                        <li class="on"><a href="./market.jsp">장보기</a></li>
                    </ul>
                </aside>
                <article class="order">
                    <nav>
                        <img src="../images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                        <p>
                        	<img src="../images/sub_page_nav_ico.gif" alt=""/>
                            HOME > 장보기 > <em>장보기</em>
                        </p>
                    </nav>

                    <!-- 내용 시작 -->
                    <h3>주문상품 확인</h3>
                    <div class="info">
                        <img src="/Farmstory2/thumb/${thumb2}" alt="${pName}">

                        <table border="0">                            
                            <tr>
                                <td>상품명</td>
                                <td>${pName}</td>
                            </tr>
                            <tr>
                                <td>상품코드</td>
                                <td>${pNo}</td>
                            </tr>
                            <tr>
                                <td>배송비</td>
                                <td class="delivery">${deliveryWithComma}</td>
                            </tr>
                            <tr>
                                <td>판매가격</td>
                                <td>${priceWithComma}</td>
                            </tr>
                            <tr>
                                <td>구매수량</td>
                                <td class="count">${count}</td>
                            </tr>
                            <tr>
                                <td>합계</td>
                                <td class="total">${finalPriceWithComma}</td>
                            </tr>
                        </table>
                    </div>
                    <h3>주문정보 입력</h3>
                    <div class="shipping">
                    <form id="orderForm" action="/Farmstory2/market/insertOrder.do" method="post">
                    	<input type="hidden" name="orderProduct" value="${pNo}"/>
                    	<input type="hidden" name="orderCount" value="${count}"/>
                    	<input type="hidden" name="orderDelivery" value="${delivery}"/>
                    	<input type="hidden" name="orderPrice" value="${price}"/>
                    	<input type="hidden" name="orderTotal" value="${finalPrice}"/>
                    	<input type="hidden" name="orderUser" value="${user.uid}"/>
                    	<table>
                            <tr>
                                <td>받는분</td>
                                <td><input type="text" name="receiver" value="${user.name}"></td>
                            </tr>
                            <tr>
                                <td>휴대폰</td>
                                <td><input type="text" name="hp" value="${user.hp}"></td>
                            </tr>
                            <tr>
                                <td>배송주소</td>
                                <td>
                                    <input type="text" name="zip" readonly value="${user.zip}"><button type="button" id="btnZip" onclick="zipcode()">우편번호 검색</button>
                                    <input type="text" name="addr1" placeholder="기본주소 검색" value="${user.addr1}">
                                    <input type="text" name="addr2" placeholder="상세주소 입력" value="${user.addr2}">
                                </td>
                            </tr>
                            <tr>
                                <td>기타</td>
                                <td>
                                    <textarea name="etc"></textarea>
                                </td>
                            </tr>
                        </table>
                    </form>
                    </div>

                    <p>
                    	<a href="#" id="btnShopping"><img src="../images/market_btn_shopping.gif"></a>
                        <a href="#" id="btnBuy"><img src="../images/market_btn_buy.gif" alt="구매하기"></a>
                    </p>
                    <!-- 내용 끝 -->
                </article>
            </section>

        </div>
        
        
<%@ include file="../_footer.jsp" %>