# Day 01 · 2026-11-18（周三）

**主题：环境搭建 + Java 语法热身｜2.5 小时**

## 今日任务清单

- [ ] 装 JDK 17，配 `JAVA_HOME` 与 `PATH`
- [ ] IDEA 新建项目，跑出 HelloWorld
- [ ] 命令行 `javac` + `java` 再跑一遍，搞清 `.java → .class → JVM`
- [ ] 写 3 个程序：`TempConvert.java`、`Bmi.java`、`LeapYear.java`
- [ ] 代码提交，README 打卡表打勾

## 验收

```
java -version   → 输出 17.x
git --version   → 有输出
```

- `LeapYear` 必须覆盖 1900（平年）和 2000（闰年）
- 能在纸上画出：`.java` --javac--> `.class` --java--> JVM 执行

## 今晚要写的三个程序（自己写，不许抄）

1. **TempConvert**：输入摄氏温度，输出华氏温度（`F = C × 9/5 + 32`），保留 1 位小数
2. **Bmi**：输入体重(kg)和身高(m)，输出 BMI 值与分级（<18.5 偏瘦 / 18.5–23.9 正常 / ≥24 偏胖）
3. **LeapYear**：输入年份，输出是否闰年（能被 4 整除且不能被 100 整除，或能被 400 整除）

## 自测

```bash
cd day01
javac TempConvert.java && java TempConvert
```
