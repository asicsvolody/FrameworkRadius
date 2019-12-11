/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;

import java.util.List;

public class Table {
    private int idSource;
    private String srcSchema;
    private String srcTable;
    private String lastLogFile;
    private String lastLogFileLoc2;

    private List<Column> columns;

    public Table(int idSource, String srcSchema, String srcTable, String lastLogFile, String lastLogFileLoc2, List<Column> columns) {
        this.idSource = idSource;
        this.srcSchema = srcSchema;
        this.srcTable = srcTable;
        this.lastLogFile = lastLogFile;
        this.lastLogFileLoc2 = lastLogFileLoc2;
        this.columns = columns;
    }

    public int getIdSource() {
        return idSource;
    }

    public String getSrcSchema() {
        return srcSchema;
    }

    public String getSrcTable() {
        return srcTable;
    }

    public String getLastLogFile() {
        return lastLogFile;
    }

    public String getLastLogFileLoc2() {
        return lastLogFileLoc2;
    }

    public List<Column> getColumns() {
        return columns;
    }
}
