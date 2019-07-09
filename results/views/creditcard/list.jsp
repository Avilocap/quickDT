    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>

    <!-- Listing grid -->

    <security:authorize access="hasRole('ADMIN')">
        <a href="creditcard/create.do"> <spring:message
                code="general.create"/>
        </a>
    </security:authorize>

    <display:table pagesize="5" class="displaytag" keepStatus="true"
                name="creditcards" requestURI="${requestURI}" id="row">


        <!-- Attributes -->

        
            <spring:message code="creditcard.holder" var="holder"/>
            <display:column property="holder" title="${holder}" sortable="true"/>

            
            <spring:message code="creditcard.number" var="number"/>
            <display:column property="number" title="${number}" sortable="true"/>

            
            <spring:message code="creditcard.brand" var="brand"/>
            <display:column property="brand" title="${brand}" sortable="true"/>

            
            <spring:message code="creditcard.expiration" var="expiration"/>
            <display:column property="expiration" title="${expiration}" sortable="true"/>

            
            <spring:message code="creditcard.cvv" var="cvv"/>
            <display:column property="cvv" title="${cvv}" sortable="true"/>

            


        <security:authorize access="hasRole('ADMIN')">
            <display:column>
                <a href="creditcard/edit.do?creditcardId=${row.id}"> <spring:message
                        code="general.edit"/>
                </a>
            </display:column>
        </security:authorize>


    </display:table>

    