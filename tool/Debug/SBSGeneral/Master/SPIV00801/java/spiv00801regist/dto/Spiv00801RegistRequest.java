package jp.co.brycen.wms.spiv00801regist.dto;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.spiv00801.dto.Spiv00801RegistConditionDto;

@XmlRootElement(name = "Spiv00801RegistRequest")
public class Spiv00801RegistRequest extends AbstractRequest{
	//Get data from request to register
	public Spiv00801RegistConditionDto spiv00801RegistCondition = new Spiv00801RegistConditionDto();
}
