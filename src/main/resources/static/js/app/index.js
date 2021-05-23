var main = {
    init: function () {
        var _this = this;

        $("#send").on("click", function () {
            _this.send();
        });
        $("#link").on('keyup', function (e) {
            if (e.key === 'Enter' || e.keyCode === 13) {
                _this.send();
            }
        });
    },

    send: function () {
        var param = {
            link: $('#link').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/link',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(param)
        }).done(function (data) {
            var result = data;
            $("#result").html(`<h2>생성된 단축 URL은 <a href="${result.link}">${result.link} </a> 입니다.</h2>`);

        }).fail(function (error) {
            alert("오류가 발생했습니다");
        });
    }
};

main.init();
