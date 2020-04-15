// onload
const onload = () => {
	// if the user already agreed to the disclaimer
	// simply show the form when the page is loaded
	if (getCookie('disclaimer')) {
		document.querySelector('#show-disclaimer-button').className = 'hidden';
		document.querySelector('#disclaimer').className = 'hidden';
		document.querySelector('#symptom-form').className = 'symptom-form shown-block';
	}
}

// hide the show-disclaimer-button
// and show the disclaimer statement block
const showDisclaimer = () => {
	document.querySelector('#show-disclaimer-button').className = 'hidden';
	document.querySelector('#disclaimer').className = 'shown-block';
}

// user disagreed to disclaimer
// show the show-disclaimer-button again
// and hide the disclaimer statement
const disagreeDisclaimer = () => {
	document.querySelector('#show-disclaimer-button').className = 'btn btn-primary shown-inline';
	document.querySelector('#disclaimer').className = 'hidden';
}

// user agreed to disclaimer
// hide the disclaimer statement block and show the symptoms form
// also sets the disclaimer cookie; note no expiration or way to remove
const agreeDisclaimer = () => {
	document.querySelector('#disclaimer').className = 'hidden';
	document.querySelector('#symptom-form').className = 'symptom-form shown-block';
	document.cookie = 'disclaimer=true';
}

// thanks to w3schools for this snippet!
// simple helper function to get cookies
const getCookie = (cname) => {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');

    for(var i = 0; i < ca.length; i++) {
    	var c = ca[i];
  
    	while (c.charAt(0) == ' ') {
    		c = c.substring(1);
    	}
  
    	if (c.indexOf(name) == 0) {
    		return c.substring(name.length, c.length);
    	}
    }

    return "";
}
