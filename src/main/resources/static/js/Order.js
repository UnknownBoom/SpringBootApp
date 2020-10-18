function EditOrder(e) {
    var tbody;
    table = document.getElementById('table'); //таблица найдена
    var i = e.parentNode.parentNode.parentElement.rowIndex; //индекс строки взят
    console.log(i);
    var tr = document.getElementById('table').getElementsByTagName('tr')[i];
    document.getElementById('edit_id').value = tr.childNodes[1].textContent;
    document.getElementById('edit_date').value = tr.childNodes[3].textContent;
    document.getElementById('edit_order_name').value = tr.childNodes[5].textContent;
    document.getElementById('product_type_enum').value = tr.childNodes[7].textContent;
    document.getElementById('edit_customer_username').value = tr.childNodes[9].textContent;
    document.getElementById('edit_manager_username').value = tr.childNodes[11].textContent;
        document.getElementById('edit_planed_date_end').value = tr.childNodes[13].textContent;
        document.getElementById('edit_price').value = tr.childNodes[15].textContent;
}

function DeleteOrder(e) {
    var tbody;
    table = document.getElementById('table'); //таблица найдена
    var i = e.parentNode.parentNode.parentElement.rowIndex; //индекс строки взят
    console.log(i);
    var tr = document.getElementById('table').getElementsByTagName('tr')[i];
    document.getElementById('delete_id').value = tr.childNodes[1].textContent;
}













