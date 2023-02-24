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
import jp.co.brycen.wms.spcm51801search.dto.Spcm51801SearchRequest; 
import jp.co.brycen.wms.spcm51801search.dto.Spcm51801SearchResponse; 
 
/** 
 * 画面：出荷グループ番号検索 
 * 概要：検索(件数取得) 
 */ 
public class Spcm51801SearchCntProcess extends Spcm51801SearchAllRecProcess { 
 
    public Spcm51801SearchCntProcess(ILogSender logSender) { 
        super(logSender); 
    } 
 
    /** 
     * レスポンスのインスタンスを取得 
     * @return 
     */ 
    protected AbstractResponse getResponse() 
    { 
        return new Spcm51801SearchResponse(); 
    } 
 
    @Override 
    protected AbstractResponse createNewResponse(AbstractRequest request) { 
        return new Spcm51801SearchResponse(); 
    } 
 
    /** 
     * Set selected fields 
     * 
     * @return 
     */ 
    protected String setSelectFields() { 
        StringBuilder strSql = new StringBuilder(); 
        strSql.append(" COUNT(*) as CNT ");
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
 
    /** 
     * レスポンスをセット 
     * @return 
     * @throws SQLException 
     */ 
    protected void setResponse(ResultSet rs, Spcm51801SearchResponse spcm51801Search) throws SQLException 
    { 
        while (rs.next()) { 
            spcm51801Search.spcm51801SearchCount.DATACNT = rs.getLong("CNT"); 
        } 
    } 
}  
