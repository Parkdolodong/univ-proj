$(document).ready(function(){
  $('#gButton').click(function(){
    let userid = $(this).attr('data-userid');
    console.log("userid="+userid);

    $.get( '/apis/user-info?userid='+userid, function( data ) {
      let nick = data.nick;
      let addr = data.addr;

      let htmlStr = '<p>'+nick+'</p>';
      htmlStr += '<p>'+addr+'</p';

      $( '.result' ).append(htmlStr);
    });
  });

  $('#pButton').click(function(){
    let userid = $(this).attr('data-userid');
    let userpw = $(this).attr('data-userpw');
    let nick = $(this).attr('data-nick');
    let addr = $(this).attr('data-addr');
    console.log("userid="+userid);

    let obj = {
      "userId":userid,
      "userPw":userpw,
      "nick":nick,
      "addr":addr
    };

    $.ajax({
      type: "POST",
      url: "/apis/register",
      data: JSON.stringify(obj),
      contentType: "application/json",
      success: function(res) {
        let nick = res.nick;
        let addr = res.addr;

        let htmlStr = '<p>'+nick+'</p>';
        htmlStr += '<p>'+addr+'</p';

        $( '.result' ).append(htmlStr);
      }

    });

  });
});