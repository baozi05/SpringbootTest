````
端口
server.port=8080
````
### 在resource里需要配置数据库相关配置
#### 配置项目的名称
````
spring.application.name=MySpringboot
spring.jpa.open-in-view=false
````
#### 配置数据库的连接 URL
````
spring.datasource.url=jdbc:mysql://localhost:3306/springdata?serverTimezone=UTC
````
#### 数据库姓名
``spring.datasource.username=root``
#### 数据库密码
````
spring.datasource.password=bhr050219
````
#### 配置数据库连接的驱动类名称    MySql8.0 以上的 - JDBC 名称
````
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
````

#### 配置是否格式化打印 SQL
````
spring.jpa.properties.hibernate.format_sql=true
````
* true：格式化显示（更易阅读）。
* false：单行显示（紧凑）。

#### 配置是否显示 SQL 语句
````
spring.jpa.show-sql=true
````
#### true：控制台会打印 SQL 语句（调试方便）。
#### false：不打印 SQL 语句。

####  配置 JPA（Java Persistence API）自动生成数据库表结构的策略
````
spring.jpa.hibernate.ddl-auto=update
````
* none：不会自动创建或更新表结构。
* create：每次启动都会创建新表（会清空已有表数据）。
* create-drop：启动时创建表，关闭时删除表。
* update：启动时根据实体类更新表结构（不会删除已有数据）。
* validate：只验证表结构是否符合实体类定义，不会进行修改。

## HTTP协议中 REST的用法
>+ **@GetMapping**：处理 GET 请求。---**查询请求**
>+ **@PostMapping**：处理 POST 请求。---**新增**
>+ **@PutMapping**：处理 PUT 请求。    ---**修改**
>+ **@DeleteMapping**：处理 DELETE请求。---**删除**

## 实际开发中使用接口来接收实现类的原因
* 使用接口可以对代码实现**解耦合**
```java
// 若直接使用类来实现接口
public class AnimalService {
    private Dog dog = new Dog(); // 强依赖 Dog 类
    public void makeAnimalSound() {
        dog.makeSound();
    }
}
// 此时如果要将dog修改为cat，需要将所有Dog替换，因为这种方法是强耦合的
public class AnimalService {
    private Cat cat = new Cat(); // 改为强依赖 Cat 类
    public void makeAnimalSound() {
        cat.makeSound();
    }
}
```
但如果用接口来接收类
```java
    public class AnimalService {
        private IAnimal animal;
    
        public AnimalService(IAnimal animal) {
            this.animal = animal; // 依赖接口，而非具体类
        }
    
        public void makeAnimalSound() {
            animal.makeSound();
        }
}
// 此时若需要修改实现类，只需要传入需要替换的类，从而减少代码修改量
    public class Main {
        public static void main(String[] args) {
            AnimalService service = new AnimalService(new Dog());
            service.makeAnimalSound(); 
    
            service = new AnimalService(new Cat());
            service.makeAnimalSound(); 
        }
}
```
## JDBC（Java Database Connectivity）和JPA（Java Persistence API）都是用于在Java应用程序中与数据库进行交互的技术，但它们有不同的工作方式和应用场景。

>### 1. **JDBC (Java Database Connectivity)**

### JDBC 是 Java 中的低级数据库访问技术，它为开发者提供了与关系型数据库进行交互的基本方法。使用 JDBC 时，开发者需要自己管理数据库连接、SQL 语句的执行和结果集的处理。

**特点：**
- **低级操作**：开发者需要手动编写 SQL 查询，管理数据库连接，执行 SQL，并处理返回结果。
- **灵活性高**：因为是直接与数据库交互，开发者可以精确控制 SQL 语句、数据库连接等。
- **不具备 ORM**：JDBC 只是数据库操作的工具，并不提供对象关系映射功能，开发者需要手动处理对象和数据库表之间的转换。

**适用场景：**
- 需要细粒度控制数据库操作时。
- 自定义复杂的 SQL 查询和数据库操作时。

