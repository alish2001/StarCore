package com.starnetmc.Core.Modules.Database;

import java.sql.*;

/**
 * Created by Jay on 1/1/2016.
 * Jay is responsible for this class.
 */
public class DBQuery {

    public static MySQL db;
    private static ResultSet rs;
    public static Connection pconn;
    public static Statement st;


    public void exampleQuery() {//TODO Ali remove later
        String query = "SELECT * FROM Accounts;";
        try {
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("name") + " ID: " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void examplePreparedStatement(String name, int id) {
        String query = "INSERT INTO Accounts (value1, value2, value3) VALUES (?, ?, ?);";

        try {
            PreparedStatement ps = pconn.prepareStatement(query);

            ps.setString(1, "Set value 1");
            ps.setString(2, "Set value 2");
            ps.setString(3, "Set value 3");

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}