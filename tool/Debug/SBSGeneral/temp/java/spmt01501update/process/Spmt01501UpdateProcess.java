package jp.co.brycen.wms.#LowerScreenId#update.process;

import java.util.ArrayList;
import java.util.List;

import jp.co.brycen.common.ConstantValue;
import jp.co.brycen.common.FuncID;
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
import jp.co.brycen.common.utility.FormatUtility;
import jp.co.brycen.common.utility.MessageUtility;
import jp.co.brycen.common.utility.ValidateUtility;
import jp.co.brycen.wms.sequence.dto.SequenceRequest;
import jp.co.brycen.wms.sequence.dto.SequenceResponse;
import jp.co.brycen.wms.sequence.process.SequenceProcess;
import jp.co.brycen.wms.#LowerScreenId#.dto.#FirstScreenId#ExclusionConditionDto;
import jp.co.brycen.wms.#LowerScreenId#.dto.#FirstScreenId#ExclusionResultDto;
import jp.co.brycen.wms.#LowerScreenId#.process.#FirstScreenId#ExclusionProcess;
import jp.co.brycen.wms.#LowerScreenId#update.dto.#FirstScreenId#UpdateRequest;
import jp.co.brycen.wms.#LowerScreenId#update.dto.#FirstScreenId#UpdateResponse;

/**
 * 画面：依頼主マスタ
 * 概要：
 */
