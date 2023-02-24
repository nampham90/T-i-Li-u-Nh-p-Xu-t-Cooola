package jp.co.brycen.wms.#LowerScreenId#init.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.#LowerScreenId#init.dto.#FirstScreenId#InitRequest;
import jp.co.brycen.wms.#LowerScreenId#init.dto.#FirstScreenId#InitResponse;
import jp.co.brycen.wms.#LowerScreenId#init.process.#FirstScreenId#InitProcess;


@Path("wms")
public class #FirstScreenId#InitWebService extends AbstractWebService {
	@POST
	@Path("/#FirstScreenId#Init")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public #FirstScreenId#InitResponse search(#FirstScreenId#InitRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (#FirstScreenId#InitResponse) abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new #FirstScreenId#InitProcess(this);
	}
}
