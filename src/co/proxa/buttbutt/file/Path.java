package co.proxa.buttbutt.file;

public enum Path {

    SQL_USERNAME       ("Buttbutt Configuration.SQL.username"),
    SQL_PASSWORD       ("Buttbutt Configuration.SQL.password"),
    SQL_DATABASE       ("Buttbutt Configuration.SQL.database"),
    SQL_IP             ("Buttbutt Configuration.SQL.ip"),
    SQL_PORT           ("Buttbutt Configuration.SQL.port"),
    SQL_TABLE_PREFIX   ("Buttbutt Configuration.SQL.table_prefix");

    public String filePath;

    Path(String filePath) {
        this.filePath = filePath;
    }
}
