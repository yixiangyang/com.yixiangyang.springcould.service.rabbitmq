# com.yixiangyang.springcould.service.rabbitmq
#
##1、rabbitmq 交换机有四种类型 direct 直接fanout 扇出交换topic 主题交换 headers 标题交换  
##direct交换  
直接交换基于消息路由密钥将消息传递到队列. 工作原理:队列用路由密钥K绑定到交换机,当具有路由密钥R的新消息到达直接交换时，
如果K = R，则交换机将其路由到队列,直接交换通常用于以循环方式在多个工作者（同一应用程序的实例）之间分配任务  
![Image text](https://github.com/yixiangyang/com.yixiangyang.image.respository/blob/master/rabbitMqImage/exchange-direct.png)
##fanout 扇出交换  
扇出交换将消息路由到绑定到它的所有队列，并忽略路由密钥。如果N队列绑定到扇出交换，则当向该交换发布新消息时，该消息的副本将被传递到所有N个队列。扇出交换是消息广播路由的理想选择  
![Image text](https://github.com/yixiangyang/com.yixiangyang.image.respository/blob/master/rabbitMqImage/exchange-fanout.png)
##topic 主题交换  
主题基于消息路由密钥与用于将队列绑定到交换的模式之间的匹配来交换将消息路由到一个或多个队列。主题交换类型通常用于实现各种发布/订阅模式变体。主题交换通常用于消息的多播路由。  
##headers 标题交换  
标头交换设计用于在多个属性上进行路由，这些属性更容易表示为消息头而不是路由密钥。标头交换忽略路由密钥属性。相反，用于路由的属性取自headers属性。如果标头的值等于绑定时指定的值，则认为消息是匹配的。  
