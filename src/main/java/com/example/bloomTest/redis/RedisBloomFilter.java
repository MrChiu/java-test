package com.example.bloomTest.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.BloomFilterStrategies;
import com.google.common.hash.Funnel;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 16:51 2018-12-12
 */
public class RedisBloomFilter<T> {

    private static Strategy strategy;


    public static <T> RedisBloomFilter<T> create(Funnel<? super T> funnel, int expectedInsertions, double fpp){
        checkArgument(expectedInsertions >= 0, "Expected insertions (%s) must be >= 0", expectedInsertions);
        checkArgument(fpp > 0.0, "False positive probability (%s) must be > 0.0", fpp);
        checkArgument(fpp < 1.0, "False positive probability (%s) must be < 1.0", fpp);
        strategy = new CustomBloomFilterStrategy();

        if (expectedInsertions == 0) {
            expectedInsertions = 1;
        }
    /*
     * TODO(user): Put a warning in the javadoc about tiny fpp values, since the resulting size
     * is proportional to -log(p), but there is not much of a point after all, e.g.
     * optimalM(1000, 0.0000000000000001) = 76680 which is less than 10kb. Who cares!
     */
        long numBits = optimalNumOfBits(expectedInsertions, fpp);
        int numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);
        try {
            return new RedisBloomFilter<T>(new BloomFilterStrategies.LockFreeBitArray(numBits), numHashFunctions, funnel, strategy);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not create BloomFilter of " + numBits + " bits", e);
        }
    }

    static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    static int optimalNumOfHashFunctions(long n, long m) {
        // (m / n) * log(2), but avoid truncation due to division!
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    public void resetBitmap() {

    }

    public void put(String s) {
        strategy.put(s, );
    }

    public boolean mightContain(String str1) {
    }
}
