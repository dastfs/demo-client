package kz.tdcis.poe.democlient.dto.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BlockChainTokenDto {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @JsonProperty("refresh_expires_in")
    private Integer refreshExpiresIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("error_code")
    private String errorCode;
}
