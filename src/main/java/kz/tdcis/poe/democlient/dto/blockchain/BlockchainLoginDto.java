package kz.tdcis.poe.democlient.dto.blockchain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlockchainLoginDto {
    private String org;
    private String user;
    private String password;

    @Override
    public String toString() {
        return "org=" + org + "&user=" + user + "&password=" + password;
    }
}
