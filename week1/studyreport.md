[TOC]

# 一、VI/VIM编辑器的使用

## 1.基础命令

 - Normal模式：
   - 启动Vim，默认在Normal模式下，按下`i`键切换至Insert模式
   - `x`删除当前光标所在的一个字符
   - `:wq`存盘退出（`:w`存盘，`:q`退出）
   - `dd`剪切当前行
   - `p`粘贴剪切板
   - `hjkl`分别是上下左右移动光标
   - `:help`显示帮助
- Insert模式：
  - `ESC`，返回Normal模式
  - 正常书写文本

## 2.常用命令

- 各种插入模式：

  - `a`在光标后插入
  - `o`在当前行后插入一个新行
  - `O`在当前行前插入一个新行
  - `cw`替换从光标所在位置到后一个单词结尾的字符

- 简单的移动光标：

  - `0`数字0，道行头
  - `^`到本行第一个非空字符的位置
  - `$`到本行行尾
  - `g_`到本行最后一个非空字符的位置
  - `/pattern`搜索patten的字符串（搜索出多个匹配的字符串是可按n键到下一个）

- 拷贝/粘贴：

  - `P/p`P表示在当前位置之前粘贴，p表示在当前位置之后粘贴
  - `yy`拷贝当前行

- 打开/保存/退出/改变文件：

  - `:e<path/to/file>`打开一个文件


  - `shift+zz`存盘退出
  - `:q!`退出不保存
  - `:bn,:bp`打开多个文件时慎用这两个命令切换下一个或上一个文件

## 3.使用过程

​	通过查找博客学习了上述常用命令，熟悉了vim的基本操作之后，便使用vim编写了这次的helloworld.java，ant的build文件，还有Junit单元测试文件，通过这些文件的练习，我对vim有了初步的了解。之前很少使用vim主要是因为对vim不了解，不熟悉命令，导致每次尝试使用都会产生一大堆问题，效率很低。相信通过不断的联系可以很快上手vim。

#二、JAVA语言学习

## 1.基础语法

- 细节：java源文件命令必须与main函数所在类命名一致

- 主函数入口: 

  ```java
  public static void main(String[] args){…}
  ```

  - public关键字，这个好理解，声明主函数为public就是告诉其他的类可以访问这个函数。(和C语言一样)
  - static关键字，告知编译器main函数是一个静态函数。也就是说main函数中的代码是存储在静态存储区的，即当定义了类以后这段代码就已经存在了。如果main()方法没有使用static修饰符，那么编译不会出错，但是如果你试图执行该程序将会报错，提示main()方法不存在。因为包含main()的类并没有实例化（即没有这个类的对象），所以其main()方法也不会存。而使用static修饰符则表示该方法是静态的，不需要实例化即可使用。
  - 参数String[] args主要作用是为程序使用者在命令行状态下与程序交互提供了一种手段，即在执行.class文件时可以传入参数。此外在其他类中直接使用main()函数，并可以传递参数。

- 其他基础语法，例如各种变量，分支语句和循环语句等与c++基本一致，不再一一介绍。

## 2.swing的应用(所写内容并未全部用到)