>### 2. **JPA (Java Persistence API)**

JPA 是 Java 提供的一个高级持久化框架，它是用于处理 Java 对象和关系数据库之间映射的标准 API。JPA 提供了对象关系映射（ORM）功能，使开发者可以更容易地在 Java 应用程序中操作数据库。

**特点：**
- **高层抽象**：JPA 通过实体类（Entity Classes）来表示数据库中的表，并提供了丰富的注解和配置方式，用于对象与数据库之间的映射。
- **自动化管理**：开发者只需要操作 Java 对象，而不需要直接写 SQL 查询，JPA 会自动生成 SQL 语句。
- **支持复杂查询**：JPA 提供了 JPQL（Java Persistence Query Language）用于对象的查询，支持像 SQL 一样的查询功能。
- **ORM 机制**：JPA 自动处理 Java 对象与数据库表之间的转换，避免了手动编写 SQL 和对象映射的繁琐。
- **与数据库无关**：JPA 可以支持不同的数据库，开发者可以更加专注于业务逻辑而不需要过多关注数据库的细节。

**适用场景：**
- 当应用程序需要与数据库进行持久化存储时。
- 当你需要利用 ORM 特性（如自动生成 SQL、管理对象与数据库的映射）来简化开发。
- 当你希望减少数据库操作的复杂度和代码量时。

### 总结对比：

| 特性         | JDBC                 | JPA                 |
|------------|----------------------|---------------------|
| **抽象层级**   | 低级，直接操作数据库           | 高级，基于 ORM 的对象关系映射   |
| **SQL 处理** | 需要手动编写 SQL           | 自动生成 SQL            |
| **灵活性**    | 高，完全控制 SQL 执行        | 低，依赖 JPA 提供的功能      |
| **学习曲线**   | 较陡峭，需要手动处理数据库细节      | 较平缓，使用实体类和注解来简化开发   |
| **开发效率**   | 较低，代码量多，容易出错         | 较高，简化了很多数据库操作       |
| **性能**     | 较高（直接控制 SQL 执行和连接管理） | 性能相对稍低（由于 ORM 层的开销） |
| **适用场景**   | 精细控制数据库操作和 SQL 查询    | 简化 CRUD 操作和持久化管理    |

**总结：**
- 如果你需要更细致的控制，并且不介意手动管理 SQL 和对象映射，可以使用 **JDBC**。
- 如果你希望简化开发过程，减少与数据库直接交互的复杂性，可以使用 **JPA**，尤其适合用于大型应用的持久化层。



## JPA只需创建一个接口来继承Crudrepository的原因
当定义一个继承自 **CrudRepository** 或 **JpaRepository** 的接口时，Spring 会在运行时通过动态代理（Java 的 Proxy 或基于 CGLIB 的代理）自动生成该接口的具体实现类。这意味着你不需要手动编写实现类。

#### 优点：
* 减少了样板代码（boilerplate code）。
* 快速开发，专注于业务逻辑而不是数据访问层的实现细节。
* Spring 根据方法的命名约定（例如 findByName）解析并生成对应的查询

#### 内置常用操作支持
**CrudRepository** 接口中已经提供了很多常见的操作方法，例如：

* save()：保存或更新实体。
* findById()：根据主键查找实体。
* deleteById()：根据主键删除实体。
* findAll()：获取所有记录。

### **CRUD** 是数据库操作中的一个常见术语，代表四种基本操作：**Create（创建）**、**Read（读取）**、**Update（更新）** 和 **Delete（删除）**。它们是对数据进行操作的核心功能，在开发任何涉及数据存储的系统时都会用到。

以下是对 CRUD 各部分的详细解释：

---

### 1. **Create（创建）**
用于在数据库中插入新记录。

- **功能**：向数据库表中添加新的数据行。
- **对应的 SQL 操作**：`INSERT`
- **示例 SQL 语句**：
  ```sql
  INSERT INTO students (name, age) VALUES ('Alice', 20);
  ```
