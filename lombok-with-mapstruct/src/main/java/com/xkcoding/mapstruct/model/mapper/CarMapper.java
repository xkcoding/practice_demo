package com.xkcoding.mapstruct.model.mapper;

import com.xkcoding.mapstruct.model.dto.CarDTO;
import com.xkcoding.mapstruct.model.entity.CarDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 对象转化
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2021-07-13 10:47
 */
@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDTO carToCarDto(CarDO car);
}