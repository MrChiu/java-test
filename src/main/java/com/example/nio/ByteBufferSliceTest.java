package com.example.nio;

import java.nio.ByteBuffer;

/**
 * @author: qiudong
 * @description: ByteBuffer使用
 * @date: Created in 12:46 2019-03-13
 */
public class ByteBufferSliceTest {

    public static void main(String[] args){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte)i);
        }
        buffer.position(3);
        buffer.limit(6);
        ByteBuffer slice = buffer.slice();

        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 11;
            slice.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
