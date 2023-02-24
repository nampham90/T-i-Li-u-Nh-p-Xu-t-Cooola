/** 
 * Object search 
 */ 
function spcm51801Dto(){ 
	this.cstmordnosearchSearch = ''; 
	this.arvlplndatefromsearchSearch = ''; 
	this.arvlplndatetosearchSearch = ''; 
	this.arvlrdatefromsearchSearch = ''; 
	this.arvlrdatetosearchSearch = ''; 
	this.arvlrstckdatefromsearchSearch = ''; 
	this.arvlrstckdatetosearchSearch = ''; 
	this.sliplnnosearchSearch = ''; 
	this.siplnrsltnosearchSearch = ''; 
	this.spplynmsearchSearch = ''; 
} 
 
var dtoSpcm51801 = null; 
 
/** 
 * [商品コード]検索画面コントロール 
 */ 
wmsController.controller('Spcm51801SearchCtrl',['$scope','$uibModalInstance','$rootScope','$timeout', 
	function($scope,$uibModalInstance, $rootScope, $timeout) { 
	// Grid information 
	var dtoGridInfo = new gridInfoDto(); 
	var columnNm = []; 
	var formItemList = []; 
 
	//Perform click on link of CSTMORDNO 
	$scope.lnkCSTMORDNOClick = function(ITEMCD, ITEMNM,NUMRSRV1) { 
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
 
		// Cstmordno Column 
		column = new columnInfo(); 
		column.title = columnNm[0]; 
		column.name = "CSTMORDNO"; 
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
			tagA.setAttribute("onclick", "lnkCSTMORDNOClick('" + escapeJS(escaped) + "', '" + 
					escapeJS(instance.getDataAtRowProp(row, "CSTMORDNO")) +  "','" + 
					escapeJS(instance.getDataAtRowProp(row, "CSTMORDNO")) +  "')"); 
		}; 
		header.push(column); 
 
		//  Arvlplndate Column
		column = new columnInfo(); 
		column.title = columnNm[1]; 
		column.name = "ARVLPLNDATE"; 
		column.dataType = dataTypeString; 
		column.width = 150; 
		column.align = 0; 
		header.push(column); 
 
		//  Arvlrdate Column
		column = new columnInfo(); 
		column.title = columnNm[2]; 
		column.name = "ARVLRDATE"; 
		column.dataType = dataTypeString; 
		column.width = 150; 
		column.align = 0; 
		header.push(column); 
 
		//  Arvlrstckdate Column
		column = new columnInfo(); 
		column.title = columnNm[3]; 
		column.name = "ARVLRSTCKDATE"; 
		column.dataType = dataTypeString; 
		column.width = 150; 
		column.align = 0; 
		header.push(column); 
 
		//  Sliplnno Column
		column = new columnInfo(); 
		column.title = columnNm[4]; 
		column.name = "SLIPLNNO"; 
		column.dataType = dataTypeString; 
		column.width = 150; 
		column.align = 0; 
		header.push(column); 
 
		//  Siplnrsltno Column
		column = new columnInfo(); 
		column.title = columnNm[5]; 
		column.name = "SIPLNRSLTNO"; 
		column.dataType = dataTypeString; 
		column.width = 150; 
		column.align = 0; 
		header.push(column); 
 
		//  Divkbn Column
		column = new columnInfo(); 
		column.title = columnNm[6]; 
		column.name = "DIVKBN"; 
		column.dataType = dataTypeString; 
		column.width = 150; 
		column.align = 0; 
		header.push(column); 
 
		//  Arvlcompsts Column
		column = new columnInfo(); 
		column.title = columnNm[7]; 
		column.name = "ARVLCOMPSTS"; 
		column.dataType = dataTypeString; 
		column.width = 150; 
		column.align = 0; 
		header.push(column); 
 
		//  Spplynm Column
		column = new columnInfo(); 
		column.title = columnNm[8]; 
		column.name = "SPPLYNM"; 
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
		var res = callWebService(service + spcm51801InitWs, request, function(response) {}, null, null, null); 
 
		// Init dto if not init yet 
		if(dtoSpcm51801 == null){ 
			dtoSpcm51801 = new spcm51801Dto(); 
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
				"SCRNID": SPCM51801 
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
		var resRec = callWebService(service + spcm51801SearchCntWs,	request, function(response) { 
			if (!fnSetErrorMsg( $scope, response.fatalError)) { 
			    // Get records 
			    fnGetRecords(); 
			    // Set total records 
			    $scope.recordsCnt = response.spcm51801SearchCount.DATACNT; 
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
			"spcm51801SearchCondition" : { 
				"CSTMORDNOSEARCH" : dtoSpcm51801.cstmordnosearchSearch, 
				"ARVLPLNDATEFROMSEARCH" : dtoSpcm51801.arvlplndatefromsearchSearch, 
				"ARVLPLNDATETOSEARCH" : dtoSpcm51801.arvlplndatetosearchSearch, 
				"ARVLRDATEFROMSEARCH" : dtoSpcm51801.arvlrdatefromsearchSearch, 
				"ARVLRDATETOSEARCH" : dtoSpcm51801.arvlrdatetosearchSearch, 
				"ARVLRSTCKDATEFROMSEARCH" : dtoSpcm51801.arvlrstckdatefromsearchSearch, 
				"ARVLRSTCKDATETOSEARCH" : dtoSpcm51801.arvlrstckdatetosearchSearch, 
				"SLIPLNNOSEARCH" : dtoSpcm51801.sliplnnosearchSearch, 
				"SIPLNRSLTNOSEARCH" : dtoSpcm51801.siplnrsltnosearchSearch, 
				"SPPLYNMSEARCH" : dtoSpcm51801.spplynmsearchSearch 
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
		var res = callWebService(service + spcm51801SearchWs,request, function(response) { 
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
		$scope.cstmordnosearch = ""; 
		$scope.arvlplndatefromsearch = ""; 
		$scope.arvlplndatetosearch = ""; 
		$scope.arvlrdatefromsearch = ""; 
		$scope.arvlrdatetosearch = ""; 
		$scope.arvlrstckdatefromsearch = ""; 
		$scope.arvlrstckdatetosearch = ""; 
		$scope.sliplnnosearch = ""; 
		$scope.siplnrsltnosearch = ""; 
		$scope.spplynmsearch = ""; 
 
		fnClearMessage(); 
		// Store search conditon 
		fnStoreSearchCondition(); 
 
		//Reload grid with default search condition 
		fnSearchRecords(true); 
	} 
 
 function fnClearMessage() { 
		$scope.cstmordnosearchMessage = ""; 
		$scope.arvlplndatefromsearchMessage = ""; 
		$scope.arvlplndatetosearchMessage = ""; 
		$scope.arvlrdatefromsearchMessage = ""; 
		$scope.arvlrdatetosearchMessage = ""; 
		$scope.arvlrstckdatefromsearchMessage = ""; 
		$scope.arvlrstckdatetosearchMessage = ""; 
		$scope.sliplnnosearchMessage = ""; 
		$scope.siplnrsltnosearchMessage = ""; 
		$scope.spplynmsearchMessage = ""; 
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
 
		if ( $scope.cstmordnosearch != undefined || $scope.cstmordnosearch != null ) { 
			dtoSpcm51801.cstmordnosearchSearch = $scope.cstmordnosearch; 
		} 
		if ( $scope.arvlplndatefromsearch != undefined || $scope.arvlplndatefromsearch != null ) { 
			dtoSpcm51801.arvlplndatefromsearchSearch = $scope.arvlplndatefromsearch; 
		} 
		if ( $scope.arvlplndatetosearch != undefined || $scope.arvlplndatetosearch != null ) { 
			dtoSpcm51801.arvlplndatetosearchSearch = $scope.arvlplndatetosearch; 
		} 
		if ( $scope.arvlrdatefromsearch != undefined || $scope.arvlrdatefromsearch != null ) { 
			dtoSpcm51801.arvlrdatefromsearchSearch = $scope.arvlrdatefromsearch; 
		} 
		if ( $scope.arvlrdatetosearch != undefined || $scope.arvlrdatetosearch != null ) { 
			dtoSpcm51801.arvlrdatetosearchSearch = $scope.arvlrdatetosearch; 
		} 
		if ( $scope.arvlrstckdatefromsearch != undefined || $scope.arvlrstckdatefromsearch != null ) { 
			dtoSpcm51801.arvlrstckdatefromsearchSearch = $scope.arvlrstckdatefromsearch; 
		} 
		if ( $scope.arvlrstckdatetosearch != undefined || $scope.arvlrstckdatetosearch != null ) { 
			dtoSpcm51801.arvlrstckdatetosearchSearch = $scope.arvlrstckdatetosearch; 
		} 
		if ( $scope.sliplnnosearch != undefined || $scope.sliplnnosearch != null ) { 
			dtoSpcm51801.sliplnnosearchSearch = $scope.sliplnnosearch; 
		} 
		if ( $scope.siplnrsltnosearch != undefined || $scope.siplnrsltnosearch != null ) { 
			dtoSpcm51801.siplnrsltnosearchSearch = $scope.siplnrsltnosearch; 
		} 
		if ( $scope.spplynmsearch != undefined || $scope.spplynmsearch != null ) { 
			dtoSpcm51801.spplynmsearchSearch = $scope.spplynmsearch; 
		} 
	} 
} ]); 
 
function lnkCSTMORDNOClick(ITEMCD,ITEMNM,NUMRSRV1) { 
	ITEMCD = unescapeHtml(ITEMCD); 
	ITEMNM = unescapeHtml(ITEMNM); 
	NUMRSRV1 = unescapeHtml(NUMRSRV1); 
	var element = document.getElementById("gridHandsontableModal"); 
	var $scope = angular.element(element).scope(); 
	$scope.lnkCSTMORDNOClick(ITEMCD,ITEMNM,NUMRSRV1); 
} 
