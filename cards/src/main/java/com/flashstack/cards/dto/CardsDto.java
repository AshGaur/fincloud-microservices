package com.flashstack.cards.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardsDto {
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Pattern(regexp="(^$|[0-9]{12})",message = "CardNumber must be 12 digits")
    private String cardNumber;

    @NotEmpty(message = "CardType can not be a null or empty")
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    private Integer totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    private Integer amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    private Integer availableAmount;
}
