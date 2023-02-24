package jp.co.brycen.wms.spot01601search.process;

import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.wms.spot01601search.dto.Spot01601SearchResponse;
import jp.co.brycen.wms.spot01601search.process.Spot01601SearchAllRecProcess;

/**
 * 画面：依頼主マスタ
 * 概要：検索(件数取得)
 */
public class Spot01601SearchRecCntProcess extends Spot01601SearchAllRecProcess {

	public Spot01601SearchRecCntProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	protected String setSelectFields() {
		StringBuilder strSql = new StringBuilder();
		strSql.append("	count(*) as CNT ");

		return strSql.toString();
	}

	@Override
	protected void setResponse(ResultSet rs, Spot01601SearchResponse response) throws SQLException {
		while (rs.next()) {
			response.DATACNT = rs.getLong("CNT");
		}
	}
}
