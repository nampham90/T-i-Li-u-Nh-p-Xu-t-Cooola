package jp.co.brycen.wms.spcm51801init.webservice; 
 
import javax.ws.rs.Consumes; 
import javax.ws.rs.POST; 
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 
 
import jp.co.brycen.common.dto.response.AbstractResponse; 
import jp.co.brycen.common.process.AbstractProcess; 
import jp.co.brycen.common.webservice.AbstractWebService; 
import jp.co.brycen.wms.spcm51801init.dto.Spcm51801InitRequest; 
import jp.co.brycen.wms.spcm51801init.dto.Spcm51801InitResponse; 
import jp.co.brycen.wms.spcm51801init.process.Spcm51801InitProcess; 
 
@Path("wms") 
public class Spcm51801InitWebService extends AbstractWebService { 
 
    @POST 
    @Path("/Spcm51801Init") 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") 
    public Spcm51801InitResponse search(Spcm51801InitRequest item) { 
        AbstractResponse abs = super.executeProcess(item); 
        return (Spcm51801InitResponse)abs; 
    } 
 
    @Override 
    protected AbstractProcess getProcess() { 
        return new Spcm51801InitProcess(this); 
    } 
}  
