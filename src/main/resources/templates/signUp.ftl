<#include "base.ftl">
<html>

<#macro title>
    <title>Sign Up</title>
    <link rel="shortcut icon" href="/img_3.png" type="image/png">
</#macro>
<style>
    @font-face {
        font-family: Helvetica;
        src: url(/fonts/Helvetica.ttf)
    }

    html {
        font-family: Helvetica, system-ui;
    }

    form {
        display: flex;
        flex-direction: column;
    }

    input {
        margin-top: 20px;
    }
</style>

<#macro content>
    <script>
        const form  = document.getElementById("form");
        let isValid = true;
        function validFunction() {
            return isValid;
        }

        function checkEmail(email) {
            const xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (this.readyState === 4 && this.status === 200) {
                    if (this.responseText === "email taken") {
                        document.getElementById("error").innerHTML = "Email is already taken";
                        isValid = false
                    } else {
                        document.getElementById("error").innerHTML = ""
                        document.getElementById("error").className = "error"
                        isValid = true
                    }
                }
            }
            xmlhttp.open("GET","/checkSignUp/email/" + email, true);
            xmlhttp.send();
        }
        function checkNick(nickname) {
            const xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (this.readyState === 4 && this.status === 200) {
                    if (this.responseText === "nick taken") {
                        document.getElementById("error").innerHTML = "Nickname is already taken";
                        isValid = false
                    } else {
                        document.getElementById("error").innerHTML = ""
                        document.getElementById("error").className = "error"
                        isValid = true
                    }
                }
            }
            xmlhttp.open("GET","/checkSignUp/nick/" + nickname, true);
            xmlhttp.send();
        }
        const nickname = document.getElementById("userNick");
        nickname.addEventListener("input", function (event) {
            if (nickname.validity.valid) {
                nickname.setCustomValidity("");
            } else {
                nickname.setCustomValidity("Must contain not less than 4 characters");
            }
        });
        const password = document.getElementById("userPass");
        password.addEventListener("input", function (event) {
            if (password.validity.valid) {
                password.setCustomValidity("");
            } else {
                password.setCustomValidity("Must contain not less than 4 characters");
            }
        });
        const email = document.getElementById("userEmail");
        email.addEventListener("input", function (event) {
            if (email.validity.typeMismatch) {
                email.setCustomValidity("Enter your email");
            } else {
                email.setCustomValidity("");
            }
        });
    </script>
    <br>
    <h1>Sign Up</h1>
    <br>
    <br>
    <div id="error" class="error active"></div>
    <br>
    <form id="form" action="/signUp" method="post" onsubmit="return validFunction()">

        <p class="lead">
            Nickname:
            <input id="userNick" name="nick" type="text" placeholder="NickName" minlength="5" onkeyup="checkNick(this.value)" required/>
        </p>
        <p class="lead">
            Email:
            <input id="userEmail" name="email" type="email" placeholder="Email" onkeyup="checkEmail(this.value)" required/>
        </p>
        <p class="lead">
            Password:
            <input id="userPass" name="password" type="password" placeholder="Password" minlength="5" required/>
        </p>
        <input type="submit" value="Sign Up">
        <br>
        <br>
    </form>
</#macro>
</html>
