<%@ page import="webapp.model.SectionType" %>
<%@ page import="webapp.utils.HtmlUtil" %>
<%@ page import="webapp.model.OrganizationSection" %>
<%@ page import="webapp.model.Organization" %>
<%@ page import="webapp.utils.DateUtil" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.lang.String" %>
<%@ page import="static webapp.utils.DateUtil.*" %>
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
    <h3> ${resume.fullName} &nbsp; <a href="?uuid=${resume.uuid}&action=edit"> <img src="img/pencil.png"></a> </h3>

    <c:forEach var="contactEntry" items="${resume.contacts}">
        <jsp:useBean id="contactEntry"
        type="java.util.Map.Entry<webapp.model.ContactType,java.lang.String>"/>
        <%=contactEntry.getKey().toHtml(contactEntry.getValue()) %> <br/>
    </c:forEach>

    <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<webapp.model.SectionType,webapp.model.Section>"/>
        <c:choose>
        <c:when test="${sectionEntry.key eq SectionType.PERSONAL || sectionEntry.key eq SectionType.OBJECTIVE || sectionEntry.key eq SectionType.ACHIEVEMENT || sectionEntry.key eq SectionType.QUALIFICATIONS}">
            <%=HtmlUtil.TitletoHtml(sectionEntry.getKey().getTitle())%><br/>
            <%=HtmlUtil.ResumeSectiontoHtml(sectionEntry.getKey(),resume,true)%><br/>
        </c:when>
        <c:when test="${sectionEntry.key eq SectionType.EDUCATION || sectionEntry.key eq SectionType.EXPERIENCE}">
            <%=HtmlUtil.TitletoHtml(sectionEntry.getKey().getTitle())%><br/>
            <c:forEach var="org" items="<%= ((OrganizationSection) sectionEntry.getValue()).getOrgList()%>" varStatus="counter">
                <jsp:useBean id="org" type="webapp.model.Organization" />
                <c:forEach var="pos" items="${org.positions}" >

                    <table border="0">
                        <tr>
                            <td width="30" ></td><td width="120" > <table border="0"> <tr> <td>${pos.getFormatedDate("start")} -</td> </tr>  <tr> <td>${pos.getFormatedDate("end")} </td> </tr></table></td><td> <a href="http://${org.orgLink.urlName}"> ${org.orgLink.linkName} </a></td>
                        </tr>
                        <tr>
                            <td> </td><td></td><td><h3> ${pos.title} </h3> </td>
                        </tr>
                        <tr>
                            <td> </td><td></td><td> ${pos.description.replaceAll("\\n","<br />")}   </td>
                        </tr>
                    </table>
                 </c:forEach>
            </c:forEach>
        </c:when>
        </c:choose>
    </c:forEach>




</section>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>