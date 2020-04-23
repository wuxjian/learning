package the.school.learning.service;

import org.springframework.stereotype.Service;
import the.school.learning.mapper.UserMapper;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
}
