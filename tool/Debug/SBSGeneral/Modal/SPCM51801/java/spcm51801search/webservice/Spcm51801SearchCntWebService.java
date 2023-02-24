package jp.co.brycen.wms.spcm51801search.webservice; 
import java.sql.SQLException; 
 
import javax.ws.rs.Consumes; 
import javax.ws.rs.POST; 
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 
 
import jp.co.brycen.common.dto.response.AbstractResponse; 
import jp.co.brycen.common.process.AbstractProcess; 
import jp.co.brycen.common.webservice.AbstractWebService; 
import jp.co.brycen.wms.spcm51801search.dto.Spcm51801SearchRequest; 
import jp.co.brycen.wms.spcm51801search.dto.Spcm51801SearchResponse; 
import jp.co.brycen.wms.spcm51801search.process.Spcm51801SearchCntProcess; 
 
@Path("wms") 
public class Spcm51801SearchCntWebService extends AbstractWebService { 
 
    @POST 
    @Path("/Spcm51801SearchCnt") 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") 
    public Spcm51801SearchResponse search(Spcm51801SearchRequest item) throws SQLException { 
        AbstractResponse abs = super.executeProcess(item); 
        return (Spcm51801SearchResponse)abs; 
    } 
 
    @Override 
    protected AbstractProcess getProcess() { 
        return new Spcm51801SearchCntProcess(this); 
    } 
}  
