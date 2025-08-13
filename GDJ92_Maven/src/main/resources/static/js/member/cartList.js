/**
 * 
 */

const allCheck = document.getElementById('checkAll');

allCheck.addEventListener('change', function () {
	const checks = document.getElementsByClassName('ch');
	for (c of checks) {
		c.toggleAttribute('checked');
	}
});