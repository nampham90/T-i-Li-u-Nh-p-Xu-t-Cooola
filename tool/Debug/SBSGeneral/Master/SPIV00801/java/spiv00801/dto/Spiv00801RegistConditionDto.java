package jp.co.brycen.wms.spiv00801.dto; 
 
import javax.xml.bind.annotation.XmlRootElement; 
 
import jp.co.brycen.common.dto.AbstractDto; 
 
@XmlRootElement(name = "Spiv00801RegistCondition") 
public class Spiv00801RegistConditionDto extends AbstractDto { 
 

/**
* INVNTRNO
*/
public String INVNTRNO;

/**
* ADJSTDATE
*/
public String ADJSTDATE;

/**
* UPDDATETIME
*/
public String UPDDATETIME;

/**
* ROWS
*/
public String ROWS;
 
} 
 
