package jp.co.brycen.wms.#LowerScreenId#regist.process;

import jp.co.brycen.common.FuncID;
import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.database.DBStatement;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.exception.FatalException;
import jp.co.brycen.common.exception.ProcessCheckErrorException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.wms.#LowerScreenId#regist.dto.#FirstScreenId#RegistRequest;
import jp.co.brycen.wms.#LowerScreenId#regist.dto.#FirstScreenId#RegistResponse;

/**
 * 画面：依頼主マスタ
 * 概要：登録
 */
public class #FirstScreenId#RegistProcess extends AbstractProcess {
	public #FirstScreenId#RegistProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response,
			AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException {
		DBStatement ps = null;
		try {
			#FirstScreenId#RegistRequest req#FirstScreenId#Regist = (#FirstScreenId#RegistRequest) request;
			
			#InitVariable#@@req#FirstScreenId#Regist.#LowerScreenId#RegistCondition;

			String usrcdLogin = request.accessInfo.USRCD;
			String cstmcd = request.accessInfo.CSTMCD;
			#Validate#
			
			StringBuilder strSql = new StringBuilder();
			// SQL

			ps = dba.prepareStatement(strSql);

			#PsSetstring#
			ps.executeUpdate();
		} finally {
			if (ps != null)
				ps.close();
		}

		#FirstScreenId#RegistResponse res#FirstScreenId#Regist = (#FirstScreenId#RegistResponse) response;
		return res#FirstScreenId#Regist;
	}

	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request) {
		return new #FirstScreenId#RegistResponse();
	}
}
