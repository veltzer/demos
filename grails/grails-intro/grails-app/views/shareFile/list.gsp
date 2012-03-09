<%@ page import="com.sharefile.model.ShareFile" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'shareFile.label', default: 'ShareFile')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-shareFile" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-shareFile" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
     <div class="message" role="status">${flash.message}</div>
 </g:if>
    <g:render template="list" model="${[list: shareFileInstanceList, header: 'My upload']}"/>
    <div style="height: 20px; background-color: #ddd;"></div>
    <g:render template="list" model="${[list: otherFiles, header: 'Shared files']}"/>
</div>
</body>
</html>
