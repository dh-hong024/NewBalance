package practice.newbalance.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.newbalance.domain.member.DeliveryAddress;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryAddressDto {
    private Long id;

    private String recipient;

    private String destination;

    private String recipientNumber;

    private String zipCode;

    private String address;

    private String detailAddress;

    private Boolean defaultYN;

}
