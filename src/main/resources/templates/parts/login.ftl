<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> User Name : </label>
        <div class="col-sm-3">
            <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                   class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   placeholder="User name"/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password: </label>
        <div class="col-sm-3">
            <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   placeholder="Password"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
    </div>
    <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Retry password: </label>
            <div class="col-sm-3">
                <input type="password" name="secondPassword"
                       class="form-control ${(secondPasswordError??)?string('is-invalid', '')}"
                       placeholder="Retype password"/>
            <#if secondPasswordError??>
                <div class="invalid-feedback">
                    ${secondPasswordError}
                </div>
            </#if>
            </div>
        </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Email: </label>
        <div class="col-sm-3">
            <input type="email" name="eMail"
                   <#if user??>value="${user.eMail!}</#if>"
                   class="form-control ${(emailError??)?string('is-invalid', '')}" placeholder="example@some.com"/>
            <#if mailError??>
            <div class="invalid-feedback">
                ${emailError}
            </div>
            </#if>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="g-recaptcha" data-sitekey="6LcjImMUAAAAALlOa-F7l29zjEgs9fo5y4Eg_DVZ"></div>
        <#if captchaError??>
        <div class="alert alert-danger" role="alert">
            ${captchaError}
        </div>
        </#if>
    </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
    <button type="submit" class="btn btn-primary"><#if isRegisterForm>Create<#else>Sign in</#if></button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary">Log out</button>
</form>
</#macro>