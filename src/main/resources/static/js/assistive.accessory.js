onClickUpload = function () {
	document.getElementsByName('accessoryImage')[0].addEventListener('change',
			function(event) {
				reader.readAsDataURL(this.files[0]);
			});
	
	console.log("onClickUpload");
	
	var reader = new FileReader();

	reader.onload = function(event) {
		document.getElementById('uploadedImg').setAttribute('src', event.target.result);
		console.log("reader.onload");
		//========================================
		var el = document.getElementById('uploadedImg');
		var vanilla = new Croppie(el, {
		    viewport: { width: 100, height: 100 },
		    boundary: { width: 300, height: 300 },
		    showZoomer: false,
		    enableOrientation: true
		});
		vanilla.bind({
		    url: el.src
		});
		//on button click
		vanilla.result('blob').then(function(blob) {
		    // do something with cropped blob
		});
		
		// O QUE ESTAVA ANTES
//		var element = document.getElementById('uploadedImg');
//		var resize = new Croppie(element, {
//			enableExif: true,
//		    viewport: {
//		        width: 400,
//		        height: 400
//		    }
//		});
//
//		resize.croppie();
		//========================================
		
	}
}

var reader = new FileReader();

reader.onload = function(event) {
	document.getElementById('uploadedImg').setAttribute('src', event.target.result);
	// pegar o "src" da imagem carregada
	// chamar uma função que irá "croppar"
}