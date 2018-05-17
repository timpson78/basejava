<%@ page import="webapp.model.ContactType" %>
<%@ page import="webapp.model.SectionType" %>
<%@ page import="webapp.utils.HtmlUtil" %>
<%@ page import="webapp.model.OrganizationSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/css/style.css">
    <jsp:useBean id="resume" type="webapp.model.Resume" scope="request" />
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
<section>
    <form method="post" action="resumes" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd> <input type="text" name="fullname" size="50" value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <p>
            <c:forEach var="contype" items="<%=ContactType.values()%>">
                   <dl>
                     <dt>${contype.title}</dt>
                     <dd><input type="text" name="${contype.name()}" size="30" value="${resume.getContact(contype)}"></dd>
                   </dl>
            </c:forEach>


        <c:forEach var="sectype" items="<%=SectionType.values()%>">
            <jsp:useBean id="sectype"
                         type="webapp.model.SectionType"/>
            <h3>${sectype.title}:</h3>
            <c:choose>
                <c:when test="${sectype eq SectionType.PERSONAL || sectype eq SectionType.OBJECTIVE}">
                    <input type="text" name="${sectype.name()}" value="<%=HtmlUtil.ResumeSectiontoHtml(sectype,resume,false)%>" size="150" >   </br>
                </c:when>
                <c:when test="${sectype eq SectionType.ACHIEVEMENT || sectype eq SectionType.QUALIFICATIONS}">
                    <textarea name="${sectype.name()}" cols="40" rows="5" ><%=HtmlUtil.ResumeSectiontoHtml(sectype,resume,false)%>  </textarea></br>
                </c:when>
                <c:when test="${sectype eq SectionType.EDUCATION || sectype eq SectionType.EXPERIENCE}">
                    <c:set var="section" value="${resume.getSection(sectype)}"/>
                    <jsp:useBean id="section" type="webapp.model.Section"/>
                    <input type="hidden" name="${sectype}"  value="${sectype}" >
                    <c:forEach var="org" items="<%= ((OrganizationSection) section).getOrgList()%>" varStatus="counter">
                        <dl style="background: none" >
                            <c:if test="${sectype eq SectionType.EDUCATION}">
                                <dt style="width: 200px">Наименование учебного заведения:</dt>
                            </c:if>
                            <c:if test="${sectype eq SectionType.EXPERIENCE}">
                                <dt style="width: 200px">Наименование организации:</dt>
                            </c:if>
                                <dd><input type="text" name="${sectype}_org"  value="${org.orgLink.linkName}"  style="width: 600px"> </dd>
                                <br>
                                <br>
                                <dt style="width: 200px">Сайт организации:</dt>
                                <dd><input type="text" name="${sectype}_url"  value="${org.orgLink.urlName}"  style="width: 300px">  </dd>
                            </dl>

                    <c:forEach var="pos" items="${org.positions}" >
                        <jsp:useBean id="pos" type="webapp.model.Organization.Position"/>
                            <dl style="background: none">
                                <p>Период работы:</p>
                                <dt style="width: 30px">c:</dt>
                                <dd><input type="date" name="${sectype}_startdate_${counter.index}"  value="${pos.startDate}"  style="width: 130px" > </dd>
                                <dt style="width: 30px">по:</dt>
                                <dd><input type="date" name="${sectype}_enddate_${counter.index}"  value="${pos.endDate}"  style="width: 130px" > </dd>
                            </dl>
                            <br>

                            <dl style="background: none">
                                <c:if test="${sectype eq SectionType.EDUCATION}">
                                    <dt style="width: 200px">Специальность:</dt>
                                </c:if>
                                <c:if test="${sectype eq SectionType.EXPERIENCE}">
                                    <dt style="width: 200px">Должность:</dt>
                                </c:if>
                                <dd><input type="text" name="${sectype}_title_${counter.index}"  value="${pos.title}"  style="width: 600px"> </dd>
                            </dl>
                            <dl style="background: none">
                                <dt style="width: 200px">Описание:</dt>
                                <dd> <textarea name="${sectype}_description_${counter.index}" cols="80" rows="4">${pos.description} </textarea> </dd>
                            </dl>
                            <br>
                    </c:forEach>
                    </c:forEach>
                    <c:if test="${resume.fullName!=null}">
                    <c:if test="${sectype eq SectionType.EDUCATION}">
                        <h4>Добавление нового учебного заведения:</h4>
                    </c:if>
                    <c:if test="${sectype eq SectionType.EXPERIENCE}">
                        <h4>Добавление новой организации:</h4>
                    </c:if>
                    <dl style="background: none" >
                        <c:if test="${sectype eq SectionType.EDUCATION}">
                            <dt style="width: 200px">Наименование учебного заведения:</dt>
                        </c:if>
                        <c:if test="${sectype eq SectionType.EXPERIENCE}">
                            <dt style="width: 200px">Наименование организации:</dt>
                        </c:if>

                        <dd><input type="text" name="${sectype}_org_new"  value="${org.orgLink.linkName}"  style="width: 600px"> </dd>
                        <br>
                        <br>
                        <dt style="width: 200px">Сайт организации:</dt>
                        <dd><input type="text" name="${sectype}_url_new"  value="${org.orgLink.urlName}"  style="width: 300px">  </dd>
                    </dl>
                    <p>Период работы:</p>
                    <dt style="width: 30px">c:</dt>
                    <dd><input type="date" name="${sectype}_startdate_new"  value="${pos.startDate}"  style="width: 130px"> </dd>
                    <dt style="width: 30px">по:</dt>
                    <dd><input type="date" name="${sectype}_enddate_new"  value="${pos.endDate}"  style="width: 130px"> </dd>
                    </dl>
                    <br>

                    <dl style="background: none">
                        <c:if test="${sectype eq SectionType.EDUCATION}">
                            <dt style="width: 200px">Специальность:</dt>
                        </c:if>
                        <c:if test="${sectype eq SectionType.EXPERIENCE}">
                            <dt style="width: 200px">Должность:</dt>
                        </c:if>
                        <dd><input type="text" name="${sectype}_title_new"  value="${pos.title}"  style="width: 600px"> </dd>
                    </dl>
                    <dl style="background: none">
                        <dt style="width: 200px">Описание:</dt>
                        <dd> <textarea name="${sectype}_description_new" cols="80" rows="4">${pos.description} </textarea> </dd>
                    </dl>
                    </c:if>
                    <br>
                </c:when>
            </c:choose>
        </c:forEach>

        <hr>
        <button type="submit"> Сохранить </button>
        <button onclick="window.history.back()"> Отменить</button>
    </form>

</section>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>