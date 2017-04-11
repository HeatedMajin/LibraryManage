完成前端页面的开发
登录界面，注册界面，用户操作界面

1.搭建开发环境，导入所用的jar

2.按照mvc模式，创建使用的包
	dao层:
		cn.majin.domain
		cn.majin.Dao
		cn.majin.DaoImpl
		
	service层：
		cn.majin.service
		cn.majin.serviceImpl
		工具包：
		cn.majin.Utils
		
	web层：
		cn.majin.Web.user
		cn.majin.Web.book
		cn.majin.web.filter
		WEB-INF/jsp
		
	异常包：
		cn.majin.Exception
	
	测试包：
		Junit.test
		
3.创建book的javabean对象
	 String id --- 书的编号
	 String name --- 书的名字
	 String author --- 书的作者
	 Date publishdate --- 出版日期
	 String detail --- 书的简介
   	
	 
4.创建操作数据库的Dao接口，定义如下方法：
	int doUpdate(String SQL);
	List doSelect(String SQL); --- 做查询时，直接处理的时查询语句，所以需要写一个工具类来专门产生SQL

5.实现BookDao接口,实现上述方法。

	--- 发现四个方法中有 链接数据库 、 断开数据库连接 的公共代码 --- 抽取为公共方法到工具类中（JDBCUtils）
	--- 发现链接数据库的驱动名、链接的url、用户名、密码 写定了不太好 --- 抽取到配置文件中 --- 同时创建读取配置文件的工具类(ProperUtils)
	
6.对工具类JDBCUtils、ProperUtils进行测试
      对dao层的BookDaoImpl类进行测试
  
    ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
    ！！！！这里出现了很伤的问题，使用命令行向表中添加数据后，在命令行中可以查到东西，！！
    ！！！ ！但是，在java程序中却得不到任何数据。关闭命令行后，再重新打开。发现又查不到了。
    ！！！！  恍然，对表执行的更改类操作需要 “提交” 以后才会生效			rs.get	！！！
    ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 
      测试好了……
      
7.创建user的javabean对象
    String username --- 用户的名称
    String gender   --- 用户的性别
    String password --- 用户的密码
   	String email	--- 用户的邮箱
   	String phone	---用户的电话号
   	Date birthday 	--- 用户的生日
   	String preference --- 用户的爱好
   	
  创建数据库中的User表
  create table users(
  	username varchar(10) primary key,
  	gender varchar(6),
  	password varchar(20) not null,
  	phone varchar(20),
  	birthday Date,
  	email varchar(20),
  	preference varchar(100)
  );
  
 向表中插入测试数据
 insert into users (username,gender,password,phone,birthday,email,preference) values('123','male','123','123',sysdate,'123@163.com','football');
 insert into users (username,gender,password,phone,birthday,email,preference)  values('333','male','123','123',sysdate,'123@163.com','football');
 insert into users (username,gender,password,phone,birthday,email,preference)  values('222','male','123','123',sysdate,'123@163.com','football');
 insert into users (username,gender,password,phone,birthday,email,preference) values('111','male','123','123',sysdate,'123@163.com','football');

   	
	 

8.实现UserDao接口,实现Dao的上述方法。但是，发现这个类的方法实现与BookDao提供的大部分一样。
  于是，将两个类进行合并.将Dao接口改变成：
	User findUser(String name,String password)
	void addUser(User u)
	boolean isExist(String name)
	void editUser(User u)	 
	
	
9.测试通过

-----DAO层开发到此完成
	
10.开发用户登录和注册模块
	在service层创建接口，提供登录和注册
	user login(String username , String password);
	在数据库中搜索username对应的元组，看看用户名和密码是不是匹配
	若匹配则完成登录
	若不匹配则不允许登录
	
	boolean register(String username);
	先检查用户名是不是已经存在
	当是合法的用户名，向数据库中添加
	
	void editUser(User u)
	修改一个用户的信息

-----Service层开发完成


11.web层的开发
	login.jsp提供用户的登录界面（设为web应用的欢迎界面）
	login.java servelt
	register.jsp提供用户的注册界面
		（传递参数register）
	UserFormBean.java--register()注册表单
	formcheck.java (servelt)
	index.html采用分帧，
		top.jsp上显示"您好，用户名 注销"
		left.jsp显示供用户的操作
			|--	查看账号信息
			|-- 修改账号信息
			
			|-- 添加书本
			|-- 按照条件筛选书本
			|-- 提供自然语言查询
	
			|--	本版介绍
			|--	意见反馈
	
