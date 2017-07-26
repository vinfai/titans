package com.titans.service;

import com.dangdang.ddframe.rdb.sharding.api.rule.BindingTableRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.jdbc.core.datasource.ShardingDataSource;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vinfai
 * @since 2017/7/26
 */
public class SharedingJdbcTest {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = getShardingDataSource();
       /* for (int i = 0; i < 10000; i++) {
            try {
                insert(dataSource, i, 100+i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/

       printGroupBy(dataSource);

    }

    private static void printGroupBy(final DataSource dataSource) throws SQLException {
        String sql = "SELECT o.user_id, COUNT(*) FROM t_order o  GROUP BY o.user_id order by 1 desc limit 0,10 ";
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    System.out.println("user_id: " + rs.getInt(1) + ", count: " + rs.getInt(2));
                }
            }
        }
    }

    private static void insert(final DataSource dataSource, Integer orderId, Integer userId) throws SQLException {
        String sql = "insert into t_order(order_id,user_id) values(?,?)";
        Connection conn = dataSource.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, userId);


        boolean execute = preparedStatement.execute();
        System.out.println(execute);
        //conn.commit();
        conn.close();
    }

    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>(1);
        result.put("commdata", createDataSource("commdata"));
        return result;
    }

    private static DataSource createDataSource(String dataSourceName) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(String.format("jdbc:mysql://192.168.8.108:3306/%s", dataSourceName));
        dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        dataSource.setUsername("commdata");
        dataSource.setPassword("commdata123");
        return dataSource;
    }

    private static ShardingDataSource getShardingDataSource() {
        DataSourceRule dataSourceRule = new DataSourceRule(createDataSourceMap());
        TableRule orderTableRule = TableRule.builder("t_order").actualTables(Arrays.asList("t_order_0", "t_order_1")).dataSourceRule(dataSourceRule).build();
        TableRule orderItemTableRule = TableRule.builder("t_order_item").actualTables(Arrays.asList("t_order_item_0", "t_order_item_1")).dataSourceRule(dataSourceRule).build();
        ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule).tableRules(Arrays.asList(orderTableRule, orderItemTableRule))
                .bindingTableRules(Collections.singletonList(new BindingTableRule(Arrays.asList(orderTableRule, orderItemTableRule))))
                //.databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new ModuloDatabaseShardingAlgorithm()))
                .tableShardingStrategy(new TableShardingStrategy("order_id", new ModuloTableShardingAlgorithm())).build();
        return new ShardingDataSource(shardingRule);
    }
}
