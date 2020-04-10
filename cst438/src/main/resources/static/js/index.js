// hide the show-disclaimer-button
// and show the disclaimer statement block
function showDisclaimer() {
	document.querySelector('#show-disclaimer-button').className = 'hidden';
	document.querySelector('#disclaimer').className = 'shown-block';
}

// user disagreed to disclaimer
// show the show-disclaimer-button again
// and hide the disclaimer statement
function disagreeDisclaimer() {
	document.querySelector('#show-disclaimer-button').className = 'btn btn-primary shown-inline';
	document.querySelector('#disclaimer').className = 'hidden';
}

// user agreed to disclaimer
// hide the disclaimer statement block
// and show the symptoms form
function agreeDisclaimer() {
	document.querySelector('#disclaimer').className = 'hidden';
	document.querySelector('#symptom-form').className = 'shown-block';
}
