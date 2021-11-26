package com.zq.mybatis.generator.generator;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * @author qiang.zhao
 * @project: merchant-kili
 * @description:
 * @date 2021/11/26 15:40
 */
public class CustomerJavaTypeResolver extends JavaTypeResolverDefaultImpl {

    public CustomerJavaTypeResolver() {
        super();
        //把数据库的 TINYINT 映射成 Integer
        super.typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
