package jp.co.brycen.wms.spcm51801search.dto; 
 
import java.util.ArrayList; 
 
import javax.xml.bind.annotation.XmlRootElement; 
 
import jp.co.brycen.common.dto.response.AbstractResponse; 
import jp.co.brycen.wms.spcm51801.dto.Spcm51801SearchCountDto; 
import jp.co.brycen.wms.spcm51801.dto.Spcm51801SearchRowDto; 
 
/** 
 * The Class SearchResultResponse. 
 */ 
@XmlRootElement(name = "result") 
public class Spcm51801SearchResponse extends AbstractResponse { 
 
    // 一覧データ 
    public ArrayList<Spcm51801SearchRowDto> rows; 
 
    // Search result (Records count) 
    public Spcm51801SearchCountDto spcm51801SearchCount = new Spcm51801SearchCountDto(); 
}  
