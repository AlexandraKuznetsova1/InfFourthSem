<#ftl encoding='UTF-8'>
<#include "base.ftl">
<html>
<#macro title>
    <title>Profile</title>
    <link rel="shortcut icon" href="/img_3.png" type="image/jpg">
</#macro>
<style>
    @font-face {
        font-family: Helvetica;
        src: url(/fonts/Helvetica.ttf)
    }

    html {
        font-family: Helvetica, system-ui;
    }

    .avatar {
        width: 200px;
        height: 200px;
        border-radius: 50%;
        border: 2px solid black;
        object-fit: cover;
        display: block;
        margin: 20px auto;
    }

    .button_load {
        margin-top: 10px;
        margin-bottom: 40px;
    }

</style>

<#macro content>


    <h1>Your profile</h1>
    <table style="margin-left:auto;margin-right:auto;">
        <tr>
        <tr>
            <#if user.avatarUrl??>
                <td><img alt="user_img" src="${user.avatarUrl}" class="avatar"></td>
            <#else>
                <td><img alt="user_img" src="img.png" class="avatar"></td>
            </#if>
        </tr>
        <td>
            <table>
                <tr>
                    <td>
                        <h2>
                            <strong>Nickname: ${user.nick}</strong>
                        </h2>
                    </td>

                </tr>
                <tr>
                    <td>
                        <h3>
                            <em>Email: ${user.email} </em>
                        </h3>
                    </td>
                </tr>
            </table>
        </td>
        </tr>
    </table>
    <form action="/upload" method="post" novalidate enctype="multipart/form-data" class="button_load">
        <br>
        <p style="font-size:25px">
            Change avatar
        </p>

        <p class="lead">
            <input name="avatar" type="file" accept=".jpg, .png, .jpeg">
        </p>

        <p class="lead">
            <input type="submit" name="upload" value="Change">
        </p>
    </form>

    <br>
        <p class="lead"><a href="/signOut" class="btn btn-primary">Sign Out</a></p>
    <br>
</#macro>
</html>
