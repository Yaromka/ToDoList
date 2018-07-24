<#import "parts/common.ftl" as common>

<@common.page>
    <#if isCurrentUser>
    <#include "parts/taskEdit.ftl" />
    </#if>
    <#include "parts/taskList.ftl" />
</@common.page>