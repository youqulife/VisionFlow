# 有趣生活软件-视觉健康管理系统

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Java](https://img.shields.io/badge/Java-17-brightgreen)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.6-brightgreen)](https://spring.io/projects/spring-boot)

VisionFlow - 让每一次视力关怀，都清晰可循。

## 项目介绍

VisionFlow 是一套专为眼镜验光配镜行业打造的客户关系管理(CRM)系统，旨在为视力健康服务提供者提供垂直领域的 SaaS 解决方案。本系统基于现代化的 Java 技术栈构建，集成了验光、配镜、客户管理等核心功能，帮助眼镜店、眼科诊所等机构高效管理客户信息、验光记录、配镜方案等业务流程。

### 核心功能

- **验光管理**: 完整记录客户的验光数据，包括球镜、柱镜、轴位、瞳距等专业参数
- **配镜管理**: 管理客户的配镜方案、镜片选择、镜架款式等信息
- **客户关系管理**: 维护客户档案，跟踪客户历史记录，提供客户回访提醒
- **库存管理**: 管理镜片、镜架等商品库存
- **订单管理**: 处理配镜订单，跟踪订单状态
- **数据统计**: 提供业务数据统计分析功能

### 技术架构

- **后端框架**: Java 17 + Spring Boot 3 + Spring Security
- **持久层**: MyBatis-Plus + MySQL 8.0
- **缓存**: Redis + Redisson (分布式锁)
- **接口文档**: Knife4j (OpenAPI 3)
- **定时任务**: XXL-JOB
- **对象映射**: MapStruct
- **工具类库**: Hutool
- **代码生成**: MyBatis-Plus Generator + Velocity
- **数据库迁移工具**: Flyway

## 系统特性

- 基于 Spring Security 的 RBAC 权限控制
- 支持多环境配置 (local, dev, prod)
- 集成 Redis 缓存提升系统性能
- 支持分布式锁防止并发冲突
- 内置代码生成器，提高开发效率
- 提供完整的 RESTful API 接口
- 集成 XXL-JOB 分布式任务调度
- 支持 MinIO 和阿里云 OSS 对象存储

## 快速开始

### 环境要求

- JDK 17
- MySQL 8.0
- Redis
- Maven 3.6+

### 安装步骤

1. 克隆项目到本地
   ```bash
   git clone https://github.com/youqulife/VisionFlow.git
   ```

2. 导入数据库脚本
   ```bash
   mysql -u root -p < sql/visionflow_boot.sql
   ```

3. 修改配置文件
   根据实际环境修改 `src/main/resources/application-dev.yml` 中的数据库、Redis 等配置

4. 编译和运行
   ```bash
   mvn clean package -DskipTests
   mvn spring-boot:run
   ```

### 访问地址

- API 文档: http://localhost:8080/doc.html
- 管理后台: http://localhost:8080 (待开发)

## 项目结构

```
src
├── main
│   ├── java
│   │   └── com.youqusoft.vision.flow
│   │       ├── common        # 通用模块
│   │       ├── config        # 配置模块
│   │       ├── core          # 核心模块
│   │       ├── modules       # 业务模块
│   │       │   ├── member    # 会员模块
│   │       │   ├── order     # 订单模块
│   │       │   └── product   # 商品模块
│   │       └── system        # 系统模块
│   └── resources             # 资源文件
└── test                      # 测试模块
```

## 开发注意事项
### 数据库脚本迁移
- 数据库脚本迁移框架：Flyway
- 脚本存放位置：src/main/resources/db/migration
- 脚本命名规则：V{version}_{description}.sql (V必须为大写)

## 贡献指南

我们欢迎任何形式的贡献，包括但不限于：

1. 提交 Issue 报告 Bug 或建议新功能
2. Fork 项目并提交 Pull Request
3. 改进文档和示例代码
4. 参与社区讨论和问题解答

### 开发规范

- 遵循项目现有的代码风格和架构设计
- 新功能需提供相应的单元测试
- 提交前请确保通过所有测试用例
- Commit 信息需清晰描述变更内容

## 许可证

本项目基于 GPL 3.0 开源协议，详情请查看 [LICENSE](LICENSE) 文件。

## 联系我们

- 项目官网: (待完善)
- 技术支持: (待完善)
- 商务合作: (待完善)

## 致谢

本项目基于 youlai-boot 开源项目进行二次开发，在此感谢原作者的贡献。