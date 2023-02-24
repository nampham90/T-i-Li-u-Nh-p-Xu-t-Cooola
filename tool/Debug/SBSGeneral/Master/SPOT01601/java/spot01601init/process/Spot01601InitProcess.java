package jp.co.brycen.wms.spot01601init.process;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.exception.FatalException;
import jp.co.brycen.common.exception.ProcessCheckErrorException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.wms.spot01601init.dto.Spot01601InitResponse;


/**
 * 画面：衣類主マスタ
 * 概要：初期表示
 */

public class Spot01601InitProcess extends AbstractProcess{
	public Spot01601InitProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response,
			AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException {
		return response;
	}

	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request) {
		return new Spot01601InitResponse();
	}
}
