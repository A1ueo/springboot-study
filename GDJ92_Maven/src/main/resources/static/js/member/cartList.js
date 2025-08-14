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
	let flag = false;
	const numArr = [];
	const elArr = [];
	checkArr.forEach(function (c) {
		if (c.checked == true) {
			flag = true;
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
		} else {
			alert('삭제 실패');
			flag = false;
		}
	});
	
	if (flag == true) {
		alert('삭제 완료');
		checkAll.checked = false;
	}
};

/*
delBtn.onclick = function () {
	checkArr = document.querySelectorAll('.ch');
	let flag = false;
	checkArr.forEach(function (c) {
		if (c.checked == true) {
			flag = true;
			const params = new URLSearchParams();
			params.append("productNum", c.value);
			
			fetch('./deleteCart', {
				method: 'POST',
				body: params
			})
			.then(r => r.text())
			.then(r => {
				r = r.trim();
				if (r == 1) {
					c.parentElement.parentElement.parentElement.remove();
				} else {
					alert('삭제 실패');
					flag = false;
				}
			});
		}
	});
	if (flag == true) {
		alert('삭제 완료');
		checkAll.checked = false;
	}
};
*/