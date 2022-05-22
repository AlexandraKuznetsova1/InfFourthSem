<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <@title></@title>
    <style>
        body {
            background: white url(/img_1.png) repeat-x fixed;
            background-size: 100%;
        }

        .mainer {
            border: 1px solid white;
            margin: 10px 200px 10px 200px;
            background-color: rgba(0, 0, 0, .5);
            color: #ffffff;
        }

        textarea {
            width: 1100px;
            height: 400px;
        }

        table {
            border-collapse: separate;
        }

        td {
            padding-right: 50px;
        }

        /* Modify the background color */

        .navbar-custom {
            background-color: #cecece;
        }

        /* Modify brand and text color */

        .navbar-custom .navbar-brand,
        .navbar-custom .navbar-text {
            color: black;
        }
    </style>

</head>
<body>

<nav class="navbar navbar-expand-sm navbar-custom">
    <a class="navbar-brand"><img alt="got_img" src="/img_2.png" width="50" height="50" class="rounded-circle"
                                 style=""></a>

    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="navbar-brand" href="/info">About</a>
        </li>
        <li class="nav-item">
            <a class="navbar-brand" href="/secrets">Secrets</a>
        </li>
        <li class="nav-item">
            <a class="navbar-brand" href="/tips">Tips</a>
        </li>
        <li class="nav-item">
            <a class="navbar-brand" href="/users">Users</a>
        </li>
        <li class="nav-item">
            <a class="navbar-brand" href="/secrets/add">Add Secret</a>
        </li>
        <li class="nav-item">
            <a class="navbar-brand" href="/tips/add">Add Tip</a>
        </li>
    </ul>

    <#if user??>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                    <#if user.avatarUrl?has_content>
                        <a href="/profile">
                            <img alt="user_img" src="${user.avatarUrl}" width="50" height="50" class="rounded-circle"
                                 style="margin-top: 20px;">
                        </a>
                    <#else>
                    <a href="/profile">
                    <img alt="user_img" src="img.png" width="50" height="50"
                             class="rounded-circle" style="margin-top: 20px;">
                        </a>
                    </#if>
            </li>
        </ul>
    <#else>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="navbar-brand" href="/signIn">Sign In</a>
            </li>
            <li class="nav-item">
                <a class="navbar-brand" href="/signUp">Sign Up</a>
            </li>
        </ul>
    </#if>

</nav>

<div class="mainer">
    <main role="main" class="container">
        <@content></@content>
    </main>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>