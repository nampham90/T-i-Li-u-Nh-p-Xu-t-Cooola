package jp.co.brycen.wms.spot01601init.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spot01601init.dto.Spot01601InitRequest;
import jp.co.brycen.wms.spot01601init.dto.Spot01601InitResponse;
import jp.co.brycen.wms.spot01601init.process.Spot01601InitProcess;


@Path("wms")
public class Spot01601InitWebService extends AbstractWebService {
	@POST
	@Path("/Spot01601Init")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Spot01601InitResponse search(Spot01601InitRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (Spot01601InitResponse) abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new Spot01601InitProcess(this);
	}
}
