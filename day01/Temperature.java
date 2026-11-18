/**
 * Day 1 / 程序 1：华氏度 <-> 摄氏度 互转
 *
 * 考点：double 浮点运算、强制类型转换与四舍五入、方法定义与返回值。
 *
 * 公式：
 *   C = (F - 32) * 5 / 9
 *   F = C * 9 / 5 + 32
 *
 * 运行：
 *   java -cp out Temperature 98.6      # 按华氏度解释，输出摄氏度
 *   java -cp out Temperature c 37      # 按摄氏度解释，输出华氏度
 *   java -cp out Temperature           # 不带参数时跑内置用例表
 *
 * 注意 5 / 9 的坑：
 *   写成 (F - 32) * 5 / 9   -> 先乘后除，5 和 9 是 int，但式子已被提升为 double，结果正确
 *   写成 (F - 32) * (5 / 9) -> 5 / 9 先做整数除法得到 0，结果恒为 0.0，这是典型错误
 */
public class Temperature {

    /** 华氏度转摄氏度。 */
    public static double fahrenheitToCelsius(double f) {
        return (f - 32.0) * 5.0 / 9.0;
    }

    /** 摄氏度转华氏度。 */
    public static double celsiusToFahrenheit(double c) {
        return c * 9.0 / 5.0 + 32.0;
    }

    /** 保留 2 位小数。用 Math.round 做四舍五入，避免 String.format 依赖默认 Locale。 */
    public static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    /** 边界校验：绝对零度以下在物理上不存在，这里按 -459.67F / -273.15C 兜底。 */
    public static void checkRange(double f, double c) {
        if (f < -459.67) {
            throw new IllegalArgumentException("低于绝对零度(华氏): " + f);
        }
        if (c < -273.15) {
            throw new IllegalArgumentException("低于绝对零度(摄氏): " + c);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            runCases();
            return;
        }

        try {
            if (args.length >= 2 && (args[0].equalsIgnoreCase("c"))) {
                double c = Double.parseDouble(args[1]);
                checkRange(0.0, c);
                double f = celsiusToFahrenheit(c);
                System.out.println(round2(c) + " °C = " + round2(f) + " °F");
            } else {
                double f = Double.parseDouble(args[0]);
                checkRange(f, 0.0);
                double c = fahrenheitToCelsius(f);
                System.out.println(round2(f) + " °F = " + round2(c) + " °C");
            }
        } catch (NumberFormatException e) {
            System.err.println("参数不是合法数字: " + e.getMessage());
            System.exit(2);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(3);
        }
    }

    /** 内置用例，跑通就说明公式和边界都对。 */
    private static void runCases() {
        double[][] cases = {
                // {华氏度, 期望摄氏度}
                {212.0, 100.0},
                {32.0, 0.0},
                {98.6, 37.0},
                {-40.0, -40.0},
                {0.0, -17.78}
        };

        System.out.printf("%-10s %-10s %-10s %-6s%n", "F", "C(实际)", "C(期望)", "结果");
        boolean allPass = true;
        for (double[] c : cases) {
            double actual = round2(fahrenheitToCelsius(c[0]));
            double expect = c[1];
            boolean pass = Math.abs(actual - expect) < 0.011;
            allPass &= pass;
            System.out.printf("%-10s %-10s %-10s %-6s%n",
                    round2(c[0]), actual, expect, pass ? "PASS" : "FAIL");
        }

        System.out.println();
        System.out.println("反向验证 -40 °C = " + round2(celsiusToFahrenheit(-40.0)) + " °F（期望 -40.0）");
        System.out.println(allPass ? "全部用例通过" : "存在失败用例");
    }
}
