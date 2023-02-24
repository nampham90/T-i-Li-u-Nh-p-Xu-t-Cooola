package jp.co.brycen.wms.#LowerScreenId#checkdetail.dto;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.#LowerScreenId#.dto.#FirstScreenId#CheckDetailConditionDto;

@XmlRootElement(name = "#FirstScreenId#CheckDetailRequest")
public class #FirstScreenId#CheckDetailRequest  extends AbstractRequest {
	// Get data form controller to check link on grid
		public #FirstScreenId#CheckDetailConditionDto #LowerScreenId#CheckDetailCondition = new #FirstScreenId#CheckDetailConditionDto();
}
