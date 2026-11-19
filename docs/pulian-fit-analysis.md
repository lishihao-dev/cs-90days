# 普联软件「Java 开发（财务信息化）」匹配度分析（2028 届版）

_更新：2026-11-19。前提修正：2026 年 11 月读大三上 = 2024 级 = **2028 届**（2028 年 6 月毕业）。_

> **前一版按 2027 届算（进面 70%–80%），前提不成立，已作废。** 大三上在 2026-11，倒推四年制：
> 2024-09 大一 → 2025-09 大二 → 2026-09 大三 → 2027-09 大四 → **2028-06 毕业**。
> 2027 届对应的是 2023 级，现在应该读大四上。

## 一、结论

**这条岗现在投不了：JD 写死「2027 年毕业的在校学生」，你是 2028 届，HR 第一层就筛掉。**
技术准备度不是问题——100 天学完正好覆盖它的全部要求；问题是届数，不在你能力范围内。

| 场景 | 进面 | 拿 offer |
|---|---|---|
| 现在（2026-11）投这条 2027 届岗 | 10%–15% | 5%–8% |
| 现在投 + 打招呼里说明"2028 届但可连续实习 12 个月" | 20%–25% | 10%–12% |
| **2027-03 起**（普联开 2028 届暑期实习通道）投 | **50%–60%** | **30%–40%** |
| 2027-09 秋招正式批 | 60%–70% | 30%–35% |

**真正的投递节点是 2027 年 3 月，不是现在。** 那时普联按 2028 届招，你属于正常排队。

## 二、JD 逐条对照

| JD 原文 | 你的情况 | 判定 |
|---|---|---|
| 计算机相关专业本科及以上 | 计算机科学与技术，本科 | ✅ |
| **2027 年毕业的在校学生** | **2028 届（2028-06 毕业）** | ❌ **唯一卡点** |
| 掌握 Java 核心语法 | 100 天计划 D1–D28 | ✅ 可达 |
| 熟悉 Spring Boot 等基础框架 | D29–D49 项目一 | ✅ 可达 |
| 了解 MySQL 数据库操作 | D15–D28 | ✅ 可达 |
| 有财务系统开发经验者优先 | D50–D70 补记账模块 | ⚠️ 加分项，可补 |
| 实习 6 个月、5 天/周 | 大四下（2028-02 → 06）无课，可全职 | ✅ 到时满足 |
| 四六级 | JD 全文未提 | ✅ 不影响 |

**你有一个别人没有的窗口**：2028 届的大四下是 2028-02 → 2028-06，
这段时间无课、无考研压力、可直接全职 5 个月。也就是说，
2027 年 3 月投暑期实习进去、干到 2028 年 6 月毕业，能连续干满 **14 个月**——
这个时长比任何一个 2027 届学生都长。破例话术就写这一点。

## 三、现在到 2027-03 该做什么

**现在不投这一家，但简历和项目必须在这四个月里做完。**

| 阶段 | 日期 | 学习 | 求职 |
|---|---|---|---|
| 一 | 11-19 → 12-13（25 天） | Java 语法 → 面向对象 → 集合 → IO → 多线程 | 简历初稿 |
| 二 | 12-14 → 01-10（28 天） | MySQL → Spring Boot → MyBatis-Plus → 项目一起步 | 投**不限届数**的：亚微、和利时、极飞、百度 |
| 三 | 01-11 → 02-07（28 天） | 项目一 8 接口 + 财务模块 + 部署上线 | 第二批投递；接寒假可谈的岗 |
| 四 | 02-08 → 02-27（20 天） | Redis + JWT + 八股一轮 | 简历定稿（后端版 + 数据版） |
| 五 | **03-01 → 03-31** | 只补八股和算法 | **暑期实习提前批主战场：普联 + 30–50 家** |

## 四、D50–D70 必做的一段：把项目改成财务场景

普联做石油石化、建筑、银行保险的管理信息化。项目一加这四张表，方向就对上了：

