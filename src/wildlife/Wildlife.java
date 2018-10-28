package wildlife;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Wildlife {

    public static void main(String[] args) {
        Wildlife wildlife = new Wildlife();
        try {
            wildlife.displayClassCount();
            System.out.println("");
            wildlife.displayCreepyCrawly("Reptiles");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void displayClassCount() throws Exception{
        DBConnector dbc = new DBConnector();
        String query =  "SELECT `class`, COUNT(*) AS `Number of species` " +
                        "FROM `animals` " +
                        "GROUP BY `class` " +
                        "ORDER BY `Number of species` DESC;";
        System.out.println(query);
        Connection connection = dbc.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()){
            String animal_class = rs.getString("class");
            int speciesCount = rs.getInt("Number of species");
            
            System.out.println(String.format("%-12s", animal_class)+" "+String.format("%2d", speciesCount));
        }
    }
    
    public void displayCreepyCrawly(String animal_class) throws Exception{
        DBConnector dbc = new DBConnector();
        String query =  "SELECT CONCAT(`group`, ' of ', `animal_name`) AS `Creepy-crawly` " +
                        "FROM `animals` NATURAL JOIN `groups` " +
                        "WHERE `class` = '"+animal_class+"';";
        
        System.out.println(query);
        Connection connection = dbc.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()){
            String value = rs.getString("Creepy-crawly");
            
            System.out.println(value);
        }
    }
}
