/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.radius;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonToHiveTableConfig {

    private final String pathFrom;
    private final String baseTo;
    private final String tableTo;

    private final List<String> partitions;

    private JsonToHiveTableConfig(String pathFrom, String baseTo, String tableTo, List<String> partitions) {
        this.pathFrom = pathFrom;
        this.baseTo = baseTo;
        this.tableTo = tableTo;
        this.partitions = partitions;
    }

    public String getPathFrom() {
        return pathFrom;
    }

    public String getBaseTo() {
        return baseTo;
    }

    public String getTableTo() {
        return tableTo;
    }

    public List<String> getPartitions() {
        return partitions;
    }

    public static JsonToHiveTableConfig getConfig(String[] args) throws Exception {
        if(args.length < 3)
            throw new Exception("Args is not correct");
        String pathFrom = args[0];
        String baseTo = args[1];
        String tableTo = args[2];
        List<String> partitions = Arrays.stream(Arrays.copyOfRange(args, 3, args.length))
                .map(v -> String.join(" ",v.split("/",2)))
                .collect(Collectors.toList());
        return new JsonToHiveTableConfig(pathFrom, baseTo, tableTo, partitions);
    }


}
