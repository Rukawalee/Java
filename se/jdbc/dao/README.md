# DAO
数据访问对象（Data Access Object）
* 实现功能模块化
* 可以被子类继承或直接使用
* 包含CRUD(create, read, update, delete)

### Dao [链入](./1_dao)
* 适用于 INSERT, UPDATE, DELETE 操作
    * `void update(String, Object[])`
* 适用于 SELECT 操作
    * `<T> T queryOne(Class<T>, String, Object[])`：返回实例对象
    * `<T> List<T> queryList<Class<T>, String, Object[])`：返回实例对象列表
    * `<E> E queryValue(String, Object[])`：返回一个元素值

### BeanUtils [链入](./2_beanUtils)
操作JavaBean属性的工具包类
* 通过`BeanUtils#setProperty(Object, String, Object)`为设置属性
* 通过`BeanUtils#getProperty(Object, String)`获取属性
* 环境搭建：
    1. 导入`commons-beanutils`的jar包
    2. 导入`commons-logging`的jar包

### DatabaseMetaData（了解） [链入](./3_databaseMetaData)
能够获得有关数据库管理系统的各种信息，通过Connection#getMetaData()得到
* getURL()：获得数据库的URL
* getUserName()：获得当前用户名
* isReadOnly()：数据库是否只读
* getDriverName()：获得驱动程序名
* getDriverVersion()：获得驱动程序版本号
* getDatabaseProductName()：获得数据库产品名
* getDatabaseProductVersion()：获得数据库版本号

### LOB [链入](./4_lob)
Large Objects（大对象），用来存储大量的二进制和文本数据的一种类型
* BLOB(二进制数据)：适用于存储大量的二进制数据
* CLOB(单字符数据)：适用于存储超长的文本数据
* NCLOB(多字符数据)：适用于存储超长的文本数据
* 注：
    1. Blob#getBinaryStream()：获取输入流
    2. 存储文件过大会导致数据库的性能下降
    3. 只能用`PreparedStatement#setBlob(index, InputStream)`操作

### JDBC事务处理 [链入](./5_transaction)
要么操作顺利提交（commit），要么出现异常回滚（rollback）到初试状态
* 事务执行流程：
    1. `Connection#setAutoCommit(false)`：取消自动提交
    2. 所有SQL执行成功后`Connection#commit()`：提交事务
    3. 出现异常后在catch块中`Connection#rollback()`：回滚事务
    4. 恢复Connetion自动提交
* 注：
    1. 必须使用同一个Connection保证事务

### 数据库连接池 [链入](./6_dataSource)
数据源javax.sql.DataSource接口表示数据库连接池
* DBCP 连接池创建：
    1. 新建`BasicDataSource`类对象，通过setter传参
    2. 通过`BasicDataSourceFactory.createDataSource(Properties)`传入Properties配置文件
* C3P0 连接池创建：
    1. 新建`ComboPooledDataSource`类对象，通过setter传参
    2. 通过`ComboPooledDataSource(String)`构造器，传入XML配置文件

### DBUtils [链入](./7_dbutils)
Apache提供的一个开源对JDBC简单封装的工具类
* QueryRunner
    1. `query(Connection, String, ResultSetHandler, Object[])`
        * 返回结果主要由ResultSetHandler的`覆写handle(ResultSet)方法`决定
    2. `update(Connection, String, Object)`
        * 执行INSERT, UPDATE, DELETE操作
* ResultSetHandler
    1. `BeanHandler(Class)`：将结果集第一条记录转为Class实体
    2. `BeanListHandler(Class)`：将结果集转为Class实体的集合
    3. `MapHandler()`：将结果集第一条记录转为Map(列名:列值)键值对
    4. `MapListHandler()`：将结果集转为Map(列名:列值)键值对的集合
    5. `ScalarHandler()`：将结果集第一条记录的第一列值返回

### DAO设计 [链入](./8_dao)
较完整的DAO设计模式
* DAO接口
    * JDBCDAOImpl实现类
        * CustomerDao类

### JDBC调用存储过程
通过`Connection#prepareCall(String)`创建一个`CallableStatement`实例
* 通过`CallableStatement#regitsterOutParameter`注册 OUT 参数
* 通过`CallableStatement#setXxx()`设定 IN 或 IN OUT 参数
* 通过`CallableStatement#setNull()`设定 null 值
* 通过`CallableStatement#execute()`执行存储过程
* 通过`CallableStatement#getXxx()`获取返回值
* 注：
    1. CallableStatement是Statement子接口
	1. 调用函数
        * 字符串开头`?=`
	2. 调用存储过程
        * 字符串开头不是`?=`
    3. 查看存储过程或函数定义
        SELECT text FROM '库名' WHERE LOWER(name) = '名字'