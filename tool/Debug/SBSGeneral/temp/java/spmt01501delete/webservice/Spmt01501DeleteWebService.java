package jp.co.brycen.wms.#LowerScreenId#delete.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.#LowerScreenId#delete.dto.#FirstScreenId#DeleteRequest;
import jp.co.brycen.wms.#LowerScreenId#delete.dto.#FirstScreenId#DeleteResponse;
import jp.co.brycen.wms.#LowerScreenId#delete.process.#FirstScreenId#DeleteProcess;

@Path("wms")
public class #FirstScreenId#DeleteWebService extends AbstractWebService{
	@POST
	@Path("/#FirstScreenId#Delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public #FirstScreenId#DeleteResponse search(#FirstScreenId#DeleteRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (#FirstScreenId#DeleteResponse) abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new #FirstScreenId#DeleteProcess(this);
	}
}
