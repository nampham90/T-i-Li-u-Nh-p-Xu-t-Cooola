package jp.co.brycen.wms.#LowerScreenId#regist.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.#LowerScreenId#regist.dto.#FirstScreenId#RegistRequest;
import jp.co.brycen.wms.#LowerScreenId#regist.dto.#FirstScreenId#RegistResponse;
import jp.co.brycen.wms.#LowerScreenId#regist.process.#FirstScreenId#RegistProcess;

@Path("wms")
public class #FirstScreenId#RegistWebService extends AbstractWebService{
	@POST
	@Path("/#FirstScreenId#Regist")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public #FirstScreenId#RegistResponse search(#FirstScreenId#RegistRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (#FirstScreenId#RegistResponse) abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new #FirstScreenId#RegistProcess(this);
	}
}
