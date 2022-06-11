package com.example.batis.repository;

import com.example.batis.model.Region;
import com.example.batis.model.RegionDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

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
    List<RegionDTO> findAll();

    @ResultMap("regionResultMap")
    @Select("SELECT * FROM regions WHERE id = #{id}")
    Optional<RegionDTO> findById(long id);

    @ResultMap("regionResultMap")
    @Select("SELECT * FROM regions WHERE name = #{name} and short_name = #{shortName}")
    Optional<RegionDTO> findByNameAndShortname(Region region);

    @ResultMap("regionResultMap")
    @Delete("DELETE FROM regions WHERE id = #{id}")
    boolean deleteById(long id);

    @Transactional
    @ResultMap("regionResultMap")
    @Insert("INSERT into regions(name, short_name) VALUES(#{name}, #{shortName})")
    int addRegion(Region region);

    @ResultMap("regionResultMap")
    @Select("SELECT * FROM regions WHERE id = SCOPE_IDENTITY()")
    RegionDTO getLastRegion();

    @ResultMap("regionResultMap")
    @Update("UPDATE regions SET name=#{name}, short_name=#{shortName} WHERE id =#{id}")
    boolean updateRegion(RegionDTO region);
}
