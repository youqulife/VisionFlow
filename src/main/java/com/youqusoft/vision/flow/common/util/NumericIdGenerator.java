package com.youqusoft.vision.flow.common.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 工具类：基于雪花算法生成 8 位纯数字唯一ID
 * 
 *  @author hogworts  2025年8月29日 下午1:32:05
 */
public class NumericIdGenerator {

    // ===== 内部雪花算法实现 =====
    private static class Snowflake {
        private final long machineId;
        private long sequence = 0L;
        private long lastTimestamp = -1L;

        private final static long MACHINE_ID_BITS = 5L;   // 5位机器ID
        private final static long SEQUENCE_BITS = 12L;    // 12位序列号
        private final static long MAX_MACHINE_ID = ~(-1L << MACHINE_ID_BITS);
        private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

        private final static long MACHINE_ID_SHIFT = SEQUENCE_BITS;
        private final static long TIMESTAMP_SHIFT = SEQUENCE_BITS + MACHINE_ID_BITS;

        public Snowflake(long machineId) {
            if (machineId > MAX_MACHINE_ID || machineId < 0) {
                throw new IllegalArgumentException("Machine ID out of range");
            }
            this.machineId = machineId;
        }

        private long currentTimeMillis() {
            return System.currentTimeMillis();
        }

        private long waitNextMillis(long lastTimestamp) {
            long ts = currentTimeMillis();
            while (ts <= lastTimestamp) {
                ts = currentTimeMillis();
            }
            return ts;
        }

        public synchronized long nextId() {
            long timestamp = currentTimeMillis();

            if (timestamp == lastTimestamp) {
                sequence = (sequence + 1) & MAX_SEQUENCE;
                if (sequence == 0) {
                    timestamp = waitNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0L;
            }
            lastTimestamp = timestamp;

            return ((timestamp) << TIMESTAMP_SHIFT) | (machineId << MACHINE_ID_SHIFT) | sequence;
        }
    }

    // ===== 工具类方法 =====
    private final Snowflake snowflake;

    public NumericIdGenerator(long machineId) {
        this.snowflake = new Snowflake(machineId);
    }

    /**
     * 生成单个 8 位纯数字唯一ID
     */
    public String generateId() {
        long id = snowflake.nextId();
        return String.format("%08d", id % 100_000_000L);
    }

    /**
     * 批量生成唯一ID集合
     */
    public Set<String> generateBatch(int count) {
        Set<String> codes = new HashSet<>(count);
        while (codes.size() < count) {
            codes.add(generateId());
        }
        return codes;
    }

    /**
     * 批量生成并保存为 CSV 文件
     */
    public void generateToCsv(int count, String filePath) {
        Set<String> codes = generateBatch(count);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String code : codes) {
                writer.write(code);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("写入CSV失败: " + e.getMessage(), e);
        }
    }

    private static NumericIdGenerator instance;

    public static NumericIdGenerator getInstance() {
        if (instance == null) {
            synchronized (NumericIdGenerator.class) {
                if (instance == null) {
                    instance = new NumericIdGenerator(1);
                }
            }
        }
        return instance;
    }

    // ===== 测试示例 =====
    public static void main(String[] args) {
        NumericIdGenerator generator = new NumericIdGenerator(1);

        // 生成单个ID
        System.out.println("单个ID: " + generator.generateId());

        // 批量生成 10 个
        System.out.println("批量生成 10 个ID: " + generator.generateBatch(10));

        // 生成 40 万个并写入 CSV
        generator.generateToCsv(400_000, "numeric_ids.csv");
        System.out.println("已生成 400000 个ID到 numeric_ids.csv 文件");
    }
}
