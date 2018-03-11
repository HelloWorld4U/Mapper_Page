package cn.itcast;

import cn.itcast.mapper.UserMapper;
import cn.itcast.model.User;
import cn.itcast.util.SqlSessionUtils;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class MapperTest {

    private SqlSession sqlSession;

    private UserMapper userMapper;

    @Before
    public void init(){
        sqlSession = SqlSessionUtils.getSession();
        userMapper=sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("小红");
        user.setAge(18);
        user.setBirthday(new Date());
        user.setCreated(new Date());
        userMapper.insert(user);
        sqlSession.close();

    }

    @Test
    public void testInsertSelective(){
        User user = new User();
        user.setName("小黑");
        user.setAge(18);
        user.setUserName("xixixi");
        //user.setPassWord("11111");
        user.setBirthday(new Date());
        user.setCreated(new Date());
        userMapper.insertSelective(user);
        sqlSession.close();
    }

    @Test
    public void testSelectCount(){
        User user = new User();
        user.setName("小黑");

        //int account=userMapper.selectCount(user);
        int account=userMapper.selectCount(null);
        System.out.println(account);
        sqlSession.close();
    }


    @Test
    public void selectByPrimaryKey(){


        //int account=userMapper.selectCount(user);
        User user1 =userMapper.selectByPrimaryKey(4L);
        System.out.println(user1);
        sqlSession.close();
    }

    @Test
    public void delete(){

        User user = new User();
        user.setName("小黑");
        int account= userMapper.delete(user);
        System.out.println(account);
        sqlSession.close();

    }

    @Test
    public void deleteByPrimaryKey(){

        int account= userMapper.deleteByPrimaryKey(7L);
        System.out.println(account);
        sqlSession.close();

    }


    @Test
    public void updateByPrimaryKey(){

        User user = new User();
        user.setId(11l);
        user.setName("小黑111");
        int account= userMapper.updateByPrimaryKey(user);
        System.out.println(account);
        sqlSession.close();

    }

    @Test
    public void updateByPrimaryKeySelective(){

        User user = new User();
        user.setId(12l);
        user.setName("小黑111");
        int account= userMapper.updateByPrimaryKeySelective(user);
        System.out.println(account);
        sqlSession.close();

    }

    @Test
    public void updateByExampleSelective(){

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> ids = new ArrayList<>();
        ids.add(11l);
        ids.add(12l);
        criteria.andIn("id",ids);
        User user = new User();
        user.setName("小黑222");
        int account= userMapper.updateByExampleSelective(user,example);
        System.out.println(account);
        sqlSession.close();

    }


    @Test
    public void selectCountByExample(){

        Example example = new Example(User.class);

        Example.Criteria criteria = example.createCriteria();

        //criteria.andEqualTo("Name","小黑111");
        criteria.andLike("Name","李%");

        int account= userMapper.selectCountByExample(example);
        System.out.println(account);
        sqlSession.close();

    }



    @Test
    public void selectByExample() {

        Example example = new Example(User.class);

        Example.Criteria criteria = example.createCriteria();

        //criteria.andEqualTo("Name","小黑111");
        //criteria.andLike("Name","李%");
        criteria.andIsNotNull("userName");

        List<User> users = userMapper.selectByExample(example);
        System.out.println(users);
        sqlSession.close();
    }

    @Test
    public void findByPage() {

        Map<String,Object> map = new HashMap<>();
        map.put("start",0);
        map.put("pagesize",2);

        List<User> list = userMapper.findByPage(map);


        System.out.println(list);
        sqlSession.close();
    }

    //使用分页插件
    @Test
    public void pageHelper() {

        PageHelper.startPage(1,2);

        List<User> list = userMapper.select(null);

        PageInfo<User> info= new PageInfo<User>(list);

        List<User> list1 = info.getList();
        for (User user : list1) {
            System.out.println(user);
        }
        System.out.println(info);
        System.out.println("getPages"+info.getPages());
        System.out.println("getPageNum"+info.getPageNum());
        System.out.println("getEndRow"+info.getEndRow());
        System.out.println("getLastPage"+info.getLastPage());
        System.out.println("数组getNavigatepageNums"+info.getNavigatepageNums().toString());

        sqlSession.close();

    }




}
