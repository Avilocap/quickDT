    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>

    <!-- Listing grid -->

    <security:authorize access="hasRole('ADMIN')">
        <a href="activity/create.do"> <spring:message
                code="general.create"/>
        </a>
    </security:authorize>

    <display:table pagesize="5" class="displaytag" keepStatus="true"
                name="activitys" requestURI="${requestURI}" id="row">


        <!-- Attributes -->

        
            <spring:message code="activity.title" var="title"/>
            <display:column property="title" title="${title}" sortable="true"/>

            
            <spring:message code="activity.speakers" var="speakers"/>
            <display:column property="speakers" title="${speakers}" sortable="true"/>

            
            <spring:message code="activity.startdate" var="startdate"/>
            <display:column property="startdate" title="${startdate}" sortable="true"/>

            
            <spring:message code="activity.duration" var="duration"/>
            <display:column property="duration" title="${duration}" sortable="true"/>

            
            <spring:message code="activity.room" var="room"/>
            <display:column property="room" title="${room}" sortable="true"/>

            
            <spring:message code="activity.summary" var="summary"/>
            <display:column property="summary" title="${summary}" sortable="true"/>

            
            <spring:message code="activity.attachment" var="attachment"/>
            <display:column property="attachment" title="${attachment}" sortable="true"/>

            


        <security:authorize access="hasRole('ADMIN')">
            <display:column>
                <a href="activity/edit.do?activityId=${row.id}"> <spring:message
                        code="general.edit"/>
                </a>
            </display:column>
        </security:authorize>


    </display:table>

    