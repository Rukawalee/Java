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
* getDatabaseProductName()：获得数据库产品名
* getDatabaseProductVersion()：获得数据库版本号
* getDriverName()：获得驱动程序名
* getDriverVersion()：获得驱动程序版本号