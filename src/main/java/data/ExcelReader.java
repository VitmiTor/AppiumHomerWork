package data;

import com.poiji.bind.Poiji;
import model.OperationsModel;

import java.io.File;
import java.util.List;

public class ExcelReader {
    private static final String excelpath = "src/test/resources/data/operaciones.xlsx";

    public static List<OperationsModel> getOperationsList() {
        return Poiji.fromExcel(new File(excelpath), OperationsModel.class);
    }
}
