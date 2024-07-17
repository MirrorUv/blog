# blog

因为有些接口需要登录后才能查看，所以通过自定义注解@RequireLogin和拦截器实现了这个功能，用户登录是会返回一个token,把token放到header里面就能继续访问。

在注册方面，用户名不能重复。



获取某个用户的所有文章列表 有四个参数 

@param *uid* 文章id

@param *page* 第几页  默认 0

@param *size* 某页有多少数据  默认 10

*@param* *sort* *排序方式 * 默认 asc 



