package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.time.LocalDate;

public class ContractService {

    private Integer months;
    private Contract contract;

    private OnlinePaymentService paymentService;

    public ContractService(Integer months, OnlinePaymentService paymentService) {
        this.months = months;
        this.paymentService = paymentService;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public OnlinePaymentService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(OnlinePaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processContract(Contract contract) {
        LocalDate contractStartDate = contract.getDate();

        if (months > 0) {
            double baseInstallment = contract.getTotalValue() / months;
            for (int i=0; i<months; i++) {
                LocalDate dueDate = contractStartDate.plusMonths(i+1);

                double interest = paymentService.interest(baseInstallment, i+1);
                double installmentWithInterest = baseInstallment + interest;

                double paymentFee = paymentService.paymentFee(installmentWithInterest);
                double finalAmount = baseInstallment + interest + paymentFee;

                contract.addInstallment(new Installment(dueDate, finalAmount));
        }

        }
    }
}
