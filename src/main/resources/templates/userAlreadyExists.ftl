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
    <h1>User with such data already exists!</h1>
    <h2><a href="/signUp">Go back</a></h2>
</#macro>
</html>
