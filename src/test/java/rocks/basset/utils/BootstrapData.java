package rocks.basset.utils;


import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import javax.ejb.Stateless;
import java.io.File;
import java.net.MalformedURLException;

@Stateless
public class BootstrapData {

    public void initDBData() throws MalformedURLException, DataSetException {
        //FlatXmlDataSet flatXmlDataSet = new FlatXmlDataSet(new File("src/test/resources/dataset/dataset.xml"));
        //IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/dataset/dataset.xml"));
        //DatabaseDataSourceConnection db = new DatabaseDataSourceConnection();
        System.out.println();
    }
}
