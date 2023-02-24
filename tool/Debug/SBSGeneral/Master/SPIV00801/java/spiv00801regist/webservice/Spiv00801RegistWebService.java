package jp.co.brycen.wms.spiv00801regist.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spiv00801regist.dto.Spiv00801RegistRequest;
import jp.co.brycen.wms.spiv00801regist.dto.Spiv00801RegistResponse;
import jp.co.brycen.wms.spiv00801regist.process.Spiv00801RegistProcess;

@Path("wms")
public class Spiv00801RegistWebService extends AbstractWebService{
	@POST
	@Path("/Spiv00801Regist")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Spiv00801RegistResponse search(Spiv00801RegistRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (Spiv00801RegistResponse) abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new Spiv00801RegistProcess(this);
	}
}
