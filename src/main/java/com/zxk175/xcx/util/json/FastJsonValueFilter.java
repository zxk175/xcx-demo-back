package com.zxk175.xcx.util.json;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.zxk175.xcx.util.MyStrUtil;

/**
 * @author zxk175
 * @since 2020-03-18 10:26
 */
public class FastJsonValueFilter implements ValueFilter {

    @Override
    public Object process(Object object, String name, Object value) {
        String data = "data";
        boolean flag = ObjectUtil.isNull(value);
        if (flag && data.equals(name)) {
            return new Object();
        }

        if (ObjectUtil.isNull(value)) {
            return MyStrUtil.EMPTY;
        }

        return value;
    }

}
