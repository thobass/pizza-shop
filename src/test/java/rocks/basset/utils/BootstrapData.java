package rocks.basset.utils;


import javax.ejb.Stateless;

@Stateless
public class BootstrapData {

    public void initDBData() {
       // FlatXmlDataSet flatXmlDataSet = new FlatXmlDataSet(new File("src/test/resources/dataset/dataset.xml"));
        //IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/dataset/dataset.xml"));
       // DatabaseDataSourceConnection db = new DatabaseDataSourceConnection();
        System.out.println();
    }
}
