<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<a href="${pageContext.request.contextPath}/">
    <img id="logo" src="${pageContext.request.contextPath}/static/img/site/logo.jpg" class="logo">
</a>

<form action="${pageContext.request.contextPath}/product/search" method="post" id="search_form">
    <div class="searchDiv">
        <input name="query" type="text" value="${query}" placeholder="时尚男鞋  太阳镜 ">
        <button type="submit" class="searchButton">搜索</button>
        <div class="searchBelow">
            <c:forEach items="${cs}" var="c" varStatus="st">
                <c:if test="${st.count>=5 and st.count<=8}">
						<span>
							<a href="${pageContext.request.contextPath}/category/list?cid=${c.id}">
                                    ${c.name}
                            </a>
							<c:if test="${st.count!=8}">
                                <span>|</span>
                            </c:if>
						</span>
                </c:if>
            </c:forEach>
        </div>
    </div>
</form>
