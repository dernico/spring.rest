package schlingel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schlingel.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}




//package schlingel.repository;
//
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.security.access.prepost.PreAuthorize;
//import schlingel.domain.User;
//import org.springframework.data.repository.CrudRepository;
//
//
//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
//public interface UserRepository extends CrudRepository<User, Long> {
//    User findByUsername(String username);
//
//    @Override
//    @PreAuthorize("ADMIN_USER")
//    void deleteById(Long id);
//
//    @Override
//    @PreAuthorize("ADMIN_USER")
//    void delete(User var1);
//
//    @Override
//    @PreAuthorize("ADMIN_USER")
//    void deleteAll(Iterable<? extends User> var1);
//
//    @Override
//    @PreAuthorize("ADMIN_USER")
//    void deleteAll();
//}
