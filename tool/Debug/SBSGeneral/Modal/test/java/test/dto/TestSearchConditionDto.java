package jp.co.brycen.wms.test.dto; 
 
import javax.xml.bind.annotation.XmlRootElement; 
 
import jp.co.brycen.common.dto.request.AbstractRequest; 
 
@XmlRootElement(name="TestSearchConditionDto") 
public class TestSearchConditionDto extends AbstractRequest{ 
 
    /*  */ 
    public String A;
    /*  */ 
    public String B;
    /*  */ 
    public String C;
    /*  */ 
    public String D;
 
}  
