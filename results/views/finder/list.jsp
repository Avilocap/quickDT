    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>

    <!-- Listing grid -->

    <security:authorize access="hasRole('ADMIN')">
        <a href="finder/create.do"> <spring:message
                code="general.create"/>
        </a>
    </security:authorize>

    <display:table pagesize="5" class="displaytag" keepStatus="true"
                name="finders" requestURI="${requestURI}" id="row">


        <!-- Attributes -->

        
            <spring:message code="finder.keyowrd" var="keyowrd"/>
            <display:column property="keyowrd" title="${keyowrd}" sortable="true"/>

            
            <spring:message code="finder.startdate" var="startdate"/>
            <display:column property="startdate" title="${startdate}" sortable="true"/>

            
            <spring:message code="finder.enddate" var="enddate"/>
            <display:column property="enddate" title="${enddate}" sortable="true"/>

            
            <spring:message code="finder.maximumfee" var="maximumfee"/>
            <display:column property="maximumfee" title="${maximumfee}" sortable="true"/>

            


        <security:authorize access="hasRole('ADMIN')">
            <display:column>
                <a href="finder/edit.do?finderId=${row.id}"> <spring:message
                        code="general.edit"/>
                </a>
            </display:column>
        </security:authorize>


    </display:table>

    