-----------------登录和注册功能完成

12.修改用户信息
	left.jsp
	EditUserServlet.java(Servlet)
	EditUserInfo.jsp --- 提供信息修改的表单（上面有原本信息为默认值）
		（传递参数edit）
	FormCheck.java(Servlet) 
	UserFormBean.java---register()进行修改
	notice.jsp --- 显示操作是否成功
	
	
13.显示用户信息
	left.jsp
	ShowUserServlet.java(Servlet)
	UserInfo.jsp --显示用户信息
	
----显示和修改用户信息开发完成

14.创建book的javabean对象
	 String id --- 书的编号
	 String name --- 书的名字
	 String author --- 书的作者
	 Date publishdate --- 出版日期
	 String detail --- 书的简介
  创建数据库中的book表
  create table book(
  	id varchar(30) primary key,
  	name varchar(30) not null,
  	author varchar(30),
  	publishdate Date,
  	detail varchar(300)
  );
  
 向表中插入测试数据
 insert into book (id,name,author,publishdate,detail) values('1234567890','情人','杜拉斯',sysdate,'杜拉斯代表作之一，自传性质的小说，获一九八四年法国龚古尔文学奖。全书以法国殖民者在越南的生活为背景，描写贫穷的法国女孩与富有的中国少爷之间深沉而无望的爱情。');
 insert into book (id,name,author,publishdate,detail) values('2234567890','挪威的森林','村上春树',sysdate,'这是一部动人心弦的、平缓舒雅的、略带感伤的恋爱小说。小说主人公渡边以第一人称展开他同两个女孩间的爱情纠葛。');
 insert into book (id,name,author,publishdate,detail) values('3234567890','无人生还','阿婆',sysdate,'最后的结局出乎意外。可以颠覆你的思维。侦探小说。最后的结局出乎意外。可以颠覆你的思维。侦探小说最后的结局出乎意外。可以颠覆你的思维。侦探小说');
 insert into book (id,name,author,publishdate,detail) values('4234567890','白夜行','东野圭吾',sysdate,'我的天空里没有太阳，总是黑夜，但并不暗，因为有东西代替了太阳。虽然没有太阳那么明亮，但对我来说已经足够。凭借这份光，我便能把黑夜当成太阳。我从来就没有太阳，所以不怕失去。——白夜行 ');
 insert into book (id,name,author,publishdate,detail) values('1111111111','百年孤独','马尔克斯',sysdate,'魔幻现实主义 写了一个家族的百年兴衰。豆瓣评分9分以上。看了一个月，名字很长很复杂，我看这本书的时候是靠着感觉的，感觉上知道谁是谁，看此书需要耐心，平静下来。'); 	
 insert into book (id,name,author,publishdate,detail) values('1111111222','天才在左，疯子在右','高铭',sysdate,'本书以访谈录的形式记载了生活在另一个角落的人群（精神病患者、心理障碍者等边缘人）深刻、视角独特的所思所想，让人们可以了解到疯子抑或天才真正的内心世界。此书是国内第一本具有人文情怀的精神病患谈访录。');             	
15.对book进行操作的Dao层

	BookDaoImpl
		|-- void addBook(Book b)
		|-- List getBooks(String sql)
		|-- void delBook(String id)
		|-- void eitBook(Book b) 

16.对book进行操作的Service层
	
	BookServiceImpl
		|-- void addBook(Book b)
		|-- List getBooks(String sql)
		|-- void delBook(String id)
		|-- void editBook(Book b)
		
17.进行web层的开发
	为left.jsp“筛选书本”添加响应
		（参数filter）
	SearchBookServlet.java(Servlet)
	filterSearch.jsp --- 分帧
		分帧|--filterSearchTop.jsp 提供几个筛选项
			(提交筛选)
			在filterSearchMain帧中，SearchBookServlet.java
			筛选选项保存到SearchConditionBean中，generate()生成sql
			执行查询，结果放到List集合中，再放到request域中，转发到listBook.jsp显示
			改进界面listBook.jsp改为listBookLikeBaidu.jsp,像百度搜索那样来显示

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
出现问题，首先网页中中文 到Bean中后是乱码
那么，一定是从request中读取数据时编码不对，
request.setCharacterEncoding("UTF-8");
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
其次，数据库的日期查询只能是 “dd-M月 -yy”格式
想把格式更改，但是不知道该咋整
只能改变客户端传递的日期格式
！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！


