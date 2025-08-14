/**
 * 
 */

const checkAll = document.getElementById('checkAll');
let checkArr = document.querySelectorAll('.ch');

checkAll.onchange = function () {
	checkArr.forEach(function (c) {
		c.checked = checkAll.checked;
	});
};

checkArr.forEach((c) => {
	c.addEventListener('change', function () {
		if (c.checked == false) {
			checkAll.checked = false;
		}

		flag = true;
		for (c2 of checkArr) {
			if (c2.checked == false) {
				flag = false;
				break;
			}
		}
		checkAll.checked = flag;
	});
});

const delBtn = document.getElementById('delBtn');
delBtn.onclick = function () {
	checkArr = document.querySelectorAll('.ch');
	const numArr = [];
	const elArr = [];
	checkArr.forEach(function (c) {
		if (c.checked == true) {
			numArr.push(c.value);
			elArr.push(c.parentElement.parentElement.parentElement);
		}
	});

	const params = new URLSearchParams();
	params.append("numArr", numArr);

	fetch('./deleteCart', {
		method: 'POST',
		body: params
	})
	.then(r => r.text())
	.then(r => {
		r = r.trim();
		if (r > 0) {
			elArr.forEach((c) => {
				c.remove();
			});
			alert('삭제 완료');
			checkAll.checked = false;
		} else {
			alert('삭제 실패');
		}
	});
};

const signUpBtn = document.getElementById('signUpBtn');

signUpBtn.onclick = function () {
	checkArr = document.querySelectorAll('.ch');
	const numArr = [];
	const elArr = [];
	checkArr.forEach(function (c) {
		if (c.checked == true) {
			numArr.push(c.value);
			elArr.push(c.parentElement.parentElement.parentElement);
		}
	});
	
	const params = new URLSearchParams();
	params.append("numArr", numArr);
	
	fetch('/account/signUp', {
		method: 'POST',
		body: params
	})
	.then(r => r.text())
	.then(r => {
		r = r.trim();
		if (r > 0) {
			elArr.forEach((c) => {
				c.remove();
			});
			alert('가입 완료');
			checkAll.checked = false;
		} else {
			alert('가입 실패');
		}
	});
};




