package data;

import model.OperationsModel;

public class DataGiver {
    private MapParser mapParser = new MapParser();

    public OperationsModel getSumNumbers() {
        return mapParser.getOperations().get(1);
    }
}
