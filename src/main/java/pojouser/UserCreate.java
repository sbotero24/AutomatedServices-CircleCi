package pojouser;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class UserCreate {
	@Getter @Setter
    private String password;
	@Getter @Setter
    private String email;


}
