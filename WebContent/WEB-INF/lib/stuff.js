$(document).ready(function() {
	displayToggleText = document.getElementById("List");
	displayToggleText.innerHTML = " ";
})


function ListCtrl($scope) {

    $scope.stats = [];
	$scope.ourDate = '';
	$scope.tableHTML = '';
	$scope.tableInfo = '';
	
    $scope.getDate = function () {
     return $scope.ourDate;
    };


	//adds new items in order
    $scope.addItem = function () {
    	
    	console.log($scope.barcode + "   " + $scope.barcodes);
		$.ajax( {
			url: 'http://127.0.0.1/Failure',
			data: {Date : $scope.ourDate},
			type: 'GET',
			dataType: 'XML',
			async: false,
			contentType: 'application/json; charset=UTF-8',
			beforeSend: function( request ){
				request.setRequestHeader( "Auth", '47f5493577fb4e489a8dd4b51a0c72c0' );
			},
			success: function sucess(result){
				$scope.tableInfo = result;
			},
			error: function( jqXHR, error ){
				console.log( 'error ' + error );
			}
		} );
		
		displayToggleText = document.getElementById("List");
		displayToggleText.innerHTML = $scope.tableHTML;
		
	};

}
