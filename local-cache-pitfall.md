# Local Cache陷阱

相关文档：[mybatis configuration][mybatis configuration]，[mybatis mapper xml][mybatis mapper xml]

相关代码：[src/main/java/localcache](src/main/java/me/chanjar/mybatislearn/localcache)，[src/test/java/localcache/service](src/test/java/me/chanjar/mybatislearn/localcache/service)

最近发现一个问题，当使用Spring Transaction的时候，发现对同一个sequence多次调用nextval返回的结果相同。经过一番搜索发现此问题和mybatis local cache有关。

在继续探讨这个问题之前，先搞清楚mybatis的cache类型：

## local cache和cache

mybatis提供了两种cache类型：local cache和cache

* local cache，也就是所谓的局部缓存。由以下参数控制：
  * ``localCacheScope``，见[相关文档][mybatis configuration]
* cache，也就是所谓的二级缓存。由以下参数控制：
  * ``cacheEnabled``，见[相关文档][mybatis configuration]
  * ``cache``，[相关文档][mybatis mapper xml]
  * ``cache-ref``，[相关文档][mybatis mapper xml]
  * ``useCache``，[相关文档][mybatis mapper xml]
  * ``flushCache``，[相关文档][mybatis mapper xml]

要特别注意的是，mybatis的local cache是无法关闭的。

那么local cache干了什么？在默认配置情况下，mybatis会将同一session内的查询结果都放在local cache中，这样可以提高性能，避免每次都hit到数据库。

那么cache干了什么呢？和local cache相对的，cache是跨session的，也就是说这个session中缓存的结果，在另外一个session中也能够用到。

## 问题分析

前面已经讲到了在同一session中的查询会将结果缓存，那么这个和我们一开始提到的问题有什么关系呢？聪明的你一定已经想到了，这个问题和启用了事务有关。

实际上mybatis在和spring集成后，会自动将session绑定到事务上，那么就会产生前面提到的问题。

## 解决办法

有以下几种解决办法：

1. 在mybatis配置文件中``localCacheScope=STATEMENT``。
2. 在mapper配置文件中，给select设置``flushCache=true``。需要注意的是，这样会将local cache和cache都清空掉。
3. 不用事务

## 相关代码

1. [NativeTest](src/test/java/localcache/service/NativeTest.java)：说明在使用SqlSession时，local cache的作用
2. [NoTxTest](src/test/java/localcache/service/NoTxTest.java)：说明在无事务时，local cache的作用
3. [TxTest](src/test/java/localcache/service/TxTest.java)：说明在有事务时，local cache的作用

[mybatis configuration]: http://www.mybatis.org/mybatis-3/configuration.html#settings
[mybatis mapper xml]: http://www.mybatis.org/mybatis-3/sqlmap-xml.html
