# OSCMS Common Library

OSCMS Common Library 是 OSC 课程管理系统的共享工具库，包含了所有微服务共用的工具类、异常定义、响应模型等。

## 功能特性

- **JWT 工具类** - 提供 JWT token 的生成、验证和解析
- **统一异常处理** - 定义了系统中使用的各种业务异常
- **API 响应模型** - 统一的 API 响应格式
- **安全配置** - Spring Security 相关的配置和工具

## 项目结构

```
src/main/java/com/osc/oscms/common/
├── exception/           # 异常定义
│   ├── AuthException/   # 认证相关异常
│   ├── CourseException/ # 课程相关异常
│   ├── SubmissionException/ # 作业提交相关异常
│   └── UsersManageException/ # 用户管理相关异常
├── response/            # API 响应模型
└── util/                # 工具类
    └── JwtUtil.java     # JWT 工具类
```

## 构建和发布

### 本地构建

```bash
mvn clean compile
```

### 安装到本地 Maven 仓库

```bash
mvn clean install
```

### 发布到远程仓库

```bash
mvn clean deploy
```

## 使用方式

在其他微服务项目中添加依赖：

```xml
<dependency>
    <groupId>com.osc</groupId>
    <artifactId>oscms-common</artifactId>
    <version>1.0.1-SNAPSHOT</version>
</dependency>
```

## 主要组件

### JwtUtil

JWT token 处理工具类，提供以下功能：

- `generateToken(UserDetails userDetails, String actualUsername)` - 生成 JWT token
- `validateToken(String token)` - 验证 token 有效性
- `getUserIdFromToken(String token)` - 从 token 中提取用户 ID
- `getUsernameFromToken(String token)` - 从 token 中提取用户名
- `getRolesFromToken(String token)` - 从 token 中提取角色信息

### ApiResponse

统一的 API 响应格式：

```java
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String timestamp;
    // ...
}
```

### 异常类

- `UserNotFoundException` - 用户未找到异常
- `InvalidRoleException` - 无效角色异常
- `UsernameAlreadyExistsException` - 用户名已存在异常
- 其他业务异常...

## 配置要求

使用此库的服务需要在 `application.yml` 中配置：

```yaml
jwt:
  secret: ${JWT_SECRET:YourSuperSecretKeyForJWTGenerationWhichIsVeryLongAndSecure12345!}
  expiration:
    ms: ${JWT_EXPIRATION_MS:3600000} # 1小时
```

## 依赖要求

- Java 17+
- Spring Boot 3.4.5
- Spring Security 6.x
- com.auth0:java-jwt:4.4.0

## 版本历史

- **1.0.1-SNAPSHOT** - 当前开发版本
  - 支持 JWT token 生成和验证
  - 统一异常处理
  - API 响应模型

## 许可证

本项目采用 MIT 许可证。