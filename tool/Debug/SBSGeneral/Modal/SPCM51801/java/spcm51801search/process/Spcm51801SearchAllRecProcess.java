package jp.co.brycen.wms.spcm51801search.process; 
 
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
import jp.co.brycen.wms.spcm51801.dto.Spcm51801SearchRowDto; 
import jp.co.brycen.wms.spcm51801search.dto.Spcm51801SearchRequest; 
import jp.co.brycen.wms.spcm51801search.dto.Spcm51801SearchResponse; 
 
/** 
 * 画面: 
 */ 
public class Spcm51801SearchAllRecProcess extends AbstractProcess { 
 
    public Spcm51801SearchAllRecProcess(ILogSender logSender) { 
        super(logSender); 
    } 
    @Override 
    protected void beforeProcess(DBAccessor dba, AbstractRequest request, AbstractResponse response, 
            AbstractResponse parentResponse) throws FatalException, DBException, ProcessCheckErrorException { 
            // Search condition 
            Spcm51801SearchRequest reqSpcm51801Search = (Spcm51801SearchRequest) request; 
            String cstmordnosearch = reqSpcm51801Search.spcm51801SearchCondition.CSTMORDNOSEARCH; 
            String arvlplndatefromsearch = reqSpcm51801Search.spcm51801SearchCondition.ARVLPLNDATEFROMSEARCH; 
            String arvlplndatetosearch = reqSpcm51801Search.spcm51801SearchCondition.ARVLPLNDATETOSEARCH; 
            String arvlrdatefromsearch = reqSpcm51801Search.spcm51801SearchCondition.ARVLRDATEFROMSEARCH; 
            String arvlrdatetosearch = reqSpcm51801Search.spcm51801SearchCondition.ARVLRDATETOSEARCH; 
            String arvlrstckdatefromsearch = reqSpcm51801Search.spcm51801SearchCondition.ARVLRSTCKDATEFROMSEARCH; 
            String arvlrstckdatetosearch = reqSpcm51801Search.spcm51801SearchCondition.ARVLRSTCKDATETOSEARCH; 
            String sliplnnosearch = reqSpcm51801Search.spcm51801SearchCondition.SLIPLNNOSEARCH; 
            String siplnrsltnosearch = reqSpcm51801Search.spcm51801SearchCondition.SIPLNRSLTNOSEARCH; 
            String spplynmsearch = reqSpcm51801Search.spcm51801SearchCondition.SPPLYNMSEARCH; 
            HashMap<String, String> formItemList = CommonUtility.getFormItemList(reqSpcm51801Search.formItemList); 
            List <ErrorDto> msg = new ArrayList<ErrorDto>(); 
 
            if (!ValidateUtility.CheckNull(cstmordnosearch) && ValidateUtility.CheckMaxLength(cstmordnosearch, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "cstmordnosearch";
                ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "50");
                msg.add(ret);
            }
            if (!ValidateUtility.CheckNull(arvlplndatefromsearch) && ValidateUtility.CheckMaxLength(arvlplndatefromsearch, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "arvlplndatefromsearch";
                ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "50");
                msg.add(ret);
            }
            if (!ValidateUtility.CheckNull(arvlplndatetosearch) && ValidateUtility.CheckMaxLength(arvlplndatetosearch, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "arvlplndatetosearch";
                ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "50");
                msg.add(ret);
            }
            if (!ValidateUtility.CheckNull(arvlrdatefromsearch) && ValidateUtility.CheckMaxLength(arvlrdatefromsearch, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "arvlrdatefromsearch";
                ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "50");
                msg.add(ret);
            }
            if (!ValidateUtility.CheckNull(arvlrdatetosearch) && ValidateUtility.CheckMaxLength(arvlrdatetosearch, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "arvlrdatetosearch";
                ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "50");
                msg.add(ret);
            }
            if (!ValidateUtility.CheckNull(arvlrstckdatefromsearch) && ValidateUtility.CheckMaxLength(arvlrstckdatefromsearch, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "arvlrstckdatefromsearch";
                ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "50");
                msg.add(ret);
            }
            if (!ValidateUtility.CheckNull(arvlrstckdatetosearch) && ValidateUtility.CheckMaxLength(arvlrstckdatetosearch, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "arvlrstckdatetosearch";
                ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "50");
                msg.add(ret);
            }
            if (!ValidateUtility.CheckNull(sliplnnosearch) && ValidateUtility.CheckMaxLength(sliplnnosearch, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "sliplnnosearch";
                ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "50");
                msg.add(ret);
            }
            if (!ValidateUtility.CheckNull(siplnrsltnosearch) && ValidateUtility.CheckMaxLength(siplnrsltnosearch, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "siplnrsltnosearch";
                ret.errMsg = MessageUtility.getMessageMsg("ME000050", request.accessInfo).replace("%1", "50");
                msg.add(ret);
            }
            if (!ValidateUtility.CheckNull(spplynmsearch) && ValidateUtility.CheckMaxLength(spplynmsearch, 50)) 
            {
                ErrorDto ret = new ErrorDto();
                ret.controlID = "spplynmsearch";
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
            Spcm51801SearchRequest reqSpcm51801Search = (Spcm51801SearchRequest) request; 
            String cstmcd = reqSpcm51801Search.accessInfo.CSTMCD; 
            String brnchcd = reqSpcm51801Search.accessInfo.BRNCHCD; 
            String cstmordnosearch = reqSpcm51801Search.spcm51801SearchCondition.CSTMORDNOSEARCH; 
            String arvlplndatefromsearch = reqSpcm51801Search.spcm51801SearchCondition.ARVLPLNDATEFROMSEARCH; 
            String arvlplndatetosearch = reqSpcm51801Search.spcm51801SearchCondition.ARVLPLNDATETOSEARCH; 
            String arvlrdatefromsearch = reqSpcm51801Search.spcm51801SearchCondition.ARVLRDATEFROMSEARCH; 
            String arvlrdatetosearch = reqSpcm51801Search.spcm51801SearchCondition.ARVLRDATETOSEARCH; 
            String arvlrstckdatefromsearch = reqSpcm51801Search.spcm51801SearchCondition.ARVLRSTCKDATEFROMSEARCH; 
            String arvlrstckdatetosearch = reqSpcm51801Search.spcm51801SearchCondition.ARVLRSTCKDATETOSEARCH; 
            String sliplnnosearch = reqSpcm51801Search.spcm51801SearchCondition.SLIPLNNOSEARCH; 
            String siplnrsltnosearch = reqSpcm51801Search.spcm51801SearchCondition.SIPLNRSLTNOSEARCH; 
            String spplynmsearch = reqSpcm51801Search.spcm51801SearchCondition.SPPLYNMSEARCH; 
 
            // SQL 
            StringBuilder strSql = new StringBuilder(); 
 
            strSql.append("SELECT "); 
            strSql.append(this.setSelectFields()); 
            strSql.append("FROM "); 
 
            this.setExecWhere();
            // FROM WHERE TODO
 
            if (!ValidateUtility.CheckNull(cstmordnosearch)) 
                //strSql.append("        COLUMN LIKE ? "); 
            if (!ValidateUtility.CheckNull(arvlplndatefromsearch)) 
                //strSql.append("        COLUMN LIKE ? "); 
            if (!ValidateUtility.CheckNull(arvlplndatetosearch)) 
                //strSql.append("        COLUMN LIKE ? "); 
            if (!ValidateUtility.CheckNull(arvlrdatefromsearch)) 
                //strSql.append("        COLUMN LIKE ? "); 
            if (!ValidateUtility.CheckNull(arvlrdatetosearch)) 
                //strSql.append("        COLUMN LIKE ? "); 
            if (!ValidateUtility.CheckNull(arvlrstckdatefromsearch)) 
                //strSql.append("        COLUMN LIKE ? "); 
            if (!ValidateUtility.CheckNull(arvlrstckdatetosearch)) 
                //strSql.append("        COLUMN LIKE ? "); 
            if (!ValidateUtility.CheckNull(sliplnnosearch)) 
                //strSql.append("        COLUMN LIKE ? "); 
            if (!ValidateUtility.CheckNull(siplnrsltnosearch)) 
                //strSql.append("        COLUMN LIKE ? "); 
            if (!ValidateUtility.CheckNull(spplynmsearch)) 
                //strSql.append("        COLUMN LIKE ? "); 
 
 
            int index = 2; 
            // Set bind parameter 
            ps = dba.prepareStatement(strSql); 
            ps.setString(1, cstmcd); 
            ps.setString(2, brnchcd); 
            if (!ValidateUtility.CheckNull(cstmordnosearch)) { 
                index++; 
                ps.setString(index, cstmordnosearch); 
            } 
            if (!ValidateUtility.CheckNull(arvlplndatefromsearch)) { 
                index++; 
                ps.setString(index, arvlplndatefromsearch); 
            } 
            if (!ValidateUtility.CheckNull(arvlplndatetosearch)) { 
                index++; 
                ps.setString(index, arvlplndatetosearch); 
            } 
            if (!ValidateUtility.CheckNull(arvlrdatefromsearch)) { 
                index++; 
                ps.setString(index, arvlrdatefromsearch); 
            } 
            if (!ValidateUtility.CheckNull(arvlrdatetosearch)) { 
                index++; 
                ps.setString(index, arvlrdatetosearch); 
            } 
            if (!ValidateUtility.CheckNull(arvlrstckdatefromsearch)) { 
                index++; 
                ps.setString(index, arvlrstckdatefromsearch); 
            } 
            if (!ValidateUtility.CheckNull(arvlrstckdatetosearch)) { 
                index++; 
                ps.setString(index, arvlrstckdatetosearch); 
            } 
            if (!ValidateUtility.CheckNull(sliplnnosearch)) { 
                index++; 
                ps.setString(index, sliplnnosearch); 
            } 
            if (!ValidateUtility.CheckNull(siplnrsltnosearch)) { 
                index++; 
                ps.setString(index, siplnrsltnosearch); 
            } 
            if (!ValidateUtility.CheckNull(spplynmsearch)) { 
                index++; 
                ps.setString(index, spplynmsearch); 
            } 
 
            rs = ps.executeQuery(); 
 
            // Process set value for response 
            Spcm51801SearchResponse resSpcm51801Search = (Spcm51801SearchResponse) response; 
            setResponse(rs, resSpcm51801Search); 
 
            // Check records count 
            if (resSpcm51801Search.rows != null && resSpcm51801Search.rows.size() == 0) { 
                resSpcm51801Search.rows = null; 
            } 
 
            return resSpcm51801Search; 
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
        return new Spcm51801SearchResponse(); 
    } 
 
    /** 
     * Set response value 
     * 
     * @return 
     * @throws SQLException 
     */ 
    protected void setResponse(ResultSet rs, Spcm51801SearchResponse resSpcm51801Search) throws SQLException { 
        ArrayList<Spcm51801SearchRowDto> lst = new ArrayList<Spcm51801SearchRowDto>(); 
        while (rs.next()) { 
            Spcm51801SearchRowDto row = new Spcm51801SearchRowDto(); 
        row.CSTMORDNO = rs.getString("CSTMORDNO");
        row.ARVLPLNDATE = rs.getString("ARVLPLNDATE");
        row.ARVLRDATE = rs.getString("ARVLRDATE");
        row.ARVLRSTCKDATE = rs.getString("ARVLRSTCKDATE");
        row.SLIPLNNO = rs.getString("SLIPLNNO");
        row.SIPLNRSLTNO = rs.getString("SIPLNRSLTNO");
        row.DIVKBN = rs.getString("DIVKBN");
        row.ARVLCOMPSTS = rs.getString("ARVLCOMPSTS");
        row.SPPLYNM = rs.getString("SPPLYNM");
            lst.add(row); 
        } 
        resSpcm51801Search.rows = lst; 
    } 
}  
