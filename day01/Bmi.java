/**
 * Day 1 / 程序 2：BMI 计算器
 *
 * 考点：基本类型与强制转换、条件分支、方法拆分、浮点比较。
 *
 * 公式：BMI = 体重(kg) / 身高(m)^2
 *
 * 中国成人标准（与 WHO 略有差异，按国标归档）：
 *   BMI < 18.5          偏瘦
 *   18.5 <= BMI < 24.0  正常
 *   24.0 <= BMI < 28.0  超重
 *   BMI >= 28.0         肥胖
 *
 * 运行：
 *   java -cp out Bmi 70 1.75
 *   java -cp out Bmi            # 跑内置用例
 */
public class Bmi {

    /** 计算 BMI。身高以米为单位。 */
    public static double calc(double weightKg, double heightM) {
        if (weightKg <= 0) {
            throw new IllegalArgumentException("体重必须大于 0: " + weightKg);
        }
        if (heightM <= 0 || heightM > 3.0) {
            throw new IllegalArgumentException("身高不合理(米): " + heightM);
        }
        return weightKg / (heightM * heightM);
    }

    /** 按中国成人标准返回分级。 */
    public static String level(double bmi) {
        if (bmi < 18.5) {
            return "偏瘦";
        } else if (bmi < 24.0) {
            return "正常";
        } else if (bmi < 28.0) {
            return "超重";
        } else {
            return "肥胖";
        }
    }

    public static double round1(double v) {
        return Math.round(v * 10.0) / 10.0;
    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            try {
                double weight = Double.parseDouble(args[0]);
                double height = Double.parseDouble(args[1]);
                double bmi = calc(weight, height);
                System.out.println("体重 " + weight + " kg，身高 " + height + " m");
                System.out.println("BMI = " + round1(bmi) + "（" + level(bmi) + "）");
            } catch (NumberFormatException e) {
                System.err.println("参数不是合法数字: " + e.getMessage());
                System.exit(2);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                System.exit(3);
            }
            return;
        }

        runCases();
    }

    private static void runCases() {
        // {体重, 身高, 期望BMI, 期望分级}
        Object[][] cases = {
                {50.0, 1.75, 16.3, "偏瘦"},
                {70.0, 1.75, 22.9, "正常"},
                {80.0, 1.75, 26.1, "超重"},
                {95.0, 1.75, 31.0, "肥胖"},
                {70.0, 1.75, 22.9, "正常"}
        };

        System.out.printf("%-8s %-8s %-10s %-10s %-8s %-6s%n",
                "体重kg", "身高m", "BMI实际", "BMI期望", "分级", "结果");
        boolean allPass = true;
        for (Object[] c : cases) {
            double w = (Double) c[0];
            double h = (Double) c[1];
            double expectBmi = (Double) c[2];
            String expectLevel = (String) c[3];

            double bmi = round1(calc(w, h));
            String lv = level(calc(w, h));
            boolean pass = Math.abs(bmi - expectBmi) < 0.06 && lv.equals(expectLevel);
            allPass &= pass;

            System.out.printf("%-8s %-8s %-10s %-10s %-8s %-6s%n",
                    w, h, bmi, expectBmi, lv, pass ? "PASS" : "FAIL");
        }

        System.out.println();
        System.out.println("边界：BMI=18.5 -> " + level(18.5) + "（期望 正常）");
        System.out.println("边界：BMI=24.0 -> " + level(24.0) + "（期望 超重）");
        System.out.println("边界：BMI=28.0 -> " + level(28.0) + "（期望 肥胖）");
        System.out.println(allPass ? "全部用例通过" : "存在失败用例");
    }
}