18.将搜索的结果进行分页显示
	三个对象
	QueryInfo
		|-- currentPage
		|-- pageSize
	QueryResult
		|-- totalSize
		|-- List context
		|-- 
	PageBean
		|-- totalSize
		|-- List context
		|-- totalPage
		|-- pageSize
		|-- currentPage
		|-- previousPage
		|-- nextPage
		|-- int[] p
		
		
		
		

V5:
1.	使用数据库连接池来提升数据库的访问性能
	重新使用DBCP、和DBUtils的工具来开发

2.	修改逻辑
	用户层修改密码 和修改信息分开
	

V5.1
	加入记住密码功能
过滤器
	1.编写过滤器，解决全站乱码
	2.编写过滤器，实现jsp页面不存缓(好像不管用的样子)
	3.编写过滤器，实现静态资源的存缓控制
	4.编写过滤器，实现全站的压缩(压缩时，不要进行全站的压缩，只进行显示页面的压缩即可)
	5.编写过滤器,检查登录状态,必须确保所有操作实在用户登录状态下完成的

	
V5.2
	
		
	增加role表、user_role表
		
		create table role(
			id varchar(10) primary key,
			name varchar(255) not null
		)
		
		
		一个用户可以拥有多个角色，一个角色可以被多个用户拥有
		create table user_role(
			username varchar(10) not null,
			role_id varchar(10) not null,
			constraint user_role_PK primary key (username,role_id),
			constraint user_id_FK_user_role foreign key (username) references users(username),
			constraint role_id_FK_user_role foreign key (role_id) references role(id)
		)
		
		
		分身份：
	一：学生
		自身信息的所有操作
		只能查找书
		
	二：图书管理员
		自身信息的所有操作
		对书本的所有操作

	三：数据库管理人员
		授予角色
		授予权限
		添加资源
		查看数据库的记录（查看谁在什么时候改动了数据库、改了什么） --->触发器
		
		create table log4change(
			op_table varchar(40) not null,
			op_type varchar(10) not null,
			op_user varchar(10) not null, 
			op_date date not null
		);
		
		//创建行级触发器（表级触发器不允许使用NEW,OLD）			 	
		create or replace trigger tri_dml_op_users
			before delete or insert or update
			on users
			for each row
		declare
			op_table varchar(40);
			uname users.username%type;
			var_tag varchar(10);
		begin
			op_table := 'users';
			if inserting then
				var_tag :='注册账号';
			elsif updating then 
				var_tag := '修改账号';
			elsif deleting then
				var_tag := '删除账号'; 
			end if;
			
			if inserting then 
				uname := :new.username;
			else
				uname := :old.username;
			end if;
			
			insert into log4change values(
				op_table,
				var_tag,
				uname,				
				sysdate
			);
		end tri_dml_op_users;		
		
		
		drop trigger tri_dml_op_users;
		
		
		//对书本的记录
		create or replace trigger tri_dml_op_book
			before delete or insert or update
			on book
			for each row
		declare
			op_table varchar(40);
			var_tag varchar(10);
		begin
			op_table := 'book';
			if inserting then
				var_tag :='添加新书';
			elsif updating then 
				var_tag := '修改书本';
			elsif deleting then
				var_tag := '删除书本'; 
			end if;
			
			insert into log4change values(
				op_table,
				var_tag,
				'unknown',	//这里我们无法获取是谁改了			
				sysdate
			);
		end tri_dml_op_book;
		
		
		//必须在业务逻辑层在对用户登记
		update log4change set op_user = ?,op_type=op_type||?  where op_user = 'unknown'
		
		//表的op_type太小放不下
		alter table log4change modify op_type varchar2(50);
		
		(ORA-00054: 资源正忙, 但指定以 NOWAIT 方式获取资源, 或者超时失效) 这是什么鬼？？
		GG。。。重启数据库
		
		//查看有哪些触发器
		select trigger_name from all_triggers;
		
		
增加后台管理员的一个逆天权限：封号，解封
		
		创建一个表记录被封的号
		create table shutUser(
			username varchar(10),
			constraint shutUser_username_FK foreign key (username) references users(username)
		)

V5.3 在用户登录时，根据用户的不同身份进行不同的登录显示
	
	学生：个人信息 、找书
	图书管理员：个人信息、找书、改书、填书、删书
	数据库管理员：进入后台管理界面

	使用自定义标签

