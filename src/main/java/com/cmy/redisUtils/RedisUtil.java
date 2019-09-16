package com.cmy.redisUtils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private static JedisPool jedisPool;
    //获取JedisPool对象
    public static JedisPool getJedisPool(String ip,int port){
        if (jedisPool == null){
            //创建jedisPoolConfig对象
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            //设置连接池最大的线程数（一个线程就是一个jedis）
            jedisPoolConfig.setMaxTotal(20);
            //设置最大空闲数（保留2个Jedis对象备用，当有新的请求来如果没有其他可用对象，就用这个保留对象）
            jedisPoolConfig.setMaxIdle(2);
            //设置检查项为true，表示从线程池中获取的对象一定是经过检查可用的
            jedisPoolConfig.setTestOnBorrow(true);
            //构造方法获取jedisPool对象，参数6000为连接redis的超时时间，超过这个时间redis就报错便不再连接，单位为毫秒
            jedisPool = new JedisPool(jedisPoolConfig,ip,port,6000);
        }else {
            return jedisPool;
        }
        return jedisPool;
    }

    //关闭Pool对象
    public static void close(JedisPool jedisPool){
        if (jedisPool != null){
            jedisPool.close();
        }
    }
}
