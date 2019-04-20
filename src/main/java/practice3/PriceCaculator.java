package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {
    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;
    private BigDecimal subTotal;
    private BigDecimal taxTotal;
    private BigDecimal grandTotal;

    public PriceCaculator(Order order) {
        this.orderLineItemList = order.getOrderLineItemList();
        this.discounts = order.getDiscounts();
        this.tax = order.getTax();
    }

    public BigDecimal calculate() {
        calculateSubTotal();
        subtractDiscounts();
        calculateTax();
        calculateGrandTotal();

        return grandTotal;
    }

    private void calculateGrandTotal() {
        // calculate GrandTotal
        grandTotal = subTotal.add(taxTotal);
    }

    private void calculateTax() {
        // calculate tax
        taxTotal = subTotal.multiply(tax);
    }

    private void subtractDiscounts() {
        // Subtract discounts
        for (BigDecimal discount : discounts) {
            subTotal = subTotal.subtract(discount);
        }
    }

    private void calculateSubTotal() {
        subTotal = new BigDecimal(0);

        // Total up line items
        for (OrderLineItem lineItem : orderLineItemList) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
    }
}
