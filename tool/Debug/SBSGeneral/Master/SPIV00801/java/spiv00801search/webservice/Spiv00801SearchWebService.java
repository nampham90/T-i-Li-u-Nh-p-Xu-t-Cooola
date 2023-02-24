package jp.co.brycen.wms.spiv00801search.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spiv00801search.dto.Spiv00801SearchRequest;
import jp.co.brycen.wms.spiv00801search.dto.Spiv00801SearchResponse;
import jp.co.brycen.wms.spiv00801search.process.Spiv00801SearchProcess;

@Path("wms")
public class Spiv00801SearchWebService extends AbstractWebService {
	@POST
	@Path("/Spiv00801Search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Spiv00801SearchResponse search(Spiv00801SearchRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (Spiv00801SearchResponse)abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new Spiv00801SearchProcess(this);
	}
}
