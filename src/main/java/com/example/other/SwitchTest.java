package com.example.other;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 18:02 2018-06-08
 */
public class SwitchTest {

    public static void main(String[] args){
        String i = "Ea";
        int result = 0;

        switch (i){
            case "FB": result = 1;break;
            case "Ea": result = 100;break;
            case "d": result = 200;break;
            default: return;
        }

        System.out.println(result);
    }
}


//Classfile /D:/work_project/ycloud-store-inventory/target/test-classes/other/switchTest.class
//Last modified 2018-6-9; size 900 bytes
//        MD5 checksum b5787c53d36470e10c632f216a1c9055
//        Compiled from "switchTest.java"
//public class other.switchTest
//        minor version: 0
//        major version: 52
//        flags: ACC_PUBLIC, ACC_SUPER
//        Constant pool:
//        #1 = Methodref          #10.#31        // java/lang/Object."<init>":()V
//        #2 = String             #32            // Ea
//        #3 = Methodref          #33.#34        // java/lang/String.hashCode:()I
//        #4 = Methodref          #33.#35        // java/lang/String.equals:(Ljava/lang/Object;)Z
//        #5 = String             #36            // FB
//        #6 = String             #37            // d
//        #7 = Fieldref           #38.#39        // java/lang/System.out:Ljava/io/PrintStream;
//        #8 = Methodref          #40.#41        // java/io/PrintStream.println:(I)V
//        #9 = Class              #42            // other/switchTest
//        #10 = Class              #43            // java/lang/Object
//        #11 = Utf8               <init>
//  #12 = Utf8               ()V
//          #13 = Utf8               Code
//          #14 = Utf8               LineNumberTable
//          #15 = Utf8               LocalVariableTable
//          #16 = Utf8               this
//          #17 = Utf8               Lother/switchTest;
//          #18 = Utf8               main
//          #19 = Utf8               ([Ljava/lang/String;)V
//          #20 = Utf8               args
//          #21 = Utf8               [Ljava/lang/String;
//          #22 = Utf8               i
//          #23 = Utf8               Ljava/lang/String;
//          #24 = Utf8               result
//          #25 = Utf8               I
//          #26 = Utf8               StackMapTable
//          #27 = Class              #21            // "[Ljava/lang/String;"
//          #28 = Class              #44            // java/lang/String
//          #29 = Utf8               SourceFile
//          #30 = Utf8               switchTest.java
//          #31 = NameAndType        #11:#12        // "<init>":()V
//          #32 = Utf8               Ea
//          #33 = Class              #44            // java/lang/String
//          #34 = NameAndType        #45:#46        // hashCode:()I
//          #35 = NameAndType        #47:#48        // equals:(Ljava/lang/Object;)Z
//          #36 = Utf8               FB
//          #37 = Utf8               d
//          #38 = Class              #49            // java/lang/System
//          #39 = NameAndType        #50:#51        // out:Ljava/io/PrintStream;
//          #40 = Class              #52            // java/io/PrintStream
//          #41 = NameAndType        #53:#54        // println:(I)V
//          #42 = Utf8               other/switchTest
//          #43 = Utf8               java/lang/Object
//          #44 = Utf8               java/lang/String
//          #45 = Utf8               hashCode
//          #46 = Utf8               ()I
//          #47 = Utf8               equals
//          #48 = Utf8               (Ljava/lang/Object;)Z
//          #49 = Utf8               java/lang/System
//          #50 = Utf8               out
//          #51 = Utf8               Ljava/io/PrintStream;
//          #52 = Utf8               java/io/PrintStream
//          #53 = Utf8               println
//          #54 = Utf8               (I)V
//          {
//public other.switchTest();
//        descriptor: ()V
//        flags: ACC_PUBLIC
//        Code:
//        stack=1, locals=1, args_size=1
//        0: aload_0
//        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
//        4: return
//        LineNumberTable:
//        line 8: 0
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0       5     0  this   Lother/switchTest;
//
//public static void main(java.lang.String[]);
//        descriptor: ([Ljava/lang/String;)V
//        flags: ACC_PUBLIC, ACC_STATIC
//        Code:
//        stack=2, locals=5, args_size=1
//        0: ldc           #2                  // String Ea
//        2: astore_1
//        3: iconst_0
//        4: istore_2
//        5: aload_1
//        6: astore_3
//        7: iconst_m1
//        8: istore        4
//        10: aload_3
//        11: invokevirtual #3                  // Method java/lang/String.hashCode:()I
//        14: lookupswitch  { // 2
//        100: 70
//        2236: 40
//default: 82
//        }
//        40: aload_3
//        41: ldc           #2                  // String Ea
//        43: invokevirtual #4                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
//        46: ifeq          55
//        49: iconst_1
//        50: istore        4
//        52: goto          82
//        55: aload_3
//        56: ldc           #5                  // String FB
//        58: invokevirtual #4                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
//        61: ifeq          82
//        64: iconst_0
//        65: istore        4
//        67: goto          82
//        70: aload_3
//        71: ldc           #6                  // String d
//        73: invokevirtual #4                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
//        76: ifeq          82
//        79: iconst_2
//        80: istore        4
//        82: iload         4
//        84: tableswitch   { // 0 to 2
//        0: 112
//        1: 117
//        2: 123
//default: 130
//        }
//        112: iconst_1
//        113: istore_2
//        114: goto          131
//        117: bipush        100
//        119: istore_2
//        120: goto          131
//        123: sipush        200
//        126: istore_2
//        127: goto          131
//        130: return
//        131: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
//        134: iload_2
//        135: invokevirtual #8                  // Method java/io/PrintStream.println:(I)V
//        138: return
//        LineNumberTable:
//        line 11: 0
//        line 12: 3
//        line 14: 5
//        line 15: 112
//        line 16: 117
//        line 17: 123
//        line 18: 130
//        line 21: 131
//        line 22: 138
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0     139     0  args   [Ljava/lang/String;
//        3     136     1     i   Ljava/lang/String;
//        5     134     2 result   I
//        StackMapTable: number_of_entries = 9
//        frame_type = 255 /* full_frame */
//        offset_delta = 40
//        locals = [ class "[Ljava/lang/String;", class java/lang/String, int, class java/lang/String, int ]
//        stack = []
//        frame_type = 14 /* same */
//        frame_type = 14 /* same */
//        frame_type = 11 /* same */
//        frame_type = 29 /* same */
//        frame_type = 4 /* same */
//        frame_type = 5 /* same */
//        frame_type = 6 /* same */
//        frame_type = 249 /* chop */
//        offset_delta = 0
//        }
//        SourceFile: "switchTest.java"



//Classfile /D:/work_project/ycloud-store-inventory/target/test-classes/other/switchTest.class
//Last modified 2018-6-9; size 659 bytes
//        MD5 checksum a8b6e4c9f165a2a6e48aaa902dbcc967
//        Compiled from "switchTest.java"
//public class other.switchTest
//        minor version: 0
//        major version: 52
//        flags: ACC_PUBLIC, ACC_SUPER
//        Constant pool:
//        #1 = Methodref          #5.#23         // java/lang/Object."<init>":()V
//        #2 = Fieldref           #24.#25        // java/lang/System.out:Ljava/io/PrintStream;
//        #3 = Methodref          #26.#27        // java/io/PrintStream.println:(I)V
//        #4 = Class              #28            // other/switchTest
//        #5 = Class              #29            // java/lang/Object
//        #6 = Utf8               <init>
//   #7 = Utf8               ()V
//           #8 = Utf8               Code
//           #9 = Utf8               LineNumberTable
//           #10 = Utf8               LocalVariableTable
//           #11 = Utf8               this
//           #12 = Utf8               Lother/switchTest;
//           #13 = Utf8               main
//           #14 = Utf8               ([Ljava/lang/String;)V
//           #15 = Utf8               args
//           #16 = Utf8               [Ljava/lang/String;
//           #17 = Utf8               i
//           #18 = Utf8               I
//           #19 = Utf8               result
//           #20 = Utf8               StackMapTable
//           #21 = Utf8               SourceFile
//           #22 = Utf8               switchTest.java
//           #23 = NameAndType        #6:#7          // "<init>":()V
//           #24 = Class              #30            // java/lang/System
//           #25 = NameAndType        #31:#32        // out:Ljava/io/PrintStream;
//           #26 = Class              #33            // java/io/PrintStream
//           #27 = NameAndType        #34:#35        // println:(I)V
//           #28 = Utf8               other/switchTest
//           #29 = Utf8               java/lang/Object
//           #30 = Utf8               java/lang/System
//           #31 = Utf8               out
//           #32 = Utf8               Ljava/io/PrintStream;
//           #33 = Utf8               java/io/PrintStream
//           #34 = Utf8               println
//           #35 = Utf8               (I)V
//           {
//public other.switchTest();
//        descriptor: ()V
//        flags: ACC_PUBLIC
//        Code:
//        stack=1, locals=1, args_size=1
//        0: aload_0
//        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
//        4: return
//        LineNumberTable:
//        line 8: 0
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0       5     0  this   Lother/switchTest;
//
//public static void main(java.lang.String[]);
//        descriptor: ([Ljava/lang/String;)V
//        flags: ACC_PUBLIC, ACC_STATIC
//        Code:
//        stack=2, locals=3, args_size=1
//        0: iconst_1
//        1: istore_1
//        2: iconst_0
//        3: istore_2
//        4: iload_1
//        5: tableswitch   { // 0 to 2
//        0: 32
//        1: 37
//        2: 43
//default: 50
//        }
//        32: iconst_1
//        33: istore_2
//        34: goto          51
//        37: bipush        100
//        39: istore_2
//        40: goto          51
//        43: sipush        200
//        46: istore_2
//        47: goto          51
//        50: return
//        51: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
//        54: iload_2
//        55: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
//        58: return
//        LineNumberTable:
//        line 11: 0
//        line 12: 2
//        line 14: 4
//        line 15: 32
//        line 16: 37
//        line 17: 43
//        line 18: 50
//        line 21: 51
//        line 22: 58
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0      59     0  args   [Ljava/lang/String;
//        2      57     1     i   I
//        4      55     2 result   I
//        StackMapTable: number_of_entries = 5
//        frame_type = 253 /* append */
//        offset_delta = 32
//        locals = [ int, int ]
//        frame_type = 4 /* same */
//        frame_type = 5 /* same */
//        frame_type = 6 /* same */
//        frame_type = 0 /* same */
//        }
//        SourceFile: "switchTest.java"