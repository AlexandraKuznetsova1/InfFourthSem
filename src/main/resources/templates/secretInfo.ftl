<#ftl encoding="UTF-8">
<#include 'base.ftl'>

<#macro title>
    <title>secret</title>
    <link rel="shortcut icon" href="/img_3.png" type="image/png">
</#macro>

<#macro content>
    <br>
    <#if user??>
        <p class="lead"><a href="/mysecrets"><- Back</a></p>
    <#else>
        <p class="lead"><a href="/secrets"><- Back</a></p>
    </#if>
    <br>

    <#if secret?has_content>
        <br>
        <br>
        <h1>${secret.title}</h1>
        <br>
        <br>
        <br>
        <img src="${secret.photoUrl}" width="709" height="350">
        <br>
        <br>
        <br>
        <div>${secret.text}</div>
        <br>
        <div>
            <table>
                <tr>
                    <td>
                        <a href="/users/${author.id}">
                            <img alt="user_img" src="${author.avatarUrl}" width="50" height="50" class="color-square">
                        </a>
                    </td>

                    <td><strong style="font-size:20px">Nick: ${secret.userNick}</strong></td>
                    <td><small class="text-muted" style="font-size:17px"><em>${secret.date}</em></small></td>
                    <td><small class="text-muted" style="font-size:17px">secret ${secret.id}</small></td>
                </tr>
            </table>
        </div>

        <br>

            <#if comments?has_content>
                <p class="lead">Comments:</p>
                <#list comments as comment>
                    <table>
                        <tr>
                            <td>
                                <a href="/users/${comment.userId}">
                                    <img alt="user_img" src="${comment.userAvatar}" width="50" height="50" class="color-square">
                                </a>
                            </td>
                            <td><strong style="font-size:20px">Nick: ${comment.userNick}</strong></td>
                        </tr>
                    </table>
                    <div class="alert alert-dark" role="alert">
                        <div>${comment.text}</div>
                    </div>
                </#list>
            <#else>
                <p class="lead">Comments Missmatch Exception!</p>
            </#if>

            <#if user?has_content>
                <form action="/comments/secret/${secret.id}" method="post" novalidate>
                    <p class="lead">Insert comment:</p>
                    <p class="lead">
                        <label>
                            <textarea name="text" placeholder="Comment" class="comment" style="width: 710px"></textarea>
                        </label><br>
                    </p>
                    <p class="lead"><input type="submit" value="Save"></p>
                    <br>
                </form>
            <#else>
                <p class="lead">Sign In to leave comments</p>
            </#if>
        </#if>

</#macro>