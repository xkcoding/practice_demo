package com.xkcoding.mapstruct.model.entity;

import com.xkcoding.mapstruct.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * DO
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2021-07-13 10:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDO {
    private String make;
    private int numberOfSeats;
    private CarType type;

    //    public CarDO() {
    //    }
    //
    //    public CarDO(String make, int numberOfSeats, CarType type) {
    //        this.make = make;
    //        this.numberOfSeats = numberOfSeats;
    //        this.type = type;
    //    }
    //
    //    public String getMake() {
    //        return make;
    //    }
    //
    //    public void setMake(String make) {
    //        this.make = make;
    //    }
    //
    //    public int getNumberOfSeats() {
    //        return numberOfSeats;
    //    }
    //
    //    public void setNumberOfSeats(int numberOfSeats) {
    //        this.numberOfSeats = numberOfSeats;
    //    }
    //
    //    public CarType getType() {
    //        return type;
    //    }
    //
    //    public void setType(CarType type) {
    //        this.type = type;
    //    }
}
