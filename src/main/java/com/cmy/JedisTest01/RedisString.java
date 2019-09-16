package com.cmy.JedisTest01;

import com.cmy.redisUtils.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class RedisString {
    public static void main(String[] args) {
        //创建Jedis对象指定连接的Redis服务器的ip及端口
        String host="192.168.71.131";
        int port = 6379;
//        Jedis jedis = new Jedis(host,port);
//        jedis.set("k1","v1");
//        String k1Value = jedis.get("k1");
//        System.out.println(k1Value);
//
//        jedis.mset("k2","v2","k3","v3");
//        List<String> mget = jedis.mget("k2", "k3");
//        System.out.println("mgetList is " + mget);
        //调用Jedis对象的方法，操作Redis数据



        //创建JedisPoll对象
        JedisPool jedisPool = RedisUtil.getJedisPool(host,port);
        //从jedisPoll中获取jedis对象
        Jedis resource = jedisPool.getResource();
        resource.set("poolKey","poolValue");
        System.out.println(resource.get("poolKey"));
        if (resource != null){
            resource.close();//此关闭并不是销毁对象，而是放回线程池中去
        }
    }
}
