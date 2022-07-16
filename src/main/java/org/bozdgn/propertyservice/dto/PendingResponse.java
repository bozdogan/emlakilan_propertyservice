package org.bozdgn.propertyservice.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PendingResponse {
    PropertyOutput property;
    @JsonAlias("approve_link")
    String approveUrl;
    @JsonAlias("reject_link")
    String rejectUrl;
}
