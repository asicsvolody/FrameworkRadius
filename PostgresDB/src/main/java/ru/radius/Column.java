/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;

public class Column {
    private String name;
    private String type;
    private Integer numPartColumn;
    private boolean pk;
    private int position;

    public Column(String name, String type, Integer numPartColumn, boolean pk, int position) {
        this.name = name;
        this.type = type;
        this.numPartColumn = numPartColumn;
        this.pk = pk;
        this.position = position;
    }

    public Column(String name, String type, boolean pk, int position) {
        this.name = name;
        this.type = type;
        this.pk = pk;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getNumPartColumn() {
        return numPartColumn;
    }

    public boolean isPk() {
        return pk;
    }

    public int getPosition() {
        return position;
    }
}
