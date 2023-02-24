package jp.co.brycen.wms.#LowerScreenId#update.dto;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.#LowerScreenId#.dto.#FirstScreenId#UpdateConditionDto;

@XmlRootElement(name = "#FirstScreenId#UpdateRequest")
public class #FirstScreenId#UpdateRequest extends AbstractRequest{
	//Get data from request to update
		public #FirstScreenId#UpdateConditionDto #LowerScreenId#UpdateCondition = new #FirstScreenId#UpdateConditionDto();
}
