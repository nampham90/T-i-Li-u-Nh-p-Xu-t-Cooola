package jp.co.brycen.wms.testsearch.dto; 
 
import javax.xml.bind.annotation.XmlRootElement; 
 
import jp.co.brycen.common.dto.request.AbstractRequest; 
import jp.co.brycen.wms.test.dto.TestSearchConditionDto; 
 
/** 
 * The Class TestSearchItemRequest. 
 */ 
@XmlRootElement(name = "TestSearchRequest") 
public class TestSearchRequest extends AbstractRequest { 
 
    // 検索条件 
    public TestSearchConditionDto testSearchCondition = new TestSearchConditionDto(); 
}  
