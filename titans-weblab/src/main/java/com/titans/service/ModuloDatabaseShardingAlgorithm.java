package com.titans.service;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public final class ModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {
    
    @Override
    public String doEqualSharding(final Collection<String> dataSourceNames, final ShardingValue<Integer> shardingValue) {
        HashFunction hashFunction = Hashing.murmur3_128();
        for (String each : dataSourceNames) {
//            if (each.endsWith(shardingValue.getValue() % 2 + "")) {

            if (each.endsWith(shardingValue.getValue()% 2 + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }
    
    @Override
    public Collection<String> doInSharding(final Collection<String> dataSourceNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(dataSourceNames.size());
        for (Integer value : shardingValue.getValues()) {
            for (String dataSourceName : dataSourceNames) {
                if (dataSourceName.endsWith(value % 2 + "")) {
                    result.add(dataSourceName);
                }
            }
        }
        return result;
    }
    
    @Override
    public Collection<String> doBetweenSharding(final Collection<String> dataSourceNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(dataSourceNames.size());
        Range<Integer> range = shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : dataSourceNames) {
                if (each.endsWith(i % 2 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}