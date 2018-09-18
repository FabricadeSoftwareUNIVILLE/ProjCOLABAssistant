var reader = new FileReader();

onClickUpload = function() {
	document.getElementsByName('accessoryImage')[0].addEventListener('change',
			function(event) {
				reader.readAsDataURL(this.files[0]);
			});

	reader.onload = function(event) {
		document.getElementById('uploadedImg').setAttribute('src',
				event.target.result);
	}
}