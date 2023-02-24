package jp.co.brycen.wms.spot01601search.dto;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.spot01601.dto.Spot01601SearchConditionDto;

public class Spot01601SearchRequest  extends AbstractRequest {

	/**
	 * 検索条件
	 */
	public Spot01601SearchConditionDto spot01601SearchCondition = new Spot01601SearchConditionDto();

}
