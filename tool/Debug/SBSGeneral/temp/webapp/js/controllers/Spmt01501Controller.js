/**
 * Object search
 */
function spmt01501Dto () {
	this.buyercd = "";
	this.buyernm = "";
}

/**
 * Object Row Dto
 */
function spmt01501RowDto () {
	this.buyercdAddNew = "";
	this.buyernmAddNew = "";
	this.buyerzip = "";
	this.buyeradrs1 = "";
	this.buyeradrs2 = "";
	this.buyeradrs3 = "";
	this.buyertel = "";
	this.buyerfax = "";
	this.buyerusrnm = "";
	this.buyermailadrs = "";
	this.buyerremark = "";
	this.buyersortno = "";
}

//Init Object
var dtoSpmt01501 = null;
var hiddenDtoSpmt01501 = null;
var dtoSpmt01501Row = null;
var hiddenDtoSpmt01501Row = null;

/**
 * SPMT01501 Controller
 */
wmsController.controller( 'Spmt01501Ctrl', [ '$scope', '$http', '$location', '$modal', '$rootScope', '$timeout', 'AlertService', '$q', function ( $scope, $http, $location, $modal, $rootScope, $timeout, AlertService, $q ) {

	var isInit = true;
	// Create DTO Grid
	var dtoGridInfo = new gridInfoDto();
	// Variable Location
	var transferDispId = "";

	var formItemList = [];
	var columnNm = [];

    $timeout(function(){
    	fnInit();
    });

	// Pop up search item
	$scope.btnTestClick = function () {
		var modal = fnOpenSpcm51801Window( $modal );
		modal.then( function ( result ) {
			try {
				$scope.buyersortno = result.CSTMCD + ' - ' + result.CSTMNM;
				//$scope.buyersortno = fnCutStr(result.CSTMNM,46);
			} catch ( e ) {
			}
		} );
	}

 	$scope.fnInitFromMenu = function(){
 		fnInit();
	}

	$scope.btnSearchClick = function () {
		fnStoreSearchCondition();
		fnClearNotification();
		fnSearchRecords(true);
	};

	$scope.btnClearClick = function () {
		fnClearNotification();
		if ( fnCheckDetailChange() ) {
			var msg = message[ 'MQ000018' ];
			confirmPopup( AlertService, msg, confirmTitle, function () {
				fnClear();
				fnSetHidden();
				hiddenDtoSpmt01501 = fnGetObjScreenSearch();
				$scope.chkOptCls = false;
				$scope.hiddenMODE = modeIns;
			}, null );
		} else {
			fnClear();
			fnSetHidden();
			hiddenDtoSpmt01501 = fnGetObjScreenSearch();
			$scope.chkOptCls = false;
			$scope.hiddenMODE = modeIns;
		}
	}

	$scope.buyercdLinkClick = function ( buyercd, detailUpddatetime ) {
		fnClearNotificationAddNew();
		fnClearNotification();
		if ( fnCheckDetailChange() ) {
			var msg = message[ 'MQ000018' ];
			confirmPopup( AlertService, msg, confirmTitle, function () {
				fnBuyercdClick( buyercd, detailUpddatetime );
			}, null );
		} else {
			fnBuyercdClick( buyercd, detailUpddatetime );
		}
	}

	$scope.btnNewClick = function () {
		fnClearNotification();
		if ( fnCheckDetailChange() ) {
			var msg = message[ 'MQ000018' ];
			confirmPopup( AlertService, msg, confirmTitle, function () {
				fnExecNewClick();
			}, null );
		} else {
			fnExecNewClick();
		}

	}

	$scope.btnConfirmClick = function () {
		var countRecord = fnRemoveSeparater( $scope.recordsCnt );
		var request;

		// Clear old notification
		fnClearNotification();

		// store condition
		fnStoreAddNewCondition();

		// Set request
		request = fnGetRequestCheckDetail();
		// Input check
		var res = callWebService( service + spmt01501CheckDetailWs, request, function ( response ) {

			// Set error check input message
			if ( !fnSetErrorMsg( $scope,response.fatalError  ) ) {
				var isErr = false;
				// Execute
				if ( $scope.hiddenMODE == modeIns ) {
					// Call web service insert
					request = fnGetRequestRegister();
					callWebService( service + spmt01501RegistWs, request, function ( response ) {
						fnRestoreSearchCondition();
						if ( countRecord > 0 ) {
							isErr = fnSearchRecords( dtoSpmt01501.pageNum, tmt030System.SYSKBN10, true );
						}
						if ( !isErr ) {
							fnAddNewSuccess();
						}
					}, null, null, null );
				} else if ( $scope.hiddenMODE == modeUpd ) {
					// Call web service Update
					request = fnGetRequestUpdate();
					callWebService( service + spmt01501UpdateWs, request, function ( response ) {
						// Notification of exclusive check
						if ( fnSetErrorMsg( $scope, response.fatalError ) ) {
							return;
						}
						fnRestoreSearchCondition();
						if ( countRecord > 0 ) {
							isErr = fnSearchRecords( dtoSpmt01501.pageNum, tmt030System.SYSKBN10, true );
						}
						if ( !isErr ) {
							fnAddNewSuccess();
						}
					}, null, null, null );
				}
			} else {
				errorPopup(AlertService, message['ME000174'], errorTitle);
			}
			$scope.$apply();

		}, null, null, null );
	}

	$scope.btnDeleteClick = function () {
		fnStoreAddNewCondition();
		var request;
		request = {
			"spmt01501CheckLink" : {
				"BUYERCD" : dtoSpmt01501Row.buyercdAddNew,
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

		var res = callWebService( service + spmt01501CheckLinkDeleteWs, request, function ( response ) {
			if ( response.DATACNT >= 1 ) {
				var msg = message[ 'ME000075' ].replace("%1","選択された依頼先コード").replace("%2","配送先マスタ");
				$scope.message = msg;
			} else {
				var msg = message[ 'MQ000006' ];
				confirmPopup( AlertService, msg, confirmTitle, function () {
					fnExecDelete();
				}, null );
			}
			$scope.$apply();
		}, null, null, null );
	}

	/**
	 * Execute Delete
	 */
	function fnExecDelete () {
		var request = {
			"spmt01501DeleteCondition" : {
				"BUYERCD" : dtoSpmt01501Row.buyercdAddNew,
				"UPDDATETIMEHidden" : $scope.updatetime
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

		// Delete
		var res = callWebService( service + spmt01501DeleteWs, request, function ( response ) {
			if ( fnSetErrorMsg( $scope, response.fatalError ) == false ) {
				// Delete success
				var isErr = false;
				fnRestoreSearchCondition();
				isErr = fnSearchRecords(true );
				if ( !isErr ) {
					// Clear add new
					fnClearAddNew();
					fnSetHidden();
					$scope.hiddenMODE = modeIns;
					$scope.chkOptCls=false;
					fnAuthKbnList( 1 );
					infoPopup( AlertService, message[ 'MI000009' ], infoTitle );
				} else {
					$scope.message = message[ 'ME000014' ].replace( '%1', dtoSpmt01501Row.buyerAddNew );
				}
			}
		}, null, null, null );
	}

	$scope.fnCheckChange = function ( dispId ) {
		transferDispId = dispId;
		if ( fnCheckDetailChange() ) {
			confirmPopup( AlertService, message[ 'MQ000018' ], confirmTitle, $scope.fnTransferOKClick, null );
			return true;
		}
	}

	$scope.fnTransferOKClick = function () {
 		fnDestoryObject($scope, SPMT01501, transferDispId);
 		$location.path('/' + transferDispId);
	}

	$scope.btnZipClick = function() {
		var request = {
				"accessInfo" : {
					"USRCD" : loginInfo.usrcd,
					"LANG": loginInfo.lang,
					"CSTMCD" : loginInfo.cstmcd,
					"BRNCHCD" : loginInfo.brnchcd,
					"TOKEN": loginInfo.token
				},
				"ZIP": $scope.buyerzip
			}

		var res = callWebService( service + tsm010SearchWs, request, function ( response ) {
			$scope.buyeradrs1 = response.PREFNM + response.MUNICIPALITYNM;
			$scope.buyeradrs2 =  response.TOWNAREANM;

			$scope.$apply();
		}, null, null, null );
	}

	$scope.fnCheckChange = function ( dispId ) {
		transferDispId = dispId;
		if ( fnCheckDetailChange() ) {
			confirmPopup( AlertService, message[ 'MQ000018' ], confirmTitle, $scope.fnTransferOKClick, null );
			return true;
		}
	}

	$scope.fnTransferOKClick = function () {
 		fnDestoryObject($scope, SPMT01501, transferDispId);
 		$location.path('/' + transferDispId);
	}

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
				"SCRNID": SPMT01501
			}
		};

		var res = callWebService(service + tmt340SearchWs, request,
	        function(response) {

				for(var i=0; i < response.rows.length; i++){
					var row = response.rows[i];
					switch(row.SEQNO){
						case 0: $rootScope.pagetitle = row.FORMITEMNM; break;
						case 1: $scope.formItemNm1 = row.FORMITEMNM; break;
						case 2: $scope.formItemNm2 = row.FORMITEMNM; break;
						case 3: $scope.formItemNm3 = row.FORMITEMNM; break;
						case 4:	$scope.formItemNm4 = row.FORMITEMNM; break;
						case 5: $scope.formItemNm5 = row.FORMITEMNM; break;
						case 6: $scope.formItemNm6 = row.FORMITEMNM; break;

						case 7:
						case 8:
						case 9:
						case 10:
						case 11:
						case 12:
						case 13:
						case 14:
						case 15:
						case 16:
						case 17:
						case 18: columnNm.push(row.FORMITEMNM); break;

						case 19: $scope.formItemNm19 = row.FORMITEMNM; break;
						case 20: $scope.formItemNm20 = row.FORMITEMNM; break;
						case 21: $scope.formItemNm21 = row.FORMITEMNM; break;
						case 22: $scope.formItemNm22 = row.FORMITEMNM; break;
						case 23: $scope.formItemNm23 = row.FORMITEMNM; break;
						case 24: $scope.formItemNm24 = row.FORMITEMNM; break;
						case 25: $scope.formItemNm25 = row.FORMITEMNM; break;
						case 26: $scope.formItemNm26 = row.FORMITEMNM; break;
						case 27: $scope.formItemNm27 = row.FORMITEMNM; break;
						case 28: $scope.formItemNm28 = row.FORMITEMNM; break;
						case 29: $scope.formItemNm29 = row.FORMITEMNM; break;
						case 30: $scope.formItemNm30 = row.FORMITEMNM; break;
						case 31: $scope.formItemNm31 = row.FORMITEMNM; break;
						case 32: $scope.formItemNm32 = row.FORMITEMNM; break;
						case 33: $scope.formItemNm33 = row.FORMITEMNM; break;
						case 34: $scope.formItemNm34 = row.FORMITEMNM; break;
						case 35: $scope.formItemNm35 = row.FORMITEMNM; break;
						case 36: $scope.formItemNm36 = row.FORMITEMNM; break;
						case 37: $scope.formItemNm37 = row.FORMITEMNM; break;
						case 38: columnNm.push(row.FORMITEMNM); break;
					}
				}

				fnExecInit();
				$scope.$apply();
				$rootScope.$apply();
	        },
	        null, null, null
	    );
	}

	/**
	 * Init and set default grid
	 */
	function fnInitList () {
		// Get column info
		var columns = fnGetColumnInfo();
		$scope.recordsCnt = "0";
		var key = "gridHandsontable";
		fnInitHandsontable($scope, key);
		fnSetHandsontableHeader($scope, key, columns);
		fnClearHandsontable($scope, key);
		fnCreateHandsontable($scope, key);
		fnCompileHandsontable(key);
	}


	/**
	 * Init screen
	 */
	function fnInit () {
		window.scrollTo( 0 , 0);
		fnSetFormItemNm();
		fnClear();
		$scope.chkOptCls = false;
		$('#buyercd').focus();
	}

	/**
	 * Setup column in gridRecords
	 */
	function fnGetColumnInfo () {
		var header = [];

		column = new columnInfo();
		column.title = columnNm[0];
		column.name = "BUYERCD";
		column.dataType = dataTypeString;
		column.width = 150;
		column.align = 0;
		column.renderFunc = function(instance, td, row, col, prop, value, cp) {
			Handsontable.renderers.TextRenderer.apply(this, arguments);
			fnSetUpdkbn(instance, td, row);
			fnSetColorkbn(instance, td, row, col);
			var escaped =escapeHtml(value);
			var tagA = document.createElement('A');
			Handsontable.dom.empty(td);
			td.appendChild(tagA);
			tagA.innerHTML = escaped;
			tagA.setAttribute("onclick", "buyercdLinkClick(\"" + escapeJS(escaped) + "\",\"" +
				escapeJS(instance.getDataAtRowProp(row, "DETAILUPDDATETIME")) +  "\")");
		};
		header.push( column );

		 // 依頼主略名
		column = new columnInfo();
		column.title = columnNm[1];
		column.name = "BUYERNM";
		column.dataType = dataTypeString;
		column.width = 220;
		column.align = 0;
		header.push( column );

		// 依頼主郵便番号
		column = new columnInfo();
		column.title = columnNm[2];
		column.name = "BUYERRNM";
		column.dataType = dataTypeString;
		column.width = 130
		column.align = 0;
		header.push( column );

		// 依頼主住所1
		column = new columnInfo();
		column.title = columnNm[3];
		column.name = "BUYERZIP";
		column.dataType = dataTypeString;
		column.width = 300;
		column.align = 0;
		header.push( column );

		// 依頼主住所2
		column = new columnInfo();
		column.title = columnNm[4];
		column.name = "BUYERADRS1";
		column.dataType = dataTypeString;
		column.width = 300;
		column.align = 0;
		header.push( column );

		// 依頼主住所3
		column = new columnInfo();
		column.title = columnNm[5];
		column.name = "BUYERADRS2";
		column.dataType = dataTypeString;
		column.width = 300;
		column.align = 0;
		header.push( column );

		// 依頼主TEL
		column = new columnInfo();
		column.title = columnNm[6];
		column.name = "BUYERADRS3";
		column.dataType = dataTypeString;
		column.width = 160;
		column.align = 0;
		header.push( column );

		// 依頼主FAX
		column = new columnInfo();
		column.title = columnNm[7];
		column.name = "BUYERTEL";
		column.dataType = dataTypeString;
		column.width = 160;
		column.align = 0;
		header.push( column );

		// 依頼主名
		column = new columnInfo();
		column.title = columnNm[8];
		column.name = "BUYERFAX";
		column.dataType = dataTypeString;
		column.width = 250;
		column.align = 0;
		header.push( column );

		// 得意先担当者
		column = new columnInfo();
		column.title = columnNm[9];
		column.name = "BUYERUSRNM";
		column.dataType = dataTypeString;
		column.width = 100;
		column.align = 1;
		header.push( column );

		// 依頼主メールアドレス
		column = new columnInfo();
		column.title = columnNm[10];
		column.name = "BUYERMAILADRS";
		column.dataType = dataTypeString;
		column.width = 120;
		column.align = 1;
		header.push( column );

		// 引当優先順
		column = new columnInfo();
		column.title = columnNm[12];
		column.name = "BUYERSORTNO";
		column.dataType = dataTypeString;
		column.width = 100;
		column.align = 1;
		header.push( column );

		// 備考
		column = new columnInfo();
		column.title = columnNm[11];
		column.name = "BUYERREMARK";
		column.dataType = dataTypeString;
		column.width = 250;
		column.align = 0;
		header.push( column );

		column = new columnInfo();
		column.title = "更新日時";
		column.name = "DETAILUPDDATETIME";
		column.dataType = dataTypeString;
		column.hidden = true;
		column.renderFunc = fnHidingColumn;
		column.width = 0.1;
		// header.push( column );

		return header;
	}

	/**
	 * Save search condition to storage
	 */
	function fnStoreSearchCondition () {
		if ( $scope.buyercd != undefined || $scope.buyercd != null ) {
			dtoSpmt01501.buyercd = $scope.buyercd;
		}
		if ( $scope.buyernm != undefined || $scope.buyernm != null ) {
			dtoSpmt01501.buyernm = $scope.buyernm;
		}
	}

	/**
	 * Restore search condition to storage
	 */
	function fnRestoreSearchCondition () {
		$scope.buyercd = dtoSpmt01501.buyercd;
		$scope.buyernm = dtoSpmt01501.buyernm;
	}

	/**
	 * Set request to search
	 * @param pageNum current page
	 * @param dispNum record each page
	 */
	function fnGetRequest () {
		var request = {
			"spmt01501SearchCondition" : {
				"BUYERCD" : dtoSpmt01501.buyercd,
				"BUYERNM" : dtoSpmt01501.buyernm,
			},
			"formItemList": formItemList,
			"accessInfo" : {
				"USRCD" : loginInfo.usrcd,
				"LANG": loginInfo.lang,
				"CSTMCD" : loginInfo.cstmcd,
				"BRNCHCD" : loginInfo.brnchcd,
				"BRNCHNM" : loginInfo.brnchnm,
				"TOKEN": loginInfo.token
			}
		};

		return request;
	}

	/**
	 * Call request to webservice
	 * @param pageNum current page request
	 * @param dispNum record each page
	 * @param initFlg flag init
	 */
	function fnSearchRecords (initFlg ) {
		var isErr = false;
		// set request
		var request = fnGetRequest();

		// Init List
		fnInitList();

		// Request count record
		var resRec = callWebService( service + spmt01501SearchCntWs, request, function ( response ) {
			// Check error
			if ( fnSetErrorMsg( $scope, response.fatalError ) ) {
				isErr = true;
				return;
			}

			if ( response.DATACNT == 0 ) {
				var msg = message[ 'ME000115' ];
				infoPopup(AlertService, msg, infoTitle, null);
				isErr = true;
			}

    		// 取得件数
    		var dataCnt = response.DATACNT;

    		if(initFlg == true){
    			var dtoMessage = checkRecordCnt($scope, dataCnt);
        		if(dtoMessage.message  == ""){
        			 $scope.cntOverOK(dataCnt);
        		} else {
        			if(dtoMessage.popupType == "C"){
        				confirmPopup(AlertService, dtoMessage.message, confirmTitle, function(){return $scope.cntOverOK(dataCnt);});
        			} else {
        				errorPopup(AlertService, dtoMessage.message, errorTitle);
        			}
        		}
    		} else {
    			$scope.cntOverOK(dataCnt);
    		}
		}, null, null, null );

		return isErr;
	}


    $scope.cntOverOK = function(dataCnt){
		$scope.recordsCnt = fnFormatNumber(dataCnt);
		fnGetRecords();
    }


	/**
	 * Set record to grid
	 * @param pageNum current page request
	 * @param dispNum record each page
	 */
	function fnGetRecords () {

		// set request
		var request = fnGetRequest();

		// call request
		var res = callWebService( service + spmt01501SearchWs, request, function ( response ) {
			var rows = getRows( $scope, response );
			var columns = fnGetColumnInfo();

			if ( rows != null ) {
				$scope.recNum = rows.length;
			}

			var key = "gridHandsontable";
			fnSetDataRowsAtColumnsItem($scope, key, rows);
			// ソート用デフォルトデータとして保持
			fnSetHandsontableDefaultData($scope, key, rows);
			// データ保持
			fnSetHandsontableData($scope, key, rows);
			// Handsontable更新
			fnUpdateHandsontable($scope, key);
			fnCompileHandsontable(key);
			// <<Handsontable Sample-------------------------------------------------------------------

			// Set scroll
			if ( document.getElementById( "scrollBody" ) != undefined ) {
				document.getElementById( "scrollBody" ).style.height = "170px";
			}
		}, function ( response ) {
			$scope.$apply();
		}, null, null );
		return res;
	}

	/**
	 * Clear
	 */
	function fnClear () {
		fnInitList();
		$scope.buyercd = "";
		$scope.buyernm = "";
		fnClearAddNew();
		fnSetHidden();
		fnAuthKbnList(1);
	}

	/**
	 * Set clear for field
	 */
	function fnClearAddNew(){
 		$scope.buyercdAddNew = "";
		$scope.buyernmAddNew = "";
		$scope.buyerrnmAddNew = "";
		$scope.buyerzip = "";
		$scope.buyeradrs1 = "";
		$scope.buyeradrs2 = "";
		$scope.buyeradrs3 = "";
		$scope.buyertel = "";
		$scope.buyerfax = "";
		$scope.buyerusrnm = "";
		$scope.buyermailadrs = "";
		$scope.buyerremark = "";
		$scope.buyersortno = "";
		fnClearNotificationAddNew();
 	}
	/**
	 * Set Hidden Item
	 */
	function fnSetHidden () {
		hiddenDtoSpmt01501Row = fnGetObjScreen();
	}

	/**
	 * Click buyercd on record
	 * @param buyercd value get from girdRecord
	 * @param detailUpddatetime value get from girdRecord
	 */
	function fnBuyercdClick ( buyercd, detailUpddatetime ) {
		var request;
		request = {
			"spmt01501CheckLink" : {
				"BUYERCD" : buyercd,
				"DETAILUPDDATETIME" : detailUpddatetime
			},
			"accessInfo" : {
				"USRCD" : loginInfo.usrcd,
				"LANG": loginInfo.lang,
				"CSTMCD" : loginInfo.cstmcd,
				"BRNCHCD" : loginInfo.brnchcd,
				"TOKEN": loginInfo.token
			}
		};

		var res = callWebService( service + spmt01501CheckLinkWs, request, function ( response ) {
			if ( response.DATACNT == 1 && response.UPDDATETIMECHECK ) {
				fnGetDetailBuyercd( buyercd );
			} else if ( response.DATACNT == 1 && !response.UPDDATETIMECHECK ) {
				$scope.message = message[ 'ME000003' ];
				fnSearchRecords(true );
				fnClearAddNew();
			} else {
				$scope.message = message[ 'ME000063' ].replace( "%1", buyercd );
				fnSearchRecords(true);
				fnClearAddNew();
			}
		}, null, null, null );
	}

	/**
	 * Get database set detail into section add new
	 * @param buyercd get from gridRecord
	 */
	function fnGetDetailBuyercd ( buyercd ) {
		request = {
			"spmt01501GetDetailCondition" : {
				"BUYERCD" : buyercd
			},
			"accessInfo" : {
				"USRCD" : loginInfo.usrcd,
				"LANG": loginInfo.lang,
				"CSTMCD" : loginInfo.cstmcd,
				"BRNCHCD" : loginInfo.brnchcd,
				"TOKEN": loginInfo.token
			}
		};

		var res = callWebService( service + spmt01501GetDetailWs, request, function ( response ) {
			$scope.buyercdAddNew = response.BUYERCD;
			$scope.buyernmAddNew = response.BUYERNM;
			$scope.buyerrnmAddNew = response.BUYERRNM;
			$scope.buyerzip = response.BUYERZIP;
			$scope.buyeradrs1 = response.BUYERADRS1;
			$scope.buyeradrs2 = response.BUYERADRS2;
			$scope.buyeradrs3 = response.BUYERADRS3;
			$scope.buyertel = response.BUYERTEL;

			//fnRsrvtypekbnChange();
			$scope.buyerfax = response.BUYERFAX;
			$scope.buyerusrnm = response.BUYERUSRNM;
			$scope.buyermailadrs = response.BUYERMAILADRS;
			$scope.buyersortno = response.BUYERSORTNO;
			$scope.buyerremark = response.BUYERREMARK;
			$scope.updatetime = response.UPDDATETIME;

			$scope.hiddenMODE = modeUpd;
			//Set Disable buyercd
			$scope.chkOptCls=true;
			fnAuthKbnList( 0 );
			$scope.$apply();
			fnSetHidden();
			hiddenDtoSpmt01501 = fnGetObjScreenSearch();
		}, null, null, null );
	}

	/**
	 * Clear all notification
	 */
	function fnClearNotification () {
		$scope.message = "";
		$scope.buyercdMessage = "";
		$scope.buyernmMessage = "";
		fnClearNotificationAddNew();
	}

	/**
	 * Clear notification addnew Section
	 */
	function fnClearNotificationAddNew () {
		$scope.buyercdAddNewMessage = "";
		$scope.buyernmAddNewMessage = "";
		$scope.buyerrnmAddNewMessage = "";
		$scope.buyerzipMessage = "";
		$scope.buyeradrs1Message = "";
		$scope.buyeradrs2Message = "";
		$scope.buyeradrs3Message = "";
		$scope.buyertelMessage = "";
		$scope.buyerfaxMessage = "";
		$scope.buyerusrnmMessage = "";
		$scope.buyermailadrsMessage = "";
		$scope.buyerremarkMessage = "";
		$scope.buyersortnoMessage = "";
		$scope.updatetimeMessage = "";
	}

	/**
	 * Execute Init
	 */
	function fnExecInit () {
		fnInitList();
		fnClearAddNew();
		hiddenDtoSpmt01501 = fnGetObjScreenSearch();
		if ( dtoSpmt01501 == null ) {
			dtoSpmt01501 = new spmt01501Dto();
			dtoSpmt01501.buyercd = "";
			dtoSpmt01501.buyernm = "";
		}
		$scope.hiddenMODE = modeIns;
		$scope.selectedCstmcd = loginInfo.cstmcd;
		fnAuthKbnList( 1 );

		// Init Web
		var request = {
			"accessInfo" : {
				"USRCD" : loginInfo.usrcd,
				"LANG": loginInfo.lang,
				"CSTMCD" : loginInfo.cstmcd,
				"BRNCHCD" : loginInfo.brnchcd,
				"TOKEN": loginInfo.token
			}
		};
		var res = callWebService( service + spmt01501InitWs, request, function ( response ) {
		}, null, null, null );
	}

	/**
	 * Execute New Click
	 */
	function fnExecNewClick () {
		fnClearAddNew();
		fnSetHidden();
		$scope.hiddenMODE = modeIns;
		$scope.chkOptCls=false;
		fnAuthKbnList( 1 );
	}

	/**
	 * Save model to object
	 */
	function fnStoreAddNewCondition () {
		dtoSpmt01501Row = new spmt01501RowDto();
		dtoSpmt01501Row.buyercdAddNew = $scope.buyercdAddNew;
		dtoSpmt01501Row.buyernmAddNew = $scope.buyernmAddNew;
		dtoSpmt01501Row.buyerrnmAddNew = $scope.buyerrnmAddNew;
		dtoSpmt01501Row.buyerzip = $scope.buyerzip;
		dtoSpmt01501Row.buyeradrs1 = $scope.buyeradrs1;
		dtoSpmt01501Row.buyeradrs2 = $scope.buyeradrs2;
		dtoSpmt01501Row.buyeradrs3 = $scope.buyeradrs3;
		dtoSpmt01501Row.buyertel = $scope.buyertel;
		dtoSpmt01501Row.buyerfax = $scope.buyerfax;
		dtoSpmt01501Row.buyerusrnm = $scope.buyerusrnm;
		dtoSpmt01501Row.buyermailadrs = $scope.buyermailadrs;
		dtoSpmt01501Row.buyersortno = $scope.buyersortno;
		dtoSpmt01501Row.buyerremark = $scope.buyerremark;
	}

	/**
	 * Model to request for check detail
	 */
	function fnGetRequestCheckDetail () {
		var request = {
			"accessInfo" : {
				"USRCD" : loginInfo.usrcd,
				"LANG": loginInfo.lang,
				"CSTMCD" : loginInfo.cstmcd,
				"BRNCHCD" : loginInfo.brnchcd,
				"TOKEN": loginInfo.token
			},
			"formItemList": formItemList,
			"spmt01501CheckDetailCondition" : {
				"MODE" : $scope.hiddenMODE,
				"BUYERCD" : dtoSpmt01501Row.buyercdAddNew,
				"BUYERNM" : dtoSpmt01501Row.buyernmAddNew,
				"BUYERRNM" : dtoSpmt01501Row.buyerrnmAddNew,
				"BUYERZIP" : dtoSpmt01501Row.buyerzip,
				"BUYERADRS1" : dtoSpmt01501Row.buyeradrs1,
				"BUYERADRS2" : dtoSpmt01501Row.buyeradrs2,
				"BUYERADRS3" : dtoSpmt01501Row.buyeradrs3,
				"BUYERTEL" : dtoSpmt01501Row.buyertel,
				"BUYERFAX" :dtoSpmt01501Row.buyerfax,
				"BUYERUSRNM" : dtoSpmt01501Row.buyerusrnm,
				"BUYERMAILADRS" : dtoSpmt01501Row.buyermailadrs,
				"BUYERSORTNO": dtoSpmt01501Row.buyersortno,
				"BUYERREMARK" : dtoSpmt01501Row.buyerremark
			}
		}

		return request;
	}

	/**
	 * Get request for register
	 */
	function fnGetRequestRegister () {
		var request = {
			"accessInfo" : {
				"USRCD" : loginInfo.usrcd,
				"LANG": loginInfo.lang,
				"CSTMCD" : loginInfo.cstmcd,
				"BRNCHCD" : loginInfo.brnchcd,
				"TOKEN": loginInfo.token
			},
			"formItemList": formItemList,
			"spmt01501RegistCondition" : {
				"BUYERCD" : dtoSpmt01501Row.buyercdAddNew,
				"BUYERNM" : dtoSpmt01501Row.buyernmAddNew,
				"BUYERRNM" : dtoSpmt01501Row.buyerrnmAddNew,
				"BUYERZIP" : dtoSpmt01501Row.buyerzip,
				"BUYERADRS1" : dtoSpmt01501Row.buyeradrs1,
				"BUYERADRS2" : dtoSpmt01501Row.buyeradrs2,
				"BUYERADRS3" : dtoSpmt01501Row.buyeradrs3,
				"BUYERTEL" : dtoSpmt01501Row.buyertel,
				"BUYERFAX" : dtoSpmt01501Row.buyerfax,
				"BUYERUSRNM" : dtoSpmt01501Row.buyerusrnm,
				"BUYERMAILADRS" : dtoSpmt01501Row.buyermailadrs,
				"BUYERSORTNO": dtoSpmt01501Row.buyersortno,
				"BUYERREMARK" : dtoSpmt01501Row.buyerremark,
			}
		}
		return request;
	}

	/**
	 * Get request for update
	 */
	function fnGetRequestUpdate () {
		var request = {
			"accessInfo" : {
				"USRCD" : loginInfo.usrcd,
				"LANG": loginInfo.lang,
				"CSTMCD" : loginInfo.cstmcd,
				"BRNCHCD" : loginInfo.brnchcd,
				"TOKEN": loginInfo.token
			},
			"formItemList": formItemList,
			"spmt01501UpdateCondition" : {
				"BUYERCD" : dtoSpmt01501Row.buyercdAddNew,
				"BUYERNM" : dtoSpmt01501Row.buyernmAddNew,
				"BUYERRNM" : dtoSpmt01501Row.buyerrnmAddNew,
				"BUYERZIP" : dtoSpmt01501Row.buyerzip,
				"BUYERADRS1" : dtoSpmt01501Row.buyeradrs1,
				"BUYERADRS2" : dtoSpmt01501Row.buyeradrs2,
				"BUYERADRS3" : dtoSpmt01501Row.buyeradrs3,
				"BUYERTEL" : dtoSpmt01501Row.buyertel,
				"BUYERFAX" : dtoSpmt01501Row.buyerfax,
				"BUYERUSRNM" : dtoSpmt01501Row.buyerusrnm,
				"BUYERMAILADRS" : dtoSpmt01501Row.buyermailadrs,
				"BUYERSORTNO": dtoSpmt01501Row.buyersortno,
				"BUYERREMARK" : dtoSpmt01501Row.buyerremark,
				"UPDDATETIMEHidden" : $scope.updatetime
			}
		}

		return request;
	}

	/**
	 * Authority list
	 * @param type 0 or 1, show delete button
	 */
	function fnAuthKbnList ( type ) {
		switch ( authKbnList[ 'SPMT01501' ] ) {
			case '1':
				document.getElementById( 'btnSearch' ).style.display = '';
				document.getElementById( 'btnClear' ).style.display = '';
				document.getElementById( 'btnNew' ).style.display = 'none';
				document.getElementById( 'btnConfirm' ).style.display = 'none';
				document.getElementById( 'btnDelete' ).style.display = 'none';
				break;
			case '2':
				document.getElementById( 'btnSearch' ).style.display = '';
				document.getElementById( 'btnClear' ).style.display = '';
				document.getElementById( 'btnNew' ).style.display = '';
				document.getElementById( 'btnConfirm' ).style.display = '';
				document.getElementById( 'btnDelete' ).style.display = ( type == 1 ) ? 'none' : '';
				break;
		}
	}

	/**
	 * Return value '' if String equal NULL
	 * @param str input string
	 */
	function fnNullString ( str ) {
		if ( str == 'NULL' ) {
			return '';
		} else {
			return str;
		}
	}

	/**
	 * Confirm success
	 */
	function fnAddNewSuccess () {
		fnClearAddNew();
		fnSetHidden();
		$scope.hiddenMODE = modeIns;
		$scope.chkOptCls = false;
		fnAuthKbnList(1);
		infoPopup( AlertService, message[ 'MI000008' ], infoTitle );
		$scope.$apply();
	}

	/**
	 * Get all model in screen set to object
	 */
	function fnGetObjScreen () {
		var initDtoSpmt01501Row = new spmt01501RowDto();
		initDtoSpmt01501Row.buyercdAddNew = $scope.buyercdAddNew == undefined ? "" : $scope.buyercdAddNew;
		initDtoSpmt01501Row.buyernmAddNew = $scope.buyernmAddNew == undefined ? "" : $scope.buyernmAddNew;
		initDtoSpmt01501Row.buyerrnmAddNew = $scope.buyerrnmAddNew == undefined ? "" : $scope.buyerrnmAddNew;
		initDtoSpmt01501Row.buyerzip = $scope.buyerzip == undefined ? "" : $scope.buyerzip;
		initDtoSpmt01501Row.buyeradrs1 = $scope.buyeradrs1 == undefined ? "" : $scope.buyeradrs1;
		initDtoSpmt01501Row.buyeradrs2 = $scope.buyeradrs2 == undefined ? "" : $scope.buyeradrs2;
		initDtoSpmt01501Row.buyeradrs3 = $scope.buyeradrs3 == undefined ? "" : $scope.buyeradrs3;
		initDtoSpmt01501Row.buyertel = $scope.buyertel == undefined ? "" : $scope.buyertel;
		initDtoSpmt01501Row.buyerfax = $scope.buyerfax == undefined ? "" : $scope.buyerfax;
		initDtoSpmt01501Row.buyerusrnm = $scope.buyerusrnm == undefined ? "" : $scope.buyerusrnm;
		initDtoSpmt01501Row.buyermailadrs = $scope.buyermailadrs == undefined ? "" : $scope.buyermailadrs;
		initDtoSpmt01501Row.buyersortno = $scope.buyersortno == undefined ? "" : $scope.buyersortno;
		initDtoSpmt01501Row.buyerremark = $scope.buyerremark == undefined ? "" : $scope.buyerremark;
		return initDtoSpmt01501Row;
	}

	/**
	 * Get all model in search set to object
	 */
	function fnGetObjScreenSearch () {
		var initDtoSpmt01501 = new spmt01501Dto();
		initDtoSpmt01501.buyercd = $scope.buyercd == undefined ? "" : $scope.buyercd;
		initDtoSpmt01501.buyernm = $scope.buyernm == undefined ? "" : $scope.buyernm;
		initDtoSpmt01501.pageNum = dtoSpmt01501 == null ? 1 : dtoSpmt01501.pageNum;
		return initDtoSpmt01501;
	}
	/**
	 * Check add new
	 */
	function fnCheckDetailChange () {
		var currentDtoSpmt01501Row = fnGetObjScreen();
		return fnCheckChange( hiddenDtoSpmt01501Row, currentDtoSpmt01501Row );
	}

} ] )

function buyercdLinkClick(buyercd, detailUpddatetime) {
	buyercd = unescapeHtml(buyercd);
	var element = document.getElementById("gridHandsontable");
	var $scope = angular.element(element).scope();
	$scope.buyercdLinkClick(buyercd, detailUpddatetime);
}