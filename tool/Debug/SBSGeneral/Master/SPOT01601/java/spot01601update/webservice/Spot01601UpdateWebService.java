package jp.co.brycen.wms.spot01601update.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spot01601update.dto.Spot01601UpdateRequest;
import jp.co.brycen.wms.spot01601update.dto.Spot01601UpdateResponse;
import jp.co.brycen.wms.spot01601update.process.Spot01601UpdateProcess;

@Path("wms")
public class Spot01601UpdateWebService extends AbstractWebService {
	@POST
	@Path("/Spot01601Update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Spot01601UpdateResponse search(Spot01601UpdateRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (Spot01601UpdateResponse) abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new Spot01601UpdateProcess(this);
	}
}
