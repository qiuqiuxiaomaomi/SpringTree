# 本篇主要讲述字节码文件
# 文件名: QueueService.class

F:\gitCode\SpringTree\target\classes\com\bonaparte\service>javap -verbose QueueService.class
Classfile /F:/gitCode/SpringTree/target/classes/com/bonaparte/service/QueueService.class
  Last modified 2018-7-19; size 2530 bytes      ---- 文件的更新时间
  MD5 checksum ea979aa1433548e81f6aa2a79cbecd80   ----- 文件的MD值
  Compiled from "QueueService.java"
public class com.bonaparte.service.QueueService
  minor version: 0     ---- class文件的次版本号
  major version: 52    ---- 该JVM支持的最大class文件的版本号
  flags: ACC_PUBLIC, ACC_SUPER   ---- QueueService类的访问信息，为public
Constant pool:              ----- 常量池
    #1 = Methodref          #28.#71       // java/lang/Object."<init>":()V
    #2 = Fieldref           #72.#73       // java/lang/System.out:Ljava/io/PrintStream;
    #3 = String             #74           // 生产一个苹果
    #4 = Methodref          #75.#76       // java/io/PrintStream.println:(Ljava/lang/String;)V
    #5 = Fieldref           #12.#77       // com/bonaparte/service/QueueService.basket:Ljava/util/concurrent/BlockingQueue;
    #6 = String             #78           // an apple
    #7 = InterfaceMethodref #79.#80       // java/util/concurrent/BlockingQueue.put:(Ljava/lang/Object;)V
    #8 = String             #81           // 消费一个苹果
    #9 = InterfaceMethodref #79.#82       // java/util/concurrent/BlockingQueue.take:()Ljava/lang/Object;
   #10 = Class              #83           // java/lang/String
   #11 = InterfaceMethodref #79.#84       // java/util/concurrent/BlockingQueue.size:()I
   #12 = Class              #85           // com/bonaparte/service/QueueService
   #13 = Methodref          #12.#71       // com/bonaparte/service/QueueService."<init>":()V
   #14 = Methodref          #86.#87       // java/util/concurrent/Executors.newCachedThreadPool:()Ljava/util/concurrent/ExecutorService;
   #15 = Class              #88           // com/bonaparte/service/QueueService$1Producer
   #16 = Methodref          #15.#89       // com/bonaparte/service/QueueService$1Producer."<init>":(Lcom/bonaparte/service/QueueService;Lcom/bonaparte/service/QueueService;)V
   #17 = Class              #90           // com/bonaparte/service/QueueService$1Consumer
   #18 = Methodref          #17.#89       // com/bonaparte/service/QueueService$1Consumer."<init>":(Lcom/bonaparte/service/QueueService;Lcom/bonaparte/service/QueueService;)V
   #19 = InterfaceMethodref #91.#92       // java/util/concurrent/ExecutorService.submit:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
   #20 = Long               10000l
   #22 = Methodref          #93.#94       // java/lang/Thread.sleep:(J)V
   #23 = Class              #95           // java/lang/InterruptedException
   #24 = Methodref          #23.#96       // java/lang/InterruptedException.printStackTrace:()V
   #25 = InterfaceMethodref #91.#97       // java/util/concurrent/ExecutorService.shutdown:()V
   #26 = Class              #98           // java/util/concurrent/ArrayBlockingQueue
   #27 = Methodref          #26.#99       // java/util/concurrent/ArrayBlockingQueue."<init>":(I)V
   #28 = Class              #100          // java/lang/Object
   #29 = Utf8               Consumer
   #30 = Utf8               InnerClasses
   #31 = Utf8               Producer
   #32 = Utf8               basket
   #33 = Utf8               Ljava/util/concurrent/BlockingQueue;
   #34 = Utf8               Signature
   #35 = Utf8               Ljava/util/concurrent/BlockingQueue<Ljava/lang/String;>;
   #36 = Utf8               <init>
   #37 = Utf8               ()V
   #38 = Utf8               Code
   #39 = Utf8               LineNumberTable
   #40 = Utf8               LocalVariableTable
   #41 = Utf8               this
   #42 = Utf8               Lcom/bonaparte/service/QueueService;
   #43 = Utf8               produce
   #44 = Utf8               Exceptions
   #45 = Utf8               consumer
   #46 = Utf8               ()Ljava/lang/String;
   #47 = Utf8               apple
   #48 = Utf8               Ljava/lang/String;
   #49 = Utf8               getAppleNumber
   #50 = Utf8               ()I
   #51 = Utf8               testQueue
   #52 = Utf8               e
   #53 = Utf8               Ljava/lang/InterruptedException;
   #54 = Utf8               queueService
   #55 = Utf8               service2
   #56 = Utf8               Ljava/util/concurrent/ExecutorService;
   #57 = Utf8               producer
   #58 = Utf8               Lcom/bonaparte/service/QueueService$1Producer;
   #59 = Utf8               Lcom/bonaparte/service/QueueService$1Consumer;
   #60 = Utf8               StackMapTable
   #61 = Class              #85           // com/bonaparte/service/QueueService
   #62 = Class              #101          // java/util/concurrent/ExecutorService
   #63 = Class              #88           // com/bonaparte/service/QueueService$1Producer
   #64 = Class              #90           // com/bonaparte/service/QueueService$1Consumer
   #65 = Class              #95           // java/lang/InterruptedException
   #66 = Utf8               <clinit>
   #67 = Utf8               SourceFile
   #68 = Utf8               QueueService.java
   #69 = Utf8               RuntimeVisibleAnnotations
   #70 = Utf8               Lorg/springframework/stereotype/Service;
   #71 = NameAndType        #36:#37       // "<init>":()V
   #72 = Class              #102          // java/lang/System
   #73 = NameAndType        #103:#104     // out:Ljava/io/PrintStream;
   #74 = Utf8               生产一个苹果
   #75 = Class              #105          // java/io/PrintStream
   #76 = NameAndType        #106:#107     // println:(Ljava/lang/String;)V
   #77 = NameAndType        #32:#33       // basket:Ljava/util/concurrent/BlockingQueue;
   #78 = Utf8               an apple
   #79 = Class              #108          // java/util/concurrent/BlockingQueue
   #80 = NameAndType        #109:#110     // put:(Ljava/lang/Object;)V
   #81 = Utf8               消费一个苹果
   #82 = NameAndType        #111:#112     // take:()Ljava/lang/Object;
   #83 = Utf8               java/lang/String
   #84 = NameAndType        #113:#50      // size:()I
   #85 = Utf8               com/bonaparte/service/QueueService
   #86 = Class              #114          // java/util/concurrent/Executors
   #87 = NameAndType        #115:#116     // newCachedThreadPool:()Ljava/util/concurrent/ExecutorService;
   #88 = Utf8               com/bonaparte/service/QueueService$1Producer
   #89 = NameAndType        #36:#117      // "<init>":(Lcom/bonaparte/service/QueueService;Lcom/bonaparte/service/QueueService;)V
   #90 = Utf8               com/bonaparte/service/QueueService$1Consumer
   #91 = Class              #101          // java/util/concurrent/ExecutorService
   #92 = NameAndType        #118:#119     // submit:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
   #93 = Class              #120          // java/lang/Thread
   #94 = NameAndType        #121:#122     // sleep:(J)V
   #95 = Utf8               java/lang/InterruptedException
   #96 = NameAndType        #123:#37      // printStackTrace:()V
   #97 = NameAndType        #124:#37      // shutdown:()V
   #98 = Utf8               java/util/concurrent/ArrayBlockingQueue
   #99 = NameAndType        #36:#125      // "<init>":(I)V
  #100 = Utf8               java/lang/Object
  #101 = Utf8               java/util/concurrent/ExecutorService
  #102 = Utf8               java/lang/System
  #103 = Utf8               out
  #104 = Utf8               Ljava/io/PrintStream;
  #105 = Utf8               java/io/PrintStream
  #106 = Utf8               println
  #107 = Utf8               (Ljava/lang/String;)V
  #108 = Utf8               java/util/concurrent/BlockingQueue
  #109 = Utf8               put
  #110 = Utf8               (Ljava/lang/Object;)V
  #111 = Utf8               take
  #112 = Utf8               ()Ljava/lang/Object;
  #113 = Utf8               size
  #114 = Utf8               java/util/concurrent/Executors
  #115 = Utf8               newCachedThreadPool
  #116 = Utf8               ()Ljava/util/concurrent/ExecutorService;
  #117 = Utf8               (Lcom/bonaparte/service/QueueService;Lcom/bonaparte/service/QueueService;)V
  #118 = Utf8               submit
  #119 = Utf8               (Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
  #120 = Utf8               java/lang/Thread
  #121 = Utf8               sleep
  #122 = Utf8               (J)V
  #123 = Utf8               printStackTrace
  #124 = Utf8               shutdown
  #125 = Utf8               (I)V
{
  public static java.util.concurrent.BlockingQueue<java.lang.String> basket;
    descriptor: Ljava/util/concurrent/BlockingQueue;
    flags: ACC_PUBLIC, ACC_STATIC
    Signature: #35                          // Ljava/util/concurrent/BlockingQueue<Ljava/lang/String;>;

  public com.bonaparte.service.QueueService();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 16: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/bonaparte/service/QueueService;

  public void produce() throws java.lang.InterruptedException;
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #3                  // String 生产一个苹果
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: getstatic     #5                  // Field basket:Ljava/util/concurrent/BlockingQueue;
        11: ldc           #6                  // String an apple
        13: invokeinterface #7,  2            // InterfaceMethod java/util/concurrent/BlockingQueue.put:(Ljava/lang/Object;)V
        18: return
      LineNumberTable:
        line 20: 0
        line 21: 8
        line 22: 18
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      19     0  this   Lcom/bonaparte/service/QueueService;
    Exceptions:
      throws java.lang.InterruptedException

  public java.lang.String consumer() throws java.lang.InterruptedException;
    descriptor: ()Ljava/lang/String;
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=2, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #8                  // String 消费一个苹果
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: getstatic     #5                  // Field basket:Ljava/util/concurrent/BlockingQueue;
        11: invokeinterface #9,  1            // InterfaceMethod java/util/concurrent/BlockingQueue.take:()Ljava/lang/Object;
        16: checkcast     #10                 // class java/lang/String
        19: astore_1
        20: aload_1
        21: areturn
      LineNumberTable:
        line 25: 0
        line 26: 8
        line 27: 20
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      22     0  this   Lcom/bonaparte/service/QueueService;
           20       2     1 apple   Ljava/lang/String;
    Exceptions:
      throws java.lang.InterruptedException

  public int getAppleNumber();
    descriptor: ()I
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: getstatic     #5                  // Field basket:Ljava/util/concurrent/BlockingQueue;
         3: invokeinterface #11,  1           // InterfaceMethod java/util/concurrent/BlockingQueue.size:()I
         8: ireturn
      LineNumberTable:
        line 31: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  this   Lcom/bonaparte/service/QueueService;

  public void testQueue();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=4, locals=6, args_size=1
         0: new           #12                 // class com/bonaparte/service/QueueService
         3: dup
         4: invokespecial #13                 // Method "<init>":()V
         7: astore_1
         8: invokestatic  #14                 // Method java/util/concurrent/Executors.newCachedThreadPool:()Ljava/util/concurrent/ExecutorService;
        11: astore_2
        12: new           #15                 // class com/bonaparte/service/QueueService$1Producer
        15: dup
        16: aload_0
        17: aload_1
        18: invokespecial #16                 // Method com/bonaparte/service/QueueService$1Producer."<init>":(Lcom/bonaparte/service/QueueService;Lcom/bonaparte/service/QueueService;)V
        21: astore_3
        22: new           #17                 // class com/bonaparte/service/QueueService$1Consumer
        25: dup
        26: aload_0
        27: aload_1
        28: invokespecial #18                 // Method com/bonaparte/service/QueueService$1Consumer."<init>":(Lcom/bonaparte/service/QueueService;Lcom/bonaparte/service/QueueService;)V
        31: astore        4
        33: aload_2
        34: aload_3
        35: invokeinterface #19,  2           // InterfaceMethod java/util/concurrent/ExecutorService.submit:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
        40: pop
        41: aload_2
        42: aload         4
        44: invokeinterface #19,  2           // InterfaceMethod java/util/concurrent/ExecutorService.submit:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
        49: pop
        50: ldc2_w        #20                 // long 10000l
        53: invokestatic  #22                 // Method java/lang/Thread.sleep:(J)V
        56: goto          66
        59: astore        5
        61: aload         5
        63: invokevirtual #24                 // Method java/lang/InterruptedException.printStackTrace:()V
        66: aload_2
        67: invokeinterface #25,  1           // InterfaceMethod java/util/concurrent/ExecutorService.shutdown:()V
        72: return
      Exception table:
         from    to  target type
            50    56    59   Class java/lang/InterruptedException
      LineNumberTable:
        line 35: 0
        line 66: 8
        line 67: 12
        line 68: 22
        line 69: 33
        line 70: 41
        line 72: 50
        line 75: 56
        line 73: 59
        line 74: 61
        line 76: 66
        line 77: 72
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
           61       5     5     e   Ljava/lang/InterruptedException;
            0      73     0  this   Lcom/bonaparte/service/QueueService;
            8      65     1 queueService   Lcom/bonaparte/service/QueueService;
           12      61     2 service2   Ljava/util/concurrent/ExecutorService;
           22      51     3 producer   Lcom/bonaparte/service/QueueService$1Producer;
           33      40     4 consumer   Lcom/bonaparte/service/QueueService$1Consumer;
      StackMapTable: number_of_entries = 2
        frame_type = 255 /* full_frame */
          offset_delta = 59
          locals = [ class com/bonaparte/service/QueueService, class com/bonaparte/service/QueueService, class java/util/concurrent/ExecutorService, class com/bonaparte/service/QueueService$1Producer, class com/bonaparte/service/QueueService$1Consumer ]
          stack = [ class java/lang/InterruptedException ]
        frame_type = 6 /* same */

  static {};
    descriptor: ()V
    flags: ACC_STATIC
    Code:
      stack=3, locals=0, args_size=0
         0: new           #26                 // class java/util/concurrent/ArrayBlockingQueue
         3: dup
         4: iconst_3
         5: invokespecial #27                 // Method java/util/concurrent/ArrayBlockingQueue."<init>":(I)V
         8: putstatic     #5                  // Field basket:Ljava/util/concurrent/BlockingQueue;
        11: return
      LineNumberTable:
        line 17: 0
}
SourceFile: "QueueService.java"
RuntimeVisibleAnnotations:
  0: #70()
InnerClasses:
     #29= #17; //Consumer=class com/bonaparte/service/QueueService$1Consumer
     #31= #15; //Producer=class com/bonaparte/service/QueueService$1Producer