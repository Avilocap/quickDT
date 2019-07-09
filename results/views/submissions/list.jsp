    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>

    <!-- Listing grid -->

    <security:authorize access="hasRole('ADMIN')">
        <a href="submissions/create.do"> <spring:message
                code="general.create"/>
        </a>
    </security:authorize>

    <display:table pagesize="5" class="displaytag" keepStatus="true"
                name="submissionss" requestURI="${requestURI}" id="row">


        <!-- Attributes -->

        
            <spring:message code="submissions.ticker" var="ticker"/>
            <display:column property="ticker" title="${ticker}" sortable="true"/>

            
            <spring:message code="submissions.moment" var="moment"/>
            <display:column property="moment" title="${moment}" sortable="true"/>

            
            <spring:message code="submissions.status" var="status"/>
            <display:column property="status" title="${status}" sortable="true"/>

            


        <security:authorize access="hasRole('ADMIN')">
            <display:column>
                <a href="submissions/edit.do?submissionsId=${row.id}"> <spring:message
                        code="general.edit"/>
                </a>
            </display:column>
        </security:authorize>


    </display:table>

    