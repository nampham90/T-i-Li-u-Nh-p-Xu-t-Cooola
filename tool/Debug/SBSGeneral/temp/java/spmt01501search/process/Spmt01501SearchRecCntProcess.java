package jp.co.brycen.wms.#LowerScreenId#search.process;

import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.wms.#LowerScreenId#search.dto.#FirstScreenId#SearchResponse;
import jp.co.brycen.wms.#LowerScreenId#search.process.#FirstScreenId#SearchAllRecProcess;

/**
 * 画面：依頼主マスタ
 * 概要：検索(件数取得)
 */
public class #FirstScreenId#SearchRecCntProcess extends #FirstScreenId#SearchAllRecProcess {

	public #FirstScreenId#SearchRecCntProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	protected String setSelectFields() {
		StringBuilder strSql = new StringBuilder();
		strSql.append("	count(*) as CNT ");

		return strSql.toString();
	}

	@Override
	protected void setResponse(ResultSet rs, #FirstScreenId#SearchResponse response) throws SQLException {
		while (rs.next()) {
			response.DATACNT = rs.getLong("CNT");
		}
	}
}
