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


const addBtn = document.getElementById('add');
const result = document.getElementById('result');
// let del = document.querySelectorAll('.btn-x');

let count = 0;

addBtn.addEventListener('click', function() {
	// result.classList.toggle('d-none');
	const inner = document.createElement('div');
	inner.classList.add('mb-3');

	const content = `
					<label for="formFile" class="form-label">Default file input example</label>
					<input type="file" class="form-control" id="formFile" name="attaches">
					<button type="button" class="btn-x">X</button>
				`;
	inner.innerHTML += content;
	count++;

	result.append(inner);
	
	// del = document.querySelectorAll('.btn-x');
});

result.addEventListener('click', function(e) {
	if(e.target.classList.contains('btn-x')) {
		e.target.parentElement.remove();
	}
});

/*
function btnX(p) {
	console.log(p + 'x버튼');
}
*/