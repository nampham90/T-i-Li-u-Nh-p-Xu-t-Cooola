package jp.co.brycen.wms.spot01601search.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.wms.spot01601.dto.Spot01601SearchRowDto;

@XmlRootElement(name = "result")
public class Spot01601SearchResponse extends AbstractResponse {
	//List result of request
	public ArrayList<Spot01601SearchRowDto> rows;
	//Number of row
	public Long DATACNT;

}
