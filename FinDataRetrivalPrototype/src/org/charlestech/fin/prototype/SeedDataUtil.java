package org.charlestech.fin.prototype;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Created by banzhu on 14-2-3.
 */
public class SeedDataUtil {
    //static part
    public static String seedFilePath = "seed/fin_seed.xml";

    private static SeedDataUtil seedDataInstance = null;

    private static synchronized void syncInit() {
        if (null == seedDataInstance) {
            seedDataInstance = new SeedDataUtil(seedFilePath);
        }
    }

    private static SeedDataUtil getSeedDataInstance() {
        if (null == seedDataInstance) {
            syncInit();
        }
        return seedDataInstance;
    }

    public static FinLookupSeed findSeedByChineseName(String chineseName) {
        //TODO: judge whether it's the Chinese Characters
        if (null == chineseName) {
            return null;
        } else {
            FinLookupSeed seedRecord = null;
            List<FinLookupSeed> lookupList = SeedDataUtil.getSeedDataInstance().getSeedList();
            for (FinLookupSeed lookupItem : lookupList) {
                if (chineseName.equals(lookupItem.getChineseName())) {
                    seedRecord = lookupItem;
                    break;
                }
            }
            return seedRecord;
        }
    }


    //non-static part

    private List<FinLookupSeed> seedList;

    private SeedDataUtil() {
        super();
        seedList = new ArrayList<FinLookupSeed>();
    }

    private SeedDataUtil(String seedDataPath) {
        seedList = new ArrayList<FinLookupSeed>();
        try {
            Document doc = new SAXReader().read(new File(seedDataPath));
            Element root = doc.getRootElement();
            Element database = root.element("database");
            if (null != database) {
                Element tableData = database.element("table_data");
                if (null != tableData) {
                    String tableName = tableData.attributeValue("name");
                    if ("fin_lookup_ml".equalsIgnoreCase(tableName)) {
                        List<Element> rows = tableData.elements("row");
                        for (Element row : rows) {
                            List<Element> fields = row.elements("field");
                            FinLookupSeed seed = new FinLookupSeed();
                            for (Element field : fields) {
                                if ("column_name".equalsIgnoreCase(field.attributeValue("name"))) {
                                    seed.setColumnName(field.getText());
                                } else if ("table_name".equalsIgnoreCase(field.attributeValue("name"))) {
                                    seed.setTableName(field.getText());
                                } else if ("category".equalsIgnoreCase(field.attributeValue("name"))) {
                                    seed.setCategory(field.getText());
                                } else if ("chinese_name".equalsIgnoreCase(field.attributeValue("name"))) {
                                    seed.setChineseName(field.getText());
                                } else if ("english_name".equalsIgnoreCase(field.attributeValue("name"))) {
                                    seed.setEnglishName(field.getText());
                                }
                            }
                            seedList.add(seed);
                        }

                    }
                }
            }
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }

    public List<FinLookupSeed> getSeedList() {
        return seedList;
    }

    public void setSeedList(List<FinLookupSeed> seedList) {
        this.seedList = seedList;
    }

    public static void main(String[] args) {
        String chineseName = "应收账款(万元)";
        FinLookupSeed seed = findSeedByChineseName(chineseName);
        System.out.println(seed.toString());
    }
}