```sql
-- 会计科目
CREATE TABLE account (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  code VARCHAR(20) NOT NULL COMMENT '科目编码 1001/1002',
  name VARCHAR(64) NOT NULL COMMENT '库存现金/银行存款',
  type TINYINT NOT NULL COMMENT '1资产 2负债 3权益 4成本 5损益',
  parent_id BIGINT DEFAULT 0 COMMENT '父科目，支持多级',
  UNIQUE KEY uk_code (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 记账凭证头
CREATE TABLE voucher (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  voucher_no VARCHAR(32) NOT NULL COMMENT '凭证号 记-2027-01-0001',
  biz_date DATE NOT NULL COMMENT '业务日期',
  period CHAR(7) NOT NULL COMMENT '会计期间 2027-01',
  summary VARCHAR(255) COMMENT '摘要',
  creator_id BIGINT NOT NULL,
  status TINYINT DEFAULT 0 COMMENT '0草稿 1已审核 2已记账',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uk_no (voucher_no),
  KEY idx_period (period)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 凭证分录（一借一贷，借贷必相等）
CREATE TABLE voucher_entry (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  voucher_id BIGINT NOT NULL,
  account_id BIGINT NOT NULL,
  direction TINYINT NOT NULL COMMENT '1借 2贷',
  amount DECIMAL(18,2) NOT NULL,
  KEY idx_voucher (voucher_id),
  KEY idx_account (account_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 科目余额（按月汇总，报表数据源）
CREATE TABLE account_balance (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  account_id BIGINT NOT NULL,
  period CHAR(7) NOT NULL,
  debit_total DECIMAL(18,2) DEFAULT 0 COMMENT '本期借方发生额',
  credit_total DECIMAL(18,2) DEFAULT 0 COMMENT '本期贷方发生额',
  UNIQUE KEY uk_acc_period (account_id, period)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

配套接口（8 个，正好是项目一的量）：

| 方法 | 路径 | 作用 |
|---|---|---|
| POST | `/api/voucher` | 新增凭证（校验借贷平衡，不平返回 400） |
| GET | `/api/voucher/page` | 按期间分页查凭证 |
| POST | `/api/voucher/{id}/audit` | 审核凭证（草稿→已审核，状态机校验） |
| POST | `/api/voucher/{id}/post` | 记账（已审核→已记账，写 account_balance） |
| GET | `/api/account/tree` | 科目树（递归 parent_id） |
| GET | `/api/report/balance?period=` | 科目余额表 |
| GET | `/api/report/profit?period=` | 简易利润表（损益类科目汇总） |
| GET | `/api/voucher/export?period=` | 导出 CSV |

面试里的差别：「我做了个 CRUD」和「我实现了借贷平衡校验、凭证状态机、期末余额结转」
是两个量级。后者说明你懂业务约束，而不只是会调 MyBatis。

## 五、四六级：确认为零影响

- JD 原文没有任何英语要求。
- 财务信息化是国内业务线，客户是石油石化、建筑、银行保险。
- 普联虽有日本大阪机构，但那是另一条业务线，校招时分开招。
- 技术面只会问 Spring Bean 生命周期、MySQL 索引失效、事务隔离级别这类。

真有精力，把它花在 D50–D70 那段财务模块上，收益是四六级的十倍。

## 六、必答的面试题（提前准备）

| 问题 | 该怎么答 |
|---|---|
| 为什么选我们 | 「做管理信息化，客户是集团企业，业务复杂度比互联网 CRUD 高；我项目里做了凭证和余额结转，想继续往这个方向深入」 |
| 你能实习多久 | 「2028 届，2027-03 起到 2028-06 毕业可连续实习 14 个月，暑期可全职 5 天/周，大四下无课也能全职」 |
| 讲讲你的项目 | 从凭证状态机讲起，讲借贷平衡校验怎么拦截脏数据，讲余额表为什么用按月汇总而不是实时算 |
| 你还有哪些不足 | 「分布式和消息队列只在课程里接触过，没在生产环境用过；MySQL 索引优化是自己拿 explain 试出来的，没有系统的调优经验」 |

## 七、填表注意：届别按毕业年份填

网申系统里的「毕业年份」字段筛的是**毕业日期**，不是入学年份，也不是「XX 级」。

- 2027 年 6 月毕业 → 填 **2027**
- **2028 年 6 月毕业 → 填 2028**（你是这一档，即使习惯说自己是「2024 级」）

填错的后果：简历写 2027 届，但学信网是 2028 年毕业，入职背调或签三方时对不上，
offer 可能被撤回。HR 也会从「大三」和「2027 届」的矛盾里看出问题——
2026 年 11 月读大三却自称 2027 届，本身就是硬矛盾。

---

_说明：JD 条件为招聘平台原文摘录；概率为主观估算，非统计数据。
"大四下无课"按一般教学计划推断，若实际有课需调整到暑期窗口。_
