<#ftl encoding="UTF-8">
<#include 'base.ftl'>

<#macro title>
    <title>Profile</title>
    <link rel="shortcut icon" href="/img_3.png" type="image/png">
</#macro>

<#macro content>

    <#if detailUser?has_content>
        <br>
        <h1>User page</h1>
        <br>
        <table>
            <tr>
                <td>
                    <#if detailUser.avatarUrl?has_content>
                         <img alt="user_img" src="${detailUser.avatarUrl}" width="150" height="150" class="hover-square">
                    <#else>
                        <img alt="user_img" src="/img.png" width="150" height="150" class="hover-square">
                </#if>
                </td>
                <td>
                    <table>
                        <tr>
                            <td>
                                <h2>
                                    <strong>${detailUser.nick}</strong>
                                </h2>
                            </td>

                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <br>
        <br>
    <#else>
        <br>
        <p class="lead"><em>Something went wrong</em></p>
        <br>
    </#if>

</#macro>