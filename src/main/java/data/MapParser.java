package data;

import model.OperationsModel;
import utilities.Logs;

import java.util.HashMap;
import java.util.Map;

public class MapParser {

    public Map<Integer, OperationsModel> getOperations() {
        Logs.debug("Creating operations model");

        final var map = new HashMap<Integer, OperationsModel>();
        final var operationsList = ExcelReader.getOperationsList();
        for (var element : operationsList) {
            map.put(element.getSumatoria(), element);
        }
        Logs.debug(map.toString());
        return map;
    }
}
