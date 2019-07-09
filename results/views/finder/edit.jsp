    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    <%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

    <form:form action="finder/edit.do" modelAttribute="finder">

        <form:hidden path="id"/>
        <form:hidden path="version"/>

        
            <acme:textbox path="keyowrd" code="finder.keyowrd"/>
            <br/>
            
            <acme:textbox path="startdate" code="finder.startdate"/>
            <br/>
            
            <acme:textbox path="enddate" code="finder.enddate"/>
            <br/>
            
            <acme:textbox path="maximumfee" code="finder.maximumfee"/>
            <br/>
            



        <!---------------------------- BOTONES -------------------------->
        <input class="button" type="submit" name="save"
            value="<spring:message code="general.save" />"/>


        <input type="submit" class="button" name="delete"
            value="<spring:message code="general.delete" />"/>


    </form:form>

    