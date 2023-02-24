package jp.co.brycen.wms.spiv00801search.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.wms.spiv00801.dto.Spiv00801SearchRowDto;

@XmlRootElement(name = "result")
public class Spiv00801SearchResponse extends AbstractResponse {
	//List result of request
	public ArrayList<Spiv00801SearchRowDto> rows;
	//Number of row
	public Long DATACNT;

}
