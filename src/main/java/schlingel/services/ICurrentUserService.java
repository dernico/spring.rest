package schlingel.services;

import schlingel.domain.User;

public interface ICurrentUserService {
    User getUserFromRequest();
}
