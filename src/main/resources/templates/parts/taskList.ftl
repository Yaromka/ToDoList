<#include "security.ftl">
<div class="card-columns">
    <#list tasks as task>
        <div class="card my-3">
            <div class="m-2">
                <span>${task.description}</span><br/>
                <i>#${task.tag}</i>
            </div>
            <div class="card-footer text-muted">
                <a href="/user-tasks/${task.author.id}">${task.author.username}</a>
                <#if task.author.id == currentUserId>
                <a class="btn btn-primary" href="/user-tasks/${task.author.id}?task=?{task.id}">Edit</a>
                </#if>
            </div>
        </div>
    <#else>
    There are no tasks
    </#list>
</div>