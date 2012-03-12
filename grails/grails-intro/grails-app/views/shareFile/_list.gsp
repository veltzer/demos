<h1>${header}</h1>

 <table>
     <thead>
     <tr>

         <g:sortableColumn property="name" title="${message(code: 'shareFile.name.label', default: 'Name')}"/>

         <g:sortableColumn property="description"
                           title="${message(code: 'shareFile.description.label', default: 'Description')}"/>

         <g:sortableColumn property="size" title="${message(code: 'shareFile.size.label', default: 'Size')}"/>

         <g:sortableColumn property="ext" title="${message(code: 'shareFile.ext.label', default: 'Ext')}"/>

         <th></th>

     </tr>
     </thead>
     <tbody>
     <g:each in="${list}" status="i" var="shareFileInstance">
         <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

             <td>
                 <g:if test="${shareFileInstance.creator.username == session.user.username}">
                 <g:link action="show"
                         id="${shareFileInstance.id}">${fieldValue(bean: shareFileInstance, field: "name")}</g:link></td>
             </g:if>
             <g:else>
                 ${fieldValue(bean: shareFileInstance, field: "name")}
             </g:else>
             <td>${fieldValue(bean: shareFileInstance, field: "description")}</td>

             <td>${fieldValue(bean: shareFileInstance, field: "size")}K</td>

             <td>${fieldValue(bean: shareFileInstance, field: "ext")}</td>

             <td><g:link action="download" params="${[id: shareFileInstance.id]}">
                 <g:message code="shareFile.download" default="Download"/>
             </g:link>
             </td>

         </tr>
     </g:each>
     </tbody>
 </table>
