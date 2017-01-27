#这个东西理解还不够
* 就是SocketChannel会使东西自动产生可中断套接字
* Socket打开线程后会使
`ServerSocket s=new ServerSocket(8189); 
 Socket incoming=s.accept(); `这个会使一直扫描端口8189所以会导致线程阻塞
