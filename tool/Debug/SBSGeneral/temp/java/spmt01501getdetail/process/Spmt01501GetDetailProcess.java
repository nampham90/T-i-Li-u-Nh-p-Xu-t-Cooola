package jp.co.brycen.wms.#LowerScreenId#getdetail.process;

import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.database.DBStatement;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.exception.FatalException;
import jp.co.brycen.common.exception.ProcessCheckErrorException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.wms.#LowerScreenId#getdetail.dto.#FirstScreenId#GetDetailRequest;
import jp.co.brycen.wms.#LowerScreenId#getdetail.dto.#FirstScreenId#GetDetailResponse;

/**
 * 画面：依頼主マスタ
 * 概要：詳細取得
 */
public class #FirstScreenId#GetDetailProcess extends AbstractProcess{

	public #FirstScreenId#GetDetailProcess(ILogSender logSender) {
		super(logSender);
	}
	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response,
			AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException {

		ResultSet rs = null;
		DBStatement ps = null;
		try {
			#FirstScreenId#GetDetailRequest req#FirstScreenId#GetDetail = (#FirstScreenId#GetDetailRequest) request;
			String cstmcd = request.accessInfo.CSTMCD;
			#InitVariable#@@req#FirstScreenId#GetDetail.#LowerScreenId#GetDetailCondition;

			// SQL
			StringBuilder strSql = new StringBuilder();
			

			ps = dba.prepareStatement(strSql);
			#PsSetstring#
			rs = ps.executeQuery();

			#FirstScreenId#GetDetailResponse res#FirstScreenId#GetDetail = (#FirstScreenId#GetDetailResponse) response;
			setResponse(rs, res#FirstScreenId#GetDetail);
			
			return res#FirstScreenId#GetDetail;
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
		return new #FirstScreenId#GetDetailResponse();
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
