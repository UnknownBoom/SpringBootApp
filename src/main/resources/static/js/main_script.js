let edit_buttons = document.querySelectorAll('.btn-ed');
let add_buttons = document.querySelectorAll('.btn-add');
let row_elements = [];
let theads = document.querySelectorAll('th.tmark');
console.log(theads);

let add_allow = true;
let add_allow3 = true;

let place1 = document.getElementById('placeElem1');
let place3 = document.getElementById('placeElem3');

let btn_event_listener3 = function(e){
	if(add_allow3){
		for(let i = 1; i < theads.length; i++){
			let thead_id = theads[i].innerText.toLowerCase();

			let inner_place3 = document.createElement('div');

			inner_place3.className = 'form-group';
            let label3 = document.createElement('label');
			label3.className = "col-form-label";
			label3.setAttribute('for',thead_id);
			label3.innerText = thead_id;
            let input3 = document.createElement('input');
			input3.setAttribute('type','text');
			input3.setAttribute('name',thead_id);
			input3.className = "form-control";
			input3.id = thead_id;
			place3.appendChild(inner_place3);
			inner_place3.appendChild(label3);
			inner_place3.appendChild(input3);
			add_allow3 = false;
		}
	}
}

let btn_event_listener1 = function(e){
		let tr = e.target.parentElement.parentElement.parentElement;
		console.log(tr);
		row_elements = tr.querySelectorAll('td');
		console.log(row_elements);
	

	if(add_allow){
		for(let i = 0; i < theads.length; i++){
			let thead_id = theads[i].innerText.toLowerCase();

			let inner_place1 = document.createElement('div');
			let label1 = document.createElement('label');
			let input1 = document.createElement('input');
			inner_place1.className = 'form-group';
			label1.className = "col-form-label";
			label1.setAttribute('for',thead_id);
			label1.innerText = thead_id;
			input1.className = "form-control";
			input1.setAttribute("name",thead_id);
			input1.id = thead_id;
			
			if(thead_id == "photo"){
                		input1.setAttribute('type','file');
				if(i == 0){
					input1.setAttribute('disabled','true');
				} 
			} else{
				input1.setAttribute('type','text');
				if(i == 0){
					input1.setAttribute('disabled','true');
				} 
			}
			console.log(row_elements[i]);
			input1.value = row_elements[i].innerText;

			place1.appendChild(inner_place1);
			inner_place1.appendChild(label1);
			inner_place1.appendChild(input1);
			add_allow = false;
		}
	} 
}

for(let i = 0; i < edit_buttons.length; i++){
	edit_buttons[i].addEventListener("click", btn_event_listener1);
}
for(let i = 0; i < add_buttons.length; i++){
	add_buttons[i].addEventListener("click", btn_event_listener3);
	console.log(add_buttons);
}
