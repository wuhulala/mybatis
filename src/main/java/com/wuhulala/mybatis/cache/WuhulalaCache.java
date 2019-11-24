package com.wuhulala.mybatis.cache;

import org.apache.ibatis.cache.Cache;

/**
 * @author wuhulala<br>
 * @date 2019/11/24<br>
 * @since v1.0<br>
 */
public class WuhulalaCache implements Cache {

    private Cache delegate;

    private int size;

    public WuhulalaCache(Cache delegate) {
        this.delegate = delegate;
        this.size = 1024;
        System.out.println("wuhulala cache init");
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("wuhulala cache put");
        delegate.putObject(key, value);
    }

    @Override
    public Object getObject(Object key) {
        return delegate.getObject(key);
    }

    @Override
    public Object removeObject(Object key) {
        return delegate.removeObject(key);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    public void setSize(int size) {
        System.out.println("wuhulala cache");
        this.size = size;
    }
}
