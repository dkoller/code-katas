package es.rachelcarmena;

public class PaymentService {

	private UserValidator userValidator;
	private PaymentGateway paymentGateway;

	public PaymentService(UserValidator userValidator, PaymentGateway paymentGateway) {
		this.userValidator = userValidator;
		this.paymentGateway = paymentGateway;
	}

	public void processPayment(User user, PaymentDetails paymentDetails) {
		if (! userValidator.isValid(user))
			throw new IllegalArgumentException();
		paymentGateway.process(paymentDetails);		
	}
}
