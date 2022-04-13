package com.shippingoo.resource.job;

import com.shippingoo.entity.Job;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
public class GetAJobForSeller extends Job {
//    applyStatus 0 -> You did not apply but you can apply
//    applyStatus 1 -> you already Applied
    private int applyStatus;


    private Long buyerId;
    public Long getBuyerId(){
        return  null;
    }

    private LocalDateTime createdAt;

}
