/**
 * 
 */

const pn = document.querySelectorAll('.pn');
const searchForm = document.getElementById('searchForm');
const pageNum = document.querySelector('#pageNum');

pn.forEach(function(p) {
	p.addEventListener('click', function() {
		const data = this.dataset.pn;
		pageNum.value = data;
		searchForm.submit();
	});
/*	p.addEventListener('click', (e) => {
		const data = e.target.dataset.pn;
		pageNum.value = data;
		searchForm.submit();
	});
*/
});