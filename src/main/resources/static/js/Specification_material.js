function EditSpecification_material(e) {
    var tbody;
    table = document.getElementById('table'); //таблица найдена
    var i = e.parentNode.parentNode.parentElement.rowIndex; //индекс строки взят
    console.log(i);
    var tr = document.getElementById('table').getElementsByTagName('tr')[i];
    document.getElementById('edit_id').value = tr.childNodes[1].textContent;
    document.getElementById('edit_product_type').value = tr.childNodes[3].textContent;
    document.getElementById('edit_material_type').value = tr.childNodes[5].textContent;
    document.getElementById('edit_amount').value = tr.childNodes[7].textContent;
}

function DellSpecification_material(e) {
    var tbody;
    table = document.getElementById('table'); //таблица найдена
    var i = e.parentNode.parentNode.parentElement.rowIndex; //индекс строки взят
    console.log(i);
    var tr = document.getElementById('table').getElementsByTagName('tr')[i];
    document.getElementById('delete_id').value = tr.childNodes[1].textContent;
}







