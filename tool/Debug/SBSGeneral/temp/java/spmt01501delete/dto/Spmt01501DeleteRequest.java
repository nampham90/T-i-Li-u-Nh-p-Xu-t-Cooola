package jp.co.brycen.wms.#LowerScreenId#delete.dto;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.#LowerScreenId#.dto.#FirstScreenId#DeleteConditionDto;

@XmlRootElement(name = "#FirstScreenId#DeleteRequest")
public class #FirstScreenId#DeleteRequest extends AbstractRequest{
	//Get data from request to delete
	public #FirstScreenId#DeleteConditionDto #LowerScreenId#DeleteCondition = new #FirstScreenId#DeleteConditionDto();
}
