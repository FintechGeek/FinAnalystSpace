package org.charlestech.fin.prototype;

/**
 * Created by banzhu on 14-2-3.
 */
public class FinLookupSeed {
    private String columnName;
    private String tableName;
    private String category;
    private String chineseName;
    private String englishName;

    public FinLookupSeed(String columnName, String tableName, String category,
                         String chineseName, String englishName) {
        super();
        this.columnName = columnName;
        this.tableName = tableName;
        this.category = category;
        this.chineseName = chineseName;
        this.englishName = englishName;
    }

    public FinLookupSeed(FinLookupSeed seed) {
        this.columnName = seed.getColumnName();
        this.tableName = seed.getTableName();
        this.category = seed.getCategory();
        this.chineseName = seed.getChineseName();
        this.englishName = seed.getEnglishName();
    }

    public FinLookupSeed() {
        super();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Column Name: " + this.getColumnName() + ",\t");
        sb.append("Table Name: " + this.getTableName() + ",\t");
        sb.append("Category: " + this.getCategory() + ",\t");
        sb.append("Chinese Name: " + this.getChineseName() + ",\t");
        sb.append("English Name: " + this.getEnglishName());
        return sb.toString();
    }

    public Object clone() {
        FinLookupSeed seed = null;
        try {
            seed = (FinLookupSeed) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return seed;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
}
