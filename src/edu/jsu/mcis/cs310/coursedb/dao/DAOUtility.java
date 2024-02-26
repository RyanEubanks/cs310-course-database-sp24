package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA24 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();

        try {
        
            if (rs != null) {

                ResultSetMetaData meta = rs.getMetaData();
                int cols = meta.getColumnCount();

                while(rs.next()) {
                    JsonObject job = new JsonObject();

                    for(int i = 1; i <= cols; i++) {
                        job.put(meta.getCatalogName(i), rs.getObject(i));
                    }
                    records.add(job);
                }
                return records.toString();
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
