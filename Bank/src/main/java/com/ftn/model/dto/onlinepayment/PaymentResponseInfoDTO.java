package com.ftn.model.dto.onlinepayment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Jasmina on 04/12/2017.
 */
@Data
@NoArgsConstructor
public class PaymentResponseInfoDTO {

    private long acquirerOrderId;

    private Date acquirerTimestamp;

    private long issuerOrderId;

    private Date issuerTimestamp;
}
