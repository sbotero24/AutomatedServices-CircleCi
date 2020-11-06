package pojouser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreate {
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    private String id;
    private String ciudad;
    private String pais;



}
