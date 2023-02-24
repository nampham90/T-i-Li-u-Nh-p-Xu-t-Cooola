package jp.co.brycen.wms.testsearch.dto; 
 
import java.util.ArrayList; 
 
import javax.xml.bind.annotation.XmlRootElement; 
 
import jp.co.brycen.common.dto.response.AbstractResponse; 
import jp.co.brycen.wms.test.dto.TestSearchCountDto; 
import jp.co.brycen.wms.test.dto.TestSearchRowDto; 
 
/** 
 * The Class SearchResultResponse. 
 */ 
@XmlRootElement(name = "result") 
public class TestSearchResponse extends AbstractResponse { 
 
    // 一覧データ 
    public ArrayList<TestSearchRowDto> rows; 
 
    // Search result (Records count) 
    public TestSearchCountDto testSearchCount = new TestSearchCountDto(); 
}  
