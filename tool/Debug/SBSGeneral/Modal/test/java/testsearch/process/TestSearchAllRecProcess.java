package jp.co.brycen.wms.testsearch.process; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.List; 
 
import jp.co.brycen.common.ConstantValue; 
import jp.co.brycen.common.ILogSender; 
import jp.co.brycen.common.database.DBAccessor; 
import jp.co.brycen.common.database.DBStatement; 
import jp.co.brycen.common.dto.ErrorDto; 
import jp.co.brycen.common.dto.request.AbstractRequest; 
import jp.co.brycen.common.dto.response.AbstractResponse; 
import jp.co.brycen.common.exception.DBException; 
import jp.co.brycen.common.exception.FatalException; 
import jp.co.brycen.common.exception.ProcessCheckErrorException; 
import jp.co.brycen.common.process.AbstractProcess; 
import jp.co.brycen.common.utility.CommonUtility; 
import jp.co.brycen.common.utility.MessageUtility; 
import jp.co.brycen.common.utility.ValidateUtility; 
import jp.co.brycen.wms.test.dto.TestSearchRowDto; 
import jp.co.brycen.wms.testsearch.dto.TestSearchRequest; 
import jp.co.brycen.wms.testsearch.dto.TestSearchResponse; 
 
/** 
 * 画面: 
 */ 
public class TestSearchAllRecProcess extends AbstractProcess { 
 
    public TestSearchAllRecProcess(ILogSender logSender) { 
        super(logSender); 
    } 
    @Override 
    protected void beforeProcess(DBAccessor dba, AbstractRequest request, AbstractResponse response, 
            AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException { 
            // Search condition 
            TestSearchRequest reqTestSearch = (TestSearchRequest) request; 
            String a = reqTestSearch.testSearchCondition.A; 
            String b = reqTestSearch.testSearchCondition.B; 
            String c = reqTestSearch.testSearchCondition.C; 
            String d = reqTestSearch.testSearchCondition.D; 
            HashMap<String, String> formItemList = CommonUtility.getFormItemList(reqTestSearch.formItemList); 
            List <ErrorDto> msg = new ArrayList<ErrorDto>(); 
 
            if (!ValidateUtility.CheckNull(a) && ValidateUtility.CheckMaxLength(a, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "a";
                ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "50");
                msg.add(ret);
            }
            if (!ValidateUtility.CheckNull(b) && ValidateUtility.CheckMaxLength(b, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "b";
                ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "50");
                msg.add(ret);
            }
            if (!ValidateUtility.CheckNull(c) && ValidateUtility.CheckMaxLength(c, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "c";
                ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "50");
                msg.add(ret);
            }
            if (!ValidateUtility.CheckNull(d) && ValidateUtility.CheckMaxLength(d, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "d";
                ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "50");
                msg.add(ret);
            }
            if (msg.size() > 0) 
            {
                throw new ProcessCheckErrorException(msg, ConstantValue.FATAL_ERROR);
            }
    } 
 
 
    @Override 
    public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response, 
            AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException { 
        ResultSet rs = null; 
        DBStatement ps = null; 
 
        try { 
            // Search condition 
            TestSearchRequest reqTestSearch = (TestSearchRequest) request; 
            String cstmcd = reqTestSearch.accessInfo.CSTMCD; 
            String brnchcd = reqTestSearch.accessInfo.BRNCHCD; 
            String a = reqTestSearch.testSearchCondition.A; 
            String b = reqTestSearch.testSearchCondition.B; 
            String c = reqTestSearch.testSearchCondition.C; 
            String d = reqTestSearch.testSearchCondition.D; 
 
            // SQL 
            StringBuilder strSql = new StringBuilder(); 
 
            strSql.append("SELECT "); 
            strSql.append(this.setSelectFields()); 
            strSql.append("FROM "); 
 
            this.setExecWhere();
            // FROM WHERE TODO
 
            if (!ValidateUtility.CheckNull(a)) 
                //strSql.append("        COLUMN LIKE ? "); 
            if (!ValidateUtility.CheckNull(b)) 
                //strSql.append("        COLUMN LIKE ? "); 
            if (!ValidateUtility.CheckNull(c)) 
                //strSql.append("        COLUMN LIKE ? "); 
            if (!ValidateUtility.CheckNull(d)) 
                //strSql.append("        COLUMN LIKE ? "); 
 
 
            int index = 2; 
            // Set bind parameter 
            ps = dba.prepareStatement(strSql); 
            ps.setString(1, cstmcd); 
            ps.setString(2, brnchcd); 
            if (!ValidateUtility.CheckNull(a)) { 
                index++; 
                ps.setString(index, a); 
            } 
            if (!ValidateUtility.CheckNull(b)) { 
                index++; 
                ps.setString(index, b); 
            } 
            if (!ValidateUtility.CheckNull(c)) { 
                index++; 
                ps.setString(index, c); 
            } 
            if (!ValidateUtility.CheckNull(d)) { 
                index++; 
                ps.setString(index, d); 
            } 
 
            rs = ps.executeQuery(); 
 
            // Process set value for response 
            TestSearchResponse resTestSearch = (TestSearchResponse) response; 
            setResponse(rs, resTestSearch); 
 
            // Check records count 
            if (resTestSearch.rows != null && resTestSearch.rows.size() == 0) { 
                resTestSearch.rows = null; 
            } 
 
            return resTestSearch; 
        } catch (SQLException e) { 
            throw new DBException(e); 
        } finally { 
            try { 
                if (rs != null) 
                    rs.close(); 
                if (ps != null) 
                    ps.close(); 
            } catch (SQLException e) { 
                throw new DBException(e); 
            } 
        } 
    } 
 
    /** 
     * Set selected fields 
     * 
     * @return 
     */ 
    protected String setSelectFields() { 
        StringBuilder strSql = new StringBuilder(); 
        strSql.append("");
        return strSql.toString(); 
    } 
 
    /** 
     * ORDER BY 
     */ 
 
    protected String setOrderBy () { 
        StringBuilder strSql = new StringBuilder(); 
        strSql.append("");
        return strSql.toString(); 
    } 
 
    /** 
     * EXEC WHERE
     */ 
 
    protected String setExecWhere() { 
        StringBuilder strSql = new StringBuilder(); 
        strSql.append("");
        return strSql.toString(); 
    } 
 
    @Override 
    protected AbstractResponse createNewResponse(AbstractRequest request) { 
        return new TestSearchResponse(); 
    } 
 
    /** 
     * Set response value 
     * 
     * @return 
     * @throws SQLException 
     */ 
    protected void setResponse(ResultSet rs, TestSearchResponse resTestSearch) throws SQLException { 
        ArrayList<TestSearchRowDto> lst = new ArrayList<TestSearchRowDto>(); 
        while (rs.next()) { 
            TestSearchRowDto row = new TestSearchRowDto(); 
        row.E = rs.getString("E");
        row.D = rs.getString("D");
        row.F = rs.getString("F");
        row.G = rs.getString("G");
            lst.add(row); 
        } 
        resTestSearch.rows = lst; 
    } 
}  
