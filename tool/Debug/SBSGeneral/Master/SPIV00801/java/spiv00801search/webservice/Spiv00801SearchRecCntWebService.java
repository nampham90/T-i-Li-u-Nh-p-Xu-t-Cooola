package jp.co.brycen.wms.spiv00801search.webservice;

import java.sql.SQLException;

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
import jp.co.brycen.wms.spiv00801search.process.Spiv00801SearchRecCntProcess;

@Path("wms")
public class Spiv00801SearchRecCntWebService extends AbstractWebService{
	@POST
	@Path("/Spiv00801SearchCnt")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Spiv00801SearchResponse search(Spiv00801SearchRequest item) throws SQLException {
		AbstractResponse abs = super.executeProcess(item);
		return (Spiv00801SearchResponse) abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new Spiv00801SearchRecCntProcess(this);
	}
}
