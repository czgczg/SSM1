总结
旅客住宿登记的安排房间模块功能基本完成，
但没有使用三层架构解耦，
controller层代码仍需优化。

优化建议写个前端适配器
不然每次都需要和前端数据类型转换
Adapter来调节数据类型不一致问题
减少Controller层负担


数据库建表很重要
有前端代码时，
优先考虑看懂前端所需数据名称和属性
（包含请求参数与限制条件）
根据它来确定表中类型元素

是否外键？
逻辑外键
（优点：比物理外键性能高，）
（缺点：需要手动查询多张表，
        要求对数据表间关系清楚，）

是否删除？
逻辑删除
（优点：易于恢复数据，
    删除时不会引发含外键的表删除异常）
（缺点，数据冗余，
    其他操作时忘记flag可能会引发数据混乱）


数据库建表后若使用逆向工程，
则尽量少改数据库，可以改动前端代码

知识点：
Json
Ajax
ssm框架注解
分页
数据库增删改查

抓包工具分析
请求参数（转发带参，重定向重新请求）
页面转发关系，前端页面搜索
页面显示，断点调试



bug
启动404 500
检查配置文件 Spring applicationContext
web.xml
tomcat版本
最好删除再重新加载


解决org.apache.ibatis.binding.BindingException:
 Invalid bound statement (not found)错误
 在使用IDEA开发时，
 如果打包时*Mapper.xml没有自动复制到class输出目录的mapper类包下，
 则需要在pom文件中添加mybatis加载配置文件的配置！ 
 编译时xml也要和mapper一起
 
 
 