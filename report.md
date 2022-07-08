<div align=center>
<img src="http://img.070077.xyz/202204021121013.png" width="50%" />
</div>



---

<div align=center><font size='70'>《面向对象程序设计实践（Java）》大作业报告</font></div>


---



### 成员列表

|    姓名    |    学号    |    班级    |
| :--------: | :--------: | :--------: |
| **王祥龙** | 2020211415 | 2020211322 |

### 需求分析

设计一个贩卖各类书籍的电子商务网站的购物车系统。在需求文档中提到了三种降价策略和一种不降价策略，将所有策略进行实现，并作为销售物品的其中一个成员计算优惠后的价格，并在其基础上进行一定的接口扩展，增强其实用性。

### 抽象和分类

首先根据UML标准画出类图。

![类图](https://s3.bmp.ovh/imgs/2022/07/07/1c779bb09047192f.png)

首先是完成主体功能的Sale类，其包括属性items，用于存储所有项目，提供`getTotal()`函数计算所有书目优惠后的价格。`Sale`与`SaleLineItem`是聚合关系，每一个书目都可以作为独立的个体存在。其包括每种书的本书、详细信息与销售策略。详细信息为类`ProductSpecification`，其与`SaleLineItem`是组合关系，其单独一部分的意义是抽象的，不能独立存在。`ProductSpecification`中具有属性`isbn`,`price`,`title`,`type`,方法均为属性的getter。同样的，`SaleLineItem`与`IPricingStrategy`也是组合关系，在初始化时通过`PricingStrategyFactory`来通过判断类别提供响应的策略。`PricingStrategyFactory`是工厂模式和单子模式的体现，包含一个类名为自身的私有静态对象，通过公共方法`getInstance()`来进行实例的调用。单子模式分为饿汉式单例类和懒汉式单例类，这里使用了饿汉式。饿汉式在资源占用上略大于懒汉式，但在反应时间与速度上要快于懒汉式。单子模式对于防止多开窗口造成资源浪费的现象十分有用。同时工厂类中还包括每本书的折扣价和折扣的百分比，这些对应三种书目的折扣幅度，设置成变量是为了后面新增的接口做准备。有一个get定价策略的方法，这是工厂模式的体现，相当于从外部将类型丢进这个方法，其就会自动加工成定价策略的类型并返回给外部，从而提供策略的自动化初始化。`IPricingStrategy`是其返回的类型，也是众多定价策略的一个接口，还是`SaleLineItem`的一个组成部分（组合关系）。这里有三个定价策略，均只实现了接口的抽象函数，这些类要在工厂中进行选择性的实例化。作为拓展功能，这里新增了`ShowOnScreen`类作为可视化的显示类。其下的四个`MyJDialog`为前缀的类同样使用了单子模式，在可视化界面中体现为同时只会存在一个相同的界面，节约内存资源。

#### 基础功能/模块：

•`Sale`：提供计算优惠后总价的方法。

•`SaleLineItem`：每个书目的类，包含份数、详细信息与降价策略。提供计算该书目的优惠后总价的方法。

•`ProductSpecification`：包含ISBN号、书名、价格和类型。

•`IPricingStrategy`：降价策略的接口，与无降价措施、按百分比降价与按价格降价这三个类为泛化关系，提供计算某销售项目根据该降价策略实施后的价格的抽象方法。

•`PricingStrategyFactory`：策略工厂，用于根据类型返回对应的策略，同时采用单子模式，防止资源占用太大

#### 拓展功能/模块：

•为每个页面嵌套了单子模式（4个`MyJDialog`前缀的类）

•将本次购物清单输出到文件（`log`方法）

•新增了添加书目的接口

•新增了修改降价幅度的接口

然后画出对象图与活动图：

![](https://s3.bmp.ovh/imgs/2022/07/06/560263cf1e08205f.png)

![](https://s3.bmp.ovh/imgs/2022/07/06/d9f0edceba0924e2.png)

这里不再详细阐述。

### 细节处理

#### 拓展功能

在作业要求的基础上，我还完成了以下三个拓展功能：

- 添加某样商品至购物车
- 调整降价幅度
- 输出单次购物清单至文件中

#### 单子模式的页面

所有子页面打开时均嵌套了单子模式，无论点击多少次仅打开一个页面。

#### 单价与折扣价的互相制约

在添加至购物车页面如果单价小于折扣价则会弹出错误提示

```java
if(type == 0 && price < PricingStrategyFactory.getDiscountPerBook()) 
    throw new PriceException();
```

在修改降价幅度界面如果折扣价大于单价也会弹出错误提示

```java
case 1:
    for(int i = 0; i < items.size(); i++)
    {
        if(Integer.parseInt(textField.getText()) > ((SaleLineItem)items.get(i)).getProdSpec().getPrice())
            throw new PriceException();
    }
    PricingStrategyFactory.setDiscountPerBook(Integer.parseInt(textField.getText()));
    break;
```

#### 其余的异常处理

本程序针对几乎所有的异常均做出了相应的处理，具体可在`ShowOnScreen`类下查看。

### 效果展示

具体的效果演示见视频[Java期末大作业验收运行展示](https://www.bilibili.com/video/BV1eU4y1D7G7?share_source=copy_web)，这里仅提供基本操作的截图。

![](https://s3.bmp.ovh/imgs/2022/07/07/fdfa890bf98ae5d1.png)

![](https://s3.bmp.ovh/imgs/2022/07/08/acdcc87b0064b1fc.png)

![](https://s3.bmp.ovh/imgs/2022/07/07/aff4c8cf0c3ae05a.png)



![](https://s3.bmp.ovh/imgs/2022/07/07/f33e9e8e36c007e3.png)

![](https://s3.bmp.ovh/imgs/2022/07/08/b9075aaafe836d34.png)

### 遇到的问题与解决方法

#### 关于修改降价策略幅度的问题

在第一次编写修改模块时，发现在修改完成员变量后并没有起到相应的效果。经分析后，发现所有图书的降价策略部分在items初始化时便确定并没有再更改，因此这里需要修改完变量后对items重新进行一次初始化。

#### 关于可视化界面的刷新

在初版系统中，程序在添加新数目后无法将添加内容立即添加进结算页面，必须再点一下其他按钮才会出现，经过测试后发现统一在container上布局，并在响应函数的末尾设置`jFrame`可见即可做到实时刷新页面。

### 思考与总结

本次Java大作业让我更深刻地认识到了工厂、单子和策略三种设计模式的具体实现，并让我对设计模式有了浓厚的兴趣以及更深刻的理解。相比起c++，Java具有很棒的垃圾回收机制，让程序员能够专注于编程与设计模式本身而不用在意内存管理、指针释放这些复杂问题。而面向对象编程的思想也为设计模式提供了基础。在今后的学习中，我会更加重视设计模式的学习与感悟，让我对面向对象的思想拥有更为深刻的理解，为项目开发打下扎实的基础。

---