package schlingel;

import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import schlingel.controller.PersonController;
import schlingel.domain.User;
import schlingel.dto.UserDto;
import schlingel.repository.UserRepository;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
//@SpringBootTest
public class PersonControllerTest {

    @Autowired
    PersonController controller;

//    @Autowired
//    private MockMvc mockMvc;

    @MockBean
    private UserRepository repo;

    @org.junit.Test
    public void createUserAndMakeSurePasswordIsEncrypted() {

        UserDto dto = new UserDto();
        dto.setPassword("asd");
        dto.setFirstName("Nico");

        User newUser = dto.toDomain();

        when(repo.save(newUser)).thenReturn(newUser);
        newUser = controller.CreateUser(dto);

        assertThat("password starts with {bcrypt}", newUser.getPassword(), startsWith("{bcrypt}"));
        verify(repo).save(ArgumentMatchers.any(User.class));
    }
}