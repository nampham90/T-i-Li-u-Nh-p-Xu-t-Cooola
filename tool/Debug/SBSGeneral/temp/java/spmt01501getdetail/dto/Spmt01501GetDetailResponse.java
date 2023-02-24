package jp.co.brycen.wms.#LowerScreenId#getdetail.dto;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.response.AbstractResponse;

@XmlRootElement(name = "result")
public class #FirstScreenId#GetDetailResponse extends AbstractResponse{
	/**
	 * 依頼主コード
	 */
	public String BUYERCD;
	/**
	 * 依頼主名
	 */
	public String BUYERNM;
	/**
	 * 依頼主略名
	 */
	public String BUYERRNM;
	/**
	 * 依頼主郵便番号
	 */
	public String BUYERZIP;
	/**
	 * 依頼主住所1
	 */
	public String BUYERADRS1;
	/**
	 * 依頼主住所2
	 */
	public String BUYERADRS2;
	/**
	 * 依頼主住所3
	 */
	public String BUYERADRS3;
	/**
	 * 依頼主TEL
	 */
	public String BUYERTEL;
	/**
	 * 依頼主FAX
	 */
	public String BUYERFAX;
	/**
	 * 依頼主担当者
	 */
	public String BUYERUSRNM;
	/**
	 * 依頼主メールアドレス
	 */
	public String BUYERMAILADRS;
	/**
	 * 引当優先順
	 */
	public String BUYERSORTNO;
	/**
	 * 備考
	 */
	public String BUYERREMARK;

	/**
	 * 更新日時(HIDDEN項目)
	 */
	public String UPDDATETIME;
}
