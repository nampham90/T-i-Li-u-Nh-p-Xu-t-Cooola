package jp.co.brycen.wms.spiv00801search.process;

import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.wms.spiv00801search.dto.Spiv00801SearchResponse;
import jp.co.brycen.wms.spiv00801search.process.Spiv00801SearchAllRecProcess;

/**
 * 画面：依頼主マスタ
 * 概要：検索(件数取得)
 */
public class Spiv00801SearchRecCntProcess extends Spiv00801SearchAllRecProcess {

	public Spiv00801SearchRecCntProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	protected String setSelectFields() {
		StringBuilder strSql = new StringBuilder();
		strSql.append("	count(*) as CNT ");

		return strSql.toString();
	}

	@Override
	protected void setResponse(ResultSet rs, Spiv00801SearchResponse response) throws SQLException {
		while (rs.next()) {
			response.DATACNT = rs.getLong("CNT");
		}
	}
}
