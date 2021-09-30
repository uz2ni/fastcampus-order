package dev.practice.order.domain.partner;

import lombok.Getter;

@Getter
public class PartnerInfo {
    private final Long id;
    private final String partnerToken;
    private final String partnerName;
    private final String businessNo;
    private final String email;
    private final Partner.Status status;

    PartnerInfo(Partner partner) {
        this.id = partner.getId();
        this.partnerName = partner.getPartnerName();
        this.partnerToken = partner.getPartnerToken();
        this.businessNo = partner.getBusinessNo();
        this.email = partner.getEmail();
        this.status = partner.getStatus();
    }
}
