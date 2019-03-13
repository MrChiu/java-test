package com.example.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: qiudong
 * @description: 将文件映射到内存
 * @date: Created in 12:53 2019-03-13
 */
public class UseMappedFileTest {

    public static void main(String args[]) throws Exception {
        RandomAccessFile raf1 = new RandomAccessFile("D:\\test.txt", "rw");
        RandomAccessFile raf2 = new RandomAccessFile("D:\\test_bak.txt", "rw");
        FileChannel fc1 = raf1.getChannel();
        FileChannel fc2 = raf2.getChannel();

        long size = fc1.size();

        MappedByteBuffer mbb1 = fc1.map(FileChannel.MapMode.READ_WRITE, 0, size);
        MappedByteBuffer mbb2 = fc2.map(FileChannel.MapMode.READ_WRITE, 0, size);
        for (int i = 0; i < size; i++) {
            byte b = mbb1.get(i);
            mbb2.put(i, b);
        }

        raf1.close();
        raf2.close();
    }
}
