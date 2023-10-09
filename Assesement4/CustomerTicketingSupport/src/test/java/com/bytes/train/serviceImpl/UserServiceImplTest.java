package com.bytes.train.serviceImpl;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bytes.train.CustomerTicketingSupportApplication;
import com.bytes.train.entities.UserDetails;
import com.bytes.train.repos.AgentRepository;
import com.bytes.train.repos.UserRepository;
import com.bytes.train.service.UserService;

@SpringBootTest(classes = CustomerTicketingSupportApplication.class)
public class UserServiceImplTest {
	@MockBean
	private AgentRepository agentRepository;

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

    @Test
    public void testLoginAccess_Success() throws Exception {
        String userName = "user123";
        String userPassword = "password123";

        UserDetails userDetails = new UserDetails();
        userDetails.setUserName(userName);
        userDetails.setUserPassword(userPassword);

        when(userRepository.findByUserName(userName)).thenReturn(userDetails);

        UserDetails result = userService.loginAccess(userName, userPassword);

        assertEquals(userDetails, result);
        verify(userRepository, times(1)).findByUserName(userName);
    }

    @Test
    public void testLoginAccess_IncorrectPassword() throws Exception {
        String userName = "user123";
        String correctPassword = "password123";
        String incorrectPassword = "wrongpassword";

        UserDetails userDetails = new UserDetails();
        userDetails.setUserName(userName);
        userDetails.setUserPassword(correctPassword);

        when(userRepository.findByUserName(userName)).thenReturn(userDetails);

        UserDetails result = userService.loginAccess(userName, incorrectPassword);

        assertNull(result);
        verify(userRepository, times(1)).findByUserName(userName);
    }

}
