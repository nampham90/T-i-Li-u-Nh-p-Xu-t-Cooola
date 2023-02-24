package jp.co.brycen.wms.#LowerScreenId#.process;

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
import jp.co.brycen.wms.#LowerScreenId#.dto.#FirstScreenId#ExclusionConditionDto;
import jp.co.brycen.wms.#LowerScreenId#.dto.#FirstScreenId#ExclusionResultDto;

public class #FirstScreenId#ExclusionProcess extends AbstractProcess  {

	public #FirstScreenId#ExclusionProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response,
			AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException {

		ResultSet rs = null;
		DBStatement ps = null;

		#FirstScreenId#ExclusionConditionDto req#FirstScreenId#Exclusion = (#FirstScreenId#ExclusionConditionDto) request;
		
		#InitVariable#@@req#FirstScreenId#Exclusion;
		
		String upddatetime = "";
		#FirstScreenId#ExclusionResultDto res#FirstScreenId#Exclusion = (#FirstScreenId#ExclusionResultDto) response;
		try {
			StringBuilder strSQL = new StringBuilder();
			strSQL.append("SELECT");
			strSQL.append("	UPDDATETIME ");
			strSQL.append("FROM ");
			strSQL.append("	TMT150_BUYER ");
			strSQL.append("WHERE ");
			strSQL.append("	CSTMCD  = ? ");
			strSQL.append("	AND BUYERCD = ? ");
			strSQL.append(";");

			ps = dba.prepareStatement(strSQL);
			ps.setString(1, cstmcd);
			ps.setString(2, buyercd);
			rs = ps.executeQuery();

			while (rs.next()) {
				upddatetime = rs.getString("UPDDATETIME");
			}

			res#FirstScreenId#Exclusion.ret = upddatetime.equals(upddatetimeHidden);
			return res#FirstScreenId#Exclusion;
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
		return new #FirstScreenId#ExclusionResultDto();
	}
}
