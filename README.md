# springboot-all
Method: Get 和 Post
1、Get是用来从服务器上获得数据，而Post是用来向服务器上传递数据。

2、Get将表单中数据的按照variable=value的形式，添加到action所指向的URL后面，并且两者使用“?”连接，而各个变量之间使用“&”连接；Post是将表单中的数据放在form的数据体中，按照变量和值相对应的方式，传递到action所指向URL。

3、Get是不安全的，因为在传输过程，数据被放在请求的URL中，而如今现有的很多服务器、代理服务器或者用户代理都会将请求URL记录到日志文件中，然后放在某个地方，这样就可能会有一些隐私的信息被第三方看到。另外，用户也可以在浏览器上直接看到提交的数据，一些系统内部消息将会一同显示在用户面前。Post的所有操作对用户来说都是不可见的。

4、Get传输的数据量小，这主要是因为受URL长度限制；而Post可以传输大量的数据，所以在上传文件只能使用Post（当然还有一个原因，将在后面的提到）。

5、Get限制Form表单的数据集的值必须为ASCII字符；而Post支持整个ISO10646字符集。

6、Get是Form的默认方法。

GET 和 POST 的数据格式都是一样的：
GET 支持的最大字节限制是 2048 Bytes
POST 支持的最大字节限制是 2GB 
GET请求请提交的数据放置在HTTP请求协议头中，而POST提交的数据则放在实体数据中； 
GET方式提交的数据最多只能有1024字节，而POST则没有此限制。 

web服务器调用相应的 jsp/servlet来响应客户端请求的时候，对于post的请求，web服务器已经把客户端提交的数据取出来，添到request对象中去了。不过，对于get、post的请求servlet 的 doGet、 doPost方法会被响应调用。也就是说如果客户端送来的是一个get的请求，那么你写到servlet中的dopost()方法中的代码是不会执行的，反之如果是post的请求，写在doget()中代码是不会被调用的（对于所有方式的请求，写在doservice的代码是会被调用的，因为在HttpServlet类中doGet doPost的请求都是由doService来分发的.
