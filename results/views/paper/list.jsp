    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>

    <!-- Listing grid -->

    <security:authorize access="hasRole('ADMIN')">
        <a href="paper/create.do"> <spring:message
                code="general.create"/>
        </a>
    </security:authorize>

    <display:table pagesize="5" class="displaytag" keepStatus="true"
                name="papers" requestURI="${requestURI}" id="row">


        <!-- Attributes -->

        
            <spring:message code="paper.title" var="title"/>
            <display:column property="title" title="${title}" sortable="true"/>

            
            <spring:message code="paper.summary" var="summary"/>
            <display:column property="summary" title="${summary}" sortable="true"/>

            
            <spring:message code="paper.document" var="document"/>
            <display:column property="document" title="${document}" sortable="true"/>

            
            <spring:message code="paper.cameraready" var="cameraready"/>
            <display:column property="cameraready" title="${cameraready}" sortable="true"/>

            


        <security:authorize access="hasRole('ADMIN')">
            <display:column>
                <a href="paper/edit.do?paperId=${row.id}"> <spring:message
                        code="general.edit"/>
                </a>
            </display:column>
        </security:authorize>


    </display:table>

    