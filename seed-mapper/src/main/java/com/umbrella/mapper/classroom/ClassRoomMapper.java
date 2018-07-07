package com.umbrella.mapper.classroom;

import com.umbrella.model.ClassRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Description: 教室Mapper接口
 * </p>
 *
 * @author: 011096=>yangyunsen@inner.czy.com
 * @date: 2018-01-10
 * @version: 1.0
 */
@Mapper
@Repository
public interface ClassRoomMapper {

    /**
     * 查询教室列表
     * @param: []
     * @return: java.util.List<com.umbrella.model.ClassRoom>
     * @author: [011096]=>yangyunsen@inner.czy.com
     * @date: 2018/1/10
    */
    @Select("select * from class")
    List<ClassRoom> listClassRoom ();

    /**
     * 测试mapper.xml
     * @param: []
     * @return: java.util.List<com.umbrella.model.ClassRoom>
     * @author: [011096]=>yangyunsen@inner.czy.com
     * @date: 2018/1/10
    */
    List<ClassRoom> getClassRooms ();
}
