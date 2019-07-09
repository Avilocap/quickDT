    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    <%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

    <form:form action="paper/edit.do" modelAttribute="paper">

        <form:hidden path="id"/>
        <form:hidden path="version"/>

        
            <acme:textbox path="title" code="paper.title"/>
            <br/>
            
            <acme:textbox path="summary" code="paper.summary"/>
            <br/>
            
            <acme:textbox path="document" code="paper.document"/>
            <br/>
            
            <acme:textbox path="cameraready" code="paper.cameraready"/>
            <br/>
            



        <!---------------------------- BOTONES -------------------------->
        <input class="button" type="submit" name="save"
            value="<spring:message code="general.save" />"/>


        <input type="submit" class="button" name="delete"
            value="<spring:message code="general.delete" />"/>


    </form:form>

    