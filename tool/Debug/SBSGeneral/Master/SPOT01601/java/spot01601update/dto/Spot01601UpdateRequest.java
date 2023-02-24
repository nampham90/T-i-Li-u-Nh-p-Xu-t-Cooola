package jp.co.brycen.wms.spot01601update.dto;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.spot01601.dto.Spot01601UpdateConditionDto;

@XmlRootElement(name = "Spot01601UpdateRequest")
public class Spot01601UpdateRequest extends AbstractRequest{
	//Get data from request to update
		public Spot01601UpdateConditionDto spot01601UpdateCondition = new Spot01601UpdateConditionDto();
}