- **场景**：用户注册新账号，添加新产品到库存等。

---

### 2. **Read（读取）**
用于从数据库中查询数据。

- **功能**：从数据库中检索数据，可能是特定记录或满足条件的一组记录。
- **对应的 SQL 操作**：`SELECT`
- **示例 SQL 语句**：
  ```sql
  SELECT * FROM students WHERE age > 18;
  ```
- **场景**：用户查看自己的个人信息，获取所有订单记录等。

---

### 3. **Update（更新）**
用于修改数据库中已存在的数据。

- **功能**：更改现有数据的内容。
- **对应的 SQL 操作**：`UPDATE`
- **示例 SQL 语句**：
  ```sql
  UPDATE students SET age = 21 WHERE name = 'Alice';
  ```
- **场景**：修改用户个人信息，更新订单状态等。

---

### 4. **Delete（删除）**
用于从数据库中移除数据。

- **功能**：删除一条或多条数据记录。
- **对应的 SQL 操作**：`DELETE`
- **示例 SQL 语句**：
  ```sql
  DELETE FROM students WHERE name = 'Alice';
  ```
- **场景**：用户注销账户，删除历史订单记录等。

---

### **CRUD 的实际应用**
在开发中，CRUD 操作广泛应用于各种系统，比如：
- **用户管理系统**：注册新用户（Create），查看用户信息（Read），更新用户信息（Update），删除用户账户（Delete）。
- **博客系统**：发布文章（Create），查看文章列表（Read），修改文章（Update），删除文章（Delete）。
- **电商系统**：添加新商品（Create），查询商品详情（Read），更新库存信息（Update），删除下架商品（Delete）。

---

### **CRUD 在 Web 开发中的实现**
- 在 Web 开发中，CRUD 通常通过 RESTful API 实现：
    - **Create**：HTTP 方法 `POST`
    - **Read**：HTTP 方法 `GET`
    - **Update**：HTTP 方法 `PUT` 或 `PATCH`
    - **Delete**：HTTP 方法 `DELETE`

**示例：**
- `POST /api/users` 用于创建新用户。
- `GET /api/users/{id}` 用于获取某个用户的信息。
- `PUT /api/users/{id}` 用于更新用户信息。
- `DELETE /api/users/{id}` 用于删除用户。

---

### **CRUD 与框架**
在 Spring Boot、Django 等框架中，CRUD 操作通常由 **ORM（对象关系映射）** 工具简化。例如：
- 在 **Spring Data JPA** 中，`JpaRepository` 提供了默认的 CRUD 方法，例如：
    - `save()`：用于创建或更新。
    - `findById()`：用于读取。
    - `delete()`：用于删除。

**示例代码：**
```java
// 保存或更新
Student student = new Student("Alice", 20);
studentRepository.save(student);

// 读取
Optional<Student> result = studentRepository.findById(1L);

// 删除
studentRepository.delete(student);
```

---

### **总结**
CRUD 是 **Create, Read, Update, Delete** 的缩写  
CRUD 是数据库操作的基础，贯穿了几乎所有的软件开发项目。无论是简单的用户管理系统，还是复杂的大型应用系统，CRUD 操作都是数据交互中不可或缺的一部分。

### Controller Service Repository 三层架构的作用
1. #### Controller 层（控制层）
>**作用**：  
处理来自客户端的请求（如 HTTP 请求）。
调用 Service 层处理具体的业务逻辑。
将处理结果返回给客户端（如 JSON、HTML）。

>**职责**：  
作为系统的入口点。
解析请求参数并进行基础校验。
不包含复杂的业务逻辑，只负责调度。

>**常见内容**：
使用框架的注解（如 Spring 中的 @RestController 或 @Controller）。
HTTP 方法映射（如 @GetMapping、@PostMapping 等）。

2. #### Service 层（业务逻辑层）
>**作用**：  
承担系统的业务逻辑，处理业务规则。
调用 Repository 层与数据库交互。
可能需要处理事务管理。
 
