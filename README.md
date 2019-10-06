# SeckillingSystem
模拟商品秒杀场景

系统分为五个module，
BaseModule module 负责提供整个项目的基本实体类及基本的jpa操作，提供项目的entity和dao；
Compute module负责完成商品的抢购操作，实现对数据库的修改操作；
Order module实现订单的查询删除操作，查看购买商品形成的订单是否成功；
Shopping module实现与用户交互，接收用户发送的立即购买商品请求信息；
ShoppingCart module负责完成购物车的相关操作，包括查询购物车中的商品，删除购物车中的物品以及清空购物车。

