package jp.co.brycen.wms.spot01601.dto; 
 
import javax.xml.bind.annotation.XmlRootElement; 
 
import jp.co.brycen.common.dto.AbstractDto; 
 
@XmlRootElement(name = "Spot01601SearchCondition") 
public class Spot01601SearchConditionDto extends AbstractDto { 
 

/**
* SOPLNDATEFROM
*/
public String SOPLNDATEFROM;

/**
* SOPLNDATETO
*/
public String SOPLNDATETO;

/**
* BTNO
*/
public String BTNO;

/**
* PRCSNO
*/
public String PRCSNO;

/**
* INNO
*/
public String INNO;

/**
* PCKNGLSTNO
*/
public String PCKNGLSTNO;
 
} 
 
