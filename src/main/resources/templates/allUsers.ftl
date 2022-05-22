<#ftl encoding="UTF-8">
<#include 'base.ftl'>

<#macro title>
    <title>All secrets</title>
    <link rel="shortcut icon" href="/img_3.png" type="image/png">

</#macro>

<#macro content>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        window.onload = function showAll() {
            let request = new XMLHttpRequest()
            request.open('GET', '/api/users', false)
            request.send()


            let html = ""

            let response = JSON.parse(request.response)

            for (let i = 0; i < response.length; i++) {
                html += "<a href='/users/" + response[i]['id'] + "'/>"
                html += "<div class='alert alert-dark' role='alert'>"
                html += "<table><tr>"
                html += "<td><img alt='user_img' src='" + response[i]['avatarUrl'] +
                    "' width='50' height='50' class='rounded-circle'></td>"
                html += "<td><h3>"
                html += "<strong>" + response[i]['nick'] + "</strong>"
                html += "</h3></td></tr></table></div></a>"
            }
            document.getElementById('result').innerHTML = html
        }

        function showResult(nickname) {
            const xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    let html = ""

                    let response = JSON.parse(xmlhttp.response)

                    for (let i = 0; i < response.length; i++) {
                        html += "<a href='/users/" + response[i]['id'] + "'/>"
                        html += "<div class='alert alert-dark' role='alert'>"
                        html += "<table><tr>"
                        html += "<td><img alt='user_img' src='" + response[i]['avatarUrl'] +
                            "' width='50' height='50' class='rounded-circle'/></td>"
                        html += "<td><h3>"
                        html += "<strong>" + response[i]['nick'] + "</strong>"
                        html += "</h3></td></tr></table></div></a>"
                    }
                    document.getElementById('result').innerHTML = html
                }
            }
            xmlhttp.open("GET", "/api/users/" + nickname, true);
            xmlhttp.send();
        }

    </script>

    <br>
    <h1>All users</h1>
    <br>

    <form>
        <p class="lead" id="1" style="float: left; margin-right: 50px;">
            Search for nickname:<br>
            <input name="nickname" type="text" onkeyup="showResult(this.value)"><br>
        </p>
    </form>

    <br>
    <br>
    <br>
    <br>

    <div id="result"></div>

</#macro>