<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>RuneTracker</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"
            async
    ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"
            async
    ></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"
            async
    ></script>

    <style>
        .footer {
            position: absolute;
            bottom: 0;
            background-color: #f5f5f5;
            width: 100%;
            line-height: 40px;
            height: 40px;
        }
    </style>
</head>
<body>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container justify-content-between">
            <a href="/" class="navbar-brand">
                RuneTracker
            </a>
            <form class="form-inline">
                <input class="form-control mr-sm-2" type="search" placeholder="Lookup user" aria-label="Search">
                <button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
</header>
<div class="container">
    <h1>Data points</h1>

    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th>User</th>
            <th>When</th>
            <th>Options</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Robin</td>
            <td>01/01/01 00:00:00</td>
            <td>
                <button type="button" class="btn btn-primary">View</button>
                <button type="button" class="btn btn-primary">Edit</button>
                <button type="button" class="btn btn-danger">Delete</button>
            </td>
        </tr>
        <tr>
            <td>Robin</td>
            <td>01/01/01 00:00:00</td>
            <td>
                <button type="button" class="btn btn-primary">View</button>
                <button type="button" class="btn btn-primary">Edit</button>
                <button type="button" class="btn btn-danger">Delete</button>
            </td>
        </tr>
        <tr>
            <td>Robin</td>
            <td>01/01/01 00:00:00</td>
            <td>
                <button type="button" class="btn btn-primary">View</button>
                <button type="button" class="btn btn-primary">Edit</button>
                <button type="button" class="btn btn-danger">Delete</button>
            </td>
        </tr>
        </tbody>
    </table>

    <a href="/">Add a datapoint</a>
</div>
<footer class="footer">
    <div class="container">
        <div>
            <p>
                Made by <a href="//robinvdb.me" target="_blank" rel="noopener">Robin Van den Broeck</a> for IP-Major.
                View the source code at <a
                    href="https://github.com/ucll-ip-2018/project-internet-programming-2018-RobinVdBroeck"
                    target="_blank" rel="noopener">Github</a>
            </p>
        </div>
    </div>
</footer>


</body>
</html>
