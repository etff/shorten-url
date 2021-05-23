var main = {
    init: function () {
        var _this = this;

        $("#send").on("click", function () {
            _this.send();
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
            $("#result").html(`생성된 단축 URL은  ${result.link} 입니다.`);

        }).fail(function (error) {
            alert(error.responseJSON.message);
        });
    }
};

main.init();
