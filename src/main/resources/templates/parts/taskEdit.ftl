<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Task editor
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
                <input type="text" name="tag" class="form-control"
                       value="<#if task??>${task.tag}</#if>" placeholder="Write tag">
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if task??>${task.id}</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary ml-2">Save task</button>
            </div>
        </form>
    </div>
</div>