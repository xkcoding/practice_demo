package com.xkcoding.mapstruct.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * DTO
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2021-07-13 10:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private String make;
    private int seatCount;
    private String type;

//    public CarDTO() {
//    }
//
//    public CarDTO(String make, int seatCount, String type) {
//        this.make = make;
//        this.seatCount = seatCount;
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
//    public int getSeatCount() {
//        return seatCount;
//    }
//
//    public void setSeatCount(int seatCount) {
//        this.seatCount = seatCount;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
}