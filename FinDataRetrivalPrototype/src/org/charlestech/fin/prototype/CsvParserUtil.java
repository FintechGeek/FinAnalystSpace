package org.charlestech.fin.prototype;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
/**
 * Created by banzhu on 14-2-3.
 */
public class CsvParserUtil {
    public static List<FinancialStatement> parseStatement(String filePath){
        List<FinancialStatement> financialStatements= new ArrayList<FinancialStatement>();
        CsvParser parser = new CsvParser(filePath, "utf8");
        int recordCount = 0;
        while(parser.hasNext()){
            List<String> row = parser.next();
            if (0 == parser.getLineNumber()){
                recordCount = row.size() - 1;
                for (int i=1; i <= recordCount; i++){
                    Timestamp releaseTime = formatReleaseTime(row.get(i));
                    financialStatements.add(new FinancialStatement(releaseTime));
                }
            }else{
                if ((recordCount + 1) == row.size() && !row.get(0).isEmpty()){
                    FinLookupSeed seed = SeedDataUtil.findSeedByChineseName(row.get(0));
                    for(int i = 0; i < recordCount; i++){
                        financialStatements.get(i).getDataMap().put(seed,formatAmount(row.get(i+1)));
                    }
                }
            }
        }
        return financialStatements;
    }

    public static List<List<String>> parseIndustryCode(String filePath){
        List<List<String>> industryCodes = new ArrayList<List<String>>();
        CsvParser parser = new CsvParser(filePath, "utf8");
        while(parser.hasNext()){
            List<String> row = parser.next();
            industryCodes.add(row);
        }
        return industryCodes;
    }

    public static Timestamp formatReleaseTime(String releaseTime){
        //TODO: centralize the format pattern
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        try{
            date = formatter.parse(releaseTime);
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        return new Timestamp(date.getTime());
    }

    public static Double formatAmount(String amount){
        if (amount.matches("\\d+\\.\\d+") || amount.matches("\\d+"))
            return Double.parseDouble(amount);
        else
            return Double.parseDouble("0.0");
    }

    public static void main(String[] args){
        //Timestamp t = formatReleaseTime("2013/9/30");
        List<FinancialStatement> l = parseStatement("FinDataRetrivalPrototype/sample/balance.csv");
//		for (FinancialStatement f : l){
//			FinancialStatement ff = f;
//			System.out.println(ff.getReleaseTime().toString());
//			Set<Map.Entry<FinLookupSeed, Double>> entrySet = ff.getDataMap().entrySet();
//			for(Map.Entry<FinLookupSeed, Double> entry : entrySet){
//				System.out.println(entry.getKey().toString());
//				System.out.println(entry.getValue().toString());
//			}
////			System.out.println(ff.getDataMap().toString());
////			System.out.println(ff.toString());
//		}
//		for (FinancialStatement f : l){
//			System.out.println(f.toString());
//		}
        l.get(0).setStatementType("BALANCE");
        l.get(0).setStockId("600031");
        try {
            int num = DataModelUtil.insertFinancialStatement(l.get(0));
            System.out.println(num);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
