package jp.co.brycen.wms.#LowerScreenId#getdetail.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.#LowerScreenId#getdetail.dto.#FirstScreenId#GetDetailRequest;
import jp.co.brycen.wms.#LowerScreenId#getdetail.dto.#FirstScreenId#GetDetailResponse;
import jp.co.brycen.wms.#LowerScreenId#getdetail.process.#FirstScreenId#GetDetailProcess;

@Path("wms")
public class #FirstScreenId#GetDetailWebService extends AbstractWebService {

	@POST
	@Path("/#FirstScreenId#GetDetail")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public #FirstScreenId#GetDetailResponse search(#FirstScreenId#GetDetailRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (#FirstScreenId#GetDetailResponse) abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new #FirstScreenId#GetDetailProcess(this);
	}
}
