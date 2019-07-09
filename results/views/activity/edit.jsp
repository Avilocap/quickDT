    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    <%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

    <form:form action="activity/edit.do" modelAttribute="activity">

        <form:hidden path="id"/>
        <form:hidden path="version"/>

        
            <acme:textbox path="title" code="activity.title"/>
            <br/>
            
            <acme:textbox path="speakers" code="activity.speakers"/>
            <br/>
            
            <acme:textbox path="startdate" code="activity.startdate"/>
            <br/>
            
            <acme:textbox path="duration" code="activity.duration"/>
            <br/>
            
            <acme:textbox path="room" code="activity.room"/>
            <br/>
            
            <acme:textbox path="summary" code="activity.summary"/>
            <br/>
            
            <acme:textbox path="attachment" code="activity.attachment"/>
            <br/>
            



        <!---------------------------- BOTONES -------------------------->
        <input class="button" type="submit" name="save"
            value="<spring:message code="general.save" />"/>


        <input type="submit" class="button" name="delete"
            value="<spring:message code="general.delete" />"/>


    </form:form>

    