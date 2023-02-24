package jp.co.brycen.wms.#LowerScreenId#search.webservice;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.#LowerScreenId#search.dto.#FirstScreenId#SearchRequest;
import jp.co.brycen.wms.#LowerScreenId#search.dto.#FirstScreenId#SearchResponse;
import jp.co.brycen.wms.#LowerScreenId#search.process.#FirstScreenId#SearchRecCntProcess;

@Path("wms")
public class #FirstScreenId#SearchRecCntWebService extends AbstractWebService{
	@POST
	@Path("/#FirstScreenId#SearchCnt")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public #FirstScreenId#SearchResponse search(#FirstScreenId#SearchRequest item) throws SQLException {
		AbstractResponse abs = super.executeProcess(item);
		return (#FirstScreenId#SearchResponse) abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new #FirstScreenId#SearchRecCntProcess(this);
	}
}
