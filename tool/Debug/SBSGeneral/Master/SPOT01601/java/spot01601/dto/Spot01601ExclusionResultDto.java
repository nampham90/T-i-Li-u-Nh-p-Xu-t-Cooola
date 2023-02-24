package jp.co.brycen.wms.#LowerScreenId#.dto;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.AbstractDto;
import jp.co.brycen.common.dto.response.AbstractResponse;

@XmlRootElement(name = "#FirstScreenId#ExclusionResult")
public class #FirstScreenId#ExclusionResultDto extends AbstractResponse {
	
	/**
	 *  結果
	 */
	public Boolean ret;
	
}
