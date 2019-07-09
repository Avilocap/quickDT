    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>

    <!-- Listing grid -->

    <security:authorize access="hasRole('ADMIN')">
        <a href="actor/create.do"> <spring:message
                code="general.create"/>
        </a>
    </security:authorize>

    <display:table pagesize="5" class="displaytag" keepStatus="true"
                name="actors" requestURI="${requestURI}" id="row">


        <!-- Attributes -->

        
            <spring:message code="actor.name" var="name"/>
            <display:column property="name" title="${name}" sortable="true"/>

            
            <spring:message code="actor.middlename" var="middlename"/>
            <display:column property="middlename" title="${middlename}" sortable="true"/>

            
            <spring:message code="actor.surname" var="surname"/>
            <display:column property="surname" title="${surname}" sortable="true"/>

            
            <spring:message code="actor.email" var="email"/>
            <display:column property="email" title="${email}" sortable="true"/>

            
            <spring:message code="actor.phone" var="phone"/>
            <display:column property="phone" title="${phone}" sortable="true"/>

            
            <spring:message code="actor.address" var="address"/>
            <display:column property="address" title="${address}" sortable="true"/>

            


        <security:authorize access="hasRole('ADMIN')">
            <display:column>
                <a href="actor/edit.do?actorId=${row.id}"> <spring:message
                        code="general.edit"/>
                </a>
            </display:column>
        </security:authorize>


    </display:table>

    