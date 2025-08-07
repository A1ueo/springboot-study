/**
 * 
 */

const submitBtn = document.querySelector('#submit');
const frm = document.getElementById('frm');

const boardNum = document.querySelector('#boardNum');

submitBtn.addEventListener('click', function () {
	if (confirm('저장 하시겠습니까?')) {
		frm.submit();
	}
});

const add = document.getElementById('add');
const result = document.getElementById('result');

let count = result.dataset.fileCount;

add.addEventListener('click', function() {
	const div = document.createElement('div');
	div.classList.add('mb-3', 'd-flex', 'w-100', 'justify-content-between');

	div.innerHTML += 
		`<input type="file" class="form-control col-11" id="formFile" name="attaches">
		<button type="button" class="del btn btn-outline-danger">X</button>`;

/*
	let el = document.createElement("input");
	el.setAttribute('type', 'file');
	el.classList.add('form-control');
	el.setAttribute('name', 'attaches');
	div.appendChild(el);
	
	el = document.createElement('button');
	el.classList.add('del', 'btn', 'btn-outline-danger');
	el.setAttribute('type', 'button');
	el.innerText='X';
	div.appendChild(el);
*/
	result.appendChild(div);
	count++;
	if (count >= 5) {
		add.setAttribute('disabled', 'disabled');
	}
});

result.addEventListener('click', function(e) {
	if(e.target.classList.contains('del')) {
		e.target.parentElement.remove();
		count--;
		if (count < 5) {
			add.removeAttribute('disabled');
		}
	}
});


const deleteFile = document.querySelectorAll('.deleteFile');

deleteFile.forEach(function(item) {
	item.addEventListener('click', function(e){
			const fileNum = item.dataset.fileNum;
			
		if (e.target.classList.contains('deleteFile')) {
			console.log(fileNum);
	
			let params = new URLSearchParams();
			params.append("fileNum", fileNum);
	
			fetch('./deleteFile', {
				method: 'POST',
				body: params
			})
			.then(r => r.json())
			.then(r => {
			});
		}
	});
});


