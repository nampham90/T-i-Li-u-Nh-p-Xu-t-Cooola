package jp.co.brycen.wms.#LowerScreenId#getdetail.dto;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.#LowerScreenId#.dto.#FirstScreenId#GetDetailConditionDto;

@XmlRootElement(name = "#FirstScreenId#CheckLinkRequest")
public class #FirstScreenId#GetDetailRequest extends AbstractRequest{
	//Get data from request to get detail
	public #FirstScreenId#GetDetailConditionDto #LowerScreenId#GetDetailCondition = new #FirstScreenId#GetDetailConditionDto();
}
