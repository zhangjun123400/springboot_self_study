package com.zhangjun.mbg.mapper.user;


import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UmsResourceMapper {

    @Select("SELECT t1.url FROM ums_resource t1\n" +
            "INNER JOIN ums_role_resource_relation t2 ON t1.id = t2.resource_id\n" +
            "INNER JOIN ums_role t3 ON t3.id = t2.role_id\n" +
            "INNER JOIN ums_admin_role_relation t4 ON t4.role_id = t3.id\n" +
            "INNER JOIN ums_admin t5 ON t5.id = t4.admin_id\n" +
            "where t5.id =#{id}")
    List<String> getResourceByUserId(Long id);
}