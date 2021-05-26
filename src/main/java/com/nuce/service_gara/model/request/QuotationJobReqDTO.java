package com.nuce.service_gara.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationJobReqDTO {
    private int jobId;

    private int price;

    @Override
    public String toString(){
        return this.jobId + ", " + this.price;
    }

}
