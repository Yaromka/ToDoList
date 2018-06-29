<#import "parts/common.ftl" as common>
<@common.page>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new Task
</a>
<div class="collapse <#if task??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" action="add">
            <div class="form-group">
                <input type="text" name="description" class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                       value="<#if task??>${task.description}</#if>" placeholder="Write a task">
                <#if descriptionError??>
                    <div class="invalid-feedback">
                        ${descriptionError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" name="tag" class="form-control ${(tagError??)?string('is-invalid', '')}"
                       value="<#if task??>${task.tag}</#if>" placeholder="Write tag">
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary ml-2">Add</button>
            </div>
        </form>
    </div>
</div>
<div class="form-row mt-3">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="tag" class="form-control" placeholder="Search by tag">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>

<h4>To Do Tasks: </h4>
<div class="card-columns">
    <#list tasks as task>
    <div class="card my-3">
        <div class="m-2">
            <span>${task.description}</span>
        </div>
        <div class="card-footer text-muted">
            <i>${task.tag}</i>
        </div>
    </div>
    <#else>
    There are no tasks
    </#list>
</div>
</@common.page>