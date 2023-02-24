package jp.co.brycen.wms.spot01601search.webservice;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spot01601search.dto.Spot01601SearchRequest;
import jp.co.brycen.wms.spot01601search.dto.Spot01601SearchResponse;
import jp.co.brycen.wms.spot01601search.process.Spot01601SearchRecCntProcess;

@Path("wms")
public class Spot01601SearchRecCntWebService extends AbstractWebService{
	@POST
	@Path("/Spot01601SearchCnt")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Spot01601SearchResponse search(Spot01601SearchRequest item) throws SQLException {
		AbstractResponse abs = super.executeProcess(item);
		return (Spot01601SearchResponse) abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new Spot01601SearchRecCntProcess(this);
	}
}
