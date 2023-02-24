package jp.co.brycen.wms.#LowerScreenId#search.process;


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
import jp.co.brycen.wms.#LowerScreenId#.dto.#FirstScreenId#SearchRowDto;
import jp.co.brycen.wms.#LowerScreenId#search.dto.#FirstScreenId#SearchRequest;
import jp.co.brycen.wms.#LowerScreenId#search.dto.#FirstScreenId#SearchResponse;
import jp.co.brycen.wms.tmt150check.dto.Tmt150CheckRequest;
import jp.co.brycen.wms.tmt150check.dto.Tmt150CheckResponse;
import jp.co.brycen.wms.tmt150check.process.Tmt150CheckProcess;
/**
 * 画面：依頼主マスタ
 * 概要：検索
 */
public class #FirstScreenId#SearchAllRecProcess extends AbstractProcess {
	public #FirstScreenId#SearchAllRecProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	protected void beforeProcess(DBAccessor dba, AbstractRequest request, AbstractResponse response,
			AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException {
		List<ErrorDto> msg = new ArrayList<ErrorDto>();

			// リクエスト
			#FirstScreenId#SearchRequest req#FirstScreenId#Search = (#FirstScreenId#SearchRequest) request;
			#InitVariable#@@req#FirstScreenId#Search.#LowerScreenId#SearchCondition;

			#Validate#

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
			#FirstScreenId#SearchRequest req#FirstScreenId#Search = (#FirstScreenId#SearchRequest) request;
			String cstmcd = request.accessInfo.CSTMCD;
			String brnchnm = request.accessInfo.BRNCHNM;

			#InitVariable#@@req#FirstScreenId#Search.#LowerScreenId#SearchCondition;

			// SQL
			StringBuilder strSql = new StringBuilder();
			

			ps = dba.prepareStatement(strSql);
			#PsSetstring#

			rs = ps.executeQuery();
			#FirstScreenId#SearchResponse res#FirstScreenId#Search = (#FirstScreenId#SearchResponse) response;
			setResponse(rs, res#FirstScreenId#Search, brnchnm);

			if (res#FirstScreenId#Search.rows != null && res#FirstScreenId#Search.rows.size() == 0) {
				res#FirstScreenId#Search.rows = null;
			}

			return res#FirstScreenId#Search;
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
		return new #FirstScreenId#SearchResponse();
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
	protected void setResponse(ResultSet rs, #FirstScreenId#SearchResponse response) throws SQLException {
		ArrayList<#FirstScreenId#SearchRowDto> lst = new ArrayList<#FirstScreenId#SearchRowDto>();
		while (rs.next()) {
			#FirstScreenId#SearchRowDto row = new #FirstScreenId#SearchRowDto();
			#SetResponseRowDto#
			lst.add(row);
		}

		response.rows = lst;
	}
}
