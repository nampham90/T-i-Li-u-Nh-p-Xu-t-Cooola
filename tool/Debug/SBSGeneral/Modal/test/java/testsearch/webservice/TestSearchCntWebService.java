package jp.co.brycen.wms.testsearch.webservice; 
import java.sql.SQLException; 
 
import javax.ws.rs.Consumes; 
import javax.ws.rs.POST; 
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 
 
import jp.co.brycen.common.dto.response.AbstractResponse; 
import jp.co.brycen.common.process.AbstractProcess; 
import jp.co.brycen.common.webservice.AbstractWebService; 
import jp.co.brycen.wms.testsearch.dto.TestSearchRequest; 
import jp.co.brycen.wms.testsearch.dto.TestSearchResponse; 
import jp.co.brycen.wms.testsearch.process.TestSearchCntProcess; 
 
@Path("wms") 
public class TestSearchCntWebService extends AbstractWebService { 
 
    @POST 
    @Path("/TestSearchCnt") 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") 
    public TestSearchResponse search(TestSearchRequest item) throws SQLException { 
        AbstractResponse abs = super.executeProcess(item); 
        return (TestSearchResponse)abs; 
    } 
 
    @Override 
    protected AbstractProcess getProcess() { 
        return new TestSearchCntProcess(this); 
    } 
}  
