package jp.co.brycen.wms.#LowerScreenId#search.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.wms.#LowerScreenId#.dto.#FirstScreenId#SearchRowDto;

@XmlRootElement(name = "result")
public class #FirstScreenId#SearchResponse extends AbstractResponse {
	//List result of request
	public ArrayList<#FirstScreenId#SearchRowDto> rows;
	//Number of row
	public Long DATACNT;

}
