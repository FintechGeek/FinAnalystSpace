package org.charlestech.fin.prototype;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;
/**
 * Created by banzhu on 14-2-3.
 */
public class CsvParser implements Iterator<List<String>>{
    private CsvListReader reader = null;
    private List<String> row = null;

    public CsvParser(String csvFile, String encoding) {
        super();
        try {
            reader = new CsvListReader(new InputStreamReader(new FileInputStream(csvFile), encoding), CsvPreference.EXCEL_PREFERENCE);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean hasNext(){
        try {
            row = reader.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return row != null;
    }

    public List<String> next(){
        return row;
    }

    public void remove(){
        throw new UnsupportedOperationException("CsvParser is just for reading only.");
    }

    public void close(){
        if(reader != null){
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int getLineNumber(){
        return reader.getLineNumber()-1;
    }

    public static void main(String[] args){
        String file = "sample/balance.csv";

        CsvParser p = new CsvParser(file, "GBK");
        while(p.hasNext()){
            List<String> row = p.next();
            System.out.println(p.getLineNumber() + " : " + row.get(0) + ", " + row.get(1) + ", " + row.get(2));
        }
        p.close();
    }
}
