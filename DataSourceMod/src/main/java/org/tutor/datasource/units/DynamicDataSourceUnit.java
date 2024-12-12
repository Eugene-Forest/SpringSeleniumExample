package org.tutor.datasource.units;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.ConnectionBuilder;
import java.sql.SQLException;
import java.sql.ShardingKeyBuilder;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/20
 */
public class DynamicDataSourceUnit extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return null;
    }

    @Override
    protected DataSource determineTargetDataSource() {
        return super.determineTargetDataSource();
    }

    /**
     * Create a new {@code ConnectionBuilder} instance
     */
    @Override
    public ConnectionBuilder createConnectionBuilder() throws SQLException {
        return super.createConnectionBuilder();
    }

    /**
     * Creates a new {@code ShardingKeyBuilder} instance
     */
    @Override
    public ShardingKeyBuilder createShardingKeyBuilder() throws SQLException {
        return super.createShardingKeyBuilder();
    }
}
