var displayToggleText;

$(document).ready(function() {
	displayToggleText = document.getElementById("fbtable");
	displayToggleText.innerHTML = "<table><tr><th>Team 1</th><th>Team 1 Score</th><th>Team 2</th><th>Team 2 Score</th></tr></table>";
	$("#fbtable").html("<table><tr><th>Team 1</th><th>Team 1 Score</th><th>Team 2</th><th>Team 2 Score</th></tr></table>");
})


function ListCtrl($scope) {

    $scope.stats = [];
	$scope.ourDate = '';
	$scope.tableHTML = '';
	$scope.JSONString = '';
	
    $scope.getName = function () {
     return $scope.ourDate;
    };


	//adds new items in order
    $scope.findGames = function () {
    	$scope.ourDate = $scope.ourDate.replace(/\D/g,'');
    	if ($scope.ourDate.length==8){
    		$scope.ourDate = $scope.ourDate.substring(0, 4) + "-" + $scope.ourDate.substring(4, 6) + "-" + $scope.ourDate.substring(6, 8);
    	}
    	else {
    		$scope.ourDate = "";
    	}
	    displayToggleText.innerHTML = "<table><tr><th>Team 1</th><th>Team 1 Score</th><th>Team 2</th><th>Team 2 Score</th></tr></table>";
		$("#fbtable").html("<table><tr><th>Team 1</th><th>Team 1 Score</th><th>Team 2</th><th>Team 2 Score</th></tr></table>");	
		$.ajax( {
			url: "http://localhost:8080/getGamesByDate?date=" + $scope.ourDate,
			type: 'GET',
			dataType: 'JSON',
			async: false,
			contentType: 'application/json; charset=UTF-8',
			success: function sucess(result){
				$scope.JSONString=JSON.stringify(result);
			},
			error: function( jqXHR, error ){
				console.log( 'error ' + error );
			}
		} );
		if ($scope.JSONString.split("team\":\"")[1] != "none\"}"){
			var dataArr = [];
			var currentString = $scope.JSONString;
			var currentArr = [1,2,3];
			while (currentArr.length>1){
				currentArr = currentString.split("team");
				currentArr.splice(0,1);
				currentString = currentArr.join("team");
				dataArr.push({
							"teamOne": currentString.split("name\":\"")[1].split("\",\"")[0],
							"teamOneScore": currentString.split("pts\":\"")[2].split("\",\"")[0],
							"teamTwo" : currentString.split("opponent\":\"")[1].split("\",\"")[0],
							"teamTwoScore" : currentString.split("pts\":\"")[1].split("\",\"")[0]
						});
				currentArr = currentString.split("date");
				currentArr.splice(0,1);
				currentString = currentArr.join("date");
			}
			for (i=0; i<dataArr.length; i++){
				displayToggleText.innerHTML = displayToggleText.innerHTML.split('</table>')[0]+'<tr><td>' + dataArr[i].teamOne + "</td><td>" + dataArr[i].teamOneScore + "</td><td>" + dataArr[i].teamTwo + "</td><td>" + dataArr[i].teamTwoScore + "</td></tr></table>";
			}
			$("#fbtable").html(displayToggleText.innerHTML);
		}
	};
}
