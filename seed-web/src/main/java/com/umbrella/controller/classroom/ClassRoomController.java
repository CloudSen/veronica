package com.umbrella.controller.classroom;

import com.umbrella.mapper.ClassRoomMapper;
import com.umbrella.model.ClassRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private ClassRoomMapper classRoomMapper;

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
