package jp.co.brycen.wms.testinit.process; 
 
import jp.co.brycen.common.ILogSender; 
import jp.co.brycen.common.database.DBAccessor; 
import jp.co.brycen.common.dto.request.AbstractRequest; 
import jp.co.brycen.common.dto.response.AbstractResponse; 
import jp.co.brycen.common.exception.DBException; 
import jp.co.brycen.common.exception.FatalException; 
import jp.co.brycen.common.exception.ProcessCheckErrorException; 
import jp.co.brycen.common.process.AbstractProcess; 
import jp.co.brycen.wms.testinit.dto.TestInitResponse; 
 
 
/** 
 * 画面： 
 * 概要： 
 */ 
public class TestInitProcess extends AbstractProcess { 
 
    public TestInitProcess(ILogSender logSender) { 
        super(logSender); 
    } 
 
    @Override 
    public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse) 
            throws FatalException, DBException, ProcessCheckErrorException { 
        return response; 
    } 
 
    @Override 
    protected AbstractResponse createNewResponse(AbstractRequest request){ 
        return new TestInitResponse(); 
    } 
}  
