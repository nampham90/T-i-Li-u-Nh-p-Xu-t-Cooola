/** 
 * Object search 
 */ 
function testDto(){ 
	this.aSearch = ''; 
	this.bSearch = ''; 
	this.cSearch = ''; 
	this.dSearch = ''; 
} 
 
var dtoTest = null; 
 
/** 
 * [商品コード]検索画面コントロール 
 */ 
wmsController.controller('TestSearchCtrl',['$scope','$uibModalInstance','$rootScope','$timeout', 
	function($scope,$uibModalInstance, $rootScope, $timeout) { 
	// Grid information 
	var dtoGridInfo = new gridInfoDto(); 
	var columnNm = []; 
	var formItemList = []; 
 
	//Perform click on link of E 
	$scope.lnkEClick = function(ITEMCD, ITEMNM,NUMRSRV1) { 
		var result = { 
			ITEMCD : '', 
			ITEMNM : '', 
			NUMRSRV1 : '' 
		}; 
		result.ITEMCD = ITEMCD; 
		result.ITEMNM = ITEMNM; 
		result.NUMRSRV1 = NUMRSRV1; 
		$uibModalInstance.close(result); 
	}; 
 
	// 初期表示 
    $timeout(function(){ 
    	fnInit(); 
    }); 
 
	/** 
	 * Define columns 
	 */ 
	function fnGetColumnInfo() { 
		var header = []; 
 
		// E Column 
		column = new columnInfo(); 
		column.title = columnNm[0]; 
		column.name = "E"; 
		column.dataType = dataTypeLink; 
		column.width = 200; 
		column.align = 0; 
		column.renderFunc = function(instance, td, row, col, prop, value, cp) { 
			Handsontable.renderers.TextRenderer.apply(this, arguments); 
			fnSetUpdkbn(instance, td, row); 
			fnSetColorkbn(instance, td, row, col); 
			var escaped = escapeHtml(value); 
			var tagA = document.createElement('A'); 
			Handsontable.dom.empty(td); 
			td.appendChild(tagA); 
			tagA.innerHTML = escaped; 
			tagA.className = "link-grid"; 
			tagA.setAttribute("onclick", "lnkEClick('" + escapeJS(escaped) + "', '" + 
					escapeJS(instance.getDataAtRowProp(row, "E")) +  "','" + 
					escapeJS(instance.getDataAtRowProp(row, "E")) +  "')"); 
		}; 
		header.push(column); 
 
		//  D Column
		column = new columnInfo(); 
		column.title = columnNm[1]; 
		column.name = "D"; 
		column.dataType = dataTypeString; 
		column.width = 150; 
		column.align = 0; 
		header.push(column); 
 
		//  F Column
		column = new columnInfo(); 
		column.title = columnNm[2]; 
		column.name = "F"; 
		column.dataType = dataTypeString; 
		column.width = 150; 
		column.align = 0; 
		header.push(column); 
 
		//  G Column
		column = new columnInfo(); 
		column.title = columnNm[3]; 
		column.name = "G"; 
		column.dataType = dataTypeString; 
		column.width = 150; 
		column.align = 0; 
		header.push(column); 
 
		return header; 
	} 
 
	/** 
	 * Init display 
	 */ 
	function fnInit() { 
 
		var request = { 
			"accessInfo" : { 
				"USRCD" : loginInfo.usrcd, 
				"LANG": loginInfo.lang, 
				"CSTMCD" : loginInfo.cstmcd, 
				"BRNCHCD" : loginInfo.brnchcd, 
				"TOKEN": loginInfo.token 
			} 
		}; 
 
		// Invoke webservice for init display 
		var res = callWebService(service + testInitWs, request, function(response) {}, null, null, null); 
 
		// Init dto if not init yet 
		if(dtoTest == null){ 
			dtoTest = new testDto(); 
		} 
 
		//Set value for search condition 
		fnStoreSearchCondition(); 
 
		// 画面項目名を取得 
		fnSetFormItemNm(); 
	} 
 
 
	/** 
	 * 画面に項目名をセット 
	 */ 
	function fnSetFormItemNm(){ 
		var request = { 
			"accessInfo":{ 
				"USRCD": loginInfo.usrcd, 
				"LANG": loginInfo.lang, 
				"CSTMCD": loginInfo.cstmcd, 
				"BRNCHCD":loginInfo.brnchcd, 
				"TOKEN": loginInfo.token 
			}, 
			"tmt340SearchCondition": { 
				"SCRNID": TEST 
			} 
		}; 
 
		var res = callWebService(service + tmt340SearchWs, request, 
	        function(response) { 
				for(var i=0; i < response.rows.length; i++){ 
					var row = response.rows[i]; 
					switch(row.SEQNO){ 
						case 0: $scope.formItemNm0 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 1: $scope.formItemNm1 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 2: $scope.formItemNm2 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 3: $scope.formItemNm3 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 4: $scope.formItemNm4 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 5: $scope.formItemNm5 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 6: $scope.formItemNm6 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 7: $scope.formItemNm7 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 8: $scope.formItemNm8 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 9: $scope.formItemNm9 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 10: $scope.formItemNm10 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 11: $scope.formItemNm11 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 12: $scope.formItemNm12 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 13: $scope.formItemNm13 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 14: $scope.formItemNm14 = row.FORMITEMNM; fnSetFormItemList(row.SEQNO, row.FORMITEMNM, formItemList); break; 
						case 15:
						case 16:
						case 17:
						case 18:
						case 19:
						case 20:
						case 21:
						case 22:
						case 23:
						case 24:
						case 25: columnNm.push(row.FORMITEMNM); break; 
					} 
				} 
 
				// 検索 
				fnSearchRecords(true); 
 
				$scope.$apply(); 
	        }, 
	        null, null, null 
	    ); 
	} 
 
 
	/** 
	 * Process search 
	 * 
	 * @param initFlg 
	 *            true:In case init 
	 */ 
	function fnSearchRecords(initFlg) { 
		// Get request 
		var request = fnGetRequest(); 
 
		// Invoke webservice get count of records base on search condition 
		var resRec = callWebService(service + testSearchCntWs,	request, function(response) { 
			if (!fnSetErrorMsg( $scope, response.fatalError)) { 
			    // Get records 
			    fnGetRecords(); 
			    // Set total records 
			    $scope.recordsCnt = response.testSearchCount.DATACNT; 
			    $scope.$apply(); 
            }
		}, function(response) { 
			fnSetErrorMsg($scope, response.fatalError); 
		}, null, null); 
 
		if (resRec == false) { 
			fnInitList(); 
		} 
	} 
 
	/** 
	 * Get request info 
	 */ 
	function fnGetRequest() { 
		var request = { 
			"testSearchCondition" : { 
				"A" : dtoTest.aSearch, 
				"B" : dtoTest.bSearch, 
				"C" : dtoTest.cSearch, 
				"D" : dtoTest.dSearch 
			}, 
			"formItemList": formItemList, 
			"accessInfo" : { 
				"USRCD" : loginInfo.usrcd, 
				"LANG": loginInfo.lang, 
				"CSTMCD" : loginInfo.cstmcd, 
				"BRNCHCD" : loginInfo.brnchcd, 
				"TOKEN": loginInfo.token 
			} 
		}; 
		return request; 
	} 
 
	/** 
	 * Get rows data 
	 */ 
	function fnGetRecords() { 
 
		// Get request 
		var request = fnGetRequest(); 
 
		// Invoke webservice get records base on search condition 
		var res = callWebService(service + testSearchWs,request, function(response) { 
			var rows = getRows($scope, response); 
			// Define columns 
			var columns = fnGetColumnInfo(); 
 
			// >>Handsontable Sample------------------------------------------------------------------- 
			var key = "gridHandsontableModal"; 
			// 初期化 エレメントID 
			fnInitHandsontable($scope, key); 
			// ヘッダを保持 
			fnSetHandsontableHeader($scope, key, columns); 
			fnSetDataRowsAtColumnsItem($scope, key, rows); 
			// Handsontableをクリア 
			fnClearHandsontable($scope, key); 
			// Handsontableを作成 
			fnCreateHandsontable($scope, key); 
			// ソート用デフォルトデータとして保持 
			fnSetHandsontableDefaultData($scope, key, rows); 
			// データ保持 
			fnSetHandsontableData($scope, key, rows); 
			// Handsontable更新 
			fnUpdateHandsontable($scope, key); 
			fnCompileHandsontable(key); 
			// <<Handsontable Sample------------------------------------------------------------------- 
 
		}, function(response) { 
			fnSetErrorMsg($scope, response.fatalError); 
		}, null); 
	}; 
 
	// Search button click 
	$scope.btnSearchClick = function() { 
		// Get and Store search conditon in param 
		fnStoreSearchCondition(); 
 
		// Get records 
		fnSearchRecords(true); 
	}; 
 
	// Clear button click 
	$scope.btnClearClick = function() { 
		$scope.a = ""; 
		$scope.b = ""; 
		$scope.c = ""; 
		$scope.d = ""; 
 
		fnClearMessage(); 
		// Store search conditon 
		fnStoreSearchCondition(); 
 
		//Reload grid with default search condition 
		fnSearchRecords(true); 
	} 
 
 function fnClearMessage() { 
		$scope.aMessage = ""; 
		$scope.bMessage = ""; 
		$scope.cMessage = ""; 
		$scope.dMessage = ""; 
 }
	//Perform close modal 
	$scope.btnCloseClick = function() { 
		$uibModalInstance.close(); 
	}; 
 
	/* 
	 * Store search condition 
	 */ 
	function fnStoreSearchCondition () { 
		fnClearMessage(); 
 
		if ( $scope.a != undefined || $scope.a != null ) { 
			dtoTest.aSearch = $scope.a; 
		} 
		if ( $scope.b != undefined || $scope.b != null ) { 
			dtoTest.bSearch = $scope.b; 
		} 
		if ( $scope.c != undefined || $scope.c != null ) { 
			dtoTest.cSearch = $scope.c; 
		} 
		if ( $scope.d != undefined || $scope.d != null ) { 
			dtoTest.dSearch = $scope.d; 
		} 
	} 
} ]); 
 
function lnkEClick(ITEMCD,ITEMNM,NUMRSRV1) { 
	ITEMCD = unescapeHtml(ITEMCD); 
	ITEMNM = unescapeHtml(ITEMNM); 
	NUMRSRV1 = unescapeHtml(NUMRSRV1); 
	var element = document.getElementById("gridHandsontableModal"); 
	var $scope = angular.element(element).scope(); 
	$scope.lnkEClick(ITEMCD,ITEMNM,NUMRSRV1); 
} 
