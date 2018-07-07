package com.umbrella.model;

import java.io.Serializable;

/**
 * <p>
 * Description: 教室实体类
 * </p>
 *
 * @author: 011096=>yangyunsen@inner.czy.com
 * @date: 2018-01-10
 * @version: 1.0
 */
public class ClassRoom implements Serializable {

    private static final long serialVersionUID = -8530891679491205926L;
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
