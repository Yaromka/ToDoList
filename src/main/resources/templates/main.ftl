<#import "parts/common.ftl" as common>
<@common.page>

<#include "parts/taskEdit.ftl" />
<div class="form-row mt-3">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="tag" class="form-control" placeholder="Search by tag">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>

<#include "parts/taskList.ftl" />
</@common.page>