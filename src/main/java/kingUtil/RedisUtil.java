package kingUtil;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author kingcall
 * @create 2017-11-12 23:07
 * Describe
 **/
@Repository
public class RedisUtil{
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 判断一个key值是否存在
     *
     * @param key 要判断的key
     * @return 如果存在返回true，否则返回false
     */
    
    public boolean hasKey(String key) {
        boolean flag = false;
        if (key != null) {
            flag = redisTemplate.hasKey(key);
        }
        return flag;
    }

    /**
     * 获取所有的key
     *
     * @return 获取到的所有key
     */
    
    public Set<String> getKeys() {
        return redisTemplate.keys("*");
    }

    /**
     * 获取所有的key
     * @param str key的前缀为str
     * @return
     */
    public Set<String> getKeys(String str) {
        return redisTemplate.keys(str + "*");
    }

    /**
     * 添加一个String
     * @param key   key
     * @param value value
     */

    public void addString(String key, String value) {
        if (key != null && value != null) {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 添加一个String，并设置有效时间
     * @param key   key
     * @param value value
     * @param time  有效时间，单位秒
     */

    public void addString(String key, String value, long time) {
        if (key != null && value != null && time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 获取一个String类型数据
     * @param key key
     * @return 获取到的数据
     */

    public String getString(String key) {
        if (key != null) {
            boolean flag = hasKey(key);
            if (flag) {
                return String.valueOf(redisTemplate.opsForValue().get(key));
            }
        }
        return null;
    }

    /**
     * 向list中添加一条数据，不覆盖原数据（value值允许重复）
     * @param key         key
     * @param value       value
     * @param leftOrRight 插入方向，传空或left从左插入，传right从右插入
     */

    public void addListForOne(String key, String value, String leftOrRight) {
        if ("left".equals(leftOrRight) || leftOrRight == null) {
            redisTemplate.opsForList().leftPush(key, value);
        } else if ("right".equals(leftOrRight)) {
            redisTemplate.opsForList().rightPush(key, value);
        }
    }

    /**
     * 向list中添加一组数据，不覆盖原数据（value值允许重复）
     * @param key         key
     * @param value       value
     * @param leftOrRight 插入方向，传空或left从左插入，传right从右插入
     */
    public void addList(String key, Collection<String> value, String leftOrRight) {
        if (key != null && value != null && value.size() > 0) {
            if ("left".equals(leftOrRight) || leftOrRight == null) {
                redisTemplate.opsForList().leftPushAll(key, value);
            } else if ("right".equals(leftOrRight)) {
                redisTemplate.opsForList().rightPushAll(key, value);
            }
        }
    }

    /**
     * 替换list中的一条数据
     *
     * @param key   key
     * @param cnt   数据位置，下标从0开始
     * @param value 替换后的数据
     */
    public void setListForOne(String key, Long cnt, String value) {
        if (key != null && value != null && cnt != null) {
            boolean flag = hasKey(key);
            redisTemplate.opsForList().set(key, cnt, value);
        }
    }

    /**
     * 获取一个list中全部数据
     * @param key key
     * @return 获取到的list
     */
    public List<String> getList(String key) {
        if (key != null) {
            boolean flag = hasKey(key);
            if (flag) {
                List<Object> objects = redisTemplate.opsForList().range(key, 0, -1);
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < objects.size(); i++) {
                    list.add(String.valueOf(objects.get(i)));
                }
                return list;
            }
        }
        return null;
    }

    /**
     * 向set中添加一组数据（value值不允许重复，重复则覆盖）
     *
     * @param key   key
     * @param value value
     */
    public void addSet(String key, Object... value) {
        if (key != null && value != null) {
            redisTemplate.opsForSet().add(key,value);
        }
    }
   /* public void addSet(String key, Set<String> value) {
        if (key != null && value != null) {
            redisTemplate.opsForSet().add(key, value);
        }
    }*/

    /**
     * 获取一个Set中全部数据
     *
     * @param key key
     * @return 获取到的set
     */
    public Set<String> getSet(String key) {
        if (key != null) {
            boolean flag = hasKey(key);
            if (flag) {
                Set<Object> setObjects = redisTemplate.opsForSet().members(key);
                Set<String> setString = new HashSet<String>();
                for (Object object : setObjects) {
                    setString.add(String.valueOf(object));
                }
                return setString;
            }
        }
        return null;
    }

    /**
     * 向有序set中添加一条数据（value值重复位置不同则覆盖，value值不同位置相同则添加）
     *
     * @param key    key
     * @param value  value
     * @param number 数据位置，下标从0开始
     */
    public void addZSet(String key, String value, double number) {
        if (key != null && value != null && number > -1) {
            redisTemplate.opsForSet().add(key, value, number);
        }
    }

    /**
     * 有序set中添加一组数据（value值重复位置不同则覆盖，value值不同位置相同则添加）
     *
     * @param key     key
     * @param values  value值集合
     * @param numbers 位置集合
     */
    public void addZSet(String key, String[] values, double[] numbers) {
        if (key != null && values != null && values.length == numbers.length && values.length > 0) {
            redisTemplate.opsForSet().add(key, values, numbers);
        }
    }

    /**
     * 获取一个ZSet中全部数据
     *
     * @param key key
     * @return 获取到的有序set
     */
    public Set<Object> getZSet(String key) {
        if (key != null) {
            boolean falg = hasKey(key);
            if (falg) {
                return redisTemplate.opsForZSet().range(key, 0, -1);
            }
        }
        return null;
    }

    /**
     * 向map中添加一条数据，不覆盖原数据（mapkey值不能重复，value值允许重复）
     *
     * @param key   key
     * @param value value
     */
    public void addHash(String key, String mapkey, String value) {
        if (key != null && mapkey != null && value != null) {
            redisTemplate.opsForHash().put(key, mapkey, value);
        }
    }

    /**
     * 向map中添加一组数据，不覆盖原数据（mapkey值不能重复，value值允许重复）
     *
     * @param key   key
     * @param value value
     */
    public void addHash(String key, Map<String, String> value) {
        if (key != null && value != null && value.size() > 0) {
            redisTemplate.opsForHash().putAll(key, value);
        }
    }

    /**
     * 获取一个Hash中全部数据
     *
     * @param key key
     * @return 获取到的map
     */
    public Map<String, String> getHash(String key) {
        if (key != null) {
            Map<Object, Object> mapObject = redisTemplate.opsForHash().entries(key);
            Set<Object> keyword = mapObject.keySet();
            Map<String, String> map = new HashMap<String, String>();
            for (Object str : keyword) {
                map.put(String.valueOf(str), String.valueOf(mapObject.get(str)));
            }
            return map;
        }
        return null;
    }

    /**
     * 获取一个Hash中一条数据
     *
     * @param key key
     * @return 获取到的数据
     */
    public Object getHash(String key, String hashkey) {
        if (key != null && hashkey != null) {
            return String.valueOf(redisTemplate.opsForHash().get(key, hashkey));
        }
        return null;
    }

    /**
     * 删除一条数据
     *
     * @param key key
     */
    public void delete(String key) {
        if (key != null) {
            boolean flag = hasKey(key);
            if (flag) {
                redisTemplate.delete(key);
            }
        }
    }

    /**
     * 删除一批数据
     *
     * @param keys key集合
     */
    public void delete(List<String> keys) {
        if (keys != null && keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 清空全部数据
     */
    public void deleteDb() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }
}
