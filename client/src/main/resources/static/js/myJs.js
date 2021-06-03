let modal = $('.basic.modal');

let home = $('.list.icon');
home.sticky({
    context: '#context',
    offset: 60
});
home.click(function () {
    modal.modal('show');
})

/* 数组转json
 * @param array 数组
 * @param type 类型 json array
 */
function formatArray(array, type) {
    let dataArray = {};
    $.each(array, function () {
        if (dataArray[this.name]) {
            if (!dataArray[this.name].push) {
                dataArray[this.name] = [dataArray[this.name]];
            }
            dataArray[this.name].push(this.value || '');
        } else {
            dataArray[this.name] = this.value || '';
        }
    });
    return ((type == "json") ? JSON.stringify(dataArray) : dataArray);
}

//文章分类
$('.menu .classify').popup({
    popup: '.popup',
    hoverable: true,
    delay: {
        show: 0,
        hide: 400
    }
});