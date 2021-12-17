package com.company;

public class Address {
    private  boolean valid;
    private int postCode;
    private String address;

    public Address(boolean valid, int postCode, String address) {
        this.valid = valid;
        this.postCode = postCode;
        this.address = address;
    }

    public double calculateTax(double cost) throws TaxCalculationNotAvailableException, InvalidAddressException {
        if (!this.valid) {
            throw new InvalidAddressException("AddressNotValid");
        }
        throw new TaxCalculationNotAvailableException("TaxCalculationNotAvailable");
    }

    public class TaxCalculationNotAvailableException extends Exception {
        public TaxCalculationNotAvailableException(String errorMessage) {
            super(errorMessage);
        }
    }

    public class InvalidAddressException extends Exception {
        public InvalidAddressException(String errorMessage) {
            super(errorMessage);
        }
    }
}
