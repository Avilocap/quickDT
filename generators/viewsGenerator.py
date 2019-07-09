import os 
def createViews(entityJson):

    

    entityName = entityJson["Name"]
    entityProperties = entityJson["Properties"]

    claseminusculas = entityName.lower()
    claseMayusculas = entityName.capitalize()

    if not (os.path.exists("./results/views")):
        os.mkdir("./results/views")

    if not (os.path.exists("./results/views/"+claseminusculas)):
        os.mkdir("./results/views/"+claseminusculas)



    propertiesListBlock = ""
    propertiesEditBlock = ""
    propertiesHiddenBlock = ""
    propertiesMessageBlock = ""

    for prop in entityProperties:
        wantToDisplay = prop["Display"]
        if (wantToDisplay):
            propName = prop["Name"]
            propCapitalize = propName.capitalize()
            propLowerCase = propName.lower()

            propertyListBlock = '''
            <spring:message code="'''+claseminusculas+'''.'''+propLowerCase+'''" var="'''+propLowerCase+'''"/>
            <display:column property="'''+propLowerCase+'''" title="${'''+propLowerCase+'''}" sortable="true"/>

            '''
            propertiesListBlock = propertiesListBlock + propertyListBlock

            propertyEditBlock = '''
            <acme:textbox path="'''+propLowerCase+'''" code="'''+claseminusculas+'''.'''+propLowerCase+'''"/>
            <br/>
            '''
            propertiesEditBlock = propertiesEditBlock + propertyEditBlock

            propertyMessageBlock = '''    
'''+claseminusculas+'''.'''+propLowerCase+'''='''+propCapitalize+''''''
            propertiesMessageBlock = propertiesMessageBlock + propertyMessageBlock

        else:
            propName = prop["Name"]
            propLowerCase = propName.lower()
            propertyHidden = '''
        <form:hidden path="'''+propLowerCase+'''"/>
        '''
            propertiesHiddenBlock = propertiesHiddenBlock + propertyHidden


    listview = '''    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>

    <!-- Listing grid -->

    <security:authorize access="hasRole('ADMIN')">
        <a href="'''+claseminusculas+'''/create.do"> <spring:message
                code="general.create"/>
        </a>
    </security:authorize>

    <display:table pagesize="5" class="displaytag" keepStatus="true"
                name="'''+claseminusculas+'''s" requestURI="${requestURI}" id="row">


        <!-- Attributes -->

        '''+propertiesListBlock+'''


        <security:authorize access="hasRole('ADMIN')">
            <display:column>
                <a href="'''+claseminusculas+'''/edit.do?'''+claseminusculas+'''Id=${row.id}"> <spring:message
                        code="general.edit"/>
                </a>
            </display:column>
        </security:authorize>


    </display:table>

    '''


    editview = '''    
    <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    <%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

    <form:form action="'''+claseminusculas+'''/edit.do" modelAttribute="'''+claseminusculas+'''">

        <form:hidden path="id"/>
        <form:hidden path="version"/>'''+propertiesHiddenBlock+'''

        '''+propertiesEditBlock+'''



        <!---------------------------- BOTONES -------------------------->
        <input class="button" type="submit" name="save"
            value="<spring:message code="general.save" />"/>


        <input type="submit" class="button" name="delete"
            value="<spring:message code="general.delete" />"/>


    </form:form>

    '''



    tilesEn = '''<?xml version="1.0" encoding="UTF-8" ?>

    <!-- 
    * tiles.xml
    *
    * Copyright (C) 2018 Universidad de Sevilla
    * 
    * The use of this project is hereby constrained to the conditions of the 
    * TDG Licence, a copy of which you may download from 
    * http://www.tdg-seville.info/License.html
    -->

    <!DOCTYPE tiles-definitions PUBLIC
        "-//Apache Software Foundation//DTD Tiles Configuration 2.0//EN"
        "http://tiles.apache.org/dtds/tiles-config_2_0.dtd">

    <tiles-definitions>

        <definition name="'''+claseminusculas+'''/edit" extends="master.page">
            <put-attribute name="title" value="'''+claseMayusculas+''': Edit" />
            <put-attribute name="body" value="/views/'''+claseminusculas+'''/edit.jsp" />
        </definition>
        
        <definition name="'''+claseminusculas+'''/list" extends="master.page">
            <put-attribute name="title" value="'''+claseMayusculas+''': List" />
            <put-attribute name="body" value="/views/'''+claseminusculas+'''/list.jsp" />
        </definition>
        
    </tiles-definitions>

    '''

    tilesEs = '''<?xml version="1.0" encoding="UTF-8" ?>

    <!-- 
    * tiles.xml
    *
    * Copyright (C) 2018 Universidad de Sevilla
    * 
    * The use of this project is hereby constrained to the conditions of the 
    * TDG Licence, a copy of which you may download from 
    * http://www.tdg-seville.info/License.html
    -->

    <!DOCTYPE tiles-definitions PUBLIC
        "-//Apache Software Foundation//DTD Tiles Configuration 2.0//EN"
        "http://tiles.apache.org/dtds/tiles-config_2_0.dtd">


    <tiles-definitions>

        <definition name="'''+claseminusculas+'''/edit" extends="master.page">
            <put-attribute name="title" value="'''+claseMayusculas+''': Edición" />
            <put-attribute name="body" value="/views/'''+claseminusculas+'''/edit.jsp" />
        </definition>
        
        <definition name="'''+claseminusculas+'''/list" extends="master.page">
            <put-attribute name="title" value="'''+claseMayusculas+''': Listado" />
            <put-attribute name="body" value="/views/'''+claseminusculas+'''/list.jsp" />
        </definition>
        
    </tiles-definitions>

    '''



    text_file = open(f"results/views/"+claseminusculas+"/list.jsp", "w")
    text_file.write(listview)
    text_file.close()

    text_file = open(f"results/views/"+claseminusculas+"/edit.jsp", "w")
    text_file.write(editview)
    text_file.close()

    text_file = open(f"results/views/"+claseminusculas+"/messages.properties", "w")
    text_file.write(propertiesMessageBlock)
    text_file.close()

    text_file = open(f"results/views/"+claseminusculas+"/messages_es.properties", "w")
    text_file.write(propertiesMessageBlock)
    text_file.close()

    text_file = open(f"results/views/"+claseminusculas+"/tiles.xml", "w")
    text_file.write(tilesEn)
    text_file.close()

    text_file = open(f"results/views/"+claseminusculas+"/tiles_es.xml", "w")
    text_file.write(tilesEs)
    text_file.close()
