package com.bytes.train.serviceImpl;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bytes.train.CustomerTicketingSupportApplication;
import com.bytes.train.entities.UserDetails;
import com.bytes.train.execptions.ResourceNotFoundException;
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

	private UserDetails validUserDetails;

	@BeforeEach
	public void setUp() {
		validUserDetails = new UserDetails();
		validUserDetails.setUserName("validUser");
		validUserDetails.setUserPassword("validPassword");
	}

//Positive Test cases
	@Test
	public void testLoginAccess_Success() throws Exception {
		String userName = "validUser";
		String userPassword = "validPassword";

		when(userRepository.findByUserName(userName)).thenReturn(validUserDetails);

		UserDetails result = userService.loginAccess(userName, userPassword);

		assertEquals(validUserDetails, result);
		verify(userRepository, times(1)).findByUserName(userName);
	}

////Negative Test Case For Login
	@Test
	public void testLoginAccess_IncorrectPassword() throws Exception {
		String userName = "validUser";

		String incorrectPassword = "wrongpassword";

		when(userRepository.findByUserName(userName)).thenReturn(validUserDetails);

		UserDetails result = userService.loginAccess(userName, incorrectPassword);

		assertNull(result);
		verify(userRepository, times(1)).findByUserName(userName);

	}

//   Negative Test Case For User Login
	@Test
	public void testLoginAccessWithInvalidUsername() throws Exception {
		// Arrange
		String invalidUserName = "invalidUser";
		String validPassword = "validPassword";
		when(userRepository.findByUserName(invalidUserName)).thenReturn(null);

		// Act and Assert
		assertThrows(ResourceNotFoundException.class, () -> {
			userService.loginAccess(invalidUserName, validPassword);
		});
	}

}