- **Swing**是一个为[Java](https://zh.wikipedia.org/wiki/Java)设计的[GUI](https://zh.wikipedia.org/wiki/GUI)工具包。Swing是[Java基础类](https://zh.wikipedia.org/wiki/JFC)的一部分。Swing包括了[图形用户界面](https://zh.wikipedia.org/wiki/%E5%9B%BE%E5%BD%A2%E7%94%A8%E6%88%B7%E7%95%8C%E9%9D%A2)（GUI）组件如：文本框，文本域，按钮，分隔窗格和表。（位于`javax.swing` 包）一个 Java 的图形界面，由各种不同类型的“元素”组成，例如: 窗口、菜单栏、对话框、标签、按钮、文本框等等，这些“元素”统一被称为 **组件**（`Component`）

- **顶层容器：**

  1. `JFrame` 一个普通的窗口（绝大多数 Swing 图形界面程序使用 JFrame 作为顶层容器）

     ```java
     JFrame frame = new JFrame("Calculator"); //创建标题名
     frame.setTitle("Calculator");	//设置标题名
     frame.setSize(400,280);			//设置大小
     frame.setLayout(null);			//布局管理器，默认为流式布局；常用有5种：FlowLayout、BorderLayout、GridLayout、CardLayout、GridBagLayout(后面有详细介绍)
     frame.setLocationRelativeTo(null); //设置相对位置
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//设置单击窗体右上角关闭图标后，程序会做出怎样的处理。
     frame.setBounds(0,0,400,200);// 显示X轴位置,显示Y轴位置 ,宽,长
     frame.setVisible(true); // 窗口默认是不可见的
     ```

  2. `JDialog`对话框

- **菜单栏：**

  1. 菜单条 
     `JComponent`类的子类 `JMenubar` 负责创建菜单条，即`JMenubar` 的一个实例就是一个菜单条，`JFrame`类用一个把菜单条放到窗口的方法：(该方法将菜单条添加到窗口的顶端，需要注意的是，只能向窗口添加一个菜单条)

     ```java
     setJMenuBar( JMenuBar bar);
     ```

  2. 菜单 
     `JComponent`类的子类`JMenu`负责创建菜单，即`JMenu`的一个实例就是一个菜单。

  3. 菜单项 
     `JComponent`类的子类`JMenuItem`负责创建菜单项，即`JMenuItem`的一个实例就是一个菜单项

  4. 嵌入子菜单 
     `JMenu`是`JMenuItem`的子类，因此菜单本身也是一个菜单项，当把一个菜单看作菜单项添加到摸个菜单中时，称这个菜单为子菜单。

  5. 菜单上的图标 
     可以用 图标类`Icon`声明一个图标，使用`ImageIcon`类创建一个图标。

- **中间容器**

  中间容器充当基本组件的载体，不可独立显示。中间容器可以添加若干基本组件（也可以嵌套添加中间容器），对容器内的组件进行管理，类似于给各种复杂的组件进行分组管理。最顶层的一个中间容器必须依托在顶层容器（窗口）内。

  | 组件         | 描述                                     |
  | ------------ | ---------------------------------------- |
  | JPanel       | 一般轻量级面板容器组件                   |
  | JScrollPane  | 带滚动条的，可以水平和垂直滚动的面板组件 |
  | JSplitPane   | 分隔面板                                 |
  | JTabbedPane  | 选项卡面板                               |
  | JLayeredPane | 层级面板                                 |

  ```java
  JPanel panel = new JPanel();
  panel.setLayout(null);
  panel.setBounds(0,0,400,50);
  ……
  ```

- **基本组建：**

  基本组件是直接实现人机交互的组件。

  | 组件       | 描述   |
  | ---------- | ------ |
  | JLabel     | 标签   |
  | JButton    | 按钮   |
  | JCheckBox  | 复选框 |
  | JTextField | 文本框 |
  | JTextArea  | 文本域 |
  | JList      | 列表   |
  | JSlider    | 滑块   |

  ```java
  JTextField textField = new JTextField();
  textField.setEditable(false);
  textField.setBounds(0,0,400,50);
  textField.setHorizontalAlignment(JTextField.RIGHT);
  ```

- **布局管理器：**

  把 `Swing` 的各种组件(`JComponent`)添加到面板容器中(`JPanel`)，需要给面板容器指定布局管理器(`LayoutManager`)，明确容器(`Container`)内的各个组件之间的排列布局方式。

  | 布局管理器    | 描述                                                         |
  | ------------- | ------------------------------------------------------------ |
  | FlowLayout    | *流式布局*，按组件加入的顺序，按水平方向排列，排满一行换下一行继续排列。 |
  | GridLayout    | *网格布局*，把`Container`按指定行列数分隔出若干网格，每一个网格按顺序放置一个控件。 |
  | GridBagLayout | *网格袋布局*，按网格划分`Container`，每个组件可占用一个或多个网格，可将组件垂直、水平或沿它们的基线对齐。 |
  | BoxLayout     | *箱式布局*，将`Container`中的多个组件按 水平 或 垂直 的方式排列。 |
  | GroupLayout   | *分组布局*，将组件按层次分组（串行 或 并行），分别确定 组件组 在 水平 和 垂直 方向上的位置。 |
  | CardLayout    | *卡片布局*，将`Container`中的每个组件看作一张卡片，一次只能显示一张卡片，默认显示第一张卡片。 |
  | BorderLayout  | *边界布局*，把`Container`按方位分为 5 个区域（东、西、南、北、中），每个区域放置一个组件。 |
  | SpringLayout  | *弹性布局*，通过定义组件四条边的坐标位置来实现布局。         |
  | null          | *绝对布局*，通过设置组件在`Container`中的**坐标位置**来放置组件。 |

## 3.JAVA使用-Calculator制作

​	在学习java的过程中，之前有了c++的基础，所遇到困难不是很大，主要时swing包的学习何应用。在计算机的设计过程中，我将整个设计分为三个部分：主程序入口类，GUI类，button及其事件监听类。

```java
public class Calc{}	//主程序入口类

class calcFrame extends JFrame{} //GUI类

class button extends JButton{}	//button及其事件监听类
```

​	主程序入口类主要进行GUI的生成和执行，进行一些初始化操作；

```java
public static void main(String[] args) {
    calcFrame frame = calcFrame.getInstance();
    frame.init();
    frame.setVisible(true);
}
```

​	GUI类继承JFrame类，并使用之前介绍的swing包中JFrame、JPanel，JTextField，JButton等基本容器和组件，主要设计思路是：JFrame为整个窗口，设计好大小和布局；JPanel进行布局，通过嵌套等各种操作将JPanel合理布置在JFrame中，除此之外，JPanel里面容纳了JTextField和JButton等。

​	button及其事件监听类继承了JButton，这个类创建button元素，并且为每个button注册监听事件，处理点击的效果和逻辑。

```java
public button(String key){	
    super(key);		//初始化父类
    this.frame=calcFrame.getInstance();
    this.setBackground(new Color(65,201,252,50));
    this.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
            button btn = (button) event.getSource();
            handleInput(btn.getText());
        }
    });
}
```

​	主要细节：采用单例模式，GUI类只生成一个实例，一方面因为在这个计算机中只有一个JFrame窗口，另一方面是因为在主程序入口类需要产生一个GUI实例，并且进行初始化操作，在button类中也需要一个GUI实例来相应button点击的效果。采用单例模式是的逻辑更加清晰，并且更加符合逻辑。

```java
private static calcFrame frame;	//共享的静态实例
private calcFrame(){}
public static calcFrame getInstance(){
    if(frame == null)
    frame=new calcFrame();
    return frame;
}
```

​	在实现三个类各自的封装和联系之后，计算器的功能也就很好的实现；这计算器的GUI设计让我对java的样式布局包和接口有了初步的了解，接下来的学习希望可以更进一步。



#三、Ant安装与使用

##1.安装

1. 下载ant，下载地址：http://ant.apache.org/bindownload.cgi; 我下载的版本是ant-1.9.11

2. 解压到指定目录下：

   - ```
     tar -zxvf apache-ant-1.9.11-bin.tar.gz
     ```

   - ```
     sudo mv apache-ant-1.9.11 /usr/share
     ```

     或者将以上两个命令合并为一个：

     ```java
     sudo tar -zxvf apache-ant-1.9.11-bin.tar.gz -C /usr/share
     ```

3. 配置环境变量，打开原JDK的环境变量的配置文件

   - ```
     sudo vi /etc/profile
     ```

   - ```
     export ANT_HOME=/usr/share/apache-ant-1.9.11
     ```

   - ```
     export PATH=……:${ANT_HOME}/bin //在PATH中加上ant的bin目录路径
     ```

4. 验证ant是否安装成功：

   - ```
     source /etc/profile	//更新配置文件
     ```

   - ```
     ant -version	//查看安装ant的版本
     ```

## 2.使用

利用ant自动编译运行helloworld.java

Ant的核心就是配置文件build.xml，在build.xml文件中配置相关的任务后，使用ant命令即可自动执行，所以我们需要掌握ant的相关配置，这里先在桌面新建一个目录testAnt，然后进入该目录，新建一个build.xml文件，srcdir和destdir文件夹，分别放置helloworld.java源文件和helloworld.class字节码文件。

1. build.xml文件内容：

   ```xml
   <?xml version="1.0"?>
   <project name="helloworld_Ant" default="run_helloworld" basedir="">
   	<target name="compile_helloworld">
           <!--srcdir指定了java源文件所在的路径，destdir指定的是编译后的class文件存放的路径-->
   		<javac srcdir="srcdir" destdir="destdir" />	
   	</target>
   	<target name="run_helloworld" depends="compile_helloworld">
           <!--<java>标签中的classname属性，指定了要执行的main class名，<classpath>标签中的<pathelement>标签，指定了class文件的路径。-->
   		<java classname="helloworld">
   			<classpath>
   				<pathelement path="destdir" />
   			</classpath>
   		</java>
   	</target>
   </project>	
   <!--depends表示当前target必须在depends所代码的target执行完之后执行-->
   ```

   - build.xml需要遵循一定的格式，这样ant命令才能正确执行，一个build.xml文件是以`<project>`标签为根节点的，`<project>`节点中可以指定`name`属性，表示项目的名称，`basedir`代表项目的根目录，`default`表示项目的默认任务名，这里的`default`属性值为`run_helloworld`，则执行ant命令时会找`default`对应的`target`去执行（如果ant命令没有指定任务名的话）。
   - `<project>`标签中定义了一个`<target>`标签，该标签表示的就是一个任务，`<target>`标签中的name属性表示任务名，我们可以在命令行下直接使用`ant + 任务名`来执行某个特定的任务，例如上面的例子中，我们可以使用`ant run_helloworld`命令去执行`name`值为`run_helloworld`的`target`任务，由于我们在`<project>`标签中配置了`default`属性为`run_helloworld`，所以在命令行下只需要执行ant命令就可以运行`run_helloworld`任务了。

2. srcdir目录下helloworld.java文件内容：

   ```java
   public class helloworld{
   	public static void main(String [] args){
   		System.out.println("Hello, World!");
   	}
   }
   ```

   之后在终端testAnt文件夹下输入ant即可编译运行helloworld.java。



# 四、Junit的安装与使用

JUnit是一个开源的java单元测试框架。

## 1.安装

1. 下载Junit，下载地址：http://www.junit.org](http://www.junit.org/)我下载的版本是：junit-4.10.jar

2. 移动到指定目录:

   ```
   sudo mv junit-4.10.jar /usr/share
   ```

3. 配置：

   ```java
   vim ~/.bashrc
   //在文件末尾添加
   export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_91
   export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:/usr/share/junit-4.9.jar:$CLASSPATH
   export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH:$HOME/bin
   ```

4. 更新配置文件：

   ```
   source ~/.bashrc	//更新配置文件
   ```

## 2.使用

先在桌面新建一个目录testJunit，然后进入该目录，新建HelloWorld.java和HelloWorldTest.java文件。

1. HelloWorld.java文件内容：

   ```java
   public class HelloWorld {
       public String printHelloWorld(){
           return "Hello World!";
       }
       public static void main(String [] args){
           HelloWorld helloworld = new HelloWorld();
           System.out.println(helloworld.printHelloWorld());
       }
   }
   ```

   ​

2. HelloWorldTest.java文件内容：

   ```java
   import org.junit.Test;
   import static org.junit.Assert.assertEquals;
   import org.junit.runner.JUnitCore;
   import org.junit.runner.Result;
   import org.junit.runner.notification.Failure;

   public class HelloWorldTest{
       @Test
       public void testSayHello(){
           HelloWorld helloworld =new HelloWorld(); 
           assertEquals("Hello World!",  helloworld.printHelloWorld());
           assertEquals("Hello World",  helloworld.printHelloWorld());
       }
       public static void main(String args[]){
           Result result = JUnitCore.runClasses(HelloWorldTest.class);
           for(Failure failure : result.getFailures())
               System.out.println(failure.toString());
           System.out.println(result.wasSuccessful());
       }
   }
   ```

3. 然后进行测试：

   ```
   javac *java	//两个文件同时编译，如果编译成功则说明Junit配置成功
   java HelloWorldTest	//运行Junit单元测试
   ```



# 五、Ant和Junit实现helloworld自动进行单元测试

文件目录结构：

- testAntJunit
  - srcdir
    - HelloWorld.java	(代码同上)
    - HelloWorldTest.java  (代码同上)
  - destdir
  - build.xml

1. build.xml代码：

   ```xml
   <?xml version="1.0"?>
   <project name="helloworld_Ant_Junit" default="Junit_test_helloworld" basedir="">
   	<target name="compile">
   		<javac srcdir="srcdir" destdir="destdir" />
   	</target>

   	<target name="Junit_test_helloworld" depends="compile">
   		<junit>
   			<classpath>
                   <!-- //配置classpath之后可以不用再链接这些jar包
   				<fileset dir="/home/administrator/Downloads">
   					<include name="junit-4.10.jar" />
   				</fileset>
   				-->
   				<pathelement path="destdir" />
   			</classpath>
   			<batchtest> 
   				<fileset dir="destdir">
   					<include name="HelloWorldTest.class" />
   				</fileset>
   			</batchtest>
   		</junit>
   	</target>
   </project>
   ```

   ​

# 六、jar的了解及使用

## 维基百科介绍：

在[软件](https://zh.wikipedia.org/wiki/%E8%BD%AF%E4%BB%B6)领域，**JAR文件**（Java归档，英语：**J**ava **AR**chive）是一种[软件包](https://zh.wikipedia.org/wiki/%E8%BD%AF%E4%BB%B6%E5%8C%85%E6%A0%BC%E5%BC%8F)[文件格式](https://zh.wikipedia.org/wiki/%E6%96%87%E4%BB%B6%E6%A0%BC%E5%BC%8F)，通常用于聚合大量的[Java类文件](https://zh.wikipedia.org/w/index.php?title=Java%E7%B1%BB%E6%96%87%E4%BB%B6&action=edit&redlink=1)、相关的[元数据](https://zh.wikipedia.org/wiki/%E5%85%83%E6%95%B0%E6%8D%AE)和资源（文本、图片等）文件到一个文件，以便分发Java平台[应用软件](https://zh.wikipedia.org/wiki/%E5%BA%94%E7%94%A8%E8%BD%AF%E4%BB%B6)或[库](https://zh.wikipedia.org/wiki/%E5%87%BD%E5%BC%8F%E5%BA%AB)。[3][[3\]](https://zh.wikipedia.org/wiki/JAR_

JAR文件是一种[归档文件](https://zh.wikipedia.org/w/index.php?title=%E5%BD%92%E6%A1%A3%E6%96%87%E4%BB%B6&action=edit&redlink=1)，以[ZIP格式](https://zh.wikipedia.org/wiki/ZIP%E6%A0%BC%E5%BC%8F)构建，以`.jar`为[文件扩展名](https://zh.wikipedia.org/wiki/%E6%96%87%E4%BB%B6%E6%89%A9%E5%B1%95%E5%90%8D)。用户可以使用[JDK](https://zh.wikipedia.org/wiki/JDK)自带的jar命令创建或提取JAR文件。也可以使用其他`zip`压缩工具，不过压缩时zip文件头里的条目顺序很重要，因为[Manifest文件](https://zh.wikipedia.org/wiki/Manifest%E6%96%87%E4%BB%B6)常需放在首位。

### 可执行的JAR文件

一个可执行Java程序以及其使用的[库](https://zh.wikipedia.org/wiki/%E5%87%BD%E5%BC%8F%E5%BA%AB)文件可以打包在一个JAR文件中。

可执行的JAR文件中的Manifest文件用代码`Main-Class: myPrograms.MyClass`指定了入口点类，注意要指明该类的路径（-cp参数将被忽略）。有些操作系统可以在点击后直接运行可执行JAR文件。而更典型的调用则是通过命令行执行“`java -jar foo.jar`”。

### Manifest

在[Java平台](https://zh.wikipedia.org/wiki/Java%E5%B9%B3%E8%87%BA)中, [Manifest文件](https://zh.wikipedia.org/wiki/Manifest%E6%96%87%E4%BB%B6)是JAR归档中所包含的特殊文件。[[5\]](https://zh.wikipedia.org/wiki/JAR_(%E6%96%87%E4%BB%B6%E6%A0%BC%E5%BC%8F)#cite_note-5)[[6\]](https://zh.wikipedia.org/wiki/JAR_(%E6%96%87%E4%BB%B6%E6%A0%BC%E5%BC%8F)#cite_note-6)Manifest文件被用来定义扩展或档案打包相关数据。Manifest文件是一个[元数据](https://zh.wikipedia.org/wiki/%E5%85%83%E6%95%B0%E6%8D%AE)文件，它包含了不同部分中的[键-值对](https://zh.wikipedia.org/w/index.php?title=%E9%94%AE-%E5%80%BC%E5%AF%B9&action=edit&redlink=1)数据。如果一个JAR文件被当作可执行文件，则其中的Manifest文件需要指出该程序的主类文件。通常Manifest文件的文件名为`MANIFEST.MF`。

## 使用命令介绍

**jar命令**有**生成、查看、更新、解开**jar包的作用，包含**META-INF/MANIFEST.MF**文件。

它是**jar包**生成的时候，自动创建的，主要负责指定jar包的**main文件位置**和**当前文件夹**。

jar命令的参数详解：

| 参数 | 含义                                                    |
| ---- | ------------------------------------------------------- |
| -c   | 创建新的归档文件                                        |
| -t   | 列出归档目录                                            |
| -x   | 解压缩已归档的指定（或所有）文件                        |
| -u   | 更新现有的归档文件                                      |
| -v   | 在标准输出中生成详细输出                                |
| -f   | 指定归档文件名                                          |
| -m   | 包含指定清单文件中的清单信息                            |
| -M   | 不创建条目的清单文件                                    |
| -i   | 为指定的 jar 文件生成索引信息                           |
| -C   | 更改为指定的目录并包含其中的文件                        |
| -e   | 为捆绑到可执行 jar 文件的独立应用程序指定应用程序入口点 |

示例：jar命令打包时默认会在jar包中添加清单(manifest)文件，如果不想添加，手动指定`-M`选项：

```
>jar -cvf HelloWorld.jar HelloWorld.class   #将HelloWorld.class文件打入jar包
    已添加清单
    正在添加: HelloWorld.class(输入 = 427) (输出 = 289)(压缩了 32%)
>jar -tf HelloWorld.jar   #查看归档文件的内容
    META-INF/
    META-INF/MANIFEST.MF
    HelloWorld.class
>jar -xf HelloWorld.jar META-INF/MANIFEST.MF   #解压出其中的META-INF/MANIFEST.MF文件
>type META-INF\MANIFEST.MF   #查看清单文件的内容
    Manifest-Version: 1.0
    Created-By: 1.8.0_51 (Oracle Corporation)
>jar -cvfM HelloWorld.jar HelloWorld.class   #将HelloWorld.class文件打入jar包，不要添加清单文件
    正在添加: HelloWorld.class(输入 = 427) (输出 = 289)(压缩了 32%)
>jar -tf HelloWorld.jar
    HelloWorld.class
>jar -xf HelloWorld.jar   #解压tar文件到当前目录

```

如果要生成可以运行的jar包，需要指定jar包的应用程序入口点，用-e选项：

```
>jar -cvfe HelloWorld.jar HelloWorld HelloWorld.class   #创建可以运行的jar包
    已添加清单
    正在添加: HelloWorld.class(输入 = 427) (输出 = 289)(压缩了 32%)
>jar -tf HelloWorld.jar   #查看归档的内容
    META-INF/
    META-INF/MANIFEST.MF
    HelloWorld.class
>type META-INF\MANIFEST.MF   #查看清单文件的内容
    Manifest-Version: 1.0
    Created-By: 1.8.0_51 (Oracle Corporation)
    Main-Class: HelloWorld
>java -jar HelloWorld.jar   #运行jar包
    Hello World!!
```

查看jar包的文件列表：

```
jar vtf  HelloWorld.jar
```

# SonarQube

## 简介

Sonar 是一个用于代码质量管理的开源平台，用于管理源代码的质量，可以从七个维
度检测代码质量。



