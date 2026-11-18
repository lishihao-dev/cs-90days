# Day 1 · 2026-11-18（周三）

> 主题：环境搭建 + Java 语法热身
> 投入：2.5–3 小时
> 交付物：本目录下 4 个能跑的 `.java` + 一次 git push

## 环境现状（已实测，不要重复踩）

| 项 | 实测结果 | 处理 |
|---|---|---|
| `java`（PATH） | `1.8.0_503`（Oracle JRE） | 不要用 |
| `javac`（PATH） | `25.0.4.1`（`C:\Users\31995\.jdks\jdk-25.0.4.1+1`） | 用它编译出来的 class 是 version 69，JRE 8 加载不了 |
| `JAVA_HOME` | `C:\Users\31995\.jdks\jdk-25.0.4.1+1` | 正确，跟着它走 |
| git | PortableGit `C:\Program Files\WorkBuddy\resources\vendor\PortableGit\cmd` | 已配 `http.sslBackend=openssl` + `http.sslCAInfo` |

**结论：本机用 JDK 25，不要用 PATH 上那个 java 8。** 统一走 `run.ps1`，它会把 JDK 25 的 bin 提到 PATH 最前，并打开 UTF-8，避免中文乱码。

## 目录内容

| 文件 | 考点 |
|---|---|
| `HelloWorld.java` | `.java → .class → JVM` 链路、`main(String[] args)`、类加载器 |
| `Temperature.java` | double 运算、`(F-32)*5/9` 与 `(F-32)*(5/9)` 的坑、方法拆分、边界校验 |
| `Bmi.java` | 强制转换、`if/else if` 区间判定、浮点比较、格式化输出 |
| `LeapYear.java` | `&&` / `\|\|` 优先级与短路、取模、1900 与 2000 两个边界 |
| `run.ps1` | 一键编译 + 跑全部用例（ASCII 编写，防止 PS5.1 按 ANSI 读取乱码） |

## 执行

```powershell
cd D:\cs-90days\day01
powershell -NoProfile -ExecutionPolicy Bypass -File .\run.ps1
```

只跑一个、带参数：

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\run.ps1 LeapYear 2024
powershell -NoProfile -ExecutionPolicy Bypass -File .\run.ps1 Temperature c 37
powershell -NoProfile -ExecutionPolicy Bypass -File .\run.ps1 Bmi 70 1.75
```

`run.ps1` 必须带 `-ExecutionPolicy Bypass`，因为本机执行策略禁止运行未签名脚本。
想一劳永逸：

```powershell
Set-ExecutionPolicy -Scope CurrentUser RemoteSigned
```

## 手动跑一遍（要理解，不能只会按脚本）

```powershell
$env:Path = "C:\Users\31995\.jdks\jdk-25.0.4.1+1\bin;" + $env:Path
cd D:\cs-90days\day01
javac -encoding UTF-8 -d out HelloWorld.java
java -cp out HelloWorld
```

- `-d out`：指定 class 输出目录，`javac` 按 package 自动建子目录
- `-cp out`：告诉 JVM 去哪找 class，不是找 `.java`
- `java Hello.class` 会失败 —— `java` 收的是**类名**，不是文件名

## 验收标准（4 条全过才算 Day 1 完成）

1. `run.ps1` 跑完，四个程序都打印「全部用例通过」
2. 能说清 `.java → javac → .class → java → JVM` 这条链，以及 `-d` / `-cp` 各管什么
3. `LeapYear 1900` 输出平年、`LeapYear 2000` 输出闰年
4. 本目录改动已 commit 并 push 到 `lishihao-dev/cs-90days`

```powershell
cd D:\cs-90days
git add -A
git commit -m "feat(day01): 环境搭建 + 温度/BMI/闰年三个程序"
git push
```

## 今晚的测试

测试卷在对话里（20 题 / 100 分，覆盖 JDK、八大类型、装箱拆箱、`==` vs `equals`、String 不可变、类型转换、运算符短路、`++i`/`i++`、switch、循环控制、值传递、javac/java、Git 三区）。

提交方式：选择题写题号+字母，编程题贴完整代码。**≥85 直接进 Day 2（集合框架）。**

## 下一步（Day 2 预告）

集合框架入门：`ArrayList` / `LinkedList` / `HashMap` 的底层结构差异、`hashCode` 与 `equals` 约定、遍历时删除的 `ConcurrentModificationException`。
