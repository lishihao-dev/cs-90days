/**
 * Day 1 / 程序 3：闰年判断
 *
 * 考点：逻辑运算符（&& / ||）的优先级与短路、% 取模、方法返回值。
 *
 * 闰年规则：
 *   1. 能被 4 整除，但不能被 100 整除  -> 闰年
 *   2. 能被 400 整除                    -> 闰年
 *   合并写法： (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)
 *
 * 两个必须覆盖的边界：
 *   1900 -> false（能被 4 和 100 整除，不能被 400 整除）
 *   2000 -> true （能被 400 整除）
 *
 * 运行：
 *   java -cp out LeapYear 2024
 *   java -cp out LeapYear          # 跑内置用例
 */
public class LeapYear {

    /** 判定闰年。year < 1 视为非法。 */
    public static boolean isLeap(int year) {
        if (year < 1) {
            throw new IllegalArgumentException("年份非法: " + year);
        }
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        if (args.length >= 1) {
            try {
                int y = Integer.parseInt(args[0]);
                System.out.println(y + " -> " + (isLeap(y) ? "闰年" : "平年"));
            } catch (NumberFormatException e) {
                System.err.println("参数不是合法整数: " + e.getMessage());
                System.exit(2);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                System.exit(3);
            }
            return;
        }

        int[] years = {1900, 2000, 2024, 2026, 2023, 2100, 2400};
        boolean[] expect = {false, true, true, false, false, false, true};

        System.out.printf("%-8s %-10s %-10s %-6s%n", "年份", "实际", "期望", "结果");
        boolean allPass = true;
        for (int i = 0; i < years.length; i++) {
            boolean actual = isLeap(years[i]);
            boolean pass = actual == expect[i];
            allPass &= pass;
            System.out.printf("%-8s %-10s %-10s %-6s%n",
                    years[i], actual, expect[i], pass ? "PASS" : "FAIL");
        }

        System.out.println();
        System.out.println("短路演示：");
        int n = 0;
        // n != 0 为 false，&& 右侧不会求值，所以不会抛 ArithmeticException
        if (n != 0 && 10 / n > 1) {
            System.out.println("不会走到这里");
        } else {
            System.out.println("  n=0 时 && 短路生效，未执行 10/n，没有抛异常");
        }

        System.out.println(allPass ? "全部用例通过" : "存在失败用例");
    }
}