public class #FirstScreenId#UpdateProcess extends AbstractProcess{
	public #FirstScreenId#UpdateProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	protected void beforeProcess(DBAccessor dba, AbstractRequest request, AbstractResponse response,
			AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException {

		// Cast to update request
		#FirstScreenId#UpdateRequest req#FirstScreenId#Update = (#FirstScreenId#UpdateRequest) request;

		// Cast to exclusion request
		#FirstScreenId#ExclusionConditionDto req#FirstScreenId#Exclusion = new #FirstScreenId#ExclusionConditionDto();
		req#FirstScreenId#Exclusion.accessInfo = req#FirstScreenId#Update.accessInfo;
		req#FirstScreenId#Exclusion.CSTMCD = request.accessInfo.CSTMCD;
		
		#InitVariable#@@req#FirstScreenId#Update.#LowerScreenId#UpdateCondition;

		#FirstScreenId#ExclusionProcess proc#FirstScreenId#Exclusion = new #FirstScreenId#ExclusionProcess(this);
		#FirstScreenId#ExclusionResultDto res#FirstScreenId#Exclusion = (#FirstScreenId#ExclusionResultDto) proc#FirstScreenId#Exclusion
				.execute(dba, req#FirstScreenId#Exclusion, response);

		List<ErrorDto> msg = new ArrayList<ErrorDto>();
		if (!res#FirstScreenId#Exclusion.ret) {
			ErrorDto ret = new ErrorDto();
			ret.controlID = "updatetime";
			ret.errMsg = MessageUtility.getMessageMsg("ME000003", request.accessInfo);
			msg.add(ret);
		}

		if (msg.size() > 0) {
			throw new ProcessCheckErrorException(msg, ConstantValue.FATAL_ERROR);
		}
	}

	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response,
			AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException {
		DBStatement ps = null;

		updateBuyerHistory(dba, request);
		updateBuyer(dba, request);

		#FirstScreenId#UpdateResponse res#FirstScreenId#Update = (#FirstScreenId#UpdateResponse) response;
		return res#FirstScreenId#Update;
	}

	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request) {
		return new #FirstScreenId#UpdateResponse();
	}

	protected void updateBuyer(DBAccessor dba, AbstractRequest request) throws FatalException, DBException, ProcessCheckErrorException {
		DBStatement ps = null;
		try {
			#FirstScreenId#UpdateRequest req#FirstScreenId#Update = (#FirstScreenId#UpdateRequest) request;
			
			#InitVariable#@@req#FirstScreenId#Update.#LowerScreenId#UpdateCondition;

			String usrcdLogin = request.accessInfo.USRCD;
			String cstmcd = request.accessInfo.CSTMCD;

			StringBuilder strSql = new StringBuilder();
			

			ps = dba.prepareStatement(strSql);
			#PsSetstring#
			ps.executeUpdate();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	protected void updateBuyerHistory(DBAccessor dba, AbstractRequest request) throws FatalException, DBException, ProcessCheckErrorException {
		DBStatement ps = null;
		try {
			#FirstScreenId#UpdateRequest req#FirstScreenId#Update = (#FirstScreenId#UpdateRequest) request;
			
			#InitVariable#@@req#FirstScreenId#Update.#LowerScreenId#UpdateCondition;

			String usrcdLogin = request.accessInfo.USRCD;
			String cstmcd = request.accessInfo.CSTMCD;

			String seqno = getSequence(dba, req#FirstScreenId#Update, "MTHS");

			StringBuilder strSql = new StringBuilder();
			

			ps = dba.prepareStatement(strSql);
			#PsSetstring#
			
			ps.executeUpdate();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	/**
	 * Get the sequence
	 *
	 * @param dba
	 * @param request
	 * @return
	 * @throws FatalException
	 * @throws DBException
	 * @throws ProcessCheckErrorException
	 */
	private String getSequence(DBAccessor dba, #FirstScreenId#UpdateRequest request, String prefix)
			throws FatalException, DBException, ProcessCheckErrorException {
		// Create request
		SequenceRequest reqSequence = new SequenceRequest();
		reqSequence.sequenceCondition.PREFIX = prefix;
		reqSequence.accessInfo = request.accessInfo;

		// Run the process to get the sequence
		SequenceProcess procSequence = new SequenceProcess(this);
		SequenceResponse resSequence = (SequenceResponse) procSequence.execute(dba, reqSequence, null);
		return resSequence.sequence.SEQUENCE;
	}

	protected String setUpdateFields() {
		StringBuilder strSql = new StringBuilder();
		strSql.append(" SEQNO");
		strSql.append(" ,RASNCD");
		strSql.append(" ,RASNNM");
		strSql.append(" ,RASNUSRCD");
		strSql.append(" ,RASNREMARK");
		strSql.append(" ,CSTMCD");
		strSql.append(" ,BUYERCD");
		strSql.append(" ,BUYERNM");
		strSql.append(" ,BUYERRNM");
		strSql.append(" ,BUYERZIP");
		strSql.append(" ,PRFCTRSCD");
		strSql.append(" ,JISCD");
		strSql.append(" ,BUYERADRS1");
		strSql.append(" ,BUYERADRS2");
		strSql.append(" ,BUYERADRS3");
		strSql.append(" ,BUYERTEL");
		strSql.append(" ,BUYERFAX");
		strSql.append(" ,BUYERUSRNM");
		strSql.append(" ,BUYERMAILADRS");
		strSql.append(" ,BUYERREMARK");
		strSql.append(" ,BUYERSORTNO");
		strSql.append(" ,CPTRNO");
		strSql.append(" ,CNTRYCD");
		strSql.append(" ,DPRTRPRTCD");
		strSql.append(" ,ARACD");
		strSql.append(" ,URL");
		strSql.append(" ,DELFLG");
		strSql.append(" ,CDSRV1");
		strSql.append(" ,CDSRV2");
		strSql.append(" ,CDSRV3");
		strSql.append(" ,CDSRV4");
		strSql.append(" ,CDSRV5");
		strSql.append(" ,CDSRV6");
		strSql.append(" ,CDSRV7");
		strSql.append(" ,CDSRV8");
		strSql.append(" ,CDSRV9");
		strSql.append(" ,CDSRV10");
		strSql.append(" ,CDSRV11");
		strSql.append(" ,CDSRV12");
		strSql.append(" ,CDSRV13");
		strSql.append(" ,CDSRV14");
		strSql.append(" ,CDSRV15");
		strSql.append(" ,CDSRV16");
		strSql.append(" ,CDSRV17");
		strSql.append(" ,CDSRV18");
		strSql.append(" ,CDSRV19");
		strSql.append(" ,CDSRV20");
		strSql.append(" ,CDSRV21");
		strSql.append(" ,CDSRV22");
		strSql.append(" ,CDSRV23");
		strSql.append(" ,CDSRV24");
		strSql.append(" ,CDSRV25");
		strSql.append(" ,CDSRV26");
		strSql.append(" ,CDSRV27");
		strSql.append(" ,CDSRV28");
		strSql.append(" ,CDSRV29");
		strSql.append(" ,CDSRV30");
		strSql.append(" ,CDSRV31");
		strSql.append(" ,CDSRV32");
		strSql.append(" ,CDSRV33");
		strSql.append(" ,CDSRV34");
		strSql.append(" ,CDSRV35");
		strSql.append(" ,CDSRV36");
		strSql.append(" ,CDSRV37");
		strSql.append(" ,CDSRV38");
		strSql.append(" ,CDSRV39");
		strSql.append(" ,CDSRV40");
		strSql.append(" ,CDSRV41");
		strSql.append(" ,CDSRV42");
		strSql.append(" ,CDSRV43");
		strSql.append(" ,CDSRV44");
		strSql.append(" ,CDSRV45");
		strSql.append(" ,CDSRV46");
		strSql.append(" ,CDSRV47");
		strSql.append(" ,CDSRV48");
		strSql.append(" ,CDSRV49");
		strSql.append(" ,CDSRV50");
		strSql.append(" ,STRRSRV1");
		strSql.append(" ,STRRSRV2");
		strSql.append(" ,STRRSRV3");
		strSql.append(" ,STRRSRV4");
		strSql.append(" ,STRRSRV5");
		strSql.append(" ,STRRSRV6");
		strSql.append(" ,STRRSRV7");
		strSql.append(" ,STRRSRV8");
		strSql.append(" ,STRRSRV9");
		strSql.append(" ,STRRSRV10");
		strSql.append(" ,STRRSRV11");
		strSql.append(" ,STRRSRV12");
		strSql.append(" ,STRRSRV13");
		strSql.append(" ,STRRSRV14");
		strSql.append(" ,STRRSRV15");
		strSql.append(" ,STRRSRV16");
		strSql.append(" ,STRRSRV17");
		strSql.append(" ,STRRSRV18");
		strSql.append(" ,STRRSRV19");
		strSql.append(" ,STRRSRV20");
		strSql.append(" ,STRRSRV21");
		strSql.append(" ,STRRSRV22");
		strSql.append(" ,STRRSRV23");
		strSql.append(" ,STRRSRV24");
		strSql.append(" ,STRRSRV25");
		strSql.append(" ,STRRSRV26");
		strSql.append(" ,STRRSRV27");
		strSql.append(" ,STRRSRV28");
		strSql.append(" ,STRRSRV29");
		strSql.append(" ,STRRSRV30");
		strSql.append(" ,STRRSRV31");
		strSql.append(" ,STRRSRV32");
		strSql.append(" ,STRRSRV33");
		strSql.append(" ,STRRSRV34");
		strSql.append(" ,STRRSRV35");
		strSql.append(" ,STRRSRV36");
		strSql.append(" ,STRRSRV37");
		strSql.append(" ,STRRSRV38");
		strSql.append(" ,STRRSRV39");
		strSql.append(" ,STRRSRV40");
		strSql.append(" ,STRRSRV41");
		strSql.append(" ,STRRSRV42");
		strSql.append(" ,STRRSRV43");
		strSql.append(" ,STRRSRV44");
		strSql.append(" ,STRRSRV45");
		strSql.append(" ,STRRSRV46");
		strSql.append(" ,STRRSRV47");
		strSql.append(" ,STRRSRV48");
		strSql.append(" ,STRRSRV49");
		strSql.append(" ,STRRSRV50");
		strSql.append(" ,TXTSRV1");
		strSql.append(" ,TXTSRV2");
		strSql.append(" ,TXTSRV3");
		strSql.append(" ,TXTSRV4");
		strSql.append(" ,TXTSRV5");
		strSql.append(" ,TXTSRV6");
		strSql.append(" ,TXTSRV7");
		strSql.append(" ,TXTSRV8");
		strSql.append(" ,TXTSRV9");
		strSql.append(" ,TXTSRV10");
		strSql.append(" ,TXTSRV11");
		strSql.append(" ,TXTSRV12");
		strSql.append(" ,TXTSRV13");
		strSql.append(" ,TXTSRV14");
		strSql.append(" ,TXTSRV15");
		strSql.append(" ,TXTSRV16");
		strSql.append(" ,TXTSRV17");
		strSql.append(" ,TXTSRV18");
		strSql.append(" ,TXTSRV19");
		strSql.append(" ,TXTSRV20");
		strSql.append(" ,TXTSRV21");
		strSql.append(" ,TXTSRV22");
		strSql.append(" ,TXTSRV23");
		strSql.append(" ,TXTSRV24");
		strSql.append(" ,TXTSRV25");
		strSql.append(" ,TXTSRV26");
		strSql.append(" ,TXTSRV27");
		strSql.append(" ,TXTSRV28");
		strSql.append(" ,TXTSRV29");
		strSql.append(" ,TXTSRV30");
		strSql.append(" ,TXTSRV31");
		strSql.append(" ,TXTSRV32");
		strSql.append(" ,TXTSRV33");
		strSql.append(" ,TXTSRV34");
		strSql.append(" ,TXTSRV35");
		strSql.append(" ,TXTSRV36");
		strSql.append(" ,TXTSRV37");
		strSql.append(" ,TXTSRV38");
		strSql.append(" ,TXTSRV39");
		strSql.append(" ,TXTSRV40");
		strSql.append(" ,TXTSRV41");
		strSql.append(" ,TXTSRV42");
		strSql.append(" ,TXTSRV43");
		strSql.append(" ,TXTSRV44");
		strSql.append(" ,TXTSRV45");
		strSql.append(" ,TXTSRV46");
		strSql.append(" ,TXTSRV47");
		strSql.append(" ,TXTSRV48");
		strSql.append(" ,TXTSRV49");
		strSql.append(" ,TXTSRV50");
		strSql.append(" ,NUMRSRV1");
		strSql.append(" ,NUMRSRV2");
		strSql.append(" ,NUMRSRV3");
		strSql.append(" ,NUMRSRV4");
		strSql.append(" ,NUMRSRV5");
		strSql.append(" ,NUMRSRV6");
		strSql.append(" ,NUMRSRV7");
		strSql.append(" ,NUMRSRV8");
		strSql.append(" ,NUMRSRV9");
		strSql.append(" ,NUMRSRV10");
		strSql.append(" ,NUMRSRV11");
		strSql.append(" ,NUMRSRV12");
		strSql.append(" ,NUMRSRV13");
		strSql.append(" ,NUMRSRV14");
		strSql.append(" ,NUMRSRV15");
		strSql.append(" ,NUMRSRV16");
		strSql.append(" ,NUMRSRV17");
		strSql.append(" ,NUMRSRV18");
		strSql.append(" ,NUMRSRV19");
		strSql.append(" ,NUMRSRV20");
		strSql.append(" ,NUMRSRV21");
		strSql.append(" ,NUMRSRV22");
		strSql.append(" ,NUMRSRV23");
		strSql.append(" ,NUMRSRV24");
		strSql.append(" ,NUMRSRV25");
		strSql.append(" ,NUMRSRV26");
		strSql.append(" ,NUMRSRV27");
		strSql.append(" ,NUMRSRV28");
		strSql.append(" ,NUMRSRV29");
		strSql.append(" ,NUMRSRV30");
		strSql.append(" ,NUMRSRV31");
		strSql.append(" ,NUMRSRV32");
		strSql.append(" ,NUMRSRV33");
		strSql.append(" ,NUMRSRV34");
		strSql.append(" ,NUMRSRV35");
		strSql.append(" ,NUMRSRV36");
		strSql.append(" ,NUMRSRV37");
		strSql.append(" ,NUMRSRV38");
		strSql.append(" ,NUMRSRV39");
		strSql.append(" ,NUMRSRV40");
		strSql.append(" ,NUMRSRV41");
		strSql.append(" ,NUMRSRV42");
		strSql.append(" ,NUMRSRV43");
		strSql.append(" ,NUMRSRV44");
		strSql.append(" ,NUMRSRV45");
		strSql.append(" ,NUMRSRV46");
		strSql.append(" ,NUMRSRV47");
		strSql.append(" ,NUMRSRV48");
		strSql.append(" ,NUMRSRV49");
		strSql.append(" ,NUMRSRV50");
		strSql.append(" ,DATERSRV1");
		strSql.append(" ,DATERSRV2");
		strSql.append(" ,DATERSRV3");
		strSql.append(" ,DATERSRV4");
		strSql.append(" ,DATERSRV5");
		strSql.append(" ,DATERSRV6");
		strSql.append(" ,DATERSRV7");
		strSql.append(" ,DATERSRV8");
		strSql.append(" ,DATERSRV9");
		strSql.append(" ,DATERSRV10");
		strSql.append(" ,DATERSRV11");
		strSql.append(" ,DATERSRV12");
		strSql.append(" ,DATERSRV13");
		strSql.append(" ,DATERSRV14");
		strSql.append(" ,DATERSRV15");
		strSql.append(" ,DATERSRV16");
		strSql.append(" ,DATERSRV17");
		strSql.append(" ,DATERSRV18");
		strSql.append(" ,DATERSRV19");
		strSql.append(" ,DATERSRV20");
		strSql.append(" ,DATERSRV21");
		strSql.append(" ,DATERSRV22");
		strSql.append(" ,DATERSRV23");
		strSql.append(" ,DATERSRV24");
		strSql.append(" ,DATERSRV25");
		strSql.append(" ,DATERSRV26");
		strSql.append(" ,DATERSRV27");
		strSql.append(" ,DATERSRV28");
		strSql.append(" ,DATERSRV29");
		strSql.append(" ,DATERSRV30");
		strSql.append(" ,DATERSRV31");
		strSql.append(" ,DATERSRV32");
		strSql.append(" ,DATERSRV33");
		strSql.append(" ,DATERSRV34");
		strSql.append(" ,DATERSRV35");
		strSql.append(" ,DATERSRV36");
		strSql.append(" ,DATERSRV37");
		strSql.append(" ,DATERSRV38");
		strSql.append(" ,DATERSRV39");
		strSql.append(" ,DATERSRV40");
		strSql.append(" ,DATERSRV41");
		strSql.append(" ,DATERSRV42");
		strSql.append(" ,DATERSRV43");
		strSql.append(" ,DATERSRV44");
		strSql.append(" ,DATERSRV45");
		strSql.append(" ,DATERSRV46");
		strSql.append(" ,DATERSRV47");
		strSql.append(" ,DATERSRV48");
		strSql.append(" ,DATERSRV49");
		strSql.append(" ,DATERSRV50");
		strSql.append(" ,ENTUSRCD");
		strSql.append(" ,ENTDATETIME");
		strSql.append(" ,ENTPRG");
		strSql.append(" ,UPDUSRCD");
		strSql.append(" ,UPDDATETIME");
		strSql.append(" ,UPDPRG");

		return strSql.toString();
	}

	protected String setSelectFields(String seqno) {
		StringBuilder strSql = new StringBuilder();
		strSql.append(" '" + seqno + "'"); //SEQNO
		strSql.append("	,NULL"); //
		strSql.append("	,NULL");
		strSql.append("	,NULL");
		strSql.append("	,NULL");
		strSql.append("	,TMT150_BUYER.CSTMCD");
		strSql.append(" ,TMT150_BUYER.BUYERCD");
		strSql.append(" ,TMT150_BUYER.BUYERNM");
		strSql.append(" ,TMT150_BUYER.BUYERRNM");
		strSql.append(" ,TMT150_BUYER.BUYERZIP");
		strSql.append(" ,TMT150_BUYER.PRFCTRSCD");
		strSql.append(" ,TMT150_BUYER.JISCD");
		strSql.append(" ,TMT150_BUYER.BUYERADRS1");
		strSql.append(" ,TMT150_BUYER.BUYERADRS2");
		strSql.append(" ,TMT150_BUYER.BUYERADRS3");
		strSql.append(" ,TMT150_BUYER.BUYERTEL");
		strSql.append(" ,TMT150_BUYER.BUYERFAX");
		strSql.append(" ,TMT150_BUYER.BUYERUSRNM");
		strSql.append(" ,TMT150_BUYER.BUYERMAILADRS");
		strSql.append(" ,TMT150_BUYER.BUYERREMARK");
		strSql.append(" ,TMT150_BUYER.BUYERSORTNO");
		strSql.append(" ,TMT150_BUYER.CPTRNO");
		strSql.append(" ,TMT150_BUYER.CNTRYCD");
		strSql.append(" ,TMT150_BUYER.DPRTRPRTCD");
		strSql.append(" ,TMT150_BUYER.ARACD");
		strSql.append(" ,TMT150_BUYER.URL");
		strSql.append(" ,TMT150_BUYER.DELFLG");
		strSql.append(" ,TMT150_BUYER.CDSRV1");
		strSql.append(" ,TMT150_BUYER.CDSRV2");
		strSql.append(" ,TMT150_BUYER.CDSRV3");
		strSql.append(" ,TMT150_BUYER.CDSRV4");
		strSql.append(" ,TMT150_BUYER.CDSRV5");
		strSql.append(" ,TMT150_BUYER.CDSRV6");
		strSql.append(" ,TMT150_BUYER.CDSRV7");
		strSql.append(" ,TMT150_BUYER.CDSRV8");
		strSql.append(" ,TMT150_BUYER.CDSRV9");
		strSql.append(" ,TMT150_BUYER.CDSRV10");
		strSql.append(" ,TMT150_BUYER.CDSRV11");
		strSql.append(" ,TMT150_BUYER.CDSRV12");
		strSql.append(" ,TMT150_BUYER.CDSRV13");
		strSql.append(" ,TMT150_BUYER.CDSRV14");
		strSql.append(" ,TMT150_BUYER.CDSRV15");
		strSql.append(" ,TMT150_BUYER.CDSRV16");
		strSql.append(" ,TMT150_BUYER.CDSRV17");
		strSql.append(" ,TMT150_BUYER.CDSRV18");
		strSql.append(" ,TMT150_BUYER.CDSRV19");
		strSql.append(" ,TMT150_BUYER.CDSRV20");
		strSql.append(" ,TMT150_BUYER.CDSRV21");
		strSql.append(" ,TMT150_BUYER.CDSRV22");
		strSql.append(" ,TMT150_BUYER.CDSRV23");
		strSql.append(" ,TMT150_BUYER.CDSRV24");
		strSql.append(" ,TMT150_BUYER.CDSRV25");
		strSql.append(" ,TMT150_BUYER.CDSRV26");
		strSql.append(" ,TMT150_BUYER.CDSRV27");
		strSql.append(" ,TMT150_BUYER.CDSRV28");
		strSql.append(" ,TMT150_BUYER.CDSRV29");
		strSql.append(" ,TMT150_BUYER.CDSRV30");
		strSql.append(" ,TMT150_BUYER.CDSRV31");
		strSql.append(" ,TMT150_BUYER.CDSRV32");
		strSql.append(" ,TMT150_BUYER.CDSRV33");
		strSql.append(" ,TMT150_BUYER.CDSRV34");
		strSql.append(" ,TMT150_BUYER.CDSRV35");
		strSql.append(" ,TMT150_BUYER.CDSRV36");
		strSql.append(" ,TMT150_BUYER.CDSRV37");
		strSql.append(" ,TMT150_BUYER.CDSRV38");
		strSql.append(" ,TMT150_BUYER.CDSRV39");
		strSql.append(" ,TMT150_BUYER.CDSRV40");
		strSql.append(" ,TMT150_BUYER.CDSRV41");
		strSql.append(" ,TMT150_BUYER.CDSRV42");
		strSql.append(" ,TMT150_BUYER.CDSRV43");
		strSql.append(" ,TMT150_BUYER.CDSRV44");
		strSql.append(" ,TMT150_BUYER.CDSRV45");
		strSql.append(" ,TMT150_BUYER.CDSRV46");
		strSql.append(" ,TMT150_BUYER.CDSRV47");
		strSql.append(" ,TMT150_BUYER.CDSRV48");
		strSql.append(" ,TMT150_BUYER.CDSRV49");
		strSql.append(" ,TMT150_BUYER.CDSRV50");
		strSql.append(" ,TMT150_BUYER.STRRSRV1");
		strSql.append(" ,TMT150_BUYER.STRRSRV2");
		strSql.append(" ,TMT150_BUYER.STRRSRV3");
		strSql.append(" ,TMT150_BUYER.STRRSRV4");
		strSql.append(" ,TMT150_BUYER.STRRSRV5");
		strSql.append(" ,TMT150_BUYER.STRRSRV6");
		strSql.append(" ,TMT150_BUYER.STRRSRV7");
		strSql.append(" ,TMT150_BUYER.STRRSRV8");
		strSql.append(" ,TMT150_BUYER.STRRSRV9");
		strSql.append(" ,TMT150_BUYER.STRRSRV10");
		strSql.append(" ,TMT150_BUYER.STRRSRV11");
		strSql.append(" ,TMT150_BUYER.STRRSRV12");
		strSql.append(" ,TMT150_BUYER.STRRSRV13");
		strSql.append(" ,TMT150_BUYER.STRRSRV14");
		strSql.append(" ,TMT150_BUYER.STRRSRV15");
		strSql.append(" ,TMT150_BUYER.STRRSRV16");
		strSql.append(" ,TMT150_BUYER.STRRSRV17");
		strSql.append(" ,TMT150_BUYER.STRRSRV18");
		strSql.append(" ,TMT150_BUYER.STRRSRV19");
		strSql.append(" ,TMT150_BUYER.STRRSRV20");
		strSql.append(" ,TMT150_BUYER.STRRSRV21");
		strSql.append(" ,TMT150_BUYER.STRRSRV22");
		strSql.append(" ,TMT150_BUYER.STRRSRV23");
		strSql.append(" ,TMT150_BUYER.STRRSRV24");
		strSql.append(" ,TMT150_BUYER.STRRSRV25");
		strSql.append(" ,TMT150_BUYER.STRRSRV26");
		strSql.append(" ,TMT150_BUYER.STRRSRV27");
		strSql.append(" ,TMT150_BUYER.STRRSRV28");
		strSql.append(" ,TMT150_BUYER.STRRSRV29");
		strSql.append(" ,TMT150_BUYER.STRRSRV30");
		strSql.append(" ,TMT150_BUYER.STRRSRV31");
		strSql.append(" ,TMT150_BUYER.STRRSRV32");
		strSql.append(" ,TMT150_BUYER.STRRSRV33");
		strSql.append(" ,TMT150_BUYER.STRRSRV34");
		strSql.append(" ,TMT150_BUYER.STRRSRV35");
		strSql.append(" ,TMT150_BUYER.STRRSRV36");
		strSql.append(" ,TMT150_BUYER.STRRSRV37");
		strSql.append(" ,TMT150_BUYER.STRRSRV38");
		strSql.append(" ,TMT150_BUYER.STRRSRV39");
		strSql.append(" ,TMT150_BUYER.STRRSRV40");
		strSql.append(" ,TMT150_BUYER.STRRSRV41");
		strSql.append(" ,TMT150_BUYER.STRRSRV42");
		strSql.append(" ,TMT150_BUYER.STRRSRV43");
		strSql.append(" ,TMT150_BUYER.STRRSRV44");
		strSql.append(" ,TMT150_BUYER.STRRSRV45");
		strSql.append(" ,TMT150_BUYER.STRRSRV46");
		strSql.append(" ,TMT150_BUYER.STRRSRV47");
		strSql.append(" ,TMT150_BUYER.STRRSRV48");
		strSql.append(" ,TMT150_BUYER.STRRSRV49");
		strSql.append(" ,TMT150_BUYER.STRRSRV50");
		strSql.append(" ,TMT150_BUYER.TXTSRV1");
		strSql.append(" ,TMT150_BUYER.TXTSRV2");
		strSql.append(" ,TMT150_BUYER.TXTSRV3");
		strSql.append(" ,TMT150_BUYER.TXTSRV4");
		strSql.append(" ,TMT150_BUYER.TXTSRV5");
		strSql.append(" ,TMT150_BUYER.TXTSRV6");
		strSql.append(" ,TMT150_BUYER.TXTSRV7");
		strSql.append(" ,TMT150_BUYER.TXTSRV8");
		strSql.append(" ,TMT150_BUYER.TXTSRV9");
		strSql.append(" ,TMT150_BUYER.TXTSRV10");
		strSql.append(" ,TMT150_BUYER.TXTSRV11");
		strSql.append(" ,TMT150_BUYER.TXTSRV12");
		strSql.append(" ,TMT150_BUYER.TXTSRV13");
		strSql.append(" ,TMT150_BUYER.TXTSRV14");
		strSql.append(" ,TMT150_BUYER.TXTSRV15");
		strSql.append(" ,TMT150_BUYER.TXTSRV16");
		strSql.append(" ,TMT150_BUYER.TXTSRV17");
		strSql.append(" ,TMT150_BUYER.TXTSRV18");
		strSql.append(" ,TMT150_BUYER.TXTSRV19");
		strSql.append(" ,TMT150_BUYER.TXTSRV20");
		strSql.append(" ,TMT150_BUYER.TXTSRV21");
		strSql.append(" ,TMT150_BUYER.TXTSRV22");
		strSql.append(" ,TMT150_BUYER.TXTSRV23");
		strSql.append(" ,TMT150_BUYER.TXTSRV24");
		strSql.append(" ,TMT150_BUYER.TXTSRV25");
		strSql.append(" ,TMT150_BUYER.TXTSRV26");
		strSql.append(" ,TMT150_BUYER.TXTSRV27");
		strSql.append(" ,TMT150_BUYER.TXTSRV28");
		strSql.append(" ,TMT150_BUYER.TXTSRV29");
		strSql.append(" ,TMT150_BUYER.TXTSRV30");
		strSql.append(" ,TMT150_BUYER.TXTSRV31");
		strSql.append(" ,TMT150_BUYER.TXTSRV32");
		strSql.append(" ,TMT150_BUYER.TXTSRV33");
		strSql.append(" ,TMT150_BUYER.TXTSRV34");
		strSql.append(" ,TMT150_BUYER.TXTSRV35");
		strSql.append(" ,TMT150_BUYER.TXTSRV36");
		strSql.append(" ,TMT150_BUYER.TXTSRV37");
		strSql.append(" ,TMT150_BUYER.TXTSRV38");
		strSql.append(" ,TMT150_BUYER.TXTSRV39");
		strSql.append(" ,TMT150_BUYER.TXTSRV40");
		strSql.append(" ,TMT150_BUYER.TXTSRV41");
		strSql.append(" ,TMT150_BUYER.TXTSRV42");
		strSql.append(" ,TMT150_BUYER.TXTSRV43");
		strSql.append(" ,TMT150_BUYER.TXTSRV44");
		strSql.append(" ,TMT150_BUYER.TXTSRV45");
		strSql.append(" ,TMT150_BUYER.TXTSRV46");
		strSql.append(" ,TMT150_BUYER.TXTSRV47");
		strSql.append(" ,TMT150_BUYER.TXTSRV48");
		strSql.append(" ,TMT150_BUYER.TXTSRV49");
		strSql.append(" ,TMT150_BUYER.TXTSRV50");
		strSql.append(" ,TMT150_BUYER.NUMRSRV1");
		strSql.append(" ,TMT150_BUYER.NUMRSRV2");
		strSql.append(" ,TMT150_BUYER.NUMRSRV3");
		strSql.append(" ,TMT150_BUYER.NUMRSRV4");
		strSql.append(" ,TMT150_BUYER.NUMRSRV5");
		strSql.append(" ,TMT150_BUYER.NUMRSRV6");
		strSql.append(" ,TMT150_BUYER.NUMRSRV7");
		strSql.append(" ,TMT150_BUYER.NUMRSRV8");
		strSql.append(" ,TMT150_BUYER.NUMRSRV9");
		strSql.append(" ,TMT150_BUYER.NUMRSRV10");
		strSql.append(" ,TMT150_BUYER.NUMRSRV11");
		strSql.append(" ,TMT150_BUYER.NUMRSRV12");
		strSql.append(" ,TMT150_BUYER.NUMRSRV13");
		strSql.append(" ,TMT150_BUYER.NUMRSRV14");
		strSql.append(" ,TMT150_BUYER.NUMRSRV15");
		strSql.append(" ,TMT150_BUYER.NUMRSRV16");
		strSql.append(" ,TMT150_BUYER.NUMRSRV17");
		strSql.append(" ,TMT150_BUYER.NUMRSRV18");
		strSql.append(" ,TMT150_BUYER.NUMRSRV19");
		strSql.append(" ,TMT150_BUYER.NUMRSRV20");
		strSql.append(" ,TMT150_BUYER.NUMRSRV21");
		strSql.append(" ,TMT150_BUYER.NUMRSRV22");
		strSql.append(" ,TMT150_BUYER.NUMRSRV23");
		strSql.append(" ,TMT150_BUYER.NUMRSRV24");
		strSql.append(" ,TMT150_BUYER.NUMRSRV25");
		strSql.append(" ,TMT150_BUYER.NUMRSRV26");
		strSql.append(" ,TMT150_BUYER.NUMRSRV27");
		strSql.append(" ,TMT150_BUYER.NUMRSRV28");
		strSql.append(" ,TMT150_BUYER.NUMRSRV29");
		strSql.append(" ,TMT150_BUYER.NUMRSRV30");
		strSql.append(" ,TMT150_BUYER.NUMRSRV31");
		strSql.append(" ,TMT150_BUYER.NUMRSRV32");
		strSql.append(" ,TMT150_BUYER.NUMRSRV33");
		strSql.append(" ,TMT150_BUYER.NUMRSRV34");
		strSql.append(" ,TMT150_BUYER.NUMRSRV35");
		strSql.append(" ,TMT150_BUYER.NUMRSRV36");
		strSql.append(" ,TMT150_BUYER.NUMRSRV37");
		strSql.append(" ,TMT150_BUYER.NUMRSRV38");
		strSql.append(" ,TMT150_BUYER.NUMRSRV39");
		strSql.append(" ,TMT150_BUYER.NUMRSRV40");
		strSql.append(" ,TMT150_BUYER.NUMRSRV41");
		strSql.append(" ,TMT150_BUYER.NUMRSRV42");
		strSql.append(" ,TMT150_BUYER.NUMRSRV43");
		strSql.append(" ,TMT150_BUYER.NUMRSRV44");
		strSql.append(" ,TMT150_BUYER.NUMRSRV45");
		strSql.append(" ,TMT150_BUYER.NUMRSRV46");
		strSql.append(" ,TMT150_BUYER.NUMRSRV47");
		strSql.append(" ,TMT150_BUYER.NUMRSRV48");
		strSql.append(" ,TMT150_BUYER.NUMRSRV49");
		strSql.append(" ,TMT150_BUYER.NUMRSRV50");
		strSql.append(" ,TMT150_BUYER.DATERSRV1");
		strSql.append(" ,TMT150_BUYER.DATERSRV2");
		strSql.append(" ,TMT150_BUYER.DATERSRV3");
		strSql.append(" ,TMT150_BUYER.DATERSRV4");
		strSql.append(" ,TMT150_BUYER.DATERSRV5");
		strSql.append(" ,TMT150_BUYER.DATERSRV6");
		strSql.append(" ,TMT150_BUYER.DATERSRV7");
		strSql.append(" ,TMT150_BUYER.DATERSRV8");
		strSql.append(" ,TMT150_BUYER.DATERSRV9");
		strSql.append(" ,TMT150_BUYER.DATERSRV10");
		strSql.append(" ,TMT150_BUYER.DATERSRV11");
		strSql.append(" ,TMT150_BUYER.DATERSRV12");
		strSql.append(" ,TMT150_BUYER.DATERSRV13");
		strSql.append(" ,TMT150_BUYER.DATERSRV14");
		strSql.append(" ,TMT150_BUYER.DATERSRV15");
		strSql.append(" ,TMT150_BUYER.DATERSRV16");
		strSql.append(" ,TMT150_BUYER.DATERSRV17");
		strSql.append(" ,TMT150_BUYER.DATERSRV18");
		strSql.append(" ,TMT150_BUYER.DATERSRV19");
		strSql.append(" ,TMT150_BUYER.DATERSRV20");
		strSql.append(" ,TMT150_BUYER.DATERSRV21");
		strSql.append(" ,TMT150_BUYER.DATERSRV22");
		strSql.append(" ,TMT150_BUYER.DATERSRV23");
		strSql.append(" ,TMT150_BUYER.DATERSRV24");
		strSql.append(" ,TMT150_BUYER.DATERSRV25");
		strSql.append(" ,TMT150_BUYER.DATERSRV26");
		strSql.append(" ,TMT150_BUYER.DATERSRV27");
		strSql.append(" ,TMT150_BUYER.DATERSRV28");
		strSql.append(" ,TMT150_BUYER.DATERSRV29");
		strSql.append(" ,TMT150_BUYER.DATERSRV30");
		strSql.append(" ,TMT150_BUYER.DATERSRV31");
		strSql.append(" ,TMT150_BUYER.DATERSRV32");
		strSql.append(" ,TMT150_BUYER.DATERSRV33");
		strSql.append(" ,TMT150_BUYER.DATERSRV34");
		strSql.append(" ,TMT150_BUYER.DATERSRV35");
		strSql.append(" ,TMT150_BUYER.DATERSRV36");
		strSql.append(" ,TMT150_BUYER.DATERSRV37");
		strSql.append(" ,TMT150_BUYER.DATERSRV38");
		strSql.append(" ,TMT150_BUYER.DATERSRV39");
		strSql.append(" ,TMT150_BUYER.DATERSRV40");
		strSql.append(" ,TMT150_BUYER.DATERSRV41");
		strSql.append(" ,TMT150_BUYER.DATERSRV42");
		strSql.append(" ,TMT150_BUYER.DATERSRV43");
		strSql.append(" ,TMT150_BUYER.DATERSRV44");
		strSql.append(" ,TMT150_BUYER.DATERSRV45");
		strSql.append(" ,TMT150_BUYER.DATERSRV46");
		strSql.append(" ,TMT150_BUYER.DATERSRV47");
		strSql.append(" ,TMT150_BUYER.DATERSRV48");
		strSql.append(" ,TMT150_BUYER.DATERSRV49");
		strSql.append(" ,TMT150_BUYER.DATERSRV50");
		strSql.append("	,?");
		strSql.append("	,SYSDATETIME()");
		strSql.append("	,'" + FuncID.FPMT0150 + "'");
		strSql.append("	,?");
		strSql.append("	,SYSDATETIME()");
		strSql.append("	,'" + FuncID.FPMT0150 + "'");

		return strSql.toString();
	}
}
