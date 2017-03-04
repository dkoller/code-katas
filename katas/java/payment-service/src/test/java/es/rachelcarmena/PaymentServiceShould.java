package es.rachelcarmena;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceShould {

	private static final User VALID_USER = new User();
	private static final User INVALID_USER = new User();
	private static final PaymentDetails PAYMENT_DETAILS = new PaymentDetails();

	private PaymentService paymentService;

	@Mock UserValidator userValidator;
	@Mock PaymentGateway paymentGateway;

	@Before
	public void initialize() {
		paymentService = new PaymentService(userValidator, paymentGateway);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throw_an_exception_when_user_is_invalid() throws Exception {
		given(userValidator.isValid(INVALID_USER)).willReturn(false);

		try {
			paymentService.processPayment(INVALID_USER, PAYMENT_DETAILS);
		} finally {
			verify(userValidator).isValid(INVALID_USER);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void not_process_payment_when_user_is_valid() throws Exception {
		given(userValidator.isValid(VALID_USER)).willReturn(false);

		try {
			paymentService.processPayment(VALID_USER, PAYMENT_DETAILS);
		} finally {
			verifyZeroInteractions(paymentGateway);
		}
	}

	@Test
	public void process_payment_when_user_is_valid() throws Exception {
		given(userValidator.isValid(VALID_USER)).willReturn(true);

		paymentService.processPayment(VALID_USER, PAYMENT_DETAILS);

		verify(paymentGateway).process(PAYMENT_DETAILS);
	}
}
