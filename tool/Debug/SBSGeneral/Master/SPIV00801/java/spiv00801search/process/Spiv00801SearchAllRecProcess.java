package jp.co.brycen.wms.spiv00801search.process;


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
import jp.co.brycen.wms.spiv00801.dto.Spiv00801SearchRowDto;
import jp.co.brycen.wms.spiv00801search.dto.Spiv00801SearchRequest;
import jp.co.brycen.wms.spiv00801search.dto.Spiv00801SearchResponse;
import jp.co.brycen.wms.tmt150check.dto.Tmt150CheckRequest;
import jp.co.brycen.wms.tmt150check.dto.Tmt150CheckResponse;
import jp.co.brycen.wms.tmt150check.process.Tmt150CheckProcess;
/**
 * 画面：依頼主マスタ
 * 概要：検索
 */
public class Spiv00801SearchAllRecProcess extends AbstractProcess {
	public Spiv00801SearchAllRecProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	protected void beforeProcess(DBAccessor dba, AbstractRequest request, AbstractResponse response,
			AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException {
		List<ErrorDto> msg = new ArrayList<ErrorDto>();

			// リクエスト
			Spiv00801SearchRequest reqSpiv00801Search = (Spiv00801SearchRequest) request;
     String invntrno = reqSpiv00801Search.spiv00801SearchCondition.INVNTRNO;

     if (ValidateUtility.CheckNull(invntrno)) { 
	        ErrorDto ret = new ErrorDto(); 
	        ret.controlID = "invntrno"; 
	        ret.errMsg = MessageUtility.getMessageMsg("ME000116", request.accessInfo); 
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
			Spiv00801SearchRequest reqSpiv00801Search = (Spiv00801SearchRequest) request;
			String cstmcd = request.accessInfo.CSTMCD;
			String brnchnm = request.accessInfo.BRNCHNM;

     String invntrno = reqSpiv00801Search.spiv00801SearchCondition.INVNTRNO;

			// SQL
			StringBuilder strSql = new StringBuilder();
			

			ps = dba.prepareStatement(strSql);
 integer index = 0;
     if (!ValidateUtility.CheckNull(invntrno)) { 
         index++;
         ps.setString(index, invntrno);
     } 

			rs = ps.executeQuery();
			Spiv00801SearchResponse resSpiv00801Search = (Spiv00801SearchResponse) response;
			setResponse(rs, resSpiv00801Search, brnchnm);

			if (resSpiv00801Search.rows != null && resSpiv00801Search.rows.size() == 0) {
				resSpiv00801Search.rows = null;
			}

			return resSpiv00801Search;
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
		return new Spiv00801SearchResponse();
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
	protected void setResponse(ResultSet rs, Spiv00801SearchResponse response) throws SQLException {
		ArrayList<Spiv00801SearchRowDto> lst = new ArrayList<Spiv00801SearchRowDto>();
		while (rs.next()) {
			Spiv00801SearchRowDto row = new Spiv00801SearchRowDto();
     row.INVNTRNO = rs.getString("INVNTRNO");
     row.ITEMCD = rs.getString("ITEMCD");
     row.ITEMNM = rs.getString("ITEMNM");
     row.LCTNCD = rs.getString("LCTNCD");
     row.QLTYCD = rs.getString("QLTYCD");
     row.QLTYNM = rs.getString("QLTYNM");
     row.INVNTRCSQTY = rs.getString("INVNTRCSQTY");
     row.INVNTRBLQTY = rs.getString("INVNTRBLQTY");
     row.INVNTRPCEQTY = rs.getString("INVNTRPCEQTY");
     row.INVNTRALLQTY = rs.getString("INVNTRALLQTY");
     row.STCKMNGKEY1 = rs.getString("STCKMNGKEY1");
     row.STCKMNGKEY2 = rs.getString("STCKMNGKEY2");
     row.STCKMNGKEY3 = rs.getString("STCKMNGKEY3");
     row.STCKMNGKEY4 = rs.getString("STCKMNGKEY4");
     row.STCKMNGKEY5 = rs.getString("STCKMNGKEY5");
     row.STCKMNGDATE1 = rs.getString("STCKMNGDATE1");
     row.STCKMNGDATE2 = rs.getString("STCKMNGDATE2");
     row.STCKMNGDATE3 = rs.getString("STCKMNGDATE3");
     row.STCKMNGDATE4 = rs.getString("STCKMNGDATE4");
     row.STCKMNGDATE5 = rs.getString("STCKMNGDATE5");
     row.STCKMNGNUM1 = rs.getString("STCKMNGNUM1");
     row.STCKMNGNUM2 = rs.getString("STCKMNGNUM2");
     row.STCKMNGNUM3 = rs.getString("STCKMNGNUM3");
     row.STCKMNGNUM4 = rs.getString("STCKMNGNUM4");
     row.STCKMNGNUM5 = rs.getString("STCKMNGNUM5");
     row.STCKNMNEECD = rs.getString("STCKNMNEECD");
     row.STCKNMNEENM = rs.getString("STCKNMNEENM");
     row.INVNTRUSRCD = rs.getString("INVNTRUSRCD");
     row.INVNTRUSRNM = rs.getString("INVNTRUSRNM");
     row.INVNTRDATETIME = rs.getString("INVNTRDATETIME");
     row.UPDDATETIME = rs.getString("UPDDATETIME");
     row.DATASTCKQTY = rs.getString("DATASTCKQTY");
     row.STACKQTY = rs.getString("STACKQTY");
			lst.add(row);
		}

		response.rows = lst;
	}
}