>**职责**：  
业务逻辑的核心部分。
组织多个 Repository 层的调用。
确保业务逻辑的一致性。

>**常见内容**：
使用 @Service 注解。
定义与业务相关的接口和实现类。
3. ### Repository 层（数据访问层）
>作用：
与数据库进行直接交互。
执行增删改查（CRUD）操作。

>职责：
通过 ORM 框架（如 Hibernate、JPA）或 SQL 执行数据库操作。
仅负责数据的存储和检索，不包含业务逻辑。

>常见内容：
使用 @Repository 注解。
定义接口（如 JPA Repository）或实现类。

## 注意：
在实体类内的 Setter和 Getter方法一定要与实体类的属性相对应
如属性是 `userName`,那么Setter和Getter方法必须为 `getuserName`和 `setuserName`,否则会出现在添加到数据库时Spring 在反序列化 JSON 数据时无法正确设置 userName 字段的值。
### 每次测试完后，可以使用`TRUNCATE TABLE tb_user`来清除测试插入的数据，并将主键重置为1

### `@NotNull`、`@NotBlank` 和 `@NotEmpty`三者区别
在Java Bean Validation中，`@NotNull`、`@NotBlank` 和 `@NotEmpty` 都是用于验证属性不为空的注解，但它们之间有一些细微的区别，主要体现在验证的对象类型和空值的定义上。

### 1. **@NotNull**
- **作用**：验证该字段的值不能为 `null`。
- **适用范围**：适用于任何类型的对象，包括字符串、数字、集合等。
- **判断标准**：仅验证是否为 `null`，不管对象是否为空（例如空字符串、空集合等都被认为是有效的）。

**示例**：
```java
@NotNull(message = "Name cannot be null")
private String name;
```
- 如果 `name` 为 `null`，则验证失败。
- 如果 `name` 是一个空字符串（""），验证通过。

### 2. **@NotEmpty**
- **作用**：验证该字段不能为 `null` 且不能为空（对于字符串，不能为空字符串；对于集合，不能为空集合）。
- **适用范围**：适用于字符串、集合、数组等类型。
- **判断标准**：检查字段是否为 `null` 或空字符串（对字符串）/空集合（对集合）/空数组（对数组）。

**示例**：
```java
@NotEmpty(message = "Name cannot be empty")
private String name;
```
- 如果 `name` 为 `null` 或空字符串（""），则验证失败。
- 如果 `name` 为 `" "`（包含空格的字符串），则验证通过（空格不是空字符串）。

### 3. **@NotBlank**
- **作用**：验证该字段不能为 `null`，且不能为空白（空字符串或仅包含空白字符，如空格）。
- **适用范围**：通常用于字符串类型。
- **判断标准**：检查字段是否为 `null` 或为空白字符（空字符串、只包含空格的字符串等）。

**示例**：
```java
@NotBlank(message = "Name cannot be blank")
private String name;
```
- 如果 `name` 为 `null`、空字符串（""）或仅包含空白字符（如 `" "` 或 `"\t"`），则验证失败。
- 如果 `name` 为 `"John"`，则验证通过。

### 总结对比：
| 注解            | 适用类型      | 验证规则                              |
|---------------|-----------|-----------------------------------|
| **@NotNull**  | 所有对象类型    | 仅验证是否为 `null`，不验证空字符串或空集合等        |
| **@NotEmpty** | 字符串、集合、数组 | 验证是否为 `null` 或空字符串/空集合/空数组        |
| **@NotBlank** | 字符串类型     | 验证是否为 `null` 或空字符串/空白字符串（如 `" "`） |

### 何时使用：
- **@NotNull**：用于字段的非空检查，尤其适用于引用类型（如对象、数字等），即要求字段不能为空，但允许字段为“空字符串”或“空集合”。
- **@NotEmpty**：适用于集合或字符串类型，要求字段不仅不能为空，还必须包含至少一个元素或字符。
- **@NotBlank**：特别适用于字符串，要求字段不能为 `null` 且不能是空白字符串。

