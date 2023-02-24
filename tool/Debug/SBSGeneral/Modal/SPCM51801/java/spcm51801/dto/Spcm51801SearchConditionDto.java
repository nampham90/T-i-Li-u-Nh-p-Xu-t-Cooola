package jp.co.brycen.wms.spcm51801.dto; 
 
import javax.xml.bind.annotation.XmlRootElement; 
 
import jp.co.brycen.common.dto.request.AbstractRequest; 
 
@XmlRootElement(name="Spcm51801SearchConditionDto") 
public class Spcm51801SearchConditionDto extends AbstractRequest{ 
 
    /*  */ 
    public String CSTMORDNOSEARCH;
    /*  */ 
    public String ARVLPLNDATEFROMSEARCH;
    /*  */ 
    public String ARVLPLNDATETOSEARCH;
    /*  */ 
    public String ARVLRDATEFROMSEARCH;
    /*  */ 
    public String ARVLRDATETOSEARCH;
    /*  */ 
    public String ARVLRSTCKDATEFROMSEARCH;
    /*  */ 
    public String ARVLRSTCKDATETOSEARCH;
    /*  */ 
    public String SLIPLNNOSEARCH;
    /*  */ 
    public String SIPLNRSLTNOSEARCH;
    /*  */ 
    public String SPPLYNMSEARCH;
 
}  
