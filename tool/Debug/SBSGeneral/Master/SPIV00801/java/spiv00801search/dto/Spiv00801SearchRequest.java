package jp.co.brycen.wms.spiv00801search.dto;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.spiv00801.dto.Spiv00801SearchConditionDto;

public class Spiv00801SearchRequest  extends AbstractRequest {

	/**
	 * 検索条件
	 */
	public Spiv00801SearchConditionDto spiv00801SearchCondition = new Spiv00801SearchConditionDto();

}