选择使用哪个注解，取决于你想验证的具体需求。
### 在规范完注解后需要再需要验证参数的地方加上`valuedated` 这样的注解
### 每次接口测试完后，使用 `TRUNCATE TABLE tb_user` 来重置数据库主键以及清空测试内容

## Java开发里 _DAO_、_POJO_、_DTO_ 等类的区别
在 Java 开发中，**POJO**、**DTO** 和 **DAO** 是常见的三种类类型，主要用于不同的应用开发层次或功能模块中。以下是它们的定义和用途：

---

### **1. POJO（Plain Old Java Object）**
- **定义**：POJO 是普通的 Java 对象，不依赖于任何框架或库，通常是用来表示业务实体的类。
- **特性**：
  - 只有属性（字段）和 getter/setter 方法。
  - 不包含复杂的业务逻辑或依赖框架的注解。
  - 类通常是序列化的（实现 `Serializable` 接口）。
- **用途**：用来表示数据库中的表或业务逻辑中的实体。例如：`User`、`Product`。

```java
public class User {
    private int id;
    private String name;
    private String email;

    // Constructors
    public User() {}

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

---

### **2. DTO（Data Transfer Object）**
- **定义**：DTO 是数据传输对象，用于在不同系统、模块或层之间传输数据。它是一种特殊的 POJO。
- **特性**：
  - 通常不包含业务逻辑，只用来承载数据。
  - 可以对数据进行裁剪，仅保留需要的字段（避免传递敏感或冗余数据）。
- **用途**：
  - 在服务层和控制层之间传递数据。
  - 简化数据传输，减少系统耦合。

```java
public class UserDTO {
    private String name;
    private String email;

