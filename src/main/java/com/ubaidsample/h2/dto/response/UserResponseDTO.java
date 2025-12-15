/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.h2.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubaidsample.h2.annotation.MaskSensitiveData;
import com.ubaidsample.h2.dto.common.AuditHistoryDTO;
import com.ubaidsample.h2.enums.MaskSensitiveDataCategory;
import com.ubaidsample.h2.enums.MaskSensitiveDataStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("idempotency_key")
    private String idempotencyKey;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("email")
    @MaskSensitiveData(strategy = MaskSensitiveDataStrategy.PARTIAL)
    private String email;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("date_of_leaving")
    private LocalDate dateOfLeaving;

    @JsonProperty("postal_code")
    private Integer postalCode;
	
    @JsonProperty("account_number")
    @MaskSensitiveData(strategy = MaskSensitiveDataStrategy.LAST_FOUR, category = MaskSensitiveDataCategory.PCI)
    private String accountNumber;

    @JsonProperty("auditHistoryDTO")
    private AuditHistoryDTO auditHistoryDTO;
}