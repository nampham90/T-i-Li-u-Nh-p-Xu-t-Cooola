package jp.co.brycen.wms.spot01601.dto; 
 
import javax.xml.bind.annotation.XmlRootElement; 
 
import jp.co.brycen.common.dto.AbstractDto; 
 
@XmlRootElement(name = "Spot01601SearchRow") 
public class Spot01601SearchRowDto extends AbstractDto { 
 

/**
* SOPLNDATE
*/
public String SOPLNDATE;

/**
* SHIPMNTPLNDAT
*/
public String SHIPMNTPLNDAT;

/**
* INCDNTPLNNO
*/
public String INCDNTPLNNO;

/**
* SOORDNO
*/
public String SOORDNO;

/**
* PRCSNO
*/
public String PRCSNO;

/**
* CSTMORDNO
*/
public String CSTMORDNO;

/**
* SOGRPNO
*/
public String SOGRPNO;

/**
* PCKUSRCD
*/
public String PCKUSRCD;

/**
* PCKNGDATETIME
*/
public String PCKNGDATETIME;

/**
* BTNO
*/
public String BTNO;

/**
* DATESRALNO
*/
public String DATESRALNO;

/**
* INNO
*/
public String INNO;

/**
* PCKNGLSTNO
*/
public String PCKNGLSTNO;
 
} 
 
