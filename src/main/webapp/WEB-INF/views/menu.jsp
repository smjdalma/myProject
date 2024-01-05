<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Blog2</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- Free jqGrid library -->
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/css/ui.jqgrid.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/jquery.jqgrid.min.js"></script>

<style>
    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {
        height: 1500px
    }

    /* Set gray background color and 100% height */
    .sidenav {
        background-color: #f1f1f1;
        height: 100%;
    }

    footer {
        background-color: #555;
        color: white;
        padding: 15px;
    }

    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
        .sidenav {
            height: auto;
            padding: 15px;
        }

        .row.content {
            height: auto;
        }
    }
</style>
<script>
    $(function () {

        $.ajax({
            type: 'get',
            url: '/menu/menulist',
            dataType: 'json',
            success: function (rData) {
                $.each(rData, function (index, menuDTO) {
                    const menuname = menuDTO.menuname;
                    const url = menuDTO.url;
                    let menuString = "";
                        menuString += "<li>";
                        menuString += "   <a class='btnMenu' href='" + url + "'>" + menuname + "</a>";
                        menuString += "</li>";
                    $("#menuList").append(menuString);
                });
            },
            error: function (error) {
                console.log(error);
            }
        });

        $("#menuList").on("click", ".btnMenu", function(e) {
            e.preventDefault();
            const url = $(this).attr("href");
            // Page Fragment
            $("#content").load(url + " #container");
        });

        // const arrow = (a, b) => a + b;
        // console.log("arrow Result" + arrow(50, 101));
    });
</script>
</head>
<body>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <h4>Sim's Blog</h4>
            <ul id="menuList" class="nav nav-pills nav-stacked">
                <!-- list loaded by ajax -->
            </ul>
            <br>
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search Blog..">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button">
                        <span class="glyphicon glyphicon-search">
                        </span>
                    </button>
                </span>
            </div>
        </div>

        <div class="col-sm-9">
            <div id="content">
                <h1>Main Page</h1>
            </div>
        </div>
    </div>
</div>
<footer class="container-fluid">
    <p>Footer Text</p>
</footer>
</body>
</html>
