package com.starnetmc.Core.Modules.Database;

import com.starnetmc.Core.Utils.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Jay on 1/1/2016.
 * Jay is responsible for this class.
 */
public class MySQL {

    protected static Connection connection;

    private DBQuery dbq = new DBQuery();

    public boolean checkConnection() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    public Connection openConnection() {

        try {
            if (checkConnection()) {
                return connection;
            }
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://158.69.209.171:3306/StarNet?zeroDateTimeBehavior=convertToNull", "external", "WTeLtzYYszm343QD");
            DBQuery.st = connection.createStatement();
            DBQuery.pconn = connection;
            Logger.log("<Database> Database Connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (!checkConnection()) {
                return;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
