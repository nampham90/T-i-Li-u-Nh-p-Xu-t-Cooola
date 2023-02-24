package jp.co.brycen.wms.spiv00801init.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spiv00801init.dto.Spiv00801InitRequest;
import jp.co.brycen.wms.spiv00801init.dto.Spiv00801InitResponse;
import jp.co.brycen.wms.spiv00801init.process.Spiv00801InitProcess;


@Path("wms")
public class Spiv00801InitWebService extends AbstractWebService {
	@POST
	@Path("/Spiv00801Init")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Spiv00801InitResponse search(Spiv00801InitRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (Spiv00801InitResponse) abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new Spiv00801InitProcess(this);
	}
}