V5.4 提供借书功能
	create table borrow(
		username varchar(10),
		book_id varchar(30),
		borrowDate date,
		constraint borrow_username_bid_PK primary key(username,book_id),
		constraint borrow_username_FK foreign key(username) references users(username),
		constraint borrow_bookid_FK foreign key(book_id) references book(id)
	);
	
	给学生在找书时，点开详细信息，可看到书是不是可借的
	当是已借阅的，显示被谁借走了,被借走的时间。同时，“借阅”按钮要隐藏。
	当是可借阅的，显示“借阅”按钮。同时，为借阅事件添加处理类，实现借阅。
	
	在左侧的“书本操作”中，添加一个“查看自己的借阅”，显示已经借阅的书。（仍是学生可使用）
	同时，在相应的书本后面，提供“还书”功能。
	
	在左侧的“书本操作”中，添加一个“查看所有用户的借阅”，显示所有借阅的书（仅图书管理员可用）
	不提供任何操作
	
	
	
	
	给图书管理员提供查看借阅情况、借阅统计、生成报表
	
	借阅统计--->触发器（每次借书，修改记录表中数据）
	//创建记录表，记录每本书被借阅的次数
	create table borrowPerBook(
		book_id varchar(10) primary key,
		borrow_count int default(0),
		constraint borrowTrail_FK_book_id foreign key(book_id) references book(id)
	)

	//创建触发器,当有进行借书时，向borrowTrail中进行登记，记录每本书被解约的次数
		create or replace trigger tri_dml_op_borrowPerBook
			before  insert 
			on borrow
			for each row
		declare
			existcolumn borrowPerBook.book_id%type;
		begin
			select book_id into existcolumn
			from borrowPerBook where  book_id = :new.book_id;
			update borrowPerBook
			set borrow_count = borrow_count+1 
			where book_id = :new.book_id;
			exception when no_data_found then
				insert into borrowPerBook(book_id,borrow_count)
				values(:new.book_id,1);
		end tri_dml_op_borrowPerBook;	
		
		//创建记录表，记录每个月借阅量
		create table borrowPerMonth(
			year varchar(4),
			month varchar(2),
			borrow_count int default(0)
		)
		
		加入测试数据
		insert into borrowPerMonth values('2017','01',30);
		insert into borrowPerMonth values('2017','02',40);
		insert into borrowPerMonth values('2017','03',50);
		insert into borrowPerMonth values('2017','04',30);
		insert into borrowPerMonth values('2017','05',80);
		insert into borrowPerMonth values('2017','06',20);
		insert into borrowPerMonth values('2017','07',90);
		insert into borrowPerMonth values('2017','08',10);
		insert into borrowPerMonth values('2017','09',100);
		insert into borrowPerMonth values('2017','10',30);
		insert into borrowPerMonth values('2017','11',40);
		insert into borrowPerMonth values('2017','12',20);
		
		//创建触发器，当发生借书时，做出相应的记录
		create or replace trigger tri_dml_op_borrowPerMonth
			before  insert 
			on borrow
			for each row
		declare
			test int;
		begin
			select borrow_count into test
			from borrowPerMonth where year=to_char(sysdate,'yyyy') and month=to_char(sysdate,'mm');
			update borrowPerMonth
			set borrow_count = borrow_count+1 
			where year=to_char(sysdate,'yyyy') and month=to_char(sysdate,'mm');
			exception when no_data_found then
				insert into borrowPerMonth(year,month,borrow_count)
				values(to_char(sysdate,'yyyy'),to_char(sysdate,'mm'),1);
		end tri_dml_op_borrowPerMonth;	
		
		
V5.5
	最后一项任务：自然语言			
		
	bug1：查看数据库变动时，修改用户并不全，有的没有  --> 要不要写存储过程？
	bug2:事务性	   -- >		事务过滤器
	bug3:jsp脚本攻击   -- > 	转义过滤器 （好像没啥用）
	bug4:自动更新词库
	（woc，很纯啊，为什么要用文件保存词库，使用数据库啊）
	
	
V5.6
	模糊查询
	
V5.7
	（前台）查看自己的借阅，最好分页
		     查看所有人的借阅，最后页分页
		     数据报表 也分页 (下一页显示 下一年份/另外一组书的借阅量)
	（后台）显示所有数据库变动
		     显示所有用户 也分页	
V6(UI升级)
	查询结果标注红色
	css抽取
	servlet合并
	支付宝接入
	图片校验码
	
	完结~~~~~
		
		sql优化：
			1.触发器
			2.存储过程
			3.函数
			4.请求级事务
			5.数据库连接池
		
			   
select *
from (
select rownum rn，e.*
from (select * from emp) e
）
where rn >=5 and rn<=10	
    	
	
	

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	