package rocks.basset.utils;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUnitDataExtractor {

    @Test
    void dataExtractor() throws ClassNotFoundException, SQLException, DatabaseUnitException, IOException {
        // database connection
        Class driverClass = Class.forName("org.postgresql.Driver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("dataset.xml"));
        // write DTD file
        FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("dataset.dtd"));
    }
}
