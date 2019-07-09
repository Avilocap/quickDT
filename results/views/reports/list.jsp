    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>

    <!-- Listing grid -->

    <security:authorize access="hasRole('ADMIN')">
        <a href="reports/create.do"> <spring:message
                code="general.create"/>
        </a>
    </security:authorize>

    <display:table pagesize="5" class="displaytag" keepStatus="true"
                name="reportss" requestURI="${requestURI}" id="row">


        <!-- Attributes -->

        
            <spring:message code="reports.decision" var="decision"/>
            <display:column property="decision" title="${decision}" sortable="true"/>

            
            <spring:message code="reports.comments" var="comments"/>
            <display:column property="comments" title="${comments}" sortable="true"/>

            
            <spring:message code="reports.originalityscore" var="originalityscore"/>
            <display:column property="originalityscore" title="${originalityscore}" sortable="true"/>

            
            <spring:message code="reports.qualityscore" var="qualityscore"/>
            <display:column property="qualityscore" title="${qualityscore}" sortable="true"/>

            
            <spring:message code="reports.redabilityscore" var="redabilityscore"/>
            <display:column property="redabilityscore" title="${redabilityscore}" sortable="true"/>

            


        <security:authorize access="hasRole('ADMIN')">
            <display:column>
                <a href="reports/edit.do?reportsId=${row.id}"> <spring:message
                        code="general.edit"/>
                </a>
            </display:column>
        </security:authorize>


    </display:table>

    