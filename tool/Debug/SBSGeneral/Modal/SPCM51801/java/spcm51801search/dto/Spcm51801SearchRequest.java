package jp.co.brycen.wms.spcm51801search.dto; 
 
import javax.xml.bind.annotation.XmlRootElement; 
 
import jp.co.brycen.common.dto.request.AbstractRequest; 
import jp.co.brycen.wms.spcm51801.dto.Spcm51801SearchConditionDto; 
 
/** 
 * The Class Spcm51801SearchItemRequest. 
 */ 
@XmlRootElement(name = "Spcm51801SearchRequest") 
public class Spcm51801SearchRequest extends AbstractRequest { 
 
    // 検索条件 
    public Spcm51801SearchConditionDto spcm51801SearchCondition = new Spcm51801SearchConditionDto(); 
}  
