package jp.co.brycen.wms.testinit.webservice; 
 
import javax.ws.rs.Consumes; 
import javax.ws.rs.POST; 
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 
 
import jp.co.brycen.common.dto.response.AbstractResponse; 
import jp.co.brycen.common.process.AbstractProcess; 
import jp.co.brycen.common.webservice.AbstractWebService; 
import jp.co.brycen.wms.testinit.dto.TestInitRequest; 
import jp.co.brycen.wms.testinit.dto.TestInitResponse; 
import jp.co.brycen.wms.testinit.process.TestInitProcess; 
 
@Path("wms") 
public class TestInitWebService extends AbstractWebService { 
 
    @POST 
    @Path("/TestInit") 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") 
    public TestInitResponse search(TestInitRequest item) { 
        AbstractResponse abs = super.executeProcess(item); 
        return (TestInitResponse)abs; 
    } 
 
    @Override 
    protected AbstractProcess getProcess() { 
        return new TestInitProcess(this); 
    } 
}  
