/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.typeHandles;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.eastbar.web.Times;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author C.lins@aliyun.com
 * @date 2014年11月07
 * @time 上午10:24
 * @description :
 */
public class TimeTypeHandle implements TypeHandler<String> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, s);
    }

    @Override
    public String getResult(ResultSet resultSet, String s) throws SQLException {
        return Times.s2date(resultSet.getDate(s));
    }

    @Override
    public String getResult(ResultSet resultSet, int i) throws SQLException {
        return Times.s2date(resultSet.getDate(i));
    }

    @Override
    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        return Times.s2date(callableStatement.getDate(i));
    }
}
