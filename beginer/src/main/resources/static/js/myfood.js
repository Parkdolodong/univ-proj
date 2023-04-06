$(document).ready(function () {
// search
    $("#searchButton").click(function () {
        const query = $("#searchBox").val();
        $.get("/apis/search?query=" + query, function (response) {


            var index = response.index;
            var title = response.title;
            var category = response.category;
            var address = response.address;
            var roadAddress = response.roadAddress;
            var homePageLink = response.homepageLink;
            var imageLink = response.imageLink;
            var isVisit = response.isVisit;
            var visitCount = response.visitCount;
            var lastVisitDate = response.islastVisitDateVisit;

            $('#wish_image').attr('src', imageLink);
            $('#wish_title').text(title);
            $('#wish_category').text(category);
            $('#wish_address').text(address);
            $('#wish_road_address').text(roadAddress);
            $('#wish_link').attr('href', homePageLink)

            $('#wishButton').attr('data-wish_image', imageLink)
            $('#wishButton').attr('data-title', title)
            $('#wishButton').attr('data-category', category)
            $('#wishButton').attr('data-address', address)
            $('#wishButton').attr('data-road_address', roadAddress)
            $('#wishButton').attr('data-home_page_link', homePageLink)


            // search_result.search_result = response;
            $('#search-result').attr('style', 'visible');
        });
    });

    // Enter
    $("#searchBox").keydown(function (key) {
       console.log("noting to do...")
    });

    $("#wishButton").click(function () {
        let i = $(this).attr('data-wish_image')
        let t = $(this).attr('data-title')
        let c = $(this).attr('data-category')
        let a = $(this).attr('data-address')
        let r = $(this).attr('data-road_address')
        let u = $(this).attr('data-home_page_link')

        //david
        //todo: 사용자 아이디도 같이 보내야함.
        let obj = {
            "userId":"",
            "imageLink": i,
            "title": t,
            "category": c,
            "address": a,
            "roadAddress": r,
            "homePageLink": u

        }

        //david
        //todo: my 맛집 저정로직 완성필요 url 정의후
        $.ajax({
            type: "POST",
            async: true,
            url: "",
            timeout: 3000,
            data: JSON.stringify(obj),
            contentType: "application/json",
            error: function (request, status, error) {

            },
            success: function (response, status, request) {
                getWishList();
            }
        });
    });

    //DAVID
    //Todo: 삭제 로직 url 추가해야함
//    $("[class*='item-']").on('click', function (){
//        let index = $(this).attr('class');
//        let indexx = index.split('-')[1];
//
//        $.ajax({
//            type: "DELETE" ,
//            async: true ,
//            url: ""+indexx,
//            timeout: 3000
//        });
//        getWishList();
//
//    });

    //DAVID
    //Todo: 삭제 로직 url 추가해야함
    $(document).on("click", "[class*='item-']", function() {
        let index = $(this).attr('class');
        let indexx = index.split('-')[1];
        $.ajax({
            type: "DELETE" ,
            async: false ,
            url: "/api/restaurant/"+indexx,
            timeout: 3000
        });
        getWishList();
    });

    
    // function 
    // deleteItem(index){
       
        // $.ajax({
        //     type: "DELETE" ,
        //     async: true ,
        //     url: "/api/restaurant/"+index,
        //     timeout: 3000
        // });
        // getWishList();
        
    // }

    //david
    //todo: 나의 맛집리스트 가져오는 url추가해야함
    function getWishList() {
        $.get('', function (response){
            const data = response;
            $("#wish-list").empty();
            if (data.length > 0) {
                
                var tb = $("<table />");
                for (var i in data) {
                    var index = data[i].index;
                    var title = data[i].title;
                    var categorye = data[i].category;
                    var address = data[i].address;
                    var roadAddress = data[i].roadAddress;
                    var homePageLink = data[i].homePageLink;
                    var imageLink = data[i].imageLink;
                    var isVisit = data[i].isVisit;
                    var visitCount = data[i].visitCount;
                    var lastVisitDate = data[i].lastVisitDate;

                    var innerHtml = "";
                    // var btn = '<button id="test" onclick="deleteItem(\''+index+'\')">삭제</button>';
                    innerHtml += "<tr>";
                    innerHtml += "<td>"+title+"</td>";
                    innerHtml += "<td>"+categorye+"</td>";
                    innerHtml += "<td>"+address+"</td>";
                    innerHtml += "<td>"+roadAddress+"</td>";
                    // innerHtml += "<td><button class='item11'>삭제</button></td>";
                    innerHtml += "<td><button class='item-"+index+"'>삭제</button></td>";
                    innerHtml +="</tr>";

                    // var row = $("<tr />").append(
                    //     $("<td />").text(title),
                    //     $("<td />").text(categorye),
                    //     $("<td />").text(address),
                    //     $("<td />").text(roadAddress)
                    // );
                    // row.addClass(index);

                    tb.append(innerHtml);
                }
                $("#wish-list").append(tb);
            }
        });
    }
});


