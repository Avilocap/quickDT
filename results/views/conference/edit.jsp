    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    <%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

    <form:form action="conference/edit.do" modelAttribute="conference">

        <form:hidden path="id"/>
        <form:hidden path="version"/>

        
            <acme:textbox path="title" code="conference.title"/>
            <br/>
            
            <acme:textbox path="acronym" code="conference.acronym"/>
            <br/>
            
            <acme:textbox path="venue" code="conference.venue"/>
            <br/>
            
            <acme:textbox path="submissiondeadline" code="conference.submissiondeadline"/>
            <br/>
            
            <acme:textbox path="notificationdeadline" code="conference.notificationdeadline"/>
            <br/>
            
            <acme:textbox path="camera_readydeadline" code="conference.camera_readydeadline"/>
            <br/>
            
            <acme:textbox path="startdate" code="conference.startdate"/>
            <br/>
            
            <acme:textbox path="enddate" code="conference.enddate"/>
            <br/>
            
            <acme:textbox path="summary" code="conference.summary"/>
            <br/>
            
            <acme:textbox path="fee" code="conference.fee"/>
            <br/>
            



        <!---------------------------- BOTONES -------------------------->
        <input class="button" type="submit" name="save"
            value="<spring:message code="general.save" />"/>


        <input type="submit" class="button" name="delete"
            value="<spring:message code="general.delete" />"/>


    </form:form>

    