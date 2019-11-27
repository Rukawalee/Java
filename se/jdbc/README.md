# JDBC
Java数据库连接（Java Database Connectivity）

### Driver（了解）
* Java程序用于数据库连接驱动的接口
* 通过`Driver#connect(String, Properties)`方法获取Connection
* 注：Properties只能放入的键名：`"user", "password"`

### DriverManager（掌握）
* Java程序用于管理数据库连接驱动的类
* 通过`DriverManager#getConnection(String, String, String)`获取Connection
* 注：DriverManager通过`Class.forName(String)`加载对应数据库驱动

### DriverInfo（了解）
* 存储数据库驱动信息的实体类

### SQL [链入](0_sql)
* 存放当前项目数据库所需初始化SQL命令

### Connection [链入](1_connection)
* 数据库连接接口
* 通过`Connection#createSatement()`获取SQL执行器
* 通过`Connection#prepareStatement(String)`获取SQL预执行器
* 通过`Connection#.prepareStatement(String, int)`获取主键ResultSet
* 通过`Connection#getMetaData()`获取DatabaseMetaData数据库元信息
* 注：
    1. 执行器**直接替换SQL关键字**，不能有效预防SQL注入
    2. 预执行器**预加载SQL替换占位符**，能有效预防SQL注入
    3. **数据库连接资源，使用后需要关闭**

### Statement [链入](2_statement)
* SQL执行器接口
* 通过`Statement#executeQuery(String)`向数据库发送SQL，返回查询结果集ResultSet
* 通过`Statement#executeUpdate(String)`向数据库发送SQL，返回修改数
* 注：
    1. `executeQuery(String)`只适用于SELECT
    2. `executeUpdate(String)`仅适用于`UPDATE, INSERT, DELETE`
    3. **数据库连接资源，使用后需要关闭**

### Utils [链入](3_utils)
* 数据库工具类
    1. `JdbcUtil#getConnection()`，通过配置文件获取Connection
    2. `JdbcUtil#release(Connetion, Statement)`，释放数据库连接资源
    3. `JdbcUtil#release(Connetion, Statement, ResultSet)`，释放数据库连接资源

### ResultSet [链入](4_resultSet)
* 数据库查询结果集接口
* 结果集列索引从1开始
* 通过`ResultSet#next()`向下移动指针获取记录，有记录返回true否者返回false
* 通过`ResultSet#getXxx([index|column])`方法获取列内容
* 通过`ResultSet#getMetaData()`得到ResultSetMetaData对象
* 注：
    1. 结果集对象默认指向第一条记录前一行
    2. `ResultSet#next()`先判断是否有下一行，然后移动到下一条记录
    3. `ResultSet#getXxx([index|column])`常用`getString()`和`getObject()`

### PreparedStatement [链入](5_preparedStatement)
* SQL预加载执行器，是Statement子接口
* 数据库缓存SQL，遇到相同SQL不再编译直接传入参数执行
* 传入带占位符的SQL语句，提供了补充占位符的方法
* 通过`setXxx(index, val)`方法替换占位符
* 注：
    1. 预加载SQL，能有效预防SQL注入
    2. 提高SQL可读性和可维护性，提高性能
    3. 使用不带参数的`executeXxx()`方法
    4. 避免冗长的SQL拼写出错

### SQL注入（了解）
* 是利用某些系统没有对用户输入的数据进行充分检查
* 用户在数据中输入非法的SQL语句或命令

### ResultSetMetaData [链入](6_resultSetMetaData)
* 描述RsultSet的元数据对象，获取列名..
* 通过`ResultSetMetaData#getColumnCount()`获取列数
* 通过`ResultSetMetaData#getColumnLabel(index)`获取索引列别名
* getColumnName(index)：获得指定列名
* getColumnTypeName(index)：获得类型名
* getColumnDisplaySize(index)：获得指定列最大标准宽度
* isNullable(index)：指定列是否可为null
* isAutoIncrement(index)：指定列是否自增
* 设计通用的查询方法：需要综合使用反射
    1. SQL查询结果集ResultSet
    2. `ResulteSet#getMetaData()`
    3. 获取结果集列名和值，生成一个Map
    4. `clazz#newInstance()`反射创建实体类对象
    5. `Method#invoke(Object, args[])`反射为实体的属性
* 注：
    1. index从1开始
    2. **Customer**是数据表映射类

### 注意事项
* 数据库资源非常宝贵需要在使用后关闭
* 关闭数据库资源前可能会出现异常，所以将关闭操作放入到finally中
* 关闭数据库资源时可能会出现异常，所以将资源对象设置为null

# [DAO](./dao)
数据访问对象（Data Access Object）