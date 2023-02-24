package jp.co.brycen.wms.#LowerScreenId#checkdetail.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.#LowerScreenId#checkdetail.dto.#FirstScreenId#CheckDetailRequest;
import jp.co.brycen.wms.#LowerScreenId#checkdetail.dto.#FirstScreenId#CheckDetailResponse;
import jp.co.brycen.wms.#LowerScreenId#checkdetail.process.#FirstScreenId#CheckDetailProcess;

@Path("wms")
public class #FirstScreenId#CheckDetailWebService extends AbstractWebService {

	@POST
	@Path("/#FirstScreenId#CheckDetail")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public #FirstScreenId#CheckDetailResponse search(#FirstScreenId#CheckDetailRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (#FirstScreenId#CheckDetailResponse) abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new #FirstScreenId#CheckDetailProcess(this);
	}
}