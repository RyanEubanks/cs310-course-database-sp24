package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    private static final String QUERY_INSERT = "INSERT INTO registration (studentid, termid, crn) VALUES (?,?,?)";
    private static final String QUERY_DELETE = "DELETE FROM registration WHERE studentid=? AND termid=? AND crn=?";
    private static final String QUERY_DELETE2 = "DELETE FROM registration WHERE studentid=? AND termid=?";
    private static final String QUERY_SELECT = "SELECT * FROM registration WHERE studentid=? AND termid=?";

    //private static final String QUERY_SELECT = "SELECT * FROM registration WHERE studentid=? AND termid=? AND crn=?";



    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cs;
        
        try {
            
            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_INSERT);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);

                cs = ps.executeUpdate();

                if (cs > 0) {
                    result = true;
                }
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;

        int deletedRow;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_DELETE);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);

                deletedRow = ps.executeUpdate();

                if(deletedRow > 0) {
                    result = true;
                }
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;

        int deletedRow;
        
        try {
            
            Connection conn = daoFactory.getConnection();

            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_DELETE2);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);

                deletedRow = ps.executeUpdate();

                if(deletedRow > 0) {
                    result = true;
                }

            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_SELECT);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);

                rs = ps.executeQuery();

                DAOUtility.getResultSetAsJson(rs);
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}
