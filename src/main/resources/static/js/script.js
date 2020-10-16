let delete_buttons = document.querySelectorAll('.btn-del');
let edit_buttons = document.querySelectorAll('.btn-ed');
let inputs = document.querySelectorAll('#placeElem1 .tinput')

console.log(edit_buttons);

let ed_event_listener = function(e){
    let theads = document.querySelectorAll('.tmark');
    let tr = e.target.parentElement.parentElement.parentElement;
    let tds = [];
    let values = [];
    for(let i = 1; i < theads.length; i++){
        if(theads[i].innerText.toLowerCase() != 'photo' && theads[i].innerText.toLowerCase() != 'roles'){
            tds[i] = tr.querySelectorAll('td')[i];
            values[i] = tds[i].innerText;
            console.log(values[i]);

            if(values[i]){
                inputs[i].value = values[i];
                console.log(inputs[i]);
            }

        }


    }
    console.log(values);
    console.log(tr);


}

let del_event_listener = function(e){
    let tr = e.target.parentElement.parentElement.parentElement;
    console.log(tr, arguments);
    console.log(tr.querySelectorAll('td')[0].innerText);
    let tr_id = tr.querySelectorAll('td')[0].innerText;

    let place = document.getElementById('placeElem2');
    let input = document.createElement('input');
    let next_sibling = document.getElementById("placeBefore");
    input.name = "user_id";
    input.value = tr.id;
    input.className = 'hidden-el';
    console.log(input.value);

    place.insertBefore(input, next_sibling);
}
for(let i = 0; i < delete_buttons.length; i++){
    delete_buttons[i].addEventListener('click', del_event_listener);
}
for(let i = 0; i < edit_buttons.length; i++){
    edit_buttons[i].addEventListener('click', ed_event_listener);
}





