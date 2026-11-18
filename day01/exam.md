# Day 1 测试卷 · JDK / 语法 / Git

- 总分 100，限时 60 分钟，闭卷
- 范围：JDK-JRE-JVM、八大基本类型、装箱拆箱、`==` vs `equals`、String 不可变、
  类型转换、运算符与短路、`++i`/`i++`、switch、循环控制、值传递、javac/java、Git 三区
- 提交：选择题写题号+字母；编程题贴完整 `.java`
- 评分：≥85 优秀（直接进 Day 2）｜70–84 合格（错题当天重做）｜<70 先补半天

---

## 一、选择题（10 题 × 4 分 = 40 分）

**1.** 关于 JDK、JRE、JVM 三者关系，正确的是：
A. JRE 包含 JDK，JDK 包含 JVM
B. JDK 包含 JRE，JRE 包含 JVM
C. JVM 包含 JRE，JRE 包含 JDK
D. 三者相互独立，无包含关系

**2.** 下列基本类型中，占用字节数**相同**的一组是：
A. `int` 和 `long`
B. `char` 和 `short`
C. `float` 和 `double`
D. `boolean` 和 `byte`

**3.** 关于默认值，说法正确的是：
A. `Integer` 的默认值是 `0`
B. `Integer` 的默认值是 `null`
C. `int` 的默认值是 `null`
D. `Boolean` 的默认值是 `false`，而 `boolean` 的默认值是 `null`

**4.** `String s = "a"; s = "b";` 这两行之后，关于 String 的不可变性，正确理解是：
A. 因为不可变，所以第二行编译报错
B. 变量 `s` 可以重新指向新对象，但原对象 `"a"` 的内容本身不可被修改
C. String 内部 `char[]` 是 `public` 的，可以随时改
D. String 是可变的，只是没提供 setter 方法

**5.** 下面代码的输出是：
```java
int i = 5;
int j = i++ + ++i;
System.out.println(i + "," + j);
```
A. `6,11`　B. `7,12`　C. `7,11`　D. `6,12`

**6.** 关于下面这段代码，说法正确的是：
```java
int a = 0;
if (a != 0 && 10 / a > 1) {
    System.out.println("ok");
}
```
A. 运行期抛出 `ArithmeticException`
B. 正常运行，不进入 if 分支，也不抛异常
C. 编译期就报错，因为除数为 0 是常量
D. 正常运行，并且会打印 `ok`

**7.** 下列类型中，**不能**直接作为 `switch` 表达式的类型是：
A. `String`　B. `enum`　C. `long`　D. `Integer`

**8.** 下面代码输出什么：
```java
for (int i = 0; i < 3; i++) {
    if (i == 1) continue;
    System.out.print(i);
}
```
A. `01`　B. `02`　C. `012`　D. `0`

**9.** 下面代码输出什么：
```java
public static void change(int x) { x = 100; }
public static void change(int[] arr) { arr[0] = 100; }

public static void main(String[] args) {
    int a = 1;
    int[] b = {1};
    change(a);
    change(b);
    System.out.println(a + "," + b[0]);
}
```
A. `1,1`　B. `100,100`　C. `1,100`　D. `100,1`

**10.** 源码目录为 `src/Hello.java`（无 `package` 声明），已执行 `javac -d out src/Hello.java`。
下列哪条命令能正确运行：
A. `java out/Hello.class`
B. `java -cp out Hello`
C. `java Hello.class`
D. `java -d out Hello`

---

## 二、判断题（5 题 × 2 分 = 10 分）

**1.** Java 的 `char` 占 2 字节，可以直接存一个汉字。

**2.** `Integer a = 127; Integer b = 127;` 时 `a == b` 的结果是 `true`。

**3.** 方法内部把引用类型参数的引用改成指向新对象，调用方传入的变量也会跟着变。

**4.** `byte b = 128;` 可以编译通过。

**5.** `git commit` 会把工作区的改动直接写入版本库，跳过暂存区。

---

## 三、读代码写输出（3 题，7 + 7 + 6 = 20 分）

### 第 1 题（7 分）
```java
public class T1 {
    public static void main(String[] args) {
        int x = 10;
        int y = x > 5 ? x-- : ++x;
        System.out.println("x=" + x + ", y=" + y);
        System.out.println(0.1 + 0.2 == 0.3);
        System.out.println((int) 3.99);
        System.out.println((int) -3.99);
    }
}
```
写出四行输出。

### 第 2 题（7 分）
```java
public class T2 {
    static int count = 0;
    static void f() { count++; }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) continue;
            f();
            if (i == 3) break;
        }
        System.out.println(count);
    }
}
```
写出输出，并说明循环实际执行了哪几次 `f()`。

### 第 3 题（6 分）
```java
public class T3 {
    public static void main(String[] args) {
        String a = "hello";
        String b = "hel" + "lo";
        String c = new String("hello");
        String d = "hel";
        String e = d + "lo";

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a == e);
        System.out.println(a.equals(e));
    }
}
```
写出四行输出，并解释第 1 行和第 3 行为什么结果不同。

---

## 四、编程题（2 题 × 15 分 = 30 分）

### 第 1 题（15 分）闰年与月份天数

单文件 `Calendar.java`，实现：

```java
public static boolean isLeapYear(int year)
// year < 1 抛 IllegalArgumentException("年份非法: " + year)

public static int daysInMonth(int year, int month)
// month 不在 1~12 抛 IllegalArgumentException("月份非法: " + month)
// 2 月按 isLeapYear 返回 28 或 29
```

要求：
- `daysInMonth` 的月份分支**必须用 switch**（`1/3/5/7/8/10/12` 一组，`4/6/9/11` 一组，`2` 单独）
- `main` 内跑通下表 7 条断言
- 末尾手动触发 `daysInMonth(2026, 13)`，用 `try-catch` 捕获并打印

| 调用 | 期望 |
|---|---|
| `isLeapYear(1900)` | `false` |
| `isLeapYear(2000)` | `true` |
| `isLeapYear(2024)` | `true` |
| `isLeapYear(2026)` | `false` |
| `daysInMonth(2024, 2)` | `29` |
| `daysInMonth(2026, 2)` | `28` |
| `daysInMonth(2026, 4)` | `30` |

### 第 2 题（15 分）素数计数与乘法表

单文件 `Drill.java`，实现：

```java
public static int countPrimes(int n)
// 返回小于 n 的素数个数。n <= 1 返回 0。不得调用库函数判素数。

public static void printTable(int n)
// 输出 n×n 乘法表，每格右对齐占 4 字符宽（printf 的 %4d）
// n <= 0 抛 IllegalArgumentException("n 必须大于 0")
```

要求：
- `countPrimes` 用 `for` + 取模试除，试除上界到 `Math.sqrt(i)`
- `printTable(5)` 期望输出：

```
   1   2   3   4   5
   2   4   6   8  10
   3   6   9  12  15
   4   8  12  16  20
   5  10  15  20  25
```

- `main` 里打印 `countPrimes(100)`（期望 25）与 `countPrimes(1000000)`（期望 78498）

---

## 评分细则（我批改时按这个给分）

| 项 | 扣分点 |
|---|---|
| 编程题 | 编译不过 -15；断言未全过一处 -3；边界校验写在 `main` 而不是方法里 -3；用库函数判素数 -5 |
| 读代码题 | 输出错一处 -2；解释题为空或理由不成立 -3 |
| 选择题 | 单选，答错 0 分，不倒扣 |

批改输出格式：每题对错 + 错因分类（概念错 / 手误 / 边界漏判）+ 对应知识点回看位置。
