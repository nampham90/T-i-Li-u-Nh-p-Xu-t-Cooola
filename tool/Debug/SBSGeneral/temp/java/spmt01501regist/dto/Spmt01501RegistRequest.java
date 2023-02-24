package jp.co.brycen.wms.#LowerScreenId#regist.dto;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.#LowerScreenId#.dto.#FirstScreenId#RegistConditionDto;

@XmlRootElement(name = "#FirstScreenId#RegistRequest")
public class #FirstScreenId#RegistRequest extends AbstractRequest{
	//Get data from request to register
	public #FirstScreenId#RegistConditionDto #LowerScreenId#RegistCondition = new #FirstScreenId#RegistConditionDto();
}
