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
import jp.co.brycen.wms.testsearch.dto.TestSearchRequest; 
import jp.co.brycen.wms.testsearch.dto.TestSearchResponse; 
 
/** 
 * 画面：出荷グループ番号検索 
 * 概要：検索(件数取得) 
 */ 
public class TestSearchCntProcess extends TestSearchAllRecProcess { 
 
    public TestSearchCntProcess(ILogSender logSender) { 
        super(logSender); 
    } 
 
    /** 
     * レスポンスのインスタンスを取得 
     * @return 
     */ 
    protected AbstractResponse getResponse() 
    { 
        return new TestSearchResponse(); 
    } 
 
    @Override 
    protected AbstractResponse createNewResponse(AbstractRequest request) { 
        return new TestSearchResponse(); 
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
    protected void setResponse(ResultSet rs, TestSearchResponse testSearch) throws SQLException 
    { 
        while (rs.next()) { 
            testSearch.testSearchCount.DATACNT = rs.getLong("CNT"); 
        } 
    } 
}  
