//点击新增按钮增加,出现信息表，添加tr
//
var check = document.getElementsByName("checkbox");
var btn = document.getElementById("btn");
var all = document.getElementById("all");
var search = document.getElementById("searchoutdate_btn");
// 全选
all.onclick = function () {
    for (var i = 0; i < check.length; i++) {
        check[i].checked = true;
    }
}
// 反选
reverse.onclick = function () {
    for (var i = 0; i < check.length; i++) {
        check[i].checked = !check[i].checked;
    }
}

//按行删除

function Del(t) {
    if (confirm("是否清除该过期商品")) {
    }
}

//对选择的进行删除
var Select = document.getElementById("select");
Select.onclick = function () {
    // var k=0;
    if (confirm("是否清除这些商品")) {
        var compatibility = "";
        var check = document.getElementsByName("checkbox");
        for (var f = 0; f < check.length; f++) {
            if (check[f].checked == true) {
                compatibility += check[f].value + ",";
            }
        }
        if (compatibility == "") {
            alert("没有选中的信息");
            return;
        }
        compatibility = compatibility.substring(0, compatibility.lastIndexOf(","));
        window.location.href = "GoodsServlet?action=outdelS&comp=" + compatibility;
    }
}
//搜索功能,模糊查询
var search_btn = document.getElementById("search_btn");
search_btn.onclick = function () {
    var search = document.getElementById("search");
    filter = search.value;
    td = document.getElementsByTagName("td");
    th = document.getElementsByClassName("th");
    for (i = 0; i < th.length; i++) {
        th[i].style.background = "";
        if (filter == "")
            continue;
        if (th[i].innerText.indexOf(filter) >= 0) {
            th[i].style.background = "#969696";
        }
    }
    for (i = 0; i < td.length; i++) {
        td[i].style.background = "";
        if ((i + 1) % 7 == 0 || filter == "")
            continue;
        if (td[i].innerText.indexOf(filter) >= 0 && filter != "") {
            td[i].style.background = "#969696";
        }
    }
}

//检索数据库
search.onclick = function (){
    window.location.href = "GoodsServlet?action=outlist";
}


