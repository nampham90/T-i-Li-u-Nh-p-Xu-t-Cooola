package jp.co.brycen.wms.spiv00801regist.process;

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
import jp.co.brycen.wms.spiv00801regist.dto.Spiv00801RegistRequest;
import jp.co.brycen.wms.spiv00801regist.dto.Spiv00801RegistResponse;

/**
 * 画面：依頼主マスタ
 * 概要：登録
 */
public class Spiv00801RegistProcess extends AbstractProcess {
	public Spiv00801RegistProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response,
			AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException {
		DBStatement ps = null;
		try {
			Spiv00801RegistRequest reqSpiv00801Regist = (Spiv00801RegistRequest) request;
			
     String invntrno = reqSpiv00801Regist.spiv00801RegistCondition.INVNTRNO;
     String adjstdate = reqSpiv00801Regist.spiv00801RegistCondition.ADJSTDATE;
     String upddatetime = reqSpiv00801Regist.spiv00801RegistCondition.UPDDATETIME;
     String rows = reqSpiv00801Regist.spiv00801RegistCondition.ROWS;

			String usrcdLogin = request.accessInfo.USRCD;
			String cstmcd = request.accessInfo.CSTMCD;
     if (ValidateUtility.CheckNull(invntrno)) { 
	        ErrorDto ret = new ErrorDto(); 
	        ret.controlID = "invntrno"; 
	        ret.errMsg = MessageUtility.getMessageMsg("ME000116", request.accessInfo); 
	        msg.add(ret); 
     } 
     if (ValidateUtility.CheckNull(adjstdate)) { 
	        ErrorDto ret = new ErrorDto(); 
	        ret.controlID = "adjstdate"; 
	        ret.errMsg = MessageUtility.getMessageMsg("ME000116", request.accessInfo); 
	        msg.add(ret); 
     } 
			
			StringBuilder strSql = new StringBuilder();
			// SQL

			ps = dba.prepareStatement(strSql);

 integer index = 0;
     if (!ValidateUtility.CheckNull(invntrno)) { 
         index++;
         ps.setString(index, invntrno);
     } 
     if (!ValidateUtility.CheckNull(adjstdate)) { 
         index++;
         ps.setString(index, adjstdate);
     } 
         index++;
         ps.setString(index, upddatetime);
         index++;
         ps.setString(index, rows);
			ps.executeUpdate();
		} finally {
			if (ps != null)
				ps.close();
		}

		Spiv00801RegistResponse resSpiv00801Regist = (Spiv00801RegistResponse) response;
		return resSpiv00801Regist;
	}

	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request) {
		return new Spiv00801RegistResponse();
	}
}
