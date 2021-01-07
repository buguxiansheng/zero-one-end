package com.lcfc.demo.modules.sys.mapper;

import com.lcfc.demo.modules.sys.dto.UserLoginDto;
import com.lcfc.demo.modules.sys.entity.Menu;
import com.lcfc.demo.modules.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * @author will
     * @param userName
     * @return password
     */
    String getUser(@Param("userName") String userName);


    /**
     * @author will
     * @param userNames
     * @return users
     */
    List<UserLoginDto> getUserList(@Param("names") List<String> userNames);

    /**
     * @author will
     * @param user
     * @param userName
     */
    void modifyUserInfo(@Param("user") User user,@Param("oldUserName") String userName);

    /**
     * @author will
     *
     * @param userName
     * @Return 该用户的菜单
     */
    List<Menu> getMenuList(@Param("userName") String userName);
}
