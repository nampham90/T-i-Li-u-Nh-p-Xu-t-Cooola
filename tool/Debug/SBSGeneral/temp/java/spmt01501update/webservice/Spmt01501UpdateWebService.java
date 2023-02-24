package jp.co.brycen.wms.#LowerScreenId#update.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.#LowerScreenId#update.dto.#FirstScreenId#UpdateRequest;
import jp.co.brycen.wms.#LowerScreenId#update.dto.#FirstScreenId#UpdateResponse;
import jp.co.brycen.wms.#LowerScreenId#update.process.#FirstScreenId#UpdateProcess;

@Path("wms")
public class #FirstScreenId#UpdateWebService extends AbstractWebService {
	@POST
	@Path("/#FirstScreenId#Update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public #FirstScreenId#UpdateResponse search(#FirstScreenId#UpdateRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (#FirstScreenId#UpdateResponse) abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new #FirstScreenId#UpdateProcess(this);
	}
}
