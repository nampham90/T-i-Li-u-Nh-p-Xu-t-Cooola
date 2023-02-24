package jp.co.brycen.wms.#LowerScreenId#checkdetail.process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.brycen.common.ConstantValue;
import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.database.DBStatement;
import jp.co.brycen.common.dto.ErrorDto;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.exception.FatalException;
import jp.co.brycen.common.exception.ProcessCheckErrorException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.utility.CommonUtility;
import jp.co.brycen.common.utility.FormatUtility;
import jp.co.brycen.common.utility.MessageUtility;
import jp.co.brycen.common.utility.ValidateUtility;
import jp.co.brycen.wms.#LowerScreenId#checkdetail.dto.#FirstScreenId#CheckDetailRequest;
import jp.co.brycen.wms.#LowerScreenId#checkdetail.dto.#FirstScreenId#CheckDetailResponse;
/**
 * 画面：依頼主マスタ
 * 概要：
 */
public class #FirstScreenId#CheckDetailProcess extends AbstractProcess{
	public #FirstScreenId#CheckDetailProcess(ILogSender logSender) {
		super(logSender);
	}
	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response,
			AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException {
		List<ErrorDto> msg = new ArrayList<ErrorDto>();
		#FirstScreenId#CheckDetailRequest req#FirstScreenId#CheckDetail = (#FirstScreenId#CheckDetailRequest) request;
		
		String cstmcd = request.accessInfo.CSTMCD;
		
		#InitVariable#@@req#FirstScreenId#CheckDetail.#LowerScreenId#CheckDetailCondition;

		// 画面項目名を取得
		HashMap<String,String> formItemList = CommonUtility.getFormItemList(req#FirstScreenId#CheckDetail.formItemList);

		#Validate#

		if (msg.size() > 0) {
			throw new ProcessCheckErrorException(msg, ConstantValue.FATAL_ERROR);
		}

		return response;
	}

	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request) {
		return new #FirstScreenId#CheckDetailResponse();
	}
}
