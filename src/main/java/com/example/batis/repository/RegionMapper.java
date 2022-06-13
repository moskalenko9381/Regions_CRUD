package com.example.batis.repository;

import com.example.batis.model.Region;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RegionMapper {
    @Results(id = "regionResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "shortName", column = "short_name")
    })
    @Select("select * from regions")
    List<Region> findAll();

    @ResultMap("regionResultMap")
    @Select("SELECT * FROM regions WHERE id = #{id}")
    Optional<Region> findById(long id);

    @ResultMap("regionResultMap")
    @Select("SELECT * FROM regions WHERE name = #{name} and short_name = #{shortName}")
    Optional<Region> findByNameAndShortname(String name, String shortName);

    @ResultMap("regionResultMap")
    @Delete("DELETE FROM regions WHERE id = #{id}")
    boolean deleteById(long id);

    @Insert("INSERT into regions(name, short_name) VALUES(#{name}, #{shortName})")
    int addRegion(String name, String shortName);

    @ResultMap("regionResultMap")
    @Update("UPDATE regions SET name=#{name}, short_name=#{shortName} WHERE id =#{id}")
    boolean updateRegion(Region region);
}
