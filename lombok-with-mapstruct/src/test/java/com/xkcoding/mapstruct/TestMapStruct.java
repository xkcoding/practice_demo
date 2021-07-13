package com.xkcoding.mapstruct;

import com.xkcoding.mapstruct.enums.CarType;
import com.xkcoding.mapstruct.model.dto.CarDTO;
import com.xkcoding.mapstruct.model.entity.CarDO;
import com.xkcoding.mapstruct.model.mapper.CarMapper;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * 测试
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2021-07-13 10:54
 */
public class TestMapStruct {
    @Test
    public void shouldMapCarToDto() {
        //given
        CarDO car = new CarDO("Morris", 5, CarType.SPORTS);

        //when
        CarDTO carDto = CarMapper.INSTANCE.carToCarDto(car);

        //then
        Assert.assertNotNull(carDto);
        Assert.assertEquals("Morris", carDto.getMake());
        Assert.assertEquals(5, carDto.getSeatCount());
        Assert.assertEquals("SPORTS", carDto.getType());
    }
}