    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>

    <!-- Listing grid -->

    <security:authorize access="hasRole('ADMIN')">
        <a href="conference/create.do"> <spring:message
                code="general.create"/>
        </a>
    </security:authorize>

    <display:table pagesize="5" class="displaytag" keepStatus="true"
                name="conferences" requestURI="${requestURI}" id="row">


        <!-- Attributes -->

        
            <spring:message code="conference.title" var="title"/>
            <display:column property="title" title="${title}" sortable="true"/>

            
            <spring:message code="conference.acronym" var="acronym"/>
            <display:column property="acronym" title="${acronym}" sortable="true"/>

            
            <spring:message code="conference.venue" var="venue"/>
            <display:column property="venue" title="${venue}" sortable="true"/>

            
            <spring:message code="conference.submissiondeadline" var="submissiondeadline"/>
            <display:column property="submissiondeadline" title="${submissiondeadline}" sortable="true"/>

            
            <spring:message code="conference.notificationdeadline" var="notificationdeadline"/>
            <display:column property="notificationdeadline" title="${notificationdeadline}" sortable="true"/>

            
            <spring:message code="conference.camera_readydeadline" var="camera_readydeadline"/>
            <display:column property="camera_readydeadline" title="${camera_readydeadline}" sortable="true"/>

            
            <spring:message code="conference.startdate" var="startdate"/>
            <display:column property="startdate" title="${startdate}" sortable="true"/>

            
            <spring:message code="conference.enddate" var="enddate"/>
            <display:column property="enddate" title="${enddate}" sortable="true"/>

            
            <spring:message code="conference.summary" var="summary"/>
            <display:column property="summary" title="${summary}" sortable="true"/>

            
            <spring:message code="conference.fee" var="fee"/>
            <display:column property="fee" title="${fee}" sortable="true"/>

            


        <security:authorize access="hasRole('ADMIN')">
            <display:column>
                <a href="conference/edit.do?conferenceId=${row.id}"> <spring:message
                        code="general.edit"/>
                </a>
            </display:column>
        </security:authorize>


    </display:table>

    