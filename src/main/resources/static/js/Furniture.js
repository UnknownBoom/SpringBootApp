function EditFurniture(e) {
    var tbody;
    table = document.getElementById('table'); //таблица найдена
    var i = e.parentNode.parentNode.parentElement.rowIndex; //индекс строки взят
    console.log(i);
    var tr = document.getElementById('table').getElementsByTagName('tr')[i];
    document.getElementById('edit_article').value = tr.childNodes[1].textContent;
    document.getElementById('edit_furniture_type').value = tr.childNodes[3].textContent;
    document.getElementById('edit_unit').value = tr.childNodes[5].textContent;
    document.getElementById('edit_amount').value = tr.childNodes[7].textContent;
    document.getElementById('edit_supplier_name').value = tr.childNodes[9].textContent;
    document.getElementById('edit_purchase_price').value = tr.childNodes[11].textContent;
    document.getElementById('edit_weight').value = tr.childNodes[13].textContent;
}

function DeleteFurniture(e) {
    var tbody;
    table = document.getElementById('table'); //таблица найдена
    var i = e.parentNode.parentNode.parentElement.rowIndex; //индекс строки взят
    console.log(i);
    var tr = document.getElementById('table').getElementsByTagName('tr')[i];
    document.getElementById('delete_id').value = tr.childNodes[1].textContent;
}