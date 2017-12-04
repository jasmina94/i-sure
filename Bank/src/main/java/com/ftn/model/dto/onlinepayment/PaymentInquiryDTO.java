package com.ftn.model.dto.onlinepayment;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Jasmina on 04/12/2017.
 */
@Data
@NoArgsConstructor
public class PaymentInquiryDTO {

    @NotNull
    @Size(min = 30, max = 30)
    private String merchantId;

    @NotNull
    @Size(min = 100, max = 100)
    private String merchantPassword;

    @NotNull
    private double amount;

    @NotNull
    private int merchantTransactionId;

    @NotNull
    private Date merchantTimestamp;

    @NotNull
    private String errorUrl;
}
