<#include "base.ftl">

<html lang="en">
<#macro title>
    <title>SignIn</title>
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

</style>

<#macro content>
    <br>
    <h1>Sign In</h1>
    <br>
    <h2><p class="text-danger">Password or email are incorrect</p></h2>
    <br>
    <form action="/signIn" method="post">
        <p class="lead">
            Email:<br>
            <input name="email" type="email" placeholder="Email"/>
        </p>
        <p class="lead">
            Password:<br>
            <input name="password" type="password" placeholder="Password"/>
        </p>
        <br>
        <p class="lead">
            Remember me:
            <input name="rememberMe" type="checkbox">
        </p>
        <br>
        <p class="lead"><input type="submit" value="Enter"></p>
        <br>
    </form>
</#macro>
</html>
