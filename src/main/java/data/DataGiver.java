package data;

import model.OperationsModel;

public class DataGiver {

    public static OperationsModel getSumNumbers() {
        return MapParser.getOperations().get(1);
    }
}
