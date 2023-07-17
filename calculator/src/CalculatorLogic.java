import javax.swing.*;
import java.math.BigDecimal;

public class CalculatorLogic {
    // cal()方法中编写了计算逻辑的实现。
    public static double calculate(String str1, String str2, String signal) {
        double a2, b2;    //定义两个变量a2,b2
        double result2 = 0;        //初始化
        if (str1.equals("."))
            str1 = "0.0";
        if (str2.equals("."))
            str2 = "0.0";
        a2 = Double.valueOf(str1).doubleValue();
        b2 = Double.valueOf(str2).doubleValue();
        if (signal.equals("+")) {
            result2 = a2 + b2;
        }
        if (signal.equals("—")) {
            result2 = a2 - b2;
        }
        if (signal.equals("*")) {
            BigDecimal m1 = new BigDecimal(Double.toString(a2));
            BigDecimal m2 = new BigDecimal(Double.toString(b2));
            //乘法运算,只需导入组件（import  java.math.BigDecimal）
            result2 = m1.multiply(m2).doubleValue();
        }
        if (signal.equals("/")) {
            if (b2 == 0) {
                result2 = 0;
            } else {
                result2 = a2 / b2;
            }
        }
        return result2;
    }
}