    // Constructors
    public UserDTO() {}

    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

**区别**：  
POJO 可能包含所有数据库字段，DTO 只包含当前需要传输的字段。

---

### **3. DAO（Data Access Object）**
- **定义**：DAO 是数据访问对象，用于封装对数据库的操作逻辑。
- **特性**：
  - 提供 CRUD（Create, Read, Update, Delete）操作的接口。
  - 和数据库交互，通常与 ORM 框架（如 MyBatis 或 Hibernate）一起使用。
- **用途**：
  - 将数据库操作逻辑与业务逻辑分离。
  - 提高代码的可维护性和测试性。

```java
public interface UserDAO {
    // Create
    void insertUser(User user);

    // Read
    User getUserById(int id);

    // Update
    void updateUser(User user);

    // Delete
    void deleteUser(int id);
}
```

**实现类**（结合 MyBatis 示例）：
```java
@Mapper
public interface UserDAO {
    @Insert("INSERT INTO users (id, name, email) VALUES (#{id}, #{name}, #{email})")
    void insertUser(User user);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User getUserById(int id);

    @Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(int id);
}
```

---

### **总结**
1. **POJO**：用来表示数据实体，主要用在数据模型层。
2. **DTO**：用来传递数据，主要用在服务层和控制层之间。
3. **DAO**：封装数据库操作，主要用在数据访问层。

三者各司其职，共同构建清晰分层的代码架构，有助于提高项目的可维护性和扩展性。

### ORM
> 什么是ORM？(**Object Relation Mapping**)
> 
> 对象关系映射。对象指的是Java对象，关系指的是数据库中的关系模型，对象关系映射，指的就是在Java对象和数据库的关系模型之间建立一种对应关系，比如用一个Java的Student类，去对应数据库中的一张student表，类中的属性和表中的列一一对应。Student类就对应student表，一个Student对象就对应student表中的一行数据

Spring Boot 中的 JPA（Java Persistence API）和 MyBatis 的作用确实有相似之处——它们都用于操作数据库，但它们在设计理念、实现方式和使用场景上有显著区别。以下是对两者的对比和分析：

---

### **1. JPA 与 MyBatis 的相似之处**
1. **功能定位**
  - 都是 Java 应用中操作数据库的持久化框架。
  - 都可以通过 Spring Boot 集成，简化配置和开发流程。
2. **基本功能**
  - 支持数据库 CRUD 操作。
  - 提供与关系型数据库的交互机制。
3. **与 Spring 的关系**
  - Spring Data JPA 是 Spring 官方对 JPA 的封装，与 Spring Boot 无缝集成。
  - MyBatis 也可以通过 `mybatis-spring-boot-starter` 集成到 Spring Boot 项目中。

---

### **2. JPA 与 MyBatis 的区别**

#### **1. 编程模型**
- **JPA（基于 ORM 模型）**
  - JPA 是一种 **全自动对象关系映射（ORM）** 框架。
  - 将数据库表直接映射为 Java 对象，开发者无需写 SQL，只需操作实体类和接口，JPA 会自动生成 SQL。
  - 适合对数据库表与对象模型关系高度一致的项目。
  - 示例：
    ```java
    @Entity
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private Integer age;
        }
    ```

- **MyBatis（基于 SQL 模型）**
  - MyBatis 不隐藏 SQL，开发者需要自己编写 SQL 语句并配置映射规则。
  - 提供灵活性，适合复杂查询和高性能优化场景。
  - 示例：
    ```xml
    <select id="getUserById" resultType="User">
        SELECT * FROM user WHERE id = #{id}
    </select>
    ```

#### **2. 开发模式**
- **JPA**：
  - **声明式编程**：通过注解或接口声明的方式操作数据。
  - 示例：
    ```java
    public interface UserRepository extends JpaRepository<User, Long> {
        User findByName(String name);
    }
    ```
  - 无需写 SQL，更多依赖框架自动生成语句。

- **MyBatis**：
  - **手写 SQL**：开发者完全控制 SQL，编写动态 SQL 或使用注解定义 SQL。
  - 示例：
    ```java
    @Select("SELECT * FROM user WHERE name = #{name}")
    User findByName(@Param("name") String name);
    ```

#### **3. 灵活性**
- **JPA**
  - 自动化程度高，但对复杂 SQL（如多表联查、动态条件查询）支持相对较弱。
  - 可扩展性依赖 HQL（Hibernate Query Language）或 Criteria API，但语法复杂。
- **MyBatis**
  - 手动编写 SQL，非常灵活，能够充分利用数据库的特性。
  - 对性能调优、复杂查询支持更好。

#### **4. 性能**
- **JPA**
  - 在简单场景中性能较好，但自动生成 SQL 可能不够高效。
  - 默认加载策略（如懒加载）需要开发者谨慎处理，避免性能问题。
- **MyBatis**
  - 由于 SQL 由开发者手动编写，可以精细控制性能，但增加了开发复杂度。

#### **5. 学习曲线**
- **JPA**
  - 自动化程度高，入门简单，但深入掌握（如关联关系、缓存机制等）有一定难度。
- **MyBatis**
  - 初学时需要理解 SQL 和映射规则，学习曲线略陡。

---

### **3. 适用场景**
| **框架**  | **适用场景**                                                                                     |
|-----------|------------------------------------------------------------------------------------------------|
| **JPA**   | - 数据表与实体类一一对应，关系模型简单。<br>- 项目以 CRUD 为主，复杂 SQL 需求较少。<br>- 更关注开发效率而非性能。|
| **MyBatis** | - 需要灵活编写复杂 SQL，或依赖数据库特定功能（如存储过程、视图）。<br>- 数据库模型复杂，实体与表结构不完全一致。<br>- 需要手动优化 SQL 性能。 |

---

### **4. 小结**
Spring Boot 中的 JPA 和 MyBatis 都可以用来操作数据库：
- 如果你的项目追求开发效率、表结构简单，可以选择 **JPA**。
- 如果项目需要对数据库操作有更高的控制力、复杂 SQL 较多，**MyBatis** 会更合适。

两者并非互相排斥，**在某些项目中可以结合使用**，例如：用 JPA 处理简单 CRUD，用 MyBatis 处理复杂查询。选择合适的工具，才能更好地满足项目需求。