package model.services;

public interface OnlinePaymentService {

    Double interestRate = 0.0;
    Double paymentFeeRate = 0.0;

    double paymentFee(Double amount);
    double interest(Double amount, Integer months);
}
