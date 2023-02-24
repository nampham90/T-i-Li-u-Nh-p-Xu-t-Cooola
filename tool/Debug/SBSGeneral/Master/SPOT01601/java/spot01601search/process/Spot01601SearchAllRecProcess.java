package jp.co.brycen.wms.spot01601search.process;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import jp.co.brycen.common.utility.MessageUtility;
import jp.co.brycen.common.utility.ValidateUtility;
import jp.co.brycen.wms.spot01601.dto.Spot01601SearchRowDto;
import jp.co.brycen.wms.spot01601search.dto.Spot01601SearchRequest;
import jp.co.brycen.wms.spot01601search.dto.Spot01601SearchResponse;
import jp.co.brycen.wms.tmt150check.dto.Tmt150CheckRequest;
import jp.co.brycen.wms.tmt150check.dto.Tmt150CheckResponse;
import jp.co.brycen.wms.tmt150check.process.Tmt150CheckProcess;
/**
 * 画面：依頼主マスタ
 * 概要：検索
 */
public class Spot01601SearchAllRecProcess extends AbstractProcess {
	public Spot01601SearchAllRecProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	protected void beforeProcess(DBAccessor dba, AbstractRequest request, AbstractResponse response,
			AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException {
		List<ErrorDto> msg = new ArrayList<ErrorDto>();

			// リクエスト
			Spot01601SearchRequest reqSpot01601Search = (Spot01601SearchRequest) request;
     String soplndatefrom = reqSpot01601Search.spot01601SearchCondition.SOPLNDATEFROM;
     String soplndateto = reqSpot01601Search.spot01601SearchCondition.SOPLNDATETO;
     String btno = reqSpot01601Search.spot01601SearchCondition.BTNO;
     String prcsno = reqSpot01601Search.spot01601SearchCondition.PRCSNO;
     String inno = reqSpot01601Search.spot01601SearchCondition.INNO;
     String pcknglstno = reqSpot01601Search.spot01601SearchCondition.PCKNGLSTNO;

     if (!ValidateUtility.CheckNull(btno) && ValidateUtility.CheckMaxLength(btno, 20)) { 
	        ErrorDto ret = new ErrorDto(); 
	        ret.controlID = "btno"; 
	        ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "20"); 
	        msg.add(ret); 
     } 
     if (!ValidateUtility.CheckNull(prcsno) && ValidateUtility.CheckMaxLength(prcsno, 20)) { 
	        ErrorDto ret = new ErrorDto(); 
	        ret.controlID = "prcsno"; 
	        ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "20"); 
	        msg.add(ret); 
     } 
     if (!ValidateUtility.CheckNull(inno) && ValidateUtility.CheckMaxLength(inno, 20)) { 
	        ErrorDto ret = new ErrorDto(); 
	        ret.controlID = "inno"; 
	        ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "20"); 
	        msg.add(ret); 
     } 
     if (!ValidateUtility.CheckNull(pcknglstno) && ValidateUtility.CheckMaxLength(pcknglstno, 20)) { 
	        ErrorDto ret = new ErrorDto(); 
	        ret.controlID = "pcknglstno"; 
	        ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "20"); 
	        msg.add(ret); 
     } 

			// Throw the error message if there is an error
			if (msg.size() > 0) {
				throw new ProcessCheckErrorException(msg, ConstantValue.FATAL_ERROR);
			}
	}

	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response,
			AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException {

		ResultSet rs = null;
		DBStatement ps = null;
		try {
			Spot01601SearchRequest reqSpot01601Search = (Spot01601SearchRequest) request;
			String cstmcd = request.accessInfo.CSTMCD;
			String brnchnm = request.accessInfo.BRNCHNM;

     String soplndatefrom = reqSpot01601Search.spot01601SearchCondition.SOPLNDATEFROM;
     String soplndateto = reqSpot01601Search.spot01601SearchCondition.SOPLNDATETO;
     String btno = reqSpot01601Search.spot01601SearchCondition.BTNO;
     String prcsno = reqSpot01601Search.spot01601SearchCondition.PRCSNO;
     String inno = reqSpot01601Search.spot01601SearchCondition.INNO;
     String pcknglstno = reqSpot01601Search.spot01601SearchCondition.PCKNGLSTNO;

			// SQL
			StringBuilder strSql = new StringBuilder();
			

			ps = dba.prepareStatement(strSql);
 integer index = 0;
         index++;
         ps.setString(index, soplndatefrom);
         index++;
         ps.setString(index, soplndateto);
         index++;
         ps.setString(index, btno);
         index++;
         ps.setString(index, prcsno);
         index++;
         ps.setString(index, inno);
         index++;
         ps.setString(index, pcknglstno);

			rs = ps.executeQuery();
			Spot01601SearchResponse resSpot01601Search = (Spot01601SearchResponse) response;
			setResponse(rs, resSpot01601Search, brnchnm);

			if (resSpot01601Search.rows != null && resSpot01601Search.rows.size() == 0) {
				resSpot01601Search.rows = null;
			}

			return resSpot01601Search;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			} catch (SQLException e) {
				throw new DBException(e);
			}
		}
	}

	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request) {
		return new Spot01601SearchResponse();
	}
	
    @Override
	protected String setSelectFields() {
		StringBuilder strSql = new StringBuilder();
		strSql.append("");

		return strSql.toString();
	}

	/**
	 * Set result to list object
	 *
	 * @param rs
	 *            resultset
	 * @param response
	 *            object of result
	 * @param brnchnm
	 *            倉庫
	 * @throws SQLException
	 */
	protected void setResponse(ResultSet rs, Spot01601SearchResponse response) throws SQLException {
		ArrayList<Spot01601SearchRowDto> lst = new ArrayList<Spot01601SearchRowDto>();
		while (rs.next()) {
			Spot01601SearchRowDto row = new Spot01601SearchRowDto();
     row.SOPLNDATE = rs.getString("SOPLNDATE");
     row.SHIPMNTPLNDAT = rs.getString("SHIPMNTPLNDAT");
     row.INCDNTPLNNO = rs.getString("INCDNTPLNNO");
     row.SOORDNO = rs.getString("SOORDNO");
     row.PRCSNO = rs.getString("PRCSNO");
     row.CSTMORDNO = rs.getString("CSTMORDNO");
     row.SOGRPNO = rs.getString("SOGRPNO");
     row.PCKUSRCD = rs.getString("PCKUSRCD");
     row.PCKNGDATETIME = rs.getString("PCKNGDATETIME");
     row.BTNO = rs.getString("BTNO");
     row.DATESRALNO = rs.getString("DATESRALNO");
     row.INNO = rs.getString("INNO");
     row.PCKNGLSTNO = rs.getString("PCKNGLSTNO");
			lst.add(row);
		}

		response.rows = lst;
	}
}
