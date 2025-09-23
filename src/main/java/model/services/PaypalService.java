package model.services;

public class PaypalService implements OnlinePaymentService{

    Double interestRate = 0.01;
    Double paymentFeeRate = 0.02;

    public double paymentFee(Double amount) {
        return amount * paymentFeeRate;
    }
    public double interest(Double amount, Integer months) {
        return amount * interestRate * months;
    }

}
