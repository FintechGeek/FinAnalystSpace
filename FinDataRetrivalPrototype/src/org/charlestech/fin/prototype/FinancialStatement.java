package org.charlestech.fin.prototype;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.sql.Timestamp;
/**
 * Created by banzhu on 14-2-3.
 */
public class FinancialStatement {
    private String stockId;
    private String statementType;
    private Timestamp releaseTime;
    private Map<FinLookupSeed, Double> dataMap;

    public FinancialStatement(){
        super();
    }

    public FinancialStatement(Timestamp releaseTime){
        super();
        this.dataMap = new HashMap<FinLookupSeed, Double>();
        this.statementType = new String();
        this.stockId = new String();
        this.releaseTime = releaseTime;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("Stock ID:" + this.getStockId() + ";\n");
        sb.append("Statement Type: " + this.getStatementType() + ";\n");
        sb.append("Release Time: " + this.getReleaseTime().toString() + ";\n");
        Set<Map.Entry<FinLookupSeed, Double>> entrySet = this.getDataMap().entrySet();
        for(Map.Entry<FinLookupSeed, Double> entry : entrySet){
            sb.append(entry.getKey().toString() + ";\n");
            sb.append(entry.getValue().toString() + ";\n");
        }
        return sb.toString();
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStatementType() {
        return statementType;
    }
    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Map<FinLookupSeed, Double> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<FinLookupSeed, Double> dataMap) {
        this.dataMap = dataMap;
    }
}
