package atmsimulatorsystem.assistant.ui.Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class DatabaseHandler {
    private static DatabaseHandler handler = null;
    
    private static final String DB_URL = "jdbc:derby:database/atmsimulatorsystem;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;

    public DatabaseHandler() {
        createConnection();
        setupPersonalDetail1Table();
        setupPersonalDetail2Table();
        setupPersonalDetail3Table();
    }
    
//    public static DatabaseHandler getInstance(){
//        if(handler == null){
//            handler = new DatabaseHandler();
//        }
//        return handler;
//    }
    
    void createConnection(){
        try{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        conn = DriverManager.getConnection(DB_URL);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    void setupPersonalDetail1Table(){
        String TABLE_NAME = "PERSONALDETAILS1";
        try{
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if(tables.next()){
                System.out.println("Table " + TABLE_NAME + " already exists. Ready for go!");
            }else{
                stmt.execute("CREATE TABLE "+ TABLE_NAME + "("
                        + "Id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\n"
                        + "first_name varchar(100),\n"
                        + "middle_name varchar(100),\n"
                        + "last_name varchar(100),\n"
                        + "father_name varchar(100),\n"
                        + "gender char(5),\n"
                        + "email_address varchar(100),\n"
                        + "marital_status varchar(100),\n"
                        + "address varchar(100),\n"
                        + "city varchar(100),\n"
                        + "pin_code varchar(100),\n"
                        + "state varchar(100)"
                        + ")");
                
            }
        }catch(SQLException e){
            System.err.println(e.getMessage() + " --- setupDatabase");
        }finally{
            
        }
    }
    
    void setupPersonalDetail2Table(){
        String TABLE_NAME = "PERSONALDETAILS2";
        try{
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if(tables.next()){
                System.out.println("Table " + TABLE_NAME + " already exists. Ready for go!");
            }else{
                stmt.execute("CREATE TABLE "+ TABLE_NAME + "("
                        + "Id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\n"
                        + "religion varchar(100),\n"
                        + "income varchar(100),\n"
                        + "education_qualification varchar(100),\n"
                        + "occupation varchar(100),\n"
                        + "sin_number varchar(100),\n"
                        + "status varchar(100),\n"
                        + "existing_account varchar(100),\n"
                        + "account_type varchar(100)"
                        + ")");  
            }
        }catch(SQLException e){
            System.err.println(e.getMessage() + " --- setupDatabase");
        }finally{
            
        }
    }
        void setupPersonalDetail3Table(){
        String TABLE_NAME = "PERSONALDETAILS3";
        try{
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if(tables.next()){
                System.out.println("Table " + TABLE_NAME + " already exists. Ready for go!");
            }else{
                stmt.execute("CREATE TABLE "+ TABLE_NAME + "("
                        + "Id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\n"
                        + "randomNumber varchar(100),\n"
                        + "pinNumber varchar(100),\n"
                        + "service_required varchar(100)"
                        + ")");  
            }
        }catch(SQLException e){
            System.err.println(e.getMessage() + " --- setupDatabase");
        }finally{
            
        }
    }
    public ResultSet execQuery(String query){
        ResultSet result;
        try{
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }finally{
        }
        return result;
    }
    
    public boolean execAction(String qu){
        try{
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Excption at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        }finally{
        }
    }
}
