    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    <%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

    <form:form action="actor/edit.do" modelAttribute="actor">

        <form:hidden path="id"/>
        <form:hidden path="version"/>
        <form:hidden path="photo"/>
        

        
            <acme:textbox path="name" code="actor.name"/>
            <br/>
            
            <acme:textbox path="middlename" code="actor.middlename"/>
            <br/>
            
            <acme:textbox path="surname" code="actor.surname"/>
            <br/>
            
            <acme:textbox path="email" code="actor.email"/>
            <br/>
            
            <acme:textbox path="phone" code="actor.phone"/>
            <br/>
            
            <acme:textbox path="address" code="actor.address"/>
            <br/>
            



        <!---------------------------- BOTONES -------------------------->
        <input class="button" type="submit" name="save"
            value="<spring:message code="general.save" />"/>


        <input type="submit" class="button" name="delete"
            value="<spring:message code="general.delete" />"/>


    </form:form>

    