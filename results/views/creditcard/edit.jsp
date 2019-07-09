    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    <%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

    <form:form action="creditcard/edit.do" modelAttribute="creditcard">

        <form:hidden path="id"/>
        <form:hidden path="version"/>

        
            <acme:textbox path="holder" code="creditcard.holder"/>
            <br/>
            
            <acme:textbox path="number" code="creditcard.number"/>
            <br/>
            
            <acme:textbox path="brand" code="creditcard.brand"/>
            <br/>
            
            <acme:textbox path="expiration" code="creditcard.expiration"/>
            <br/>
            
            <acme:textbox path="cvv" code="creditcard.cvv"/>
            <br/>
            



        <!---------------------------- BOTONES -------------------------->
        <input class="button" type="submit" name="save"
            value="<spring:message code="general.save" />"/>


        <input type="submit" class="button" name="delete"
            value="<spring:message code="general.delete" />"/>


    </form:form>

    