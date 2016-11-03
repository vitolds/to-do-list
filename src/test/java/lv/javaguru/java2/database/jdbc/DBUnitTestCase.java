package lv.javaguru.java2.database.jdbc;

import org.junit.Before;

public abstract class DBUnitTestCase {

    protected DatabaseUtil databaseUtil = new DatabaseUtil();


    //@Before // Uncomment this line if you want to use the sql script to insert some predefined values
    public void init() throws Exception {
        databaseUtil.setupDatabaseFromFile(getDatabaseFile());
    }

    protected abstract String getDatabaseFile();

}
