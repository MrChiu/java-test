package com.example.nioTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 18:09 2019-03-13
 */
public class ByteBufferFlipTest {

    public static void main(String[] args){
        ByteBuffer buff = ByteBuffer.allocate(20);
        FileChannel fin = null;
        FileChannel fout = null;
        try{
            fin = new FileInputStream("D:\\test.txt").getChannel();
            fout = new FileOutputStream("D:\\test_bak.txt").getChannel();
            while(fin.read(buff) != -1) {
                buff.flip();
                fout.write(buff);
                buff.clear();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
