package model;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("MathSheet")
public class OperationsModel {
    @ExcelCellName("key")
    private int sumatoria;
    @ExcelCellName("Numero1")
    private int number1;
    @ExcelCellName("Numero2")
    private int number2;

    @ExcelCellName("Resultado")
    private int result;

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public int getResult() {
        return result;
    }

    public int getSumatoria() {
        return sumatoria;
    }
}
