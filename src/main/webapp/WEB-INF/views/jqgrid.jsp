<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User CRUD 작업용 페이지</title>
    <!-- BootStrap CDN -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- jQuery UI library -->
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

    <!-- Free jqGrid library -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/css/ui.jqgrid.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/jquery.jqgrid.min.js"></script>

    <style>
        table {
            font-size : 20px;
        }
    </style>
    <script>
        $(function(){
            function fmtBtnDelete(cellvalue, options, rowObject){
                let str = "";
                str += "<div class='btn-group'>";
                str += "    <button type='button' class='btn btn-default btn-sm btnDelete'>삭제</button>";
                str += "</div>";
                return str;
            }

            $("#jqGrid").jqGrid({
                url: "/home/users",
                datatype: "json",
                colNames: ["번호", "이름", "주소", "삭제"],
                colModel: [
                    { label: "번호", name: "num", width: 75, sorttype: "int", align: "center", editable: true},
                    { label: "이름", name: "name", width: 150, align: "center", editable: true, edittype: 'text' },
                    { label: "주소", name: "add", width: 150, align: "center", editable: true, edittype: 'text' },
                    { name: 'btnDelete', index:'btnDelete', width:80, align: "center", formatter:fmtBtnDelete, sortable: false}
                ],
                jsonReader : {
                    // records: "count",
                    page: "result.page",
                    total: "result.total",
                    root: "result.list" // 데이터 시작점
                },
                pagination : true,
                pager : "#jqGridPager",
                rowNum : 10,
                rowList: [10, 20, 30],
                sortname: "num",  //
                sortorder: 'asc', // 이 2개를 쓰니까 (왜인진 모르겠지만) 순서가 뒤죽박죽이 됨
                                  // 해결 : 번호를 string으로 정렬하고 있었음. colModel sorttype: "int" 추가하니 해결됨
                viewrecords: true,
                page : 1,
                loadonce : false, // 이걸 true로하면 페이징은 되는데 리로딩이 안되고, 이걸 false로 하면 페이징이 안됨
                // totalpages : 3,
                // totalrecords : 31,
                // scroll : 1,
                width : "1000px",
                height : "410px", // "auto"로 하면 scroll바가 없어지고 테이블이 조정됨
                caption: 'jqGrid 페이징 예제',
                // cellEdit: true,
                // cellsubmit : 'remote',
                // cellurl : '/home/update',
                // editurl: '/home/update',
                ajaxRowOptions: {
                    contentType: 'application/json; charset=utf-8'
                },
                serializeRowData: function(postdata) {
                    // 보내는 데이터 JSON으로 바인딩하기 때문에, GET메서드는 serializeRowData: (serializeRowData) => serializeRowData로  보내줘야 함
                    return JSON.stringify(postdata);
                },
                ondblClickRow: function (rowid) {
                    var editparameters = {
                        "keys" : true,
                        "url" : "/home/update",
                        "mtype" : "PUT"
                    }
                    $(this).jqGrid('editRow', rowid, editparameters);
                }
            });

            $("#jqGrid").jqGrid('navGrid', '#jqGridPager', {
                    edit: false,
                    add: true,
                    del: false,
                    search: false,
                    refresh: true,
                },
                { }, // default settings for edit
                {
                    // settings for add
                    url: '/home/insert',  // specify your add URL here

                    mtype: 'POST',
                    contentType: 'application/json',

                    addCaption: "Add User",
                    processData: "Saving...",
                    serializeEditData: function(data) {
                        const json = {
                            "num" : data.num,
                            "name" : data.name,
                            "add" : data.add
                        }
                        console.log(JSON.stringify(json));
                        return JSON.stringify(json);
                    },
                    width: 400,
                    closeAfterAdd:true,
                    reloadAfterSubmit:true,
                },
                { }, // default settings for delete
                { }, // search options
                { }  // view parameters
            );

            // arrow함수 사용법
            // function fn(a, b) {
            //     return a + b;
            // }
            // var arrow = (a, b) => a + b

            $("#btnReload").click(function() {
                $("#resultTable").hide();
                $("#jqGrid").jqGrid("setGridParam", {url : "/home/users", page : 1}).trigger("reloadGrid");
            });

            $("#searchInput").on("keyup", function(key){
                if(key.keyCode === 13) {
                    doSearch();
                }
            });

            $("#btnSearch").click(function() {
                doSearch();
            });

            function doSearch() {
                const searchString = $("#searchInput").val();
                const searchField = $("#searchSelection").val();
                const url = "/home/user?searchString=" + searchString + "&searchField=" + searchField;
                $("#jqGrid").jqGrid("setGridParam", {url : url, page : 1}).trigger("reloadGrid");
                // $("#jqGrid").jqGrid("clearGridData");
                // $("#jqGrid").jqGrid("addRowData", 1, result, 'first');
            }

            $("#jqGrid").on("click", ".btnDelete", function(){
                const tr = $(this).parent().parent().parent();
                const num = tr.find("td:first").text();
                $.ajax({
                    type : "delete",
                    url : "/home/delete/" + num,
                    success : function(rData) {
                        $("#divResult").html(JSON.stringify(rData));
                        // 마지막 페이지의 마지막 데이터를 삭제했다면 이전 페이지를 불러오기
                        const rowCount = $("#jqGrid").getGridParam("reccount");
                        const endPage = $("#jqGrid").getGridParam("page");
                        if(rowCount === 1) {
                            $("#jqGrid").setGridParam({"page": (endPage - 1)}).trigger("reloadGrid");
                        } else {
                            $("#jqGrid").trigger("reloadGrid");
                        }
                    },
                    error : function(rData) {
                        $("#divResult").html(JSON.stringify(rData.responseJSON));
                    }
                });
            });

        });
    </script>
</head>
<body>
<div class="container-fluid">
    <div id="divResult">　</div>
    <div class="row" style="width:1000px;">
        <div class="col-md-1">
            <span style="font-size:25px;">Search</span>
        </div>
        <div class="col-md-8">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <select id="searchSelection" class="btn btn-outline-secondary dropdown-toggle">
                        <option value="num">번호</option>
                        <option value="name">이름</option>
                        <option value="add">주소</option>
                    </select>
                </div>
                <input id="searchInput" type="text" class="form-control">
            </div>
        </div>
        <div class="col-md-3">
            <button id="btnSearch" class="btn btn-info" type="button">Search</button>
            <button id="btnReload" class="btn btn-secondary btn-small">Reload</button>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt-4">
            <table id="jqGrid"></table>
            <div id="jqGridPager"></div>
        </div>
    </div>
    <!--
    <div class="row">
        <div class="col-md-12 mt-5">
            <h2>NEW USER</h2>
            <div class="input-group">
                <span class="input-group-text">num</span>
                <input id="inputNum" type="number" placeholder="num" aria-describedby="basic-addon3">
            </div><br>
            <div class="input-group">
                <span class="input-group-text">name</span>
                <input id="inputName" type="text" placeholder="name" aria-describedby="basic-addon3">
            </div><br>
            <div class="input-group">
                <span class="input-group-text">add</span>
                <input id="inputAdd" type="text" placeholder="add" aria-describedby="basic-addon3">
            </div><br>
            <button id="btnInsert" class="btn btn-info" type="button">INSERT</button>
            <button id="btnUpdate" class="btn btn-primary" type="button" style="display: none">UPDATE</button>
        </div>
    </div>
    -->
</div>
</body>
</html>