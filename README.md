# Android MVP 不一样的实现方案

### 前言
------
模式设计开发的目的很简单：为了更好的管理项目代码。

苹果官方一直推崇MVC模式，Google也为开发者设计出MVP，MVVM两种模式。不管哪种方式都是为了解决项目壮大后臃肿的问题。

本文只是说明一种见解，提供一种思路而非框架，也希望它能抛砖引玉。

### Android上的MVC
------
Android上MVC上其实也比较明显。

Android上对Context依赖性很强，Android上很多操作依赖Context来执行，而Activity也是经典的Context，[Activity也就是纯天然的“C”](https://www.zhihu.com/question/19766132)。

View布局控件刚好是V，这点无争议。Android上控件也都只会包含显示的逻辑，我们为了实现某种功能的独立，也会把它封装成自定义控件。

至于Model层，不同APP需求不同，也不是本文讨论重点。

### 对MVP的见解
------
在原本的设计中，大量的功能集合在Activity中。虽说移动设计一切从简，但功能向复杂化发展不可避免。Google为了解决这种混乱，推出了Fragment来拆分原有的Activity。其实在Fragment出现之前，类似的情况下（比如存在多个tab的界面），也是采用多个类似Fragment的对象来切割Activity功能。

然而事实证明Fragment依然存在局限性，Fragment只是切割功能，而功能内部业务逻辑与界面逻辑的混乱却无法解决。所以MVP解决方案由此而生。（[MVC与MVP的差异直观的介绍](http://blog.csdn.net/duo2005duo/article/details/50594757/)）

Google官方也推出了官方MVP示例[TODO-MVP](http://blog.csdn.net/lavor_zl/article/details/51180537)。但是本人觉得在这个项目里Activity与Fragment的局面比较尴尬，原本属于C的Fragment被分配到V，Activity依然是调度者，但似乎与Presenter的角色有些重复（再次声明Activity是Context，这点很重要！），假设去除Fragment，Activity到底属于什么。

### 设计的想法
------
所以我的想法是，View层使用一个类来封装所有View控件的修改和事件监听，View与Presenter通讯通过接口隔离。

Presenter只负责业务上的逻辑，做一个更纯粹的业务员。P由Activity和Fragment来实现，这样也更能体现两者在原有Android架构上的地位。

Model层根据需求各有不同，此处不做详解。

| View        | Presenter    |  Model  |
| ----- | -----  | ---- |
| ViewContent	|Activity / Fragment	|OkHttp/sqlite...|
| 实现所有View逻辑，隔离用户与P |业务逻辑，隔离V和M，通过接口与两者交互 |数据交互|

### 后话
------

学习编程的重点是学习其思想，而不是学习如何使用一套框架。假如有天找不到合适的轮子，我们可以自己造一个。

OOP（面向对象）出现的时候，人们以为POP（面向过程）的时代即将结束。然而这么多年过去了，现实告诉我们，他们各有各的世界。而编程世界里，活的最坚强的其实是BOP（面向Boss编程），认真你就输了！

不管怎样，合适的才是最好的，MVP，MVC，MVVM都是为了寻找一种更合适的解决方案。

### 附上例子
------
根据上面的设计，此处附上一个例子[Android MPV Demo](https://github.com/Yeamy/MVPDemo)：

#### Content View
继承自ContentView的类负责View的逻辑，比如TextView修改文字，Button受点击，或者列表滚动时，ActionBar改变透明度...

通过接口实现与P的通讯。在数据交互时会直接传递Bean，原则上只能对其读取不能修改。

#### Activity & Fragment
这两个做为有生命周期特征的业务，我们可以理解为业务本身也有周期性，其实并没有什么毛病。

如果觉得它们的生命周期特征对业务的实现有影响，别忘了还有后台Server这个特殊的Context可以来帮忙。这样做的设计更符合Android平台特征。

#### Model层
由于只是演示，所以没有对Model做具体实现，只是打了个基础。

##### Request & Task
task目录下为后台任务处理，根据项目选择网络框架，序列化工具。
请求通过Request传递，一个Request可以对应一个网络接口或者一个数据库读取操作，一件本地任务，一套任务逻辑。然后把处理的结果传递给P，P再从Request中取得真正的数据Data Bean。

##### Data Bean
所有的Bean类保存在data目录下，他们仅仅是数据结构，所提供的方法只是为了更好的表达自己，或者是方便修改自己的工具。

#### License

Copyright 2017 Yeamy.
	
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
	
http://www.apache.org/licenses/LICENSE-2.0
	
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
