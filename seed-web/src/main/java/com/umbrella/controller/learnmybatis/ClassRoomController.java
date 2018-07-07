package com.umbrella.controller.learnmybatis;

import com.umbrella.mapper.classroom.ClassRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Description: 教室REST控制器
 * </p>
 *
 * @author: 011096=>yangyunsen@inner.czy.com
 * @date: 2018-01-10
 * @version: 1.0
 */
@RestController
@RequestMapping("/class")
public class ClassRoomController {

    private ClassRoomMapper classRoomMapper;

    @Autowired
    public ClassRoomController (ClassRoomMapper classRoomMapper) {
        this.classRoomMapper = classRoomMapper;
    }

    @RequestMapping("/listClassRoom")
    @ResponseBody
    public Object listClassRoom () {
        return classRoomMapper.listClassRoom();
    }

    @RequestMapping("/getClassRoom")
    @ResponseBody
    public Object getClassRoom () {
        return classRoomMapper.getClassRooms();
    }
}
