package kz.tdcis.poe.democlient.dto.poeHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddProofResponse {
    public String certificate;
    public String sign;
    public String date_created;
    public String date_inserted;
    public String id;
    public String owner;
    public String proof;
    public String user;
    public String txHash;
}
