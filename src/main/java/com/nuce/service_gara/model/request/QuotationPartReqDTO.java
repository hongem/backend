package com.nuce.service_gara.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationPartReqDTO {
    private int partId;

    private int price;

    private int number;

    @Override
    public String toString(){
        return this.partId + ", " + this.number + ", " + this.price;
    }
}
