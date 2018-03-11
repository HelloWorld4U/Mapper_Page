package cn.itcast.mapper;

import cn.itcast.model.User;
import com.github.abel533.mapper.Mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User> {
    List<User> findByPage(Map<String, Object> map);
}
