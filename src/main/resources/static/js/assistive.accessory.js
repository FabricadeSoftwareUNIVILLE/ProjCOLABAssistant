var reader = new FileReader();

onClickUpload = function() {
	document.getElementsByName('accessoryImage')[0].addEventListener('change',
			function(event) {
				reader.readAsDataURL(this.files[0]);
			});

	reader.onload = function(event) {
		document.getElementById('uploadedImg').setAttribute('src',
				event.target.result);
		console.log("reader.onload");

		var el = document.getElementById('uploadedImg');
		
		vanilla.bind({
			url : el.src
		});
		console.log(vanilla);
		// on button click
		vanilla.result('blob').then(function(blob) {
			// do something with cropped blob
		});

	}
}