    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>

    <!-- Listing grid -->

    <security:authorize access="hasRole('ADMIN')">
        <a href="message/create.do"> <spring:message
                code="general.create"/>
        </a>
    </security:authorize>

    <display:table pagesize="5" class="displaytag" keepStatus="true"
                name="messages" requestURI="${requestURI}" id="row">


        <!-- Attributes -->

        
            <spring:message code="message.senddate" var="senddate"/>
            <display:column property="senddate" title="${senddate}" sortable="true"/>

            
            <spring:message code="message.subject" var="subject"/>
            <display:column property="subject" title="${subject}" sortable="true"/>

            
            <spring:message code="message.body" var="body"/>
            <display:column property="body" title="${body}" sortable="true"/>

            
            <spring:message code="message.topic" var="topic"/>
            <display:column property="topic" title="${topic}" sortable="true"/>

            


        <security:authorize access="hasRole('ADMIN')">
            <display:column>
                <a href="message/edit.do?messageId=${row.id}"> <spring:message
                        code="general.edit"/>
                </a>
            </display:column>
        </security:authorize>


    </display:table>

    